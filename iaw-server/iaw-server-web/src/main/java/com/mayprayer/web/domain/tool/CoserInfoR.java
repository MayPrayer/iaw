package com.mayprayer.web.domain.tool;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: shiwx
 * @Description: TODO
 * @Date: Created in 13:46 2024/3/24
 * @Modified By:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CoserInfoR {
    private String code;

    private String Text;


    private CoserInfo data;

}
