package com.joy.property.vehicle;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.home.ServiceInspectionPositionTo;
import com.jinyi.ihome.module.home.ServiceTypeTo;
import com.joy.common.api.ApiClient;
import com.joy.common.api.HomeApi;
import com.joy.common.api.HttpCallback;
import com.joy.property.R;
import com.joy.property.base.BaseActivity;
import com.joy.property.utils.ViewPagerCompat;
import com.joy.property.vehicle.adapter.PositionAdapter;
import com.joy.property.vehicle.adapter.RefreshEvent;
import com.joyhome.nacity.app.util.CustomDialogFragment;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by xz on 2017/3/14.
 *
 */
public class SelectInLegalActivity extends BaseActivity implements View.OnClickListener {

    private GridLayout gridView;
    private List<ServiceTypeTo> serviceList=new ArrayList<>();
    private PositionAdapter adapter;
    private List<View>viewList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_in_legal);
        getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        findView();
        getData();
        initView(serviceList);

    }




    private void findView() {
        gridView = (GridLayout) findViewById(R.id.gridView);


    }

    private void getData() {

    serviceList.addAll((List<ServiceTypeTo>) getIntent().getSerializableExtra("InlegalList"));
        findViewById(R.id.back).setOnClickListener(this);

    }
    private void initView(List<ServiceTypeTo> typeTos) {

        View baseView=View.inflate(getThisContext(),R.layout.grid_view_base,null);
        GridLayout baseGridView = (GridLayout) baseView.findViewById(R.id.gridView);


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

        if (serviceTypeTo.getChildList() != null) {
            initView(serviceTypeTo.getChildList());
          setAnnimation(gridView.getChildAt(gridView.getChildCount() - 2), gridView.getChildCount()>=3?gridView.getChildCount()-2:0);
          setAnnimation(gridView.getChildAt(gridView.getChildCount()-1), gridView.getChildCount()>=3?gridView.getChildCount()-2:0);
        }else {
            EventBus.getDefault().post(new RefreshEvent(serviceTypeTo.getTypeSid(),1,serviceTypeTo.getTypeName()));
            finish();
        }
    });

            baseGridView.addView(view);


        }
        GridLayout.LayoutParams layoutParamsGird = new GridLayout.LayoutParams();
        layoutParamsGird.height= ViewPagerCompat.LayoutParams.WRAP_CONTENT;
        layoutParamsGird.width= (int) (getScreenWidth()*0.66666);
        layoutParamsGird.setMargins(0,0,0,0);
        baseView.setLayoutParams(layoutParamsGird);
        viewList.add(baseView);

        gridView.addView(baseView);
        if (viewList.size()>=2) {

        }

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
 // goToAnimation(2);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK)
            onBackPressed();
        return super.onKeyDown(keyCode, event);
    }
    public void setAnnimation(final View view,int index) {

		ObjectAnimator oa = ObjectAnimator.ofFloat(view, "translationX", 0-index*getScreenWidth()*2/3, -getScreenWidth()*2/3-index*getScreenWidth()*2/3);
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
}
