package com.fangle.invoiceproject.web.rest.util;

import com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;
import java.util.HashMap;
/**
 * <p>Title: 2018/7/20 0020</p>
 * <p>Description: com.fangle.invoiceproject.web.rest.util</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: 方格尔科技</p>*
 *
 * @author 作者: duych
 * @version 创建时间：下午 6:46
 */
public class HttpClientUtil {

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    /**
     *
     * @param url
     * @param token
     * @param obj
     * @return
     */
    public static Response PostJson(String url, String token, Object obj)  {
        OkHttpClient client = new OkHttpClient();

        RequestBody body = null;
        Request request;

        if (obj != null) {
            Gson g = new Gson();
            body = RequestBody.create(JSON, g.toJson(obj));
        }
        else{
            body = RequestBody.create(JSON, "");
        }


        if (token == null || token.equals("")) {
            request = new Request.Builder().url(url).post(body)
                .addHeader("content-type", "application/json")
                .addHeader("cache-control", "no-cache").build();
        } else {
            request = new Request.Builder().url(url).post(body)
                .addHeader("content-type", "application/json")
                .addHeader("authorization", "Bearer " + token)
                .addHeader("cache-control", "no-cache").build();
        }

        Response response=null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            System.out.println("url:"+url);
            e.printStackTrace();
        }
        return response;
    }

    public static Response PostJson(String url, Object obj) throws IOException {
        return PostJson(url, null, obj);
    }

    public static Response PostJson(String url) throws IOException {
        return PostJson(url);
    }

    /**
     * 发送get请求
     * @param url
     * @return
     * @throws IOException
     */
    public static Response Get(String url) throws IOException {
        return Get( url, null);
    }
    /**
     * get 方法
     *
     * @param url
     * @param args
     * @return
     * @throws IOException
     */
    public static Response Get(String url, HashMap<String, Object> args) throws IOException {
        OkHttpClient client = new OkHttpClient();
        StringBuilder sb = new StringBuilder();
        sb.append(url);
        if (args != null && args.size() > 0) {
            sb.append("?");
            boolean first = true;
            for (String getKey : args.keySet()) {
                if (first){
                    sb.append(String.format("%s=%s", getKey, args.get(getKey)));

                }
                else {
                    sb.append(String.format("&%s=%s", getKey, args.get(getKey)));
                }
                first=false;
            }
        }
        Request request = new Request.Builder().url(url).addHeader("cache-control", "no-cache").build();
        Response response = client.newCall(request).execute();

        return response;
    }


}
