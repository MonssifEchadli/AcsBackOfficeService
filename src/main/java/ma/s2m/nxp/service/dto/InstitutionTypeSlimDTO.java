package ma.s2m.nxp.service.dto;

import java.io.Serializable;
import java.util.Objects;

public class InstitutionTypeSlimDTO implements Serializable {

    private Long istCode;

    private String istLabel;

    public Long getIstCode() {
        return istCode;
    }

    public void setIstCode(Long istCode) {
        this.istCode = istCode;
    }

    public String getIstLabel() {
        return istLabel;
    }

    public void setIstLabel(String istLabel) {
        this.istLabel = istLabel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InstitutionTypeSlimDTO)) return false;
        InstitutionTypeSlimDTO that = (InstitutionTypeSlimDTO) o;
        return istCode.equals(that.istCode) && istLabel.equals(that.istLabel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(istCode, istLabel);
    }

    @Override
    public String toString() {
        return "InstitutionTypeSlimDTO{" +
            "istCode=" + istCode +
            ", istLabel='" + istLabel + '\'' +
            '}';
    }
}
