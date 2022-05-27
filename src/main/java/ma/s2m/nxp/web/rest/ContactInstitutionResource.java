package ma.s2m.nxp.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import ma.s2m.nxp.repository.ContactInstitutionRepository;
import ma.s2m.nxp.service.ContactInstitutionService;
import ma.s2m.nxp.service.dto.ContactInstitutionDTO;
import ma.s2m.nxp.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link ma.s2m.nxp.domain.ContactInstitution}.
 */
@RestController
@RequestMapping("/api")
public class ContactInstitutionResource {

    private final Logger log = LoggerFactory.getLogger(ContactInstitutionResource.class);

    private static final String ENTITY_NAME = "acsBackOfficeContactInstitution";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ContactInstitutionService contactInstitutionService;

    private final ContactInstitutionRepository contactInstitutionRepository;

    public ContactInstitutionResource(
        ContactInstitutionService contactInstitutionService,
        ContactInstitutionRepository contactInstitutionRepository
    ) {
        this.contactInstitutionService = contactInstitutionService;
        this.contactInstitutionRepository = contactInstitutionRepository;
    }

    /**
     * {@code POST  /contact-institutions} : Create a new contactInstitution.
     *
     * @param contactInstitutionDTO the contactInstitutionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new contactInstitutionDTO, or with status {@code 400 (Bad Request)} if the contactInstitution has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/contact-institutions")
    public ResponseEntity<ContactInstitutionDTO> createContactInstitution(@RequestBody ContactInstitutionDTO contactInstitutionDTO)
        throws URISyntaxException {
        log.debug("REST request to save ContactInstitution : {}", contactInstitutionDTO);
        if (contactInstitutionDTO.getContInstCode() != null) {
            throw new BadRequestAlertException("A new contactInstitution cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ContactInstitutionDTO result = contactInstitutionService.save(contactInstitutionDTO);
        return ResponseEntity
            .created(new URI("/api/contact-institutions/" + result.getContInstCode()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getContInstCode().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /contact-institutions/:contInstCode} : Updates an existing contactInstitution.
     *
     * @param contInstCode the id of the contactInstitutionDTO to save.
     * @param contactInstitutionDTO the contactInstitutionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated contactInstitutionDTO,
     * or with status {@code 400 (Bad Request)} if the contactInstitutionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the contactInstitutionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/contact-institutions/{contInstCode}")
    public ResponseEntity<ContactInstitutionDTO> updateContactInstitution(
        @PathVariable(value = "contInstCode", required = false) final Long contInstCode,
        @RequestBody ContactInstitutionDTO contactInstitutionDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ContactInstitution : {}, {}", contInstCode, contactInstitutionDTO);
        if (contactInstitutionDTO.getContInstCode() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(contInstCode, contactInstitutionDTO.getContInstCode())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!contactInstitutionRepository.existsById(contInstCode)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ContactInstitutionDTO result = contactInstitutionService.save(contactInstitutionDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, contactInstitutionDTO.getContInstCode().toString())
            )
            .body(result);
    }

    /**
     * {@code PATCH  /contact-institutions/:contInstCode} : Partial updates given fields of an existing contactInstitution, field will ignore if it is null
     *
     * @param contInstCode the id of the contactInstitutionDTO to save.
     * @param contactInstitutionDTO the contactInstitutionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated contactInstitutionDTO,
     * or with status {@code 400 (Bad Request)} if the contactInstitutionDTO is not valid,
     * or with status {@code 404 (Not Found)} if the contactInstitutionDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the contactInstitutionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/contact-institutions/{contInstCode}", consumes = "application/merge-patch+json")
    public ResponseEntity<ContactInstitutionDTO> partialUpdateContactInstitution(
        @PathVariable(value = "contInstCode", required = false) final Long contInstCode,
        @RequestBody ContactInstitutionDTO contactInstitutionDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ContactInstitution partially : {}, {}", contInstCode, contactInstitutionDTO);
        if (contactInstitutionDTO.getContInstCode() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(contInstCode, contactInstitutionDTO.getContInstCode())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!contactInstitutionRepository.existsById(contInstCode)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ContactInstitutionDTO> result = contactInstitutionService.partialUpdate(contactInstitutionDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, contactInstitutionDTO.getContInstCode().toString())
        );
    }

    /**
     * {@code GET  /contact-institutions} : get all the contactInstitutions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of contactInstitutions in body.
     */
    @GetMapping("/contact-institutions")
    public List<ContactInstitutionDTO> getAllContactInstitutions() {
        log.debug("REST request to get all ContactInstitutions");
        return contactInstitutionService.findAll();
    }

    /**
     * {@code GET  /contact-institutions/:id} : get the "id" contactInstitution.
     *
     * @param id the id of the contactInstitutionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the contactInstitutionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/contact-institutions/{id}")
    public ResponseEntity<ContactInstitutionDTO> getContactInstitution(@PathVariable Long id) {
        log.debug("REST request to get ContactInstitution : {}", id);
        Optional<ContactInstitutionDTO> contactInstitutionDTO = contactInstitutionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(contactInstitutionDTO);
    }

    /**
     * {@code DELETE  /contact-institutions/:id} : delete the "id" contactInstitution.
     *
     * @param id the id of the contactInstitutionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/contact-institutions/{id}")
    public ResponseEntity<Void> deleteContactInstitution(@PathVariable Long id) {
        log.debug("REST request to delete ContactInstitution : {}", id);
        contactInstitutionService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
