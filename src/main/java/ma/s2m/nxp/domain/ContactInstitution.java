package ma.s2m.nxp.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ContactInstitution.
 */
@Entity
@Table(name = "contact_institution")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ContactInstitution implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long conInstCode;

    @Column(name = "con_inst_first_name")
    private String conInstFirstName;

    @Column(name = "con_inst_last_name")
    private String conInstLastName;

    @Column(name = "con_inst_phone")
    private String conInstPhone;

    @Column(name = "con_inst_email")
    private String conInstEmail;

    @Column(name = "con_inst_job")
    private String conInstJob;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getConInstCode() {
        return conInstCode;
    }

    public void setConInstCode(Long conInstCode) {
        this.conInstCode = conInstCode;
    }

    public ContactInstitution conInstCode(Long conInstCode) {
        this.conInstCode = conInstCode;
        return this;
    }

    public String getConInstFirstName() {
        return this.conInstFirstName;
    }

    public ContactInstitution conInstFirstName(String conInstFirstName) {
        this.conInstFirstName = conInstFirstName;
        return this;
    }

    public void setConInstFirstName(String conInstFirstName) {
        this.conInstFirstName = conInstFirstName;
    }

    public String getConInstLastName() {
        return this.conInstLastName;
    }

    public ContactInstitution conInstLastName(String conInstLastName) {
        this.conInstLastName = conInstLastName;
        return this;
    }

    public void setConInstLastName(String conInstLastName) {
        this.conInstLastName = conInstLastName;
    }

    public String getConInstPhone() {
        return this.conInstPhone;
    }

    public ContactInstitution conInstPhone(String conInstPhone) {
        this.conInstPhone = conInstPhone;
        return this;
    }

    public void setConInstPhone(String conInstPhone) {
        this.conInstPhone = conInstPhone;
    }

    public String getConInstEmail() {
        return this.conInstEmail;
    }

    public ContactInstitution conInstEmail(String conInstEmail) {
        this.conInstEmail = conInstEmail;
        return this;
    }

    public void setConInstEmail(String conInstEmail) {
        this.conInstEmail = conInstEmail;
    }

    public String getConInstJob() {
        return this.conInstJob;
    }

    public ContactInstitution conInstJob(String conInstJob) {
        this.conInstJob = conInstJob;
        return this;
    }

    public void setConInstJob(String conInstJob) {
        this.conInstJob = conInstJob;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ContactInstitution)) {
            return false;
        }
        return conInstCode != null && conInstCode.equals(((ContactInstitution) o).conInstCode);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ContactInstitution{" +
            "conInstCode=" + getConInstCode() +
            ", conInstFirstName='" + getConInstFirstName() + "'" +
            ", conInstLastName='" + getConInstLastName() + "'" +
            ", conInstPhone='" + getConInstPhone() + "'" +
            ", conInstEmail='" + getConInstEmail() + "'" +
            ", conInstJob='" + getConInstJob() + "'" +
            "}";
    }
}
