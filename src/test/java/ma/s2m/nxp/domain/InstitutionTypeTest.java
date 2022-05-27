package ma.s2m.nxp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import ma.s2m.nxp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class InstitutionTypeTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InstitutionType.class);
        InstitutionType institutionType1 = new InstitutionType();
        institutionType1.setIstCode(1L);
        InstitutionType institutionType2 = new InstitutionType();
        institutionType2.setIstCode(institutionType1.getIstCode());
        assertThat(institutionType1).isEqualTo(institutionType2);
        institutionType2.setIstCode(2L);
        assertThat(institutionType1).isNotEqualTo(institutionType2);
        institutionType1.setIstCode(null);
        assertThat(institutionType1).isNotEqualTo(institutionType2);
    }
}
