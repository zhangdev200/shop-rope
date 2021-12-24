package com.javaweb.shopping.interceptor;


import com.javaweb.shopping.annotation.AuthShopKeeper;
import com.javaweb.shopping.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//拦截除商家以外的用户
@Component
public class ShopKeeperInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断请求的方法上是否有注解
        boolean haveAuthShopKeeper = handler.getClass().isAssignableFrom(HandlerMethod.class);

        if(haveAuthShopKeeper){
            //如果有注解，判断是否是AuthShopKeeper
            AuthShopKeeper ma = ((HandlerMethod)handler).getMethodAnnotation(AuthShopKeeper.class);
            //如果存在该注解
            if(ma != null) {
                //获得taken信息
                String token = request.getHeader("token");
                if (token != null && !token.isEmpty()) {
                    JwtParser parser = Jwts.parser();
                    parser.setSigningKey("xiaofeng"); //解析token的SigningKey必须和生成token时设置密码一致
                    //如果token正确（密码正确，有效期内）则正常执行，否则抛出异常
                    Jws<Claims> claimsJws = parser.parseClaimsJws(token);
                    Claims body = claimsJws.getBody();
                    //获得名为user的对象
                    Object user = body.get("user");
                    //判断用户是否是商家
                    if (!((User) user).isShopKeeper()) {
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
