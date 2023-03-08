package com.scm.myblog.utils;

import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Component
public class WebUtils {

    //获取ServletContext
    public static ServletContext getServletContext(ServletContextEvent sce) {
        return sce.getServletContext();
    }

    /**
     * 获取Cookie中指定参数的值
     */
    public static String getCookieVal(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    System.out.println("Cookie中sissionid的值为：" + cookie.getValue());
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    /*判断此用户是否在线（用户不一定登陆），传入sessionid
    /
     */
    public static boolean isUserOnBlog(HttpServletRequest request, String sessionId) {
        Object map = request.getServletContext().getAttribute("map");
        String ipAddress = request.getRemoteAddr();
        if (map != null) {
            HashMap<String, List<HttpSession>> map2 = (HashMap<String, List<HttpSession>>) map;
            List<HttpSession> httpSessions = map2.get(ipAddress);
            for (HttpSession httpSession : httpSessions) {
                if (httpSession.getId().equals(sessionId)) {
                    System.out.println("上线用户为Sessionid为：" + sessionId);
                    return true;
                }
            }
        }
        return false;
    }

    //强行不让用户访问网页（用户不一定登陆），传入sessionId
    public static boolean removeUser(HttpServletRequest request, String sessionId) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            //使session失效
            session.invalidate();
            System.out.println("用户离线！");
            return true;
            //失效后，需要进行的操作，List链表中需要减去，用到了Session域监听器
        }
        return false;
    }

    /*
    获取在线人数(不一定登陆)
     */
    public static void getOnInternatCount(HttpServletRequest request) {
        Object r = request.getServletContext().getAttribute("map");
        if (r != null) {
            HashMap<String, List<HttpSession>> map = (HashMap<String, List<HttpSession>>) r;
            System.out.println("在线人数为：" + map.size());
        }
    }

    /*
    判断用户是否登陆
     */
    public static boolean isExistUserSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session.getAttribute("user") != null;
    }

    /*
    移除用户登陆
     */
    public static void removeUserSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
    }

    /*
    获取登陆人数
     */
    public static Integer getLoginCount(HttpServletRequest request) {
        Object user = request.getServletContext().getAttribute("set");
        HashSet<String> hashMap = new HashSet<String>();
        if (user != null) {
            hashMap = (HashSet<String>) user;
            System.out.println(hashMap);
            System.out.println("在线人数为：" + hashMap.size());
        }
        return hashMap.size();
    }

    /*
    设置cookie的sessionid并返回
     */
    public static Cookie setCookieIsSessionId(String sessionId) {
        Cookie cookie1 = new Cookie("JSESSIONID", sessionId);
        cookie1.setPath("/");
        cookie1.setMaxAge(60 * 60 * 10);
        return cookie1;
    }

    public static void setValBySession(HttpServletRequest request, String key, String val) {
        HttpSession session = request.getSession();
        session.setAttribute(key, val);
    }

    public static String getValBySession(HttpServletRequest request, String key) {
        return (String) request.getSession().getAttribute(key);
    }
}
