package ma.s2m.nxp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ContactInstitutionMapperTest {

    private ContactInstitutionMapper contactInstitutionMapper;

    @BeforeEach
    public void setUp() {
        contactInstitutionMapper = new ContactInstitutionMapperImpl();
    }
}
