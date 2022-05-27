package ma.s2m.nxp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import ma.s2m.nxp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CountryDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CountryDTO.class);
        CountryDTO countryDTO1 = new CountryDTO();
        countryDTO1.setCouCode(1L);
        CountryDTO countryDTO2 = new CountryDTO();
        assertThat(countryDTO1).isNotEqualTo(countryDTO2);
        countryDTO2.setCouCode(countryDTO1.getCouCode());
        assertThat(countryDTO1).isEqualTo(countryDTO2);
        countryDTO2.setCouCode(2L);
        assertThat(countryDTO1).isNotEqualTo(countryDTO2);
        countryDTO1.setCouCode(null);
        assertThat(countryDTO1).isNotEqualTo(countryDTO2);
    }
}
