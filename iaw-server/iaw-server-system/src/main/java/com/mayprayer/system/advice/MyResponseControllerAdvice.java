package com.mayprayer.system.advice;


import cn.hutool.crypto.symmetric.SM4;
import cn.hutool.json.JSONUtil;
import com.mayprayer.common.utils.annotations.ResponseEncode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Component
public class MyResponseControllerAdvice implements ResponseBodyAdvice {

    @Autowired
    private SM4 sm4;

    /**
     * 是否开启
     */
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }


    /**
     * 在写入 响应体之前 加密数据
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (returnType.getMethod().isAnnotationPresent(ResponseEncode.class)) {
            String responseData = JSONUtil.toJsonStr(body);
            String endecrypt = sm4.encrypt(responseData).toString();
            Map<String,String> result = new HashMap<>();
            result.put("endecryptData",endecrypt);
            return  result;
        }
        return body;
    }



}
