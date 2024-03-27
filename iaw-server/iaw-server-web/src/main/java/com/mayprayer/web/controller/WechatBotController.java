package com.mayprayer.web.controller;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.mayprayer.common.utils.annotations.Anonymous;
import com.mayprayer.common.utils.constant.Constant;
import com.mayprayer.web.domain.tool.Directive;
import com.mayprayer.web.domain.wechat.WechatBotUserDto;
import com.mayprayer.web.domain.wechat.WxBotMessageDto;
import com.mayprayer.web.domain.wechat.WxBotMessageSendDto;
import com.mayprayer.web.service.chat.BaiduChatApi;
import com.mayprayer.web.service.tool.FreeApiService;
import com.mayprayer.web.service.tool.MRService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/wechat/bot")
@Slf4j
public class WechatBotController {

    @Autowired
    private BaiduChatApi baiduChatApi;

    @Value("${wx.sendApi}")
    private  String wxSendApi;

    @Autowired
   private FreeApiService freeApiService;


    private List<String> blackList = new ArrayList<>();


    List<String> keywords = new ArrayList<>();

    @Autowired
    private MRService mrService;


    @PostConstruct
    public void init (){
        blackList.add("微信团队");
        keywords.add("菜单");
        keywords.add("摸鱼日历");
        keywords.add("美女视频");
        keywords.add("v50");
        keywords.add("骂人宝典");

        keywords.add("奖状生成器");
        keywords.add("热映电影");
        keywords.add("星座运势");
        keywords.add("奖状生成器");
        keywords.add("绘图");
        keywords.add("一笔签名");
        keywords.add("新闻简报");
        keywords.add("天气预报");
        keywords.add("段子");
        keywords.add("舔狗日记");
        keywords.add("随机coser");
        keywords.add("更多功能");
    }


    /**
     * @param type          消息类型
     * @param content       内容
     * @param source        参与对象json字符串
     * @param isMentioned   是否被@
     * @param isMsgFromSelf 是否是来自自己的消息
     */
    @PostMapping("/reply")
    @Anonymous
    public void reply(@RequestParam("type") String type, @RequestParam("content") String content,
                      @RequestParam("source") String source, @RequestParam("isMentioned") String isMentioned,
                      @RequestParam("isMsgFromSelf") String isMsgFromSelf) {
        log.info("type: " + type + "\n");
        log.info("content: " + content + "\n");
        log.info("source: " + source + "\n");
        log.info("isMentioned: " + isMentioned + "\n");
        log.info("isMsgFromSelf: " + isMsgFromSelf + "\n");

        //首先看有没有被@
        content = content.replace("@三昧", "").trim();

        WechatBotUserDto wechatBotUserDto = JSONUtil.toBean(source, WechatBotUserDto.class, true);

        WxBotMessageSendDto messageSendDto = new WxBotMessageSendDto();
        List<WxBotMessageDto> messageDto = new ArrayList<>();
        WxBotMessageDto chatMessage = new WxBotMessageDto();
        String userNickName = null;
        if (null != wechatBotUserDto.getRoom()) {
            Directive directive = containDirective(content.trim());
            if (isMentioned.equals(Constant.INT_YES + "")) {
                chatMessage.setContent(baiduChatApi.reply(content,wechatBotUserDto.getRoom().getId()+"_"+wechatBotUserDto.getFrom().getId()));
                messageDto.add(chatMessage);
            } else if (null!=directive) {
                messageDto=   getKeyWordCall(directive, messageDto);
            }
            //群消息
            userNickName = wechatBotUserDto.getRoom().getPayload().getTopic();
            messageSendDto.setIsRoom(true);
            messageSendDto.setTo(userNickName);

        } else {
            Directive directive = containDirective(content.trim());
            if (null!=directive) {
                messageDto= getKeyWordCall(directive, messageDto);
            } else {
                chatMessage.setContent(baiduChatApi.reply(content,wechatBotUserDto.getFrom().getId()));
                messageDto.add(chatMessage);
            }
            //发送个人消息
            userNickName = wechatBotUserDto.getFrom().getPayload().getName();
            messageSendDto.setTo(userNickName);
        }
        log.info("消息内容为:"+JSONUtil.toJsonStr(messageDto));
        if (blackList.contains(userNickName)) {
            return;
        }
        if (CollectionUtil.isEmpty(messageDto)){
            return;
        }
        sendMsg(messageDto,messageSendDto);
    }

    /**
     *
     * @param messageDtos
     * @param messageSendDto
     */
    public void  sendMsg(List<WxBotMessageDto> messageDtos,WxBotMessageSendDto messageSendDto){
        List<WxBotMessageDto> msgList = new ArrayList<>();
        for (WxBotMessageDto  messageDto:messageDtos){
            if (messageDto.getIsToMaster()==true){
                //如果是反馈功能 发一份消息给主人
                WxBotMessageDto toMasterMsg = new WxBotMessageDto();
                toMasterMsg.setContent(messageDto.getContent());
                messageDto.setContent("反馈成功");
                List<WxBotMessageDto> toMasterMsgList = new ArrayList<>();
                toMasterMsgList.add(toMasterMsg);
                WxBotMessageSendDto toMasterMsgDto = new WxBotMessageSendDto();
                toMasterMsgDto.setData(toMasterMsgList);
                toMasterMsgDto.setTo("凉秋");
                HttpUtil.post(wxSendApi, JSONUtil.toJsonStr(toMasterMsgDto));
            }
            msgList.add(messageDto);
        }
        messageSendDto.setData(msgList);

        String jsonStr = JSONUtil.toJsonStr(messageSendDto);
        log.info("发送消息内容为:"+jsonStr);
        String post = HttpUtil.post(wxSendApi, jsonStr);
        log.info("发送消息结果为：" + post);

    }










    /**
     * 看是否包含指令 ，并且将参数全部弄到集合中
     * @param content
     * @return
     */
    Directive containDirective(String content){
        String[] split = content.split(" ");
        List<String> list = Arrays.asList(split);
        list = list.stream().filter(e->!e.trim().equals("")).collect(Collectors.toList());
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()){
            String directive = iterator.next();
            if (keywords.contains(directive)){
                iterator.remove();
               return Directive.builder().directive(directive).content(content).params(list).build();
            }
        }
        return  null;
    }



   List <WxBotMessageDto>   getKeyWordCall(Directive directiveObj, List <WxBotMessageDto> wxBotMessageDtos){
        String directive = directiveObj.getDirective();
        WxBotMessageDto  wxBotMessageDto = new WxBotMessageDto();
        List<String> params = directiveObj.getParams();
        if ("美女视频".equals(directive)){
            String result = freeApiService.getMMVideo();
            wxBotMessageDto.setType("fileUrl");
            wxBotMessageDto.setContent(result);
        }else  if ("摸鱼日历".equals(directive)){
            String result = freeApiService.getMYDate();
            wxBotMessageDto.setType("fileUrl");
            wxBotMessageDto.setContent(result);
        }else  if ("v50".equals(directive)){
            String result = freeApiService.getKFC();
            wxBotMessageDto.setContent(result);
        }else if ("新闻简报".equals(directive)){
            String result = freeApiService.getSimpleNews();
            wxBotMessageDto.setType("fileUrl");
            wxBotMessageDto.setContent(result);
        }else  if ("private".equals(directive)){
          if (null==params||params.size()!=1){
              wxBotMessageDto.setContent("指令参数有误");
              wxBotMessageDtos.add(wxBotMessageDto);
              return  wxBotMessageDtos;
          }
            freeApiService.getPrivateInfo(params.get(0));
        }else if ("天气预报".equals(directive)){
            if (null==params||params.size()==0){
                wxBotMessageDto.setContent("指令参数有误");
                wxBotMessageDtos.add(wxBotMessageDto);
                return  wxBotMessageDtos;
            }
            wxBotMessageDto.setContent(freeApiService.getWheather(params.get(params.size()-1)));;
        }else if ("舔狗日记".equals(directive)){
            String result = freeApiService.getTG();
            wxBotMessageDto.setContent(result);
        }else if ("视频搜索".equals(directive)){

        }
        else if ("骂人宝典".equals(directive)){
            if (CollectionUtil.isEmpty(params)){
                wxBotMessageDto.setContent(mrService.getRandomMR("弱"));;
            }else{
                wxBotMessageDto.setContent(mrService.getRandomMR(params.get(0)));
            }
        }else if ("更多功能".equals(directive)){
            wxBotMessageDto.setIsToMaster(true);
            wxBotMessageDto.setContent(directiveObj.getContent());
        }else if ("随机coser".equals(directive)){
            if (null==params||params.size()>1){
                wxBotMessageDto.setContent("指令参数有误");
                wxBotMessageDtos.add(wxBotMessageDto);
                return  wxBotMessageDtos;
            }
            Integer num =1;
            try{
                if (CollectionUtil.isNotEmpty(params)){
                    num = Integer.parseInt((String) params.get(0));
                    if(num>10){
                        wxBotMessageDto.setContent("图片最多不能超过10张");
                        wxBotMessageDtos.add(wxBotMessageDto);
                    }
                }

            }catch (Exception e){
                wxBotMessageDto.setContent("指令参数有误");
                wxBotMessageDtos.add(wxBotMessageDto);
                return  wxBotMessageDtos;
            }
            Integer count=0;
            List<String> imgResult = freeApiService.getCOSER();
            for (String img :imgResult){
                count++;
                if (count>num){
                    break;
                }
                WxBotMessageDto newWxBotMessageDto = new WxBotMessageDto();
                newWxBotMessageDto.setType("fileUrl");
                newWxBotMessageDto.setContent(img);
                wxBotMessageDtos.add(newWxBotMessageDto);
            }
            return  wxBotMessageDtos;
        }




        else if ("菜单".equals(directive)){
            String result ="欢迎使用三昧机器人！\n"+
                           "以下为常用指令: \n\n"+
                           "🐠摸鱼日历🐠:生成当日摸鱼日历    \n\n"+
                           "📰新闻简报📰:生成当日重要新闻    \n\n"+
                           "☀️天气预报☀️:生成指定地区10天天气 \n"+
                           "eg:天气预报  武汉              \n\n"+
                           "💄️美女视频💄:生成随机美女视频    \n\n"+
                           "💄视频搜索💄:视频搜索           \n"+
//                           "eg:视频搜索 斗罗大陆  即可获取最新集数 \n"+
//                           "视频搜索 斗罗大陆  集数  即可获取视频链接 \n\n"+
                           "🍗v50🍗: 生成一条疯狂星期四文案  \n\n"+
                           "🐶舔狗日记🐶:生成一条舔狗日记    \n\n"+
                           "🐎骂人宝典🐎:生成一条脏话信息    \n"+
                           "eg:骂人宝典  或者 骂人宝典 强    \n\n"+
                           "🍭随机coser🍭:随机生成指定数量coser图片 \n"+
                           "eg:随机coser  3 \n\n"+
                           "🤕更多功能🤕:后台留言更多功能     \n"+
                           "eg:更多功能  希望加入定时提醒功能 \n\n"+
                           "🛒菜单🛒:提供指令帮助    \n\n"+
                           "目前已接入聊天功能支持一对一聊天,添加好友即可，也可拉入群聊" +
                    "      欢迎您的使用！";
            wxBotMessageDto.setContent(result);
        } else{
            wxBotMessageDto.setContent("暂不支持该指令");
        }

        //过滤 空消息内容
       wxBotMessageDtos = wxBotMessageDtos.stream().filter(e->StrUtil.isNotBlank(e.getContent())).collect(Collectors.toList());

        wxBotMessageDtos.add(  wxBotMessageDto);
       return  wxBotMessageDtos;
    }














}
