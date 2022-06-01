package ma.s2m.nxp.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import ma.s2m.nxp.domain.InstitutionType;
import ma.s2m.nxp.repository.InstitutionTypeRepository;
import ma.s2m.nxp.service.InstitutionTypeService;
import ma.s2m.nxp.service.dto.InstitutionTypeDTO;
import ma.s2m.nxp.service.dto.InstitutionTypeSlimDTO;
import ma.s2m.nxp.service.mapper.InstitutionTypeMapper;
import ma.s2m.nxp.service.mapper.InstitutionTypeSlimMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link InstitutionType}.
 */
@Service
@Transactional
public class InstitutionTypeServiceImpl implements InstitutionTypeService {

    private final Logger log = LoggerFactory.getLogger(InstitutionTypeServiceImpl.class);

    private final InstitutionTypeRepository institutionTypeRepository;

    private final InstitutionTypeSlimMapper institutionTypeSlimMapper;

    public InstitutionTypeServiceImpl(InstitutionTypeRepository institutionTypeRepository, InstitutionTypeSlimMapper institutionTypeSlimMapper) {
        this.institutionTypeRepository = institutionTypeRepository;
        this.institutionTypeSlimMapper = institutionTypeSlimMapper;
    }


    @Override
    @Transactional(readOnly = true)
    public List<InstitutionTypeSlimDTO> findAll() {
        log.debug("Request to get all InstitutionTypes");
        return institutionTypeRepository
            .findAll()
            .stream()
            .map(institutionTypeSlimMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

}
