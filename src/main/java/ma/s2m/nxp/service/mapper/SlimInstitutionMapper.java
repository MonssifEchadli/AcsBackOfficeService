package ma.s2m.nxp.service.mapper;


import ma.s2m.nxp.domain.*;
import ma.s2m.nxp.service.dto.SlimInstitutionDTO;
import ma.s2m.nxp.service.dto.LogoDTO;
import org.mapstruct.*;

@Mapper(
    componentModel = "spring",
    uses= {ContactSlimMapper.class, LogoDTO.class, InstitutionTypeSlimMapper.class, CurrencySlimMapper.class, CountrySlimMapper.class }
)
public interface SlimInstitutionMapper extends EntityMapper<SlimInstitutionDTO, Institution>{
    @Mapping(target = "contact", source = "contact")
    @Mapping(target = "logo", source = "logo")
    @Mapping(target = "instType", source = "instType")
    @Mapping(target = "currency", source = "currency")
    @Mapping(target = "country", source = "country")
    SlimInstitutionDTO toDto(Institution s);
}
