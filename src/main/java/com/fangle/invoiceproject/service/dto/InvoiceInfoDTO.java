package com.fangle.invoiceproject.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the InvoiceInfo entity.
 */
public class InvoiceInfoDTO implements Serializable {

    private Long id;

    @NotNull
    private String kpTime;

    @NotNull
    @Size(max = 4)
    private String kpStatus;

    @NotNull
    private Integer kpMoney;

    @NotNull
    @Size(max = 4)
    private String titleType;

    @NotNull
    @Size(max = 11)
    private String invoiceTitle;

    @Size(max = 20)
    private String qydutyNum;

    @Size(max = 12)
    private String fpContent;

    @Size(max = 12)
    private String email;

    @Size(max = 12)
    private String phone;

    @NotNull
    @Size(max = 50)
    private String payRecordid;

    @Size(max = 20)
    private String invguId;

    @Size(max = 20)
    private String fpdm;

    @Size(max = 20)
    private String fphm;

    @Size(max = 20)
    private String kpr;

    @Size(max = 20)
    private String kprId;

    @Size(max = 255)
    private String reson;

    private Instant createTime;

    private Instant updateTime;

    private Long extractId;

    private String extractFphm;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKpTime() {
        return kpTime;
    }

    public void setKpTime(String kpTime) {
        this.kpTime = kpTime;
    }

    public String getKpStatus() {
        return kpStatus;
    }

    public void setKpStatus(String kpStatus) {
        this.kpStatus = kpStatus;
    }

    public Integer getKpMoney() {
        return kpMoney;
    }

    public void setKpMoney(Integer kpMoney) {
        this.kpMoney = kpMoney;
    }

    public String getTitleType() {
        return titleType;
    }

    public void setTitleType(String titleType) {
        this.titleType = titleType;
    }

    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
    }

    public String getQydutyNum() {
        return qydutyNum;
    }

    public void setQydutyNum(String qydutyNum) {
        this.qydutyNum = qydutyNum;
    }

    public String getFpContent() {
        return fpContent;
    }

    public void setFpContent(String fpContent) {
        this.fpContent = fpContent;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPayRecordid() {
        return payRecordid;
    }

    public void setPayRecordid(String payRecordid) {
        this.payRecordid = payRecordid;
    }

    public String getInvguId() {
        return invguId;
    }

    public void setInvguId(String invguId) {
        this.invguId = invguId;
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

    public String getKpr() {
        return kpr;
    }

    public void setKpr(String kpr) {
        this.kpr = kpr;
    }

    public String getKprId() {
        return kprId;
    }

    public void setKprId(String kprId) {
        this.kprId = kprId;
    }

    public String getReson() {
        return reson;
    }

    public void setReson(String reson) {
        this.reson = reson;
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

    public Long getExtractId() {
        return extractId;
    }

    public void setExtractId(Long eInvoiceInfoId) {
        this.extractId = eInvoiceInfoId;
    }

    public String getExtractFphm() {
        return extractFphm;
    }

    public void setExtractFphm(String eInvoiceInfoFphm) {
        this.extractFphm = eInvoiceInfoFphm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        InvoiceInfoDTO invoiceInfoDTO = (InvoiceInfoDTO) o;
        if (invoiceInfoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), invoiceInfoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "InvoiceInfoDTO{" +
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
            ", extract=" + getExtractId() +
            ", extract='" + getExtractFphm() + "'" +
            "}";
    }
}
