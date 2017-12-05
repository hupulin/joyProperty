package com.joy.property.asyntask;


import com.joy.property.MainActivity;
import com.joy.property.views.UpdateView;

import android.app.Activity;
import android.content.Context;

 
public class MainTask {

	public MainTask() {

	}

	public static class UpdateTask extends GenericTask {

		public Context mContext;
		public UpdateView updateView;

		public UpdateTask(Context context) {
			mContext = context;
			updateView = new UpdateView(mContext);
		}

		public UpdateTask(Context context, boolean shownull) {
			mContext = context;
			updateView = new UpdateView(mContext, shownull);
		}
    public UpdateTask(Context context, boolean shownull, MainActivity activity) {
			mContext = context;
			updateView = new UpdateView(mContext, shownull,activity);

		}

		@Override
		protected TaskResult _doInBackground(TaskParams... params) {
			try {
				updateView.Start();
				return TaskResult.OK;
			} catch (Exception ignored) {
			}
			return TaskResult.FAILED;
		}
	}

}
