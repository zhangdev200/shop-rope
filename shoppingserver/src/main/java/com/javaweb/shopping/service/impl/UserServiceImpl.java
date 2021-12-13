package com.javaweb.shopping.service.impl;
import com.javaweb.shopping.entity.User;
import com.javaweb.shopping.mapper.UserMapper;
import com.javaweb.shopping.service.UserService;
import com.javaweb.shopping.vo.ResStatus;
import com.javaweb.shopping.vo.ResultVO;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

//用户注册的实现与登入检测的实现
@Service
public class UserServiceImpl implements UserService {
    @Autowired
   private UserMapper userMapper;

    @Transactional
    public ResultVO userResgit(String name, String pwd){
        synchronized (this){ //同步锁
            System.out.println("我运行了");
            //1.根据用户查询，这个用户是否已经被注册
            Example example = new Example(User.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("username", name);
            List<User> users = userMapper.selectByExample(example);

            //2.如果没有被注册则进行保存操作
            if (users.size()==0){
                User user = new User();
                user.setUsername(name);
                user.setUserImg("img/default.png");
                user.setPassword(pwd);
                int i = userMapper.insertUseGeneratedKeys(user);
                if (i > 0) {
                    return new ResultVO(ResStatus.OK, "注册成功！", user);
                } else {
                    return new ResultVO(ResStatus.NO, "注册失败！", null);
                }
            }else {
                    return new ResultVO(ResStatus.NO, "用户名已经被注册！", null);
            }
        }

    }

    //检查登入用户

    public ResultVO checkLogin( String name, String pwd){
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username", name);
        List<User> users = userMapper.selectByExample(example);
        if (users.size()==0){
            return new ResultVO(ResStatus.NO,"登录失败，用户名不存在！",null);
        }else{
            if(pwd.equals(users.get(0).getPassword())){
                //如果登录验证成功，则需要生成令牌token（token就是按照特定规则生成的字符串）
                //使用jwt规则生成token字符串
                JwtBuilder builder = Jwts.builder();

                HashMap<String,Object> map = new HashMap<>();
                map.put("isAdmin",users.get(0).isAdmin());
                map.put("isShopKeeper",users.get(0).isShopKeeper());
                map.put("isVIP",users.get(0).isVIP());

                String token = builder.setSubject(name)                     //主题，就是token中携带的数据
                        .setIssuedAt(new java.util.Date())                            //设置token的生成时间
                        .setId(users.get(0).getUserId() + "")               //设置用户id为token  id
                        .setClaims(map)                                     //map中可以存放用户的角色权限信息
                        .setExpiration(new Date(System.currentTimeMillis() + 24*60*60*1000)) //设置token过期时间
                        .signWith(SignatureAlgorithm.HS256, "xiaofeng")     //设置加密方式和加密密码
                        .compact();

                return new ResultVO(ResStatus.OK,token,users.get(0));
            }else{
                return new ResultVO(ResStatus.NO,"登录失败，密码错误！",null);
            }
        }

    }

    public ResultVO becomeVIP(String name){
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username", name);
        List<User> users = userMapper.selectByExample(example);
        if (users.size()==0){
            return new ResultVO(ResStatus.NO,"用户名不存在！",null);
        }else{
            if(users.get(0).isVIP()){
                return new ResultVO(ResStatus.NO,"用户已经是VIP！",null);
            }
            else{
                users.get(0).setVIP(true);
                Example example2 = new Example(User.class);
                Example.Criteria criteria1 = example2.createCriteria();
                criteria1.andEqualTo("username", name);
                userMapper.updateByExample(users.get(0),example2);
                JwtBuilder builder = Jwts.builder();

                HashMap<String,Object> map = new HashMap<>();
                map.put("isAdmin",users.get(0).isAdmin());
                map.put("isShopKeeper",users.get(0).isShopKeeper());
                map.put("isVIP",users.get(0).isVIP());

                String token = builder.setSubject(name)                     //主题，就是token中携带的数据
                        .setIssuedAt(new java.util.Date())                            //设置token的生成时间
                        .setId(users.get(0).getUserId() + "")               //设置用户id为token  id
                        .setClaims(map)                                     //map中可以存放用户的角色权限信息
                        .setExpiration(new Date(System.currentTimeMillis() + 24*60*60*1000)) //设置token过期时间
                        .signWith(SignatureAlgorithm.HS256, "xiaofeng")     //设置加密方式和加密密码
                        .compact();
                return new ResultVO(ResStatus.OK,token,users.get(0));
            }
        }
    }

}
