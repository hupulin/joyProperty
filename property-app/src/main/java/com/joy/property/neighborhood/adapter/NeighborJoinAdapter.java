package com.joy.property.neighborhood.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jinyi.ihome.module.neighbor.NeighborCommentTo;
import com.jinyi.ihome.module.neighbor.NeighborLikeTo;
import com.jinyi.ihome.module.neighbor.NeighborPostTo;
import com.joy.library.utils.DateUtil;

import com.joy.property.R;
import com.joyhome.nacity.app.common.adapter.ModeListAdapter;

/**
 * Created by xz on 2016/5/23.
 **/
public class NeighborJoinAdapter extends ModeListAdapter<NeighborPostTo> {
    private Context mContext;
    public NeighborJoinAdapter(Context context) {
        super(context);
        this.mContext = context;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row=convertView;
        NeighborJoinHolder holder;


    if (row == null) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        row = inflater.inflate(R.layout.neighbor_join_item, null);
        holder = new NeighborJoinHolder(row);
        row.setTag(holder);
    } else {
        holder = (NeighborJoinHolder) row.getTag();
    }
NeighborPostTo mode=mList.get(position);
        System.out.println(mode.toString()+"mode");
        if (mode.getPostOwner()!= null) {
            displayImage(holder.getHeadImage(),mode.getPostOwner().getIcon(),R.drawable.head_image);
            if (!TextUtils.isEmpty(mode.getPostOwner().getName())) {
                holder.getJoinName().setText(mode.getPostOwner().getName());
            }
        }
        String myLike = null;
        String myComment = null;
        if ("活动".equals(mode.getPostTypeName()) || "话题".equals(mode.getPostTypeName()) || "调查".equals(mode.getPostTypeName())) {
            for (NeighborLikeTo likeTo : mode.getLikeList()) {

                if (likeTo.getLikeOwner() != null && mode.getPostOwner() != null && mUserHelper.getUserInfoTo().getSid().equals(likeTo.getLikeOwner().getSid())) {

                    myLike = "我赞了" + mode.getPostOwner().getName();
                    holder.getCommentedName().setText(myLike);
                }
            }
            for (NeighborCommentTo commentTo : mode.getCommentList()) {
                if (mode.getPostOwner() != null && commentTo.getCommentOwner().getSid().equals(mUserHelper.getSid())) {
                    myComment = "我评论了" + mode.getPostOwner().getName();
                    holder.getCommentedName().setText(myComment);
                }
            }
            if (myComment != null && myLike != null && mode.getPostOwner() != null) {
                holder.getCommentedName().setText("我参与了" + (TextUtils.isEmpty(mode.getRefId()) ? mode.getPostOwner().getName() : mode.getPostOwner().getName() + "的" + mode.getPostTypeName()));
            }
            if (mode.getLikeList()==null||mode.getLikeList().size()==0||mode.getCommentList()==null||mode.getCommentList().size()==0){
                holder.getCommentedName().setText("我参与了"+mode.getPostTypeName());
            }
        }else {

            for (NeighborLikeTo likeTo : mode.getLikeList()) {

                if (likeTo.getLikeOwner() != null && mode.getPostOwner() != null && mUserHelper.getUserInfoTo().getSid().equals(likeTo.getLikeOwner().getSid())) {

                    myLike = "我赞了" + mode.getPostOwner().getName();
                    holder.getCommentedName().setText(myLike);
                }
            }
            for (NeighborCommentTo commentTo : mode.getCommentList()) {
                if (mode.getPostOwner() != null && commentTo.getCommentOwner().getSid().equals(mUserHelper.getSid())) {
                    myComment = "我评论了" + mode.getPostOwner().getName();
                    holder.getCommentedName().setText(myComment);
                }
            }
            if (myComment != null && myLike != null && mode.getPostOwner() != null) {
                holder.getCommentedName().setText("我参与了" + (TextUtils.isEmpty(mode.getRefId()) ? mode.getPostOwner().getName() : mode.getPostOwner().getName() + "的" + mode.getPostTypeName()));
            }
        }
        holder.getCommentContent().setText(mode.getPostSubject()==null?mode.getPostContent():mode.getPostSubject()+mode.getPostContent());
        holder.getJoinTime().setText(DateUtil.getDateTimeFormat(DateUtil.mDateTimeFormatStringNoSecond, mode.getPostTime()));



    return row;
}



}
