package com.joy.property.neighborhood.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.holder.Holder;
import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.neighbor.NeighborhoodInteractionTo;
import com.jinyi.ihome.module.neighbor.NeighborhoodLogParam;
import com.jinyi.ihome.module.neighbor.NeighborhoodLogTo;
import com.joy.common.api.ApiClient;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.NeighborApi;
import com.joy.common.helper.ApartmentInfoHelper;
import com.joy.common.helper.UserInfoHelper;

import com.joy.property.R;
import com.joyhome.nacity.app.MainApp;
import com.squareup.picasso.Picasso;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Sai on 15/8/4.
 * 网络图片加载例子
 */
public class NeighborNetworkImageHolderView implements Holder<NeighborhoodInteractionTo> {

    private TextView lbtLeft;
    private TextView lbtRight;
    private ImageView imageView;
    private Context context;
    @Override
    public View createView(Context context) {
    View headView = View.inflate(context, R.layout.auto_row_head_lbt_view, null);
        lbtLeft = (TextView) headView.findViewById(R.id.lbtLeft);
        lbtRight = (TextView) headView.findViewById(R.id.lbtRight);
        imageView = (ImageView) headView.findViewById(R.id.imageView);
        this.context=context;
        return headView;
    }

    @Override
    public void UpdateUI(Context context,int position, NeighborhoodInteractionTo data) {
        Picasso.with(context).load(MainApp.getPicassoImagePath(data.getNeighborhoodMediaTo().getMediaUrl())).into(imageView);
        RelativeLayout.LayoutParams paramLeft = new RelativeLayout.LayoutParams((int) (getScreenWidthPixels(context) * 0.2041), (int) (getScreenWidthPixels(context) * 0.0708));
        RelativeLayout.LayoutParams paramRight = new RelativeLayout.LayoutParams((int) (getScreenWidthPixels(context) * 0.2041), (int) (getScreenWidthPixels(context) * 0.0708));

        if (data.getShowbtns() == 1) {
            lbtLeft.setVisibility(View.GONE);
            lbtRight.setText("查看活动");

            paramRight.setMargins((int) (getScreenWidthPixels(context) * 0.4), (int) (getScreenWidthPixels(context) * 0.268), 0, 0);
            lbtRight.setLayoutParams(paramRight);
        } else {
            if (data.getInteractionType() == 2) {
              paramLeft.width= (int) (getScreenWidthPixels(context)*0.2986);
                paramLeft.setMargins((int) (getScreenWidthPixels(context) * 0.1027), (int) (getScreenWidthPixels(context) * 0.268), 0, 0);
                paramRight.setMargins((int) (getScreenWidthPixels(context) * 0.65), (int) (getScreenWidthPixels(context) * 0.268), 0, 0);
                lbtLeft.setLayoutParams(paramLeft);
                lbtLeft.setBackgroundResource(R.drawable.neighbor_lbt_icon);
                lbtRight.setLayoutParams(paramRight);

                lbtLeft.setText("看看邻居怎么说");
                lbtRight.setText("我想说");
//                lbtRight.setOnClickListener(v -> {
//                    Intent intent = new Intent(context, PostActivity.class);
//                    intent.putExtra("TopicSid", firstInteraction.getRefId());
//                    startActivity(intent);
//                });

            } else if (data.getInteractionType() == 1) {
                paramLeft.setMargins((int) (getScreenWidthPixels(context) * 0.15), (int) (getScreenWidthPixels(context) * 0.268), 0, 0);
                paramRight.setMargins((int) (getScreenWidthPixels(context) * 0.65), (int) (getScreenWidthPixels(context) * 0.268), 0, 0);
                lbtLeft.setLayoutParams(paramLeft);
                lbtRight.setLayoutParams(paramRight);
                lbtLeft.setText("查看活动");
                lbtRight.setText("我要报名");
               // lbtRight.setOnClickListener(v -> joinCampaign(data.getRefId(), "报名成功", new Intent(context, CampaignActivity.class), 1, 1));
            } else {
                paramLeft.setMargins((int) (getScreenWidthPixels(context) * 0.15), (int) (getScreenWidthPixels(context) * 0.268), 0, 0);
                paramRight.setMargins((int) (getScreenWidthPixels(context) * 0.65), (int) (getScreenWidthPixels(context) * 0.268), 0, 0);
                lbtLeft.setLayoutParams(paramLeft);
                lbtRight.setLayoutParams(paramRight);
                lbtLeft.setText("查看调查");
                lbtRight.setText("我要参与");
          //      joinCampaign(data.getRefId(), "参与成功", new Intent(context, InvestigateActivity.class), 3, 1);
            }

        }
    }
    public int getScreenWidthPixels(Context context) {
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }
    public void joinCampaign(String interactionSid,String text,Intent intent,int type,int status){
        NeighborApi api= ApiClient.create(NeighborApi.class);

        ApartmentInfoHelper mHelper = ApartmentInfoHelper.getInstance(context);
        UserInfoHelper mUserHelper = UserInfoHelper.getInstance(context);
        NeighborhoodLogParam param=new NeighborhoodLogParam();

        param.setOwnerSid(mUserHelper.getSid());
        param.setApartmentSid(mHelper.getSid());
        param.setRefId(interactionSid);
        param.setType(type);
        param.setStatus(status);
        api.JoinCampaign(param, new HttpCallback<MessageTo<NeighborhoodLogTo>>(context) {
            @Override
            public void success(MessageTo<NeighborhoodLogTo> msg, Response response) {


                if (msg.getSuccess() == 0) {
                    if (status == 2) {
                        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
                        new Thread(() -> {
                            SystemClock.sleep(1000);
                            intent.putExtra("interactionSid", interactionSid);
                            context.startActivity(intent);
                        }).start();
                    }
                } else
                    Toast.makeText(context, msg.getMessage(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void failure(RetrofitError error) {

                System.out.println(error.toString());
            }
        });

    }
}
