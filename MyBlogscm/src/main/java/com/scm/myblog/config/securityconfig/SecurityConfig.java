package com.scm.myblog.config.securityconfig;

import com.scm.myblog.common.ExceptionLancer.MyAuthenticationException;
import com.scm.myblog.filter.AuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
//开启权限管理系统
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthFilter af;
    @Autowired
    private MyAuthenticationException myAuthenticationException;
    //密码加密解密
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Order(1)
    protected void configure(HttpSecurity http) throws Exception {
        //设置无需权限即可访问的
        for (String n: ApiConfig.NoAuthApi){
            http.authorizeRequests().antMatchers(n).permitAll();
        }
        http
                //关闭csrf
                .csrf().disable()
                //不通过session获取security上下文
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                //其他的所有接口都需要带token认证
                .anyRequest().authenticated()
                .and().exceptionHandling().authenticationEntryPoint(myAuthenticationException);

        //配置自定义的过滤器在何处执行
        //在UsernamePasswordAuthenticationFilter之前
        http.addFilterBefore(af, UsernamePasswordAuthenticationFilter.class);
        //配置跨域请求
        http.cors();
    }


    //用于进行用户验证
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
