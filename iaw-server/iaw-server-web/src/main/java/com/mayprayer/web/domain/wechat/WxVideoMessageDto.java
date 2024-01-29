package com.mayprayer.web.domain.wechat;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.mayprayer.common.utils.enums.WxMsgType;
import lombok.Data;

@Data

public class WxVideoMessageDto extends WxMessgeDto {
    @JacksonXmlProperty(localName  = "MediaId")
    private String MediaId;
    @JacksonXmlProperty(localName  = "ThumbMediaId")
    private String ThumbMediaId;

    WxVideoMessageDto(){
        setMsgType(WxMsgType.VIDEO.getCode());
    }

}
