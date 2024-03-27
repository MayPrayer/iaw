package com.mayprayer.web.domain.tool;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: shiwx
 * @Description: TODO
 * @Date: Created in 10:56 2024/3/24
 * @Modified By:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PrivateInfoR {

    private Integer code;

    private PrivateInfo data;

    private String message;

}
