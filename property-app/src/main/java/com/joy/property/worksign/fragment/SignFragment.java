package com.joy.property.worksign.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Network;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.Util.signencode.SXHttpUtils;
import com.Util.signencode.aes.WLHSecurityUtils;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.google.gson.Gson;
import com.jinyi.ihome.module.worksign.SignJsonTo;
import com.jinyi.ihome.module.worksign.SignMainPageTo;
import com.jinyi.ihome.module.worksign.SignMessageTo;
import com.joy.library.utils.DateUtil;
import com.joy.property.R;
import com.joy.property.base.BaseFragment;
import com.joy.property.utils.NetTimeUtil;
import com.joy.property.worksign.SignDetailActivity;
import com.joy.property.worksign.SignSubmitActivity;
import com.joy.property.worksign.WorkContentActivity;
import com.joy.property.worksign.adapter.SignBaseParam;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by xzz on 2017/12/28.
 **/
public class SignFragment extends BaseFragment implements View.OnClickListener {

    private TextView workContent;
    public TextView workerName;

    private TextView signAddress;
    private TextView signRecord;
    private String deviceId;
    public static String workerNameInfo;
    public LocationClient mLocationClient = null;
    private MyLocationListener myListener = new MyLocationListener();
    private View rootView;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 20:
                    currentTime.setText(DateUtil.getDateString(DateUtil.mTimeFormat));
                    break;
            }
        }
    };
    private TextView currentTime;
    private Thread timeThread;
    private String parkName;

    public SignFragment(String parkName) {
        this.parkName = parkName;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_sign, container, false);
        initView(rootView);

        mLocationClient = new LocationClient(getThisContext());
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();

        option.setIsNeedAddress(true);

        getData(null);


        mLocationClient.setLocOption(option);
        mLocationClient.start();
        setCountTime();

        return rootView;
    }

    /**
     * 签到圆里面显示的倒计时
     */
    private void setCountTime() {
        timeThread = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                SystemClock.sleep(1000);
                handler.sendEmptyMessage(20);
            }

        });
        timeThread.start();
    }

    private void initView(View rootView) {
        rootView.findViewById(R.id.sign_click).setOnClickListener(this);
        rootView.findViewById(R.id.sign_card_bg).setOnClickListener(this);
        rootView.findViewById(R.id.click_mask).setOnClickListener(this);
        workContent = (TextView) rootView.findViewById(R.id.work_content);
        workerName = (TextView) rootView.findViewById(R.id.worker_name);
        signAddress = (TextView) rootView.findViewById(R.id.sign_address);
        signRecord = (TextView) rootView.findViewById(R.id.sign_record);
        currentTime = (TextView) rootView.findViewById(R.id.current_time);
    }

    public void setLastSignLayout(SignMainPageTo mainPageTo) {

        rootView.findViewById(R.id.last_sign_layout).setVisibility(View.VISIBLE);
        ((TextView) rootView.findViewById(R.id.last_sign_address)).setText(mainPageTo.getSignitem().getAddress());
        ((TextView) rootView.findViewById(R.id.last_work_content)).setText("工作内容：" + mainPageTo.getSignitem().getWork_cont());
        ((TextView) rootView.findViewById(R.id.last_sign_time)).setText("上次签到时间  " + mainPageTo.getSignitem().getCreatetime());
        rootView.findViewById(R.id.look_detail).setOnClickListener(v -> {
            Intent intent = new Intent(getThisContext(), SignDetailActivity.class);
            intent.putExtra("SignSid", mainPageTo.getSignitem().getLogid());
            startActivity(intent);
            goToAnimation(1);
        });


    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.sign_click:
                handler.postDelayed(() -> {
                    Intent intent = new Intent(getThisContext(), SignSubmitActivity.class);
                    intent.putExtra("WorkContent", workContent.getText().toString());
                    intent.putExtra("DeviceId", deviceId);
                    intent.putExtra("ParkName",parkName);
                    intent.putExtra("SignAddress", signAddress.getText().toString());
                    startActivity(intent);

                    goToAnimation(1);
                }, 200);

                break;
            case R.id.sign_card_bg:
                handler.postDelayed(() -> {
                    Intent intent = new Intent(getThisContext(), WorkContentActivity.class);
                    intent.putExtra("ParkName", parkName);
                    startActivityForResult(intent, 10);
                    goToAnimation(1);
                }, 200);
                break;
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10 && data != null)
            workContent.setText(data.getStringExtra("WorkContent"));
    }

    public void getData(String macAddress) {
        deviceId = macAddress;
        SignJsonTo jsonTo = new SignJsonTo();
        jsonTo.setDeviceId("1909DCFD-243D-2F68-233A-250C9C9B571E");
        jsonTo.setTradeType("GetHome");
        jsonTo.setEqId(macAddress);

        jsonTo.setOpenId(mUserHelper.getSid());
        jsonTo.setUniqueStr(((TelephonyManager) getThisContext().getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId());
        SignBaseParam param = new SignBaseParam();
        param.setParamData(WLHSecurityUtils.toURLDecoded(WLHSecurityUtils.encrypt(new Gson().toJson(jsonTo))));
        System.out.println(new Gson().toJson(jsonTo) + "json");
        Map<String, String> params = new HashMap<>();
        params.put("ParamData", param.getParamData());
        SXHttpUtils.requestPostData(getActivity(), "http://prowatch.joyhomenet.com:8081/watch/index.php/backend/api.html", params, "UTF-8", new SXHttpUtils.LoadListener() {
            @Override
            public void onLoadSuccess(String result) {
                if (result == null)
                    return;

                SignMessageTo<SignMainPageTo> msg = new Gson().fromJson(new String(WLHSecurityUtils.decrypt(result.getBytes())), SignMessageTo.class);

                if (msg == null)
                    return;
                if (msg.getResultCode() == 0) {
                    SignMainPageTo signMainPageTo = new Gson().fromJson(new Gson().toJson(msg.getResultContent()), SignMainPageTo.class);
                    if (signMainPageTo == null)
                        return;

                    workerName.setText(DateUtil.longToString(NetTimeUtil.getSignNetTime(), DateUtil.mFormatDateString) + "  " + signMainPageTo.getBranchname() + "：" + signMainPageTo.getUsername() + "  " + parkName);

                    workerNameInfo = DateUtil.longToString(NetTimeUtil.getSignNetTime(), DateUtil.mFormatDateString) + "  " + signMainPageTo.getBranchname() + "：" + signMainPageTo.getUsername() + "  " + parkName;
                    if (signMainPageTo.getSignnum() > 0) {
                        signRecord.setText("今日一共签到" + signMainPageTo.getSignnum() + "次");
                        setLastSignLayout(signMainPageTo);
                    }
                }
            }

            @Override
            public void onLoadError() {

            }
        });
    }


    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
            //以下只列举部分获取地址相关的结果信息
            //更多结果信息获取说明，请参照类参考中BDLocation类中的说明

            String addr = location.getAddrStr();    //获取详细地址信息
            String country = location.getCountry();    //获取国家
            String province = location.getProvince();    //获取省份
            String city = location.getCity();    //获取城市
            String district = location.getDistrict();    //获取区县
            String street = location.getStreet();    //获取街道信息
            signAddress.setText(district + street);
        }
    }

    public static String getWokerNameInfo() {
        return workerNameInfo;
    }

}
