package com.joy.property.worksign.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jinyi.ihome.module.worksign.SignRecordTo;
import com.joy.library.utils.DateUtil;
import com.joy.property.R;
import com.joy.property.worksign.SignDetailActivity;
import com.joyhome.nacity.app.MainApp;
import com.joyhome.nacity.app.common.adapter.ModeListAdapter;

/**
 * Created by xz on 2016/7/12.
 **/
public class SignRecordAdapter extends ModeListAdapter<SignRecordTo.SignListTo> {
    private Context mContext;
    private Gson gson;

    public SignRecordAdapter(Context context) {
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
            row = inflater.inflate(R.layout.sign_record_item, null);
            holder = new SignRecordHolder(row);
            row.setTag(holder);
        } else {
            holder = (SignRecordHolder) row.getTag();
        }
        holder.getGridView().removeAllViews();
        SignRecordTo.SignListTo mode = gson.fromJson(gson.toJson(mList.get(position)), SignRecordTo.SignListTo.class);

        if (!TextUtils.isEmpty(mode.getWork_img())) {
            String[] imageList;
            if (mode.getWork_img().contains(","))
             imageList = mode.getWork_img().split(",");
            else
                imageList = mode.getWork_img().split(";");
            for (int i = 0; i < imageList.length; i++) {

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
                System.out.println(MainApp.getImagePath(imageList[i]));
                Glide.with(mContext).load(MainApp.getImagePath(imageList[i])).into(imageView);
                holder.getGridView().addView(imageView);
            }
        }

        holder.getRemarkContent().setText(mode.getRemark());
        holder.getSignPosition().setText(mode.getAddress());
        holder.getSignTime().setText("签到时间："+ DateUtil.getDateString(DateUtil.getFormatDateLongTime(mode.getCreatetime()), DateUtil.mDateTimeFormatStringNoSecond));
        holder.getWorkContent().setText("工作内容："+mode.getWork_cont());
        if (mode.getStatus()==2){
            holder.getRecordStatue().setBackgroundResource(R.drawable.sign_record_abnormal_bg);
            holder.getRecordStatue().setText("异常");
            holder.getRecordStatue().setTextColor(Color.parseColor("#e8b619"));
        }else if (mode.getStatus()==3){
            holder.getRecordStatue().setBackgroundResource(R.drawable.sign_record_invalid_bg);
            holder.getRecordStatue().setText("失效");
            holder.getRecordStatue().setTextColor(Color.parseColor("#fa6363"));
        }else {
            holder.getRecordStatue().setBackgroundResource(R.drawable.sign_record_normal_bg);
            holder.getRecordStatue().setText("正常");
            holder.getRecordStatue().setTextColor(Color.parseColor("#3bafda"));
        }
        holder.getParent().setOnClickListener(v -> {
            Intent intent=new Intent(mContext,SignDetailActivity.class);
                    intent.putExtra("SignSid",mode.getId());
                    mContext.startActivity(intent);
        });
        return row;
    }

}
