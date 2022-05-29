package ma.s2m.nxp.service.dto;

import java.io.Serializable;
import java.util.Objects;

public class InstitutionTypeSlimDTO implements Serializable {

    private Long istCode;

    private String istLabe;

    public Long getIstCode() {
        return istCode;
    }

    public void setIstCode(Long istCode) {
        this.istCode = istCode;
    }

    public String getIstLabe() {
        return istLabe;
    }

    public void setIstLabe(String istLabe) {
        this.istLabe = istLabe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InstitutionTypeSlimDTO)) return false;
        InstitutionTypeSlimDTO that = (InstitutionTypeSlimDTO) o;
        return istCode.equals(that.istCode) && istLabe.equals(that.istLabe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(istCode, istLabe);
    }

    @Override
    public String toString() {
        return "InstitutionTypeSlimGetInstitutionDTO{" +
            "istCode=" + istCode +
            ", istLabe='" + istLabe + '\'' +
            '}';
    }
}
