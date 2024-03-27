package com.mayprayer.web.domain.tool;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: shiwx
 * @Description: TODO
 * @Date: Created in 10:47 2024/3/24
 * @Modified By:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Directive {

    /**
     * 指令
     */
    private String directive;

    /**
     * 参数
     */
    private List<String> params;


    /**
     * 原始对话
     */
    private String  content;

}
