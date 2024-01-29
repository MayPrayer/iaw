package com.mayprayer.system.mapper;

import com.mayprayer.system.domain.SysUser;
import org.apache.ibatis.annotations.Param;

public interface SysUserMapper {


    SysUser loadUserByUsername(@Param("userName") String userName);


}
