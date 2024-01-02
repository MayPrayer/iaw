package com.mayprayer.common.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
* 角色表
* @TableName sys_role
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SysRole extends  BaseDomain {


    /**
    * 角色名称
    */
    @NotBlank(message="[角色名称]不能为空")
    @Size(max= 100,message="编码长度不能超过100")
    @ApiModelProperty("角色名称")
    @Length(max= 100,message="编码长度不能超过100")
    private String roleName;
    /**
    * 角色标识
    */
    @NotBlank(message="[角色标识]不能为空")
    @Size(max= 100,message="编码长度不能超过100")
    @ApiModelProperty("角色标识")
    @Length(max= 100,message="编码长度不能超过100")
    private String roleKey;



}
