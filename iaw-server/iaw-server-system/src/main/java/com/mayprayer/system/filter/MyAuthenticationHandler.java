package com.mayprayer.system.filter;

import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONUtil;
import com.maynrent.utils.AjaxResult;
import com.maynrent.utils.constant.Constants;
import com.maynrent.utils.constant.TokenConstant;
import com.maynrent.utils.enums.ResultCode;
import com.mayprayer.common.domain.dto.sys.LoginUser;
import org.apache.tomcat.util.http.ResponseUtil;
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
        AjaxResult result = AjaxResult.builder().code(ResultCode.AUTHORIZED.getCode())
                .msg(ResultCode.AUTHORIZED.getDesc())
                .data(null)
                .build();
        ResponseUtil.writeJson(JSONUtil.toJsonStr(result),response, HttpStatus.UNAUTHORIZED);
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
        AjaxResult result = AjaxResult.builder().code(ResultCode.UNLOGIN.getCode())
                .msg(ResultCode.UNLOGIN.getDesc())
                .data(null)
                .build();
        ResponseUtil.writeJson(JSONUtil.toJsonStr(result),response, HttpStatus.UNAUTHORIZED);
    }



    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        AjaxResult result = AjaxResult.error(exception.getMessage());
        ResponseUtil.writeJson(JSONUtil.toJsonStr(result),response, HttpStatus.OK);
    }



    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String token = IdUtil.fastUUID();
        LoginUser user = (LoginUser)authentication.getPrincipal();
        redisTemplate.opsForValue().set(TokenConstant.LOGIN_USER_FOLDER+user.getUserCode()+"_"+token,user,expireTime,TimeUnit.MINUTES);
        //2.返回登录成功的json数据
        AjaxResult result = AjaxResult.success(Constants.DEFAULT_SUCCESS_MSG,token);
        ResponseUtil.writeJson(JSONUtil.toJsonStr(result),response, HttpStatus.OK);
//        ResponseUtil.writeToken(JSONUtil.toJsonStr(result),response,token);
    }


    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Cookie[] cookies = request.getCookies();
        String token =null;
        if (null!=cookies&&cookies.length>0){
            for (Cookie cookie : cookies){
                if (cookie.getName().equals(TokenConstant.USER_TOKEN)){
                    token = cookie.getValue();
                }
            }
        }
        if (null!=redisTemplate.keys("*_"+token)){
            redisTemplate.delete(redisTemplate.keys("*_"+token));
        }
        AjaxResult result = AjaxResult.success(Constants.DEFAULT_SUCCESS_MSG);
        ResponseUtil.writeJson(JSONUtil.toJsonStr(result),response, HttpStatus.OK);
    }


}
