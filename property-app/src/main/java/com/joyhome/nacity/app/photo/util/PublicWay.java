package com.joyhome.nacity.app.photo.util;


import android.app.Activity;

import com.joy.property.myservice.MyServiceOrderActivity;
import com.joy.property.receiver.ForceOfflineActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class PublicWay {
	public final static ArrayList<Activity> activityList = new ArrayList<>();
	public  static Activity goodsDetailActivity=null;
	public static Activity pushJumpActivity=null;
	public static Map<String,Activity> currentActivity=new HashMap<>();
	public static MyServiceOrderActivity myServiceOrderActivity=null;
	public static long currentNetTime;
	public static ForceOfflineActivity forceOfflineActivity=null;
}
