//package com.scm.myblog.listener;
//
//import org.springframework.context.annotation.Configuration;
//
//import javax.servlet.ServletContext;
//import javax.servlet.annotation.WebListener;
//import javax.servlet.http.*;
//import java.util.List;
//import java.util.Map;
//@Configuration
//@WebListener()
//public class MySessionListener implements HttpSessionListener{
//
//    private ServletContext sc;
//    private HttpSession currentSession;
//    private String clientIp;
//    private Map<String,List<HttpSession>> map;
//    private List<HttpSession> sessions;
//
//    @Override
//    public void sessionDestroyed(HttpSessionEvent se) {
//        sc = se.getSession().getServletContext();
//        currentSession = se.getSession();
//        clientIp = (String) currentSession.getAttribute("clientIp");
//        map = (Map<String, List<HttpSession>>) sc.getAttribute("map");
//        //从Map中获取List
//        sessions = map.get(clientIp);
//        //从List中删除当前Session对象
//        try{
//            sessions.remove(currentSession);
//        }catch (Exception e) {
//            log.info("用户异常！");
//        }
//        if(sessions != null){
//            //如果List中没有该元素，则说明当前IP所发出的会话全部关闭，就可以从map中
//            //将当前IP对应的Entry对象删除
//            //若List中仍有元素，当前IP所发出的会话任存在，那么将变化过的List写回到map
//            if (sessions.size() == 0){
//                map.remove(clientIp);
//            }else {
//                map.put(clientIp,sessions);
//            }
//            sc.setAttribute("map",map);
//        }
//
//    }
//}