package com.joy.property.vehicle.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.apartment.ApartmentInfoTo;
import com.jinyi.ihome.module.home.LllegalParkParam;
import com.jinyi.ihome.module.home.ServiceInspectionPositionTo;
import com.jinyi.ihome.module.home.ServiceMainTo;
import com.jinyi.ihome.module.home.ServiceRequestParam;
import com.jinyi.ihome.module.home.ServiceTypeTo;
import com.joy.common.api.ApartmentApi;
import com.joy.common.api.ApiClient;
import com.joy.common.api.HomeApi;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.VendorApi;
import com.joy.common.widget.UploadImage;
import com.joy.library.fragment.CustomDialogFragment;
import com.joy.library.utils.DateUtil;
import com.joy.library.utils.FileUtil;
import com.joy.property.MainApplication;
import com.joy.property.R;
import com.joy.property.base.BaseFragment;
import com.joy.property.common.ShowImageActivity;
import com.joy.property.constant.Constant;

import com.joy.property.task.SideBar;
import com.joy.property.vehicle.CarNumberActivity;
import com.joy.property.vehicle.SelectInLegalActivity;
import com.joy.property.vehicle.SelectPositionActivity;
import com.joy.property.vehicle.VehicleInspectionActivity;
import com.joy.property.vehicle.adapter.ApartmentAdapter;
import com.joy.property.utils.CustomDialog;
import com.joy.property.vehicle.adapter.PlaceListAdapter;
import com.joy.property.vehicle.adapter.RefreshEvent;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;
import com.wefika.flowlayout.FlowLayout;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONObject;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by usb on 2017/2/26.
 */

    @SuppressLint("ValidFragment")
    public class SubmissionFragment extends BaseFragment
            implements View.OnClickListener, UpCompletionHandler {

        private EditText mContent;
        private ImageView mAdd;
        private TextView mTip;
        private TextView mCommunity;
        private TextView mType;
        private TextView mPlace;
        private TextView mCarNum;
        private ImageView mCarOrder;
        private FlowLayout flowLayout;
        private RelativeLayout selectCommunity;
        private RelativeLayout selectPlace;
        private RelativeLayout selectType;
        private RelativeLayout carNumber;
        private RelativeLayout carOrder;
        private int count = 0;
        private StringBuffer stringBuffer = new StringBuffer();
        private List<String> pathList = new ArrayList<>();
        private List<String> mList = new ArrayList<>();
        private ApartmentInfoTo apartment;
        private ServiceTypeTo serviceTypeTo;
        private CustomDialogFragment dialogFragment = null;
        private LinearLayout ownerInspectionLayout;
        private TextView roomStart;
        private boolean isOrder;
        private ServiceRequestParam param;
        private boolean isFirstRegist;
        private String apartmentSid ="";


    public SubmissionFragment(){

        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            super.onCreateView(inflater, container, savedInstanceState);
            View rooView = inflater.inflate(R.layout.fragment_vehicle_submission, container, false);
            findById(rooView);
            initData();
            if (!isFirstRegist)
                EventBus.getDefault().register(this);
            isFirstRegist=true;
            return rooView;
        }


        private void findById(View view) {
            mContent = (EditText) view.findViewById(R.id.content);
            mTip = (TextView) view.findViewById(R.id.tip);
            mCommunity = (TextView) view.findViewById(R.id.tv_select_community);
            mType = (TextView) view.findViewById(R.id.tv_select_type);
            mPlace = (TextView) view.findViewById(R.id.tv_select_place);
            mCarNum = (TextView) view.findViewById(R.id.tv_car_number);
            mCarOrder = (ImageView) view.findViewById(R.id.iv_car_order);
            mAdd = (ImageView) view.findViewById(R.id.add);
            mAdd.setOnClickListener(this);
            flowLayout = (FlowLayout) view.findViewById(R.id.flowLayout);
            selectCommunity = (RelativeLayout) view.findViewById(R.id.select_community);
            selectCommunity.setOnClickListener(this);
            selectPlace = (RelativeLayout) view.findViewById(R.id.select_place);
            selectPlace.setOnClickListener(this);
            selectType = (RelativeLayout) view.findViewById(R.id.select_type);
            selectType.setOnClickListener(this);
            carNumber = (RelativeLayout) view.findViewById(R.id.car_number);
            carNumber.setOnClickListener(this);
            carOrder = (RelativeLayout) view.findViewById(R.id.car_order);
            carOrder.setOnClickListener(this);
            roomStart = (TextView) view.findViewById(R.id.roomStart);
            Button mSubmit = (Button) view.findViewById(R.id.submit);
            mSubmit.setOnClickListener(this);

            FlowLayout.LayoutParams params = getFlowLayoutParams();
            params.topMargin = 10;
            params.leftMargin = 10;
            mAdd.setLayoutParams(params);
        }


        private void initData() {
            param = new ServiceRequestParam();
            mContent.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    mTip.setText("还可以输入" + (200 - s.length()) + "字");
                    if (s.length() > 200) {
                        Toast.makeText(getThisContext(), "你只能输入200字哦", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }

        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (resultCode == FragmentActivity.RESULT_OK) {
                switch (requestCode) {
                    case Constant.RESULT_SDCARD:
                        Uri uri = data.getData();
                        Log.e("uri", uri.toString());
                        String mPhotoPath = FileUtil.getPath(getThisContext(), uri);
                        if (!TextUtils.isEmpty(mPhotoPath)) {
                            getDisplayImage(mPhotoPath);
                        }
                        break;

                    case Constant.RESULT_CAMERA:
                        Uri uri2 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                        if (uri2 != null) {
                            getDisplayImage(mList.get(0));
                        } else {
                            Toast.makeText(getThisContext(), "sdcard无效或没有插入", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case Constant.RESULT_SERVICE_TYPE_CANCEL:
                        getActivity().finish();
                        break;
                }
            } else if (resultCode == 1014) {
                if (requestCode == 1024) {
                    String path = data.getStringExtra("path");
                    Iterator<String> iterator = pathList.iterator();
                    while (iterator.hasNext()) {
                        String s = iterator.next();
                        if (path.equals(s)) {
                            iterator.remove();
                        }
                    }
                    flowLayout.removeAllViewsInLayout();

                    FlowLayout.LayoutParams params = getFlowLayoutParams();
                    params.leftMargin = 10;
                    params.topMargin = 10;
                    flowLayout.addView(mAdd, flowLayout.getChildCount());
                    mAdd.setVisibility(View.VISIBLE);
                    for (String p : pathList) {
                        ImageView imageView = new ImageView(getThisContext());
                        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                        Bitmap bitmap = BitmapFactory.decodeFile(p);
                        imageView.setImageBitmap(bitmap);
                        imageView.setTag(p);
                        flowLayout.addView(imageView, flowLayout.getChildCount() - 1, params);
                        imageView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(getThisContext(), ShowImageActivity.class);
                                intent.putExtra("path", (String) v.getTag());
                                startActivityForResult(intent, 1024);

                            }
                        });
                    }

                    if (flowLayout.getChildCount() > 4) {
                        mAdd.setVisibility(View.GONE);
                    }


                }
            }else if (resultCode == 20) {
                if (requestCode == 20) {

                    String carNum = data.getStringExtra("carNum");
                    Log.i("2221",carNum);
                    mCarNum.setText(carNum);
                }
            }
        }

        private void getDisplayImage(String photoPath) {
            String path = UploadImage.getImageUri(photoPath);
            pathList.add(path);
            Bitmap bitmap = BitmapFactory.decodeFile(path);
            FlowLayout.LayoutParams params = getFlowLayoutParams();
            params.leftMargin = 10;
            params.topMargin = 10;
            ImageView image = new ImageView(getThisContext());
            image.setScaleType(ImageView.ScaleType.FIT_XY);
            image.setImageBitmap(bitmap);
            image.setTag(path);
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getThisContext(), ShowImageActivity.class);
                    intent.putExtra("path", v.getTag().toString());
                    startActivityForResult(intent, 1024);
                }
            });

            flowLayout.addView(image, flowLayout.getChildCount() - 1, params);
            if (flowLayout.getChildCount() > 4) {
                mAdd.setVisibility(View.GONE);
            }

        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.add:
                    uploadImageDialog();
                    break;
                case R.id.select_community:
                   selectCommunity();
                    break;
                case R.id.select_place:
                  getPlaceList();
                    break;
                case R.id.select_type:
                    getLllegallyList();
                    break;
                case R.id. car_number:
                    Intent intent = new Intent(getThisContext(), CarNumberActivity.class);
                    intent.putExtra("mCarNum",mCarNum.getText().toString());
                    startActivityForResult(intent,20);
              //      startActivity(intent);
                    break;
                case R.id. car_order:
                    isGetOrder();
                    break;

                case R.id.submit:
                    if (checking())
                        return;

                    if (pathList != null && pathList.size() > 0) {
                        uploadImage();
                    } else {
                        submitData();
                    }
                    break;
            }
        }
//违停类型
    private void selectType() {
        final CustomDialog dialog = new CustomDialog(getActivity(), R.layout.dialog_apartment_list, R.style.myDialogTheme);
        TextView mCancel = (TextView) dialog.findViewById(R.id.cancel);
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        final ListView mList = (ListView) dialog.findViewById(R.id.list_apartment);
        List<String> place= new ArrayList<>();
        place.add("临时违停");
        place.add("严重违停");
        place.add("违停");
        PlaceListAdapter mAdapter = new PlaceListAdapter(getThisContext());
        mAdapter.setList(place);
        mList.setAdapter(mAdapter);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }
//选择是否已贴罚单
    public void isGetOrder() {
            isOrder=!isOrder;
        if(isOrder){
            mCarOrder.setImageResource(R.drawable.vehicle_icon_select);
        }else{
            mCarOrder.setImageResource(R.drawable.vehicle_icon);
        }

    }


    //违停地点
    private void selectPlace() {
        final CustomDialog dialog = new CustomDialog(getActivity(), R.layout.dialog_apartment_list, R.style.myDialogTheme);
        TextView mCancel = (TextView) dialog.findViewById(R.id.cancel);
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        final ListView mList = (ListView) dialog.findViewById(R.id.list_apartment);
        List<String> place= new ArrayList<String>();
        place.add("地面");
        place.add("地下");
        place.add("车库");
        PlaceListAdapter mAdapter = new PlaceListAdapter(getThisContext());
        mAdapter.setList(place);
        mList.setAdapter(mAdapter);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
     }
    //选择小区
         private void selectCommunity() {
                    final CustomDialog dialog = new CustomDialog(getActivity(), R.layout.dialog_apartment_list, R.style.myDialogTheme);
                    TextView mCancel = (TextView) dialog.findViewById(R.id.cancel);
                   SideBar sideBar = (SideBar)dialog.findViewById(R.id.sideBar);
                    mCancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                 //   final ListView mList = (ListView) dialog.findViewById(R.id.list_apartment);
             final PullToRefreshListView pullToRefreshListView = (PullToRefreshListView) dialog.findViewById(R.id.list_apartment);
             pullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
             ListView mList=pullToRefreshListView.getRefreshableView();
                    ApartmentApi api = ApiClient.create(ApartmentApi.class);
                    final CustomDialogFragment dialogFragment = new CustomDialogFragment();
                    dialogFragment.show(getFragmentManager(), "");
                    api.findByUserSid(mUserHelper.getSid(), new HttpCallback<MessageTo<List<ApartmentInfoTo>>>(getThisContext()) {
                                @Override
                                public void success(MessageTo<List<ApartmentInfoTo>> msg, Response response) {
                                   dialogFragment.dismiss();
                                    if (msg.getSuccess() == 0) {
                                        final List<ApartmentInfoTo> infoList = new ArrayList<>();
                                        infoList.addAll(compara(msg.getData()));
                                        ApartmentAdapter mAdapter = new ApartmentAdapter(getThisContext());
                                        mList.setAdapter(mAdapter);
                                        mAdapter.setList(infoList);
                                        sideBar.setFocusable(true);
                                        sideBar.requestFocus();
                                        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

                                            @Override
                                            public void onTouchingLetterChanged(String s) {
                                                int position = mAdapter.getPositionForSection(s.charAt(0));
                                                if (position != -1) {
                                                    Log.i("2", "onTouchingLetterChanged: "+s);
                                                    mList.setSelection(position);
                                                }

                                            }
                                        });

                                        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                apartment = infoList.get(position-1);
                                                mCommunity.setText(apartment.getApartmentName());
                                                param.setApartmentSid(apartment.getApartmentSid());
                                                if (!apartmentSid.equals(apartment.getApartmentName()))
                                                    cleanData();
                                                apartmentSid =apartment.getApartmentSid();
                                                //serviceTypeTo = null;
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
//        private void selectApartmentDialog() {
//            final CustomDialog dialog = new CustomDialog(getActivity(), R.layout.dialog_apartment, R.style.myDialogTheme);
//            TextView mCancel = (TextView) dialog.findViewById(R.id.cancel);
//            mCancel.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    dialog.dismiss();
//                }
//            });
//            final ListView mList = (ListView) dialog.findViewById(R.id.list);
//            ApartmentApi api = ApiClient.create(ApartmentApi.class);
//            final CustomDialogFragment dialogFragment = new CustomDialogFragment();
//            dialogFragment.show(getFragmentManager(), "");
//            System.out.println(mUserHelper.getSid());
//            api.findByUserSid(mUserHelper.getSid(), new HttpCallback<MessageTo<List<ApartmentInfoTo>>>(getThisContext()) {
//                        @Override
//                        public void success(MessageTo<List<ApartmentInfoTo>> msg, Response response) {
//                            dialogFragment.dismiss();
//                            if (msg.getSuccess() == 0) {
//
//                                final List<ApartmentInfoTo> infoList = new ArrayList<>();
//
//                                infoList.addAll(msg.getData());
//                                ApartmentAdapter mAdapter = new ApartmentAdapter(getThisContext());
//                                mAdapter.setList(infoList);
//                                mList.setAdapter(mAdapter);
//
//                                mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                                    @Override
//                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                                        apartment = infoList.get(position);
//                                        mApartment.setText(apartment.getApartmentName());
//                                        mCategory.setText("请选择提报类别");
//
//                                        //  init();
//                                        serviceTypeTo = null;
//                                        dialog.dismiss();
//                                    }
//                                });
//                            } else {
//                                Toast.makeText(getThisContext(),
//                                        msg.getMessage(), Toast.LENGTH_SHORT).show();
//                            }
//                        }
//
//                        @Override
//                        public void failure(RetrofitError error) {
//                            dialogFragment.dismissAllowingStateLoss();
//                            super.failure(error);
//                        }
//                    }
//            );
//
//            dialog.setCancelable(true);
//            dialog.setCanceledOnTouchOutside(true);
//            dialog.show();
//        }

        /**
         * 选择类别
         */
//        private void categoryShowDialog() {
//
//            final CustomDialog dialog = new CustomDialog(getActivity(), R.layout.dialog_apartment, R.style.myDialogTheme);
//            final ListView mList = (ListView) dialog.findViewById(R.id.list);
//            TextView mCancel = (TextView) dialog.findViewById(R.id.cancel);
//            HomeApi api = ApiClient.create(HomeApi.class);
//            final CustomDialogFragment dialogFragment = new CustomDialogFragment();
//            dialogFragment.show(getFragmentManager(),"");
//            api.findServiceTypeInfoByApartmentAndCategory(4, apartment.getApartmentSid(), new HttpCallback<MessageTo<List<ServiceTypeTo>>>(getActivity()) {
//                @Override
//                public void success(MessageTo<List<ServiceTypeTo>> msg, Response response) {
//                    dialogFragment.dismissAllowingStateLoss();
//                    if (msg.getSuccess() == 0) {
//                        final List<ServiceTypeTo> typeToList = new ArrayList<>();
//                        typeToList.addAll(msg.getData());
//                        TypeAdapter mAdapter = new TypeAdapter(getThisContext());
//                        mAdapter.setList(typeToList);
//                        mList.setAdapter(mAdapter);
//                        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                            @Override
//                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                                serviceTypeTo = typeToList.get(position);
//                                mCategory.setText(serviceTypeTo.getTypeName());
//                                dialog.dismiss();
//                            }
//                        });
//                    } else {
//                        Toast.makeText(getThisContext(),
//                                msg.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//
//                }
//
//                @Override
//                public void failure(RetrofitError error) {
//                    dialogFragment.dismissAllowingStateLoss();
//                    super.failure(error);
//                }
//            });
//
//            mCancel.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    dialog.dismiss();
//                }
//            });
//            dialog.setCancelable(true);
//            dialog.setCanceledOnTouchOutside(true);
//            dialog.show();
//        }

        private void uploadImage() {
            VendorApi api = ApiClient.create(VendorApi.class);
            dialogFragment = new CustomDialogFragment();
            dialogFragment.show(getFragmentManager(), "");
            api.getQnToken(new HttpCallback<MessageTo<String>>(getActivity()) {
                @Override
                public void success(MessageTo<String> msg, Response response) {
                    if (msg.getSuccess() == 0) {
                        String token = msg.getData();
                        UploadManager uploadManager = new UploadManager();
                        if (pathList != null && pathList.size() > 0) {
                            for (int i = 0; i < pathList.size(); i++) {
                                uploadManager.put(pathList.get(i), UUID.randomUUID().toString(), token, SubmissionFragment.this, null);
                            }
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

        private boolean checking() {

            if (TextUtils.isEmpty(mContent.getText())) {
                Toast.makeText(getThisContext(), "提报内容为空哦，请输入描述以便于您的提报的被准确处理", Toast.LENGTH_SHORT).show();
                return true;
            } else if (apartment == null) {
                Toast.makeText(getThisContext(), "请先选择小区", Toast.LENGTH_SHORT).show();
                return true;
           }else if (TextUtils.isEmpty(mPlace.getText())) {
                Toast.makeText(getThisContext(), "请先选择违停地点", Toast.LENGTH_SHORT).show();
                return true;
            }else if (TextUtils.isEmpty(mType.getText())) {
                Toast.makeText(getThisContext(), "请先选择违停类型", Toast.LENGTH_SHORT).show();
                return true;
            }
            else if (TextUtils.isEmpty(mCarNum.getText())) {
                Toast.makeText(getThisContext(), "请填写车牌号", Toast.LENGTH_SHORT).show();
                return true;
            }
// else if (serviceTypeTo == null) {
//                Toast.makeText(getThisContext(), "请选择提报类型", Toast.LENGTH_SHORT).show();
//                return true;
//            }
            return false;
        }

        private void submitData() {

            HomeApi api = ApiClient.create(HomeApi.class);
            param.setServiceCategory("5CFB60A1-C1FC-4751-B123-05157F02C70D");
            param.setServiceDesc(mContent.getText().toString());
            param.setOwnerSid(mUserHelper.getSid());
            param.setCarNo(mCarNum.getText().toString());
            if (stringBuffer.toString().endsWith(";")) {
                param.setServiceImages(stringBuffer.toString().substring(0, stringBuffer.toString().lastIndexOf(";")));
            }
//            param.setIllegallyParkedPostion();

            param.setApartmentSid(apartment.getApartmentSid());
           param.setCarNo(mCarNum.getText().toString());
            param.setIllegallyParkedNotion(isOrder?"1":"0");
//            param.setTypeSid(serviceTypeTo.getTypeSid());
//            param.setTypeName(serviceTypeTo.getTypeName());
//            if (type==0){
//                param.setServicePhone(mCommunity.getText().toString());
//                param.setRoomNo(mCarOrder.getText().toString());
//                param.setServiceContact(mPlace.getText().toString());
//                   param.setServiceDesc((TextUtils.isEmpty(ownerName.getText())?"":(ownerName.getText().toString()+";"))+ownerRoom.getText().toString()+";"+(TextUtils.isEmpty(ownerPhone.getText())?"":ownerPhone.getText().toString()+";")+mContent.getText().toString());
//            }
            if (dialogFragment == null) {
                dialogFragment = new CustomDialogFragment();dialogFragment.show(getFragmentManager(),"");
            }
            System.out.println(param);
            api.addServiceRequest(param, new HttpCallback<MessageTo<ServiceMainTo>>(getThisContext()) {

                @Override
                public void success(MessageTo<ServiceMainTo> msg, Response response) {
                    dialogFragment.dismissAllowingStateLoss();
                    System.out.println(msg+"msg");
                    if (msg.getSuccess() == 0) {

                        Intent intent = new Intent(getThisContext(), VehicleInspectionActivity.class);
                        intent.putExtra("value", "0");
                   //     intent.putExtra("type", getActivity().getIntent().getIntExtra("type", 1));
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);


                    } else {
                        Toast.makeText(getThisContext(), msg.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void failure(RetrofitError error) {
                    dialogFragment.dismissAllowingStateLoss();
                    super.failure(error);
                    System.out.println(error+"error");
                }
            });

        }

        private void uploadImageDialog() {
            final CustomDialog dialog = new CustomDialog(getActivity(), R.layout.dialog_upload_image, R.style.myDialogTheme);
            Button btnCancel = (Button) dialog.findViewById(R.id.btn_cancel);
            Button btnCamera = (Button) dialog.findViewById(R.id.btn_camera);
            Button btnAlbum = (Button) dialog.findViewById(R.id.btn_album);
            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    dialog.dismiss();
                }
            });


            btnCamera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openCamera();
                    dialog.dismiss();
                }
            });


            btnAlbum.setOnClickListener(new View.OnClickListener() {
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
                        Toast.makeText(getThisContext(), "sdcard无效或没有插入", Toast.LENGTH_LONG).show();
                    }
                    dialog.dismiss();
                }
            });

            dialog.setCanceledOnTouchOutside(true);
            dialog.setCancelable(true);
            dialog.show();

        }

        /**
         * open the system camera
         */
        private void openCamera() {
            if (FileUtil.IsExistsSDCard()) {
                Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File mPhotoFile = new File(MainApplication.getCacheImagePath());
                if (!mPhotoFile.exists())
                    mPhotoFile.mkdir();

                File mOutPhotoFile = new File(MainApplication.getCacheImagePath(),
                        DateUtil.getDateString("yyyyMMddHHmmss") + ".png");
                String mPhotoPath = mOutPhotoFile.getAbsolutePath();

                mList.clear();
                mList.add(mPhotoPath);
                Uri uri = Uri.fromFile(mOutPhotoFile);
                intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                intentCamera.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
                startActivityForResult(intentCamera, Constant.RESULT_CAMERA);
            } else {
                Toast.makeText(getThisContext(), "sdcard无效或没有插入", Toast.LENGTH_LONG).show();
            }

        }

        @Override
        public void complete(String s, ResponseInfo responseInfo, JSONObject jsonObject) {

            stringBuffer.append(s + ";");
            count++;
            if (count == flowLayout.getChildCount() - 1) {
                submitData();
            }
        }

        protected Context getThisContext() {

            return getActivity();
        }

        public FlowLayout.LayoutParams getFlowLayoutParams() {
            int W = getScreenWidthPixels(getThisContext()) - 70;
            return new FlowLayout.LayoutParams(W / 4, W / 4);
        }
    //排序首字母
    public List<ApartmentInfoTo> compara(List<ApartmentInfoTo> infos){
        List<ApartmentInfoTo> mInfoTos=new ArrayList<>();

        int k,j;
        String[] fstr=new String[infos.size()];
        for(k=0;k<infos.size();k++){
            fstr[k]=getPingYin(infos.get(k).getPlace().getName());
            infos.get(k).setmPinyin(fstr[k]);
        }

        Arrays.sort(fstr);
        for(k=0;k<fstr.length;k++){
            for(ApartmentInfoTo info:infos)
            {

                if(fstr[k].equals(info.getmPinyin())){
                    mInfoTos.add(info);

                }
            }
        }

        return mInfoTos;
    }
    public static String getPingYin(String inputString) {

        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();

        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);

        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

        format.setVCharType(HanyuPinyinVCharType.WITH_V);

        char[] input = inputString.trim().toCharArray();

        String output = "";

        try {

            for (char curchar : input) {

                if (java.lang.Character.toString(curchar).matches(

                        "[\\u4E00-\\u9FA5]+")) {

                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(

                            curchar, format);

                    output += temp[0];

                } else

                    output += java.lang.Character.toString(curchar);

            }

        } catch (BadHanyuPinyinOutputFormatCombination e) {

            e.printStackTrace();

        }

        return output;


    }
public void getPlaceList(){
    if (TextUtils.isEmpty(apartmentSid)) {
        Toast.makeText(getThisContext(), "请先选择小区", Toast.LENGTH_LONG).show();
        return;
    }
    HomeApi api=ApiClient.create(HomeApi.class);
    CustomDialogFragment dialogFragment = new CustomDialogFragment();
    dialogFragment.show(getFragmentManager(), "");
    api.getPosition(apartmentSid, 0, new HttpCallback<MessageTo<List<ServiceInspectionPositionTo>>>(getThisContext()) {
       @Override
       public void success(MessageTo<List<ServiceInspectionPositionTo>> msg, Response response) {

           dialogFragment.dismiss();
           System.out.println(msg+"msg----------------------------------");
           if (msg.getSuccess()==0){
               if (msg.getData()==null||msg.getData().size()==0){
                   Toast.makeText(getThisContext(),"当前小区暂未开通该功能，请联系悦嘉家工作人员",Toast.LENGTH_LONG).show();
               }else {
                   Intent intent = new Intent(getThisContext(), SelectPositionActivity.class);
                   intent.putExtra("PositionList", (Serializable) msg.getData());
                   intent.putExtra("ApartmentSid", apartmentSid);
                   startActivity(intent);
                   goToAnimation(1);
               }
           }else
               Toast.makeText(getThisContext(),"当前小区暂未开通该功能，请联系悦嘉家工作人员",Toast.LENGTH_LONG).show();
       }

       @Override
       public void failure(RetrofitError error) {
           System.out.println(error + "error");
       }
   });

}
    public void getLllegallyList(){
        if (TextUtils.isEmpty(apartmentSid)) {
            Toast.makeText(getThisContext(), "请先选择项目", Toast.LENGTH_LONG).show();
            return;
        }
        HomeApi api=ApiClient.create(HomeApi.class);
        LllegalParkParam param=new LllegalParkParam();
        param.setApartmentSid(apartmentSid);
        param.setCategorySid("5CFB60A1-C1FC-4751-B123-05157F02C70D");
        CustomDialogFragment dialogFragment=new CustomDialogFragment();
        dialogFragment.show(getFragmentManager(),"");
        api.getLllegalList(param, new HttpCallback<MessageTo<List<ServiceTypeTo>>>(getThisContext()) {
            @Override
            public void success(MessageTo<List<ServiceTypeTo>> msg, Response response) {
                dialogFragment.dismiss();
                if (msg.getSuccess() == 0) {
                    if (msg.getData() == null || msg.getData().size() == 0)
                        Toast.makeText(getThisContext(), "当前小区暂未开通该功能，请联系悦嘉家工作人员", Toast.LENGTH_LONG).show();
                    else {
                        Intent intent = new Intent(getThisContext(), SelectInLegalActivity.class);
                        intent.putExtra("InlegalList", (Serializable) msg.getData());
                        startActivity(intent);
                    }
                }
            }

            @Override
            public void failure(RetrofitError error) {
                dialogFragment.dismiss();
                System.out.println(error + "error");
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEventMainThread(RefreshEvent event) {

        String msg = event.getMsg();
         if (event.getType()==2) {
             String positionName=event.getmName();
             positionName=positionName.substring(0,positionName.length()-1);
             param.setIllegallyParkedPostion(msg);
             param.setPositionName(positionName);

             if (positionName.contains("/"))
             mPlace.setText(positionName.substring(positionName.lastIndexOf("/")+1));
             else
                 mPlace.setText(positionName);
         }
        if (event.getType()==1) {
//            param.setpo(msg);
            mType.setText(event.getmName());
            param.setTypeSid(msg);
            param.setTypeName(event.getmName());
        }

    }
    public void submit(){

    }
    public void cleanData(){
        param.setIllegallyParkedPostion(null);
        param.setPositionName(null);
        param.setTypeName(null);
        param.setTypeSid(null);
        mPlace.setText("");
        mType.setText("");
    }
}

