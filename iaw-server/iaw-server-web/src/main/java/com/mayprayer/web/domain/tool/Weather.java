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

    String code;
    String msg ;

    String city;

    List<Map<String,String>> data;


    @Override
    public String toString() {
        StringBuilder resultBuiler = new StringBuilder("");
        for (Map<String,String> dateWeather : data ){
            resultBuiler.append("日期："+dateWeather.get("date")+"\n"+
                                "天气情况:"+dateWeather.get("cap")+"\n"+
                                "最高气温:"+dateWeather.get("highTemp")+"\n"+
                                "最低气温:"+dateWeather.get("lowTemp")+"\n"+"\n\n");
        }
        return "地区："+city+"\n\n"+resultBuiler.toString();

    }
}
