package ma.s2m.nxp.service.mapper;

import ma.s2m.nxp.domain.*;
import ma.s2m.nxp.service.dto.LogoSlimGetInstitutionDto;
import org.mapstruct.*;

public interface LogoSlimGetInstMapper extends EntityMapper<LogoSlimGetInstitutionDto, Logo> {
    @Named("logCode")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "logCode", source = "logCode")
    LogoSlimGetInstitutionDto toDtoId(Logo logo);
}
