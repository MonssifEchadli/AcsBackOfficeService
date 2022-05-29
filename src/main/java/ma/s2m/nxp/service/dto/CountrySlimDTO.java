package ma.s2m.nxp.service.dto;

import java.io.Serializable;
import java.util.Objects;

public class CountrySlimDTO implements Serializable {

    private Long couCode;

    private String couName;

    public Long getCouCode() {
        return couCode;
    }

    public void setCouCode(Long couCode) {
        this.couCode = couCode;
    }

    public String getCouName() {
        return couName;
    }

    public void setCouName(String couName) {
        this.couName = couName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CountrySlimDTO)) return false;
        CountrySlimDTO that = (CountrySlimDTO) o;
        return couCode.equals(that.couCode) && couName.equals(that.couName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(couCode, couName);
    }

    @Override
    public String toString() {
        return "CountrySlimInstitutionDTO{" +
            "couCode=" + couCode +
            ", couName='" + couName + '\'' +
            '}';
    }
}
