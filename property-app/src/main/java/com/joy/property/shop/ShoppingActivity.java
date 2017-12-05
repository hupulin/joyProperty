package com.joy.property.shop;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.ToxicBakery.viewpager.transforms.ABaseTransformer;
import com.ToxicBakery.viewpager.transforms.ForegroundToBackgroundTransformer;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.infrastructure.MessageToBulk;
import com.jinyi.ihome.module.newshop.ActivityGoodsVoListTo;
import com.jinyi.ihome.module.newshop.ActivityListParam;
import com.jinyi.ihome.module.newshop.ActivityTimeTo;
import com.jinyi.ihome.module.newshop.ActivityTo;
import com.jinyi.ihome.module.newshop.BlankParam;
import com.jinyi.ihome.module.newshop.CategoryChannelTo;
import com.jinyi.ihome.module.newshop.CategoryTo;
import com.jinyi.ihome.module.newshop.ChannelParam;
import com.jinyi.ihome.module.newshop.MainInfoDettailTo;
import com.jinyi.ihome.module.newshop.MainInfoTo;
import com.joy.common.api.ApiClientBulk;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.NewShopApi;
import com.joy.library.utils.DateUtil;
import com.joy.property.MainApp;
import com.joy.property.MainApplication;
import com.joy.property.R;
import com.joy.property.base.BaseActivity;
import com.joy.property.shop.adapter.HotActivityAdapter;
import com.joy.property.shop.adapter.HotAdapter;
import com.joy.property.shop.shoputil.CarNumberUtil;
import com.joyhome.nacity.app.photo.util.PublicWay;
import com.joyhome.nacity.app.propertyCenter.NetworkImageHolderView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit.RetrofitError;
import retrofit.client.Response;
import rx.Observable;

/**
 * Created by xz on 2016/7/13.
 **/
public class ShoppingActivity extends BaseActivity implements OnItemClickListener, View.OnClickListener {
    private List<MainInfoDettailTo> list = new ArrayList<>();
    private ConvenientBanner autoRow;
    private List<CategoryChannelTo> shopList = new ArrayList<>();
    private ImageView selling;
    private ImageView sellingOne;
    private ImageView sellingThree;
    private ImageView searchGoods;
    private ListView listView;
    private ListView activityListView;
    private ScrollView scrollView;
    private List<CategoryTo> categoryList = new ArrayList<>();
    private HotAdapter adapter;
    private TextView carNumber;
    private LinearLayout menuLayout;
    private List<MainInfoDettailTo> shopAdList = new ArrayList<>();
    private ImageView hyMarket;
    private ImageView electricRepair;
    private ImageView activityImage;
    private TextView hour;
    private TextView minute;
    private TextView second;
    private long leaveTime;
    private TextView millSecond;
    private TextView titleName;
    private Thread threadTime;
    private RelativeLayout adLayout;
    private ActivityTimeTo activityTimeInfo=new ActivityTimeTo();
    private boolean onStop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_new);
        initView();
        getActivityData();
        getHyMarket();

        setAutoRow();
        getChannel();

        getType();
        getUserInfo();
        //  getAdPosition();
        // addMenu();
      PublicWay.activityList.add(this);
    }


    private void initView() {
        autoRow = (ConvenientBanner) findViewById(R.id.autoRow);
        activityListView = (ListView) findViewById(R.id.shopListView);
        activityListView.setDividerHeight(0);
        listView = (ListView) findViewById(R.id.listView);
        setListViewHeightBasedOnChildren(listView);
        listView.setDividerHeight(0);
        scrollView = (ScrollView) findViewById(R.id.scrollView);
        scrollView.post(() -> scrollView.scrollTo(0, 0));
        findViewById(R.id.shop_type).setOnClickListener(this);
        LinearLayout unListView = (LinearLayout) findViewById(R.id.un_listView);
        unListView.setFocusable(true);
        unListView.setFocusableInTouchMode(true);
        listView.requestFocus();
        findViewById(R.id.shoppingCar).setOnClickListener(this);
        //    findViewById(R.id.lookAllGoods).setOnClickListener(this);
        carNumber = (TextView) findViewById(R.id.carNumber);
        menuLayout = (LinearLayout) findViewById(R.id.menuLayout);
        findViewById(R.id.back).setOnClickListener(this);
        searchGoods= (ImageView)findViewById(R.id.searchGoods);
        searchGoods.setOnClickListener(this);
        activityImage = (ImageView) findViewById(R.id.iv_ad);
        hyMarket = (ImageView) findViewById(R.id.hyMarket);
        electricRepair = (ImageView) findViewById(R.id.electric_repair);
        hour = (TextView) findViewById(R.id.hour);
        minute = (TextView) findViewById(R.id.minute);
        second = (TextView) findViewById(R.id.second);
        millSecond = (TextView) findViewById(R.id.millSecond);
        titleName = (TextView) findViewById(R.id.title_name);
        adLayout = (RelativeLayout) findViewById(R.id.adLayout);
        adLayout.setOnClickListener(this);
    }

    private void setAutoRow() {

        NewShopApi api = ApiClientBulk.create(NewShopApi.class);
        api.getMainPageInfo("1", mUserHelperBulk.getUserInfoTo().getApartmentSid(), new HttpCallback<MessageTo<MainInfoTo>>(this) {
            @Override
            public void success(MessageTo<MainInfoTo> msg, Response response) {
                if (msg.getSuccess() == 0) {

                    if (msg.getData() != null && msg.getData().getList() != null && msg.getData().getList().size() > 0) {
                        list.clear();
                        list.addAll(msg.getData().getList());
                        getAdInfoSecond(msg.getData().getList());
                    } else {
                        autoRow.setVisibility(View.GONE);
                        searchGoods.setVisibility(View.GONE);
                    }
                }

            }

            @Override
            public void failure(RetrofitError error) {
                autoRow.setVisibility(View.GONE);
                searchGoods.setVisibility(View.GONE);

            }
        });
    }

    private void getAdInfoSecond(List<MainInfoDettailTo> mList) {
        String transforemerName = ForegroundToBackgroundTransformer.class.getSimpleName();
        try {
            Class cls = Class.forName("com.ToxicBakery.viewpager.transforms." + transforemerName);
            ABaseTransformer transforemer = (ABaseTransformer) cls.newInstance();
            autoRow.getViewPager().setPageTransformer(true, transforemer);
            if (transforemerName.equals("StackTransformer")) {
                autoRow.setScrollDuration(1200);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        //  initImageLoader();

        List<String> networkImages = new ArrayList<>();
        for (int i = 0; i < mList.size(); i++)
            networkImages.add(MainApp.getPicassoImagePath(mList.get(i).getPicUrl()));
        autoRow.setPages(NetworkImageHolderView::new, networkImages).setPageIndicator(new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused}).setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT).setOnItemClickListener(position -> {
            adJump(mList.get(position));
        });
        autoRow.startTurning(5000);

    }

    @Override
    public void onItemClick(int position) {

    }

    private void getChannel() {

//                    channelList.clear();
//              for(int i=0;i<3;i++){
//                  ShopTypeTo typeTo=new ShopTypeTo();
//                  typeTo.setTypeName("频道"+i);
//                  typeTo.setTypeSid("3333");
//                  typeTo.setTypeIcon(MainApp.getPicassoImagePath("shop_head_image.jpg"));
//                  channelList.add(typeTo);
//              }
//                    Picasso.with(getThisContext()).load(channelList.get(0).getTypeIcon()).into(selling);
//                    Picasso.with(getThisContext()).load(channelList.get(1).getTypeIcon()).into(sellingOne);
//                    Picasso.with(getThisContext()).load(channelList.get(2).getTypeIcon()).into(sellingThree);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                onBackPressed();
                break;
            case R.id.sellingLayout:
                Observable.from(shopAdList).filter(mainInfoDettailTo -> mainInfoDettailTo.getAdPosition() == 1).subscribe(this::adJump);

                break;
            case R.id.searchGoods:
                Intent intentSearch = new Intent(getThisContext(), SearchActivity.class);
                startActivity(intentSearch);
                goToAnimation(1);
                break;
            case R.id.selling_right_one:
                Observable.from(shopAdList).filter(mainInfoDettailTo -> mainInfoDettailTo.getAdPosition() == 2).subscribe(this::adJump);
                break;
//            case R.id.selling_right_tow:
//
//                break;
            case R.id.selling_right_three:
                Observable.from(shopAdList).filter(mainInfoDettailTo -> mainInfoDettailTo.getAdPosition() == 3).subscribe(this::adJump);
                break;
//            case R.id.selling_right_four:
//
//                break;
            case R.id.shop_type:
                showType();
                break;
            case R.id.shoppingCar:
                startActivity(new Intent(getThisContext(), ShoppingCarActivity.class));
                goToAnimation(1);

                break;
//            case R.id.lookAllGoods:
//                Intent intent = new Intent(getThisContext(), ActivityShopMore.class);
//                startActivity(intent);
//
//                goToAnimation(1);
//                break;
            case R.id.propertyBulk:
                Intent intent2 = new Intent(getThisContext(), ShopBulkActivity.class);
                startActivity(intent2);
                goToAnimation(1);
                break;

            case R.id.adLayout:
                Intent intent=new Intent(getThisContext(),CampaignListActivity.class);
                startActivity(intent);
                goToAnimation(1);
                break;
        }
    }

    public void getChannelList() {
        adapter = new HotAdapter(getThisContext());
        NewShopApi api = ApiClientBulk.create(NewShopApi.class);
        ChannelParam param = new ChannelParam();
        param.setCommunityId(mUserHelperBulk.getUserInfoTo().getApartmentSid());
        System.out.println(param + "param");

        api.getChannelGoodsList(param, new HttpCallback<MessageToBulk<List<CategoryChannelTo>>>(getThisContext()) {
            @Override
            public void success(MessageToBulk<List<CategoryChannelTo>> msg, Response response) {
                if (msg.getCode() == 0) {
                    Log.i("2222", "msg: "+msg.toString());

                    shopList.clear();
                    System.out.println(msg.getCommunityCategoryGoodsVoList()+"很长");
                    if (msg.getCommunityCategoryGoodsVoList() != null)
                        shopList.addAll(msg.getCommunityCategoryGoodsVoList());
                    //  setShopList();

                    adapter.setList(shopList);
                    listView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                    listView.setOnItemClickListener((adapterView, view, position, l) -> {
                        if (shopList.get(position).getGoodsApiVolist().size()==0)
                            return;
                        Intent intent = new Intent(getThisContext(), ShopMoreType.class);
                        CategoryTo categoryTo = new CategoryTo();
                        categoryTo.setName(shopList.get(position).getCategoryName());
                        categoryTo.setId(shopList.get(position).getCategoryId());
                        intent.putExtra("category", categoryTo);

                        startActivity(intent);
                        goToAnimation(1);
                    });

                    scrollView.post(() -> scrollView.scrollTo(0, 0));
                    setListViewHeightBasedOnChildren(listView);

                } else
                    ToastShowLong(getThisContext(), msg.getMessage());

            }

            @Override
            public void failure(RetrofitError error) {
                Log.i("2222", "failure: "+error.toString());
                System.out.println(error.toString() + "error");
            }
        });
    }


    public static void setListViewHeightBasedOnChildren(ListView listView) {
        if (listView == null) return;
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    public void showType() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.dialog_shop_type, null);

        GridLayout channelGorup = (GridLayout) contentView.findViewById(R.id.channel_group);
        for (CategoryTo shopListTo : categoryList) {
            View linearLayout = LinearLayout.inflate(getThisContext(), R.layout.shop_type_pup_item, null);
            TextView channelName = (TextView) linearLayout.findViewById(R.id.channel_name);
            channelName.setText(shopListTo.getName());
            linearLayout.setOnClickListener(v -> ToastShowLong(getThisContext(), shopListTo.getName()));
            WindowManager wm = (WindowManager) getThisContext().getSystemService(getThisContext().WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            int mWidth = display.getWidth();
            int marginLeft = (int) (mWidth * 0.0805);
            int marginBottom = (int) (mWidth * 0.0486);
            int width = (int) (mWidth * 0.2313);
            GridLayout.LayoutParams lp = new GridLayout.LayoutParams();
            lp.width = width;
            lp.height = (int) (mWidth * 0.0416);
            lp.setMargins(marginLeft, 0, 0, marginBottom);
            linearLayout.setLayoutParams(lp);
            linearLayout.setOnClickListener(v -> {
                Intent intent = new Intent(getThisContext(), ShopMoreType.class);
                intent.putExtra("category", shopListTo);

                startActivity(intent);
                goToAnimation(1);
            });
            channelGorup.addView(linearLayout);
        }

        View view = findViewById(R.id.layout_title);
        contentView.setFocusable(true);
        contentView.setFocusableInTouchMode(true);

        PopupWindow popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setFocusable(true);
        contentView.findViewById(R.id.pup_layout).setOnClickListener(v -> popupWindow.dismiss());
        popupWindow.setOutsideTouchable(true);
//
//        if (Build.VERSION.SDK_INT < 24)
//        {
//            popupWindow.showAsDropDown(null);
//        }
//        else
//        {
            // 适配 android 7.0
//            int[] location = new int[2];
//            view.getLocationInWindow(location);
//            int x=location[0];
//            int y=location[1];
//            Log.i("22222", "x"+x+"y"+y);
     //       popupWindow.showAsDropDown(view);

          popupWindow.showAtLocation(view, Gravity.NO_GRAVITY, 0,0);
//        }
    }

    private void getType() {
        NewShopApi api = ApiClientBulk.create(NewShopApi.class);
        BlankParam param = new BlankParam();
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

    public void getUserInfo() {
        if (mUserHelperBulk.getUserInfoTo() != null) {
            getMyShopCarNumber();

        }

    }

    public void getMyShopCarNumber() {
      CarNumberUtil.getCarNumber(mUserHelperBulk.getSid(),getThisContext(),carNumber);
    }

    private void setJumpActivity() {
        adapter.setJumpAcitivty(goodsSid -> {
            Intent intent = new Intent(getThisContext(), SideGoodsDetailActivity.class);
            intent.putExtra("GoodsSid", goodsSid);
            startActivity(intent);
            goToAnimation(1);

        });
    }







    public void adJump(MainInfoDettailTo info) {

        if (info != null && info.getForwardType() != 0) {
            if (info.getForwardType() == 1) {
                if (info.getInsideForwordType() != 0) {
                    Intent intent = null;
                    if (info.getInsideForwordType() == 1) {
                        intent = new Intent(getThisContext(), MerchantShopActivity.class);
                        intent.putExtra("ShopSid", info.getInsideForwordId() + "");
                        intent.putExtra("ShopName",info.getLayoutName());
                    } else if (info.getInsideForwordType() == 2) {
                        intent = new Intent(getThisContext(), ShopMoreType.class);
                        CategoryTo categoryTo = new CategoryTo();
                        categoryTo.setName(info.getLayoutName());
                        categoryTo.setId(info.getInsideForwordId() + "");
                        intent.putExtra("category", categoryTo);
                        intent.putExtra("isAdEnter",true);


                    } else if (info.getInsideForwordType() == 3) {
                        intent = new Intent(getThisContext(), SideGoodsDetailActivity.class);
                        intent.putExtra("GoodsSid", info.getInsideForwordId() + "");
                    } else if (info.getInsideForwordType() == 4) {
                        intent = new Intent(getThisContext(), OtherBulkActivity.class);
                        intent.putExtra("GroupId", info.getInsideForwordId() + "");
                        intent.putExtra("title", info.getLayoutName());
                    }
                    if (intent != null) {
                        startActivity(intent);
                        goToAnimation(1);
                    }
                }

            } else if (info.getForwardType() == 2) {
                Intent intent = new Intent(getThisContext(), ShopAdActivity.class);
                intent.putExtra("urlTitle", info.getLayoutName());
                intent.putExtra("shopUrl", info.getForwardUrl());
                startActivity(intent);
                goToAnimation(1);
            } else if (info.getForwardType() == 3) {
                Intent intent = new Intent(getThisContext(), PictureTextActivity.class);
                intent.putExtra("id", info.getId());
                intent.putExtra("title", info.getLayoutName());
                startActivity(intent);
                goToAnimation(1);
            }

        }
    }

    public void setShopList() {
        for (int i = 0; i < shopList.size(); i++) {
            if (i != 2) {
                for (int j = 0; j < 5; j++) {
                    shopList.get(i).getGoodsApiVolist().add(shopList.get(i).getGoodsApiVolist().get(0));
                }
            } else {
                for (int j = 0; j < 5; j++) {
                    shopList.get(i).getGoodsApiVolist().add(shopList.get(i).getGoodsApiVolist().get(1));
                }
            }
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (carNumber!=null)
            CarNumberUtil.getCarNumber(mUserHelperBulk.getSid(),getThisContext(),carNumber);
        onStop=false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        autoRow.stopTurning();
         PublicWay.activityList.remove(this);
        new Handler().removeCallbacks(threadTime);

    }
    private void getActivityData(){
        NewShopApi api=ApiClientBulk.create(NewShopApi.class);
        ActivityListParam param=new ActivityListParam();
        param.setCommunityId(mUserHelperBulk.getUserInfoTo().getApartmentSid());

        api.getActivityList(param, new HttpCallback<MessageToBulk<List<ActivityTo>>>(getThisContext()) {
            @Override
            public void success(MessageToBulk<List<ActivityTo>> msg, Response response) {
                getChannelList();
                setJumpActivity();
                if (msg.getCode() == 0) {
                    adLayout.setVisibility(View.VISIBLE);
                    if (msg.getActivityList() != null && msg.getActivityList().size() > 0) {
                        setActivityTitle(msg.getActivityList().get(0).getActivityVo());
                        setActivityView(msg.getActivityList().get(0).getActivityVo().getActivityGoodsVoList());

                        Log.i("222", "success: "+msg.getActivityList().get(0).getActivityVo().toString());
                        Log.i("222", "success: "+msg.getActivityList().get(0).getActivityVo().getActivityGoodsVoList().toString());
                    } else
                        adLayout.setVisibility(View.GONE);
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
    public void setActivityView(List<ActivityGoodsVoListTo> activityList){
        HotActivityAdapter adapter=new HotActivityAdapter(getThisContext(),false);
        adapter.setList(activityList);
        activityListView.setAdapter(adapter);
        activityListView.setDividerHeight(0);

        adapter.notifyDataSetChanged();
        activityListView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(getThisContext(), SideGoodsDetailActivity.class);
            intent.putExtra("GoodsSid", activityList.get(position).getActivityGoodsId());
            intent.putExtra("ActivityGoods", true);
            intent.putExtra("ActivityTimeInfo" ,activityTimeInfo);
            startActivity(intent);
            goToAnimation(1);


        });
    }
    public void setActivityTitle(ActivityTo.ActivityVoTo activityTo){
        activityTimeInfo.setActivityEndTime(activityTo.getActivityEndTime());
        activityTimeInfo.setActivityStartTime(activityTo.getActivityStartTime());
        activityTimeInfo.setWarmupStartTime(activityTo.getWarmupStartTime());
        activityTimeInfo.setActivityId(activityTo.getActivityId());

        titleName.setText(activityTo.getActivityName());

        if (DateUtil.cureentMillis()<DateUtil.DateMillis(activityTo.getWarmupStartTime(), DateUtil.mDateTimeFormatString)||DateUtil.cureentMillis()>DateUtil.DateMillis(activityTo.getActivityEndTime(), DateUtil.mDateTimeFormatString)){
            adLayout.setVisibility(View.GONE);
            activityListView.setVisibility(View.GONE);
        }else {
            adLayout.setVisibility(View.VISIBLE);
            if (DateUtil.cureentMillis()>DateUtil.DateMillis(activityTo.getWarmupStartTime(),DateUtil.mDateTimeFormatString)&&DateUtil.cureentMillis()<DateUtil.DateMillis(activityTo.getActivityStartTime(),DateUtil.mDateTimeFormatString)){
                countTime(DateUtil.DateMillis(activityTo.getActivityStartTime(),DateUtil.mDateTimeFormatString),DateUtil.DateMillis(activityTo.getActivityEndTime(),DateUtil.mDateTimeFormatString));
            }else if (DateUtil.cureentMillis()>DateUtil.DateMillis(activityTo.getActivityStartTime(),DateUtil.mDateTimeFormatString)&&DateUtil.cureentMillis()<DateUtil.DateMillis(activityTo.getActivityEndTime(),DateUtil.mDateTimeFormatString)){
                countTime(DateUtil.DateMillis(activityTo.getActivityEndTime(),DateUtil.mDateTimeFormatString),0);
            }
        }
        Glide.with(MainApplication.mContext).load(MainApp.getPicassoImagePath(activityTo.getActivityUrl())).into(activityImage);

    }

    @Override
    protected void onStop() {
        super.onStop();
        onStop=true;
    }

    public void countTime(long startTime,long endTime ){

        leaveTime=startTime-DateUtil.cureentMillis();
        leaveTime=leaveTime*1000;
        threadTime = new Thread(() -> {
            for (;leaveTime>0&&!onStop;) {

                leaveTime = leaveTime - 100;
                System.out.println("-------------线程------------" + leaveTime);

                runOnUiThread(() -> {
                    hour.setText(leaveTime/1000/3600+"");
                    minute.setText((leaveTime/1000/60-leaveTime/1000/3600*60)+"");
                    second.setText((leaveTime / 1000 - leaveTime / 1000 / 60 * 60) + "");


                    millSecond.setText(leaveTime / 100 % 10 + "");

                });
                SystemClock.sleep(100);
                if (leaveTime<=0) {
                    if (endTime != 0) {
                        leaveTime = endTime - DateUtil.cureentMillis();
                        leaveTime = leaveTime * 1000;
                        for (; leaveTime > 0&&!onStop; ) {


                            leaveTime = leaveTime - 100;
                            System.out.println("-------------线程------------" + leaveTime);

                            runOnUiThread(() -> {
                                hour.setText(leaveTime / 1000 / 3600 + "");
                                minute.setText((leaveTime / 1000 / 60 - leaveTime / 1000 / 3600 * 60) + "");
                                second.setText((leaveTime / 1000 - leaveTime / 1000 / 60 * 60) + "");


                                millSecond.setText(leaveTime / 100 % 10 + "");


                            });
                            SystemClock.sleep(100);

                        }
                    }else {
                        runOnUiThread(() -> {
                            adLayout.setVisibility(View.GONE);
                            activityListView.setVisibility(View.GONE);
                        });
                    }
                }

            }
        });
        threadTime.start();
    }


    /**
     * 花样菜场
     */
    public void getHyMarket(){
        System.out.println(mUserHelperBulk.getUserInfoTo().getApartmentSid() + "userSid");
        NewShopApi api = ApiClientBulk.create(NewShopApi.class);
        api.getMainPageInfo("4","2.0", mUserHelperBulk.getUserInfoTo().getApartmentSid(), new HttpCallback<MessageTo<MainInfoTo>>(this) {
            @Override
            public void success(MessageTo<MainInfoTo> msg, Response response) {

                if (msg.getSuccess() == 0) {

                    if (msg.getData() != null && msg.getData().getList() != null && msg.getData().getList().size() > 0) {
                        MainInfoDettailTo firstDetail=msg.getData().getList().get(0);
                        if (msg.getData().getList().size()>1){

                            setHyMarket(101==firstDetail.getInsideForwordId()?msg.getData().getList().get(1):msg.getData().getList().get(0));
                            setElectricRepair(101==firstDetail.getInsideForwordId()?msg.getData().getList().get(0):msg.getData().getList().get(1));
                        }else {
                            if (101==firstDetail.getInsideForwordId())
                                setElectricRepair(firstDetail);
                            else
                                setHyMarket(firstDetail);
                        }
//
                    }
                }

            }

            @Override
            public void failure(RetrofitError error) {
                autoRow.setVisibility(View.GONE);
                searchGoods.setVisibility(View.GONE);

            }
        });

    }
    public void setHyMarket(MainInfoDettailTo detailTo){
        hyMarket.setVisibility(View.VISIBLE);
        Picasso.with(getThisContext()).load(MainApp.getPicassoImagePath(detailTo.getPicUrl())).into(hyMarket);

        hyMarket.setOnClickListener(v -> {
            Intent intent = new Intent(getThisContext(), HyCaiChangActivity.class);
            intent.putExtra("thirdPartyAccessId", detailTo.getInsideForwordId() + "");
            intent.putExtra("LayoutTitle",detailTo.getLayoutName());
            startActivity(intent);
            goToAnimation(1);
        });
    }
    public void setElectricRepair(MainInfoDettailTo detailTo ) {

        electricRepair.setVisibility(View.VISIBLE);

        Picasso.with(getThisContext()).load(MainApp.getPicassoImagePath(detailTo.getPicUrl())).into(electricRepair);

        electricRepair.setOnClickListener(v -> {
            Intent intent = new Intent(getThisContext(), HyCaiChangActivity.class);
            intent.putExtra("thirdPartyAccessId", detailTo.getInsideForwordId() + "");
            intent.putExtra("LayoutTitle",detailTo.getLayoutName());
            startActivity(intent);
            goToAnimation(1);
        });
    }

}
