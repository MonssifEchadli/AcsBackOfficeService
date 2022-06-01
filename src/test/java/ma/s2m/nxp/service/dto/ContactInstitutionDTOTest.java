package ma.s2m.nxp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import ma.s2m.nxp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ContactInstitutionDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ContactInstitutionDTO.class);
        ContactInstitutionDTO contactInstitutionDTO1 = new ContactInstitutionDTO();
        contactInstitutionDTO1.setConInstCode(1L);
        ContactInstitutionDTO contactInstitutionDTO2 = new ContactInstitutionDTO();
        assertThat(contactInstitutionDTO1).isNotEqualTo(contactInstitutionDTO2);
        contactInstitutionDTO2.setConInstCode(contactInstitutionDTO1.getConInstCode());
        assertThat(contactInstitutionDTO1).isEqualTo(contactInstitutionDTO2);
        contactInstitutionDTO2.setConInstCode(2L);
        assertThat(contactInstitutionDTO1).isNotEqualTo(contactInstitutionDTO2);
        contactInstitutionDTO1.setConInstCode(null);
        assertThat(contactInstitutionDTO1).isNotEqualTo(contactInstitutionDTO2);
    }
}
