package com.mayprayer.web.controller;

import cn.hutool.crypto.digest.DigestUtil;
import com.mayprayer.common.utils.xml.XmlUtil;
import com.mayprayer.web.context.MessageStrategyContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;

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

    @Autowired
    private MessageStrategyContext messageStrategyContext;







    /**
     * get方式主要为验签
     */
    @GetMapping("/dispatch")
    public void dispatch(@RequestParam("signature") String signature ,@RequestParam("timestamp") String timestamp,@RequestParam("nonce") String nonce,@RequestParam("echostr") String echostr){
        String token  =wxToken;
        //字典排序
        String[]  noSortStr = {token,timestamp,nonce};
        Arrays.sort(noSortStr);

        StringBuilder  sortStr = new StringBuilder();
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
    @PostMapping( value = "/dispatch",consumes = MediaType.TEXT_XML_VALUE,produces = MediaType.APPLICATION_XML_VALUE)
    public Object  watch() throws  Exception{
        Map<String, String> messageMap = XmlUtil.parseXml(request.getInputStream());
        log.info("微信接受消息为：{}",messageMap);
        String reply = messageStrategyContext.reply(messageMap);
        log.info("微信回复消息为：{}",reply);
        return reply;
    }





}
