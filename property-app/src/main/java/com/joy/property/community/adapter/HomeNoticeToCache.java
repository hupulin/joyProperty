package com.joy.property.community.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joy.property.R;
import com.joy.property.utils.htmlText.HtmlTextView;


/**
 * Created by Admin on 2014-11-13
 */
public class HomeNoticeToCache {

    private View view;

    private TextView browseQty;
    private TextView commentQty;
    private TextView noticeDate;
    private TextView noticeTitle;
    private TextView noticeContent;
    private TextView noticeAbstract;
    private ImageView mImagePic;
   private RelativeLayout mLayoutImage;
    public HomeNoticeToCache(View view) {
        this.view = view;
    }



    public TextView getBrowseQty() {
        if (browseQty == null) {
            browseQty = (TextView) view.findViewById(R.id.browse_amount);
        }
        return browseQty;
    }

//    public TextView getCommentQty() {
//        if (commentQty == null) {
//            commentQty = (TextView) view.findViewById(R.id.comments_amount);
//        }
//        return commentQty;
//    }

    public TextView getNoticeDate() {
        if (noticeDate == null) {
            noticeDate = (TextView) view.findViewById(R.id.notice_time);
        }
        return noticeDate;
    }

    public TextView getNoticeTitle() {
        if (noticeTitle == null) {
            noticeTitle = (TextView) view.findViewById(R.id.notice_title);
        }
        return noticeTitle;
    }
    public TextView getNoticeContent() {
        if (noticeContent == null) {
            noticeContent = (TextView) view.findViewById(R.id.notice_content);
        }
        return noticeContent;
    }

//    public TextView getNoticeAbstract() {
//        if (noticeAbstract == null) {
//            noticeAbstract = (TextView) view.findViewById(R.id.notice_content);
//        }
//        return noticeAbstract;
//    }

    public ImageView getmImagePic() {
        if (this.mImagePic == null) {
            this.mImagePic = (ImageView) view.findViewById(R.id.image_pic);
        }
        return mImagePic;
    }

    public RelativeLayout getmLayoutImage() {
        if (this.mLayoutImage == null) {
            this.mLayoutImage = (RelativeLayout) view.findViewById(R.id.layout);
        }
        return mLayoutImage;
    }
}
