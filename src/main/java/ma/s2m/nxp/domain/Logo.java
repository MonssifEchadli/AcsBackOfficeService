package ma.s2m.nxp.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Logo.
 */
@Entity
@Table(name = "logo")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Logo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long logCode;

    @Column(name = "name")
    private String name;

    @Column(name = "mime")
    private String mime;

    @Column(name = "length")
    private Long length;

    @Lob
    @Column(name = "data", length = 65555)
    private String data;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getLogCode() {
        return logCode;
    }

    public void setLogCode(Long logCode) {
        this.logCode = logCode;
    }

    public Logo logCode(Long logCode) {
        this.logCode = logCode;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public Logo name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMime() {
        return this.mime;
    }

    public Logo mime(String mime) {
        this.mime = mime;
        return this;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public Long getLength() {
        return this.length;
    }

    public Logo length(Long length) {
        this.length = length;
        return this;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    public String getData() {
        return this.data;
    }

    public Logo data(String data) {
        this.data = data;
        return this;
    }

    public void setData(String data) {
        this.data = data;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Logo)) {
            return false;
        }
        return logCode != null && logCode.equals(((Logo) o).logCode);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Logo{" +
            "logCode=" + getLogCode() +
            ", name='" + getName() + "'" +
            ", mime='" + getMime() + "'" +
            ", length=" + getLength() +
            ", data='" + getData() + "'" +
            "}";
    }
}
