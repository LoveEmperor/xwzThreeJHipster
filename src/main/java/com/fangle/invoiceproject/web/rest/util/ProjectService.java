package com.fangle.invoiceproject.web.rest.util;

import com.fangle.invoiceproject.service.dto.*;
import com.fangle.invoiceproject.service.vo.ApplicationVo;
import com.fangle.invoiceproject.service.vo.OpenInvoiceInfoVo;
import com.google.gson.*;
import okhttp3.Response;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.Instant;
import java.util.Date;



/**
 * <p>Title: 2018/7/20 0020</p>
 * <p>Description: com.fangle.invoiceproject.web.rest.util</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: 方格尔科技</p>*
 *
 * @author 作者: duych
 * @version 创建时间：下午 7:31
 */
public class ProjectService {
    private static long getTime; // 上次获取到TOKEAN 的时间
    private static String access_token;

    private final Logger log = LoggerFactory.getLogger(ProjectService.class);


    public static <Cmd, ACK> AckDto<ACK> doHttpFun(String url,Cmd cmd, Class<?> ackType) throws IOException {
        AckDto<ACK> ack = new AckDto<ACK>();
//        String fullUrl = SysconfigYml.get().getZsinvoiceProject().getUrl() + url;
//        String fullUrl = "http://www.taxinfo123.com:8082/tcis/advSer/getToken?appId=0d0c67b8295a11e7b7434cd30abca22q&appSecret=57111-78743-49030";
        String fullUrl = "http://www.taxinfo123.com:8082/tcis"+url;
            Response response = HttpClientUtil.PostJson(fullUrl,cmd);
        if (response != null) {
            try {

                String body = response.body().string();
                    if(response.code()==200){
                       if("com.fangle.invoiceproject.service.dto.AccessTokenDTO".equals(ackType.getName())){
                        JSONObject jsonObject = new JSONObject(body);
                        String strs = jsonObject.get("map").toString();
                        ack.setDataObj((ACK) new Gson().fromJson(strs,ackType));
                        ack.setStatus(response.code());
                    }else{
                           ack.setDataObj((ACK) new Gson().fromJson(body,ackType));
                           ack.setStatus(response.code());
                       }
                }else {
                            ack = (AckDto<ACK>) new Gson().fromJson(body, AckDto.class);
                            ack.setStatus(response.code());
                }


                return ack;
            } catch (Exception e) {
                e.printStackTrace();
                ack.setStatus(500);
                ack.setError_code(100106);
                ack.setError_desc("unknow error");
                return ack;
            }

        } else {
            ack.setStatus(500);
            ack.setError_code(100106);
            ack.setError_desc("unknow error");
            return ack;
        }
    }

    /**
     * 获取TOKEN
     *
     * @return
     * @throws Exception
     */
    private static AccessTokenDTO getAccessToken() throws Exception {
        AccessTokenDTO dto = new AccessTokenDTO();
        if (null != access_token) {
            long currentTime = System.currentTimeMillis();
            // access_token有效期为7200秒
            if ((currentTime - getTime) < 7000000) {
                dto.setPkey(access_token);
                return dto;
            }
        }
        return getAccessTokenAways();
    }

    /**
     * 总是重新获取token
     *
     * @return
     * @throws Exception
     */
    private static AccessTokenDTO getAccessTokenAways() throws Exception {
        ApplicationVo vo = new ApplicationVo();
        vo.setAppId("0d0c67b8295a11e7b7434cd30abca22q");
        vo.setAppSecret("57111-78743-49030");
        String url = "/advSer/getToken?appId=0d0c67b8295a11e7b7434cd30abca22q&appSecret=57111-78743-49030";
        AckDto<AccessTokenDTO> result =  ProjectService.doHttpFun(url,vo,AccessTokenDTO.class );
        getTime = System.currentTimeMillis();

        AccessTokenDTO at = new AccessTokenDTO();
        try {
            access_token = result.getDataObj().getPkey();
            at.setPkey(access_token);
            at.setNsrsbh(result.getDataObj().getNsrsbh());
            at.setExpires(result.getDataObj().getExpires());
            at.setNsrmc(result.getDataObj().getNsrmc());

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (null == access_token) {
            throw new Exception();
        }
        return at;
    }


    public static AccessTokenDTO doGetTokenFunc() throws Exception {

        AccessTokenDTO dto = new AccessTokenDTO();
        dto = getAccessToken();
        if (dto != null) {
            return dto;
        }
        return null;
    }

    public static InvoiceInfoDTO doSubmitInvoiceInfoFunc(OpenInvoiceInfoVo vo) throws Exception {

        String url = "/advSer/st/openfp?";
        AckDto<InvoiceInfoDTO> result =  ProjectService.doHttpFun(url,vo,InvoiceInfoDTO.class );

        InvoiceInfoDTO at = new InvoiceInfoDTO();
        try {
            at = result.getDataObj();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return at;
    }

}
