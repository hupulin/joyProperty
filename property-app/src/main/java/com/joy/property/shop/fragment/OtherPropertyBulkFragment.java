package com.joy.property.shop.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jinyi.ihome.infrastructure.MessageToBulk;
import com.jinyi.ihome.module.newshop.AddCarParam;
import com.jinyi.ihome.module.newshop.ChannelDetailParam;
import com.jinyi.ihome.module.newshop.ShopListDetailTo;
import com.joy.common.api.ApiClientBulk;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.NewShopApi;
import com.joy.property.R;
import com.joy.property.base.BaseFragment;
import com.joyhome.nacity.app.bulk.adapter.GroupPurchaseToAdapter;
import com.joy.property.shop.GoodsDetailActivity;
import com.joyhome.nacity.app.util.CustomDialogFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit.RetrofitError;
import retrofit.client.Response;


@SuppressLint("ValidFragment")
public class OtherPropertyBulkFragment extends BaseFragment {

    private PullToRefreshListView mPullRefreshListView;
    private ListView mList;
    private String sid;
    private String groupId;
    private int pageIndex;
    private TextView carNumber;
    private GroupPurchaseToAdapter mAdapter = null;
    private List<ShopListDetailTo> shopList=new ArrayList<>();
    public OtherPropertyBulkFragment(String sid,String groupId,TextView carNumber){
    this.sid=sid;
        this.groupId=groupId;
        this.carNumber=carNumber;
}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
         View mRootView   = inflater.inflate(R.layout.fragment_group_purchase, container, false);

        findById(mRootView);
        mPullRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        mList = mPullRefreshListView.getRefreshableView();
        mList.setItemsCanFocus(true);
        registerForContextMenu(mList);
        mAdapter = new GroupPurchaseToAdapter(getActivity(),getActivity());
        mAdapter.setList(shopList);
        mList.setAdapter(mAdapter);
         setList(0);
        if (!TextUtils.isEmpty(mHelper.getSid())) {
            initDatas();
        }

        return mRootView;


    }


    private void findById(View view) {
        mPullRefreshListView = (PullToRefreshListView) view.findViewById(R.id.lv_PullToRefreshListView);

    }

    private void initDatas() {
    mPullRefreshListView.setOnRefreshListener(new OnRefreshListener2<ListView>() {
        @Override
        public void onPullDownToRefresh(PullToRefreshBase<ListView> listViewPullToRefreshBase) {
            String label = DateUtils.formatDateTime(
                    getThisContext(),
                    System.currentTimeMillis(),
                    DateUtils.FORMAT_SHOW_TIME
                            | DateUtils.FORMAT_SHOW_DATE
                            | DateUtils.FORMAT_ABBREV_ALL);
            // Update the LastUpdatedLabel
            listViewPullToRefreshBase.getLoadingLayoutProxy()
                    .setLastUpdatedLabel(label);
            pageIndex = 0;
            setList(0);
        }

        @Override
        public void onPullUpToRefresh(PullToRefreshBase<ListView> listViewPullToRefreshBase) {
            String label = DateUtils.formatDateTime(
                    getThisContext(),
                    System.currentTimeMillis(),
                    DateUtils.FORMAT_SHOW_TIME
                            | DateUtils.FORMAT_SHOW_DATE
                            | DateUtils.FORMAT_ABBREV_ALL);
            // Update the LastUpdatedLabel
            listViewPullToRefreshBase.getLoadingLayoutProxy()
                    .setLastUpdatedLabel(label);
            pageIndex++;
            setList(pageIndex);
        }
    });


    }

    private void setList(int index) {
            NewShopApi api = ApiClientBulk.create(NewShopApi.class);
            final CustomDialogFragment dialogFragment = new CustomDialogFragment();
            dialogFragment.show(getFragmentManager(), "");
            ChannelDetailParam param=new ChannelDetailParam();
            param.setCommunityId(mUserHelperBulk.getUserInfoTo().getApartmentSid());
            param.setCategoryId(sid);
            param.setCurrentPage(index + 1 + "");
            param.setPageSize("");
            param.setGrouponId(groupId);
            api.getOtherBulkChannelDetail(param, new HttpCallback<MessageToBulk<List<ShopListDetailTo>>>(getThisContext()) {
                @Override
                public void success(MessageToBulk<List<ShopListDetailTo>> msg, Response response) {
                    dialogFragment.dismissAllowingStateLoss();
                    if (msg.getCode() == 0) {
                        if (index == 0)
                            shopList.clear();
                        shopList.addAll(msg.getCommunityGroupGoodsList());
                        mAdapter.notifyDataSetChanged();
                        setComplete();
                        mList.setOnItemClickListener((parent, view, position, id) -> {
                            System.out.println(shopList.get(position - 1).getGoodsId());
                            Intent intent = new Intent(getThisContext(), GoodsDetailActivity.class);
                            intent.putExtra("GoodsSid", shopList.get(position - 1).getGoodsId());
                            Bundle bundle = new Bundle();
                            bundle.putString("GoodsSid", shopList.get(position - 1).getGoodsId() + "");
                            intent.putExtras(bundle);
                            startActivity(intent);
                            goToAnimation(1);
                        });
                        mAdapter.setAddCarClickListener((shopSid, name) ->addCar(shopSid));

                    } else {
                        Toast.makeText(getThisContext(), msg.getMessage(), Toast.LENGTH_LONG).show();
                    }
                    setComplete();
                }

                @Override
                public void failure(RetrofitError error) {

                    dialogFragment.dismissAllowingStateLoss();
                    setComplete();


                }
            });

    }

    /**
     * 设置刷新完成
     */
    private void setComplete() {
        mPullRefreshListView.onRefreshComplete();

    }

    @Override
    protected Context getThisContext() {
        return getActivity();
    }
    public void addCar(String goodsId){
        NewShopApi api=ApiClientBulk.create(NewShopApi.class);
        AddCarParam param=new AddCarParam();
        param.setGoodsId(goodsId);
        param.setUserId(mUserHelperBulk.getSid());
        param.setNum("");
        final CustomDialogFragment dialogFragment = new CustomDialogFragment();
        dialogFragment.show(getFragmentManager(), "");
        api.addCar(param, new HttpCallback<MessageToBulk>(getThisContext()) {
            @Override
            public void success(MessageToBulk msg, Response response) {
                dialogFragment.dismiss();
                if (msg.getCode()== 0) {
                    Toast.makeText(getThisContext(), "加入购物车成功", Toast.LENGTH_LONG).show();
                    carNumber.setText(msg.getTotal() + "");
                }else
                    Toast.makeText(getThisContext(), msg.getMessage(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void failure(RetrofitError error) {
                dialogFragment.dismiss();

            }
        });
    }
 
}
