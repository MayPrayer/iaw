package com.mayprayer.common.domain.dto.wechat.message;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.mayprayer.common.utils.enums.WxMsgType;
import lombok.Data;

@Data

public class WxUrlMessageDto extends WxMessgeDto{
    @JacksonXmlProperty(localName  = "Title")
    private String  Title;
    @JacksonXmlProperty(localName  = "Description")
    private String  Description;
    @JacksonXmlProperty(localName  = "Url")
    private String  Url;

    WxUrlMessageDto(){
        setMsgType(WxMsgType.URL.getCode());
    }


}
