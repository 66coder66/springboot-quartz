//package com.pbs.config;
//
//import org.springframework.stereotype.Repository;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@Repository
//public class SystemInterceptor extends HandlerInterceptorAdapter {
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        request.setCharacterEncoding("UTF-8");
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("text/html;charset=UTF-8");
//        //请求路径
//        String cpth = request.getContextPath().replaceFirst("/", "");
//        return super.preHandle(request, response, handler);
//    }
//}
