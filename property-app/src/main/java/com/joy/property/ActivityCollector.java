package com.joy.property;

/**
 * Created by usb on 2016/7/26.
 */
import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

public class ActivityCollector {

    public static List<Activity> activities=new ArrayList<Activity>();
   public static MainActivity mMainActivity=null;
    public static void addActivity(Activity activity){

        activities.add(activity);

    }

    public static void removeActivity(Activity activity){

        activities.remove(activity);

    }

    public static void finishAll(){

        for(Activity activity:activities){

//            if(activity.isFinishing()){

                activity.finish();
         //   }
        }

    }
}
