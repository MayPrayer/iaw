package com.mayprayer.web.domain;

import com.mayprayer.system.domain.BaseDomain;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Size;

/**
* 
* @TableName tool_free_api
*/
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ToolFreeApi extends BaseDomain {



    /**
    * api名称
    */
    @Size(max= 255,message="编码长度不能超过255")
    @ApiModelProperty("api名称")
    @Length(max= 255,message="编码长度不能超过255")
    private String name;
    /**
    * api调用地址
    */
    @Size(max= 255,message="编码长度不能超过255")
    @ApiModelProperty("api调用地址")
    @Length(max= 255,message="编码长度不能超过255")
    private String url;


    private String keyword;

}
