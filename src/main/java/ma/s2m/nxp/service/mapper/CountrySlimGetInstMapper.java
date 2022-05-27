package ma.s2m.nxp.service.mapper;

import ma.s2m.nxp.domain.Country;
import ma.s2m.nxp.service.dto.CountrySlimInstitutionDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {})
public interface CountrySlimGetInstMapper extends EntityMapper<CountrySlimInstitutionDTO, Country>{
    @Named("couCode")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "couCode", source = "couCode")
    CountrySlimInstitutionDTO toDtoId(Country country);
}
