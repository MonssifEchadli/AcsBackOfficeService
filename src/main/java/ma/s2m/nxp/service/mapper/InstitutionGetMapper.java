package ma.s2m.nxp.service.mapper;


import ma.s2m.nxp.domain.*;
import ma.s2m.nxp.service.dto.GetInstitutionDTO;
import org.mapstruct.*;

@Mapper(
    componentModel = "spring",
    uses= {ContactSlimGetInstMapper.class, LogoSlimGetInstMapper.class, InstitutionTypeSlimGetInstMapper.class, CurrencySlimGetInstMapper.class, CountrySlimGetInstMapper.class }
)
public interface InstitutionGetMapper extends EntityMapper<GetInstitutionDTO, Institution>{
    @Mapping(target= "contact", source="contact")
    @Mapping(target="logo", source="logo")
    @Mapping(target = "instType", source = "instType", qualifiedByName = "istCode")
    @Mapping(target = "currency", source = "currency", qualifiedByName = "curCode")
    @Mapping(target = "country", source = "country", qualifiedByName = "couCode")
    GetInstitutionDTO toDto(Institution s);
}
