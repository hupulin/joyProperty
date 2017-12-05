package com.joy.property.common.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;


import com.joy.property.MainApplication;
import com.lidroid.xutils.BitmapUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 2015-01-26
 */
public abstract class ModeListAdapter<T> extends BaseAdapter {

    protected List<T> mList;
    protected Context mContext;
    protected ListView mListView;
    protected BitmapUtils mBitmapUtils;
    public ModeListAdapter(Context context) {
        this.mContext = context;
        mBitmapUtils = new BitmapUtils(mContext);
    }
    public void displayImage(ImageView imageView, String uri) {

        if (imageView == null) {
            return;
        }
        if (TextUtils.isEmpty(uri)) {
            return;
        }
        mBitmapUtils.configDefaultBitmapConfig(Bitmap.Config.RGB_565);
        mBitmapUtils.configDefaultShowOriginal(false);
        mBitmapUtils.configDefaultAutoRotation(true);
        mBitmapUtils.display(imageView, MainApplication.getImagePath(uri));
    }


    public void displayImage(ImageView imageView, String uri, int StringId) {

        if (imageView == null) {
            return;
        }
        if (TextUtils.isEmpty(uri)) {
            imageView.setImageResource(StringId);
            return;
        }

        mBitmapUtils.configDefaultBitmapConfig(Bitmap.Config.RGB_565);
        mBitmapUtils.configDefaultShowOriginal(false);
        mBitmapUtils.configDefaultAutoRotation(true);
        mBitmapUtils.display(imageView, MainApplication.getImagePath(uri));
    }
    @Override
    public int getCount() {
        if (mList != null)
            return mList.size();
        else
            return 0;
    }

    @Override
    public Object getItem(int position) {
        return mList == null ? null : mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    abstract public View getView(int position, View convertView, ViewGroup parent);

    public void setList(List<T> list) {
        this.mList = list;
        notifyDataSetChanged();
    }

    public List<T> getList() {
        return mList;
    }

    public void setList(T[] list) {
        List<T> arrayList = new ArrayList<T>(list.length);
        for (T t : list) {
            arrayList.add(t);
        }
        setList(arrayList);
    }

    public ListView getListView() {
        return mListView;
    }

    public void setListView(ListView listView) {
        mListView = listView;
    }
    public int getScreenWidthPixels(Context context) {
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }
}
