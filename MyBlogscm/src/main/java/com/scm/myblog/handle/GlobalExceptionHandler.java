package com.scm.myblog.handle;
import com.scm.myblog.entity.StatusMes;
import com.scm.myblog.entity.VO.Result;
import com.scm.myblog.exception.DefinitionException;
import com.scm.myblog.exception.SystemException;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Log4j2
@ControllerAdvice
public class GlobalExceptionHandler {

    //springsecurity，非过滤器上抛出的异常
    @ResponseBody
    @ExceptionHandler(value = InternalAuthenticationServiceException.class)
    public Result handleInternalAuthenticationServiceException(InternalAuthenticationServiceException e) {
        System.out.println(e.getMessage());
        return new Result();
    }
    //认证失败的(密码校验失败)
    @ResponseBody
    @ExceptionHandler(value =AuthenticationException.class)
    public Result handleInvalidGrantException(AuthenticationException e) {
        System.out.println(e.getMessage());
        return new Result(null, StatusMes.PASSWORD_ERR.getCode(), e.getMessage());
    }
    /**
     * 处理业务异常
     *
     */
    @ExceptionHandler(value = DefinitionException.class)
    @ResponseBody
    public Result bizExceptionHandler(DefinitionException e) {
        return Result.defineError(e);
    }

    /**
     * 处理系统异常
     *
     */
    @ExceptionHandler(value = SystemException.class)
    @ResponseBody
    public Result exceptionHandler(SystemException e) {
        log.info("系统异常："+e.getErrorCode()+"+++"+e.getErrorMsg());
        return Result.systemError(e);
    }
}
