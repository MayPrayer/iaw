package com.mayprayer.system.filter;

import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONUtil;
import com.mayprayer.common.utils.constant.Constant;
import com.mayprayer.common.utils.constant.ResponseConstant;
import com.mayprayer.common.utils.enums.ResultCode;
import com.mayprayer.common.utils.response.R;
import com.mayprayer.system.domain.dto.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Component("myAuthenticationHandler")
public class MyAuthenticationHandler implements AccessDeniedHandler, AuthenticationEntryPoint, AuthenticationFailureHandler, AuthenticationSuccessHandler, LogoutSuccessHandler {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * token 过期时间
     */
    @Value("${token.expireTime}")
    private Long expireTime ;


    /**
     *
     * @param request
     * @param response
     * @param accessDeniedException
     * @throws IOException
     * @throws ServletException
     * 未授权返回消息
     *
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        R result = R.builder().code(ResultCode.AUTHORIZED.getCode())
                .msg(ResultCode.AUTHORIZED.getDesc())
                .data(null)
                .build();
       writeJson(JSONUtil.toJsonStr(result),response, HttpStatus.UNAUTHORIZED);
    }


    /**
     * 未登录
     * @param request
     * @param response
     * @param authException
     * @throws IOException
     * @throws ServletException
     * 未登录返回信息
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        R result =  R.builder().code(ResultCode.NO_LOGIN.getCode())
                .msg(ResultCode.NO_LOGIN.getDesc())
                .data(null)
                .build();
        writeJson(JSONUtil.toJsonStr(result),response, HttpStatus.UNAUTHORIZED);
    }



    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        R result =  R.builder().msg(exception.getMessage()).build();
        writeJson(JSONUtil.toJsonStr(result),response, HttpStatus.OK);
    }



    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String token = IdUtil.fastUUID();
        LoginUser user = (LoginUser)authentication.getPrincipal();
        redisTemplate.opsForValue().set(Constant.LOGIN_USER_FOLDER+user.getUsername()+"_"+token,user,expireTime,TimeUnit.MINUTES);
        //2.返回登录成功的json数据
        R result = R.success(ResponseConstant.RESPONSE_SUCCESS_MSG,token);
       writeJson(JSONUtil.toJsonStr(result),response, HttpStatus.OK);
    }


    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Cookie[] cookies = request.getCookies();
        String token =null;
        if (null!=cookies&&cookies.length>0){
            for (Cookie cookie : cookies){
                if (cookie.getName().equals(Constant.USER_TOKEN)){
                    token = cookie.getValue();
                }
            }
        }
        if (null!=redisTemplate.keys("*_"+token)){
            redisTemplate.delete(redisTemplate.keys("*_"+token));
        }
        R result = R.success(ResponseConstant.RESPONSE_SUCCESS_MSG);
       writeJson(JSONUtil.toJsonStr(result),response, HttpStatus.OK);
    }




    void writeJson(String result, HttpServletResponse response, HttpStatus status) throws IOException {
        response.setCharacterEncoding("utf-8");    //设置 HttpServletResponse使用utf-8编码
        response.setHeader("Content-Type", "application/json");  //设置响应头的编码
        response.setStatus(status.value());
        response.getWriter().write(result);
    }
}
