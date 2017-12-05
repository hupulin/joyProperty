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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jinyi.ihome.module.apartment.ApartmentInfoTo;
import com.joy.common.api.ApartmentApi;
import com.joy.common.api.ApiClient;
import com.joy.common.api.CarApi;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.VendorApi;
import com.joy.common.widget.UploadImage;
import com.joy.property.MainApplication;
import com.joy.property.R;
import com.joy.property.base.BaseActivity;
import com.joy.property.constant.Constant;
import com.joy.property.inspection.adapter.ApartmentAdapter;
import com.joy.property.vehicle.adapter.VehicleBrandAdapter;
import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.car.CarFindParam;
import com.jinyi.ihome.module.car.CarParam;
import com.jinyi.ihome.module.car.CarTo;
import com.joy.library.fragment.CustomDialogFragment;
import com.joy.library.utils.CustomDialog;
import com.joy.library.utils.DateUtil;
import com.joy.library.utils.FileUtil;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Admin on 2015-03-24.
 * 车辆信息管理 编辑保存
 */
public class EditorVehicleInformationActivity extends BaseActivity
        implements OnClickListener, UpCompletionHandler {
    private TextView mPlateLicenseNo;
    private EditText mVehicleOwnerName;
    private EditText mVehicleOwnerPhone;
    private TextView mVehicleBrandName;
    private TextView mVehicleColor;
    //小区
    private TextView apartment;
    private RelativeLayout itemApartment ;
    private EditText mVehicleModel;
    private ImageView mVehicleImage;
    private String mCarNo;
    private String mPhotoPath = "";
    private String mImagePath = "";
    private String mPath = "";
    private EditText mCarPlace;
    private CustomDialogFragment dialogFragment = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor_vehicle_information);
        findById();
        getIntentData();
        initialData();
        listeningEditWatcher();

    }


    /**
     * 初始化控件
     */
    private void findById() {
        mBack = (ImageView) findViewById(R.id.back);
        mBack.setOnClickListener(this);
        mPlateLicenseNo = (TextView) findViewById(R.id.license);
        mVehicleOwnerName = (EditText) findViewById(R.id.vehicle_owner);
        mVehicleOwnerPhone = (EditText) findViewById(R.id.owner_phone);
        mVehicleBrandName = (TextView) findViewById(R.id.vehicle_brand_name);
        mVehicleColor = (TextView) findViewById(R.id.vehicle_color);
        Button mSaveInformation = (Button) findViewById(R.id.save_information);
        mSaveInformation.setOnClickListener(this);
        RelativeLayout mItemBrand = (RelativeLayout) findViewById(R.id.item04);
        //新增小区一行
        apartment = (TextView) findViewById(R.id.apartment);
         itemApartment = (RelativeLayout) findViewById(R.id.item_apartment);
        mItemBrand.setOnClickListener(this);
        RelativeLayout mItemColor = (RelativeLayout) findViewById(R.id.item06);
        mItemColor.setOnClickListener(this);
        mVehicleModel = (EditText) findViewById(R.id.vehicle_model);
        mVehicleImage = (ImageView) findViewById(R.id.image);
        mVehicleImage.setOnClickListener(this);
        mCarPlace = (EditText) findViewById(R.id.place);
    }

    private void getIntentData() {
        mCarNo = getIntent().getStringExtra("carNo");

    }

    private   String[] apartmentName={} ;
    private   String apartmentSid ;
    private String[] carPlace={};
    private String[] apartmentSidList={} ;
    // 初始化数据
    private void initialData() {
        CarApi api = ApiClient.create(CarApi.class);
        CarFindParam findParam = new CarFindParam();
        findParam.setCarNo(mCarNo);
        Log.i("222", "initialData: "+mUserHelper.getSid());
        findParam.setUserSid(mUserHelper.getSid());
        final CustomDialogFragment dialogFragment = new CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "");
        api.findCar(findParam, new HttpCallback<MessageTo<CarTo>>(this) {
            @Override
            public void success(MessageTo<CarTo> msg, Response response) {
                dialogFragment.dismissAllowingStateLoss();
                if (msg.getSuccess() == 0) {
                    final CarTo mCar = msg.getData();
                    if (1 == mCar.getIsYeZhu()){

                        if (!TextUtils.isEmpty(mCar.getCarApartmentName())) {
                             apartmentName =mCar.getCarApartmentName().split(",");
                            apartment.setText(apartmentName[0]);

                        }
                        if (!TextUtils.isEmpty(mCar.getCarApartmentId())) {
                            apartmentSidList =mCar.getCarApartmentId().split(",");
                            apartmentSid=apartmentSidList[0];
                        }
                            if (!TextUtils.isEmpty(mCar.getCarPlace())) {
                                carPlace = mCar.getCarPlace().split(",");
                                mCarPlace.setText(carPlace[0]);
                            }
                                 mCarPlace.setEnabled(false);
                            itemApartment.setOnClickListener(new OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if(apartmentName==null||apartmentName.length==0){
                                        return;
                                    }else{
                                    vehicleApartmentDialogShow(apartmentName);}
                                }
                            });

                    }else{
                        itemApartment.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                selectApartmentDialog();
                            }
                        });
                        mCarPlace.setEnabled(true);

                    }
                    //姓名
                    if (!TextUtils.isEmpty(mCar.getCarOwner())) {
                        mVehicleOwnerName.setText(mCar.getCarOwner());
                    }
                    //电话
                    if (!TextUtils.isEmpty(mCar.getCarPhone())) {
                        mVehicleOwnerPhone.setText(mCar.getCarPhone());
                    }
                    //车牌
                    if (!TextUtils.isEmpty(mCar.getCarNo())) {
                        mPlateLicenseNo.setText(mCar.getCarNo());
                    }
//                    if (0 == mCar.getIsYeZhu()&&mCar.getCarRecords() != null ) {
////                        if (!TextUtils.isEmpty(mCar.getCarRecords().get(0).getOwnerCarPlace())) {
////                            mCarPlace.setText(mCar.getCarRecords().get(0).getOwnerCarPlace());
////                        }
//                        if (!TextUtils.isEmpty(mCar.getCarRecords().get(0).getOwnerName())) {
//                            mVehicleOwnerName.setText(mCar.getCarRecords().get(0).getOwnerName());
//                        }
//
//                        if (!TextUtils.isEmpty(mCar.getCarRecords().get(0).getOwnerPhone())) {
//                            mVehicleOwnerPhone.setText(mCar.getCarRecords().get(0).getOwnerPhone());
//                        }
//                    }else{
////车位
//                        if (!TextUtils.isEmpty(mCar.getCarPlace())) {
//                            mCarPlace.setText(mCar.getCarPlace());
//                        }
////姓名
//                        if (!TextUtils.isEmpty(mCar.getCarOwner())) {
//                            mVehicleOwnerName.setText(mCar.getCarOwner());
//                        }
////电话
//                        if (!TextUtils.isEmpty(mCar.getCarPhone())) {
//                            mVehicleOwnerPhone.setText(mCar.getCarPhone());
//                        }
//                    }


                    if (!TextUtils.isEmpty(mCar.getCarModel())) {
                        mVehicleModel.setText(mCar.getCarModel());
                    }
                    if (!TextUtils.isEmpty(mCar.getCarBrand())) {
                        mVehicleBrandName.setText(mCar.getCarBrand());
                    }
                    if (!TextUtils.isEmpty(mCar.getCarColor())) {
                        mVehicleColor.setText(mCar.getCarColor());
                    }
                    displayImage(mVehicleImage, mCar.getCarImages());
                    if (!TextUtils.isEmpty(mCar.getCarImages())) {
                        mPath=mCar.getCarImages();
                    }
//                    if (!TextUtils.isEmpty(mCar.getCarPlace())) {
//
//                        mCarPlace.setText(mCar.getCarPlace());
//                    }
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

    private void listeningEditWatcher() {
        mVehicleOwnerName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 20) {
                    Toast.makeText(getThisContext(),
                            "名字最多只能输入20个字哦", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.back:
                onBackPressed();
                break;
            case R.id.item04:
                //品牌选择对话框
                vehicleBrandDialogShow();
                break;
            case R.id.item06:
                //颜色选择对话框
                vehicleColorDialogShow();
                break;
            case R.id.save_information:

                //保存信息
                if (checking())
                    return;
                if (!TextUtils.isEmpty(mImagePath)) {
                    uploadImage();
                } else {
                    saveInformation();
                }

                break;
            case R.id.image:
                uploadImageDialogShow();
                break;
            default:
                break;
        }
    }
    private boolean checking() {
        if (TextUtils.isEmpty(apartment.getText())) {
            Toast.makeText(getThisContext(), "请选择小区", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (TextUtils.isEmpty(mCarPlace.getText())) {
            Toast.makeText(getThisContext(), "请输入车位", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (!TextUtils.isEmpty(mVehicleOwnerPhone.getText())) {

            String mStr[]  = mVehicleOwnerPhone.getText().toString().split("/");
            for (String s: mStr) {

                if (s.length() < 11 || !s.matches("^(1[3|4|5|8|7]\\d{9})$")) {
                    Toast.makeText(getThisContext(),"请输入正确的手机号码" ,Toast.LENGTH_LONG).show();
                    return true;
                }

            }
        }
        return false;
    }

    private void uploadImageDialogShow() {
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
        }
    }

    //显示图片 并保存
    private void getDisplayImage(String path) {
        mImagePath = UploadImage.getImageUri(path);
        Bitmap bitmap = BitmapFactory.decodeFile(mImagePath);
        mVehicleImage.setImageBitmap(bitmap);
    }

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


    /**
     * 品牌选择对话框
     */
    private void vehicleBrandDialogShow() {
        final CustomDialog dialog = new CustomDialog(this, R.layout.dialog_vehicle_brand, R.style.myDialogTheme);
        TextView mCancel = (TextView) dialog.findViewById(R.id.cancel);
        final ListView mBrandList = (ListView) dialog.findViewById(R.id.list);
        mCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        CarApi api = ApiClient.create(CarApi.class);
        api.loadConfig(new HttpCallback<MessageTo<String[][]>>(this) {
            @Override
            public void success(MessageTo<String[][]> msg, Response response) {
                if (msg.getSuccess() == 0) {
                    String[][] data = msg.getData();
                    final String[] array = data[1];
                    VehicleBrandAdapter mAdapter = new VehicleBrandAdapter(getThisContext());
                    mAdapter.setList(array);
                    mBrandList.setAdapter(mAdapter);
                    mBrandList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String mBrand = array[position];
                            mVehicleBrandName.setText(mBrand);
                            dialog.dismiss();
                        }
                    });
                } else {
                    Toast.makeText(getThisContext(),
                            msg.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                super.failure(error);
            }
        });
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }
    /**
     * 小区选择对话框
     */
    private void vehicleApartmentDialogShow(String[] apartmentName  ) {
        final CustomDialog dialog = new CustomDialog(this, R.layout.dialog_vehicle_brand, R.style.myDialogTheme);
        TextView mDialogCancel = (TextView) dialog.findViewById(R.id.cancel);
        final ListView mColorList = (ListView) dialog.findViewById(R.id.list);
        mDialogCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        VehicleBrandAdapter mAdapter = new VehicleBrandAdapter(getThisContext());
        mAdapter.setList(apartmentName);
        mColorList.setAdapter(mAdapter);
        mColorList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String mApartment = apartmentName[position];
                apartment.setText(mApartment);
                apartmentSid=apartmentSidList[position];
                mCarPlace.setText(carPlace[position]);
                dialog.dismiss();
            }
        });
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }


    /**
     * 颜色选择对话框
     */
    private void vehicleColorDialogShow() {
        final CustomDialog dialog = new CustomDialog(this, R.layout.dialog_vehicle_brand, R.style.myDialogTheme);
        TextView mDialogCancel = (TextView) dialog.findViewById(R.id.cancel);
        final ListView mColorList = (ListView) dialog.findViewById(R.id.list);
        mDialogCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        CarApi api = ApiClient.create(CarApi.class);
        api.loadConfig(new HttpCallback<MessageTo<String[][]>>(this) {
            @Override
            public void success(MessageTo<String[][]> msg, Response response) {
                if (msg.getSuccess() == 0) {
                    String[][] data = msg.getData();
                    final String[] array = data[0];
                    VehicleBrandAdapter mAdapter = new VehicleBrandAdapter(getThisContext());
                    mAdapter.setList(array);
                    mColorList.setAdapter(mAdapter);
                    mColorList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String mColor = array[position];
                            mVehicleColor.setText(mColor);
                            dialog.dismiss();
                        }
                    });
                } else {
                    Toast.makeText(getThisContext(),
                            msg.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                super.failure(error);
            }
        });
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    private void selectApartmentDialog() {
        final com.joy.property.utils.CustomDialog dialog = new com.joy.property.utils.CustomDialog(this, R.layout.dialog_apartment, R.style.myDialogTheme);
        TextView mCancel = (TextView) dialog.findViewById(R.id.cancel);
        mCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        final ListView mList = (ListView) dialog.findViewById(R.id.list);
        ApartmentApi api = ApiClient.create(ApartmentApi.class);
        final CustomDialogFragment dialogFragment = new CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "");
        System.out.println(mUserHelper.getSid());
        api.findByUserSid(mUserHelper.getSid(), new HttpCallback<MessageTo<List<ApartmentInfoTo>>>(getThisContext()) {
                    @Override
                    public void success(MessageTo<List<ApartmentInfoTo>> msg, Response response) {
                        dialogFragment.dismiss();
                        if (msg.getSuccess() == 0) {

                            final List<ApartmentInfoTo> infoList = new ArrayList<>();

                            infoList.addAll(msg.getData());
                            ApartmentAdapter mAdapter = new ApartmentAdapter(getThisContext());
                            mAdapter.setList(infoList);
                            mList.setAdapter(mAdapter);

                            mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                                    apartment = infoList.get(position);
                                    apartment.setText(infoList.get(position).getApartmentName());
                                    apartmentSid=infoList.get(position).getApartmentSid();

//                                    mApartment.setText(apartment.getApartmentName());
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
                        dialogFragment.dismissAllowingStateLoss();
                        super.failure(error);
                    }
                }
        );

        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    //上传到七牛
    private void uploadImage() {
        VendorApi api = ApiClient.create(VendorApi.class);
        dialogFragment = new CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "");
        api.getQnToken(new HttpCallback<MessageTo<String>>(this) {
            @Override
            public void success(MessageTo<String> msg, Response response) {
                if (msg.getSuccess() == 0) {
                    String token = msg.getData();
                    UploadManager uploadManager = new UploadManager();
                    uploadManager.put(mImagePath, UUID.randomUUID().toString(), token, EditorVehicleInformationActivity.this, null);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                dialogFragment.dismiss();
                super.failure(error);
            }
        });

    }

    @Override
    public void complete(String s, ResponseInfo responseInfo, JSONObject jsonObject) {
        mPath = s;
        saveInformation();
    }


    /**
     * 保存信息
     */
    private void saveInformation() {


        CarApi api = ApiClient.create(CarApi.class);
        CarParam param = new CarParam();
        param.setUserSid(mUserHelper.getSid());
        param.setCarImages(mPath);
        param.setApartmentSid(apartmentSid);
        param.setCarPlace(mCarPlace.getText().toString());
        param.setCarModel(mVehicleModel.getText().toString());
        param.setCarBrand(mVehicleBrandName.getText().toString());
        param.setCarColor(mVehicleColor.getText().toString());
        param.setCarNo(mPlateLicenseNo.getText().toString());
        param.setCarOwner(mVehicleOwnerName.getText().toString());
        param.setCarPhone(mVehicleOwnerPhone.getText().toString());

        if (dialogFragment == null) {
            dialogFragment = new CustomDialogFragment();
            dialogFragment.show(getSupportFragmentManager(),"");
        }
        api.updateCar(param, new HttpCallback<MessageTo<CarTo>>(this) {
            @Override
            public void success(MessageTo<CarTo> msg, Response response) {
                dialogFragment.dismissAllowingStateLoss();
                if (msg.getSuccess() == 0) {
                    Intent intent = new Intent(getThisContext(), VehicleInformationActivity.class);
                    intent.putExtra("carInfo", msg.getData());
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
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

    @Override
    protected Context getThisContext() {
        return EditorVehicleInformationActivity.this;
    }

    @Override
    protected String toPageName() {
        return "车辆信息编辑页";
    }
}
