package com.scm.myblog.common.ExceptionLancer;

import com.scm.myblog.common.ErrResult.ErrorResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

// 也重写父类的hashcode方法
@EqualsAndHashCode(callSuper = true)
@Data
public class CommonException extends RuntimeException{
    private String message;
    private String code;
    private ErrorResult errorResult;

    public CommonException(String code, String msg) {
        this.code=code;
        this.message=msg;
        this.errorResult = new ErrorResult(code, msg);
    }

    public CommonException(ErrorResult errorResult) {
        this.code=errorResult.getCode();
        this.message=errorResult.getMsg();
        this.errorResult = errorResult;
    }
}
