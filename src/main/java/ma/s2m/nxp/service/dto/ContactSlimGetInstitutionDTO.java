package ma.s2m.nxp.service.dto;

import java.util.Objects;

public class ContactSlimGetInstitutionDTO {

    private Long contInstCode;

    private String contInstFirstName;

    private String contInstLastName;

    public Long getContInstCode() {
        return contInstCode;
    }

    public void setContInstCode(Long contInstCode) {
        this.contInstCode = contInstCode;
    }

    public String getContInstFirstName() {
        return contInstFirstName;
    }

    public void setContInstFirstName(String contInstFirstName) {
        this.contInstFirstName = contInstFirstName;
    }

    public String getContInstLastName() {
        return contInstLastName;
    }

    public void setContInstLastName(String contInstLastName) {
        this.contInstLastName = contInstLastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContactSlimGetInstitutionDTO)) return false;
        ContactSlimGetInstitutionDTO that = (ContactSlimGetInstitutionDTO) o;
        return contInstCode.equals(that.contInstCode) && contInstFirstName.equals(that.contInstFirstName) && contInstLastName.equals(that.contInstLastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contInstCode, contInstFirstName, contInstLastName);
    }

    @Override
    public String toString() {
        return "ContactSlimGetInstitutionDTO{" +
            "contInstCode=" + contInstCode +
            ", contInstFirstName='" + contInstFirstName + '\'' +
            ", contInstLastName='" + contInstLastName + '\'' +
            '}';
    }
}
