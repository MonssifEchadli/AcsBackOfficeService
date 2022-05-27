package ma.s2m.nxp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import ma.s2m.nxp.IntegrationTest;
import ma.s2m.nxp.domain.Logo;
import ma.s2m.nxp.repository.LogoRepository;
import ma.s2m.nxp.service.dto.LogoDTO;
import ma.s2m.nxp.service.mapper.LogoMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

/**
 * Integration tests for the {@link LogoResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class LogoResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MIME = "AAAAAAAAAA";
    private static final String UPDATED_MIME = "BBBBBBBBBB";

    private static final Long DEFAULT_LENGTH = 1L;
    private static final Long UPDATED_LENGTH = 2L;

    private static final byte[] DEFAULT_DATA = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_DATA = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_DATA_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_DATA_CONTENT_TYPE = "image/png";

    private static final String ENTITY_API_URL = "/api/logos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{logCode}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private LogoRepository logoRepository;

    @Autowired
    private LogoMapper logoMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLogoMockMvc;

    private Logo logo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Logo createEntity(EntityManager em) {
        Logo logo = new Logo()
            .name(DEFAULT_NAME)
            .mime(DEFAULT_MIME)
            .length(DEFAULT_LENGTH)
            .data(DEFAULT_DATA)
            .dataContentType(DEFAULT_DATA_CONTENT_TYPE);
        return logo;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Logo createUpdatedEntity(EntityManager em) {
        Logo logo = new Logo()
            .name(UPDATED_NAME)
            .mime(UPDATED_MIME)
            .length(UPDATED_LENGTH)
            .data(UPDATED_DATA)
            .dataContentType(UPDATED_DATA_CONTENT_TYPE);
        return logo;
    }

    @BeforeEach
    public void initTest() {
        logo = createEntity(em);
    }

    @Test
    @Transactional
    void createLogo() throws Exception {
        int databaseSizeBeforeCreate = logoRepository.findAll().size();
        // Create the Logo
        LogoDTO logoDTO = logoMapper.toDto(logo);
        restLogoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(logoDTO)))
            .andExpect(status().isCreated());

        // Validate the Logo in the database
        List<Logo> logoList = logoRepository.findAll();
        assertThat(logoList).hasSize(databaseSizeBeforeCreate + 1);
        Logo testLogo = logoList.get(logoList.size() - 1);
        assertThat(testLogo.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testLogo.getMime()).isEqualTo(DEFAULT_MIME);
        assertThat(testLogo.getLength()).isEqualTo(DEFAULT_LENGTH);
        assertThat(testLogo.getData()).isEqualTo(DEFAULT_DATA);
        assertThat(testLogo.getDataContentType()).isEqualTo(DEFAULT_DATA_CONTENT_TYPE);
    }

    @Test
    @Transactional
    void createLogoWithExistingId() throws Exception {
        // Create the Logo with an existing ID
        logo.setLogCode(1L);
        LogoDTO logoDTO = logoMapper.toDto(logo);

        int databaseSizeBeforeCreate = logoRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restLogoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(logoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Logo in the database
        List<Logo> logoList = logoRepository.findAll();
        assertThat(logoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllLogos() throws Exception {
        // Initialize the database
        logoRepository.saveAndFlush(logo);

        // Get all the logoList
        restLogoMockMvc
            .perform(get(ENTITY_API_URL + "?sort=logCode,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].logCode").value(hasItem(logo.getLogCode().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].mime").value(hasItem(DEFAULT_MIME)))
            .andExpect(jsonPath("$.[*].length").value(hasItem(DEFAULT_LENGTH.intValue())))
            .andExpect(jsonPath("$.[*].dataContentType").value(hasItem(DEFAULT_DATA_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].data").value(hasItem(Base64Utils.encodeToString(DEFAULT_DATA))));
    }

    @Test
    @Transactional
    void getLogo() throws Exception {
        // Initialize the database
        logoRepository.saveAndFlush(logo);

        // Get the logo
        restLogoMockMvc
            .perform(get(ENTITY_API_URL_ID, logo.getLogCode()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.logCode").value(logo.getLogCode().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.mime").value(DEFAULT_MIME))
            .andExpect(jsonPath("$.length").value(DEFAULT_LENGTH.intValue()))
            .andExpect(jsonPath("$.dataContentType").value(DEFAULT_DATA_CONTENT_TYPE))
            .andExpect(jsonPath("$.data").value(Base64Utils.encodeToString(DEFAULT_DATA)));
    }

    @Test
    @Transactional
    void getNonExistingLogo() throws Exception {
        // Get the logo
        restLogoMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewLogo() throws Exception {
        // Initialize the database
        logoRepository.saveAndFlush(logo);

        int databaseSizeBeforeUpdate = logoRepository.findAll().size();

        // Update the logo
        Logo updatedLogo = logoRepository.findById(logo.getLogCode()).get();
        // Disconnect from session so that the updates on updatedLogo are not directly saved in db
        em.detach(updatedLogo);
        updatedLogo
            .name(UPDATED_NAME)
            .mime(UPDATED_MIME)
            .length(UPDATED_LENGTH)
            .data(UPDATED_DATA)
            .dataContentType(UPDATED_DATA_CONTENT_TYPE);
        LogoDTO logoDTO = logoMapper.toDto(updatedLogo);

        restLogoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, logoDTO.getLogCode())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(logoDTO))
            )
            .andExpect(status().isOk());

        // Validate the Logo in the database
        List<Logo> logoList = logoRepository.findAll();
        assertThat(logoList).hasSize(databaseSizeBeforeUpdate);
        Logo testLogo = logoList.get(logoList.size() - 1);
        assertThat(testLogo.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testLogo.getMime()).isEqualTo(UPDATED_MIME);
        assertThat(testLogo.getLength()).isEqualTo(UPDATED_LENGTH);
        assertThat(testLogo.getData()).isEqualTo(UPDATED_DATA);
        assertThat(testLogo.getDataContentType()).isEqualTo(UPDATED_DATA_CONTENT_TYPE);
    }

    @Test
    @Transactional
    void putNonExistingLogo() throws Exception {
        int databaseSizeBeforeUpdate = logoRepository.findAll().size();
        logo.setLogCode(count.incrementAndGet());

        // Create the Logo
        LogoDTO logoDTO = logoMapper.toDto(logo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLogoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, logoDTO.getLogCode())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(logoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Logo in the database
        List<Logo> logoList = logoRepository.findAll();
        assertThat(logoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchLogo() throws Exception {
        int databaseSizeBeforeUpdate = logoRepository.findAll().size();
        logo.setLogCode(count.incrementAndGet());

        // Create the Logo
        LogoDTO logoDTO = logoMapper.toDto(logo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLogoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(logoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Logo in the database
        List<Logo> logoList = logoRepository.findAll();
        assertThat(logoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamLogo() throws Exception {
        int databaseSizeBeforeUpdate = logoRepository.findAll().size();
        logo.setLogCode(count.incrementAndGet());

        // Create the Logo
        LogoDTO logoDTO = logoMapper.toDto(logo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLogoMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(logoDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Logo in the database
        List<Logo> logoList = logoRepository.findAll();
        assertThat(logoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateLogoWithPatch() throws Exception {
        // Initialize the database
        logoRepository.saveAndFlush(logo);

        int databaseSizeBeforeUpdate = logoRepository.findAll().size();

        // Update the logo using partial update
        Logo partialUpdatedLogo = new Logo();
        partialUpdatedLogo.setLogCode(logo.getLogCode());

        partialUpdatedLogo.mime(UPDATED_MIME).length(UPDATED_LENGTH);

        restLogoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLogo.getLogCode())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedLogo))
            )
            .andExpect(status().isOk());

        // Validate the Logo in the database
        List<Logo> logoList = logoRepository.findAll();
        assertThat(logoList).hasSize(databaseSizeBeforeUpdate);
        Logo testLogo = logoList.get(logoList.size() - 1);
        assertThat(testLogo.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testLogo.getMime()).isEqualTo(UPDATED_MIME);
        assertThat(testLogo.getLength()).isEqualTo(UPDATED_LENGTH);
        assertThat(testLogo.getData()).isEqualTo(DEFAULT_DATA);
        assertThat(testLogo.getDataContentType()).isEqualTo(DEFAULT_DATA_CONTENT_TYPE);
    }

    @Test
    @Transactional
    void fullUpdateLogoWithPatch() throws Exception {
        // Initialize the database
        logoRepository.saveAndFlush(logo);

        int databaseSizeBeforeUpdate = logoRepository.findAll().size();

        // Update the logo using partial update
        Logo partialUpdatedLogo = new Logo();
        partialUpdatedLogo.setLogCode(logo.getLogCode());

        partialUpdatedLogo
            .name(UPDATED_NAME)
            .mime(UPDATED_MIME)
            .length(UPDATED_LENGTH)
            .data(UPDATED_DATA)
            .dataContentType(UPDATED_DATA_CONTENT_TYPE);

        restLogoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLogo.getLogCode())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedLogo))
            )
            .andExpect(status().isOk());

        // Validate the Logo in the database
        List<Logo> logoList = logoRepository.findAll();
        assertThat(logoList).hasSize(databaseSizeBeforeUpdate);
        Logo testLogo = logoList.get(logoList.size() - 1);
        assertThat(testLogo.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testLogo.getMime()).isEqualTo(UPDATED_MIME);
        assertThat(testLogo.getLength()).isEqualTo(UPDATED_LENGTH);
        assertThat(testLogo.getData()).isEqualTo(UPDATED_DATA);
        assertThat(testLogo.getDataContentType()).isEqualTo(UPDATED_DATA_CONTENT_TYPE);
    }

    @Test
    @Transactional
    void patchNonExistingLogo() throws Exception {
        int databaseSizeBeforeUpdate = logoRepository.findAll().size();
        logo.setLogCode(count.incrementAndGet());

        // Create the Logo
        LogoDTO logoDTO = logoMapper.toDto(logo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLogoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, logoDTO.getLogCode())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(logoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Logo in the database
        List<Logo> logoList = logoRepository.findAll();
        assertThat(logoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchLogo() throws Exception {
        int databaseSizeBeforeUpdate = logoRepository.findAll().size();
        logo.setLogCode(count.incrementAndGet());

        // Create the Logo
        LogoDTO logoDTO = logoMapper.toDto(logo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLogoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(logoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Logo in the database
        List<Logo> logoList = logoRepository.findAll();
        assertThat(logoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamLogo() throws Exception {
        int databaseSizeBeforeUpdate = logoRepository.findAll().size();
        logo.setLogCode(count.incrementAndGet());

        // Create the Logo
        LogoDTO logoDTO = logoMapper.toDto(logo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLogoMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(logoDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Logo in the database
        List<Logo> logoList = logoRepository.findAll();
        assertThat(logoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteLogo() throws Exception {
        // Initialize the database
        logoRepository.saveAndFlush(logo);

        int databaseSizeBeforeDelete = logoRepository.findAll().size();

        // Delete the logo
        restLogoMockMvc
            .perform(delete(ENTITY_API_URL_ID, logo.getLogCode()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Logo> logoList = logoRepository.findAll();
        assertThat(logoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
