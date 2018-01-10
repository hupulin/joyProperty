package com.joy.property.worksign;


import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.Util.signencode.SXHttpUtils;
import com.Util.signencode.aes.WLHSecurityUtils;
import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.worksign.CheckDeviceTo;
import com.jinyi.ihome.module.worksign.SignApartmentTo;
import com.jinyi.ihome.module.worksign.SignJsonTo;
import com.jinyi.ihome.module.worksign.SignMessageTo;
import com.joy.common.api.ApiClient;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.VendorApi;
import com.joy.library.fragment.CustomDialogFragment;
import com.joy.property.R;
import com.joy.property.base.BaseActivity;
import com.joy.property.utils.ACache;
import com.joy.property.utils.CustomDialog;
import com.joy.property.utils.StatuBarUtil;
import com.joy.property.worksign.adapter.SignApartmentAdapter;
import com.joy.property.worksign.adapter.SignBaseParam;
import com.joy.property.worksign.adapter.SignSubmitJsonTo;
import com.joy.property.worksign.fragment.FootprintFragment;
import com.joy.property.worksign.fragment.SignFragment;
import com.joyhome.nacity.app.photo.util.Bimp;
import com.joyhome.nacity.app.photo.util.ImageItem;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import retrofit.client.Response;


/**
 * Created by xzz on 2017/12/28.
 **/
public class WorkSignActivity extends BaseActivity implements View.OnClickListener {
    private List<Fragment> fragmentList = new ArrayList<>();
    private ViewPager viewPager;
    private LinearLayout signButtonLayout;
    private LinearLayout footprintLayout;
    private TextView apartmentName;
    private RelativeLayout checkLayout;
    private SignFragment signFragment;

    private boolean isRebind;
    private View offLineIcon;
    private List<SignSubmitJsonTo> cacheSubmitList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_sign);
        findView();
        checkDevice();
       getCacheData();

    }


    private void findView() {
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        signButtonLayout = (LinearLayout) findViewById(R.id.sign_button_layout);
        footprintLayout = (LinearLayout) findViewById(R.id.footprint_layout);
        signButtonLayout.setOnClickListener(this);
        footprintLayout.setOnClickListener(this);
        offLineIcon = findViewById(R.id.offline_upload_icon);
        findViewById(R.id.back).setOnClickListener(v -> {finish();goToAnimation(2);});
        ((TextView) findViewById(R.id.user_name)).setText(mUserHelper.getUserInfoTo().getName() + "(" + mUserHelper.getPhone() + ")");
        apartmentName = (TextView) findViewById(R.id.apartment_name);
        apartmentName.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        apartmentName.setOnClickListener(this);
        findViewById(R.id.cancel).setOnClickListener(this);
        findViewById(R.id.confirm).setOnClickListener(this);
        checkLayout = (RelativeLayout) findViewById(R.id.check_layout);
        findViewById(R.id.sign_bind).setOnClickListener(this);
        findViewById(R.id.offline_upload).setOnClickListener(this);
    }

    private void initFragment() {
        signFragment = new SignFragment();
        fragmentList.add(signFragment);
        fragmentList.add(new FootprintFragment());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                if (position != 0) {
                    signButtonLayout.setAlpha((float) 0.5);
                    footprintLayout.setAlpha(1);
                } else {
                    footprintLayout.setAlpha((float) 0.5);
                    signButtonLayout.setAlpha(1);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return 2;
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.footprint_layout:

                viewPager.setCurrentItem(1);
                break;
            case R.id.sign_button_layout:

                viewPager.setCurrentItem(0);
                break;
            case R.id.apartment_name:
                getApartment();
                break;
            case R.id.confirm:
                submitDevice(null,isRebind?0:1);
                break;
            case R.id.cancel:
                if (!isRebind) {
                    finish();
                    goToAnimation(2);
                } else
                    checkLayout.setVisibility(View.GONE);

                break;
            case R.id.sign_bind:
                signRebind();
                break;
            case R.id.offline_upload:
                if (cacheSubmitList!=null&&cacheSubmitList.size()>0){
                   startActivity(new Intent(getThisContext(),OfflineUploadActivity.class));
                    goToAnimation(1);
                    finish();
                }else {
                    ToastShowLong(getThisContext(),"没有未提交的签到");
                }

                break;
        }
    }

    /**
     * 重新绑定设备到小区
     */
    private void signRebind() {
        checkLayout.setVisibility(View.VISIBLE);

        ((TextView) findViewById(R.id.title_text)).setText("重新绑定");
        isRebind = true;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void checkDevice() {
        SignJsonTo jsonTo = new SignJsonTo();
        jsonTo.setDeviceId("1909DCFD-243D-2F68-233A-250C9C9B571E");
        jsonTo.setTradeType("GetCheck");

        jsonTo.setUniqueStr(((TelephonyManager) getThisContext().getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId());
        jsonTo.setOpenId(mUserHelper.getSid());
        //    jsonTo.setParkName("幸福家园");
        SignBaseParam param = new SignBaseParam();
        param.setParamData(WLHSecurityUtils.toURLDecoded(WLHSecurityUtils.encrypt(new Gson().toJson(jsonTo))));
        System.out.println(new Gson().toJson(jsonTo) + "json");
        Map<String, String> params = new HashMap<>();
        params.put("ParamData", param.getParamData());
        SXHttpUtils.requestPostData(WorkSignActivity.this, "http://nd.alipayer.cn/index.php/backend/api.html", params, "UTF-8", new SXHttpUtils.LoadListener() {
            @Override
            public void onLoadSuccess(String result) {
                SignMessageTo<CheckDeviceTo> msg = new Gson().fromJson(new String(WLHSecurityUtils.decrypt(result.getBytes())), SignMessageTo.class);


                if (msg.getResultCode() == 0) {
                    CheckDeviceTo checkDeviceTo = new Gson().fromJson(new Gson().toJson(msg.getResultContent()), CheckDeviceTo.class);
                    if (checkDeviceTo.getStatus() == 0)
                        initFragment();
                    else if (checkDeviceTo.getStatus() == 1 && TextUtils.isEmpty(checkDeviceTo.getParkName())) {
                        checkLayout.setVisibility(View.VISIBLE);
                        StatuBarUtil.setStatueBarWhiteColor(getWindow());
                    } else if (checkDeviceTo.getStatus() == 2) {
                        submitDeviceDialog(checkDeviceTo.getParkName(), "您新的设备正在审核中，再次提交审核");
                    } else
                        submitDeviceDialog(checkDeviceTo.getParkName(), "您在新的设备登录巡更，需提交审核");

                }


            }

            @Override
            public void onLoadError() {

            }
        });
    }

    //选择小区
    public void getApartment() {
        final CustomDialogFragment dialogFragment = new CustomDialogFragment();
        final CustomDialog dialog = new CustomDialog(getThisContext(), R.layout.dialog_apartment, R.style.myDialogTheme);
        TextView mCancel = (TextView) dialog.findViewById(R.id.cancel);
        mCancel.setOnClickListener(v -> dialog.dismiss());
        dialogFragment.show(getSupportFragmentManager(), "");
        final ListView mList = (ListView) dialog.findViewById(R.id.list);
        SignJsonTo jsonTo = new SignJsonTo();
        jsonTo.setDeviceId("1909DCFD-243D-2F68-233A-250C9C9B571E");
        jsonTo.setTradeType("GetProjects");

        jsonTo.setUniqueStr(((TelephonyManager) getThisContext().getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId());
        jsonTo.setOpenId(mUserHelper.getSid());
        //    jsonTo.setParkName("幸福家园");
        SignBaseParam param = new SignBaseParam();
        param.setParamData(WLHSecurityUtils.toURLDecoded(WLHSecurityUtils.encrypt(new Gson().toJson(jsonTo))));
        System.out.println(new Gson().toJson(jsonTo) + "json");
        Map<String, String> params = new HashMap<>();
        params.put("ParamData", param.getParamData());
        SXHttpUtils.requestPostData(WorkSignActivity.this, "http://nd.alipayer.cn/index.php/backend/api.html", params, "UTF-8", new SXHttpUtils.LoadListener() {
            @Override
            public void onLoadSuccess(String result) {
                SignMessageTo<List<SignApartmentTo>> msg = new Gson().fromJson(new String(WLHSecurityUtils.decrypt(result.getBytes())), SignMessageTo.class);
                System.out.println(msg + "************************");
                dialogFragment.dismiss();
                if (msg.getResultCode() == 0) {

                    final List<SignApartmentTo> infoList = new ArrayList<>();

                    infoList.addAll(new Gson().fromJson(new Gson().toJson(msg.getResultContent()), new TypeToken<List<SignApartmentTo>>() {
                    }.getType()));
                    SignApartmentAdapter mAdapter = new SignApartmentAdapter(getThisContext());
                    mAdapter.setList(infoList);
                    mList.setAdapter(mAdapter);

                    mList.setOnItemClickListener((parent, view, position, id) -> {
                        SignApartmentTo apartment = infoList.get(position);
                        apartmentName.setText(apartment.getTitle());

                        dialog.dismiss();
                    });
                    dialog.show();
                } else {
                    Toast.makeText(getThisContext(), msg.getReason(), Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onLoadError() {
                dialogFragment.dismiss();
            }
        });
    }

    /**
     * 是否申请更换手机对话框
     */
    public void submitDeviceDialog(String parkName, String title) {
        CustomDialog alertDialog = new CustomDialog(getThisContext(), R.layout.dialog_submit_device, R.style.myDialogTheme);
        ((TextView) alertDialog.findViewById(R.id.title)).setText(title);
        alertDialog.findViewById(R.id.btn_confirm).setOnClickListener(v -> {
            submitDevice(parkName,0);
            alertDialog.dismiss();
        });
        alertDialog.findViewById(R.id.btn_close).setOnClickListener(v -> {
            alertDialog.dismiss();
            finish();
        });
        alertDialog.show();
    }

    //type 1第一次绑定 0其它情况绑定
    public void submitDevice(String parkName,int type) {
        if (parkName == null) {
            if (TextUtils.isEmpty(apartmentName.getText().toString())) {
                ToastShowLong(getThisContext(), "请选择绑定小区");
                return;
            }
        }

        SignJsonTo jsonTo = new SignJsonTo();
        jsonTo.setDeviceId("1909DCFD-243D-2F68-233A-250C9C9B571E");
        jsonTo.setTradeType("POSTCheck");

        jsonTo.setUniqueStr(((TelephonyManager) getThisContext().getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId());
        jsonTo.setOpenId(mUserHelper.getSid());
        jsonTo.setFristCheck(type);
        jsonTo.setParkName(parkName == null ? apartmentName.getText().toString() : parkName);
        SignBaseParam param = new SignBaseParam();
        param.setParamData(WLHSecurityUtils.toURLDecoded(WLHSecurityUtils.encrypt(new Gson().toJson(jsonTo))));
        System.out.println(new Gson().toJson(jsonTo) + "json");
        Map<String, String> params = new HashMap<>();
        params.put("ParamData", param.getParamData());
        SXHttpUtils.requestPostData(WorkSignActivity.this, "http://nd.alipayer.cn/index.php/backend/api.html", params, "UTF-8", new SXHttpUtils.LoadListener() {
            @Override
            public void onLoadSuccess(String result) {
                SignMessageTo msg = new Gson().fromJson(new String(WLHSecurityUtils.decrypt(result.getBytes())), SignMessageTo.class);
                if (msg.getResultCode() == 0) {
                    Toast.makeText(getThisContext(), "绑定小区成功", Toast.LENGTH_LONG).show();
                    checkLayout.setVisibility(View.GONE);
                    initFragment();
                } else
                    Toast.makeText(getThisContext(), msg.getReason(), Toast.LENGTH_LONG).show();


            }

            @Override
            public void onLoadError() {

            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        signFragment.getData(null);
        getCacheData();
    }

    /**
     * 查看是否存在缓存
     */
    public void getCacheData() {
        cacheSubmitList = JSON.parseArray(new ACache().getAsString("SignSubmitJson"), SignSubmitJsonTo.class);
        if (cacheSubmitList!=null&&cacheSubmitList.size()>0)
            offLineIcon.setBackgroundResource(R.drawable.sign_offline_upload_select);
        else
            offLineIcon.setBackgroundResource(R.drawable.sign_offline_upload_un_select);

    }




}