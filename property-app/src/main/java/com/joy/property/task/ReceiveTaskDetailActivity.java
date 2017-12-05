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
import android.view.View.OnClickListener;
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
import com.joy.property.common.LongPictureShowActivity;
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
 * 记录类型
 * （
 * 1：呼叫，
 * 2：物业响应，20：转发，
 * 21：退回，22:处理中
 * 3：撤消，4：处理完成，待评价，
 * 6：流程结束，9：关闭
 * ）
 * <p>
 * 以前的 状态 处理中 实际上 是保护  2开头的所有状态
 */
public class ReceiveTaskDetailActivity extends BaseActivity
        implements OnClickListener,OnDateSetListenerNew {
    private static final int TASK_DETAILS = 2;
    private PopupWindow pw;

    private Button mSubmit;
    private TextView mUser;
    private TextView mDate;
    private TextView mTip;
    private TextView mContent;
    private ServiceMainExpandTo mainExpandTo;
    private TextView mForward;
    private TextView change;
    private TextView mApartment;
    private TextView mTitle;
    private FlowLayout flowLayout;
    private String mServiceSid = "";
    private LinearLayout llShaft;
    private LinearLayout llHist;
    private TextView mProgress;
    private Button mReceive;
    private TextView mServiceContact;
    private TextView mServicePhone;
    private TextView mServiceAddress;
    private TextView mServiceTime;
    private TextView mTextAddress;
    private TextView mTextTime;
    private RelativeLayout mLayoutContact;
    private TextView mServiceFee;
    private TextView mTextServiceFee;
    private TextView mSerialNumber;
    private TextView mEmergency;
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
    private TextView shui_leixing;
    private TextView xiadan_shijian;
    private TextView xiangying_shijian;
    private TextView shui_shuliang;
    private TextView fukuan_fangshi;
    private View boa;
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
        haveRights();
        initIntentDatas();
        getMessageQty();
        initDatas(true);
        if (!isFirstRegist)
            EventBus.getDefault().register(this);
        isFirstRegist=true;
        infoTo = (ApartmentInfoTo) getIntent().getSerializableExtra("Time");
        NetTimeUtil.getNetTimeLong();

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
        mMessage.setOnClickListener(this);
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
        mForward.setOnClickListener(this);
        change.setOnClickListener(this);
        mForward.setVisibility(View.GONE);
        change.setVisibility(View.GONE);
        mApartment = (TextView) findViewById(R.id.apartment);
        mTitle = (TextView) findViewById(R.id.tv_title);
        mTitle.setSelected(true);
        mTitle.setFocusable(true);
        mTitle.setFocusableInTouchMode(true);
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
        mServiceContact = (TextView) findViewById(R.id.service_contact);
        mServicePhone = (TextView) findViewById(R.id.service_phone);
        mServiceTime = (TextView) findViewById(R.id.service_time);
        mLayoutContact = (RelativeLayout) findViewById(R.id.layout_contact);
        mLayoutContact.setVisibility(View.GONE);
        mTextServiceFee = (TextView) findViewById(R.id.text_fee);
        mTextServiceFee.setVisibility(View.GONE);
        mServiceQtyDesc = (TextView) findViewById(R.id.service_qty_desc);
        mServiceQtyDesc.setVisibility(View.GONE);
        shui_leixing = (TextView) findViewById(R.id.shui_leixing);
        xiadan_shijian = (TextView) findViewById(R.id.xiadan_shijian);
        xiangying_shijian = (TextView) findViewById(R.id.xiangying_shijian);
        shui_shuliang = (TextView) findViewById(R.id.shui_shuliang);
        fukuan_fangshi = (TextView) findViewById(R.id.fukuan_fangshi);
        mSerialNumber = (TextView) findViewById(R.id.serial_num);
        mEmergency = (TextView) findViewById(R.id.emergency);

        boa = findViewById(R.id.boa);
        mNotice = (TextView) findViewById(R.id.tv_notice);
        inLegalPlace = (TextView) findViewById(R.id.inLegalPlace);
        inLegalType = (TextView) findViewById(R.id.inLegalType);
        carNo=(TextView)findViewById(R.id.carNo);


    }

    private void initIntentDatas() {
//        mServiceSid = getIntent().getStringExtra("sid");
//        mValue = getIntent().getIntExtra("value", 0);

        intent = this.getIntent();
        Bundle bunde = intent.getExtras();

            /* 取得Bundle对象中的数据 */
        mServiceSid = bunde.getString("sid");
        mApartmentSid = bunde.getString("apartmentSid");
        mValue = bunde.getInt("value", 0);
        type = bunde.getString("type", "0");

        mDoneToBack = bunde.getString("Done2Back");
        if (mDoneToBack != null) {
            if (mDoneToBack.equals("DoneToBack")) {
                confirmDoneShow();
            }
        }

    }

    /**
     * 获取留言数
     */
    private void getMessageQty() {
        HomeApi api = ApiClient.create(HomeApi.class);
        final CustomDialogFragment dialogFragment = new CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "");
        api.getMessageWaitQtyByService(mServiceSid, new HttpCallback<MessageTo<Integer>>(this) {
            @Override
            public void success(MessageTo<Integer> msg, Response response) {
                dialogFragment.dismissAllowingStateLoss();
                if (msg.getSuccess() == 0) {
                    if (msg.getData() > 0) {
                        mTip.setVisibility(View.VISIBLE);
                        mTip.setText(String.valueOf(msg.getData()));
                    }

                } else {
                    Toast.makeText(getThisContext(), msg.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                dialogFragment.dismissAllowingStateLoss();
                super.failure(error);
            }
        });


    }

    private void initDatas(boolean isFirst) {
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
        System.out.println(mServiceSid+"serviceSid");
        api.findServiceMainExpandBySid(mServiceSid, new HttpCallback<MessageTo<ServiceMainExpandTo>>(this) {
            @Override
            public void success(MessageTo<ServiceMainExpandTo> msg, Response response) {
                if (msg.getSuccess() == 0) {

                    mainExpandTo = msg.getData();
                    Log.i("222", "getTypeSid"+mainExpandTo.getTypeSid()+"getServiceSid"+mainExpandTo.getServiceSid());
                    init(isFirst);
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
    private void init(boolean isFirst) {
        Log.i("222", "mainExpandTo: "+mainExpandTo.getServiceStatus());

        mStatus =mainExpandTo.getServiceStatus();
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
        if(isFirst){mTitle.setText(mainExpandTo.getTypeName());}

        //  System.out.println(mainExpandTo.getTypeName() + "name");
        String mCategorySid = mainExpandTo.getServiceCategory();

        if (TextUtils.equals(mCategorySid, "9098ED29-072D-4653-A37D-3C2F6DF80861")) {
            mServiceTime.setVisibility(View.VISIBLE);
            mTextTime.setVisibility(View.VISIBLE);
            mLayoutContact.setVisibility(View.VISIBLE);
            mTextTime.setText("服务时间：");
            mTextAddress.setText("服务地址：");
            mServiceFee.setVisibility(View.VISIBLE);
            mTextServiceFee.setVisibility(View.VISIBLE);
            mTextServiceFee.setText("服务费用：");
        } else if (TextUtils.equals(mCategorySid, "BCCF6994-9449-4E6D-9F5B-09CE08AD9353")) {
            mServiceFee.setVisibility(View.VISIBLE);
            mLayoutContact.setVisibility(View.VISIBLE);
            mServiceTime.setVisibility(View.VISIBLE);
            mTextTime.setVisibility(View.VISIBLE);
            mTextTime.setText("维修时间：");
            mTextAddress.setText("维修地址：");
            mTextServiceFee.setVisibility(View.VISIBLE);
            mTextServiceFee.setText("维修费用：");
        } else if (TextUtils.equals(mCategorySid, "C733AA3D-32FA-4F5B-B299-061044661DC0")) {

            mLayoutContact.setVisibility(View.VISIBLE);
            mTextTime.setVisibility(View.GONE);
            mServiceTime.setVisibility(View.GONE);
            mTextAddress.setText("维修地址：");
            mServiceFee.setVisibility(View.GONE);
            mTextServiceFee.setVisibility(View.GONE);

        } else if (TextUtils.equals(mCategorySid, "7D2B996C-12EC-4CD4-8499-B453E96AF11F")) {
            mLayoutContact.setVisibility(View.GONE);
            mServiceFee.setVisibility(View.GONE);
            mTextServiceFee.setVisibility(View.GONE);

        } else if (TextUtils.equals(mCategorySid, "51979B62-10E6-44C7-88B8-4B239B1CE03F")) {
            mServiceTime.setVisibility(View.VISIBLE);
            mTextTime.setVisibility(View.VISIBLE);
            mLayoutContact.setVisibility(View.VISIBLE);
            mTextTime.setText("预约时间：");
            mTextAddress.setText("服务地址：");
            mServiceFee.setVisibility(View.VISIBLE);
            mTextServiceFee.setVisibility(View.VISIBLE);
            mTextServiceFee.setText("服务费用：");
            DisplayMetrics dm = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(dm);
            int densityDpi = dm.densityDpi;
            float scale = dm.density;
            int marginleft = (int) scale;
            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) boa.getLayoutParams();
            lp.addRule(RelativeLayout.BELOW, R.id.fukuan_fangshi);
            boa.setLayoutParams(lp);
            shui_leixing.setVisibility(View.VISIBLE);
            xiadan_shijian.setVisibility(View.VISIBLE);
            xiangying_shijian.setVisibility(View.VISIBLE);
            shui_shuliang.setVisibility(View.VISIBLE);
            fukuan_fangshi.setVisibility(View.VISIBLE);
            shui_leixing.setText("水的类型：" + mainExpandTo.getTypeName());


            if (mainExpandTo.getCreatedOn() != null) {
                xiadan_shijian.setText("下单时间：" + DateUtil.getDateTimeFormat(DateUtil.mDateTimeFormatString, mainExpandTo.getCreatedOn()));
            }


            if (mainExpandTo.getResponseTime() != null) {
                System.out.println("响应时间：" + DateUtil.getDateTimeFormat(DateUtil.mDateTimeFormatString, mainExpandTo.getResponseTime()));
                xiangying_shijian.setText("响应时间：" + DateUtil.getDateTimeFormat(DateUtil.mDateTimeFormatString, mainExpandTo.getResponseTime()));
            } else {
                xiangying_shijian.setText("响应时间：暂无");
            }


            shui_shuliang.setText("水的数量：" + mainExpandTo.getServiceQty());

            System.out.println(mainExpandTo.getServiceMainExtTo().getPayType() + "paytype");
            if (mainExpandTo!=null&& mainExpandTo.getServiceMainExtTo()!=null) {
                if (mainExpandTo.getServiceMainExtTo().getPayType() == 0) {
                    fukuan_fangshi.setText("付款方式：货到付款");
                }
                if (mainExpandTo.getServiceMainExtTo().getPayType() == 1) {
                    fukuan_fangshi.setText("付款方式：支付宝");
                }
            }
        }

        if (!TextUtils.isEmpty(mainExpandTo.getServiceContact())) {

            mServiceContact.setText(mainExpandTo.getServiceContact());
        }

        if (!TextUtils.isEmpty(mainExpandTo.getServicePhone())) {

            mServicePhone.setText(mainExpandTo.getServicePhone());
        }

        if (!TextUtils.isEmpty(mainExpandTo.getServiceAddress())) {

            mServiceAddress.setText(mainExpandTo.getServiceAddress());

        }
        if (!TextUtils.isEmpty(mainExpandTo.getServiceQtyDesc())) {
            mServiceQtyDesc.setVisibility(View.VISIBLE);
            mServiceQtyDesc.setText(mainExpandTo.getServiceQtyDesc());
        }

        if (!TextUtils.isEmpty(mainExpandTo.getServiceBookingTime())) {

            mServiceTime.setText(mainExpandTo.getServiceBookingTime());
        }
        mServiceFee.setText("￥" + df.format(mainExpandTo.getServicePrice()));

        if (mValue == 2) {
            mReceive.setVisibility(View.GONE);
            mSubmit.setVisibility(View.GONE);
            mForward.setVisibility(View.GONE);
            change.setVisibility(View.GONE);

        } else if (mainExpandTo.getResponseUser() != null) {

            if (!mUserHelper.getSid().equals(mainExpandTo.getResponseUser().getSid())) {
                mSubmit.setVisibility(View.GONE);
                mForward.setVisibility(View.GONE);
                change.setVisibility(View.GONE);

            } else if ("4".equals(mainExpandTo.getServiceStatus())
                    || "6".equals(mainExpandTo.getServiceStatus())
                    || "9".equals(mainExpandTo.getServiceStatus())
                    || !mUserHelper.getSid().equals(mainExpandTo.getResponseUser().getSid())) {
                mForward.setVisibility(View.GONE);
                change.setVisibility(View.GONE);
                mSubmit.setVisibility(View.GONE);
            } else if ("1".equals(mainExpandTo.getServiceStatus())) {
                mReceive.setVisibility(View.GONE);
                change.setVisibility(View.GONE);
                mForward.setVisibility(View.GONE);
                mSubmit.setVisibility(View.GONE);
            } else if ("1".equals(mainExpandTo.getServiceStatus())) {
                mReceive.setVisibility(View.GONE);
                mForward.setVisibility(View.GONE);
                change.setVisibility(View.GONE);
                mSubmit.setVisibility(View.GONE);
            } else {
                mReceive.setVisibility(View.GONE);
                mForward.setVisibility(View.VISIBLE);
                change.setVisibility(View.VISIBLE);
                mSubmit.setVisibility(View.VISIBLE);
            }
        } else {
            if ("4".equals(mainExpandTo.getServiceStatus())
                    || "6".equals(mainExpandTo.getServiceStatus())
                    || "9".equals(mainExpandTo.getServiceStatus())
                    ) {
                mForward.setVisibility(View.GONE);
                change.setVisibility(View.GONE);
                mSubmit.setVisibility(View.GONE);

            } else if ("1".equals(mainExpandTo.getServiceStatus())) {

                mReceive.setVisibility(View.GONE);
                mForward.setVisibility(View.GONE);
                change.setVisibility(View.GONE);
                mSubmit.setVisibility(View.GONE);
            } else {

                mForward.setVisibility(View.VISIBLE);
                change.setVisibility(View.VISIBLE);
                mSubmit.setVisibility(View.VISIBLE);

            }
        }

        if (!TextUtils.isEmpty(intent.getStringExtra("right"))) {
            if (intent.getStringExtra("right").equals("right")) {
                Log.i("数据", "init:null ");
                Log.i("数据", "init: "+menuToList.toString());
if(menuToList.size()>0) {
    for (int i = 0; i < menuToList.size(); i++) {
        Log.i("数据", "init: " + menuToList.get(i).getName());

        if ("悦服务大厅-派单权限".equals(menuToList.get(i).getName())) {
            System.out.println(menuTo.getName());

//                            if (menuTo.getName().equals("悦服务大厅-派单权限") || menuTo.getName().equals("送水") || menuTo.getName().equals("巡检") || menuTo.getName().equals("投诉") || menuTo.getName().equals("家政服务") || menuTo.getName().equals("入室维修") || menuTo.getName().equals("公共维修")||"任务大厅-车辆巡检".equals(menuTo.getName())) {
            Log.i("数据", "init: " + menuTo.toString());
            mReceive.setVisibility(View.VISIBLE);
            mReceive.setText("派单");
//                            }
        }
        ;

    }

}

            }

        }

        if (!TextUtils.isEmpty(mainExpandTo.getApartmentName())) {
            mApartment.setText(mainExpandTo.getApartmentName());
            //System.out.println(mainExpandTo.getApartmentName().toString() + "name");
        }
        if (!TextUtils.isEmpty(mainExpandTo.getServiceNo())) {
            mSerialNumber.setText("服务编号："+mainExpandTo.getServiceNo());
        }
        if (!TextUtils.isEmpty(mainExpandTo.getServiceEmergenctStatus())) {
            mEmergency.setVisibility(View.VISIBLE);
            String serialnumber = String.format("<font color='#bebebe'>%s</font><font color ='#46b4d9'>%s</font>" , "紧急程度：",mainExpandTo.getServiceEmergenctStatus());
            mEmergency.setText(Html.fromHtml(serialnumber));
        }

        if (!TextUtils.isEmpty(mainExpandTo.getServiceDesc())) {
            mContent.setText(mainExpandTo.getServiceDesc());
        }
        if (!TextUtils.isEmpty(mainExpandTo.getServiceDesc())&&"51979B62-10E6-44C7-88B9-4B239B1CE02F".equals(mainExpandTo.getServiceCategory())&&!TextUtils.isEmpty(mainExpandTo.getRoomNo())) {
            mContent.setText( (TextUtils.isEmpty(mainExpandTo.getServiceContact())?"":(mainExpandTo.getServiceContact()+";"))+mainExpandTo.getRoomNo()+";"+(TextUtils.isEmpty(mainExpandTo.getServicePhone())?"":mainExpandTo.getServicePhone()+";")+mainExpandTo.getServiceDesc());
        }


        if (mainExpandTo.getCreateUser() != null) {

            if (mainExpandTo.getCreateUser().getName().contains("(") || mainExpandTo.getCreateUser().getName().contains(")")) {
                mUser.setText(mainExpandTo.getCreateUser().getName());
                //System.out.println(mainExpandTo.getCreateUser().getName().toString() + "name");
            } else {
                if (!TextUtils.isEmpty(mainExpandTo.getServiceOwnerNo())) {
                    mUser.setText(mainExpandTo.getServiceOwnerNo() + "(" + mainExpandTo.getCreateUser().getName() + ")");
                } else {
                    mUser.setText("(" + mainExpandTo.getCreateUser().getName() + ")");
                }
            }
        }

        if (!TextUtils.isEmpty(mainExpandTo.getServiceImages())) {
            DisplayMetrics mDisplayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
            int W = mDisplayMetrics.widthPixels - 50;
            FlowLayout.LayoutParams params = new FlowLayout.LayoutParams(W / 4, W / 4);
            params.leftMargin = 10;
            params.topMargin = 10;
            String[] path = mainExpandTo.getServiceImages().split(";");
            for (int i = 0; i <path.length ; i++) {
                ImageView imageView = new ImageView(this);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                displayImage(imageView, path[i]);
                imageView.setTag(R.id.tag_second2,i);
                imageView.setTag(R.id.tag_first2,mainExpandTo.getServiceImages());
                flowLayout.addView(imageView, flowLayout.getChildCount(), params);
                imageView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getThisContext(), LongPictureShowActivity.class);
                        intent.putExtra("path",(String)v.getTag(R.id.tag_first2));
                        intent.putExtra("index", (int)v.getTag(R.id.tag_second2));
                        startActivity(intent);
                    }
                });
            }
        }

        if (mainExpandTo.getCreatedOn() != null) {
            mDate.setText(DateUtil.getDateTimeFormat(DateUtil.mFormatDateString2, mainExpandTo.getCreatedOn()));

        }

        if (!TextUtils.isEmpty(mainExpandTo.getWaitingTime())) {
            String mStr = String.format("<font color='#bebebe'>%s</font><font color='#faca39'>%s</font>", "任务已开始", mainExpandTo.getWaitingTime());
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
         * */


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
                        if(histToList.get(i).getCreatedOn()!=null){
                            mTime.setText(DateUtil.getDateTimeFormat(DateUtil.mDateTimeFormatString, histToList.get(i).getCreatedOn()));
                        }
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
                        if (histToList.get(i).getCreateUser() != null) {
                            mDesc2.setText(histToList.get(i).getCreateUser().getName() + "   已被指派");

                        } else {
                            mDesc2.setText("   已被指派");
                        }
if(histToList.get(i).getCreatedOn()!=null) {
    mReceiveTime.setText(DateUtil.getDateTimeFormat(DateUtil.mDateTimeFormatString, histToList.get(i).getCreatedOn()));
}
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
                        if (histToList.get(i).getCreateUser() != null) {
                            mDesc22.setText(histToList.get(i).getCreateUser().getName() + " 开始着手处理任务");

                        } else {
                            mDesc22.setText(" 开始着手处理任务");
                        }
                        TextView expectTime= (TextView) view.findViewById(R.id.response);
                        expectTime.setText("查看预计完成时间");
                        expectTime.setGravity(Gravity.CENTER);
                        if(histToList.get(i).getCreatedOn()!=null) {
                            mReceiveTime22.setText(DateUtil.getDateTimeFormat(DateUtil.mDateTimeFormatString, histToList.get(i).getCreatedOn()));
                        }
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

                        expectTime.setOnClickListener(new OnClickListener() {
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
                        if (histToList.get(i).getCreateUser() != null) {
                            mDesc04.setText(histToList.get(i).getCreateUser().getName() + " 完成任务，开始等待评价");
                        } else {
                            mDesc04.setText("完成任务，开始等待评价");
                        }

                        TextView mTime04 = (TextView) view.findViewById(R.id.deal_time);
                        if(histToList.get(i).getCreatedOn()!=null){
                            mTime04.setText(DateUtil.getDateTimeFormat(DateUtil.mDateTimeFormatString, histToList.get(i).getCreatedOn()));
                        }
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
                        mResponse.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(getThisContext(), HistoryFeedBackActivity.class);
                                if("5CFB60A1-C1FC-4751-B123-05157F02C70D".equals(mainExpandTo.getServiceCategory())){
                                    intent.putExtra("type", "1");
                                }
                                intent.putExtra("mode", histToList.get(finalII));
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
                        mDesc06.setText("业主已评价，任务结束");
                        if(histToList.get(i).getCreatedOn()!=null) {
                            mTime06.setText(DateUtil.getDateTimeFormat(DateUtil.mDateTimeFormatString, histToList.get(i).getCreatedOn()));
                        }
                        mComment.getPaint().setFlags(Paint.ANTI_ALIAS_FLAG);
                        mComment.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
                        mComment.setText("查看评价");
                        final int finalIother=i;
                        mComment.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(getThisContext(), CheckEvaluateActivity.class);
                                intent.putExtra("histToList", histToList.get(finalIother));
                                intent.putExtra("mode",mainExpandTo);

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
                        if(histToList.get(i).getCreatedOn()!=null) {
                            mTime09.setText(DateUtil.getDateTimeFormat(DateUtil.mDateTimeFormatString, histToList.get(i).getCreatedOn()));

                        } smComment.getPaint().setFlags(Paint.ANTI_ALIAS_FLAG);
                        smComment.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
                        smComment.setText("查看关闭原因");
                        smComment.setOnClickListener(new OnClickListener() {
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
                        if(histToList.get(i).getCreatedOn()!=null) {
                            mTime20.setText(DateUtil.getDateTimeFormat(DateUtil.mDateTimeFormatString, histToList.get(i).getCreatedOn()));
                        } mResponse20.setText("查看转发描述");
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
                        mResponse20.setOnClickListener(new OnClickListener() {
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
                        if(histToList.get(i).getCreatedOn()!=null) {
                            mTime21.setText(DateUtil.getDateTimeFormat(DateUtil.mDateTimeFormatString, histToList.get(i).getCreatedOn()));
                        } mResponse21.setText("查看原因");
                        mResponse21.setGravity(Gravity.CENTER);
                        final int finalI1 = i;
                        mResponse21.setOnClickListener(new OnClickListener() {
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
        if ("5CFB60A1-C1FC-4751-B123-05157F02C70D".equals(mainExpandTo.getServiceCategory())){
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
            System.out.println(mainExpandTo+"main---");
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
            if (!TextUtils.isEmpty(mainExpandTo.getTypeName())) {
                inLegalType.setVisibility(View.VISIBLE);
                String serialnumber = String.format("<font color='#bebebe'>%s</font><font color ='#46b4d9'>%s</font>" , "违停类型：",mainExpandTo.getTypeName());
                inLegalType.setText(Html.fromHtml(serialnumber));
            }else {
                inLegalType.setVisibility(View.GONE);
            }
        }


    }
    //查看预计完成时间
    private void expectTimeDialogShow(String desc){
        final CustomDialog dialog = new CustomDialog(this,
                R.layout.dialog_check_expect_time, R.style.myDialogTheme);
        TextView mDesc = (TextView) dialog.findViewById(R.id.expectTime);
        LinearLayout close = (LinearLayout) dialog.findViewById(R.id.parent);
        Date date=DateUtil.getFormatDateLongTime(desc);
         SimpleDateFormat mFormatDateTime = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        String time=mFormatDateTime.format(date);
        TextView mClose = (TextView) dialog.findViewById(R.id.tv_sure);
        if (!TextUtils.isEmpty(desc)) {
            mDesc.setText(time);

        }else {
            mDesc.setText("没有预计完成时间");
        }


        mClose.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        close.setOnClickListener(new OnClickListener() {
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


        mImageView.setOnClickListener(new OnClickListener() {
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
        mImage.setOnClickListener(new OnClickListener() {
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

    private void commentDialogShow() {
        final CustomDialog dialog = new CustomDialog(this, R.layout.dialog_comment, R.style.myDialogTheme);
        ImageView mClose = (ImageView) dialog.findViewById(R.id.read_close);
        TextView mContent = (TextView) dialog.findViewById(R.id.evaluation_content);
        RatingBar mRate01 = (RatingBar) dialog.findViewById(R.id.rb_rate01);
        RatingBar mRate02 = (RatingBar) dialog.findViewById(R.id.rb_rate02);
        RatingBar mRate03 = (RatingBar) dialog.findViewById(R.id.rb_rate03);
        final TextView mText01 = (TextView) dialog.findViewById(R.id.tv_text01);
        final TextView mText02 = (TextView) dialog.findViewById(R.id.tv_text02);
        final TextView mText03 = (TextView) dialog.findViewById(R.id.tv_text03);
        final TextView mSatisfaction = (TextView) dialog.findViewById(R.id.satisfaction);

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


        if (mainExpandTo.getEvaluationItem1() == 1.0f) {
            mText01.setText("不满");
        } else if (mainExpandTo.getEvaluationItem1() == 2.0f) {
            mText01.setText("差评");
        } else if (mainExpandTo.getEvaluationItem1() == 3.0f) {
            mText01.setText("一般");
        } else if (mainExpandTo.getEvaluationItem1() == 4.0f) {
            mText01.setText("满意");
        } else if (mainExpandTo.getEvaluationItem1() == 5.0f) {
            mText01.setText("惊喜");
        }

        if (mainExpandTo.getEvaluationItem2() == 1.0f) {
            mText02.setText("不满");
        } else if (mainExpandTo.getEvaluationItem2() == 2.0f) {
            mText02.setText("差评");
        } else if (mainExpandTo.getEvaluationItem2() == 3.0f) {
            mText02.setText("一般");
        } else if (mainExpandTo.getEvaluationItem2() == 4.0f) {
            mText02.setText("满意");
        } else if (mainExpandTo.getEvaluationItem2() == 5.0f) {
            mText02.setText("惊喜");
        }
        if (mainExpandTo.getEvaluationItem3() == 1.0f) {
            mText03.setText("不满");
        } else if (mainExpandTo.getEvaluationItem3() == 2.0f) {
            mText03.setText("差评");
        } else if (mainExpandTo.getEvaluationItem3() == 3.0f) {
            mText03.setText("一般");
        } else if (mainExpandTo.getEvaluationItem3() == 4.0f) {
            mText03.setText("满意");
        } else if (mainExpandTo.getEvaluationItem3() == 5.0f) {
            mText03.setText("惊喜");
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
        mClose.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
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

        sClose.setOnClickListener(new OnClickListener() {
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
                if (getIntent().getBooleanExtra("isComplaintPush", false)) {
                    startActivity(new Intent(getThisContext(), ComplaintManagerActivity.class));
                } else if (getIntent().getBooleanExtra("houseKeeping", false)) {
                    startActivity(new Intent(getThisContext(), HouseManagerActivity.class));
                } else if (getIntent().getBooleanExtra("publicRepair", false)) {
                    startActivity(new Intent(getThisContext(), RepairsManagementActivity.class));
                } else if (getIntent().getBooleanExtra("roomRepair", false)) {
                    startActivity(new Intent(getThisContext(), RoomRepairsManagementActivity.class));
                } else if (getIntent().getBooleanExtra("taskHall", false)) {
                    startActivity(new Intent(getThisContext(), TaskHallActivity.class));
//                    System.out.println("dddd");
//                    if (getIntent().getBooleanExtra("house",false)){
//                        System.out.println("aaaaaa");
//                        startActivity(new Intent(getThisContext(),HouseManagerActivity.class));
//                    }else if(getIntent().getBooleanExtra("public",false)){
//                                                   System.out.println("bbbbb");
//                        sta f(getIntent().getBooleanExtra("room",false)){
//                        System.out.println("ccccccc");
//                        startActivity(new Intent(getThisContext(),RoomRepairsManagementActivity.class));
//                    }else if(getIntent().getBooleanExtra("isComp",false)){
//                        System.out.println("dddd");
//                        startActivity(new Intent(getThisContext(),RoomRepairsManagementActivity.class));
//                    }

                } else {
                    intent = new Intent(this, TaskHallActivity.class);
                    intent.putExtra("is_submit", is_submit);
                    //数据回传到上一个actvity
                    setResult(0x123, intent);

                    setResult(TASK_DETAILS, null);

                }

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
            case R.id.change:
                 intent=new Intent(getThisContext(),SelectServiceActivity.class);
                intent.putExtra("ApartmentSid",mainExpandTo.getApartmentSid());
                intent.putExtra("type","1");
                intent.putExtra("CategorySid",mainExpandTo.getServiceCategory());
                startActivity(intent);
                goToAnimation(1);
//                intent = new Intent(this, ForwardCenterActivity.class);
//                intent.putExtra("heart", "heart");
//                intent.putExtra("mode", mainExpandTo);
//                startActivity(intent);
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
                    Log.i("222222222", "onClick: 1"+mainExpandTo.getServiceCategory());

                    if("5CFB60A1-C1FC-4751-B123-05157F02C70D".equals(mainExpandTo.getServiceCategory())){
                        intent = new Intent(this, FeedBackTaskOlderActivity.class);
                        intent.putExtra("mode", mainExpandTo);
                        intent.putExtra("serviceType", serviceType);
                        intent.putExtra("serviceSid", serviceSid);
                        intent.putExtra("is_submit", is_submit);
                        startActivityForResult(intent, 0x124);
                        Log.i("2222222222", "onClick: 1");
                    }else{
                        intent = new Intent(this, FeedBackTaskActivity.class);
                        intent.putExtra("mode", mainExpandTo);
                        intent.putExtra("serviceType", serviceType);
                        intent.putExtra("serviceSid", serviceSid);
                        intent.putExtra("is_submit", is_submit);
                        Log.i("2222222222", "onClick: 2");
                        startActivityForResult(intent, 0x124);
                    }

                }
                break;
            case R.id.btn_receive:

                application.setApp_ServiceMainExpandTo(mainExpandTo);
                if (!TextUtils.isEmpty(intent.getStringExtra("right"))) {
                    if (intent.getStringExtra("right").equals("right")) {
                        intent = new Intent(this, SelectPersonPopupWindow.class);
                        intent.putExtra("mode", mainExpandTo);
                        intent.putExtra("aSid", mApartmentSid);
                        startActivity(intent);

                    } else {
                        receiveTask();
                    }
                } else {
                    intent = new Intent(this, SelectPersonPopupWindow.class);
                    intent.putExtra("mode", mainExpandTo);
                    startActivity(intent);
                }
                break;

        }

    }

    private void expectTimeDialog() {
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
//                .setMinuteText("分")
                .setCyclic(false)
                .setMinMillseconds(PublicWay.currentNetTime)
                .buildNew();
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
        param.setServiceSid(mainExpandTo.getServiceSid());
        param.setUserSid(mUserHelper.getSid());
        param.setExpectFinishTime(date);
        final CustomDialogFragment dialogFragment = new CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "");
        //System.out.print(param.getServiceSid() + param.getUserSid());
        Log.i("222", "param: "+param.toString());
        api.inTheProcessing(param, new HttpCallback<MessageTo<ServiceMainExpandTo>>(this) {
            @Override
            public void success(MessageTo<ServiceMainExpandTo> msg, Response response) {
                dialogFragment.dismissAllowingStateLoss();
                if (msg.getSuccess() == 0) {
                    //Toast.makeText(getThisContext(), "成功提交数据", Toast.LENGTH_SHORT).show();
                    initDatas(false);

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

    private void receiveTask() {
        ServiceResponseParam param = new ServiceResponseParam();
        HomeApi api = ApiClient.create(HomeApi.class);
        param.setGroupUserSid(mUserHelper.getSid());
        param.setServiceSid(mainExpandTo.getServiceSid());
        final CustomDialogFragment dialogFragment = new CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "");
        api.responseService(param, new HttpCallback<MessageTo<ServiceMainExpandTo>>(this) {
            @Override
            public void success(MessageTo<ServiceMainExpandTo> msg, Response response) {
                dialogFragment.dismissAllowingStateLoss();
                if (msg.getSuccess() == 0) {
                    Intent intent = new Intent(getThisContext(), TaskHallActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
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
        btnCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnAdd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL,
                        Uri.parse("tel:" + mainExpandTo.getOwnerPhone()));
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
    private void confirmDoneShow() {
        final CustomDialog dialog = new CustomDialog(this, R.layout.dialog_task_done, R.style.myDialogTheme);
        Button btnsubmit = (Button) dialog.findViewById(R.id.btn_submit);
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getThisContext(), TaskHallActivity.class);
                intent.putExtra("page",2);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    @Override
    protected Context getThisContext() {
        return ReceiveTaskDetailActivity.this;
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
                initDatas(false);
                System.out.println(data.getStringExtra("flag")+"-------");
                if (data.getStringExtra("flag").equals("flag_who")) {
                    mForward.setVisibility(View.GONE);
                    change.setVisibility(View.GONE);
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
            if (getIntent().getBooleanExtra("isComplaintPush", false)) {
                startActivity(new Intent(getThisContext(), ComplaintManagerActivity.class));
            } else if (getIntent().getBooleanExtra("houseKeeping", false)) {
                startActivity(new Intent(getThisContext(), HouseManagerActivity.class));
            } else if (getIntent().getBooleanExtra("publicRepair", false)) {
                startActivity(new Intent(getThisContext(), RepairsManagementActivity.class));
            } else if (getIntent().getBooleanExtra("roomRepair", false)) {
                startActivity(new Intent(getThisContext(), RoomRepairsManagementActivity.class));
            } else if (getIntent().getBooleanExtra("taskHall", false)) {
                startActivity(new Intent(getThisContext(), TaskHallActivity.class));
            } else {
                intent = new Intent(this, TaskHallActivity.class);
                intent.putExtra("is_submit", is_submit);
                //数据回传到上一个actvity
                setResult(0x123, intent);

//                setResult(TASK_DETAILS,null);
//
            }
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
        Log.i("222", "getDateToString: "+sf.format(d));
        return sf.format(d);
    }
    @Override
    public void onDateSet(TimePickerExpect timePickerView, long millseconds) {
        date = getDateToString(millseconds);
        flag = false;

        inTheProcessing(date);

    }
    private String serviceType;
    private String serviceSid;
    private String id;
    @Subscribe
    public void onEventMainThread(RefreshEventOther event) {

          serviceSid = event.getMsg();
            serviceType=event.getmName();
        id=(String)event.getmCommon();
//            userSid =msg;
//            param.setResponseUser(userSid);
        mTitle.setText(event.getmName());
        Log.i("2222", "serviceSid"+serviceSid+"serviceType"+serviceType+"id"+id);
    }


}
