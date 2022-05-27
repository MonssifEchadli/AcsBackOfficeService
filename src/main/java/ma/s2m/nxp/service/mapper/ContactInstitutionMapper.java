package ma.s2m.nxp.service.mapper;

import ma.s2m.nxp.domain.*;
import ma.s2m.nxp.service.dto.ContactInstitutionDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ContactInstitution} and its DTO {@link ContactInstitutionDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ContactInstitutionMapper extends EntityMapper<ContactInstitutionDTO, ContactInstitution> {
    @Named("contInstCode")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "contInstCode", source = "contInstCode")
    ContactInstitutionDTO toDtoId(ContactInstitution contactInstitution);
}
