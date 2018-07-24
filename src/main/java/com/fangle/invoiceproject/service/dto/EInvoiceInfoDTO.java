package com.fangle.invoiceproject.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the EInvoiceInfo entity.
 */
public class EInvoiceInfoDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 12)
    private String fpdm;

    @NotNull
    @Size(max = 8)
    private String fphm;

    @Size(max = 14)
    private String kprq;

    @Size(max = 20)
    private String xfsh;

    @Size(max = 14)
    private String xfmc;

    @Size(max = 20)
    private String gfsh;

    @Size(max = 14)
    private String gfmc;

    @Size(max = 11)
    private String jehj;

    @Size(max = 11)
    private String sehj;

    @Size(max = 60)
    private String url;

    private Instant createTime;

    private Instant updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFpdm() {
        return fpdm;
    }

    public void setFpdm(String fpdm) {
        this.fpdm = fpdm;
    }

    public String getFphm() {
        return fphm;
    }

    public void setFphm(String fphm) {
        this.fphm = fphm;
    }

    public String getKprq() {
        return kprq;
    }

    public void setKprq(String kprq) {
        this.kprq = kprq;
    }

    public String getXfsh() {
        return xfsh;
    }

    public void setXfsh(String xfsh) {
        this.xfsh = xfsh;
    }

    public String getXfmc() {
        return xfmc;
    }

    public void setXfmc(String xfmc) {
        this.xfmc = xfmc;
    }

    public String getGfsh() {
        return gfsh;
    }

    public void setGfsh(String gfsh) {
        this.gfsh = gfsh;
    }

    public String getGfmc() {
        return gfmc;
    }

    public void setGfmc(String gfmc) {
        this.gfmc = gfmc;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

        EInvoiceInfoDTO eInvoiceInfoDTO = (EInvoiceInfoDTO) o;
        if (eInvoiceInfoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), eInvoiceInfoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EInvoiceInfoDTO{" +
            "id=" + getId() +
            ", fpdm='" + getFpdm() + "'" +
            ", fphm='" + getFphm() + "'" +
            ", kprq='" + getKprq() + "'" +
            ", xfsh='" + getXfsh() + "'" +
            ", xfmc='" + getXfmc() + "'" +
            ", gfsh='" + getGfsh() + "'" +
            ", gfmc='" + getGfmc() + "'" +
            ", jehj='" + getJehj() + "'" +
            ", sehj='" + getSehj() + "'" +
            ", url='" + getUrl() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            "}";
    }
}
