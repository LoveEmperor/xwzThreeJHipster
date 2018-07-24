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
 * 用户发票信息
 */
@ApiModel(description = "用户发票信息")
@Entity
@Table(name = "invoice_info")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class InvoiceInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 开票时间
     */
    @NotNull
    @ApiModelProperty(value = "开票时间", required = true)
    @Column(name = "kp_time", nullable = false)
    private String kpTime;

    /**
     * 1:开票中、2：成功、3：失败
     */
    @NotNull
    @Size(max = 4)
    @ApiModelProperty(value = "1:开票中、2：成功、3：失败", required = true)
    @Column(name = "kp_status", length = 4, nullable = false)
    private String kpStatus;

    /**
     * 开票金额(分)
     */
    @NotNull
    @ApiModelProperty(value = "开票金额(分)", required = true)
    @Column(name = "kp_money", nullable = false)
    private Integer kpMoney;

    /**
     * 1:个人、2：企业
     */
    @NotNull
    @Size(max = 4)
    @ApiModelProperty(value = "1:个人、2：企业", required = true)
    @Column(name = "title_type", length = 4, nullable = false)
    private String titleType;

    /**
     * 发票抬头
     */
    @NotNull
    @Size(max = 11)
    @ApiModelProperty(value = "发票抬头", required = true)
    @Column(name = "invoice_title", length = 11, nullable = false)
    private String invoiceTitle;

    /**
     * 企业税号
     */
    @Size(max = 20)
    @ApiModelProperty(value = "企业税号")
    @Column(name = "qyduty_num", length = 20)
    private String qydutyNum;

    /**
     * 发票内容
     */
    @Size(max = 12)
    @ApiModelProperty(value = "发票内容")
    @Column(name = "fp_content", length = 12)
    private String fpContent;

    /**
     * 电子邮箱
     */
    @Size(max = 12)
    @ApiModelProperty(value = "电子邮箱")
    @Column(name = "email", length = 12)
    private String email;

    /**
     * 手机号码
     */
    @Size(max = 12)
    @ApiModelProperty(value = "手机号码")
    @Column(name = "phone", length = 12)
    private String phone;

    /**
     * 关联支付记录id
     */
    @NotNull
    @Size(max = 50)
    @ApiModelProperty(value = "关联支付记录id", required = true)
    @Column(name = "pay_recordid", length = 50, nullable = false)
    private String payRecordid;

    /**
     * 查询开票结果唯一标识
     */
    @Size(max = 20)
    @ApiModelProperty(value = "查询开票结果唯一标识")
    @Column(name = "invgu_id", length = 20)
    private String invguId;

    /**
     * 发票代码
     */
    @Size(max = 20)
    @ApiModelProperty(value = "发票代码")
    @Column(name = "fpdm", length = 20)
    private String fpdm;

    /**
     * 发票号码
     */
    @Size(max = 20)
    @ApiModelProperty(value = "发票号码")
    @Column(name = "fphm", length = 20)
    private String fphm;

    @Size(max = 20)
    @Column(name = "kpr", length = 20)
    private String kpr;

    @Size(max = 20)
    @Column(name = "kpr_id", length = 20)
    private String kprId;

    /**
     * 失败原因
     */
    @Size(max = 255)
    @ApiModelProperty(value = "失败原因")
    @Column(name = "reson", length = 255)
    private String reson;

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

    @OneToOne
    @JoinColumn(unique = true)
    private EInvoiceInfo extract;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKpTime() {
        return kpTime;
    }

    public InvoiceInfo kpTime(String kpTime) {
        this.kpTime = kpTime;
        return this;
    }

    public void setKpTime(String kpTime) {
        this.kpTime = kpTime;
    }

    public String getKpStatus() {
        return kpStatus;
    }

    public InvoiceInfo kpStatus(String kpStatus) {
        this.kpStatus = kpStatus;
        return this;
    }

    public void setKpStatus(String kpStatus) {
        this.kpStatus = kpStatus;
    }

    public Integer getKpMoney() {
        return kpMoney;
    }

    public InvoiceInfo kpMoney(Integer kpMoney) {
        this.kpMoney = kpMoney;
        return this;
    }

    public void setKpMoney(Integer kpMoney) {
        this.kpMoney = kpMoney;
    }

    public String getTitleType() {
        return titleType;
    }

    public InvoiceInfo titleType(String titleType) {
        this.titleType = titleType;
        return this;
    }

    public void setTitleType(String titleType) {
        this.titleType = titleType;
    }

    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public InvoiceInfo invoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
        return this;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
    }

    public String getQydutyNum() {
        return qydutyNum;
    }

    public InvoiceInfo qydutyNum(String qydutyNum) {
        this.qydutyNum = qydutyNum;
        return this;
    }

    public void setQydutyNum(String qydutyNum) {
        this.qydutyNum = qydutyNum;
    }

    public String getFpContent() {
        return fpContent;
    }

    public InvoiceInfo fpContent(String fpContent) {
        this.fpContent = fpContent;
        return this;
    }

    public void setFpContent(String fpContent) {
        this.fpContent = fpContent;
    }

    public String getEmail() {
        return email;
    }

    public InvoiceInfo email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public InvoiceInfo phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPayRecordid() {
        return payRecordid;
    }

    public InvoiceInfo payRecordid(String payRecordid) {
        this.payRecordid = payRecordid;
        return this;
    }

    public void setPayRecordid(String payRecordid) {
        this.payRecordid = payRecordid;
    }

    public String getInvguId() {
        return invguId;
    }

    public InvoiceInfo invguId(String invguId) {
        this.invguId = invguId;
        return this;
    }

    public void setInvguId(String invguId) {
        this.invguId = invguId;
    }

    public String getFpdm() {
        return fpdm;
    }

    public InvoiceInfo fpdm(String fpdm) {
        this.fpdm = fpdm;
        return this;
    }

    public void setFpdm(String fpdm) {
        this.fpdm = fpdm;
    }

    public String getFphm() {
        return fphm;
    }

    public InvoiceInfo fphm(String fphm) {
        this.fphm = fphm;
        return this;
    }

    public void setFphm(String fphm) {
        this.fphm = fphm;
    }

    public String getKpr() {
        return kpr;
    }

    public InvoiceInfo kpr(String kpr) {
        this.kpr = kpr;
        return this;
    }

    public void setKpr(String kpr) {
        this.kpr = kpr;
    }

    public String getKprId() {
        return kprId;
    }

    public InvoiceInfo kprId(String kprId) {
        this.kprId = kprId;
        return this;
    }

    public void setKprId(String kprId) {
        this.kprId = kprId;
    }

    public String getReson() {
        return reson;
    }

    public InvoiceInfo reson(String reson) {
        this.reson = reson;
        return this;
    }

    public void setReson(String reson) {
        this.reson = reson;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public InvoiceInfo createTime(Instant createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public Instant getUpdateTime() {
        return updateTime;
    }

    public InvoiceInfo updateTime(Instant updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public EInvoiceInfo getExtract() {
        return extract;
    }

    public InvoiceInfo extract(EInvoiceInfo eInvoiceInfo) {
        this.extract = eInvoiceInfo;
        return this;
    }

    public void setExtract(EInvoiceInfo eInvoiceInfo) {
        this.extract = eInvoiceInfo;
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
        InvoiceInfo invoiceInfo = (InvoiceInfo) o;
        if (invoiceInfo.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), invoiceInfo.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "InvoiceInfo{" +
            "id=" + getId() +
            ", kpTime='" + getKpTime() + "'" +
            ", kpStatus='" + getKpStatus() + "'" +
            ", kpMoney=" + getKpMoney() +
            ", titleType='" + getTitleType() + "'" +
            ", invoiceTitle='" + getInvoiceTitle() + "'" +
            ", qydutyNum='" + getQydutyNum() + "'" +
            ", fpContent='" + getFpContent() + "'" +
            ", email='" + getEmail() + "'" +
            ", phone='" + getPhone() + "'" +
            ", payRecordid='" + getPayRecordid() + "'" +
            ", invguId='" + getInvguId() + "'" +
            ", fpdm='" + getFpdm() + "'" +
            ", fphm='" + getFphm() + "'" +
            ", kpr='" + getKpr() + "'" +
            ", kprId='" + getKprId() + "'" +
            ", reson='" + getReson() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            "}";
    }
}
