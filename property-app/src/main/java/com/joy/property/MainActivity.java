package com.joy.property;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.ToxicBakery.viewpager.transforms.ABaseTransformer;
import com.ToxicBakery.viewpager.transforms.RotateUpTransformer;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.igexin.sdk.PushManager;
import com.jauker.widget.BadgeView;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.common.AdInfoTo;
import com.jinyi.ihome.module.common.DeviceParam;
import com.jinyi.ihome.module.common.DeviceTo;
import com.jinyi.ihome.module.home.MessageParam;
import com.jinyi.ihome.module.home.ServiceMainExpandTo;
import com.jinyi.ihome.module.home.ServiceParam;
import com.jinyi.ihome.module.home.ServiceRedPointTo;
import com.jinyi.ihome.module.home.ServiceTaskParam;
import com.jinyi.ihome.module.neighbor.NeighborMessageParam;
import com.jinyi.ihome.module.neighbor.NeighborReportNewPostTo;
import com.jinyi.ihome.module.newshop.BulkUserInfoParam;
import com.jinyi.ihome.module.newshop.UserMessage;
import com.jinyi.ihome.module.owner.ParkUserInfoParam;
import com.jinyi.ihome.module.owner.UserInfoTo;
import com.jinyi.ihome.module.system.GroupMenuParam;
import com.jinyi.ihome.module.system.GroupMenuTo;
import com.joy.common.api.ApiClient;
import com.joy.common.api.ApiClientBulk;
import com.joy.common.api.ApiClientPark;
import com.joy.common.api.CommonApi;
import com.joy.common.api.HomeApi;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.NewShopApi;
import com.joy.common.api.SystemApi;
import com.joy.common.api.UserApi;
import com.joy.common.application.KeyValue;
import com.joy.common.helper.ApartmentInfoHelper;
import com.joy.common.helper.UserInfoBulkHelper;
import com.joy.common.helper.UserInfoHelper;
import com.joy.library.fragment.CustomDialogFragment;
import com.joy.library.utils.ConfigUtil;
import com.joy.library.utils.DateUtil;
import com.joy.property.adapter.GroupMenuAdapter;
import com.joy.property.asyntask.MainTask;
import com.joy.property.asyntask.TaskParams;
import com.joy.property.community.CommunityBulletinActivity;
import com.joy.property.community.NoticeDetailActivity;
import com.joy.property.complaint.ComplaintManagerActivity;
import com.joy.property.host.OwnerMouthActivity;
import com.joy.property.house.HouseManagerActivity;
import com.joy.property.inspection.VillageInspectionActivity;
import com.joy.property.manage.InspectionManage;
import com.joy.property.message.MessageCenterActivity;
import com.joy.property.myservice.MyServiceOrderActivity;
import com.joy.property.neighborhood.CampaignActivity;
import com.joy.property.neighborhood.FansActivity;
import com.joy.property.neighborhood.InvestigateActivity;
import com.joy.property.neighborhood.Neighbor;
import com.joy.property.neighborhood.RefreshEvent;
import com.joy.property.neighborhood.TopicActivity;
import com.joy.property.reaction.ReactionRateActivity;
import com.joy.property.receiver.ForceOfflineActivity;
import com.joy.property.receiver.PushMessageReceiver;
import com.joy.property.repairs.RepairsManagementActivity;
import com.joy.property.repairs.RoomRepairsManagementActivity;
import com.joy.property.shop.CampaignListActivity;
import com.joy.property.shop.MyCouponActivity;
import com.joy.property.shop.MyShoppingActivity;
import com.joy.property.shop.ShoppingActivity;
import com.joy.property.shop.SideGoodsDetailActivity;
import com.joy.property.task.LeaveMessageActivity;
import com.joy.property.task.ReceiveTaskDetailActivity;
import com.joy.property.task.TaskHallActivity;
import com.joy.property.utils.ChangeParkUtil;
import com.joy.property.utils.CustomDialog;
import com.joy.property.utils.NoScrollGridView;
import com.joy.property.utils.SpUtil;
import com.joy.property.utils.StatisticsUtil;
import com.joy.property.vehicle.VehicleInspectionActivity;
import com.joy.property.vehicle.VehicleInspectionManage;
import com.joy.property.vehicle.VehicleManagementActivity;
import com.joy.property.views.MyScrollViewMain;
import com.joy.property.visit.ReceiveExpressActivity;
import com.joy.property.visit.SerialNumberActivity;
import com.joy.property.visit.view.CaptureActivity;
import com.joy.property.worksign.WorkSignActivity;
import com.joyhome.nacity.app.photo.util.PublicWay;
import com.joyhome.nacity.app.propertyCenter.NetworkImageHolderView;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import cn.jpush.android.api.JPushInterface;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends SlidingFragmentActivity implements
        OnClickListener, OnTouchListener {
    private TextView mTip;
    private TextView mTitle;
    private TextView taskPoint;
    private TextView neighborPoint;
    private TextView messagePoint;
    private String client = "";
    private ApartmentInfoHelper mHelper;
    private UserInfoHelper mUserHelper;
    private SharedPreferences sp;
    private NoScrollGridView mNoScrollGridView;
    private List<GroupMenuTo> menuToList = new ArrayList<>();
    private GroupMenuAdapter mAdapter = null;
    private WebView web;
    private MyReceiver receiver;
    private ConvenientBanner lbt;
    private List<AdInfoTo> listTop = new ArrayList<>();
    private List<AdInfoTo> list = new ArrayList<>();
    private ServiceMainExpandTo mainExpandTo;
    private StatisticsUtil star;
    private boolean isEnd = false;
    private RelativeLayout taskhallnew_top, neighbornew_top;
    private BadgeView badgeView, badgeView2, badgeView3;
    private List<NeighborReportNewPostTo> neighborCount;
    private int messageCenterCount;
    private MainApplication application;
    private Date lastChance, lastOpportunity, lastOdds, lastOccasion, firstCome;
    private boolean isCompetence = false;
    private ServiceParam serviceParam;
    private Thread messageThread;
    private Date date;
    private boolean firstChange;
    private ImageView changePark;
    protected UserInfoBulkHelper mUserHelperBulk;
    private boolean firstChangeInspect;
    private ConvenientBanner autoRow;
    private RelativeLayout parent;
    private boolean isFirstRegisterEventBus=true;
    private RelativeLayout bottomView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        application = (MainApplication) this.getApplicationContext();
        PushManager.getInstance().initialize(this.getApplicationContext());
        client = PushManager.getInstance().getClientid(this);
        sp = PreferenceManager.getDefaultSharedPreferences(getThisContext());
        mHelper = ApartmentInfoHelper.getInstance(getThisContext());
        mUserHelper = UserInfoHelper.getInstance(getThisContext());
        mUserHelper = UserInfoHelper.getInstance(this.getApplication());
        mUserHelperBulk=UserInfoBulkHelper.getInstance(this.getApplication());
        isOffLian();
        getParkLimit();
        findById();
        firstCome = DateUtil.getDate();
        ApiClient.setHomeUrl();
        mUserHelper= UserInfoHelper.getInstance(getThisContext());
        mAdapter = new GroupMenuAdapter(getThisContext());
        mAdapter.setList(menuToList);
        mNoScrollGridView.setAdapter(mAdapter);
        getUserInfo();
        message();
        initDatas();
        setMode();
        initSlideMenu();
        updateApk();
        star = new StatisticsUtil();
        // star.sendStatistics("1", getThisContext().toString());
        star.sendStatistics(mUserHelper.getSid(), "进入主页", getThisContext());
        MobclickAgent.setDebugMode(true);
        MobclickAgent.openActivityDurationTrack(false);
        // MobclickAgent.updateOnlineConfig(this);
        receiver = new MyReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("update");
        registerReceiver(receiver, filter);

        if (!SpUtil.getBoolean(getThisContext(), "FIRSTLOADPROPETY")) {
            getAdInfoTop();
            getAdInfo();

        } else {
            getAdInfoSecond(list);
            setTopAutoRow(listTop);
            getAdInfoTop();
            getAdInfo();

        }


        getNewMessage();
        com.Util.SpUtil.put(getThisContext(), "IsSplashActivity", false);
        registerEventBus();
        ActivityCollector.mMainActivity=this;
    }

    private void isOffLian() {

        if (com.Util.SpUtil.getBoolean(getThisContext(),"IsOffLine")){
            if (PublicWay.forceOfflineActivity==null) {
                Intent intent1 = new Intent(getThisContext(), ForceOfflineActivity.class);
                intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent1);

//                 goToAnimation(2);
            }
        }
    }
    private void getNewMessage() {
//        messageThread = new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//
//                while (!isEnd) {

                    noticeMessageCenter();
                    noticeNeighbor();
                    noticeTaskHall();

//                    if (isCompetence == false) {
//                        setCompetence();
//                    }
//                    SystemClock.sleep(5000);
//                }
//            }
//        });
//        messageThread.start();
    }


    ///////////////////////////////////////////////
    //    StatisticsUtil.sendStatistics(mUserHelper.getSid(),getResources().getString(R.string.enter_main));
    private void Bannal() {
        //web = (WebView) findViewById(R.id.image_tag);
        WebSettings webSettings = web.getSettings();
        webSettings.setSavePassword(false);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSettings.setSaveFormData(false);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(false);
        web.setWebChromeClient(new MyWebChromeClient());
        String url = "file:///android_asset/index.html";
        web.loadUrl(url);
//        gestureDetector = new GestureDetector(this, new MyOnGestureListener());
//        web.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                gestureDetector.onTouchEvent(event);
//                return true;
//            }
//        });
    }

    private MyScrollViewMain myScrollView;
    private void findById() {
        RelativeLayout mHomeMore = (RelativeLayout) findViewById(R.id.home_more);
        mHomeMore.setOnClickListener(this);
        TextView mHomeNews = (TextView) findViewById(R.id.home_news);
        mHomeNews.setOnClickListener(this);
        bottomView = (RelativeLayout) findViewById(R.id.view);

        parent = (RelativeLayout) findViewById(R.id.parent);
        findViewById(R.id.visitnew_top).setOnClickListener(this);
        findViewById(R.id.villagenew_top).setOnClickListener(this);
        myScrollView = (MyScrollViewMain) findViewById(R.id.my_scrollview);
        myScrollView.smoothScrollTo(0,20);
        myScrollView.setOnScrollListener(new MyScrollViewMain.OnScrollListener() {
            @Override
            public void onScroll(int scrollY) {
                Log.i("22222", "onScroll滑动中"+scrollY);
            }
        });
        taskhallnew_top = (RelativeLayout) findViewById(R.id.taskhallnew_top);
        taskPoint = (TextView) findViewById(R.id.task_tv);
        neighborPoint = (TextView) findViewById(R.id.neighbor_tv);
        messagePoint = (TextView) findViewById(R.id.message_tv);
        taskhallnew_top.setOnClickListener(this);

        neighbornew_top = (RelativeLayout) findViewById(R.id.neighbornew_top);
        neighbornew_top.setOnClickListener(this);
//
//        badgeView = new BadgeView(this);
//        badgeView.setTargetView(taskPoint);
//        badgeView.setBadgeGravity(Gravity.TOP | Gravity.RIGHT);
//        badgeView.setBackground(12, Color.RED);

//        badgeView2 = new BadgeView(this);
//        badgeView2.setTargetView(neighborPoint);
//        badgeView2.setBadgeGravity(Gravity.TOP | Gravity.RIGHT);
//        badgeView2.setBackground(20, Color.RED);
//        badgeView2.setTextColor(Color.RED);
//        badgeView3 = new BadgeView(this);
//        badgeView3.setTargetView(mHomeNews);
//        badgeView3.setBadgeGravity(Gravity.TOP | Gravity.RIGHT);
//        badgeView3.setBackground(20, Color.RED);
         autoRow = (ConvenientBanner) findViewById(R.id.autoRow);
         lbt = (ConvenientBanner) findViewById(R.id.lbt);
         autoRow.startTurning(5000);
       lbt.startTurning(3000);
        mTip = (TextView) findViewById(R.id.news_tip);
        mTip.setVisibility(View.INVISIBLE);
        mTitle = (TextView) findViewById(R.id.title);
        mNoScrollGridView = (NoScrollGridView) findViewById(R.id.gridView);
        mNoScrollGridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        mNoScrollGridView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                return event.getAction() == MotionEvent.ACTION_MOVE;
            }
        });
    }


    //顶部轮播图拿数据
    private void getAdInfoTop() {

        CommonApi api = ApiClient.create(CommonApi.class);

        api.findAdListInfoByApartmentSid("a241b63a-df3c-43a8-a7fd-477e5f950d9f", 13, new HttpCallback<MessageTo<List<AdInfoTo>>>(this) {
            @Override
            public void success(MessageTo<List<AdInfoTo>> msg, Response response) {
                if (msg.getSuccess() == 0) {
                    listTop.clear();
                    findViewById(R.id.ad_load).setVisibility(View.VISIBLE);

                    if (msg.getData() != null && msg.getData().size() > 0) {
                        findViewById(R.id.ad_load).setVisibility(View.GONE);
                        listTop.addAll(msg.getData());
                        if (!listTop.toString().equals(SpUtil.getString(getThisContext(), "Propetycache"))) {

                            Log.i("222", "listTop: "+listTop.toString());

                        }
                    }
                    setTopAutoRow(listTop);

                }
            }

            @Override
            public void failure(RetrofitError error) {
                super.failure(error);
            }
        });
    }
    //中部轮播图
    private void getAdInfo() {

        CommonApi api = ApiClient.create(CommonApi.class);

        api.findAdListInfoByApartmentSid("a241b63a-df3c-43a8-a7fd-477e5f950d9f", 4, new HttpCallback<MessageTo<List<AdInfoTo>>>(this) {
            @Override
            public void success(MessageTo<List<AdInfoTo>> msg, Response response) {
                if (msg.getSuccess() == 0) {
                    list.clear();
                    findViewById(R.id.lbt_load).setVisibility(View.VISIBLE);

                    if (msg.getData() != null && msg.getData().size() > 0) {
                        findViewById(R.id.lbt_load).setVisibility(View.GONE);

                        list.addAll(msg.getData());
                        if (!list.toString().equals(SpUtil.getString(getThisContext(), "Propetycache"))) {
                            Log.i("222", "list: "+list.toString());


                        }
                    }
                    getAdInfoSecond(list);

                }
            }

            @Override
            public void failure(RetrofitError error) {
                super.failure(error);
            }
        });
    }
    //中部轮播图
    private void getAdInfoSecond(List<AdInfoTo> mList) {
        String transformerName = RotateUpTransformer.class.getSimpleName();
        try {
            Class cls = Class.forName("com.ToxicBakery.viewpager.transforms." + transformerName);
            ABaseTransformer transformer = (ABaseTransformer) cls.newInstance();
            lbt.getViewPager().setPageTransformer(true, transformer);
            //部分3D特效需要调整滑动速度
            if (transformerName.equals("StackTransformer")) {
                lbt.setScrollDuration(1200);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        List<String> networkImages = new ArrayList<String>();
        for (int i = 0; i < mList.size(); i++)
            networkImages.add(com.joyhome.nacity.app.MainApp.getImagePath(mList.get(i).getAdImage()));

        lbt.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
            @Override
            public NetworkImageHolderView createHolder() {
                return new NetworkImageHolderView();
            }

        }, networkImages).setPageIndicator(new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused});
//        if (mList.size() < 2) {
//            lbt.setCanLoop(false);
//
//        }
         lbt.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                StatisticsUtil.sendStatistics(mUserHelper.getSid(), "点击底部图片", getThisContext());
                if(!"".equals(mList.get(position).getAdContent())||!"".equals(mList.get(position).getAdUrl())){
                    Intent intent = new Intent(getThisContext(), AdvertisingActivity.class);
                    intent.putExtra("AdInfoTo", mList.get(position));
                    getThisContext().startActivity(intent);
                    Log.i("222", "AdInfoTo"+mList.get(position).toString());
                }
            }
        });
    }
    //top轮播图滑动
    private void setTopAutoRow(List<AdInfoTo> mList) {
        String transformerName = RotateUpTransformer.class.getSimpleName();
        try {
            Class cls = Class.forName("com.ToxicBakery.viewpager.transforms." + transformerName);
            ABaseTransformer transformer = (ABaseTransformer) cls.newInstance();
            autoRow.getViewPager().setPageTransformer(true, transformer);
            //部分3D特效需要调整滑动速度
            if (transformerName.equals("StackTransformer")) {
                autoRow.setScrollDuration(1200);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        List<String> networkImages = new ArrayList<String>();
        for (int i = 0; i < mList.size(); i++)
            networkImages.add(com.joyhome.nacity.app.MainApp.getImagePath(mList.get(i).getAdImage()));

        autoRow.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
            @Override
            public NetworkImageHolderView createHolder() {
                return new NetworkImageHolderView();
            }

        }, networkImages).setPageIndicator(new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused});
//        if (mList.size() < 2) {
//            autoRow.setCanLoop(false);
//
//        }
        autoRow.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if(!"".equals(mList.get(position).getAdContent())||!"".equals(mList.get(position).getAdUrl())){
                Intent intent = new Intent(getThisContext(), AdvertisingActivity.class);
                intent.putExtra("AdInfoTo", mList.get(position));
                Log.i("222", "AdInfoTo"+mList.get(position).toString());

                getThisContext().startActivity(intent);
            }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //停止翻滚
        lbt.stopTurning();
        autoRow.stopTurning();
        unregisterReceiver(receiver);


        new Handler().removeCallbacks(messageThread);
       EventBus.getDefault().unregister(this);
    }

    private void initDatas() {

        UserInfoHelper mUserHelper = UserInfoHelper.getInstance(this);
        if ("kong".equals(SpUtil.getString(getThisContext(),"HomeInfo"))){
            SpUtil.put(getThisContext(),"HomeInfo",ConfigUtil.getString(sp, KeyValue.KEY_USER_INFO, ""));
        }
            UserInfoTo userInfoTo = JSON.parseObject(SpUtil.getString(getThisContext(),"HomeInfo"), UserInfoTo.class);
            mUserHelper.updateUser(userInfoTo,getThisContext());
            mUserHelper.setUserInfoTo(userInfoTo);

        if (mUserHelper.getUserInfoTo().getGroupTo() != null) {
            //  mTitle.setText(mUserHelper.getUserInfoTo().getGroupTo().getGroupName());
            // mTitle.setText(mUserHelper.getUserInfoTo().getGroupTo().getGroupName() + "  " + mUserHelper.getUserInfoTo().getName());
            mTitle.setText(mUserHelper.getUserInfoTo().getName() + "  " + mUserHelper.getUserInfoTo().getTag());


        }
//        if (!TextUtils.isEmpty(client)) {
//            DeviceParam param = new DeviceParam();
//            CommonApi api = ApiClient.create(CommonApi.class);
//            param.setDeviceToken(client);
//            param.setDeviceApp(11);
//            param.setOwnerSid(mUserHelper.getSid());
//            param.setDeviceType("android");
//            param.setType(1);
//            api.updateOwnerDeviceInfo(param, new HttpCallback<MessageTo<DeviceTo>>(this) {
//                @Override
//                public void success(MessageTo<DeviceTo> msg, Response response) {
//                    if (msg.getSuccess() == 0) {
//
//                    } else {
//                        //     Toast.makeText(getThisContext(), msg.getMessage(), Toast.LENGTH_LONG).show();
//                    }
//                }
//
//                @Override
//                public void failure(RetrofitError error) {
//                    super.failure(error);
//                }
//            });
//
//        }
    }


    private void updateApk() {
        TaskParams params = new TaskParams();
        MainTask.UpdateTask dbTask = new MainTask.UpdateTask(this, false,this);
        dbTask.execute(params);
    }


    private void setMode() {

        SystemApi api = ApiClient.create(SystemApi.class);
        final GroupMenuParam menuParam = new GroupMenuParam();
        menuParam.setUserSid(mUserHelper.getSid());
        final CustomDialogFragment dialogFragment = new CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "");
        api.findByTwiceUser(menuParam, new HttpCallback<MessageTo<List<GroupMenuTo>>>(getThisContext()) {
            @Override
            public void success(MessageTo<List<GroupMenuTo>> msg, Response response) {
                dialogFragment.dismissAllowingStateLoss();
                if (msg == null) return;
                if (msg.getSuccess() == 0) {
                    if (msg.getData() != null && msg.getData().size() > 0) {
                        menuToList.clear();
                        List<GroupMenuTo> list = msg.getData();
                        Iterator<GroupMenuTo> gmt_it = list.iterator();
                        while (gmt_it.hasNext()) {
                            GroupMenuTo groupMenuTo = gmt_it.next();
                            if (TextUtils.equals(groupMenuTo.getCode(), "A002") || TextUtils.equals(groupMenuTo.getCode(), "A003") || TextUtils.equals(groupMenuTo.getCode(), "A001") || TextUtils.equals(groupMenuTo.getCode(), "A009")) {
                                gmt_it.remove();
                            }
                        }

                        Log.i("2222", "success: "+list.size()+menuToList.toString());
                        if(list.size()!=12){
                            if (list != null || list.size() != 0) {
                                GroupMenuTo groupMenuTo = new GroupMenuTo();
                                groupMenuTo.setName("敬请期待");
                                groupMenuTo.setCode("A" + 0);
                                list.add(groupMenuTo);
                            }

                        }
                        if(list.size()>12){
                            bottomView.setVisibility(View.GONE );

                        }else{
                            myScrollView.setOnScrollUpListener(new MyScrollViewMain.OnScrollUpListener() {
                                @Override
                                public void onScrollUp() {
                                    Log.i("22222", "onScrollup: 抬起了");
                                    myScrollView.fullScroll(ScrollView.FOCUS_UP);
                                }
                            });
                            bottomView.setVisibility(View.VISIBLE);

                        }
                        menuToList.addAll(list);
                        mAdapter.notifyDataSetChanged();

                    }

                    mNoScrollGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            GroupMenuTo menuTo = menuToList.get(position);

                            if (TextUtils.equals(menuTo.getCode(), "A001")) {
                                //任务大厅
                                startActivity(new Intent(getThisContext(), TaskHallActivity.class));

                            } else if (TextUtils.equals(menuTo.getCode(), "A002")) {
                                //验证访客
                                visitShowDialog();

                            } else if (TextUtils.equals(menuTo.getCode(), "A003")) {
                                //小区巡检
                                startActivity(new Intent(getThisContext(), VillageInspectionActivity.class));

                            } else if (TextUtils.equals(menuTo.getCode(), "A004")) {
                                //业主口碑
                                StatisticsUtil.sendStatistics(mUserHelper.getSid(), "满意度", getThisContext());

                                startActivity(new Intent(getThisContext(), OwnerMouthActivity.class));

                            } else if (TextUtils.equals(menuTo.getCode(), "A005")) {
                                //投诉管理
                                StatisticsUtil.sendStatistics(mUserHelper.getSid(), "投诉管理", getThisContext());

                                startActivity(new Intent(getThisContext(), ComplaintManagerActivity.class));

                            } else if (TextUtils.equals(menuTo.getCode(), "A006")) {
                                //公共维修
                                StatisticsUtil.sendStatistics(mUserHelper.getSid(), "公共维修", getThisContext());
                                startActivity(new Intent(getThisContext(), RepairsManagementActivity.class));

                            } else if (TextUtils.equals(menuTo.getCode(), "A007")) {
                                //车辆管理
                                vehicleinspectionShowDialog();
                            } else if (TextUtils.equals(menuTo.getCode(), "A008")) {
                                //入室维修
                                startActivity(new Intent(getThisContext(), RoomRepairsManagementActivity.class));
                                StatisticsUtil.sendStatistics(mUserHelper.getSid(), "入室维修", getThisContext());
                            } else if (TextUtils.equals(menuTo.getCode(), "A009")) {
                                //Neighbor邻居圈
                                startActivity(new Intent(getThisContext(), Neighbor.class));
                                finish();
                            } else if (TextUtils.equals(menuTo.getCode(), "A010")) {
                                //悦购
                                getUserInfoClick();

                            } else if (TextUtils.equals(menuTo.getCode(), "A011")) {
                                //家政管理
                                startActivity(new Intent(getThisContext(), HouseManagerActivity.class));
                                StatisticsUtil.sendStatistics(mUserHelper.getSid(), "家政管理", getThisContext());
                            } else if (TextUtils.equals(menuTo.getCode(), "A012")) {
                                //通知
                                startActivity(new Intent(getThisContext(), CommunityBulletinActivity.class));
                                StatisticsUtil.sendStatistics(mUserHelper.getSid(), "最新通知", getThisContext());
                            } else if (TextUtils.equals(menuTo.getCode(), "A013")) {
                                //响应速度
                                StatisticsUtil.sendStatistics(mUserHelper.getSid(), "响应速度", getThisContext());
                                startActivity(new Intent(getThisContext(), ReactionRateActivity.class));
                            } else if (TextUtils.equals(menuTo.getCode(), "A014")) {
                                //巡检管理
                                StatisticsUtil.sendStatistics(mUserHelper.getSid(), "巡检管理", getThisContext());
                                startActivity(new Intent(getThisContext(), InspectionManage.class));
                            }
                            else if (TextUtils.equals(menuTo.getCode(), "A015")) {
                                //车辆巡检管理
                                startActivity(new Intent(getThisContext(), VehicleInspectionManage.class));
                            }else if (TextUtils.equals(menuTo.getCode(), "A016")) {
                                //车辆巡检管理
                                startActivity(new Intent(getThisContext(), ReceiveExpressActivity.class));

                            }else if (TextUtils.equals(menuTo.getCode(), "A017")) {
                                //巡更
                                startActivity(new Intent(getThisContext(), WorkSignActivity.class));

                            }
                            else if (TextUtils.equals(menuTo.getCode(), "A0")) {
                                //敬请期待
                               Toast.makeText(getThisContext(), "敬请期待", Toast.LENGTH_LONG).show();

                            }
                        }
                    });
                } else {
                    //   Toast.makeText(getThisContext(), msg.getMessage(), Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void failure(RetrofitError error) {
                dialogFragment.dismissAllowingStateLoss();
                super.failure(error);
            }
        });

    }


    @SuppressWarnings("deprecation")
    private void message() {
        com.Util.SpUtil.put(getThisContext(), "CurrentType", -1);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        String data = ConfigUtil.getString(sp, MainApplication.KeyValue.KEY_HOME_DATA, "");
        Log.i("99999", "data: "+data);
        if (!TextUtils.isEmpty(data)) {

           int type = Integer.parseInt(JSONObject.parseObject(data).getString("type"));

            if (isRunningForeground()) {
                ActivityManager am = (ActivityManager) this.getSystemService(Context.ACTIVITY_SERVICE);
                ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
                Context c = null;
                try {

                    c = createPackageContext(cn.getPackageName(), CONTEXT_INCLUDE_CODE | CONTEXT_IGNORE_SECURITY);
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
                jumpActivity(c, type, data);

            } else {
                jumpActivity(getThisContext(), type, data);
            }
        }
    }
    //消息跳转forString
//    private void jumpActivityTo(String type, Context c, String data) {
//        String sid = JSONObject.parseObject(data).getString("goodsId");
//        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getThisContext());
//        Intent i;
//        switch (type) {
//            case "goods":
//                i = new Intent(c, GoodsDetailActivity.class);
//                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                i.putExtra("GoodsSid", sid);
//                startActivity(i);
//               // Log.i("goods", sid);
//                ConfigUtil.saveString(sp, MainApplication.KeyValue.KEY_HOME_DATA, "");
//                break;
//            //可跳可不跳
//            case "order":
//                i = new Intent(c, MyShoppingActivity.class);
//                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                //   i.putExtra("serviceSid", sid);
//                startActivity(i);
//                ConfigUtil.saveString(sp, MainApplication.KeyValue.KEY_HOME_DATA, "");
//                break;
//            case "coupon":
//                i = new Intent(c, MyCouponActivity.class);
//                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(i);
//                ConfigUtil.saveString(sp, MainApplication.KeyValue.KEY_HOME_DATA, "");
//                break;
//        }
//    }
    /**
     * 消息跳转
     * １：呼叫
     * ２：消息
     * ３：公告
     * 4: 团购
     * 5：用户认证通过
     * 6 快递
     * 7 帐单
     * 8 调查
     * 10 自定义网站
     * 11 家政
     * 12 公共维修
     * 13 入室维修
     * 14 投诉
     * 17 巡检管理
     * 18 送水管理
     *推送跳转
     * @param context
     * @param type
     * @param data
     */
    private void jumpActivity(Context context, int type, String data) {
        Intent i;

        String sid = JSONObject.parseObject(data).getString("sid");
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getThisContext());
        switch (type) {
            case  98:
                Bundle bundle=new Bundle();
                i = new Intent(context, SideGoodsDetailActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                bundle.putString("GoodsSid",JSONObject.parseObject(data).getString("goodsId"));
                i.putExtras(bundle);
                startActivity(i);
                ConfigUtil.saveString(sp,MainApplication.KeyValue.KEY_HOME_DATA, "");
                break;
            //访客验证通过推送
            case 26:
//                i = new Intent(context, GoodsDetailActivity.class);
//                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                i.putExtra("GoodsSid", sid);
//                startActivity(i);
////                Log.i("goods", sid);
                ConfigUtil.saveString(sp,MainApplication.KeyValue.KEY_HOME_DATA, "");
                break;
            //日常工单-
            case 34:
                i = new Intent(context, TaskHallActivity.class);
                i.putExtra("page", 1);
                startActivity(i);
                ConfigUtil.saveString(sp, MainApplication.KeyValue.KEY_HOME_DATA, "");
                break;
            case 36:
                i = new Intent(context, TaskHallActivity.class);
                i.putExtra("page", 3);
                startActivity(i);
                ConfigUtil.saveString(sp, MainApplication.KeyValue.KEY_HOME_DATA, "");
                break;
            case 37:
                i = new Intent(context, TaskHallActivity.class);
                i.putExtra("page", 3);
                startActivity(i);
                ConfigUtil.saveString(sp, MainApplication.KeyValue.KEY_HOME_DATA, "");
                break;
            case 40:
                i = new Intent(context, TaskHallActivity.class);
                startActivity(i);
                ConfigUtil.saveString(sp, MainApplication.KeyValue.KEY_HOME_DATA, "");
                break;
            //可跳可不跳
            case 99:
                i = new Intent(context, MyShoppingActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //   i.putExtra("serviceSid", sid);
                startActivity(i);
                ConfigUtil.saveString(sp, MainApplication.KeyValue.KEY_HOME_DATA, "");
                break;
            case 97:
                i = new Intent(context, MyCouponActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                ConfigUtil.saveString(sp, MainApplication.KeyValue.KEY_HOME_DATA, "");
                break;
            case 1:
                i = new Intent(context, MyServiceOrderActivity.class);
                i.putExtra("Index", 2);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                ConfigUtil.saveString(sp, MainApplication.KeyValue.KEY_HOME_DATA, "");
                break;
            //推送
            case 6:
                i = new Intent(context, MyServiceOrderActivity.class);
                i.putExtra("Index",1);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                ConfigUtil.saveString(sp, MainApplication.KeyValue.KEY_HOME_DATA, "");
                break;
            //留言推送
            case 9:
//                i = new Intent(context, LeaveMessageActivity.class);
//                i.putExtra("sid", sid);
                initMessageDatas(sid);
//                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(i);
                ConfigUtil.saveString(sp, MainApplication.KeyValue.KEY_HOME_DATA, "");
                break;
            //小区通知推送
            case 3:
                i = new Intent(context, NoticeDetailActivity.class);
                i.putExtra("sid", sid);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                ConfigUtil.saveString(sp, MainApplication.KeyValue.KEY_HOME_DATA, "");
                break;

            case 11:
                i = new Intent(context, ReceiveTaskDetailActivity.class);
                i.putExtra("sid", sid);
                i.putExtra("houseKeeping", true);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                ConfigUtil.saveString(sp, MainApplication.KeyValue.KEY_HOME_DATA, "");

                break;
            case 12:
                i = new Intent(context, ReceiveTaskDetailActivity.class);
                i.putExtra("sid", sid);
                i.putExtra("publicRepair", true);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                ConfigUtil.saveString(sp, MainApplication.KeyValue.KEY_HOME_DATA, "");

                break;
            case 13:
                i = new Intent(context, ReceiveTaskDetailActivity.class);
                i.putExtra("sid", sid);
                i.putExtra("roomRepair", true);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                ConfigUtil.saveString(sp, MainApplication.KeyValue.KEY_HOME_DATA, "");

                break;
            case 14:
                i = new Intent(context, ReceiveTaskDetailActivity.class);
                i.putExtra("sid", sid);
                i.putExtra("isComplaintPush", true);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                ConfigUtil.saveString(sp, MainApplication.KeyValue.KEY_HOME_DATA, "");

                break;
            case 17:
                i = new Intent(context, ReceiveTaskDetailActivity.class);
                i.putExtra("sid", sid);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                ConfigUtil.saveString(sp, MainApplication.KeyValue.KEY_HOME_DATA, "");
                break;
            case 18:
                i = new Intent(context, ReceiveTaskDetailActivity.class);
                i.putExtra("sid", sid);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                ConfigUtil.saveString(sp, MainApplication.KeyValue.KEY_HOME_DATA, "");
                break;
//            case 100:
//                if (PublicWay.forceOfflineActivity==null) {
//                    Intent intent1 = new Intent(getThisContext(), ForceOfflineActivity.class);
//                    intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(intent1);
////                goToAnimation(2);
//                }

//                break;
            case 33:
                i = new Intent(context, TaskHallActivity.class);
                i.putExtra("page", 2);
                startActivity(i);
                ConfigUtil.saveString(sp, MainApplication.KeyValue.KEY_HOME_DATA, "");
                break;
            case 101:

                i = new Intent(context, TaskHallActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                ConfigUtil.saveString(sp, MainApplication.KeyValue.KEY_HOME_DATA, "");

                break;
            //推送
            case 106:
                i = new Intent(context, MyServiceOrderActivity.class);
                i.putExtra("Index", 1);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                ConfigUtil.saveString(sp, MainApplication.KeyValue.KEY_HOME_DATA, "");
                break;
            //留言推送
            case 121:
//                i = new Intent(context, LeaveMessageActivity.class);
//                i.putExtra("sid", sid);

                 initMessageDatasPark(sid);
//
//                    startActivity(i);

                ConfigUtil.saveString(sp, MainApplication.KeyValue.KEY_HOME_DATA, "");
                break;
            //小区通知推送
            case 125:
                i = new Intent(context, NoticeDetailActivity.class);
                i.putExtra("sid", sid);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                ChangeParkUtil.changeToPark(getThisContext(), mUserHelper);
                ConfigUtil.saveString(sp, MainApplication.KeyValue.KEY_HOME_DATA, "");
                break;

            case 110:
                i = new Intent(context, ReceiveTaskDetailActivity.class);
                i.putExtra("sid", sid);
                i.putExtra("houseKeeping", true);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                ConfigUtil.saveString(sp, MainApplication.KeyValue.KEY_HOME_DATA, "");

                break;
            case 111:
                i = new Intent(context, ReceiveTaskDetailActivity.class);
                i.putExtra("sid", sid);
                i.putExtra("publicRepair", true);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                ChangeParkUtil.changeToPark(getThisContext(), mUserHelper);
                startActivity(i);
                ConfigUtil.saveString(sp, MainApplication.KeyValue.KEY_HOME_DATA, "");

                break;
            case 122:

                i = new Intent(context, ReceiveTaskDetailActivity.class);
                i.putExtra("sid", sid);
                i.putExtra("roomRepair", true);
                ChangeParkUtil.changeToPark(getThisContext(), mUserHelper);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                ConfigUtil.saveString(sp, MainApplication.KeyValue.KEY_HOME_DATA, "");

                break;
            case 113:
                i = new Intent(context, ReceiveTaskDetailActivity.class);
                i.putExtra("sid", sid);
                i.putExtra("isComplaintPush", true);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                ChangeParkUtil.changeToPark(getThisContext(), mUserHelper);
                startActivity(i);
                ConfigUtil.saveString(sp, MainApplication.KeyValue.KEY_HOME_DATA, "");

                break;
            case 126:

                i = new Intent(context, TaskHallActivity.class);
                i.putExtra("sid", sid);
                i.putExtra("HallTask", true);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                ConfigUtil.saveString(sp, MainApplication.KeyValue.KEY_HOME_DATA, "");

                break;
            case 123:
                i =  new Intent(getThisContext(), CampaignActivity.class);
                i.putExtra("interactionSid", sid);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                ConfigUtil.saveString(sp, MainApp.KeyValue.KEY_HOME_DATA, "");

                break;
            case 124:
                i =  new Intent(getThisContext(), TopicActivity.class);
                i.putExtra("interactionSid", sid);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                ConfigUtil.saveString(sp, MainApp.KeyValue.KEY_HOME_DATA, "");
                break;
            case 128:
                i =  new Intent(getThisContext(), InvestigateActivity.class);
                i.putExtra("interactionSid", sid);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                ConfigUtil.saveString(sp, MainApp.KeyValue.KEY_HOME_DATA, "");
                break;
            case 119:
                i = new Intent(getThisContext(), FansActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                ConfigUtil.saveString(sp, MainApp.KeyValue.KEY_HOME_DATA, "");
                break;
            case 39:
                i = new Intent(getThisContext(), CampaignListActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                ConfigUtil.saveString(sp, MainApp.KeyValue.KEY_HOME_DATA, "");
                break;
            case 131:
                i = new Intent(context, MyServiceOrderActivity.class);
                i.putExtra("Index", 2);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                ConfigUtil.saveString(sp, MainApplication.KeyValue.KEY_HOME_DATA, "");
                break;
            default:
        }
    }

    @SuppressWarnings("deprecation")
    private boolean isRunningForeground() {
        ActivityManager am = (ActivityManager) this.getSystemService(Context.ACTIVITY_SERVICE);
        ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
        String currentPackageName = cn.getPackageName();
        return !TextUtils.isEmpty(currentPackageName) && currentPackageName.equals(getPackageName());
    }

    //侧滑
    private void initSlideMenu() {
        SlidingMenu sm = getSlidingMenu();//SlidingMenu控件的初始化

        setBehindContentView(R.layout.menu_left);
        sm.setShadowWidthRes(R.dimen.shadow_width);
        sm.setSlidingEnabled(true);
        sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);//拉动事件区域  TOUCHMODE_FULLSCREEN  --全屏  边缘
        sm.setShadowWidthRes(R.dimen.shadow_width);

        sm.setShadowDrawable(R.drawable.shadow);//阴影Drawable
        sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);//拉开后离边框距离
        NavigationFragment navigationFragment = new NavigationFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_left, navigationFragment).commit();
        sm.setBehindScrollScale(0);
        sm.setFadeDegree(0.25f);//颜色渐变比例-
        sm.toggle();



        sm.setOnOpenListener(() -> {StatisticsUtil.sendStatistics(mUserHelper.getSid(), "划出侧边栏", getThisContext());
                parent.setVisibility(View.VISIBLE);
                }
        );

        sm.setOnCloseListener(() -> {
            parent.setVisibility(View.GONE);
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {


        return super.onTouchEvent(event);
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_to_small_9));
                break;
            case MotionEvent.ACTION_UP:
                view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_to_normal));
                break;
            case MotionEvent.ACTION_CANCEL:
                view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_to_normal));
                break;

        }
        return false;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.home_more:
                toggle();
                StatisticsUtil.sendStatistics(mUserHelper.getSid(), "点击侧边栏", getThisContext());
                break;
            case R.id.home_news:
                application.setAmount(DateUtil.getDate());
                startActivity(new Intent(this, MessageCenterActivity.class));
                mTip.setVisibility(View.INVISIBLE);

              //  badgeView3.setBadgeCount(0);
                messagePoint.setBackgroundResource(R.color.transparent);

                StatisticsUtil.sendStatistics(mUserHelper.getSid(), "消息", getThisContext());
                break;
            case R.id.visitnew_top:

                //  验证访客
                StatisticsUtil.sendStatistics(mUserHelper.getSid(), "访客验证", getThisContext());
                visitShowDialog();
                break;
            case R.id.villagenew_top:

                //小区巡检
                StatisticsUtil.sendStatistics(mUserHelper.getSid(), "巡检", getThisContext());
                inspectionShowDialog();
                break;
            case R.id.taskhallnew_top:

                //任务大厅

                application.setNumber(DateUtil.getDate());
                startActivity(new Intent(getThisContext(), TaskHallActivity.class));
                StatisticsUtil.sendStatistics(mUserHelper.getSid(), "任务大厅", getThisContext());
               // badgeView.setVisibility(View.GONE);
                taskPoint.setBackgroundResource(R.color.transparent);

                break;
            case R.id.neighbornew_top:

                //Neighbor邻居圈

                application.setDigital(DateUtil.getDate());
                startActivity(new Intent(getThisContext(), Neighbor.class));
               // badgeView2.setBadgeCount(0);
                neighborPoint.setBackgroundResource(R.color.transparent);

                break;


            default:
                break;

        }
    }

    private void visitShowDialog() {
        final CustomDialog dialog = new CustomDialog(this, R.layout.activity_visit, R.style.myDialogTheme);
        if (dialog.isShowing()) {
            return;
        }
        changePark = (ImageView) dialog.findViewById(R.id.changePark);

        TextView mVerifyCode = (TextView) dialog.findViewById(R.id.verify_code);
        TextView mVerifyNo = (TextView) dialog.findViewById(R.id.verify_no);
        TextView mBarCode = (TextView) dialog.findViewById(R.id.bar_code);
        ImageView mCancel = (ImageView) dialog.findViewById(R.id.cancel);
        mCancel.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                dialog.dismiss();

            }
        });

        mVerifyCode.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getThisContext(), CaptureActivity.class);
                intent.putExtra("type","0");
                startActivity(intent);
                StatisticsUtil.sendStatistics(mUserHelper.getSid(), "访客验证-二维码验证", getThisContext());
                dialog.dismiss();
            }
        });

        mVerifyNo.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(getThisContext(), SerialNumberActivity.class));
                StatisticsUtil.sendStatistics(mUserHelper.getSid(), "访客验证-编号验证", getThisContext());
                dialog.dismiss();
            }
        });
        mBarCode.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getThisContext(), CaptureActivity.class);
                intent.putExtra("type","1");
                startActivity(intent);
                dialog.dismiss();
            }
        });


        dialog.setOnDismissListener(dialog1 -> ChangeParkUtil.changeToHome(getThisContext(), mUserHelper));
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    /**
     * 派单弹窗
     **/
    private void competenceShow() {
        final CustomDialog dialog = new CustomDialog(this, R.layout.dialog_assignment, R.style.myDialogTheme);
        Button btn_go = (Button) dialog.findViewById(R.id.btn_go);
        Button btn_close = (Button) dialog.findViewById(R.id.btn_close);
        btn_go.setOnClickListener((View v) -> {
            application.setIsCompetence(DateUtil.getDate());
            taskPoint.setBackgroundResource(R.color.transparent);

            Intent intent = new Intent(getThisContext(), TaskHallActivity.class);
            intent.putExtra("page", 2);
            startActivity(intent);
            String date = DateUtil.getDateTimeFormat(DateUtil.mDateTimeFormatString, new Date());
            SpUtil.put(getThisContext(), "lastTime", date);
            dialog.dismiss();
            isCompetence = false;
        });
        btn_close.setOnClickListener((View v) -> {
            dialog.dismiss();
            String date = DateUtil.getDateTimeFormat(DateUtil.mDateTimeFormatString, DateUtil.getDate());
            SpUtil.put(getThisContext(), "lastTime", date);
            isCompetence = false;
        });
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);

        dialog.show();
        isCompetence = true;
    }


    @Override
    protected void onResume() {
        super.onResume();
        UserInfoHelper mUserHelper = UserInfoHelper.getInstance(getThisContext());
        if (mUserHelper.getUserInfoTo() != null && mUserHelper.getUserInfoTo().getGroupTo() != null) {
            //mTitle.setText(mUserHelper.getUserInfoTo().getGroupTo().getGroupName());
            //mTitle.setText(mUserHelper.getUserInfoTo().getGroupTo().getGroupName()+"  "+mUserHelper.getUserInfoTo().getName());
            mTitle.setText(mUserHelper.getUserInfoTo().getName() + "  " + mUserHelper.getUserInfoTo().getTag());
        }
        MobclickAgent.onPageStart("首页");

        MobclickAgent.onResume(this);
        Log.i("2222", "onResume: "+1111);
//        getAdInfoTop();
//        getAdInfo();
        //开始翻滚
        lbt.startTurning(3000);
        autoRow.startTurning(5000);



    }

    //
    @Override
    protected void onPause() {
        super.onPause();

        MobclickAgent.onPageEnd("首页");
        MobclickAgent.onPause(this);
        isEnd = true;


    }

    int iExitCount = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            iExitCount++;

            if (iExitCount % 2 == 1) {
                Toast.makeText(this, getString(R.string.info_back),
                        Toast.LENGTH_LONG).show();

            }
            if (iExitCount % 2 == 0) {
                finish();

            }
            return true;
        } else {
            iExitCount = 0;
        }
        return super.onKeyDown(keyCode, event);
    }

    private Context getThisContext() {

        return MainActivity.this;
    }

    public RelativeLayout.LayoutParams getLayoutParam(ImageView view) {

        int mScreenWidth = getScreenWidthPixels(getThisContext());
        int mHeight = (int) (mScreenWidth * 0.35);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view.getLayoutParams();
        params.height = mHeight;

        return params;
    }

    private int getScreenWidthPixels(Context context) {
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }

    final class MyWebChromeClient extends WebChromeClient {
        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {

            result.confirm();
            return true;
        }
    }

    private class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            startActivity(new Intent(getThisContext(), TaskHallActivity.class));
        }

    }

    private void initMessageDatas(String mServiceSid) {

        HomeApi api = ApiClient.create(HomeApi.class);
        api.findServiceMainExpandBySid(mServiceSid, new HttpCallback<MessageTo<ServiceMainExpandTo>>(this) {
            @Override
            public void success(MessageTo<ServiceMainExpandTo> msg, Response response) {
                if (msg.getSuccess() == 0) {
                    mainExpandTo = msg.getData();
                    Intent intent = new Intent(getThisContext(), LeaveMessageActivity.class);
                    intent.putExtra("mode", mainExpandTo);
                    startActivity(intent);
                } else {
                    /*Toast.makeText(getThisContext(), msg.getMessage(),
                            Toast.LENGTH_LONG).show();*/
                }
            }

            @Override
            public void failure(RetrofitError error) {
                super.failure(error);
            }
        });

    }

    private void initMessageDatasPark(String mServiceSid) {

        HomeApi api = ApiClientPark.create(HomeApi.class);
        api.findServiceMainExpandBySid(mServiceSid, new HttpCallback<MessageTo<ServiceMainExpandTo>>(this) {
            @Override
            public void success(MessageTo<ServiceMainExpandTo> msg, Response response) {
                if (msg.getSuccess() == 0) {
                    ChangeParkUtil.changeToPark(getThisContext(), mUserHelper);
                    mainExpandTo = msg.getData();
                    Intent intent = new Intent(getThisContext(), LeaveMessageActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("mode", mainExpandTo);
                    startActivity(intent);
                } else {
                    /*Toast.makeText(getThisContext(), msg.getMessage(),
                            Toast.LENGTH_LONG).show();*/
                }
            }

            @Override
            public void failure(RetrofitError error) {
                super.failure(error);
            }
        });

    }
    /**
     * 任务大厅红点
     **/
    private void noticeTaskHall() {
        HomeApi api = ApiClient.create(HomeApi.class);
        final ServiceTaskParam serviceTaskParam = new ServiceTaskParam();

            String taskLastTime = SpUtil.getString(getThisContext(),"TaskLastTime");
            if (taskLastTime == null||"kong".equals(taskLastTime)) {
                serviceTaskParam.setLastTime(firstCome);

            } else {
                serviceTaskParam.setLastTime(DateUtil.setStringToDate(taskLastTime));
            }

        SpUtil.put(getThisContext(),"TaskLastTime",DateUtil.getDateString());
        serviceTaskParam.setOwnerSid(mUserHelper.getSid());
        System.out.println(serviceTaskParam+"taskParam");
        api.redPointTaskHall(serviceTaskParam, new HttpCallback<MessageTo<Integer>>(this) {
            @Override
            public void success(MessageTo<Integer> msg, Response response) {
                if (msg.getSuccess() == 0) {
                    if (msg.getData() != null) {
                        if (msg.getData() > 0) {

                            //badgeView.setText("新");
                            taskPoint.setBackgroundResource(R.drawable.shop_car_bg);

                        } else {
                            taskPoint.setBackgroundResource(R.color.transparent);
                            //      badgeView.setVisibility(View.GONE);
                        }
                    }
                } else {
//                    Toast.makeText(getThisContext(), msg.getMessage(),
//                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }


    /**
     * 任务大厅派单
     **/
    private void setCompetence() {
        HomeApi api = ApiClient.create(HomeApi.class);
        serviceParam = new ServiceParam();
        if (application != null) {
            lastOccasion = application.getIsCompetence();


        }
        String dateString = SpUtil.getString(getThisContext(), "lastTime");

        Date date;
        if (!"kong".equals(dateString))
            date = DateUtil.getFormatDateLongTime(dateString);
        else {
            date = new Date();
            String date1 = DateUtil.getDateTimeFormat(DateUtil.mDateTimeFormatString, date);
            SpUtil.put(getThisContext(), "lastTime", date1);
        }

        serviceParam.setLastTime(date);
        serviceParam.setGroupUserSid(mUserHelper.getSid());


        api.competenceTaskHall(serviceParam, new HttpCallback<MessageTo<Integer>>(this) {
            @Override
            public void success(MessageTo<Integer> msg, Response response) {
                if (msg.getSuccess() == 0) {
                    if (msg.getData() != null) {


                        if (msg.getData() > 0) {
                            taskPoint.setBackgroundResource(R.drawable.shop_car_bg);

                            competenceShow();
                        }
                    }

                } else {
//                    Toast.makeText(getThisContext(), msg.getMessage(),
//                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }


    /**
     * 邻居圈红点
     **/
    private void noticeNeighbor() {

        if (!TextUtils.isEmpty(mUserHelper.getSid())) {
            HomeApi api = ApiClient.create(HomeApi.class);
            final NeighborMessageParam neighborMessageParam = new NeighborMessageParam();
            if (application != null) {
                lastOpportunity = application.getDigital();
                if (lastOpportunity == null) {
                    neighborMessageParam.setLastTime(firstCome);
                } else {
                    neighborMessageParam.setLastTime(lastOpportunity);
                }

            }
            neighborMessageParam.setUserSid(mUserHelper.getSid());

            api.redPointNeighbor(neighborMessageParam, new HttpCallback<MessageTo<List<NeighborReportNewPostTo>>>(this) {
                @Override
                public void success(MessageTo<List<NeighborReportNewPostTo>> msg, Response response) {

                    if (msg.getSuccess() == 0&&msg.getData()!=null) {
                        neighborCount = msg.getData();
                        int sum = 0;
                        int d = 0;
                        for (int i = 0; i < neighborCount.size(); i++) {
                            d = neighborCount.get(i).getCountTotal();
                            sum += d;
                        }
                        if (sum != 0) {
                            neighborPoint.setBackgroundResource(R.drawable.shop_car_bg);
                        //    badgeView2.setBadgeCount(1);
                        } else {
                            neighborPoint.setBackgroundResource(R.color.transparent);

                            //    badgeView2.setBadgeCount(0);
                        }
                    }

                }

                @Override
                public void failure(RetrofitError error) {

                }
            });
        }
    }


    /**
     * 消息中心红点
     **/
    private void noticeMessageCenter() {
        if (!TextUtils.isEmpty(mUserHelper.getSid())) {
            HomeApi api = ApiClient.create(HomeApi.class);
            final MessageParam messageParam = new MessageParam();
            if (application != null) {
                lastOdds = application.getAmount();
                if (lastOdds == null) {
                    messageParam.setLastTime(firstCome);
                } else {
                    messageParam.setLastTime(lastOdds);
                }

            }
            messageParam.setOwnerSid(mUserHelper.getSid());


            api.redPointMessageCenter(messageParam, new HttpCallback<ServiceRedPointTo>(this) {
                @Override
                public void success(ServiceRedPointTo msg, Response response) {


                    if (msg != null)
                        messageCenterCount = msg.getData();
                    if (messageCenterCount != 0) {
                        messagePoint.setBackgroundResource(R.drawable.shop_car_bg);

//                        badgeView3.setBadgeCount(messageCenterCount);
                    } else {
                        messagePoint.setBackgroundResource(R.color.transparent);

//                        badgeView3.setBadgeCount(0);
                    }
                }

                @Override
                public void failure(RetrofitError error) {

                }
            });
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        isEnd = false;
    getNewMessage();
        ApiClient.setHomeUrl();
        if (SpUtil.getString(getThisContext(),"HomeInfo")==null){
            SpUtil.put(getThisContext(),"HomeInfo",ConfigUtil.getString(sp, KeyValue.KEY_USER_INFO, ""));
        }
            UserInfoTo userInfoTo = JSON.parseObject(SpUtil.getString(getThisContext(),"HomeInfo"), UserInfoTo.class);
            mUserHelper.updateUser(userInfoTo, getThisContext());
            mUserHelper.setUserInfoTo(userInfoTo);
        com.Util.SpUtil.put(getThisContext(), "CurrentType", -1);
//
        Log.i("555", "onRestart: "+com.Util.SpUtil.getBoolean(getThisContext(),"IsOffLine"));
        if (com.Util.SpUtil.getBoolean(getThisContext(),"IsOffLine")){
            if (PublicWay.forceOfflineActivity==null) {
                Log.i("555", "onRestart1: "+com.Util.SpUtil.getBoolean(getThisContext(),"IsOffLine"));

                Intent intent1 = new Intent(getThisContext(), ForceOfflineActivity.class);
                intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent1);
//                goToAnimation(2);
            }
        }

    }

    private void inspectionShowDialog() {
        firstChangeInspect = false;
        final CustomDialog dialog = new CustomDialog(this, R.layout.inspection_dialog, R.style.myDialogTheme);
        if (dialog.isShowing()) {
            return;
        }
        TextView mVerifyCode = (TextView) dialog.findViewById(R.id.verify_code);
        TextView mVerifyNo = (TextView) dialog.findViewById(R.id.verify_no);
        ImageView mCancel = (ImageView) dialog.findViewById(R.id.cancel);
        mCancel.setOnClickListener(view -> dialog.dismiss());
        mVerifyCode.setOnClickListener(view -> {
            Intent intent = new Intent(getThisContext(), VillageInspectionActivity.class);
            intent.putExtra("type", 0);
            startActivity(intent);
            StatisticsUtil.sendStatistics(mUserHelper.getSid(), "巡检-业主提报", getThisContext());
            dialog.dismiss();
        });

        mVerifyNo.setOnClickListener(view -> {
            Intent intent = new Intent(getThisContext(), VillageInspectionActivity.class);
            intent.putExtra("type", 1);
            startActivity(intent);
            StatisticsUtil.sendStatistics(mUserHelper.getSid(), "巡检-物业提报", getThisContext());
            dialog.dismiss();
        });


        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    private void vehicleinspectionShowDialog() {
        firstChangeInspect = false;
        final CustomDialog dialog = new CustomDialog(this, R.layout.vehicle_inspection_dialog, R.style.myDialogTheme);
        if (dialog.isShowing()) {
            return;
        }
        TextView mVerifyCode = (TextView) dialog.findViewById(R.id.verify_code);
        TextView mVerifyNo = (TextView) dialog.findViewById(R.id.verify_no);
        ImageView mCancel = (ImageView) dialog.findViewById(R.id.cancel);
        mCancel.setOnClickListener(view -> dialog.dismiss());
        mVerifyCode.setOnClickListener(view -> {
            Intent intent = new Intent(getThisContext(), VehicleInspectionActivity.class);
            startActivity(intent);
            dialog.dismiss();
        });

        mVerifyNo.setOnClickListener(view -> {
            startActivity(new Intent(getThisContext(), VehicleManagementActivity.class));
            StatisticsUtil.sendStatistics(mUserHelper.getSid(), "车辆管理", getThisContext());
            dialog.dismiss();
        });


        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }
    public  void getParkLimit(){
        UserApi api= ApiClientPark.create(UserApi.class);
        ParkUserInfoParam param=new ParkUserInfoParam();
        param.setOwnerName(mUserHelper.getUserInfoTo().getName());
        param.setOwnerPhone(mUserHelper.getPhone());
        Log.i("222", "getParkLimit: " + param.toString());
         api.getParkLimitPost(param, new HttpCallback<MessageTo<UserInfoTo>>(getThisContext()) {
             @Override
             public void success(MessageTo<UserInfoTo> msg, Response response) {
                 if (msg.getSuccess() == 0) {
                     String limitPark = "";
                     Log.i("222", "getParkLimit: " + msg.getData().getSid());

                     if (msg.getData() == null || msg.getData().getGroupMenuToList() == null) {
                         SpUtil.put(getThisContext(), "limitPark", "");
                         SpUtil.put(getThisContext(), "ParkInfo", JSON.toJSONString(msg.getData()));
                         return;
                     }
                     SpUtil.put(getThisContext(), "ParkInfo", JSON.toJSONString(msg.getData()));
                     if (msg.getData().getSid() != null)
                         upLoadDevice(msg.getData().getSid());
                     for (GroupMenuTo menuTo : msg.getData().getGroupMenuToList())
                         limitPark = limitPark + menuTo.getCode() + "-";
                     SpUtil.put(getThisContext(), "limitPark", limitPark);
                 } else
                     SpUtil.put(getThisContext(), "limitPark", "");
             }

             @Override
             public void failure(RetrofitError error) {
                 System.out.println(error + "error");
                 SpUtil.put(getThisContext(), "limitPark", "");
             }
         });

    }
    private void changePark() {
        if (!firstChange){
            ApiClient.setParkUrl();
            changePark.setBackgroundResource(R.drawable.selector_home);
            firstChange=true;

        }else {
            ApiClient.setHomeUrl();
            changePark.setBackgroundResource(R.drawable.selector_park);
            firstChange=false;

        }
    }
    //悦购用户信息
    public void getUserInfo(){
if (mUserHelperBulk.getUserInfoTo()!=null)
    return;
        NewShopApi api= ApiClientBulk.create(NewShopApi.class);
        BulkUserInfoParam param=new BulkUserInfoParam();
        //南都物业
     param.setOldCommunityId("b4a87080-5b85-4438-9b39-44aa456cdf00");
//       幸福家园
   //param.setOldCommunityId("a241b63a-df3c-43a8-a7fd-477e5f950d9f");

        param.setOldUserId(mUserHelper.getSid());
        com.joyhome.nacity.app.util.CustomDialogFragment dialogFragment=new com.joyhome.nacity.app.util.CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "");
        Log.i("22222", "UserInfoTo:11111 "+param.toString());
        api.shopLogin(param, new HttpCallback<UserMessage>(getThisContext()) {
            @Override
            public void success(UserMessage msg, Response response) {
                dialogFragment.dismiss();
                Log.i("22222", "UserInfoTo:222222 "+msg.toString());
                if (msg.getCode() == 0) {
                    UserInfoTo infoTo = new UserInfoTo();
                    Log.i("22222", "UserInfoTo: "+infoTo.toString());
                    infoTo.setSid(msg.getUserId());
                    infoTo.setApartmentSid(msg.getCommunityId());
                    mUserHelperBulk.updateUser(infoTo);

                }

            } @Override
              public void failure(RetrofitError error) {
                Log.i("22222", "error: "+error.toString());

                dialogFragment.dismiss();

            }
        });
    }
    public void upLoadDevice(String sid){
        if (!TextUtils.isEmpty(client)) {
            DeviceParam param = new DeviceParam();
            CommonApi api = ApiClientPark.create(CommonApi.class);
            param.setDeviceToken(client);
            param.setDeviceApp(11);
            param.setOwnerSid(sid);
            param.setDeviceType("android");
            param.setType(1);
            param.setRegistrationId(JPushInterface.getRegistrationID(MainActivity.this));
            Log.i("11111", param.toString());
            api.updateOwnerDeviceInfo(param, new HttpCallback<MessageTo<DeviceTo>>(this) {
                @Override
                public void success(MessageTo<DeviceTo> msg, Response response) {
                    if (msg.getSuccess() == 0) {
                    } else {
                    }
                }

                @Override
                public void failure(RetrofitError error) {
                }
            });

        }
    }
    public void getUserInfoClick() {
        if (mUserHelperBulk.getSid() != null) {
            ConfigUtil.saveString(sp, com.joyhome.nacity.app.MainApp.KeyValue.KEY_DATE_SHOP, DateUtil.getDateTimeFormat(DateUtil.mDateTimeFormatString, new Date()));
            startActivity(new Intent(getThisContext(), ShoppingActivity.class));

        } else {
            NewShopApi api = ApiClientBulk.create(NewShopApi.class);
            BulkUserInfoParam param = new BulkUserInfoParam();
            param.setOldCommunityId(mHelper.getSid());
            param.setOldUserId(mUserHelper.getSid());
            com.joyhome.nacity.app.util.CustomDialogFragment dialogFragment = new com.joyhome.nacity.app.util.CustomDialogFragment();
            dialogFragment.show(getSupportFragmentManager(), "");
            api.shopLogin(param, new HttpCallback<UserMessage>(getThisContext()) {
                @Override
                public void success(UserMessage msg, Response response) {
                    dialogFragment.dismiss();
                    if (msg.getCode() == 0) {

                        UserInfoTo infoTo = new UserInfoTo();
                        infoTo.setSid(msg.getUserId());
                        infoTo.setApartmentSid(msg.getCommunityId());
                        mUserHelperBulk.updateUser(infoTo);
                        Log.i("111", msg.toString());

                        startActivity(new Intent(getThisContext(), ShoppingActivity.class));
                    }

                }

                @Override
                public void failure(RetrofitError error) {
                    Log.i("111", error.toString());
                    dialogFragment.dismiss();
                }
            });
        }
    }
    private void registerEventBus() {
    if (isFirstRegisterEventBus)
        EventBus.getDefault().register(this);
    }

    /**
     *
     * 新任务红点
     */
    @Subscribe
    public void getNewTaskPoint(RefreshEvent event){
        if ("TaskPoint".equals(event.getmMsg()))
            taskPoint.setBackgroundResource(R.drawable.shop_car_bg);
        if ("OffLine".equals(event.getmMsg())) {
            IntentFilter filter = new IntentFilter();
            filter.addAction("com.igexin.sdk.action.C6UZZUwOft9Wiq3iKNiBH4");
            registerReceiver(new PushMessageReceiver(), filter);
            String data = ConfigUtil.getString(sp, MainApplication.KeyValue.KEY_HOME_DATA, "");

            if (!TextUtils.isEmpty(data)) {

                int type = Integer.parseInt(JSONObject.parseObject(data).getString("type"));
                if (type==100&& PublicWay.forceOfflineActivity==null){
                    Intent intent1 = new Intent(getThisContext(), ForceOfflineActivity.class);
                    intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent1);
                }
            }
       }
    }

}
