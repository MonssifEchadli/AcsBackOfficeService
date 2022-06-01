package ma.s2m.nxp.service.mapper;


import ma.s2m.nxp.domain.ContactInstitution;
import ma.s2m.nxp.service.dto.ContactSlimDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring" , uses={})
public interface ContactSlimMapper extends EntityMapper<ContactSlimDTO, ContactInstitution> {
    @Named("conInstCode")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "conInstCode", source = "conInstCode")
    ContactSlimDTO toDtoId(ContactInstitution contactInstitution);
}
