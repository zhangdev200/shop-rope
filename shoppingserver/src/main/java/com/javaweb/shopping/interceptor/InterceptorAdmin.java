package com.javaweb.shopping.interceptor;

import com.javaweb.shopping.annotation.AuthAdmin;
import com.javaweb.shopping.entity.User;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InterceptorAdmin implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截器");
        //获得名为user的对象
        Object user = request.getSession().getAttribute("user");
        //判断请求的方法上是否有注解
        boolean haveAnnotataion = handler.getClass().isAssignableFrom(HandlerMethod.class);

        if(haveAnnotataion){
            //如果有注解，判断是否是Auth
            AuthAdmin ma = ((HandlerMethod)handler).getMethodAnnotation(AuthAdmin.class);
            //如果存在该注解
            if(ma != null) {
                //判断用户名是否是admin
                if(!((User)user).isAdmin()){
                    //如果不是转发
                    request.setAttribute("msg","没有权限!");
                    request.getRequestDispatcher("/index").forward(request,response);
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }

}

