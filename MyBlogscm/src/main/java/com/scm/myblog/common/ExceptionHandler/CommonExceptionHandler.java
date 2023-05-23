package com.scm.myblog.common.ExceptionHandler;

import com.scm.myblog.common.EnumLancer.ErrorEnum;
import com.scm.myblog.common.ErrResult.ErrorResult;
import com.scm.myblog.common.ExceptionLancer.CommonException;
import com.scm.myblog.entity.CORE.StatusMes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class CommonExceptionHandler {

    private ErrorResult Params_Common_Res(List<String> defaultMsg){
        System.out.println(defaultMsg);
        return ErrorResult.build(new ErrorResult(ErrorEnum.Params_Check_Err.getCode(),ErrorEnum.Params_Check_Err.getMessage()), defaultMsg.get(0));
    }
    private ErrorResult Params_Common_Res(){
        return new ErrorResult(ErrorEnum.Params_Check_Err.getCode(),ErrorEnum.Params_Check_Err.getMessage());
    }

//    // 请求方法不支持
//    @ResponseBody
//    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
//    public ErrorResult handleInternalAuthenticationServiceException(HttpRequestMethodNotSupportedException e) {
//        log.info(e.getMessage());
//        return ErrorResult.build(ErrorResult.builder()
//                .msg(ErrorEnum.Req_Method_Not_Exist.getMessage())
//                .code(ErrorEnum.Req_Method_Not_Exist.getCode())
//                .build(),e.getMessage());
//    }

    //springsecurity，非过滤器上抛出的异常
    @ExceptionHandler(value = InternalAuthenticationServiceException.class)
    public ErrorResult handleInternalAuthenticationServiceException(InternalAuthenticationServiceException e) {
        log.info(e.getMessage());
        return new ErrorResult();
    }
    //认证失败的(密码校验失败)
    @ExceptionHandler(value = AuthenticationException.class)
    public ErrorResult handleInvalidGrantException(AuthenticationException e) {
        log.info(e.getMessage());
        return new ErrorResult(StatusMes.PASSWORD_ERR.getCode(), e.getMessage());
    }

    // 404 捕获
    @ExceptionHandler(value = NoHandlerFoundException.class)
    public ErrorResult handleServletException(Exception e){
        return ErrorResult.build(new ErrorResult(ErrorEnum.Req_Not_Exist.getCode(),ErrorEnum.Req_Not_Exist.getMessage()), e.getMessage());
    }

    // 错误情况：
    //1 通过get请求传递的带有body体的参数错误
    @ExceptionHandler(value = BindException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResult handleBindException(BindException e) {
        log.error("{}", e.getMessage(), e);
        List<String> defaultMsg = e.getBindingResult().getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.toList());
        return Params_Common_Res(defaultMsg);
    }

    @ExceptionHandler(value = TypeMismatchException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResult typeParamsException(TypeMismatchException e) {
        log.error("{}", e.getMessage(), e);
        return ErrorResult.build(new ErrorResult());
    }

    // 错误情况：
    // 1：使用PathVariable并且是get请求的参数错误。
    // 2：使用RequestParam并且是from-data方式提交的post请求的参数错误。
    @ExceptionHandler(value = ConstraintViolationException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResult handleBindGetException(ConstraintViolationException e) {
        log.error("{}", e.getMessage(), e);
        List<String> defaultMsg = e.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
        return Params_Common_Res(defaultMsg);
    }


    // 错误情况：
    //1 使用了ResponseBody并且是json数据类型的post请求
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("{}", e.getMessage(), e);
        List<String> defaultMsg = e.getBindingResult().getFieldErrors()
                .stream()
                .map(fieldError -> "【" + fieldError.getField() + "】" + fieldError.getDefaultMessage())
                .collect(Collectors.toList());
        return Params_Common_Res(defaultMsg);
    }

    /**
     * 兼容Validation校验框架：忽略参数异常处理器
     */
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResult handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.error("{}", e.getMessage(), e);
        log.error("ParameterName: {}", e.getParameterName());
        log.error("ParameterType: {}", e.getParameterType());
        return Params_Common_Res();
    }

    // 前端并没有提交必要的参数信息
    @ExceptionHandler(HttpMessageNotReadableException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResult error(HttpMessageNotReadableException e){
        log.error("{}", e.getMessage(), e);
        return ErrorResult.build(new ErrorResult(ErrorEnum.Params_Lack_Err.getMessage(),ErrorEnum.Params_Lack_Err.getCode()), e.getMessage());
    }

    // 通用业务异常
    @ExceptionHandler(value = CommonException.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResult handleCommonException(CommonException e) {
        if (e.getErrorResult() != null) {
            return ErrorResult.build(e.getErrorResult(),"请重新登陆！");
        }
        return new ErrorResult(ErrorEnum.Custom_Err.getCode(), e.getMessage());
    }
    // 其他运行时异常
    @ExceptionHandler(value = Exception.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResult handleDefaultException(Exception e) {
        log.error("{}, {}", e.getMessage(), e);
        return new ErrorResult(ErrorEnum.System_Err.getCode(), e.getMessage());
    }
}
