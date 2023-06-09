package com.mayprayer.common.utils.enums;

public enum WxMsgType {

    Text(1,"文本消息"),
    PIC(2,"图片消息"),
    VOICE(3, "语音消息"),
    VIDEO(4,"视频消息"),
    THUMB_VIDEO(5, "短视频消息"),
    LOCATION(6,"地理位置消息"),
    URL(7, "链接消息");



    private Integer code;
    private String  desc;

    WxMsgType(Integer code,String desc){
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
