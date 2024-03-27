package com.mayprayer.web.domain.tool;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: shiwx
 * @Description: TODO
 * @Date: Created in 13:47 2024/3/24
 * @Modified By:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CoserInfo {

    private String Title;

    private List<String> data;

}
