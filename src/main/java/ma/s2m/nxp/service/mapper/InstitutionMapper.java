package ma.s2m.nxp.service.mapper;

import ma.s2m.nxp.domain.*;
import ma.s2m.nxp.service.dto.InstitutionDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Institution} and its DTO {@link InstitutionDTO}.
 */
@Mapper(
    componentModel = "spring",
    uses = {  LogoMapper.class, InstitutionTypeMapper.class, CurrencyMapper.class, CountryMapper.class }
)
public interface InstitutionMapper extends EntityMapper<InstitutionDTO, Institution> {
    @Mapping(target = "logo", source = "logo", qualifiedByName = "logCode")
    @Mapping(target = "instType", source = "instType", qualifiedByName = "istCode")
    @Mapping(target = "currency", source = "currency", qualifiedByName = "curCode")
    @Mapping(target = "country", source = "country", qualifiedByName = "couCode")
    InstitutionDTO toDto(Institution s);
}
