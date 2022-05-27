package ma.s2m.nxp.service.mapper;

import ma.s2m.nxp.domain.*;
import ma.s2m.nxp.service.dto.CurrencyDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Currency} and its DTO {@link CurrencyDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CurrencyMapper extends EntityMapper<CurrencyDTO, Currency> {
    @Named("curCode")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "curCode", source = "curCode")
    CurrencyDTO toDtoId(Currency currency);
}
