package com.mayprayer.web.controller;


import cn.hutool.crypto.digest.DigestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

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



    @PostMapping("/dispatch")
    public void  watch(){

    }






}
