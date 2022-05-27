package ma.s2m.nxp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import ma.s2m.nxp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ContactInstitutionTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ContactInstitution.class);
        ContactInstitution contactInstitution1 = new ContactInstitution();
        contactInstitution1.setContInstCode(1L);
        ContactInstitution contactInstitution2 = new ContactInstitution();
        contactInstitution2.setContInstCode(contactInstitution1.getContInstCode());
        assertThat(contactInstitution1).isEqualTo(contactInstitution2);
        contactInstitution2.setContInstCode(2L);
        assertThat(contactInstitution1).isNotEqualTo(contactInstitution2);
        contactInstitution1.setContInstCode(null);
        assertThat(contactInstitution1).isNotEqualTo(contactInstitution2);
    }
}
