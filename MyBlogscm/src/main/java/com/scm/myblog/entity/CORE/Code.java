package com.scm.myblog.entity.CORE;

public class Code {
    //成功的
    public static final String SAVE_OK = "20001";
    public static final String DELETE_OK = "20002";
    public static final String UPDATE_OK = "20003";
    public static final String GET_OK = "20004";
    public static final String PASSWORD_OK= "20005";
    public static final String PASSWORD_OK_ADMIN= "20006";

    //退出登陆成功
    public static final String Cancel_OK= "20008";
    public static final String REGISTER_OK= "20009";
    public static final String PAGE_OK = "20004";


    //失败的
    public static final String SAVE_ERR = "10001";
    public static final String DELETE_ERR = "10002";
    public static final String UPDATE_ERR = "10003";

    //异常处理状态码
    public static final String SYSTEM_ERR = "10007";

    /**
     * 插入mysql从redis失败
     * */
    public static final String InsertMySQLFromRedis_ERR = "10012";
    //参数校验错误
    public static final String Parms_Err= "10015";


    //上传成功
    public static final String UPLOAD_OK= "2005";
    public static final String UPLOAD_ERR= "10005";
}
