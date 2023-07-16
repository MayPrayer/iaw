package com.mayprayer.web.manager;

import cn.hutool.cache.impl.TimedCache;
import cn.hutool.core.util.StrUtil;
import com.mayprayer.common.domain.dto.wechat.message.WxMessgeDto;
import com.mayprayer.common.domain.dto.wechat.message.WxTextMessageDto;
import com.mayprayer.web.api.chat.BaiduChatApi;
import com.mayprayer.web.api.chat.OwnThinkChatApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: shiwx
 * @Description: TODO
 * @Date: Created in 15:28 2023/7/16
 * @Modified By:
 */
@Service("textMessageHandler")
@Slf4j
public class TextMessageHandler extends AbstractMessageHandler{

    @Autowired
    private OwnThinkChatApi ownThinkChatApi;

    @Autowired
    private TimedCache timedCache;

    @Autowired
    private BaiduChatApi baiduChatApi;


    @Override
    WxMessgeDto handle() {
        WxTextMessageDto wxTextMessageDto = (WxTextMessageDto) wxMessgeDto;
        String result = null;
        if (StrUtil.isBlank((String)timedCache.get(wxTextMessageDto.getMsgId()))){
            result= baiduChatApi.reply(wxTextMessageDto.getContent());
            timedCache.put(wxTextMessageDto.getMsgId(),result);
            return  buildSubscribeMsg(result);
        }
        String newResult = new String((String) timedCache.get(wxTextMessageDto.getMsgId()));
        timedCache.remove(wxTextMessageDto.getMsgId());
        result = newResult ;
        return  buildSubscribeMsg(result);
    }


    WxTextMessageDto buildSubscribeMsg(String reply){
        WxTextMessageDto result = new WxTextMessageDto();
        result.setFromUserName(wxMessgeDto.getToUserName());
        result.setToUserName(wxMessgeDto.getFromUserName());
        result.setContent(reply);
        return result;
    }



}
