package com.mayprayer.system.manager.wx;

import cn.hutool.core.bean.BeanUtil;
import com.mayprayer.common.domain.dto.wechat.WxEventMessageDto;
import com.mayprayer.common.domain.dto.wechat.WxLocationMessageDto;
import com.mayprayer.common.domain.dto.wechat.WxMessgeDto;
import com.mayprayer.common.domain.dto.wechat.WxPicMessageDto;
import com.mayprayer.common.domain.dto.wechat.WxTextMessageDto;
import com.mayprayer.common.domain.dto.wechat.WxThumbVideoMessageDto;
import com.mayprayer.common.domain.dto.wechat.WxUrlMessageDto;
import com.mayprayer.common.domain.dto.wechat.WxVideoMessageDto;
import com.mayprayer.common.domain.dto.wechat.WxVoiceMessageDto;
import com.mayprayer.common.utils.enums.WxMsgType;
import com.mayprayer.common.utils.xml.XmlUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @Author: shiwx
 * @Description: TODO
 * @Date: Created in 15:28 2023/7/16
 * @Modified By:
 */

@Slf4j
public abstract class AbstractMessageHandler{

    public WxMessgeDto wxMessgeDto;

   public String run (Map map) {
        init(map);
        WxMessgeDto result = handle();
       return reply(result);
    }

    void init(Map map){
        WxMsgType type = WxMsgType.buildWxMsgType((String) map.get("MsgType"));
        if (type.equals(WxMsgType.TEXT)){
            wxMessgeDto =  BeanUtil.toBean(map, WxTextMessageDto.class);
        }else if (type.equals(WxMsgType.PIC)){
            wxMessgeDto =  BeanUtil.toBean(map, WxPicMessageDto.class);
        }else if (type.equals(WxMsgType.URL)){
            wxMessgeDto =  BeanUtil.toBean(map, WxUrlMessageDto.class);
        }else if (type.equals(WxMsgType.LOCATION)){
            wxMessgeDto =  BeanUtil.toBean(map, WxLocationMessageDto.class);
        }else if (type.equals(WxMsgType.THUMB_VIDEO)){
            wxMessgeDto =  BeanUtil.toBean(map, WxThumbVideoMessageDto.class);
        }else if (type.equals(WxMsgType.VIDEO)){
            wxMessgeDto =  BeanUtil.toBean(map, WxVideoMessageDto.class);
        }else if (type.equals(WxMsgType.VOICE)){
            wxMessgeDto =  BeanUtil.toBean(map, WxVoiceMessageDto.class);
        }else if (type.equals(WxMsgType.EVENT)){
            wxMessgeDto =  BeanUtil.toBean(map, WxEventMessageDto.class);
        }else {
            return ;
        }
    };

   abstract WxMessgeDto handle();

   String reply(WxMessgeDto wxMessgeDto) {
       String result = "";
       try{
           result= XmlUtil.textMessageToXml(wxMessgeDto);
       }catch (Exception e){
        log.error("对象转xml字符串失败：{}",result,e);
       }
      return result;
    }


}