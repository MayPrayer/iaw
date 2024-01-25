package com.mayprayer.system.mapper;

import com.mayprayer.common.domain.SysUser;

public interface SysUserMapper {


    SysUser loadUserByUserCode(String username);


}
