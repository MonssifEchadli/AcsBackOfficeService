package ma.s2m.nxp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import ma.s2m.nxp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CountryTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Country.class);
        Country country1 = new Country();
        country1.setCouCode(1L);
        Country country2 = new Country();
        country2.setCouCode(country1.getCouCode());
        assertThat(country1).isEqualTo(country2);
        country2.setCouCode(2L);
        assertThat(country1).isNotEqualTo(country2);
        country1.setCouCode(null);
        assertThat(country1).isNotEqualTo(country2);
    }
}
