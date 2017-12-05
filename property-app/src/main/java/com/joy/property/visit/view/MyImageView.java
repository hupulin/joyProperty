package com.joy.property.visit.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by usb on 2017/8/8.
 */

public class MyImageView extends ImageView {

    private int color;

    public MyImageView(Context context) {
        super(context);
        color = Color.parseColor("#eeeeee");

    }

    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        color = Color.parseColor("#eeeeee");
        // color=Color.parseColor(attrs.getAttributeValue(namespace,
        // "BorderColor"));
    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        // 画边框
        Rect rec = canvas.getClipBounds();
        String TAG = null;
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
        canvas.drawRect(rec, paint);
    }
}