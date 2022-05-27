package ma.s2m.nxp.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Currency.
 */
@Entity
@Table(name = "currency")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Currency implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long curCode;

    @Column(name = "cur_alph_code")
    private String curAlphCode;

    @Column(name = "cur_defa_numb_deci")
    private Long curDefaNumbDeci;

    @Column(name = "cur_labe")
    private String curLabe;

    @Column(name = "cur_symb")
    private String curSymb;

    @Column(name = "cur_iden")
    private String curIden;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getCurCode() {
        return curCode;
    }

    public void setCurCode(Long curCode) {
        this.curCode = curCode;
    }

    public Currency curCode(Long curCode) {
        this.curCode = curCode;
        return this;
    }

    public String getCurAlphCode() {
        return this.curAlphCode;
    }

    public Currency curAlphCode(String curAlphCode) {
        this.curAlphCode = curAlphCode;
        return this;
    }

    public void setCurAlphCode(String curAlphCode) {
        this.curAlphCode = curAlphCode;
    }

    public Long getCurDefaNumbDeci() {
        return this.curDefaNumbDeci;
    }

    public Currency curDefaNumbDeci(Long curDefaNumbDeci) {
        this.curDefaNumbDeci = curDefaNumbDeci;
        return this;
    }

    public void setCurDefaNumbDeci(Long curDefaNumbDeci) {
        this.curDefaNumbDeci = curDefaNumbDeci;
    }

    public String getCurLabe() {
        return this.curLabe;
    }

    public Currency curLabe(String curLabe) {
        this.curLabe = curLabe;
        return this;
    }

    public void setCurLabe(String curLabe) {
        this.curLabe = curLabe;
    }

    public String getCurSymb() {
        return this.curSymb;
    }

    public Currency curSymb(String curSymb) {
        this.curSymb = curSymb;
        return this;
    }

    public void setCurSymb(String curSymb) {
        this.curSymb = curSymb;
    }

    public String getCurIden() {
        return this.curIden;
    }

    public Currency curIden(String curIden) {
        this.curIden = curIden;
        return this;
    }

    public void setCurIden(String curIden) {
        this.curIden = curIden;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Currency)) {
            return false;
        }
        return curCode != null && curCode.equals(((Currency) o).curCode);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Currency{" +
            "curCode=" + getCurCode() +
            ", curAlphCode='" + getCurAlphCode() + "'" +
            ", curDefaNumbDeci=" + getCurDefaNumbDeci() +
            ", curLabe='" + getCurLabe() + "'" +
            ", curSymb='" + getCurSymb() + "'" +
            ", curIden='" + getCurIden() + "'" +
            "}";
    }
}
