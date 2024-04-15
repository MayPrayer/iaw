package com.mayprayer.web.domain.video;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EpisodeInfo {

    private String id ;

    private String url;

    private String name;

    private String sort;


}
