package com.joy.property.inspection;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.home.ServiceEvaluationParam;
import com.jinyi.ihome.module.home.ServiceMainExpandTo;
import com.jinyi.ihome.module.home.ServiceMainTo;
import com.jinyi.ihome.module.home.ServiceReturnParam;
import com.joy.common.api.ApiClient;
import com.joy.common.api.HomeApi;
import com.joy.common.api.HttpCallback;
import com.joy.library.fragment.CustomDialogFragment;
import com.joy.library.utils.DateUtil;
import com.joy.property.R;
import com.joy.property.base.BaseActivity;
import com.joy.property.common.PictureShowActivity;
import com.joy.property.myservice.MyServiceOrderActivity;
import com.joy.property.task.CheckEvaluateActivity;
import com.joy.property.task.LeaveMessageActivity;
import com.joy.property.utils.CustomDialog;
import com.joy.property.utils.StatuBarUtil;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Admin on 2015-02-03
 */
public class CallDetailActivity extends BaseActivity
        implements OnClickListener {


    private String mServiceSid = "";
    private ServiceMainExpandTo mainExpandTo;
    private TextView mCreateDate;
    private TextView mCreateTime;
    private TextView mServiceDesc;
    private LinearLayout llImage;
    private Button mCancelReport;
    private RelativeLayout llReply;
    private TextView tvHead;
    private TextView mRpDate;
    private TextView tvText;
    private RelativeLayout llOver;
    private RelativeLayout llButton;
    private TextView mRpDesc;
    private LinearLayout llRpImage;
    private RelativeLayout llResult;
    private TextView mResultEvaluation;
    private TextView tvNews;
    private TextView mUser;
    private TextView mEmergency;
    private TextView mApartmentName;
    private TextView mOwnerPhone;
    private TextView getmServiceNumbr;
    private Button EvaluationSubmit;
    private TextView mNotice;
    private TextView inLegalPlace;
    private TextView inLegalType;
    private TextView carNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        findById();
        initIntentDatas();
        loadNewsQty();
        initDatas();
    }


    private void  findById() {
        dialog = new CustomDialog(this,
                R.layout.dialog_feedback_evaluation, R.style.myDialogTheme);
        parent = (RelativeLayout)dialog.findViewById(R.id.parent);
        findViewById(R.id.back).setOnClickListener(this);
        mCreateDate = (TextView) findViewById(R.id.tv_date);
        mCreateTime = (TextView) findViewById(R.id.tv_time);
        getmServiceNumbr = (

                TextView) findViewById(R.id.tv_number);
        mEmergency = (TextView) findViewById(R.id.tv_emergency);
        mServiceDesc = (TextView)findViewById(R.id.tv_desc);
        llImage = (LinearLayout) findViewById(R.id.ll_image);
        mCancelReport = (Button) findViewById(R.id.cancel_report);
        mCancelReport.setOnClickListener(this);
        llReply = (RelativeLayout) findViewById(R.id.ll_reply);
        tvHead = (TextView) findViewById(R.id.tv_head);
        tvHead.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        tvHead.getPaint().setAntiAlias(true);
        mRpDate = (TextView) findViewById(R.id.rp_date);
        tvText = (TextView) findViewById(R.id.tv_text);
        llOver = (RelativeLayout) findViewById(R.id.ll_over);
        llButton = (RelativeLayout) findViewById(R.id.ll_button);
        mRpDesc = (TextView) findViewById(R.id.rp_desc);
        llRpImage = (LinearLayout) findViewById(R.id.rp_image);
        llResult = (RelativeLayout) findViewById(R.id.ll_result);
        mResultEvaluation = (TextView) findViewById(R.id.tv_result_evaluation);
        Button btnEvaluation = (Button) findViewById(R.id.btn_evaluation);
        btnEvaluation.setOnClickListener(this);
        Button btnUnSovled = (Button) findViewById(R.id.btn_unsolved);
        btnUnSovled.setOnClickListener(this);
        Button btnReadEvaluation = (Button) findViewById(R.id.btn_read_evaluation);
        btnReadEvaluation.setOnClickListener(this);
        TextView leaveMessage = (TextView) findViewById(R.id.leave_msg);
        leaveMessage.setOnClickListener(this);
        tvNews = (TextView) findViewById(R.id.tv_news);
        tvNews.setVisibility(View.INVISIBLE);
        mUser = (TextView) findViewById(R.id.user);
        mApartmentName = (TextView) findViewById(R.id.apartment);
        mOwnerPhone = (TextView) findViewById(R.id.tv_phone);
        mOwnerPhone.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        mOwnerPhone.getPaint().setAntiAlias(true);
        mOwnerPhone.setOnClickListener(this);
        mNotice = (TextView) findViewById(R.id.tv_notice);
        inLegalPlace = (TextView) findViewById(R.id.inLegalPlace);
        inLegalType = (TextView) findViewById(R.id.inLegalType);
        carNo=(TextView)findViewById(R.id.carNo);
    }

    private void initIntentDatas() {
        mServiceSid = getIntent().getStringExtra("sid");
    }


    /**
     * 获取留言数
     */
    private void loadNewsQty() {
        HomeApi api = ApiClient.create(HomeApi.class);
        api.getMessageWaitQtyByService(mServiceSid, new HttpCallback<MessageTo<Integer>>(this) {
            @Override
            public void success(MessageTo<Integer> msg, Response response) {
                if (msg.getSuccess() == 0) {
                    if (msg.getData() > 0) {
                        tvNews.setVisibility(View.VISIBLE);
                        tvNews.setText(msg.getData().toString());
                    } else {
                        tvNews.setVisibility(View.INVISIBLE);
                    }

                } else {
                    Toast.makeText(getThisContext(), msg.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                super.failure(error);

            }
        });

    }

    private void initDatas() {
        HomeApi api = ApiClient.create(HomeApi.class);
        final CustomDialogFragment dialogFragment = new CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "");
        api.findServiceMainExpandBySid(mServiceSid, new HttpCallback<MessageTo<ServiceMainExpandTo>>(this) {
            @Override
            public void success(MessageTo<ServiceMainExpandTo> msg, Response response) {
                dialogFragment.dismiss();
                if (msg.getSuccess() == 0) {
                    if (msg.getData() != null) {
                        mainExpandTo = msg.getData();
                        runOnUiThread(() -> {
                            init();
                        });
                    }
                } else {
                    Toast.makeText(getThisContext(),
                            msg.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                dialogFragment.dismissAllowingStateLoss();
                super.failure(error);
            }
        });
    }

    private void init() {
        Log.i("2222", "init: "+mainExpandTo.getStatusS());
        if ( mainExpandTo.getCreateUser() != null) {
            if (mainExpandTo.getCreateUser().getName().contains("(")||mainExpandTo.getCreateUser().getName().contains(")")) {
                mUser.setText( mainExpandTo.getCreateUser().getName());
            }else {
                if (!TextUtils.isEmpty(mainExpandTo.getServiceOwnerNo())) {
                    mUser.setText(mainExpandTo.getServiceOwnerNo()+"("+mainExpandTo.getCreateUser().getName() +")");
                }else {
                    mUser.setText( mainExpandTo.getCreateUser().getName());
                }

            }

        }
        if (!TextUtils.isEmpty(mainExpandTo.getServiceNo())) {

            getmServiceNumbr.setText("服务编号："+mainExpandTo.getServiceNo());
        }
        //紧急程度
        if (!TextUtils.isEmpty(mainExpandTo.getServiceEmergenctStatus())) {
            mEmergency.setVisibility(View.VISIBLE);
            String serialnumber = String.format("<font color='#bebebe'>%s</font><font color ='#46b4d9'>%s</font>" , "紧急程度：",mainExpandTo.getServiceEmergenctStatus());
            mEmergency.setText(Html.fromHtml(serialnumber));
        }else {
            mEmergency.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(mainExpandTo.getIllegallyParkedNotion())) {
            mNotice.setVisibility(View.VISIBLE);
            String serialnumber = String.format("<font color='#bebebe'>%s</font><font color ='#46b4d9'>%s</font>" , "违停通知单：","0".equals(mainExpandTo.getIllegallyParkedNotion())?"无":"有");
            mNotice.setText(Html.fromHtml(serialnumber));
        }else {
            mNotice.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(mainExpandTo.getPostionName())) {
            inLegalPlace.setVisibility(View.VISIBLE);
            String serialnumber = String.format("<font color='#bebebe'>%s</font><font color ='#46b4d9'>%s</font>" , "违停地点：",mainExpandTo.getPostionName());
            inLegalPlace.setText(Html.fromHtml(serialnumber));
        }else {
            inLegalPlace.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(mainExpandTo.getCarNo())) {
            carNo.setVisibility(View.VISIBLE);
            String serialnumber = String.format("<font color='#bebebe'>%s</font><font color ='#46b4d9'>%s</font>" , "车牌号码：",mainExpandTo.getCarNo());
            carNo.setText(Html.fromHtml(serialnumber));
        }else {
            carNo.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(mainExpandTo.getTypeName())&&getIntent().getBooleanExtra("InlegalType",false)) {
            inLegalType.setVisibility(View.VISIBLE);
            String serialnumber = String.format("<font color='#bebebe'>%s</font><font color ='#46b4d9'>%s</font>" , "违停类型：",mainExpandTo.getTypeName());
            inLegalType.setText(Html.fromHtml(serialnumber));
        }else {
            inLegalType.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(mainExpandTo.getApartmentName())) {
            mApartmentName.setText(mainExpandTo.getApartmentName());
        }


        if (mainExpandTo.getCreatedOn() != null) {
            mCreateDate.setText(DateUtil.getDateTimeFormat(DateUtil.mDateFormatString, mainExpandTo.getCreatedOn()));
            mCreateTime.setText(DateUtil.getDateTimeFormat(DateUtil.mTimeFormat, mainExpandTo.getCreatedOn()));
        }

        if (!TextUtils.isEmpty(mainExpandTo.getServiceDesc())) {
            mServiceDesc.setText(mainExpandTo.getServiceDesc());
        }
        if (!TextUtils.isEmpty(mainExpandTo.getServiceDesc())&&!TextUtils.isEmpty(mainExpandTo.getRoomNo())) {
            mServiceDesc.setText((TextUtils.isEmpty(mainExpandTo.getServiceContact())?"":(mainExpandTo.getServiceContact()+";"))+mainExpandTo.getRoomNo()+";"+(TextUtils.isEmpty(mainExpandTo.getServicePhone())?"":mainExpandTo.getServicePhone()+";")+mainExpandTo.getServiceDesc());
        }


        if (!TextUtils.isEmpty(mainExpandTo.getServiceImages())) {
            String[] path = mainExpandTo.getServiceImages().split(";");
            for (String s : path) {
                int width = getScreenWidthPixels(this) - 138;
                LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(width / 4, width / 4);
                ImageView image = new ImageView(this);
                displayImage(image, s);
                param.leftMargin = 13;
                image.setScaleType(ImageView.ScaleType.FIT_XY);
                llImage.addView(image, param);
                image.setTag(mainExpandTo.getServiceImages());
                image.setOnClickListener((View v)-> {
                        String mPath = (String) v.getTag();
                        Intent intent = new Intent(getThisContext(), PictureShowActivity.class);
                        intent.putExtra("path", mPath);
                        startActivity(intent);

                });
            }
        }
        if ("1".equals(mainExpandTo.getServiceStatus())) {
           mCancelReport.setVisibility(View.VISIBLE);
        }else if ("2".equals(mainExpandTo.getServiceStatus())||"22".equals(mainExpandTo.getServiceStatus())||"20".equals(mainExpandTo.getServiceStatus())||"21".equals(mainExpandTo.getServiceStatus())) {
            mCancelReport.setVisibility(View.GONE);
            llReply.setVisibility(View.VISIBLE);
            if (mainExpandTo.getResponseUser() != null) {
                tvHead.setText(mainExpandTo.getResponseUser().getName());
                mOwnerPhone.setText(mainExpandTo.getResponseUser().getPhone());
            }

            if (mainExpandTo.getResponseTime() != null) {
                mRpDate.setText(DateUtil.getDateTimeFormat(DateUtil.mFormatDateTimeString, mainExpandTo.getResponseTime()));

            }

            tvText.setText("呼叫已被响应!物业小伙伴正在积极的处理中!");
            tvText.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.no_response_ic, 0, 0);
            tvText.setCompoundDrawablePadding(13);

        } else if ("4".equals(mainExpandTo.getServiceStatus())) {//待评论
            mCancelReport.setVisibility(View.GONE);
            llReply.setVisibility(View.VISIBLE);
            llOver.setVisibility(View.VISIBLE);
            tvText.setVisibility(View.GONE);
            if (mainExpandTo.getResponseUser() != null) {
                tvHead.setText(mainExpandTo.getResponseUser().getName());
                mOwnerPhone.setText(mainExpandTo.getResponseUser().getPhone());
            }


            llButton.setVisibility(View.VISIBLE);
            if (mainExpandTo.getResponseTime() != null) {
                mRpDate.setText(DateUtil.getDateTimeFormat(DateUtil.mFormatDateTimeString, mainExpandTo.getResponseTime()));

            }

            if (!TextUtils.isEmpty(mainExpandTo.getReplyDesc())) {
                mRpDesc.setText(mainExpandTo.getReplyDesc());

            }

            if (!TextUtils.isEmpty(mainExpandTo.getReplyImages())) {
                String imageId[] = mainExpandTo.getReplyImages().split(";");
                for (String s : imageId) {

                    int width = getScreenWidthPixels(this) - 138;
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width / 4, width / 4);
                    params.leftMargin = 13;
                    ImageView rpImage = new ImageView(this);
                    rpImage.setTag(mainExpandTo.getReplyImages());
                    rpImage.setScaleType(ImageView.ScaleType.FIT_XY);
                    displayImage(rpImage, s);
                    rpImage.setOnClickListener((View v)-> {
                            Intent intent = new Intent(getThisContext(), PictureShowActivity.class);
                            intent.putExtra("path", (String) v.getTag());
                            startActivity(intent);

                    });
                    llRpImage.addView(rpImage, params);
                }
            }


        } else if ("6".equals(mainExpandTo.getServiceStatus()) || "9".equals(mainExpandTo.getServiceStatus())) { //处理结束
            mCancelReport.setVisibility(View.GONE);
            llReply.setVisibility(View.VISIBLE);
            tvText.setVisibility(View.GONE);
            llResult.setVisibility(View.VISIBLE);
            if (mainExpandTo.getResponseUser() != null) {
                tvHead.setText(mainExpandTo.getResponseUser().getName());
                mOwnerPhone.setText(mainExpandTo.getResponseUser().getPhone());
            }

            if (mainExpandTo.getResponseTime() != null) {
                mRpDate.setText(DateUtil.getDateTimeFormat(DateUtil.mFormatDateTimeString, mainExpandTo.getResponseTime()));

            }
            if (!TextUtils.isEmpty(mainExpandTo.getReplyDesc())) {
                mRpDesc.setText(mainExpandTo.getReplyDesc());
            }

            if (!TextUtils.isEmpty(mainExpandTo.getReplyImages())) {
                String imageId[] = mainExpandTo.getReplyImages().split(";");
                for (String s : imageId) {
                    int width = getScreenWidthPixels(this) - 138;
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width / 4, width / 4);
                    ImageView rpImage = new ImageView(this);
                    params.leftMargin = 13;
                    rpImage.setTag(mainExpandTo.getReplyImages());
                    rpImage.setScaleType(ImageView.ScaleType.FIT_XY);
                    displayImage(rpImage, s);
                    rpImage.setOnClickListener((View v)-> {
                            Intent intent = new Intent(getThisContext(), PictureShowActivity.class);
                            intent.putExtra("path", (String) v.getTag());
                            startActivity(intent);

                    });
                    llRpImage.addView(rpImage, params);
                }
            }
            llButton.setVisibility(View.GONE);
//            if (mainExpandTo.getEvaluationItem3() != null) {
//                if (mainExpandTo.getEvaluationItem3() > 0 && mainExpandTo.getEvaluationItem3() < 3) {
//                    mResultEvaluation.setText("我对这次问题的解决感到十分不满！");
//                } else if (mainExpandTo.getEvaluationItem3() == 3) {
//                    mResultEvaluation.setText("我对这次问题的解决感到一般！");
//                } else if (mainExpandTo.getEvaluationItem3() > 3) {
//                    mResultEvaluation.setText("我对这次问题的解决感到十分满意！");
//                }
//            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                onBackPressed();
                break;

            case R.id.tv_phone:
                dialogDialPhoneShow();
                break;
            case R.id.leave_msg:
                //跳至留言页
                // tvNews.setVisibility(View.INVISIBLE);
                Intent intent = new Intent(this, LeaveMessageActivity.class);
                intent.putExtra("mode", mainExpandTo);
                startActivity(intent);
                break;
            case R.id.btn_read_evaluation:
//                readEvaluationShow();
                Intent intentEvaluate = new Intent(getThisContext(), CheckEvaluateActivity.class);
                intentEvaluate.putExtra("mode",mainExpandTo);
                Log.i("222222", "mainExpandTo: "+mainExpandTo.getEvaluationItem1());
                startActivity(intentEvaluate);
                break;
            case R.id.btn_evaluation:
                evaluationShow();
                break;
            case R.id.btn_unsolved:
                unsolvedShow();
                break;
            case R.id.cancel_report:
                cancelReportShowDialog();
                break;
            default:
                break;
        }

    }

    private void dialogDialPhoneShow() {

     final CustomDialog dialog = new CustomDialog(getThisContext() ,
             R.layout.dialog_detele_msg,R.style.myDialogTheme);
        Button btnAdd = (Button) dialog.findViewById(R.id.btn_add);
        Button btnCancel = (Button) dialog.findViewById(R.id.btn_cancel);

     TextView mTip = (TextView) dialog.findViewById(R.id.tip);
        mTip.setText("即将为您联系此负责人");
       btnAdd.setText("取消");
        btnCancel.setText("确定");
        btnAdd.setOnClickListener((View v)-> {
                dialog.dismiss();

        });

        btnCancel.setOnClickListener((View v)-> {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+mainExpandTo.getResponseUser().getPhone()));
                startActivity(intent);
                dialog.dismiss();

        });

        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

    }

    private void cancelReportShowDialog() {
        final CustomDialog dialog = new CustomDialog(this, R.layout.dialog_detele_msg, R.style.myDialogTheme);
        TextView mTip = (TextView) dialog.findViewById(R.id.tip);
        mTip.setText("确定要撤销这条提报吗？");
        Button btnCancel = (Button) dialog.findViewById(R.id.btn_cancel);
        Button btnAdd = (Button) dialog.findViewById(R.id.btn_add);
        btnCancel.setOnClickListener((View v)-> {
                dialog.dismiss();

        });

        btnAdd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeApi api = ApiClient.create(HomeApi.class);
                api.revokeServiceMain(mainExpandTo.getServiceSid(), mUserHelper.getSid(), new HttpCallback<MessageTo<ServiceMainTo>>(getThisContext()) {
                    @Override
                    public void success(MessageTo<ServiceMainTo> msg, Response response) {
                        if (msg.getSuccess() == 0) {
                            if("0".equals(getIntent().getStringExtra("type"))){
                            Intent intent = new Intent(getThisContext(), VillageInspectionActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.putExtra("value", "0");
                            startActivity(intent);
                            finish();}
                            else if("1".equals(getIntent().getStringExtra("type"))){
                                Intent intent = new Intent(getThisContext(), MyServiceOrderActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent.putExtra("value", "0");
                                startActivity(intent);
                                finish();
                            }
                        } else {
                            Toast.makeText(getThisContext(), msg.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        super.failure(error);
                    }
                });
            }
        });

        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    private void unsolvedShow() {
        final CustomDialog dialog = new CustomDialog(this, R.layout.dialog_return_deal, R.style.myDialogTheme);
        ImageView unsolvedClose = (ImageView) dialog.findViewById(R.id.unsolved_close);
        final EditText unsolvedReason = (EditText) dialog.findViewById(R.id.unsolved_reason);
        Button btnSubmit = (Button) dialog.findViewById(R.id.btn_submit);
        btnSubmit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(unsolvedReason.getText())) {
                    Toast.makeText(getThisContext(), "请输入未解决原因，以便于您的问题被准确处理",
                            Toast.LENGTH_LONG).show();
                } else {
                    ServiceReturnParam mParams = new ServiceReturnParam();
                    mParams.setOwnerSid(mUserHelper.getSid());
                    mParams.setServiceSid(mainExpandTo.getServiceSid());
                    mParams.setReturnDesc(unsolvedReason.getText().toString());
                    HomeApi api = ApiClient.create(HomeApi.class);
                    final CustomDialogFragment dialogFragment = new CustomDialogFragment();
                    dialogFragment.show(getSupportFragmentManager(), "");
                    api.addServiceReturn(mParams, new HttpCallback<MessageTo<ServiceMainTo>>(getThisContext()) {
                        @Override
                        public void success(MessageTo<ServiceMainTo> msg, Response response) {
                            dialogFragment.dismissAllowingStateLoss();
                            if (msg.getSuccess() == 0) {
                                Intent intent = new Intent(getThisContext(), CallDetailActivity.class);
                                intent.putExtra("sid", msg.getData().getServiceSid());
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
        });
        unsolvedClose.setOnClickListener((View v)-> {
                dialog.dismiss();

        });

        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();

    }
    private  RelativeLayout parent;
    private  CustomDialog dialog;
    private void evaluationShow() {
        final EditText evaluationContent = (EditText) dialog.findViewById(R.id.evaluation_content);
        final RatingBar mRate01 = (RatingBar) dialog.findViewById(R.id.rb_rate01);
        final RatingBar mRate02 = (RatingBar) dialog.findViewById(R.id.rb_rate02);
        final RatingBar mRate03 = (RatingBar) dialog.findViewById(R.id.rb_rate03);
        final TextView mText01 = (TextView)dialog.findViewById(R.id.tv_text01);
        final TextView mText02 = (TextView)dialog.findViewById(R.id.tv_text02);
        final TextView mText03 = (TextView)dialog.findViewById(R.id.tv_text03);
        final TextView evaluationDesc = (TextView) dialog.findViewById(R.id.evaluation_desc);
        final TextView tip = (TextView) dialog.findViewById(R.id.tip);
        ImageView evaluationClose = (ImageView) dialog.findViewById(R.id.evaluation_close);
        EvaluationSubmit = (Button) dialog.findViewById(R.id.evaluation_submit);
        evaluationContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tip.setText("还可以输入" + (100 - s.length()) + "字");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        mRate01.setOnRatingBarChangeListener((RatingBar ratingBar, float rating, boolean fromUser)-> {
                if (rating < 2.0f) {
                    mRate01.setRating(1.0f);
                }
            if (rating == 1.0f) {
                mText01.setText("差评");
            } else if (rating == 2.0f) {
                mText01.setText("不满");
            } else if (rating == 3.0f) {
                mText01.setText("一般");
            } else if (rating == 4.0f) {
                mText01.setText("满意");
            } else if (rating == 5.0f) {
                mText01.setText("惊喜");
            }
            numOne=(int) mRate01.getRating();
            initButton();

        });
        mRate02.setOnRatingBarChangeListener((RatingBar ratingBar, float rating, boolean fromUser)-> {
                if (rating < 2.0f) {

                    mRate02.setRating(1.0f);
                }
            if (rating == 1.0f) {
                mText02.setText("差评");
            } else if (rating == 2.0f) {
                mText02.setText("不满");
            } else if (rating == 3.0f) {
                mText02.setText("一般");
            } else if (rating == 4.0f) {
                mText02.setText("满意");
            } else if (rating == 5.0f) {
                mText02.setText("惊喜");
            }
            numTwo=(int) mRate02.getRating();
initButton();
        });
        mRate03.setOnRatingBarChangeListener((RatingBar ratingBar, float rating, boolean fromUser)-> {
                if (rating < 2.0f) {
                    mRate03.setRating(1.0f);

                }
            if (rating == 1.0f) {
                mText03.setText("差评");
            } else if (rating == 2.0f) {
                mText03.setText("不满");
            } else if (rating == 3.0f) {
                mText03.setText("一般");
            } else if (rating == 4.0f) {
                mText03.setText("满意");
            } else if (rating == 5.0f) {
                mText03.setText("惊喜");
            }
            numThree=(int) mRate03.getRating();
            initButton();

        });

        evaluationClose.setOnClickListener((View v)-> {

                dialog.dismiss();
            StatuBarUtil.setStatueBarBlueColor(getWindow());


        });

        EvaluationSubmit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//                int total=numOne+numTwo+numThree;
//                Log.i("222", "onClick: "+total);
                ServiceEvaluationParam mParam = new ServiceEvaluationParam();
                mParam.setOwnerSid(mUserHelper.getSid());
                mParam.setEvaluationDesc(evaluationContent.getText().toString());
                mParam.setEvaluationItem1(numOne);
                mParam.setEvaluationItem2(numTwo);
                mParam.setEvaluationItem3(numThree);
                mParam.setServiceSid(mainExpandTo.getServiceSid());
                HomeApi api = ApiClient.create(HomeApi.class);
                if(numOne<3||numTwo<3||numThree<3){
                    Intent intent = new Intent(getThisContext(), NegativeCommentActivity.class);
                    intent.putExtra("param",mParam);
                    StatuBarUtil.setStatueBarReactionColor(getWindow());
                    parent.setVisibility(View.INVISIBLE);
                    startActivity(intent);
                }else {
                    final CustomDialogFragment dialogFragment = new CustomDialogFragment();
                    dialogFragment.show(getSupportFragmentManager(), "");
                     api.addServiceEvaluation(mParam, new HttpCallback<MessageTo<ServiceMainTo>>(getThisContext()) {
                         @Override
                        public void success(MessageTo<ServiceMainTo> msg, Response response) {
                        dialogFragment.dismissAllowingStateLoss();
                        if (msg.getSuccess() == 0) {
                            Intent intent = new Intent(getThisContext(), CallDetailActivity.class);
                            intent.putExtra("sid", msg.getData().getServiceSid());
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
        });
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(false);
        dialog.show();
    }

    private void initButton() {
        if(numOne<3||numTwo<3||numThree<3){
            EvaluationSubmit.setText("填写差评原因");
        }else {
            EvaluationSubmit.setText("提交");

        }
    }

    private int numOne=5;
private int numTwo=5;
private int numThree=5;

    private void readEvaluationShow() {
        final CustomDialog dialog = new CustomDialog(this,
                R.layout.dialog_read_evaluation, R.style.myDialogTheme);
        ImageView ivReadClose = (ImageView) dialog.findViewById(R.id.read_close);
        ivReadClose.setOnClickListener((View v)-> {
                dialog.dismiss();

        });
        TextView mContent = (TextView) dialog.findViewById(R.id.my_evaluation_content);
        if (!TextUtils.isEmpty(mainExpandTo.getEvaluationDesc())) {
            mContent.setText(mainExpandTo.getEvaluationDesc());
        }
        RatingBar mRate01 = (RatingBar) dialog.findViewById(R.id.rb_rate01);
        RatingBar mRate02 = (RatingBar) dialog.findViewById(R.id.rb_rate02);
        RatingBar mRate03 = (RatingBar) dialog.findViewById(R.id.rb_rate03);
        final TextView mText01 = (TextView)dialog.findViewById(R.id.tv_text01);
        final TextView mText02 = (TextView)dialog.findViewById(R.id.tv_text02);
        final TextView mText03 = (TextView)dialog.findViewById(R.id.tv_text03);
//        Log.i("2222221", "mainExpandTo: "+mainExpandTo.getEvaluationItem1());
//        Log.i("2222222", "mainExpandTo: "+mainExpandTo.getEvaluationItem2());
//        Log.i("2222223", "mainExpandTo: "+mainExpandTo.getEvaluationItem3());
        if (mainExpandTo.getEvaluationItem1() != null) {
            mRate01.setRating((float) mainExpandTo.getEvaluationItem1());
        }

        if (mainExpandTo.getEvaluationItem2() != null) {
            mRate02.setRating((float) mainExpandTo.getEvaluationItem2());
        }
        if (mainExpandTo.getEvaluationItem3() != null) {
            mRate03.setRating((float) mainExpandTo.getEvaluationItem3());
        }

        final TextView mSatisfaction = (TextView) dialog.findViewById(R.id.satisfaction);
        if (mainExpandTo.getEvaluationItem3() != null) {
            if (mainExpandTo.getEvaluationItem3() > 0 && mainExpandTo.getEvaluationItem3() < 3) {
                mSatisfaction.setText("我对这次问题的解决感到十分不满！");
            } else if (mainExpandTo.getEvaluationItem3() == 3) {
                mSatisfaction.setText("业主对这次问题的解决感到一般！");
            } else if (mainExpandTo.getEvaluationItem3() > 3) {
                mSatisfaction.setText("业主对这次问题的解决感到十分满意！");
            }

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

        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    protected Context getThisContext() {
        return CallDetailActivity.this;
    }

    @Override
    protected String toPageName() {
        return "小区巡检--呼叫详情页";
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        if(EvaluationSubmit!=null){
            EvaluationSubmit.setEnabled(true);//将触发控件设置为可用
        }

    }
    @Override
    protected void onResume() {
        StatuBarUtil.setStatueBarBlueColor(getWindow());

            Log.i("22222", "onRestart: 1111111111111111");
            parent.setVisibility(View.VISIBLE);

        super.onResume();
    }

}
