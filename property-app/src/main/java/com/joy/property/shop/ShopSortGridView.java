package com.joy.property.shop;

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

import com.jinyi.ihome.module.newshop.ShopTypeTo;
import com.joy.property.R;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xzz on 2016/3/2.
 **/
public class ShopSortGridView extends GridLayout{
    private  int width=0;
    private Context context;
    private  List<ShopTypeTo> recordInfo=new ArrayList<>();
    public  interface OnItemClickListener{
        void onItemClick(View v, String typeSid);
    }

    OnItemClickListener onItemClickListener;


    public void setOnItemClickListener(OnItemClickListener dsglOicl) {
        this.onItemClickListener = dsglOicl;
    }
    public ShopSortGridView(Context context) {
        this(context, null);
    }

    public ShopSortGridView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        this.context=context;
        init();
    }

    private void init() {
        setColumnCount(5);

        LayoutTransition transition = new LayoutTransition();
        transition.setDuration(100);
        setLayoutTransition(transition);
    }

    public void setItems(List<ShopTypeTo>items) {
        removeAllViews();
        // 往gridlayout中添加一些textview，
        recordInfo.clear();




        for (int i=0;i<items.size(); i++) {

           addItem(items.get(i).getTypeIcon(),items.get(i).getTypeName(),items.get(i).getTypeSid());
        }

        recordInfo.addAll(items);
    }

    public List<String> getItems(){
        // 遍历所有的textView，获得上面的文字，返回出去
        List<String> result = new ArrayList<>();
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            result.add((getChildAt(i)).getTag().toString());
        }
        return result;
    }

    public void addItem(String url,String text,String tag) {
        View tv = newItem(url, text, tag);


        tv.setOnClickListener(ocl);
        addView(tv);
    }


    OnClickListener ocl = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if(onItemClickListener !=null){
                for(int i=0;i<getChildCount();i++)
                    onItemClickListener.onItemClick(v,recordInfo.get(i).getTypeSid());
            }

        }
    };



    private View newItem(String url, String text, String tag) {
        View view;
        view=LinearLayout.inflate(getContext(), R.layout.shop_sort_item, null);
        ImageView sortIcon = (ImageView) view.findViewById(R.id.sort_image);
        TextView sortName = (TextView) view.findViewById(R.id.sort_name);
        if(!TextUtils.isEmpty(url))
            Picasso.with(context).load(url).into(sortIcon);
        sortName.setText(text);
        WindowManager wm = (WindowManager)context.getSystemService(context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        int mWidth=display.getWidth();
        int margin =(int)(mWidth*0.0472);
        width =(int)(mWidth*0.1061);
        LayoutParams lp = new LayoutParams();
        lp.height = LayoutParams.WRAP_CONTENT;
        lp.width =(int)(mWidth*0.1861);
        lp.setMargins(0, 0, 0, margin);
        view.setLayoutParams(lp);
        view.setTag(tag);


        return view;
    }








}



