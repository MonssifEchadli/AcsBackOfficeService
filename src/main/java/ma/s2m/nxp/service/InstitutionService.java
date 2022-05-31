package ma.s2m.nxp.service;

import java.util.List;
import java.util.Optional;

import ma.s2m.nxp.service.dto.SlimInstitutionDTO;

/**
 * Service Interface for managing {@link ma.s2m.nxp.domain.Institution}.
 */
public interface InstitutionService {
    /**
     * Save a institution.
     *
     * @param slimInstitutionDTO the entity to save.
     * @return the persisted entity.
     */
    SlimInstitutionDTO save(SlimInstitutionDTO slimInstitutionDTO);

    /**
     * Partially updates a institution.
     *
     * @param slimInstitutionDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<SlimInstitutionDTO> partialUpdate(SlimInstitutionDTO slimInstitutionDTO);

    /**
     * Get all the institutions.
     *
     * @return the list of entities.
     */
    List<SlimInstitutionDTO> findAll();


    /**
     * Get the "id" institution.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SlimInstitutionDTO> findOne(Long id);

    /**
     * Delete the "id" institution.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
