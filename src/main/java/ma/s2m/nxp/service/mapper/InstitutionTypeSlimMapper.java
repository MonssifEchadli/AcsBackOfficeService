package ma.s2m.nxp.service.mapper;

import ma.s2m.nxp.domain.InstitutionType;
import ma.s2m.nxp.service.dto.InstitutionTypeSlimDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {})
public interface InstitutionTypeSlimMapper extends EntityMapper<InstitutionTypeSlimDTO, InstitutionType>{
    @Named("istCode")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "istCode", source = "istCode")
    InstitutionTypeSlimDTO toDtoId(InstitutionType institutionType);
}
