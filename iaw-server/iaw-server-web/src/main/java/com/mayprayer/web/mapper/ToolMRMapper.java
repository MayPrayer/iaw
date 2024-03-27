package com.mayprayer.web.mapper;

import com.mayprayer.web.domain.tool.ToolMR;

import java.util.List;

/**
 * @Author: shiwx
 * @Description: TODO
 * @Date: Created in 21:10 2024/3/25
 * @Modified By:
 */
public interface ToolMRMapper {


   int batchInsert(List<ToolMR> list);

   ToolMR getRandomMR(String level);

}
