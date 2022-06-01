package ma.s2m.nxp.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link ma.s2m.nxp.domain.InstitutionType} entity.
 */
public class InstitutionTypeDTO implements Serializable {

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
        if (this == o) {
            return true;
        }
        if (!(o instanceof InstitutionTypeDTO)) {
            return false;
        }

        InstitutionTypeDTO institutionTypeDTO = (InstitutionTypeDTO) o;
        if (this.istCode == null) {
            return false;
        }
        return Objects.equals(this.istCode, institutionTypeDTO.istCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.istCode);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InstitutionTypeDTO{" +
            "istCode=" + getIstCode() +
            ", istLabel='" + getIstLabel() + "'" +
            "}";
    }
}
