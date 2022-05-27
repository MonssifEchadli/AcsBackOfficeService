package ma.s2m.nxp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import ma.s2m.nxp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class InstitutionTypeDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InstitutionTypeDTO.class);
        InstitutionTypeDTO institutionTypeDTO1 = new InstitutionTypeDTO();
        institutionTypeDTO1.setIstCode(1L);
        InstitutionTypeDTO institutionTypeDTO2 = new InstitutionTypeDTO();
        assertThat(institutionTypeDTO1).isNotEqualTo(institutionTypeDTO2);
        institutionTypeDTO2.setIstCode(institutionTypeDTO1.getIstCode());
        assertThat(institutionTypeDTO1).isEqualTo(institutionTypeDTO2);
        institutionTypeDTO2.setIstCode(2L);
        assertThat(institutionTypeDTO1).isNotEqualTo(institutionTypeDTO2);
        institutionTypeDTO1.setIstCode(null);
        assertThat(institutionTypeDTO1).isNotEqualTo(institutionTypeDTO2);
    }
}
