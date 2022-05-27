package ma.s2m.nxp.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import ma.s2m.nxp.domain.InstitutionType;
import ma.s2m.nxp.repository.InstitutionTypeRepository;
import ma.s2m.nxp.service.InstitutionTypeService;
import ma.s2m.nxp.service.dto.InstitutionTypeDTO;
import ma.s2m.nxp.service.mapper.InstitutionTypeMapper;
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

    private final InstitutionTypeMapper institutionTypeMapper;

    public InstitutionTypeServiceImpl(InstitutionTypeRepository institutionTypeRepository, InstitutionTypeMapper institutionTypeMapper) {
        this.institutionTypeRepository = institutionTypeRepository;
        this.institutionTypeMapper = institutionTypeMapper;
    }

    @Override
    public InstitutionTypeDTO save(InstitutionTypeDTO institutionTypeDTO) {
        log.debug("Request to save InstitutionType : {}", institutionTypeDTO);
        InstitutionType institutionType = institutionTypeMapper.toEntity(institutionTypeDTO);
        institutionType = institutionTypeRepository.save(institutionType);
        return institutionTypeMapper.toDto(institutionType);
    }

    @Override
    public Optional<InstitutionTypeDTO> partialUpdate(InstitutionTypeDTO institutionTypeDTO) {
        log.debug("Request to partially update InstitutionType : {}", institutionTypeDTO);

        return institutionTypeRepository
            .findById(institutionTypeDTO.getIstCode())
            .map(
                existingInstitutionType -> {
                    institutionTypeMapper.partialUpdate(existingInstitutionType, institutionTypeDTO);
                    return existingInstitutionType;
                }
            )
            .map(institutionTypeRepository::save)
            .map(institutionTypeMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<InstitutionTypeDTO> findAll() {
        log.debug("Request to get all InstitutionTypes");
        return institutionTypeRepository
            .findAll()
            .stream()
            .map(institutionTypeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<InstitutionTypeDTO> findOne(Long id) {
        log.debug("Request to get InstitutionType : {}", id);
        return institutionTypeRepository.findById(id).map(institutionTypeMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete InstitutionType : {}", id);
        institutionTypeRepository.deleteById(id);
    }
}
