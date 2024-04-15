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
public class RespIqiyiDto {

        private List<RespSearchIqiyiDto> list;

}
