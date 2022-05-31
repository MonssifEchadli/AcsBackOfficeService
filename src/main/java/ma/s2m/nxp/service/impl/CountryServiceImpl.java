package ma.s2m.nxp.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import ma.s2m.nxp.domain.Country;
import ma.s2m.nxp.repository.CountryRepository;
import ma.s2m.nxp.service.CountryService;
import ma.s2m.nxp.service.dto.CountryDTO;
import ma.s2m.nxp.service.dto.CountrySlimDTO;
import ma.s2m.nxp.service.mapper.CountryMapper;
import ma.s2m.nxp.service.mapper.CountrySlimMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Country}.
 */
@Service
@Transactional
public class CountryServiceImpl implements CountryService {

    private final Logger log = LoggerFactory.getLogger(CountryServiceImpl.class);

    private final CountryRepository countryRepository;

    private final CountrySlimMapper countrySlimMapper;

    public CountryServiceImpl(CountryRepository countryRepository, CountrySlimMapper countrySlimMapper) {
        this.countryRepository = countryRepository;
        this.countrySlimMapper = countrySlimMapper;
    }


    @Override
    @Transactional(readOnly = true)
    public List<CountrySlimDTO> findAll() {
        log.debug("Request to get all Countries");
        return countryRepository.findAll().stream().map(countrySlimMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

}
