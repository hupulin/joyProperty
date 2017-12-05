package com.joy.property.shop;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.ToxicBakery.viewpager.transforms.ABaseTransformer;
import com.ToxicBakery.viewpager.transforms.ForegroundToBackgroundTransformer;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jinyi.ihome.infrastructure.MessageToBulk;
import com.jinyi.ihome.module.newshop.AddCarParam;
import com.jinyi.ihome.module.newshop.CommentParam;
import com.jinyi.ihome.module.newshop.GoodsApiTo;
import com.jinyi.ihome.module.newshop.GoodsCommentTo;
import com.jinyi.ihome.module.newshop.GoodsDetail;
import com.jinyi.ihome.module.newshop.GoodsDetailParam;
import com.jinyi.ihome.module.newshop.GoodsDetailPicTo;
import com.jinyi.ihome.module.newshop.ImmediateBuyParam;
import com.jinyi.ihome.module.newshop.ImmediatelyGoodsTo;
import com.joy.common.api.ApiClientBulk;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.NewShopApi;
import com.joy.common.widget.UploadImage;
import com.joy.library.utils.DateUtil;
import com.joy.property.MainApp;
import com.joy.property.R;
import com.joy.property.base.BaseActivity;
import com.joyhome.nacity.app.photo.util.PublicWay;
import com.joyhome.nacity.app.propertyCenter.NetworkImageHolderView;
import com.joy.property.shop.adapter.GoodsCommentAdapter;
import com.joy.property.shop.shoputil.CarNumberUtil;
import com.joy.property.shop.shoputil.TimeTextViewUtil;
import com.joyhome.nacity.app.util.CustomDialog;
import com.joyhome.nacity.app.util.CustomDialogFragment;
import com.joyhome.nacity.app.util.SpUtil;
import com.joyhome.nacity.app.util.htmlText.HtmlTextView;
import com.mob.tools.utils.UIHandler;
import com.squareup.picasso.Picasso;
import com.zhy.android.percent.support.PercentLinearLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * Created by Admin on 2014-11-17.
 * 团购详情
 */
public class GoodsDetailActivity extends BaseActivity
        implements OnClickListener, ViewPager.OnPageChangeListener, PlatformActionListener, Handler.Callback {
    private TextView tvMySubmission;
    private TextView tvCallDetail;
    private ImageView ivCursor;

    private int one;
    private int pre;
    private ListView listView;
    private TextView mTitle;

    private PullToRefreshListView pullListView;
    private RelativeLayout commentLayout;
    private String goodsSid;
    private List<GoodsCommentTo> commentList=new ArrayList<>();
    private List<GoodsCommentTo> goodCommentList=new ArrayList<>();
    private List<GoodsCommentTo> middleCommentList=new ArrayList<>();
    private List<GoodsCommentTo> badCommentList=new ArrayList<>();
    private List<GoodsCommentTo> textPictureList=new ArrayList<>();
    private GoodsCommentAdapter adapter;
    private TextView goodComment;
    private TextView allComment;
    private TextView badComment;
    private TextView middleComment;
    private LinearLayout commentTypeLayout;
    private TextView shopCarNumber;
    private GoodsDetail goodsDetail;
    private View headView;
    private ImageView backTop;
    private ConvenientBanner autoRow;
    private List<GoodsDetailPicTo> picList=new ArrayList<>();
    private ImageView noData;
    private HtmlTextView htmlText;
    private PercentLinearLayout picTextLayout;
    private int commentType;
    private ScrollView scrollView;
    private int pageIndex;
    private TextView footView;
    private LinearLayout goodsInvalid;
    private LinearLayout goodsPurchaseLayout;
    private String priceRightText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_detail);
        findById();
        getData();
        onRefresh();
        setBackTop();
        PublicWay.activityList.add(this);
    }

    private void onRefresh() {
        pullListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageIndex++;

                if (commentType == 0)
                    getAllComment(pageIndex, false);
                else if (commentType == 1)
                    getGoodComment(pageIndex);
                else if (commentType == 2)
                    getMiddleComment(pageIndex);
                else if (commentType == 3)
                    getBadComment(pageIndex);

            }
        });
    }


    private void findById() {
        findViewById(R.id.purchase).setOnClickListener(this);
        findViewById(R.id.goods_share).setOnClickListener(this);
        pullListView = (PullToRefreshListView) findViewById(R.id.listView);
        pullListView.setMode(PullToRefreshBase.Mode.PULL_UP_TO_REFRESH);
        listView= pullListView.getRefreshableView();
        adapter = new GoodsCommentAdapter(getThisContext());
        adapter.setList(commentList);
        listView.setAdapter(adapter);
        listView.setClickable(false);
        listView.setLongClickable(false);
        goodsSid=getIntent().getStringExtra("GoodsSid");
        shopCarNumber = (TextView) findViewById(R.id.shopCarNumber);
        findViewById(R.id.add_car).setOnClickListener(this);
        backTop = (ImageView) findViewById(R.id.backTop);
        backTop.setOnClickListener(this);
        findViewById(R.id.carLayout).setOnClickListener(this);
        findViewById(R.id.iv_back).setOnClickListener(this);
        noData = (ImageView) findViewById(R.id.noData);
        CarNumberUtil.getCarNumber(mUserHelperBulk.getSid(),getThisContext(),shopCarNumber);
        htmlText = (HtmlTextView) findViewById(R.id.htmlText);
        picTextLayout = (PercentLinearLayout) findViewById(R.id.picTextLayout);
        scrollView = (ScrollView) findViewById(R.id.scrollView);
        goodsInvalid = (LinearLayout) findViewById(R.id.goodsInvalid);
        goodsPurchaseLayout = (LinearLayout) findViewById(R.id.goods_purchase_layout);
    }
    private void getData() {
        NewShopApi api= ApiClientBulk.create(NewShopApi.class);
        GoodsDetailParam param=new GoodsDetailParam();
        Bundle bundle = getIntent().getExtras();//获取一个句柄
        if (TextUtils.isEmpty(goodsSid))
            goodsSid=bundle.getString("GoodsSid");
        param.setGoodsId(goodsSid);
        param.setGoodsType("0");
        Log.i("222", "getData: "+param.toString());
        CustomDialogFragment dialogFragment=new CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "");
        api.getGoodsDetail(param, new HttpCallback<GoodsApiTo>(getThisContext()) {
            @Override
            public void success(GoodsApiTo msg, Response response) {
                dialogFragment.dismiss();
                if (msg!=null&&msg.getCode() == 0) {
                    goodsInvalid.setVisibility(View.GONE);
                    goodsPurchaseLayout.setVisibility(View.GONE);
                    if (msg.getGoodsApiVo() != null) {

                        scrollHeadView(msg.getGoodsApiVo());
                        goodsDetail = msg.getGoodsApiVo();
                        textPictureList.clear();
                        GoodsCommentTo goodsCommentTo = new GoodsCommentTo();
                        goodsCommentTo.setGoodsDetailsStr(goodsDetail.getGoodsDetailsStr());
                        textPictureList.add(goodsCommentTo);
                        adapter.setList(textPictureList);
                        adapter.notifyDataSetChanged();

                    }

                    if (msg.getGoodsPicApiVoList() != null) {
                        setAutoRow(msg.getGoodsPicApiVoList());
                        picList.addAll(msg.getGoodsPicApiVoList());
                    }
                }else
                    goodsInvalid.setVisibility(View.VISIBLE);
                goodsPurchaseLayout.setVisibility(View.VISIBLE);
            }
        });
    }
    private void initIntentDatas() {

    }
    public void setHeadView(GoodsDetail goods){

        getBadComment(0);
        getGoodComment(0);
        getMiddleComment(0);
        headView = View.inflate(getThisContext(), R.layout.goods_detail_head_view, null);

        listView.addHeaderView(headView);

        commentLayout = (RelativeLayout) headView.findViewById(R.id.commentLayout);
        autoRow = (ConvenientBanner) headView.findViewById(R.id.autoRow);
        setAutoRow(picList);
        ImageView propertySend = (ImageView) headView.findViewById(R.id.propertySend);
        TextView bulkTitle = (TextView) headView. findViewById(R.id.bulk_title);
        ImageView bulkIcon = (ImageView) headView. findViewById(R.id.bulk_icon);
        TextView disCount = (TextView) headView.findViewById(R.id.discount);
        TextView originalPricce = (TextView) headView.findViewById(R.id.original_price);
        TextView bulkAmount = (TextView) headView.findViewById(R.id.bulk_amount);
        TextView saleType = (TextView) headView.findViewById(R.id.saleType);
        TimeTextViewUtil countTime = (TimeTextViewUtil) headView.findViewById(R.id.countTime);
        tvMySubmission = (TextView) headView.findViewById(R.id.tv_my_submission);
        commentTypeLayout = (LinearLayout) headView.findViewById(R.id.layout);
        tvMySubmission.setOnClickListener(this);
        tvCallDetail = (TextView) headView.findViewById(R.id.tv_call_detail);
        tvCallDetail.setOnClickListener(this);
        ivCursor = (ImageView) headView. findViewById(R.id.iv_cursor);
        TextView nowPrice = (TextView) headView. findViewById(R.id.now_price);
        TextView priceRight = (TextView) headView.findViewById(R.id.now_price_right);
        goodComment = (TextView) headView.findViewById(R.id.good_comment);
        goodComment.setOnClickListener(this);
        allComment = (TextView) headView.findViewById(R.id.all_comment);
        allComment.setOnClickListener(this);
        badComment = (TextView) headView.findViewById(R.id.bad_comment);
        badComment.setOnClickListener(this);
        middleComment = (TextView) headView.findViewById(R.id.medium_comment);
        middleComment.setOnClickListener(this);
        ivCursor.setX((float) (getScreenWidth() * 0.61));
        tvMySubmission.setTextColor(Color.parseColor("#353535"));
        tvCallDetail.setTextColor(Color.parseColor("#f17834"));
        bulkTitle.setText("\u3000　" + goods.getGoodsName());


        if ("团购".equals(goods.getIsGroup())){
            bulkIcon.setVisibility(View.VISIBLE);
            bulkTitle.setText("\u3000　" + goods.getGoodsName());
            disCount.setVisibility(View.VISIBLE);
            originalPricce.setVisibility(View.VISIBLE);
            if (Double.valueOf(goods.getDiscountPercentage())<100)
            {

                disCount.setVisibility(View.VISIBLE);
                disCount.setText((float) (goods.getDiscountPercentage() / 10) + "折");
            }
            else
                disCount.setVisibility(View.GONE);
        }else {
            bulkIcon.setVisibility(View.GONE);
            bulkTitle.setText(goods.getGoodsName());
            disCount.setVisibility(View.INVISIBLE);
            originalPricce.setVisibility(View.INVISIBLE);
        }

        nowPrice.setText((int)goods.getCurrentPrice()+"");
        priceRightText=goods.getCurrentPrice()+"0";
        priceRightText=priceRightText.contains(".")?priceRightText.substring(priceRightText.indexOf("."),priceRightText.length()):".00";
        priceRight.setText(priceRightText);

        originalPricce.setText(goods.getRetailPrice() + "");
        originalPricce.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        bulkAmount.setText(goods.getSalesNum());
        saleType.setText(goods.getStoresName());
        saleType.setOnClickListener(view -> jumpShop(goods.getMerchantId(),goods.getSalesType(),goods.getStoresName()));

        // if ("快递配送".equals(goods.getDistributionMode()))
        propertySend.setVisibility(View.GONE);
        if (goods.getActivityEndTime()!=null) {
            countTime.setTimes(DateUtil.getFormatDateLongTime(goods.getActivityEndTime()).getTime() - System.currentTimeMillis());
            countTime.run();
        }else
            countTime.setVisibility(View.INVISIBLE);

        autoRow.startTurning(5000);

        adapter.notifyDataSetChanged();
    }
    @SuppressLint("setJavaScriptEnabled")
    private void setCursor() {
        tvMySubmission.setText("商品详情");
        tvCallDetail.setText("商品评价");
        // 设置图片的默认位置
        Matrix matrix = new Matrix();
        // 获得图片的宽度
        Bitmap lineBm = BitmapFactory.decodeResource(getResources(),
                R.drawable.navigation_ic);
        int imgWidth = lineBm.getWidth();
        // 获得屏幕的宽度
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        int screenWidth = outMetrics.widthPixels;
        // 可用进行设置图片位置
        int dx = (screenWidth / 2 - imgWidth) / 2;
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) ivCursor.getLayoutParams();
        params.leftMargin = dx;
        matrix.postTranslate(dx, 0);
        ivCursor.setImageMatrix(matrix);
        one = screenWidth / 2;


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                onBackPressed();

                break;
            case R.id.purchase:
                PurchaseDialogShow(false);

                break;
            case R.id.tv_my_submission:
                htmlText.setHtmlFromString(goodsDetail.getGoodsDetailsStr(),false);
                pullListView.setVisibility(View.GONE);
                listView.setVisibility(View.GONE);
                scrollView.setVisibility(View.VISIBLE);
                noData.setVisibility(View.GONE);
                break;
            case R.id.add_car:
                PurchaseDialogShow(true);
                break;

            case R.id.tv_call_detail:
                htmlText.setHtmlFromString("", false);
                pullListView.setVisibility(View.VISIBLE);
                listView.setVisibility(View.VISIBLE);
                scrollView.setVisibility(View.GONE);
                if (listView.getHeaderViewsCount()==1)
                    setHeadView(goodsDetail);

                adapter.notifyDataSetChanged();


                setCommentList();
                break;
            case R.id.goods_share:
                shareShow();
                break;
            case R.id.all_comment:
                commentType=0;
                pageIndex=0;
                if (commentList.size()>15)
                    pullListView.setMode(PullToRefreshBase.Mode.PULL_UP_TO_REFRESH);
                else
                    pullListView.setMode(PullToRefreshBase.Mode.DISABLED);
                setAllComment();
                break;
            case R.id.good_comment:
                if (goodCommentList.size()>15)
                    pullListView.setMode(PullToRefreshBase.Mode.PULL_UP_TO_REFRESH);
                else
                    pullListView.setMode(PullToRefreshBase.Mode.DISABLED);
                pageIndex=0;
                commentType=1;
                setGoodsCommnet();
                break;
            case R.id.medium_comment:
                if (middleCommentList.size()>15)
                    pullListView.setMode(PullToRefreshBase.Mode.PULL_UP_TO_REFRESH);
                else
                    pullListView.setMode(PullToRefreshBase.Mode.DISABLED);
                pageIndex=0;
                commentType=2;
                setMiddleComment();
                break;
            case R.id.bad_comment:
                if (badCommentList.size()>15)
                    pullListView.setMode(PullToRefreshBase.Mode.PULL_UP_TO_REFRESH);
                else
                    pullListView.setMode(PullToRefreshBase.Mode.DISABLED);
                pageIndex=0;
                commentType=3;
                setBadComment();
                break;
            case R.id.backTop:
                listView.setSelection(0);
                break;
            case R.id.carLayout:
                Intent intent=new Intent(getThisContext(),ShoppingCarActivity.class);
                startActivity(intent);
                goToAnimation(1);
                break;
        }
    }

    @Override
    protected Context getThisContext() {
        return getApplicationContext();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        htmlText.setHtmlFromString("", false);
    }

    @Override
    protected String toPageName() {
        return "团购详情页";
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position == 0) {

            tvMySubmission.setTextColor(0xff969696);
            tvCallDetail.setTextColor(0xff979797);
        } else if (position == 1) {
            tvMySubmission.setTextColor(0xff979797);
            tvCallDetail.setTextColor(0xff969696);
        }
        TranslateAnimation anim = new TranslateAnimation(one * pre, one * position, 0, 0);
        pre = position;
        anim.setDuration(300);
        anim.setFillAfter(true);
        ivCursor.startAnimation(anim);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void PurchaseDialogShow(boolean isAddCar) {
        final CustomDialog dialog = new CustomDialog(this, R.layout.dialog_detail_purchase, R.style.myDialogTheme);
        LinearLayout dismissLayout= (LinearLayout) dialog.findViewById(R.id.dismissLayout);
        TextView confirmPurchase = (TextView) dialog.findViewById(R.id.confirmPurchase);
        ImageView purchaseAdd = (ImageView)dialog. findViewById(R.id.purchaseAdd);
        TextView purchaseNumber = (TextView) dialog.findViewById(R.id.purchaseNumber);
        ImageView purchaseReduce = (ImageView) dialog.findViewById(R.id.purchaseReduce);
        TextView purchaseName = (TextView) dialog.findViewById(R.id.purchaseName);
        TextView purchaseSurplus = (TextView) dialog.findViewById(R.id.purchaseSurplus);
        ImageView purchaseClose = (ImageView)dialog. findViewById(R.id.purchaseClose);
        ImageView purchaseImage = (ImageView) dialog.findViewById(R.id.purchaseImage);
        ImageView purchaseImageBg = (ImageView)dialog. findViewById(R.id.purchaseImageBg);
        RelativeLayout layout = (RelativeLayout)dialog. findViewById(R.id.layout);

        confirmPurchase.setOnClickListener(v -> {
            if (isAddCar){
                addCar(goodsSid,purchaseNumber.getText().toString());
            }else
                immediatelyBuy(purchaseNumber.getText().toString());
            dialog.dismiss();
        });
        purchaseNumber.setText(goodsDetail.getMinSalesNum() + "");
        purchaseName.setText(goodsDetail.getGoodsName());
        if (picList!=null&&picList.size()>0)
            Picasso.with(getThisContext()).load(MainApp.getPicassoImagePath(goodsDetail.getPicUrl())).into(purchaseImage);

        purchaseClose.setOnClickListener(v -> dialog.dismiss());
        purchaseSurplus.setText("库存" + goodsDetail.getNum() + "件");
        purchaseAdd.setOnClickListener(view -> {
            if (Integer.valueOf(purchaseNumber.getText().toString())>=goodsDetail.getNum()){
                ToastShowLong(getThisContext(),"您已超过库存量了~");
                return;
            }
            if (Integer.valueOf(purchaseNumber.getText().toString())>=goodsDetail.getMaxSalesNum()){
                ToastShowLong(getThisContext(),"您已超过最大购买量~");
                return;
            }
            purchaseNumber.setText(Integer.valueOf(purchaseNumber.getText().toString()) + 1 + "");
        });
        purchaseReduce.setOnClickListener(view -> {
            if (Integer.valueOf(purchaseNumber.getText().toString())<=goodsDetail.getMinSalesNum()){
                ToastShowLong(getThisContext(),"不能再少了~");
                return;
            }
            if(Integer.valueOf(purchaseNumber.getText().toString())>1){
                purchaseNumber.setText(Integer.valueOf(purchaseNumber.getText().toString()) - 1 + "");
            }
        });
        dismissLayout.setOnClickListener(view -> dialog.dismiss());
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        dialog.show();

    }
    private void shareShow() {

        final CustomDialog dialog = new CustomDialog(getThisContext(),
                R.layout.goods_share, R.style.myDialogTheme);

        TextView ivCancel = (TextView) dialog.findViewById(R.id.cancel);
        LinearLayout mWeChat = (LinearLayout) dialog.findViewById(R.id.wechat);
        LinearLayout mQQ = (LinearLayout) dialog.findViewById(R.id.QQ);

        final LinearLayout mMoment = (LinearLayout) dialog.findViewById(R.id.moment);
        final Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.share_ic);
        ivCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        ShareSDK.initSDK(getThisContext());
        mWeChat.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Wechat.ShareParams wsp = new Wechat.ShareParams();
                wsp.setShareType(Platform.SHARE_IMAGE);
                wsp.setImageData(bitmap);
                wsp.setTitle("悦嘉家，等你回家");
                Platform plat = ShareSDK.getPlatform(getThisContext(), Wechat.NAME);
                plat.setPlatformActionListener(GoodsDetailActivity.this);
                plat.share(wsp);
            }
        });
        mQQ.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                QQ.ShareParams shareParams = new QQ.ShareParams();
                shareParams.setShareType(Platform.SHARE_IMAGE);
                shareParams.setTitle("悦嘉家，等你回家");
                shareParams.setImagePath(UploadImage.getInstance(getThisContext()).saveBitmap(bitmap));
                Platform qq = ShareSDK.getPlatform(getThisContext(), QQ.NAME);
                qq.share(shareParams);
            }
        });

        mMoment.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                WechatMoments.ShareParams shareParams = new WechatMoments.ShareParams();
                shareParams.setShareType(Platform.SHARE_IMAGE);
                shareParams.setImageData(bitmap);
                shareParams.setTitle("悦嘉家，等你回家");
                Platform moment = ShareSDK.getPlatform(getThisContext(), WechatMoments.NAME);
                moment.setPlatformActionListener(GoodsDetailActivity.this);
                moment.share(shareParams);
            }
        });
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    @Override
    public void onComplete(Platform platform, int action, HashMap<String, Object> stringObjectHashMap) {
        Message msg = new Message();
        msg.arg1 = 1;
        msg.arg2 = action;
        msg.obj = platform;
        UIHandler.sendMessage(msg, this);
    }

    @Override
    public void onError(Platform platform, int action, Throwable throwable) {
        throwable.printStackTrace();
        Message msg = new Message();
        msg.arg1 = 2;
        msg.arg2 = action;
        msg.obj = throwable;
        UIHandler.sendMessage(msg, this);
    }

    @Override
    public void onCancel(Platform platform, int action) {
        Message msg = new Message();
        msg.arg1 = 3;
        msg.arg2 = action;
        msg.obj = platform;
        UIHandler.sendMessage(msg, this);
    }

    @Override
    public boolean handleMessage(Message msg) {
        String text =  "";
        switch (msg.arg1) {
            case 1: {
                // 成功
                //Platform plat = (Platform) msg.obj;
                text = "分享成功";
            }
            break;
            case 2: {
                // 失败
                if ("WechatClientNotExistException".equals(msg.obj.getClass().getSimpleName())) {
                    text = getString(R.string.wechat_client_inavailable);
                } else if ("WechatTimelineNotSupportedException".equals(msg.obj.getClass().getSimpleName())) {
                    text = getString(R.string.wechat_client_inavailable);
                } else {
                    text = getString(R.string.share_failed);
                }
            }
            break;
            case 3: {
                // 取消
                //Platform plat = (Platform) msg.obj;
                text = "取消分享";
            }
            break;
        }

        Toast.makeText(getThisContext(), text, Toast.LENGTH_LONG).show();
        return false;
    }
    public void setCommentList(){
        commentLayout.setVisibility(View.VISIBLE);
        getAllComment(0, false);
    }
    public void getAllComment(int index,boolean isDisplay){
        NewShopApi api=ApiClientBulk.create(NewShopApi.class);
        CommentParam param=new CommentParam();
        param.setGoodsId(goodsSid);
        param.setPageSize("20");
        param.setCurrentPage(index + 1 + "");
        CustomDialogFragment dialogFragment=new CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "");
        api.getAllComment(param, new HttpCallback<MessageToBulk<List<GoodsCommentTo>>>(getThisContext()) {
            @Override
            public void success(MessageToBulk<List<GoodsCommentTo>> msg, Response response) {
                dialogFragment.dismiss();
                setRefreshComplement();
                if (msg.getCode() == 0) {
                    if (index == 0) {
                        commentList.clear();
                    }
                    if (msg.getGoodsAllEvaluateList() != null) {
                        commentList.addAll(msg.getGoodsAllEvaluateList());
                        allComment.setText("全部(" + msg.getTotal() + ")");
                        if (commentList.size() > 15)
                            pullListView.setMode(PullToRefreshBase.Mode.PULL_UP_TO_REFRESH);
                        else
                            pullListView.setMode(PullToRefreshBase.Mode.DISABLED);
                        setNoDataIcon(commentList.size());
                        adapter.setList(commentList);
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void failure(RetrofitError error) {
                setRefreshComplement();
                dialogFragment.dismiss();
            }
        });
    }
    public void getGoodComment(int index){
        NewShopApi api=ApiClientBulk.create(NewShopApi.class);
        CommentParam param=new CommentParam();
        param.setGoodsId(goodsSid);
        param.setPageSize("20");
        param.setCurrentPage(index + 1 + "");
        CustomDialogFragment dialogFragment=new CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "");
        api.getGoodComment(param, new HttpCallback<MessageToBulk<List<GoodsCommentTo>>>(getThisContext()) {
            @Override
            public void success(MessageToBulk<List<GoodsCommentTo>> msg, Response response) {
                dialogFragment.dismiss();
                setRefreshComplement();
                if (msg.getCode() == 0) {
                    if (index == 0)
                        goodCommentList.clear();
                    if (msg.getGoodsAllEvaluateList() != null) {
                        goodCommentList.addAll(msg.getGoodsAllEvaluateList());
                        goodComment.setText("好评(" + msg.getTotal() + ")");
                        if (index >= 1)
                            adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void failure(RetrofitError error) {
                setRefreshComplement();
                dialogFragment.dismiss();
            }
        });
    }
    public void getMiddleComment(int index){
        NewShopApi api=ApiClientBulk.create(NewShopApi.class);
        CommentParam param=new CommentParam();
        param.setGoodsId(goodsSid);
        param.setPageSize("20");
        param.setCurrentPage(index + 1 + "");
        CustomDialogFragment dialogFragment=new CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(),"");
        api.getMiddleComment(param, new HttpCallback<MessageToBulk<List<GoodsCommentTo>>>(getThisContext()) {
            @Override
            public void success(MessageToBulk<List<GoodsCommentTo>> msg, Response response) {
                dialogFragment.dismiss();
                setRefreshComplement();
                if (msg.getCode() == 0) {
                    if (index == 0) {
                        middleCommentList.clear();
                    }
                    if (msg.getGoodsAllEvaluateList() != null) {
                        middleCommentList.addAll(msg.getGoodsAllEvaluateList());
                        middleComment.setText("中评(" + msg.getTotal() + ")");
                        if (index >= 1)
                            adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void failure(RetrofitError error) {
                setRefreshComplement();
                dialogFragment.dismiss();
            }
        });
    }
    public void getBadComment(int index){
        NewShopApi api=ApiClientBulk.create(NewShopApi.class);
        CommentParam param=new CommentParam();
        param.setGoodsId(goodsSid);
        param.setPageSize("20");
        param.setCurrentPage(index + 1 + "");
        CustomDialogFragment dialogFragment=new CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "");
        api.getBadComment(param, new HttpCallback<MessageToBulk<List<GoodsCommentTo>>>(getThisContext()) {
            @Override
            public void success(MessageToBulk<List<GoodsCommentTo>> msg, Response response) {
                dialogFragment.dismiss();
                setRefreshComplement();
                if (msg.getCode() == 0) {
                    if (index == 0) {
                        badCommentList.clear();
                    }
                    if (msg.getGoodsAllEvaluateList() != null) {
                        badCommentList.addAll(msg.getGoodsAllEvaluateList());
                        badComment.setText("差评(" + msg.getTotal() + ")");
                        if (index >= 1)
                            adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void failure(RetrofitError error) {
                setRefreshComplement();
                dialogFragment.dismiss();
            }
        });
    }
    public void setGoodsCommnet(){
        noData.setVisibility(View.GONE);
        setNoDataIcon(goodCommentList.size());
        goodComment.setBackgroundResource(R.drawable.goods_comment_select_bg);
        goodComment.setTextColor(Color.parseColor("#ffffff"));
        allComment.setBackgroundResource(R.drawable.goods_comment_unselect_bg);
        allComment.setTextColor(Color.parseColor("#353535"));
        middleComment.setBackgroundResource(R.drawable.goods_comment_unselect_bg);
        middleComment.setTextColor(Color.parseColor("#353535"));
        badComment.setBackgroundResource(R.drawable.goods_comment_unselect_bg);
        badComment.setTextColor(Color.parseColor("#353535"));
        adapter.setList(goodCommentList);
        adapter.notifyDataSetChanged();

    }
    public void setAllComment(){
        noData.setVisibility(View.GONE);
        setNoDataIcon(commentList.size());
        allComment.setBackgroundResource(R.drawable.goods_comment_select_bg);
        allComment.setTextColor(Color.parseColor("#ffffff"));
        goodComment.setBackgroundResource(R.drawable.goods_comment_unselect_bg);
        goodComment.setTextColor(Color.parseColor("#353535"));
        middleComment.setBackgroundResource(R.drawable.goods_comment_unselect_bg);
        middleComment.setTextColor(Color.parseColor("#353535"));
        badComment.setBackgroundResource(R.drawable.goods_comment_unselect_bg);
        badComment.setTextColor(Color.parseColor("#353535"));
        adapter.setList(commentList);

        adapter.notifyDataSetChanged();

    }
    public void setMiddleComment(){
        noData.setVisibility(View.GONE);
        setNoDataIcon(middleCommentList.size());
        middleComment.setBackgroundResource(R.drawable.goods_comment_select_bg);
        middleComment.setTextColor(Color.parseColor("#ffffff"));
        goodComment.setBackgroundResource(R.drawable.goods_comment_unselect_bg);
        goodComment.setTextColor(Color.parseColor("#353535"));
        allComment.setBackgroundResource(R.drawable.goods_comment_unselect_bg);
        allComment.setTextColor(Color.parseColor("#353535"));
        badComment.setBackgroundResource(R.drawable.goods_comment_unselect_bg);
        badComment.setTextColor(Color.parseColor("#353535"));
        adapter.setList(middleCommentList);
        adapter.notifyDataSetChanged();
    }
    public void setBadComment(){
        noData.setVisibility(View.GONE);
        setNoDataIcon(badCommentList.size());
        badComment.setBackgroundResource(R.drawable.goods_comment_select_bg);
        badComment.setTextColor(Color.parseColor("#ffffff"));
        goodComment.setBackgroundResource(R.drawable.goods_comment_unselect_bg);
        goodComment.setTextColor(Color.parseColor("#353535"));
        allComment.setBackgroundResource(R.drawable.goods_comment_unselect_bg);
        allComment.setTextColor(Color.parseColor("#353535"));
        middleComment.setBackgroundResource(R.drawable.goods_comment_unselect_bg);
        middleComment.setTextColor(Color.parseColor("#353535"));
        adapter.setList(badCommentList);
        adapter.notifyDataSetChanged();
    }
    public  void setPictureText() {
        commentLayout.setVisibility(View.GONE);
        adapter.setList(textPictureList);
        adapter.notifyDataSetChanged();
    }
    public void addCar(String goodsId,String num){
        NewShopApi api=ApiClientBulk.create(NewShopApi.class);
        AddCarParam param=new AddCarParam();
        param.setGoodsId(goodsId);
        param.setUserId(mUserHelperBulk.getSid());
        param.setNum(num);
        final CustomDialogFragment dialogFragment = new CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "");
        api.addCar(param, new HttpCallback<MessageToBulk>(getThisContext()) {
            @Override
            public void success(MessageToBulk msg, Response response) {
                dialogFragment.dismiss();
                if (msg.getCode()==0) {
                    ToastShowLong(getThisContext(), "加入购物车成功");
                    shopCarNumber.setText(msg.getTotal() + "");
                    SpUtil.put(getThisContext(), "HaveAddCar",true);
                }else
                    ToastShowLong(getThisContext(),msg.getMessage());
            }

            @Override
            public void failure(RetrofitError error) {
                dialogFragment.dismiss();

            }
        });
    }
    public void immediatelyBuy(String goodsNumber){
        NewShopApi api=ApiClientBulk.create(NewShopApi.class);
        ImmediateBuyParam param=new ImmediateBuyParam();
        param.setRetailPrice(goodsDetail.getCurrentPrice() + "");
        param.setGoodsId(goodsDetail.getGoodsId() + "");
        param.setMerchantId(goodsDetail.getMerchantId());
        param.setGoodsNum(goodsNumber);
        param.setDiscountPercentage("100");
        param.setUserId(mUserHelperBulk.getSid());
        api.immediatelyBuy(param, new HttpCallback<MessageToBulk<ImmediatelyGoodsTo>>(getThisContext()) {
            @Override
            public void success(MessageToBulk<ImmediatelyGoodsTo> msg, Response response) {
                if (msg.getCode() == 0) {
                    Intent intent = new Intent(getThisContext(), ImmediatelyConfirmOlderActivity.class);
                    intent.putExtra("CartSettleGoodsTo", msg.getImmediateBuyGoodVo());
                    intent.putExtra("IsSeaBuy",msg.getIsSeaBuy());
                    startActivity(intent);
                    goToAnimation(1);
                }else
                    ToastShowLong(getThisContext(),msg.getMessage());
            }

            @Override
            public void failure(RetrofitError error) {
                System.out.println(error + "");
            }
        });

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

    }

    public void setBackTop() {
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                if (headView != null) {
                    backTop.setAlpha((-headView.getY() - 800) / 200);
                }
            }
        });
    }
    private void setAutoRow(List<GoodsDetailPicTo>mList){
        String transforemerName =ForegroundToBackgroundTransformer.class.getSimpleName();
        try {
            Class cls = Class.forName("com.ToxicBakery.viewpager.transforms." + transforemerName);
            ABaseTransformer transforemer = (ABaseTransformer) cls.newInstance();
            autoRow.getViewPager().setPageTransformer(true, transforemer);

            if (transforemerName.equals("StackTransformer")){
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

        autoRow.setPages(NetworkImageHolderView::new, networkImages).setPageIndicator(new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused}).setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        goToAnimation(2);
        adapter.setNull();
    }
    public void setNoDataIcon(int size){
        if (size==0)
            noData.setVisibility(View.VISIBLE);
        else
            noData.setVisibility(View.GONE);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK)
            onBackPressed();
        return super.onKeyDown(keyCode, event);
    }
    public void scrollHeadView(GoodsDetail goods){
        headView = View.inflate(getThisContext(), R.layout.goods_detail_head_view, null);
        if (goods.getGoodsDetailsStr()!=null)
            htmlText.setHtmlFromString(goods.getGoodsDetailsStr(),false);
        commentLayout = (RelativeLayout) headView.findViewById(R.id.commentLayout);
        autoRow = (ConvenientBanner) headView.findViewById(R.id.autoRow);
        ImageView propertySend = (ImageView) headView.findViewById(R.id.propertySend);
        TextView bulkTitle = (TextView) headView. findViewById(R.id.bulk_title);
        ImageView bulkIcon = (ImageView) headView. findViewById(R.id.bulk_icon);
        TextView disCount = (TextView) headView.findViewById(R.id.discount);
        TextView originalPricce = (TextView) headView.findViewById(R.id.original_price);
        TextView bulkAmount = (TextView) headView.findViewById(R.id.bulk_amount);
        TextView saleType = (TextView) headView.findViewById(R.id.saleType);
        TimeTextViewUtil countTime = (TimeTextViewUtil) headView.findViewById(R.id.countTime);
        tvMySubmission = (TextView) headView.findViewById(R.id.tv_my_submission);
        commentTypeLayout = (LinearLayout) headView.findViewById(R.id.layout);
        tvMySubmission.setOnClickListener(this);
        tvCallDetail = (TextView) headView.findViewById(R.id.tv_call_detail);
        tvCallDetail.setOnClickListener(this);
        ivCursor = (ImageView) headView. findViewById(R.id.iv_cursor);
        TextView nowPrice = (TextView) headView. findViewById(R.id.now_price);
        TextView priceRight = (TextView) headView.findViewById(R.id.now_price_right);
        goodComment = (TextView) headView.findViewById(R.id.good_comment);
        goodComment.setOnClickListener(this);
        allComment = (TextView) headView.findViewById(R.id.all_comment);
        allComment.setOnClickListener(this);
        badComment = (TextView) headView.findViewById(R.id.bad_comment);
        badComment.setOnClickListener(this);
        middleComment = (TextView) headView.findViewById(R.id.medium_comment);
        middleComment.setOnClickListener(this);
        ivCursor.setX((float) (getScreenWidth() * 0.105));
        tvMySubmission.setTextColor(Color.parseColor("#f17834"));
        tvCallDetail.setTextColor(Color.parseColor("#353535"));

        //     setPictureText();
        noData.setVisibility(View.GONE);
        saleType.setText(goods.getStoresName());
        saleType.setOnClickListener(view -> jumpShop(goods.getMerchantId(),goods.getSalesType(),goods.getStoresName()));
        bulkTitle.setText("\u3000　"+goods.getGoodsName());

        if ("团购".equals(goods.getIsGroup())){
            bulkIcon.setVisibility(View.VISIBLE);
            bulkTitle.setText("\u3000　" + goods.getGoodsName());
            disCount.setVisibility(View.VISIBLE);
            originalPricce.setVisibility(View.VISIBLE);
            if (Double.valueOf(goods.getDiscountPercentage())<100)
            {

                disCount.setVisibility(View.VISIBLE);
                disCount.setText((float) (goods.getDiscountPercentage() / 10) + "折");
            }
            else
                disCount.setVisibility(View.GONE);
        }else {
            bulkIcon.setVisibility(View.GONE);
            bulkTitle.setText(goods.getGoodsName());
            disCount.setVisibility(View.INVISIBLE);
            originalPricce.setVisibility(View.INVISIBLE);
        }

        nowPrice.setText((int)goods.getCurrentPrice()+"");
        priceRightText=goods.getCurrentPrice()+"0";
        priceRightText=priceRightText.contains(".")?priceRightText.substring(priceRightText.indexOf("."),priceRightText.length()):".00";
        priceRight.setText(priceRightText);
        originalPricce.setText(goods.getRetailPrice() + "");
        originalPricce.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        bulkAmount.setText(goods.getSalesNum());

        // if ("快递配送".equals(goods.getDistributionMode()))
        propertySend.setVisibility(View.GONE);
        System.out.println(goods.getActivityEndTime()+"endTime");
        if (goods.getActivityEndTime()!=null) {
            countTime.setTimes(DateUtil.getFormatDateLongTime(goods.getActivityEndTime()).getTime() - System.currentTimeMillis());
            countTime.run();
        }else
            countTime.setVisibility(View.INVISIBLE);
        picTextLayout.addView(headView);
        autoRow.startTurning(5000);
    }
    public void setRefreshComplement(){
        pullListView.onRefreshComplete();
    }
    public void jumpShop(String shopSid,String name,String storeName){
        Intent intent;
        if ("自营商品".equals(name))
            intent=new Intent(getThisContext(),SelfShopActivity.class);
        else
            intent=new Intent(getThisContext(),MerchantShopActivity.class);
        intent.putExtra("ShopSid",shopSid);
        intent.putExtra("ShopName",storeName);
        startActivity(intent);
        goToAnimation(1);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (shopCarNumber!=null)
            CarNumberUtil.getCarNumber(mUserHelperBulk.getSid(),getThisContext(),shopCarNumber);
    }
}
