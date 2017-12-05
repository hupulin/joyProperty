package com.joy.property.visit.view;

import android.animation.LayoutTransition;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jinyi.ihome.module.home.ServiceTypeTo;
import com.joy.property.R;
import com.joyhome.nacity.app.MainApp;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by usb on 2017/8/4.
 */

public class ExpressGridView extends GridLayout {
    private Context context;
    private List<String> names =new ArrayList<>();
    private  int width=0;
    private View expandView;

    public ExpressGridView(Context context) {
        this(context,null);
    }

    public ExpressGridView(Context context, AttributeSet attrs) {
        super(context, attrs,0);
        this.context=context;
        init();
    }
    private void init() {
        setColumnCount(3);
//        LayoutTransition transition = new LayoutTransition();
//        transition.setDuration(100);
//        setLayoutTransition(transition);
    }
    //点击事件
    OnClickListener ocl = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if(dsglOicl !=null){
                for(int i=0;i<getChildCount();i++)
                    if(v==getChildAt(i)) {
                        if(i>=8&&getChildCount()>9){
                            i++;
                        }
                        if(!names.get(i).equals("更  多")&&!names.get(i).equals("收  起"))
                        {
                            dsglOicl.onItemClick(v, names.get(i));
                        }else {
                            if(names.get(i).equals("更  多"))
                                dsglOicl.onItemClick(v,"更  多");
                            else if(names.get(i).equals("收  起"))
                                dsglOicl.onItemClick(v,"收  起");
                        }
                    }
            }

        }
    };
    public void setItems(List<String>items) {
        removeAllViews();
        // 往gridlayout中添加一些textview，
        names.clear();
        if(items.size()>8){

            items.add(8,"更  多");

            items.add(items.size(),"收  起");
        }

        for (int i=0;i<(items.size()<9?items.size():9); i++) {

            addItem(items.get(i));
        }

        names.addAll(items);
    }
    public  interface OnItemClickListener{
        void onItemClick(View v, String text);
    }

    OnItemClickListener dsglOicl;


    public void setOnItemClickListener(OnItemClickListener dsglOicl) {
        this.dsglOicl = dsglOicl;
    }
    public void addItem(String text) {
        View tv = newTv(text);
        addView(tv);
        tv.setOnClickListener(ocl);

    }
    private View newTv(String text) {
        View result;
        result= LinearLayout.inflate(getContext(), R.layout.tv_express, null);
        TextView tv = (TextView) result.findViewById(R.id.textView);
        // 设置文字居中
        result.setTag(text);
            tv.setText(text);
//        // 指定宽高
//        // 宽度是屏幕的四分之一
        WindowManager wm = (WindowManager)context.getSystemService(context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        int mWidth=display.getWidth();
       int margin =(int)(mWidth*0.019444444);
        width = (mWidth-14*margin)/3;
        LayoutParams lp = new LayoutParams();
        lp.width = LayoutParams.WRAP_CONTENT;
//        lp.height =(int)(margin*3.42857143);
        lp.height = LayoutParams.WRAP_CONTENT;
        int margintop =(int)(margin*0.714428571);

        lp.setMargins(margin, margintop, margin, margintop);
        result.setLayoutParams(lp);
        return result;
    }
    public void setVisible(){

        //  removeView(getChildAt(5));
        expandView=getChildAt(8);
        TextView tv = (TextView) expandView.findViewById(R.id.textView);
        String text=names.get(9);

            tv.setText(text);
        for (int i=9;i<names.size()-1;i++){
            View view=newTv(names.get(i+1));
            view.setOnClickListener(ocl);
            addView(view);
        }

    }
    public void setInVisible(){
        expandView=getChildAt(8);
        TextView tv = (TextView) expandView.findViewById(R.id.textView);
        tv.setText(names.get(8));
        for(int i=names.size()-1;i>=9;i--){
            removeView(getChildAt(i));
        }

    }

}
