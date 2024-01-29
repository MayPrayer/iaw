package com.mayprayer.system.config;

import com.mayprayer.common.utils.annotations.Anonymous;
import com.mayprayer.common.utils.spring.SpringUtils;
import com.mayprayer.system.filter.LoginFilter;
import com.mayprayer.system.filter.MyAuthenticationHandler;
import com.mayprayer.system.filter.MyAuthenticationProvider;
import com.mayprayer.system.filter.TokenFilter;
import com.mayprayer.system.manager.LoginUserManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //开启注解方式验证接口
public class SecurityConfig {


    /**
     * 白名单
     */
    private static final String[] URL_WHITELIST = {
            "/login",
            "/doc.html",
            "/webjars/**",
            "/swagger-resources",
            "/v3/api-docs",
            "/api-docs",
            "/swagger-ui/**",
    };



    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider myAuthenticationProvider(LoginUserManager loginUserManager, SM3PasswordEncoder sm3PasswordEncoder){
        MyAuthenticationProvider myAuthenticationProvider = new MyAuthenticationProvider();
        myAuthenticationProvider.setUserDetailsService(loginUserManager);
        myAuthenticationProvider.setHideUserNotFoundExceptions(false);
        myAuthenticationProvider.setPasswordEncoder(sm3PasswordEncoder);
        return myAuthenticationProvider;
    }






    //如果想好看
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, LoginFilter loginFilter,
                                           MyAuthenticationHandler myAuthenticationHandler, TokenFilter tokenFilter) throws Exception {

        return http
                .csrf().disable()
                .formLogin().loginProcessingUrl("/login")
                .failureHandler(myAuthenticationHandler)
                .successHandler(myAuthenticationHandler)
                .and()
                .exceptionHandling().authenticationEntryPoint(myAuthenticationHandler)
                .accessDeniedHandler(myAuthenticationHandler)
                .and()
                .logout().logoutUrl("/loginout")
                .logoutSuccessHandler(myAuthenticationHandler)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(URL_WHITELIST).permitAll()
                .antMatchers(getAnonymousUrls()).permitAll() //放行允许匿名访问的url
                .anyRequest().authenticated() //其余都需要授权
                .and()
                .addFilterBefore(tokenFilter,UsernamePasswordAuthenticationFilter.class)
                .addFilterAt(loginFilter,UsernamePasswordAuthenticationFilter.class) //替换原有认证策略
                //禁用缓存
                .headers().cacheControl().disable().and()
                .build();

    }



    private String[] getAnonymousUrls(){
        // 获取所有的 RequestMapping
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = SpringUtils.getBean(RequestMappingHandlerMapping.class).getHandlerMethods();
        Set<String> allAnonymousAccess = new HashSet<>();
        // 循环 RequestMapping
        for (Map.Entry<RequestMappingInfo, HandlerMethod> infoEntry : handlerMethods.entrySet()) {
            HandlerMethod value = infoEntry.getValue();
            // 获取方法上 AnonymousAccess 类型的注解
            Anonymous methodAnnotation = value.getMethodAnnotation(Anonymous.class);
            // 如果方法上标注了 AnonymousAccess 注解，就获取该方法的访问全路径
            if (methodAnnotation != null) {
                allAnonymousAccess.addAll(infoEntry.getKey().getPatternsCondition().getPatterns());
            }
        }
        return allAnonymousAccess.toArray(new String[0]);
    }




}
