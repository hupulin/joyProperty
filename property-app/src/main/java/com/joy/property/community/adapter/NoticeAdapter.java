package com.joy.property.community.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.jinyi.ihome.module.notice.HomeNoticeTo;
import com.joy.library.utils.DateUtil;
import com.joy.property.R;
import com.joy.property.utils.htmlText.HtmlTextView;
import com.joyhome.nacity.app.MainApp;
import com.joyhome.nacity.app.common.adapter.ModeListAdapter;
import com.squareup.picasso.Picasso;

/**
 * Created by Admin on 2014-11-13
 */


public class NoticeAdapter extends ModeListAdapter<HomeNoticeTo> {

    public NoticeAdapter(Context context) {
        super(context);
        this.mContext = context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        HomeNoticeToCache holder = null;
//        ImageLoaderConfiguration config = ImageLoadleUtil.config(mContext);
//        ImageLoader.getInstance().init(config);
//        DisplayImageOptions option = ImageLoadleUtil.getImageloaderOption();
//        ImageLoader loader= ImageLoader.getInstance();
        if (row == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            row = inflater.inflate(R.layout.list_item_notice, null);
            holder = new HomeNoticeToCache(row);
            row.setTag(holder);
        } else {
            holder = (HomeNoticeToCache) row.getTag();
        }
        HomeNoticeTo mode = mList.get(position);
        holder.getBrowseQty().setText(String.valueOf(mode.getBrowseQty()));
        //holder.getCommentQty().setText(String.valueOf(mode.getCommentQty()));
        holder.getNoticeDate().setText(DateUtil.getDateTimeFormat(DateUtil.mDateFormatString, mode.getNoticeDate()));
        //holder.getNoticeAbstract().setText(mode.getNoticeAbstract());
        holder.getNoticeTitle().setText(mode.getNoticeTitle());

   //    holder.getNoticeContent().setText(mode.getNoticeContent().replaceAll("\\s*|(\r\n)", ""));
     //   holder.getNoticeContent().setText(Html.fromHtml(mode.getNoticeContent().replaceAll("\\s*|(\r\n)", "").trim()));
        holder.getNoticeContent().setText(mode.getNoticeAbstract());

//        HtmlTextView htmlTextView=(HtmlTextView)holder.getNoticeContent();
//      //  &nbsp;
//        htmlTextView.setHtmlFromString(mode.getNoticeContent().replaceAll(" ","2"),false);
        if (!TextUtils.isEmpty(mode.getNoticeImage())&&!(mode.getNoticeImage().equals("~/images/notice_default.png"))) {
           // System.out.println(mode.getNoticeImage()+"cz");
            holder.getmLayoutImage().setVisibility(View.VISIBLE);
            holder.getmImagePic().setLayoutParams(getLayoutParam());

            //loader.displayImage(MainApp.getImagePath(mode.getNoticeImage()),holder.getmImagePic(),option);
            Picasso.with(mContext).load(MainApp.getImagePath(mode.getNoticeImage())).into(holder.getmImagePic());
          //  imageLoader.displayImage(MainApp.getImagePath(mode.getNoticeImage()),holder.getmImagePic());
        }else {
            holder.getmLayoutImage().setVisibility(View.GONE);
        }
        if (TextUtils.isEmpty(mode.getNoticeAbstract())){
            holder.getNoticeContent().setVisibility(View.GONE);
        }else{
            holder.getNoticeContent().setVisibility(View.VISIBLE);
        }
        return row;
    }

    public RelativeLayout.LayoutParams getLayoutParam() {
        int mWidth = getScreenWidthPixels(mContext)-96;
        int mHeight = mWidth/2;

        return new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, mHeight);
    }
}
