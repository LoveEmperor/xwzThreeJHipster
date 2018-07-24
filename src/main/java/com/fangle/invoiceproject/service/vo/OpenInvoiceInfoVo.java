package com.fangle.invoiceproject.service.vo;

import lombok.Data;

/**
 * <p>Title: 2018/7/24 0024</p>
 * <p>Description: com.fangle.invoiceproject.service.vo</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: 方格尔科技</p>*
 *
 * @author 作者: duych
 * @version 创建时间：下午 7:59
 */
@Data
public class OpenInvoiceInfoVo {

    //销方纳税人识别号
    private String xfnsrsbh;
    //购方纳税人识别号
    private String gfnsrsbh;
    //购方纳税人名称
    private String gfmc;
    //购方银行名称帐号
    private String gfdzdh;
    //价税金额合计
    private Double jehj;
    //商品列表
    private String spobj;
    //购方电话
    private String phone;
    //购方邮箱地址
    private String email;
    //购方微信用户号
    private String wechat;
    //开票人
    private String kpr;
    //收款人
    private String skr;
    //复核人
    private String fhr;
    //备注
    private String memo;
    //请求密钥
    private String pkey;
    //发票类型
    private String type;
    //是否开含税票
    private Integer taxType;
    //开票人id
    private String kprId;
    //需要推送的地址
    private String sendUrl;
}
