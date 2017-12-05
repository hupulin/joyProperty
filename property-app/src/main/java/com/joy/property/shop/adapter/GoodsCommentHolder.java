package com.joy.property.shop.adapter;

import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joy.library.utils.HeadImageView;
import com.joy.property.R;
import com.joyhome.nacity.app.util.htmlText.HtmlTextView;

/**
 * Created by xz on 2016/7/19.
 **/
public class GoodsCommentHolder {
    private View view;
    private HeadImageView headImage;
    private TextView commenterName;
    private TextView commentTime;
    private TextView commentResult;
    private TextView commentContent;
    private GridLayout gridView;
    private HtmlTextView htmlText;
    private RelativeLayout commentLayout;
    public GoodsCommentHolder(View view){

        this.view=view;
    }
    public ImageView getHeadImage(){
        if(headImage==null)
            headImage= (HeadImageView) view.findViewById(R.id.head_image);
        return headImage;
    }
    public TextView getCommenterName(){
        if(commenterName==null)
            commenterName= (TextView) view.findViewById(R.id.commenter_name);
return commenterName;
    }
    public TextView getCommentTime(){
        if(commentTime==null)
            commentTime= (TextView) view.findViewById(R.id.comment_time);
        return commentTime;
    }
    public TextView getCommentResult(){
        if(commentResult==null)
            commentResult= (TextView) view.findViewById(R.id.comment_result);
        return commentResult;
    }
    public TextView getCommentContent(){
        if(commentContent==null)
            commentContent= (TextView) view.findViewById(R.id.comment_content);
        return commentContent;
    }
    public GridLayout getGridView(){
        if(gridView==null)
            gridView= (GridLayout) view.findViewById(R.id.gridView);
        return gridView;
    }
    public HtmlTextView getHtmlText(){
        if (htmlText==null)
             htmlText = (HtmlTextView) view.findViewById(R.id.htmlText);
       return htmlText;
        }
public RelativeLayout getCommentLayout(){
    if (commentLayout==null)
         commentLayout = (RelativeLayout) view.findViewById(R.id.commentLayout);
        return commentLayout;

}
}
