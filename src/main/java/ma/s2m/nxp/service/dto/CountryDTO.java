package ma.s2m.nxp.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link ma.s2m.nxp.domain.Country} entity.
 */
public class CountryDTO implements Serializable {

    private Long couCode;

    private String couIden;

    private String couName;

    private String couNati;

    public Long getCouCode() {
        return couCode;
    }

    public void setCouCode(Long couCode) {
        this.couCode = couCode;
    }

    public String getCouIden() {
        return couIden;
    }

    public void setCouIden(String couIden) {
        this.couIden = couIden;
    }

    public String getCouName() {
        return couName;
    }

    public void setCouName(String couName) {
        this.couName = couName;
    }

    public String getCouNati() {
        return couNati;
    }

    public void setCouNati(String couNati) {
        this.couNati = couNati;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CountryDTO)) {
            return false;
        }

        CountryDTO countryDTO = (CountryDTO) o;
        if (this.couCode == null) {
            return false;
        }
        return Objects.equals(this.couCode, countryDTO.couCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.couCode);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CountryDTO{" +
            "couCode=" + getCouCode() +
            ", couIden='" + getCouIden() + "'" +
            ", couName='" + getCouName() + "'" +
            ", couNati='" + getCouNati() + "'" +
            "}";
    }
}
