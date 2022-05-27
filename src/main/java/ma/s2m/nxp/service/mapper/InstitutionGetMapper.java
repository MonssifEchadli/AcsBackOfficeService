package ma.s2m.nxp.service.mapper;


import ma.s2m.nxp.domain.*;
import ma.s2m.nxp.service.dto.GetInstitutionDTO;
import ma.s2m.nxp.service.dto.LogoDTO;
import org.mapstruct.*;

@Mapper(
    componentModel = "spring",
    uses= {ContactSlimGetInstMapper.class, LogoDTO.class, InstitutionTypeSlimGetInstMapper.class, CurrencySlimGetInstMapper.class, CountrySlimGetInstMapper.class }
)
public interface InstitutionGetMapper extends EntityMapper<GetInstitutionDTO, Institution>{
    @Mapping(target = "contactSlimGetInstitutionDTO", source = "contact")
    @Mapping(target = "logoDTO", source = "logo")
    @Mapping(target = "institutionTypeSlimGetInstitutionDTO", source = "instType")
    @Mapping(target = "currencySlimGetInstitutionDTO", source = "currency")
    @Mapping(target = "countrySlimInstitutionDTO", source = "country")
    GetInstitutionDTO toDto(Institution s);
}
