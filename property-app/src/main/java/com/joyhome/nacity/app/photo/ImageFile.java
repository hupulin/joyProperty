package com.joyhome.nacity.app.photo;


import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.joy.property.R;
import com.joyhome.nacity.app.base.BaseActivity;
import com.joyhome.nacity.app.photo.adapter.FolderAdapter;

/**
 * 这个类主要是用来进行显示包含图片的文件夹
 *
 * @author king
 * @QQ:595163260
 * @version 2014年10月18日  下午11:48:06
 */

public class ImageFile extends BaseActivity {

	private FolderAdapter folderAdapter;
	private Button bt_cancel;
	private Context mContext;
	private int num ;
    private String flag = "";
    private String type = "";
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.plugin_camera_image_file);
		//PublicWay.activityList.add(this);
		num = getIntent().getIntExtra("number",0);
		flag = getIntent().getStringExtra("flag");
		type = getIntent().getStringExtra("type");
		mContext = this;
		bt_cancel = (Button) findViewById(R.id.cancel);
		bt_cancel.setOnClickListener(new CancelListener());
		GridView gridView = (GridView) findViewById(R.id.fileGridView);
		TextView textView = (TextView) findViewById(R.id.headerTitle);
		textView.setText(getString(R.string.photo));
		folderAdapter = new FolderAdapter(this ,num , flag,type);
		gridView.setAdapter(folderAdapter);
	}

	private class CancelListener implements OnClickListener {// 取消按钮的监听
		public void onClick(View v) {
			//清空选择的图片
			//Bimp.tempSelectBitmap.clear();
			//Intent intent = new Intent();

			finish();
		}
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			//Intent intent = new Intent();

			finish();
		}
		
		return true;
	}

}
