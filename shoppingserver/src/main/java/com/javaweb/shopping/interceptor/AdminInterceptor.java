package com.javaweb.shopping.interceptor;

import com.javaweb.shopping.annotation.AuthAdmin;
import com.javaweb.shopping.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

//拦截除管理员以外的用户
@Component
public class AdminInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断请求的方法上是否有注解
        boolean haveAuthAdmin = handler.getClass().isAssignableFrom(HandlerMethod.class);

        if(haveAuthAdmin){
            //如果有注解，判断是否是AuthAdmin
            AuthAdmin ma = ((HandlerMethod)handler).getMethodAnnotation(AuthAdmin.class);
            //如果存在该注解
            if(ma != null) {
                //获得taken信息
                String token = request.getHeader("token");
                if(token != null && !token.isEmpty()) {
                    JwtParser parser = Jwts.parser();
                    parser.setSigningKey("xiaofeng"); //解析token的SigningKey必须和生成token时设置密码一致
                    //如果token正确（密码正确，有效期内）则正常执行，否则抛出异常
                    Jws<Claims> claimsJws = parser.parseClaimsJws(token);
                    Claims body=claimsJws.getBody();
                    //获得管理员身份的判断结果
                    boolean result = (boolean) body.get("isAdmin");
                    //判断用户名是否是admin
                    if (!result) {
                        //如果不是转发
                        request.setAttribute("msg", "没有权限!");
                        request.getRequestDispatcher("").forward(request, response);
                        return false;
                    }
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

