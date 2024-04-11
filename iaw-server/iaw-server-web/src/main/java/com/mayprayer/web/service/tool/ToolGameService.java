package com.mayprayer.web.service.tool;


import com.mayprayer.web.domain.tool.ToolGame;
import com.mayprayer.web.mapper.ToolGameMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("toolGameService")
public class ToolGameService {

            @Autowired
            private ToolGameMapper toolGameMapper;



           List<ToolGame> getGameList(ToolGame toolGame){
               return  toolGameMapper.getGameList(toolGame);
            }


}
