package ma.s2m.nxp.service.dto;

import java.io.Serializable;
import java.util.Objects;

public class InstitutionTypeSlimGetInstitutionDTO implements Serializable {

    private Long istCode;

    private String istLabe;

    public Long getInstCode() {
        return istCode;
    }

    public String getIdentifier() {
        return istLabe;
    }

    public void setInstCode(Long instCode) {
        this.istCode = instCode;
    }

    public void setIdentifier(String identifier) {
        this.istLabe = identifier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InstitutionTypeSlimGetInstitutionDTO)) return false;
        InstitutionTypeSlimGetInstitutionDTO that = (InstitutionTypeSlimGetInstitutionDTO) o;
        return istCode.equals(that.istCode) && istLabe.equals(that.istLabe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(istCode, istLabe);
    }

    @Override
    public String toString() {
        return "InstitutionTypeSlimGetInstitutionDTO{" +
            "instCode=" + istCode +
            ", identifier='" + istLabe + '\'' +
            '}';
    }
}
