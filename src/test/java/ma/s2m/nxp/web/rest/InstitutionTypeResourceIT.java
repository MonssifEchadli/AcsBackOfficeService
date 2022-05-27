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
import ma.s2m.nxp.domain.InstitutionType;
import ma.s2m.nxp.repository.InstitutionTypeRepository;
import ma.s2m.nxp.service.dto.InstitutionTypeDTO;
import ma.s2m.nxp.service.mapper.InstitutionTypeMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link InstitutionTypeResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class InstitutionTypeResourceIT {

    private static final String DEFAULT_IST_LABE = "AAAAAAAAAA";
    private static final String UPDATED_IST_LABE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/institution-types";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{istCode}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private InstitutionTypeRepository institutionTypeRepository;

    @Autowired
    private InstitutionTypeMapper institutionTypeMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInstitutionTypeMockMvc;

    private InstitutionType institutionType;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InstitutionType createEntity(EntityManager em) {
        InstitutionType institutionType = new InstitutionType().istLabe(DEFAULT_IST_LABE);
        return institutionType;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InstitutionType createUpdatedEntity(EntityManager em) {
        InstitutionType institutionType = new InstitutionType().istLabe(UPDATED_IST_LABE);
        return institutionType;
    }

    @BeforeEach
    public void initTest() {
        institutionType = createEntity(em);
    }

    @Test
    @Transactional
    void createInstitutionType() throws Exception {
        int databaseSizeBeforeCreate = institutionTypeRepository.findAll().size();
        // Create the InstitutionType
        InstitutionTypeDTO institutionTypeDTO = institutionTypeMapper.toDto(institutionType);
        restInstitutionTypeMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(institutionTypeDTO))
            )
            .andExpect(status().isCreated());

        // Validate the InstitutionType in the database
        List<InstitutionType> institutionTypeList = institutionTypeRepository.findAll();
        assertThat(institutionTypeList).hasSize(databaseSizeBeforeCreate + 1);
        InstitutionType testInstitutionType = institutionTypeList.get(institutionTypeList.size() - 1);
        assertThat(testInstitutionType.getIstLabe()).isEqualTo(DEFAULT_IST_LABE);
    }

    @Test
    @Transactional
    void createInstitutionTypeWithExistingId() throws Exception {
        // Create the InstitutionType with an existing ID
        institutionType.setIstCode(1L);
        InstitutionTypeDTO institutionTypeDTO = institutionTypeMapper.toDto(institutionType);

        int databaseSizeBeforeCreate = institutionTypeRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restInstitutionTypeMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(institutionTypeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InstitutionType in the database
        List<InstitutionType> institutionTypeList = institutionTypeRepository.findAll();
        assertThat(institutionTypeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllInstitutionTypes() throws Exception {
        // Initialize the database
        institutionTypeRepository.saveAndFlush(institutionType);

        // Get all the institutionTypeList
        restInstitutionTypeMockMvc
            .perform(get(ENTITY_API_URL + "?sort=istCode,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].istCode").value(hasItem(institutionType.getIstCode().intValue())))
            .andExpect(jsonPath("$.[*].istLabe").value(hasItem(DEFAULT_IST_LABE)));
    }

    @Test
    @Transactional
    void getInstitutionType() throws Exception {
        // Initialize the database
        institutionTypeRepository.saveAndFlush(institutionType);

        // Get the institutionType
        restInstitutionTypeMockMvc
            .perform(get(ENTITY_API_URL_ID, institutionType.getIstCode()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.istCode").value(institutionType.getIstCode().intValue()))
            .andExpect(jsonPath("$.istLabe").value(DEFAULT_IST_LABE));
    }

    @Test
    @Transactional
    void getNonExistingInstitutionType() throws Exception {
        // Get the institutionType
        restInstitutionTypeMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewInstitutionType() throws Exception {
        // Initialize the database
        institutionTypeRepository.saveAndFlush(institutionType);

        int databaseSizeBeforeUpdate = institutionTypeRepository.findAll().size();

        // Update the institutionType
        InstitutionType updatedInstitutionType = institutionTypeRepository.findById(institutionType.getIstCode()).get();
        // Disconnect from session so that the updates on updatedInstitutionType are not directly saved in db
        em.detach(updatedInstitutionType);
        updatedInstitutionType.istLabe(UPDATED_IST_LABE);
        InstitutionTypeDTO institutionTypeDTO = institutionTypeMapper.toDto(updatedInstitutionType);

        restInstitutionTypeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, institutionTypeDTO.getIstCode())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(institutionTypeDTO))
            )
            .andExpect(status().isOk());

        // Validate the InstitutionType in the database
        List<InstitutionType> institutionTypeList = institutionTypeRepository.findAll();
        assertThat(institutionTypeList).hasSize(databaseSizeBeforeUpdate);
        InstitutionType testInstitutionType = institutionTypeList.get(institutionTypeList.size() - 1);
        assertThat(testInstitutionType.getIstLabe()).isEqualTo(UPDATED_IST_LABE);
    }

    @Test
    @Transactional
    void putNonExistingInstitutionType() throws Exception {
        int databaseSizeBeforeUpdate = institutionTypeRepository.findAll().size();
        institutionType.setIstCode(count.incrementAndGet());

        // Create the InstitutionType
        InstitutionTypeDTO institutionTypeDTO = institutionTypeMapper.toDto(institutionType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInstitutionTypeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, institutionTypeDTO.getIstCode())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(institutionTypeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InstitutionType in the database
        List<InstitutionType> institutionTypeList = institutionTypeRepository.findAll();
        assertThat(institutionTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchInstitutionType() throws Exception {
        int databaseSizeBeforeUpdate = institutionTypeRepository.findAll().size();
        institutionType.setIstCode(count.incrementAndGet());

        // Create the InstitutionType
        InstitutionTypeDTO institutionTypeDTO = institutionTypeMapper.toDto(institutionType);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInstitutionTypeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(institutionTypeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InstitutionType in the database
        List<InstitutionType> institutionTypeList = institutionTypeRepository.findAll();
        assertThat(institutionTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamInstitutionType() throws Exception {
        int databaseSizeBeforeUpdate = institutionTypeRepository.findAll().size();
        institutionType.setIstCode(count.incrementAndGet());

        // Create the InstitutionType
        InstitutionTypeDTO institutionTypeDTO = institutionTypeMapper.toDto(institutionType);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInstitutionTypeMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(institutionTypeDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the InstitutionType in the database
        List<InstitutionType> institutionTypeList = institutionTypeRepository.findAll();
        assertThat(institutionTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateInstitutionTypeWithPatch() throws Exception {
        // Initialize the database
        institutionTypeRepository.saveAndFlush(institutionType);

        int databaseSizeBeforeUpdate = institutionTypeRepository.findAll().size();

        // Update the institutionType using partial update
        InstitutionType partialUpdatedInstitutionType = new InstitutionType();
        partialUpdatedInstitutionType.setIstCode(institutionType.getIstCode());

        partialUpdatedInstitutionType.istLabe(UPDATED_IST_LABE);

        restInstitutionTypeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedInstitutionType.getIstCode())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedInstitutionType))
            )
            .andExpect(status().isOk());

        // Validate the InstitutionType in the database
        List<InstitutionType> institutionTypeList = institutionTypeRepository.findAll();
        assertThat(institutionTypeList).hasSize(databaseSizeBeforeUpdate);
        InstitutionType testInstitutionType = institutionTypeList.get(institutionTypeList.size() - 1);
        assertThat(testInstitutionType.getIstLabe()).isEqualTo(UPDATED_IST_LABE);
    }

    @Test
    @Transactional
    void fullUpdateInstitutionTypeWithPatch() throws Exception {
        // Initialize the database
        institutionTypeRepository.saveAndFlush(institutionType);

        int databaseSizeBeforeUpdate = institutionTypeRepository.findAll().size();

        // Update the institutionType using partial update
        InstitutionType partialUpdatedInstitutionType = new InstitutionType();
        partialUpdatedInstitutionType.setIstCode(institutionType.getIstCode());

        partialUpdatedInstitutionType.istLabe(UPDATED_IST_LABE);

        restInstitutionTypeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedInstitutionType.getIstCode())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedInstitutionType))
            )
            .andExpect(status().isOk());

        // Validate the InstitutionType in the database
        List<InstitutionType> institutionTypeList = institutionTypeRepository.findAll();
        assertThat(institutionTypeList).hasSize(databaseSizeBeforeUpdate);
        InstitutionType testInstitutionType = institutionTypeList.get(institutionTypeList.size() - 1);
        assertThat(testInstitutionType.getIstLabe()).isEqualTo(UPDATED_IST_LABE);
    }

    @Test
    @Transactional
    void patchNonExistingInstitutionType() throws Exception {
        int databaseSizeBeforeUpdate = institutionTypeRepository.findAll().size();
        institutionType.setIstCode(count.incrementAndGet());

        // Create the InstitutionType
        InstitutionTypeDTO institutionTypeDTO = institutionTypeMapper.toDto(institutionType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInstitutionTypeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, institutionTypeDTO.getIstCode())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(institutionTypeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InstitutionType in the database
        List<InstitutionType> institutionTypeList = institutionTypeRepository.findAll();
        assertThat(institutionTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchInstitutionType() throws Exception {
        int databaseSizeBeforeUpdate = institutionTypeRepository.findAll().size();
        institutionType.setIstCode(count.incrementAndGet());

        // Create the InstitutionType
        InstitutionTypeDTO institutionTypeDTO = institutionTypeMapper.toDto(institutionType);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInstitutionTypeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(institutionTypeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InstitutionType in the database
        List<InstitutionType> institutionTypeList = institutionTypeRepository.findAll();
        assertThat(institutionTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamInstitutionType() throws Exception {
        int databaseSizeBeforeUpdate = institutionTypeRepository.findAll().size();
        institutionType.setIstCode(count.incrementAndGet());

        // Create the InstitutionType
        InstitutionTypeDTO institutionTypeDTO = institutionTypeMapper.toDto(institutionType);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInstitutionTypeMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(institutionTypeDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the InstitutionType in the database
        List<InstitutionType> institutionTypeList = institutionTypeRepository.findAll();
        assertThat(institutionTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteInstitutionType() throws Exception {
        // Initialize the database
        institutionTypeRepository.saveAndFlush(institutionType);

        int databaseSizeBeforeDelete = institutionTypeRepository.findAll().size();

        // Delete the institutionType
        restInstitutionTypeMockMvc
            .perform(delete(ENTITY_API_URL_ID, institutionType.getIstCode()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<InstitutionType> institutionTypeList = institutionTypeRepository.findAll();
        assertThat(institutionTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
