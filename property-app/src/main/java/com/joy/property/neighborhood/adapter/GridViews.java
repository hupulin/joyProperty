package com.joy.property.neighborhood.adapter;

import android.animation.LayoutTransition;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.Display;
import android.view.DragEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jinyi.ihome.module.neighbor.NeighborPostTypeTo;

import com.joy.property.R;
import com.joyhome.nacity.app.MainApp;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xzz on 2016/3/2.
 */
public class GridViews extends GridLayout{
    private boolean allow;
    private boolean isFour=false;
    private  int width=0;
    private String typeSid;
    List<NeighborPostTypeTo>recordItems;
    Context context;
    public static interface OnItemClickListener{
        public void onItemClick(View v, String text, String name);
    }

    OnItemClickListener dsglOicl;


    public void setOnItemClickListener(OnItemClickListener dsglOicl) {
        this.dsglOicl = dsglOicl;
    }
    public GridViews(Context context) {
        this(context,null);
    }

    public GridViews(Context context, AttributeSet attrs) {
        super(context, attrs,0);
        this.context=context;
        init();
    }

    private void init() {
        setColumnCount(3);

        LayoutTransition transition = new LayoutTransition();
        transition.setDuration(100);
        setLayoutTransition(transition);
    }

    public void setItems(List<NeighborPostTypeTo>items) {
        removeAllViews();
        recordItems=new ArrayList<>();
        recordItems.clear();
        recordItems.addAll(items);
        // 往gridlayout中添加一些textview，

        if(items.size()==4)
        {
            setColumnCount(2);
            isFour=true;


        }
        for (NeighborPostTypeTo item:items) {
            addItem(item.getTypeImage(),item.getTypeName(),item.getTypeSid());
        }
    }

    public List<String> getItems(){
        // 遍历所有的textview，获得上面的文字，返回出去
        List<String> result = new ArrayList<>();
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            result.add((getChildAt(i)).getTag().toString());
        }
        return result;
    }

    public void addItem(String url,String text,String tag) {
        View tv = newTv(url,text,tag);
        if (allow) {
            tv.setOnLongClickListener(olcl);
        } else {
            tv.setOnLongClickListener(null);
        }
        tv.setOnClickListener(ocl);
        addView(tv);
    }


    OnClickListener ocl = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if(dsglOicl !=null){
                String typeName;
                for(int i=0;i<getChildCount();i++) {
                    if (v == getChildAt(i)) {
                        typeSid = recordItems.get(i).getTypeSid();
                        typeName=recordItems.get(i).getTypeName();
                        dsglOicl.onItemClick(v, typeSid, typeName);
                    }
                }
            }

        }
    };


    public void setAllowDrag(boolean allow) {
        this.allow = allow;
        if (allow) {
            setOnDragListener(odl);
        } else {
            setOnDragListener(null);
        }
    }

    private View newTv(String url,String text,String tag) {
        View result = new View(getContext());
        result=LinearLayout.inflate(getContext(), R.layout.neighbor_post_item, null);
        TextView tv = (TextView) result.findViewById(R.id.tv);
        ImageView iv = (ImageView) result.findViewById(R.id.code_register);
        // 设置文字居中
result.setTag(tag);
        // 设置文字大小

        // 设置背景
     //  result.setBackgroundResource(R.drawable.dsgl_tv_bg_selector);
tv.setText(text);
     int margin = 0;
//        // 指定宽高
//        // 宽度是屏幕的四分之一

        WindowManager wm = (WindowManager)context.getSystemService(context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        int mWidth=display.getWidth();
        width = (int)(mWidth/9*2);
iv.setMaxWidth(width);
        iv.setMinimumWidth(width);
        iv.setMinimumHeight(width);
        iv.setMaxHeight(width);
    //    tv.setTextSize((float) (mWidth* 0.0236));
        if(isFour){
           margin=(int)((mWidth-2*width)/3);

        }else {
            margin=(int)((mWidth-3*width)/4);
        }
        LayoutParams lp = new LayoutParams();
      lp.width = width;
     lp.height =LayoutParams.WRAP_CONTENT;

  lp.setMargins(margin, 25, 0, 0);
//        // 关联布局参数到tv上
        result.setLayoutParams(lp);
        result.setTag(tag);
        if(url!=null)
        Picasso.with(getContext()).load(MainApp.getImagePath(url)).into(iv);
        return result;
    }

    private View dragedView;
    private OnLongClickListener olcl = new OnLongClickListener() {

        @Override
        public boolean onLongClick(View v) {
            dragedView = v;
            // 第二个参数在构造方法中传入的view，可以变成影子，其实就是截图、快照(在startDrag之后，view怎么变化，都不影响影子)
            v.startDrag(null, new DragShadowBuilder(v), null, 0);
            // v.setEnabled(false);

            return false;
        }
    };



    private OnDragListener odl = new OnDragListener() {
        // 如果有view发送startDrag了，就会在这里收到DragEvent
        // v 设置了监听的view
        // event 拖拽事件
        // 返回值表示对拖拽事件感兴趣，之后返回true才后有后续的事件
        @Override
        public boolean onDrag(View v, DragEvent event) {
            // Log.d("onDrag", getDragEventAction(event));
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    initRects();
                    if (dragedView != null) {
                        dragedView.setEnabled(false);
                    }
                    break;
                case DragEvent.ACTION_DRAG_LOCATION:
                    int index = findTouchIndex((int) event.getX(),
                            (int) event.getY());
                    if (index >= 0 && dragedView != null
                            && getChildAt(index) != dragedView) {
                        removeView(dragedView);
                        addView(dragedView, index);
                    }
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    if (dragedView != null) {
                        dragedView.setEnabled(true);
                    }
                    break;

            }

            return true;
        }

        Rect[] rects;

        protected void initRects() {
            int childCount = getChildCount();
            rects = new Rect[childCount];
            for (int i = 0; i < childCount; i++) {
                View child = getChildAt(i);
                rects[i] = new Rect(child.getLeft(), child.getTop(),
                        child.getRight(), child.getBottom());
            }
        }

        private int findTouchIndex(int x, int y) {
            for (int i = 0; i < rects.length; i++) {
                if (rects[i].contains(x, y)) {
                    return i;
                }
            }
            return -1;
        }
    };

    // SparseArray<String> 相当于 HashMap<Integer,String> 但更高效、谷歌官方推荐
    static SparseArray<String> dragEventType = new SparseArray<>();
    static {
        dragEventType.put(DragEvent.ACTION_DRAG_STARTED, "STARTED");
        dragEventType.put(DragEvent.ACTION_DRAG_ENDED, "ENDED");
        dragEventType.put(DragEvent.ACTION_DRAG_ENTERED, "ENTERED");
        dragEventType.put(DragEvent.ACTION_DRAG_EXITED, "EXITED");
        dragEventType.put(DragEvent.ACTION_DRAG_LOCATION, "LOCATION");
        dragEventType.put(DragEvent.ACTION_DROP, "DROP");
    }


}



