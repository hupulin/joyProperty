package com.joy.property.common.adapter;

import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.joyhome.nacity.app.MainApp;
import com.joyhome.nacity.app.util.ImageLoadleUtil;

import java.util.List;

/**
 * Created by Admin on 2015-05-14
 */
public class LongImagePagerAdapter extends PagerAdapter {

    private List<String> mList;

    public LongImagePagerAdapter(List<String> list) {
        this.mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {

        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {


//            PhotoView view = new PhotoView(container.getContext());
////           Uri uri=Uri.parse(MainApp.getImagePath(mList.get(position)));
////            SimpleDraweeView draweeView=new SimpleDraweeView(container.getContext());
////        DraweeController  draweeController =
////                Fresco.newDraweeControllerBuilder()
////                        .setUri(uri)
////                        .setAutoPlayAnimations(true) // 设置加载图片完成后是否直接进行播放
////                        .build();
////        draweeView.setController(draweeController);
//            view.enable();
//            view.setScaleType(ImageView.ScaleType.FIT_CENTER);
//            Picasso.with(container.getContext()).load(MainApp.getImagePath(mList.get(position))).into(new Target() {
//                @Override
//                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom loadedFrom) {
//
//
//
//                   if(bitmap.getHeight()/bitmap.getWidth()>=2)
//                       view.setScaleType(ImageView.ScaleType.FIT_START);
//                       view.setImageBitmap(bitmap);
//                }
//
//                @Override
//                public void onBitmapFailed(Drawable drawable) {
//
//                }
//
//                @Override
//                public void onPrepareLoad(Drawable drawable) {
//
//                }
//            });
        Bitmap bitmap= ImageLoadleUtil.loaderCache(0,container.getContext(),MainApp.getImagePath(mList.get(position)));

        PhotoView view = new PhotoView(container.getContext());
        view.enable();
        view.setScaleType(ImageView.ScaleType.FIT_CENTER);
        if(bitmap!=null&&bitmap.getHeight()/bitmap.getWidth()>=2) {
            view.setScaleType(ImageView.ScaleType.FIT_START);
            view.setImageBitmap(bitmap);
        }else {
            Glide.with(container.getContext()).load(MainApp.getImagePath(mList.get(position))).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(view);
        }
        container.addView(view);


        return view;

    }


}
