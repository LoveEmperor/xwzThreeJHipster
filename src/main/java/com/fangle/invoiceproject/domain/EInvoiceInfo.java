package com.fangle.invoiceproject.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * 发票电子版式文件
 */
@ApiModel(description = "发票电子版式文件")
@Entity
@Table(name = "e_invoice_info")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EInvoiceInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 发票代码
     */
    @NotNull
    @Size(max = 12)
    @ApiModelProperty(value = "发票代码", required = true)
    @Column(name = "fpdm", length = 12, nullable = false)
    private String fpdm;

    /**
     * 发票号码
     */
    @NotNull
    @Size(max = 8)
    @ApiModelProperty(value = "发票号码", required = true)
    @Column(name = "fphm", length = 8, nullable = false)
    private String fphm;

    /**
     * 开票日期
     */
    @Size(max = 14)
    @ApiModelProperty(value = "开票日期")
    @Column(name = "kprq", length = 14)
    private String kprq;

    /**
     * 销方税号
     */
    @Size(max = 20)
    @ApiModelProperty(value = "销方税号")
    @Column(name = "xfsh", length = 20)
    private String xfsh;

    /**
     * 销方名称
     */
    @Size(max = 14)
    @ApiModelProperty(value = "销方名称")
    @Column(name = "xfmc", length = 14)
    private String xfmc;

    /**
     * 购方税号
     */
    @Size(max = 20)
    @ApiModelProperty(value = "购方税号")
    @Column(name = "gfsh", length = 20)
    private String gfsh;

    /**
     * 购方名称
     */
    @Size(max = 14)
    @ApiModelProperty(value = "购方名称")
    @Column(name = "gfmc", length = 14)
    private String gfmc;

    /**
     * 金额合计
     */
    @Size(max = 11)
    @ApiModelProperty(value = "金额合计")
    @Column(name = "jehj", length = 11)
    private String jehj;

    /**
     * 税额合计
     */
    @Size(max = 11)
    @ApiModelProperty(value = "税额合计")
    @Column(name = "sehj", length = 11)
    private String sehj;

    /**
     * pdf文件路径
     */
    @Size(max = 60)
    @ApiModelProperty(value = "pdf文件路径")
    @Column(name = "url", length = 60)
    private String url;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @Column(name = "create_time")
    private Instant createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    @Column(name = "update_time")
    private Instant updateTime;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFpdm() {
        return fpdm;
    }

    public EInvoiceInfo fpdm(String fpdm) {
        this.fpdm = fpdm;
        return this;
    }

    public void setFpdm(String fpdm) {
        this.fpdm = fpdm;
    }

    public String getFphm() {
        return fphm;
    }

    public EInvoiceInfo fphm(String fphm) {
        this.fphm = fphm;
        return this;
    }

    public void setFphm(String fphm) {
        this.fphm = fphm;
    }

    public String getKprq() {
        return kprq;
    }

    public EInvoiceInfo kprq(String kprq) {
        this.kprq = kprq;
        return this;
    }

    public void setKprq(String kprq) {
        this.kprq = kprq;
    }

    public String getXfsh() {
        return xfsh;
    }

    public EInvoiceInfo xfsh(String xfsh) {
        this.xfsh = xfsh;
        return this;
    }

    public void setXfsh(String xfsh) {
        this.xfsh = xfsh;
    }

    public String getXfmc() {
        return xfmc;
    }

    public EInvoiceInfo xfmc(String xfmc) {
        this.xfmc = xfmc;
        return this;
    }

    public void setXfmc(String xfmc) {
        this.xfmc = xfmc;
    }

    public String getGfsh() {
        return gfsh;
    }

    public EInvoiceInfo gfsh(String gfsh) {
        this.gfsh = gfsh;
        return this;
    }

    public void setGfsh(String gfsh) {
        this.gfsh = gfsh;
    }

    public String getGfmc() {
        return gfmc;
    }

    public EInvoiceInfo gfmc(String gfmc) {
        this.gfmc = gfmc;
        return this;
    }

    public void setGfmc(String gfmc) {
        this.gfmc = gfmc;
    }

    public String getJehj() {
        return jehj;
    }

    public EInvoiceInfo jehj(String jehj) {
        this.jehj = jehj;
        return this;
    }

    public void setJehj(String jehj) {
        this.jehj = jehj;
    }

    public String getSehj() {
        return sehj;
    }

    public EInvoiceInfo sehj(String sehj) {
        this.sehj = sehj;
        return this;
    }

    public void setSehj(String sehj) {
        this.sehj = sehj;
    }

    public String getUrl() {
        return url;
    }

    public EInvoiceInfo url(String url) {
        this.url = url;
        return this;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public EInvoiceInfo createTime(Instant createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public Instant getUpdateTime() {
        return updateTime;
    }

    public EInvoiceInfo updateTime(Instant updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EInvoiceInfo eInvoiceInfo = (EInvoiceInfo) o;
        if (eInvoiceInfo.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), eInvoiceInfo.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EInvoiceInfo{" +
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
