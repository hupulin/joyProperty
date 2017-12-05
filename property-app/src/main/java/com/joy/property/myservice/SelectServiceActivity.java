package com.joy.property.myservice;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.home.ServiceInspectionPositionTo;
import com.jinyi.ihome.module.home.ServiceTypeParam;
import com.jinyi.ihome.module.home.ServiceTypeTo;
import com.joy.common.api.ApiClient;
import com.joy.common.api.ApiClientNormal;
import com.joy.common.api.HomeApi;
import com.joy.common.api.HttpCallback;
import com.joy.property.R;
import com.joy.property.base.BaseActivity;
import com.joy.property.myservice.adapter.ApplyServiceAdapter;
import com.joy.property.utils.ViewPagerCompat;
import com.joy.property.vehicle.adapter.PositionAdapter;
import com.joy.property.vehicle.adapter.RefreshEvent;
import com.joy.property.vehicle.adapter.RefreshEventOther;
import com.joyhome.nacity.app.util.CustomDialogFragment;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by xz on 2017/3/14.
 */
public class SelectServiceActivity extends BaseActivity implements View.OnClickListener {

    private GridLayout gridView;
    private TextView mAllType;
    private List<ServiceInspectionPositionTo> positionList=new ArrayList<>();
    private String allType="全部类型";
    private String allTypeSid;
    private String categorySid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_filter_dailog);
        getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        findView();

        getData();
    }




    private void findView() {
        gridView = (GridLayout) findViewById(R.id.gridView);
        findViewById(R.id.back).setOnClickListener(this);
        mAllType=(TextView) findViewById(R.id.all_type);

        if("1".equals(getIntent().getStringExtra("type"))){
            mAllType.setVisibility(View.GONE);
        }
        mAllType.setOnClickListener(this);
        findViewById(R.id.cancel).setOnClickListener(this);
    }

    private void getData() {
    if (!TextUtils.isEmpty(getIntent().getStringExtra("CategorySid"))){
        setList(getIntent().getStringExtra("CategorySid"),"全部");
    }else {
        initViewOne();
    }



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK)

            onBackPressed();
        return super.onKeyDown(keyCode, event);
    }

    private void initView(List<ServiceTypeTo> typeTos) {

        View baseView=View.inflate(getThisContext(), R.layout.select_category_item, null);
        GridLayout baseGridView = (GridLayout) baseView.findViewById(R.id.gridView);
        baseView.findViewById(R.id.allLayout).setVisibility(View.GONE);

        for (int i=0;i<typeTos.size();i++){
            ServiceTypeTo typeTo=typeTos.get(i);
            View view=View.inflate(getThisContext(),R.layout.select_position_item,null);
            TextView positionName = (TextView) view.findViewById(R.id.positionName);
            ImageView nextIcon = (ImageView) view.findViewById(R.id.nextIcon);
            positionName.setText(typeTo.getTypeName());
            nextIcon.setVisibility(typeTo.getChildList() == null ? View.GONE : View.VISIBLE);
            view.setTag(typeTo);
            view.setOnClickListener(v -> {

                ServiceTypeTo serviceTypeTo = (ServiceTypeTo) v.getTag();
                allType=serviceTypeTo.getTypeName();
                allTypeSid=serviceTypeTo.getTypeSid();
                if (serviceTypeTo.getChildList() != null) {

                    initView(serviceTypeTo.getChildList());
                    setAnnimation(gridView.getChildAt(gridView.getChildCount() - 2), gridView.getChildCount()>=3?gridView.getChildCount()-2:0);
                    setAnnimation(gridView.getChildAt(gridView.getChildCount()-1), gridView.getChildCount()>=3?gridView.getChildCount()-2:0);
                }else {
                    EventBus.getDefault().post(new RefreshEventOther(serviceTypeTo.getTypeSid(),3,serviceTypeTo.getTypeName(),categorySid));
                    finish();


                }
            });

            baseGridView.addView(view);


        }
        GridLayout.LayoutParams layoutParamsGird = new GridLayout.LayoutParams();
        layoutParamsGird.height= ViewPagerCompat.LayoutParams.WRAP_CONTENT;
        layoutParamsGird.width= (int) (getScreenWidth()*0.666666);
        layoutParamsGird.setMargins(0, 0, 0, 0);
        baseView.setLayoutParams(layoutParamsGird);


        gridView.addView(baseView);


    }

    private void initViewOne() {

        View baseView=View.inflate(getThisContext(), R.layout.select_category_item, null);

        baseView.findViewById(R.id.houseKeeping).setOnClickListener(v -> setList("9098ED29-072D-4653-A37D-3C2F6DF80861","家政服务"));
        baseView.findViewById(R.id.roomLayout).setOnClickListener(v -> setList("BCCF6994-9449-4E6D-9F5B-09CE08AD9353","入室维修"));
        baseView.findViewById(R.id.publicLayout).setOnClickListener(v -> setList("C733AA3D-32FA-4F5B-B299-061044661DC0","公共维修"));
        baseView.findViewById(R.id.inspectLayout).setOnClickListener(v -> setList("51979B62-10E6-44C7-88B9-4B239B1CE02F","巡检"));
        baseView.findViewById(R.id.suggest).setOnClickListener(v ->{allTypeSid="9B773735-1E0E-4677-A3B5-19A50B58D15D";allType="建议"; });
        baseView.findViewById(R.id.praise).setOnClickListener(v ->{allTypeSid="3198DD68-1346-4856-BD50-90E9373559A0";allType="表扬";});
        baseView.findViewById(R.id.complaintLayout).setOnClickListener(v -> setList("7D2B996C-12EC-4CD4-8499-B453E96AF11F","投诉"));
        baseView.findViewById(R.id.carInspectLayout).setOnClickListener(v -> setList("5CFB60A1-C1FC-4751-B123-05157F02C70D","违停管理"));
//       baseView.findViewById(R.id.inspectLayout).setOnClickListener(v -> setList("B89C08B9-CD76-4B94-AE27-2617157180EF","日常工单"));

        GridLayout.LayoutParams layoutParamsGird = new GridLayout.LayoutParams();
        layoutParamsGird.height= ViewPagerCompat.LayoutParams.MATCH_PARENT;
        layoutParamsGird.width= (int) (getScreenWidth()*0.666666);
        layoutParamsGird.setMargins(0, 0, 0, 0);
        baseView.setLayoutParams(layoutParamsGird);


        gridView.addView(baseView);

    }
    public void setAnnimation(final View view,int index) {

        ObjectAnimator oa = ObjectAnimator.ofFloat(view, "translationX", 0 - index *getScreenWidth()*2/3, -getScreenWidth()*2/3 - index * getScreenWidth()*2/3);
        oa.setDuration(500);
        oa.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                if (gridView.getChildCount()>1) {
                    backAnimation(gridView.getChildAt(gridView.getChildCount() - 1), gridView.getChildCount() >= 3 ? gridView.getChildCount()-2 : 0, false);
                    backAnimation(gridView.getChildAt(gridView.getChildCount() - 2), gridView.getChildCount() >= 3 ? gridView.getChildCount()-2 : 0, true);
                }else
                    finish();
                break;

            case R.id.all_type:
                EventBus.getDefault().post(new RefreshEventOther(allTypeSid,3,allType,categorySid));
                finish();
                break;
            case R.id.cancel:
                finish();
                break;
        }
    }
    public void backAnimation(final View view,int index,boolean move) {

        ObjectAnimator oa = ObjectAnimator.ofFloat(view, "translationX", -getScreenWidth()*2/3-index*getScreenWidth()*2/3, 0-index*getScreenWidth()*2/3);
        oa.setDuration(500);
        oa.start();

        oa.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (move)
                    gridView.removeView(gridView.getChildAt(gridView.getChildCount()-1));
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }
    private void setList(String typeSid,String typeName) {
        categorySid=typeSid;
        allType=typeName;

        HomeApi api = ApiClient.create(HomeApi.class);

        final ServiceTypeParam typeParam = new ServiceTypeParam();
        typeParam.setApartmentSid(getIntent().getStringExtra("ApartmentSid"));

        typeParam.setCategorySid(typeSid);

        final com.joyhome.nacity.app.util.CustomDialogFragment dialogFragment = new com.joyhome.nacity.app.util.CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "");
        api.findTypeByCategory(typeParam, new HttpCallback<MessageTo<List<ServiceTypeTo>>>(getThisContext()) {
            @Override
            public void success(MessageTo<List<ServiceTypeTo>> msg, Response response) {

                if (msg == null) return;
                if (msg.getSuccess() == 0) {
                    dialogFragment.dismissAllowingStateLoss();
                    initView(msg.getData());
                    if (gridView.getChildCount()>=2) {
                        setAnnimation(gridView.getChildAt(gridView.getChildCount() - 2), gridView.getChildCount() >= 3 ? gridView.getChildCount() - 2 : 0);
                        setAnnimation(gridView.getChildAt(gridView.getChildCount() - 1), gridView.getChildCount() >= 3 ? gridView.getChildCount() - 2 : 0);
                    }

                } else {

                    Toast.makeText(getThisContext(), msg.getMessage(), Toast.LENGTH_LONG).show();
                }


            }

            @Override
            public void failure(RetrofitError error) {
                dialogFragment.dismissAllowingStateLoss();

            }
        });


    }
}
