package com.mayprayer.web.domain.video;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RespTXVideoInfo {

    private String id ;

    private String videoUrl;

    private String url;

    private String title;

    private String rawTags;
}
