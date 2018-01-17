package com.joy.property.worksign;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.Util.BluetoothManager;
import com.Util.signbluetooth.ConvertData;
import com.Util.signbluetooth.HolloBluetooth;
import com.Util.signencode.SXHttpUtils;
import com.Util.signencode.aes.WLHSecurityUtils;
import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.worksign.SignAddressTo;
import com.jinyi.ihome.module.worksign.SignJsonTo;
import com.jinyi.ihome.module.worksign.SignMessageTo;
import com.jinyi.ihome.module.worksign.SignSubmitJsonTo;
import com.joy.common.api.ApiClient;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.VendorApi;
import com.joy.library.utils.DateUtil;
import com.joy.library.utils.FileUtil;
import com.joy.property.R;
import com.joy.property.base.BaseActivity;
import com.joy.property.base.EventBusEvent;
import com.joy.property.constant.Constant;
import com.joy.property.utils.ACache;
import com.joy.property.utils.NetTimeUtil;

import com.joy.property.worksign.fragment.SignFragment;
import com.joy.property.worksign.photo.zoom.SignGalleryActivity;
import com.joyhome.nacity.app.MainApp;
import com.joyhome.nacity.app.photo.util.Bimp;
import com.joyhome.nacity.app.photo.util.ImageItem;
import com.joyhome.nacity.app.util.CustomDialogFragment;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;
import com.ta.utdid2.android.utils.NetworkUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * Created by xzz on 2017/12/28.
 **/
public class SignSubmitActivity extends BaseActivity implements UpCompletionHandler, View.OnClickListener {

    private GridLayout gridView;

    private String mPhotoPath = "";
    private TextView submit;
    private StringBuilder stringBuilder = new StringBuilder();
    private int mCount = 0;
    private CustomDialogFragment dialogFragment;
    private TextView remarkContent;

    private Handler handler = new Handler();
    private byte[] mKey = ConvertData.hexStringToBytes("424541434F4E");//"BEACON"
    private HolloBluetooth mble;

    private boolean mScanning = true;
    private TextView signPosition;
    private String deviceId;
    private boolean stop;
    private SignSubmitJsonTo jsonTo;
    private List<String> imageList = new ArrayList<>();
    private List<ImageItem> rrcordImageItemList = new ArrayList<>();
    private long netTime;//网络时间

    private TextView signTime;
    private SignAddressTo deviceAddressTo;//当前扫描到的蓝牙设备位置信息
    private String devicePower="100%";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_detail);

        initView();
        setSignTime();
        setGridView();
        OpenBlueTooth();
        EventBus.getDefault().register(this);
        Bimp.tempSelectBitmap.clear();
        NetTimeUtil.initNetTime();
    }

    /**
     * 设置顶部显示的时间
     */
    private void setSignTime() {

        signTime.setText("签到时间  " + DateUtil.getDateString(new Date(), DateUtil.mFormatTimeShort));
        handler.postDelayed(() -> {
            signTime.setText("签到时间  " + DateUtil.longToString(NetTimeUtil.getSignNetTime(), DateUtil.mFormatTimeShort));
        }, 2000);
    }


    private void initView() {
        gridView = (GridLayout) findViewById(R.id.grid_view);
        submit = (TextView) findViewById(R.id.submit);
        remarkContent = (TextView) findViewById(R.id.remark_content);
        signPosition = (TextView) findViewById(R.id.sign_position);
        signTime = (TextView) findViewById(R.id.sign_time);
        TextView workContent = (TextView) findViewById(R.id.work_content);
        submit.setOnClickListener(this);
        workContent.setText("工作内容：" + (TextUtils.isEmpty(getIntent().getStringExtra("WorkContent")) ? "未选择" : getIntent().getStringExtra("WorkContent")));
        ((TextView) findViewById(R.id.worker_name)).setText(SignFragment.getWokerNameInfo());
        findViewById(R.id.back).setOnClickListener(v -> {
            finish();
            Bimp.tempSelectBitmap.clear();
            goToAnimation(2);
        });
    }

    /**
     * 打开蓝牙
     */
    private void OpenBlueTooth() {
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        BluetoothManager.turnOnBluetooth();

        new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                SystemClock.sleep(100);
                if (mBluetoothAdapter.isEnabled()) {
                    scanLeDevice();
                    break;
                }
            }
        }).start();

    }

    /**
     * 照片九宫格
     */
    private void setGridView() {
        gridView.removeAllViews();
        for (int i = 0; i < (Bimp.tempSelectBitmap.size() == 9 ? 9 : Bimp.tempSelectBitmap.size() + 1); i++) {
            ImageView imageView = new ImageView(getThisContext());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();

            if (Bimp.tempSelectBitmap.size() != 9 && i == Bimp.tempSelectBitmap.size()) {
                imageView.setBackgroundResource(R.drawable.work_sign_camara_icon);
                imageView.setTag("camera");
            } else {

                imageView.setImageBitmap(Bimp.tempSelectBitmap.get(i).getBitmap());
                imageView.setTag(i);
            }
            params.width = (int) (getScreenWidth() * (170.5 / 720));
            params.height = (int) (getScreenWidth() * (170.5 / 720));
            if (i % 3 == 0)
                params.leftMargin = 0;
            else
                params.leftMargin = (int) (getScreenWidth() * 50.0 / 720);
            params.bottomMargin = (int) (getScreenWidth() * 30.0 / 720);
            imageView.setLayoutParams(params);

            imageView.setOnClickListener(v -> {
                if (!"camera".equals(v.getTag())) {

                    Intent intent = new Intent(getThisContext(), SignGalleryActivity.class);
                    intent.putExtra("position", "3");
                    intent.putExtra("ID", (int) v.getTag());
                    intent.putExtra("number", 9);
                    intent.putExtra("flag", "repair");
                    startActivityForResult(intent, Constant.RESULT_SDCARD);
                } else {
                    openCamera();
                }
            });

            gridView.addView(imageView);
        }

    }

    // 打开照相机
    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void openCamera() {

        if (FileUtil.IsExistsSDCard()) {
            Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            File mPhotoFile = new File(MainApp.getCacheImagePath() + "/Camera/");
            if (!mPhotoFile.exists())
                mPhotoFile.mkdir();

            File mOutPhotoFile = new File(MainApp.getCacheImagePath() + "/Camera/",
                    DateUtil.getDateString("yyyyMMddHHmmss") + ".jpg");
            mPhotoPath = mOutPhotoFile.getAbsolutePath();

            Uri uri = Uri.fromFile(mOutPhotoFile);
            intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            startActivityForResult(intentCamera, Constant.RESULT_CAMERA);
        } else {
            Toast.makeText(getThisContext(), "sdcard无效或没有插入", Toast.LENGTH_LONG).show();
        }

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {

                case Constant.RESULT_CAMERA:
                    if (Bimp.tempSelectBitmap.size() < 9) {

                        ImageItem takePhoto = new ImageItem();
                        takePhoto.setImagePath(mPhotoPath);
                        takePhoto.setBitmap(Bimp.getBitmap(mPhotoPath));
                        Bimp.tempSelectBitmap.add(takePhoto);
                        imageList.add(mPhotoPath);
                        rrcordImageItemList.add(takePhoto);
                        setGridView();

                    }
                    mPhotoPath = "";
                    break;
                case Constant.RESULT_SDCARD:
                    for (ImageItem imageItem : Bimp.tempSelectBitmap) {
                        for (int i = 0; i < rrcordImageItemList.size(); i++) {

                        }
                    }
                    setGridView();
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 上传图片获取token
     */

    private void uploaderImage() {
        NetTimeUtil.initNetTime();
        submit.setEnabled(false);
        VendorApi api = ApiClient.create(VendorApi.class);
        dialogFragment = new CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "");
        api.getQnToken(new HttpCallback<MessageTo<String>>(getThisContext()) {
            @Override
            public void success(MessageTo<String> msg, Response response) {
                submit.setEnabled(true);
                if (msg.getSuccess() == 0) {
                    String token = msg.getData();
                    UploadManager uploadManager = new UploadManager();
                    if (Bimp.tempSelectBitmap.size() > 0) {
                        for (ImageItem imageItem : Bimp.tempSelectBitmap) {
                            String mStr = UUID.randomUUID().toString();
                            stringBuilder.append("http://7xk6y7.com2.z0.glb.qiniucdn.com/").append(mStr);
                            stringBuilder.append(",");
                            uploadManager.put(Bimp.getImageUri(imageItem.imagePath, false, false), mStr, token, SignSubmitActivity.this, null);
                        }
                    }
                }

            }

            @Override
            public void failure(RetrofitError error) {
                dialogFragment.dismissAllowingStateLoss();
                super.failure(error);

                submit.setEnabled(true);
            }
        });
    }

    @Override
    public void complete(String key, ResponseInfo info, JSONObject response) {
        mCount++;
        if (mCount == Bimp.tempSelectBitmap.size()) {

            submit(jsonTo);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submit:
                if (Bimp.tempSelectBitmap.size() == 0) {
                    ToastShowLong(getThisContext(), "还没有拍照");
                    return;
                }
                if (TextUtils.isEmpty(deviceId)) {
                    ToastShowLong(getThisContext(), "没有扫描到设备");
                    return;
                }
                jsonTo = new SignSubmitJsonTo();

                jsonTo.setTradeType("PostSign");
                jsonTo.setUniqueStr(getDeviceUid());
                jsonTo.setOpenId(mUserHelper.getSid());
                jsonTo.setImageList(imageList);
                jsonTo.setParkName(getIntent().getStringExtra("ParkName"));
                jsonTo.setSignNote(TextUtils.isEmpty(remarkContent.getText().toString())?"未填写":remarkContent.getText().toString());
                jsonTo.setJobStr(TextUtils.isEmpty(getIntent().getStringExtra("WorkContent")) ? "未选择" : getIntent().getStringExtra("WorkContent"));
                jsonTo.setEqMark(deviceId);
                jsonTo.setSignTime(DateUtil.getTime());
                if (NetworkUtils.isConnectInternet(getThisContext())) {
                    if (getIntent().getStringExtra("ParkName") != null && deviceAddressTo != null && getIntent().getStringExtra("ParkName").equals(deviceAddressTo.getParkname()))
                        uploaderImage();
                    else
                        ToastShowLong(getThisContext(), "请返回绑定小区进行签到");


                } else {
                    submit.setEnabled(false);
                    List<SignSubmitJsonTo> jsonList = JSON.parseArray(new ACache().getAsString(mUserHelper.getSid()+"SignSubmitJson"), SignSubmitJsonTo.class);
                    if (jsonList == null)
                        jsonList = new ArrayList<>();
                    jsonList.add(jsonTo);

                    new ACache().put(mUserHelper.getSid()+"SignSubmitJson", JSON.toJSONString(jsonList));
                    ToastShowLong(getThisContext(), "签到已存本地，请在3个小时内在签到首页上传");
                    handler.postDelayed(() -> {
                        Bimp.tempSelectBitmap.clear();
                        submit.setEnabled(true);
                        finish();
                        goToAnimation(2);

                    }, 2000);
                }
                break;
        }
    }

    /**
     * 提交签到数据
     *
     * @param jsonTo
     */
    private void submit(SignSubmitJsonTo jsonTo) {

        jsonTo.setSignTime(DateUtil.getLongDateTime(NetTimeUtil.getSignNetTime()));
        jsonTo.setImgToken(stringBuilder.toString().substring(0, stringBuilder.toString().length() - 1));
        jsonTo.setEqEy(devicePower);

        SXHttpUtils.requestSubmitPostData(SignSubmitActivity.this, jsonTo, new SXHttpUtils.LoadListener() {
            @Override
            public void onLoadSuccess(String result) {
                SignMessageTo msg = new Gson().fromJson(new String(WLHSecurityUtils.decrypt(result.getBytes())), SignMessageTo.class);
                dialogFragment.dismiss();
                if (msg.getResultCode() == 0) {
                    com.joy.property.utils.CustomDialog alertDialog = new com.joy.property.utils.CustomDialog(getThisContext(), R.layout.dialog_sign_success, R.style.myDialogTheme);
                    alertDialog.show();
                    Bimp.tempSelectBitmap.clear();
                    handler.postDelayed(() -> {
                        alertDialog.dismiss();
                        finish();
                        goToAnimation(2);
                    }, 3000);
                }
            }

            @Override
            public void onLoadError() {
                dialogFragment.dismiss();
            }
        });


    }

    private Thread scanThread;

    /**
     * s扫描蓝牙设备
     */

    private void scanLeDevice() {

        mble = HolloBluetooth.getInstance(getThisContext());
        //判断本设备是否支持蓝牙ble，并连接本地蓝牙设备
        if (!mble.isBleSupported() || !mble.connectLocalDevice()) {
            return;
        }


        //设置蓝牙扫描的回调函数
        mble.setScanCallBack(mLeScanCallback);


        mScanning = true;
        scanThread = new Thread(() -> {
            for (int i = 0; i < 1000 && !stop; i++) {
                mble.startLeScan();
                SystemClock.sleep(5000);
                mble.stopLeScan();
                SystemClock.sleep(10000);

            }
        });
        scanThread.start();

    }

    // 扫描的结果
    private BluetoothAdapter.LeScanCallback mLeScanCallback =
            (device, rssi, scanRecord) -> runOnUiThread(() -> {
//                	String deviceName = "H01";//device.getName();
                String deviceName = device.getName();

                if (deviceName == null || deviceName.length() <= 0)

                    if (scanRecord.length < 30) {
                        return;
                    }

                if (scanRecord[0] != 0x02 || scanRecord[1] != 0x01 ||
                        scanRecord[2] != 0x06 || scanRecord[3] != 0x1A || scanRecord[4] != (byte) (0xff) ||
                        scanRecord[5] != 0x58 || scanRecord[6] != 0x69 || scanRecord[7] != 0x59 || scanRecord[8] != 0x16 ||
                        scanRecord[21] != 0x58 || scanRecord[22] != 0x47 || scanRecord[23] != 0x53 || scanRecord[24] != 0x55) {
                    return;
                }


                byte[] tmp = new byte[6];

                byte[] mac = new byte[6];

                for (int i = 9; i < 15; i++) {
                    tmp[i - 9] = (byte) ((~(scanRecord[i] ^ mKey[i - 9]) + 0x55) & 0xff);
                    mac[i - 9] = scanRecord[i];
                }

                byte[] sign = new byte[6];
                System.arraycopy(scanRecord, 15, sign, 0, 6);
                if (!ConvertData.cmpBytes(sign, tmp)) {
                    return;
                }

                //SX说明：电量
                int power = (scanRecord[29] & 0xff);

                if (mScanning) {
                           getDeviceAddress(ConvertData.bytesToHexString(mac, false));
                    devicePower=power+"%";


                }
            });

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(scanThread);
        stop = true;
        EventBus.getDefault().unregister(this);
        Bimp.tempSelectBitmap.clear();
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    /**
     * 用来获取设备位置
     */
    private void getDeviceAddress(String macAddress) {
        runOnUiThread(() ->   signPosition.setText("已找到巡更设备"));

        deviceId = macAddress;
        SignJsonTo jsonTo = new SignJsonTo();
        jsonTo.setDeviceId("1909DCFD-243D-2F68-233A-250C9C9B571E");
        jsonTo.setTradeType("GetSignas");
        jsonTo.setEqId(macAddress);
        jsonTo.setOpenId(mUserHelper.getSid());
        jsonTo.setUniqueStr(getDeviceUid());

        SXHttpUtils.requestPostData(SignSubmitActivity.this, jsonTo, new SXHttpUtils.LoadListener() {
            @Override
            public void onLoadSuccess(String result) {
                if (result == null)
                    return;
                SignMessageTo<SignAddressTo> msg = new Gson().fromJson(new String(WLHSecurityUtils.decrypt(result.getBytes())), SignMessageTo.class);

                if (msg == null)
                    return;
                if (msg.getResultCode() == 0) {

                    deviceAddressTo = new Gson().fromJson(new Gson().toJson(msg.getResultContent()), SignAddressTo.class);
                    signPosition.setText(TextUtils.isEmpty(deviceAddressTo.getEqaddress()) ? "未录入" : deviceAddressTo.getEqaddress());
                }
            }

            @Override
            public void onLoadError() {

            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK)
            Bimp.tempSelectBitmap.clear();

        return super.onKeyDown(keyCode, event);
    }

    @Subscribe
    public void deleteCacheImagePath(EventBusEvent<Integer> event) {
        if ("DeleteAllPicture".equals(event.getType())) {
         imageList.clear();
        }
        if ("DeletePosition".equals(event.getType())) {
            int position=event.getMode();
            imageList.remove(position);
        }

    }
}
