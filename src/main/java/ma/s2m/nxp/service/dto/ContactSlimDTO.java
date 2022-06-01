package ma.s2m.nxp.service.dto;

import java.io.Serializable;
import java.util.Objects;

public class ContactSlimDTO implements Serializable {

    private Long conInstCode;

    private String conInstFirstName;

    private String conInstLastName;

    private String conInstPhone;

    private String conInstEmail;

    public Long getConInstCode() {
        return conInstCode;
    }

    public void setConInstCode(Long conInstCode) {
        this.conInstCode = conInstCode;
    }

    public String getConInstFirstName() {
        return conInstFirstName;
    }

    public void setConInstFirstName(String conInstFirstName) {
        this.conInstFirstName = conInstFirstName;
    }

    public String getConInstLastName() {
        return conInstLastName;
    }

    public void setConInstLastName(String conInstLastName) {
        this.conInstLastName = conInstLastName;
    }

    public String getConInstPhone() {
        return conInstPhone;
    }

    public void setConInstPhone(String conInstPhone) {
        this.conInstPhone = conInstPhone;
    }

    public String getConInstEmail() {
        return conInstEmail;
    }

    public void setConInstEmail(String conInstEmail) {
        this.conInstEmail = conInstEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContactSlimDTO)) return false;
        ContactSlimDTO that = (ContactSlimDTO) o;
        return conInstCode.equals(that.conInstCode) && conInstFirstName.equals(that.conInstFirstName) && conInstLastName.equals(that.conInstLastName) && conInstPhone.equals(that.conInstPhone) && conInstEmail.equals(that.conInstEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(conInstCode, conInstFirstName, conInstLastName, conInstPhone, conInstEmail);
    }

    @Override
    public String toString() {
        return "ContactSlimDTO{" +
            "conInstCode=" + conInstCode +
            ", conInstFirstName='" + conInstFirstName + '\'' +
            ", conInstLastName='" + conInstLastName + '\'' +
            ", conInstPhone='" + conInstPhone + '\'' +
            ", conInstEmail='" + conInstEmail + '\'' +
            '}';
    }
}
