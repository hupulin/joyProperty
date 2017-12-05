package com.joy.property.shop;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
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
import com.jinyi.ihome.module.newshop.MyShopCarTo;
import com.jinyi.ihome.module.newshop.ShopListDetailTo;
import com.jinyi.ihome.module.shop.SearchParam;
import com.joy.common.api.ApiClientBulk;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.NewShopApi;
import com.joy.property.R;
import com.joy.property.base.BaseActivity;
import com.joyhome.nacity.app.photo.util.PublicWay;
import com.joy.property.shop.adapter.GoodsListAdapter;
import com.joyhome.nacity.app.util.CustomDialogFragment;
import com.joyhome.nacity.app.util.ViewPagerCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by xz on 2016/7/16.
 **/
public class SearchActivity extends BaseActivity implements View.OnClickListener {
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
    private TextView carNumber;
    private EditText searchText;
    private LinearLayout noSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_shop);
        initView();
        setSearch();
        refreshData();
        getType();
        enterShop();
        enterGoodsDetail();
        PublicWay.activityList.add(this);

    }

    private void refreshData() {
        pullListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                pagerIndex = 1;
                if (currentSelect == 0) {
                    getSalesData(pagerIndex + "", searchText.getText().toString());
                } else if (currentSelect == 1) {
                    searchByPrice(pagerIndex + "", searchText.getText().toString());
                } else if (currentSelect ==2) {
                    getNewGoods(pagerIndex + "", searchText.getText().toString());
                }
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                pagerIndex++;
                if (currentSelect == 0) {
                    getSalesData(pagerIndex + "", searchText.getText().toString());
                } else if (currentSelect == 1) {
                    searchByPrice(pagerIndex + "", searchText.getText().toString());
                } else if (currentSelect ==2) {
                    getNewGoods(pagerIndex + "", searchText.getText().toString());
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
        carNumber = (TextView) findViewById(R.id.carNumber);
        searchText = (EditText) findViewById(R.id.et_search);
        popKeyBoard(searchText);
        findViewById(R.id.tv_cancel).setOnClickListener(this);
        findViewById(R.id.iv_back).setOnClickListener(this);
        noSearch = (LinearLayout) findViewById(R.id.noSearch);
    }
    private void searchByPrice(String index,String name) {
        if (TextUtils.isEmpty(name))
            return;
        NewShopApi api = ApiClientBulk.create(NewShopApi.class);
//        final CustomDialogFragment dialogFragment = new CustomDialogFragment();
//        dialogFragment.show(getSupportFragmentManager(), "");
        SearchParam param = new SearchParam();
        param.setCommunityId(mUserHelperBulk.getUserInfoTo().getApartmentSid());
        param.setCurrentPage(index);
        param.setPageSize("20");
        param.setGoodsName(name);
        if (highPrice)
            param.setPriceSort("desc");
        else
        param.setPriceSort("asc");
        api.searchByPrice(param, new HttpCallback<MessageToBulk<List<ShopListDetailTo>>>(getThisContext()) {
            @Override
            public void success(MessageToBulk<List<ShopListDetailTo>> msg, Response response) {
           //     dialogFragment.dismiss();
                setListComplement();
                if (msg.getCode() == 0) {

                    if ("1".equals(index))
                        detailList.clear();
                    if (msg.getGoodsApiVoList() != null)
                        detailList.addAll(msg.getGoodsApiVoList());
                    setSearchVisible(detailList.size());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                setListComplement();
          //      dialogFragment.dismiss();
            }
        });
    }

    private void getSalesData(String index,String name) {
        if (TextUtils.isEmpty(name))
            return;
        NewShopApi api = ApiClientBulk.create(NewShopApi.class);
//        final CustomDialogFragment dialogFragment = new CustomDialogFragment();
//        dialogFragment.show(getSupportFragmentManager(), "");
        SearchParam param = new SearchParam();
        param.setCurrentPage(index);
        param.setPageSize("20");
        param.setGoodsName(name);
        param.setCommunityId(mUserHelperBulk.getUserInfoTo().getApartmentSid());
        System.out.println(param+"param");
        api.searchBySalas(param, new HttpCallback<MessageToBulk<List<ShopListDetailTo>>>(getThisContext()) {
            @Override
            public void success(MessageToBulk<List<ShopListDetailTo>> msg, Response response) {
             //   dialogFragment.dismiss();

                setListComplement();
                if (msg.getCode() == 0) {
                    if ("1".equals(index)) {
                        detailList.clear();
                    }
                    if (msg.getGoodsApiVoList() != null)
                        detailList.addAll(msg.getGoodsApiVoList());
                    setSearchVisible(detailList.size());
                    adapter.notifyDataSetChanged();

                }
            }

            @Override
            public void failure(RetrofitError error) {
              //  dialogFragment.dismiss();
                setListComplement();
            }
        });
    }
    private void getNewGoods(String index,String name) {
        if (TextUtils.isEmpty(name))
          return;
        NewShopApi api = ApiClientBulk.create(NewShopApi.class);
//        final CustomDialogFragment dialogFragment = new CustomDialogFragment();
//        dialogFragment.show(getSupportFragmentManager(), "");
        SearchParam param = new SearchParam();
        param.setCurrentPage(index);
        param.setPageSize("20");
        param.setGoodsName(name);
        param.setCommunityId(mUserHelperBulk.getUserInfoTo().getApartmentSid());
        api.searchByNewGoods(param, new HttpCallback<MessageToBulk<List<ShopListDetailTo>>>(getThisContext()) {
            @Override
            public void success(MessageToBulk<List<ShopListDetailTo>> msg, Response response) {
            //    dialogFragment.dismiss();
                setListComplement();
                if (msg.getCode() == 0) {
                    if ("1".equals(index)) {
                        detailList.clear();
                    }
                    if (msg.getGoodsApiVoList() != null)
                        detailList.addAll(msg.getGoodsApiVoList());
                    setSearchVisible(detailList.size());
                    adapter.notifyDataSetChanged();

                }
            }

            @Override
            public void failure(RetrofitError error) {
         //       dialogFragment.dismiss();
                setListComplement();
            }
        });
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.sales:
                currentSelect=0;
                getSalesData("1",searchText.getText().toString());
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
                    searchByPrice("1",searchText.getText().toString());
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
                getNewGoods("1",searchText.getText().toString());
                pagerIndex=1;
                break;
            case R.id.shop_type:
                showType();
                break;
            case R.id.shopCar:
                startActivity(new Intent(getThisContext(),ShoppingCarActivity.class));
                goToAnimation(1);
                break;
            case R.id.tv_cancel:
                searchText.setText("");

                break;
            case R.id.iv_back:
                onBackPressed();
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
    private void enterShop() {
        adapter.setCanEnterShopClickListener((shopSid, name,saleType) -> {
            Intent intent;
            if ("自营商品".equals(saleType))
             intent=new Intent(getThisContext(),SelfShopActivity.class);
            else
                intent=new Intent(getThisContext(),MerchantShopActivity.class);
            intent.putExtra("ShopSid",shopSid);
            intent.putExtra("ShopName",name);
            startActivity(intent);
        });
        adapter.setAddCarClickListener(new GoodsListAdapter.AddCarListener() {
            @Override
            public void OnAddCarClick(String goodsId, String name) {
                addCar(goodsId);
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
                if (msg.getCode() == 0) {
                    ToastShowLong(getThisContext(), "加入购物车成功");
                    //     carNumber.setText(msg.getTotal() + "");
                } else
                    ToastShowLong(getThisContext(), msg.getMessage());
            }

            @Override
            public void failure(RetrofitError error) {
                dialogFragment.dismiss();

            }
        });
    }
//    public void getMyShopCar(){
//        NewShopApi api=ApiClientBulk.create(NewShopApi.class);
//        AddCarParam param=new AddCarParam();
//        param.setUserId(mUserHelperBulk.getSid());
//        System.out.println(param.getUserId() + "------");
//        api.getMyShopCar(param, new HttpCallback<MessageToBulk<List<MyShopCarTo>>>(getThisContext()) {
//            @Override
//            public void success(MessageToBulk<List<MyShopCarTo>> msg, Response response) {
//                if (msg.getCode() == 0) {
//                    int count = 0;
//                    if (msg.getCartMerchantGoodsGlobalVoList() != null) {
//                        for (MyShopCarTo myShopCarTo : msg.getCartMerchantGoodsGlobalVoList()) {
//                            if (myShopCarTo != null && myShopCarTo.getCartMerchantGoodsVolist() != null) {
//                                count = count + myShopCarTo.getCartMerchantGoodsVolist().size();
//                            }
//                        }
//                    }
//                    carNumber.setText(count + "");
//                }
//            }
//        });
//    }
    private void enterGoodsDetail() {
        adapter.setEnterGoodsDetailListener(new GoodsListAdapter.EnterGoodsDetailListener() {
            @Override
            public void OnEnterDetailClick(String goodsSid,boolean isActivity) {
                Intent intent = new Intent(getThisContext(), SideGoodsDetailActivity.class);
                intent.putExtra("GoodsSid", goodsSid);
                intent.putExtra("ActivityGoods",isActivity);
                startActivity(intent);
            }
        });
    }
    private void setSearch() {
        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                cleanSearch(charSequence.toString());
                if (currentSelect == 0) {
                    getSalesData("1", searchText.getText().toString());
                } else if (currentSelect == 1) {
                    searchByPrice(pagerIndex + "", charSequence.toString());
                } else if (currentSelect == 2) {
                    getNewGoods("1", searchText.getText().toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
    public void cleanSearch(String name){
        if (TextUtils.isEmpty(name))
        categoryList.clear();
        adapter.notifyDataSetChanged();
        return;
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
    public void setSearchVisible(int size){
        if (size==0)
            noSearch.setVisibility(View.VISIBLE);
        else
            noSearch.setVisibility(View.GONE);
    }
    public void popKeyBoard(EditText editText){
        Timer timer = new Timer();
        timer.schedule(new TimerTask()   {
            public void run() {
                InputMethodManager inputManager = (InputMethodManager)editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.showSoftInput(editText, 0);
            }
        }, 500);
    }
}
