package ma.s2m.nxp.service.dto;

import java.util.Objects;

public class CurrencySlimGetInstitutionDTO {

    private Long curCode;

    private String curAlphCode;

    public Long getCurCode() {
        return curCode;
    }

    public String getCurAlphCode() {
        return curAlphCode;
    }

    public void setCurCode(Long curCode) {
        this.curCode = curCode;
    }

    public void setCurAlphCode(String curAlphCode) {
        this.curAlphCode = curAlphCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CurrencySlimGetInstitutionDTO)) return false;
        CurrencySlimGetInstitutionDTO that = (CurrencySlimGetInstitutionDTO) o;
        return curCode.equals(that.curCode) && curAlphCode.equals(that.curAlphCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(curCode, curAlphCode);
    }

    @Override
    public String toString() {
        return "CurrencySlimGetInstitutionDTO{" +
            "curCode=" + curCode +
            ", curAlphCode='" + curAlphCode + '\'' +
            '}';
    }
}
