package com.fangle.invoiceproject.service.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: 2018/7/21 0021</p>
 * <p>Description: com.fangle.invoiceproject.service.dto</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: 方格尔科技</p>*
 *
 * @author 作者: duych
 * @version 创建时间：下午 3:26
 */
public class AccessTokenAllDto implements Serializable {

    private String code;
    private ArrayList<AccessTokenDTO> map;
    private String message;
    private ArrayList<ZsTokenObject> object;

    public String getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
    public List<ZsTokenObject> getObject() {
        return object;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public void setObject(ArrayList<ZsTokenObject> object) {
        this.object = object;
    }

    public ArrayList<AccessTokenDTO> getMap() {
        return map;
    }

    public void setMap(ArrayList<AccessTokenDTO> map) {
        this.map = map;
    }


}
