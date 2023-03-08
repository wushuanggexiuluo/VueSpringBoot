package com.scm.myblog.filter;

import com.scm.myblog.entity.BO.LoginUser;
import com.scm.myblog.entity.Code;
import com.scm.myblog.entity.StatusMes;
import com.scm.myblog.exception.DefinitionException;
import com.scm.myblog.exception.MyAuthenticationException;
import com.scm.myblog.utils.JWTUtils;
import com.scm.myblog.utils.RedisUtils;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class AuthFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取token
        String token = request.getHeader("token");
        if(!StringUtils.hasText(token)){
            filterChain.doFilter(request, response);
            return;
        }
        //解析token
        String uid = JWTUtils.validateToken(token);
        //从redis中获取用户信息

        LoginUser u= RedisUtils.getDataFromRedis("login:"+uid,LoginUser.class);
        //如果获取不到
        if (u == null) {
            System.out.println("登陆信息过期");
            //转发到controller层处理
            request.setAttribute("code", StatusMes.LOGIN_LAPSE.getCode());
            request.getRequestDispatcher("/Error/Auth").forward(request, response);
            return;
        }
        //将信息存入securityHolder
        //获取权限信息封装到UsernamePasswordAuthenticationToken
        UsernamePasswordAuthenticationToken ux=new UsernamePasswordAuthenticationToken(u,null,u.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(ux);
        filterChain.doFilter(request, response);
    }
}
