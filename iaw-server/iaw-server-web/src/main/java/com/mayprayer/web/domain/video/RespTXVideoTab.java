package com.mayprayer.web.domain.video;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RespTXVideoTab {

    private String title;

    private String asnycParams;

    private  Boolean isSelected;

}
