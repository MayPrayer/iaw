package com.mayprayer.web.domain.video;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RespIqiyiVideoInfo {

    private String name ; //名称

    private  String url ; //链接

    private Integer order; //排序
}
