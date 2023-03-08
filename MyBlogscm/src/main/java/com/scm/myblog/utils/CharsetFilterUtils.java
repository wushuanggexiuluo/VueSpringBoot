package com.scm.myblog.utils;

/**
 * 字符集过滤器
 *
 * @author Lancer
 * @date 2022/12/08
 */
public class CharsetFilterUtils {
    /**
     * tran字符集
     *
     * @param str str
     * @return {@link String}
     */
    public static String tranCharset(String str){
        if (str.indexOf("%23") >= 1) {
            str = str.replace("%23", "#");
        }
        if (str.indexOf("%2B") >= 1) {
            str = str.replace("%2B", "+");
        }
        if (str.indexOf("%2F") >= 1) {
            str = str.replace("%2F", "?");
        }
        if (str.indexOf("%26") >= 1) {
            str = str.replace("%26", "&");
        }
        if (str.indexOf("%3D") >= 1) {
            str = str.replace("%3D", "=");
        }
        if (str.indexOf("rtfgv") >= 1) {
            str = str.replace("rtfgv", "%");
        }
        return str;
    }
}
