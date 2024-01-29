package com.mayprayer.system.config;

import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.digest.SM3;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SM3PasswordEncoder implements PasswordEncoder {

    private SM3 sm3 = new SM3();

    @Override
    public String encode(CharSequence rawPassword) {
        if (rawPassword == null) {
            throw new IllegalArgumentException("输入密码不能为空");
        } else {
            return sm3.digestHex(rawPassword.toString());
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
