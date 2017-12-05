package com.joyhome.nacity.app.propertyCenter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.joy.property.R;
import com.joyhome.nacity.app.util.ImageLoadleUtil;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.squareup.picasso.Picasso;

/**
 * Created by Sai on 15/8/4.
 * 网络图片加载例子
 */
public class NetworkImageHolderView implements Holder<String> {
    private ImageView imageView;
    Context mContext;
    @Override
    public View createView(Context context) {
        //你可以通过layout文件来创建，也可以像我一样用代码创建，不一定是Image，任何控件都可以进行翻页
        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }

    @Override
    public void UpdateUI(Context context,int position, String data) {

     // ImageLoader.getInstance().displayImage(data, imageView);
        ImageLoaderConfiguration config = ImageLoadleUtil.config(context);
      ImageLoader.getInstance().init(config);
        DisplayImageOptions   options = new DisplayImageOptions.Builder()
                .showStubImage(R.drawable.housekeep_bannal)         // 设置图片下载期间显示的图片
                .showImageForEmptyUri(R.drawable.housekeep_bannal)  // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.drawable.housekeep_bannal)       // 设置图片加载或解码过程中发生错误显示的图片
                .cacheInMemory(true)                        // 设置下载的图片是否缓存在内存中
                .cacheOnDisc(true)                          // 设置下载的图片是否缓存在SD卡中
                .displayer(new RoundedBitmapDisplayer(0))  // 设置成圆角图片
                .build();
        ImageLoader loader= ImageLoader.getInstance();
loader.displayImage(data, imageView, options);
    }

}
