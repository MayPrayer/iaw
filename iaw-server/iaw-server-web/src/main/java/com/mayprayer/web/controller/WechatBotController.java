package com.mayprayer.web.controller;


import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.mayprayer.common.utils.annotations.Anonymous;
import com.mayprayer.common.utils.constant.Constant;
import com.mayprayer.web.domain.wechat.WechatBotUserDto;
import com.mayprayer.web.domain.wechat.WxBotMessageDto;
import com.mayprayer.web.domain.wechat.WxBotMessageSendDto;
import com.mayprayer.web.service.chat.BaiduChatApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/wechat/bot")
@Slf4j
public class WechatBotController {

    @Autowired
    private BaiduChatApi baiduChatApi;

    @Value("${wx.sendApi}")
    private  String wxSendApi;

    List<String> keywords;


    @PostMapping("/reply")
    @Anonymous
    public void reply(@RequestParam("type") String type , @RequestParam("content")String content ,
                        @RequestParam("source")String  source ,@RequestParam("isMentioned")String isMentioned ,
                        @RequestParam("isMsgFromSelf")String isMsgFromSelf){
        log.info("type: "+type+"\n");
        log.info("content: "+content+"\n");
        log.info("source: "+source+"\n");
        log.info("isMentioned: "+isMentioned+"\n");
        log.info("isMsgFromSelf: "+isMsgFromSelf+"\n");

        //首先看有没有被@
        String reply = null;

        content = content.replace("@三昧","");

        WechatBotUserDto wechatBotUserDto = JSONUtil.toBean(source, WechatBotUserDto.class,true);

        WxBotMessageSendDto messageSendDto = new WxBotMessageSendDto();


        if (null!=wechatBotUserDto.getRoom()){
            if (isMentioned.equals(Constant.INT_YES+"")){
                reply= baiduChatApi.reply(content);
            }
            //群消息
            messageSendDto.setIsRoom(true);
            messageSendDto.setTo(wechatBotUserDto.getRoom().getPayload().getTopic());

        }else {
            reply= baiduChatApi.reply(content);
            //发送个人消息
            messageSendDto.setTo(wechatBotUserDto.getFrom().getPayload().getName());
        }
        WxBotMessageDto text = WxBotMessageDto.builder().content(reply).type("text").build();
        List<WxBotMessageDto>  msgList = new ArrayList<>();
        msgList.add(text);
        messageSendDto.setData(msgList);
        String post = HttpUtil.post(wxSendApi, JSONUtil.toJsonStr(messageSendDto));
        log.info("发送消息结果为："+post);

        }





}
