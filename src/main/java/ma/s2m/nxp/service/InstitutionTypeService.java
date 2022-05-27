package ma.s2m.nxp.service;

import java.util.List;
import java.util.Optional;
import ma.s2m.nxp.service.dto.InstitutionTypeDTO;

/**
 * Service Interface for managing {@link ma.s2m.nxp.domain.InstitutionType}.
 */
public interface InstitutionTypeService {
    /**
     * Save a institutionType.
     *
     * @param institutionTypeDTO the entity to save.
     * @return the persisted entity.
     */
    InstitutionTypeDTO save(InstitutionTypeDTO institutionTypeDTO);

    /**
     * Partially updates a institutionType.
     *
     * @param institutionTypeDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<InstitutionTypeDTO> partialUpdate(InstitutionTypeDTO institutionTypeDTO);

    /**
     * Get all the institutionTypes.
     *
     * @return the list of entities.
     */
    List<InstitutionTypeDTO> findAll();

    /**
     * Get the "id" institutionType.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<InstitutionTypeDTO> findOne(Long id);

    /**
     * Delete the "id" institutionType.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
