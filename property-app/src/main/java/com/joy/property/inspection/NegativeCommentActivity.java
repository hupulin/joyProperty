package com.joy.property.inspection;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.home.DataTo;
import com.jinyi.ihome.module.home.ServiceEvaluationParam;
import com.jinyi.ihome.module.home.ServiceMainTo;
import com.joy.common.api.ApiClient;
import com.joy.common.api.HomeApi;
import com.joy.common.api.HttpCallback;
import com.joy.library.fragment.CustomDialogFragment;
import com.joy.property.R;
import com.joy.property.utils.flowlayout.FlowLayout;
import com.joy.property.utils.flowlayout.TagAdapter;
import com.joy.property.utils.flowlayout.TagFlowLayout;
import com.joyhome.nacity.app.base.BaseActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * Created by usb on 2017/6/7.
 */

public class NegativeCommentActivity extends BaseActivity implements View.OnClickListener {
    private TagFlowLayout mFlowLayout;
    private EditText mEvaluation;
    private List<String> mNames=new ArrayList<>();
    private int [] select={};
    private List<String> mNamesSelect=new ArrayList<>();

    private List<TextView> flowViewList=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dailog_feedback_negative);
        getWindow().setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        findView();
        getData();
    }




    private void findView() {

        findViewById(R.id.back).setOnClickListener(this);
        findViewById(R.id.evaluation_submit).setOnClickListener(this);
        mFlowLayout = (TagFlowLayout) findViewById(R.id.flowlayout);
        mEvaluation = (EditText) findViewById(R.id.evaluation_content);
        mFlowLayout.setAdapter(mAdapter);

    }
    public static void sortArray(int[] targetArr) {
        long t = System.currentTimeMillis();
        Arrays.sort(targetArr);
        System.out.println("执行时间：" + (System.currentTimeMillis() - t) + "毫秒");
        System.out.println("排序后结果");
        for(int i : targetArr){
            System.out.print(i + "  ");
        }
    }
    private TagAdapter<String> mAdapter=new TagAdapter<String>(mNames){
        @Override
        public View getView(FlowLayout parent, int position, String s) {
            LayoutInflater mInflater = LayoutInflater.from(getThisContext());
            RelativeLayout mRelativeLayout = (RelativeLayout) mInflater.inflate(R.layout.tv_new,
                    mFlowLayout, false);
            TextView textView = (TextView) mRelativeLayout.findViewById(R.id.textView);
            textView.setText(s);
            flowViewList.add(textView);
            mFlowLayout.setOnSelectListener(new TagFlowLayout.OnSelectListener(){
                @Override
                public void onSelected(Set<Integer> selectPosSet)
                {
                    mNamesSelect.clear();
                    Object[] obj = selectPosSet.toArray();
                    select=new int [selectPosSet.size()];

                    for (int i = 0; i < obj.length; i++) {
                        select[i] = (int) obj[i];//将Object对象数组转为整型数组（强制向下转型）
                        flowViewList.get(select[i]);
                    }
                    sortArray(select);
                    for (int i = 0; i <select.length ; i++) {
                        mNamesSelect.add(mNames.get(select[i]));
                    }
                    Log.i("2222", "onSelected: "+mNamesSelect.toString());

                }
            });

            mFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                @Override
                public boolean onTagClick(View view, int position, com.joy.property.utils.flowlayout.FlowLayout parent)
                {
                    if (flowViewList.get(position).isSelected()){
                        flowViewList.get(position).setTextColor(Color.parseColor("#666666"));
                        flowViewList.get(position).setSelected(false);
                        flowViewList.get(position).setBackgroundDrawable(getResources().getDrawable(R.drawable.negative_comment_uncheck_bg));

                    }else{
                        flowViewList.get(position).setTextColor(Color.parseColor("#ffffff"));
                        flowViewList.get(position).setSelected(true);
                        flowViewList.get(position).setBackgroundDrawable(getResources().getDrawable(R.drawable.negative_comment_check_bg));
                    }
                    return true;
                }
            });
//
            return mRelativeLayout;
        }
    };

    private void getData() {

            HomeApi api = ApiClient.create(HomeApi.class);
            api.findEvaluateLabel("0", new HttpCallback<MessageTo<DataTo>>(this) {
                @Override
                public void success(MessageTo<DataTo> msg, Response response) {
                    {
                        if (msg.getSuccess() == 0) {
                            mNames.addAll(msg.getData().getList());
                            Log.i("2221", "success: "+mNames.toString());
                            Log.i("2222", "success: "+msg.getData().toString());
                            mAdapter.notifyDataChanged();
                        }
                    }
                }
                @Override
                public void failure(RetrofitError error) {
                    super.failure(error);
                    Log.i("2222", "11: "+error.toString());


                }
            });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode== KeyEvent.KEYCODE_BACK)
            onBackPressed();
        return super.onKeyDown(keyCode, event);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                onBackPressed();
                goToAnimation(2);
                break;
            case R.id.evaluation_submit:
                if (checking())
                    return;
                submit();
                break;
            case R.id.cancel:
                finish();
                break;
        }
    }
    private boolean checking() {
if(mNamesSelect.size()==0&&TextUtils.isEmpty(mEvaluation.getText().toString())){
        Toast.makeText(this, "请输入差评原因~~", Toast.LENGTH_SHORT).show();

        return true;
}        return false;

    }
    public static String listToString(List<String> stringList){
        if (stringList==null) {
            return null;
        }
        StringBuilder result=new StringBuilder();
        boolean flag=false;
        for (String string : stringList) {
            if (flag) {
                result.append(";");
            }else {
                flag=true;
            }
            result.append(string);
        }
        return result.toString();
    }
    private void submit() {
        ServiceEvaluationParam  mParam=(ServiceEvaluationParam)getIntent().getSerializableExtra("param");
        mParam.setBadEvaluationDescribe(listToString(mNamesSelect));
        mParam.setBadEvaluationRemark(mEvaluation.getText().toString());
        HomeApi api = ApiClient.create(HomeApi.class);
        final CustomDialogFragment dialogFragment = new CustomDialogFragment();
        Log.i("2222", "mParam: "+mParam);
        dialogFragment.show(getSupportFragmentManager(), "");
        api.addServiceEvaluation(mParam, new HttpCallback<MessageTo<ServiceMainTo>>(getThisContext()) {
            @Override
            public void success(MessageTo<ServiceMainTo> msg, Response response) {
                dialogFragment.dismissAllowingStateLoss();
                if (msg.getSuccess() == 0) {
                    Intent intent = new Intent(getThisContext(), CallDetailActivity.class);
                    intent.putExtra("sid", msg.getData().getServiceSid());
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getThisContext(), msg.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                dialogFragment.dismissAllowingStateLoss();
                super.failure(error);
            }
        });
    }
}