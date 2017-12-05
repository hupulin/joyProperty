package com.joy.property.shop;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.Util.MyShoppingEasySlidingTabs;
import com.Util.TabsFragmentAdapter;
import com.jinyi.ihome.infrastructure.MessageToBulk;
import com.joy.common.api.ApiClientBulk;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.NewShopApi;
import com.joy.property.R;
import com.joy.property.base.BaseActivity;

import com.joy.property.shop.fragment.FinishFragment;
import com.joy.property.shop.fragment.MyShoppingFragment;
import com.joy.property.shop.fragment.WaiteCommentFragment;
import com.joy.property.shop.fragment.WaitePayFragment;
import com.joy.property.shop.fragment.WaiteReiciveFragment;
import com.joy.property.shop.fragment.WaiteSendFragment;
import com.joy.property.shop.shoputil.CarNumberUtil;

import com.joyhome.nacity.app.util.CustomDialog;
import com.joyhome.nacity.app.util.CustomDialogFragment;
import com.joyhome.nacity.app.util.SpUtil;

import java.util.LinkedList;
import java.util.List;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by xz on 2016/7/25.
 **/
public class MyShoppingActivity extends BaseActivity implements View.OnClickListener {
    private  MyShoppingEasySlidingTabs easySlidingTabs;
    private  ViewPager easyVP;
    public static final String[] titles = {"全 部", "待付款", "待发货", "待收货","待评价","已完成"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_shopping);
        this.initViews();
        this.initData();
        initCarNumber();
    }

    private void initViews(){
        this.easySlidingTabs = (MyShoppingEasySlidingTabs) this.findViewById(R.id.tpi);
        findViewById(R.id.iv_back).setOnClickListener(this);
        this.easyVP = (ViewPager) this.findViewById(R.id.viewPager);
        findViewById(R.id.shopCar).setOnClickListener(this);
        findViewById(R.id.customerService).setOnClickListener(this);
    }

   public void initCarNumber(){
    TextView carNumber = (TextView) findViewById(R.id.carNumber);
    CarNumberUtil.getCarNumber(mUserHelperBulk.getSid(), getThisContext(), carNumber);
    }

    private void initData() {
        List<Fragment> fragments = new LinkedList<>();
        fragments.add(new MyShoppingFragment(0));
        fragments.add(new WaitePayFragment(1));
        fragments.add(new WaiteSendFragment(2));
        fragments.add(new WaiteReiciveFragment(3));
        fragments.add(new WaiteCommentFragment(4));
        fragments.add(new FinishFragment(5));
        TabsFragmentAdapter adapter = new TabsFragmentAdapter(this.getSupportFragmentManager(), titles, fragments);
        this.easyVP.setAdapter(adapter);
        this.easySlidingTabs.setViewPager(this.easyVP);
        if (getIntent().getBooleanExtra("IsFinishPay",false)) {
            if (getIntent().getIntExtra("CurrentPage", 0)== 0)
                easyVP.setCurrentItem(2);
            else
                easyVP.setCurrentItem(getIntent().getIntExtra("CurrentPage",0));
        }
        else  {
            easyVP.setCurrentItem(getIntent().getIntExtra("CurrentPage",0));
        }
        if (SpUtil.getInt(getThisContext(), "ShopCurrentPage")!=0) {
            easyVP.setCurrentItem(SpUtil.getInt(getThisContext(), "ShopCurrentPage"));
            SpUtil.put(getThisContext(), "ShopCurrentPage", 0);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.shopCar:
                Intent intent=new Intent(getThisContext(),ShoppingCarActivity.class);
                startActivity(intent);
                goToAnimation(1);
                break;
            case R.id.customerService:
                getServicePhone();
                break;
        }
    }
public void getServicePhone(){
    NewShopApi api= ApiClientBulk.create(NewShopApi.class);
    CustomDialogFragment dialogFragment=new CustomDialogFragment();
    dialogFragment.show(getSupportFragmentManager(), "");
    api.getCustomerService(new HttpCallback<MessageToBulk>(getThisContext()) {
        @Override
        public void success(MessageToBulk msg, Response response) {
            dialogFragment.dismiss();
            if (msg.getCode()==0){
                if (msg.getCustomerServiceVo()!=null&&msg.getCustomerServiceVo().getCustomerServicePhone()!=null)
                mobileShowDialog(msg.getCustomerServiceVo().getCustomerServicePhone());
            }
        }

        @Override
        public void failure(RetrofitError error) {
            dialogFragment.dismiss();
        }
    });

}
    private void mobileShowDialog(String phoneNumber) {
        final CustomDialog dialog = new CustomDialog(getThisContext(), R.layout.dialog_contact_service, R.style.myDialogTheme);

        Button btnAdd = (Button) dialog.findViewById(R.id.confirm);
        Button btnCancel = (Button) dialog.findViewById(R.id.cancel);
        TextView title = (TextView) dialog.findViewById(R.id.dialogText);
        title.setText("确认拨打电话："+phoneNumber);
        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL,
                    Uri.parse("tel:" + phoneNumber));
            startActivity(intent);
            dialog.dismiss();

        });
        btnCancel.setOnClickListener(v1 -> {
            dialog.dismiss();
        });
        dialog.findViewById(R.id.parent).setOnClickListener(v -> dialog.dismiss());
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();

    }
    private void changeNameShowDialog(String  phoneNumber) {
        final CustomDialog dialog = new CustomDialog(getThisContext(), R.layout.change_name_dialog, R.style.myDialogTheme);

        Button btnAdd = (Button) dialog.findViewById(R.id.btn_add);
        Button btnCancel = (Button) dialog.findViewById(R.id.btn_cancel);
        TextView title = (TextView) dialog.findViewById(R.id.title);
        title.setText("确认拨打电话："+phoneNumber);
        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL,
                    Uri.parse("tel:" + phoneNumber));
            startActivity(intent);
            dialog.dismiss();

        });
        btnCancel.setOnClickListener(v1 -> {
            dialog.dismiss();
        });
        dialog.findViewById(R.id.parent).setOnClickListener(v -> dialog.dismiss());
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();

    }
}
