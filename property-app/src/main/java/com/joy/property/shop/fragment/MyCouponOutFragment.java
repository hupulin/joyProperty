package com.joy.property.shop.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.infrastructure.MessageToBulk;
import com.jinyi.ihome.module.newshop.ActivityCouponTo;
import com.jinyi.ihome.module.newshop.CouponTo;
import com.jinyi.ihome.module.newshop.MyCouponParam;
import com.joy.common.api.ApiClientBulk;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.NewShopApi;
import com.joy.common.helper.UserInfoBulkHelper;

import com.joy.property.R;
import com.joy.property.shop.CampaignListActivity;
import com.joy.property.shop.adapter.CouponAdapter;
import com.joy.property.shop.adapter.MyCouponAdapter;
import com.joy.property.base.BaseFragment;
import com.joyhome.nacity.app.util.CustomDialogFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by xz on 2016/10/31.
 **/
@SuppressLint("ValidFragment")
public class MyCouponOutFragment extends BaseFragment {
    private View self;
    private PullToRefreshListView mPullToRefreshListView;
    private ListView mListView;
    private MyCouponAdapter mAdapter = null;
    private int type;
    private int pageIndex;
    private List<CouponTo>couponList=new ArrayList<>();
    private ImageView noCoupon;

    public MyCouponOutFragment(){

    }
    public MyCouponOutFragment(int type){
        this.type=type;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (this.self == null) {
            this.self = inflater.inflate(R.layout.fragment_coupon, null);
        }
        if (this.self.getParent() != null) {
            ViewGroup parent = (ViewGroup) this.self.getParent();
            parent.removeView(this.self);
        }

        initView();
        getCouponUnUseList(0);
        refreshData();
        setCouponListener();
        return self;
    }

    private void setCouponListener() {
//        mAdapter.setIntoActivityListener(new MyCouponAdapter.IntoActivityListener() {
//            @Override
//            public void intoActivity(String couponId) {
//                NewShopApi api = ApiClientBulk.create(NewShopApi.class);
//                final CustomDialogFragment dialogFragment = new CustomDialogFragment();
//                dialogFragment.show(getActivity().getSupportFragmentManager(), "");
//                System.out.println(couponId + "couponId");
//                api.getCouponActivity(couponId, new HttpCallback<MessageTo<ActivityCouponTo>>(getThisContext()) {
//
//
//                    @Override
//                    public void success(MessageTo<ActivityCouponTo> msg, Response response) {
//                        dialogFragment.dismiss();
//                        if (msg.getSuccess() == 0) {
//                            if ("0".equals(msg.getData().getActivityId())) {
//                                Toast.makeText(getThisContext(), "暂无适用商品", Toast.LENGTH_LONG).show();
//                            } else {
//                                //进入活动界面
//                                Intent intent = new Intent(getThisContext(), CampaignListActivity.class);
//                                intent.putExtra("couponId", msg.getData().getActivityId());
//                                startActivity(intent);
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void failure(RetrofitError error) {
//                        dialogFragment.dismiss();
//                        System.out.println(error + "error");
//                    }
//                });
//            }
//        });
    }

    private void initView() {
        noCoupon = (ImageView) self.findViewById(R.id.noCoupon);
        mPullToRefreshListView = (PullToRefreshListView)  self.findViewById(R.id.lv_PullToRefreshListView);
        mPullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        mListView = mPullToRefreshListView.getRefreshableView();
        registerForContextMenu(mListView);
        mAdapter = new MyCouponAdapter(getThisContext(),2);
        mAdapter.setList(couponList);
        mListView.setAdapter(mAdapter);
        mListView.setDivider(getResources().getDrawable(R.drawable.divide_bg));
        mListView.setDividerHeight((int) (getScreenWidthPixels(getThisContext()) * 0.0277));

    }
    private void refreshData() {
        mPullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageIndex = 0;
                getCouponUnUseList(0);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageIndex++;
                getCouponUnUseList(pageIndex);
            }
        });
    }
    public void getCouponUnUseList(int index) {
        mUserHelperBulk= UserInfoBulkHelper.getInstance(getThisContext());
        NewShopApi api= ApiClientBulk.create(NewShopApi.class);
        final CustomDialogFragment customDialog = new CustomDialogFragment();
        customDialog.show(getFragmentManager(), "");
        MyCouponParam param=new MyCouponParam();
        param.setUserId(mUserHelperBulk.getSid());
        param.setPageSize("");
        param.setCurrentPage(index + 1 + "");
        api.getCouponOut(param, new HttpCallback<MessageToBulk<List<CouponTo>>>(getThisContext()) {
            @Override
            public void success(MessageToBulk<List<CouponTo>> msg, Response response) {
                customDialog.dismiss();
                if (msg.getCode() == 0) {

                    if (index == 0) {
                        couponList.clear();
                    }
                    if (msg.getMyInvalideCouponList() != null) {
                        couponList.addAll(msg.getMyInvalideCouponList());

                        mAdapter.setList(couponList);
                        mAdapter.notifyDataSetChanged();

                    }
                    setCouponImageVisible(couponList.size());
                } else
                    Toast.makeText(getThisContext(), msg.getMessage(), Toast.LENGTH_LONG).show();
                mPullToRefreshListView.onRefreshComplete();
            }

            @Override
            public void failure(RetrofitError error) {
                customDialog.dismiss();
                mPullToRefreshListView.onRefreshComplete();
            }
        });



    }


    public void setCouponImageVisible(int size){
        if (size>0)
            noCoupon.setVisibility(View.GONE);
        else
            noCoupon.setVisibility(View.VISIBLE);
    }





}
