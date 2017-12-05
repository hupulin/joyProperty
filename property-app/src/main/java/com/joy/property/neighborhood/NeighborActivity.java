package com.joy.property.neighborhood;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.camnter.easyslidingtabs.widget.EasySlidingTabs;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.neighbor.NeighborLikeTo;
import com.jinyi.ihome.module.neighbor.NeighborMessageParam;
import com.jinyi.ihome.module.neighbor.NeighborPostTo;
import com.jinyi.ihome.module.neighbor.NeighborPostTypeTo;
import com.jinyi.ihome.module.neighbor.NeighborUserCommentTo;
import com.jinyi.ihome.module.neighbor.OwnerMessageTo;
import com.joy.common.api.ApiClient;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.NeighborApi;

import com.joy.common.application.KeyValue;
import com.joy.common.helper.ApartmentInfoHelper;
import com.joy.common.helper.UserInfoHelper;
import com.joy.library.utils.ConfigUtil;
import com.joy.library.utils.DateUtil;

import com.joy.property.MainActivity;
import com.joy.property.MainApp;
import com.joy.property.R;
import com.joy.property.neighborhood.adapter.KeyboardListenRelativeLayout;
import com.joy.property.neighborhood.adapter.NeighborPostToAdapter;
import com.joy.property.neighborhood.fragment.NeighborFragment;
import com.joy.property.neighborhood.fragment.NeighborReplyFragment;
import com.joy.property.utils.ACache;
import com.joy.property.utils.CustomDialog;
import com.joy.property.utils.SpUtil;

import com.joy.property.utils.StatisticsUtil;
import com.joy.property.base.BaseActivity;


import java.util.ArrayList;
import java.util.List;


import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Admin on 2014-11-24
 * 邻居圈
 */



public class NeighborActivity extends BaseActivity implements OnClickListener {
    private TextView liveQty;
    private TextView newsQty;
    private PullToRefreshListView mPullToRefreshListView;
    private int pageIndex = 0;
    private List<NeighborPostTo> postList = new ArrayList<>();
    private NeighborPostToAdapter mAdapter = null;
    private EditText mContent;
    private ListView mListView;
    private String qty;
    private List<NeighborPostTypeTo> mytypeList = new ArrayList<>();
    private Dialog mdialog;
    private ImageView dailog_close;
    private boolean haveLike;
    private boolean haveComment;
    private LinearLayout newsLayout;
    private ImageView newsImage;
    private List<NeighborUserCommentTo> mUserCommentList;
    private List<NeighborLikeTo>mUserLikeList;
    private List<NeighborPostTypeTo> typeList = new ArrayList<>();
    private ImageView neighborShowIcon;
    private RelativeLayout neighborShow;
    private List<NeighborPostTo> SortPostList = new ArrayList<>();

    private PopupWindow mPopupWindow;
    private EasySlidingTabs tpi;
    private ViewPager viewPager;
    private List<Fragment>mFragments=new ArrayList<>();
    private String[] title={"按最新发布","按最新回复"};
    private RelativeLayout moveLine;
    private TextView reply;
    private int titleType=1;
    private TextView commentContent;
    private boolean isFocusChange;
    private String currentSelect="";
    private List<TextView>buttonList=new ArrayList<>();
    private TextView replyText;
    private TextView releaseText;
    private KeyboardListenRelativeLayout relativeLayout;
    private RelativeLayout neighborShowTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbor);

        findById();



    }

    @Override
    protected void onResume() {
        super.onResume();
        SpUtil.put(getThisContext(), "TopicSid", "");
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void initFragment() {
        mHelper=ApartmentInfoHelper.getInstance(MainApp.mContext);
        if (!ConfigUtil.getString(sp, KeyValue.KEY_USER_INFO, "").equals(SpUtil.getString(getThisContext(),"HomeInfo")))
            SpUtil.put(getThisContext(),"ParkNeighbor",true);
        System.out.println(mHelper.getSid() + "sid----------");
        ((TextView)(findViewById(R.id.apartmentTitle))).setText(TextUtils.isEmpty(mHelper.getApartmentName())?" 圈 子":("邻居圈（"+mHelper.getApartmentName() + ")"));
        mFragments.add(new NeighborFragment(reply, commentContent, getThisContext()));
        mFragments.add(new NeighborReplyFragment(reply, commentContent));
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (positionOffsetPixels != 0)
                    moveLine.setX(positionOffsetPixels / 2);

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 1) {
                    NeighborReplyFragment replyFragment = (NeighborReplyFragment) mFragments.get(1);
                    replyFragment.init(currentSelect);
                    replyText.setTextColor(Color.parseColor("#4fb2d6"));
                    releaseText.setTextColor(Color.parseColor("#999999"));
                    moveLine.setX(getScreenWidth() / 2);
                }
                if (position == 0) {
                    NeighborFragment neighborFragment = (NeighborFragment) mFragments.get(0);

                    neighborFragment.init(currentSelect);
                    releaseText.setTextColor(Color.parseColor("#4fb2d6"));
                    replyText.setTextColor(Color.parseColor("#999999"));
                    moveLine.setX(0);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    private void init() {
mUserHelper= UserInfoHelper.getInstance(MainApp.mContext);

            /**
             * 获取新的用户评论检查
             */
            RelativeLayout rlInvite = (RelativeLayout)findViewById(R.id.rl_invite);
            rlInvite.setOnClickListener(this);
            RelativeLayout mInvite = (RelativeLayout)findViewById(R.id.ll_invite);
            mInvite.setOnClickListener(this);

            newsQty = (TextView) findViewById(R.id.news_qty);
            newsLayout = (LinearLayout) findViewById(R.id.news_layout);
            newsLayout.setVisibility(View.GONE);

            newsImage = (ImageView) findViewById(R.id.news_image);


            if (!TextUtils.isEmpty(mHelper.getSid())) {
                NeighborMessageParam mMessageParam = new NeighborMessageParam();
                mMessageParam.setApartmentSid(mHelper.getSid());
                mMessageParam.setUserSid(mUserHelper.getSid());
                mMessageParam.setPageIndex(0);
                String lastTime = ConfigUtil.getString(sp, MainApp.KeyValue.KEY_REFRESH_TIME, "");
                if (!TextUtils.isEmpty(lastTime)) {
                    mMessageParam.setLastTime(DateUtil.getFormatDateLongTime(lastTime));
                }
                NeighborApi api = ApiClient.create(NeighborApi.class);
                api.findNeighborPostUserNewComment(mMessageParam, new HttpCallback<MessageTo<List<NeighborUserCommentTo>>>(this.getApplication()) {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void success(MessageTo<List<NeighborUserCommentTo>> msg, Response response) {
                        if (msg.getSuccess() == 0) {
                            mUserCommentList = new ArrayList<>();
                            mUserCommentList.addAll(msg.getData());


                            haveComment = true;

                            if (mUserCommentList.size() > 0) {
                                newsQty.setVisibility(View.VISIBLE);
                                newsLayout.setVisibility(View.VISIBLE);
                                newsLayout.setOnClickListener(NeighborActivity.this);
                                newsQty.setText(mUserCommentList.size() + "条新消息");
                                displayImage(newsImage, mUserCommentList.get(mUserCommentList.size() - 1).getCommentOwner().getIcon(), R.drawable.guest_head_image);
                                if (haveLike && mUserLikeList.size() > 0) {
                                    newsQty.setText(mUserCommentList.size() + mUserLikeList.size() + "条新消息");
                                    if (mUserCommentList.get(mUserCommentList.size() - 1).getCommentTime().getTime() < mUserLikeList.get(mUserLikeList.size() - 1).getLikeTime().getTime()) {
                                        displayImage(newsImage, mUserLikeList.get(mUserLikeList.size() - 1).getLikeOwner().getIcon(), R.drawable.guest_head_image);
                                    }
                                }

                            } else {

//                                newsQty.setVisibility(View.GONE);
//                                newsLayout.setVisibility(View.GONE);
//                                newsImage.setVisibility(View.GONE);
                            }
                        } else {

                        }

                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }
                });
                api.findNeighborPostUserNewLike(mMessageParam, new HttpCallback<MessageTo<List<NeighborLikeTo>>>(this.getApplication()) {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void success(MessageTo<List<NeighborLikeTo>> msg, Response response) {
                        if (msg.getSuccess() == 0) {
                            mUserLikeList = new ArrayList<>();
                            mUserLikeList.addAll(msg.getData());


                            haveLike = true;
                            if (mUserLikeList.size() > 0) {
                                newsQty.setVisibility(View.VISIBLE);
                                newsQty.setText(mUserLikeList.size() + "条消息");
                                newsLayout.setOnClickListener(NeighborActivity.this);
                                newsLayout.setVisibility(View.VISIBLE);
                                displayImage(newsImage, mUserLikeList.get(mUserLikeList.size() - 1).getLikeOwner().getIcon(), R.drawable.guest_head_image);
                                if (haveComment && mUserCommentList.size() > 0) {
                                    newsQty.setText(mUserLikeList.size() + mUserCommentList.size() + "条消息");
                                    if (mUserCommentList.get(mUserCommentList.size() - 1).getCommentTime().getTime() > mUserLikeList.get(mUserLikeList.size() - 1).getLikeTime().getTime()) {
                                        displayImage(newsImage, mUserCommentList.get(mUserCommentList.size() - 1).getCommentOwner().getIcon(), R.drawable.guest_head_image);
                                    }
                                }
                            } else {
//                                newsQty.setVisibility(View.GONE);
//                                newsImage.setVisibility(View.GONE);
//                                newsLayout.setVisibility(View.GONE);
                            }
                        } else {

                        }

                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }
                });
            }


    }


    private void findById() {
        findViewById(R.id.iv_back).setOnClickListener(this);
        Button circleEntry = (Button) findViewById(R.id.circle_entry);
        circleEntry.setOnClickListener(this);
        neighborShow = (RelativeLayout) findViewById(R.id.neighborShow);
        neighborShow.setOnClickListener(this);
        neighborShowTest = (RelativeLayout) findViewById(R.id.neighborShowTest);
        neighborShowIcon = (ImageView) findViewById(R.id.neighborShowIcon);
     //   tpi = (EasySlidingTabs) findViewById(R.id.tpi);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        moveLine = (RelativeLayout) findViewById(R.id.moveLine);
        findViewById(R.id.myNeighbor).setOnClickListener(this);
        reply = (TextView) findViewById(R.id.content);
        commentContent = (TextView) findViewById(R.id.commentContent);
        SpUtil.put(getThisContext(), "canRefreshAdapter", false);
        findViewById(R.id.releaseLayout).setOnClickListener(this);
        findViewById(R.id.replyLayout).setOnClickListener(this);
        replyText = (TextView) findViewById(R.id.reply);
        releaseText = (TextView) findViewById(R.id.releaseText);
        relativeLayout = (KeyboardListenRelativeLayout) findViewById(R.id.keyboardRelativeLayout);
        relativeLayout.setOnKeyboardStateChangedListener(new KeyboardListenRelativeLayout.IOnKeyboardStateChangedListener() {

            public void onKeyboardStateChanged(int state) {
                switch (state) {
                    case KeyboardListenRelativeLayout.KEYBOARD_STATE_HIDE://软键盘隐藏
                        if (viewPager.getCurrentItem()==0) {
                            NeighborFragment neighborFragment = (NeighborFragment) mFragments.get(0);
                            neighborFragment.hideDialog();
                        }else
                        ((NeighborReplyFragment)mFragments.get(1)).hideDialog();
                        break;

                    default:
                        break;
                }
            }
        });

    }
    @Override
    public void onClick(View v) {
        Intent intent1=new Intent(NeighborActivity.this,PostActivity.class);
        switch (v.getId()) {
            case R.id.iv_back:

                onBackPressed();

                break;

            case R.id.myNeighbor:
                mUserHelper=UserInfoHelper.getInstance(getThisContext());
               Intent intentMyNeighbor=new Intent(getThisContext(), MyNeighborActivity.class) ;
                OwnerMessageTo messageTo=new OwnerMessageTo();
                messageTo.setFlag(0);
                messageTo.setOwnerImage(mUserHelper.getUserInfoTo().getImage());
                messageTo.setOwnerSid(mUserHelper.getSid());
                intentMyNeighbor.putExtra("messageTo", messageTo);
                intentMyNeighbor.putExtra("type", "1");
                StatisticsUtil.sendStatistics(mUserHelper.getSid(), "邻居圈-个人主页", getThisContext());
                startActivity(intentMyNeighbor);

                break;

            case R.id.circle_entry:

                Intent intent = new Intent(NeighborActivity.this, PostActivity.class);
                startActivity(intent);

                SpUtil.put(getThisContext(), "postType", "11");
                SpUtil.put(getThisContext(),"typeNameNeighbor","随便说说");
                goToAnimation(1);






                break;
            case R.id.news_layout:

                 intent = new Intent(this, NeighborhoodCircleNewsActivity.class);
                startActivity(intent);
                newsLayout.setVisibility(View.GONE);
                break;

            case R.id.iv_neighbor_dailog_close:
                ScaleAnimation mAnimation=new ScaleAnimation(1f, 1.2f, 1f, 1.2f,
                        Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                mAnimation.setDuration(300);
                dailog_close.startAnimation(mAnimation);
//                mAnimation.setAnimationListener(new Animation.AnimationListener() {
//                    @Override
//                    public void onAnimationStart(Animation animation) {
//
//                    }
//
//                    @Override
//                    public void onAnimationEnd(Animation animation) {
                mdialog.dismiss();
//                    }
//
//                    @Override
//                    public void onAnimationRepeat(Animation animation) {
//
//                    }
//                });

                break;
            case R.id.neighborShow:

                showSort();
                break;
            case R.id.releaseLayout:
                viewPager.setCurrentItem(0);
                break;
            case R.id.replyLayout:
                viewPager.setCurrentItem(1);
                break;
        }
    }



    /**
     * 分享对话框
     */


    @Override
    public void onBackPressed() {
        postList.clear();


        finish();
        super.onBackPressed();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            onBackPressed();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected Context getThisContext() {
        return NeighborActivity.this;
    }

    @Override
    protected String toPageName() {
        return "邻居圈";
    }
    private void loader() {
        if(JSON.parseArray(new ACache().getAsString("typeList"),NeighborPostTypeTo.class)!=null)
            typeList=JSON.parseArray(new ACache().getAsString("typeList"),NeighborPostTypeTo.class);

        NeighborApi api = ApiClient.create(NeighborApi.class);

        api.findNeighborPostTypeList(new HttpCallback<MessageTo<List<NeighborPostTypeTo>>>(getThisContext()) {
            @Override
            public void success(MessageTo<List<NeighborPostTypeTo>> msg, Response response) {
                if (msg == null) return;
                if (msg.getSuccess() == 0) {
                    typeList.clear();
                    typeList.addAll(msg.getData());
                    new ACache().put("typeList", JSON.toJSONString(typeList));
                }
            }

            @Override
            public void failure(RetrofitError error) {


            }
        });


    }

    public void showSort(){
        View popupView = getLayoutInflater().inflate(R.layout.neighbor_circle_child_item, null);
        View textView;

        LinearLayout typeListLayout = (LinearLayout) popupView.findViewById(R.id.typeListLayout);
        String[] titleName={"活动","话题","调查"};
        buttonList.clear();
        for(int i=0;i<typeList.size()+3;i++){

            textView=View.inflate(getThisContext(),R.layout.typelist_item,null);
            TextView typeName= (TextView) textView.findViewById(R.id.typeName);
            buttonList.add(typeName);
            titleType=i-typeList.size();

            if (i<typeList.size()&&currentSelect.equals(typeList.get(i).getTypeSid())) {
                typeName.setBackgroundResource(R.drawable.neighbor_child_text_bg);
            }

            if ("17".equals(currentSelect)&&i==typeList.size())
                typeName.setBackgroundResource(R.drawable.neighbor_child_text_bg);
            if ("18".equals(currentSelect)&&i==typeList.size()+1)
                typeName.setBackgroundResource(R.drawable.neighbor_child_text_bg);
            if ("19".equals(currentSelect)&&i==typeList.size()+2)
                typeName.setBackgroundResource(R.drawable.neighbor_child_text_bg);

            typeName.setTag(titleType);
            if (i<typeList.size()) {
                NeighborPostTypeTo postTypeTo = typeList.get(i);
                typeName.setText(postTypeTo.getTypeName());

                typeName.setOnClickListener(v -> {
                    StatisticsUtil.sendStatistics(mUserHelper.getSid(), "邻居圈-" + mHelper.getApartmentName() +"-"+ postTypeTo.getTypeName(), getThisContext());
                    if (viewPager.getCurrentItem()==0) {
                        NeighborFragment neighborFragment = (NeighborFragment) mFragments.get(0);
                        neighborFragment.setListSort(0, Integer.valueOf(postTypeTo.getTypeSid()), true);


                    }
                    else {
                        NeighborReplyFragment replyFragment = (NeighborReplyFragment) mFragments.get(1);
                        replyFragment.setListSort(0, Integer.valueOf(postTypeTo.getTypeSid()), true);

                    }
                    currentSelect=postTypeTo.getTypeSid();
                    for (TextView button:buttonList) {
                        button.setBackgroundColor(Color.parseColor("#00000000"));
                        button.setTextColor(Color.parseColor("#353535"));
                    }
                    typeName.setTextColor(Color.parseColor("#4fb2d6"));


                    mPopupWindow.dismiss();
                });
            }else {
                int selectId=i-typeList.size();
                typeName.setText(titleName[selectId]);
                typeName.setOnClickListener(v -> {
                    StatisticsUtil.sendStatistics(mUserHelper.getSid(), "邻居圈-" + mHelper.getApartmentName() +"-"+ titleName[selectId], getThisContext());
                    if (viewPager.getCurrentItem()==0) {
                        NeighborFragment neighborFragment = (NeighborFragment) mFragments.get(0);
                        neighborFragment.setListSort(0, 17+selectId, true);
                        currentSelect=17+selectId+"";

                    }
                    else {
                        NeighborReplyFragment replyFragment = (NeighborReplyFragment) mFragments.get(1);
                        replyFragment.setListSort(0,17+selectId, true);
                        currentSelect=17+selectId+"";
                    }
                    mPopupWindow.dismiss();
                });
                //   typeName.setText(titleName[i-typeList.size()]);
//              int allType= (int) typeName.getTag()+1;
//              textView.setOnClickListener(v -> {
//
//              Intent intent=new Intent(getThisContext(),InteractActivity.class);
//                  intent.putExtra("allType",allType);
//                  startActivity(intent);

                //            });
            }
            typeListLayout.addView(textView);
        }
        TextView newRelease= (TextView) popupView.findViewById(R.id.newRelease);
        buttonList.add(newRelease);
        if ("".equals(currentSelect)) {
            newRelease.setTextColor(Color.parseColor("#4fb2d6"));
            newRelease.setBackgroundResource(R.drawable.neighbor_child_text_bg);
        }
        newRelease .setOnClickListener(v -> {
            StatisticsUtil.sendStatistics(mUserHelper.getSid(), "邻居圈-"+mHelper.getApartmentName()+"-首页", getThisContext());

            for (TextView button : buttonList) {
                button.setBackgroundColor(Color.parseColor("#00000000"));
                button.setTextColor(Color.parseColor("#353535"));
            }
            newRelease.setTextColor(Color.parseColor("#4fb2d6"));
            mPopupWindow.dismiss();
            currentSelect = "";
            if (viewPager.getCurrentItem() == 0) {
                NeighborFragment neighborFragment = (NeighborFragment) mFragments.get(0);
                neighborFragment.setList2(0);
            } else {
                NeighborReplyFragment neighborFragment = (NeighborReplyFragment) mFragments.get(1);
                neighborFragment.setList2(0);
            }
        });
        buttonList.add((TextView) (popupView.findViewById(R.id.newReply)));
        popupView. findViewById(R.id.newReply).setOnClickListener(v -> {

            for (TextView button : buttonList) {
                button.setBackgroundColor(Color.parseColor("#00000000"));
                button.setTextColor(Color.parseColor("#353535"));
            }
            ((TextView) (popupView.findViewById(R.id.newReply))).setTextColor(Color.parseColor("#4fb2d6"));
            mPopupWindow.dismiss();
            StatisticsUtil.sendStatistics(mUserHelper.getSid(), "邻居圈-" + mHelper.getApartmentName() + "-互动", getThisContext());

            Intent intent = new Intent(getThisContext(), InteractActivity.class);

            startActivity(intent);


        });
        buttonList.add((TextView) (popupView.findViewById(R.id.care)));
        popupView.findViewById(R.id.care).setOnClickListener(v -> {
            for (int i = 0; i < buttonList.size(); i++) {
                buttonList.get(i).setBackgroundColor(Color.parseColor("#000000"));
                buttonList.get(i).setTextColor(Color.parseColor("#353535"));
            }
            ((TextView) (popupView.findViewById(R.id.care))).setTextColor(Color.parseColor("#4fb2d6"));
            StatisticsUtil.sendStatistics(mUserHelper.getSid(), "邻居圈-" + mHelper.getApartmentName() + "-我的关注", getThisContext());

            Intent intent = new Intent(getThisContext(), MyCareActivity.class);
            startActivity(intent);
            mPopupWindow.dismiss();
        });
        mPopupWindow = new PopupWindow(popupView, (int) (getScreenWidth() * 0.5166), LinearLayout.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setAnimationStyle(R.style.expand);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));

        neighborShowIcon.setBackgroundResource(R.drawable.neighbor_up);
        Log.i("222", "neighborShow: "+neighborShow.getWidth());
        Log.i("222", "neighborShow: "+getScreenWidth());
        int  Offset=(int)(neighborShow.getWidth()-getScreenWidth() * 0.5166)/2;
    mPopupWindow.showAsDropDown(neighborShow, Offset, 0);
        mPopupWindow.setOnDismissListener(() -> neighborShowIcon.setBackgroundResource(R.drawable.neighbor_down));
    }


    private FragmentPagerAdapter adapter =new FragmentPagerAdapter(getSupportFragmentManager()){
        @Override
        public CharSequence getPageTitle(int position) {

            return title[position];
        }



        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {

            return 2;
        }
    };

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (!isFocusChange) {
            initFragment();
          init();
            loader();
            isFocusChange=true;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SpUtil.put(getThisContext(), "canRefreshAdapter", false);
        if (SpUtil.getString(getThisContext(), "neighborReplyTime")== null)
        SpUtil.put(getThisContext(), "neighborReplyTime", DateUtil.getDateString());
        for(NeighborPostTypeTo typeTo:typeList){
            if (SpUtil.getString(getThisContext(),typeTo.getTypeSid()+"neighborReplyTime")==null)
                SpUtil.put(getThisContext(), typeTo.getTypeSid() + "neighborReplyTime", DateUtil.getDateString());
        }
    }



}
