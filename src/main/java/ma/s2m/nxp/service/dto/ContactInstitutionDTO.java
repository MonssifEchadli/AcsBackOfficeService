package ma.s2m.nxp.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link ma.s2m.nxp.domain.ContactInstitution} entity.
 */
public class ContactInstitutionDTO implements Serializable {

    private Long contInstCode;

    private String contInstFirstName;

    private String contInstLastName;

    private String contInstPhone;

    private String contInstEmail;

    private String contInstJob;

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

    public String getContInstJob() {
        return contInstJob;
    }

    public void setContInstJob(String contInstJob) {
        this.contInstJob = contInstJob;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ContactInstitutionDTO)) {
            return false;
        }

        ContactInstitutionDTO contactInstitutionDTO = (ContactInstitutionDTO) o;
        if (this.contInstCode == null) {
            return false;
        }
        return Objects.equals(this.contInstCode, contactInstitutionDTO.contInstCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.contInstCode);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ContactInstitutionDTO{" +
            "contInstCode=" + getContInstCode() +
            ", contInstFirstName='" + getContInstFirstName() + "'" +
            ", contInstLastName='" + getContInstLastName() + "'" +
            ", contInstPhone='" + getContInstPhone() + "'" +
            ", contInstEmail='" + getContInstEmail() + "'" +
            ", contInstJob='" + getContInstJob() + "'" +
            "}";
    }
}
