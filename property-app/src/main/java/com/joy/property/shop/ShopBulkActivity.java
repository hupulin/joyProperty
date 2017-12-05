package com.joy.property.shop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.Util.EasySlidingTabs;
import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.infrastructure.MessageToBulk;
import com.jinyi.ihome.module.newshop.CategoryChannelTo;
import com.jinyi.ihome.module.newshop.CategoryTo;
import com.jinyi.ihome.module.newshop.ChannelParam;
import com.jinyi.ihome.module.newshop.ShopListDetailTo;
import com.jinyi.ihome.module.newshop.ShopTypeTo;
import com.joy.common.api.ApiClient;
import com.joy.common.api.ApiClientBulk;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.NewShopApi;
import com.joy.property.R;
import com.joy.property.base.BaseActivity;
import com.joyhome.nacity.app.photo.util.PublicWay;
import com.joy.property.shop.fragment.GoodsCommentFragment;
import com.joy.property.shop.fragment.PropertyBulkFragment;
import com.joy.property.shop.shoputil.CarNumberUtil;
import com.joyhome.nacity.app.util.CustomDialogFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by xz on 2016/7/18.
 **/
public class ShopBulkActivity extends BaseActivity implements View.OnClickListener {

    private ViewPager viewPager;
    private EasySlidingTabs tpi;
    private List<CategoryChannelTo>typeList=new ArrayList<>();
    private List<Fragment> fragments=new ArrayList<>();
    private TextView carNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_bulk);
        initView();
        getChannel();
        PublicWay.activityList.add(this);
    }

public void initFragment(){
    for (CategoryChannelTo channelTo:typeList){
        fragments.add(new PropertyBulkFragment(channelTo.getCategoryId(),carNumber));
    }
    adapter.notifyDataSetChanged();
    tpi.notifyDataSetChanged();
}

    private void initView() {
        tpi = (EasySlidingTabs) findViewById(R.id.tpi);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        tpi.setViewPager(viewPager);
        findViewById(R.id.shopCar).setOnClickListener(this);
        carNumber = (TextView) findViewById(R.id.carNumber);
        findViewById(R.id.back).setOnClickListener(this);
        CarNumberUtil.getCarNumber(mUserHelperBulk.getSid(),getThisContext(),carNumber);
    }
    private void getChannel() {
        NewShopApi api= ApiClientBulk.create(NewShopApi.class);
        final CustomDialogFragment dialogFragment = new CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "");
        ChannelParam param=new ChannelParam();
        param.setCommunityId(mUserHelperBulk.getUserInfoTo().getApartmentSid());
        api.getBulkChannel(param, new HttpCallback<MessageToBulk<List<CategoryChannelTo>>>(getThisContext()) {
            @Override
            public void success(MessageToBulk<List<CategoryChannelTo>> msg, Response response) {
                dialogFragment.dismiss();
                if (msg.getCode() == 0) {
                    typeList.clear();
                    if (msg.getCommunityGroupGoodsCategoryList() != null) {
                        typeList.addAll(msg.getCommunityGroupGoodsCategoryList());

                        initFragment();
                    }
                }
            }

            @Override
            public void failure(RetrofitError error) {
                dialogFragment.dismiss();
            }
        });


    }
    private FragmentPagerAdapter adapter=new FragmentPagerAdapter(getSupportFragmentManager()) {
        @Override
        public CharSequence getPageTitle(int position) {
            return typeList.get(position).getCategoryName();
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return typeList==null?0:typeList.size();
        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        goToAnimation(2);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK)
            onBackPressed();
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back:
                onBackPressed();
                break;
            case R.id.shopCar:
                startActivity(new Intent(getThisContext(), ShoppingCarActivity.class));
                goToAnimation(1);

                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PublicWay.activityList.remove(this);
    }
}
