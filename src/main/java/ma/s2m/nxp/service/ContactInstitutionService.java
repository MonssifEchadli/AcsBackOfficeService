package ma.s2m.nxp.service;

import java.util.List;
import java.util.Optional;
import ma.s2m.nxp.service.dto.ContactInstitutionDTO;

/**
 * Service Interface for managing {@link ma.s2m.nxp.domain.ContactInstitution}.
 */
public interface ContactInstitutionService {
    /**
     * Save a contactInstitution.
     *
     * @param contactInstitutionDTO the entity to save.
     * @return the persisted entity.
     */
    ContactInstitutionDTO save(ContactInstitutionDTO contactInstitutionDTO);

    /**
     * Partially updates a contactInstitution.
     *
     * @param contactInstitutionDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ContactInstitutionDTO> partialUpdate(ContactInstitutionDTO contactInstitutionDTO);

    /**
     * Get all the contactInstitutions.
     *
     * @return the list of entities.
     */
    List<ContactInstitutionDTO> findAll();

    /**
     * Get the "id" contactInstitution.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ContactInstitutionDTO> findOne(Long id);

    /**
     * Delete the "id" contactInstitution.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
