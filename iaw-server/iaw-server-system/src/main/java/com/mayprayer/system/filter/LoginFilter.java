package com.mayprayer.system.filter;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mayprayer.system.domain.dto.LoginDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class LoginFilter  extends UsernamePasswordAuthenticationFilter {

    private final ObjectMapper objectMapper = new ObjectMapper();


    public LoginFilter(AuthenticationManager authenticationManager,
                         MyAuthenticationHandler authenticationHandler) throws Exception {
        super(authenticationManager);
        setAuthenticationFailureHandler(authenticationHandler);
        setAuthenticationSuccessHandler(authenticationHandler);
        //登陆使用的路径
        setFilterProcessesUrl("/login");
    }

    private static boolean isContentTypeJson(HttpServletRequest request) {
        final String contentType = request.getContentType();
        return "UTF-8".equalsIgnoreCase(contentType) || MimeTypeUtils.APPLICATION_JSON_VALUE.equalsIgnoreCase(contentType);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!HttpMethod.POST.name().equalsIgnoreCase(request.getMethod())) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        LoginDto loginParam = null;
        if (isContentTypeJson(request)) {
            try {
                loginParam = objectMapper.readValue(request.getInputStream(), LoginDto.class);
            } catch (Exception e) {
                throw  new BadCredentialsException("参数不合法无法登录");
            }
        } else {
            loginParam =LoginDto.builder()
                    .userName(request.getParameter("username"))
                    .password(request.getParameter("password"))
                    .build();
        }
        Authentication authentication = authenticationLogin(loginParam, request);
        return authentication;
    }


    /**
     *  认证
     */
    Authentication authenticationLogin(LoginDto loginBO,HttpServletRequest request){
        loginBO.setUserName(loginBO.getUserName().trim());
        loginBO.setPassword(loginBO.getPassword().trim());
        UsernamePasswordAuthenticationToken authRequest = UsernamePasswordAuthenticationToken.unauthenticated(loginBO.getUserName(), loginBO.getPassword());
        setDetails(request, authRequest);
        Authentication authenticate=  this.getAuthenticationManager().authenticate(authRequest);
        return  authenticate;
    }






}
