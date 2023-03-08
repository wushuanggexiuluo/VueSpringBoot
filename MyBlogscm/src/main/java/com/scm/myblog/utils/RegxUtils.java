package com.scm.myblog.utils;


import com.scm.myblog.myenum.RegxEnum;

import java.util.regex.Pattern;

public class RegxUtils {

    public static boolean regxUrl(String value) {
        //获取正则
        String s = RegxEnum.Internet.getRegx();
        //校验
        return  Pattern.matches(s, value);
    }

    public static boolean regxEmail(String value) {
        //获取正则
        String s = RegxEnum.Email.getRegx();
        //校验
        return  Pattern.matches(s, value);
    }

    public static boolean regxIp(String value) {
        //获取正则
        String s = RegxEnum.Ip.getRegx();
        //校验
        return  Pattern.matches(s, value);
    }
}
