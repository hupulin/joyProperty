/*
 * Copyright (C) 2009 Teleca Poland Sp. z o.o. <android@teleca.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.joyhome.nacity.app.common.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.joy.common.helper.UserInfoHelper;
import com.joy.property.MainApplication;
import com.joyhome.nacity.app.MainApp;
import com.lidroid.xutils.BitmapUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ArrayList通用Adapter
 *
 * @param <T>
 * @author gCoder
 */
public abstract class ModeListAdapter<T> extends BaseAdapter {

    protected List<T> mList;
    protected Context mContext;
    protected ListView mListView;
    protected BitmapUtils mBitmapUtils;
    protected UserInfoHelper mUserHelper;
    protected SharedPreferences sp;
    protected ImageLoader imageLoader = ImageLoader.getInstance();
    public ModeListAdapter(Context context) {
        this.mContext = context;

        mUserHelper = UserInfoHelper.getInstance(MainApplication.mContext);
        sp = PreferenceManager.getDefaultSharedPreferences(mContext);

    }

    public void displayImage(ImageView imageView, String stringId) {

        if (imageView == null) {
            return;
        }
        if (TextUtils.isEmpty(stringId)) {
            return;
        }
        mBitmapUtils = new BitmapUtils(mContext);
        mBitmapUtils.configDefaultAutoRotation(true);
        mBitmapUtils.configDefaultBitmapConfig(Bitmap.Config.RGB_565);
        mBitmapUtils.configDefaultShowOriginal(false);
        mBitmapUtils.display(imageView, MainApp.getImagePath(stringId));

    }

    public void displayImage(ImageView imageView, String stringId, int id) {
        if (imageView == null) {
            return;
        }
        if (TextUtils.isEmpty(stringId)) {
            imageView.setImageResource(id);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setBackgroundResource(id);
            return;
        }
        imageView.setBackgroundResource(0);
        mBitmapUtils = new BitmapUtils(mContext);
        mBitmapUtils.configDefaultAutoRotation(true);
        mBitmapUtils.configDefaultBitmapConfig(Bitmap.Config.RGB_565);
        mBitmapUtils.configDefaultShowOriginal(false);
        mBitmapUtils.display(imageView, MainApp.getImagePath(stringId));
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
        List<T> arrayList = new ArrayList<>(list.length);
        Collections.addAll(arrayList, list);
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
