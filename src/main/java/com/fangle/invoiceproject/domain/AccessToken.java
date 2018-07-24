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
 * 系统签到获取Token
 */
@ApiModel(description = "系统签到获取Token")
@Entity
@Table(name = "access_token")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AccessToken implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 纳税人名称
     */
    @Size(max = 20)
    @ApiModelProperty(value = "纳税人名称")
    @Column(name = "nsrmc", length = 20)
    private String nsrmc;

    /**
     * 纳税人识别号
     */
    @NotNull
    @Size(max = 30)
    @ApiModelProperty(value = "纳税人识别号", required = true)
    @Column(name = "nsrsbh", length = 30, nullable = false)
    private String nsrsbh;

    /**
     * 超时时间秒
     */
    @Size(max = 8)
    @ApiModelProperty(value = "超时时间秒")
    @Column(name = "expires", length = 8)
    private String expires;

    /**
     * 请求密钥
     */
    @NotNull
    @Size(max = 40)
    @ApiModelProperty(value = "请求密钥", required = true)
    @Column(name = "pkey", length = 40, nullable = false)
    private String pkey;

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

    public String getNsrmc() {
        return nsrmc;
    }

    public AccessToken nsrmc(String nsrmc) {
        this.nsrmc = nsrmc;
        return this;
    }

    public void setNsrmc(String nsrmc) {
        this.nsrmc = nsrmc;
    }

    public String getNsrsbh() {
        return nsrsbh;
    }

    public AccessToken nsrsbh(String nsrsbh) {
        this.nsrsbh = nsrsbh;
        return this;
    }

    public void setNsrsbh(String nsrsbh) {
        this.nsrsbh = nsrsbh;
    }

    public String getExpires() {
        return expires;
    }

    public AccessToken expires(String expires) {
        this.expires = expires;
        return this;
    }

    public void setExpires(String expires) {
        this.expires = expires;
    }

    public String getPkey() {
        return pkey;
    }

    public AccessToken pkey(String pkey) {
        this.pkey = pkey;
        return this;
    }

    public void setPkey(String pkey) {
        this.pkey = pkey;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public AccessToken createTime(Instant createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public Instant getUpdateTime() {
        return updateTime;
    }

    public AccessToken updateTime(Instant updateTime) {
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
        AccessToken accessToken = (AccessToken) o;
        if (accessToken.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), accessToken.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AccessToken{" +
            "id=" + getId() +
            ", nsrmc='" + getNsrmc() + "'" +
            ", nsrsbh='" + getNsrsbh() + "'" +
            ", expires='" + getExpires() + "'" +
            ", pkey='" + getPkey() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            "}";
    }
}
