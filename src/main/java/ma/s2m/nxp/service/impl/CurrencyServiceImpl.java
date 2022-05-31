package ma.s2m.nxp.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import ma.s2m.nxp.domain.Currency;
import ma.s2m.nxp.repository.CurrencyRepository;
import ma.s2m.nxp.service.CurrencyService;
import ma.s2m.nxp.service.dto.CurrencyDTO;
import ma.s2m.nxp.service.dto.CurrencySlimDTO;
import ma.s2m.nxp.service.mapper.CurrencyMapper;
import ma.s2m.nxp.service.mapper.CurrencySlimMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Currency}.
 */
@Service
@Transactional
public class CurrencyServiceImpl implements CurrencyService {

    private final Logger log = LoggerFactory.getLogger(CurrencyServiceImpl.class);

    private final CurrencyRepository currencyRepository;

    private final CurrencyMapper currencyMapper;

    private final CurrencySlimMapper currencySlimMapper;

    public CurrencyServiceImpl(CurrencyRepository currencyRepository, CurrencyMapper currencyMapper, CurrencySlimMapper currencySlimMapper) {
        this.currencyRepository = currencyRepository;
        this.currencyMapper = currencyMapper;
        this.currencySlimMapper = currencySlimMapper;
    }


    @Override
    @Transactional(readOnly = true)
    public List<CurrencySlimDTO> findAll() {
        log.debug("Request to get all Currencies");
        return currencyRepository.findAll().stream().map(currencySlimMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

}
