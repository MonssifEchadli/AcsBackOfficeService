package ma.s2m.nxp.service.mapper;

import ma.s2m.nxp.domain.*;
import ma.s2m.nxp.service.dto.LogoDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Logo} and its DTO {@link LogoDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface LogoMapper extends EntityMapper<LogoDTO, Logo> {
    @Named("logCode")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "logCode", source = "logCode")
    LogoDTO toDtoId(Logo logo);
}
