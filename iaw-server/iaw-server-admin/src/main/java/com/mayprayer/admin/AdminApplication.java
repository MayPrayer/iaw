package com.mayprayer.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//不加的话默认扫描com.mayprayer.admin下所有注解
@ComponentScan(basePackages = "com.mayprayer.*")

public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class,args);
    }
}
