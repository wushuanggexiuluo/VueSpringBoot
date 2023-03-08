package com.scm.myblog.myenum;



public enum RegxEnum {
    //邮箱
    Email{

        public String getRegx(){
            return "^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$";
        }
    },
    //ip
    Ip{
        public String getRegx() {
            return "^(\\d{1,3}.\\d{1,3}.\\d{1,3}.\\d{1,3})$";
        }

    },
    //网址
    Internet{

        public String getRegx() {
            return "^[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(\\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+\\.?$";
        }

    };
    //字母和数字

    //定义抽象方法,其他枚举来实现
    public abstract String getRegx();


}

