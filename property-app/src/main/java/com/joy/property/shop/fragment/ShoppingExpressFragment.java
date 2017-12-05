package com.joy.property.shop.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.newshop.MyShoppingTo;
import com.jinyi.ihome.module.newshop.ShoppingExpressTo;
import com.joy.common.api.ApiClient;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.NewShopApi;
import com.joy.property.R;
import com.joy.property.base.BaseFragment;
import com.joy.property.shop.adapter.MyShoppingAdapter;
import com.joyhome.nacity.app.util.CustomDialogFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit.RetrofitError;
import retrofit.client.Response;


@SuppressLint("ValidFragment")
public class ShoppingExpressFragment extends BaseFragment {


    private MyShoppingAdapter adapter;
    private List<MyShoppingTo> shoppingList = new ArrayList<>();
    private String type;
    private TextView expressCompany;
    private TextView expressNumber;
    private TextView deliverName;
    private TextView deliverPhone;
    private TextView expressInformation;

    public ShoppingExpressFragment(String type) {
        this.type = type;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View mRootView = inflater.inflate(R.layout.fragment_shopping_express, container, false);
        initView(mRootView);
        setData();
        return mRootView;


    }


    public void initView(View mRootView) {


        expressCompany = (TextView) mRootView.findViewById(R.id.expressCompany);
        expressNumber = (TextView) mRootView.findViewById(R.id.expressNumber);
        deliverName = (TextView) mRootView.findViewById(R.id.deliverName);
        deliverPhone = (TextView) mRootView.findViewById(R.id.deliverPhone);
        expressInformation = (TextView) mRootView.findViewById(R.id.expressInformation);
    }


    private void setData() {
        NewShopApi api = ApiClient.create(NewShopApi.class);
        final CustomDialogFragment dialogFragment = new CustomDialogFragment();
        dialogFragment.show(getFragmentManager(), "");
api.getExpress(type, new HttpCallback<MessageTo<ShoppingExpressTo>>(getThisContext()) {
    @Override
    public void success(MessageTo<ShoppingExpressTo> msg, Response response) {
        dialogFragment.dismiss();
        if(msg.getSuccess()==0){
            setView(msg.getData());
        }else
            Toast.makeText(getThisContext(),msg.getMessage(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void failure(RetrofitError error) {
        super.failure(error);
        dialogFragment.dismiss();
        System.out.println(error);
    }
});

    }


    @Override
    protected Context getThisContext() {
        return getActivity();
    }
    public void setView(ShoppingExpressTo expressTo){
        expressCompany.setText("配送快递"+expressTo.getCompany());
        expressNumber.setText("快递单号:"+expressTo.getExpressNumber());
        expressInformation.setText("\\u3000\\u3000\\u3000\\u3000\\u3000\\u3000\\u3000"+expressTo.getExpressInformation());
       deliverName.setText("配送员姓名:"+expressTo.getDeliverName());
        deliverPhone.setText("配送员手机:"+expressTo.getDeliverPhone());
        if(expressTo.isIsProperty()){
            expressNumber.setVisibility(View.GONE);
            expressInformation.setVisibility(View.GONE);
            deliverPhone.setVisibility(View.GONE);
            expressCompany.setText("配送员姓名:" + expressTo.getDeliverName());
            deliverName.setText("配送员手机:"+expressTo.getDeliverPhone());
        }
    }
}
