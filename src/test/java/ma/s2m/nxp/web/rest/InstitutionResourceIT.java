package ma.s2m.nxp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import ma.s2m.nxp.IntegrationTest;
import ma.s2m.nxp.domain.Institution;
import ma.s2m.nxp.repository.InstitutionRepository;
import ma.s2m.nxp.service.dto.InstitutionDTO;
import ma.s2m.nxp.service.mapper.InstitutionMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link InstitutionResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class InstitutionResourceIT {

    private static final String DEFAULT_IDENTIFIER = "AAAAAAAAAA";
    private static final String UPDATED_IDENTIFIER = "BBBBBBBBBB";

    private static final String DEFAULT_LABEL = "AAAAAAAAAA";
    private static final String UPDATED_LABEL = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREATION_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATION_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_INS_CHLL_INFR_HEDR = "AAAAAAAAAA";
    private static final String UPDATED_INS_CHLL_INFR_HEDR = "BBBBBBBBBB";

    private static final String DEFAULT_INS_CHLL_INFR_TEXT = "AAAAAAAAAA";
    private static final String UPDATED_INS_CHLL_INFR_TEXT = "BBBBBBBBBB";

    private static final String DEFAULT_INS_CHLL_INFR_LABL = "AAAAAAAAAA";
    private static final String UPDATED_INS_CHLL_INFR_LABL = "BBBBBBBBBB";

    private static final String DEFAULT_INS_SBMT_ATHN_LABL = "AAAAAAAAAA";
    private static final String UPDATED_INS_SBMT_ATHN_LABL = "BBBBBBBBBB";

    private static final String DEFAULT_INS_RSND_INFR_LABL = "AAAAAAAAAA";
    private static final String UPDATED_INS_RSND_INFR_LABL = "BBBBBBBBBB";

    private static final String DEFAULT_INS_WHY_INFR_LABL = "AAAAAAAAAA";
    private static final String UPDATED_INS_WHY_INFR_LABL = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/institutions";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{instCode}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private InstitutionRepository institutionRepository;

    @Autowired
    private InstitutionMapper institutionMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInstitutionMockMvc;

    private Institution institution;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Institution createEntity(EntityManager em) {
        Institution institution = new Institution()
            .identifier(DEFAULT_IDENTIFIER)
            .label(DEFAULT_LABEL)
            .address(DEFAULT_ADDRESS)
            .creationDate(DEFAULT_CREATION_DATE)
            .insChllInfrHedr(DEFAULT_INS_CHLL_INFR_HEDR)
            .insChllInfrText(DEFAULT_INS_CHLL_INFR_TEXT)
            .insChllInfrLabl(DEFAULT_INS_CHLL_INFR_LABL)
            .insSbmtAthnLabl(DEFAULT_INS_SBMT_ATHN_LABL)
            .insRsndInfrLabl(DEFAULT_INS_RSND_INFR_LABL)
            .insWhyInfrLabl(DEFAULT_INS_WHY_INFR_LABL);
        return institution;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Institution createUpdatedEntity(EntityManager em) {
        Institution institution = new Institution()
            .identifier(UPDATED_IDENTIFIER)
            .label(UPDATED_LABEL)
            .address(UPDATED_ADDRESS)
            .creationDate(UPDATED_CREATION_DATE)
            .insChllInfrHedr(UPDATED_INS_CHLL_INFR_HEDR)
            .insChllInfrText(UPDATED_INS_CHLL_INFR_TEXT)
            .insChllInfrLabl(UPDATED_INS_CHLL_INFR_LABL)
            .insSbmtAthnLabl(UPDATED_INS_SBMT_ATHN_LABL)
            .insRsndInfrLabl(UPDATED_INS_RSND_INFR_LABL)
            .insWhyInfrLabl(UPDATED_INS_WHY_INFR_LABL);
        return institution;
    }

    @BeforeEach
    public void initTest() {
        institution = createEntity(em);
    }

    @Test
    @Transactional
    void createInstitution() throws Exception {
        int databaseSizeBeforeCreate = institutionRepository.findAll().size();
        // Create the Institution
        InstitutionDTO institutionDTO = institutionMapper.toDto(institution);
        restInstitutionMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(institutionDTO))
            )
            .andExpect(status().isCreated());

        // Validate the Institution in the database
        List<Institution> institutionList = institutionRepository.findAll();
        assertThat(institutionList).hasSize(databaseSizeBeforeCreate + 1);
        Institution testInstitution = institutionList.get(institutionList.size() - 1);
        assertThat(testInstitution.getIdentifier()).isEqualTo(DEFAULT_IDENTIFIER);
        assertThat(testInstitution.getLabel()).isEqualTo(DEFAULT_LABEL);
        assertThat(testInstitution.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testInstitution.getCreationDate()).isEqualTo(DEFAULT_CREATION_DATE);
        assertThat(testInstitution.getInsChllInfrHedr()).isEqualTo(DEFAULT_INS_CHLL_INFR_HEDR);
        assertThat(testInstitution.getInsChllInfrText()).isEqualTo(DEFAULT_INS_CHLL_INFR_TEXT);
        assertThat(testInstitution.getInsChllInfrLabl()).isEqualTo(DEFAULT_INS_CHLL_INFR_LABL);
        assertThat(testInstitution.getInsSbmtAthnLabl()).isEqualTo(DEFAULT_INS_SBMT_ATHN_LABL);
        assertThat(testInstitution.getInsRsndInfrLabl()).isEqualTo(DEFAULT_INS_RSND_INFR_LABL);
        assertThat(testInstitution.getInsWhyInfrLabl()).isEqualTo(DEFAULT_INS_WHY_INFR_LABL);
    }

    @Test
    @Transactional
    void createInstitutionWithExistingId() throws Exception {
        // Create the Institution with an existing ID
        institution.setInstCode(1L);
        InstitutionDTO institutionDTO = institutionMapper.toDto(institution);

        int databaseSizeBeforeCreate = institutionRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restInstitutionMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(institutionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Institution in the database
        List<Institution> institutionList = institutionRepository.findAll();
        assertThat(institutionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllInstitutions() throws Exception {
        // Initialize the database
        institutionRepository.saveAndFlush(institution);

        // Get all the institutionList
        restInstitutionMockMvc
            .perform(get(ENTITY_API_URL + "?sort=instCode,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].instCode").value(hasItem(institution.getInstCode().intValue())))
            .andExpect(jsonPath("$.[*].identifier").value(hasItem(DEFAULT_IDENTIFIER)))
            .andExpect(jsonPath("$.[*].label").value(hasItem(DEFAULT_LABEL)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].creationDate").value(hasItem(DEFAULT_CREATION_DATE.toString())))
            .andExpect(jsonPath("$.[*].insChllInfrHedr").value(hasItem(DEFAULT_INS_CHLL_INFR_HEDR)))
            .andExpect(jsonPath("$.[*].insChllInfrText").value(hasItem(DEFAULT_INS_CHLL_INFR_TEXT)))
            .andExpect(jsonPath("$.[*].insChllInfrLabl").value(hasItem(DEFAULT_INS_CHLL_INFR_LABL)))
            .andExpect(jsonPath("$.[*].insSbmtAthnLabl").value(hasItem(DEFAULT_INS_SBMT_ATHN_LABL)))
            .andExpect(jsonPath("$.[*].insRsndInfrLabl").value(hasItem(DEFAULT_INS_RSND_INFR_LABL)))
            .andExpect(jsonPath("$.[*].insWhyInfrLabl").value(hasItem(DEFAULT_INS_WHY_INFR_LABL)));
    }

    @Test
    @Transactional
    void getInstitution() throws Exception {
        // Initialize the database
        institutionRepository.saveAndFlush(institution);

        // Get the institution
        restInstitutionMockMvc
            .perform(get(ENTITY_API_URL_ID, institution.getInstCode()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.instCode").value(institution.getInstCode().intValue()))
            .andExpect(jsonPath("$.identifier").value(DEFAULT_IDENTIFIER))
            .andExpect(jsonPath("$.label").value(DEFAULT_LABEL))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS))
            .andExpect(jsonPath("$.creationDate").value(DEFAULT_CREATION_DATE.toString()))
            .andExpect(jsonPath("$.insChllInfrHedr").value(DEFAULT_INS_CHLL_INFR_HEDR))
            .andExpect(jsonPath("$.insChllInfrText").value(DEFAULT_INS_CHLL_INFR_TEXT))
            .andExpect(jsonPath("$.insChllInfrLabl").value(DEFAULT_INS_CHLL_INFR_LABL))
            .andExpect(jsonPath("$.insSbmtAthnLabl").value(DEFAULT_INS_SBMT_ATHN_LABL))
            .andExpect(jsonPath("$.insRsndInfrLabl").value(DEFAULT_INS_RSND_INFR_LABL))
            .andExpect(jsonPath("$.insWhyInfrLabl").value(DEFAULT_INS_WHY_INFR_LABL));
    }

    @Test
    @Transactional
    void getNonExistingInstitution() throws Exception {
        // Get the institution
        restInstitutionMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewInstitution() throws Exception {
        // Initialize the database
        institutionRepository.saveAndFlush(institution);

        int databaseSizeBeforeUpdate = institutionRepository.findAll().size();

        // Update the institution
        Institution updatedInstitution = institutionRepository.findById(institution.getInstCode()).get();
        // Disconnect from session so that the updates on updatedInstitution are not directly saved in db
        em.detach(updatedInstitution);
        updatedInstitution
            .identifier(UPDATED_IDENTIFIER)
            .label(UPDATED_LABEL)
            .address(UPDATED_ADDRESS)
            .creationDate(UPDATED_CREATION_DATE)
            .insChllInfrHedr(UPDATED_INS_CHLL_INFR_HEDR)
            .insChllInfrText(UPDATED_INS_CHLL_INFR_TEXT)
            .insChllInfrLabl(UPDATED_INS_CHLL_INFR_LABL)
            .insSbmtAthnLabl(UPDATED_INS_SBMT_ATHN_LABL)
            .insRsndInfrLabl(UPDATED_INS_RSND_INFR_LABL)
            .insWhyInfrLabl(UPDATED_INS_WHY_INFR_LABL);
        InstitutionDTO institutionDTO = institutionMapper.toDto(updatedInstitution);

        restInstitutionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, institutionDTO.getInstCode())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(institutionDTO))
            )
            .andExpect(status().isOk());

        // Validate the Institution in the database
        List<Institution> institutionList = institutionRepository.findAll();
        assertThat(institutionList).hasSize(databaseSizeBeforeUpdate);
        Institution testInstitution = institutionList.get(institutionList.size() - 1);
        assertThat(testInstitution.getIdentifier()).isEqualTo(UPDATED_IDENTIFIER);
        assertThat(testInstitution.getLabel()).isEqualTo(UPDATED_LABEL);
        assertThat(testInstitution.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testInstitution.getCreationDate()).isEqualTo(UPDATED_CREATION_DATE);
        assertThat(testInstitution.getInsChllInfrHedr()).isEqualTo(UPDATED_INS_CHLL_INFR_HEDR);
        assertThat(testInstitution.getInsChllInfrText()).isEqualTo(UPDATED_INS_CHLL_INFR_TEXT);
        assertThat(testInstitution.getInsChllInfrLabl()).isEqualTo(UPDATED_INS_CHLL_INFR_LABL);
        assertThat(testInstitution.getInsSbmtAthnLabl()).isEqualTo(UPDATED_INS_SBMT_ATHN_LABL);
        assertThat(testInstitution.getInsRsndInfrLabl()).isEqualTo(UPDATED_INS_RSND_INFR_LABL);
        assertThat(testInstitution.getInsWhyInfrLabl()).isEqualTo(UPDATED_INS_WHY_INFR_LABL);
    }

    @Test
    @Transactional
    void putNonExistingInstitution() throws Exception {
        int databaseSizeBeforeUpdate = institutionRepository.findAll().size();
        institution.setInstCode(count.incrementAndGet());

        // Create the Institution
        InstitutionDTO institutionDTO = institutionMapper.toDto(institution);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInstitutionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, institutionDTO.getInstCode())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(institutionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Institution in the database
        List<Institution> institutionList = institutionRepository.findAll();
        assertThat(institutionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchInstitution() throws Exception {
        int databaseSizeBeforeUpdate = institutionRepository.findAll().size();
        institution.setInstCode(count.incrementAndGet());

        // Create the Institution
        InstitutionDTO institutionDTO = institutionMapper.toDto(institution);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInstitutionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(institutionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Institution in the database
        List<Institution> institutionList = institutionRepository.findAll();
        assertThat(institutionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamInstitution() throws Exception {
        int databaseSizeBeforeUpdate = institutionRepository.findAll().size();
        institution.setInstCode(count.incrementAndGet());

        // Create the Institution
        InstitutionDTO institutionDTO = institutionMapper.toDto(institution);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInstitutionMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(institutionDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Institution in the database
        List<Institution> institutionList = institutionRepository.findAll();
        assertThat(institutionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateInstitutionWithPatch() throws Exception {
        // Initialize the database
        institutionRepository.saveAndFlush(institution);

        int databaseSizeBeforeUpdate = institutionRepository.findAll().size();

        // Update the institution using partial update
        Institution partialUpdatedInstitution = new Institution();
        partialUpdatedInstitution.setInstCode(institution.getInstCode());

        partialUpdatedInstitution
            .label(UPDATED_LABEL)
            .address(UPDATED_ADDRESS)
            .creationDate(UPDATED_CREATION_DATE)
            .insChllInfrText(UPDATED_INS_CHLL_INFR_TEXT)
            .insChllInfrLabl(UPDATED_INS_CHLL_INFR_LABL)
            .insWhyInfrLabl(UPDATED_INS_WHY_INFR_LABL);

        restInstitutionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedInstitution.getInstCode())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedInstitution))
            )
            .andExpect(status().isOk());

        // Validate the Institution in the database
        List<Institution> institutionList = institutionRepository.findAll();
        assertThat(institutionList).hasSize(databaseSizeBeforeUpdate);
        Institution testInstitution = institutionList.get(institutionList.size() - 1);
        assertThat(testInstitution.getIdentifier()).isEqualTo(DEFAULT_IDENTIFIER);
        assertThat(testInstitution.getLabel()).isEqualTo(UPDATED_LABEL);
        assertThat(testInstitution.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testInstitution.getCreationDate()).isEqualTo(UPDATED_CREATION_DATE);
        assertThat(testInstitution.getInsChllInfrHedr()).isEqualTo(DEFAULT_INS_CHLL_INFR_HEDR);
        assertThat(testInstitution.getInsChllInfrText()).isEqualTo(UPDATED_INS_CHLL_INFR_TEXT);
        assertThat(testInstitution.getInsChllInfrLabl()).isEqualTo(UPDATED_INS_CHLL_INFR_LABL);
        assertThat(testInstitution.getInsSbmtAthnLabl()).isEqualTo(DEFAULT_INS_SBMT_ATHN_LABL);
        assertThat(testInstitution.getInsRsndInfrLabl()).isEqualTo(DEFAULT_INS_RSND_INFR_LABL);
        assertThat(testInstitution.getInsWhyInfrLabl()).isEqualTo(UPDATED_INS_WHY_INFR_LABL);
    }

    @Test
    @Transactional
    void fullUpdateInstitutionWithPatch() throws Exception {
        // Initialize the database
        institutionRepository.saveAndFlush(institution);

        int databaseSizeBeforeUpdate = institutionRepository.findAll().size();

        // Update the institution using partial update
        Institution partialUpdatedInstitution = new Institution();
        partialUpdatedInstitution.setInstCode(institution.getInstCode());

        partialUpdatedInstitution
            .identifier(UPDATED_IDENTIFIER)
            .label(UPDATED_LABEL)
            .address(UPDATED_ADDRESS)
            .creationDate(UPDATED_CREATION_DATE)
            .insChllInfrHedr(UPDATED_INS_CHLL_INFR_HEDR)
            .insChllInfrText(UPDATED_INS_CHLL_INFR_TEXT)
            .insChllInfrLabl(UPDATED_INS_CHLL_INFR_LABL)
            .insSbmtAthnLabl(UPDATED_INS_SBMT_ATHN_LABL)
            .insRsndInfrLabl(UPDATED_INS_RSND_INFR_LABL)
            .insWhyInfrLabl(UPDATED_INS_WHY_INFR_LABL);

        restInstitutionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedInstitution.getInstCode())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedInstitution))
            )
            .andExpect(status().isOk());

        // Validate the Institution in the database
        List<Institution> institutionList = institutionRepository.findAll();
        assertThat(institutionList).hasSize(databaseSizeBeforeUpdate);
        Institution testInstitution = institutionList.get(institutionList.size() - 1);
        assertThat(testInstitution.getIdentifier()).isEqualTo(UPDATED_IDENTIFIER);
        assertThat(testInstitution.getLabel()).isEqualTo(UPDATED_LABEL);
        assertThat(testInstitution.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testInstitution.getCreationDate()).isEqualTo(UPDATED_CREATION_DATE);
        assertThat(testInstitution.getInsChllInfrHedr()).isEqualTo(UPDATED_INS_CHLL_INFR_HEDR);
        assertThat(testInstitution.getInsChllInfrText()).isEqualTo(UPDATED_INS_CHLL_INFR_TEXT);
        assertThat(testInstitution.getInsChllInfrLabl()).isEqualTo(UPDATED_INS_CHLL_INFR_LABL);
        assertThat(testInstitution.getInsSbmtAthnLabl()).isEqualTo(UPDATED_INS_SBMT_ATHN_LABL);
        assertThat(testInstitution.getInsRsndInfrLabl()).isEqualTo(UPDATED_INS_RSND_INFR_LABL);
        assertThat(testInstitution.getInsWhyInfrLabl()).isEqualTo(UPDATED_INS_WHY_INFR_LABL);
    }

    @Test
    @Transactional
    void patchNonExistingInstitution() throws Exception {
        int databaseSizeBeforeUpdate = institutionRepository.findAll().size();
        institution.setInstCode(count.incrementAndGet());

        // Create the Institution
        InstitutionDTO institutionDTO = institutionMapper.toDto(institution);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInstitutionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, institutionDTO.getInstCode())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(institutionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Institution in the database
        List<Institution> institutionList = institutionRepository.findAll();
        assertThat(institutionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchInstitution() throws Exception {
        int databaseSizeBeforeUpdate = institutionRepository.findAll().size();
        institution.setInstCode(count.incrementAndGet());

        // Create the Institution
        InstitutionDTO institutionDTO = institutionMapper.toDto(institution);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInstitutionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(institutionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Institution in the database
        List<Institution> institutionList = institutionRepository.findAll();
        assertThat(institutionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamInstitution() throws Exception {
        int databaseSizeBeforeUpdate = institutionRepository.findAll().size();
        institution.setInstCode(count.incrementAndGet());

        // Create the Institution
        InstitutionDTO institutionDTO = institutionMapper.toDto(institution);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInstitutionMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(institutionDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Institution in the database
        List<Institution> institutionList = institutionRepository.findAll();
        assertThat(institutionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteInstitution() throws Exception {
        // Initialize the database
        institutionRepository.saveAndFlush(institution);

        int databaseSizeBeforeDelete = institutionRepository.findAll().size();

        // Delete the institution
        restInstitutionMockMvc
            .perform(delete(ENTITY_API_URL_ID, institution.getInstCode()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Institution> institutionList = institutionRepository.findAll();
        assertThat(institutionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
