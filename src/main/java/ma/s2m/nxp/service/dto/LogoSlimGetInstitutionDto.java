package ma.s2m.nxp.service.dto;

import javax.persistence.Lob;
import java.util.Arrays;
import java.util.Objects;

public class LogoSlimGetInstitutionDto {

    private Long logCode;

    @Lob
    private byte[] data;

    private String dataContentType;

    public Long getLogCode() {
        return logCode;
    }

    public void setLogCode(Long logCode) {
        this.logCode = logCode;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getDataContentType() {
        return dataContentType;
    }

    public void setDataContentType(String dataContentType) {
        this.dataContentType = dataContentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LogoSlimGetInstitutionDto)) return false;
        LogoSlimGetInstitutionDto that = (LogoSlimGetInstitutionDto) o;
        return logCode.equals(that.logCode) && Arrays.equals(data, that.data) && dataContentType.equals(that.dataContentType);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(logCode, dataContentType);
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }

    @Override
    public String toString() {
        return "LogoSlimInstitutionDto{" +
            "logCode=" + logCode +
            ", data=" + Arrays.toString(data) +
            ", dataContentType='" + dataContentType + '\'' +
            '}';
    }
}
