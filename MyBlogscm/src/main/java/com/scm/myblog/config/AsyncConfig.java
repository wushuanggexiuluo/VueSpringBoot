package com.scm.myblog.config;

import com.scm.myblog.config.entity.ThreadPoolInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@RequiredArgsConstructor
@Configuration
public class AsyncConfig {

    private final ThreadPoolInfo info;

    @Bean("asyncExecutor")
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(info.getCorePoolSize());
        executor.setMaxPoolSize(info.getMaxPoolSize());
        executor.setQueueCapacity(info.getQueueCapacity());
        executor.setThreadNamePrefix(info.getThreadNamePrefix());
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }
}