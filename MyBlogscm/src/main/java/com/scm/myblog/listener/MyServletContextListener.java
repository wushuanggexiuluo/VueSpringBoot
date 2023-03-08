package com.scm.myblog.listener;

import com.scm.myblog.entity.StatusMes;
import com.scm.myblog.exception.SystemException;
import com.scm.myblog.serviceUtils.RedisServiceBox;
import com.scm.myblog.serviceUtils.UserBlogUtils;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import static com.scm.myblog.serviceUtils.RedisServiceBox.getRedisWriteDBTime;

@Configuration
@WebListener()
public class MyServletContextListener implements ServletContextListener {

    @Override
    //Application被初始化的时候创建
    public void contextInitialized(ServletContextEvent sce) {
        //创建一个Map，key为IP，value为该IP上所发出的会话的对象，此map用于统计在线人数（包括没有登陆的）
        HashMap<String, List<HttpSession>> map = new HashMap<>();
        //创建一个set,存入的值为登陆成功的sessionid,用于统计登陆人数
        HashSet<String> set = new HashSet<>();
        ServletContext sc = sce.getServletContext();
        sc.setAttribute("map", map);
        sc.setAttribute("set", set);
        //初始化Redis持久化方案
        getRedisWriteDBTime(sce);
    }

    /**
     * servlet销毁
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();
        //无论是否设置都需要在服务关闭时写入数据库
        RedisServiceBox.writeDbFromRedis();
        System.out.println("Servlet被销毁了，数据写入数据库");

    }
}
