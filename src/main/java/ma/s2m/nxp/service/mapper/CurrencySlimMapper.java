package ma.s2m.nxp.service.mapper;

import ma.s2m.nxp.domain.Currency;
import ma.s2m.nxp.service.dto.CurrencySlimDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {})
public interface CurrencySlimMapper extends EntityMapper<CurrencySlimDTO, Currency>{
    @Named("curCode")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "curCode", source = "curCode")
    CurrencySlimDTO toDtoId(Currency currency);
}
