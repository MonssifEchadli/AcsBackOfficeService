package ma.s2m.nxp.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import ma.s2m.nxp.domain.ContactInstitution;
import ma.s2m.nxp.repository.ContactInstitutionRepository;
import ma.s2m.nxp.service.ContactInstitutionService;
import ma.s2m.nxp.service.dto.ContactInstitutionDTO;
import ma.s2m.nxp.service.mapper.ContactInstitutionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ContactInstitution}.
 */
@Service
@Transactional
public class ContactInstitutionServiceImpl implements ContactInstitutionService {

    private final Logger log = LoggerFactory.getLogger(ContactInstitutionServiceImpl.class);

    private final ContactInstitutionRepository contactInstitutionRepository;

    private final ContactInstitutionMapper contactInstitutionMapper;

    public ContactInstitutionServiceImpl(
        ContactInstitutionRepository contactInstitutionRepository,
        ContactInstitutionMapper contactInstitutionMapper
    ) {
        this.contactInstitutionRepository = contactInstitutionRepository;
        this.contactInstitutionMapper = contactInstitutionMapper;
    }

    @Override
    public ContactInstitutionDTO save(ContactInstitutionDTO contactInstitutionDTO) {
        log.debug("Request to save ContactInstitution : {}", contactInstitutionDTO);
        ContactInstitution contactInstitution = contactInstitutionMapper.toEntity(contactInstitutionDTO);
        contactInstitution = contactInstitutionRepository.save(contactInstitution);
        return contactInstitutionMapper.toDto(contactInstitution);
    }

    @Override
    public Optional<ContactInstitutionDTO> partialUpdate(ContactInstitutionDTO contactInstitutionDTO) {
        log.debug("Request to partially update ContactInstitution : {}", contactInstitutionDTO);

        return contactInstitutionRepository
            .findById(contactInstitutionDTO.getContInstCode())
            .map(
                existingContactInstitution -> {
                    contactInstitutionMapper.partialUpdate(existingContactInstitution, contactInstitutionDTO);
                    return existingContactInstitution;
                }
            )
            .map(contactInstitutionRepository::save)
            .map(contactInstitutionMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContactInstitutionDTO> findAll() {
        log.debug("Request to get all ContactInstitutions");
        return contactInstitutionRepository
            .findAll()
            .stream()
            .map(contactInstitutionMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ContactInstitutionDTO> findOne(Long id) {
        log.debug("Request to get ContactInstitution : {}", id);
        return contactInstitutionRepository.findById(id).map(contactInstitutionMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ContactInstitution : {}", id);
        contactInstitutionRepository.deleteById(id);
    }
}
