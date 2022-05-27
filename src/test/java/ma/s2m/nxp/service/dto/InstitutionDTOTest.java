package ma.s2m.nxp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import ma.s2m.nxp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class InstitutionDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InstitutionDTO.class);
        InstitutionDTO institutionDTO1 = new InstitutionDTO();
        institutionDTO1.setInstCode(1L);
        InstitutionDTO institutionDTO2 = new InstitutionDTO();
        assertThat(institutionDTO1).isNotEqualTo(institutionDTO2);
        institutionDTO2.setInstCode(institutionDTO1.getInstCode());
        assertThat(institutionDTO1).isEqualTo(institutionDTO2);
        institutionDTO2.setInstCode(2L);
        assertThat(institutionDTO1).isNotEqualTo(institutionDTO2);
        institutionDTO1.setInstCode(null);
        assertThat(institutionDTO1).isNotEqualTo(institutionDTO2);
    }
}
