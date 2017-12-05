package com.joy.property.shop.shoputil;

/**
 * Created by xz on 2016/10/10.
 */
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.text.Html;
import android.util.AttributeSet;
import android.widget.TextView;

import com.joy.property.R;

/**
 * 自定义倒计时文本控件
 * @author Administrator
 *
 */
public class TimeTextViewOlder extends TextView implements Runnable{
    Paint mPaint; //画笔,包含了画几何图形、文本等的样式和颜色信息
    private long times;
    private long mday, mhour, mmin, msecond;
    private boolean run=false; //是否启动了
    private long spendTime;

    public TimeTextViewOlder(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint=new Paint();
        TypedArray array = context.obtainStyledAttributes(attrs, com.joy.common.R.styleable.TimeTextView);
        array.recycle(); //一定要调用，否则这次的设定会对下次的使用造成影响
    }
    public TimeTextViewOlder(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mPaint=new Paint();
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TimeTextView);
        array.recycle(); //一定要调用，否则这次的设定会对下次的使用造成影响
    }
    public TimeTextViewOlder(Context context) {
        super(context);
    }
    public long getTimes() {
        return times;
    }
    public void setTimes(long times) {
        this.times = times;
        mday = times/ (1000 * 60 * 60 * 24);
        mhour =  (times % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        mmin = (times % (1000 * 60 * 60)) / (1000 * 60);
        msecond =  (times % (1000 * 60)) / 1000;
        spendTime = mday*24*3600+mhour*3600+mmin*60+msecond;
    }
    /**
     * 倒计时计算
     */
    private void ComputeTime() {
        msecond--;
        if (msecond < 0) {
            mmin--;
            msecond = 59;
            if (mmin < 0) {
                mmin = 59;
                mhour--;
                if (mhour < 0) {
                    // 倒计时结束
                    mhour = 59;
                    mday--;
                }
            }
        }
    }
    public boolean isRun() {
        return run;
    }
    public void setRun(boolean run) {
        this.run = run;
    }
    @Override
    public void run() {
        //标示已经启动
        run=true;
        ComputeTime();
        String strTime="还剩："+mhour+"小时"+mmin+"分钟"+msecond + "秒";
        this.setText(Html.fromHtml(strTime));

   if(spendTime>=2) {
       postDelayed(this, 1000);
       spendTime=spendTime-1;



   }else {
       if (listener!=null)
       listener.OnStopCountTime();
   }
    }
    private StopCountTime listener;
    public void setOnStopCountTimeListener(StopCountTime listener){
        this.listener=listener;
    }
    public interface StopCountTime{
        void OnStopCountTime();
    }
}
