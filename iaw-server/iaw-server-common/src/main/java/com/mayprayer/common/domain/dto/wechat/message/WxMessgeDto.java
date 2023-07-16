package com.mayprayer.common.domain.dto.wechat.message;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

/**
 * @Author: shiwx
 * @Description: TODO
 * @Date: Created in 14:25 2023/7/16
 * @Modified By:
 */
@JacksonXmlRootElement(localName  = "xml")
@Data
public abstract  class WxMessgeDto {

    /**
     *接收方微信号
     */
    @JacksonXmlProperty(localName  = "ToUserName")
    private String ToUserName;

    /**
     * 发送方微信号
     */
    @JacksonXmlProperty(localName  = "FromUserName")
    private String FromUserName;

    /**
     * 创建时间
     */
    @JacksonXmlProperty(localName  = "CreateTime")
    private String CreateTime;

    /**
     * 消息格式
     */
    @JacksonXmlProperty(localName  = "MsgType")
    private String MsgType;

    /**
     * 消息id
     */
    @JacksonXmlProperty(localName  = "MsgId")
    private String MsgId;

    /**
     *消息的数据ID（消息如果来自文章时才有）
     */
    @JacksonXmlProperty(localName  = "MsgDataId")
    private String MsgDataId;


    /**
     * 图文消息 索引
     */
    @JacksonXmlProperty(localName  = "Idx")
    private String Idx;


}
