package com.mayprayer.web.domain.wechat;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 微信机器人信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WxBotMessageSendDto {

    /**
     * 发给谁 昵称
     */
    public String to ;

    /**
     * 是否是群名 默认是否
     */
    public Boolean isRoom  = false;

    /**
     * 发送消息 可以是单挑休息 也可以是多条 
     */
    public List<WxBotMessageDto> data;



}
