package com.fangle.invoiceproject.web.rest.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>Title: 2018/7/24 0024</p>
 * <p>Description: com.fangle.invoiceproject.web.rest.util</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: 方格尔科技</p>*
 *
 * @author 作者: duych
 * @version 创建时间：下午 2:27
 */
public class XWZDateUtil {

    public String getNowDateFunc(){
        Date nowDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String xwzDate = dateFormat.format( nowDate );

        return xwzDate;
    }
}
