package com.mayprayer.web.domain.wechat;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WxBotMessageDto {

    /**
     * 消息类型 默认为文本消息  text
     * 图片消息  fileUrl
     */
    public String type="text";


    /**
     * 内容 可以是链接
     */
    public String content;

    public Boolean isToMaster = false;

}
