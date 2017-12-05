package com.joy.property.views;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by usb on 2017/6/30.
 */

public class MyScrollViewMain extends ScrollView {
    private OnScrollListener onScrollListener;
    private OnScrollUpListener onScrollUpListener;
    /**
     * 主要是用在用户手指离开MyScrollView，MyScrollView还在继续滑动，我们用来保存Y的距离，然后做比较
     */
    private int lastScrollY;

    public MyScrollViewMain(Context context) {
        this(context, null);
    }

    public MyScrollViewMain(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyScrollViewMain(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }



    /**
     * 用于用户手指离开MyScrollView的时候获取MyScrollView滚动的Y距离，然后回调给onScroll方法中
     */
    private Handler handler = new Handler() {

        public void handleMessage(Message msg) {
            int scrollY = MyScrollViewMain.this.getScrollY();
            switch (msg.what) {
                //0抬起的时候
                case 0:
                        if(onScrollUpListener != null){
                            onScrollUpListener.onScrollUp();
                        }
                    break;
                //1移动的时候

                case 1:
            if(lastScrollY != scrollY){
                lastScrollY = scrollY;
                    if(onScrollListener != null){
                        onScrollListener.onScroll(scrollY);
                    }
            }

                    break;

                default:
                    break;
            }
            //此时的距离和记录下的距离不相等，在隔5毫秒给handler发送消息

        }

    };

    /**
     * 重写onTouchEvent， 当用户的手在MyScrollView上面的时候，
     * 直接将MyScrollView滑动的Y方向距离回调给onScroll方法中，当用户抬起手的时候，
     * MyScrollView可能还在滑动，所以当用户抬起手我们隔5毫秒给handler发送消息，在handler处理
     * MyScrollView滑动的距离
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Message msg = new Message();
        int scrollY = MyScrollViewMain.this.getScrollY();
        switch(ev.getAction()){
            case MotionEvent.ACTION_UP:
                msg.what = 0;//可以是基本类型，可以是对象，可以是List、map等；
//                handler.sendMessageDelayed(handler.obtainMessage(), 5);
                handler.sendMessage(msg);
                break;
            case MotionEvent.ACTION_MOVE:  //
                msg.what = 1;//可以是基本类型，可以是对象，可以是List、map等；
//                msg.obj = scrollY;
                handler.sendMessageDelayed(msg, 5);
                break;
        }
        return super.onTouchEvent(ev);
    }


    /**
     *
     * 滚动的回调接口
     *
     * @author xiaanming
     *
     */
    public interface OnScrollListener{
        /**
         * 回调方法， 返回MyScrollView滑动的Y方向距离
         * @param scrollY
         */
        public void onScroll(int scrollY);
    }
    /**
     * 手抬起的接口
     * @author xiaanming
     *
     */
    public interface OnScrollUpListener{
        public void onScrollUp();
    }

    /**
     *  设置手抬起的接口
     * @param onScrollUpListener
     */
    public void setOnScrollUpListener(OnScrollUpListener onScrollUpListener) {
        this.onScrollUpListener = onScrollUpListener;
    }
    /**
     * 设置滚动接口
     * @param onScrollListener
     */
    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;
    }
}