package ma.s2m.nxp.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link ma.s2m.nxp.domain.ContactInstitution} entity.
 */
public class ContactInstitutionDTO implements Serializable {

    private Long conInstCode;

    private String conInstFirstName;

    private String conInstLastName;

    private String conInstPhone;

    private String conInstEmail;

    private String conInstJob;

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

    public String getConInstJob() {
        return conInstJob;
    }

    public void setConInstJob(String conInstJob) {
        this.conInstJob = conInstJob;
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
        if (this.conInstCode == null) {
            return false;
        }
        return Objects.equals(this.conInstCode, contactInstitutionDTO.conInstCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.conInstCode);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ContactInstitutionDTO{" +
            "conInstCode=" + getConInstCode() +
            ", conInstFirstName='" + getConInstFirstName() + "'" +
            ", conInstLastName='" + getConInstLastName() + "'" +
            ", conInstPhone='" + getConInstPhone() + "'" +
            ", conInstEmail='" + getConInstEmail() + "'" +
            ", conInstJob='" + getConInstJob() + "'" +
            "}";
    }
}
