package com.mayprayer.system.filter;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.PhoneUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mayprayer.common.domain.dto.sys.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Set;

@Component
@Slf4j
public class LoginFilter  extends UsernamePasswordAuthenticationFilter {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserService userService;



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
        LoginBO loginParam = null;
        if (isContentTypeJson(request)) {
            try {
                loginParam = objectMapper.readValue(request.getInputStream(), LoginBO.class);
            } catch (Exception e) {
                throw  new BadCredentialsException("参数不合法无法登录");
            }
        } else {
            Integer loginType = null==request.getParameter("loginType")?0:Integer.parseInt(request.getParameter("loginType"));
            loginParam =LoginBO.builder()
                    .username(request.getParameter("username"))
                    .password(request.getParameter("password"))
                    .loginType(loginType)
                    .validateImgCode(request.getParameter("validateImgCode"))
                    .validateImgCodeId(request.getParameter("validateImgCodeId"))
                    .validateMsgCode(request.getParameter("validateMsgCode"))
                    .validateMsgCodeId(request.getParameter("validateMsgCodeId"))
                    .build();
        }
        checkCode(loginParam);
        Authentication authentication = authenticationLogin(loginParam, request);
        //清除图片验证码
        redisTemplate.delete(loginParam.getValidateImgCodeId());
        if (authentication.isAuthenticated()){
            //清除图片验证码
            redisTemplate.delete(loginParam.getValidateMsgCodeId());
        }

        return authentication;
    }

    /**
     * 验证短信或者图片验证码
     * @return
     */
     void checkCode(LoginBO loginBO){
         if (!PhoneUtil.isMobile(loginBO.getUsername())){
             throw new BadCredentialsException("手机号格式错误");
         }
         if (null==loginBO.getLoginType()||(loginBO.getLoginType()!=1&&loginBO.getLoginType()!=2)){
             throw new BadCredentialsException("登录方式有误");
         }
         //常规校验
         if (StringUtil.isBlank(loginBO.getUsername())){
             throw new BadCredentialsException("用户名不能为空");
         }
            //校验图片验证码
         if (StringUtil.isBlank(loginBO.getValidateImgCodeId())||StringUtil.isBlank(loginBO.getValidateImgCode())){
             throw new BadCredentialsException("图片验证码不能为空");
         }
         String imagCode = (String)redisTemplate.opsForValue().get(ValidateConstant.CAPTCHA_PREFIX + loginBO.getValidateImgCodeId());
         if (StringUtil.isBlank(imagCode)){
             throw new BadCredentialsException("图片验证码已失效");
         }

         if (!imagCode.equalsIgnoreCase(loginBO.getValidateImgCode().trim())){
             throw new BadCredentialsException("图片验证码错误");
         }

         //登录方式类型校验
         if (loginBO.getLoginType()==1){
            //校验用户名密码
             if (StringUtil.isBlank(loginBO.getPassword())){
                 throw new BadCredentialsException("密码不能为空");
             }
         }else if (loginBO.getLoginType()==2){
            //校验短信验证码
             String  phoneStr =  loginBO.getValidateMsgCodeId().split("_")[1];
             if (!loginBO.getUsername().equals(phoneStr)){
                 throw new BadCredentialsException("手机号码已更换,请重新获取验证码");
             }
             if (StringUtil.isBlank(loginBO.getValidateMsgCodeId())||StringUtil.isBlank(loginBO.getValidateMsgCode())){
                 throw new BadCredentialsException("短信验证码不能为空");
             }
             String msgCode = (String)redisTemplate.opsForValue().get(ValidateConstant.CAPTCHA_PREFIX + loginBO.getValidateMsgCodeId());
             if (StringUtil.isBlank(msgCode)){
                 throw new BadCredentialsException("短信验证码已失效");
             }
             if (!msgCode.equalsIgnoreCase(loginBO.getValidateMsgCode())){
                 throw new BadCredentialsException("短信验证码错误");
             }
         }
     }


    /**
     *  认证
     */
    Authentication authenticationLogin(LoginBO loginBO,HttpServletRequest request){
        loginBO.setUsername(loginBO.getUsername().trim());
        loginBO.setPassword(loginBO.getPassword().trim());
        if (loginBO.getLoginType()==1){
            // 账号密码登录
            UsernamePasswordAuthenticationToken authRequest = UsernamePasswordAuthenticationToken.unauthenticated(loginBO.getUsername(), loginBO.getPassword());
            setDetails(request, authRequest);
            Authentication authenticate=  this.getAuthenticationManager().authenticate(authRequest);
            return  authenticate;
        }else if (loginBO.getLoginType()==2){
            User curUser = userService.selectUserByUserCode(loginBO.getUsername());
            if (null==curUser){
//                throw new BadCredentialsException("用户不存在");
                //不存在客户默认注册一个账号
                User user = new User();
                user.setUserCode(loginBO.getUsername());
                user.setPassword(StringUtil.generateRandomStrNoSpecial(6));
                String token = userService.register(user);
                Set<String> keys = redisTemplate.keys("*_" + token);
                String key = keys.stream().findFirst().get();
                Map<String,Object> userMap= (Map)  redisTemplate.opsForValue().get(key);
                LoginUser loginUser = LoginUser.buildLoginUserDTO(userMap);
                UsernamePasswordAuthenticationToken userToken  = new UsernamePasswordAuthenticationToken(loginUser,null,loginUser.getAuthorities());
                userToken.setDetails(new WebAuthenticationDetails(request));
                SecurityContextHolder.getContext().setAuthentication(userToken);
                return  userToken;
            }else{
                LoginUser user  = new LoginUser();
                BeanUtil.copyProperties(curUser,user);
                UsernamePasswordAuthenticationToken userToken  = new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
                userToken.setDetails(new WebAuthenticationDetails(request));
                SecurityContextHolder.getContext().setAuthentication(userToken);
                return  userToken;
            }
        }else{
            throw  new BadCredentialsException("登录方式暂不支持");
        }
    }






}
