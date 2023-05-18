package com.mayprayer.admin.handle;

import cn.hutool.core.io.IoUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.symmetric.SM4;
import cn.hutool.json.JSONUtil;
import com.mayprayer.common.utils.annotations.RequestDecode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.Map;

@ControllerAdvice
@Component
public class MyRequestControllerAdvice implements RequestBodyAdvice {

   @Autowired
   private SM4 sm4;


    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return  true ;
    }

    /**
     * 在读取请求体之前先进行解密操作
     */
    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        if (parameter.getMethod().isAnnotationPresent(RequestDecode.class)) {
                return new HttpInputMessage() {
                    @Override
                    public InputStream getBody() throws IOException {
                        String bodyStr = IoUtil.read(inputMessage.getBody(), Charset.defaultCharset());
                        Map<String,String> endecryptDataMap = JSONUtil.toBean(bodyStr, Map.class);
                        return  IoUtil.toStream(sm4.decrypt(endecryptDataMap.get("endecryptData")));
                    }

                    @Override
                    public HttpHeaders getHeaders() {
                        return inputMessage.getHeaders();
                    }
                };
            }
            return  inputMessage;
        }


    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }
}
