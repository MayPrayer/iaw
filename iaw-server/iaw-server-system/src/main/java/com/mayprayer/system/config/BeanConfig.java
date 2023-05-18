package com.mayprayer.system.config;

import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.symmetric.SM4;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class BeanConfig {

    @Value("${endecrypt}")
    private String endecrypt;

    @Bean
    SM4 getSm4(){
    return SmUtil.sm4(endecrypt.getBytes());
    }


}
