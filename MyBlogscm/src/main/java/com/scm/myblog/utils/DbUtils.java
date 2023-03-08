package com.scm.myblog.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class DbUtils {

    public static Integer getCurePage(Integer x,Integer y){
        return (x-1)*y;
    }

    public static String EncodePassword(String password){
        BCryptPasswordEncoder bcrypt=new BCryptPasswordEncoder();
        return  bcrypt.encode(password);
    }


}
