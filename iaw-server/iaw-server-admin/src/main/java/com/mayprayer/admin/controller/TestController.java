package com.mayprayer.admin.controller;


import com.mayprayer.common.utils.annotations.ResponseEncode;
import com.mayprayer.common.utils.response.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/test")
public class TestController {





    @GetMapping("test")
    @ResponseEncode
    public R test(){
        log.info("我执行了");
        return  R.success("你猜我猜不猜");
    }





}
