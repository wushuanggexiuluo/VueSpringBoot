package com.scm.myblog.scheduledtask;
import com.scm.myblog.utils.SpringContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 调度运行
 *
 * @author 孙超孟
 * @date 2023/04/23
 */
@Slf4j
public class SchedulingRunnable implements Runnable {

    private final String beanName;

    private final String methodName;

    private final String params;

    public SchedulingRunnable(String beanName, String methodName) {
        this(beanName, methodName, null);
    }

    public SchedulingRunnable(String beanName, String methodName, String params) {
        this.beanName = beanName;
        this.methodName = methodName;
        this.params = params;
    }

    @Override
    public void run() {
        log.info("定时任务开始执行 - bean：{}，方法：{}，参数：{}", beanName, methodName, params);
        long startTime = System.currentTimeMillis();

        try {
            Object target = SpringContextUtils.getBean(beanName);
            Method method;
            if (!StringUtils.isEmpty(params)) {
                method = target.getClass().getDeclaredMethod(methodName, String.class);
            } else {
                method = target.getClass().getDeclaredMethod(methodName);
            }

            ReflectionUtils.makeAccessible(method);
            if (!StringUtils.isEmpty(params)) {
                method.invoke(target, params);
            } else {
                method.invoke(target);
            }
        } catch (Exception ex) {
            log.error(String.format("定时任务执行异常 - bean：%s，方法：%s，参数：%s ", beanName, methodName, params), ex);
        }

        long times = System.currentTimeMillis() - startTime;
        log.info("定时任务执行结束 - bean：{}，方法：{}，参数：{}，耗时：{} 毫秒", beanName, methodName, params, times);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SchedulingRunnable)) return false;
        SchedulingRunnable that = (SchedulingRunnable) o;
        return Objects.equals(beanName, that.beanName) && Objects.equals(methodName, that.methodName) && Objects.equals(params, that.params);
    }

    @Override
    public int hashCode() {
        return Objects.hash(beanName, methodName, params);
    }
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        SchedulingRunnable that = (SchedulingRunnable) o;
//        if (params == null) {
//            return beanName.equals(that.beanName) &&
//                    methodName.equals(that.methodName) &&
//                    that.params == null;
//        }
//
//        return beanName.equals(that.beanName) &&
//                methodName.equals(that.methodName) &&
//                params.equals(that.params);
//    }
//
//    @Override
//    public int hashCode() {
//        if (params == null) {
//            return Objects.hash(beanName, methodName);
//        }
//
//        return Objects.hash(beanName, methodName, params);
//    }
}  
