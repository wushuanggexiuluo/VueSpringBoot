package com.scm.myblog.common.EnumLancer;

/*
这里只定义公用的异常，其他个性异常请勿定义！
 1 系统异常
 2 自定义异常
 3 缺失必要的参数
 4 参数校验异常
 5 404 异常
 */
public enum ErrorEnum implements IResponseEnum {

    Custom_Err ("A00001", "此是自定义异常！\n"),
    // 服务器异常保底者
    System_Err("A00002", "服务器跑路了!\n"),
    Params_Lack_Err ("A00003", "缺失必要的参数!\n"),
    Params_Check_Err ("A00004", "参数校验异常!\n"),
    Req_Not_Exist ("A00005", "请求的信息不存在!\n"),
    Req_Method_Not_Exist ("A00006", "请求方法错误!\n"),
    ;
    /** 错误码 */
    private final String errorCode;

    /** 错误信息 */
    private final String errorMsg;
    ErrorEnum(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
    @Override
    public String getCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return errorMsg;
    }
}
