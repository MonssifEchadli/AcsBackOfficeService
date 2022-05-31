package ma.s2m.nxp.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import ma.s2m.nxp.domain.Institution;
import ma.s2m.nxp.repository.InstitutionRepository;
import ma.s2m.nxp.service.InstitutionService;
import ma.s2m.nxp.service.dto.SlimInstitutionDTO;
import ma.s2m.nxp.service.mapper.SlimInstitutionMapper;
import ma.s2m.nxp.service.mapper.InstitutionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Institution}.
 */
@Service
@Transactional
public class InstitutionServiceImpl implements InstitutionService {

    private final Logger log = LoggerFactory.getLogger(InstitutionServiceImpl.class);

    private final InstitutionRepository institutionRepository;

    private final InstitutionMapper institutionMapper;

    private final SlimInstitutionMapper slimInstitutionMapper;

    public InstitutionServiceImpl(InstitutionRepository institutionRepository, InstitutionMapper institutionMapper, SlimInstitutionMapper slimInstitutionMapper) {
        this.institutionRepository = institutionRepository;
        this.institutionMapper = institutionMapper;
        this.slimInstitutionMapper = slimInstitutionMapper;
    }

    @Override
    @Transactional
    public SlimInstitutionDTO save(SlimInstitutionDTO slimInstitutionDTO) {
        log.debug("Request to save Institution : {}", slimInstitutionDTO);
        Institution institution = slimInstitutionMapper.toEntity(slimInstitutionDTO);
        institution = institutionRepository.save(institution);
        return slimInstitutionMapper.toDto(institution);
    }

    @Override
    public Optional<SlimInstitutionDTO> partialUpdate(SlimInstitutionDTO slimInstitutionDTO) {
        log.debug("Request to partially update Institution : {}", slimInstitutionDTO);

        return institutionRepository
            .findById(slimInstitutionDTO.getInstCode())
            .map(
                existingInstitution -> {
                    slimInstitutionMapper.partialUpdate(existingInstitution, slimInstitutionDTO);
                    return existingInstitution;
                }
            )
            .map(institutionRepository::save)
            .map(slimInstitutionMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SlimInstitutionDTO> findAll() {
        log.debug("Request to get all Institutions");
        return institutionRepository.findAll().stream().map(slimInstitutionMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SlimInstitutionDTO> findOne(Long id) {
        log.debug("Request to get Institution : {}", id);
        return institutionRepository.findById(id).map(slimInstitutionMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Institution : {}", id);
        institutionRepository.deleteById(id);
    }
}
