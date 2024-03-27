package com.mayprayer.web.domain.tool;

import com.mayprayer.system.domain.BaseDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: shiwx
 * @Description: 骂人宝典
 * @Date: Created in 20:55 2024/3/25
 * @Modified By:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ToolMR extends BaseDomain {

    private String text;
    private String level;

}
