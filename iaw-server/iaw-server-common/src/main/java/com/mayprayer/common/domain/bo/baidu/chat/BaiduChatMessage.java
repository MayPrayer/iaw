package com.mayprayer.common.domain.bo.baidu.chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: shiwx
 * @Description: TODO
 * @Date: Created in 20:15 2023/7/16
 * @Modified By:
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BaiduChatMessage {

    private String  role ;  //可以为 assistant 或者user
    private String content;    //提问

}
