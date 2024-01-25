package com.mayprayer.system.manager.login;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.mayprayer.common.domain.SysUser;
import com.mayprayer.common.domain.dto.sys.LoginUser;
import com.mayprayer.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("loginUserManager")
public class LoginUserManager implements UserDetailsService {

    @Autowired
    private SysUserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StrUtil.isBlank(username)){
            throw  new IllegalArgumentException("用户名不能为空");
        }
        SysUser user = userMapper.loadUserByUserCode(username);
        if (null==user){
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        //构建 UserDetails对象
        return buildUserDetail(user);
    }



    public UserDetails buildUserDetail(SysUser user){
       LoginUser loginUser = new LoginUser();
        BeanUtil.copyProperties(user,loginUser);
        //暂时没有权限信息
        return null;
    }

}
