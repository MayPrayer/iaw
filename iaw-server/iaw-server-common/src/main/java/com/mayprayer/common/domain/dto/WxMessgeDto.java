package com.mayprayer.common.domain.dto;

import lombok.Data;

@Data
public class WxMessgeDto {

    /**
     *接收方微信号
     */

    private String ToUserName;

    /**
     * 发送方微信号
     */
    private String FromUserName;

    /**
     * 创建时间
     */
    private String CreateTime;

    /**
     * 消息格式
     */
    private Integer MsgType;

    /**
     * 消息id
     */
    private String MsgId;

    /**
     *消息的数据ID（消息如果来自文章时才有）
     */
    private String MsgDataId;


    /**
     * 图文消息 索引
     */
    private String Idx;
}
