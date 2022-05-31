package ma.s2m.nxp.service;

import java.util.List;
import java.util.Optional;
import ma.s2m.nxp.service.dto.CountryDTO;
import ma.s2m.nxp.service.dto.CountrySlimDTO;

/**
 * Service Interface for managing {@link ma.s2m.nxp.domain.Country}.
 */
public interface CountryService {
    /**
     * Get all the countries.
     *
     * @return the list of entities.
     */
    List<CountrySlimDTO> findAll();
}
