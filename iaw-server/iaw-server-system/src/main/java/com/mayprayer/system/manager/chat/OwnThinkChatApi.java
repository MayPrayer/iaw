package com.mayprayer.system.manager.chat;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.mayprayer.common.domain.dto.chat.OwnThinkRespDto;
import org.springframework.stereotype.Service;

/**
 * 思知
 */
@Service("ownThinkChatApi")
public class OwnThinkChatApi extends ChatApi{

    public String reply(String words){
        String result = HttpUtil.get(String.format(OWN_THINK_API, words));
        OwnThinkRespDto ownThinkRespDto = JSONUtil.toBean(result, OwnThinkRespDto.class);
        return  ownThinkRespDto.getReply();
    }

}
