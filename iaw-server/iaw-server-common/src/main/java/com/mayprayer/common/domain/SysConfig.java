package com.mayprayer.common.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
* 配置表
* @TableName sys_config
*/
@Data
public class SysConfig extends  BaseDomain {


    /**
    * key
    */
    @NotBlank(message="[key]不能为空")
    @Size(max= 255,message="编码长度不能超过255")
    @ApiModelProperty("key")
    @Length(max= 255,message="编码长度不能超过255")
    private String configKey;
    /**
    * value
    */
    @NotBlank(message="[value]不能为空")
    @Size(max= 255,message="编码长度不能超过255")
    @ApiModelProperty("value")
    @Length(max= 255,message="编码长度不能超过255")
    private String configValue;
    /**
    * 禁用状态
    */
    @NotNull(message="[禁用状态]不能为空")
    @ApiModelProperty("禁用状态")
    private Integer status;
    /**
    * 排序
    */
    @NotNull(message="[排序]不能为空")
    @ApiModelProperty("排序")
    private Integer sort;

}
