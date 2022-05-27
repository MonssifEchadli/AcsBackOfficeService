package ma.s2m.nxp.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import ma.s2m.nxp.repository.InstitutionTypeRepository;
import ma.s2m.nxp.service.InstitutionTypeService;
import ma.s2m.nxp.service.dto.InstitutionTypeDTO;
import ma.s2m.nxp.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link ma.s2m.nxp.domain.InstitutionType}.
 */
@RestController
@RequestMapping("/api")
public class InstitutionTypeResource {

    private final Logger log = LoggerFactory.getLogger(InstitutionTypeResource.class);

    private static final String ENTITY_NAME = "acsBackOfficeInstitutionType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InstitutionTypeService institutionTypeService;

    private final InstitutionTypeRepository institutionTypeRepository;

    public InstitutionTypeResource(InstitutionTypeService institutionTypeService, InstitutionTypeRepository institutionTypeRepository) {
        this.institutionTypeService = institutionTypeService;
        this.institutionTypeRepository = institutionTypeRepository;
    }

    /**
     * {@code POST  /institution-types} : Create a new institutionType.
     *
     * @param institutionTypeDTO the institutionTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new institutionTypeDTO, or with status {@code 400 (Bad Request)} if the institutionType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/institution-types")
    public ResponseEntity<InstitutionTypeDTO> createInstitutionType(@RequestBody InstitutionTypeDTO institutionTypeDTO)
        throws URISyntaxException {
        log.debug("REST request to save InstitutionType : {}", institutionTypeDTO);
        if (institutionTypeDTO.getIstCode() != null) {
            throw new BadRequestAlertException("A new institutionType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InstitutionTypeDTO result = institutionTypeService.save(institutionTypeDTO);
        return ResponseEntity
            .created(new URI("/api/institution-types/" + result.getIstCode()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getIstCode().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /institution-types/:istCode} : Updates an existing institutionType.
     *
     * @param istCode the id of the institutionTypeDTO to save.
     * @param institutionTypeDTO the institutionTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated institutionTypeDTO,
     * or with status {@code 400 (Bad Request)} if the institutionTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the institutionTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/institution-types/{istCode}")
    public ResponseEntity<InstitutionTypeDTO> updateInstitutionType(
        @PathVariable(value = "istCode", required = false) final Long istCode,
        @RequestBody InstitutionTypeDTO institutionTypeDTO
    ) throws URISyntaxException {
        log.debug("REST request to update InstitutionType : {}, {}", istCode, institutionTypeDTO);
        if (institutionTypeDTO.getIstCode() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(istCode, institutionTypeDTO.getIstCode())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!institutionTypeRepository.existsById(istCode)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        InstitutionTypeDTO result = institutionTypeService.save(institutionTypeDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, institutionTypeDTO.getIstCode().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /institution-types/:istCode} : Partial updates given fields of an existing institutionType, field will ignore if it is null
     *
     * @param istCode the id of the institutionTypeDTO to save.
     * @param institutionTypeDTO the institutionTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated institutionTypeDTO,
     * or with status {@code 400 (Bad Request)} if the institutionTypeDTO is not valid,
     * or with status {@code 404 (Not Found)} if the institutionTypeDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the institutionTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/institution-types/{istCode}", consumes = "application/merge-patch+json")
    public ResponseEntity<InstitutionTypeDTO> partialUpdateInstitutionType(
        @PathVariable(value = "istCode", required = false) final Long istCode,
        @RequestBody InstitutionTypeDTO institutionTypeDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update InstitutionType partially : {}, {}", istCode, institutionTypeDTO);
        if (institutionTypeDTO.getIstCode() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(istCode, institutionTypeDTO.getIstCode())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!institutionTypeRepository.existsById(istCode)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<InstitutionTypeDTO> result = institutionTypeService.partialUpdate(institutionTypeDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, institutionTypeDTO.getIstCode().toString())
        );
    }

    /**
     * {@code GET  /institution-types} : get all the institutionTypes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of institutionTypes in body.
     */
    @GetMapping("/institution-types")
    public List<InstitutionTypeDTO> getAllInstitutionTypes() {
        log.debug("REST request to get all InstitutionTypes");
        return institutionTypeService.findAll();
    }

    /**
     * {@code GET  /institution-types/:id} : get the "id" institutionType.
     *
     * @param id the id of the institutionTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the institutionTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/institution-types/{id}")
    public ResponseEntity<InstitutionTypeDTO> getInstitutionType(@PathVariable Long id) {
        log.debug("REST request to get InstitutionType : {}", id);
        Optional<InstitutionTypeDTO> institutionTypeDTO = institutionTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(institutionTypeDTO);
    }

    /**
     * {@code DELETE  /institution-types/:id} : delete the "id" institutionType.
     *
     * @param id the id of the institutionTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/institution-types/{id}")
    public ResponseEntity<Void> deleteInstitutionType(@PathVariable Long id) {
        log.debug("REST request to delete InstitutionType : {}", id);
        institutionTypeService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
