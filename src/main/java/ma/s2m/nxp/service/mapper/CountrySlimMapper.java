package ma.s2m.nxp.service.mapper;

import ma.s2m.nxp.domain.Country;
import ma.s2m.nxp.service.dto.CountrySlimDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {})
public interface CountrySlimMapper extends EntityMapper<CountrySlimDTO, Country>{
    @Named("couCode")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "couCode", source = "couCode")
    CountrySlimDTO toDtoId(Country country);
}
