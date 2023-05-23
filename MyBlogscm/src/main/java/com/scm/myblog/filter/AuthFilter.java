package com.scm.myblog.filter;

import com.scm.myblog.entity.BO.LoginUser;
import com.scm.myblog.entity.CORE.StatusMes;
import com.scm.myblog.utils.JWTUtils;
import com.scm.myblog.utils.RedisUtils;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
@Slf4j
public class AuthFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {

        //获取token
        String token = request.getHeader("token");
        if(!StringUtils.hasText(token)){
            try {
                filterChain.doFilter(request, response);
            }
            catch (Exception h){
                log.info(h.getMessage());
            }

            return;
        }
        //解析token
        String uid = JWTUtils.validateToken(token);
        //从redis中获取用户信息
        LoginUser u= RedisUtils.getDataFromRedis("login:"+uid,LoginUser.class);
        //如果获取不到
        if (u == null) {
            log.info("登陆信息过期");
            //转发到controller层处理
            request.setAttribute("code", StatusMes.LOGIN_LAPSE.getCode());
            request.getRequestDispatcher("/Error2/Auth").forward(request, response);
            return;
        }
        //将信息存入securityHolder
        //获取权限信息封装到UsernamePasswordAuthenticationToken
        UsernamePasswordAuthenticationToken ux=new UsernamePasswordAuthenticationToken(u,null,u.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(ux);
        filterChain.doFilter(request, response);
    }
}
