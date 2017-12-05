package com.joy.property.vehicle;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.apartment.ApartmentInfoTo;
import com.jinyi.ihome.module.car.CarTo;
import com.jinyi.ihome.module.car.CarViolationParam;
import com.joy.common.api.ApartmentApi;
import com.joy.common.api.ApiClient;
import com.joy.common.api.CarApi;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.VendorApi;
import com.joy.common.widget.UploadImage;
import com.joy.library.fragment.CustomDialogFragment;
import com.joy.library.utils.CustomDialog;
import com.joy.library.utils.DateUtil;
import com.joy.library.utils.FileUtil;
import com.joy.property.MainApplication;
import com.joy.property.R;
import com.joy.property.base.BaseActivity;
import com.joy.property.common.PictureShowActivity;
import com.joy.property.common.ShowImageActivity;
import com.joy.property.constant.Constant;
import com.joy.property.inspection.adapter.ApartmentAdapter;
import com.joy.property.vehicle.adapter.ContactAdapter;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Admin on 2015-03-24.
 * 车辆管理 信息明细
 */
public class VehicleInformationActivity extends BaseActivity
        implements OnClickListener, UpCompletionHandler {


    private TextView mFailStopNumber;
    private TextView mCarOwnerName;
    private TextView mOwnerTelephone;
    private TextView mPlaceNo;
    private TextView mPlaceStatus;
    private TextView mPlaceEndTime;
    private ImageView mCarImages;
    private CarTo mCar;
    private String mPhotoPath = "";
    private ImageView mAddImage;
    private StringBuilder stringBuilder = new StringBuilder();
    private ApartmentInfoTo apartmentInfoTo;
    private EditText mPlace;
    private EditText mDescContent;
    private LinearLayout mImageLayout;
    private RelativeLayout mPlaceInformation;
    private List<String> pathList = new ArrayList<>();
    private int count = 0;
    private TextView mCarPlace;


    private TextView mRoomNo;
    private TextView mVisitingTime;
    private TextView mDepartureTime;
    //访客记录

    private RelativeLayout mVisitorsRecord;
    //访客信息
    private RelativeLayout sVisitorsRecord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_information);
        findById();
        getIntentData();
        initializeData();
    }


    private void findById() {
        mBack = (ImageView) findViewById(R.id.back);
        mBack.setOnClickListener(this);
        mFailStopNumber = (TextView) findViewById(R.id.number);
        ImageButton mRecordStop = (ImageButton) findViewById(R.id.record);
        mRecordStop.setOnClickListener(this);
        mCarOwnerName = (TextView) findViewById(R.id.owner_name);
        mOwnerTelephone = (TextView) findViewById(R.id.owner_phone);
        ImageButton mContactOwner = (ImageButton) findViewById(R.id.telephone);
        mContactOwner.setOnClickListener(this);
        Button mEditInformation = (Button) findViewById(R.id.edit_information);
        mEditInformation.setOnClickListener(this);
        mPlaceNo = (TextView) findViewById(R.id.placeNo);
        mPlaceStatus = (TextView) findViewById(R.id.placeStatus);
        mPlaceEndTime = (TextView) findViewById(R.id.endTime);
        mCarImages = (ImageView) findViewById(R.id.car_icon);
        RelativeLayout mHistoryRecord = (RelativeLayout) findViewById(R.id.relativeLayout);
        mHistoryRecord.setOnClickListener(this);
        mPlaceInformation = (RelativeLayout) findViewById(R.id.place_info);
        mCarPlace = (TextView) findViewById(R.id.place);
        mRoomNo = (TextView) findViewById(R.id.roomno);
        mVisitingTime = (TextView) findViewById(R.id.visitingtime);
        mDepartureTime = (TextView) findViewById(R.id.departuretime);
        mVisitorsRecord = (RelativeLayout) findViewById(R.id.relativeLayout3);
        mVisitorsRecord.setOnClickListener(this);
        sVisitorsRecord = (RelativeLayout) findViewById(R.id.relativeLayout20);
    }

    private void getIntentData() {
        mCar = (CarTo) getIntent().getSerializableExtra("carInfo");
        Log.i("3333", "getIntentData: "+mCar.toString());
    }

    //初始化数据
    private void initializeData() {
        if (1 == mCar.getIsYeZhu()) {
            /**显示外层信息**/
            ((TextView) findViewById(R.id.smtitle)).setText("车主信息");

            mVisitorsRecord.setVisibility(View.GONE);
            sVisitorsRecord.setVisibility(View.GONE);
            //手  机  号

            if (!TextUtils.isEmpty(mCar.getCarPhone())) {
                mOwnerTelephone.setText(mCar.getCarPhone());
            } else {
                mOwnerTelephone.setText("暂无");
                mOwnerTelephone.setTextColor(0xffbebebe);
            }
            //业主姓名
            if (!TextUtils.isEmpty(mCar.getCarOwner())) {
                mCarOwnerName.setText(mCar.getCarOwner());
            } else {
                mCarOwnerName.setText("暂无");
                mCarOwnerName.setTextColor(0xffbebebe);
            }
            //车位
            if (!TextUtils.isEmpty(mCar.getCarPlace())) {
                mCarPlace.setText(mCar.getCarPlace());
            } else {
                mCarPlace.setText("暂无");
                mCarPlace.setTextColor(0xffbebebe);

            }

        } else {

                if (mCar.getCarRecords() == null ) {
                    ((TextView) findViewById(R.id.smtitle)).setText("车主信息");
                    mVisitorsRecord.setVisibility(View.GONE);
                    sVisitorsRecord.setVisibility(View.GONE);
                    //手机号
                    if (!TextUtils.isEmpty(mCar.getCarPhone())) {
                        mOwnerTelephone.setText(mCar.getCarPhone());
                    } else {
                        mOwnerTelephone.setText("暂无");
                        mOwnerTelephone.setTextColor(0xffbebebe);
                    }
                    //业主姓名
                    if (!TextUtils.isEmpty(mCar.getCarOwner())) {
                        mCarOwnerName.setText(mCar.getCarOwner());
                    } else {
                        mCarOwnerName.setText("暂无");
                        mCarOwnerName.setTextColor(0xffbebebe);
                    }
                    //车位
                    if (!TextUtils.isEmpty(mCar.getCarPlace())) {
                        mCarPlace.setText(mCar.getCarPlace());
                    } else {
                        mCarPlace.setText("暂无");
                        mCarPlace.setTextColor(0xffbebebe);

                    }
                } else {
                    /**显示第一层信息**/
                    ((TextView) findViewById(R.id.smtitle)).setText("被访业主信息");
                    mVisitorsRecord.setVisibility(View.VISIBLE);
                    sVisitorsRecord.setVisibility(View.VISIBLE);
                    //手机号
                    if (!TextUtils.isEmpty(mCar.getCarRecords().get(0).getOwnerPhone())) {
                        mOwnerTelephone.setText(mCar.getCarRecords().get(0).getOwnerPhone());
                    } else {
                        mOwnerTelephone.setText("暂无");
                        mOwnerTelephone.setTextColor(0xffbebebe);
                    }
                    //业主姓名
                    if (!TextUtils.isEmpty(mCar.getCarRecords().get(0).getOwnerName())) {
                        mCarOwnerName.setText(mCar.getCarRecords().get(0).getOwnerName());
                    } else {
                        mCarOwnerName.setText("暂无");
                        mCarOwnerName.setTextColor(0xffbebebe);
                    }
                    //车位
                    if (!TextUtils.isEmpty(mCar.getCarRecords().get(0).getOwnerCarPlace())) {
                        mCarPlace.setText(mCar.getCarRecords().get(0).getOwnerCarPlace());
                    } else {
                        mCarPlace.setText("暂无");
                        mCarPlace.setTextColor(0xffbebebe);

                    }
                    //新增加三行数据
                    if (!TextUtils.isEmpty(mCar.getCarRecords().get(0).getOwnerNo())) {
                        mRoomNo.setText(mCar.getCarRecords().get(0).getOwnerNo());
                    }else {
                        mRoomNo.setText("暂无");
                        mRoomNo.setTextColor(0xffbebebe);
                    }
                    if (!TextUtils.isEmpty(mCar.getCarRecords().get(0).getVisitTime())) {
                        mVisitingTime.setText(mCar.getCarRecords().get(0).getVisitTime());
                    }else {
                        mVisitingTime.setText("暂无");
                        mVisitingTime.setTextColor(0xffbebebe);
                    }
                    if (!TextUtils.isEmpty(mCar.getCarRecords().get(0).getLeaveTime())) {
                        mDepartureTime.setText(mCar.getCarRecords().get(0).getLeaveTime());
                        System.out.println("xixixixixiixixi走啦" + mCar.getCarRecords().get(0).getVisitTime());
                    }else{
                        mDepartureTime.setText("暂无");
                        mDepartureTime.setTextColor(0xffbebebe);
                    }
                }
            }
//            if (mCar.getCarRecords() != null && mCar.getCarRecords().size() > 0 && mCar.getCarNo().equals(mCar.getCarRecords().get(0).getVisitorCarNo())
//                    && mCar.getCarOwner() != null && mCar.getCarPhone() != null && mCar.getCarPlace() != null) {
////             mVisitorsRecord.setVisibility(View.GONE);
////            sVisitorsRecord.setVisibility(View.GONE);
//                ((TextView) findViewById(R.id.smtitle)).setText("车主信息");
//                /**
//                 *手机号
//                 **/
//                if (!TextUtils.isEmpty(mCar.getCarPhone())) {
//                    mOwnerTelephone.setText(mCar.getCarPhone());
//                } else {
//                    mOwnerTelephone.setText("暂无");
//                    mOwnerTelephone.setTextColor(0xffbebebe);
//                }
//            } else {
//                /**
//                 * ------------------新添加的三行记录------------
//                 * 房号
//                 * 来访时间
//                 * 离开时间
//                 **/
//                if (mCar.getCarRecords() != null && mCar.getCarRecords().size() > 0) {
//                    if (!TextUtils.isEmpty(mCar.getCarRecords().get(0).getOwnerNo())) {
//                        mRoomNo.setText(mCar.getCarRecords().get(0).getOwnerNo());
//                        System.out.println("xixixixixiixixi你回来啦" + mCar.getCarRecords().get(0).getOwnerNo());
//                    }
//
//                } else {
//                    mRoomNo.setText("暂无");
//                    mRoomNo.setTextColor(0xffbebebe);
//                }
//
//                if (mCar.getCarRecords() != null && mCar.getCarRecords().size() > 0) {
//                    if (!TextUtils.isEmpty(mCar.getCarRecords().get(0).getVisitTime())) {
//                        mVisitingTime.setText(mCar.getCarRecords().get(0).getVisitTime());
//                        System.out.println("xixixixixiixixi来啦" + mCar.getCarRecords().get(0).getVisitTime());
//                    }
//
//                } else {
//                    mVisitingTime.setText("暂无");
//                    mVisitingTime.setTextColor(0xffbebebe);
//                }
//
//                if (mCar.getCarRecords() != null && mCar.getCarRecords().size() > 0) {
//                    if (!TextUtils.isEmpty(mCar.getCarRecords().get(0).getLeaveTime())) {
//                        mDepartureTime.setText(mCar.getCarRecords().get(0).getLeaveTime());
//                        System.out.println("xixixixixiixixi走啦" + mCar.getCarRecords().get(0).getVisitTime());
//                    }
//
//                } else {
//                    mDepartureTime.setText("暂无");
//                    mDepartureTime.setTextColor(0xffbebebe);
//                }
//
//
//                if (!(mCar.getCarRecords() != null && mCar.getCarRecords().size() > 0)) {
//                    mVisitorsRecord.setVisibility(View.GONE);
//                    sVisitorsRecord.setVisibility(View.GONE);
//                    ((TextView) findViewById(R.id.smtitle)).setText("车主信息");
//                }
//                if (mCar.getCarPlaces() != null && mCar.getCarPlaces().size() > 0) {
//                    if (!TextUtils.isEmpty(mCar.getCarPlaces().get(0).getPlaceNo())) {
//                        mPlaceNo.setText(mCar.getCarPlaces().get(0).getPlaceNo());
//                    }
//                    if (mCar.getCarPlaces().get(0).getPlaceStatus() != null) {
//                        switch (mCar.getCarPlaces().get(0).getPlaceStatus()) {
//                            case 0:
//                                mPlaceStatus.setText("正常");
//                                break;
//                            case 1:
//                                mPlaceStatus.setText("已租");
//                                if (mCar.getCarPlaces().get(0).getStartDate() != null
//                                        && mCar.getCarPlaces().get(0).getEndDate() != null) {
//                                    mPlaceEndTime.setText(mCar.getCarPlaces().get(0).getStartDate()
//                                            + "~" + mCar.getCarPlaces().get(0).getEndDate());
//                                }
//                                break;
//                            case 2:
//                                mPlaceStatus.setText("已售");
//                            default:
//                                break;
//                        }
//                    }
//
//
//                } else {
//                    mPlaceInformation.setVisibility(View.GONE);
//                }
//
//            }
//            //业主姓名
//            if (!TextUtils.isEmpty(mCar.getCarOwner())) {
//                mCarOwnerName.setText(mCar.getCarOwner());
//            } else {
//                mCarOwnerName.setText("暂无");
//                mCarOwnerName.setTextColor(0xffbebebe);
//
//                if (mCar.getCarRecords() != null && mCar.getCarRecords().size() > 0) {
//                    if (!TextUtils.isEmpty(mCar.getCarRecords().get(0).getOwnerPhone())) {
//                        mOwnerTelephone.setText(mCar.getCarRecords().get(0).getOwnerPhone());
//                    } else {
//                        mOwnerTelephone.setText("暂无");
//                        mOwnerTelephone.setTextColor(0xffbebebe);
//                    }
//                } else {
//                    if (!TextUtils.isEmpty(mCar.getCarPhone())) {
//                        String[] phone = mCar.getCarPhone().split("/");
//                        if (phone.length > 0) {
//                            mOwnerTelephone.setText(phone[0]);
//                        }
//
//                    } else {
//                        mOwnerTelephone.setText("暂无");
//                        mOwnerTelephone.setTextColor(0xffbebebe);
//                    }
//                }
//
//            }

            mFailStopNumber.setText(mCar.getViolationQty() + "次");
/**
 * 车位
 **/
//
//            if (!TextUtils.isEmpty(mCar.getCarPlace())) {
//                mCarPlace.setText(mCar.getCarPlace());
//            } else {
//                mCarPlace.setText("暂无");
//                mCarPlace.setTextColor(0xffbebebe);
//
//            }

            displayImage(mCarImages, mCar.getCarImages(), R.drawable.car_01);
            if (!TextUtils.isEmpty(mCar.getCarImages())) {
                mCarImages.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getThisContext(), PictureShowActivity.class);
                        intent.putExtra("path", mCar.getCarImages());
                        startActivity(intent);
                    }
                });
            }
        }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.back:
                onBackPressed();
                break;
            case R.id.record:
                //记录违停
                recordDialogShow();
                break;
            case R.id.telephone:
                //联系车主
                if (mOwnerTelephone.getText().toString()!=null&&!"暂无".equals((mOwnerTelephone.getText().toString()))) {
                    contactCarOwnerDialogShow();
                } else {
                    Toast.makeText(this, "当前车牌未登记手机号，请咨询物业服务中心。",
                            Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.edit_information:
                intent = new Intent(this, EditorVehicleInformationActivity.class);
                intent.putExtra("carNo", mCar.getCarNo());
                startActivity(intent);
                break;
            case R.id.relativeLayout:
                intent = new Intent(this, CarViolationHistoryActivity.class);
                intent.putExtra("carNo", mCar.getCarNo());
                startActivity(intent);
                break;
            case R.id.relativeLayout3:
                intent = new Intent(this, CarVisitorsRecordActivity.class);

                intent.putExtra("carRecords", (ArrayList)mCar.getCarRecords());
                System.out.println("草泥马："+mCar.getCarRecords().get(0).getLeaveTime());
                startActivity(intent);
                break;
            default:
                break;

        }
    }

    // 通讯目录
    private void contactContentDialogShow() {
        final CustomDialog dialog = new CustomDialog(this, R.layout.dialog_contact_content,
                R.style.myDialogTheme);
        ImageView mCancel = (ImageView) dialog.findViewById(R.id.cancel);
        ListView mList = (ListView) dialog.findViewById(R.id.list);
        mCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        final List<Map<String, String>> dataList = new ArrayList<>();
        dataList.addAll(mCar.getContacts());


        ContactAdapter contactAdapter = new ContactAdapter(this);
        contactAdapter.setList(dataList);
        mList.setAdapter(contactAdapter);
        mList.setItemsCanFocus(true);
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener()

                                     {
                                         @Override
                                         public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                             Map<String, String> map = dataList.get(position);
                                             Intent intent = new Intent(Intent.ACTION_DIAL,
                                                     Uri.parse("tel:" + map.get("phone")));
                                             startActivity(intent);
                                             dialog.dismiss();
                                         }
                                     }

        );


        dialog.setCancelable(true);
        dialog.show();
    }

    //联系车主对话框
    private void contactCarOwnerDialogShow() {
        final CustomDialog dialog = new CustomDialog(this, R.layout.dialog_detele_msg,
                R.style.myDialogTheme);
        Button mCancel = (Button) dialog.findViewById(R.id.btn_cancel);
        Button mAdd = (Button) dialog.findViewById(R.id.btn_add);
        TextView tip = (TextView) dialog.findViewById(R.id.tip);
        tip.setText("确定要拨打此车主电话？");
        mCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        mAdd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_DIAL,
                        Uri.parse("tel:" + mOwnerTelephone.getText().toString())));

                dialog.dismiss();
            }
        });

        dialog.setCancelable(true);
        dialog.show();
    }

    //记录违停对话框
    private void recordDialogShow() {
        final CustomDialog dialog = new CustomDialog(this, R.layout.dialog_record_violate, R.style.myDialogTheme);
        ImageView mDialogCancel = (ImageView) dialog.findViewById(R.id.image_cancel);
        final TextView mApartmentName = (TextView) dialog.findViewById(R.id.apartment);
        mPlace = (EditText) dialog.findViewById(R.id.place);
        mDescContent = (EditText) dialog.findViewById(R.id.desc);
        Button mSubmit = (Button) dialog.findViewById(R.id.submit);
        mAddImage = (ImageView) dialog.findViewById(R.id.add);
        mImageLayout = (LinearLayout) dialog.findViewById(R.id.layout_image);
        if (apartmentInfoTo != null) {
            mApartmentName.setText(apartmentInfoTo.getApartmentName());
        } else {
            ApartmentApi api = ApiClient.create(ApartmentApi.class);
            api.findByUserSid(mUserHelper.getSid(), new HttpCallback<MessageTo<List<ApartmentInfoTo>>>(this) {
                        @Override
                        public void success(final MessageTo<List<ApartmentInfoTo>> msg, Response response) {
                            if (msg.getSuccess() == 0) {
                                if (msg.getData() != null && msg.getData().size() > 0) {
                                    apartmentInfoTo = msg.getData().get(0);
                                    mApartmentName.setText(msg.getData().get(0).getApartmentName());
                                }

                            } else {
                                Toast.makeText(getThisContext(),
                                        msg.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            super.failure(error);
                        }
                    }
            );
        }


        mApartmentName.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                selectApartmentDialog(mApartmentName);
            }
        });
        mDialogCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                        .hideSoftInputFromWindow(mDescContent.getWindowToken(),
                                InputMethodManager.HIDE_NOT_ALWAYS);
                dialog.dismiss();
            }
        });
        mPlace.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 50) {
                    Toast.makeText(VehicleInformationActivity.this,
                            "地点描述最多只能输入50字哦", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        mDescContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 200) {
                    Toast.makeText(VehicleInformationActivity.this,
                            "违停描述最多只能输入200字哦", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        mAddImage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImageDialog();
            }
        });
        mSubmit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pathList != null && pathList.size() > 0) {
                    uploadImage();
                } else {
                    submitVehicleViolateInformation();
                }
                dialog.dismiss();
            }

        });
        dialog.setCancelable(true);
        dialog.show();
    }

    //图片上传到服务器
    private void uploadImage() {
        VendorApi api = ApiClient.create(VendorApi.class);
        final CustomDialogFragment dialogFragment = new CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "");
        api.getQnToken(new HttpCallback<MessageTo<String>>(this) {
            @Override
            public void success(MessageTo<String> msg, Response response) {
                if (msg.getSuccess() == 0) {
                    String token = msg.getData();
                    UploadManager uploadManager = new UploadManager();
                    for (String s : pathList) {
                        uploadManager.put(s, UUID.randomUUID().toString(), token, VehicleInformationActivity.this, null);
                    }

                }
            }

            @Override
            public void failure(RetrofitError error) {
                dialogFragment.dismissAllowingStateLoss();
                super.failure(error);
            }
        });


    }

    //上传图片对话框
    private void uploadImageDialog() {
        final CustomDialog dialog = new CustomDialog(this, R.layout.dialog_upload_image, R.style.myDialogTheme);
        Button btnCancel = (Button) dialog.findViewById(R.id.btn_cancel);
        Button btnCamera = (Button) dialog.findViewById(R.id.btn_camera);
        Button btnAlbum = (Button) dialog.findViewById(R.id.btn_album);
        btnCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnCamera.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                openCamera();
                dialog.dismiss();
            }
        });


        btnAlbum.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FileUtil.IsExistsSDCard()) {
                    // 打开相册
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/*");
                    intent.addCategory(Intent.CATEGORY_OPENABLE);
                    intent.putExtra("return_data", true);
                    startActivityForResult(intent, Constant.RESULT_SDCARD);
                } else {
                    Toast.makeText(getThisContext(),
                            "sdcard无效或没有插入", Toast.LENGTH_LONG).show();
                }
                dialog.dismiss();
            }
        });

        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        dialog.show();
    }

    //打开照相机
    private void openCamera() {
        if (FileUtil.IsExistsSDCard()) {
            Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            File mPhotoFile = new File(MainApplication.getCacheImagePath());
            if (!mPhotoFile.exists())
                mPhotoFile.mkdir();
            File mOutPhotoFile = new File(MainApplication.getCacheImagePath(),
                    DateUtil.getDateString("yyyyMMddHHmmss") + ".png");
            mPhotoPath = mOutPhotoFile.getAbsolutePath();
            Uri uri = Uri.fromFile(mOutPhotoFile);
            intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            intentCamera.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
            startActivityForResult(intentCamera, Constant.RESULT_CAMERA);
        } else {
            Toast.makeText(this, "sdcard无效或没有插入", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case Constant.RESULT_SDCARD:
                    Uri uri = data.getData();
                    Log.e("----uri----", uri.toString());
                    String photoPath = FileUtil.getPath(this, uri);
                    if (!TextUtils.isEmpty(photoPath)) {
                        getDisplayImage(photoPath);
                    }
                    break;
                case Constant.RESULT_CAMERA:
                    Uri uri1 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                    if (uri1 != null) {
                        getDisplayImage(mPhotoPath);
                    } else {
                        Toast.makeText(this, "sdcard无效或没有插入", Toast.LENGTH_LONG).show();
                    }
                    break;
                case Constant.RESULT_SERVICE_TYPE_CANCEL:
                    finish();
                    break;
            }
        } else if (resultCode == 1014) {
            switch (requestCode) {
                case RESULT_FIRST_USER:
                    String mDeletePath = data.getStringExtra("path");
                    mImageLayout.removeAllViewsInLayout();
                    mImageLayout.addView(mAddImage);
                    mAddImage.setVisibility(View.VISIBLE);
                    Iterator<String> iterator = pathList.iterator();
                    //用迭代器删除集合中的数据
                    while (iterator.hasNext()) {
                        String str = iterator.next();
                        if (mDeletePath.equals(str)) {
                            iterator.remove();
                        }
                    }
                    if (pathList != null && pathList.size() > 0) {
                        int width = getScreenWidthPixels(this) - 300;
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width / 4,
                                width / 4);
                        params.leftMargin = 10;
                        for (String s : pathList) {
                            ImageView imageView = new ImageView(this);
                            Bitmap bitmap = BitmapFactory.decodeFile(s);
                            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                            imageView.setImageBitmap(bitmap);
                            mImageLayout.addView(imageView, mImageLayout.getChildCount() - 1, params);
                            imageView.setTag(s);
                            imageView.setOnClickListener(new OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(getThisContext(), ShowImageActivity.class);
                                    intent.putExtra("path", (String) v.getTag());
                                    startActivityForResult(intent, RESULT_FIRST_USER);
                                }
                            });

                        }
                    }
                    break;
            }
        }
    }

    //显示图片并保存
    private void getDisplayImage(String path) {

        String mImagePath = UploadImage.getImageUri(path);
        pathList.add(mImagePath);
        Bitmap bitmap = BitmapFactory.decodeFile(mImagePath);

        int width = getScreenWidthPixels(this) - 300;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width / 4,
                width / 4);
        params.leftMargin = 10;
        ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setImageBitmap(bitmap);
        imageView.setTag(mImagePath);
        mImageLayout.addView(imageView, mImageLayout.getChildCount() - 1, params);
        if (mImageLayout.getChildCount() > 3) {
            mAddImage.setVisibility(View.GONE);
        }
        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getThisContext(), ShowImageActivity.class);
                intent.putExtra("path", (String) v.getTag());
                startActivityForResult(intent, RESULT_FIRST_USER);
            }
        });
    }

    //提交车辆违停信息
    private void submitVehicleViolateInformation() {
        CarApi api = ApiClient.create(CarApi.class);
        CarViolationParam param = new CarViolationParam();
        param.setCarNo(mCar.getCarNo());
        param.setUserSid(mUserHelper.getSid());
        if (stringBuilder.toString().endsWith(";")) {
            param.setImages(stringBuilder.toString().substring(0, stringBuilder.toString().lastIndexOf(";")));
        }

        if (apartmentInfoTo != null && apartmentInfoTo.getPlace() != null
                && apartmentInfoTo.getPlace().getLocation() != null) {
            param.setLat(apartmentInfoTo.getPlace().getLocation().getLat());
            param.setLng(apartmentInfoTo.getPlace().getLocation().getLng());
        }
        if (apartmentInfoTo != null) {
            param.setApartmentSid(apartmentInfoTo.getApartmentSid());
        }
        param.setLocation(mPlace.getText().toString());
        param.setRemark(mDescContent.getText().toString());
        final CustomDialogFragment dialogFragment = new CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "");
        api.violation(param, new HttpCallback<MessageTo<CarTo>>(this) {
            @Override
            public void success(MessageTo<CarTo> msg, Response response) {
                dialogFragment.dismissAllowingStateLoss();
                if (msg.getSuccess() == 0) {
                    Intent intent = new Intent(getThisContext(), VehicleInformationActivity.class);
                    intent.putExtra("carInfo", msg.getData());
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getThisContext(),
                            msg.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                dialogFragment.dismissAllowingStateLoss();
                super.failure(error);
            }
        });


    }

    //小区选择对话框
    private void selectApartmentDialog(final TextView name) {

        final CustomDialog dialog = new CustomDialog(this, R.layout.dialog_apartment, R.style.myDialogTheme);
        TextView mCancel = (TextView) dialog.findViewById(R.id.cancel);
        final ListView mList = (ListView) dialog.findViewById(R.id.list);
        mCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        ApartmentApi api = ApiClient.create(ApartmentApi.class);
        api.findByUserSid(mUserHelper.getSid(), new HttpCallback<MessageTo<List<ApartmentInfoTo>>>(this) {
                    @Override
                    public void success(final MessageTo<List<ApartmentInfoTo>> msg, Response response) {
                        if (msg.getSuccess() == 0) {
                            final List<ApartmentInfoTo> infoList = new ArrayList<>();
                            infoList.addAll(msg.getData());
                            ApartmentAdapter mAdapter = new ApartmentAdapter(getThisContext());
                            mAdapter.setList(infoList);
                            mList.setAdapter(mAdapter);
                            mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    apartmentInfoTo = infoList.get(position);
                                    name.setText(apartmentInfoTo.getApartmentName());
                                    dialog.dismiss();
                                }
                            });
                        } else {
                            Toast.makeText(getThisContext(),
                                    msg.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        super.failure(error);
                    }
                }
        );
        dialog.setCancelable(true);
        dialog.show();

    }


    @Override
    public void complete(String s, ResponseInfo responseInfo, JSONObject jsonObject) {
        stringBuilder.append(s);
        stringBuilder.append(";");
        count++;
        if (count == mImageLayout.getChildCount() - 1) {
            submitVehicleViolateInformation();
        }
    }

    @Override
    protected Context getThisContext() {
        return VehicleInformationActivity.this;
    }

    @Override
    protected String toPageName() {
        super.toPageName();

        return "车辆信息页";
    }
}
