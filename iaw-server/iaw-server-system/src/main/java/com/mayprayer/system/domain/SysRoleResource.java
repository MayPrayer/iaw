package com.mayprayer.system.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* 角色资源关联表
* @TableName sys_role_resource
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SysRoleResource extends  BaseDomain {

    private Long placeholder ;


}
