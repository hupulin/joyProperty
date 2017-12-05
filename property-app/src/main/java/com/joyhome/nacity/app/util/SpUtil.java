package com.joyhome.nacity.app.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by xzz on 2016/1/11.
 */
public class SpUtil {
    public static SharedPreferences getSharedPreferences(Context context) {
        SharedPreferences sp = context.getSharedPreferences("config123456", Context.MODE_PRIVATE);
        return sp;
    }
    public static int getInt(Context context,String key){
        SharedPreferences sp = getSharedPreferences(context);
        int result = sp.getInt(key, 0);
        return result;
    }
    public static int getInt(Context context,String key,int defValue){
        SharedPreferences sp = getSharedPreferences(context);
        int result = sp.getInt(key, defValue);
        return result;
    }
    /**
     * 从首选项中获取String值,默认值为null
     * @param context
     * @param key
     * @return
     */
    public static String getString(Context context,String key){
        if (context!=null) {

            SharedPreferences sp = getSharedPreferences(context);
            return sp.getString(key, "kong");
        }else
            return "";
    }
    /**
     * 从首选项中获取boolean值,默认值为false
     * @param context
     * @param key
     * @return
     */
    public static boolean getBoolean(Context context,String key){
        SharedPreferences sp = getSharedPreferences(context);
        boolean result = sp.getBoolean(key, false);
        return result;
    }
    /**
     * 向首选项中存储数据(仅限于String,int,boolean三种数据类型)
     * @param context
     * @param key
     * @param value
     */
    public static void put(Context context,String key,Object value){
        SharedPreferences sp = getSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();
        if(value instanceof String){
            editor.putString(key, (String) value);
        }else if(value instanceof Integer){
            editor.putInt(key, (Integer) value);
        }else if(value instanceof Boolean){
            editor.putBoolean(key, (Boolean) value);
        }
        editor.commit();
    }
}
