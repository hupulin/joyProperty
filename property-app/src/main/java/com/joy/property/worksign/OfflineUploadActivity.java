package com.joy.property.worksign;

import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.ListView;

import com.Util.signencode.SXHttpUtils;
import com.Util.signencode.aes.WLHSecurityUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.worksign.MacAddressTo;
import com.jinyi.ihome.module.worksign.SignContentTo;
import com.jinyi.ihome.module.worksign.SignJsonTo;
import com.jinyi.ihome.module.worksign.SignMainPageTo;
import com.jinyi.ihome.module.worksign.SignMessageTo;
import com.joy.common.api.ApiClient;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.VendorApi;
import com.joy.library.utils.DateUtil;
import com.joy.property.R;
import com.joy.property.base.BaseActivity;
import com.joy.property.utils.ACache;
import com.joy.property.worksign.adapter.OfflineUploadAdapter;
import com.joy.property.worksign.adapter.SignBaseParam;
import com.joy.property.worksign.adapter.SignSubmitJsonTo;
import com.joyhome.nacity.app.photo.util.Bimp;
import com.joyhome.nacity.app.photo.util.ImageItem;
import com.joyhome.nacity.app.util.CustomDialogFragment;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import retrofit.RetrofitError;
import retrofit.client.Response;
import rx.Observable;

/**
 * Created by xzz on 2017/12/29.
 **/


public class OfflineUploadActivity extends BaseActivity implements UpCompletionHandler, View.OnClickListener {

    private ListView listView;
    private OfflineUploadAdapter adapter;
    private String uploadImagePath = "";
    private int mCount = 0;
    private SignSubmitJsonTo cacheJsonTo;
    private List<SignSubmitJsonTo> cacheSubmitList;

    private boolean isAllUpload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_upload);
        initView();

        getData();

    }

    /*
    获取缓存点位位置
     */
    private void getDeviceAddress() {

        String macAddressList = "";
        for (SignSubmitJsonTo submitJsonTo : cacheSubmitList)
            macAddressList = macAddressList + submitJsonTo.getEqMark() + ",";
        macAddressList = macAddressList.substring(0, macAddressList.length() - 1);

        SignJsonTo jsonTo = new SignJsonTo();

        jsonTo.setDeviceId("1909DCFD-243D-2F68-233A-250C9C9B571E");
        jsonTo.setTradeType("GetMacAes");
        jsonTo.setEqList(macAddressList);
        jsonTo.setOpenId(mUserHelper.getSid());
        jsonTo.setUniqueStr(((TelephonyManager) getThisContext().getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId());
        SignBaseParam param = new SignBaseParam();
        param.setParamData(WLHSecurityUtils.toURLDecoded(WLHSecurityUtils.encrypt(new Gson().toJson(jsonTo))));
        System.out.println(new Gson().toJson(jsonTo) + "json");
        Map<String, String> params = new HashMap<>();
        params.put("ParamData", param.getParamData());
        SXHttpUtils.requestPostData(OfflineUploadActivity.this, "http://nd.alipayer.cn/index.php/backend/api.html", params, "UTF-8", new SXHttpUtils.LoadListener() {
            @Override
            public void onLoadSuccess(String result) {
                if (result == null)
                    return;

                SignMessageTo<List<MacAddressTo>> msg = new Gson().fromJson(new String(WLHSecurityUtils.decrypt(result.getBytes())), SignMessageTo.class);
                System.out.println(msg + "address========");
                if (msg == null)
                    return;
                if (msg.getResultCode() == 0) {
                    List<MacAddressTo> macList = new Gson().fromJson(new Gson().toJson(msg.getResultContent()), new TypeToken<List<MacAddressTo>>() {
                    }.getType());
                    for (int i = 0; i < cacheSubmitList.size(); i++)
                        for (MacAddressTo macAddressTo : macList) {
                            if (cacheSubmitList.get(i).getEqMark().equals(macAddressTo.getMac())) {
                                cacheSubmitList.get(i).setAddress(macAddressTo.getAddress());
                                break;
                            }

                        }
                    adapter.setList(cacheSubmitList);
                    listView.setAdapter(adapter);
                    adapter.setUploadClick(position -> {
                        cacheJsonTo = cacheSubmitList.get(position);
                        sendSubmitData(cacheJsonTo);
                        isAllUpload = false;
                    });
                }
            }

            @Override
            public void onLoadError() {

            }
        });
    }

    private void initView() {
        listView = (ListView) findViewById(R.id.listView);
        adapter = new OfflineUploadAdapter(getThisContext());
        adapter.setList(cacheSubmitList);
        listView.setAdapter(adapter);
        listView.setDividerHeight(0);
        findViewById(R.id.all_upload).setOnClickListener(this);

        findViewById(R.id.back).setOnClickListener(v -> {
            finish();
            goToAnimation(2);
        });

    }

    /**
     * 获取缓存数据
     */
    private void getData() {
        cacheSubmitList = JSON.parseArray(new ACache().getAsString("SignSubmitJson"), SignSubmitJsonTo.class);


        if (cacheSubmitList != null && cacheSubmitList.size() > 0) {
            getDeviceAddress();


        }
    }

    /**
     * 上传照片路径处理
     */
    public void sendSubmitData(SignSubmitJsonTo mCacheJsonTo) {

        if (cacheSubmitList != null && cacheSubmitList.size() > 0) {

            if (mCacheJsonTo == null) {
                cacheJsonTo = cacheSubmitList.get(0);
                cacheSubmitList.get(0).setAllUpload(true);
            } else
                cacheJsonTo = mCacheJsonTo;


            for (String imagePath : cacheJsonTo.getImageList()) {
                ImageItem takePhoto = new ImageItem();
                takePhoto.setImagePath(imagePath);
                takePhoto.setBitmap(Bimp.getBitmap(imagePath));
                Bimp.tempSelectBitmap.add(takePhoto);
            }
            uploaderImage();
        }

    }

    //离线缓存的签到数据提交

    private void submit(SignSubmitJsonTo jsonTo) {

        jsonTo.setImgToken(uploadImagePath.substring(0, uploadImagePath.length() - 1));
        System.out.println(new Gson().toJson(jsonTo) + "jsonTo");
        SignBaseParam param = new SignBaseParam();
        param.setParamData(WLHSecurityUtils.toURLDecoded(WLHSecurityUtils.encrypt(new Gson().toJson(jsonTo))));
        Map<String, String> params = new HashMap<>();
        params.put("ParamData", param.getParamData());
        SXHttpUtils.requestPostData(OfflineUploadActivity.this, "http://nd.alipayer.cn/index.php/backend/api.html", params, "UTF-8", new SXHttpUtils.LoadListener() {
            @Override
            public void onLoadSuccess(String result) {
                SignMessageTo msg = new Gson().fromJson(new String(WLHSecurityUtils.decrypt(result.getBytes())), SignMessageTo.class);

                System.out.println(msg + "msg=========");
                if (msg.getResultCode() == 0) {
                    for (int i = 0; i < cacheSubmitList.size(); i++) {
                        if (cacheSubmitList.get(i).getSignTime().equals(jsonTo.getSignTime())) {
                            cacheSubmitList.remove(i);
                            new ACache().put("SignSubmitJson", JSON.toJSONString(cacheSubmitList));
                            adapter.notifyDataSetChanged();
                            if (isAllUpload) {
                                sendSubmitData(null);
                                if (cacheSubmitList.size() == 0)
                                    ToastShowLong(getThisContext(), "全部签到上传成功");
                            } else
                                ToastShowLong(getThisContext(), "签到上传成功");

                            break;
                        }
                    }


                } else {
                    ToastShowLong(getThisContext(), msg.getReason());
                    if (msg.getReason() != null && msg.getReason().contains("设备不存在")) {
                        for (int i = 0; i < cacheSubmitList.size(); i++) {
                            if (cacheSubmitList.get(i).getSignTime().equals(jsonTo.getSignTime())) {
                                cacheSubmitList.remove(i);
                                new ACache().put("SignSubmitJson", JSON.toJSONString(cacheSubmitList));
                                adapter.notifyDataSetChanged();
                                if (isAllUpload)
                                    sendSubmitData(null);
                            }
                        }
                    }
                }

            }

            @Override
            public void onLoadError() {
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void uploaderImage() {

        VendorApi api = ApiClient.create(VendorApi.class);
        uploadImagePath = "";
        mCount = 0;
        api.getQnToken(new HttpCallback<MessageTo<String>>(getThisContext()) {
            @Override
            public void success(MessageTo<String> msg, Response response) {

                if (msg.getSuccess() == 0) {
                    String token = msg.getData();
                    UploadManager uploadManager = new UploadManager();
                    if (Bimp.tempSelectBitmap.size() > 0) {
                        for (ImageItem imageItem : Bimp.tempSelectBitmap) {
                            String mStr = UUID.randomUUID().toString();
                            uploadImagePath = uploadImagePath + mStr + ",";
                            uploadManager.put(Bimp.getImageUri(imageItem.imagePath, false, false), mStr, token, OfflineUploadActivity.this, null);
                        }
                    }

                }
            }

            @Override
            public void failure(RetrofitError error) {
                adapter.notifyDataSetChanged();
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void complete(String key, ResponseInfo info, JSONObject response) {
        mCount++;
        if (mCount == Bimp.tempSelectBitmap.size()) {

            submit(cacheJsonTo);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Bimp.tempSelectBitmap.clear();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.all_upload:
                isAllUpload = true;
                sendSubmitData(null);
                break;
        }
    }
}
