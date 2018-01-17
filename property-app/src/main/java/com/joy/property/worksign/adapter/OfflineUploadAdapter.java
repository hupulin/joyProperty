package com.joy.property.worksign.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jinyi.ihome.module.worksign.SignRecordTo;
import com.joy.library.utils.DateUtil;
import com.joy.property.R;
import com.joy.property.worksign.SignDetailActivity;
import com.joyhome.nacity.app.MainApp;
import com.joyhome.nacity.app.common.adapter.ModeListAdapter;
import com.joyhome.nacity.app.photo.util.ImageItem;

/**
 * Created by xz on 2016/7/12.
 **/
public class OfflineUploadAdapter extends ModeListAdapter<com.jinyi.ihome.module.worksign.SignSubmitJsonTo> {
    private Context mContext;
    private Gson gson;

    public OfflineUploadAdapter(Context context) {
        super(context);
        this.mContext = context;
        gson = new Gson();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        SignRecordHolder holder;

        if (row == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            row = inflater.inflate(R.layout.sign_offline_upload_item, null);
            holder = new SignRecordHolder(row);
            row.setTag(holder);
        } else {
            holder = (SignRecordHolder) row.getTag();
        }
        holder.getGridView().removeAllViews();
        SignSubmitJsonTo mode = gson.fromJson(gson.toJson(mList.get(position)), SignSubmitJsonTo.class);
        System.out.println(mode+"mode====");
        if (mode.getImageList().size() > 0) {

            for (int i = 0; i < mode.getImageList().size(); i++) {

                ImageView imageView = new ImageView(mContext);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.width = (int) (getScreenWidthPixels(mContext) * 106.0 / 720);
                params.height = (int) (getScreenWidthPixels(mContext) * 106.0 / 720);
                if (i != 0)
                    params.leftMargin = (int) (getScreenWidthPixels(mContext) * 12.0 / 720);
                else
                    params.leftMargin = 0;
                imageView.setLayoutParams(params);
                ImageItem imageItem = new ImageItem();
                imageItem.setImagePath(mode.getImageList().get(i));
                imageView.setImageBitmap(imageItem.getBitmap());


                holder.getGridView().addView(imageView);
            }
        }
        if (mode.isAllUpload()){
            holder.getUploadLoading().setVisibility(View.VISIBLE);
            holder.getUploadIcon().setVisibility(View.GONE);
        }else {
            holder.getUploadLoading().setVisibility(View.GONE);
            holder.getUploadIcon().setVisibility(View.VISIBLE);
        }

        holder.getRemarkContent().setText(mode.getSignNote());
        holder.getSignPosition().setText(mode.getAddress());
        holder.getSignTime().setText("签到时间：" + DateUtil.longToString(Long.valueOf(mode.getSignTime()) * 1000, DateUtil.mDateTimeFormatStringNoSecond));
        holder.getWorkContent().setText("工作内容：" + (TextUtils.isEmpty(mode.getJobStr()) ? "未选择" : mode.getJobStr()));
        holder.getUpload().setOnClickListener(v -> {
            if (listener != null) {
                listener.uploadClick(position,holder.getUpload());
                holder.getUploadIcon().setVisibility(View.GONE);
                holder.getUploadLoading().setVisibility(View.VISIBLE);
                holder.getUpload().setEnabled(false);
            }
        });
        return row;
    }

    public UploadClickListener listener;

    public interface UploadClickListener {
        void uploadClick(int position, RelativeLayout uploadBtn);
    }

    public void setUploadClick(UploadClickListener listener) {
        this.listener = listener;
    }

}
