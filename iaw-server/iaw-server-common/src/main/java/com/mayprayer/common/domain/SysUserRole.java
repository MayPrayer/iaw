package com.mayprayer.common.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
* 用户角色关联表
* @TableName sys_user_role
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SysUserRole extends  BaseDomain {


    /**
    * 用户id
    */
    @NotNull(message="[用户id]不能为空")
    @ApiModelProperty("用户id")
    private Long userId;
    /**
    * 角色id
    */
    @NotNull(message="[角色id]不能为空")
    @ApiModelProperty("角色id")
    private Long roleId;


}
