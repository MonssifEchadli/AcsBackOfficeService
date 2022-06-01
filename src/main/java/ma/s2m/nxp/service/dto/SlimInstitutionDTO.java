package ma.s2m.nxp.service.dto;

import java.io.Serializable;
import java.util.Objects;

public class SlimInstitutionDTO implements Serializable {

    private Long instCode;

    private String identifier;

    private String label;

    private String address;

    private InstitutionTypeSlimDTO istType;

    private CountrySlimDTO istCountry;

    private CurrencySlimDTO istCurrency;

    private ContactSlimDTO istContact;

    private LogoDTO istLogo;

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

    public InstitutionTypeSlimDTO getIstType() {
        return istType;
    }

    public void setIstType(InstitutionTypeSlimDTO istType) {
        this.istType = istType;
    }

    public CountrySlimDTO getIstCountry() {
        return istCountry;
    }

    public void setIstCountry(CountrySlimDTO istCountry) {
        this.istCountry = istCountry;
    }

    public CurrencySlimDTO getIstCurrency() {
        return istCurrency;
    }

    public void setIstCurrency(CurrencySlimDTO istCurrency) {
        this.istCurrency = istCurrency;
    }

    public ContactSlimDTO getIstContact() {
        return istContact;
    }

    public void setIstContact(ContactSlimDTO istContact) {
        this.istContact = istContact;
    }

    public LogoDTO getIstLogo() {
        return istLogo;
    }

    public void setIstLogo(LogoDTO istLogo) {
        this.istLogo = istLogo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SlimInstitutionDTO)) return false;
        SlimInstitutionDTO that = (SlimInstitutionDTO) o;
        return Objects.equals(instCode, that.instCode) && Objects.equals(identifier, that.identifier) && Objects.equals(label, that.label) && Objects.equals(address, that.address) && Objects.equals(istType, that.istType) && Objects.equals(istCountry, that.istCountry) && Objects.equals(istCurrency, that.istCurrency) && Objects.equals(istContact, that.istContact) && Objects.equals(istLogo, that.istLogo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(instCode, identifier, label, address, istType, istCountry, istCurrency, istContact, istLogo);
    }

    @Override
    public String toString() {
        return "SlimInstitutionDTO{" +
            "instCode=" + instCode +
            ", identifier='" + identifier + '\'' +
            ", label='" + label + '\'' +
            ", address='" + address + '\'' +
            ", istType=" + istType +
            ", istCountry=" + istCountry +
            ", istCurrency=" + istCurrency +
            ", istContact=" + istContact +
            ", istLogo=" + istLogo +
            '}';
    }
}
