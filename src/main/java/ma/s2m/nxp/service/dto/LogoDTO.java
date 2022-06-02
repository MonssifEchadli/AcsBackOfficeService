package ma.s2m.nxp.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link ma.s2m.nxp.domain.Logo} entity.
 */
public class LogoDTO implements Serializable {

    private Long logCode;

    private String name;

    private String data;

    public Long getLogCode() {
        return logCode;
    }

    public void setLogCode(Long logCode) {
        this.logCode = logCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LogoDTO)) {
            return false;
        }

        LogoDTO logoDTO = (LogoDTO) o;
        if (this.logCode == null) {
            return false;
        }
        return Objects.equals(this.logCode, logoDTO.logCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.logCode);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LogoDTO{" +
            "logCode=" + getLogCode() +
            ", name='" + getName() + "'" +
            ", data='" + getData() + "'" +
            "}";
    }
}
