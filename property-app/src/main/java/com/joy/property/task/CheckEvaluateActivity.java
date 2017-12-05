package com.joy.property.task;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jinyi.ihome.module.home.ServiceHistTo;
import com.jinyi.ihome.module.home.ServiceMainExpandTo;
import com.joy.property.R;
import com.joy.property.base.BaseActivity;
import com.joy.property.utils.flowlayout.TagAdapter;
import com.joy.property.utils.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by usb on 2017/6/12.
 */

public class CheckEvaluateActivity extends BaseActivity implements
        View.OnClickListener {
    private ServiceMainExpandTo mainExpandTo;
    private ServiceHistTo serviceHistTo;

    private TextView mContent;
    private RatingBar mRate01;
    private RatingBar mRate02;
    private RatingBar mRate03;
    private TextView mText01;
    private  TextView mText02;
    private  TextView mText03;
    private  TextView mRemark;
    private   TextView mSatisfaction;
    private TagFlowLayout mFlowLayout;
    private List<String> mNames=new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_comment);
        findById();
        initIntentDatas();

        initDatas();
    }
     private TagAdapter adapter=new TagAdapter<String>(mNames) {

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
    };
    private void initIntentDatas() {
        mainExpandTo = (ServiceMainExpandTo) getIntent().getSerializableExtra("mode");
        serviceHistTo = (ServiceHistTo) getIntent().getSerializableExtra("histToList");
        Log.i("222222", "mainExpandTo: "+mainExpandTo.toString());
        if(TextUtils.isEmpty(mainExpandTo.getBadEvaluationRemark())&&TextUtils.isEmpty(mainExpandTo.getBadEvaluationDescribe())){
            mFlowLayout.setVisibility(View.GONE);
            mRelayoutOne.setVisibility(View.GONE);
            mRemark.setVisibility(View.GONE);
            mRelayoutTwo.setVisibility(View.GONE);
        }

    }
    private void initDatas() {
        if (!TextUtils.isEmpty(mainExpandTo.getEvaluationDesc())) {
            mContent.setText(mainExpandTo.getEvaluationDesc());
        }
        if (mainExpandTo.getEvaluationItem1() != null) {
            mRate01.setRating(mainExpandTo.getEvaluationItem1());
        }
        if (mainExpandTo.getEvaluationItem2() != null) {
            mRate02.setRating(mainExpandTo.getEvaluationItem2());
        }
        if (mainExpandTo.getEvaluationItem3() != null) {
            mRate03.setRating(mainExpandTo.getEvaluationItem3());
        }

        if (mainExpandTo.getEvaluationItem3() != null&&mainExpandTo.getEvaluationItem2() != null&&mainExpandTo.getEvaluationItem1() != null) {
        if (mainExpandTo.getEvaluationItem1() == 1.0f) {
            mText01.setText("差评");
        } else if (mainExpandTo.getEvaluationItem1() == 2.0f) {
            mText01.setText("不满");
        } else if (mainExpandTo.getEvaluationItem1() == 3.0f) {
            mText01.setText("一般");
        } else if (mainExpandTo.getEvaluationItem1() == 4.0f) {
            mText01.setText("满意");
        } else if (mainExpandTo.getEvaluationItem1() == 5.0f) {
            mText01.setText("惊喜");
        }

        if (mainExpandTo.getEvaluationItem2() == 1.0f) {
            mText02.setText("差评");

        } else if (mainExpandTo.getEvaluationItem2() == 2.0f) {
            mText02.setText("不满");

        } else if (mainExpandTo.getEvaluationItem2() == 3.0f) {
            mText02.setText("一般");
        } else if (mainExpandTo.getEvaluationItem2() == 4.0f) {
            mText02.setText("满意");
        } else if (mainExpandTo.getEvaluationItem2() == 5.0f) {
            mText02.setText("惊喜");
        }
        if (mainExpandTo.getEvaluationItem3() == 1.0f) {
            mText03.setText("差评");

        } else if (mainExpandTo.getEvaluationItem3() == 2.0f) {
            mText03.setText("不满");

        } else if (mainExpandTo.getEvaluationItem3() == 3.0f) {
            mText03.setText("一般");
        } else if (mainExpandTo.getEvaluationItem3() == 4.0f) {
            mText03.setText("满意");
        } else if (mainExpandTo.getEvaluationItem3() == 5.0f) {
            mText03.setText("惊喜");
        }
    }
        if (mainExpandTo.getEvaluationItem3() != null) {
            if (mainExpandTo.getEvaluationItem3() > 0 && mainExpandTo.getEvaluationItem3() < 3) {
                mSatisfaction.setText("我对这次问题的解决感到十分不满！");
            } else if (mainExpandTo.getEvaluationItem3() == 3) {
                mSatisfaction.setText("我对这次问题的解决感到一般！");
            } else if (mainExpandTo.getEvaluationItem3() > 3) {
                mSatisfaction.setText("我对这次问题的解决感到十分满意！");
            }
        }
        mFlowLayout.setAdapter(adapter);
        if(mainExpandTo.getBadEvaluationRemark()!=null){
            mRemark.setText(mainExpandTo.getBadEvaluationRemark());
        }else{
            mRemark.setText("业主的评价为空");
        }
        if (!TextUtils.isEmpty(mainExpandTo.getEvaluationDesc())) {

            mContent.setText(mainExpandTo.getEvaluationDesc());
        }else{
            mContent.setText("业主的评价为空");
        }
        if(!TextUtils.isEmpty(mainExpandTo.getBadEvaluationDescribe())){
            String[] arr = mainExpandTo.getBadEvaluationDescribe().split(";");
            for (String str : arr)
            {
                mNames.add(str);
            }
            adapter.notifyDataChanged();
        }
    }
    private RelativeLayout mRelayoutOne;
    private RelativeLayout mRelayoutTwo;
    private void findById() {
        findViewById(R.id.back).setOnClickListener(this);
        mContent = (TextView) findViewById(R.id.evaluation_content);
        mRelayoutOne = (RelativeLayout) findViewById(R.id.rl_negative);
        mRelayoutTwo = (RelativeLayout) findViewById(R.id.rl_remark_one);
        mRemark = (TextView) findViewById(R.id.remark_one);
        mFlowLayout = (TagFlowLayout)findViewById(R.id.flowlayoutother);

        mRate01 = (RatingBar) findViewById(R.id.rb_rate01);
          mRate02 = (RatingBar) findViewById(R.id.rb_rate02);
          mRate03 = (RatingBar) findViewById(R.id.rb_rate03);
          mText01 = (TextView) findViewById(R.id.tv_text01);
           mText02 = (TextView) findViewById(R.id.tv_text02);
           mText03 = (TextView) findViewById(R.id.tv_text03);
        mSatisfaction = (TextView) findViewById(R.id.satisfaction);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                onBackPressed();
                break;
            default:
                break;
        }
    }
}
