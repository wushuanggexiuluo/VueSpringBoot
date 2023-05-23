package com.scm.myblog.config.mpconfig;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * mp配置
 *
 * @author Lancer
 * @date 2022/12/08
 */
@Configuration
public class MybatisPlusConfig {
    //乐观锁配置
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        MybatisPlusInterceptor mpg = new MybatisPlusInterceptor();
        mpg.addInnerInterceptor(new PaginationInnerInterceptor(DbType.H2));
        return mpg;
    }
    //模糊+分页配置
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
