package com.fangle.invoiceproject.service.dto;

/**
 * <p>Title: 2018/7/20 0020</p>
 * <p>Description: com.fangle.invoiceproject.service.dto</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: 方格尔科技</p>*
 *
 * @author 作者: duych
 * @version 创建时间：下午 7:52
 */
public class AckDto <T> {
    private int status;
    private int error_code;
    private String error_desc;
    T dataObj;
    public AckDto(){
        this.status=200;
        this.error_code = 0;
        this.error_desc = "";
    }
    public AckDto(int status){
        this.status=status;
        this.error_code = 0;
        this.error_desc = "";
    }
    public AckDto(int status,int error_code){
        this.status=status;
        this.error_code = error_code;
        this.error_desc = "";
    }
    public AckDto(int status,int error_code,String error_desc){
        this.status=status;
        this.error_code = error_code;
        this.error_desc = error_desc;
    }

    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public int getError_code() {
        return error_code;
    }
    public void setError_code(int error_code) {
        this.error_code = error_code;
    }
    public String getError_desc() {
        return error_desc;
    }
    public void setError_desc(String error_desc) {
        this.error_desc = error_desc;
    }
    public T getDataObj() {
        return dataObj;
    }
    public void setDataObj(T dataObj) {
        this.dataObj = dataObj;
    }

}
