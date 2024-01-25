package com.mayprayer.system.filter;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.mayprayer.common.domain.dto.sys.LoginUser;
import com.mayprayer.common.utils.constant.Constant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class TokenFilter extends OncePerRequestFilter {

    @Resource
    private RedisTemplate redisTemplate;

    /**
     * token 是否支持续期
     */
    @Value("${token.renew}")
    private boolean renew ;

    /**
     * token 过期时间
     */
    @Value("${token.expireTime}")
    private Long expireTime ;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //todo 根据redis 查询当前用用户信息，未查询到 直接放行
        Cookie[] cookies = request.getCookies();
        String token =null;
        if (null!=cookies&&cookies.length>0){
            for (Cookie cookie : cookies){
                if (cookie.getName().equals(Constant.USER_TOKEN)){
                    token = cookie.getValue();
                }
            }
        }
        if (StrUtil.isNotBlank(token)){
            Set<String> keys = redisTemplate.keys("*_" + token);
            if (CollectionUtil.isNotEmpty(keys)){
                //构建一个已认证的凭证对象
                Iterator<String> it = keys.iterator();
                while (it.hasNext()){
                    String userKeyStr = it.next();
                    Map<String,Object> userMap= (Map) redisTemplate.opsForValue().get(userKeyStr);
                    LoginUser user = BeanUtil.toBean(userMap,LoginUser.class);
                    if (user!=null){
                        //查询到 直接构建一个认证对象
                        UsernamePasswordAuthenticationToken userToken  = new UsernamePasswordAuthenticationToken(user,null,null);
                        userToken.setDetails(new WebAuthenticationDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(userToken);
                        if (renew){
                            redisTemplate.opsForValue().set(userKeyStr,userMap,expireTime, TimeUnit.MINUTES);
                        }
                    }
                }
            }
        }
        //放行继续认证下一个过滤器认证 ，如果当前认证对象已经认证将不在执行后续过滤器
        filterChain.doFilter(request,response);
    }

}
