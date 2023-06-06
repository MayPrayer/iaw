package com.mayprayer.common.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
* 代码值表
* @TableName sys_code_value
*/
@Data
public class SysCodeValue extends  BaseDomain {


    /**
    * 代码值
    */
    @NotBlank(message="[代码值]不能为空")
    @Size(max= 255,message="编码长度不能超过255")
    @ApiModelProperty("代码值")
    @Length(max= 255,message="编码长度不能超过255")
    private String codeTypeValue;
    /**
    * 代码id
    */
    @NotNull(message="[代码id]不能为空")
    @ApiModelProperty("代码id")
    private Long codeTypeId;
    /**
    * 状态
    */
    @NotNull(message="[状态]不能为空")
    @ApiModelProperty("状态")
    private Integer status;

}
