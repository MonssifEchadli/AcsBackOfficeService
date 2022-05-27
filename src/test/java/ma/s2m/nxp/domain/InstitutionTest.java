package ma.s2m.nxp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import ma.s2m.nxp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class InstitutionTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Institution.class);
        Institution institution1 = new Institution();
        institution1.setInstCode(1L);
        Institution institution2 = new Institution();
        institution2.setInstCode(institution1.getInstCode());
        assertThat(institution1).isEqualTo(institution2);
        institution2.setInstCode(2L);
        assertThat(institution1).isNotEqualTo(institution2);
        institution1.setInstCode(null);
        assertThat(institution1).isNotEqualTo(institution2);
    }
}
