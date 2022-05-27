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

    private static final String DEFAULT_CONT_INST_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CONT_INST_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CONT_INST_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CONT_INST_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CONT_INST_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_CONT_INST_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_CONT_INST_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_CONT_INST_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_CONT_INST_JOB = "AAAAAAAAAA";
    private static final String UPDATED_CONT_INST_JOB = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/contact-institutions";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{contInstCode}";

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
            .contInstFirstName(DEFAULT_CONT_INST_FIRST_NAME)
            .contInstLastName(DEFAULT_CONT_INST_LAST_NAME)
            .contInstPhone(DEFAULT_CONT_INST_PHONE)
            .contInstEmail(DEFAULT_CONT_INST_EMAIL)
            .contInstJob(DEFAULT_CONT_INST_JOB);
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
            .contInstFirstName(UPDATED_CONT_INST_FIRST_NAME)
            .contInstLastName(UPDATED_CONT_INST_LAST_NAME)
            .contInstPhone(UPDATED_CONT_INST_PHONE)
            .contInstEmail(UPDATED_CONT_INST_EMAIL)
            .contInstJob(UPDATED_CONT_INST_JOB);
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
        assertThat(testContactInstitution.getContInstFirstName()).isEqualTo(DEFAULT_CONT_INST_FIRST_NAME);
        assertThat(testContactInstitution.getContInstLastName()).isEqualTo(DEFAULT_CONT_INST_LAST_NAME);
        assertThat(testContactInstitution.getContInstPhone()).isEqualTo(DEFAULT_CONT_INST_PHONE);
        assertThat(testContactInstitution.getContInstEmail()).isEqualTo(DEFAULT_CONT_INST_EMAIL);
        assertThat(testContactInstitution.getContInstJob()).isEqualTo(DEFAULT_CONT_INST_JOB);
    }

    @Test
    @Transactional
    void createContactInstitutionWithExistingId() throws Exception {
        // Create the ContactInstitution with an existing ID
        contactInstitution.setContInstCode(1L);
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
            .perform(get(ENTITY_API_URL + "?sort=contInstCode,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].contInstCode").value(hasItem(contactInstitution.getContInstCode().intValue())))
            .andExpect(jsonPath("$.[*].contInstFirstName").value(hasItem(DEFAULT_CONT_INST_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].contInstLastName").value(hasItem(DEFAULT_CONT_INST_LAST_NAME)))
            .andExpect(jsonPath("$.[*].contInstPhone").value(hasItem(DEFAULT_CONT_INST_PHONE)))
            .andExpect(jsonPath("$.[*].contInstEmail").value(hasItem(DEFAULT_CONT_INST_EMAIL)))
            .andExpect(jsonPath("$.[*].contInstJob").value(hasItem(DEFAULT_CONT_INST_JOB)));
    }

    @Test
    @Transactional
    void getContactInstitution() throws Exception {
        // Initialize the database
        contactInstitutionRepository.saveAndFlush(contactInstitution);

        // Get the contactInstitution
        restContactInstitutionMockMvc
            .perform(get(ENTITY_API_URL_ID, contactInstitution.getContInstCode()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.contInstCode").value(contactInstitution.getContInstCode().intValue()))
            .andExpect(jsonPath("$.contInstFirstName").value(DEFAULT_CONT_INST_FIRST_NAME))
            .andExpect(jsonPath("$.contInstLastName").value(DEFAULT_CONT_INST_LAST_NAME))
            .andExpect(jsonPath("$.contInstPhone").value(DEFAULT_CONT_INST_PHONE))
            .andExpect(jsonPath("$.contInstEmail").value(DEFAULT_CONT_INST_EMAIL))
            .andExpect(jsonPath("$.contInstJob").value(DEFAULT_CONT_INST_JOB));
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
        ContactInstitution updatedContactInstitution = contactInstitutionRepository.findById(contactInstitution.getContInstCode()).get();
        // Disconnect from session so that the updates on updatedContactInstitution are not directly saved in db
        em.detach(updatedContactInstitution);
        updatedContactInstitution
            .contInstFirstName(UPDATED_CONT_INST_FIRST_NAME)
            .contInstLastName(UPDATED_CONT_INST_LAST_NAME)
            .contInstPhone(UPDATED_CONT_INST_PHONE)
            .contInstEmail(UPDATED_CONT_INST_EMAIL)
            .contInstJob(UPDATED_CONT_INST_JOB);
        ContactInstitutionDTO contactInstitutionDTO = contactInstitutionMapper.toDto(updatedContactInstitution);

        restContactInstitutionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, contactInstitutionDTO.getContInstCode())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(contactInstitutionDTO))
            )
            .andExpect(status().isOk());

        // Validate the ContactInstitution in the database
        List<ContactInstitution> contactInstitutionList = contactInstitutionRepository.findAll();
        assertThat(contactInstitutionList).hasSize(databaseSizeBeforeUpdate);
        ContactInstitution testContactInstitution = contactInstitutionList.get(contactInstitutionList.size() - 1);
        assertThat(testContactInstitution.getContInstFirstName()).isEqualTo(UPDATED_CONT_INST_FIRST_NAME);
        assertThat(testContactInstitution.getContInstLastName()).isEqualTo(UPDATED_CONT_INST_LAST_NAME);
        assertThat(testContactInstitution.getContInstPhone()).isEqualTo(UPDATED_CONT_INST_PHONE);
        assertThat(testContactInstitution.getContInstEmail()).isEqualTo(UPDATED_CONT_INST_EMAIL);
        assertThat(testContactInstitution.getContInstJob()).isEqualTo(UPDATED_CONT_INST_JOB);
    }

    @Test
    @Transactional
    void putNonExistingContactInstitution() throws Exception {
        int databaseSizeBeforeUpdate = contactInstitutionRepository.findAll().size();
        contactInstitution.setContInstCode(count.incrementAndGet());

        // Create the ContactInstitution
        ContactInstitutionDTO contactInstitutionDTO = contactInstitutionMapper.toDto(contactInstitution);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restContactInstitutionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, contactInstitutionDTO.getContInstCode())
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
        contactInstitution.setContInstCode(count.incrementAndGet());

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
        contactInstitution.setContInstCode(count.incrementAndGet());

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
        partialUpdatedContactInstitution.setContInstCode(contactInstitution.getContInstCode());

        partialUpdatedContactInstitution.contInstFirstName(UPDATED_CONT_INST_FIRST_NAME).contInstJob(UPDATED_CONT_INST_JOB);

        restContactInstitutionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedContactInstitution.getContInstCode())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedContactInstitution))
            )
            .andExpect(status().isOk());

        // Validate the ContactInstitution in the database
        List<ContactInstitution> contactInstitutionList = contactInstitutionRepository.findAll();
        assertThat(contactInstitutionList).hasSize(databaseSizeBeforeUpdate);
        ContactInstitution testContactInstitution = contactInstitutionList.get(contactInstitutionList.size() - 1);
        assertThat(testContactInstitution.getContInstFirstName()).isEqualTo(UPDATED_CONT_INST_FIRST_NAME);
        assertThat(testContactInstitution.getContInstLastName()).isEqualTo(DEFAULT_CONT_INST_LAST_NAME);
        assertThat(testContactInstitution.getContInstPhone()).isEqualTo(DEFAULT_CONT_INST_PHONE);
        assertThat(testContactInstitution.getContInstEmail()).isEqualTo(DEFAULT_CONT_INST_EMAIL);
        assertThat(testContactInstitution.getContInstJob()).isEqualTo(UPDATED_CONT_INST_JOB);
    }

    @Test
    @Transactional
    void fullUpdateContactInstitutionWithPatch() throws Exception {
        // Initialize the database
        contactInstitutionRepository.saveAndFlush(contactInstitution);

        int databaseSizeBeforeUpdate = contactInstitutionRepository.findAll().size();

        // Update the contactInstitution using partial update
        ContactInstitution partialUpdatedContactInstitution = new ContactInstitution();
        partialUpdatedContactInstitution.setContInstCode(contactInstitution.getContInstCode());

        partialUpdatedContactInstitution
            .contInstFirstName(UPDATED_CONT_INST_FIRST_NAME)
            .contInstLastName(UPDATED_CONT_INST_LAST_NAME)
            .contInstPhone(UPDATED_CONT_INST_PHONE)
            .contInstEmail(UPDATED_CONT_INST_EMAIL)
            .contInstJob(UPDATED_CONT_INST_JOB);

        restContactInstitutionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedContactInstitution.getContInstCode())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedContactInstitution))
            )
            .andExpect(status().isOk());

        // Validate the ContactInstitution in the database
        List<ContactInstitution> contactInstitutionList = contactInstitutionRepository.findAll();
        assertThat(contactInstitutionList).hasSize(databaseSizeBeforeUpdate);
        ContactInstitution testContactInstitution = contactInstitutionList.get(contactInstitutionList.size() - 1);
        assertThat(testContactInstitution.getContInstFirstName()).isEqualTo(UPDATED_CONT_INST_FIRST_NAME);
        assertThat(testContactInstitution.getContInstLastName()).isEqualTo(UPDATED_CONT_INST_LAST_NAME);
        assertThat(testContactInstitution.getContInstPhone()).isEqualTo(UPDATED_CONT_INST_PHONE);
        assertThat(testContactInstitution.getContInstEmail()).isEqualTo(UPDATED_CONT_INST_EMAIL);
        assertThat(testContactInstitution.getContInstJob()).isEqualTo(UPDATED_CONT_INST_JOB);
    }

    @Test
    @Transactional
    void patchNonExistingContactInstitution() throws Exception {
        int databaseSizeBeforeUpdate = contactInstitutionRepository.findAll().size();
        contactInstitution.setContInstCode(count.incrementAndGet());

        // Create the ContactInstitution
        ContactInstitutionDTO contactInstitutionDTO = contactInstitutionMapper.toDto(contactInstitution);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restContactInstitutionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, contactInstitutionDTO.getContInstCode())
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
        contactInstitution.setContInstCode(count.incrementAndGet());

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
        contactInstitution.setContInstCode(count.incrementAndGet());

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
            .perform(delete(ENTITY_API_URL_ID, contactInstitution.getContInstCode()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ContactInstitution> contactInstitutionList = contactInstitutionRepository.findAll();
        assertThat(contactInstitutionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
