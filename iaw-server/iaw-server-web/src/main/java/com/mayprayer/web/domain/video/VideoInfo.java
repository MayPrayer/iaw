package com.mayprayer.web.domain.video;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VideoInfo {

    private String id;

    private String name;

    private List<EpisodeInfo> episodes;


    public String toString(){
        StringBuilder result = new StringBuilder();
        result.append("id: "+id+"\n");
       result.append("名称: "+name+"\n");
        result.append("集数: "+episodes.size()+"\n\n");
        return  result.toString();
    }

}
