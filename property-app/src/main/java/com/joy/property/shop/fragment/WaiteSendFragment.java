package com.joy.property.shop.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jinyi.ihome.infrastructure.MessageToBulk;
import com.jinyi.ihome.module.newshop.CancelOlderParam;
import com.jinyi.ihome.module.newshop.MyOlderParam;
import com.jinyi.ihome.module.newshop.MyOlderTo;
import com.jinyi.ihome.module.newshop.OlderSidTo;
import com.jinyi.ihome.module.newshop.PayInfo;
import com.jinyi.ihome.module.newshop.PayOlderTo;
import com.jinyi.ihome.module.newshop.PayParam;
import com.joy.common.api.ApiClientBulk;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.NewShopApi;
import com.joy.property.R;
import com.joy.property.base.BaseFragment;
import com.joyhome.nacity.app.bulk.alipay.PayResult;
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
public class WaiteSendFragment extends BaseFragment {

    private int type;
    private ListView listView;
    private MyShoppingAdapter adapter;
    private int pageIndex;
    private List<MyOlderTo>shoppingList=new ArrayList<>();
    private PullToRefreshListView pullListView;
    private PayOlderTo payPartnerTo = null;
    private static final int SDK_PAY_FLAG = 1;
    private RelativeLayout noOlderLayout;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((String) msg.obj);
                    // 支付宝返回此次支付结果及加签，建议对支付宝签名信息拿签约时支付宝提供的公钥做验签
                    String resultInfo = payResult.getResult();
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        Toast.makeText(getThisContext(), "支付成功", Toast.LENGTH_SHORT).show();
                        submittedSuccessfullyDialog();
                    } else {
                        // 判断resultStatus 为非“9000”则代表可能支付失败
                        // “8000”代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(getThisContext(), "支付结果确认中", Toast.LENGTH_SHORT).show();
                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(getThisContext(), "支付失败", Toast.LENGTH_SHORT).show();

                        }
                        submittedSuccessfullyDialog();
                    }

                    break;
                }
                default:
                    break;
            }
        }
    };



    public WaiteSendFragment(int type){
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
        param.setCurrentPage(index+1+"");
        param.setUserId(mUserHelperBulk.getSid());
        param.setPageSize("20");

        final CustomDialogFragment dialogFragment = new CustomDialogFragment();
        dialogFragment.show(getFragmentManager(), "");
        api.getWaiteGoodsList(param, new HttpCallback<MessageToBulk<List<MyOlderTo>>>(getThisContext()) {
            @Override
            public void success(MessageToBulk<List<MyOlderTo>> msg, Response response) {
                dialogFragment.dismiss();
                if (msg.getCode() == 0) {
                    if (index == 0)
                        shoppingList.clear();
                    if (msg.getMyWaitDeliverOrderList()!=null)
                    shoppingList.addAll(msg.getMyWaitDeliverOrderList());
                    setNoOlderLayout(shoppingList.size());
                    adapter.notifyDataSetChanged();
                    listView.setOnItemClickListener((adapterView, view, position, l) -> {
                        Intent intent = new Intent(getThisContext(), OlderDetailActivity.class);
                        intent.putExtra("type", type);
                        intent.putExtra("olderSid", shoppingList.get(position - 1).getOrderId() + "");
                        intent.putExtra("MyBulk", true);
                        startActivity(intent);
                        goToAnimation(1);
                    });
                }
                setComplementRefresh();
            }

            @Override
            public void failure(RetrofitError error) {
                setComplementRefresh();
                dialogFragment.dismiss();
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
            //  commentGoods(olderId);
                deleteOlder(olderId);
            }
        });
        adapter.setConfirm(new MyShoppingAdapter.ConfirmListener() {
            @Override
            public void setConfirm(String olderId, int type) {

                payMoney(olderId);
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

    public void payMoney(String olderSid){

        NewShopApi api=ApiClientBulk.create(NewShopApi.class);
        PayParam param=new PayParam();
        OlderSidTo sidTo=new OlderSidTo();
        List<OlderSidTo>sidTos=new ArrayList<>();
        sidTo.setOrderId(olderSid);
        sidTos.add(sidTo);
        param.setOrderList(sidTos);

        api.payOlder(param, new HttpCallback<MessageToBulk<PayInfo>>(getThisContext()) {
            @Override
            public void success(MessageToBulk<PayInfo> msg, Response response) {
                if (msg.getCode() == 0) {

                    payPartnerTo = msg.getPayinto().getPayPartners().get(0);
                    if (TextUtils.equals(payPartnerTo.getPayPartner().toUpperCase(), "ALIPAY_WALLET")) {
                        // 客户端支付
                        final String payPartner = payPartnerTo.getPayUrl();
                        Runnable payRunnable = new Runnable() {

                            @Override
                            public void run() {
                                // 构造PayTask 对象
                                PayTask alipay = new PayTask(getActivity());
                                // 调用支付接口，获取支付结果
                                String result = alipay.pay(payPartner);
                                Message msg = new Message();
                                msg.what = SDK_PAY_FLAG;
                                msg.obj = result;
                                mHandler.sendMessage(msg);
                            }
                        };

                        // 必须异步调用
                        Thread payThread = new Thread(payRunnable);
                        payThread.start();
                    }
                }
            }
        });


}

    private void submittedSuccessfullyDialog() {

        final CustomDialog dialog = new CustomDialog(getThisContext(),
                R.layout.dialog_service_submit_finsh, R.style.myDialogTheme);

        RelativeLayout layout = (RelativeLayout) dialog.findViewById(R.id.layout);

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });

        dialog.setCancelable(true);
        dialog.show();


    }
    public void commentGoods(String olderSid){
        Intent intent=new Intent(getThisContext(), ShoppingPostActivity.class);
        intent.putExtra("OlderSid", olderSid);
        startActivity(intent);
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
