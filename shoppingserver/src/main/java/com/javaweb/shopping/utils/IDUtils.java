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

    private static int id=0;
    private static long time;
    private static String hostCode;//主机编号
    static{
        //读取配置文件
        ResourceBundle conf = ResourceBundle.getBundle("host");
        hostCode=conf.getString("hostCode");

    }

    /**
     * 图片名生成
     */
    public static String genImageName() {
        //取当前时间的长整形值包含毫秒
        long millis = System.currentTimeMillis();
        //long millis = System.nanoTime();
        //加上三位随机数
        Random random = new Random();
        int end3 = random.nextInt(999);
        //如果不足三位前面补0
        String str = millis + String.format("%03d", end3);

        return str;
    }

    /**
     * id生成
     */
    public static long getId() {
        //取当前时间的长整形值包含毫秒
        long millis = System.currentTimeMillis();
        //long millis = System.nanoTime();
        //加上两位随机数
        long end3 = getRand(millis);
        //如果不足两位前面补0
//		String str = millis + String.format("%03d", end3);
//		long id = new Long(str);
        return end3;
    }



    private synchronized static long getRand(long l){
        if(l>time){
            time=l;
            id=0;
        }else{
            id++;
        }

        if(id>99){
            id=0;
            time=time+1;
        }
        String str = time+hostCode + String.format("%02d", id);
        long ids = new Long(str);
        //System.out.println(ids);
        return ids;
    }

}