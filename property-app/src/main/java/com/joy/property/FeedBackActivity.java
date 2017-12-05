package com.joy.property;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.joy.common.api.ApiClient;
import com.joy.common.api.FeedBackApi;
import com.joy.common.api.HttpCallback;

import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.feedback.FeedbackParam;
import com.jinyi.ihome.module.feedback.FeedbackTo;
import com.joy.library.fragment.CustomDialogFragment;
import com.joy.property.base.BaseActivity;

import retrofit.RetrofitError;
import retrofit.client.Response;

public class FeedBackActivity extends BaseActivity
        implements OnClickListener {

    private EditText mContent;
    private EditText mContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        findById();
    }

    private void findById() {
        mBack = (ImageView) findViewById(R.id.back);
        mBack.setOnClickListener(this);
        TextView  mAdd = (TextView) findViewById(R.id.add);
        mAdd.setOnClickListener(this);
        mContent = (EditText) findViewById(R.id.content);
        mContact = (EditText) findViewById(R.id.feedbackContact);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                onBackPressed();
                break;
            case R.id.add:
                if (checking()) {
                    return;
                }
                submit();

                break;

        }
    }

    private void submit() {
        FeedBackApi api = ApiClient.create(FeedBackApi.class);
        FeedbackParam param = new FeedbackParam();
        param.setFeedbackContact(mContact.getText().toString());
        param.setFeedbackUserSid(mUserHelper.getSid());
        param.setFeedbackType(0);
        param.setFeedbackContent(mContent.getText().toString());
        final CustomDialogFragment dialogFragment = new CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "");
        api.addFeedbackInfo(param, new HttpCallback<MessageTo<FeedbackTo>>(this) {
            @Override
            public void success(MessageTo<FeedbackTo> msg, Response response) {
                dialogFragment.dismiss();
                if (msg.getSuccess() == 0) {

                    finish();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                dialogFragment.dismiss();
                super.failure(error);
            }
        });

    }

    private boolean checking() {
        if (TextUtils.isEmpty(mContent.getText().toString())) {

            Toast.makeText(this, "反馈内容不能为空哦，请输入", Toast.LENGTH_LONG).show();
            return true;
        }
        return false;
    }
}
