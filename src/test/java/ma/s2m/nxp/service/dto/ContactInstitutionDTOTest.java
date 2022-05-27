package ma.s2m.nxp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import ma.s2m.nxp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ContactInstitutionDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ContactInstitutionDTO.class);
        ContactInstitutionDTO contactInstitutionDTO1 = new ContactInstitutionDTO();
        contactInstitutionDTO1.setContInstCode(1L);
        ContactInstitutionDTO contactInstitutionDTO2 = new ContactInstitutionDTO();
        assertThat(contactInstitutionDTO1).isNotEqualTo(contactInstitutionDTO2);
        contactInstitutionDTO2.setContInstCode(contactInstitutionDTO1.getContInstCode());
        assertThat(contactInstitutionDTO1).isEqualTo(contactInstitutionDTO2);
        contactInstitutionDTO2.setContInstCode(2L);
        assertThat(contactInstitutionDTO1).isNotEqualTo(contactInstitutionDTO2);
        contactInstitutionDTO1.setContInstCode(null);
        assertThat(contactInstitutionDTO1).isNotEqualTo(contactInstitutionDTO2);
    }
}
