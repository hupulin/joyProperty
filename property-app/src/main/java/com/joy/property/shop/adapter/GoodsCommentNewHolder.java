package com.joy.property.shop.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.joy.property.R;

/**
 * Created by usb on 2017/3/31.
 */
public class GoodsCommentNewHolder {
    private ImageView headImage;
    private TextView commenterName;
    private TextView commentTime;
    private TextView commentContent;
    private TextView commentSpecification;
    private View view;

    public GoodsCommentNewHolder(View view){

        this.view=view;
    }
    public ImageView getHeadImage(){
        if(headImage==null)
            headImage= (ImageView) view.findViewById(R.id.headImage);
        return headImage;
    }
    public TextView getCommenterName(){
        if(commenterName==null)
            commenterName= (TextView) view.findViewById(R.id.tv_nickname);
        return commenterName;
    }
    public TextView getCommentTime(){
        if(commentTime==null)
            commentTime= (TextView) view.findViewById(R.id.comment_date);
        return commentTime;
    }
    public TextView getCommentContent(){
        if(commentContent==null)
            commentContent= (TextView) view.findViewById(R.id.comment_content);
        return commentContent;
    }
    public TextView getCommentSpecification(){
        if(commentSpecification==null)
            commentSpecification= (TextView) view.findViewById(R.id.comment_description);
        return commentSpecification;
    }
}
