package com.joy.property.complaint;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.jinyi.ihome.module.common.ComplaintTo;
import com.joy.property.R;

/**
 * Created by Sai on 15/8/4.
 * 网络图片加载例子
 */
public class NetworkViewHolderView implements Holder<ComplaintTo> {
    private View imageView;
    Context mContext;
    View view;
    private ImageView image;
    private TextView tv_good;
    private TextView tv_so;
    private TextView tv_bad;
    private TextView tv_name;
    @Override
    public View createView(Context context) {
        //你可以通过layout文件来创建，也可以像我一样用代码创建，不一定是Image，任何控件都可以进行翻页
        imageView = View.inflate(context, R.layout.complaint_item,null);
        image = (ImageView) imageView.findViewById(R.id.iv);
        tv_good = (TextView) imageView.findViewById(R.id.good);
        tv_so = (TextView) imageView.findViewById(R.id.so);
        tv_bad = (TextView) imageView.findViewById(R.id.bad);
        tv_name = (TextView)imageView.findViewById(R.id.name);
        return imageView;
    }

    @Override
    public void UpdateUI(Context context, int position, ComplaintTo data) {
       // image.setImageBitmap(data.getImage());
        image.setImageResource(data.getImage());
        tv_good.setText(data.getGood() + "");
        tv_so.setText(data.getMedium() +"");
        tv_bad.setText(data.getBad() +"");
        tv_name.setText(data.getName()+"");
    }

}
