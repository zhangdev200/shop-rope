package com.javaweb.shopping.utils;



import java.util.HashSet;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.UUID;


/**
 *
 *类名称：各种id生成策略
 *类描述：
 *
 *编 写 人：kxw
 *编写日期：2016-8-29
 *
 *修改人：
 *修改日期：
 *修改内容：
 *
 *版本：1.0
 */
public class IDUtils {
    private static byte[] lock = new byte[0];

    // 位数，默认是8位
    private final static long w = 10000;

    public static String getId() {
        long r = 0;
        synchronized (lock) {
            r = (long) ((Math.random() + 1) * w);
        }

        return System.currentTimeMillis()%10000 + String.valueOf(r).substring(1);
    }


}