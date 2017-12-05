package com.joy.property.neighborhood;

import android.animation.LayoutTransition;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.DragEvent;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DragSortGridLayout extends GridLayout {

	private boolean allow;

	/*
	 * 方法： 指定显示的数据： setItems(List<String>)
	 * 
	 * 方法： 允许拖拽排序 setAllowDrag(boolean )
	 * 
	 * 机制：通知外界，这里面的孩子被点击了，怎么办让外界来处理
	 */
	// A 定义了接口的类
	// 1 定义一个接口
	// 2 对外提供方法，可以把实现了接口的对象设置进来
	// 3在合适的时机，调用一下接口对象上的方法
	// B 实现了接口的类
	// 1实现接口
	// 2把接口对象传递个A类
	
	public static interface OnItemClickListener{
		public void onItemClick(View v, String text);
	}
	
	OnItemClickListener dsglOicl;
	

	public void setOnItemClickListener(OnItemClickListener dsglOicl) {
		this.dsglOicl = dsglOicl;
	}

	public DragSortGridLayout(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	public DragSortGridLayout(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public DragSortGridLayout(Context context) {
		this(context, null);
	}

	private void init() {
		setColumnCount(4);
		LayoutTransition transition = new LayoutTransition();
		transition.setDuration(100);
		setLayoutTransition(transition);
	}

	public void setItems(List<Bitmap> items) {
		removeAllViews();
		// 往gridlayout中添加一些textview，
		for (Bitmap text : items) {
			addItem(text);
		}
	}

	public List<String> getItems(){
		// 遍历所有的textview，获得上面的文字，返回出去
		List<String> result = new ArrayList<String>();
		int childCount = getChildCount();
		for (int i = 0; i < childCount; i++) {
			result.add(((TextView)getChildAt(i)).getText().toString());
		}
		return result;
	}
	
	public void addItem(Bitmap text) {
		ImageView tv = newTv(text);
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
				dsglOicl.onItemClick( v, ((TextView)v).getText().toString());
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

	private ImageView newTv(Bitmap text) {
		ImageView result = new ImageView(getContext());
		// 设置文字居中
		result.setImageBitmap(text);

		// 设置背景


		int margin = 5;
		// 指定宽高
		// 宽度是屏幕的四分之一
		int width = getResources().getDisplayMetrics().widthPixels / 4 - 2
				* margin;
		LayoutParams lp = new LayoutParams();
		lp.width = width;
		lp.height = LayoutParams.WRAP_CONTENT;

		lp.setMargins(margin, margin, margin, margin);
		// 关联布局参数到tv上
		result.setLayoutParams(lp);

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
	static SparseArray<String> dragEventType = new SparseArray<String>();
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
}
