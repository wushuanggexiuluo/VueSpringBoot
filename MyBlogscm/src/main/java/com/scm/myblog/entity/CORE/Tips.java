package com.scm.myblog.entity.CORE;


public class Tips {
    //成功的
    public static final String SAVE_OK = "插入成功";
    public static final String DELETE_OK = "删除成功";
    public static final String UPDATE_OK = "更新成功";
    public static final String GET_OK = "get请求成功";
    public static final String PASSWORD_OK="密码正确";
    public static final String PASSWORD_OK_ADMIN="密码正确，并且是管理员";
    //用户存在              String
    public static final String USER_OK="用户存在";
    //退出登陆成功            String
    public static final String Cancel_OK="退出登陆成功";
    public static final String registerOK="注册成功";
    public static final String PAGE_OK = "页面数据获取成功";
    public static final String REGISTER_OK="注册成功！";

    //失败的               String
    public static final String SAVE_ERR = "插入失败";
    public static final String DELETE_ERR = "删除失败";
    public static final String UPDATE_ERR = "更新失败";
    public static final String GET_ERR = "get请求失败";
    public static final String PASSWORD_ERR="密码错误";
    //用户不存在             String
    public static final String USER_ERR="用户不存在";
    //异常处理状态码           String
    public static final String SYSTEM_ERR = "系统错误";
    public static final String SYSTEM_TIMEOUT_ERR = "";
    public static final String SYSTEM_UNKNOW_ERR = "";
    public static final String BUSINESS_ERR = "";
    //上传成功              String
    public static final String UPLOAD_OK="上传成功";
    public static final String UPLOAD_ERR="上传失败";
    public static final String Redis_ERR="Redis未启动，或其他错误";
    public static final String REGISTER_ERR="注册失败！";


    public static final String InsertMySQLFromRedis_ERR="Redis数据未更新，数据库写入失败";


    public static final String Parms_Err="参数校验失败";
}
