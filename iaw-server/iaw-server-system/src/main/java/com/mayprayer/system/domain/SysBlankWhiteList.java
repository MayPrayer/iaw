package com.mayprayer.system.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Size;

/**
* 
* @TableName sys_blank_white_list
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SysBlankWhiteList extends  BaseDomain {

    /**
    * ip地址
    */
    @Size(max= 100,message="编码长度不能超过100")
    @ApiModelProperty("ip地址")
    @Length(max= 100,message="编码长度不能超过100")
    private String ip;
    /**
    * 是否是黑名单
    */
    @Size(max= 100,message="编码长度不能超过100")
    @ApiModelProperty("是否是黑名单")
    @Length(max= 100,message="编码长度不能超过100")
    private String isBlankList;
    /**
    * 是否是白名单
    */
    @Size(max= 100,message="编码长度不能超过100")
    @ApiModelProperty("是否是白名单")
    @Length(max= 100,message="编码长度不能超过100")
    private String isWhiteList;


}
