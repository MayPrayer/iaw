package com.mayprayer.common.domain.dto;
import com.mayprayer.common.utils.enums.WxMsgType;
import lombok.Data;

@Data
public class WxLocationMessageDto extends WxMessgeDto {

    /**
     * 地理位置纬度
     */
    private  String  Location_X;

    /**
     * 地理位置经度
     */
    private String Location_Y;

    /**
     * 地图缩放大小
     */
    private String Scale;

    /**
     * 地理位置信息
     */
    private String Label;


    WxLocationMessageDto(){
        setMsgType(WxMsgType.LOCATION.getCode());
    }
}
