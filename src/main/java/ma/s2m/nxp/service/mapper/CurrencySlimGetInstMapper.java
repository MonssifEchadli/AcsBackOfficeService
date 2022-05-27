package ma.s2m.nxp.service.mapper;

import ma.s2m.nxp.domain.*;
import ma.s2m.nxp.service.dto.CurrencySlimGetInstitutionDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {})
public interface CurrencySlimGetInstMapper extends EntityMapper<CurrencySlimGetInstitutionDTO, Currency>{
    @Named("curCode")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "curCode", source = "curCode")
    CurrencySlimGetInstitutionDTO toDtoId(Currency currency);
}
