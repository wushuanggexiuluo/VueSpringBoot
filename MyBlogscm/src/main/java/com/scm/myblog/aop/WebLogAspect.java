package com.scm.myblog.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * web日志切面
 *
 * @author Lancer
 * @date 2022/12/04
 */
@Aspect
@Component
@Slf4j
public class WebLogAspect {
    /**
     * 开始时间
     */
    private Long startTime;

    /**
     * web日志切入点
     */
    @Pointcut("execution(public * com.scm.myblog.controller.*.*(..))")
    public void WebLogPointcut() {}

    /**
     * 执行之前
     *
     * @param j j
     */
    @Before("WebLogPointcut()")
    public void doBefore(JoinPoint j) {
        startTime= System.currentTimeMillis();
        ServletRequestAttributes s=(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert s != null;
        HttpServletRequest request = s.getRequest();
        log.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        log.info("请求开始时间：" + LocalDateTime.now());
        log.info("请求方式为："+request.getMethod());
        log.info("请求IP为："+request.getRemoteAddr());
        log.info("请求访问为：" + j.getSignature().getDeclaringTypeName() + "."
                + j.getSignature().getName());
        log.info("请求参数 : {}" + Arrays.toString(j.getArgs()));
        log.info("~~~~~~~~~~~~~~~~~");
    }
    /**
     * 返回通知：
     * 1. 在目标方法正常结束之后执行
     * 1. 在返回通知中补充请求日志信息
     *
     */
    @AfterReturning(returning = "r", pointcut = "WebLogPointcut()")
    public void doAfterReturning(Object r) throws Throwable {
        /*
          结束时间
         */
        log.info("请求耗时："+ (System.currentTimeMillis() - startTime));
        // 处理完请求，返回内容
        log.info("请求返回 :");
        log.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

    }
    /**
     * 异常通知：
     * 1. 在目标方法非正常结束，发生异常或者抛出异常时执行
     * 1. 在异常通知中设置异常信息，并将其保存
     *
     * @param throwable
     */
    @AfterThrowing(value = "WebLogPointcut()", throwing = "throwable")
    public void doAfterThrowing(Throwable throwable) {
        log.info("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        // 保存异常日志记录
        log.error("发生异常时间：" + LocalDateTime.now());
        log.error("抛出异常为：" + throwable.getMessage());
        log.info("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
    }
}
