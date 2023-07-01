package com.mayprayer.web.controller;


import cn.hutool.core.util.XmlUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.mayprayer.common.domain.dto.WxMessgeDto;
import com.mayprayer.common.domain.dto.WxTextMessageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Date;

@RestController
@RequestMapping("/wechat/officialaccount")
@Slf4j
public class WxOfficialAccountController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Value("${wx.token}")
    private  String wxToken;



    /**
     * get方式主要为验签
     */
    @GetMapping("/dispatch")
    public void dispatch(){
        System.out.println("调用验签接口了");
        String  signature = request.getParameter("signature");
        String  timestamp = request.getParameter("timestamp");
        String  nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        String token  =wxToken;

        //字典排序
        String[]  noSortStr = {token,timestamp,nonce};
        Arrays.sort(noSortStr);

        StringBuilder  sortStr = new StringBuilder("");
        for (String s : noSortStr){
            sortStr.append(s);
        }

        //比较签名是否一致，一致则返回之前的随机字符串
         if (DigestUtil.sha1Hex(sortStr.toString()).equals(signature)){
             try{
             response.getWriter().write(echostr);
             }catch (Exception e){
                 log.error("响应失败");
             }
         }
    }


    /**
     * 消息监听与回复
     */
    @PostMapping( "/dispatch")
    public void  watch(){
        System.out.println("调用事件接口了");
        //解析xml
        Document readXml = null;
        try{
            readXml= XmlUtil.readXML(request.getInputStream());
        }catch (Exception e){
            e.printStackTrace();
        }

        //        1.xml转bean
        WxMessgeDto wxMessgeDto = XmlUtil.xmlToBean(readXml, WxMessgeDto.class);
        System.out.println("11111111::::"+wxMessgeDto);
        WxTextMessageDto  textMessage=new WxTextMessageDto();
        textMessage.setFromUserName(wxMessgeDto.getToUserName());
        textMessage.setToUserName(wxMessgeDto.getFromUserName());
        textMessage.setContent("你好呀");
        textMessage.setCreateTime(new Date().getTime()+"");

        //          2.处理问题

        //          3.回复消息

        try {
            Document  wirteXml = XmlUtil.beanToXml(textMessage);
            response.getWriter().write(XmlUtil.toStr(wirteXml));
        }catch (Exception e){
            e.printStackTrace();
        }

    }






}
