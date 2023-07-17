package com.mayprayer.common.domain.dto.wechat;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.mayprayer.common.utils.enums.WxMsgType;
import lombok.Data;

@Data
public class WxThumbVideoMessageDto extends WxMessgeDto{
    @JacksonXmlProperty(localName  = "MediaId")
    private String MediaId;
    @JacksonXmlProperty(localName  = "ThumbMediaId")
    private String ThumbMediaId;

    public WxThumbVideoMessageDto(){
        setMsgType(WxMsgType.THUMB_VIDEO.getCode());
    }
}
