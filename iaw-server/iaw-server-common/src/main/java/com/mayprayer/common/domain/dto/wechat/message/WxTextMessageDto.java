package com.mayprayer.common.domain.dto.wechat.message;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.mayprayer.common.utils.enums.WxMsgType;
import lombok.Data;

@Data
public class WxTextMessageDto extends WxMessgeDto {

    @JacksonXmlProperty(localName  = "Content")
    private String Content;


   public WxTextMessageDto(){
      setMsgType(WxMsgType.TEXT.getCode());
    }

}
