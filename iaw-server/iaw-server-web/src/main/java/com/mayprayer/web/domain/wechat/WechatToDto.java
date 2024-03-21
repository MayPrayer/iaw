package com.mayprayer.web.domain.wechat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WechatToDto {

    public String id;


    public PayLoadDto payload;


}
