package ma.s2m.nxp.service.mapper;


import ma.s2m.nxp.domain.Institution;
import ma.s2m.nxp.service.dto.LogoDTO;
import ma.s2m.nxp.service.dto.SlimInstitutionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
    componentModel = "spring",
    uses= {ContactSlimMapper.class, LogoDTO.class, InstitutionTypeSlimMapper.class, CurrencySlimMapper.class, CountrySlimMapper.class }
)
public interface SlimInstitutionMapper extends EntityMapper<SlimInstitutionDTO, Institution>{
    @Mapping(target = "istContact", source = "istContact")
    @Mapping(target = "istLogo", source = "istLogo")
    @Mapping(target = "istCountry", source = "istCountry")
    @Mapping(target = "istCurrency", source = "istCurrency")
    @Mapping(target = "istType", source = "istType")
    SlimInstitutionDTO toDto(Institution s);
}
