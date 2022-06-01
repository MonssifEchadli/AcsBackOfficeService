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
import ma.s2m.nxp.domain.ContactInstitution;
import ma.s2m.nxp.repository.ContactInstitutionRepository;
import ma.s2m.nxp.service.dto.ContactInstitutionDTO;
import ma.s2m.nxp.service.mapper.ContactInstitutionMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link ContactInstitutionResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ContactInstitutionResourceIT {

    private static final String DEFAULT_CON_INST_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CON_INST_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CON_INST_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CON_INST_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CON_INST_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_CON_INST_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_CON_INST_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_CON_INST_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_CON_INST_JOB = "AAAAAAAAAA";
    private static final String UPDATED_CON_INST_JOB = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/contact-institutions";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{conInstCode}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ContactInstitutionRepository contactInstitutionRepository;

    @Autowired
    private ContactInstitutionMapper contactInstitutionMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restContactInstitutionMockMvc;

    private ContactInstitution contactInstitution;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ContactInstitution createEntity(EntityManager em) {
        ContactInstitution contactInstitution = new ContactInstitution()
            .conInstFirstName(DEFAULT_CON_INST_FIRST_NAME)
            .conInstLastName(DEFAULT_CON_INST_LAST_NAME)
            .conInstPhone(DEFAULT_CON_INST_PHONE)
            .conInstEmail(DEFAULT_CON_INST_EMAIL)
            .conInstJob(DEFAULT_CON_INST_JOB);
        return contactInstitution;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ContactInstitution createUpdatedEntity(EntityManager em) {
        ContactInstitution contactInstitution = new ContactInstitution()
            .conInstFirstName(UPDATED_CON_INST_FIRST_NAME)
            .conInstLastName(UPDATED_CON_INST_LAST_NAME)
            .conInstPhone(UPDATED_CON_INST_PHONE)
            .conInstEmail(UPDATED_CON_INST_EMAIL)
            .conInstJob(UPDATED_CON_INST_JOB);
        return contactInstitution;
    }

    @BeforeEach
    public void initTest() {
        contactInstitution = createEntity(em);
    }

    @Test
    @Transactional
    void createContactInstitution() throws Exception {
        int databaseSizeBeforeCreate = contactInstitutionRepository.findAll().size();
        // Create the ContactInstitution
        ContactInstitutionDTO contactInstitutionDTO = contactInstitutionMapper.toDto(contactInstitution);
        restContactInstitutionMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(contactInstitutionDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ContactInstitution in the database
        List<ContactInstitution> contactInstitutionList = contactInstitutionRepository.findAll();
        assertThat(contactInstitutionList).hasSize(databaseSizeBeforeCreate + 1);
        ContactInstitution testContactInstitution = contactInstitutionList.get(contactInstitutionList.size() - 1);
        assertThat(testContactInstitution.getConInstFirstName()).isEqualTo(DEFAULT_CON_INST_FIRST_NAME);
        assertThat(testContactInstitution.getConInstLastName()).isEqualTo(DEFAULT_CON_INST_LAST_NAME);
        assertThat(testContactInstitution.getConInstPhone()).isEqualTo(DEFAULT_CON_INST_PHONE);
        assertThat(testContactInstitution.getConInstEmail()).isEqualTo(DEFAULT_CON_INST_EMAIL);
        assertThat(testContactInstitution.getConInstJob()).isEqualTo(DEFAULT_CON_INST_JOB);
    }

    @Test
    @Transactional
    void createContactInstitutionWithExistingId() throws Exception {
        // Create the ContactInstitution with an existing ID
        contactInstitution.setConInstCode(1L);
        ContactInstitutionDTO contactInstitutionDTO = contactInstitutionMapper.toDto(contactInstitution);

        int databaseSizeBeforeCreate = contactInstitutionRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restContactInstitutionMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(contactInstitutionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ContactInstitution in the database
        List<ContactInstitution> contactInstitutionList = contactInstitutionRepository.findAll();
        assertThat(contactInstitutionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllContactInstitutions() throws Exception {
        // Initialize the database
        contactInstitutionRepository.saveAndFlush(contactInstitution);

        // Get all the contactInstitutionList
        restContactInstitutionMockMvc
            .perform(get(ENTITY_API_URL + "?sort=conInstCode,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].conInstCode").value(hasItem(contactInstitution.getConInstCode().intValue())))
            .andExpect(jsonPath("$.[*].conInstFirstName").value(hasItem(DEFAULT_CON_INST_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].conInstLastName").value(hasItem(DEFAULT_CON_INST_LAST_NAME)))
            .andExpect(jsonPath("$.[*].conInstPhone").value(hasItem(DEFAULT_CON_INST_PHONE)))
            .andExpect(jsonPath("$.[*].conInstEmail").value(hasItem(DEFAULT_CON_INST_EMAIL)))
            .andExpect(jsonPath("$.[*].conInstJob").value(hasItem(DEFAULT_CON_INST_JOB)));
    }

    @Test
    @Transactional
    void getContactInstitution() throws Exception {
        // Initialize the database
        contactInstitutionRepository.saveAndFlush(contactInstitution);

        // Get the contactInstitution
        restContactInstitutionMockMvc
            .perform(get(ENTITY_API_URL_ID, contactInstitution.getConInstCode()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.conInstCode").value(contactInstitution.getConInstCode().intValue()))
            .andExpect(jsonPath("$.conInstFirstName").value(DEFAULT_CON_INST_FIRST_NAME))
            .andExpect(jsonPath("$.conInstLastName").value(DEFAULT_CON_INST_LAST_NAME))
            .andExpect(jsonPath("$.conInstPhone").value(DEFAULT_CON_INST_PHONE))
            .andExpect(jsonPath("$.conInstEmail").value(DEFAULT_CON_INST_EMAIL))
            .andExpect(jsonPath("$.conInstJob").value(DEFAULT_CON_INST_JOB));
    }

    @Test
    @Transactional
    void getNonExistingContactInstitution() throws Exception {
        // Get the contactInstitution
        restContactInstitutionMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewContactInstitution() throws Exception {
        // Initialize the database
        contactInstitutionRepository.saveAndFlush(contactInstitution);

        int databaseSizeBeforeUpdate = contactInstitutionRepository.findAll().size();

        // Update the contactInstitution
        ContactInstitution updatedContactInstitution = contactInstitutionRepository.findById(contactInstitution.getConInstCode()).get();
        // Disconnect from session so that the updates on updatedContactInstitution are not directly saved in db
        em.detach(updatedContactInstitution);
        updatedContactInstitution
            .conInstFirstName(UPDATED_CON_INST_FIRST_NAME)
            .conInstLastName(UPDATED_CON_INST_LAST_NAME)
            .conInstPhone(UPDATED_CON_INST_PHONE)
            .conInstEmail(UPDATED_CON_INST_EMAIL)
            .conInstJob(UPDATED_CON_INST_JOB);
        ContactInstitutionDTO contactInstitutionDTO = contactInstitutionMapper.toDto(updatedContactInstitution);

        restContactInstitutionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, contactInstitutionDTO.getConInstCode())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(contactInstitutionDTO))
            )
            .andExpect(status().isOk());

        // Validate the ContactInstitution in the database
        List<ContactInstitution> contactInstitutionList = contactInstitutionRepository.findAll();
        assertThat(contactInstitutionList).hasSize(databaseSizeBeforeUpdate);
        ContactInstitution testContactInstitution = contactInstitutionList.get(contactInstitutionList.size() - 1);
        assertThat(testContactInstitution.getConInstFirstName()).isEqualTo(UPDATED_CON_INST_FIRST_NAME);
        assertThat(testContactInstitution.getConInstLastName()).isEqualTo(UPDATED_CON_INST_LAST_NAME);
        assertThat(testContactInstitution.getConInstPhone()).isEqualTo(UPDATED_CON_INST_PHONE);
        assertThat(testContactInstitution.getConInstEmail()).isEqualTo(UPDATED_CON_INST_EMAIL);
        assertThat(testContactInstitution.getConInstJob()).isEqualTo(UPDATED_CON_INST_JOB);
    }

    @Test
    @Transactional
    void putNonExistingContactInstitution() throws Exception {
        int databaseSizeBeforeUpdate = contactInstitutionRepository.findAll().size();
        contactInstitution.setConInstCode(count.incrementAndGet());

        // Create the ContactInstitution
        ContactInstitutionDTO contactInstitutionDTO = contactInstitutionMapper.toDto(contactInstitution);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restContactInstitutionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, contactInstitutionDTO.getConInstCode())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(contactInstitutionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ContactInstitution in the database
        List<ContactInstitution> contactInstitutionList = contactInstitutionRepository.findAll();
        assertThat(contactInstitutionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchContactInstitution() throws Exception {
        int databaseSizeBeforeUpdate = contactInstitutionRepository.findAll().size();
        contactInstitution.setConInstCode(count.incrementAndGet());

        // Create the ContactInstitution
        ContactInstitutionDTO contactInstitutionDTO = contactInstitutionMapper.toDto(contactInstitution);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restContactInstitutionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(contactInstitutionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ContactInstitution in the database
        List<ContactInstitution> contactInstitutionList = contactInstitutionRepository.findAll();
        assertThat(contactInstitutionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamContactInstitution() throws Exception {
        int databaseSizeBeforeUpdate = contactInstitutionRepository.findAll().size();
        contactInstitution.setConInstCode(count.incrementAndGet());

        // Create the ContactInstitution
        ContactInstitutionDTO contactInstitutionDTO = contactInstitutionMapper.toDto(contactInstitution);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restContactInstitutionMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(contactInstitutionDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ContactInstitution in the database
        List<ContactInstitution> contactInstitutionList = contactInstitutionRepository.findAll();
        assertThat(contactInstitutionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateContactInstitutionWithPatch() throws Exception {
        // Initialize the database
        contactInstitutionRepository.saveAndFlush(contactInstitution);

        int databaseSizeBeforeUpdate = contactInstitutionRepository.findAll().size();

        // Update the contactInstitution using partial update
        ContactInstitution partialUpdatedContactInstitution = new ContactInstitution();
        partialUpdatedContactInstitution.setConInstCode(contactInstitution.getConInstCode());

        partialUpdatedContactInstitution.conInstFirstName(UPDATED_CON_INST_FIRST_NAME).conInstJob(UPDATED_CON_INST_JOB);

        restContactInstitutionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedContactInstitution.getConInstCode())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedContactInstitution))
            )
            .andExpect(status().isOk());

        // Validate the ContactInstitution in the database
        List<ContactInstitution> contactInstitutionList = contactInstitutionRepository.findAll();
        assertThat(contactInstitutionList).hasSize(databaseSizeBeforeUpdate);
        ContactInstitution testContactInstitution = contactInstitutionList.get(contactInstitutionList.size() - 1);
        assertThat(testContactInstitution.getConInstFirstName()).isEqualTo(UPDATED_CON_INST_FIRST_NAME);
        assertThat(testContactInstitution.getConInstLastName()).isEqualTo(DEFAULT_CON_INST_LAST_NAME);
        assertThat(testContactInstitution.getConInstPhone()).isEqualTo(DEFAULT_CON_INST_PHONE);
        assertThat(testContactInstitution.getConInstEmail()).isEqualTo(DEFAULT_CON_INST_EMAIL);
        assertThat(testContactInstitution.getConInstJob()).isEqualTo(UPDATED_CON_INST_JOB);
    }

    @Test
    @Transactional
    void fullUpdateContactInstitutionWithPatch() throws Exception {
        // Initialize the database
        contactInstitutionRepository.saveAndFlush(contactInstitution);

        int databaseSizeBeforeUpdate = contactInstitutionRepository.findAll().size();

        // Update the contactInstitution using partial update
        ContactInstitution partialUpdatedContactInstitution = new ContactInstitution();
        partialUpdatedContactInstitution.setConInstCode(contactInstitution.getConInstCode());

        partialUpdatedContactInstitution
            .conInstFirstName(UPDATED_CON_INST_FIRST_NAME)
            .conInstLastName(UPDATED_CON_INST_LAST_NAME)
            .conInstPhone(UPDATED_CON_INST_PHONE)
            .conInstEmail(UPDATED_CON_INST_EMAIL)
            .conInstJob(UPDATED_CON_INST_JOB);

        restContactInstitutionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedContactInstitution.getConInstCode())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedContactInstitution))
            )
            .andExpect(status().isOk());

        // Validate the ContactInstitution in the database
        List<ContactInstitution> contactInstitutionList = contactInstitutionRepository.findAll();
        assertThat(contactInstitutionList).hasSize(databaseSizeBeforeUpdate);
        ContactInstitution testContactInstitution = contactInstitutionList.get(contactInstitutionList.size() - 1);
        assertThat(testContactInstitution.getConInstFirstName()).isEqualTo(UPDATED_CON_INST_FIRST_NAME);
        assertThat(testContactInstitution.getConInstLastName()).isEqualTo(UPDATED_CON_INST_LAST_NAME);
        assertThat(testContactInstitution.getConInstPhone()).isEqualTo(UPDATED_CON_INST_PHONE);
        assertThat(testContactInstitution.getConInstEmail()).isEqualTo(UPDATED_CON_INST_EMAIL);
        assertThat(testContactInstitution.getConInstJob()).isEqualTo(UPDATED_CON_INST_JOB);
    }

    @Test
    @Transactional
    void patchNonExistingContactInstitution() throws Exception {
        int databaseSizeBeforeUpdate = contactInstitutionRepository.findAll().size();
        contactInstitution.setConInstCode(count.incrementAndGet());

        // Create the ContactInstitution
        ContactInstitutionDTO contactInstitutionDTO = contactInstitutionMapper.toDto(contactInstitution);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restContactInstitutionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, contactInstitutionDTO.getConInstCode())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(contactInstitutionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ContactInstitution in the database
        List<ContactInstitution> contactInstitutionList = contactInstitutionRepository.findAll();
        assertThat(contactInstitutionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchContactInstitution() throws Exception {
        int databaseSizeBeforeUpdate = contactInstitutionRepository.findAll().size();
        contactInstitution.setConInstCode(count.incrementAndGet());

        // Create the ContactInstitution
        ContactInstitutionDTO contactInstitutionDTO = contactInstitutionMapper.toDto(contactInstitution);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restContactInstitutionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(contactInstitutionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ContactInstitution in the database
        List<ContactInstitution> contactInstitutionList = contactInstitutionRepository.findAll();
        assertThat(contactInstitutionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamContactInstitution() throws Exception {
        int databaseSizeBeforeUpdate = contactInstitutionRepository.findAll().size();
        contactInstitution.setConInstCode(count.incrementAndGet());

        // Create the ContactInstitution
        ContactInstitutionDTO contactInstitutionDTO = contactInstitutionMapper.toDto(contactInstitution);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restContactInstitutionMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(contactInstitutionDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ContactInstitution in the database
        List<ContactInstitution> contactInstitutionList = contactInstitutionRepository.findAll();
        assertThat(contactInstitutionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteContactInstitution() throws Exception {
        // Initialize the database
        contactInstitutionRepository.saveAndFlush(contactInstitution);

        int databaseSizeBeforeDelete = contactInstitutionRepository.findAll().size();

        // Delete the contactInstitution
        restContactInstitutionMockMvc
            .perform(delete(ENTITY_API_URL_ID, contactInstitution.getConInstCode()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ContactInstitution> contactInstitutionList = contactInstitutionRepository.findAll();
        assertThat(contactInstitutionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
