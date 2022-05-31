package ma.s2m.nxp.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import ma.s2m.nxp.repository.InstitutionRepository;
import ma.s2m.nxp.service.InstitutionService;
import ma.s2m.nxp.service.dto.SlimInstitutionDTO;
import ma.s2m.nxp.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link ma.s2m.nxp.domain.Institution}.
 */
@RestController
@RequestMapping("/api")
public class InstitutionResource {

    private final Logger log = LoggerFactory.getLogger(InstitutionResource.class);

    private static final String ENTITY_NAME = "acsBackOfficeInstitution";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InstitutionService institutionService;

    private final InstitutionRepository institutionRepository;

    public InstitutionResource(InstitutionService institutionService, InstitutionRepository institutionRepository) {
        this.institutionService = institutionService;
        this.institutionRepository = institutionRepository;
    }

    /**
     * {@code POST  /institutions} : Create a new institution.
     *
     * @param slimInstitutionDTO the institutionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new institutionDTO, or with status {@code 400 (Bad Request)} if the institution has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/institutions")
    public ResponseEntity<SlimInstitutionDTO> createInstitution(@RequestBody SlimInstitutionDTO slimInstitutionDTO) throws URISyntaxException {
        log.debug("REST request to save Institution : {}", slimInstitutionDTO);
        if (slimInstitutionDTO.getInstCode() != null) {
            throw new BadRequestAlertException("A new institution cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SlimInstitutionDTO result = institutionService.save(slimInstitutionDTO);
        return ResponseEntity
            .created(new URI("/api/institutions/" + result.getInstCode()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getInstCode().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /institutions/:instCode} : Updates an existing institution.
     *
     * @param instCode the id of the institutionDTO to save.
     * @param slimInstitutionDTO the institutionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated institutionDTO,
     * or with status {@code 400 (Bad Request)} if the institutionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the institutionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/institutions/{instCode}")
    public ResponseEntity<SlimInstitutionDTO> updateInstitution(
        @PathVariable(value = "instCode", required = false) final Long instCode,
        @RequestBody SlimInstitutionDTO slimInstitutionDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Institution : {}, {}", instCode, slimInstitutionDTO);
        if (slimInstitutionDTO.getInstCode() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(instCode, slimInstitutionDTO.getInstCode())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!institutionRepository.existsById(instCode)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        SlimInstitutionDTO result = institutionService.save(slimInstitutionDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, slimInstitutionDTO.getInstCode().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /institutions/:instCode} : Partial updates given fields of an existing institution, field will ignore if it is null
     *
     * @param instCode the id of the institutionDTO to save.
     * @param slimInstitutionDTO the institutionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated institutionDTO,
     * or with status {@code 400 (Bad Request)} if the institutionDTO is not valid,
     * or with status {@code 404 (Not Found)} if the institutionDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the institutionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/institutions/{instCode}", consumes = "application/merge-patch+json")
    public ResponseEntity<SlimInstitutionDTO> partialUpdateInstitution(
        @PathVariable(value = "instCode", required = false) final Long instCode,
        @RequestBody SlimInstitutionDTO slimInstitutionDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Institution partially : {}, {}", instCode, slimInstitutionDTO);
        if (slimInstitutionDTO.getInstCode() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(instCode, slimInstitutionDTO.getInstCode())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!institutionRepository.existsById(instCode)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<SlimInstitutionDTO> result = institutionService.partialUpdate(slimInstitutionDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, slimInstitutionDTO.getInstCode().toString())
        );
    }

    @GetMapping("/institutions")
    public List<SlimInstitutionDTO> getAllOfInstitutions(){
           log.debug("Rest request to get all Institutions");
           return institutionService.findAll();
    }

    @GetMapping("/institutions/{id}")
    public ResponseEntity<SlimInstitutionDTO> getInstitution(@PathVariable Long id) {
        log.debug("REST request to get Institution : {}", id);
        Optional<SlimInstitutionDTO> slimInstitutionDTO = institutionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(slimInstitutionDTO);
    }

    /**
     * {@code DELETE  /institutions/:id} : delete the "id" institution.
     *
     * @param id the id of the institutionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/institutions/{id}")
    public ResponseEntity<Void> deleteInstitution(@PathVariable Long id) {
        log.debug("REST request to delete Institution : {}", id);
        institutionService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
