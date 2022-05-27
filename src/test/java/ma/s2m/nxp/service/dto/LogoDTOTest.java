package ma.s2m.nxp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import ma.s2m.nxp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class LogoDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LogoDTO.class);
        LogoDTO logoDTO1 = new LogoDTO();
        logoDTO1.setLogCode(1L);
        LogoDTO logoDTO2 = new LogoDTO();
        assertThat(logoDTO1).isNotEqualTo(logoDTO2);
        logoDTO2.setLogCode(logoDTO1.getLogCode());
        assertThat(logoDTO1).isEqualTo(logoDTO2);
        logoDTO2.setLogCode(2L);
        assertThat(logoDTO1).isNotEqualTo(logoDTO2);
        logoDTO1.setLogCode(null);
        assertThat(logoDTO1).isNotEqualTo(logoDTO2);
    }
}
