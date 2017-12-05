package com.joy.property.shop;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.Util.MyShoppingEasySlidingTabs;
import com.Util.TabsFragmentAdapter;
import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.newshop.ExpressName;
import com.joy.common.api.ApiClient;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.NewShopApi;
import com.joy.property.R;
import com.joyhome.nacity.app.base.BaseActivity;
import com.joy.property.shop.fragment.MyShoppingFragment;
import com.joy.property.shop.fragment.ShoppingExpressFragment;
import com.joyhome.nacity.app.util.CustomDialogFragment;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import retrofit.RetrofitError;
import retrofit.client.Response;
import rx.Observable;

/**
 * Created by xz on 2016/7/25.
 **/
public class ShoppingExpressActivity extends BaseActivity implements View.OnClickListener {
    private MyShoppingEasySlidingTabs easySlidingTabs;
    private ViewPager easyVP;
 private List<String>titles=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_express);
        this.initViews();
        getData();

    }

    private void getData() {
        NewShopApi api= ApiClient.create(NewShopApi.class);
        final CustomDialogFragment dialogFragment = new CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "");
        api.getExpressName(new HttpCallback<MessageTo<List<ExpressName>>>(getThisContext()) {
            @Override
            public void success(MessageTo<List<ExpressName>> msg, Response response) {
                dialogFragment.dismiss();
                if(msg.getSuccess()==0){
                    Observable.from(msg.getData()).subscribe(expressName -> titles.add(expressName.getExpressName()));
                 initData();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                super.failure(error);
                dialogFragment.dismiss();
                System.out.println(error.toString());
            }
        });

    }

    private void initViews(){
        this.easySlidingTabs = (MyShoppingEasySlidingTabs) this.findViewById(R.id.tpi);
        findViewById(R.id.iv_back).setOnClickListener(this);
        this.easyVP = (ViewPager) this.findViewById(R.id.viewPager);
    }

    private void initData() {

        List<Fragment> fragments = new LinkedList<>();
for(int i=0;i<titles.size();i++)
    fragments.add(new ShoppingExpressFragment(i+1+""));
easySlidingTabs.notifyDataSetChanged();

//        TabsFragmentAdapter adapter = new TabsFragmentAdapter(this.getSupportFragmentManager(), titles, fragments);
//        this.easyVP.setAdapter(adapter);
//        this.easySlidingTabs.setViewPager(this.easyVP);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
        }
    }
}
