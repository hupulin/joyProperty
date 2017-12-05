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
import com.jinyi.ihome.infrastructure.MessageToBulk;
import com.jinyi.ihome.module.newshop.AddCarParam;
import com.jinyi.ihome.module.newshop.BlankParam;
import com.jinyi.ihome.module.newshop.CategoryTo;
import com.jinyi.ihome.module.newshop.GoodsListParam;
import com.jinyi.ihome.module.newshop.MyShopCarTo;
import com.jinyi.ihome.module.newshop.ShopListDetailTo;
import com.joy.common.api.ApiClientBulk;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.NewShopApi;
import com.joy.property.R;
import com.joy.property.base.BaseActivity;
import com.joyhome.nacity.app.photo.util.PublicWay;
import com.joy.property.shop.adapter.GoodsListAdapter;
import com.joy.property.shop.shoputil.CarNumberUtil;
import com.joyhome.nacity.app.util.CustomDialogFragment;
import com.joyhome.nacity.app.util.SpUtil;
import com.joyhome.nacity.app.util.ViewPagerCompat;

import java.util.ArrayList;
import java.util.List;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by xz on 2016/7/16.
 **/
public class MerchantShopActivity extends BaseActivity implements View.OnClickListener {


    private List<ShopListDetailTo>detailList=new ArrayList<>();
    private TextView salas;
    private TextView price;
    private TextView newGoods;
    private ListView listView;
    private GoodsListAdapter adapter;
    private PullToRefreshListView pullListView;
    private int pagerIndex=1;
    private int currentSelect=0;
    private boolean highPrice;
    private ImageView priceIcon;
    private List<CategoryTo> categoryList=new ArrayList<>();
    private TextView title;

    private String shopSid;
    private TextView carNumber;
    private LinearLayout noShop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_more_type);
        initView();
        setView();
        getSalesData("");
        refreshData();
        getType();
        enterShop();
        enterGoodsDetail();

    }



    private void refreshData() {
        pullListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                pagerIndex = 1;
                if (currentSelect ==0) {
                    getSalesData("");
                } else if (currentSelect == 1) {
                    initData("");
                } else if (currentSelect == 2) {
                    getNewGoods("");
                }
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                pagerIndex++;
                if (currentSelect == 0) {
                    getSalesData(pagerIndex + "");
                } else if (currentSelect == 1) {
                    initData(pagerIndex + "");
                } else if (currentSelect == 2) {
                    getNewGoods(pagerIndex + "");
                }
            }
        });
    }


    private void initView() {
        salas = (TextView) findViewById(R.id.sales);
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
        adapter = new GoodsListAdapter(getThisContext());
        adapter.setList(detailList);
        listView.setAdapter(adapter);
        findViewById(R.id.shop_type).setOnClickListener(this);
        title = (TextView) findViewById(R.id.title);
        carNumber = (TextView) findViewById(R.id.carNumber);
        findViewById(R.id.back).setOnClickListener(this);
        findViewById(R.id.shop_car).setOnClickListener(this);
        noShop = (LinearLayout) findViewById(R.id.noShop);
    }
    private void setView() {
        CarNumberUtil.getCarNumber(mUserHelperBulk.getSid(), getThisContext(), carNumber);
        shopSid =  getIntent().getStringExtra("ShopSid");
        String shopName = getIntent().getStringExtra("ShopName");
        title.setText(shopName);
    }
    private void initData(String index) {
        NewShopApi api = ApiClientBulk.create(NewShopApi.class);
        final CustomDialogFragment dialogFragment = new CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "");
        GoodsListParam param = new GoodsListParam();
        param.setMerchantId(shopSid);
        param.setCommunityId(mUserHelperBulk.getUserInfoTo().getApartmentSid());
        param.setCurrentPage(index);
        param.setPageSize("20");
        if (highPrice)
            param.setPriceSort("desc");
        else
        param.setPriceSort("asc");
        api.getMoreMerchant(param, new HttpCallback<MessageToBulk<List<ShopListDetailTo>>>(getThisContext()) {
            @Override
            public void success(MessageToBulk<List<ShopListDetailTo>> msg, Response response) {
                dialogFragment.dismiss();
                setListComplement();
                if (msg.getCode() == 0) {
                    if ("".equals(index))
                        detailList.clear();
                    if (msg.getGoodsApiVoList() != null) {
                        detailList.addAll(msg.getGoodsApiVoList());

                        setNoShop(detailList.size());
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                setListComplement();
                dialogFragment.dismiss();
            }
        });
    }

    private void getSalesData(String index) {
        NewShopApi api = ApiClientBulk.create(NewShopApi.class);
        final CustomDialogFragment dialogFragment = new CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "");
        GoodsListParam param = new GoodsListParam();
        param.setCurrentPage(index);
        param.setPageSize("20");
        param.setCommunityId(mUserHelperBulk.getUserInfoTo().getApartmentSid());
        param.setMerchantId(shopSid);
        System.out.println(shopSid + "sid");
        api.getBySaleMerchant(param, new HttpCallback<MessageToBulk<List<ShopListDetailTo>>>(getThisContext()) {
            @Override
            public void success(MessageToBulk<List<ShopListDetailTo>> msg, Response response) {
                dialogFragment.dismiss();
                setListComplement();
                System.out.println(msg + "---");
                if (msg.getCode() == 0) {
                    if ("".equals(index)) {
                        detailList.clear();
                    }
                    if (msg.getGoodsApiVoList() != null)
                        detailList.addAll(msg.getGoodsApiVoList());
                    setNoShop(detailList.size());
                    adapter.notifyDataSetChanged();

                }
            }

            @Override
            public void failure(RetrofitError error) {
                dialogFragment.dismiss();
                setListComplement();
            }
        });
    }
    private void getNewGoods(String index) {
        NewShopApi api = ApiClientBulk.create(NewShopApi.class);
        final CustomDialogFragment dialogFragment = new CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "");
        GoodsListParam param = new GoodsListParam();
        param.setCurrentPage(index);
        param.setPageSize("20");
        param.setCommunityId(mUserHelperBulk.getUserInfoTo().getApartmentSid());
        param.setMerchantId(shopSid);
        api.getByNewGoodsMerchant(param, new HttpCallback<MessageToBulk<List<ShopListDetailTo>>>(getThisContext()) {
            @Override
            public void success(MessageToBulk<List<ShopListDetailTo>> msg, Response response) {
                dialogFragment.dismiss();
                setListComplement();
                if (msg.getCode() == 0) {
                    if ("".equals(index)) {
                        detailList.clear();
                    }
                    if (msg.getGoodsApiVoList() != null)
                        detailList.addAll(msg.getGoodsApiVoList());
                    setNoShop(detailList.size());
                    adapter.notifyDataSetChanged();

                }
            }

            @Override
            public void failure(RetrofitError error) {
                dialogFragment.dismiss();
                setListComplement();
            }
        });
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back:
                onBackPressed();
                break;
            case R.id.sales:
                currentSelect=0;
                getSalesData("");
                salas.setTextColor(Color.parseColor("#46b4d9"));
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
                    initData("");
                currentSelect=1;
                salas.setTextColor(Color.parseColor("#353535"));
                price.setTextColor(Color.parseColor("#46b4d9"));
                newGoods.setTextColor(Color.parseColor("#353535"));
                pagerIndex=1;
                break;
            case R.id.newGoods:
                currentSelect=2;
                salas.setTextColor(Color.parseColor("#353535"));
                price.setTextColor(Color.parseColor("#353535"));
                newGoods.setTextColor(Color.parseColor("#46b4d9"));
                getNewGoods("");
                pagerIndex=1;
                break;
            case R.id.shop_type:
                showType();
                break;
            case R.id.shop_car:
                startActivity(new Intent(getThisContext(),ShoppingCarActivity.class));
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
            linearLayout.setOnClickListener(v -> ToastShowLong(getThisContext(),shopListTo.getName()));
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
                startActivity(new Intent(getThisContext(),MerchantShopActivity.class));
                finish();
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

        api.getCategory(param, new HttpCallback<MessageToBulk<List<CategoryTo>>>(getThisContext()) {
            @Override
            public void success(MessageToBulk<List<CategoryTo>> msg, Response response) {
                if (msg.getCode() == 0) {
                    categoryList.clear();

                    categoryList.addAll(msg.getGoodsCategoryList());


                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
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
                if (msg.getCode()==0) {
                    ToastShowLong(getThisContext(), "加入购物车成功");
                    carNumber.setText(msg.getTotal() + "");
                    SpUtil.put(getThisContext(), "HaveAddCar", true);
                }else
                    ToastShowLong(getThisContext(),msg.getMessage());
            }

            @Override
            public void failure(RetrofitError error) {
                dialogFragment.dismiss();

            }
        });
    }
    private void enterGoodsDetail() {
        adapter.setEnterGoodsDetailListener(new GoodsListAdapter.EnterGoodsDetailListener() {
            @Override
            public void OnEnterDetailClick(String goodsSid,boolean isActivity) {
                Intent intent = new Intent(getThisContext(), SideGoodsDetailActivity.class);
                intent.putExtra("GoodsSid", goodsSid);
                intent.putExtra("ActivityGoods", isActivity);
              //  intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                if (PublicWay.goodsDetailActivity!=null)
                    PublicWay.goodsDetailActivity.finish();
                startActivity(intent);
            }
        });
    }
    private void enterShop() {

        adapter.setAddCarClickListener(new GoodsListAdapter.AddCarListener() {
            @Override
            public void OnAddCarClick(String goodsId, String name) {
                addCar(goodsId);
            }
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
    public void setNoShop(int size){
        if (size==0)
            noShop.setVisibility(View.VISIBLE);
        else
            noShop.setVisibility(View.GONE);
    }
}
