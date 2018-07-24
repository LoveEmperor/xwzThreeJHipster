package com.fangle.invoiceproject.service.dto;

import java.util.ArrayList;

/**
 * <p>Title: 2018/7/21 0021</p>
 * <p>Description: com.fangle.invoiceproject.service.dto</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: 方格尔科技</p>*
 *
 * @author 作者: duych
 * @version 创建时间：下午 4:14
 */
public class AccessTokenArray {

    private ArrayList<AccessTokenAllDto> accessTokenAllDto;

    public ArrayList<AccessTokenAllDto> getAccessTokenAllDto() {
        return accessTokenAllDto;
    }

    public void setAccessTokenAllDto(ArrayList<AccessTokenAllDto> accessTokenAllDto) {
        this.accessTokenAllDto = accessTokenAllDto;
    }

}
