//package com.scm.myblog.filter;
//
//import com.scm.myblog.config.securityconfig.ApiConfig;
//import com.scm.myblog.utils.WebUtils;
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//@WebFilter("/*")
//public class SessionFilter implements Filter {
//
//    //不需要过滤器就能访问到的路径
//    String[] noFilter = ApiConfig.NoAuthApi;
//
//    //判断需不需要过滤
//    private boolean isFliter(String str) {
//        for (String x : noFilter) {
//            if (str.contains(x)) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        Filter.super.init(filterConfig);
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//        String uri = request.getRequestURI();
//        log.info("需要过滤的链接为：" + uri);
//        boolean b = isFliter(uri);
//        log.info("是否需要过滤："+b);
//        if (b)
//        {
//            boolean isExistUser=WebUtils.isExistUserSession(request);
//            if(isExistUser)
//            {
//                log.info("此用户已登陆!");
//            }
//            else {
//                log.info("此用户未登录！");
//            }
//        }
//        filterChain.doFilter(request, response);
//    }
//
//    @Override
//    public void destroy() {
//        Filter.super.destroy();
//    }
//}
