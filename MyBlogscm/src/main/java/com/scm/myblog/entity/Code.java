package com.scm.myblog.entity;

public class Code {
    //成功的
    public static final Integer SAVE_OK = 20001;
    public static final Integer DELETE_OK = 20002;
    public static final Integer UPDATE_OK = 20003;
    public static final Integer GET_OK = 20004;
    public static final Integer PASSWORD_OK=20005;
    public static final Integer PASSWORD_OK_ADMIN=20006;
    //用户存在
    public static final Integer USER_OK=20007;
    //退出登陆成功
    public static final Integer Cancel_OK=20008;
    public static final Integer REGISTER_OK=20009;
    public static final Integer PAGE_OK = 20004;


    //失败的
    public static final Integer SAVE_ERR = 10001;
    public static final Integer DELETE_ERR = 10002;
    public static final Integer UPDATE_ERR = 10003;
    public static final Integer GET_ERR = 10004;
    public static final Integer PASSWORD_ERR=10005;
    //用户不存在
    public static final Integer USER_ERR=10006;
    //异常处理状态码
    public static final Integer SYSTEM_ERR = 10007;
    public static final Integer SYSTEM_TIMEOUT_ERR = 10008;
    public static final Integer SYSTEM_UNKNOW_ERR = 10009;
    public static final Integer BUSINESS_ERR = 10010;
    //Redis错误
    public static final Integer Redis_ERR = 10011;

    /**
     * 插入mysql从redis失败
     * */
    public static final Integer InsertMySQLFromRedis_ERR = 10012;
    //注册失败
    public static final Integer REGISTER_ERR=10013;
    public static final Integer USER_NO_REGISTER=10014;
    //参数校验错误
    public static final Integer Parms_Err=10015;


    //上传成功
    public static final Integer UPLOAD_OK=2005;
    public static final Integer UPLOAD_ERR=10005;
}
