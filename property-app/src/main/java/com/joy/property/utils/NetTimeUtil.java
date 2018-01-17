package com.joy.property.utils;

import android.os.Handler;
import android.os.SystemClock;

import com.joy.library.utils.DateUtil;
import com.joyhome.nacity.app.photo.util.PublicWay;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by xzz on 2017/7/30.
 */
public class NetTimeUtil {
   private static long currentTimeLong=0;
    private static long netTime;
    private Handler handler=new Handler();

    public static long getNetTimeLong(){

         currentTimeLong=System.currentTimeMillis();
        new Thread(() -> {
            try {
                currentTimeLong = getNetTime();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        Date date = new Date(currentTimeLong);// 转换为标准时间对象
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);// 输出北京时间
        System.out.println( sdf.format(date)+"date--------");
        return currentTimeLong;
    }



    public static long getNetTime() throws IOException {
        long currentTime=System.currentTimeMillis();
        try {
            URL url = new URL("http://www.ntsc.ac.cn/");// 取得资源对象
            URLConnection uc = url.openConnection();// 生成连接对象

            uc.connect();// 发出连接
            long ld = uc.getDate();// 读取网站日期时间
            PublicWay.currentNetTime=ld;
            return ld;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return currentTime;
    }

    public static void initNetTime()  {


        new Thread(() -> {


        try {
            URL url = new URL("http://www.ntsc.ac.cn/");// 取得资源对象
            URLConnection uc = url.openConnection();// 生成连接对象

            uc.connect();// 发出连接
            long ld = uc.getDate();// 读取网站日期时间
            PublicWay.currentNetTime=ld;
            netTime=ld;
        } catch (IOException e) {
            e.printStackTrace();
        }
        }).start();
    }
    public static   long getSignNetTime(){


        return netTime;
    }

    public static void setSignNetTime() {
        netTime=System.currentTimeMillis();
    }
}
