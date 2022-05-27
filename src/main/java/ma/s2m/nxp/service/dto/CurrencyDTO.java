package ma.s2m.nxp.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link ma.s2m.nxp.domain.Currency} entity.
 */
public class CurrencyDTO implements Serializable {

    private Long curCode;

    private String curAlphCode;

    private Long curDefaNumbDeci;

    private String curLabe;

    private String curSymb;

    private String curIden;

    public Long getCurCode() {
        return curCode;
    }

    public void setCurCode(Long curCode) {
        this.curCode = curCode;
    }

    public String getCurAlphCode() {
        return curAlphCode;
    }

    public void setCurAlphCode(String curAlphCode) {
        this.curAlphCode = curAlphCode;
    }

    public Long getCurDefaNumbDeci() {
        return curDefaNumbDeci;
    }

    public void setCurDefaNumbDeci(Long curDefaNumbDeci) {
        this.curDefaNumbDeci = curDefaNumbDeci;
    }

    public String getCurLabe() {
        return curLabe;
    }

    public void setCurLabe(String curLabe) {
        this.curLabe = curLabe;
    }

    public String getCurSymb() {
        return curSymb;
    }

    public void setCurSymb(String curSymb) {
        this.curSymb = curSymb;
    }

    public String getCurIden() {
        return curIden;
    }

    public void setCurIden(String curIden) {
        this.curIden = curIden;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CurrencyDTO)) {
            return false;
        }

        CurrencyDTO currencyDTO = (CurrencyDTO) o;
        if (this.curCode == null) {
            return false;
        }
        return Objects.equals(this.curCode, currencyDTO.curCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.curCode);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CurrencyDTO{" +
            "curCode=" + getCurCode() +
            ", curAlphCode='" + getCurAlphCode() + "'" +
            ", curDefaNumbDeci=" + getCurDefaNumbDeci() +
            ", curLabe='" + getCurLabe() + "'" +
            ", curSymb='" + getCurSymb() + "'" +
            ", curIden='" + getCurIden() + "'" +
            "}";
    }
}
