package com.joy.property.shop.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.jinyi.ihome.module.newshop.MainInfoDettailTo;
import com.joy.property.MainApp;
import com.joy.property.R;
import com.joy.property.shop.ThirdPartActivity;


import java.util.List;

/**
 * Created by Sai on 15/8/4.
 * 网络图片加载例子
 */
public class ThirdPartHolderView implements Holder<List<MainInfoDettailTo>> {

    private View rootView;

    @Override
    public View createView(Context context) {
        //你可以通过layout文件来创建，也可以像我一样用代码创建，不一定是Image，任何控件都可以进行翻页
        rootView = View.inflate(context, R.layout.third_part_holder_view, null);

        return rootView;
    }

    @Override
    public void UpdateUI(Context context, int position, List<MainInfoDettailTo> data) {
        GridLayout gridView = (GridLayout) rootView.findViewById(R.id.grid_view);
        for (int i = 0; i < data.size(); i++) {
            MainInfoDettailTo detailTo = data.get(i);
            View mView = View.inflate(context, R.layout.third_part_holder_item, null);

            Glide.with(context).load(MainApp.getImagePath(detailTo.getMinPicUrl())).into(((ImageView) mView.findViewById(R.id.third_part_image)));
            ((TextView) mView.findViewById(R.id.third_part_name)).setText(detailTo.getLayoutName());
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            if (i % 4 == 0) {

                params.setMargins(0, 0, 0, 0);
            }
            else {

                params.setMargins(getScreenWidthPixels(context) * 18 / 720, 0, 0, 0);

            }
            mView.setLayoutParams(params);
            mView.setTag(detailTo);
            gridView.addView(mView);
            mView.setOnClickListener(v -> {
                if (TextUtils.isEmpty(detailTo.getLayoutName()) || detailTo.getInsideForwordId() == 0)
                    return;
                Intent intent = new Intent(context, ThirdPartActivity.class);
                intent.putExtra("thirdPartyAccessId", detailTo.getInsideForwordId() + "");
                intent.putExtra("LayoutTitle", detailTo.getLayoutName());
                context.startActivity(intent);
            });
        }
    }

    public int getScreenWidthPixels(Context context) {
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }
}
