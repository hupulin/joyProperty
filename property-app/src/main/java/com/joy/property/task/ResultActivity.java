
package com.joy.property.task;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.joy.property.R;
import com.joy.property.base.BaseActivity;
import com.joy.property.task.fragment.ResultFragment;

/**
 * Created by usb on 2016/6/23.
 */
public class ResultActivity extends BaseActivity implements View.OnClickListener{

private String selectDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        findById();

        initIntentDatas();

        initDatas();


    }
    private void findById() {
        findViewById(R.id.back).setOnClickListener(this);



    }
    private void initIntentDatas() {

        selectDate = getIntent().getExtras().getString("date");
    }
    private void initDatas() {

        FragmentManager fragmentManager  =   getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ResultFragment resultFragment = new ResultFragment();
        Bundle bundle = new Bundle();
        bundle.putString("date",selectDate);
        resultFragment.setArguments(bundle);
        fragmentTransaction.add(R.id.result,resultFragment);
        fragmentTransaction.commit();

    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                onBackPressed();
                break;

            default:
                break;
        }

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


}
