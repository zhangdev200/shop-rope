package com.javaweb.shopping.utils;

import com.javaweb.shopping.vo.ResStatus;
import com.javaweb.shopping.vo.ResultVO;
import io.jsonwebtoken.*;

public class TokenUtil {
    //从token中获取某个属性
    public static Object getAttribute(String token,String attribute){
        try {
            JwtParser parser = Jwts.parser();
            parser.setSigningKey("xiaofeng"); //解析token的SigningKey必须和生成token时设置密码一致
            //如果token正确（密码正确，有效期内）则正常执行，否则抛出异常
            Jws<Claims> claimsJws = parser.parseClaimsJws(token);
            Claims body = claimsJws.getBody();
            String subject = body.getSubject();
            return body.get(attribute);
        }catch (ExpiredJwtException e){
            ResultVO resultVO = new ResultVO(ResStatus.LOGIN_FAIL_OVERDUE, "登录过期，请重新登录！", null);
            return "登录过期，请重新登录！";
        }catch (UnsupportedJwtException e){
            ResultVO resultVO = new ResultVO(ResStatus.LOGIN_FAIL_NOT, "Token不合法，请自重！", null);
            return "Token不合法，请自重！";
        }catch (Exception e){
            return "请先登录！";
        }
    }
    public static String getUsername(String token){
        return (String)getAttribute(token,"username");
    }
    public static String getUserId(String token){
        return (String)getAttribute(token,"userId");
    }
}
