package ma.s2m.nxp.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Institution.
 */
@Entity
@Table(name = "institution")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Institution implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long instCode;

    @Column(name = "identifier")
    private String identifier;

    @Column(name = "label")
    private String label;

    @Column(name = "address")
    private String address;

    @Column(name = "creation_date")
    private LocalDate creationDate;

    @Column(name = "ins_chll_infr_hedr")
    private String insChllInfrHedr;

    @Column(name = "ins_chll_infr_text")
    private String insChllInfrText;

    @Column(name = "ins_chll_infr_labl")
    private String insChllInfrLabl;

    @Column(name = "ins_sbmt_athn_labl")
    private String insSbmtAthnLabl;

    @Column(name = "ins_rsnd_infr_labl")
    private String insRsndInfrLabl;

    @Column(name = "ins_why_infr_labl")
    private String insWhyInfrLabl;

    @OneToOne
    @JoinColumn(unique = true)
    private ContactInstitution contact;

    @OneToOne
    @JoinColumn(unique = true)
    private Logo logo;

    @ManyToOne
    private InstitutionType instType;

    @ManyToOne
    private Currency currency;

    @ManyToOne
    private Country country;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getInstCode() {
        return instCode;
    }

    public void setInstCode(Long instCode) {
        this.instCode = instCode;
    }

    public Institution instCode(Long instCode) {
        this.instCode = instCode;
        return this;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public Institution identifier(String identifier) {
        this.identifier = identifier;
        return this;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getLabel() {
        return this.label;
    }

    public Institution label(String label) {
        this.label = label;
        return this;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getAddress() {
        return this.address;
    }

    public Institution address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getCreationDate() {
        return this.creationDate;
    }

    public Institution creationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public String getInsChllInfrHedr() {
        return this.insChllInfrHedr;
    }

    public Institution insChllInfrHedr(String insChllInfrHedr) {
        this.insChllInfrHedr = insChllInfrHedr;
        return this;
    }

    public void setInsChllInfrHedr(String insChllInfrHedr) {
        this.insChllInfrHedr = insChllInfrHedr;
    }

    public String getInsChllInfrText() {
        return this.insChllInfrText;
    }

    public Institution insChllInfrText(String insChllInfrText) {
        this.insChllInfrText = insChllInfrText;
        return this;
    }

    public void setInsChllInfrText(String insChllInfrText) {
        this.insChllInfrText = insChllInfrText;
    }

    public String getInsChllInfrLabl() {
        return this.insChllInfrLabl;
    }

    public Institution insChllInfrLabl(String insChllInfrLabl) {
        this.insChllInfrLabl = insChllInfrLabl;
        return this;
    }

    public void setInsChllInfrLabl(String insChllInfrLabl) {
        this.insChllInfrLabl = insChllInfrLabl;
    }

    public String getInsSbmtAthnLabl() {
        return this.insSbmtAthnLabl;
    }

    public Institution insSbmtAthnLabl(String insSbmtAthnLabl) {
        this.insSbmtAthnLabl = insSbmtAthnLabl;
        return this;
    }

    public void setInsSbmtAthnLabl(String insSbmtAthnLabl) {
        this.insSbmtAthnLabl = insSbmtAthnLabl;
    }

    public String getInsRsndInfrLabl() {
        return this.insRsndInfrLabl;
    }

    public Institution insRsndInfrLabl(String insRsndInfrLabl) {
        this.insRsndInfrLabl = insRsndInfrLabl;
        return this;
    }

    public void setInsRsndInfrLabl(String insRsndInfrLabl) {
        this.insRsndInfrLabl = insRsndInfrLabl;
    }

    public String getInsWhyInfrLabl() {
        return this.insWhyInfrLabl;
    }

    public Institution insWhyInfrLabl(String insWhyInfrLabl) {
        this.insWhyInfrLabl = insWhyInfrLabl;
        return this;
    }

    public void setInsWhyInfrLabl(String insWhyInfrLabl) {
        this.insWhyInfrLabl = insWhyInfrLabl;
    }

    public ContactInstitution getContact() {
        return this.contact;
    }

    public Institution contact(ContactInstitution contactInstitution) {
        this.setContact(contactInstitution);
        return this;
    }

    public void setContact(ContactInstitution contactInstitution) {
        this.contact = contactInstitution;
    }

    public Logo getLogo() {
        return this.logo;
    }

    public Institution logo(Logo logo) {
        this.setLogo(logo);
        return this;
    }

    public void setLogo(Logo logo) {
        this.logo = logo;
    }

    public InstitutionType getInstType() {
        return this.instType;
    }

    public Institution instType(InstitutionType institutionType) {
        this.setInstType(institutionType);
        return this;
    }

    public void setInstType(InstitutionType institutionType) {
        this.instType = institutionType;
    }

    public Currency getCurrency() {
        return this.currency;
    }

    public Institution currency(Currency currency) {
        this.setCurrency(currency);
        return this;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Country getCountry() {
        return this.country;
    }

    public Institution country(Country country) {
        this.setCountry(country);
        return this;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Institution)) {
            return false;
        }
        return instCode != null && instCode.equals(((Institution) o).instCode);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Institution{" +
            "instCode=" + getInstCode() +
            ", identifier='" + getIdentifier() + "'" +
            ", label='" + getLabel() + "'" +
            ", address='" + getAddress() + "'" +
            ", creationDate='" + getCreationDate() + "'" +
            ", insChllInfrHedr='" + getInsChllInfrHedr() + "'" +
            ", insChllInfrText='" + getInsChllInfrText() + "'" +
            ", insChllInfrLabl='" + getInsChllInfrLabl() + "'" +
            ", insSbmtAthnLabl='" + getInsSbmtAthnLabl() + "'" +
            ", insRsndInfrLabl='" + getInsRsndInfrLabl() + "'" +
            ", insWhyInfrLabl='" + getInsWhyInfrLabl() + "'" +
            "}";
    }
}
