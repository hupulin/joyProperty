package com.joyhome.nacity.app.photo.util;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.DragEvent;
import android.view.View;
import android.widget.GridView;

public class NoScrollGridView extends GridView {
	public NoScrollGridView(Context context) {
		super(context);
	}

	public NoScrollGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
setOnDragListener(odl);


	}

	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);

	}

	private View dragedView;

	private View.OnLongClickListener olcl = new View.OnLongClickListener() {

		@Override
		public boolean onLongClick(View v) {
			dragedView = v;
			// 第二个参数在构造方法中传入的view，可以变成影子，其实就是截图、快照(在startDrag之后，view怎么变化，都不影响影子)
			v.startDrag(null, new View.DragShadowBuilder(v), null, 0);
			// v.setEnabled(false);

			return false;
		}
	};






	private View.OnDragListener odl = new View.OnDragListener() {
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
