package com.fangle.invoiceproject.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A DTO for the AccessToken entity.
 */
public class AccessTokenDTO implements Serializable {

    @Size(max = 20)
    private String nsrmc;

    @NotNull
    @Size(max = 20)
    private String nsrsbh;

    @Size(max = 8)
    private String expires;

    @NotNull
    @Size(max = 20)
    private String pkey;


    public String getNsrmc() {
        return nsrmc;
    }

    public void setNsrmc(String nsrmc) {
        this.nsrmc = nsrmc;
    }

    public String getNsrsbh() {
        return nsrsbh;
    }

    public void setNsrsbh(String nsrsbh) {
        this.nsrsbh = nsrsbh;
    }

    public String getExpires() {
        return expires;
    }

    public void setExpires(String expires) {
        this.expires = expires;
    }

    public String getPkey() {
        return pkey;
    }

    public void setPkey(String pkey) {
        this.pkey = pkey;
    }


    @Override
    public String toString() {
        return "AccessTokenDTO{" +
            ", nsrmc='" + getNsrmc() + "'" +
            ", nsrsbh='" + getNsrsbh() + "'" +
            ", expires='" + getExpires() + "'" +
            ", pkey='" + getPkey() + "'" +
            "}";
    }

}
