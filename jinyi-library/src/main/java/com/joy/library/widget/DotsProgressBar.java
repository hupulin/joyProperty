package com.joy.library.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

import com.joy.library.R;


/**
 * Created by warrior on 15/1/10
 */
public class DotsProgressBar extends View {
    private float mRadius;

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Handler mHandler ;
    private int mIndex = 0;
    private int widthSize, heightSize;
    private int margin = 17;
    private int mDotCount = 3;
    private int mTextSize = 28;
    private String mTitle = "Loading";

    public DotsProgressBar(Context context) {
        super(context);
        init(context);
    }

    public DotsProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
//        init(context);
    }

    public DotsProgressBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        mRadius = context.getResources().getDimension(R.dimen.circle_indicator_radius);
        mPaint.setStyle(Paint.Style.FILL);
        start();
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public void start() {
        mIndex = -1;
        mHandler = new Handler();
        mHandler.post(mRunnable);
    }

    public void stop() {
        if (mHandler == null)
            return;

        mHandler.removeCallbacks(mRunnable);
        mHandler = null;
    }

    private int step = 1;
    private Runnable mRunnable = new Runnable() {

        @Override
        public void run() {
            mIndex += step;
            if (mIndex < 0) {
                mIndex = 1;
                step = 1;
            } else if (mIndex > (mDotCount - 1)) {
//                if ((mDotCount - 2) >= 0) {
//                    mIndex = mDotCount - 2;
//                    step = -1;
//                } else{
//                    mIndex = 0;
//                    step = 1;
//                }
                mIndex = 0;
                step = 1;
            }

            invalidate();

            mHandler.postDelayed(mRunnable, 300);

        }

    };

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        widthSize = MeasureSpec.getSize(widthMeasureSpec);
        heightSize = (int) mRadius * 2 + getPaddingBottom() + getPaddingTop();
        float tmpWidth = mPaint.measureText(mTitle, 0, mTitle.length());
        if(widthSize < tmpWidth) {
            widthSize = (int)tmpWidth;
        }
        mPaint.setTextSize(mTextSize);
        heightSize += mPaint.getTextSize() + margin * 2;
        setMeasuredDimension(widthSize, heightSize);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float dX = (widthSize - mDotCount * mRadius * 2 - (mDotCount - 1) * margin) / 2.0f;
        float dY = margin;

        for (int i = 0; i < mDotCount; i++) {
            if(i == 0) {
                if(i == mIndex) {
                    mPaint.setColor(0xffffa250);
                } else {
                    mPaint.setColor(0xffffe0bf);
                }
            } else if(i == 1) {
                if(i == mIndex) {
                    mPaint.setColor(0xff7eb4fc);
                } else {
                    mPaint.setColor(0xffd5e6f8);
                }
            } else {
                if(i == mIndex) {
                    mPaint.setColor(0xffff6d5a);
                } else {
                    mPaint.setColor(0xffffcec3);
                }
            }
            canvas.save();
            canvas.drawCircle(dX, dY, mRadius, mPaint);
            canvas.restore();

            dX += (2 * mRadius + margin);
        }

        int top = (int)dY + (int)mRadius * 2 + margin * 2 ;

        String pageTitle = "Loading...";
        Rect areaRect = new Rect(0, top, widthSize, heightSize - top);
        RectF bounds = new RectF(areaRect);
        mPaint.setTextSize(mTextSize);

        bounds.right = mPaint.measureText(pageTitle, 0, pageTitle.length());
//        bounds.bottom = mPaint.descent() - mPaint.ascent();

        bounds.left += (areaRect.width() - bounds.right) / 2.0f ;
//        bounds.top += (areaRect.height() - bounds.bottom) / 2.0f;

        mPaint.setColor(0xffbebebe);
        canvas.drawText(pageTitle, bounds.left, top, mPaint);

    }
}
