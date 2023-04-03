//package com.scm.myblog.aop;
//
//import com.scm.myblog.entity.StatusMes;
//import com.scm.myblog.entity.VO.Result;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.stereotype.Component;
//
///**
// * 假装修改切面
// * 防止有人恶意破坏演示网站
// *
// * @author Lancer
// * @date 2023/03/27
// */
//@Aspect
//@Component
//public class PretendModifyAspect {
//    //假装删除方法的切点，
//    @Pointcut("execution(public * com.scm.myblog.service.impl.*Impl.remove*(..))")
//    public void modifyRemove(){}
//    //更新方法切点
//    @Pointcut("execution(public * com.scm.myblog.service.impl.*Impl.update*(..))")
//    public void modifyUpdate(){}
//
//    @Around("modifyRemove()")
//    public Result MyModifyRemove(ProceedingJoinPoint pjp){
//        Result result = new Result();
//        result.setData(StatusMes.Pretend_delete.getModifyIds());
//        result.setCode(StatusMes.DELETE_OK.getCode());
//        result.setMessage(StatusMes.Pretend_delete.getMes());
//        return result;
//    }
//    @Around("modifyUpdate()")
//    public Result MyModifyUpdate(ProceedingJoinPoint pjp){
//        Result result = new Result();
//        result.setData(StatusMes.Pretend_update.getModifyIds());
//        result.setCode(StatusMes.UPDATE_OK.getCode());
//        result.setMessage(StatusMes.Pretend_update.getMes());
//        return result;
//    }
//}
