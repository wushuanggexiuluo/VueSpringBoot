package com.scm.myblog.entity.CORE;


import com.scm.myblog.common.EnumLancer.IResponseEnum;

public enum StatusMes implements IResponseEnum {


    Err_500("500","未知错误"),
    OK_200("200","获取成功"),

    SAVE_OK ("20001","插入成功"),
    DELETE_OK ("20002","删除成功"),
    UPDATE_OK ("20003","更新成功"),
    GET_OK ("20004","get请求成功"),
    PASSWORD_OK ("20005","密码正确"),
    PASSWORD_OK_ADMIN ("20006","密码正确，并且是管理员"),

    USER_OK ("20007","用户存在"),

    Cancel_OK ("20008","退出登陆成功"),
    REGISTER_OK ("20009","注册成功"),
    PAGE_OK ("20010","页面数据获取成功"),
    UPLOAD_OK ("2005","上传成功"),

    SAVE_ERR ("10001","插入失败"),
    DELETE_ERR ("10002","删除失败"),
    UPDATE_ERR ("10003","更新失败"),
    GET_ERR ("10004","get请求失败"),
    PASSWORD_ERR ("10005","密码错误"),


    SYSTEM_ERR ("10007","系统错误"),
    SYSTEM_TIMEOUT_ERR ("10008",""),
    SYSTEM_UNKNOW_ERR ("10009",""),
    BUSINESS_ERR ("10010",""),
    Redis_ERR ("10011","Redis未启动，或其他错误,请检查 " +
                    "1 Redis密码错误（查看Redis配置文件）如果没有密码可以删除配置文件中的Redis的Password" +
                    "2 Redis是否重复启动了多个（win使用任务管理器）" +
                    "3 Redis配置中的bind 127.0.0.1是否没有注释（注释了可以远程访问）"),
    InsertMySQLFromRedis_ERR ("10012","Redis数据未更新，数据库写入失败"),
    REGISTER_ERR ("10013","注册失败"),
    USER_NO_REGISTER ("10014","用户没有注册"),

    Parms_Err ("10015","参数异常"),
    UPLOAD_ERR ("10016","上传失败"),
    LOGIN_LAPSE("10017","未登陆或者登录凭证已过期，请重新登陆！"),
    Pretend_delete("-1","假装删除成功!需要真正删除的请下载源码后注释aop/PretendModifyAspect.java"),
    Pretend_update("-1","假装更新成功!需要真正更新数据的请下载源码后注释aop/PretendModifyAspect.java"),
    Article_Id_Err("10018","请求文章Id错误"),

    Insert_Task_Err("10019","插入的任务调度已存在，请检查BeanName与方法名是否重复"),

    Update_Task_Err1("10020","更新失败！"),
    Update_Task_Err2("10021","您所更新的任务不存在！"),
    Delete_Task_Err1("10022","您所删除的id不存在,请检查参数"),
    ;



    /** 错误码 */
    private final String errorCode;

    /** 错误信息 */
    private final String errorMsg;
    @Override
    public String getCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return errorMsg;
    }
    StatusMes(String code,String message){
        this.errorCode=code;
        this.errorMsg = message;
    }
    public Long getModifyIds() {
        return -1L;
    }
}
