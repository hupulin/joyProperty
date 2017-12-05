package com.joy.property.shop;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.infrastructure.MessageToBulk;
import com.jinyi.ihome.module.newshop.AddCarParam;
import com.jinyi.ihome.module.newshop.BlankParam;
import com.jinyi.ihome.module.newshop.CategoryTo;
import com.jinyi.ihome.module.newshop.ShopListDataTo;
import com.jinyi.ihome.module.newshop.ShopListDetailTo;
import com.joy.common.api.ApiClientBulk;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.NewShopApi;

import com.joy.property.R;
import com.joy.property.base.BaseActivity;
import com.joy.property.shop.shoputil.CarNumberUtil;
import com.joyhome.nacity.app.photo.util.PublicWay;
import com.joy.property.shop.adapter.CouponGoodsAdapter;
import com.joyhome.nacity.app.util.CustomDialogFragment;
import com.joyhome.nacity.app.util.ViewPagerCompat;

import java.util.ArrayList;
import java.util.List;

import retrofit.RetrofitError;
import retrofit.client.Response;

public class ActivityShopMore extends BaseActivity implements View.OnClickListener {


    private List<ShopListDetailTo>detailList=new ArrayList<>();
    private TextView salas;
    private TextView price;
    private TextView newGoods;
    private CouponGoodsAdapter adapter;
    private ListView listView;
    private PullToRefreshListView pullListView;
    private int pagerIndex=1;
    private int currentSelect=0;
    private boolean highPrice;
    private ImageView priceIcon;
    private List<CategoryTo> categoryList=new ArrayList<>();
    private TextView carNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_more);
        initView();
        getSalesData(pagerIndex, "3");
        refreshData();
        getType();
        // enterShop();
        getMyShopCarNumber();
        enterGoodsDetail();
        PublicWay.activityList.add(this);
    }
    private void refreshData() {
        pullListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                pagerIndex = 1;
                if (currentSelect == 2) {
                    getSalesData(pagerIndex,"1");
                } else if (currentSelect == 1) {
                    getSalesData(pagerIndex, "2");
                } else if (currentSelect == 0) {
                    getSalesData(pagerIndex,"3");
                }
            }
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                pagerIndex++;
                if (currentSelect == 2) {
                    getSalesData(pagerIndex,"1");
                } else if (currentSelect == 1) {
                    getSalesData(pagerIndex, "2");
                } else if (currentSelect == 0) {
                    getSalesData(pagerIndex, "3");

                }
            }
        });
    }
    private void initView() {
        salas = (TextView) findViewById(R.id.sales);
        TextView title = (TextView) findViewById(R.id.title);
        title.setText("适用商品");
        price = (TextView) findViewById(R.id.price);
        newGoods = (TextView) findViewById(R.id.newGoods);
        priceIcon = (ImageView) findViewById(R.id.priceIcon);
        salas.setOnClickListener(this);
        price.setOnClickListener(this);
        newGoods.setOnClickListener(this);
        pullListView = (PullToRefreshListView) findViewById(R.id.listView);
        pullListView.setMode(PullToRefreshBase.Mode.BOTH);
        listView= pullListView.getRefreshableView();
        listView.setDividerHeight(0);
        adapter = new CouponGoodsAdapter(getThisContext());
        adapter.setList(detailList);
        listView.setAdapter(adapter);
        findViewById(R.id.shop_type).setOnClickListener(this);
        carNumber = (TextView) findViewById(R.id.carNumber);
        findViewById(R.id.shopCar).setOnClickListener(this);
        findViewById(R.id.back).setOnClickListener(this);

    }
    //    private void initData(String index) {
//        NewShopApi api = ApiClientBulk.create(NewShopApi.class);
//        final CustomDialogFragment dialogFragment = new CustomDialogFragment();
//        dialogFragment.show(getSupportFragmentManager(), "");
//        GoodsListParam param = new GoodsListParam();
//        param.setCommunityId(mUserHelperBulk.getUserInfoTo().getApartmentSid());
//        param.setCurrentPage(index);
//        param.setPageSize("20");
//        if (highPrice)
//            param.setPriceSort("desc");
//        else
//        param.setPriceSort("asc");
//        api.getMore(param, new HttpCallback<MessageToBulk<List<ShopListDetailTo>>>(getThisContext()) {
//            @Override
//            public void success(MessageToBulk<List<ShopListDetailTo>> msg, Response response) {
//                dialogFragment.dismiss();
//                setListComplement();
//                if (msg.getCode() == 0) {
//                    if ("".equals(index))
//                        detailList.clear();
//                    if (msg.getGoodsApiVoList() != null)
//                        detailList.addAll(msg.getGoodsApiVoList());
//                    adapter.notifyDataSetChanged();
//                }
//            }
//
//            @Override
//            public void failure(RetrofitError error) {
//                setListComplement();
//                dialogFragment.dismiss();
//            }
//        });
//    }

    //index 页码
    //type 排序高低
    private void getSalesData(int index,String type) {
        NewShopApi api = ApiClientBulk.create(NewShopApi.class);
        final CustomDialogFragment dialogFragment = new CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "");
//        CouponGoodsListParam param = new CouponGoodsListParam();
//        param.setNextPage(index);
//        param.setLimit("20");
//        param.setSortType(type);
//        param.setOrder("asc");
//        if("2".equals(type)) {
//            if (highPrice)
//                param.setOrder("desc");
//            else
//                param.setOrder("asc");
//        }
//       param.setCouponId(getIntent().getStringExtra("couponId"));
//        param.setCommunityId(mUserHelperBulk.getUserInfoTo().getApartmentSid());
//        Log.i("2222", "getSalesData: "+param.toString());
        String  order="asc";
        if("2".equals(type)) {
            if (highPrice)
                order="desc";
            else
                order="asc";
        }
        api.getCouponGoodsList(getIntent().getStringExtra("couponId"), mUserHelperBulk.getUserInfoTo().getApartmentSid(), type, order, index, 20, new HttpCallback<MessageTo<ShopListDataTo>>(getThisContext()) {
            @Override
            public void success(MessageTo<ShopListDataTo> msg, Response response) {
                dialogFragment.dismiss();
                setListComplement();
                if (msg.getSuccess() == 0) {
                    if (index == 1) {
                        detailList.clear();
                    }
                    if (msg.getData().getList() != null) {
                        detailList.addAll(msg.getData().getList());

                        for (int i = 0; i < detailList.size(); i++) {
                            System.out.println(detailList.get(i).getRetailPrice() + "retailPrice");
                        }
                        adapter.notifyDataSetChanged();
                    }
                }

            }

            @Override
            public void failure(RetrofitError error) {
                dialogFragment.dismiss();
                setListComplement();
            }
        });
    }
//        private void getNewGoods(String index) {
//        NewShopApi api = ApiClientBulk.create(NewShopApi.class);
//        final CustomDialogFragment dialogFragment = new CustomDialogFragment();
//        dialogFragment.show(getSupportFragmentManager(), "");
//        GoodsListParam param = new GoodsListParam();
//        param.setCurrentPage(index);
//        param.setPageSize("20");
//        param.setCommunityId(mUserHelperBulk.getUserInfoTo().getApartmentSid());
//        api.getByNewGoods(param, new HttpCallback<MessageToBulk<List<ShopListDetailTo>>>(getThisContext()) {
//            @Override
//            public void success(MessageToBulk<List<ShopListDetailTo>> msg, Response response) {
//                dialogFragment.dismiss();
//                setListComplement();
//                if (msg.getCode() == 0) {
//                    if ("".equals(index)) {
//                       detailList.clear();
//                    }
//                    if (msg.getGoodsApiVoList() != null)
//
//                        detailList.addAll(msg.getGoodsApiVoList());
//                        adapter.notifyDataSetChanged();
//
//                }
//            }
//
//            @Override
//            public void failure(RetrofitError error) {
//                dialogFragment.dismiss();
//                setListComplement();
//            }
//        });
//    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back:
                onBackPressed();
                break;
            case R.id.sales:
                currentSelect=2;
                getSalesData(1,"1");
                salas.setTextColor(Color.parseColor("#4fb2d6"));
                price.setTextColor(Color.parseColor("#353535"));
                newGoods.setTextColor(Color.parseColor("#353535"));
                pagerIndex=1;
                break;
            case R.id.price:
                if (currentSelect==1) {
                    if (highPrice) {
                        highPrice = false;
                        priceIcon.setBackgroundResource(R.drawable.shop_price_up);
                    }
                    else {
                        highPrice = true;
                        priceIcon.setBackgroundResource(R.drawable.shop_price_down);
                    }
                }
                getSalesData(1, "2");
                currentSelect=1;
                salas.setTextColor(Color.parseColor("#353535"));
                price.setTextColor(Color.parseColor("#4fb2d6"));
                newGoods.setTextColor(Color.parseColor("#353535"));
                pagerIndex=1;
                break;
            case R.id.newGoods:
                currentSelect=0;
                salas.setTextColor(Color.parseColor("#353535"));
                price.setTextColor(Color.parseColor("#353535"));
                newGoods.setTextColor(Color.parseColor("#4fb2d6"));
                getSalesData(1,"3");
                pagerIndex=1;
                break;
            case R.id.shop_type:
                showType();
                break;
            case R.id.shopCar:
                startActivity(new Intent(getThisContext(),ShoppingCarActivity.class));
                goToAnimation(1);
                break;
            case R.id.add_car:

                break;
            case R.id.shoppingCar:
                startActivity(new Intent(getThisContext(), ShoppingCarActivity.class));
                goToAnimation(1);

                break;
        }
    }
    public void setListComplement(){
        pullListView.onRefreshComplete();
    }
    public void showType(){
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.dialog_shop_type, null);

        GridLayout channelGorup = (GridLayout)contentView. findViewById(R.id.channel_group);
        for(CategoryTo shopListTo:categoryList){
            View linearLayout= LinearLayout.inflate(getThisContext(), R.layout.shop_type_pup_item, null);
            TextView channelName = (TextView) linearLayout.findViewById(R.id.channel_name);
            channelName.setText(shopListTo.getName());

            WindowManager wm = (WindowManager)getThisContext().getSystemService(getThisContext().WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            int mWidth=display.getWidth();
            int marginLeft =(int)(mWidth*0.0805);
            int marginBottom =(int)(mWidth*0.0486);
            int  width = (int)(mWidth*0.2013);
            GridLayout.LayoutParams lp = new GridLayout.LayoutParams();
            lp.width = width;
            lp.height = ViewPagerCompat.LayoutParams.WRAP_CONTENT;
            lp.setMargins(marginLeft, 0, 0, marginBottom);
            linearLayout.setLayoutParams(lp);
            linearLayout.setOnClickListener(v -> {
                Intent intent=new Intent(getThisContext(),ShopMoreType.class);
                intent.putExtra("category",shopListTo);
                startActivity(intent);
                goToAnimation(1);
            });
            channelGorup.addView(linearLayout);
        }

        View view=findViewById(R.id.layout_title);
        contentView.setFocusable(true);
        contentView.setFocusableInTouchMode(true);

        PopupWindow popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setFocusable(true);
        contentView.findViewById(R.id.pup_layout).setOnClickListener(v ->popupWindow.dismiss());
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAsDropDown(view);
    }
    private void getType() {
        NewShopApi api= ApiClientBulk.create(NewShopApi.class);
        BlankParam param=new BlankParam();
        param.setCommunityId(mUserHelperBulk.getUserInfoTo().getApartmentSid());
        api.getCategory(param, new HttpCallback<MessageToBulk<List<CategoryTo>>>(getThisContext()) {
            @Override
            public void success(MessageToBulk<List<CategoryTo>> msg, Response response) {
                if (msg.getCode() == 0) {
                    categoryList.clear();
                    if (msg.getGoodsCategoryList() != null)
                        categoryList.addAll(msg.getGoodsCategoryList());
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    //    private void enterShop() {
//        adapter.setCanEnterShopClickListener((shopSid, name,saleType) -> {
//            Intent intent;
//            if ("自营商品".equals(saleType))
//             intent=new Intent(getThisContext(),SelfShopActivity.class);
//            else
//                intent=new Intent(getThisContext(),MerchantShopActivity.class);
//            intent.putExtra("ShopSid",shopSid);
//            intent.putExtra("ShopName",name);
//            System.out.println(shopSid+"sid"+name);
//            startActivity(intent);
//            goToAnimation(1);
//        });
//        adapter.setAddCarClickListener((goodsId, name) -> addCar(goodsId));
//    }
    public void addCar(String goodsId){
        NewShopApi api=ApiClientBulk.create(NewShopApi.class);
        AddCarParam param=new AddCarParam();
        param.setGoodsId(goodsId);
        param.setUserId(mUserHelperBulk.getSid());
        param.setNum("");
        final CustomDialogFragment dialogFragment = new CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "");
        api.addCar(param, new HttpCallback<MessageToBulk>(getThisContext()) {
            @Override
            public void success(MessageToBulk msg, Response response) {
                dialogFragment.dismiss();
                if (msg.getCode()== 0) {
                    ToastShowLong(getThisContext(), "加入购物车成功");
                    carNumber.setText(msg.getTotal() + "");
                }else
                    ToastShowLong(getThisContext(),msg.getMessage());
            }

            @Override
            public void failure(RetrofitError error) {
                dialogFragment.dismiss();

            }
        });
    }
    public void getMyShopCarNumber(){

        CarNumberUtil.getCarNumber(mUserHelperBulk.getSid(), getThisContext(), carNumber);
    }
    private void enterGoodsDetail() {
        adapter.setEnterGoodsDetailListener((goodsSid ,goodsType)-> {
            Intent intent = new Intent(getThisContext(), SideGoodsDetailActivity.class);
            intent.putExtra("GoodsSid",goodsSid);
        //    intent.putExtra("ActivityGoods", !"0".equals(goodsType));
            intent.putExtra("ActivityGoods",false);
            startActivity(intent);

            goToAnimation(1);
        });
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
    protected void onRestart() {
        super.onRestart();
        getMyShopCarNumber();
    }
}
