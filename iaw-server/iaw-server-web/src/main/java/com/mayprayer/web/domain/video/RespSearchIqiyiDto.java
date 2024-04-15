package com.mayprayer.web.domain.video;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RespSearchIqiyiDto {

     private  String id ;

     private  Integer  videoDocType ;// 1代表 可以观看  2则是花絮什么的

     private String g_title; //名称

     private Double score; //评分

     private String g_main_link;//链接  第一集

     private List<RespIqiyiVideoInfo>  videoinfos;


     public String toString(){
          Integer count = 1;
          if (CollectionUtil.isNotEmpty(videoinfos)){
               count= videoinfos.size();
          }
          StringBuilder  result = new StringBuilder();
          result.append("id:"+id+"\n");
          result.append("名称:"+g_title+"\n");
          if (null!=score){
               result.append("评分:"+score+"\n");
          }
          result.append("集数:"+count+"\n\n");
          return  result.toString();
     }

}
