package com.joy.property.task;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.joy.property.R;


/**
 * 
 * @author Mr.Z
 */
public class SideBar extends View {
	// 触摸事件
	private OnTouchingLetterChangedListener	onTouchingLetterChangedListener;
	// 26个字母
	public static String[]					b		= { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
	private int								choose	= -1;																																			// 选中
	private Paint							paint	= new Paint();

	private TextView						mTextDialog;
	public boolean isDismiss;
	public boolean canHaveAnimation=true;
	public long firstTime;

	public android.os.Handler mHandler = new android.os.Handler() {};
	public void setTextView(TextView mTextDialog) {
		this.mTextDialog = mTextDialog;
	}

	public SideBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public SideBar(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public SideBar(Context context) {
		super(context);
	}

	/**
	 * 重写这个方法
	 */
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		// 获取焦点改变背景颜色.
		int height = getHeight();// 获取对应高度
		int width = getWidth(); // 获取对应宽度
		int singleHeight = height / b.length;// 获取每一个字母的高度

		for (int i = 0; i < b.length; i++) {
			paint.setColor(Color.parseColor("#ffffff"));
			paint.setTypeface(Typeface.DEFAULT);
			paint.setAntiAlias(true);
			paint.setTextSize(32);
			// 选中的状态
			if(i == choose) {
				//点击之后的颜色
				paint.setColor(Color.parseColor("#ffffff"));
				paint.setFakeBoldText(true);
			}
			// x坐标等于中间-字符串宽度的一半.
			float xPos = width / 2 - paint.measureText(b[i]) / 2;
			float yPos = singleHeight * i + singleHeight;
			canvas.drawText(b[i], xPos, yPos, paint);
			paint.reset();// 重置画笔
		}
	}

	@Override
	public boolean dispatchTouchEvent(@NonNull MotionEvent event) {
		final int action = event.getAction();
		final float y = event.getY();// 点击y坐标
		final int oldChoose = choose;
		final OnTouchingLetterChangedListener listener = onTouchingLetterChangedListener;
		final int c = (int) (y / getHeight() * b.length);// 点击y坐标所占总高度的比例*b数组的长度就等于点击b中的个数.

		switch (action) {
			case MotionEvent.ACTION_UP:// 抬起
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
					setBackground(getResources().getDrawable(R.drawable.sidebar_bg_icon));
				}else {
					setBackgroundDrawable(getResources().getDrawable(R.drawable.sidebar_bg_icon));
				}

				choose = -1;//
				invalidate();
				if(mTextDialog != null) {
					mTextDialog.setVisibility(View.INVISIBLE);
				}
				canHaveAnimation=false;
				mHandler.postDelayed(() -> {
					if (!isDismiss) {

						if (System.currentTimeMillis()-firstTime>=2000) {
							setSideBarOut();
						canHaveAnimation = false;
						}
					}
				},2000);
				break;

			case MotionEvent.ACTION_DOWN:

				if ((canHaveAnimation&&System.currentTimeMillis()-firstTime>=2800)||isDismiss) {
					setSideBarIn();
				canHaveAnimation=false;

				}
				firstTime = System.currentTimeMillis();

				break;

			default:
				setBackgroundDrawable(getResources().getDrawable(R.drawable.sidebar_bg_icon));
				if(oldChoose != c) {
					if(c >= 0 && c < b.length) {
						if(listener != null) {
							listener.onTouchingLetterChanged(b[c]);
						}
						if(mTextDialog != null) {
							mTextDialog.setText(b[c]);
							mTextDialog.setVisibility(View.VISIBLE);
						}

						choose = c;
						invalidate();
					}
				}

				break;
		}
		return true;
	}

	/**
	 * 向外公开的方法
	 * 
	 * @param onTouchingLetterChangedListener
	 */
	public void setOnTouchingLetterChangedListener(OnTouchingLetterChangedListener onTouchingLetterChangedListener) {
		this.onTouchingLetterChangedListener = onTouchingLetterChangedListener;
	}

	/**
	 * 接口
	 * 
	 * @author coder
	 * 
	 */
	public interface OnTouchingLetterChangedListener {
		 void onTouchingLetterChanged(String s);
	}
	public void setSideBarIn(){
		canHaveAnimation=false;
		ObjectAnimator anim = ObjectAnimator.ofFloat(this, "alpha", 0f, 1f);
		anim.setDuration(1500);// 动画持续时间
		anim.start();
	     isDismiss=false;
		anim.addListener(new Animator.AnimatorListener() {
			@Override
			public void onAnimationStart(Animator animation) {

			}

			@Override
			public void onAnimationEnd(Animator animation) {

		      canHaveAnimation=true;

			}

			@Override
			public void onAnimationCancel(Animator animation) {

			}

			@Override
			public void onAnimationRepeat(Animator animation) {

			}
		});
	}
	public void setSideBarOut(){
		ObjectAnimator anim = ObjectAnimator.ofFloat(this,"alpha", 1f,0f);
		anim.setDuration(1500);// 动画持续时间
		anim.start();
		isDismiss=true;

		anim.addListener(new Animator.AnimatorListener() {
			@Override
			public void onAnimationStart(Animator animation) {

			}

			@Override
			public void onAnimationEnd(Animator animation) {

				canHaveAnimation=true;
			}

			@Override
			public void onAnimationCancel(Animator animation) {

			}

			@Override
			public void onAnimationRepeat(Animator animation) {

			}
		});
	}
}
