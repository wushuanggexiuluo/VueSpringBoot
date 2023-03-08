package com.scm.myblog.utils;

public class SystemUtils {
    //是否是win操作系统
    public static Boolean isWindows() {
        String s = System.getProperty("os.name");
        //startsWith用于检测是否已指定字符串前缀开始
        return s != null && s.startsWith("Windows");
    }
}
