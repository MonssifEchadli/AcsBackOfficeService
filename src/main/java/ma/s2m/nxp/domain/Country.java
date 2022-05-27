package ma.s2m.nxp.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Country.
 */
@Entity
@Table(name = "country")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Country implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long couCode;

    @Column(name = "cou_iden")
    private String couIden;

    @Column(name = "cou_name")
    private String couName;

    @Column(name = "cou_nati")
    private String couNati;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getCouCode() {
        return couCode;
    }

    public void setCouCode(Long couCode) {
        this.couCode = couCode;
    }

    public Country couCode(Long couCode) {
        this.couCode = couCode;
        return this;
    }

    public String getCouIden() {
        return this.couIden;
    }

    public Country couIden(String couIden) {
        this.couIden = couIden;
        return this;
    }

    public void setCouIden(String couIden) {
        this.couIden = couIden;
    }

    public String getCouName() {
        return this.couName;
    }

    public Country couName(String couName) {
        this.couName = couName;
        return this;
    }

    public void setCouName(String couName) {
        this.couName = couName;
    }

    public String getCouNati() {
        return this.couNati;
    }

    public Country couNati(String couNati) {
        this.couNati = couNati;
        return this;
    }

    public void setCouNati(String couNati) {
        this.couNati = couNati;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Country)) {
            return false;
        }
        return couCode != null && couCode.equals(((Country) o).couCode);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Country{" +
            "couCode=" + getCouCode() +
            ", couIden='" + getCouIden() + "'" +
            ", couName='" + getCouName() + "'" +
            ", couNati='" + getCouNati() + "'" +
            "}";
    }
}
