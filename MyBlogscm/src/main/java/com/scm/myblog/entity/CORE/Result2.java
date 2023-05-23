package com.scm.myblog.entity.CORE;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * result2
 * 接口返回数据格式
 *
 * @author 孙超孟
 * @date 2023/05/16
 */
@Data
@ApiModel(value = "接口返回对象", description = "接口返回对象")
public class Result2<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "成功标志")
    private boolean success = true;


    @ApiModelProperty(value = "返回处理消息")
    private String message = "";


    @ApiModelProperty(value = "返回代码")
    private String code = "0";


    @ApiModelProperty(value = "返回数据对象")
    private T data;


    @ApiModelProperty(value = "时间戳")
    private long timestamp = System.currentTimeMillis();

    public Result2() {
    }


    public Result2(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static <T> Result2<T> Fail(String code, String message) {
        Result2<T> r = new Result2<T>();
//        r.setTimestamp();
        r.setSuccess(false);
        r.setCode(code);
        r.setMessage(message);
        return r;
    }

    public Result2<T> success(String message) {
        this.message = message;
        this.code = StatusMes.GET_OK.getCode();
        this.success = true;
        return this;
    }

    public static <T> Result2<T> success(T data) {
        Result2<T> r = new Result2<T>();
        r.setSuccess(true);
        r.setCode(StatusMes.GET_OK.getCode());
        r.setData(data);
        return r;
    }

    public static <T> Result2<T> ok() {
        Result2<T> r = new Result2<T>();
        r.setSuccess(true);
        r.setCode(StatusMes.GET_OK.getCode());
        return r;
    }

    public static <T> Result2<T> ok(String message) {
        Result2<T> r = new Result2<T>();
        r.setSuccess(true);
        r.setCode(StatusMes.GET_OK.getCode());
        r.setMessage(message);
        return r;
    }

    public static <T> Result2<T> ok(T data) {
        Result2<T> r = new Result2<T>();
        r.setSuccess(true);
        r.setCode(StatusMes.GET_OK.getCode());
        r.setData(data);
        return r;
    }

    public static <T> Result2<T> OK() {
        Result2<T> r = new Result2<T>();
        r.setSuccess(true);
        r.setCode(StatusMes.GET_OK.getCode());
        return r;
    }

    public static <T> Result2<T> OK(String message) {
        Result2<T> r = new Result2<T>();
        r.setSuccess(true);
        r.setCode(StatusMes.GET_OK.getCode());
        r.setMessage(message);
        //Result OK(String message)方法会造成兼容性问题 issues/I4IP3D
        r.setData(null);
        return r;
    }

    public static <T> Result2<T> OK(T data) {
        Result2<T> r = new Result2<T>();
        r.setSuccess(true);
        r.setCode(StatusMes.GET_OK.getCode());
        r.setData(data);
        return r;
    }

    public static <T> Result2<T> OK(String code,String message) {
        Result2<T> r = new Result2<T>();
        r.setSuccess(true);
        r.setCode(code);
        r.setMessage(message);
        return r;
    }
    public static <T> Result2<T> OK(String message, T data) {
        Result2<T> r = new Result2<T>();
        r.setSuccess(true);
        r.setCode(StatusMes.GET_OK.getCode());
        r.setMessage(message);
        r.setData(data);
        return r;
    }
    

    public static <T> Result2<T> error(String message, T data) {
        Result2<T> r = new Result2<T>();
        r.setSuccess(false);
        r.setCode(StatusMes.Err_500.getCode());
        r.setMessage(message);
        r.setData(data);
        return r;
    }

    public static <T> Result2<T> error(CodeMes CodeMes) {
        Result2<T> r = new Result2<T>();
        r.setSuccess(false);
        r.setMessage(CodeMes.message);
        r.setCode(CodeMes.code);
        return r;
    }

    public static <T> Result2<T> error(String message) {
        return error(StatusMes.Err_500.getCode(), message);
    }

    public static <T> Result2<T> error(String code, String message) {
        Result2<T> r = new Result2<T>();
        r.setCode(code);
        r.setMessage(message);
        r.setSuccess(false);
        return r;
    }

    public Result2<T> error500(String message) {
        this.message = message;
        this.code = StatusMes.Err_500.getCode();
        this.success = false;
        return this;
    }

    public Result2<T> build(){
        return new Result2<>();
    }
}
