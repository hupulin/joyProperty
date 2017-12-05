package com.joy.property;

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

import com.jinyi.ihome.module.house.AllHouseMenuTo;
import com.joyhome.nacity.app.MainApp;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xzz on 2016/3/2.
 */
public class SquareGridView extends GridLayout{
    private boolean allow;
    private boolean isFour=false;
    private  int width=0;
    Context context;
    private List<AllHouseMenuTo> recordInfo=new ArrayList<>();
    public  interface OnItemClickListener{
         void onItemClick(View v, String text);
    }

    OnItemClickListener dsglOicl;


    public void setOnItemClickListener(OnItemClickListener dsglOicl) {
        this.dsglOicl = dsglOicl;
    }
    public SquareGridView(Context context) {
        this(context,null);
    }

    public SquareGridView(Context context, AttributeSet attrs) {
        super(context, attrs,0);
        this.context=context;
        init();
    }

    private void init() {
        setColumnCount(4);

        LayoutTransition transition = new LayoutTransition();
        transition.setDuration(100);
        setLayoutTransition(transition);
    }

    public void setItems(List<AllHouseMenuTo>items) {
        removeAllViews();
        // 往gridlayout中添加一些textview，
        recordInfo.clear();
        recordInfo.addAll(items);
//         int count=1;
//        String url=null;
       for (AllHouseMenuTo item:items) {
//            if(item.getIcon()==null)
//            {
//
//            }
//            else
//            url=item.getIcon();
            addItem(item.getIcon(),item.getName(),item.getSid());

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
        tv.setTag(text);
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
                for(int i=0;i<getChildCount();i++)
                    if(v==getChildAt(i))
                dsglOicl.onItemClick( v, recordInfo.get(i).getCode());
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
        View result;
        result=LinearLayout.inflate(getContext(), R.layout.square_dragview_item, null);
        TextView tv = (TextView) result.findViewById(R.id.tv);
        ImageView iv = (ImageView) result.findViewById(R.id.iv);

        result.setTag(tag);

         tv.setText(text);



        WindowManager wm = (WindowManager)context.getSystemService(context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        int mWidth=display.getWidth();
        int margin =(int)(mWidth*0.0069444);

        width = (mWidth-3*margin)/4;



        LayoutParams lp = new LayoutParams();
        lp.width = width;
        lp.height =width;
        lp.setMargins(0, 0, margin, margin);
        result.setLayoutParams(lp);
        result.setTag(tag);
       Picasso.with(getContext()).load(MainApp.getImagePath(url)).placeholder(R.drawable.neighbor_dailog_zhixunwuye).error(R.drawable.neighbor_dailog_zhixunwuye).into(iv);
//        ImageLoader.getInstance().init(ImageLoadleUtil.config(getContext()));
//        ImageLoader loader=ImageLoader.getInstance();
//        loader.displayImage(MainApp.getImagePath(url+".png"),iv,getImageloaderOption());


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

    public static String getDragEventAction(DragEvent de) {
        return dragEventType.get(de.getAction());
    }
    public  static DisplayImageOptions getImageloaderOption(){
        DisplayImageOptions   options = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.drawable.neighbor_dailog_zhixunwuye)  // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.drawable.neighbor_dailog_zhixunwuye)       // 设置图片加载或解码过程中发生错误显示的图片
                .cacheInMemory(false)                        // 设置下载的图片是否缓存在内存中
                .cacheOnDisc(false)                          // 设置下载的图片是否缓存在SD卡中
                .displayer(new RoundedBitmapDisplayer(0))  // 设置成圆角图片
                .build();
        return  options;
    }
}



