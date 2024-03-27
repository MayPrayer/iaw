package com.mayprayer.web.service.tool;

import cn.hutool.core.util.StrUtil;
import com.mayprayer.web.domain.tool.ToolMR;
import com.mayprayer.web.mapper.ToolMRMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

/**
 * @Author: shiwx
 * @Description: TODO
 * @Date: Created in 21:09 2024/3/25
 * @Modified By:
 */
@Service
public class MRService {

        @Autowired
        private ToolMRMapper toolMRMapper;


    public  String getRandomMR(String level) {
        if ("强".equals(level)) {
            level = "max";
        } else if ("弱".equals(level)) {
            level = "min";
        } else if (StrUtil.isBlank(level)){
            level = null;
        }else{
            return "指令错误";
        }
        return toolMRMapper.getRandomMR(level).getText();
    }




}
