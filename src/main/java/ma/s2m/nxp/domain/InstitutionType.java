package ma.s2m.nxp.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A InstitutionType.
 */
@Entity
@Table(name = "institution_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class InstitutionType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long istCode;

    @Column(name = "ist_labe")
    private String istLabe;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getIstCode() {
        return istCode;
    }

    public void setIstCode(Long istCode) {
        this.istCode = istCode;
    }

    public InstitutionType istCode(Long istCode) {
        this.istCode = istCode;
        return this;
    }

    public String getIstLabe() {
        return this.istLabe;
    }

    public InstitutionType istLabe(String istLabe) {
        this.istLabe = istLabe;
        return this;
    }

    public void setIstLabe(String istLabe) {
        this.istLabe = istLabe;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InstitutionType)) {
            return false;
        }
        return istCode != null && istCode.equals(((InstitutionType) o).istCode);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InstitutionType{" +
            "istCode=" + getIstCode() +
            ", istLabe='" + getIstLabe() + "'" +
            "}";
    }
}
