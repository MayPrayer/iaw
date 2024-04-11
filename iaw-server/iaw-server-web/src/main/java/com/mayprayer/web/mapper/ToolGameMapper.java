package com.mayprayer.web.mapper;

import com.mayprayer.web.domain.tool.ToolGame;

import java.util.List;

public interface ToolGameMapper {

   public  int insert(ToolGame toolGame);

   List<ToolGame> getGameList(ToolGame toolGame);

}
