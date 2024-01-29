package com.mayprayer.web.service.wx;

import cn.hutool.core.bean.BeanUtil;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.mayprayer.common.utils.enums.WxMsgType;
import com.mayprayer.web.domain.wechat.*;
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
           XmlMapper xmlMapper = new XmlMapper();
           return  xmlMapper.writeValueAsString(wxMessgeDto);
       }catch (Exception e){
        log.error("对象转xml字符串失败：{}",result,e);
       }
      return result;
    }


}
