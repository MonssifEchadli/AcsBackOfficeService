package ma.s2m.nxp.service.mapper;

import ma.s2m.nxp.domain.*;
import ma.s2m.nxp.service.dto.CurrencySlimDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {})
public interface CurrencySlimMapper extends EntityMapper<CurrencySlimDTO, Currency>{
    @Named("curCode")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "curCode", source = "curCode")
    CurrencySlimDTO toDtoId(Currency currency);
}
