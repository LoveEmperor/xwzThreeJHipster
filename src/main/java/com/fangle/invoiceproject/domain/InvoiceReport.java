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
 * 发票统计信息
 */
@ApiModel(description = "发票统计信息")
@Entity
@Table(name = "invoice_report")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class InvoiceReport implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 开具总份数
     */
    @NotNull
    @Size(max = 11)
    @ApiModelProperty(value = "开具总份数", required = true)
    @Column(name = "fs", length = 11, nullable = false)
    private String fs;

    /**
     * 金额合计
     */
    @NotNull
    @Size(max = 12)
    @ApiModelProperty(value = "金额合计", required = true)
    @Column(name = "jehj", length = 12, nullable = false)
    private String jehj;

    /**
     * 税额合计
     */
    @Size(max = 8)
    @ApiModelProperty(value = "税额合计")
    @Column(name = "sehj", length = 8)
    private String sehj;

    /**
     * 价税合计
     */
    @Size(max = 20)
    @ApiModelProperty(value = "价税合计")
    @Column(name = "jshj", length = 20)
    private String jshj;

    /**
     * 开票纳税人合计
     */
    @Size(max = 20)
    @ApiModelProperty(value = "开票纳税人合计")
    @Column(name = "nsrsum", length = 20)
    private String nsrsum;

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

    public String getFs() {
        return fs;
    }

    public InvoiceReport fs(String fs) {
        this.fs = fs;
        return this;
    }

    public void setFs(String fs) {
        this.fs = fs;
    }

    public String getJehj() {
        return jehj;
    }

    public InvoiceReport jehj(String jehj) {
        this.jehj = jehj;
        return this;
    }

    public void setJehj(String jehj) {
        this.jehj = jehj;
    }

    public String getSehj() {
        return sehj;
    }

    public InvoiceReport sehj(String sehj) {
        this.sehj = sehj;
        return this;
    }

    public void setSehj(String sehj) {
        this.sehj = sehj;
    }

    public String getJshj() {
        return jshj;
    }

    public InvoiceReport jshj(String jshj) {
        this.jshj = jshj;
        return this;
    }

    public void setJshj(String jshj) {
        this.jshj = jshj;
    }

    public String getNsrsum() {
        return nsrsum;
    }

    public InvoiceReport nsrsum(String nsrsum) {
        this.nsrsum = nsrsum;
        return this;
    }

    public void setNsrsum(String nsrsum) {
        this.nsrsum = nsrsum;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public InvoiceReport createTime(Instant createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public Instant getUpdateTime() {
        return updateTime;
    }

    public InvoiceReport updateTime(Instant updateTime) {
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
        InvoiceReport invoiceReport = (InvoiceReport) o;
        if (invoiceReport.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), invoiceReport.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "InvoiceReport{" +
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
