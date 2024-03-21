package com.mayprayer.web.domain.wechat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WechatBotUserDto {


   public WechatToDto room ;

    public  WechatToDto to;

    public  WechatToDto from ;

}
