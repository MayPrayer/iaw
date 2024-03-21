package com.mayprayer.web.domain.wechat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PayLoadDto {


    public String id ;

    /**
     * 群消息 这里为群名
     */
    public String topic ;

    /**
     * 单个消息  这里为当前用户名
     */
    public String name ;



}
