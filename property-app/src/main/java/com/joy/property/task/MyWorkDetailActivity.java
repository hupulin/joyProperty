package com.joy.property.task;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.apartment.ApartmentInfoTo;
import com.jinyi.ihome.module.home.ServiceForwardParam;
import com.jinyi.ihome.module.home.ServiceHistTo;
import com.jinyi.ihome.module.home.ServiceMainExpandTo;
import com.jinyi.ihome.module.home.ServiceMyWorkTo;
import com.jinyi.ihome.module.home.ServiceResponseParam;
import com.jinyi.ihome.module.owner.UserInfoTo;
import com.jinyi.ihome.module.system.GroupMenuParam;
import com.jinyi.ihome.module.system.GroupMenuTo;
import com.joy.common.api.ApiClient;
import com.joy.common.api.HomeApi;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.SystemApi;
import com.joy.library.fragment.CustomDialogFragment;
import com.joy.library.utils.DateUtil;
import com.joy.property.MainApplication;
import com.joy.property.R;
import com.joy.property.base.BaseActivity;
import com.joy.property.common.PictureShowActivity;
import com.joy.property.complaint.ComplaintManagerActivity;
import com.joy.property.house.HouseManagerActivity;
import com.joy.property.myservice.SelectServiceActivity;
import com.joy.property.repairs.RepairsManagementActivity;
import com.joy.property.repairs.RoomRepairsManagementActivity;
import com.joy.property.task.TimePickerDialog.TimePickerExpect;
import com.joy.property.task.TimePickerDialog.data.Type;
import com.joy.property.task.TimePickerDialog.listener.OnDateSetListenerNew;
import com.joy.property.utils.CustomDialog;
import com.joy.property.utils.NetTimeUtil;
import com.joy.property.vehicle.adapter.RefreshEventOther;
import com.joyhome.nacity.app.photo.util.PublicWay;
import com.wefika.flowlayout.FlowLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by usb on 2017/6/15.
 */

public class MyWorkDetailActivity extends BaseActivity implements View.OnClickListener,OnDateSetListenerNew {
    private static final int TASK_DETAILS = 2;
    private PopupWindow pw;

    private Button mSubmit;
    private TextView mUser;
    private TextView mDate;
    private TextView mTip;
    private TextView mContent;
    private ServiceMyWorkTo mainExpandTo;
    private TextView mForward;
    private TextView change;
    private TextView mApartment;
    private TextView mTitle;
    private FlowLayout flowLayout;
    private String mWorkSid = "";
    private LinearLayout llShaft;
    private LinearLayout llHist;
    private TextView mProgress;
    private Button mReceive;
    private TextView mServicePhone;
    private TextView mServiceAddress;
    private TextView mServiceTime;
    private TextView mTextAddress;
    private TextView mTextTime;
    private RelativeLayout mLayoutContact;
    private TextView mServiceFee;
    private TextView mTextServiceFee;
    private TextView mSerialNumber;
    private int mValue;
    private String type;
    private TextView mServiceQtyDesc;
    ApartmentInfoTo infoTo;
    Intent intent;
    LinearLayout llItem04;
    private String mStatus = "";
    private int status;
    private boolean flag = true;//默认为开始
    private RelativeLayout parent;

    private boolean is_submit = false;
    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);


    private List<GroupMenuTo> menuToList = new ArrayList<>();
    private GroupMenuTo menuTo = null;

    private boolean isFirstRegist;
    private String mApartmentSid = "";
    private String mDoneToBack = "";
    private MainApplication application;
    private TextView mNotice;
    private TextView inLegalPlace;
    private TextView inLegalType;
    private TextView carNo;
    private TimePickerExpect mimePickerExpect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_task_deatail);
        application = (MainApplication) getThisContext().getApplicationContext();
        findById();
        initIntentDatas();
//       getMessageQty();
        initDatas();
        NetTimeUtil.getNetTimeLong();

        mimePickerExpect = new TimePickerExpect.Builder()
                .setType(Type.ALL)
                .setCallBack(this)
//                .setCancelStringId("取消")
                .setSureStringId("确定")
                .setTitleStringId("预计完成时间")
                .setYearText("年")
                .setMonthText("月")
//                .setWheelItemTextNormalColor(getResources().getColor(R.color.main_gray_text_color))
//                .setWheelItemTextSelectorColor(getResources().getColor(R.color.text_color05))
                .setThemeColor(getResources().getColor(R.color.page_title_color))
                .setDayText("日")
                .setWheelItemTextSize(14)
                .setHourText("：00")
                .setMinMillseconds(PublicWay.currentNetTime)
                .setCyclic(false)
                .buildNew();

//        if(infoTo!=null)
//            Toast.makeText(this,infoTo.getStartTime()+"和"+infoTo.getEndTime(),Toast.LENGTH_SHORT).show();
    }

    ////////////////////////////////////
    public static int generateViewId() {
        for (; ; ) {
            final int result = sNextGeneratedId.get();
            // aapt-generated IDs have the high byte nonzero; clamp to the range under that.
            int newValue = result + 1;
            if (newValue > 0x00FFFFFF) newValue = 1; // Roll over to 1, not 0.
            if (sNextGeneratedId.compareAndSet(result, newValue)) {
                return result;
            }
        }
    }

    private void findById() {
        parent = (RelativeLayout) findViewById(R.id.parent);

        findViewById(R.id.back).setOnClickListener(this);
        mUser = (TextView) findViewById(R.id.user);
        ImageButton mMessage = (ImageButton) findViewById(R.id.message);
        mMessage.setVisibility(View.GONE);
        ImageButton phone = (ImageButton) findViewById(R.id.phone);
        phone.setVisibility(View.GONE);
        ImageButton mPhone = (ImageButton) findViewById(R.id.phone);
        mPhone.setOnClickListener(this);
        mTip = (TextView) findViewById(R.id.tip);
        mTip.setVisibility(View.INVISIBLE);
        mServiceFee = (TextView) findViewById(R.id.service_fee);
        mServiceFee.setVisibility(View.GONE);
        mDate = (TextView) findViewById(R.id.date);
        mContent = (TextView) findViewById(R.id.content);
        mForward = (TextView) findViewById(R.id.forward);
        change = (TextView) findViewById(R.id.change);
        mForward.setVisibility(View.GONE);
        change.setVisibility(View.GONE);
        mApartment = (TextView) findViewById(R.id.apartment);
        mTitle = (TextView) findViewById(R.id.tv_title);
        mSubmit = (Button) findViewById(R.id.submit);
        mSubmit.setOnClickListener(this);
        flowLayout = (FlowLayout) findViewById(R.id.flowLayout);
        llShaft = (LinearLayout) findViewById(R.id.ll_shaft);
        llHist = (LinearLayout) findViewById(R.id.ll_hist);
        mProgress = (TextView) findViewById(R.id.progress);
        mReceive = (Button) findViewById(R.id.btn_receive);
        mReceive.setVisibility(View.GONE);
        mReceive.setOnClickListener(this);
        mTextAddress = (TextView) findViewById(R.id.text_address);
        mTextTime = (TextView) findViewById(R.id.text_time);
        mServiceAddress = (TextView) findViewById(R.id.service_address);
        mServicePhone = (TextView) findViewById(R.id.service_phone);
        mServiceTime = (TextView) findViewById(R.id.service_time);
        mLayoutContact = (RelativeLayout) findViewById(R.id.layout_contact);
        mLayoutContact.setVisibility(View.GONE);
        mTextServiceFee = (TextView) findViewById(R.id.text_fee);
        mTextServiceFee.setVisibility(View.GONE);
        mServiceQtyDesc = (TextView) findViewById(R.id.service_qty_desc);
        mServiceQtyDesc.setVisibility(View.GONE);

        mSerialNumber = (TextView) findViewById(R.id.serial_num);

        mNotice = (TextView) findViewById(R.id.tv_notice);
        inLegalPlace = (TextView) findViewById(R.id.inLegalPlace);
        inLegalType = (TextView) findViewById(R.id.inLegalType);
        carNo=(TextView)findViewById(R.id.carNo);


    }

    private void initIntentDatas() {

        intent = this.getIntent();
        Bundle bunde = intent.getExtras();

            /* 取得Bundle对象中的数据 */
        mWorkSid = bunde.getString("sid");
        mValue = bunde.getInt("value", 0);

    }

    /**
     * 获取留言数
     */
//    private void getMessageQty() {
//        HomeApi api = ApiClient.create(HomeApi.class);
//        final CustomDialogFragment dialogFragment = new CustomDialogFragment();
//        dialogFragment.show(getSupportFragmentManager(), "");
//        api.getMessageWaitQtyByService(mWorkSid, new HttpCallback<MessageTo<Integer>>(this) {
//            @Override
//            public void success(MessageTo<Integer> msg, Response response) {
//                dialogFragment.dismissAllowingStateLoss();
//                if (msg.getSuccess() == 0) {
//                    if (msg.getData() > 0) {
//                        mTip.setVisibility(View.VISIBLE);
//                        mTip.setText(String.valueOf(msg.getData()));
//                    }
//
//                } else {
//                    Toast.makeText(getThisContext(), msg.getMessage(),
//                            Toast.LENGTH_LONG).show();
//                }
//            }
//
//            @Override
//            public void failure(RetrofitError error) {
//                dialogFragment.dismissAllowingStateLoss();
//                super.failure(error);
//            }
//        });
//
//
//    }

    private void initDatas() {
        if (flowLayout != null) {
            flowLayout.removeAllViews();
        }
        if (llHist != null) {
            llHist.removeAllViews();
        }
        if (llShaft != null) {
            llShaft.removeAllViews();
        }

        HomeApi api = ApiClient.create(HomeApi.class);
        System.out.println(mWorkSid+"serviceSid");
        api.getMyWorkById(mWorkSid, new HttpCallback<MessageTo<ServiceMyWorkTo>>(this) {
            @Override
            public void success(MessageTo<ServiceMyWorkTo> msg, Response response) {
                if (msg.getSuccess() == 0) {

                    mainExpandTo = msg.getData();
                    init();
                } else {
                    Toast.makeText(getThisContext(), msg.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                super.failure(error);
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void init() {
        Log.i("222", "mainExpandTo: "+mainExpandTo.getWorkStatus());
//（2：物业响应--已派单 22:处理中 6：流程结束，9：关闭）
        mStatus =mainExpandTo.getWorkStatus();
        if (mStatus != null) {

            status = Integer.parseInt(mStatus);
            if (status == 2) {
                flag = true;
                mSubmit.setText("开始处理");
            }
            if (status == 22) {
                flag = false;
                mSubmit.setText("处理完成并反馈");
            }
            if (status == 21) {
                flag = false;
                mSubmit.setText("处理完成并反馈");
            }
        }
        mTitle.setText(mainExpandTo.getTypeName());
        //  System.out.println(mainExpandTo.getTypeName() + "name");


            if ("4".equals(mainExpandTo.getWorkStatus())|| "6".equals(mainExpandTo.getWorkStatus())|| "9".equals(mainExpandTo.getWorkStatus())) {
                mSubmit.setVisibility(View.GONE);
        mainExpandTo.getWorkStatus();

            } else if ("1".equals(mainExpandTo.getWorkStatus())) {

                mReceive.setVisibility(View.GONE);
                mSubmit.setVisibility(View.GONE);
            } else {

                mSubmit.setVisibility(View.VISIBLE);

            }

        if (!TextUtils.isEmpty(mainExpandTo.getApartmentName())) {
            mApartment.setText(mainExpandTo.getApartmentName());
            //System.out.println(mainExpandTo.getApartmentName().toString() + "name");workNo
        }
        if (!TextUtils.isEmpty(mainExpandTo.getWorkNo())) {
            mSerialNumber.setText("服务编号："+mainExpandTo.getWorkNo());
        }


        if (!TextUtils.isEmpty(mainExpandTo.getRemark())) {
            mContent.setText(mainExpandTo.getRemark());
        }

        if (mainExpandTo.getCallerTime() != null) {
            mDate.setText( mainExpandTo.getCallerTime());

        }

        if (!TextUtils.isEmpty(mainExpandTo.getCallerTime())) {
            String mStr = String.format("<font color='#bebebe'>%s</font><font color='#faca39'>%s</font>", "任务已开始", mainExpandTo.getDistanceTimes());
            mProgress.setText(Html.fromHtml(mStr));
        }

        /***
         * 服务呼叫状态
         * 1 呼叫
         * 2 物业响应
         * 20 转发
         * 21 退回
         * 4 处理完成，等待评价
         * 6 流程结束
         * 22 处理中
         * 9 关闭
         * */
        if (mainExpandTo.getCallerUserName() != null) {
            mUser.setText(mainExpandTo.getCallerUserName());
        } else {
            mUser.setText("(" + mainExpandTo.getCallerUserName() + ")");
        }

        if (mainExpandTo.getHistList() != null && mainExpandTo.getHistList().size() > 0) {
            final List<ServiceHistTo> histToList = new ArrayList<>();
            histToList.addAll(mainExpandTo.getHistList());
            // System.out.println(mainExpandTo.getHistList().toString() + "aaaa");
            LayoutInflater inflater = LayoutInflater.from(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(2, 180);
            View view;
            for (int i = 0; i < histToList.size(); i++) {
                llShaft.setGravity(Gravity.CENTER_HORIZONTAL);
                switch (histToList.get(i).getHistType()) {
                    case 1:
                        view = inflater.inflate(R.layout.list_item_01, null);
                        LinearLayout llItem = (LinearLayout) view.findViewById(R.id.ll_item01);
                        TextView mDesc = (TextView) view.findViewById(R.id.desc);
                        TextView mTime = (TextView) view.findViewById(R.id.deal_time);
                        ImageView imageView = (ImageView) view.findViewById(R.id.image);
                        mDesc.setText("业主提交了请求");
                        System.out.println(histToList.get(i) + "list----");
                        System.out.println(histToList.get(i).getCreatedOn()+"list----");
                        mTime.setText(DateUtil.getDateTimeFormat(DateUtil.mDateTimeFormatString, histToList.get(i).getCreatedOn()));
                        ImageView image = new ImageView(this);
                        image.setBackgroundColor(0xffd3d3d3);
                        if (i == 0) {
                            llItem.setBackgroundResource(R.drawable.time_bg);
                            imageView.setBackgroundResource(R.drawable.time_05);
                        } else {
                            llItem.setBackgroundResource(R.drawable.time02_bg);
                            imageView.setBackgroundResource(R.drawable.time_002);
                        }
                        llHist.addView(view);
                        llShaft.addView(image, params);
                        break;
                    case 2:
                        //物业响应

                        view = inflater.inflate(R.layout.list_item_01, null);
                        TextView mDesc2 = (TextView) view.findViewById(R.id.desc);
                        LinearLayout llItem01 = (LinearLayout) view.findViewById(R.id.ll_item01);
                        TextView mReceiveTime = (TextView) view.findViewById(R.id.deal_time);
                        ImageView imageView01 = (ImageView) view.findViewById(R.id.image);
                        if (histToList.get(i).getDetailUserName() != null) {
                            mDesc2.setText(histToList.get(i).getDetailUserName() + "   已被指派");
                        } else {
                            mDesc2.setText("   已被指派");
                        }
                        mReceiveTime.setText(DateUtil.getDateTimeFormat(DateUtil.mDateTimeFormatString, histToList.get(i).getCreatedOn()));

                        ImageView image02 = new ImageView(this);

                        image02.setBackgroundColor(0xffd3d3d3);
                        if (i == 0) {
                            llItem01.setBackgroundResource(R.drawable.time_bg);
                            imageView01.setImageResource(R.drawable.time_01);

                        } else {
                            llItem01.setBackgroundResource(R.drawable.time02_bg);
                            imageView01.setImageResource(R.drawable.time_004);
                        }

                        llHist.addView(view);
                        llShaft.addView(image02, params);

                        break;

                    case 22:
                        //处理中

                        view = inflater.inflate(R.layout.list_item_03, null);
                        TextView mDesc22 = (TextView) view.findViewById(R.id.desc);
                        LinearLayout llItem22 = (LinearLayout) view.findViewById(R.id.ll_over);
                        TextView mReceiveTime22 = (TextView) view.findViewById(R.id.deal_time);
                        ImageView imageView22 = (ImageView) view.findViewById(R.id.image);
                        if (histToList.get(i).getCreateByName() != null) {
                            mDesc22.setText(histToList.get(i).getCreateByName() + " 开始着手处理任务");

                        } else {
                            mDesc22.setText(" 开始着手处理任务");
                        }
                        TextView expectTime= (TextView) view.findViewById(R.id.response);
                        expectTime.setText("查看预计完成时间");
                        expectTime.setGravity(Gravity.CENTER);
                        mReceiveTime22.setText(DateUtil.getDateTimeFormat(DateUtil.mDateTimeFormatString, histToList.get(i).getCreatedOn()));

                        ImageView image22 = new ImageView(this);

                        image22.setBackgroundColor(0xffd3d3d3);
                        if (i == 0) {
                            llItem22.setBackgroundResource(R.drawable.time_bg);
                            imageView22.setImageResource(R.drawable.time_01);
                            expectTime.setBackgroundResource(R.drawable.check_feedback);
                            expectTime.setTextColor(Color.WHITE);
                        } else {
                            llItem22.setBackgroundResource(R.drawable.time02_bg);
                            imageView22.setImageResource(R.drawable.time_004);
                            expectTime.setTextColor(0xff6c6c6c);
                            expectTime.getPaint().setFlags(Paint.ANTI_ALIAS_FLAG);
                            expectTime.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
                        }
                        final int finalI = i;

                        expectTime.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
//                                Log.i("33333", "onClick: "+histToList.get(finalI).getExpectFinishTime());
                                if(histToList.get(finalI).getExpectFinishTime()!=null){
                                    expectTimeDialogShow(histToList.get(finalI).getExpectFinishTime());
                                }
                            }
                        });
                        llHist.addView(view);
                        llShaft.addView(image22, params);
                        break;

                    case 4:
                        //处理完成，等待评价
                        view = inflater.inflate(R.layout.list_item_03, null);
                        TextView mDesc04 = (TextView) view.findViewById(R.id.desc);
                        if (histToList.get(i).getCreateByName() != null) {
                            mDesc04.setText(histToList.get(i).getCreateByName() + " 完成任务，开始等待评价");
                        } else {
                            mDesc04.setText("完成任务，开始等待评价");
                        }

                        TextView mTime04 = (TextView) view.findViewById(R.id.deal_time);
                        mTime04.setText(DateUtil.getDateTimeFormat(DateUtil.mDateTimeFormatString, histToList.get(i).getCreatedOn()));
                        TextView mResponse = (TextView) view.findViewById(R.id.response);
                        mResponse.setGravity(Gravity.CENTER);

                        ImageView imageView04 = (ImageView) view.findViewById(R.id.image);
                        llItem04 = (LinearLayout) view.findViewById(R.id.ll_over);

                        if (i == 0) {
                            llItem04.setBackgroundResource(R.drawable.time_bg);
                            mResponse.setBackgroundResource(R.drawable.check_feedback);
                            mResponse.setTextColor(Color.WHITE);
                            imageView04.setBackgroundResource(R.drawable.time_03);
                        } else {
                            llItem04.setBackgroundResource(R.drawable.time02_bg);
                            mResponse.setTextColor(0xff6c6c6c);
                            mResponse.getPaint().setFlags(Paint.ANTI_ALIAS_FLAG);
                            mResponse.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
                            imageView04.setBackgroundResource(R.drawable.time_007);
                        }

                        final int finalII = i;
                        mResponse.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //查看反馈
                                Intent intent = new Intent(getThisContext(), HistoryFeedBackActivity.class);
                                intent.putExtra("mode", histToList.get(finalII));
                                intent.putExtra("type", "1");

                                startActivity(intent);
                            }
                        });
                        ImageView image3 = new ImageView(this);
                        image3.setBackgroundColor(0xffd3d3d3);
                        llHist.addView(view);
                        llShaft.addView(image3, params);
                        break;
                    case 6:

                        //流程结束
                        view = inflater.inflate(R.layout.list_item_03, null);
                        TextView mDesc06 = (TextView) view.findViewById(R.id.desc);
                        TextView mTime06 = (TextView) view.findViewById(R.id.deal_time);
                        TextView mComment = (TextView) view.findViewById(R.id.response);
                        ImageView imageView06 = (ImageView) view.findViewById(R.id.image);
                        if (histToList.get(i).getCreateByName() != null) {
                            mDesc06.setText(histToList.get(i).getCreateByName() + " 完成任务，任务结束");
                        } else {
                            mDesc06.setText("完成任务，任务结束");
                        }
                        mTime06.setText(DateUtil.getDateTimeFormat(DateUtil.mDateTimeFormatString, histToList.get(i).getCreatedOn()));
                        mComment.getPaint().setFlags(Paint.ANTI_ALIAS_FLAG);
                        mComment.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
                        mComment.setText("查看反馈");
                        final int finalIother=i;
                        mComment.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //查看反馈
                                Intent intent = new Intent(getThisContext(), HistoryFeedBackActivity.class);
                                intent.putExtra("mode", histToList.get(finalIother));
                                intent.putExtra("type", "1");
                                startActivity(intent);
//                                commentDialogShow();
                            }
                        });


                        imageView06.setBackgroundResource(R.drawable.time_008);
                        ImageView imageView6 = new ImageView(this);
                        imageView6.setBackgroundColor(0xffd3d3d3);
                        llHist.addView(view);
                        llShaft.addView(imageView6, params);

                        break;
                    case 9:
                        //关闭差评
                        view = inflater.inflate(R.layout.list_item_03, null);
                        TextView mDesc09 = (TextView) view.findViewById(R.id.desc);
                        TextView mTime09 = (TextView) view.findViewById(R.id.deal_time);
                        TextView smComment = (TextView) view.findViewById(R.id.response);
                        ImageView imageView09 = (ImageView) view.findViewById(R.id.image);
                        mDesc09.setText("任务已被关闭");
                        mTime09.setText(DateUtil.getDateTimeFormat(DateUtil.mDateTimeFormatString, histToList.get(i).getCreatedOn()));
                        smComment.getPaint().setFlags(Paint.ANTI_ALIAS_FLAG);
                        smComment.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
                        smComment.setText("查看关闭原因");
                        smComment.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                commentcloseDialogShow();

                            }
                        });


                        imageView09.setBackgroundResource(R.drawable.time_008);
                        ImageView imageView9 = new ImageView(this);
                        imageView9.setBackgroundColor(0xffd3d3d3);
                        llHist.addView(view);
                        llShaft.addView(imageView9, params);


                        break;
                    case 20:
                        //转发
                        view = inflater.inflate(R.layout.list_item_03, null);
                        LinearLayout llItem20 = (LinearLayout) view.findViewById(R.id.ll_over);
                        TextView mDesc20 = (TextView) view.findViewById(R.id.desc);
                        TextView mTime20 = (TextView) view.findViewById(R.id.deal_time);
                        TextView mResponse20 = (TextView) view.findViewById(R.id.response);
                        ImageView mImageView = (ImageView) view.findViewById(R.id.image);
                        mDesc20.setText("任务被转发");
                        mTime20.setText(DateUtil.getDateTimeFormat(DateUtil.mDateTimeFormatString, histToList.get(i).getCreatedOn()));
                        mResponse20.setText("查看转发描述");
                        mResponse20.setGravity(Gravity.CENTER);
                        ImageView imageView20 = new ImageView(this);
                        imageView20.setBackgroundColor(0xffd3d3d3);
                        if (i == 0) {
                            llItem20.setBackgroundResource(R.drawable.time_bg);
                            mImageView.setBackgroundResource(R.drawable.time_10);
                            mResponse20.setBackgroundResource(R.drawable.check_feedback);
                            mResponse20.setTextColor(Color.WHITE);
                        } else {
                            llItem20.setBackgroundResource(R.drawable.time02_bg);
                            mImageView.setBackgroundResource(R.drawable.time_111);
                            mResponse20.getPaint().setFlags(Paint.ANTI_ALIAS_FLAG);
                            mResponse20.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
                            mResponse20.setTextColor(0xff6c6c6c);
                        }
                        final int finalI2 = i;
                        mResponse20.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                forwardDialogShow(histToList.get(finalI2).getReplyDesc());
                            }
                        });

                        llHist.addView(view);
                        llShaft.addView(imageView20, params);
                        break;
                    case 21:
                        /**
                         * return
                         * 退回
                         */

                        view = inflater.inflate(R.layout.list_item_03, null);
                        TextView mDesc21 = (TextView) view.findViewById(R.id.desc);
                        TextView mTime21 = (TextView) view.findViewById(R.id.deal_time);
                        TextView mResponse21 = (TextView) view.findViewById(R.id.response);
                        LinearLayout llItem21 = (LinearLayout) view.findViewById(R.id.ll_over);
                        ImageView mImageView21 = (ImageView) view.findViewById(R.id.image);
                        mDesc21.setText("业主反应问题未得到解决");
                        mTime21.setText(DateUtil.getDateTimeFormat(DateUtil.mDateTimeFormatString, histToList.get(i).getCreatedOn()));
                        mResponse21.setText("查看原因");
                        mResponse21.setGravity(Gravity.CENTER);
                        final int finalI1 = i;
                        mResponse21.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                reasonDialog(histToList.get(finalI1).getReplyDesc());
                            }
                        });

                        ImageView imageView21 = new ImageView(this);
                        imageView21.setBackgroundColor(0xffd3d3d3);
                        if (i == 0) {
                            mResponse21.setBackgroundResource(R.drawable.check_feedback);
                            mResponse21.setTextColor(Color.WHITE);
                            llItem21.setBackgroundResource(R.drawable.time_bg);
                            mImageView21.setBackgroundResource(R.drawable.time_06);
                        } else {
                            mResponse21.getPaint().setFlags(Paint.ANTI_ALIAS_FLAG);
                            mResponse21.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
                            mResponse21.setTextColor(0xff6c6c6c);
                            llItem21.setBackgroundResource(R.drawable.time02_bg);
                            mImageView21.setBackgroundResource(R.drawable.time_009);
                        }
                        llHist.addView(view);
                        llShaft.addView(imageView21, params);
                        break;

                }
            }
        }

    }
    //查看预计完成时间
    private void expectTimeDialogShow(String desc){
        final CustomDialog dialog = new CustomDialog(this,
                R.layout.dialog_check_expect_time, R.style.myDialogTheme);
        TextView mDesc = (TextView) dialog.findViewById(R.id.expectTime);
        LinearLayout close = (LinearLayout) dialog.findViewById(R.id.parent);
        Date date=DateUtil.getFormatDateExpectTime(desc);
        SimpleDateFormat mFormatDateTime = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        String time=mFormatDateTime.format(date);
        TextView mClose = (TextView) dialog.findViewById(R.id.tv_sure);
        if (!TextUtils.isEmpty(desc)) {
            mDesc.setText(time);

        }else {
            mDesc.setText("没有预计完成时间");
        }


        mClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        dialog.show();

    }

    private void forwardDialogShow(String desc) {
        final CustomDialog dialog = new CustomDialog(this,
                R.layout.dialog_return_reason, R.style.myDialogTheme);
        TextView mText = (TextView) dialog.findViewById(R.id.text);
        TextView mDesc = (TextView) dialog.findViewById(R.id.reason);

        ImageView mImageView = (ImageView) dialog.findViewById(R.id.close);
        mText.setText("转发描述");
        if (!TextUtils.isEmpty(desc)) {
            mDesc.setText(desc);

        }


        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        dialog.show();

    }

    /**
     * check the reason
     *
     * @param desc
     */
    private void reasonDialog(String desc) {

        final CustomDialog dialog = new CustomDialog(this, R.layout.dialog_return_reason, R.style.myDialogTheme);
        ImageView mImage = (ImageView) dialog.findViewById(R.id.close);
        TextView mReason = (TextView) dialog.findViewById(R.id.reason);
        mImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        mReason.setText(desc);

        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }


    /**
     * 关闭原因弹窗
     **/
    private void commentcloseDialogShow() {
        final CustomDialog dialog = new CustomDialog(this, R.layout.dilalog_closereason, R.style.myDialogTheme);
        ImageView sClose = (ImageView) dialog.findViewById(R.id.read_close);
        TextView sContent = (TextView) dialog.findViewById(R.id.closereson_content);


        if (!TextUtils.isEmpty(mainExpandTo.getRemark())) {
            sContent.setText(mainExpandTo.getRemark());
        }

        sClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.back:

                    intent = new Intent(this, TaskHallActivity.class);
                    intent.putExtra("is_submit", is_submit);
                    //数据回传到上一个actvity
                    setResult(0x123, intent);

                    setResult(TASK_DETAILS, null);



                application.setApp_execute_man(new UserInfoTo());
                application.setApp_care_man(new ArrayList<>());
                onBackPressed();
                finish();
                break;
            case R.id.forward:
                intent = new Intent(this, ForwardCenterActivity.class);
                intent.putExtra("heart", "heart");
                intent.putExtra("mode", mainExpandTo);
                startActivity(intent);
                break;

            case R.id.message:
                intent = new Intent(this, LeaveMessageActivity.class);
                intent.putExtra("mode", mainExpandTo);
                startActivity(intent);

                break;
            case R.id.phone:
                mobileDialog();
                break;
            case R.id.submit:
                if (flag) {
                    expectTimeDialog();
//                    initDatas();
                    //需在提交数据成功后再执行；
                } else {
                    intent = new Intent(this, MyWorkFeedBackActivity.class);
                    intent.putExtra("mode", mainExpandTo);
//                    intent.putExtra("serviceType", serviceType);
//                    intent.putExtra("serviceSid", serviceSid);
                    intent.putExtra("is_submit", is_submit);
                    startActivityForResult(intent, 0x125);
                }

                break;

        }

    }

    private void expectTimeDialog() {
        mimePickerExpect.show(getSupportFragmentManager(), "year_month_day_hour");

    }


    private void haveRights() {
        SystemApi api = ApiClient.create(SystemApi.class);
        final GroupMenuParam menuParam = new GroupMenuParam();
        menuParam.setUserSid(mUserHelper.getSid());
        final CustomDialogFragment dialogFragment = new CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "");
        api.onTheWay(menuParam, new HttpCallback<MessageTo<List<GroupMenuTo>>>(this) {
            @Override
            public void success(MessageTo<List<GroupMenuTo>> msg, Response response) {
                dialogFragment.dismissAllowingStateLoss();
                if (msg == null) return;
                if (msg.getSuccess() == 0) {
                    if (msg.getData() != null && msg.getData().size() > 0) {
                        menuToList.clear();
                        menuToList.addAll(msg.getData());
                        System.out.println("bitch：" + JSON.toJSONString(menuToList));
                        for (int i = 0; i < menuToList.size(); i++) {
                            menuTo = menuToList.get(i);

                        }
                    }
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

    //开始处理
    private void inTheProcessing(String date) {
        HomeApi api = ApiClient.create(HomeApi.class);
        ServiceForwardParam param = new ServiceForwardParam();
        param.setWorkSid(mainExpandTo.getWorkSid());
        param.setExpectFinishTime(date);
        final CustomDialogFragment dialogFragment = new CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "");
        //System.out.print(param.getServiceSid() + param.getUserSid());
        Log.i("222", "param: "+param.toString());
        api.beginDealWork(param, new HttpCallback<MessageTo<ServiceMyWorkTo>>(this) {
            @Override
            public void success(MessageTo<ServiceMyWorkTo> msg, Response response) {
                dialogFragment.dismissAllowingStateLoss();
                if (msg.getSuccess() == 0) {
                    //Toast.makeText(getThisContext(), "成功提交数据", Toast.LENGTH_SHORT).show();
                    initDatas();

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


    private void mobileDialog() {
        final CustomDialog dialog = new CustomDialog(this,
                R.layout.dialog_detele_msg, R.style.myDialogTheme);
        TextView mTip = (TextView) dialog.findViewById(R.id.tip);
        mTip.setText("确定拨打此业主电话？");
        Button btnAdd = (Button) dialog.findViewById(R.id.btn_add);
        Button btnCancel = (Button) dialog.findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL,
                        Uri.parse("tel:" +123415));
                startActivity(intent);
                dialog.dismiss();
            }
        });
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        dialog.show();
    }


    /**
     * 提交完成
     **/

    @Override
    protected Context getThisContext() {
        return MyWorkDetailActivity.this;
    }

    @Override
    protected String toPageName() {
        return "任务详情";
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        switch (resultCode) {
            case 0x123:

                is_submit = data.getBooleanExtra("is_submit", false);
                initDatas();
                if (data.getStringExtra("flag").equals("flag_who")) {
                    mSubmit.setVisibility(View.GONE);
                }
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

                intent = new Intent(this, TaskHallActivity.class);
                intent.putExtra("is_submit", is_submit);
                //数据回传到上一个actvity
                setResult(0x123, intent);

//                setResult(TASK_DETAILS,null);
//

            application.setApp_execute_man(new UserInfoTo());
            application.setApp_care_man(new ArrayList<>());
            onBackPressed();
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private String date;

    public String getDateToString(long time) {
        Date d = new Date(time);
        return sf.format(d);
    }
    @Override
    public void onDateSet(TimePickerExpect timePickerView, long millseconds) {
        date = getDateToString(millseconds);
        flag = false;

        inTheProcessing(date);

    }
//    private String serviceType;
//    private String serviceSid;
//    private String id;



}