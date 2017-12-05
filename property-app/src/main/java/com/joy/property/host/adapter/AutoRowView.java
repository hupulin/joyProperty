package com.joy.property.host.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.jinyi.ihome.module.common.SatisfactionTo;
import com.joy.property.R;
import com.joyhome.nacity.app.util.ImageLoadleUtil;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

/**
 * Created by Sai on 15/8/4.
 * 网络图片加载例子
 */
public class AutoRowView implements Holder<SatisfactionTo> {
    private TextView goodComment;
    private TextView middleComment;
    private TextView badComment;

    @Override
    public View createView(Context context) {
     View view=View.inflate(context,R.layout.satisfaction_item,null);

        goodComment = (TextView) view.findViewById(R.id.goodComment);
        middleComment = (TextView) view.findViewById(R.id.middleComment);
        badComment = (TextView) view.findViewById(R.id.badComment);

        return view;
    }

    @Override
    public void UpdateUI(Context context, int position, SatisfactionTo data) {
        goodComment.setText(data.getGoodComment()+"%");
        middleComment.setText(data.getMiddleComment()+"%");
        badComment.setText(data.getBadComment()+"%");

    }



}
