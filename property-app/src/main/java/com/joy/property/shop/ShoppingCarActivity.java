package com.joy.property.shop;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.jinyi.ihome.infrastructure.MessageToBulk;
import com.jinyi.ihome.module.newshop.AddCarParam;
import com.jinyi.ihome.module.newshop.CarGoodsInfo;
import com.jinyi.ihome.module.newshop.CartSettleGoodsTo;
import com.jinyi.ihome.module.newshop.ChangeCarParam;
import com.jinyi.ihome.module.newshop.CouponParam;
import com.jinyi.ihome.module.newshop.CouponTo;
import com.jinyi.ihome.module.newshop.DeleteGoodsParam;
import com.jinyi.ihome.module.newshop.GoodsApiTo;
import com.jinyi.ihome.module.newshop.GoodsDetail;
import com.jinyi.ihome.module.newshop.GoodsDetailParam;
import com.jinyi.ihome.module.newshop.MakeOlderNewParam;
import com.jinyi.ihome.module.newshop.MakeOrderParam;
import com.jinyi.ihome.module.newshop.MyShopCarNormalTo;
import com.jinyi.ihome.module.newshop.MyShopCarTo;
import com.jinyi.ihome.module.newshop.SaveCouponParam;
import com.jinyi.ihome.module.shop.GoodsSpecificationsTo;
import com.joy.common.api.ApiClientBulk;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.NewShopApi;
import com.joy.property.MainApp;
import com.joy.property.R;
import com.joy.property.base.BaseActivity;
import com.joy.property.shop.adapter.CouponAdapter;
import com.joy.property.shop.adapter.ShoppingCarAdapter;
import com.joy.property.shop.shoputil.DoubleUtil;
import com.joy.property.utils.flowlayout.FlowLayout;
import com.joy.property.utils.flowlayout.TagAdapter;
import com.joy.property.utils.flowlayout.TagFlowLayout;
import com.joyhome.nacity.app.photo.util.PublicWay;
import com.joyhome.nacity.app.util.CustomDialog;
import com.joyhome.nacity.app.util.CustomDialogFragment;
import com.joyhome.nacity.app.util.SpUtil;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import rx.Observable;

/**
 * Created by xz on 2016/7/21.
 **/
public class ShoppingCarActivity extends BaseActivity implements View.OnClickListener {
    private ImageView goodsSelect;
    private ImageView allSelect;
    private TextView priceLeft;
    private TextView priceRight;
    private TextView containTraffic;
    private TextView balance;
    private SwipeMenuListView listView;
    private List<MyShopCarTo> carList = new ArrayList<>();
    private ShoppingCarAdapter adapter;
    private Boolean isAllSelect = false;
    private double allPrice = 0.00;
    private double trafficFee = 0;
    private GridLayout gridView;
    private List<CarGoodsInfo> goodsInfoList = new ArrayList<>();
    // private List<CarGoodsInfo> lastChangeGoodsInfoList = new ArrayList<>();
    private List<CarGoodsInfo> changeGoodsInfoList = new ArrayList<>();
    private List<CarGoodsInfo> lastDetailList = new ArrayList<>();
    private int inventoryNum;
    private String cartId;
    private List<DeleteGoodsParam.CartGoodsListBean> cartGoodsListBeans = new ArrayList<>();
    private List<ChangeCarParam.ChangeCartGoods> cartGoodsList = new ArrayList<>();
    private List<CarGoodsInfo> carGoodsList = new ArrayList<>();
    private List<CouponTo> couponSaveList = new ArrayList<>();
    private List<CouponTo> couponList = new ArrayList<>();
    private RelativeLayout bulkLayout;
    private ImageView noshop;
    private int allCount;
    private int allCountSelect;
    private List<TextView> flowViewList = new ArrayList<>();
    private List<GoodsSpecificationsTo> goodsSpecificationsList;
    private String specificationsName = "";
    private String specificationsId = "";
    private String specificationsNameSelect;
    private double retailPrice;
    int validSelectCount = 0;
    private double shopAllPrice = 0;
    private double maxTrafficFee = 0;
    private int shopSelectCount = 0;
    private String changeSpecificationId;
    private String newChangeSpecifationId = "";
    private String newChangenewSpecificationsName = "";
    private boolean setShopNoSelect;//设置店铺不全选

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_car);
        initView();
        initData();

        setAdapterListener();
        PublicWay.activityList.add(this);
    }

    private void initView() {
        findViewById(R.id.back).setOnClickListener(this);
        goodsSelect = (ImageView) findViewById(R.id.goodsSelect);
        findViewById(R.id.coupons).setOnClickListener(this);
        allSelect = (ImageView) findViewById(R.id.allSelect);
        priceLeft = (TextView) findViewById(R.id.priceLeft);
        priceRight = (TextView) findViewById(R.id.priceRight);
        containTraffic = (TextView) findViewById(R.id.containTraffic);
        balance = (TextView) findViewById(R.id.balance);
        gridView = (GridLayout) findViewById(R.id.gridView);
        listView = (SwipeMenuListView) findViewById(R.id.listView);
        listView.setDividerHeight(0);
        adapter = new ShoppingCarAdapter(getThisContext(), this);
        goodsSelect.setOnClickListener(this);
        allSelect.setOnClickListener(this);
        findViewById(R.id.coupons).setOnClickListener(this);
        findViewById(R.id.balanceLayout).setOnClickListener(this);
        bulkLayout = (RelativeLayout) findViewById(R.id.bulkLayout);
        noshop = (ImageView) findViewById(R.id.noShop);
        findViewById(R.id.allSelectText).setOnClickListener(this);
    }

    private void initData() {
        SpUtil.put(getThisContext(), "HaveAddCar", false);
        NewShopApi api = ApiClientBulk.create(NewShopApi.class);

        AddCarParam param = new AddCarParam();
        param.setUserId(mUserHelperBulk.getSid());

        api.getMyShopCar(param, new HttpCallback<MessageToBulk<List<MyShopCarNormalTo>>>(ShoppingCarActivity.this) {
            @Override
            public void success(MessageToBulk<List<MyShopCarNormalTo>> msg, Response response) {

                carList.clear();
                if (msg.getCode() == 0) {
                    if (msg.getCartId() != null)
                        cartId = msg.getCartId();
                    if ((msg.getCartNormalGoodsVoList() != null && msg.getCartNormalGoodsVoList().size() > 0)) {
                        for (int i = 0; i < msg.getCartNormalGoodsVoList().size(); i++) {
                            if (msg.getCartNormalGoodsVoList().get(i).getCartMerchantGoodsGlobalVoList() != null && msg.getCartNormalGoodsVoList().get(i).getCartMerchantGoodsGlobalVoList().size() > 0)
                                carList.addAll(msg.getCartNormalGoodsVoList().get(i).getCartMerchantGoodsGlobalVoList());
                        }
                    }
                    if ((msg.getCartActivityVoList() != null && msg.getCartActivityVoList().size() > 0)) {
                        carList.add(setActivityCarData(msg.getCartActivityVoList().get(0)));
                    }

                    if (carList != null && carList.size() > 0) {

                        setView(carList);
                    } else {
                        bulkLayout.setVisibility(View.GONE);
                        noshop.setVisibility(View.VISIBLE);
                    }
                    setPrice(goodsInfoList);

                    listView.setOnItemClickListener((adapterView, view, position, l) -> {
                        if (!TextUtils.isEmpty(goodsInfoList.get(position).getGoodsId())) {
                            Intent intent = new Intent(getThisContext(), SideGoodsDetailActivity.class);
                            intent.putExtra("GoodsSid", goodsInfoList.get(position).getGoodsId());
                            intent.putExtra("ActivityGoods", !"0".equals(goodsInfoList.get(position).getGoodsType()));
                            if (PublicWay.goodsDetailActivity != null)
                                PublicWay.goodsDetailActivity.finish();
                            startActivity(intent);

                            goToAnimation(1);
                        } else {
                            jumpShop(goodsInfoList.get(position).getMerchantId(), goodsInfoList.get(position).getSalesType(), goodsInfoList.get(position).getStoresNameTop());

                        }
                    });

                } else
                    ToastShowLong(getThisContext(), msg.getMessage());
            }

            @Override
            public void failure(RetrofitError error) {
                super.failure(error);

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                onBackPressed();
                break;
            case R.id.allSelect:
            case R.id.allSelectText:
                allSelect();
                break;
            case R.id.balanceLayout:
                if (!check()) {
                    ToastShowLong(getThisContext(), "请至少选择一件商品");
                    return;
                }

                break;
        }
    }

    public void initSwipeMenu(SwipeMenuListView listView) {
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item


                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(dp2px(90));
                // set a icon
                deleteItem.setIcon(R.drawable.ic_delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };
        // set creator
        listView.setMenuCreator(creator);

        // step 2. listener item click event
        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                CarGoodsInfo item = goodsInfoList.get(position);

                switch (index) {
                    case 0:

                        cartGoodsListBeans.clear();
                        if (item.getMerchantNameTop() != null || item.getStoresNameTop() != null) {
                            Observable.from(carList).filter(myShopCarTo -> item.getMerchantId().equals(myShopCarTo.getMerchantId())).subscribe(myShopCarTo -> {
                                if (myShopCarTo.getCartMerchantGoodsVolist() != null)
                                    for (CarGoodsInfo goodsDetail : myShopCarTo.getCartMerchantGoodsVolist()) {
                                        DeleteGoodsParam.CartGoodsListBean bean = new DeleteGoodsParam.CartGoodsListBean();
                                        bean.setGoodsId(goodsDetail.getGoodsId());
                                        bean.setGoodsType(goodsDetail.getGoodsType());
                                        bean.setSpecificationsId(goodsDetail.getSpecificationsId());
                                        bean.setMerchantId(goodsDetail.getMerchantId());
                                        if (bean.getGoodsId() != null)
                                            cartGoodsListBeans.add(bean);
                                    }
                            });
                        } else {
                            DeleteGoodsParam.CartGoodsListBean goodBean = new DeleteGoodsParam.CartGoodsListBean();
                            goodBean.setGoodsId(item.getGoodsId());
                            goodBean.setGoodsType(item.getGoodsType());
                            goodBean.setSpecificationsId(item.getSpecificationsId());
                            goodBean.setMerchantId(item.getMerchantId());
                            cartGoodsListBeans.add(goodBean);
                        }

                        if (cartGoodsListBeans != null && cartGoodsListBeans.size() > 0)
                            deleteGoods(cartGoodsListBeans, position, item.getCartIdTop());

                        break;

                }
                return false;
            }
        });
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }


    public void allSelect() {
        if (isAllSelect) {
            isAllSelect = false;
            allSelect.setBackgroundResource(R.drawable.shopping_car_circle);
            for (int i = 0; i < goodsInfoList.size(); i++) {
                if ("正常".equals(goodsInfoList.get(i).getIsInvalid()))
                    goodsInfoList.get(i).setIsSelect(false);
                goodsInfoList.get(i).setIsShopSelect(false);
            }
        } else {
            isAllSelect = true;
            allSelect.setBackgroundResource(R.drawable.shopping_car_select);
            for (int i = 0; i < goodsInfoList.size(); i++) {
                if ("正常".equals(goodsInfoList.get(i).getIsInvalid()))
                    goodsInfoList.get(i).setIsSelect(true);
                goodsInfoList.get(i).setIsShopSelect(true);
            }
        }
        setPrice(goodsInfoList);
        adapter.notifyDataSetChanged();
    }

    private void ConponDialogShow(CarGoodsInfo mode) {

        final CustomDialog dialog = new CustomDialog(this, R.layout.dialog_coupon, R.style.myDialogTheme);
        NewShopApi api = ApiClientBulk.create(NewShopApi.class);
        CouponParam param = new CouponParam();
        ImageView noCoupon = (ImageView) dialog.findViewById(R.id.noCoupon);
        TextView storeName = (TextView) dialog.findViewById(R.id.storeName);
        storeName.setText(mode.getStoresNameTop());
        param.setCartId(mode.getCartIdTop());
        param.setMerchantId(mode.getMerchantId());
        param.setUserId(mUserHelperBulk.getSid());

        List<CouponParam.CouponParamTo> couponParamToList = new ArrayList<>();

        couponParamToList.clear();
        for (CarGoodsInfo goodsInfo : goodsInfoList) {
            CouponParam.CouponParamTo couponParamTo = new CouponParam.CouponParamTo();
            if (goodsInfo.getGoodsId() != null && goodsInfo.getMerchantId().equals(mode.getMerchantId())) {
                couponParamTo.setGoodsId(goodsInfo.getGoodsId());
                couponParamTo.setRetailPrice(goodsInfo.getRetailPrice() + "");
                couponParamTo.setGoodsNum(goodsInfo.getGoodsNum() + "");
                couponParamToList.add(couponParamTo);
            }
        }

        param.setMerchantGoodslist(couponParamToList);

        api.getCoupon(param, new HttpCallback<MessageToBulk<List<CouponTo>>>(getThisContext()) {
            @Override
            public void success(MessageToBulk<List<CouponTo>> msg, Response response) {

                if (msg.getCode() == 0) {
                    if (msg.getCartCouponList() != null) {
                        couponList.clear();
                        couponList.addAll(msg.getCartCouponList());
                        if (couponList.size() > 0)
                            noCoupon.setVisibility(View.GONE);
                        CouponAdapter couponAdapter = new CouponAdapter(getThisContext());
                        couponAdapter.setList(couponList);


                        ListView listView = (ListView) dialog.findViewById(R.id.listView);
                        listView.setAdapter(couponAdapter);
                        listView.setDividerHeight(0);
                        couponAdapter.notifyDataSetChanged();
                        couponAdapter.setSaveCoupon((couponSid, position) -> confirmCoupon(mode.getMerchantId(), couponSid, dialog, position));
                    }
                }
            }

            @Override
            public void failure(RetrofitError error) {
                super.failure(error);

            }
        });

        RelativeLayout layout = (RelativeLayout) dialog.findViewById(R.id.layout);
        layout.setOnClickListener(v -> dialog.dismiss());
        //getCouponList(couponAdapter,couponList);
        TextView purchaseClose = (TextView) dialog.findViewById(R.id.cancel);
        purchaseClose.setOnClickListener(v -> dialog.dismiss());

        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        dialog.show();


    }

    //设置传入 Adapter 数据
    public void setView(List<MyShopCarTo> shopCarToList) {

        lastDetailList.clear();
        lastDetailList.addAll(goodsInfoList);
        goodsInfoList.clear();

        for (int i = 0; i < shopCarToList.size(); i++) {

            CarGoodsInfo changeGoods = new CarGoodsInfo();
            CarGoodsInfo myTitleCar = new CarGoodsInfo();
            MyShopCarTo myShopCar = shopCarToList.get(i);
            myTitleCar.setMerchantNameTop(myShopCar.getMerchantName());
            myTitleCar.setStoresNameTop(myShopCar.getStoresName());
            myTitleCar.setMerchantId(myShopCar.getMerchantId());
            myTitleCar.setCartIdTop(myShopCar.getCartId());
            myTitleCar.setIsBottom(false);


            for (CarGoodsInfo goodsInfo : lastDetailList) {
                if (TextUtils.isEmpty(goodsInfo.getGoodsId()) && myShopCar.getMerchantId().equals(goodsInfo.getMerchantId()))
                    myTitleCar.setIsEdit(goodsInfo.isEdit());
            }
            myTitleCar.setSalesType(myShopCar.getCartMerchantGoodsVolist().get(0).getSalesType());
            myTitleCar.setIsInvalid("正常");

            goodsInfoList.add(myTitleCar);
            myShopCar.getCartMerchantGoodsVolist().get(myShopCar.getCartMerchantGoodsVolist().size() - 1).setNoLine(true);
            goodsInfoList.addAll(myShopCar.getCartMerchantGoodsVolist());
            CarGoodsInfo myBottomCar = new CarGoodsInfo();
            myBottomCar.setMerchantNameTop(myShopCar.getMerchantName());
            myBottomCar.setStoresNameTop(myShopCar.getStoresName());
            myBottomCar.setMerchantId(myShopCar.getMerchantId());
            myBottomCar.setCartIdTop(myShopCar.getCartId());
            myBottomCar.setIsBottom(true);
            myBottomCar.setSalesType(myShopCar.getCartMerchantGoodsVolist().get(0).getSalesType());
            myBottomCar.setIsInvalid("正常");

            goodsInfoList.add(myBottomCar);
            changeGoodsInfoList.add(changeGoods);
        }
        for (int i = 0; i < goodsInfoList.size(); i++) {
            goodsInfoList.get(i).setLastNewSpecificationsId(goodsInfoList.get(i).getNewSpecificationsId());
            goodsInfoList.get(i).setLastNewSpecificationsName(goodsInfoList.get(i).getNewSpecificationsName());
            goodsInfoList.get(i).setLastRetailPrice(goodsInfoList.get(i).getRetailPrice());
        }
        if (lastDetailList.size() > 0) {
            if (lastDetailList.size() == goodsInfoList.size()&&setShopNoSelect) {
                for (int i=0;i<lastDetailList.size();i++) {
                    goodsInfoList.get(i).setIsSelect(lastDetailList.get(i).isSelect());
                    goodsInfoList.get(i).setIsShopSelect(lastDetailList.get(i).isShopSelect());
                    goodsInfoList.get(i).setIsEdit(lastDetailList.get(i).isEdit());
                }
            } else {
                for (int i = 0; i < goodsInfoList.size(); i++) {
                    for (CarGoodsInfo detail : lastDetailList) {
                        if (goodsInfoList.get(i).getGoodsId() != null) {
                            if (goodsInfoList.get(i).getGoodsId().equals(detail.getGoodsId())) {
                                goodsInfoList.get(i).setIsSelect(detail.isSelect());
                                goodsInfoList.get(i).setIsEdit(detail.isEdit());

                            }
                        } else {
                            if (goodsInfoList.get(i).getMerchantId() != null && goodsInfoList.get(i).getMerchantId().equals(detail.getMerchantId())) {

                                goodsInfoList.get(i).setIsShopSelect(detail.isShopSelect());
                            }
                        }
                    }
                }
            }
        }
        adapter.setList(goodsInfoList);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        setShopNoSelect=false;
        initSwipeMenu(listView);
    }

    public void deleteGoods(List<DeleteGoodsParam.CartGoodsListBean> cartGoodsListBeans, int position, String cartId) {
        NewShopApi api = ApiClientBulk.create(NewShopApi.class);
        DeleteGoodsParam param = new DeleteGoodsParam();
        for (int i = 0; i < carList.size(); i++) {
            if (cartGoodsListBeans.get(0).getMerchantId().equals(carList.get(i).getMerchantId()))
                param.setCartId(carList.get(i).getCartId());
        }
        param.setCartGoodsList(cartGoodsListBeans);


        System.out.println(param + "param");
        CustomDialogFragment dialogFragment = new CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "");
        api.deleteGoods(param, new HttpCallback<MessageToBulk>(getThisContext()) {
            @Override
            public void success(MessageToBulk msg, Response response) {

                dialogFragment.dismiss();
                if (msg.getCode() == 0) {
                    goodsInfoList.remove(position);

                    initData();

                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    private void setAdapterListener() {

        adapter.setPurchaseNumberChangeListener((select, position, goodsTo) -> {
            int shopGoodsSelectCount = 0;
            int currentShopGoodsCount = 0;
            if (select) {
                goodsInfoList.get(position).setIsSelect(true);
                for (int i = 0; i < goodsInfoList.size(); i++) {


                    if (goodsTo.getMerchantId().equals(goodsInfoList.get(i).getMerchantId()) && goodsInfoList.get(i).getGoodsId() != null && "正常".equals(goodsInfoList.get(i).getIsInvalid())) {
                        currentShopGoodsCount++;
                        if (goodsInfoList.get(i).isSelect()) {
                            shopGoodsSelectCount++;


                        }
                    }
                    if (goodsTo.getMerchantId().equals(goodsInfoList.get(i).getMerchantId()) && currentShopGoodsCount == shopGoodsSelectCount) {
                        goodsInfoList.get(i).setIsShopSelect(true);
                    }
                }


            } else {
                goodsInfoList.get(position).setIsSelect(false);
                for (int i = 0; i < goodsInfoList.size(); i++) {
                    if (goodsTo.getMerchantId().equals(goodsInfoList.get(i).getMerchantId())) {
                        goodsInfoList.get(i).setIsShopSelect(false);
                    }

                }
            }

            setPrice(goodsInfoList);
        });
        adapter.setChangeGoodsListener((goodsId, number, position, isAdd, mode) -> {
            cartGoodsList.clear();

            for (int i = 0; i < goodsInfoList.size(); i++) {
                if (goodsId.equals(goodsInfoList.get(i).getGoodsId()) && mode.getSpecificationsId().equals(goodsInfoList.get(i).getSpecificationsId())) {
                    if (isAdd) {
                        goodsInfoList.get(i).setGoodsNum(goodsInfoList.get(i).getGoodsNum() + 1);

                    } else {
                        goodsInfoList.get(i).setGoodsNum(goodsInfoList.get(i).getGoodsNum() - 1);

                    }
                }

            }
            //    setChangeData(goodsInfoList);


        });
        adapter.setShopAllSelect((select, merchantId) -> {
            if (select) {
                for (int i = 0; i < goodsInfoList.size(); i++) {
                    if (merchantId.equals(goodsInfoList.get(i).getMerchantId()) && "正常".equals(goodsInfoList.get(i).getIsInvalid())) {
                        goodsInfoList.get(i).setIsSelect(true);
                        goodsInfoList.get(i).setIsShopSelect(true);
                    }
                }
            } else {
                for (int i = 0; i < goodsInfoList.size(); i++)
                    if (merchantId.equals(goodsInfoList.get(i).getMerchantId()) && "正常".equals(goodsInfoList.get(i).getIsInvalid())) {
                        goodsInfoList.get(i).setIsSelect(false);
                        goodsInfoList.get(i).setIsShopSelect(false);
                    }

            }
            setPrice(goodsInfoList);
            adapter.notifyDataSetChanged();
        });
        adapter.setGetCoupon(mode -> ConponDialogShow(mode));


        adapter.setEditCar((mode, isEdit) -> {
            for (int i = 0; i < goodsInfoList.size(); i++) {
                if (mode.getMerchantId().equals(goodsInfoList.get(i).getMerchantId())) {

                    goodsInfoList.get(i).setIsEdit(isEdit);
//                    if (changeGoodsInfoList.size() > 0) {
//                        changeGoodsInfoList.get(i).setIsEdit(isEdit);
//
//                    }
                }
            }
            if (!isEdit)
                changeGoods(mode.getMerchantId(), mode.getCartIdTop());

            else
                changeSpecificationId = "";
            if (TextUtils.isEmpty(cartId))
                cartId = mode.getCartIdTop();
            adapter.notifyDataSetChanged();
        });
        adapter.setSelectSpecification((mode, specificationText, priceText) -> {

            setSpecification(mode, mode.getGoodsId(), mode.getGoodsType(), TextUtils.isEmpty(mode.getNewSpecificationsId()) ? mode.getSpecificationsId() : mode.getNewSpecificationsId(), specificationText, priceText);
        });

        adapter.setMakeOlderListener(mode -> makeOrder(mode.getMerchantId(), mode.getCartIdTop()));
    }

    public void changeGoods(String merchantId, String cartId) {
        cartGoodsList.clear();
//        if (changeGoodsInfoList==null||changeGoodsInfoList.size()==0)
//            setChangeData(goodsInfoList);
        Observable.from(goodsInfoList).filter(carGoodsInfo -> merchantId.equals(carGoodsInfo.getMerchantId()) && carGoodsInfo.getGoodsId() != null && "正常".equals(carGoodsInfo.getIsInvalid()) && !carGoodsInfo.isBottom()).subscribe(carGoodsInfo1 -> {

            ChangeCarParam.ChangeCartGoods cartGoods = new ChangeCarParam.ChangeCartGoods();
            cartGoods.setGoodsId(carGoodsInfo1.getGoodsId());
            cartGoods.setPurchaseQuantity(carGoodsInfo1.getGoodsNum() + "");
            cartGoods.setGoodsType(carGoodsInfo1.getGoodsType());
            cartGoods.setSpecificationsId(carGoodsInfo1.getSpecificationsId());

            cartGoods.setNewSpecificationsName(carGoodsInfo1.getNewSpecificationsName());
            cartGoods.setNewSpecificationsId(carGoodsInfo1.getNewSpecificationsId());

            cartGoodsList.add(cartGoods);
        });


        NewShopApi api = ApiClientBulk.create(NewShopApi.class);
        ChangeCarParam param = new ChangeCarParam();
        param.setCartId(cartId);
        param.setUserId(mUserHelperBulk.getSid());
        param.setCartGoodsList(cartGoodsList);
        System.out.println(param + "param------------------------------");
        api.changeShopCar(param, new HttpCallback<MessageToBulk>(getThisContext()) {
            @Override
            public void success(MessageToBulk msg, Response response) {

                if (msg.getCode() == 0) {
                    setShopNoSelect=true;
                    initData();
                    return;
//              for (int i = 0; i < goodsInfoList.size(); i++){
//                    goodsInfoList.get(i).setLastNewSpecificationsId(goodsInfoList.get(i).getNewSpecificationsId());
//                    goodsInfoList.get(i).setLastNewSpecificationsName(goodsInfoList.get(i).getNewSpecificationsName());
//                    goodsInfoList.get(i).setLastRetailPrice(goodsInfoList.get(i).getRetailPrice());
//                }
//
//                    setPrice(goodsInfoList);
                } else {
//                        goodsInfoList.clear();
                    ToastShowLong(getThisContext(), msg.getMessage());
//                    for (int i=0;i<goodsInfoList.size();i++) {
//                        goodsInfoList.get(i).setNewSpecificationsName(goodsInfoList.get(i).getLastNewSpecificationsName());
//                        goodsInfoList.get(i).setNewSpecificationsId(goodsInfoList.get(i).getLastNewSpecificationsId());
//                        goodsInfoList.get(i).setRetailPrice(goodsInfoList.get(i).getLastRetailPrice());
//                        setPrice(goodsInfoList);
//                    }
                    setShopNoSelect=true;
                    initData();

//                        goodsInfoList.addAll(setChangeDataGoods(changeGoodsInfoList));
//                       for (int i=0;i<goodsInfoList.size();i++){
//                           goodsInfoList.get(i).setNewSpecificationsId(lastChangeGoodsInfoList.get(i).getNewSpecificationsId());
//                           goodsInfoList.get(i).setNewSpecificationsName(lastChangeGoodsInfoList.get(i).getNewSpecificationsName());
//                           goodsInfoList.get(i).setRetailPrice(lastChangeGoodsInfoList.get(i).getRetailPrice());
//                       }
//
//                        setPrice(goodsInfoList);

                }

            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

    }

    public void setPrice(List<CarGoodsInfo> carGoodsListTo) {
        allCount = 0;
        allCountSelect = 0;
        allPrice = 0.00;
        trafficFee = 0;
        double maxTrafficFee = 0;
        for (CarGoodsInfo goodsDetail : carGoodsListTo) {
            if (goodsDetail.isSelect()) {
                allPrice = DoubleUtil.add(allPrice, goodsDetail.getRetailPrice() * goodsDetail.getGoodsNum());
                //  allPrice=allPrice+goodsDetail.getRetailPrice()*goodsDetail.getGoodsNum();
            }
            if (goodsDetail.getDistributionCost() >= maxTrafficFee)
                maxTrafficFee = goodsDetail.getDistributionCost();
        }
        priceLeft.setText((int) allPrice + "");
        String moneyRight = allPrice + "0";
        priceRight.setText(moneyRight.substring(moneyRight.indexOf("."), moneyRight.length()));

        //运费计算
        for (MyShopCarTo shopCarTo : carList) {
            maxTrafficFee = 0;
            shopAllPrice = 0;
            shopSelectCount = 0;
            for (CarGoodsInfo goodsDetail : carGoodsListTo) {
                if (shopCarTo.getMerchantId().equals(goodsDetail.getMerchantId())) {
                    if (goodsDetail.isSelect() && goodsDetail.getDistributionCost() >= maxTrafficFee && !goodsDetail.isBottom()) {
                        maxTrafficFee = goodsDetail.getDistributionCost();

                    }
                    if (goodsDetail.isSelect() && !goodsDetail.isBottom() && goodsDetail.getGoodsId() != null) {

                        shopAllPrice = DoubleUtil.add(shopAllPrice, DoubleUtil.mul(goodsDetail.getRetailPrice(), goodsDetail.getGoodsNum()));

                        shopSelectCount++;
                    }
                }

            }
            trafficFee = DoubleUtil.add(trafficFee, maxTrafficFee);
            for (int i = 0; i < goodsInfoList.size(); i++) {
                if (goodsInfoList.get(i).getMerchantId().equals(shopCarTo.getMerchantId()) && goodsInfoList.get(i).isBottom()) {
                    goodsInfoList.get(i).setDistributionCost(maxTrafficFee);
                    goodsInfoList.get(i).setRetailPrice(shopAllPrice);
                    goodsInfoList.get(i).setGoodsNum(shopSelectCount);

                }
            }
        }
        containTraffic.setText(trafficFee > 0 ? "配送费:¥" + trafficFee + "" : "不含运费");
        Observable.from(goodsInfoList).filter(CarGoodsInfo::isSelect).filter(carGoodsInfo1 -> carGoodsInfo1.getGoodsId() != null).subscribe(carGoodsInfo -> {
            allCount++;
        });
        Observable.from(goodsInfoList).filter(CarGoodsInfo::isSelect).subscribe(carGoodsInfo -> {
            allCountSelect++;
        });

        validSelectCount = 0;
        Observable.from(goodsInfoList).filter(carGoodsInfo -> "正常".equals(carGoodsInfo.getIsInvalid()) && !carGoodsInfo.isBottom()).subscribe(carGoodsInfo1 -> validSelectCount++);

        if (allCountSelect < validSelectCount)
            allSelect.setBackgroundResource(R.drawable.shopping_car_circle);
        else
            allSelect.setBackgroundResource(R.drawable.shopping_car_select);

        balance.setText(allCount + "");


        adapter.notifyDataSetChanged();

    }

    public void confirmCoupon(String merchantId, String couponId, CustomDialog dialog, int position) {
        NewShopApi api = ApiClientBulk.create(NewShopApi.class);
        SaveCouponParam param = new SaveCouponParam();
        param.setMerchantId(merchantId);
        param.setCartId(cartId);
        param.setCouponId(couponId);
        api.confirmCoupon(param, new HttpCallback<MessageToBulk>(getThisContext()) {
            @Override
            public void success(MessageToBulk msg, Response response) {
                if (msg.getCode() == 0) {
                    if (!couponSaveList.contains(couponList.get(position)))
                        couponSaveList.add(couponList.get(position));
                    dialog.dismiss();
                }
            }
        });
    }

    //结算
    public void makeOrder(String merchantId, String cartId) {
        NewShopApi api = ApiClientBulk.create(NewShopApi.class);
        MakeOrderParam param = new MakeOrderParam();
        param.setCartId(cartId);
        List<MakeOrderParam.MerchantList> merchantList = new ArrayList<>();
        merchantList.clear();
        List<MakeOrderParam.ActivityGoodsListTo> activityGoodsList = new ArrayList<>();
        activityGoodsList.clear();


        List<MakeOrderParam.MerchantList.MerchantGoodslist> merchantGoodslists = new ArrayList<>();
        merchantGoodslists.clear();
        MakeOrderParam.MerchantList merchant = new MakeOrderParam.MerchantList();


        for (CarGoodsInfo goodsInfo : goodsInfoList) {


            if (merchantId.equals(goodsInfo.getMerchantId()) && !TextUtils.isEmpty(goodsInfo.getGoodsId()) && goodsInfo.isSelect()) {
                if (!"activity".equals(goodsInfo.getMerchantId())) {
                    MakeOrderParam.MerchantList.MerchantGoodslist merchantGoodslist = new MakeOrderParam.MerchantList.MerchantGoodslist();
                    merchantGoodslist.setGoodsId(goodsInfo.getGoodsId());
                    merchantGoodslist.setGoodsNum(goodsInfo.getGoodsNum() + "");
                    merchantGoodslist.setGoodsType(goodsInfo.getGoodsType());
                    merchantGoodslist.setSpecificationsId(TextUtils.isEmpty(goodsInfo.getNewSpecificationsName()) ? goodsInfo.getSpecificationsId() : goodsInfo.getNewSpecificationsId());
                    merchantGoodslist.setRetailPrice(goodsInfo.getRetailPrice() + "");
                    merchant.setMerchantId(goodsInfo.getMerchantId());
                    merchantGoodslists.add(merchantGoodslist);
                } else {
                    MakeOrderParam.ActivityGoodsListTo activityGoodsListTo = new MakeOrderParam.ActivityGoodsListTo();
                    activityGoodsListTo.setRetailPrice(goodsInfo.getRetailPrice() + "");
                    activityGoodsListTo.setSpecificationsId(TextUtils.isEmpty(goodsInfo.getNewSpecificationsName()) ? goodsInfo.getSpecificationsId() : goodsInfo.getNewSpecificationsId());
                    activityGoodsListTo.setGoodsType(goodsInfo.getGoodsType());
                    activityGoodsListTo.setGoodsId(goodsInfo.getGoodsId());
                    activityGoodsListTo.setGoodsNum(goodsInfo.getGoodsNum() + "");
                    merchant.setMerchantId("activity");
                    merchant.setMerchantId(goodsInfo.getMerchantId());
                    activityGoodsList.add(activityGoodsListTo);
                }
            }

        }
        if ((merchantGoodslists.size() == 0) && (activityGoodsList.size() == 0)) {
            ToastShowLong(getThisContext(), "请至少选择一件商品");
            return;
        }
        merchant.setMerchantGoodslist(merchantGoodslists);
        if (!"activity".equals(merchant.getMerchantId()))
            merchantList.add(merchant);


        param.setMerchantList(merchantList);
        param.setCartActivityGoodsList(activityGoodsList);
        CustomDialogFragment dialogFragment = new CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "");
        api.makeOrder(param, new HttpCallback<MessageToBulk<List<CartSettleGoodsTo>>>(getThisContext()) {
            @Override
            public void success(MessageToBulk<List<CartSettleGoodsTo>> msg, Response response) {
                dialogFragment.dismiss();

                if (msg.getCode() == 0) {

                    Intent intent = new Intent(getThisContext(), ConfirmOlderActivity.class);
                    List<CartSettleGoodsTo> settleGoodsToList = new ArrayList<>();
                    if (msg.getCartSettleGoodsVoList() != null && msg.getCartSettleGoodsVoList().size() > 0) {
                        settleGoodsToList.addAll(msg.getCartSettleGoodsVoList());
                    }
                    if (msg.getCartActivityGoodsSettle() != null && msg.getCartActivityGoodsSettle().getCartActivityGoodsList() != null && msg.getCartActivityGoodsSettle().getCartActivityGoodsList().size() > 0) {
                        CartSettleGoodsTo settleGoodsTo = new CartSettleGoodsTo();
                        settleGoodsTo = msg.getCartActivityGoodsSettle();
                        settleGoodsTo.setMerchantId("activity");
                        settleGoodsTo.setCartMerchantGoodsVolist(settleGoodsTo.getCartActivityGoodsList());
                        settleGoodsToList.add(settleGoodsTo);
                    }
                    intent.putExtra("carList", (Serializable) (settleGoodsToList));
                    intent.putExtra("IsSeaBuy", msg.getIsSeaBuy());
                    startActivity(intent);

                    goToAnimation(1);
                } else

                    ToastShowLong(getThisContext(), msg.getMessage());
            }

            @Override
            public void failure(RetrofitError error) {
                dialogFragment.dismiss();

            }
        });
    }

    public boolean check() {
        boolean haveSelect = false;
        for (CarGoodsInfo goodsInfo : goodsInfoList) {
            if (goodsInfo.isSelect() && goodsInfo.getGoodsId() != null) {
                haveSelect = true;
                break;
            }

        }
        return haveSelect;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        goToAnimation(2);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK)
            onBackPressed();
        return super.onKeyDown(keyCode, event);
    }

    public void jumpShop(String shopSid, String name, String storeName) {
        Intent intent;
        if ("activity".equals(shopSid)) {
            intent = new Intent(getThisContext(), CampaignListActivity.class);
        } else {
            if ("自营商品".equals(name))
                intent = new Intent(getThisContext(), SelfShopActivity.class);
            else
                intent = new Intent(getThisContext(), MerchantShopActivity.class);
            intent.putExtra("ShopSid", shopSid);
            intent.putExtra("ShopName", storeName);
        }
        startActivity(intent);
        goToAnimation(1);
    }

    @Override
    protected void onRestart() {

        if (SpUtil.getBoolean(getThisContext(), "HaveAddCar")) {
            initData();

        }
        super.onRestart();

    }

    public MyShopCarTo setActivityCarData(MyShopCarTo shopCarTos) {


        shopCarTos.setMerchantId("activity");
        shopCarTos.setMerchantName("活动");
        shopCarTos.setStoresName("活动");
        shopCarTos.setHaveSelect(false);
        for (int i = 0; i < shopCarTos.getCartActivityGoogsVoList().size(); i++) {
            shopCarTos.getCartActivityGoogsVoList().get(i).setMerchantId("activity");
            shopCarTos.getCartActivityGoogsVoList().get(i).setIsActivity(true);
        }
        shopCarTos.setCartMerchantGoodsVolist(shopCarTos.getCartActivityGoogsVoList());
        return shopCarTos;
    }

    public void setSpecification(CarGoodsInfo mode, String goodsSid, String goodsType, String specificationsId, TextView specificationText, TextView priceText) {
        NewShopApi api = ApiClientBulk.create(NewShopApi.class);
        GoodsDetailParam param = new GoodsDetailParam();

        param.setGoodsId(goodsSid);
        param.setGoodsType(goodsType);
        CustomDialogFragment dialogFragment = new CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "");
        param.setCommunityId(mUserHelperBulk.getUserInfoTo().getApartmentSid());
        api.getGoodsDetail(param, new HttpCallback<GoodsApiTo>(getThisContext()) {
            @Override
            public void success(GoodsApiTo msg, Response response) {
                dialogFragment.dismiss();

                if (msg.getCode() == 0) {
                    if (msg.getGoodsApiVo() != null) {

                        GoodsDetail goodsDetail = msg.getGoodsApiVo();

                        purchaseSelectDialogShow(mode, goodsDetail, specificationsId, specificationText, priceText);
                    }

                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

    }

    private void purchaseSelectDialogShow(CarGoodsInfo mode, GoodsDetail goodsDetail, String mSpecificationsId, TextView specificationText, TextView priceText) {

        flowViewList.clear();
        goodsSpecificationsList = goodsDetail.getGoodsSpecificationsList();
        final CustomDialog dialog = new CustomDialog(this, R.layout.dialog_detail_purchase_new, R.style.myDialogTheme);
        TagFlowLayout mFlowLayout = (TagFlowLayout) dialog.findViewById(R.id.flowlayout);
        String[] mNames = new String[goodsSpecificationsList.size()];
        for (int i = 0; i < goodsSpecificationsList.size(); i++)
            mNames[i] = goodsSpecificationsList.get(i).getSpecificationsName();

        dialog.findViewById(R.id.confirmPurchase).setOnClickListener(v1 -> {
            if (inventoryNum == 0) {
                ToastShowLong(getThisContext(), "库存不足");
                //  dialog.dismiss();
                return;
            }


            if (TextUtils.isEmpty(changeSpecificationId)) {
                dialog.dismiss();

                return;
            }
            dialog.dismiss();

//           changeGoodsInfoList.clear();
//      //     setChangeData(goodsInfoList);
//           lastChangeGoodsInfoList.clear();
//           lastChangeGoodsInfoList.addAll(goodsInfoList);
            for (int i = 0; i < goodsInfoList.size(); i++) {
                if (goodsDetail.getGoodsId().equals(goodsInfoList.get(i).getGoodsId()) && mode.getSpecificationsId().equals(goodsInfoList.get(i).getSpecificationsId())) {

                    goodsInfoList.get(i).setNewSpecificationsId(specificationsId);
                    goodsInfoList.get(i).setNewSpecificationsName(specificationsName);
                    goodsInfoList.get(i).setRetailPrice(retailPrice);

                    specificationText.setText("已选择:" + specificationsName);
                    priceText.setText("¥" + retailPrice);
                    changeGoods(goodsInfoList.get(i).getMerchantId(), cartId);
                    break;
                }
            }
//           goodsInfoList.clear();
//           goodsInfoList.addAll(setChangeDataGoods(changeGoodsInfoList));

        });
        LinearLayout dismissLayout = (LinearLayout) dialog.findViewById(R.id.dismissLayout);
        RelativeLayout shopCar = (RelativeLayout) dialog.findViewById(R.id.shopCar);
        RelativeLayout shopLayout = (RelativeLayout) dialog.findViewById(R.id.shopLayout);
        ImageView purchaseAdd = (ImageView) dialog.findViewById(R.id.purchaseAdd);
        TextView purchaseNumber = (TextView) dialog.findViewById(R.id.purchaseNumber);
        ImageView purchaseReduce = (ImageView) dialog.findViewById(R.id.purchaseReduce);
        TextView purchaseCost = (TextView) dialog.findViewById(R.id.purchaseCost);
        TextView addCar = (TextView) dialog.findViewById(R.id.add_car);
        TextView purchase = (TextView) dialog.findViewById(R.id.purchase);
        TextView purchaseSurplus = (TextView) dialog.findViewById(R.id.purchaseSurplus);
        TextView purchaseSpecification = (TextView) dialog.findViewById(R.id.purchaseSpecification);
        ImageView purchaseClose = (ImageView) dialog.findViewById(R.id.purchaseClose);
        ImageView purchaseImage = (ImageView) dialog.findViewById(R.id.purchaseImage);
        LinearLayout specificationLayout = (LinearLayout) dialog.findViewById(R.id.specificationLayout);

        dialog.findViewById(R.id.rl).setVisibility(View.GONE);
        purchaseNumber.setText(goodsDetail.getMinSalesNum() + "");


        //设置价格

        //     purchaseCost.setText("价格：¥" + );

        Picasso.with(getThisContext()).load(MainApp.getPicassoImagePath(goodsDetail.getPicUrl())).into(purchaseImage);
        purchaseClose.setOnClickListener(v -> dialog.dismiss());
        //   purchaseSurplus.setText("库存" + "件");

        purchaseSpecification.setText("已选择:" + specificationsName);
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
                            flowViewList.get(i).setTextColor(Color.parseColor("#f17834"));
                            flowViewList.get(i).setBackgroundDrawable(getResources().getDrawable(R.drawable.specification_bg_press));
                            specificationsName = goodsSpecificationsList.get(i).getSpecificationsName();
                            specificationsId = goodsSpecificationsList.get(i).getSpecificationsId();
                            changeSpecificationId = goodsSpecificationsList.get(i).getSpecificationsId();
                            retailPrice = goodsSpecificationsList.get(i).getRetailPrice();
                            specificationsNameSelect = specificationsName;
                            purchaseSpecification.setText("已选择:" + goodsSpecificationsList.get(i).getSpecificationsName());
                            purchaseCost.setText("价格： ¥" + retailPrice + "");
                            inventoryNum = goodsSpecificationsList.get(i).getInventoryNum();

                        } else {
                            flowViewList.get(i).setTextColor(Color.parseColor("#353535"));
                            flowViewList.get(i).setBackgroundDrawable(getResources().getDrawable(R.drawable.specification_bg));
                        }
                    }

                });

                for (int i = 0; i < flowViewList.size(); i++) {
                    if (mSpecificationsId.equals(goodsSpecificationsList.get(i).getSpecificationsId())) {
                        flowViewList.get(i).setTextColor(Color.parseColor("#f17834"));
                        flowViewList.get(i).setBackgroundDrawable(getResources().getDrawable(R.drawable.specification_bg_press));
                        purchaseSpecification.setText("已选择:" + goodsSpecificationsList.get(i).getSpecificationsName());
                        purchaseCost.setText("价格： ¥" + goodsSpecificationsList.get(i).getRetailPrice());
                        inventoryNum = goodsSpecificationsList.get(i).getInventoryNum();
                    }
                }
                return mRelativeLayout;
            }
        });
        Observable.from(mNames).filter(s1 -> !TextUtils.isEmpty(s1)).subscribe(s2 -> {
            specificationLayout.setVisibility(View.VISIBLE);
            purchaseSpecification.setVisibility(View.VISIBLE);

        });

        dismissLayout.setOnClickListener(view -> dialog.dismiss());
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        dialog.show();
    }

    //public List<CarGoodsInfoChange> setChangeData(List<CarGoodsInfo> carGoodsList){
//        changeGoodsInfoList.clear();
//        for (CarGoodsInfo info:carGoodsList){
//            CarGoodsInfoChange infoChange=new CarGoodsInfoChange();
//            infoChange.setGoodsType(info.getGoodsType());
//            infoChange.setNewSpecificationsId(info.getNewSpecificationsId());
//            infoChange.setSpecificationsName(info.getSpecificationsName());
//            infoChange.setMerchantId(info.getMerchantId());
//            infoChange.setNewSpecificationsName(info.getNewSpecificationsName());
//            infoChange.setRetailPrice(info.getRetailPrice());
//            infoChange.setCartIdTop(info.getCartIdTop());
//            infoChange.setCategoryId(info.getCategoryId());
//            infoChange.setCategoryName(info.getCategoryName());
//            infoChange.setDiscountPercentage(info.getDiscountPercentage());
//            infoChange.setDistributionCost(info.getDistributionCost());
//            infoChange.setDistributionMode(info.getDistributionMode());
//            infoChange.setGoodsId(info.getGoodsId());
//            infoChange.setGoodsLabel(info.getGoodsLabel());
//            infoChange.setGoodsName(info.getGoodsName());
//            infoChange.setMinSalesNum(info.getMinSalesNum());
//            infoChange.setIsActivity(info.isActivity());
//            infoChange.setNoLine(info.isNoLine());
//            infoChange.setStoresName(info.getStoresName());
//            infoChange.setNum(info.getNum());
//            infoChange.setStoresNameTop(info.getStoresNameTop());
//            infoChange.setSalesType(info.getSalesType());
//            infoChange.setIsEdit(info.isEdit());
//            infoChange.setIsSelect(info.isSelect());
//            infoChange.setPicUrl(info.getPicUrl());
//            infoChange.setMerchantName(info.getMerchantName());
//            infoChange.setIsGroup(info.getIsGroup());
//            infoChange.setMaxSalesNum(info.getMaxSalesNum());
//            infoChange.setGoodsNum(info.getGoodsNum());
//            infoChange.setIsInvalid(info.getIsInvalid());
//            infoChange.setMerchantIdTop(info.getMerchantIdTop());
//            infoChange.setSpecificationsId(info.getSpecificationsId());
//            infoChange.setIsSelect(info.isSelect());
//            infoChange.setMerchantNameTop(info.getMerchantNameTop());
//            infoChange.setIsBottom(info.isBottom());
//            changeGoodsInfoList.add(infoChange);
//
//
//        }
//        return changeGoodsInfoList;
//
//    }
//将改变的内容放到goodsList
//    public List<CarGoodsInfo> setChangeDataGoods(List<CarGoodsInfoChange> carGoodsList){
//    List<CarGoodsInfo>carGoodsInfos=new ArrayList<>();
//        for (CarGoodsInfoChange info:carGoodsList){
//            CarGoodsInfo infoChange=new CarGoodsInfo();
//            infoChange.setGoodsType(info.getGoodsType());
//            infoChange.setNewSpecificationsId(info.getNewSpecificationsId());
//            infoChange.setSpecificationsName(info.getSpecificationsName());
//            infoChange.setMerchantId(info.getMerchantId());
//            infoChange.setNewSpecificationsName(info.getNewSpecificationsName());
//            infoChange.setRetailPrice(info.getRetailPrice());
//            infoChange.setCartIdTop(info.getCartIdTop());
//            infoChange.setCategoryId(info.getCategoryId());
//            infoChange.setCategoryName(info.getCategoryName());
//            infoChange.setDiscountPercentage(info.getDiscountPercentage());
//            infoChange.setDistributionCost(info.getDistributionCost());
//            infoChange.setDistributionMode(info.getDistributionMode());
//            infoChange.setGoodsId(info.getGoodsId());
//            infoChange.setGoodsLabel(info.getGoodsLabel());
//            infoChange.setGoodsName(info.getGoodsName());
//            infoChange.setMinSalesNum(info.getMinSalesNum());
//            infoChange.setIsActivity(info.isActivity());
//            infoChange.setNoLine(info.isNoLine());
//            infoChange.setStoresName(info.getStoresName());
//            infoChange.setNum(info.getNum());
//            infoChange.setStoresNameTop(info.getStoresNameTop());
//            infoChange.setSalesType(info.getSalesType());
//            infoChange.setIsEdit(info.isEdit());
//            infoChange.setIsSelect(info.isSelect());
//            infoChange.setPicUrl(info.getPicUrl());
//            infoChange.setMerchantName(info.getMerchantName());
//            infoChange.setIsGroup(info.getIsGroup());
//            infoChange.setMaxSalesNum(info.getMaxSalesNum());
//            infoChange.setGoodsNum(info.getGoodsNum());
//            infoChange.setIsInvalid(info.getIsInvalid());
//            infoChange.setMerchantIdTop(info.getMerchantIdTop());
//            infoChange.setSpecificationsId(info.getSpecificationsId());
//            infoChange.setIsSelect(info.isSelect());
//            infoChange.setMerchantNameTop(info.getMerchantNameTop());
//            infoChange.setIsBottom(info.isBottom());
//            carGoodsInfos.add(infoChange);
//
//
//        }
//        return carGoodsInfos;
//
//    }
    public void makeOlderNew(String merchantId) {
        System.out.println(merchantId);
        NewShopApi api = ApiClientBulk.create(NewShopApi.class);
        MakeOlderNewParam param = new MakeOlderNewParam();
        param.setGoodsIds("");
        param.setCartId(cartId);
        for (int i = 0; i < goodsInfoList.size(); i++) {
            if (goodsInfoList.get(i).getGoodsId() != null && goodsInfoList.get(i).isSelect() && merchantId.equals(goodsInfoList.get(i).getMerchantId())) {
                param.setGoodsIds(param.getGoodsIds() + "#" + goodsInfoList.get(i).getGoodsId());
            }
        }
        if (param.getGoodsIds().length() > 2)
            param.setGoodsIds(param.getGoodsIds().substring(1));
        System.out.println(param + "param");
        api.newMakeOlder(param, new Callback<MessageToBulk<List<CartSettleGoodsTo>>>() {
            @Override
            public void success(MessageToBulk<List<CartSettleGoodsTo>> msg, Response response) {

                if (msg.getCode() == 0) {

                    Intent intent = new Intent(getThisContext(), ConfirmOlderActivity.class);
                    List<CartSettleGoodsTo> settleGoodsToList = new ArrayList<>();
                    if (msg.getCartSettleGoodsVoList() != null && msg.getCartSettleGoodsVoList().size() > 0) {
                        settleGoodsToList.addAll(msg.getCartSettleGoodsVoList());
                    }
                    if (msg.getCartActivityGoodsSettle() != null && msg.getCartActivityGoodsSettle().getCartActivityGoodsList() != null && msg.getCartActivityGoodsSettle().getCartActivityGoodsList().size() > 0) {
                        CartSettleGoodsTo settleGoodsTo = new CartSettleGoodsTo();
                        settleGoodsTo = msg.getCartActivityGoodsSettle();
                        settleGoodsTo.setMerchantId("activity");
                        settleGoodsTo.setCartMerchantGoodsVolist(settleGoodsTo.getCartActivityGoodsList());
                        settleGoodsToList.add(settleGoodsTo);
                    }
                    intent.putExtra("carList", (Serializable) (settleGoodsToList));
                    intent.putExtra("IsSeaBuy", msg.getIsSeaBuy());
                    startActivity(intent);

                    goToAnimation(1);
                } else

                    ToastShowLong(getThisContext(), msg.getMessage());

            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PublicWay.activityList.remove(this);
    }
}
