package com.fangle.invoiceproject.web.rest.util;

/**
 * <p>Title: 2018/7/20 0020</p>
 * <p>Description: com.fangle.invoiceproject.web.rest.util</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: 方格尔科技</p>*
 *
 * @author 作者: duych
 * @version 创建时间：下午 7:42
 */
public class ZsinvoiceProject {
    /**
     * 中税信息
     */
    private Boolean enable;
    private String url;
    private String appId;
    private String appSecret;

    public Boolean getEnable() {
        return enable;
    }

    public String getUrl() {
        return url;
    }

    public String getAppId() {
        return appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

}
