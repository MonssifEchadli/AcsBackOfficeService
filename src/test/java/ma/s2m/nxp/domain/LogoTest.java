package ma.s2m.nxp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import ma.s2m.nxp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class LogoTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Logo.class);
        Logo logo1 = new Logo();
        logo1.setLogCode(1L);
        Logo logo2 = new Logo();
        logo2.setLogCode(logo1.getLogCode());
        assertThat(logo1).isEqualTo(logo2);
        logo2.setLogCode(2L);
        assertThat(logo1).isNotEqualTo(logo2);
        logo1.setLogCode(null);
        assertThat(logo1).isNotEqualTo(logo2);
    }
}
