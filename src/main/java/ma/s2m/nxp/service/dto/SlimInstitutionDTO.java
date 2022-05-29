package ma.s2m.nxp.service.dto;

import java.io.Serializable;
import java.util.Objects;

public class SlimInstitutionDTO implements Serializable {

    private Long instCode;

    private String identifier;

    private String Label;

    private InstitutionTypeSlimDTO instType;

    private CountrySlimDTO country;

    private CurrencySlimDTO currency;

    private ContactSlimDTO contact;

    private LogoDTO logo;

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
        return Label;
    }

    public void setLabel(String label) {
        Label = label;
    }

    public InstitutionTypeSlimDTO getInstType() {
        return instType;
    }

    public void setInstType(InstitutionTypeSlimDTO instType) {
        this.instType = instType;
    }

    public CountrySlimDTO getCountry() {
        return country;
    }

    public void setCountry(CountrySlimDTO country) {
        this.country = country;
    }

    public CurrencySlimDTO getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencySlimDTO currency) {
        this.currency = currency;
    }

    public ContactSlimDTO getContact() {
        return contact;
    }

    public void setContact(ContactSlimDTO contact) {
        this.contact = contact;
    }

    public LogoDTO getLogo() {
        return logo;
    }

    public void setLogo(LogoDTO logo) {
        this.logo = logo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SlimInstitutionDTO)) return false;
        SlimInstitutionDTO that = (SlimInstitutionDTO) o;
        return instCode.equals(that.instCode) && identifier.equals(that.identifier) && Label.equals(that.Label) && instType.equals(that.instType) && country.equals(that.country) && currency.equals(that.currency) && contact.equals(that.contact) && logo.equals(that.logo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(instCode, identifier, Label, instType, country, currency, contact, logo);
    }

    @Override
    public String toString() {
        return "SlimInstitutionDTO{" +
            "instCode=" + instCode +
            ", identifier='" + identifier + '\'' +
            ", Label='" + Label + '\'' +
            ", instType=" + instType +
            ", country=" + country +
            ", currency=" + currency +
            ", contact=" + contact +
            ", logo=" + logo +
            '}';
    }
}
