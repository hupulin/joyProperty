package com.joy.property.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/5/11.
 */
public class CustomView extends View{

    private Paint paint;
    private float agr;

    public CustomView(Context context) {
        super(context);
        init();

    }

    private void init() {
        paint=new Paint();
        //设置画笔属性
        //

    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        if (agr>0&&agr<60){
//            canvas.drawArc(paint);
//        }else if (agr>60&&agr<180){
//            canvas.drawArc(paint);
//        }
    }
}
