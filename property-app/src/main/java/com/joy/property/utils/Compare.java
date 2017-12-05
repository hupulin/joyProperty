package com.joy.property.utils;

import android.util.Log;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Administrator on 2016/5/21.
 */
public class Compare {
    //    compareTime 比对时间  ：  响应时间 或者 处理时间
//    limit： 比对分值
//    根式如下：
    String createTime;
    String compareTime;
    String upTime;
    String downTime;
    int limit;
    public  void mode(){
        if (compareTime!=null){

        }
    }
    public   boolean VaryRed(String createTime,String compareTime,String upTime,String downTime,int limit)
    {
        boolean isOK=false;      // 是否变红  true=变化  false=不变

        Date createDateTime=StrToDate(createTime);  // 转为时间  单据提交时间

        Date compareDateTime=StrToDate(compareTime);    //dd 比对时间

        // 比对时间如果为空 这使用当前时间
        if (compareDateTime==null)
        {
            compareDateTime=new Date();
        }

        //当前单据的上班时间  下班时间  yyyy-MM-dd HH:mm:ss
        // System.out.println(upTime+"uptime1");
        if (upTime==null||upTime.contains("kong"))
            upTime="08:30";
        upTime= DateToStr(createDateTime).substring(0,10)+" "+upTime+":00";
        if (downTime==null||downTime.contains("kong"))
            downTime="17:30";
        downTime=DateToStr(createDateTime).substring(0,10)+" "+downTime+":00";

        Date todayUp=StrToDate(upTime); // 今天上班时间

        // 求第二天上班时间
        Calendar c = Calendar.getInstance();
        System.out.println(upTime+"uptime1111");
        Log.i("uptime", "uptime2222"+c);
        c.setTime(StrToDate(upTime));
        c.add(Calendar.DATE, 1);
        Date tommorwUp=  c.getTime();   //第二天上班时间
        Date todayDown=StrToDate(downTime); //当天下班时间
        // 提报时间 早于 上班时间
        if (createDateTime.getTime()<todayUp.getTime()) {
            createDateTime=todayUp;
        }
        //判断是否是 非上班时间提交的单据
        System.out.println(createDateTime+"--"+todayDown+"--"+tommorwUp);
        if(createDateTime.getTime()>todayDown.getTime()&&todayDown.getTime()<tommorwUp.getTime())
        {
            createDateTime=tommorwUp; // 单据 提交时间  使用 第二天上班时间
        }
        // 时间比较
        long diff= compareDateTime.getTime()-createDateTime.getTime();  //时间差
        long diffMinutes = diff / (1000 * 60);      // 得到分钟数

        if (diffMinutes>limit)
        {
            isOK=true;
        }
        return isOK;
    }

    /**
     * 日期转换成字符串
     * @param date
     * @return str
     */
    public static String DateToStr(Date date) {
        if (date!=null) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String str = format.format(date);
            return str;
        }else
            return "";
    }
    /**
     * 字符串转换成日期
     * @param str
     * @return date
     */
    public static Date StrToDate(String str) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 字符串转换成日期
     * @param str
     * @return date
     */
    public static Date BookingStrToDate(String str,Date creatTime) {

        // 第一种情况
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        if (str.length()<10)
            str=str+"13:00";
        int index=str.indexOf("周");
        System.out.println(str+"str");
        // 对 2月24日 周三10:30 加空格处理  2月24日 周三 10:30
        if(str.length()>(index+4)&&StringUtils.isNotEmpty(str.substring(index + 2, index + 4)))
        {
            str=str.substring(0,index+2)+" "+str.substring(index+2);
        }

        // 第二种情况    2月24日 周三 10:30  或 2月24日 周三10:30
        if (date==null) {
            // str="2月24日 星期三 10:30";
            str=str.replace("周","星期");
            // 添加年份
            String nowdateStr=DateToStr(creatTime);
            str=nowdateStr.substring(0,4)+"年"+str+":59";
            format = new SimpleDateFormat("yyyy年MM月dd日 EEEE HH:mm:ss",Locale.CHINA);
            try {
                date = format.parse(str);
            } catch (java.text.ParseException e) {
                e.printStackTrace();
            }
        }


        if (date!=null)
        {// 判断跨年
            if(date.getTime()<creatTime.getTime())
            {
                Calendar c = Calendar.getInstance();
                c.setTime(date);
                c.add(Calendar.YEAR, 1);
                date=  c.getTime();   //
            }


        }

        return date;
    }

    public   boolean VaryRed2(Date createTime,Date compareTime,String upTime,String downTime,int limit)
    {
        boolean isOK=false;      // 是否变红  true=变化  false=不变

        //  Date createDateTime=StrToDate(createTime);  // 转为时间  单据提交时间
        Date createDateTime=createTime;

        // Date compareDateTime=StrToDate(compareTime);    // 比对时间
        Date compareDateTime=compareTime;

        // 比对时间如果为空 这使用当前时间
        if (compareDateTime==null)
        {
            compareDateTime=new Date();
        }

        //当前单据的上班时间  下班时间  yyyy-MM-dd HH:mm:ss
        System.out.println(createDateTime+"createTime");
        if (createTime==null)
            createDateTime=new Date();

        System.out.println(upTime+"uptime-----------");

        if (upTime==null||upTime.contains("kong"))
            upTime="08:30";
        upTime= DateToStr(createDateTime).substring(0,10)+" "+upTime+":00";
        if (downTime==null||downTime.contains("kong"))
            downTime="17:30";
        downTime=DateToStr(createDateTime).substring(0,10)+" "+downTime+":00";


        System.out.println(upTime+"uptime***********");
        Date todayUp=StrToDate(upTime); // 今天上班时间

        // 求第二天上班时间
        Calendar c = Calendar.getInstance();
        c.setTime(StrToDate(upTime));
        c.add(Calendar.DATE, 1);
        Date tommorwUp=  c.getTime();   //第二天上班时间

        Date todayDown=StrToDate(downTime); //当天下班时间


        // 提报时间 早于 上班时间
        if (createDateTime.getTime()<todayUp.getTime()) {
            createDateTime=todayUp;
        }

        //判断是否是 非上班时间提交的单据
        if(createDateTime.getTime()>todayDown.getTime()&&todayDown.getTime()<tommorwUp.getTime())
        {
            createDateTime=tommorwUp; // 单据 提交时间  使用 第二天上班时间
        }



        // 时间比较
        long diff= compareDateTime.getTime()-createDateTime.getTime();  //时间差
        long diffMinutes = diff / (1000 * 60);      // 得到分钟数

        if (diffMinutes>limit)
        {
            isOK=true;
        }


        return isOK;

    }

}
