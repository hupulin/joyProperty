package com.joy.property.shop;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alipay.sdk.app.PayTask;
import com.jinyi.ihome.infrastructure.MessageToBulk;
import com.jinyi.ihome.module.newshop.AddressListParam;
import com.jinyi.ihome.module.newshop.AddressTo;
import com.jinyi.ihome.module.newshop.CarGoodsInfo;
import com.jinyi.ihome.module.newshop.ChangeAddressTo;
import com.jinyi.ihome.module.newshop.CouponCalculateParam;
import com.jinyi.ihome.module.newshop.CouponTo;
import com.jinyi.ihome.module.newshop.DefaultAddressTo;
import com.jinyi.ihome.module.newshop.GetAddressListParam;
import com.jinyi.ihome.module.newshop.ImmediatelyCouponParam;
import com.jinyi.ihome.module.newshop.ImmediatelyGoodsTo;
import com.jinyi.ihome.module.newshop.ImmediatelySubmitMerchant;
import com.jinyi.ihome.module.newshop.ImmediatelySubmitParam;
import com.jinyi.ihome.module.newshop.ImmediatelySubmitResultTo;
import com.jinyi.ihome.module.newshop.OlderSidTo;
import com.jinyi.ihome.module.newshop.PayInfo;
import com.jinyi.ihome.module.newshop.PayOlderTo;
import com.jinyi.ihome.module.newshop.PayParam;
import com.jinyi.ihome.module.newshop.SubmitGoodsInfo;
import com.jinyi.ihome.module.newshop.UserAddressParam;
import com.jinyi.ihome.module.newshop.UserAddressTo;
import com.joy.common.api.ApiClientBulk;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.NewShopApi;
import com.joy.library.utils.ConfigUtil;
import com.joyhome.nacity.app.MainApp;
import com.joy.property.R;
import com.joy.property.base.BaseActivity;
import com.joy.property.common.PictureShowActivity;
import com.joyhome.nacity.app.bulk.alipay.PayResult;
import com.joyhome.nacity.app.photo.util.PublicWay;
import com.joy.property.shop.adapter.CouponAdapter;
import com.joy.property.shop.shoputil.DoubleUtil;
import com.joyhome.nacity.app.util.CustomDialog;
import com.joyhome.nacity.app.util.CustomDialogFragment;
import com.joyhome.nacity.app.util.SpUtil;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by xz on 2016/7/23.
 **/
public class ImmediatelyConfirmOlderActivity extends BaseActivity implements View.OnClickListener {
    private TextView receiver;
    private TextView receiverNumber;
    private TextView receiverAddress;
    private TextView idCardNum;
    private GridLayout gridView;
    private TextView goodsNumber;
    private TextView price;
    private RelativeLayout idCard_layout;
    private TextView trafficExpense;
    private TextView trafficPattern;
    private TextView useCondition;
    private List<CarGoodsInfo>goodsInfos=new ArrayList<>();
    private TextView payWay;
    private TextView price2;
    private String isSeaBuy;
    private DefaultAddressTo defaultAddressTo=new DefaultAddressTo();
    private TextView trafficExpense2;
    private TextView couponFee;
    private TextView activityName;
    private TextView actuanPay;
    private TextView pay;
    private  double allPrice=0.00;
    private double trafficFee=0.00;
    private ImmediatelyGoodsTo goodsTo;
    private String[] path;
    private String paths="";
    private String payOrderId;
    private LinearLayout sendLayout;
    private PayOlderTo payPartnerTo = null;
    private String intentOlderSid;
    private TextView smallAmount;
    private List<CouponTo>couponList=new ArrayList<>();
    private double payMoney;
    private String realIdCard;
    private static final int SDK_PAY_FLAG = 1;
    private List<AddressTo>addressList=new ArrayList<>();
    private Boolean isGoodsListBack=false;
    private Boolean isGoodsPothoBack=false;
    private AddressTo addressJson;

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                   Bundle bundle= msg.getData();

//                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    // 支付宝返回此次支付结果及加签，建议对支付宝签名信息拿签约时支付宝提供的公钥做验签
                    PayResult payResult = new PayResult((String) msg.obj);

                    String resultInfo = payResult.getResult();
                    String resultStatus = payResult.getResultStatus();
                    System.out.println(resultInfo);
                    System.out.println(payResult.toString());
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        String payNo=resultInfo.substring(resultInfo.indexOf("out_trade_no=")+"out_trade_no=".length(),resultInfo.indexOf("&subject"));
                        Toast.makeText(getThisContext(), "支付成功", Toast.LENGTH_SHORT).show();
                        System.out.println(payNo+"payNomber");
                        submittedSuccessfullyDialog(bundle.getString("OlderSId"),payNo);
                    } else {
                        // 判断resultStatus 为非“9000”则代表可能支付失败
                        // “8000”代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(getThisContext(), "支付结果确认中", Toast.LENGTH_SHORT).show();
                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(getThisContext(), "支付失败", Toast.LENGTH_SHORT).show();

                        }
                      submittedFailfullyDialog(intentOlderSid,"");
                    }

                    break;
                }
                default:
                    break;
            }
        }
    };
    private EditText remark;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_immediately_confirm_order);
        initView();
        initData();
        PublicWay.activityList.add(this);
    }

    private void initData() {
        goodsTo = (ImmediatelyGoodsTo) getIntent().getSerializableExtra("CartSettleGoodsTo");
        setView();
        System.out.println(goodsTo+"goodsTo");
    }

    private void initView() {
        idCard_layout = (RelativeLayout) findViewById(R.id.idCard_layout);
        isSeaBuy = (String) getIntent().getSerializableExtra("IsSeaBuy");
        if(isSeaBuy.equals("no")){
            idCard_layout.setVisibility(View.GONE);

        }
        findViewById(R.id.back).setOnClickListener(this);
        sendLayout = (LinearLayout) findViewById(R.id.sendLayout);
        receiver = (TextView) findViewById(R.id.receiver);
        receiverNumber = (TextView) findViewById(R.id.receiverNumber);
        receiverAddress = (TextView) findViewById(R.id.receiverAddress);
        idCardNum = (TextView) findViewById(R.id.idCardNum);
        findViewById(R.id.addressLayout).setOnClickListener(this);
        payWay = (TextView) findViewById(R.id.payWay);
        price2 = (TextView) findViewById(R.id.price2);
        trafficExpense2 = (TextView) findViewById(R.id.trafficExpense2);
        couponFee = (TextView) findViewById(R.id.couponFee);
        activityName = (TextView) findViewById(R.id.activityName);
        actuanPay = (TextView) findViewById(R.id.actualPay);
        pay = (TextView) findViewById(R.id.pay);
        pay.setOnClickListener(this);
        remark = (EditText) findViewById(R.id.remarks);

    }
    public void getDefaultAddress() {
        NewShopApi api=ApiClientBulk.create(NewShopApi.class);
        AddressListParam param=new AddressListParam();

        param.setUserId(mUserHelperBulk.getSid());

        api.getMyDefaultReceiveAddress(param, new HttpCallback<MessageToBulk<DefaultAddressTo>>(getThisContext()) {
            @Override
            public void success(MessageToBulk<DefaultAddressTo> msg, Response response) {

                if (msg.getCode() == 0) {

                    defaultAddressTo = msg.getReceiverAddress();
                    if (defaultAddressTo==null)
                        return;
                    findViewById(R.id.addAddressLayout).setVisibility(View.GONE);
                    findViewById(R.id.haveAddressLayout).setVisibility(View.VISIBLE);
                        receiver.setText(defaultAddressTo.getReceiverName());
                        receiverAddress.setText(defaultAddressTo.getReceiverArea() + "" + defaultAddressTo.getReceiverDetailAddress());
                       idCardNum.setText("");
                        if (defaultAddressTo.getIdentityCardNo() != null)
                            idCardNum.setText(defaultAddressTo.getIdentityCardNo().replaceAll("(?<=\\d{4})\\d(?=\\d{4})", "*"));
                        receiverNumber.setText(defaultAddressTo.getReceiverPhone());
                       realIdCard=defaultAddressTo.getIdentityCardNo();
                        Log.i("32111", defaultAddressTo.toString());

                }else if (msg.getCode()==-1){

                    findViewById(R.id.addAddressLayout).setVisibility(View.GONE);
                    findViewById(R.id.haveAddressLayout).setVisibility(View.VISIBLE);
//                    getUserAddress();
                    receiver.setText("");
                    receiverAddress.setText("");
                    receiverNumber.setText("");
                    idCardNum.setText("");

                }
            }
        });
    }
public void  setView(){
    SpUtil.put(getThisContext(),"NoIdCard",false);
    SpUtil.put(getThisContext(),"IsAddressBack",false);
    getDefaultAddress();
    setSendLayout();
}
    @Override
    public void onClick(View v) {
switch (v.getId()){
    case R.id.back:
        onBackPressed();
        break;
    case R.id.couponLayout:
        Intent intent=new Intent(getThisContext(),MyCouponActivity.class);

       startActivityForResult(intent,2);
        break;
    case R.id.addressLayout:
        changeAddress();
        break;
    case R.id.pay:
        submitOlder();
        break;
}
    }


    public void postViewImage(int index, String item) {
        if (TextUtils.isEmpty(item)) {
            return;
        }
        Intent intent = new Intent(getThisContext(), PictureShowActivity.class);
        intent.putExtra("index", index);
        intent.putExtra("path", item);
        isGoodsPothoBack=true;
        startActivity(intent);
    }
    public void submitOlder(){
        if (TextUtils.isEmpty(receiverAddress.getText().toString()))
        {
            ToastShowLong(getThisContext(),"请输入收货地址");
            return;
        }
        if ("yes".equals(getIntent().getStringExtra("IsSeaBuy"))&&TextUtils.isEmpty(idCardNum.getText().toString()))
        {
            ToastShowLong(getThisContext(),"您购买了海外购商品，请填写身份证号码");
            return;
        }
        NewShopApi api= ApiClientBulk.create(NewShopApi.class);
        ImmediatelySubmitParam param=new ImmediatelySubmitParam();
        param.setReceiverDetailAddress(receiverAddress.getText().toString());
        param.setReceiverName(receiver.getText().toString());
        param.setReceiverPhone(receiverNumber.getText().toString());
        param.setUserId(mUserHelperBulk.getSid());
        param.setIdentityCardNo(realIdCard);
        param.setGoodsType(goodsTo.getGoodsType());
        param.setIsSeaBuy("yes".equals(getIntent().getStringExtra("IsSeaBuy")) ? "yes" : "no");
        ImmediatelySubmitMerchant merchant=new ImmediatelySubmitMerchant();
        merchant.setCouponId(goodsTo.getCouponId() == null ? "" : goodsTo.getCouponId());
        merchant.setGoodsTotalAmount(goodsTo.getGoodsTotalAmount() + "");
        merchant.setCustomerComments(remark.getText().toString());
        if (DoubleUtil.reduce(goodsTo.getGoodsTotalAmount(),goodsTo.getCouponCost())<=0)
        {
            merchant.setActualPayAmount(goodsTo.getDistributionCost()+"");
            payMoney=goodsTo.getDistributionCost();
        }
        else
        {
            merchant.setActualPayAmount(DoubleUtil.reduce(DoubleUtil.add(goodsTo.getGoodsTotalAmount(), goodsTo.getDistributionCost()),goodsTo.getCouponCost()) + "");
            payMoney=DoubleUtil.reduce(DoubleUtil.add(goodsTo.getGoodsTotalAmount(), goodsTo.getDistributionCost()),goodsTo.getCouponCost());
        }

        merchant.setMerchantId(goodsTo.getMerchantId());
        merchant.setDistributionCost(goodsTo.getDistributionCost() + "");
        merchant.setDistributionMode(goodsTo.getDistributionMode());
        SubmitGoodsInfo info=new SubmitGoodsInfo();
        info.setGoodsName(goodsTo.getGoodsName());
        info.setRetailPrice(goodsTo.getRetailPrice() + "");
        info.setGoodsNum(goodsTo.getGoodsNum() + "");
        info.setGoodsType(goodsTo.getGoodsType());

        if (TextUtils.isEmpty(goodsTo.getSpecificationsName())||"null".equals(goodsTo.getSpecificationsName())||goodsTo.getSpecificationsName().contains("null"))
            info.setSpecificationsName("");
        else
            info.setSpecificationsName(goodsTo.getSpecificationsName());
        info.setSpecificationsId(goodsTo.getSpecificationsId());
        info.setGoodsId(goodsTo.getGoodsId());



if ("0".equals(goodsTo.getGoodsType())) {
    merchant.setMerchantGoods(info);
   param.setMerchant(merchant);
}else {
    info.setActivityId(getIntent().getStringExtra("ActivityId")==null?"":getIntent().getStringExtra("ActivityId"));
    merchant.setActivityGoods(info);
    param.setActivityGoodsOrder(merchant);

}
        System.out.println(param + "param");

        CustomDialogFragment dialogFragment=new CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(),"");
        api.immediatelySubmitOrder(param, new HttpCallback<MessageToBulk<ImmediatelySubmitResultTo>>(getThisContext()) {
            @Override
            public void success(MessageToBulk<ImmediatelySubmitResultTo> msg, Response response) {
                dialogFragment.dismiss();
                if (msg.getCode() == 0) {
                    List<OlderSidTo> sidList = new ArrayList<>();
                    if (payMoney<=0){
                        intentOlderSid=msg.getMerchantOrder()+ "";
                        submittedSuccessfullyDialog("","");
                    }else{
                        OlderSidTo sidTo = new OlderSidTo();
                        sidTo.setOrderId(msg.getMerchantOrder().getOrderId() + "");
                        sidList.add(sidTo);
                        payMoney(sidList);
                    }
                }else
                    ToastShowLong(getThisContext(),msg.getMessage());
            }

            @Override
            public void failure(RetrofitError error) {
                dialogFragment.dismiss();
                System.out.println(error+"----");
            }
        });
    }

public void setSendLayout(){


        View viewSend=View.inflate(getThisContext(), R.layout.send_goods_immediately_item, null);

        gridView = (GridLayout) viewSend.findViewById(R.id.gridView);
        goodsNumber = (TextView)  viewSend.findViewById(R.id.goodsNumber);
        price = (TextView)  viewSend.findViewById(R.id.price);
        trafficExpense = (TextView) viewSend. findViewById(R.id.trafficExpense);
        trafficPattern = (TextView)  viewSend.findViewById(R.id.trafficPattern);
        useCondition = (TextView) viewSend. findViewById(R.id.useCondition);
        smallAmount = (TextView) viewSend. findViewById(R.id.activityName);
        RelativeLayout goodsListLayout = (RelativeLayout) viewSend.findViewById(R.id.goodsListLayout);
        goodsNumber.setText(goodsTo.getGoodsNum() + "");
        String trafficMode = "";
        allPrice=goodsTo.getGoodsTotalAmount();
        trafficMode = goodsTo.getDistributionMode();
        trafficFee =  goodsTo.getDistributionCost();
        price.setText("¥" + allPrice);
        price2.setText("¥" + allPrice + "");
        trafficPattern.setText(trafficMode);
        trafficExpense.setText("配送费:¥" + trafficFee);
        trafficExpense2.setText("¥" + trafficFee + "");
        actuanPay.setText("¥" + goodsTo.getActualPayAmount());

        for (int k= 0; k <1; k++) {
            paths = paths + goodsTo.getGoodsPic();
            GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
            int mWidth = getScreenWidth();
            layoutParams.width = (int) (mWidth * 0.2430);
            layoutParams.height = (int) (mWidth * 0.2430);
            layoutParams.setGravity(Gravity.CENTER_VERTICAL);
            layoutParams.setMargins(0, 0, 10, 0);

            View view = View.inflate(getThisContext(), R.layout.confirm_order_image, null);
           if (k>=2)
               view.setVisibility(View.GONE);
            ImageView postImage = (ImageView) view.findViewById(R.id.image);
            Picasso.with(getThisContext()).load(MainApp.getPicassoImagePath(goodsTo.getGoodsPic())).placeholder(R.drawable.shop_car_goods_bg).error(R.drawable.post_content_bg).into(postImage);
            postImage.setTag(R.id.tag_first, k);
            view.setLayoutParams(layoutParams);
            postImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getThisContext(), PictureShowActivity.class);
                    intent.putExtra("index", (int)view.getTag(R.id.tag_first));
                    intent.putExtra("path", paths);
                    isGoodsPothoBack=true;
                    startActivity(intent);
                }
            });

            gridView.addView(view);
        }

    getCoupon(allPrice, couponFee, useCondition, smallAmount, actuanPay, trafficFee);
    CarGoodsInfo goodsInfo = new CarGoodsInfo();
    goodsInfo.setGoodsName(goodsTo.getGoodsName());
    goodsInfo.setPicUrl(goodsTo.getGoodsPic());
    goodsInfo.setGoodsNum(Integer.valueOf(goodsTo.getGoodsNum()));
    goodsInfo.setRetailPrice(goodsTo.getRetailPrice());
    goodsInfo.setRetailPrice(goodsTo.getRetailPrice());
    goodsInfo.setDistributionCost(goodsTo.getDistributionCost());
    goodsInfo.setSpecificationsId(goodsTo.getSpecificationsId());
    goodsInfo.setSpecificationsName(goodsTo.getSpecificationsName());
    goodsInfos.add(goodsInfo);
    goodsListLayout.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            setJumpGoodsList(goodsInfos);
        }
    });
//    goodsListLayout.setOnClickListener(view -> {
//
//    });
        sendLayout.addView(viewSend);

}
    public void payMoney(List<OlderSidTo> olderSid){

        NewShopApi api=ApiClientBulk.create(NewShopApi.class);
        PayParam param=new PayParam();
        param.setOrderList(olderSid);
        System.out.println(param);
        CustomDialogFragment dialogFragment=new CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "");
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
                        Runnable payRunnable = new Runnable() {

                            @Override
                            public void run() {
                                // 构造PayTask 对象
                                PayTask alipay = new PayTask(ImmediatelyConfirmOlderActivity.this);
                                // 调用支付接口，获取支付结果
//                                Map<String, String> result = alipay.payV2(payPartner, true);
                                String result = alipay.pay(payPartner);
                                Message msg = new Message();
                                msg.what = SDK_PAY_FLAG;
                                msg.obj = result;
                                Bundle bundle = new Bundle();
                                // bundle.putString("OlderSid",olderSid);
                                //  bundle.putString("OlderNumber",olderNumber);
                                System.out.println(result + "result");
                                msg.setData(bundle);
                                mHandler.sendMessage(msg);
                            }
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
    private void submittedSuccessfullyDialog(String olderSid,String payNo) {
        Intent intent=new Intent(getThisContext(),FinishPayActivity.class);
        intent.putExtra("PayOrderId",payOrderId);
        intent.putExtra("olderSid", intentOlderSid);
        intent.putExtra("IsMyShopPage", true);
        startActivity(intent);
        finish();
        goToAnimation(1);
    }
    private void submittedFailfullyDialog(String olderSid,String payNo) {



            Intent intent = new Intent(getThisContext(), MyShoppingActivity.class);
            intent.putExtra("olderSid", olderSid);
            intent.putExtra("type", 1);
            startActivity(intent);
            finish();

            goToAnimation(1);

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        goToAnimation(2);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK)
            onBackPressed();
        return super.onKeyDown(keyCode, event);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode){

            case 15:
                AddressTo addressTo= (AddressTo) data.getSerializableExtra("addressTo");
                if (addressTo!=null) {
                    setSelectAddress(addressTo);
                    addressJson=addressTo;
                }
                break;
            case 16:
                getDefaultAddress();
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    public void changeAddress(){
        if(defaultAddressTo!=null){
            Intent intent=new Intent(getThisContext(),MyAddressActivity.class);
            AddressTo addressTo=new AddressTo();
            addressTo.setId(defaultAddressTo.getId());
            addressTo.setIsDefaultAddress("Y");
            addressTo.setIdentityCardNo(idCardNum.getText().toString());
            addressTo.setReceiverArea(defaultAddressTo.getReceiverArea());
            addressTo.setReceiverDetailAddress(defaultAddressTo.getReceiverDetailAddress());
            addressTo.setReceiverName(receiver.getText().toString());
            addressTo.setReceiverPhone(receiverNumber.getText().toString());
            intent.putExtra("addressJson", addressJson==null?addressTo:addressJson);
            intent.putExtra("IsSeaBuy", isSeaBuy);

            startActivityForResult(intent, 15);
            goToAnimation(1);
        }
    }
    public void changeAddressText(ChangeAddressTo addressTo){
        receiverNumber.setText(addressTo.getPhone());
        receiver.setText(addressTo.getName());
        receiverAddress.setText(addressTo.getAddress());
        idCardNum.setText(addressTo.getIdCard());

    }
    public void getCoupon( double olderPrice,TextView couponText,TextView couponCondition,TextView smallAmount,TextView actuanPay,double trafficFee){
        NewShopApi api=ApiClientBulk.create(NewShopApi.class);
        ImmediatelyCouponParam param=new ImmediatelyCouponParam();
        param.setRetailPrice(goodsTo.getRetailPrice() + "");
        param.setGoodsId(goodsTo.getGoodsId());
        param.setGoodsNum(goodsTo.getGoodsNum() + "");
        param.setUserId(mUserHelperBulk.getSid());
        param.setGoodsType(goodsTo.getGoodsType());
        System.out.println(param+"param");
        api.getImmediatelyCoupon(param, new HttpCallback<MessageToBulk<List<CouponTo>>>(getThisContext()) {
            @Override
            public void success(MessageToBulk<List<CouponTo>> msg, Response response) {
                if (msg.getCode() == 0) {
                    if (msg.getImmediateBuyGoodsCouponVoList() != null) {
                        couponList.clear();
                        if (msg.getImmediateBuyGoodsCouponVoList() != null)
                            couponList.addAll(msg.getImmediateBuyGoodsCouponVoList());

                        setCouponLayout(couponList, olderPrice, couponText, couponCondition, smallAmount );
                    }

                }
            }

            @Override
            public void failure(RetrofitError error) {
                System.out.println(error.toString());
            }
        });
    }
    private double noRulePrice=0.00;

    private String couponIdnoRule;
    public void setCouponLayout(List<CouponTo>couponList,double olderPrice,TextView couponText,TextView couponCondition,TextView smallAmount){
for (CouponTo couponTo:couponList){
    if (noRulePrice < couponTo.getDiscountAmount()) {
        noRulePrice = couponTo.getDiscountAmount();
        couponIdnoRule = couponTo.getCouponId();
        goodsTo.setCouponCost(couponTo.getDiscountAmount());
    }
}
        couponText.setText("-¥" + noRulePrice);
        if (noRulePrice<=0)
            couponCondition.setText("无可用优惠券");
        else
        couponCondition.setText("折扣"+noRulePrice);
        actuanPay.setText("¥" +(DoubleUtil.reduce(olderPrice,noRulePrice)<=0?trafficFee+"":DoubleUtil.reduce(DoubleUtil.add(olderPrice,trafficFee),noRulePrice)));
        goodsTo.setCouponId(couponIdnoRule);
        couponCondition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConponDialogShow(couponList, olderPrice, couponText, couponCondition, smallAmount, actuanPay);
            }
        });
    }
    public void selectCoupon(double olderPrice,TextView couponText,TextView couponCondition,TextView smallAmount,TextView actuanPay,double trafficFee,CouponTo couponTo){
        goodsTo.setCouponId(couponTo.getCouponId());
        goodsTo.setCouponCost(couponTo.getDiscountAmount());
        couponCondition.setText("折扣" + couponTo.getDiscountAmount());
        couponText.setText("-¥" + couponTo.getDiscountAmount());

        actuanPay.setText("¥" + (DoubleUtil.reduce(DoubleUtil.add(olderPrice, trafficFee), couponTo.getDiscountAmount()) <= 0 ? "0.00" : DoubleUtil.reduce(DoubleUtil.add(olderPrice, trafficFee), couponTo.getDiscountAmount())));
    }
    private void ConponDialogShow(List<CouponTo> couponList,double olderPrice,TextView couponText,TextView couponCondition,TextView smallAmount,TextView actuanPay) {
        final CustomDialog dialog = new CustomDialog(this, R.layout.dialog_coupon, R.style.myDialogTheme);
        CouponAdapter couponAdapter = new CouponAdapter(getThisContext());
        couponAdapter.setList(couponList);
        ListView listView = (ListView) dialog.findViewById(R.id.listView);
        listView.setAdapter(couponAdapter);
        listView.setDividerHeight(0);
        couponAdapter.notifyDataSetChanged();
        ImageView noCoupon = (ImageView) dialog.findViewById(R.id.noCoupon);
        if (couponList.size()>0)
            noCoupon.setVisibility(View.GONE);
        RelativeLayout layout = (RelativeLayout) dialog.findViewById(R.id.layout);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        TextView purchaseClose = (TextView) dialog.findViewById(R.id.cancel);
        purchaseClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        dialog.show();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectCoupon(olderPrice,couponText,couponCondition,smallAmount,actuanPay,trafficFee,couponList.get(position));
                dialog.dismiss();
            }
        });

    }
    public void setJumpGoodsList(List<CarGoodsInfo> goodsList){
        Intent intent=new Intent(getThisContext(),GoodsListActivity.class);
        intent.putExtra("goodsList", (Serializable) goodsList);
        isGoodsListBack=true;
        startActivity(intent);
        goToAnimation(1);
    }
    public void setSelectAddress(AddressTo addressTo){
        findViewById(R.id.addAddressLayout).setVisibility(View.GONE);
        findViewById(R.id.haveAddressLayout).setVisibility(View.VISIBLE);

        receiver.setText("");
        receiverNumber.setText("");
        receiverAddress.setText("");
        idCardNum.setText("");
        if (addressTo==null)
            return;
        receiver.setText(addressTo.getReceiverName());
        receiverNumber.setText(addressTo.getReceiverPhone());
        receiverAddress.setText(addressTo.getReceiverArea() + "" + addressTo.getReceiverDetailAddress());
        realIdCard=addressTo.getIdentityCardNo();
        if (addressTo.getIdentityCardNo()!=null)
        idCardNum.setText(addressTo.getIdentityCardNo().replaceAll("(?<=\\d{4})\\d(?=\\d{4})", "*") );
    }
    @Override
    protected void onRestart() {
        super.onRestart();
//        if(isGoodsListBack){
//            isGoodsListBack=false ;
//            initAddressData();
//        }
//        else if(isGoodsPothoBack){
//            isGoodsPothoBack=false ;
//            initAddressData();
//        }
//        else if ( SpUtil.getBoolean(getThisContext(),"IsAddressBack")){
//            SpUtil.put(getThisContext(),"IsAddressBack",false);
//            if (SpUtil.getBoolean(getThisContext(), "NoIdCard")) {
//                getDefaultAddress();
//            }else {
//                initAddressData();
//            }
//
//        }else {
//
//            String userJson = ConfigUtil.getString(sp, "SelectAddress", "");
//            if (SpUtil.getBoolean(getThisContext(), "NoIdCard")) {
//                getDefaultAddress();
//            } else if (!TextUtils.isEmpty(userJson)) {
//                AddressTo addressTo1 = JSON.parseObject(userJson, AddressTo.class);
//                setSelectAddress(addressTo1);
//                ConfigUtil.saveString(PreferenceManager.getDefaultSharedPreferences(getThisContext()),
//                        "SelectAddress", JSON.toJSONString(addressTo1));
//            }
//        }
    }

    private void initAddressData() {
        addressList.clear();
        GetAddressListParam param=new GetAddressListParam();
        param.setUserId(mUserHelperBulk.getSid());

        NewShopApi api= ApiClientBulk.create(NewShopApi.class);

        api.getAddress(param, new HttpCallback<MessageToBulk<List<AddressTo>>>(getThisContext()) {
            @Override
            public void success(MessageToBulk<List<AddressTo>> msg, Response response) {

                if (msg.getCode() == 0) {
                    addressList.addAll(msg.getReceiverAddressList());
                    setSelectAddressOther(addressList);


                }
            }
        });

    }
    public void setSelectAddressOther(List<AddressTo>addressList){
        for (AddressTo addressTo:addressList){
            if (addressTo.getReceiverName().equals(receiver.getText().toString())&&(addressTo.getReceiverArea()+addressTo.getReceiverDetailAddress()).equals(receiverAddress.getText().toString())){
                if (!TextUtils.isEmpty(addressTo.getIdentityCardNo())) {
                    idCardNum.setText(addressTo.getIdentityCardNo().replaceAll("(?<=\\d{4})\\d(?=\\d{4})", "*"));
                    realIdCard=addressTo.getIdentityCardNo();
                }
            }
        }


    }
}
