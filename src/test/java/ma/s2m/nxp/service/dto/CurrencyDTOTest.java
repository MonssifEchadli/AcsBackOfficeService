package ma.s2m.nxp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import ma.s2m.nxp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CurrencyDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CurrencyDTO.class);
        CurrencyDTO currencyDTO1 = new CurrencyDTO();
        currencyDTO1.setCurCode(1L);
        CurrencyDTO currencyDTO2 = new CurrencyDTO();
        assertThat(currencyDTO1).isNotEqualTo(currencyDTO2);
        currencyDTO2.setCurCode(currencyDTO1.getCurCode());
        assertThat(currencyDTO1).isEqualTo(currencyDTO2);
        currencyDTO2.setCurCode(2L);
        assertThat(currencyDTO1).isNotEqualTo(currencyDTO2);
        currencyDTO1.setCurCode(null);
        assertThat(currencyDTO1).isNotEqualTo(currencyDTO2);
    }
}
