package ma.s2m.nxp.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import ma.s2m.nxp.repository.InstitutionTypeRepository;
import ma.s2m.nxp.service.InstitutionTypeService;
import ma.s2m.nxp.service.dto.InstitutionTypeDTO;
import ma.s2m.nxp.service.dto.InstitutionTypeSlimDTO;
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
     * {@code GET  /institution-types} : get all the institutionTypes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of institutionTypes in body.
     */
    @GetMapping("/institution-types")
    public List<InstitutionTypeSlimDTO> getAllInstitutionTypes() {
        log.debug("REST request to get all InstitutionTypes");
        return institutionTypeService.findAll();
    }

}
