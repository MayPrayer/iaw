package com.mayprayer.common.domain.dto.wechat;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.mayprayer.common.utils.enums.WxMsgType;
import lombok.Data;

@Data
public class WxLocationMessageDto  extends WxMessgeDto {



    /**
     * 地理位置纬度
     */
    @JacksonXmlProperty(localName  = "Location_X")
    private  String  Location_X;

    /**
     * 地理位置经度
     */
    @JacksonXmlProperty(localName  = "Location_Y")
    private String Location_Y;

    /**
     * 地图缩放大小
     */
    @JacksonXmlProperty(localName  = "Scale")
    private String Scale;

    /**
     * 地理位置信息
     */
    @JacksonXmlProperty(localName  = "Label")
    private String Label;


    WxLocationMessageDto(){
        setMsgType(WxMsgType.LOCATION.getCode());
    }
}
