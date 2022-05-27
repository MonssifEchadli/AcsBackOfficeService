package ma.s2m.nxp.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link ma.s2m.nxp.domain.Institution} entity.
 */
public class InstitutionDTO implements Serializable {

    private Long instCode;

    private String identifier;

    private String label;

    private String address;

    private LocalDate creationDate;

    private String insChllInfrHedr;

    private String insChllInfrText;

    private String insChllInfrLabl;

    private String insSbmtAthnLabl;

    private String insRsndInfrLabl;

    private String insWhyInfrLabl;

    private ContactInstitutionDTO contact;

    private LogoDTO logo;

    private InstitutionTypeDTO instType;

    private CurrencyDTO currency;

    private CountryDTO country;

    public Long getInstCode() {
        return instCode;
    }

    public void setInstCode(Long instCode) {
        this.instCode = instCode;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public String getInsChllInfrHedr() {
        return insChllInfrHedr;
    }

    public void setInsChllInfrHedr(String insChllInfrHedr) {
        this.insChllInfrHedr = insChllInfrHedr;
    }

    public String getInsChllInfrText() {
        return insChllInfrText;
    }

    public void setInsChllInfrText(String insChllInfrText) {
        this.insChllInfrText = insChllInfrText;
    }

    public String getInsChllInfrLabl() {
        return insChllInfrLabl;
    }

    public void setInsChllInfrLabl(String insChllInfrLabl) {
        this.insChllInfrLabl = insChllInfrLabl;
    }

    public String getInsSbmtAthnLabl() {
        return insSbmtAthnLabl;
    }

    public void setInsSbmtAthnLabl(String insSbmtAthnLabl) {
        this.insSbmtAthnLabl = insSbmtAthnLabl;
    }

    public String getInsRsndInfrLabl() {
        return insRsndInfrLabl;
    }

    public void setInsRsndInfrLabl(String insRsndInfrLabl) {
        this.insRsndInfrLabl = insRsndInfrLabl;
    }

    public String getInsWhyInfrLabl() {
        return insWhyInfrLabl;
    }

    public void setInsWhyInfrLabl(String insWhyInfrLabl) {
        this.insWhyInfrLabl = insWhyInfrLabl;
    }

    public ContactInstitutionDTO getContact() {
        return contact;
    }

    public void setContact(ContactInstitutionDTO contact) {
        this.contact = contact;
    }

    public LogoDTO getLogo() {
        return logo;
    }

    public void setLogo(LogoDTO logo) {
        this.logo = logo;
    }

    public InstitutionTypeDTO getInstType() {
        return instType;
    }

    public void setInstType(InstitutionTypeDTO instType) {
        this.instType = instType;
    }

    public CurrencyDTO getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyDTO currency) {
        this.currency = currency;
    }

    public CountryDTO getCountry() {
        return country;
    }

    public void setCountry(CountryDTO country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InstitutionDTO)) {
            return false;
        }

        InstitutionDTO institutionDTO = (InstitutionDTO) o;
        if (this.instCode == null) {
            return false;
        }
        return Objects.equals(this.instCode, institutionDTO.instCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.instCode);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InstitutionDTO{" +
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
            ", contact=" + getContact() +
            ", logo=" + getLogo() +
            ", instType=" + getInstType() +
            ", currency=" + getCurrency() +
            ", country=" + getCountry() +
            "}";
    }
}
