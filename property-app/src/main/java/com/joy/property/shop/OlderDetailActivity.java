package com.joy.property.shop;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.jinyi.ihome.infrastructure.MessageToBulk;
import com.jinyi.ihome.module.newshop.CancelOlderParam;
import com.jinyi.ihome.module.newshop.CarGoodsInfo;
import com.jinyi.ihome.module.newshop.ExpressTo;
import com.jinyi.ihome.module.newshop.GoodsListTo;
import com.jinyi.ihome.module.newshop.OlderDetailGoodsTo;
import com.jinyi.ihome.module.newshop.OlderDetailParam;
import com.jinyi.ihome.module.newshop.OlderDetailTo;
import com.jinyi.ihome.module.newshop.OlderSidTo;
import com.jinyi.ihome.module.newshop.PayInfo;
import com.jinyi.ihome.module.newshop.PayOlderTo;
import com.jinyi.ihome.module.newshop.PayParam;
import com.joy.common.api.ApiClientBulk;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.NewShopApi;
import com.joy.library.utils.DateUtil;
import com.joy.property.MainApp;
import com.joy.property.base.BaseActivity;
import com.joy.property.R;
import com.joyhome.nacity.app.bulk.alipay.PayResult;
import com.joy.property.common.PictureShowActivity;
import com.joy.property.shop.adapter.OlderDetailAdapter;
import com.joy.property.shop.shoputil.TimeTextViewOlder;
import com.joyhome.nacity.app.util.CustomDialog;
import com.joyhome.nacity.app.util.CustomDialogFragment;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * Created by xz on 2016/7/25.
 */
public class OlderDetailActivity extends BaseActivity implements View.OnClickListener {
    private TextView olderNumber;
    private TextView olderStatus;
    private TextView olderTime;
    private TimeTextViewOlder olderLeaveTime;
    private TextView receiver;
    private TextView receiverNumber;
    private TextView receiverAddress;
    private RelativeLayout addressLayout;
    private ListView listView;
    private GridLayout gridView;
    private TextView goodsNumber;
    private RelativeLayout gridViewLayout;
    private TextView trafficExpense;
    private TextView trafficPattern;
    private TextView price2;
    private TextView trafficExpense2;
    private TextView couponFee;
    private TextView activityName;
    private TextView actualPayRight;
    private TextView actualPay;
    private TextView payOlder;
    private TextView deleteOlder;
    private int type;
    private String imagePath="";
    private String olderSid;
    private int goodsNumberCount;
    private PayOlderTo payPartnerTo = null;
    private static final int SDK_PAY_FLAG = 1;
    private String payOrderId;
    private List<CarGoodsInfo>goodsInfos=new ArrayList<>();
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((String) msg.obj);
                    // 支付宝返回此次支付结果及加签，建议对支付宝签名信息拿签约时支付宝提供的公钥做验签
                    String resultInfo = payResult.getResult();
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        Toast.makeText(getThisContext(), "支付成功", Toast.LENGTH_SHORT).show();
                        submittedSuccessfullyDialog(payOrderId,"");
                    } else {
                        // 判断resultStatus 为非“9000”则代表可能支付失败
                        // “8000”代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(getThisContext(), "支付结果确认中", Toast.LENGTH_SHORT).show();
                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(getThisContext(), "支付失败", Toast.LENGTH_SHORT).show();

                        }

                    }

                    break;
                }
                default:
                    break;
            }
        }
    };
    private LinearLayout expressLayout;
    private RelativeLayout remarkLayout;
    private TextView remarks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_older_detail);
        initView();
        initData();

    }

    private void initData() {
        type=getIntent().getIntExtra("type", 0);
        olderSid=getIntent().getStringExtra("olderSid");

        getData(olderSid);
    }


    private void initView() {

        findViewById(R.id.back).setOnClickListener(this);
        olderNumber = (TextView) findViewById(R.id.olderNumber);
        olderStatus = (TextView) findViewById(R.id.olderStatus);
        olderTime = (TextView) findViewById(R.id.olderTime);
        olderLeaveTime = (TimeTextViewOlder) findViewById(R.id.olderLeaveTime);
        receiver = (TextView) findViewById(R.id.receiver);
        receiverNumber = (TextView) findViewById(R.id.receiverNumber);
        receiverAddress = (TextView) findViewById(R.id.receiverAddress);
        addressLayout = (RelativeLayout) findViewById(R.id.addressLayout);
        listView = (ListView) findViewById(R.id.listView);
        gridView = (GridLayout) findViewById(R.id.gridView);
        goodsNumber = (TextView) findViewById(R.id.goodsNumber);
        gridViewLayout = (RelativeLayout) findViewById(R.id.gridViewLayout);
        trafficExpense = (TextView) findViewById(R.id.trafficExpense);
        trafficPattern = (TextView) findViewById(R.id.trafficPattern);
        price2 = (TextView) findViewById(R.id.price2);
        trafficExpense2 = (TextView) findViewById(R.id.trafficExpense2);
        couponFee = (TextView) findViewById(R.id.couponFee);
        activityName = (TextView) findViewById(R.id.activityName);
        actualPayRight = (TextView) findViewById(R.id.actualPayRight);
        actualPay = (TextView) findViewById(R.id.actualPay);
        payOlder = (TextView) findViewById(R.id.payOlder);
        deleteOlder = (TextView) findViewById(R.id.deleteOlder);
        expressLayout = (LinearLayout) findViewById(R.id.expressLayout);
        remarkLayout = (RelativeLayout) findViewById(R.id.remarkLayout);
        remarks = (TextView) findViewById(R.id.remarks);

    }
    private void getData(String olderId) {
        NewShopApi api= ApiClientBulk.create(NewShopApi.class);
        OlderDetailParam param=new OlderDetailParam();
        param.setOrderId(olderId);
        final CustomDialogFragment dialogFragment = new CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "");
        api.getOrderDetail(param, new HttpCallback<MessageToBulk<OlderDetailTo>>(getThisContext()) {
            @Override
            public void success(MessageToBulk<OlderDetailTo> msg, Response response) {
                dialogFragment.dismiss();
                if (msg.getCode() == 0) {
                    setView(msg.getOrderDetailsVo());

                }
            }

            @Override
            public void failure(RetrofitError error) {
                dialogFragment.dismiss();
            }
        });
    }
    @Override
    public void onClick(View v) {
switch (v.getId()){
    case R.id.back:
        onBackPressed();
        break;
}
    }
    public void setView(OlderDetailTo detailTo) {

        olderNumber.setText("订单编号:" + detailTo.getOrderNo());


        olderTime.setText("下单时间:" + detailTo.getOrderTime().substring(0, detailTo.getOrderTime().length()));
        receiver.setText("收货人:" + detailTo.getGoodsReceiverName());
        receiverNumber.setText(detailTo.getGoodsReceiverPhone());
        receiverAddress.setText("收货地址:" + detailTo.getGoodsReceiverAddress());
        trafficExpense.setText("¥" +detailTo.getDistributionCost() + "");
        trafficExpense2.setText("¥" + detailTo.getDistributionCost());
        //activityName.setText("-"+detailTo.getActivityName());
        price2.setText("¥" + detailTo.getTotalAmount() + "");
        actualPay.setText("¥" + (int) detailTo.getActualPayAmount() + "");
        actualPayRight.setText((detailTo.getActualPayAmount() - (int) detailTo.getActualPayAmount() + "0").substring(1));
        trafficPattern.setText(detailTo.getDistributionMode());
        couponFee.setText("-¥" + detailTo.getCouponAmount());
        for (OlderDetailGoodsTo goodsTo : detailTo.getOrderGoodsVoList()) {
            if (!imagePath.contains(goodsTo.getPicUrl()==null?"R.drawable.goods_un_load_bg":goodsTo.getPicUrl())) {
                goodsNumberCount++;
                imagePath = imagePath +  goodsTo.getPicUrl() + ";";
            }
        }
        imagePath = imagePath.substring(0, imagePath.length() - 1);
        goodsNumber.setText(detailTo.getOrderGoodsVoList().size() + "");


        String[] path = imagePath.split(";");

        for (int i = 0; i < path.length; i++) {
            GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
            int mWidth = getScreenWidthPixels(getThisContext());
            layoutParams.width = (int) (mWidth * 0.2430);
            layoutParams.height = (int) (mWidth * 0.2430);
            layoutParams.setMargins(0, 0, 10, 0);
            View view = View.inflate(getThisContext(), R.layout.confirm_order_image, null);
            ImageView postImage = (ImageView) view.findViewById(R.id.image);
            Picasso.with(getThisContext()).load(MainApp.getImagePath(path[i])).placeholder(R.drawable.post_content_bg).error(R.drawable.post_content_bg).into(postImage);
            postImage.setTag(R.id.tag_first, i);
            postImage.setTag(R.id.tag_second, imagePath);
            if (i >= 2)
                view.setVisibility(View.GONE);
            view.setLayoutParams(layoutParams);
            postImage.setOnClickListener(v -> {
                postViewImage((int) v.getTag(R.id.tag_first), (String) v.getTag(R.id.tag_second));
            });
            gridView.addView(view);
        }
        if (type == 1) {
            olderLeaveTime.setTimes((delayDate(detailTo.getOrderTime())).getTime() - System.currentTimeMillis());
            olderStatus.setText("待支付");
            listView.setVisibility(View.GONE);
            deleteOlder.setVisibility(View.GONE);
            olderLeaveTime.run();
        } else if (type == 2) {
            payOlder.setVisibility(View.GONE);
            deleteOlder.setVisibility(View.GONE);
            olderLeaveTime.setVisibility(View.GONE);
            olderStatus.setText("待发货");
            gridViewLayout.setVisibility(View.VISIBLE);
            OlderDetailAdapter adapter = new OlderDetailAdapter(getThisContext());
            List<GoodsListTo> listTos = new ArrayList<>();
            adapter.setList(listTos);
            listView.setAdapter(adapter);
        } else if (type == 3) {
            olderLeaveTime.setVisibility(View.GONE);
            olderStatus.setText("待收货");
            deleteOlder.setVisibility(View.GONE);
            payOlder.setVisibility(View.VISIBLE);
            payOlder.setText("确认收货");
            setExpressLayout(detailTo);
        } else if (type == 4) {
            payOlder.setVisibility(View.VISIBLE);
            deleteOlder.setVisibility(View.GONE);
            payOlder.setText("去评价");
            olderLeaveTime.setVisibility(View.GONE);
            olderStatus.setText("待评价");
        } else if (type == 5) {
            payOlder.setVisibility(View.VISIBLE);
            deleteOlder.setVisibility(View.GONE);
            payOlder.setText("已经完成");
            olderLeaveTime.setVisibility(View.GONE);
            olderStatus.setText("已完成");
        }
        deleteOlder.setOnClickListener(view -> cancelOlderDilog(olderSid));
        payOlder.setOnClickListener(view -> confirm());


            for (OlderDetailGoodsTo goodsTo : detailTo.getOrderGoodsVoList()) {
                CarGoodsInfo goodsInfo = new CarGoodsInfo();
                    goodsInfo.setGoodsName(goodsTo.getGoodsName());
                    goodsInfo.setPicUrl(goodsTo.getPicUrl());
                    goodsInfo.setGoodsNum(goodsTo.getGoodsNum());
                    goodsInfo.setRetailPrice(goodsTo.getRetailPrice());

                    goodsInfo.setDistributionCost(goodsTo.getDistributionCost());
                    goodsInfo.setDiscountPercentage(goodsTo.getDiscountPercentage());
                    goodsInfo.setSpecificationsName(goodsTo.getSpecificationsName());
                goodsInfos.add(goodsInfo);
            }





        gridViewLayout.setOnClickListener(view -> setJumpGoodsList(goodsInfos));

        if (!TextUtils.isEmpty(detailTo.getCustomerComments())){
            remarkLayout.setVisibility(View.VISIBLE);
            remarks.setText(detailTo.getCustomerComments());
        }

    }
    public void postViewImage(int index, String item) {
        if (TextUtils.isEmpty(item)) {
            return;
        }
        Intent intent = new Intent(getThisContext(), PictureShowActivity.class);
        intent.putExtra("index", index);
        intent.putExtra("path", item);
        startActivity(intent);
    }
    public Date delayDate(String date){

        Date mDate=DateUtil.getFormatDateLongTime(date);
        Calendar calendar  =   Calendar.getInstance();
        calendar.setTime(mDate);
        calendar.add(calendar.HOUR, 24);
        mDate=calendar.getTime();

        return mDate;
    }
    public void commentGoods(String olderSid){
        Intent intent=new Intent(getThisContext(), ShoppingPostActivity.class);
        intent.putExtra("OlderSid", olderSid);
        startActivity(intent);
        finish();
        goToAnimation(1);
    }
    private void cancelOlderDilog(String olderSid) {
        final CustomDialog dialog = new CustomDialog(getThisContext(), R.layout.dialog_bulk, R.style.myDialogTheme);

        TextView dialogText = (TextView) dialog.findViewById(R.id.dialogText);
        TextView cancel = (TextView) dialog.findViewById(R.id.cancel);
        TextView confirm = (TextView) dialog.findViewById(R.id.confirm);
        dialogText.setText("确认要取消订单吗");
        cancel.setOnClickListener(view -> dialog.dismiss());
        confirm.setOnClickListener(view -> {
            deleteOlder(olderSid);
            dialog.dismiss();
        });
        dialog.setCancelable(true);
        dialog.show();

    }
    public void deleteOlder(String olderSid){
        NewShopApi api=ApiClientBulk.create(NewShopApi.class);
        CancelOlderParam param=new CancelOlderParam();
        param.setOrderId(olderSid);
        param.setUserId(mUserHelperBulk.getSid());
        CustomDialogFragment dialogFragment=new CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "");
        api.deleteOlder(param, new HttpCallback<MessageToBulk>(getThisContext()) {
            @Override
            public void success(MessageToBulk msg, Response response) {
                dialogFragment.dismiss();
                if (msg.getCode() == 0) {
                    deleteOlder.setText("已取消");
                }
            }
        });

    }
    public void confirm(){
        if (type==1){
            payMoney(olderSid);
        }else if (type==3){
      confirmOlderDilog(olderSid);
        }else if (type==4){
            commentGoods(olderSid);
        }
    }

    public void payMoney(String olderSid){

        NewShopApi api=ApiClientBulk.create(NewShopApi.class);
        PayParam param=new PayParam();
        OlderSidTo sidTo=new OlderSidTo();
        List<OlderSidTo>sidTos=new ArrayList<>();
        sidTo.setOrderId(olderSid);
        sidTos.add(sidTo);
        param.setOrderList(sidTos);
CustomDialogFragment dialogFragment=new CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(),"");
        api.payOlder(param, new HttpCallback<MessageToBulk<PayInfo>>(getThisContext()) {
            @Override
            public void success(MessageToBulk<PayInfo> msg, Response response) {
                dialogFragment.dismiss();
                if (msg.getCode() == 0) {
                    payOrderId = msg.getPayOrderId();
                    payPartnerTo = msg.getPayinto().getPayPartners().get(0);
                    if (TextUtils.equals(payPartnerTo.getPayPartner().toUpperCase(), "ALIPAY_WALLET")) {
                        // 客户端支付
                        final String payPartner = payPartnerTo.getPayUrl();
                        Runnable payRunnable = () -> {
                            // 构造PayTask 对象
                            PayTask alipay = new PayTask(OlderDetailActivity.this);
                            // 调用支付接口，获取支付结果
                            String result = alipay.pay(payPartner);
                            Message msg1 = new Message();
                            msg1.what = SDK_PAY_FLAG;
                            msg1.obj = result;
                            mHandler.sendMessage(msg1);
                        };

                        // 必须异步调用
                        Thread payThread = new Thread(payRunnable);
                        payThread.start();
                    }
                }
            }

            @Override
            public void failure(RetrofitError error) {
                dialogFragment.dismiss();
            }
        });


    }
    public void confirmReceive(String olderSid){
        NewShopApi api=ApiClientBulk.create(NewShopApi.class);
        OlderDetailParam param=new OlderDetailParam();
        param.setOrderId(olderSid);
        CustomDialogFragment dialogFragment=new CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "");
        api.confirmReceive(param, new HttpCallback<MessageToBulk>(getThisContext()) {
            @Override
            public void success(MessageToBulk msg, Response response) {
                dialogFragment.dismiss();
                if (msg.getCode() == 0) {
                    commentGoods(olderSid);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                dialogFragment.dismiss();
            }
        });

    }
    private void confirmOlderDilog(String olderSid) {
        final CustomDialog dialog = new CustomDialog(getThisContext(), R.layout.dialog_bulk, R.style.myDialogTheme);

        TextView dialogText = (TextView) dialog.findViewById(R.id.dialogText);
        TextView cancel = (TextView) dialog.findViewById(R.id.cancel);
        TextView confirm = (TextView) dialog.findViewById(R.id.confirm);
        dialogText.setText("确定已经收到了商品");
        cancel.setOnClickListener(view -> dialog.dismiss());
        confirm.setOnClickListener(view -> {
            confirmReceive(olderSid);
            dialog.dismiss();
        });
        dialog.setCancelable(true);
        dialog.show();

    }
    private void submittedSuccessfullyDialog(String olderSidF,String payNo) {
        Intent intent=new Intent(getThisContext(),FinishPayActivity.class);
        intent.putExtra("PayOrderId", payOrderId);
        intent.putExtra("olderSid", olderSid);
        intent.putExtra("MyBulk", getIntent().getBooleanExtra("MyBulk", false));
        startActivity(intent);
        finish();
        goToAnimation(1);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (getIntent().getBooleanExtra("MyBulk",false))
            finish();
        else {
            startActivity(new Intent(getThisContext(), ShoppingActivity.class));
            finish();
        }
        goToAnimation(2);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK)
            onBackPressed();
        return super.onKeyDown(keyCode, event);
    }
    public void setExpressLayout(OlderDetailTo detailTo){
        if (detailTo.getOrderExpressList()!=null&&detailTo.getOrderExpressList().size()>0){
            expressLayout.setVisibility(View.VISIBLE);
            for (ExpressTo expressTo:detailTo.getOrderExpressList()) {
                View view = View.inflate(getThisContext(), R.layout.shop_express_item, null);
                TextView expressCompany = (TextView) view.findViewById(R.id.expressCompany);
                TextView expressName = (TextView) view.findViewById(R.id.expressName);
                TextView deliverName = (TextView) view.findViewById(R.id.deliverName);
                TextView expressNumber = (TextView) view.findViewById(R.id.expressNumber);
                TextView phone = (TextView) view.findViewById(R.id.phone);
                if (TextUtils.isEmpty(expressTo.getExpressNo())||TextUtils.isEmpty(expressTo.getExpressCompany()))
                {

                    expressCompany.setVisibility(View.GONE);
                    deliverName.setVisibility(View.GONE);
                    expressName.setText(expressTo.getPackageName());
                    expressNumber.setText("配送员姓名："+expressTo.getDeliverName());
                    phone.setText("配送员手机："+expressTo.getDeliverPhone());
                    expressLayout.addView(view);
                }else {
                    expressCompany.setVisibility(View.GONE);
                    deliverName.setVisibility(View.GONE);
                    expressName.setText(expressTo.getPackageName());
                    expressNumber.setText("配送快递："+expressTo.getExpressCompany());
                    phone.setText("配送单号："+expressTo.getExpressNo());
                    expressLayout.addView(view);
                }
            }

        }else
            expressLayout.setVisibility(View.GONE);
    }
    public void setJumpGoodsList(List<CarGoodsInfo> goodsList){
        Intent intent=new Intent(getThisContext(),GoodsListActivity.class);
        intent.putExtra("goodsList", (Serializable) goodsList);
        startActivity(intent);
        goToAnimation(1);
    }
}
