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
    private Long contInstCode;

    @Column(name = "cont_inst_first_name")
    private String contInstFirstName;

    @Column(name = "cont_inst_last_name")
    private String contInstLastName;

    @Column(name = "cont_inst_phone")
    private String contInstPhone;

    @Column(name = "cont_inst_email")
    private String contInstEmail;

    @Column(name = "cont_inst_job")
    private String contInstJob;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getContInstCode() {
        return contInstCode;
    }

    public void setContInstCode(Long contInstCode) {
        this.contInstCode = contInstCode;
    }

    public ContactInstitution contInstCode(Long contInstCode) {
        this.contInstCode = contInstCode;
        return this;
    }

    public String getContInstFirstName() {
        return this.contInstFirstName;
    }

    public ContactInstitution contInstFirstName(String contInstFirstName) {
        this.contInstFirstName = contInstFirstName;
        return this;
    }

    public void setContInstFirstName(String contInstFirstName) {
        this.contInstFirstName = contInstFirstName;
    }

    public String getContInstLastName() {
        return this.contInstLastName;
    }

    public ContactInstitution contInstLastName(String contInstLastName) {
        this.contInstLastName = contInstLastName;
        return this;
    }

    public void setContInstLastName(String contInstLastName) {
        this.contInstLastName = contInstLastName;
    }

    public String getContInstPhone() {
        return this.contInstPhone;
    }

    public ContactInstitution contInstPhone(String contInstPhone) {
        this.contInstPhone = contInstPhone;
        return this;
    }

    public void setContInstPhone(String contInstPhone) {
        this.contInstPhone = contInstPhone;
    }

    public String getContInstEmail() {
        return this.contInstEmail;
    }

    public ContactInstitution contInstEmail(String contInstEmail) {
        this.contInstEmail = contInstEmail;
        return this;
    }

    public void setContInstEmail(String contInstEmail) {
        this.contInstEmail = contInstEmail;
    }

    public String getContInstJob() {
        return this.contInstJob;
    }

    public ContactInstitution contInstJob(String contInstJob) {
        this.contInstJob = contInstJob;
        return this;
    }

    public void setContInstJob(String contInstJob) {
        this.contInstJob = contInstJob;
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
        return contInstCode != null && contInstCode.equals(((ContactInstitution) o).contInstCode);
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
            "contInstCode=" + getContInstCode() +
            ", contInstFirstName='" + getContInstFirstName() + "'" +
            ", contInstLastName='" + getContInstLastName() + "'" +
            ", contInstPhone='" + getContInstPhone() + "'" +
            ", contInstEmail='" + getContInstEmail() + "'" +
            ", contInstJob='" + getContInstJob() + "'" +
            "}";
    }
}
