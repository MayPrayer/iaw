package com.mayprayer.admin.controller;


import cn.hutool.core.bean.BeanUtil;
import com.mayprayer.admin.service.EmailService;
import com.mayprayer.common.domain.dto.EmailDto;
import com.mayprayer.common.utils.annotations.ResponseEncode;
import com.mayprayer.common.utils.response.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/test")
public class TestController {

    @Autowired
    private EmailService emailService;



    @GetMapping("test")
    @ResponseEncode
    public R test() throws Exception{
        EmailDto emai = EmailDto.builder().title("验证码消息").toUser("宝").content("今天想我没!").build();
        Map<String, Object> result = BeanUtil.beanToMap(emai);
        emailService.sendEmail("1652112896@qq.com","1652112896@qq.com","验证码",result);
        return R.success(null);
    }





}
