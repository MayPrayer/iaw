package com.mayprayer.web.context;

import com.mayprayer.common.utils.enums.WxMsgType;
import com.mayprayer.web.service.wx.EventMessageHandler;
import com.mayprayer.web.service.wx.TextMessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Author: shiwx
 * @Description: TODO
 * @Date: Created in 15:56 2023/7/16
 * @Modified By:
 */
@Service("messageStrategyContext")
public class MessageStrategyContext {
    @Autowired
    private TextMessageHandler textMessageHandler;

    @Autowired
    private EventMessageHandler eventMessageHandler;



    public String reply(Map map) {
        WxMsgType type = WxMsgType.buildWxMsgType((String) map.get("MsgType"));
        if (type.equals(WxMsgType.TEXT)){
          return textMessageHandler.run(map);
        }else if (type.equals(WxMsgType.PIC)){
            return textMessageHandler.run(map);
        }else if (type.equals(WxMsgType.URL)){
            return textMessageHandler.run(map);
        }else if (type.equals(WxMsgType.LOCATION)){
            return textMessageHandler.run(map);
        }else if (type.equals(WxMsgType.THUMB_VIDEO)){
            return textMessageHandler.run(map);
        }else if (type.equals(WxMsgType.VIDEO)){
            return textMessageHandler.run(map);
        }else if (type.equals(WxMsgType.VOICE)){
            return textMessageHandler.run(map);
        }else if (type.equals(WxMsgType.EVENT)){
            return eventMessageHandler.run(map);
        }else {
            return "";
        }
    }


}
