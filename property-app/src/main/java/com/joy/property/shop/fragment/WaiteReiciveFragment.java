package com.joy.property.shop.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jinyi.ihome.infrastructure.MessageToBulk;
import com.jinyi.ihome.module.newshop.CancelOlderParam;
import com.jinyi.ihome.module.newshop.MyOlderParam;
import com.jinyi.ihome.module.newshop.MyOlderTo;
import com.jinyi.ihome.module.newshop.OlderDetailParam;
import com.joy.common.api.ApiClientBulk;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.NewShopApi;
import com.joy.property.R;
import com.joy.property.base.BaseFragment;
import com.joy.property.shop.OlderDetailActivity;
import com.joy.property.shop.ShoppingPostActivity;
import com.joy.property.shop.adapter.MyShoppingAdapter;
import com.joyhome.nacity.app.util.CustomDialog;
import com.joyhome.nacity.app.util.CustomDialogFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit.RetrofitError;
import retrofit.client.Response;


@SuppressLint("ValidFragment")
public class WaiteReiciveFragment extends BaseFragment {

    private int type;
    private ListView listView;
    private MyShoppingAdapter adapter;
    private int pageIndex;
    private List<MyOlderTo>shoppingList=new ArrayList<>();
    private PullToRefreshListView pullListView;
    private RelativeLayout noOlderLayout;


    public WaiteReiciveFragment(int type){
    this.type=type;
}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
         View mRootView   = inflater.inflate(R.layout.fragment_my_shopping, container, false);
         initView(mRootView);
         setRefresh();
          setData(0);
        setAdapterListener();
        return mRootView;


    }




    public void initView(View view){
        pullListView = (PullToRefreshListView) view.findViewById(R.id.listView);
        pullListView.setMode(PullToRefreshBase.Mode.BOTH);
        listView= pullListView.getRefreshableView();
        adapter = new MyShoppingAdapter(getThisContext(),type,false);
        listView.setDividerHeight(0);
        adapter.setList(shoppingList);
        listView.setAdapter(adapter);
        noOlderLayout = (RelativeLayout) view.findViewById(R.id.noOlderLayout);
}
    private void setRefresh() {
        pullListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageIndex = 0;
                setData(0);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageIndex++;
                setData(pageIndex);
            }
        });
    }

    private void setData(int index) {
        NewShopApi api= ApiClientBulk.create(NewShopApi.class);
        MyOlderParam param=new MyOlderParam();
        param.setCurrentPage(index + 1 + "");
        param.setUserId(mUserHelperBulk.getSid());
        param.setPageSize("20");
        final CustomDialogFragment dialogFragment = new CustomDialogFragment();
        dialogFragment.show(getFragmentManager(), "");
        api.getWaiteReicive(param, new HttpCallback<MessageToBulk<List<MyOlderTo>>>(getThisContext()) {
            @Override
            public void success(MessageToBulk<List<MyOlderTo>> msg, Response response) {
                dialogFragment.dismiss();
                if (msg.getCode() == 0) {
                    if (index == 0)
                        shoppingList.clear();

                   if (msg.getMyWaitReceiveOrderList()!=null)
                    shoppingList.addAll(msg.getMyWaitReceiveOrderList());
                    setNoOlderLayout(shoppingList.size());
                    adapter.notifyDataSetChanged();
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                            Intent intent = new Intent(getThisContext(), OlderDetailActivity.class);
                            intent.putExtra("type", type);
                            intent.putExtra("olderSid", shoppingList.get(position - 1).getOrderId() + "");
                            intent.putExtra("MyBulk", true);
                            startActivity(intent);
                            goToAnimation(1);
                        }
                    });
                }
                setComplementRefresh();
            }

            @Override
            public void failure(RetrofitError error) {
                dialogFragment.dismiss();
                setComplementRefresh();
            }
        });
    }


    @Override
    protected Context getThisContext() {
        return getActivity();
    }
    private void setAdapterListener() {
        adapter.setCancel(new MyShoppingAdapter.CancelListener() {
            @Override
            public void setCancel(String olderId, int type) {
  
                deleteOlder(olderId);
            }
        });
        adapter.setConfirm(new MyShoppingAdapter.ConfirmListener() {
            @Override
            public void setConfirm(String olderId, int type) {
                confirmOlderDilog(olderId);

            }
        });
    }
    public void deleteOlder(String olderSid){
        NewShopApi api=ApiClientBulk.create(NewShopApi.class);
        CancelOlderParam param=new CancelOlderParam();
        param.setOrderId(olderSid);
        param.setUserId(mUserHelperBulk.getSid());
        CustomDialogFragment dialogFragment=new CustomDialogFragment();
        dialogFragment.show(getFragmentManager(), "");
        api.deleteOlder(param, new HttpCallback<MessageToBulk>(getThisContext()) {
            @Override
            public void success(MessageToBulk msg, Response response) {
                dialogFragment.dismiss();
                if (msg.getCode() == 0) {
                    setData(pageIndex);
                }
            }
        });

    }

    public void confirmReceive(String olderSid){
        NewShopApi api=ApiClientBulk.create(NewShopApi.class);
        OlderDetailParam param=new OlderDetailParam();
        param.setOrderId(olderSid);
        CustomDialogFragment dialogFragment=new CustomDialogFragment();
        dialogFragment.show(getFragmentManager(), "");
        api.confirmReceive(param, new HttpCallback<MessageToBulk>(getThisContext()) {
            @Override
            public void success(MessageToBulk msg, Response response) {
                dialogFragment.dismiss();
                if (msg.getCode() == 0) {
                    commentGoods(olderSid);

                }
            }

            @Override
            public void failure(RetrofitError error) {
                dialogFragment.dismiss();
            }
        });

    }
    public void commentGoods(String olderSid){
        Intent intent=new Intent(getThisContext(), ShoppingPostActivity.class);
        intent.putExtra("OlderSid", olderSid);
        startActivity(intent);
        goToAnimation(1);
    }
    private void confirmOlderDilog(String olderSid) {
        final CustomDialog dialog = new CustomDialog(getThisContext(), R.layout.dialog_bulk, R.style.myDialogTheme);

        TextView dialogText = (TextView) dialog.findViewById(R.id.dialogText);
        TextView cancel = (TextView) dialog.findViewById(R.id.cancel);
        TextView confirm = (TextView) dialog.findViewById(R.id.confirm);
        dialogText.setText("确定已经收到了商品");
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmReceive(olderSid);
                dialog.dismiss();
            }
        });
        dialog.setCancelable(true);
        dialog.show();

    }
    public void  setComplementRefresh(){
        pullListView.onRefreshComplete();
    }
    public void setNoOlderLayout(int size){
        if (size==0)
            noOlderLayout.setVisibility(View.VISIBLE);
        else
            noOlderLayout.setVisibility(View.GONE);
    }
}
