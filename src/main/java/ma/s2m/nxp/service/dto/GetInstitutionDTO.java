package ma.s2m.nxp.service.dto;

import java.io.Serializable;
import java.util.Objects;

public class GetInstitutionDTO implements Serializable {

    private Long instCode;

    private String identifier;

    private String Label;

    private InstitutionTypeSlimGetInstitutionDTO institutionTypeSlimGetInstitutionDTO;

    private CountrySlimInstitutionDTO countrySlimInstitutionDTO;

    private CurrencySlimGetInstitutionDTO currencySlimGetInstitutionDTO;

    private ContactSlimGetInstitutionDTO contactSlimGetInstitutionDTO;

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

    public InstitutionTypeSlimGetInstitutionDTO getInstitutionTypeSlimGetInstitutionDTO() {
        return institutionTypeSlimGetInstitutionDTO;
    }

    public void setInstitutionTypeSlimGetInstitutionDTO(InstitutionTypeSlimGetInstitutionDTO institutionTypeSlimGetInstitutionDTO) {
        this.institutionTypeSlimGetInstitutionDTO = institutionTypeSlimGetInstitutionDTO;
    }

    public CountrySlimInstitutionDTO getCountrySlimInstitutionDTO() {
        return countrySlimInstitutionDTO;
    }

    public void setCountrySlimInstitutionDTO(CountrySlimInstitutionDTO countrySlimInstitutionDTO) {
        this.countrySlimInstitutionDTO = countrySlimInstitutionDTO;
    }

    public CurrencySlimGetInstitutionDTO getCurrencySlimGetInstitutionDTO() {
        return currencySlimGetInstitutionDTO;
    }

    public void setCurrencySlimGetInstitutionDTO(CurrencySlimGetInstitutionDTO currencySlimGetInstitutionDTO) {
        this.currencySlimGetInstitutionDTO = currencySlimGetInstitutionDTO;
    }

    public ContactSlimGetInstitutionDTO getContactSlimGetInstitutionDTO() {
        return contactSlimGetInstitutionDTO;
    }

    public void setContactSlimGetInstitutionDTO(ContactSlimGetInstitutionDTO contactSlimGetInstitutionDTO) {
        this.contactSlimGetInstitutionDTO = contactSlimGetInstitutionDTO;
    }

    public LogoDTO getLogoDTO() {
        return logo;
    }

    public void setLogoDTO(LogoDTO logoDTO) {
        this.logo = logoDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GetInstitutionDTO)) return false;
        GetInstitutionDTO that = (GetInstitutionDTO) o;
        return instCode.equals(that.instCode) && identifier.equals(that.identifier) && Label.equals(that.Label) && institutionTypeSlimGetInstitutionDTO.equals(that.institutionTypeSlimGetInstitutionDTO) && countrySlimInstitutionDTO.equals(that.countrySlimInstitutionDTO) && currencySlimGetInstitutionDTO.equals(that.currencySlimGetInstitutionDTO) && contactSlimGetInstitutionDTO.equals(that.contactSlimGetInstitutionDTO) && logo.equals(that.logo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(instCode, identifier, Label, institutionTypeSlimGetInstitutionDTO, countrySlimInstitutionDTO, currencySlimGetInstitutionDTO, contactSlimGetInstitutionDTO, logo);
    }

    @Override
    public String toString() {
        return "GetInstitutionDTO{" +
            "instCode=" + instCode +
            ", identifier='" + identifier + '\'' +
            ", Label='" + Label + '\'' +
            ", institutionTypeSlimGetInstitutionDTO=" + institutionTypeSlimGetInstitutionDTO +
            ", countrySlimInstitutionDTO=" + countrySlimInstitutionDTO +
            ", currencySlimGetInstitutionDTO=" + currencySlimGetInstitutionDTO +
            ", contactSlimGetInstitutionDTO=" + contactSlimGetInstitutionDTO +
            ", logoDTO=" + logo +
            '}';
    }
}
