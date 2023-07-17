package com.mayprayer.common.domain.dto.wechat;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.mayprayer.common.utils.enums.WxMsgType;
import lombok.Data;

@Data
public class WxPicMessageDto  extends WxMessgeDto {


    /**
     * 图片链接（由系统生成）
     */

    @JacksonXmlProperty(localName  = "PicUrl")
    private String PicUrl;

    /**
     * 图片消息媒体id，可以调用获取临时素材接口拉取数据
     */
    @JacksonXmlProperty(localName  = "MediaId")
    private String MediaId;

    WxPicMessageDto(){
        setMsgType(WxMsgType.PIC.getCode());
    }



}
