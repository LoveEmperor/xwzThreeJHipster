package com.fangle.invoiceproject.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the InvoiceReport entity.
 */
public class InvoiceReportDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 11)
    private String fs;

    @NotNull
    @Size(max = 12)
    private String jehj;

    @Size(max = 8)
    private String sehj;

    @Size(max = 20)
    private String jshj;

    @Size(max = 20)
    private String nsrsum;

    private Instant createTime;

    private Instant updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFs() {
        return fs;
    }

    public void setFs(String fs) {
        this.fs = fs;
    }

    public String getJehj() {
        return jehj;
    }

    public void setJehj(String jehj) {
        this.jehj = jehj;
    }

    public String getSehj() {
        return sehj;
    }

    public void setSehj(String sehj) {
        this.sehj = sehj;
    }

    public String getJshj() {
        return jshj;
    }

    public void setJshj(String jshj) {
        this.jshj = jshj;
    }

    public String getNsrsum() {
        return nsrsum;
    }

    public void setNsrsum(String nsrsum) {
        this.nsrsum = nsrsum;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public Instant getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        InvoiceReportDTO invoiceReportDTO = (InvoiceReportDTO) o;
        if (invoiceReportDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), invoiceReportDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "InvoiceReportDTO{" +
            "id=" + getId() +
            ", fs='" + getFs() + "'" +
            ", jehj='" + getJehj() + "'" +
            ", sehj='" + getSehj() + "'" +
            ", jshj='" + getJshj() + "'" +
            ", nsrsum='" + getNsrsum() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            "}";
    }
}
