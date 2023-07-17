package com.mayprayer.system.manager.wx;

import cn.hutool.core.bean.BeanUtil;
import com.mayprayer.common.domain.dto.wechat.WxEventMessageDto;
import com.mayprayer.common.domain.dto.wechat.WxMessgeDto;
import com.mayprayer.common.domain.dto.wechat.WxTextMessageDto;
import com.mayprayer.common.utils.constant.WxMsgTypeConstant;
import org.springframework.stereotype.Service;

/**
 * @Author: shiwx
 * @Description: 微信事件处理器 目前主要是订阅事件回复
 * @Date: Created in 15:45 2023/7/16
 * @Modified By:
 */
@Service("eventMessageHandler")
public class EventMessageHandler extends AbstractMessageHandler{
    @Override
    WxMessgeDto handle() {
        WxEventMessageDto wxEventMessageDto =   (WxEventMessageDto)wxMessgeDto;
        if (wxEventMessageDto.getEvent().equals(WxMsgTypeConstant.EVENT_SUBSCRIBE)){
           return buildSubscribeMsg();
        }
        return  null;
    }


    WxTextMessageDto buildSubscribeMsg(){
        WxTextMessageDto result = new WxTextMessageDto();
        BeanUtil.copyProperties(wxMessgeDto,result);
        result.setFromUserName(wxMessgeDto.getToUserName());
        result.setToUserName(wxMessgeDto.getFromUserName());
        result.setMsgType(WxMsgTypeConstant.TEXT);
        result.setContent("欢迎关注MayPrayer的工具集,更多功能敬请期待！");
        return result;
    }

}
