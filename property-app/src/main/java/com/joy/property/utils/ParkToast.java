package com.joy.property.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ActionMenuView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.joy.property.R;

/**
 * Created by xz on 2016/12/28.
 */
public class ParkToast {
    public static void showParkToast(Context context){
        Toast toast=new Toast(context);
        toast.setGravity(Gravity.CENTER,0,0);
        View view=View.inflate(context, R.layout.park_toast,null);

//        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
//        view.setLayoutParams(params);
        toast.setView(view);

        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }
    public static void showHomeToast(Context context){
        Toast toast=new Toast(context);
        toast.setGravity(Gravity.CENTER,0,0);
        View view=View.inflate(context, R.layout.home_toast,null);

//        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
//        view.setLayoutParams(params);
        toast.setView(view);

        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }
    private static int getScreenWidthPixels(Context context) {
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }
}
