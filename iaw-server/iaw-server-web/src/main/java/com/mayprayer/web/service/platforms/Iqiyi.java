package com.mayprayer.web.service.platforms;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.mayprayer.common.utils.response.R;
import com.mayprayer.web.domain.video.RespIqiyiDto;
import com.mayprayer.web.domain.video.RespIqiyiVideoInfo;
import com.mayprayer.web.domain.video.RespSearchIqiyiDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class Iqiyi {

   private    String iqyListUrl = "https://pcw-api.iqiyi.com/strategy/pcw/data/soResultData?pageNum=1&key=%s";




     public   List<RespSearchIqiyiDto>  search(String name){
         String s = HttpUtil.get(String.format(iqyListUrl,name));
         R r = JSONUtil.toBean(s, R.class,true);
         Map data =(Map)r.getData();
         RespIqiyiDto respIqiyiDto =  BeanUtil.toBean(data.get("formatData"), RespIqiyiDto.class, CopyOptions.create().ignoreError());
         List<RespSearchIqiyiDto> list = respIqiyiDto.getList();
         list = list.stream().filter(e->e.getVideoDocType()==1&& StrUtil.isNotBlank(e.getId())).collect(Collectors.toList());
        return  list;
     }


    public static void main(String[] args) {
        Iqiyi iqiyi = new Iqiyi();
        List<RespSearchIqiyiDto> 封神榜 = iqiyi.search("封神榜");
        System.out.println(封神榜);
    }




}
