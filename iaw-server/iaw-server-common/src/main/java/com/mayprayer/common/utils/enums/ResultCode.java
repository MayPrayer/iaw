package com.mayprayer.common.utils.enums;


public enum ResultCode {

    SUCESS(2000,"操作成功"),
    LOGIN_OUT(2001,"退出成功"),
    NO_LOGIN(4001, "用户未登录"),
    AUTHORIZED(403, "未授权"),
    EXCEPTION(5000,"操作异常"),
    FAIL(5001, "操作失败");



    private Integer code;
    private String  desc;

    ResultCode(Integer code,String desc){
        this.code =code;
        this.desc =desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
