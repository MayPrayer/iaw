package com.mayprayer.system.config;

import cn.hutool.crypto.digest.DigestUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


/**
 * 自定义加密方式
 */

@Component
public class Md5PasswordEncoder implements PasswordEncoder {


    @Override
    public String encode(CharSequence rawPassword) {
        if (rawPassword == null) {
            throw new IllegalArgumentException("输入密码不能为空");
        } else {
            return DigestUtil.md5Hex((rawPassword.toString()));
        }
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        if (rawPassword == null) {
            throw new IllegalArgumentException("输入密码不能为空");
        } else{
            return encodedPassword.equals(encode(rawPassword.toString()));
        }
    }
}
