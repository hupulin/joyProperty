package com.joy.property.task;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joy.common.widget.UploadImage;
import com.joy.property.R;

import com.jinyi.ihome.module.home.ServiceHistTo;
import com.joy.library.utils.DateUtil;
import com.joy.property.base.BaseActivity;
import com.joy.property.common.LongPictureShowActivity;
import com.joy.property.common.PictureShowActivity;
import com.joy.property.utils.flowlayout.TagAdapter;
import com.joy.property.utils.flowlayout.TagFlowLayout;
import com.wefika.flowlayout.FlowLayout;

import java.util.ArrayList;
import java.util.List;

public class HistoryFeedBackActivity extends BaseActivity implements
		OnClickListener {
	private TextView mDate;
	private TextView mTime;
	private TextView mRemark;
	private TextView mContent;
	private RelativeLayout rl_evaluate;
	private RelativeLayout rl_remark;
    private ServiceHistTo  serviceHistTo;
    private FlowLayout  flowLayout;
    private TagFlowLayout mFlowLayout;
    private List<String> mNames=new ArrayList<>();



    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_history_feedback);
		findById();
		initIntentDatas();
		initDatas();
	}

	private void findById() {
        findViewById(R.id.back).setOnClickListener(this);
        mFlowLayout = (TagFlowLayout)findViewById(R.id.flowlayoutother);
        mTime = (TextView) findViewById(R.id.time);
        rl_evaluate = (RelativeLayout) findViewById(R.id.rl_evaluate);
        rl_remark = (RelativeLayout) findViewById(R.id.rl_remark);
		mDate = (TextView) findViewById(R.id.date);
		mContent =(TextView) findViewById(R.id.content);
        mRemark =(TextView) findViewById(R.id.remark);
        flowLayout = (FlowLayout) findViewById(R.id.flowLayout);

	}

	private void initIntentDatas() {
     serviceHistTo = (ServiceHistTo) getIntent().getSerializableExtra("mode");
        if("1".equals(getIntent().getStringExtra("type"))){
            mFlowLayout.setVisibility(View.GONE);
            rl_evaluate.setVisibility(View.GONE);
            mRemark.setVisibility(View.GONE);
            rl_remark.setVisibility(View.GONE);
        }
        if(TextUtils.isEmpty(serviceHistTo.getEvaluationMsg())&&TextUtils.isEmpty(serviceHistTo.getEvaluationTags())){

            mFlowLayout.setVisibility(View.GONE);
            rl_evaluate.setVisibility(View.GONE);
            mRemark.setVisibility(View.GONE);
            rl_remark.setVisibility(View.GONE);
        }
        Log.i("222", "initIntentDatas: "+serviceHistTo);
    }

	private void initDatas() {
        if (serviceHistTo.getCreatedOn() != null) {
            mDate.setText(DateUtil.getDateTimeFormat(DateUtil.mFormatDateString, serviceHistTo.getCreatedOn()));
            mTime.setText(DateUtil.getDateTimeFormat(DateUtil.mTimeFormat, serviceHistTo.getCreatedOn()));
        }
        if (!TextUtils.isEmpty(serviceHistTo.getReplyDesc())) {
            mContent.setText(serviceHistTo.getReplyDesc());
        }
        if(!"1".equals(getIntent().getStringExtra("type"))){
            if(!TextUtils.isEmpty(serviceHistTo.getEvaluationTags())){
                String[] arr = serviceHistTo.getEvaluationTags().split(",");
                Log.i("222", "initDatas: "+serviceHistTo.getEvaluationTags());
                for (String str : arr)
                {
                    mNames.add(str);
                }
            }
            mFlowLayout.setAdapter(new TagAdapter<String>(mNames) {

                @Override
                public View getView(com.joy.property.utils.flowlayout.FlowLayout parent, int position, String s) {
                    LayoutInflater mInflater = LayoutInflater.from(getThisContext());
                    RelativeLayout mRelativeLayout = (RelativeLayout) mInflater.inflate(R.layout.tv_new,
                            mFlowLayout, false);
                    TextView textView = (TextView) mRelativeLayout.findViewById(R.id.textView);
                    textView.setTextColor(Color.parseColor("#ffffff"));
                    textView.setBackgroundDrawable(getResources().getDrawable(R.drawable.negative_comment_check_bg));
                    textView.setText(s);
                    return mRelativeLayout;
                }
            });
            if(serviceHistTo.getEvaluationMsg()!=null){
                mRemark.setText(serviceHistTo.getEvaluationMsg());
            }else{
//                mRemark.setText("业主人很好的，他家的狗特别可爱就喜欢要鞋子又是就如果给我要下雨机会我i我基友吧他顿吃这我们下那个");
            }
        }

        if (!TextUtils.isEmpty(serviceHistTo.getReplyImages())) {
            String[] path = serviceHistTo.getReplyImages().split(";");
            int W = getScreenWidthPixels(this) - 50;
            FlowLayout.LayoutParams params = new FlowLayout.LayoutParams(W / 4, W / 4);
            params.leftMargin = 10;
            params.topMargin = 10;

            for (int i = 0; i <path.length ; i++) {
                ImageView image = new ImageView(this);
                image.setScaleType(ImageView.ScaleType.FIT_XY);
                displayImage(image , path[i]);
                image.setTag(R.id.tag_second2,i);
                image.setTag(R.id.tag_first2,serviceHistTo.getReplyImages());
//                image.setTag(serviceHistTo.getReplyImages());
                flowLayout.addView(image, flowLayout.getChildCount(), params);
                image.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getThisContext(),LongPictureShowActivity.class);
                        intent.putExtra("path",(String)v.getTag(R.id.tag_first2));
                        intent.putExtra("index", (int)v.getTag(R.id.tag_second2));
                        startActivity(intent);
                    }
                });

            }
        }

    }

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.back:
            onBackPressed();
            break;
           default:
               break;
		}
	}

    @Override
    protected Context getThisContext() {
        return HistoryFeedBackActivity.this;
    }

    @Override
    protected String toPageName() {
        return "历史反馈";
    }
}
