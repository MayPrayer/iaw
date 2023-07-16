package com.mayprayer.common.utils.enums;

import com.mayprayer.common.utils.constant.WxMsgTypeConstant;

public enum WxMsgType {

    TEXT(WxMsgTypeConstant.TEXT,"文本消息"),
    PIC(WxMsgTypeConstant.PIC,"图片消息"),
    VOICE(WxMsgTypeConstant.VOICE, "语音消息"),
    VIDEO(WxMsgTypeConstant.VIDEO,"视频消息"),
    THUMB_VIDEO(WxMsgTypeConstant.THUMB_VIDEO, "短视频消息"),
    LOCATION(WxMsgTypeConstant.LOCATION,"地理位置消息"),
    URL(WxMsgTypeConstant.URL, "链接消息"),
    EVENT(WxMsgTypeConstant.EVENT, "事件");





    private String code;
    private String  desc;

    WxMsgType(String code,String desc){
        this.code =code;
        this.desc =desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


   public static WxMsgType buildWxMsgType(String type) {
       switch (type) {
           case WxMsgTypeConstant.TEXT:
               return WxMsgType.TEXT;
           case WxMsgTypeConstant.PIC:
               return WxMsgType.PIC;
           case WxMsgTypeConstant.LOCATION:
               return WxMsgType.LOCATION;
           case WxMsgTypeConstant.THUMB_VIDEO:
               return WxMsgType.THUMB_VIDEO;
           case WxMsgTypeConstant.URL:
               return WxMsgType.URL;
           case WxMsgTypeConstant.VIDEO:
               return WxMsgType.VIDEO;
           case WxMsgTypeConstant.VOICE:
               return WxMsgType.VOICE;
           case WxMsgTypeConstant.EVENT:
               return WxMsgType.EVENT;
           default:
                    return  null;
       }
   }


}
