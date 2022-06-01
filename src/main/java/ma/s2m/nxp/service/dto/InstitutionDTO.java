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

    private ContactInstitutionDTO istContact;

    private LogoDTO istLogo;

    private CountryDTO istCountry;

    private CurrencyDTO istCurrency;

    private InstitutionTypeDTO istType;

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

    public ContactInstitutionDTO getIstContact() {
        return istContact;
    }

    public void setIstContact(ContactInstitutionDTO istContact) {
        this.istContact = istContact;
    }

    public LogoDTO getIstLogo() {
        return istLogo;
    }

    public void setIstLogo(LogoDTO istLogo) {
        this.istLogo = istLogo;
    }

    public CountryDTO getIstCountry() {
        return istCountry;
    }

    public void setIstCountry(CountryDTO istCountry) {
        this.istCountry = istCountry;
    }

    public CurrencyDTO getIstCurrency() {
        return istCurrency;
    }

    public void setIstCurrency(CurrencyDTO istCurrency) {
        this.istCurrency = istCurrency;
    }

    public InstitutionTypeDTO getIstType() {
        return istType;
    }

    public void setIstType(InstitutionTypeDTO istType) {
        this.istType = istType;
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
            ", istContact=" + getIstContact() +
            ", istLogo=" + getIstLogo() +
            ", istCountry=" + getIstCountry() +
            ", istCurrency=" + getIstCurrency() +
            ", istType=" + getIstType() +
            "}";
    }
}
