package ma.s2m.nxp.service.mapper;


import ma.s2m.nxp.domain.ContactInstitution;
import ma.s2m.nxp.service.dto.ContactSlimGetInstitutionDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring" , uses={})
public interface ContactSlimGetInstMapper extends EntityMapper<ContactSlimGetInstitutionDTO, ContactInstitution> {
    @Named("contInstCode")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "contInstCode", source = "contInstCode")
    ContactSlimGetInstitutionDTO toDtoId(ContactInstitution contactInstitution);
}
