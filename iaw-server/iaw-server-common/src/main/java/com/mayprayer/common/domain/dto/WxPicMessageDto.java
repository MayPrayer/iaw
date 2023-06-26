package com.mayprayer.common.domain.dto;

import com.mayprayer.common.utils.enums.WxMsgType;

public class WxPicMessageDto  extends WxMessgeDto{

    private String PicUrl;

    private String MediaId;

    WxPicMessageDto(){
        setMsgType(WxMsgType.PIC.getCode());
    }



}
