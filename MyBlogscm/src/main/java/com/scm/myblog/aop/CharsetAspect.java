package com.scm.myblog.aop;

import com.scm.myblog.utils.CharsetFilterUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CharsetAspect {

    //定义字符过滤切点
    @Pointcut("execution(* com.scm.myblog.controller.blogcontrollers.ArticleController.getArticleDetail(..))")
    public void CharSetPoint(){}

    //字符过滤环绕切面
    @Around("CharSetPoint()")
    public Object CharsetAspects(ProceedingJoinPoint pjp) throws Throwable {
        //获取所有请求参数
        Object[] args = pjp.getArgs();
        //过滤请求参数
        args[0]= CharsetFilterUtils.tranCharset((String) args[0]);
        //将结果放回pjp
        Object o;
//        try {
         o= pjp.proceed(args);
//        } catch (Throwable e) {
//            return null;
//        }
        //返回改变后的object
        return o;
    }
}
