package com.joy.property.shop;

import android.app.Activity;
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
import com.jinyi.ihome.module.newshop.ActivitySubmitMerchant;
import com.jinyi.ihome.module.newshop.AddressListParam;
import com.jinyi.ihome.module.newshop.AddressTo;
import com.jinyi.ihome.module.newshop.CarGoodsInfo;
import com.jinyi.ihome.module.newshop.CartSettleGoodsTo;
import com.jinyi.ihome.module.newshop.ChangeAddressTo;
import com.jinyi.ihome.module.newshop.ConfirmOrderTo;
import com.jinyi.ihome.module.newshop.CouponCalculateParam;
import com.jinyi.ihome.module.newshop.CouponCalculateTo;
import com.jinyi.ihome.module.newshop.CouponParam;
import com.jinyi.ihome.module.newshop.CouponTo;
import com.jinyi.ihome.module.newshop.DefaultAddressParam;
import com.jinyi.ihome.module.newshop.DefaultAddressTo;
import com.jinyi.ihome.module.newshop.GetAddressListParam;
import com.jinyi.ihome.module.newshop.OlderListTo;
import com.jinyi.ihome.module.newshop.OlderSidTo;
import com.jinyi.ihome.module.newshop.PayInfo;
import com.jinyi.ihome.module.newshop.PayOlderTo;
import com.jinyi.ihome.module.newshop.PayParam;
import com.jinyi.ihome.module.newshop.SaveCouponParam;
import com.jinyi.ihome.module.newshop.SubmitGoodsInfo;
import com.jinyi.ihome.module.newshop.SubmitMerchant;
import com.jinyi.ihome.module.newshop.SubmitParam;
import com.jinyi.ihome.module.newshop.UserAddressParam;
import com.jinyi.ihome.module.newshop.UserAddressTo;
import com.jinyi.ihome.module.owner.UserInfoTo;
import com.joy.common.api.ApiClientBulk;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.NewShopApi;


import com.joy.common.application.KeyValue;
import com.joy.library.utils.ConfigUtil;
import com.joy.property.MainApp;
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
import rx.Observable;

/**
 * Created by xz on 2016/7/23.
 **/
public class ConfirmOlderActivity extends BaseActivity implements View.OnClickListener {
    private TextView receiver;
    private TextView receiverNumber;
    private TextView receiverAddress;
    private DefaultAddressTo defaultAddressTo=new DefaultAddressTo();
    private TextView idCardNum;
    private String isSeaBuy;
    private GridLayout gridView;
    private TextView goodsNumber;
    private TextView price;
    private TextView trafficExpense;
    private TextView trafficPattern;
    private TextView useCondition;
    private TextView payWay;
    private TextView price2;
    private TextView trafficExpense2;
    private TextView couponFee;
    private TextView smallAmount;
    private TextView actuanPay;
    private TextView pay;
    private ConfirmOrderTo orderTo=new ConfirmOrderTo();
    private List<CartSettleGoodsTo> carTos=new ArrayList<>();
    private  double allPrice=0;
    private  double trafficFee=0;
    private  String images="";
    private String[] path;
    private List<CarGoodsInfo> goodsInfos=new ArrayList<>();
    private String paths="";
    private String payOrderId;
    private LinearLayout sendLayout;
    private RelativeLayout idCard_layout;
    private PayOlderTo payPartnerTo = null;
    private static final int SDK_PAY_FLAG = 1;
    private double totalPrice;
    private String intentOlderSid;
    private boolean isMyShopPage;
    private double actualPayPrice=0;
    private String cartId;
    private double currentPrice=0;
    private int selectPosition=0;
    private String realIdCard;
    private List<AddressTo>addressList=new ArrayList<>();
    private List<String>saveCouponList=new ArrayList<>();
    private List<CouponTo>couponList=new ArrayList<>();
    private List<String>selectCouponList=new ArrayList<>();
    private List<EditText>remarkList=new ArrayList<>() ;
    private List<CouponCalculateTo>calculateActivityList=new ArrayList<>();
    private List<CouponCalculateTo>calculateList=new ArrayList<>();
    private Boolean isGoodsListBack=false;
    private Boolean isGoodsPothoBack=false;
    private AddressTo addressJson;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                   Bundle bundle= msg.getData();

//                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    PayResult payResult = new PayResult((String) msg.obj);
                    // 支付宝返回此次支付结果及加签，建议对支付宝签名信息拿签约时支付宝提供的公钥做验签
                    String resultInfo = payResult.getResult();
                    String resultStatus = payResult.getResultStatus();

                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        String payNo=resultInfo.substring(resultInfo.indexOf("out_trade_no=")+"out_trade_no=".length(),resultInfo.indexOf("&subject"));
                        Toast.makeText(getThisContext(), "支付成功", Toast.LENGTH_SHORT).show();
                        submittedSuccessfullyDialog(bundle.getString("OlderSid"),payNo);
                    } else {
                        // 判断resultStatus 为非“9000”则代表可能支付失败
                        // “8000”代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(getThisContext(), "支付结果确认中", Toast.LENGTH_SHORT).show();
                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(getThisContext(), "支付失败", Toast.LENGTH_SHORT).show();

                        }
                        submittedFailfullyDialog("","");
                    }

                    break;
                }
                default:
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);
        initView();
        initData();
        PublicWay.activityList.add(this);
    }

    private void initData() {
        carTos = (List<CartSettleGoodsTo>) getIntent().getSerializableExtra("carList");
        cartId=carTos.get(0).getCartId();
        System.out.println(carTos+"cartos"+carTos.size());
        setView();
    }

    private void initView() {
        idCardNum = (TextView) findViewById(R.id.idCardNum);
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

        findViewById(R.id.addressLayout).setOnClickListener(this);


        payWay = (TextView) findViewById(R.id.payWay);

        actuanPay = (TextView) findViewById(R.id.actualPay);
        pay = (TextView) findViewById(R.id.pay);
        pay.setOnClickListener(this);
        findViewById(R.id.back).setOnClickListener(this);
    }

public void  setView(){

  getDefaultAddress();
    setSendLayout();
    SpUtil.put(getThisContext(), "NoIdCard", false);
    SpUtil.put(getThisContext(),"IsAddressBack",false);
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
        if (TextUtils.isEmpty(receiverAddress.getText().toString())) {
            ToastShowLong(getThisContext(),"请输入收货地址");
        }else if ("yes".equals(getIntent().getStringExtra("IsSeaBuy"))&&TextUtils.isEmpty(idCardNum.getText())) {
            ToastShowLong(getThisContext(),"您购买了海外购商品，请填写身份证号码");
        }
        else {
            NewShopApi api= ApiClientBulk.create(NewShopApi.class);
            SubmitParam param=new SubmitParam();
            param.setCartId(carTos.get(0).getCartId());
            param.setReceiverDetailAddress((receiverAddress.getText().toString()));
            param.setReceiverName(receiver.getText().toString());
            param.setReceiverPhone(receiverNumber.getText().toString());
            param.setUserId(mUserHelperBulk.getSid());
            param.setIdentityCardNo(realIdCard);

            param.setIsSeaBuy("yes".equals(getIntent().getStringExtra("IsSeaBuy"))?"yes":"no");
            List<SubmitMerchant>merchantList=new ArrayList<>();
            ActivitySubmitMerchant activityMerchant=new ActivitySubmitMerchant();

            for (int i=0;i<carTos.size();i++ ){
                CartSettleGoodsTo goodsInfo=carTos.get(i);
                List<SubmitGoodsInfo> goodsInfoList=new ArrayList<>();
                SubmitMerchant merchant=new SubmitMerchant();

             if (!"activity".equals(goodsInfo.getMerchantId())) {
//                    if (DoubleUtil.reduce(goodsInfo.getGoodsTotalAmount(), goodsInfo.getCouponCost()) <= 0) {
//                        merchant.setActualPayAmount(goodsInfo.getDistributionCost() + "");
//
//                    } else {
//                        merchant.setActualPayAmount(DoubleUtil.reduce(DoubleUtil.add(goodsInfo.getGoodsTotalAmount(), goodsInfo.getDistributionCost()), goodsInfo.getCouponCost()) + "");
//                    }
                    merchant.setActualPayAmount(totalPrice+"");

                    merchant.setDistributionCost(goodsInfo.getDistributionCost() + "");
                    merchant.setDistributionMode(goodsInfo.getDistributionMode());
                    merchant.setMerchantId(goodsInfo.getMerchantId());
                    merchant.setCouponId(goodsInfo.getCouponId());

                    merchant.setCustomerComments(remarkList.get(i).getText().toString());

                    if (goodsInfo.getCouponId() == null)
                        merchant.setCouponId("");


                    merchant.setGoodsTotalAmount(goodsInfo.getGoodsTotalAmount() + "");
                }else {
//                    if (DoubleUtil.reduce(goodsInfo.getGoodsTotalAmount(), goodsInfo.getCouponCost()) <= 0) {
//                        activityMerchant.setActualPayAmount(goodsInfo.getDistributionCost() + "");
//
//                    } else {
//                        activityMerchant.setActualPayAmount(DoubleUtil.reduce(DoubleUtil.add(goodsInfo.getGoodsTotalAmount(), goodsInfo.getDistributionCost()), goodsInfo.getCouponCost()) + "");
//                    }
                 activityMerchant.setActualPayAmount(totalPrice+"");

                    activityMerchant.setDistributionCost(goodsInfo.getDistributionCost() + "");
                    activityMerchant.setDistributionMode(goodsInfo.getDistributionMode());
                    activityMerchant.setCouponId(goodsInfo.getCouponId());
                    activityMerchant.setCustomerComments(remarkList.get(i).getText().toString());

                    if (goodsInfo.getCouponId() == null)
                        activityMerchant.setCouponId("");


                    activityMerchant.setGoodsTotalAmount(goodsInfo.getGoodsTotalAmount() + "");

                }
                goodsInfoList.clear();
                for (CarGoodsInfo info:goodsInfo.getCartMerchantGoodsVolist()){
                    SubmitGoodsInfo submitGoodsInfo=new SubmitGoodsInfo();
                    submitGoodsInfo.setGoodsId(info.getGoodsId());
                    submitGoodsInfo.setRetailPrice(info.getRetailPrice() + "");
                    submitGoodsInfo.setSpecificationsId(info.getSpecificationsId());
                    if (TextUtils.isEmpty(info.getSpecificationsName())||"null".equals(info.getSpecificationsName())||info.getSpecificationsName().contains("null"))
                        submitGoodsInfo.setSpecificationsName("");
                       else
                    submitGoodsInfo.setSpecificationsName(info.getSpecificationsName());
                    submitGoodsInfo.setGoodsNum(info.getGoodsNum() + "");
                    submitGoodsInfo.setRetailPrice(info.getRetailPrice() + "");
                    submitGoodsInfo.setGoodsName(info.getGoodsName());
                    submitGoodsInfo.setGoodsType(info.getGoodsType());
                    submitGoodsInfo.setActivityId(info.getActivityId());
                    goodsInfoList.add(submitGoodsInfo);
                }
                if (!"activity".equals(goodsInfo.getMerchantId())) {
                    merchant.setMerchantGoodslist(goodsInfoList);
                    merchantList.add(merchant);
                }
                else {
                    activityMerchant.setActivityGoodslist(goodsInfoList);


                }

            }

            param.setMerchantList(merchantList);
               if (activityMerchant.getActivityGoodslist()==null||activityMerchant.getActivityGoodslist().size()==0)
                   param.setActivityGoodsOrder(null);
            else
                param.setActivityGoodsOrder(activityMerchant);
            System.out.println(param+"param---------");
            CustomDialogFragment dialogFragment=new CustomDialogFragment();
            dialogFragment.show(getSupportFragmentManager(),"");
            api.submitOrder(param, new HttpCallback<MessageToBulk<List<OlderListTo>>>(getThisContext()) {
                @Override
                public void success(MessageToBulk<List<OlderListTo>> msg, Response response) {
                    dialogFragment.dismiss();
                    if (msg.getCode() == 0) {
                        List<OlderSidTo> sidList = new ArrayList<>();
                        for (OlderListTo listTo : msg.getMerchantOrderList()) {
                            if(listTo!=null) {
                                OlderSidTo sidTo = new OlderSidTo();
                                sidTo.setOrderId(listTo.getOrderId());
                                sidList.add(sidTo);
                            }
                        }
                        if (msg.getActivityGoodsOrder()!=null&&msg.getActivityGoodsOrder().getOrderId()!=null)
                            sidList.add(msg.getActivityGoodsOrder());
                        payMoney(sidList);
                        confirmCoupon(param.getMerchantList());
                    }else
                        ToastShowLong(getThisContext(),msg.getMessage());
                }

                @Override
                public void failure(RetrofitError error) {
                    dialogFragment.dismiss();

                }
            });

        }



    }

public void setSendLayout(){


    for(int i=0;i<carTos.size();i++) {

        View viewSend=View.inflate(getThisContext(),R.layout.send_goods_item,null);
        gridView = (GridLayout) viewSend.findViewById(R.id.gridView);
        goodsNumber = (TextView)  viewSend.findViewById(R.id.goodsNumber);
        RelativeLayout goodsListLayout = (RelativeLayout) viewSend.findViewById(R.id.goodsListLayout);
        TextView storeName = (TextView) viewSend.findViewById(R.id.storeName);
        price = (TextView)  viewSend.findViewById(R.id.price);
        trafficExpense = (TextView) viewSend. findViewById(R.id.trafficExpense);
        trafficPattern = (TextView)  viewSend.findViewById(R.id.trafficPattern);
        useCondition = (TextView) viewSend. findViewById(R.id.useCondition);
        price2 = (TextView) viewSend. findViewById(R.id.price2);
        trafficExpense2 = (TextView) viewSend. findViewById(R.id.trafficExpense2);
        couponFee = (TextView) viewSend. findViewById(R.id.couponFee);
        smallAmount = (TextView) viewSend. findViewById(R.id.activityName);
        EditText remark = (EditText) viewSend.findViewById(R.id.remarks);


        goodsInfos.clear();
        for (int j = 0; j < carTos.get(i).getCartMerchantGoodsVolist().size(); j++) {
            goodsInfos.add(carTos.get(i).getCartMerchantGoodsVolist().get(j));
;
        }
        remarkList.add(remark);
        if (!TextUtils.isEmpty(carTos.get(i).getStoresName()))
         storeName.setText(carTos.get(i).getStoresName());
         goodsNumber.setText(goodsInfos.size() + "");
         String trafficMode = "";
         allPrice=carTos.get(i).getGoodsTotalAmount();
         trafficMode = carTos.get(i).getDistributionMode();
         trafficFee =  carTos.get(i).getDistributionCost();


        price.setText("¥" + allPrice + "");
        price2.setText("¥" + allPrice + "");

        trafficPattern.setText(trafficMode.substring(0, trafficMode.length()));
        trafficExpense.setText("配送费:¥" + trafficFee);
        trafficExpense2.setText("¥" + trafficFee + "");
        totalPrice=DoubleUtil.add(totalPrice,DoubleUtil.add(allPrice,trafficFee));

        getCoupon(i,carTos.get(i), allPrice, couponFee, useCondition, smallAmount, actuanPay, trafficFee);

        path = new String[goodsInfos.size()];

        paths="";
        for (int j= 0; j < goodsInfos.size(); j++) {
            if ("正常".equals(goodsInfos.get(j).getIsInvalid())) {
                paths = paths + goodsInfos.get(j).getPicUrl() + ";";
            }
        }
        if (paths.length()<1)
            return;
        paths=paths.substring(0,paths.length()-1);
        path=paths.split(";");
        gridView.removeAllViews();
        for (int k= 0; k < path.length; k++) {
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
            Picasso.with(getThisContext()).load(MainApp.getPicassoImagePath(path[k])).placeholder(R.drawable.shop_car_goods_bg).error(R.drawable.post_content_bg).into(postImage);

            postImage.setTag(R.id.tag_first, k);

            view.setLayoutParams(layoutParams);


            postImage.setOnClickListener(v -> {
                Intent intent = new Intent(getThisContext(), PictureShowActivity.class);
                intent.putExtra("index", (int) v.getTag(R.id.tag_first));
                intent.putExtra("path", paths.substring(0, paths.length()));
                isGoodsPothoBack=true;
                startActivity(intent);
            });
        //     carTos.get(i).setRemark(remark);
            gridView.addView(view);
        }
        goodsListLayout.setTag(carTos.get(i).getCartMerchantGoodsVolist());
        goodsListLayout.setOnClickListener(view -> {
        setJumpGoodsList((List<CarGoodsInfo>) goodsListLayout.getTag());});

        sendLayout.addView(viewSend);
        actuanPay.setText("¥" + totalPrice);
    }
}
    public void payMoney(List<OlderSidTo> olderSid){
if (olderSid.size()==0){
    submittedSuccessfullyDialog("","");
}else {

    NewShopApi api = ApiClientBulk.create(NewShopApi.class);
    PayParam param = new PayParam();
    param.setOrderList(olderSid);
    CustomDialogFragment dialogFragment = new CustomDialogFragment();
    dialogFragment.show(getSupportFragmentManager(), "");
    intentOlderSid = olderSid.get(0).getOrderId();

    api.payOlder(param, new HttpCallback<MessageToBulk<PayInfo>>(getThisContext()) {
        @Override
        public void success(MessageToBulk<PayInfo> msg, Response response) {
            dialogFragment.dismiss();

            if (msg.getCode() == 0) {
                Log.i("111", ""+msg.getCode());

                payOrderId = msg.getPayOrderId();
                payPartnerTo = msg.getPayinto().getPayPartners().get(0);
                if (TextUtils.equals(payPartnerTo.getPayPartner().toUpperCase(), "ALIPAY_WALLET")) {
                    // 客户端支付
                    final String payPartner = payPartnerTo.getPayUrl();
                    Runnable payRunnable = new Runnable() {

                        @Override
                        public void run() {
                            // 构造PayTask 对象
                            PayTask alipay = new PayTask(ConfirmOlderActivity.this);
                            // 调用支付接口，获取支付结果
//                            Map<String, String> result = alipay.payV2(payPartner, true);
                            String result = alipay.pay(payPartner);
                            Message msg = new Message();
                            msg.what = SDK_PAY_FLAG;
                            msg.obj = result;
                            Bundle bundle = new Bundle();
                            bundle.putString("OlderSid", olderSid.get(0).getOrderId());
                            //  bundle.putString("OlderNumber",olderNumber);

                            msg.setData(bundle);
                            mHandler.sendMessage(msg);
                        }
                    };

                    // 必须异步调用
                    Thread payThread = new Thread(payRunnable);
                    payThread.start();
                }
            }else

                ToastShowLong(getThisContext(),msg.getMessage());
        }

        @Override
        public void failure(RetrofitError error) {
            dialogFragment.dismiss();
        }
    });

}
    }
    private void submittedSuccessfullyDialog(String olderSid,String payNo) {
        Intent intent=new Intent(getThisContext(),FinishPayActivity.class);
        intent.putExtra("PayOrderId",payOrderId);
        intent.putExtra("olderSid", intentOlderSid);
        intent.putExtra("IsMyShopPage", true);
        startActivity(intent);
        goToAnimation(1);
    }
    private void submittedFailfullyDialog(String olderSid,String payNo) {


//        if (isMyShopPage){
            for (Activity activity: PublicWay.activityList)
                activity.finish();
        if (PublicWay.goodsDetailActivity!=null)
            PublicWay.goodsDetailActivity.finish();
            Intent intent = new Intent(getThisContext(), MyShoppingActivity.class);
            intent.putExtra("IsFinishPay", true);
            intent.putExtra("CurrentPage",1);
            intent.putExtra("type", 1);
            startActivity(intent);
            finish();
            goToAnimation(1);
//        }else {
//            Intent intent = new Intent(getThisContext(), OlderDetailActivity.class);
//            intent.putExtra("olderSid", intentOlderSid);
//
//            intent.putExtra("type", 1);
//            startActivity(intent);
//            for (Activity activity: PublicWay.activityList)
//                activity.finish();
//            finish();
//            goToAnimation(1);
//        }
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
    public void setJumpGoodsList(List<CarGoodsInfo> goodsList){
        Intent intent=new Intent(getThisContext(),GoodsListActivity.class);
        intent.putExtra("goodsList", (Serializable) goodsList);
        isGoodsListBack=true;
        startActivity(intent);
        goToAnimation(1);
    }
    private void ConponDialogShow(CartSettleGoodsTo goodsTo,double olderPrice,TextView couponText,TextView couponCondition,TextView smallAmount,TextView actuanPay,double trafficFee,int selecPosition,double currentPrice) {
        final CustomDialog dialog = new CustomDialog(this, R.layout.dialog_coupon, R.style.myDialogTheme);
        ImageView noCoupon = (ImageView) dialog.findViewById(R.id.noCoupon);
        List<CouponTo>mCouponList=new ArrayList<>();
        mCouponList.clear();
        Observable.from(couponList).filter(couponTo ->!saveCouponList.contains(couponTo.getCouponId())).subscribe(mCouponList::add);
        if (mCouponList.size()>0)
            noCoupon.setVisibility(View.GONE);
                        CouponAdapter couponAdapter = new CouponAdapter(getThisContext());
                        couponAdapter.setList(mCouponList);
                        ListView listView = (ListView) dialog.findViewById(R.id.listView);
                        listView.setAdapter(couponAdapter);
                        listView.setDividerHeight(0);
                        couponAdapter.notifyDataSetChanged();


        RelativeLayout layout = (RelativeLayout) dialog.findViewById(R.id.layout);
        layout.setOnClickListener(v -> dialog.dismiss());

        TextView purchaseClose = (TextView) dialog.findViewById(R.id.cancel);
        purchaseClose.setOnClickListener(v -> dialog.dismiss());

        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        dialog.show();

listView.setOnItemClickListener((adapterView, view, position, l) -> {

    selectCoupon(olderPrice, couponText, couponCondition, smallAmount, actuanPay, trafficFee, mCouponList.get(position), selecPosition, currentPrice);
    calculateCoupon(goodsTo, "", smallAmount, actuanPay, trafficFee);
    dialog.dismiss();
});

    }
    public void confirmCoupon(List<SubmitMerchant>merchantList){
        NewShopApi api=ApiClientBulk.create(NewShopApi.class);
        for (SubmitMerchant merchant:merchantList) {
            if (!TextUtils.isEmpty(merchant.getCouponId())) {
                SaveCouponParam param = new SaveCouponParam();
                param.setMerchantId(merchant.getMerchantId());
                param.setCartId(cartId);
                param.setCouponId(merchant.getCouponId());
                api.confirmCoupon(param, new HttpCallback<MessageToBulk>(getThisContext()) {
                    @Override
                    public void success(MessageToBulk msg, Response response) {

                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }
                });
            }
        }
    }
    public void getCoupon(int position,CartSettleGoodsTo goodsInfo,double olderPrice,TextView couponText,TextView couponCondition,TextView smallAmount,TextView actuanPay,double trafficFee){
        NewShopApi api=ApiClientBulk.create(NewShopApi.class);
        CouponParam param=new CouponParam();
        param.setCartId(goodsInfo.getCartId());
        param.setMerchantId("activity".equals(goodsInfo.getMerchantId()) ? null : goodsInfo.getMerchantId());
        param.setMerchantId(param.getMerchantId() == null ? "activity" : param.getMerchantId());
        param.setUserId(mUserHelperBulk.getSid());
        param.setGoodsType(goodsInfo.getCartMerchantGoodsVolist().get(0).getGoodsType());
        List<CouponParam.CouponParamTo> couponParamToList=new ArrayList<>();
        List<CouponParam.CouponParamTo> activityCouponParamToList=new ArrayList<>();
        couponParamToList.clear();
        activityCouponParamToList.clear();
        for (CarGoodsInfo goods:goodsInfo.getCartMerchantGoodsVolist()){
            CouponParam.CouponParamTo couponParamTo=new CouponParam.CouponParamTo();
            if (goods.getGoodsId()!=null){
                couponParamTo.setGoodsId(goods.getGoodsId());
                couponParamTo.setRetailPrice(goods.getRetailPrice() + "");
                couponParamTo.setGoodsNum(goods.getGoodsNum() + "");
                couponParamTo.setSpecificationsId(goods.getSpecificationsId());
                if ("0".equals(goods.getGoodsType()))
                couponParamToList.add(couponParamTo);
                else
                activityCouponParamToList.add(couponParamTo);
            }
        }

        param.setMerchantGoodslist(couponParamToList);

       param.setActivityGoodslist(activityCouponParamToList);
        System.out.println(param+"param");
        api.getCoupon(param, new HttpCallback<MessageToBulk<List<CouponTo>>>(getThisContext()) {
            @Override
            public void success(MessageToBulk<List<CouponTo>> msg, Response response) {
                System.out.println(msg.getMessage()+"messageCoupon");
                if (msg.getCode() == 0) {
                    if (msg.getCartCouponList() != null) {
                        if ( carTos.get(position).getCouponList()!=null)
                     carTos.get(position).getCouponList().clear();
                        if (msg.getCartCouponList() != null)
                          carTos.get(position).setCouponList(msg.getCartCouponList());
                         couponList=carTos.get(position).getCouponList();
                        setCouponLayout(carTos.get(position).getCouponList(), olderPrice, couponText, couponCondition, smallAmount, actuanPay, trafficFee, goodsInfo, false);
                    }

                }
            }

            @Override
            public void failure(RetrofitError error) {
            }
        });
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
        }else{
            AddressTo addressTo01=new AddressTo();
            Intent intent=new Intent(getThisContext(),MyAddressActivity.class);
            intent.putExtra("IsSeaBuy", isSeaBuy);
            startActivityForResult(intent,15);
            goToAnimation(1);
        }

    }
    public void changeAddressText(AddressTo addressTo){
        receiverNumber.setText(addressTo.getReceiverPhone());
        receiverAddress.setText(addressTo.getReceiverArea() + "" + addressTo.getReceiverDetailAddress());
        receiver.setText(addressTo.getReceiverName());
        idCardNum.setText(addressTo.getIdentityCardNo());

    }
    private double noRulePrice=0.00;
    private double haveRulePrice=0.00;
    private double useAmount;
    private String couponIdRule;
    private String couponIdnoRule;
    public void setCouponLayout(List<CouponTo>couponList,double olderPrice,TextView couponText,TextView couponCondition,TextView smallAmount,TextView actuanPay,double trafficFee,CartSettleGoodsTo goodsTo,boolean isSelect){
        noRulePrice=0.00;
        haveRulePrice=0.00;
        String couponId="";

         selectPosition=0;
        Observable.from(couponList).filter(couponTo -> couponTo.getUseRule() == 0).filter(couponTo3 -> !saveCouponList.contains(couponTo3.getCouponId())).subscribe(couponTo1 -> {
            if (couponTo1.getDiscountAmount() > noRulePrice) {
                noRulePrice = couponTo1.getDiscountAmount();
                couponIdnoRule = couponTo1.getCouponId();
            }
        });
        Observable.from(couponList).filter(couponTo -> couponTo.getUseRule() == 1).filter(couponTo2 -> olderPrice>=couponTo2.getConsumptionAmount()).filter(couponTo3 -> !saveCouponList.contains(couponTo3.getCouponId())).subscribe(couponTo1 -> {
            if (couponTo1.getDiscountAmount() > haveRulePrice) {
                haveRulePrice = couponTo1.getDiscountAmount();
                useAmount = couponTo1.getConsumptionAmount();
                couponIdRule = couponTo1.getCouponId();
            }
        });

        if (noRulePrice>haveRulePrice)
        {
            couponText.setText("-¥"+noRulePrice);
            couponCondition.setText("折扣"+noRulePrice+"元");
            couponId=couponIdnoRule;
            smallAmount.setText("¥"+(DoubleUtil.reduce(olderPrice,noRulePrice)<=0?trafficFee:DoubleUtil.reduce(DoubleUtil.add(olderPrice,trafficFee),noRulePrice)));
            actualPayPrice=DoubleUtil.add(actualPayPrice,DoubleUtil.reduce(olderPrice, noRulePrice)<=0?trafficFee:DoubleUtil.reduce((DoubleUtil.add(olderPrice, trafficFee)), noRulePrice));
           currentPrice=DoubleUtil.reduce(olderPrice, noRulePrice)<=0?trafficFee:DoubleUtil.reduce((DoubleUtil.add(olderPrice, trafficFee)), noRulePrice);
        }else{
            couponText.setText("-¥"+haveRulePrice);
            couponCondition.setText("折扣"+haveRulePrice+"元");
            smallAmount.setText("¥"+(DoubleUtil.reduce(olderPrice,haveRulePrice)<=0?trafficFee:DoubleUtil.reduce(DoubleUtil.add(olderPrice,trafficFee),haveRulePrice)));
            actualPayPrice=DoubleUtil.add(actualPayPrice,DoubleUtil.reduce(olderPrice, haveRulePrice)<=0?trafficFee:DoubleUtil.reduce((DoubleUtil.add(olderPrice, trafficFee)), haveRulePrice));
            currentPrice=DoubleUtil.reduce(olderPrice, haveRulePrice)<=0?trafficFee:DoubleUtil.reduce((DoubleUtil.add(olderPrice, trafficFee)), haveRulePrice);
            couponId=couponIdRule;
        }
        if (useAmount==0&&haveRulePrice==0&&noRulePrice==0)
            couponCondition.setText("无可用优惠券");

        actuanPay.setText("¥" + actualPayPrice);
        if (!saveCouponList.contains(couponId))
            saveCouponList.add(couponId);
         for (int i=0;i<carTos.size();i++){

             if (carTos.get(i).getMerchantId().equals(goodsTo.getMerchantId())) {
                 carTos.get(i).setCouponId(couponId);
              couponCondition.setTag(i);
                 if (haveRulePrice>noRulePrice)
                     carTos.get(i).setCouponCost(haveRulePrice);
                 else
                     carTos.get(i).setCouponCost(noRulePrice);
                 break;
             }
         }
        System.out.println(goodsTo);
        if (!TextUtils.isEmpty(couponId))
            calculateCoupon(goodsTo, couponId, smallAmount, actuanPay, trafficFee);

        couponCondition.setOnClickListener(view -> ConponDialogShow(goodsTo, olderPrice, couponText, couponCondition, smallAmount, actuanPay, trafficFee, (Integer) couponCondition.getTag(), currentPrice));
    }
    public void selectCoupon(double olderPrice,TextView couponText,TextView couponCondition,TextView smallAmount,TextView actuanPay,double trafficFee,CouponTo couponTo,int selecPosition,double currentPrice){
        saveCouponList.remove(carTos.get(selecPosition).getCouponId());
        carTos.get(selecPosition).setCouponId(couponTo.getCouponId());
        carTos.get(selecPosition).setCouponCost(couponTo.getDiscountAmount());

        saveCouponList.add(couponTo.getCouponId());

        couponCondition.setText("折扣" + couponTo.getDiscountAmount());
        couponText.setText("-¥" + couponTo.getDiscountAmount());

        if (DoubleUtil.reduce(olderPrice, couponTo.getDiscountAmount())<=0)
            smallAmount.setText(trafficFee+"");
        else
        smallAmount.setText("¥" + DoubleUtil.reduce(DoubleUtil.add(olderPrice, trafficFee), couponTo.getDiscountAmount()));

        actuanPay.setText("¥" + (DoubleUtil.add(DoubleUtil.reduce(actualPayPrice, currentPrice), DoubleUtil.reduce(olderPrice, couponTo.getDiscountAmount()) <= 0 ? trafficFee : DoubleUtil.reduce(DoubleUtil.add(olderPrice, trafficFee), couponTo.getDiscountAmount()))));
    }

    public void getDefaultAddress() {
        NewShopApi api=ApiClientBulk.create(NewShopApi.class);
        AddressListParam param=new AddressListParam();
        param.setUserId(mUserHelperBulk.getSid());


        api.getMyDefaultReceiveAddress(param, new HttpCallback<MessageToBulk<DefaultAddressTo>>(getThisContext()) {
            @Override
            public void success(MessageToBulk<DefaultAddressTo> msg, Response response) {

                if (msg.getCode() == 0) {
                    if (msg.getReceiverAddress() != null) {
                        findViewById(R.id.addAddressLayout).setVisibility(View.GONE);
                        findViewById(R.id.haveAddressLayout).setVisibility(View.VISIBLE);
                        defaultAddressTo = msg.getReceiverAddress();
                        receiver.setText(defaultAddressTo.getReceiverName());
                        idCardNum.setText("");
                        receiverAddress.setText(defaultAddressTo.getReceiverArea() + "" + defaultAddressTo.getReceiverDetailAddress());
                        if (defaultAddressTo.getIdentityCardNo() != null)
                            idCardNum.setText(defaultAddressTo.getIdentityCardNo().replaceAll("(?<=\\d{4})\\d(?=\\d{4})", "*"));
                        receiverNumber.setText(defaultAddressTo.getReceiverPhone());
                        realIdCard = defaultAddressTo.getIdentityCardNo();
                    }

                } else if (msg.getCode() == -1) {
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
        receiverAddress.setText((TextUtils.isEmpty(addressTo.getReceiverArea())?"":addressTo.getReceiverArea())  + addressTo.getReceiverDetailAddress());
        realIdCard=addressTo.getIdentityCardNo();
        if (!TextUtils.isEmpty(addressTo.getIdentityCardNo()))
        idCardNum.setText(addressTo.getIdentityCardNo().replaceAll("(?<=\\d{4})\\d(?=\\d{4})", "*") );
        else
            idCardNum.setVisibility(View.GONE);

    }

    @Override
    protected void onRestart() {
        super.onRestart();
//        if(isGoodsListBack){
//            isGoodsListBack=false ;
//            initAddressData();
//        }
//
//        else if(isGoodsPothoBack){
//            isGoodsPothoBack=false ;
//            initAddressData();
//        }
//        else if ( SpUtil.getBoolean(getThisContext(),"IsAddressBack")){
//
//            SpUtil.put(getThisContext(), "IsAddressBack", false);
//            String userJson = ConfigUtil.getString(sp, "SelectAddress", "");
//
//          if (!TextUtils.isEmpty(userJson)){
//
//              AddressTo addressTo1 = JSON.parseObject(userJson, AddressTo.class);
//              addressJson=addressTo1;
//              setSelectAddress(addressTo1);
//              ConfigUtil.saveString(PreferenceManager.getDefaultSharedPreferences(getThisContext()),
//                      "SelectAddress", JSON.toJSONString(null));
//          }else {
//              ToastShowLong(getThisContext(),userJson+"取值了"+SpUtil.getBoolean(getThisContext(), "NoIdCard"));
//              if (SpUtil.getBoolean(getThisContext(), "NoIdCard")) {
//                  getDefaultAddress();
//
//              } else {
//                  initAddressData();
//              }
//          }
//        }else {
//
//            String userJson = ConfigUtil.getString(sp, "SelectAddress", "");
//            if (SpUtil.getBoolean(getThisContext(), "NoIdCard")) {
//                getDefaultAddress();
//            } else if (!TextUtils.isEmpty(userJson)) {
//                AddressTo addressTo1 = JSON.parseObject(userJson, AddressTo.class);
//                addressJson=addressTo1;
//                setSelectAddress(addressTo1);
//                ConfigUtil.saveString(PreferenceManager.getDefaultSharedPreferences(getThisContext()),
//                        "SelectAddress", JSON.toJSONString(null));
//            }
//            else {
//                initAddressData();
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

    public void calculateCoupon(CartSettleGoodsTo goodsInfo,String couponId,TextView smallAmount,TextView allPrice,double expressCost){
        NewShopApi api= ApiClientBulk.create(NewShopApi.class);
        CouponCalculateParam param=new CouponCalculateParam();
        param.setCartId(cartId);
       // param.setGoodsType((goodsInfo.getCartMerchantGoodsVolist() == null || goodsInfo.getCartMerchantGoodsVolist().size() == 0) ? "10" : "0");
        param.setMerchantId(goodsInfo.getMerchantId());
        param.setUserId(mUserHelperBulk.getSid());
        param.setCouponId(goodsInfo.getCouponId());
      //  param.setGoodsType(goodsInfo.getCartMerchantGoodsVolist().get(0).getGoodsType());
        calculateActivityList.clear();
        calculateList.clear();
        if(goodsInfo.getCartMerchantGoodsVolist()!=null&&goodsInfo.getCartMerchantGoodsVolist().size()>0){
            for (CarGoodsInfo info:goodsInfo.getCartMerchantGoodsVolist()){

                CouponCalculateTo calculateTo=new CouponCalculateTo();
                calculateTo.setRetailPrice(info.getRetailPrice()+"");
                calculateTo.setGoodsType(info.getGoodsType());
                calculateTo.setGoodsId(info.getGoodsId());
                calculateTo.setGoodsNum(info.getGoodsNum() + "");
                calculateTo.setSpecificationsId(info.getSpecificationsId());
                if ("0".equals(info.getGoodsType())) {
                    calculateList.add(calculateTo);
                    param.setGoodsType("0");
                }else {
                    calculateActivityList.add(calculateTo);
                    param.setGoodsType("10");
                }
            }
        }
//        if(goodsInfo.getCartActivityGoodsList()!=null&&goodsInfo.getCartActivityGoodsList().size()>0){
//            for (CarGoodsInfo info:goodsInfo.getCartActivityGoodsList()){
//                CouponCalculateTo calculateTo=new CouponCalculateTo();
//                calculateTo.setRetailPrice(info.getRetailPrice()+"");
//                calculateTo.setGoodsType(info.getGoodsType());
//                calculateTo.setGoodsId(info.getGoodsId());
//                calculateTo.setGoodsNum(info.getGoodsNum() + "");
//                calculateTo.setSpecificationsId(info.getSpecificationsId());
//                calculateActivityList.add(calculateTo);
//            }
//        }
        param.setMerchantGoodslist(calculateList);
        param.setActivityGoodsList(calculateActivityList);
        System.out.println(param + "param");


        api.getCouponCalculate(param, new HttpCallback<MessageToBulk<List<CouponCalculateTo>>>(getThisContext()) {
         @Override
         public void success(MessageToBulk<List<CouponCalculateTo>> msg, Response response) {

             if (msg.getCode() == 0) {
                 totalPrice = 0;
                 for (int i = 0; i < carTos.size(); i++) {
                     if (goodsInfo.getMerchantId().equals(carTos.get(i).getMerchantId())) {
                         carTos.get(i).setPayAmount(msg.getPaymentAmount());

                         carTos.get(i).setIsCalculatePrice(true);
                         smallAmount.setText("¥" + ((goodsInfo.getPayAmount() <= 0) ? trafficFee : DoubleUtil.add(goodsInfo.getPayAmount(), trafficFee)));
                         actualPayPrice = DoubleUtil.add(actualPayPrice, goodsInfo.getPayAmount() <= 0 ? trafficFee : goodsInfo.getPayAmount());
                         currentPrice = goodsInfo.getPayAmount() <= 0 ? trafficFee : (DoubleUtil.add(goodsInfo.getPayAmount(), trafficFee));
                         totalPrice = DoubleUtil.add(totalPrice, DoubleUtil.add(goodsInfo.getPayAmount(), expressCost));
                         allPrice.setText("¥"+totalPrice + "");
                     }
                 }
             }
         }
     });
    }

}

