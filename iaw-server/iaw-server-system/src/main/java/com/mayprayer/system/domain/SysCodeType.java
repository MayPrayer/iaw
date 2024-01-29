package com.mayprayer.system.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
* 代码表
* @TableName sys_code_type
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SysCodeType extends  BaseDomain {

    /**
    * 代码表名称
    */
    @NotBlank(message="[代码表名称]不能为空")
    @Size(max= 255,message="编码长度不能超过255")
    @ApiModelProperty("代码表名称")
    @Length(max= 255,message="编码长度不能超过255")
    private String codeTypeName;
    /**
    * 代码表键名
    */
    @NotBlank(message="[代码表键名]不能为空")
    @Size(max= 255,message="编码长度不能超过255")
    @ApiModelProperty("代码表键名")
    @Length(max= 255,message="编码长度不能超过255")
    private String codeTypeKey;
    /**
    * 排序
    */
    @ApiModelProperty("排序")
    private Integer sort;
    /**
    * 禁用状态
    */
    @NotNull(message="[禁用状态]不能为空")
    @ApiModelProperty("禁用状态")
    private Integer status;



}
