package com.scm.myblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.scm.myblog.*")
// 过滤器
@ServletComponentScan("com.scm.myblog.filter")
// 定时任务
@EnableScheduling
// 异步
@EnableAsync
// 本地缓存
@EnableCaching
public class MyBlogscmApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyBlogscmApplication.class, args);
    }
}
