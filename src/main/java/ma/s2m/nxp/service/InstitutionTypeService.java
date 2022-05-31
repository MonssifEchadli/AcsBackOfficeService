package ma.s2m.nxp.service;

import java.util.List;
import ma.s2m.nxp.service.dto.InstitutionTypeSlimDTO;

/**
 * Service Interface for managing {@link ma.s2m.nxp.domain.InstitutionType}.
 */
public interface InstitutionTypeService {
    /**
     * Get all the institutionTypes.
     *
     * @return the list of entities.
     */
    List<InstitutionTypeSlimDTO> findAll();
}
