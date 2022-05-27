package ma.s2m.nxp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import ma.s2m.nxp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CurrencyTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Currency.class);
        Currency currency1 = new Currency();
        currency1.setCurCode(1L);
        Currency currency2 = new Currency();
        currency2.setCurCode(currency1.getCurCode());
        assertThat(currency1).isEqualTo(currency2);
        currency2.setCurCode(2L);
        assertThat(currency1).isNotEqualTo(currency2);
        currency1.setCurCode(null);
        assertThat(currency1).isNotEqualTo(currency2);
    }
}
