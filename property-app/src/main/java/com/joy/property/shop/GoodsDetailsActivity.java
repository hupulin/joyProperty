package com.joy.property.shop;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.ToxicBakery.viewpager.transforms.ABaseTransformer;
import com.ToxicBakery.viewpager.transforms.ForegroundToBackgroundTransformer;
import com.bigkoo.convenientbanner.ConvenientBanner;
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
import com.jinyi.ihome.module.shop.GoodsLabelTo;
import com.jinyi.ihome.module.shop.GoodsSpecificationsTo;
import com.joy.common.api.ApiClientBulk;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.NewShopApi;
import com.joy.property.R;
import com.joy.property.MainApp;
import com.joy.property.base.BaseActivity;
import com.joy.property.utils.flowlayout.FlowLayout;
import com.joyhome.nacity.app.propertyCenter.NetworkImageHolderView;
import com.joy.property.shop.shoputil.CarNumberUtil;
import com.joyhome.nacity.app.util.CustomDialog;
import com.joyhome.nacity.app.util.CustomDialogFragment;
import com.joyhome.nacity.app.util.SpUtil;

import com.joy.property.utils.flowlayout.TagFlowLayout;
import com.joy.property.utils.flowlayout.TagAdapter;
import com.squareup.picasso.Picasso;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by usb on 2017/3/29.
 */
public class GoodsDetailsActivity extends BaseActivity implements View.OnClickListener {
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
    private TextView shopCarNumber;
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

    //标签数组
    private String [] mLabels;
    private List<GoodsCommentTo> goodCommentList = new ArrayList<>();
    private List<GoodsCommentTo> goodCommentAllList = new ArrayList<>();
    private List<TextView>flowViewList=new ArrayList<>();

    private List<GoodsDetailPicTo> picList = new ArrayList<>();
    private TagFlowLayout mFlowLayout;
    private List<GoodsSpecificationsTo> goodsSpecificationsList;
    private String specificationsName;
    private String specificationsId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_deitail);
        findById();
        //商品轮播图
        getData();
        //拿取好评
        getGoodComment();
        getAllComment();

    }

    private void findById() {
        autoRow = (ConvenientBanner) findViewById(R.id.autoRow);
        autoRow.startTurning(5000);
        mFlowLayout = (TagFlowLayout) findViewById(R.id.flowlayout);


        commentOne = (LinearLayout) findViewById(R.id.comment_one);
        commentTwo = (LinearLayout) findViewById(R.id.comment_two);
        findViewById(R.id.purchase).setOnClickListener(this);
        findViewById(R.id.specification_rl).setOnClickListener(this);
        findViewById(R.id.add_car).setOnClickListener(this);
        findViewById(R.id.comment_rl).setOnClickListener(this);
        findViewById(R.id.shopLayout).setOnClickListener(this);
        findViewById(R.id.carLayout).setOnClickListener(this);
        nowPrice = (TextView) findViewById(R.id.now_price);
        shopCarNumber = (TextView) findViewById(R.id.shopCarNumber);
        CarNumberUtil.getCarNumber(mUserHelperBulk.getSid(), getThisContext(), shopCarNumber);
        commentCount = (TextView) findViewById(R.id.comment_count);
        bulkAmount = (TextView) findViewById(R.id.bulk_amount);
        imageGuide = (TextView) findViewById(R.id.image_guide);
        expressMode = (TextView) findViewById(R.id.express_mode);
        expressCost = (TextView) findViewById(R.id.tv_express_cost);
        headImageOne = (ImageView) findViewById(R.id.head_image);
        headImageTwo = (ImageView) findViewById(R.id.headImage_01);
        nickNameOne = (TextView) findViewById(R.id.tv_nickname);
        nickNameTwo = (TextView) findViewById(R.id.tv_nickname_01);
        commentDateOne = (TextView) findViewById(R.id.comment_date);
        commentDateTwo = (TextView) findViewById(R.id.comment_date_01);
        commentContentOne = (TextView) findViewById(R.id.comment_content);
        commentContentTwo = (TextView) findViewById(R.id.comment_content_01);
        commentDescriptionOne = (TextView) findViewById(R.id.comment_description);
        commentDescriptionTwo = (TextView) findViewById(R.id.comment_description_01);
        mImageGuide = (TextView) findViewById(R.id.image_guide);
        shopName = (TextView) findViewById(R.id.shop_name);
        shopDescription = (TextView) findViewById(R.id.shop_description);
        findViewById(R.id.iv_back).setOnClickListener(this);
    }

    private void getData() {
        NewShopApi api = ApiClientBulk.create(NewShopApi.class);
        GoodsDetailParam param = new GoodsDetailParam();
        Bundle bundle = getIntent().getExtras();//获取一个句柄
        if (TextUtils.isEmpty(goodsSid))
            goodsSid = bundle.getString("GoodsSid");
        param.setGoodsId(goodsSid);
        param.setGoodsType(getIntent().getBooleanExtra("ActivityGoods", false) ? "10" : "0");
        CustomDialogFragment dialogFragment = new CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "");
        System.out.println(param + "param");
        api.getGoodsDetail(param, new HttpCallback<GoodsApiTo>(getThisContext()) {
            @Override
            public void success(GoodsApiTo msg, Response response) {
                dialogFragment.dismiss();
                if (msg != null && msg.getCode() == 0) {
                    if (msg.getGoodsApiVo() != null) {

                        goodsDetail = msg.getGoodsApiVo();
                        setData(goodsDetail);
                    }
                    System.out.println(msg.getGoodsApiVo().getGoodsSpecificationsList() + "picList");
                    System.out.println(msg.getGoodsApiVo().getGoodsLableList() + "picList");
                    System.out.println(msg.getGoodsApiVo() + "picList");
                    if (msg.getGoodsPicApiVoList() != null) {

                        picList.addAll(msg.getGoodsPicApiVoList());


                        setAutoRow(picList);
                    }
                }
            }

            @Override
            public void failure(RetrofitError error) {
                System.out.println(error + "error");
            }
        });
    }

    private void setData(GoodsDetail goods) {
        shopName.setText(goods.getGoodsName());
        shopDescription.setText(goods.getGoodsName());
        if("0".equals(goods.getDistributionCost())){

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
            }
        }

        nowPrice.setText("¥" + minnum + "");
        //设置标签数组
        List<GoodsLabelTo> goodsLableList=goods.getGoodsLableList();
        mLabels=new String[goodsLableList.size()];
        for (int i=0;i<goodsLableList.size();i++) {
            mLabels[i]=goodsLableList.get(i).getLableName();
        }
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
        } catch (InstantiationException e) {
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
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.comment_rl:
                //去全部评价页面
                Intent intent01=new Intent(this, CommentListActivity.class);
                intent01.putExtra("GoodsSid", goodsSid);
              //  List<GoodsCommentTo> goodCommentAllList
                intent01.putExtra("goodCommentAllList",(Serializable)goodCommentAllList);
                startActivity(intent01);
                break;
            case R.id.add_car:
                PurchaseDialogShow(true);
                break;
            case R.id.purchase:
                PurchaseDialogShow(false);
                break;
            //去店铺
            case R.id.shopLayout:

                break;
            //规格
            case R.id.specification_rl:
                PurchaseSelectDialogShow();
                break;
            //  去购物车
            case R.id.carLayout:
                Intent intent=new Intent(getThisContext(),ShoppingCarActivity.class);
                startActivity(intent);
                goToAnimation(1);
                break;
        }
    }
    private void PurchaseSelectDialogShow() {

        flowViewList.clear();
        final CustomDialog dialog = new CustomDialog(this, R.layout.dialog_add_purchase, R.style.myDialogTheme);
        TagFlowLayout mFlowLayout = (TagFlowLayout) dialog.findViewById(R.id.flowlayout);

        mFlowLayout.setAdapter(new TagAdapter<String>(mNames) {


            @Override
            public View getView(FlowLayout parent, int position, String s) {
                LayoutInflater mInflater = LayoutInflater.from(getThisContext());
                RelativeLayout mRelativeLayout = (RelativeLayout) mInflater.inflate(R.layout.tv,
                        mFlowLayout, false);
                TextView textView= (TextView) mRelativeLayout.findViewById(R.id.textView);
                textView.setText(s);
                mRelativeLayout.setTag(position);
                flowViewList.add(textView);
                mRelativeLayout.setOnClickListener(v ->{
                    for (int i=0;i<flowViewList.size();i++){
                        if (i==(int)v.getTag()){
                            flowViewList.get(i).setTextColor(Color.parseColor("#f17834"));
                            flowViewList.get(i).setBackgroundDrawable(getResources().getDrawable(R.drawable.specification_bg_press));
                            specificationsName=goodsSpecificationsList.get(i).getSpecificationsName();
                            specificationsId=goodsSpecificationsList.get(i).getSpecificationsId();
                        }else {
                            flowViewList.get(i).setTextColor(Color.parseColor("#353535"));
                            flowViewList.get(i).setBackgroundDrawable(getResources().getDrawable(R.drawable.specification_bg));
                        }
                    }

                });
                return mRelativeLayout;
            }
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
        ImageView purchaseClose = (ImageView) dialog.findViewById(R.id.purchaseClose);
        ImageView purchaseImage = (ImageView) dialog.findViewById(R.id.purchaseImage);
        purchaseNumber.setText(goodsDetail.getMinSalesNum() + "");
        //设置价格

        //     purchaseCost.setText("价格：¥" + );
        if (picList!=null&&picList.size()>0)
            Picasso.with(getThisContext()).load(MainApp.getPicassoImagePath(goodsDetail.getPicUrl())).into(purchaseImage);
        purchaseClose.setOnClickListener(v -> dialog.dismiss());
        //   purchaseSurplus.setText("库存" + "件");
        addCar.setOnClickListener(view -> {
            addCar(goodsSid, purchaseNumber.getText().toString(),
                    specificationsName, specificationsId);
            dialog.dismiss();
        });
        purchase.setOnClickListener(view -> {
            immediatelyBuy(purchaseNumber.getText().toString());
        });
        shopCar.setOnClickListener(view -> {
            Intent intent=new Intent(getThisContext(),ShoppingCarActivity.class);
            startActivity(intent);
            goToAnimation(1);
        });
        shopLayout.setOnClickListener(view -> {
            //去店铺
        });
        purchaseAdd.setOnClickListener(view -> {
//            if (Integer.valueOf(purchaseNumber.getText().toString()) >= "库存") {
//                ToastShowLong(getThisContext(), "您已超过库存量了~");
//                return;
//            }
//            if (Integer.valueOf(purchaseNumber.getText().toString()) >= goodsDetail.getMaxSalesNum()) {
//                ToastShowLong(getThisContext(), "您已超过最大购买量~");
//                return;
//            }
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
    private void PurchaseDialogShow(boolean isAddCar) {
        flowViewList.clear();
        final CustomDialog dialog = new CustomDialog(this, R.layout.dialog_detail_purchase_new, R.style.myDialogTheme);
        TagFlowLayout mFlowLayout = (TagFlowLayout) dialog.findViewById(R.id.flowlayout);
        mFlowLayout.setAdapter(new TagAdapter<String>(mNames) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                LayoutInflater mInflater = LayoutInflater.from(getThisContext());
                RelativeLayout mRelativeLayout = (RelativeLayout) mInflater.inflate(R.layout.tv,
                        mFlowLayout, false);
                TextView textView= (TextView) mRelativeLayout.findViewById(R.id.textView);
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
                        } else {
                            flowViewList.get(i).setTextColor(Color.parseColor("#353535"));
                            flowViewList.get(i).setBackgroundDrawable(getResources().getDrawable(R.drawable.specification_bg));
                        }
                    }

                });
                return mRelativeLayout;
            }
        });

        TextView confirmPurchase = (TextView) dialog.findViewById(R.id.confirmPurchase);
        LinearLayout dismissLayout = (LinearLayout) dialog.findViewById(R.id.dismissLayout);
        ImageView purchaseAdd = (ImageView) dialog.findViewById(R.id.purchaseAdd);
        TextView purchaseNumber = (TextView) dialog.findViewById(R.id.purchaseNumber);
        ImageView purchaseReduce = (ImageView) dialog.findViewById(R.id.purchaseReduce);
        TextView purchaseCost = (TextView) dialog.findViewById(R.id.purchaseCost);
        TextView purchaseSurplus = (TextView) dialog.findViewById(R.id.purchaseSurplus);
        ImageView purchaseClose = (ImageView) dialog.findViewById(R.id.purchaseClose);
        ImageView purchaseImage = (ImageView) dialog.findViewById(R.id.purchaseImage);

        confirmPurchase.setOnClickListener(v -> {
            if (isAddCar) {

                addCar(goodsSid, purchaseNumber.getText().toString(),
                        specificationsName,specificationsId);
            } else
                immediatelyBuy(purchaseNumber.getText().toString());
            dialog.dismiss();
        });

        purchaseNumber.setText(goodsDetail.getMinSalesNum() + "");
        //设置价格

        //   purchaseCost.setText("价格：¥"+价格);
        if (picList!=null&&picList.size()>0)
            Picasso.with(getThisContext()).load(MainApp.getPicassoImagePath(goodsDetail.getPicUrl())).into(purchaseImage);
        purchaseClose.setOnClickListener(v -> dialog.dismiss());
        //   purchaseSurplus.setText("库存" + "库存" + "件");
        purchaseAdd.setOnClickListener(view -> {
//            if (Integer.valueOf(purchaseNumber.getText().toString())>="库存量"){
//                ToastShowLong(getThisContext(),"您已超过库存量了~");
//                return;
//            }
//            if (Integer.valueOf(purchaseNumber.getText().toString())>=goodsDetail.getMaxSalesNum()){
//                ToastShowLong(getThisContext(),"您已超过最大购买量~");
//                return;
//            }
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

    public void getGoodComment() {
        NewShopApi api = ApiClientBulk.create(NewShopApi.class);
        CommentParam param = new CommentParam();
        param.setGoodsId(goodsSid);
        param.setPageSize("20");
        param.setCurrentPage(1 + "");
        CustomDialogFragment dialogFragment = new CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "");
        api.getGoodComment(param, new HttpCallback<MessageToBulk<List<GoodsCommentTo>>>(getThisContext()) {
            @Override
            public void success(MessageToBulk<List<GoodsCommentTo>> msg, Response response) {
                dialogFragment.dismiss();
                //     setRefreshComplement();
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
                dialogFragment.dismiss();
            }
        });
    }

    //所有评论
    public void getAllComment() {
        NewShopApi api = ApiClientBulk.create(NewShopApi.class);
        CommentParam param = new CommentParam();
        param.setGoodsId(goodsSid);
        param.setPageSize("20");
        param.setCurrentPage(1 + "");
        CustomDialogFragment dialogFragment = new CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "");
        api.getAllComment(param, new HttpCallback<MessageToBulk<List<GoodsCommentTo>>>(getThisContext()) {
            @Override
            public void success(MessageToBulk<List<GoodsCommentTo>> msg, Response response) {
                dialogFragment.dismiss();
                if (msg.getCode() == 0) {
                    if (msg.getGoodsAllEvaluateList() != null) {
                        commentCount.setText("全部（" + msg.getTotal() + "）");
                        goodCommentAllList.addAll(msg.getGoodsAllEvaluateList());
                    }
                }
            }

            @Override
            public void failure(RetrofitError error) {
                dialogFragment.dismiss();
            }
        });
    }




    private void setCommentData(List<GoodsCommentTo> goodsCommentList) {
        if (goodsCommentList.size() == 1) {
            if (goodsCommentList.get(0).getHeadUrl() !=null) {
                Picasso.with(this).load(goodsCommentList.get(0).getHeadUrl().startsWith("http") ? goodsCommentList.get(0).getHeadUrl() : MainApp.getPicassoImagePath(goodsCommentList.get(0).getHeadUrl())).into(headImageOne);
            }
            Log.i("222", "goodsCommentList1" + goodsCommentList.toString());
            nickNameOne.setText(goodsCommentList.get(0).getNickName());
            commentDateOne.setText(goodsCommentList.get(0).getEvaluateTime());
            commentContentOne.setText(goodsCommentList.get(0).getEvaluateContent());
            commentDescriptionOne.setText(goodsCommentList.get(0).getEvaluateResult());
        } else {
            Log.i("222", "goodsCommentList2" + goodsCommentList.toString());
            if (goodsCommentList.get(0).getHeadUrl() !=null) {
                Picasso.with(this).load(goodsCommentList.get(0).getHeadUrl().startsWith("http") ? goodsCommentList.get(0).getHeadUrl() : MainApp.getPicassoImagePath(goodsCommentList.get(0).getHeadUrl())).into(headImageOne);
            }
            commentDateOne.setText(goodsCommentList.get(0).getEvaluateTime());
            nickNameOne.setText(goodsCommentList.get(0).getNickName());
            commentContentOne.setText(goodsCommentList.get(0).getEvaluateContent());
            commentDescriptionOne.setText(goodsCommentList.get(0).getEvaluateResult());
            if (goodsCommentList.get(1).getHeadUrl() != null) {
                Picasso.with(this).load(goodsCommentList.get(1).getHeadUrl().startsWith("http") ? goodsCommentList.get(1).getHeadUrl() : MainApp.getPicassoImagePath(goodsCommentList.get(0).getHeadUrl())).into(headImageTwo);
            }
            nickNameTwo.setText(goodsCommentList.get(1).getNickName());
            commentDateTwo.setText(goodsCommentList.get(1).getEvaluateTime());
            commentContentTwo.setText(goodsCommentList.get(1).getEvaluateContent());
            commentDescriptionTwo.setText(goodsCommentList.get(1).getEvaluateResult());
        }
    }
    public void addCar(String goodsId,String num,String specificationsName,String specificationsId){
        NewShopApi api=ApiClientBulk.create(NewShopApi.class);
        AddCarParam param=new AddCarParam();
        param.setGoodsId(goodsId);
        param.setUserId(mUserHelperBulk.getSid());
        param.setNum(num);
        param.setSpecificationsId(specificationsId);
        param.setSpecificationsName(specificationsName);
        param.setGoodsType(getIntent().getBooleanExtra("ActivityGoods", false) ? "10" : "0");
        final CustomDialogFragment dialogFragment = new CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "");
        api.addCar(param, new HttpCallback<MessageToBulk>(getThisContext()) {
            @Override
            public void success(MessageToBulk msg, Response response) {
                dialogFragment.dismiss();
                if (msg.getCode() == 0) {
                    ToastShowLong(getThisContext(), "加入购物车成功");
                    shopCarNumber.setText(msg.getTotal() + "");
                    SpUtil.put(getThisContext(), "HaveAddCar", true);
                } else
                    ToastShowLong(getThisContext(), msg.getMessage());
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
        //选择规格的价格
        // param.setCurrentPrice();
        param.setGoodsId(goodsDetail.getGoodsId() + "");
        param.setMerchantId(goodsDetail.getMerchantId());
        param.setGoodsNum(goodsNumber);
        param.setDiscountPercentage(goodsDetail.getDiscountPercentage()+"");
        param.setUserId(mUserHelperBulk.getSid());
        param.setRetailPrice(goodsDetail.getCurrentPrice()+"");
        api.immediatelyBuy(param, new HttpCallback<MessageToBulk<ImmediatelyGoodsTo>>(getThisContext()) {
            @Override
            public void success(MessageToBulk<ImmediatelyGoodsTo> msg, Response response) {
                if (msg.getCode() == 0) {
                    Intent intent = new Intent(getThisContext(), ImmediatelyConfirmOlderActivity.class);
                    intent.putExtra("CartSettleGoodsTo", msg.getImmediateBuyGoodVo());
                    intent.putExtra("IsSeaBuy", msg.getIsSeaBuy());
                    startActivity(intent);
                    goToAnimation(1);
                } else
                    ToastShowLong(getThisContext(), msg.getMessage());
            }

            @Override
            public void failure(RetrofitError error) {
                System.out.println(error + "");
            }
        });

    }
    @Override
    protected void onRestart() {
        super.onRestart();
        if (shopCarNumber!=null)
            CarNumberUtil.getCarNumber(mUserHelperBulk.getSid(),getThisContext(),shopCarNumber);
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

}
