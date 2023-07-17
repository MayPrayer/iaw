package com.mayprayer.system.manager.chat;

import cn.hutool.cache.impl.TimedCache;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.mayprayer.common.domain.bo.baidu.chat.BaiduChatMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: shiwx
 * @Description:  后期system 中的全部定义成starter
 * @Date: Created in 19:47 2023/7/16
 * @Modified By:
 */

@Service("baiduChatApi")
@Slf4j
public class BaiduChatApi extends ChatApi{

    @Autowired
    private TimedCache timedCache;

    @Value("${baidu.bot.ak}")
    private String ak;

    @Value("${baidu.bot.sk}")
    private String sk;


    private  static  final  String BAIDU_ACCESS_TOKEN_API = "https://aip.baidubce.com/oauth/2.0/token?grant_type=client_credentials&client_id=%s&client_secret=%s";

    private  static  final  String BAIDU_ACCESS_TOKEN_KEY = "token:baidu_access_token:";

    @Override
   public  String reply(String words) {
        BaiduChatMessage baiduChatMessage  = BaiduChatMessage.builder().content(words).role("user").build();
        List<BaiduChatMessage> messages  = new ArrayList<>();
        messages.add(baiduChatMessage);
        Map paramMap = new HashMap();
        paramMap.put("messages",messages);
        String post = HttpUtil.post(String.format(BAIDU_BOT_API,getAccessToken()), JSONUtil.toJsonStr(paramMap));
        Map result = JSONUtil.toBean(post, Map.class);
        String resp = (String)result.get("result");
        if (StrUtil.isNotBlank(resp)){
            return  resp;
        }
        log.error("文心一言回复失败：{}",resp);
        return "";
    }





    private String getAccessToken(){
        String s = (String) timedCache.get(BAIDU_ACCESS_TOKEN_KEY);
        if (StrUtil.isNotBlank(s))  return  s;
        String tokenStr = HttpUtil.get(String.format(BAIDU_ACCESS_TOKEN_API, ak, sk));
        Map result = JSONUtil.toBean(tokenStr, Map.class);
        if (null!=result.get("access_token")){
            timedCache.put(BAIDU_ACCESS_TOKEN_KEY,(String)result.get("access_token") );
            return  (String)result.get("access_token");
        }
        log.error("获取百度token失败：{}",tokenStr);
        return "";
    }



}
