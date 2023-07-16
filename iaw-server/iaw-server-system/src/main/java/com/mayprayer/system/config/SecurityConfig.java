package com.mayprayer.system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //开启注解方式验证接口
public class SecurityConfig {


    /**
     * 白名单
     */
    private static final String[] URL_WHITELIST = {
            "/mallApi/captcha/**",
            "/mallApi/login",
            "/doc.html",
            "/webjars/**",
            "/swagger-resources",
            "/v3/api-docs",
            "/api-docs",
            "/swagger-ui/**",
            "/**/**"
    };
//
//
//
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }

//    @Bean
//    public DaoAuthenticationProvider myAuthenticationProvider(LoginUserManager loginUserManager, Md5PasswordEncoder md5PasswordEncoder){
//        MyAuthenticationProvider myAuthenticationProvider = new MyAuthenticationProvider();
//        myAuthenticationProvider.setUserDetailsService(loginUserManager);
//        myAuthenticationProvider.setHideUserNotFoundExceptions(false);
//        myAuthenticationProvider.setPasswordEncoder(md5PasswordEncoder);
//        return myAuthenticationProvider;
//    }






//    //如果想好看
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http, LoginFilter loginFilter,
//                                           MyAuthenticationHandler myAuthenticationHandler, TokenFilter tokenFilter) throws Exception {
//
//        return http
//                .csrf().disable()
//                .formLogin().loginProcessingUrl("/mallApi/login")
//                .failureHandler(myAuthenticationHandler)
//                .successHandler(myAuthenticationHandler)
//                .and()
//                .exceptionHandling().authenticationEntryPoint(myAuthenticationHandler)
//                .accessDeniedHandler(myAuthenticationHandler)
//                .and()
//                .logout().logoutUrl("/mallApi/loginout")
//                .logoutSuccessHandler(myAuthenticationHandler)
//                .and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authorizeRequests()
//                .antMatchers(URL_WHITELIST).permitAll()
//                .antMatchers(getAnonymousUrls()).permitAll() //放行允许匿名访问的url
//                .anyRequest().authenticated() //其余都需要授权
//                .and()
//                .addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class)
//                .addFilterAt(loginFilter,UsernamePasswordAuthenticationFilter.class) //替换原有认证策略
//                //禁用缓存
//                .headers().cacheControl().disable().and()
//                .build();
//
//    }
//
//
//
//    private String[] getAnonymousUrls(){
//        // 获取所有的 RequestMapping
//        Map<RequestMappingInfo, HandlerMethod> handlerMethods = SpringUtils.getBean(RequestMappingHandlerMapping.class).getHandlerMethods();
//        Set<String> allAnonymousAccess = new HashSet<>();
//        // 循环 RequestMapping
//        for (Map.Entry<RequestMappingInfo, HandlerMethod> infoEntry : handlerMethods.entrySet()) {
//            HandlerMethod value = infoEntry.getValue();
//            // 获取方法上 AnonymousAccess 类型的注解
//            Anonymous methodAnnotation = value.getMethodAnnotation(Anonymous.class);
//            // 如果方法上标注了 AnonymousAccess 注解，就获取该方法的访问全路径
//            if (methodAnnotation != null) {
//                allAnonymousAccess.addAll(infoEntry.getKey().getPatternsCondition().getPatterns());
//            }
//        }
//        return allAnonymousAccess.toArray(new String[0]);
//    }



    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/**/**");
    }





        //如果想好看
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http.authorizeHttpRequests()
                .anyRequest().permitAll()
                .and()
                .csrf().disable()
                .build();

    }


}
