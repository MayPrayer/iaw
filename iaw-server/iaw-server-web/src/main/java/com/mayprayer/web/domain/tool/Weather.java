package com.mayprayer.web.domain.tool;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: shiwx
 * @Description: TODO
 * @Date: Created in 13:35 2024/3/23
 * @Modified By:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Weather {


    String city;

    List<Map<String,String>> data;


    @Override
    public String toString() {
        StringBuilder resultBuiler = new StringBuilder("");
        for (Map<String,String> dateWeather : data ){
            resultBuiler.append("日期：   "+dateWeather.get("date")+"\n"+
                                "天气情况："+dateWeather.get("weather")+"\n"+
                                "气温：   "+dateWeather.get("temperature")+"\n"+
                                "风力：   "+dateWeather.get("wind")+"\n"+
                                "空气质量："+dateWeather.get("wind")+"\n\n");

        }
        return "地区："+city+"\n\n"+resultBuiler.toString();

    }
}
