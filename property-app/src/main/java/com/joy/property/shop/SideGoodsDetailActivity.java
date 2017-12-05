package com.joy.property.shop;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jinyi.ihome.infrastructure.MessageToBulk;
import com.jinyi.ihome.module.newshop.ActivityTimeTo;
import com.jinyi.ihome.module.newshop.AddCarParam;
import com.jinyi.ihome.module.newshop.GoodsApiTo;
import com.jinyi.ihome.module.newshop.GoodsDetail;
import com.jinyi.ihome.module.newshop.GoodsDetailParam;
import com.jinyi.ihome.module.newshop.GoodsDetailPicTo;
import com.jinyi.ihome.module.newshop.ImmediateBuyParam;
import com.jinyi.ihome.module.newshop.ImmediatelyGoodsTo;
import com.jinyi.ihome.module.shop.GoodsSpecificationsTo;
import com.joy.common.api.ApiClientBulk;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.NewShopApi;
import com.joy.library.utils.DateUtil;
import com.joy.property.R;
import com.joy.property.neighborhood.RefreshEvent;
import com.joy.property.shop.fragment.GoodsDetailFragment;
import com.joy.property.shop.fragment.GoodsImageTextDetailFragment;
import com.joy.property.shop.shoputil.CarNumberUtil;
import com.joy.property.shop.slide.DragLayout;
import com.joy.property.utils.Utils;
import com.joy.property.utils.flowlayout.FlowLayout;
import com.joy.property.utils.flowlayout.TagAdapter;
import com.joy.property.utils.flowlayout.TagFlowLayout;
import com.joyhome.nacity.app.MainApp;
import com.joyhome.nacity.app.base.BaseActivity;
import com.joyhome.nacity.app.photo.util.PublicWay;
import com.joyhome.nacity.app.util.CustomDialog;
import com.joyhome.nacity.app.util.SpUtil;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import retrofit.RetrofitError;
import retrofit.client.Response;
import rx.Observable;

/**
 * Created by xz on 2017/4/11.
 */
public class SideGoodsDetailActivity extends BaseActivity implements View.OnClickListener {
    private DragLayout draglayout;
    private TextView shopCarNumber;
    private String goodsSid;
    private GoodsDetail goodsDetail;
    private String specificationsName="";
    private String specificationsId;
    private List<TextView>flowViewList=new ArrayList<>();
    private List<GoodsSpecificationsTo> goodsSpecificationsList;
    private String [] mNames;
    private double retailPrice;
    private GoodsDetailFragment goodsDetailFragment;
    private TextView goodsTitle;
    private View goodsDetailLine;
    private TextView addCar;
    private RelativeLayout titleLayout;
    private TextView purchase;
    private boolean canAddOrPurchase=true;
    private int specificationCount;
    private int inventoryNum;
    private GoodsImageTextDetailFragment imageTextDetailFragment;
    private TextView offTheShelf;
    private String activityId="";
    private Boolean isFirst=true;//开始
    private boolean initEventBus;
    private boolean noFirst=false;
    private int purchaseGoodsNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_side_goods_detail);
        findView();
        getData();
        setActivityStatue();
        initEventBus();
        PublicWay.goodsDetailActivity=this;


    }

    private void initEventBus() {
        if (!initEventBus)
            EventBus.getDefault().register(this);
    }


    private void findView() {
        shopCarNumber = (TextView) findViewById(R.id.shopCarNumber);
        findViewById(R.id.carLayout).setOnClickListener(this);
        addCar = (TextView) findViewById(R.id.add_car);
        addCar.setOnClickListener(this);
        purchase = (TextView) findViewById(R.id.purchase);
        purchase.setOnClickListener(this);
        goodsTitle = (TextView) findViewById(R.id.goodsTitle);
        goodsDetailLine = findViewById(R.id.goodsDetailLine);
        titleLayout = (RelativeLayout) findViewById(R.id.title);
        findViewById(R.id.iv_back).setOnClickListener(this);
        findViewById(R.id.shopLayout).setOnClickListener(this);
        offTheShelf = (TextView) findViewById(R.id.offTheShelf);
    }


    private void getData() {
        NewShopApi api = ApiClientBulk.create(NewShopApi.class);
        GoodsDetailParam param = new GoodsDetailParam();
        param.setCommunityId(mUserHelperBulk.getUserInfoTo().getApartmentSid());
        Bundle bundle =getIntent().getExtras();//获取一个句柄

        if (TextUtils.isEmpty(goodsSid))
            goodsSid = bundle.getString("GoodsSid");
        param.setGoodsId(goodsSid);
        param.setGoodsType(getIntent().getBooleanExtra("ActivityGoods", false) ? "10" : "0");
        System.out.println(param + "param");
//        CustomDialogFragment dialogFragment = new CustomDialogFragment();
//        dialogFragment.show(getSupportFragmentManager(), "");

        api.getGoodsDetail(param, new HttpCallback<GoodsApiTo>(getThisContext()) {
            @Override
            public void success(GoodsApiTo msg, Response response) {
                //         dialogFragment.dismiss();
                if (msg != null && msg.getCode() == 0) {
                    if (msg.getGoodsApiVo() != null) {

                        goodsDetail = msg.getGoodsApiVo();

                        initView(goodsDetail, msg.getGoodsPicApiVoList());
                        setOffTheShelf();
                    }


                }
            }

            @Override
            public void failure(RetrofitError error) {
                System.out.println(error + "error");
            }
        });
    }




    private void initView(GoodsDetail goodsDetail,List<GoodsDetailPicTo> picList) {
        goodsDetailFragment = new GoodsDetailFragment(goodsDetail,picList);
        imageTextDetailFragment = new GoodsImageTextDetailFragment(goodsDetail);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.first, goodsDetailFragment).add(R.id.second, imageTextDetailFragment)
                .commit();


        DragLayout.ShowNextPageNotifier nextIntf = new DragLayout.ShowNextPageNotifier() {
            @Override
            public void onDragNext(boolean isTop) {
                if (isTop){
                    goodsDetailLine.setVisibility(View.GONE);
                    goodsTitle.setVisibility(View.GONE);
                    titleLayout.setBackgroundColor(Color.parseColor("#00000000"));
                }else {
                    goodsDetailLine.setVisibility(View.VISIBLE);
                    goodsTitle.setVisibility(View.VISIBLE);
                    titleLayout.setBackgroundColor(Color.parseColor("#ffffff"));
                    imageTextDetailFragment.setView();
                }
            }
        };

        draglayout = (DragLayout) findViewById(R.id.draglayout);
        draglayout.setNextPageListener(nextIntf);
        CarNumberUtil.getCarNumber(mUserHelperBulk.getSid(), getThisContext(), shopCarNumber);

        setData();
        setAddCarOrPurchase();
    }

    private void setData() {
        goodsSpecificationsList = goodsDetail.getGoodsSpecificationsList();

        double minnum = goodsSpecificationsList.get(0).getRetailPrice();
        mNames=new String[goodsSpecificationsList.size()];
        for (int i=0;i< goodsSpecificationsList.size();i++) {
            mNames[i]= goodsSpecificationsList.get(i).getSpecificationsName();
            if(goodsSpecificationsList.get(i).getRetailPrice()<minnum)
            {
                minnum =goodsSpecificationsList.get(i).getRetailPrice();


            }
        }
        inventoryNum=goodsSpecificationsList.get(0).getInventoryNum();
        retailPrice=minnum;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        noFirst=false;

        if (shopCarNumber!=null)
            CarNumberUtil.getCarNumber(mUserHelperBulk.getSid(), getThisContext(), shopCarNumber);
    }


    public void addCar(String goodsId,String num,String addSpecificationsName,String addSpecificationsId){

        NewShopApi api=ApiClientBulk.create(NewShopApi.class);
        AddCarParam param=new AddCarParam();
        param.setGoodsId(goodsId);
        param.setUserId(mUserHelperBulk.getSid());
        param.setNum(num);
        param.setSpecificationsId(addSpecificationsId);
        param.setSpecificationsName(addSpecificationsName);
        param.setGoodsType(getIntent().getBooleanExtra("ActivityGoods", false) ? "10" : "0");
        param.setActivityId(TextUtils.isEmpty(goodsDetail.getActivityId())?"":goodsDetail.getActivityId());
        param.setMerchantId(goodsDetail.getMerchantId()==null?"":goodsDetail.getMerchantId());
//        final CustomDialogFragment dialogFragment = new CustomDialogFragment();
//        dialogFragment.show(getSupportFragmentManager(), "");

        api.addCar(param, new HttpCallback<MessageToBulk>(getThisContext()) {
            @Override
            public void success(MessageToBulk msg, Response response) {
                //  dialogFragment.dismiss();

                if (msg.getCode() == 0) {
                    ToastShowLong(getThisContext(), "加入购物车成功");
                    CarNumberUtil.getCarNumber(mUserHelperBulk.getSid(), getThisContext(), shopCarNumber);
                    SpUtil.put(getThisContext(), "HaveAddCar", true);

                } else
                    ToastShowLong(getThisContext(), msg.getMessage());
            }

            @Override
            public void failure(RetrofitError error) {
                //    dialogFragment.dismiss();

            }
        });
    }
    public void immediatelyBuy(String goodsNumber){
        if ( TextUtils.isEmpty(specificationsId))

        {

            ToastShowLong(getThisContext(), "请选择规格");
            return;

        }
        NewShopApi api=ApiClientBulk.create(NewShopApi.class);
        ImmediateBuyParam param=new ImmediateBuyParam();
        //选择规格的价格
        // param.setCurrentPrice();
        param.setGoodsId(goodsDetail.getGoodsId() + "");
        param.setMerchantId(goodsDetail.getMerchantId());
        param.setGoodsNum(goodsNumber);
        param.setSpecificationsName(specificationsName);
        param.setSpecificationsId(specificationsId);
        param.setGoodsType(goodsDetail.getGoodsType());
        param.setUserId(mUserHelperBulk.getSid());
        param.setRetailPrice(retailPrice + "");
        System.out.println(param+"param");
        api.immediatelyBuy(param, new HttpCallback<MessageToBulk<ImmediatelyGoodsTo>>(getThisContext()) {
            @Override
            public void success(MessageToBulk<ImmediatelyGoodsTo> msg, Response response) {
                if (msg.getCode() == 0) {
                    Intent intent = new Intent(getThisContext(), ImmediatelyConfirmOlderActivity.class);
                    intent.putExtra("CartSettleGoodsTo", msg.getImmediateBuyGoodVo());
                    intent.putExtra("IsSeaBuy", msg.getIsSeaBuy());
                    intent.putExtra("ActivityId", goodsDetail.getActivityId());
                    startActivity(intent);
                    System.out.println(msg.getImmediateBuyGoodVo() + "param");
                    goToAnimation(1);
                } else
                    ToastShowLong(getThisContext(), msg.getMessage());
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

    }

    private void PurchaseDialogShow(boolean isAddCar) {
        flowViewList.clear();
        final CustomDialog dialog = new CustomDialog(getThisContext(), R.layout.dialog_detail_purchase_new, R.style.myDialogTheme);
        TagFlowLayout mFlowLayout = (TagFlowLayout) dialog.findViewById(R.id.flowlayout);

        TextView confirmPurchase = (TextView) dialog.findViewById(R.id.confirmPurchase);
        LinearLayout dismissLayout = (LinearLayout) dialog.findViewById(R.id.dismissLayout);
        ImageView purchaseAdd = (ImageView) dialog.findViewById(R.id.purchaseAdd);
        TextView purchaseNumber = (TextView) dialog.findViewById(R.id.purchaseNumber);
        ImageView purchaseReduce = (ImageView) dialog.findViewById(R.id.purchaseReduce);
        TextView purchaseCost = (TextView) dialog.findViewById(R.id.purchaseCost);
        TextView purchaseSurplus = (TextView) dialog.findViewById(R.id.purchaseSurplus);
        ImageView purchaseClose = (ImageView) dialog.findViewById(R.id.purchaseClose);
        ImageView purchaseImage = (ImageView) dialog.findViewById(R.id.purchaseImage);
        LinearLayout specificationLayout = (LinearLayout) dialog.findViewById(R.id.specificationLayout);
        TextView purchaseSpecification = (TextView) dialog.findViewById(R.id.purchaseSpecification);
        View noSpecificationLayout = dialog.findViewById(R.id.noSpecificationLayout);

        purchaseNumber.setText(this.purchaseGoodsNumber >0? this.purchaseGoodsNumber +"":goodsDetail.getMinSalesNum() + "");
        purchaseGoodsNumber=purchaseGoodsNumber >0?purchaseGoodsNumber:goodsDetail.getMinSalesNum();
        goodsDetailFragment.purchaseNum=purchaseGoodsNumber >0?purchaseGoodsNumber+"":goodsDetail.getMinSalesNum()+"";
        //设置价格

        purchaseCost.setText("价格：¥"+goodsSpecificationsList.get(0).getRetailPrice());
        purchaseSurplus.setText("库存:" + inventoryNum);
        Picasso.with(getThisContext()).load(MainApp.getPicassoImagePath(goodsDetail.getPicUrl())).into(purchaseImage);
        purchaseClose.setOnClickListener(v -> dialog.dismiss());
        purchaseSurplus.setText("库存" + goodsSpecificationsList.get(0).getInventoryNum() + "件");
        isFirst=true;



        purchaseAdd.setOnClickListener(view -> {
            if(isFirst) {
                isFirst=false;
                purchaseReduce.setBackgroundDrawable(getResources().getDrawable(R.drawable.purchase_reduce_new));}
            if (Integer.valueOf(purchaseNumber.getText().toString()) >=inventoryNum) {
                Toast.makeText(getThisContext(), "您已超过库存量了~",Toast.LENGTH_LONG).show();
                return;
            }
            if (Integer.valueOf(purchaseNumber.getText().toString()) >= goodsDetail.getMaxSalesNum()&&goodsDetail.getMaxSalesNum()!=0) {
                Toast.makeText(getThisContext(), "您已超过最大购买量~",Toast.LENGTH_LONG).show();
                return;
            }
            purchaseNumber.setText(Integer.valueOf(purchaseNumber.getText().toString()) + 1 + "");
            purchaseSpecification.setText("已选择:" + (TextUtils.isEmpty(specificationsName)?"":specificationsName) + "  x" + purchaseNumber.getText().toString());

            goodsDetailFragment.specificationContent.setText("已选择:" + (TextUtils.isEmpty(specificationsName)?"":specificationsName) + "  x" + purchaseNumber.getText().toString());
            goodsDetailFragment.purchaseNum=purchaseNumber.getText().toString();
            purchaseGoodsNumber++;

        });
        purchaseReduce.setOnClickListener(view -> {

            if (Integer.valueOf(purchaseNumber.getText().toString())<=goodsDetail.getMinSalesNum()+1){
                isFirst=true;
                purchaseReduce.setBackgroundDrawable(getResources().getDrawable(R.drawable.purchase_reduce_new_dim));

                goodsDetailFragment.specificationContent.setText("已选择:" + (TextUtils.isEmpty(specificationsName) ? "" : specificationsName) + "  x" + purchaseNumber.getText().toString());
                goodsDetailFragment.purchaseNum=purchaseNumber.getText().toString();
                purchaseGoodsNumber--;
            }
            if (Integer.valueOf(purchaseNumber.getText().toString())<=goodsDetail.getMinSalesNum()){
                Toast.makeText(getThisContext(), "不能再少了~", Toast.LENGTH_LONG).show();
                return;
            }
            if(Integer.valueOf(purchaseNumber.getText().toString())>1){
                purchaseNumber.setText(Integer.valueOf(purchaseNumber.getText().toString()) - 1 + "");
                purchaseSpecification.setText("已选择:" + (TextUtils.isEmpty(specificationsName)?"":specificationsName) + "  x" + purchaseNumber.getText().toString());
            }
        });
        confirmPurchase.setOnClickListener(v -> {
            if (TextUtils.isEmpty(specificationsId)) {
                ToastShowLong(getThisContext(), "请选择规格");
                return;
            }
            if (isAddCar) {

                addCar(goodsSid, purchaseNumber.getText().toString(),
                        specificationsName, specificationsId);
            } else
                immediatelyBuy(purchaseNumber.getText().toString());
            dialog.dismiss();
        });
        mFlowLayout.setAdapter(new TagAdapter<String>(mNames) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                LayoutInflater mInflater = LayoutInflater.from(getThisContext());
                RelativeLayout mRelativeLayout = (RelativeLayout) mInflater.inflate(R.layout.tv,
                        mFlowLayout, false);
                TextView textView = (TextView) mRelativeLayout.findViewById(R.id.textView);
                textView.setText(s);
                mRelativeLayout.setTag(position);
                flowViewList.add(textView);
                mRelativeLayout.setOnClickListener(v -> {
                    for (int i = 0; i < flowViewList.size(); i++) {
                        if (i == (int) v.getTag()) {
                            flowViewList.get(i).setTextColor(Color.parseColor("#46b4d9"));
                            flowViewList.get(i).setBackgroundDrawable(getResources().getDrawable(R.drawable.specification_bg_press));
                            specificationsName = goodsSpecificationsList.get(i).getSpecificationsName();
                            specificationsId = goodsSpecificationsList.get(i).getSpecificationsId();
                            inventoryNum = goodsSpecificationsList.get(i).getInventoryNum();
                            purchaseCost.setText("价格：¥ " + goodsSpecificationsList.get(i).getRetailPrice());
                            purchaseSpecification.setText("已选择:" + (TextUtils.isEmpty(specificationsName)?"":specificationsName)+" x "+purchaseNumber.getText().toString());
                            purchaseSurplus.setText("库存:" + inventoryNum);
                            retailPrice =goodsSpecificationsList.get(i).getRetailPrice();
                        } else {
                            flowViewList.get(i).setTextColor(Color.parseColor("#353535"));
                            flowViewList.get(i).setBackgroundDrawable(getResources().getDrawable(R.drawable.specification_bg));
                        }
                    }
                    goodsDetailFragment.specificationContent.setText("已选择:" + (TextUtils.isEmpty(specificationsName)?"":specificationsName) + "  x" + purchaseNumber.getText().toString());
                    goodsDetailFragment.specificationsId=specificationsId;
                    goodsDetailFragment.specificationsName=specificationsName;
                    goodsDetailFragment.retailPrice=retailPrice+"";

                });
                specificationCount=0;
                Observable.from(mNames).filter(s1 -> !TextUtils.isEmpty(s1)).subscribe(s2 -> {
                    specificationLayout.setVisibility(View.VISIBLE);
                    purchaseSpecification.setVisibility(View.VISIBLE);
                    specificationCount++;
                    noSpecificationLayout.setVisibility(View.GONE);
                });
                if (specificationCount==0) {

                    specificationsId = goodsSpecificationsList.get(0).getSpecificationsId();
                    specificationsName =TextUtils.isEmpty(goodsSpecificationsList.get(0).getSpecificationsName())?"": goodsSpecificationsList.get(0).getSpecificationsName();
                    purchaseSurplus.setText("库存:"+goodsSpecificationsList.get(0).getInventoryNum()+" 件");
                    retailPrice=goodsSpecificationsList.get(0).getRetailPrice();


                }
                return mRelativeLayout;
            }
        });



        dismissLayout.setOnClickListener(view -> dialog.dismiss());
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        dialog.show();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.carLayout:
                Intent intent=new Intent(getThisContext(),ShoppingCarActivity.class);
                startActivity(intent);
                goToAnimation(1);
                break;
            case R.id.purchase:

                    if (!canAddOrPurchase)
                        return;
                    if (TextUtils.isEmpty(specificationsId)) {
                        PurchaseDialogShow(false);
                    }
                    else {
                        if (Utils.isFastDoubleClick()) {
                            return;
                        }
                       immediatelyBuy(purchaseGoodsNumber + "");

                    }

                break;
            case R.id.add_car:
                if (!canAddOrPurchase)
                    return;
                if (TextUtils.isEmpty(specificationsId))
                    PurchaseDialogShow(true);
                else
                    addCar(goodsDetail.getGoodsId(),purchaseGoodsNumber+"",specificationsName,specificationsId);
                break;
            case R.id.iv_back:
                finish();
                goToAnimation(2);
                break;
            case R.id.shopLayout:
                Intent intentShop = null;
                if ("10".equals(goodsDetail.getGoodsType())){
                    intentShop=new Intent(getThisContext(),CampaignListActivity.class);
                }else {
                    if ("自营商品".equals(goodsDetail.getSalesType()))
                        intentShop = new Intent(getThisContext(), SelfShopActivity.class);
                    else
                        intentShop = new Intent(getThisContext(), MerchantShopActivity.class);
                    intentShop.putExtra("ShopSid", goodsDetail.getMerchantId());
                    intentShop.putExtra("ShopName", goodsDetail.getStoresName());
                }
                startActivity(intentShop);
                goToAnimation(1);
                break;
        }

    }
    public void setAddCarOrPurchase(){
        if (!canAddOrPurchase)
            return;
        goodsDetailFragment.setAddCar(() -> {
            if (TextUtils.isEmpty(specificationsId))
                PurchaseDialogShow(true);
            else
                addCar(goodsDetail.getGoodsId(),purchaseGoodsNumber+"",specificationsName,specificationsId);
        });
        goodsDetailFragment.setImmediatelyPurchase(() -> {

            if (TextUtils.isEmpty(specificationsId))
                PurchaseDialogShow(false);
            else
                immediatelyBuy(purchaseGoodsNumber+"");

        });
    }
    private void setActivityStatue() {

        ActivityTimeTo activityTo= (ActivityTimeTo) getIntent().getSerializableExtra("ActivityTimeInfo");
        if (activityTo!=null){


            if (DateUtil.cureentMillis()>DateUtil.DateMillis(activityTo.getWarmupStartTime(),DateUtil.mDateTimeFormatString)&&DateUtil.cureentMillis()<DateUtil.DateMillis(activityTo.getActivityStartTime(),DateUtil.mDateTimeFormatString)){
                purchase.setText("即将上线");
                addCar.setTextColor(Color.parseColor("#999999"));
                canAddOrPurchase=false;

            }
            activityId=activityTo.getActivityId();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (imageTextDetailFragment!=null&&imageTextDetailFragment. htmlText!=null)
            imageTextDetailFragment. htmlText.setHtmlFromString("", false);
        EventBus.getDefault().unregister(this);
    }
    public void setOffTheShelf(){
        if ("yes".equals(goodsDetail.getIsInvalid())||"失效".equals(goodsDetail.getIsInvalid())){
            offTheShelf.setVisibility(View.VISIBLE);
            purchase .setTextColor(Color.parseColor("#999999"));
            addCar.setTextColor(Color.parseColor("#999999"));
            canAddOrPurchase=false;
        }
    }

    @Subscribe
    public void onEventMainThread(RefreshEvent event) {

        if ("specification".equals(event.getMsg())) {
            specificationsId = event.getSpecificationsId();
            specificationsName = event.getSpecificationsName();
            retailPrice = event.getRetailPrice();
            purchaseGoodsNumber = event.getPurchaseNumber();

        }

    }
}