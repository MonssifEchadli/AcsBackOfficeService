package ma.s2m.nxp.service.mapper;

import ma.s2m.nxp.domain.*;
import ma.s2m.nxp.service.dto.InstitutionTypeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link InstitutionType} and its DTO {@link InstitutionTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface InstitutionTypeMapper extends EntityMapper<InstitutionTypeDTO, InstitutionType> {
    @Named("istCode")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "istCode", source = "istCode")
    InstitutionTypeDTO toDtoId(InstitutionType institutionType);
}
