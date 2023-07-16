package com.mayprayer.common.domain.dto.wechat.message;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.mayprayer.common.utils.enums.WxMsgType;
import lombok.Data;

@Data

public class WxVoiceMessageDto extends WxMessgeDto{


    @JacksonXmlProperty(localName  = "MediaId")
    private String MediaId;
    @JacksonXmlProperty(localName  = "Format")
    private String Format;

    WxVoiceMessageDto(){
        setMsgType(WxMsgType.VOICE.getCode());
    }


}
