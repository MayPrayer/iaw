package com.mayprayer.web.manager;

import com.mayprayer.common.domain.dto.wechat.message.WxMessgeDto;
import com.mayprayer.common.domain.dto.wechat.message.WxTextMessageDto;
import com.mayprayer.web.api.chat.OwnThinkChatApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: shiwx
 * @Description: TODO
 * @Date: Created in 15:28 2023/7/16
 * @Modified By:
 */
@Service("textMessageHandler")
public class TextMessageHandler extends AbstractMessageHandler{

    @Autowired
    private OwnThinkChatApi ownThinkChatApi;
    @Override
    WxMessgeDto handle() {
        WxTextMessageDto wxTextMessageDto = (WxTextMessageDto) wxMessgeDto;
        String result = ownThinkChatApi.reply(wxTextMessageDto.getContent());
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
