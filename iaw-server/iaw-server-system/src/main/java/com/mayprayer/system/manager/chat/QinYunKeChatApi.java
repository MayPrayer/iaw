package com.mayprayer.system.manager.chat;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;

import java.util.Map;

/**
 * 青客云
 */
public class QinYunKeChatApi  extends ChatApi{

    public String reply(String words){
        String result = HttpUtil.get(String.format(QIN_YUN_KE_API, words));
        Map map= JSONUtil.toBean(result, Map.class);
        return  (String) map.get("content");
    }

}
