package ma.s2m.nxp.service.mapper;

import ma.s2m.nxp.domain.*;
import ma.s2m.nxp.service.dto.InstitutionTypeSlimGetInstitutionDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {})
public interface InstitutionTypeSlimGetInstMapper extends EntityMapper<InstitutionTypeSlimGetInstitutionDTO, InstitutionType>{
    @Named("istCode")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "istCode", source = "istCode")
    InstitutionTypeSlimGetInstitutionDTO toDtoId(InstitutionType institutionType);
}
