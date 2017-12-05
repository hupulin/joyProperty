package com.joy.property.shop.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.holder.Holder;
import com.jinyi.ihome.module.newshop.ShopTypeTo;
import com.joy.property.R;
import com.joy.property.shop.ShopBulkActivity;
import com.joy.property.shop.ShopSortGridView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.List;

/**
 * Created by Sai on 15/8/4.
 * 网络图片加载例子
 */
public class ShopTypeHolderView implements Holder<List<ShopTypeTo>> {
    private ShopSortGridView sortGridView;
    Context mContext;
    @Override
    public View createView(Context context) {
        //你可以通过layout文件来创建，也可以像我一样用代码创建，不一定是Image，任何控件都可以进行翻页
        sortGridView = new ShopSortGridView(context);

        return sortGridView;
    }

    @Override
    public void UpdateUI(Context context,int position, List<ShopTypeTo> data) {
        sortGridView.setItems(data);
        sortGridView.setOnItemClickListener((v, typeSid) ->{
            if(typeSid.equals("001"))
            context.startActivity(new Intent(context, ShopBulkActivity.class));
        });

    }

}
