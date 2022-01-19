package com.xxx.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//登录拦截器配置
public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //return HandlerInterceptor.super.preHandle(request, response, handler);
        //登录成功后，应该有用户的session;
        Object loginUser = request.getSession().getAttribute("loginUser");
        if(loginUser == null){//没有登录
            request.setAttribute("msg","没有权限，请先登录");
            request.getRequestDispatcher("index.html").forward(request,response);
        }else{
            return true;
        }

        return false;
    }
}
