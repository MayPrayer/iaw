package com.mayprayer.common.domain.dto.wechat;

import lombok.Data;

/**
 * @Author: shiwx
 * @Description: TODO
 * @Date: Created in 14:20 2023/7/16
 * @Modified By:
 */

@Data
public class WxEventMessageDto extends WxMessgeDto{
    private String Event;
    private String EventKey;
}
