package com.mayprayer.system.domain.dto;

import com.mayprayer.system.domain.SysUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUser  extends SysUser implements UserDetails, Serializable {

    private List<SimpleGrantedAuthority> authorities;



    public void setAuthorities(List<SimpleGrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return super.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {

        //true  未失效      //  已失效
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // false 锁定       //true 正常状态
        return true;
    }



        @Override
    public boolean isCredentialsNonExpired() {
        //false 凭证过期    //true 没有过期
        return true;
    }

    @Override
    public boolean isEnabled() {
        //true 启用      //false 禁用
        return true;
    }

}
