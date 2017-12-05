package com.joy.property.shop.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
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
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jinyi.ihome.infrastructure.MessageTo;
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
import com.jinyi.ihome.module.newshop.ShopListDetailTo;
import com.jinyi.ihome.module.purchase.GroupPurchaseTo;
import com.jinyi.ihome.module.shop.GoodsLabelTo;
import com.jinyi.ihome.module.shop.GoodsSpecificationsTo;
import com.joy.common.api.ApiClient;
import com.joy.common.api.ApiClientBulk;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.NewShopApi;
import com.joy.library.utils.HtmlUtil;
import com.joy.property.R;
import com.joy.property.MainApp;
import com.joy.property.base.BaseFragment;
import com.joy.property.neighborhood.RefreshEvent;
import com.joy.property.utils.flowlayout.FlowLayout;
import com.joyhome.nacity.app.propertyCenter.NetworkImageHolderView;
import com.joy.property.shop.CampaignListActivity;
import com.joy.property.shop.CommentListActivity;
import com.joy.property.shop.ImmediatelyConfirmOlderActivity;
import com.joy.property.shop.MerchantShopActivity;
import com.joy.property.shop.SelfShopActivity;
import com.joy.property.shop.ShoppingCarActivity;
import com.joy.property.shop.shoputil.CarNumberUtil;
import com.joyhome.nacity.app.util.CustomDialog;
import com.joyhome.nacity.app.util.CustomDialogFragment;
import com.joyhome.nacity.app.util.SpUtil;
import com.joy.property.utils.flowlayout.TagAdapter;
import com.joy.property.utils.flowlayout.TagFlowLayout;
import com.squareup.picasso.Picasso;


import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import retrofit.RetrofitError;
import retrofit.client.Response;
import rx.Observable;

@SuppressLint("ValidFragment")
public class GoodsDetailFragment extends BaseFragment implements View.OnClickListener {

    private ConvenientBanner autoRow;
    private String goodsSid;
    private TextView mImageGuide;
    private TextView shopName;
    private TextView commentCount;
    private ImageView headImageOne;
    private ImageView headImageTwo;
    private TextView nickNameOne;
    private TextView nickNameTwo;
    private TextView commentDateOne;
    boolean haveSpecification=false;
    private TextView commentDateTwo;
    private TextView commentContentOne;
    private TextView commentContentTwo;
    private TextView commentDescriptionOne;
    private TextView commentDescriptionTwo;
    private TextView shopDescription;
    private TextView expressMode;
    private TextView nowPrice;
    private TextView bulkAmount;
    private TextView imageGuide;
    private TextView expressCost;
    private LinearLayout commentOne;
    private LinearLayout commentTwo;
    private GoodsDetail goodsDetail;
    private List<TextView> tvs= new LinkedList<TextView>();
    //规格数组
    private String [] mNames;
    //选中的标签名字
    private int inventoryNum;
    //标签数组
    private String [] mLabels;
    private List<GoodsCommentTo> goodCommentList = new ArrayList<>();
    private List<GoodsCommentTo> goodCommentAllList = new ArrayList<>();
    public String specificationsName="";
    public String specificationsId="";
    public String purchaseNum;
    private List<TextView>flowViewList=new ArrayList<>();
    private List<GoodsDetailPicTo> picList = new ArrayList<>();
    private TagFlowLayout mFlowLayout;
    private List<GoodsSpecificationsTo> goodsSpecificationsList;
    public TextView specificationContent;
    public String retailPrice;
    private ImageView noAutoRow;
    private Boolean isFirst=true;//开始
    private String lastBuyCount;
    private String changeSpecificationsName;

    public GoodsDetailFragment(){

    }

    public GoodsDetailFragment(GoodsDetail goodsDetail,List<GoodsDetailPicTo>picList){
        this.goodsDetail=goodsDetail;
        this.picList=picList;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View mRootView   = inflater.inflate(R.layout.fragment_goods_detail, container, false);

        findById(mRootView);

        setAutoData();
        //拿取好评



        return mRootView;


    }

    private void setAutoData() {
        if (picList!=null&&picList.size()>0) {
            setAutoRow(picList);
        }else {
            autoRow.setVisibility(View.GONE);
            noAutoRow.setVisibility(View.VISIBLE);
            imageGuide.setVisibility(View.GONE);
        }
        if (goodsDetail!=null) {
            setData(goodsDetail);
            getGoodComment();
            getAllComment();
        }
    }


    private void findById(View view) {
        autoRow = (ConvenientBanner)view. findViewById(R.id.autoRow);
        autoRow.startTurning(5000);
        mFlowLayout = (TagFlowLayout)view.  findViewById(R.id.flowlayout);


        commentOne = (LinearLayout) view. findViewById(R.id.comment_one);
        commentTwo = (LinearLayout) view. findViewById(R.id.comment_two);

        view.findViewById(R.id.specification_rl).setOnClickListener(this);

        view.findViewById(R.id.comment_rl).setOnClickListener(this);


        nowPrice = (TextView) view.findViewById(R.id.now_price);


        commentCount = (TextView) view.findViewById(R.id.comment_count);
        bulkAmount = (TextView) view.findViewById(R.id.bulk_amount);
        imageGuide = (TextView) view.findViewById(R.id.image_guide);
        expressMode = (TextView) view.findViewById(R.id.express_mode);
        expressCost = (TextView) view.findViewById(R.id.tv_express_cost);
        headImageOne = (ImageView)view. findViewById(R.id.headImage);
        headImageTwo = (ImageView) view.findViewById(R.id.headImage_01);
        nickNameOne = (TextView) view.findViewById(R.id.tv_nickname);
        nickNameTwo = (TextView) view.findViewById(R.id.tv_nickname_01);
        commentDateOne = (TextView) view.findViewById(R.id.comment_date);
        commentDateTwo = (TextView) view.findViewById(R.id.comment_date_01);
        commentContentOne = (TextView)view.findViewById(R.id.comment_content);
        commentContentTwo = (TextView)view.findViewById(R.id.comment_content_01);
        commentDescriptionOne = (TextView)view.findViewById(R.id.comment_description);
        commentDescriptionTwo = (TextView)view.findViewById(R.id.comment_description_01);
        mImageGuide = (TextView)view.findViewById(R.id.image_guide);
        shopName = (TextView)view.findViewById(R.id.shop_name);
        shopDescription = (TextView)view.findViewById(R.id.shop_description);
        specificationContent = (TextView) view.findViewById(R.id.specificationContent);
        noAutoRow = (ImageView) view.findViewById(R.id.noAutoRow);
    }

    @Override
    protected Context getThisContext() {
        return getActivity();
    }



    private void setData(GoodsDetail goods) {
        shopName.setText(goods.getGoodsName());
        shopDescription.setText(goods.getParameterDescription());
        if(0==goods.getDistributionCost()){

            expressCost.setText("配送费用：包邮");
//            double trafficFee=0.00;
//            trafficFee=goods.getDistributionCost();
//            expressCost.setText("配送费用：¥"+trafficFee);
        }else{

            expressCost.setText("配送费用：¥"+goods.getDistributionCost());
        }
        expressMode.setText("配送方式：" + goods.getDistributionMode());
        bulkAmount.setText("已售" + goods.getFakeSalesNum() + "件");
        //设置规格中最低价格和规格数组
        goodsSpecificationsList = goods.getGoodsSpecificationsList();

        double minnum =goodsSpecificationsList.get(0).getRetailPrice();
        mNames=new String[goodsSpecificationsList.size()];
        for (int i=0;i< goodsSpecificationsList.size();i++) {
            mNames[i]= goodsSpecificationsList.get(i).getSpecificationsName();
            if(goodsSpecificationsList.get(i).getRetailPrice()<minnum)
            {
                minnum = goodsSpecificationsList.get(i).getRetailPrice();

                retailPrice=minnum+"";
            }
        }
        inventoryNum=goodsSpecificationsList.get(0).getInventoryNum();
        nowPrice.setText("¥"+((minnum+"").contains(".")?(((minnum+"").substring((minnum+"").lastIndexOf("."))).length()==3?minnum+"":minnum+"0"):minnum+".00"));
        //设置标签数组
        List<GoodsLabelTo> goodsLableList=goods.getGoodsLableList();
        mLabels=new String[goodsLableList.size()];
        for (int i=0;i<goodsLableList.size();i++) {
            mLabels[i]=goodsLableList.get(i).getLableName();
        }
        retailPrice=goodsSpecificationsList.get(0).getRetailPrice()+"";
        setLabel();
    }
    //设置图片轮播
    private void setAutoRow(List<GoodsDetailPicTo> mList) {

        imageGuide.setText(1+ "/" + mList.size());
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
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        //  initImageLoader();

        List<String> networkImages = new ArrayList<>();
        for (int i = 0; i < mList.size(); i++) {
            networkImages.add(MainApp.getPicassoImagePath(mList.get(i).getPicUrl()));


        }

        autoRow.setPages(NetworkImageHolderView::new, networkImages).setPageIndicator(new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused});

        autoRow.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                imageGuide.setText((position + 1) + "/" + networkImages.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.comment_rl:
                //去全部评价页面
                Intent intent01=new Intent(getThisContext(), CommentListActivity.class);
                intent01.putExtra("GoodsSid", goodsDetail.getGoodsId());
                intent01.putExtra("GoodsType",goodsDetail.getGoodsType());
                startActivity(intent01);
                goToAnimation(1);
                break;

            //去店铺
            case R.id.shopLayout:

                break;
            //规格

            //  去购物车
            case R.id.carLayout:
                Intent intent=new Intent(getThisContext(),ShoppingCarActivity.class);
                startActivity(intent);
                goToAnimation(1);
                break;

            case R.id.specification_rl:
                PurchaseSelectDialogShow();
                break;
        }
    }


    public void getGoodComment() {
        if (goodsDetail==null)
            return;
        NewShopApi api = ApiClientBulk.create(NewShopApi.class);
        CommentParam param = new CommentParam();
        param.setGoodsId(goodsDetail.getGoodsId());
        param.setPageSize("20");
        param.setCurrentPage(1 + "");
        param.setGoodsType(goodsDetail.getGoodsType());
        System.out.println(param + "param");
        api.getGoodComment(param, new HttpCallback<MessageToBulk<List<GoodsCommentTo>>>(getThisContext()) {
            @Override
            public void success(MessageToBulk<List<GoodsCommentTo>> msg, Response response) {
                System.out.println(msg.getMessage()+"msg--");
                if (msg.getCode() == 0) {
                    if (msg.getGoodsAllEvaluateList().size() == 0) {
                        Log.i("2222", "success: 1");
                        commentOne.setVisibility(View.GONE);
                        commentTwo.setVisibility(View.GONE);
                    } else if (msg.getGoodsAllEvaluateList().size() == 1) {
                        Log.i("2222", "success: 2");
                        commentOne.setVisibility(View.VISIBLE);
                        commentTwo.setVisibility(View.GONE);
                        goodCommentList.addAll(msg.getGoodsAllEvaluateList());
                        setCommentData(goodCommentList);
                    } else {
                        Log.i("2222", "success: 3");
                        commentOne.setVisibility(View.VISIBLE);
                        commentTwo.setVisibility(View.VISIBLE);
                        goodCommentList.addAll(msg.getGoodsAllEvaluateList());
                        setCommentData(goodCommentList);
                    }
                }
            }

            @Override
            public void failure(RetrofitError error) {
                System.out.println(error+"error");
            }
        });
    }

    //所有评论
    public void getAllComment() {
        NewShopApi api = ApiClientBulk.create(NewShopApi.class);
        CommentParam param = new CommentParam();
        param.setGoodsId(goodsDetail.getGoodsId());
        param.setPageSize("20");
        param.setCurrentPage(1 + "");
        param.setGoodsType(goodsDetail.getGoodsType());

        api.getAllComment(param, new HttpCallback<MessageToBulk<List<GoodsCommentTo>>>(getThisContext()) {
            @Override
            public void success(MessageToBulk<List<GoodsCommentTo>> msg, Response response) {
                if (msg.getCode() == 0) {
                    if (msg.getGoodsAllEvaluateList() != null) {

                        commentCount.setText("评论（" + msg.getTotal() + "）");
                        goodCommentAllList.addAll(msg.getGoodsAllEvaluateList());
                    }
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }




    private void setCommentData(List<GoodsCommentTo> goodsCommentList) {
        if (goodsCommentList.size() == 1) {
            if (goodsCommentList.get(0).getHeadUrl() !=null) {
                Picasso.with(getThisContext()).load(goodsCommentList.get(0).getHeadUrl().startsWith("http") ? goodsCommentList.get(0).getHeadUrl() : MainApp.getPicassoImagePath(goodsCommentList.get(0).getHeadUrl())).into(headImageOne);
            }
            Log.i("222", "goodsCommentList1" + goodsCommentList.toString());
            nickNameOne.setText(goodsCommentList.get(0).getNickName());
            commentDateOne.setText(goodsCommentList.get(0).getEvaluateTime());
            if (TextUtils.isEmpty(goodsCommentList.get(0).getEvaluateContent()))
                commentContentOne.setText("暂无评价内容");
            else
                commentContentOne.setText(goodsCommentList.get(0).getEvaluateContent());
            commentDescriptionOne.setText(goodsCommentList.get(0).getSpecificationsName());
        } else if (goodsCommentList.size() >= 2){
            Log.i("222", "goodsCommentList2" + goodsCommentList.toString());
            if (goodsCommentList.get(0).getHeadUrl() !=null) {
                Picasso.with(getThisContext()).load(goodsCommentList.get(0).getHeadUrl().startsWith("http") ? goodsCommentList.get(0).getHeadUrl() : MainApp.getPicassoImagePath(goodsCommentList.get(0).getHeadUrl())).into(headImageOne);
            }
            commentDateOne.setText(goodsCommentList.get(0).getEvaluateTime());
            if (TextUtils.isEmpty(goodsCommentList.get(0).getEvaluateContent()))
                commentContentOne.setText("暂无评价内容");
            else
                commentContentOne.setText(goodsCommentList.get(0).getEvaluateContent());
            nickNameOne.setText(goodsCommentList.get(0).getNickName());
            commentDescriptionOne.setText(goodsCommentList.get(0).getSpecificationsName());
            if (goodsCommentList.get(1).getHeadUrl() != null) {
                Picasso.with(getThisContext()).load(goodsCommentList.get(1).getHeadUrl().startsWith("http") ? goodsCommentList.get(1).getHeadUrl() : MainApp.getPicassoImagePath(goodsCommentList.get(1).getHeadUrl())).into(headImageTwo);
            }
            nickNameTwo.setText(goodsCommentList.get(1).getNickName());
            commentDateTwo.setText(goodsCommentList.get(1).getEvaluateTime());
            if (TextUtils.isEmpty(goodsCommentList.get(1).getEvaluateContent()))
                commentContentTwo.setText("暂无评价内容");
            else
                commentContentTwo.setText(goodsCommentList.get(1).getEvaluateContent());
            commentDescriptionTwo.setText(goodsCommentList.get(1).getSpecificationsName());
        }
    }



    public void setLabel(){
        mFlowLayout.setAdapter(new TagAdapter<String>(mLabels) {

            @Override
            public View getView(FlowLayout parent, int position, String s) {
                LayoutInflater mInflater = LayoutInflater.from(getThisContext());
                TextView tv = (TextView) mInflater.inflate(R.layout.tv_label,
                        mFlowLayout, false);
                tv.setText(s);
                return tv;
            }
        });
    }

    private void PurchaseSelectDialogShow() {

        flowViewList.clear();

        final CustomDialog dialog = new CustomDialog(getThisContext(), R.layout.dialog_add_purchase, R.style.myDialogTheme);
        TagFlowLayout mFlowLayout = (TagFlowLayout) dialog.findViewById(R.id.flowlayout);
        TextView purchaseNumber = (TextView) dialog.findViewById(R.id.purchaseNumber);


        LinearLayout dismissLayout = (LinearLayout) dialog.findViewById(R.id.dismissLayout);

        RelativeLayout shopLayout = (RelativeLayout) dialog.findViewById(R.id.shopLayout);
        ImageView purchaseAdd = (ImageView) dialog.findViewById(R.id.purchaseAdd);

        ImageView purchaseReduce = (ImageView) dialog.findViewById(R.id.purchaseReduce);
        TextView purchaseCost = (TextView) dialog.findViewById(R.id.purchaseCost);
        TextView addCar = (TextView) dialog.findViewById(R.id.add_car);
        TextView purchase = (TextView) dialog.findViewById(R.id.purchase);
        TextView purchaseSurplus = (TextView) dialog.findViewById(R.id.purchaseSurplus);
        ImageView purchaseClose = (ImageView) dialog.findViewById(R.id.purchaseClose);
        ImageView purchaseImage = (ImageView) dialog.findViewById(R.id.purchaseImage);
        TextView purchaseSpecification = (TextView) dialog.findViewById(R.id.purchaseSpecification);
        LinearLayout specificationLayout = (LinearLayout) dialog.findViewById(R.id.specificationLayout);
        purchaseNumber.setText(TextUtils.isEmpty(lastBuyCount) ? (goodsDetail.getMinSalesNum() + "") : lastBuyCount);
        purchaseSurplus.setText("库存:" + goodsSpecificationsList.get(0).getInventoryNum());

        purchaseCost.setText("价格：¥ " + goodsSpecificationsList.get(0).getRetailPrice());
        Picasso.with(getThisContext()).load(MainApp.getPicassoImagePath(goodsDetail.getPicUrl())).into(purchaseImage);

        if (!TextUtils.isEmpty(specificationsId)) {
            purchaseSpecification.setText("已选择:"+(TextUtils.isEmpty(specificationsName)?"":specificationsName)+"  x" +purchaseNumber.getText().toString());
            purchaseCost.setText("价格：¥ " + retailPrice);
            purchaseNumber.setText(purchaseNum+"");
        }else
            purchaseNum=TextUtils.isEmpty(lastBuyCount) ?(goodsDetail.getMinSalesNum()+""):lastBuyCount;
        purchaseClose.setOnClickListener(v -> dialog.dismiss());
        isFirst=true;
        //   specificationsId=goodsSpecificationsList.get(0).getSpecificationsId();
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
            specificationContent.setText("已选择:"+(TextUtils.isEmpty(specificationsName)?"":specificationsName)+"  x" +purchaseNumber.getText().toString());
            purchaseSpecification.setText("已选择:"+(TextUtils.isEmpty(specificationsName)?"":specificationsName)+"  x" +purchaseNumber.getText().toString());
            purchaseNum=purchaseNumber.getText().toString();
            lastBuyCount=purchaseNum;
            EventBus.getDefault().post(new RefreshEvent("specification", specificationsId, specificationsName, Double.parseDouble(retailPrice), Integer.parseInt(lastBuyCount)));
        });
        purchaseReduce.setOnClickListener(view -> {

            if (Integer.valueOf(purchaseNumber.getText().toString())<=goodsDetail.getMinSalesNum()+1){
                isFirst=true;
                purchaseReduce.setBackgroundDrawable(getResources().getDrawable(R.drawable.purchase_reduce_new_dim));

            }
            if (Integer.valueOf(purchaseNumber.getText().toString())<=goodsDetail.getMinSalesNum()){
                Toast.makeText(getThisContext(), "不能再少了~", Toast.LENGTH_LONG).show();
                return;
            }
            if(Integer.valueOf(purchaseNumber.getText().toString())>1){
                purchaseNumber.setText(Integer.valueOf(purchaseNumber.getText().toString()) - 1 + "");
                specificationContent.setText("已选择:" + (TextUtils.isEmpty(specificationsName)?"":specificationsName) + "  x" + purchaseNumber.getText().toString());
                purchaseNum=purchaseNumber.getText().toString();
                purchaseSpecification.setText("已选择:" + (TextUtils.isEmpty(specificationsName) ? "" : specificationsName) + "  x" + purchaseNumber.getText().toString());
            }
            lastBuyCount=purchaseNum;

            EventBus.getDefault().post(new RefreshEvent("specification", specificationsId, specificationsName, Double.parseDouble(retailPrice), Integer.parseInt(lastBuyCount)));
        });

        mFlowLayout.setAdapter(new TagAdapter<String>(mNames) {


            @Override
            public View getView(FlowLayout parent, int position, String s) {

                LayoutInflater mInflater = LayoutInflater.from(getThisContext());
                RelativeLayout mRelativeLayout = (RelativeLayout) mInflater.inflate(R.layout.tv, mFlowLayout, false);
                TextView textView = (TextView) mRelativeLayout.findViewById(R.id.textView);
                textView.setText(s);
                mRelativeLayout.setTag(position);
                if (!TextUtils.isEmpty(specificationsId)&&specificationsName.equals(s)){
                    textView.setTextColor(Color.parseColor("#46b4d9"));
                    textView.setBackgroundDrawable(getResources().getDrawable(R.drawable.specification_bg_press));
                }

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
                            purchaseSpecification.setText("已选择:"+(TextUtils.isEmpty(specificationsName)?"":specificationsName)+"  x" +purchaseNumber.getText().toString());
                            purchaseSurplus.setText("库存:" + inventoryNum);
                            retailPrice = goodsSpecificationsList.get(i).getRetailPrice() + "";
                            nowPrice.setText("¥"+((retailPrice+"").contains(".")?(((retailPrice+"").substring((retailPrice+"").lastIndexOf("."))).length()==3?retailPrice+"":retailPrice+"0"):retailPrice+".00"));
                        } else {
                            flowViewList.get(i).setTextColor(Color.parseColor("#353535"));
                            flowViewList.get(i).setBackgroundDrawable(getResources().getDrawable(R.drawable.specification_bg));
                        }
                    }
                    specificationContent.setText("已选择:" + (TextUtils.isEmpty(specificationsName)?"":specificationsName) + "  x" + purchaseNumber.getText().toString());
                    EventBus.getDefault().post(new RefreshEvent("specification", specificationsId, specificationsName, Double.parseDouble(retailPrice), Integer.parseInt(purchaseNumber.getText().toString())));
                });


                return mRelativeLayout;
            }
        });
        addCar.setOnClickListener(v -> {
            if (TextUtils.isEmpty(specificationsId)) {
                Toast.makeText(getThisContext(),"请先选择规格",Toast.LENGTH_LONG).show();
                return;
            }
            dialog.dismiss();
            if (listener != null)
                listener.addCar();
        });
        purchase.setOnClickListener(v -> {
            if (TextUtils.isEmpty(specificationsId)) {
                Toast.makeText(getThisContext(),"请先选择规格",Toast.LENGTH_LONG).show();
                return;
            }
            dialog.dismiss();
            if (purchaseListener!=null)
                purchaseListener.immediatelyPurchase();
        });

        if (mNames.length>0) {
            haveSpecification=false;
            Observable.from(mNames).filter(s1 -> !TextUtils.isEmpty(s1)).subscribe(s2 -> {
                specificationLayout.setVisibility(View.VISIBLE);
                purchaseSpecification.setVisibility(View.VISIBLE);
                dialog.findViewById(R.id.noSpecificationLayout).setVisibility(View.GONE);
                haveSpecification=true;

            });
            if (!haveSpecification)
                specificationsId=goodsSpecificationsList.get(0).getSpecificationsId();

        }
        dialog.findViewById(R.id.shopLayout).setOnClickListener(v -> {
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
        });
        dialog.findViewById(R.id.shopCar).setOnClickListener(v -> {
            Intent intent=new Intent(getThisContext(),ShoppingCarActivity.class);
            startActivity(intent);
            goToAnimation(1);
        });
        dismissLayout.setOnClickListener(view -> dialog.dismiss());
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(true);
        dialog.show();
    }
    private AddCarListener listener;

    public interface AddCarListener {
        void addCar();

    }
    public void setAddCar(AddCarListener listener){
        this.listener=listener;
    }

    private ImmediatelyPurchase purchaseListener;

    public interface ImmediatelyPurchase {
        void immediatelyPurchase();

    }
    public void setImmediatelyPurchase(ImmediatelyPurchase listener){
        this.purchaseListener=listener;
    }
}