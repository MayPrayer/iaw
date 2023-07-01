package com.mayprayer.common.domain.dto;

import com.mayprayer.common.utils.enums.WxMsgType;
import lombok.Data;

@Data
public class WxTextMessageDto extends WxMessgeDto{

    private String Content;

   public WxTextMessageDto(){
       setMsgType(WxMsgType.Text.getCode());
    }

}
