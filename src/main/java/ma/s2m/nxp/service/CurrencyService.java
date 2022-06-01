package ma.s2m.nxp.service;

import java.util.List;
import java.util.Optional;
import ma.s2m.nxp.service.dto.CurrencyDTO;
import ma.s2m.nxp.service.dto.CurrencySlimDTO;

/**
 * Service Interface for managing {@link ma.s2m.nxp.domain.Currency}.
 */
public interface CurrencyService {
    /**
     * Get all the currencies.
     *
     * @return the list of entities.
     */
    List<CurrencySlimDTO> findAll();
}
