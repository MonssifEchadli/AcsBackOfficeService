package ma.s2m.nxp.service.mapper;

import ma.s2m.nxp.domain.*;
import ma.s2m.nxp.service.dto.InstitutionDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Institution} and its DTO {@link InstitutionDTO}.
 */
@Mapper(
    componentModel = "spring",
    uses = { ContactInstitutionMapper.class, LogoMapper.class, CountryMapper.class, CurrencyMapper.class, InstitutionTypeMapper.class }
)
public interface InstitutionMapper extends EntityMapper<InstitutionDTO, Institution> {
    @Mapping(target = "istContact", source = "istContact", qualifiedByName = "conInstCode")
    @Mapping(target = "istLogo", source = "istLogo", qualifiedByName = "logCode")
    @Mapping(target = "istCountry", source = "istCountry", qualifiedByName = "couCode")
    @Mapping(target = "istCurrency", source = "istCurrency", qualifiedByName = "curCode")
    @Mapping(target = "istType", source = "istType", qualifiedByName = "istCode")
    InstitutionDTO toDto(Institution s);
}
