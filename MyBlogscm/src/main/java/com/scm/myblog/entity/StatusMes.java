package com.scm.myblog.entity;

import javafx.scene.input.KeyCodeCombination;

public enum StatusMes {

    SAVE_OK {
        public Integer getCode() {
            return 20001;
        }

        public String getMes() {
            return "插入成功";
        }
    },
    DELETE_OK {
        public Integer getCode() {
            return 20002;
        }

        public String getMes() {
            return "删除成功";
        }
    },
    UPDATE_OK {
        public Integer getCode() {
            return 20003;
        }

        public String getMes() {
            return "更新成功";
        }
    },
    GET_OK {
        public Integer getCode() {
            return 20004;
        }

        public String getMes() {
            return "get请求成功";
        }
    },
    PASSWORD_OK {
        public Integer getCode() {
            return 20005;
        }

        public String getMes() {
            return "密码正确";
        }
    },
    PASSWORD_OK_ADMIN {
        public Integer getCode() {
            return 20006;
        }

        public String getMes() {
            return "密码正确，并且是管理员";
        }
    },

    USER_OK {
        public Integer getCode() {
            return 20007;
        }

        public String getMes() {
            return "用户存在";
        }
    },

    Cancel_OK {
        public Integer getCode() {
            return 20008;
        }

        public String getMes() {
            return "退出登陆成功";
        }
    },
    REGISTER_OK {
        public Integer getCode() {
            return 20009;
        }

        public String getMes() {
            return "注册成功";
        }
    },
    PAGE_OK {
        public Integer getCode() {
            return 20010;
        }

        public String getMes() {
            return "页面数据获取成功";
        }
    },
    UPLOAD_OK {
        public Integer getCode() {
            return 2005;
        }

        public String getMes() {
            return "上传成功";
        }
    },

    SAVE_ERR {
        public Integer getCode() {
            return 10001;
        }

        public String getMes() {
            return "插入失败";
        }
    },
    DELETE_ERR {
        public Integer getCode() {
            return 10002;
        }

        public String getMes() {
            return "删除失败";
        }
    },
    UPDATE_ERR {
        public Integer getCode() {
            return 10003;
        }

        public String getMes() {
            return "更新失败";
        }
    },
    GET_ERR {
        public Integer getCode() {
            return 10004;
        }

        public String getMes() {
            return "get请求失败";
        }
    },
    PASSWORD_ERR {
        public Integer getCode() {
            return 10005;
        }

        public String getMes() {
            return "密码错误";
        }
    },


    SYSTEM_ERR {
        public Integer getCode() {
            return 10007;
        }

        public String getMes() {
            return "系统错误";
        }
    },
    SYSTEM_TIMEOUT_ERR {
        public Integer getCode() {
            return 10008;
        }

        public String getMes() {
            return "";
        }
    },
    SYSTEM_UNKNOW_ERR {
        public Integer getCode() {
            return 10009;
        }

        public String getMes() {
            return "";
        }
    },
    BUSINESS_ERR {
        public Integer getCode() {
            return 10010;
        }

        public String getMes() {
            return "";
        }
    },
    Redis_ERR {
        public Integer getCode() {
            return 10011;
        }

        public String getMes() {
            return "Redis未启动，或其他错误,请检查" +
                    "1 Redis密码错误（查看Redis配置文件）" +
                    "2 Redis是否重复启动了多个（win使用任务管理器）" +
                    "3 Redis配置中的bind 127.0.0.1是否没有注释（注释了可以远程访问）";
        }
    },
    InsertMySQLFromRedis_ERR {
        public Integer getCode() {
            return 10012;
        }

        public String getMes() {
            return "Redis数据未更新，数据库写入失败";
        }
    },
    REGISTER_ERR {
        public Integer getCode() {
            return 10013;
        }

        public String getMes() {
            return "注册失败";
        }
    },
    USER_NO_REGISTER {
        public Integer getCode() {
            return 10014;
        }

        public String getMes() {
            return "用户没有注册";
        }
    },

    Parms_Err {
        public Integer getCode() {
            return 10015;
        }

        public String getMes() {
            return "参数异常";
        }
    },
    UPLOAD_ERR {
        public Integer getCode() {
            return 10016;
        }

        public String getMes() {
            return "上传失败";
        }
    },
    LOGIN_LAPSE{
        public Integer getCode() {
            return 10017;
        }

        public String getMes() {
            return "登录凭证过期";
        }
    },
    Pretend_delete{

        public Integer getCode(){
            return -1;
        }
        public String getMes(){
            return "假装删除成功!需要真正删除的请下载源码后注释aop/PretendModifyAspect.java";
        }
    },
    Pretend_update{
        public Integer getCode(){
            return -1;
        }
        public String getMes(){
            return "假装更新成功!需要真正更新数据的请下载源码后注释aop/PretendModifyAspect.java";
        }
    }
    ;

    public abstract String getMes();
    public abstract Integer getCode();

    public Long getModifyIds() {
        return -1L;
    }
}
