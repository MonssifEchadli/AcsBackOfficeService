package ma.s2m.nxp.service.dto;

import java.io.Serializable;
import java.util.Objects;

public class ContactSlimDTO implements Serializable {

    private Long contInstCode;

    private String contInstFirstName;

    private String contInstLastName;

    private String contInstPhone;

    private String contInstEmail;

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

    public String getContInstPhone() {
        return contInstPhone;
    }

    public void setContInstPhone(String contInstPhone) {
        this.contInstPhone = contInstPhone;
    }

    public String getContInstEmail() {
        return contInstEmail;
    }

    public void setContInstEmail(String contInstEmail) {
        this.contInstEmail = contInstEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContactSlimDTO)) return false;
        ContactSlimDTO that = (ContactSlimDTO) o;
        return contInstCode.equals(that.contInstCode) && contInstFirstName.equals(that.contInstFirstName) && contInstLastName.equals(that.contInstLastName) && Objects.equals(contInstPhone, that.contInstPhone) && Objects.equals(contInstEmail, that.contInstEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contInstCode, contInstFirstName, contInstLastName, contInstPhone, contInstEmail);
    }

    @Override
    public String toString() {
        return "ContactSlimGetInstitutionDTO{" +
            "contInstCode=" + contInstCode +
            ", contInstFirstName='" + contInstFirstName + '\'' +
            ", contInstLastName='" + contInstLastName + '\'' +
            ", contInstPhone='" + contInstPhone + '\'' +
            ", contInstEmail='" + contInstEmail + '\'' +
            '}';
    }
}
