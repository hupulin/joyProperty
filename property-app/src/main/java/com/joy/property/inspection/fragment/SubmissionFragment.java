package com.joy.property.inspection.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.Util.SpUtil;
import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.apartment.ApartmentInfoTo;
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
import com.joy.property.common.adapter.CommonFragmentPagerAdapter;
import com.joy.property.constant.Constant;
import com.joy.property.inspection.VillageInspectionActivity;
import com.joy.property.inspection.adapter.ApartmentAdapter;
import com.joy.property.inspection.adapter.TypeAdapter;
import com.joy.property.utils.CustomDialog;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;
import com.wefika.flowlayout.FlowLayout;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Admin on 2015-02-02
 */
@SuppressLint("ValidFragment")
public class SubmissionFragment extends BaseFragment
        implements OnClickListener, UpCompletionHandler {
    private EditText mContent;
    private ImageView mAdd;
    private TextView mTip;

    private FlowLayout flowLayout;
    private TextView mApartment;
    private TextView mEmergency;
    private TextView mCategory;
    private int count = 0;
    private StringBuffer stringBuffer = new StringBuffer();
    private List<String> pathList = new ArrayList<>();
    private List<String> mList = new ArrayList<>();
    private ApartmentInfoTo apartment;
    private ServiceTypeTo serviceTypeTo;
    private CustomDialogFragment dialogFragment = null;
    private int type;
    private LinearLayout ownerInspectionLayout;
    private EditText ownerName;
    private EditText ownerRoom;
    private EditText ownerPhone;
    private TextView roomStart;
private ViewPager mViewPager;
    public SubmissionFragment(int type){
        this.type=type;
    }
    public SubmissionFragment(int type, ViewPager viewPager){
        this.mViewPager=viewPager;
        this.type=type;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rooView = inflater.inflate(R.layout.fragment_submission, container, false);
        findById(rooView);
        setApartment();

        initData();
        setOwnerLayout();
        return rooView;
    }
//设置项目数唯一的时候默认值
    private void setApartment() {
        ApartmentApi api = ApiClient.create(ApartmentApi.class);
        final CustomDialogFragment dialogFragment = new CustomDialogFragment();
        dialogFragment.show(getFragmentManager(), "");
        api.findByUserSid(mUserHelper.getSid(), new HttpCallback<MessageTo<List<ApartmentInfoTo>>>(getThisContext()) {
            @Override
            public void success(MessageTo<List<ApartmentInfoTo>> msg, Response response) {
                dialogFragment.dismiss();
                if (msg.getSuccess() == 0) {
                if (msg.getData().size()==1){
                    apartment = msg.getData().get(0);
                    mApartment.setText(msg.getData().get(0).getApartmentName());
                    mCategory.setText("请选择提报类别");
                    serviceTypeTo = null;
                }
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
        });
    }


    private void findById(View view) {
        mContent = (EditText) view.findViewById(R.id.content);
        mTip = (TextView) view.findViewById(R.id.tip);
        mAdd = (ImageView) view.findViewById(R.id.add);
        mAdd.setOnClickListener(this);
        flowLayout = (FlowLayout) view.findViewById(R.id.flowLayout);
        mApartment = (TextView) view.findViewById(R.id.apartment);
        mApartment.setOnClickListener(this);
        mCategory = (TextView) view.findViewById(R.id.category);
        mCategory.setOnClickListener(this);
        mEmergency = (TextView) view.findViewById(R.id.emergency);
        mEmergency.setOnClickListener(this);
        ownerInspectionLayout = (LinearLayout) view.findViewById(R.id.OwnerInspectionLayout);
        ownerName = (EditText) view.findViewById(R.id.ownerName);
        ownerRoom = (EditText) view.findViewById(R.id.ownerRoom);
        ownerPhone = (EditText) view.findViewById(R.id.ownerPhone);
        roomStart = (TextView) view.findViewById(R.id.roomStart);
        Button mSubmit = (Button) view.findViewById(R.id.submit);
        mSubmit.setOnClickListener(this);

        FlowLayout.LayoutParams params = getFlowLayoutParams();
        params.topMargin = 10;
        params.leftMargin = 10;
        mAdd.setLayoutParams(params);
    }
    private void setOwnerLayout() {
        if (type==0){
            ownerInspectionLayout.setVisibility(View.VISIBLE);
            ownerRoom.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
if (s.length()>0)
    roomStart.setVisibility(View.GONE);
                    else
    roomStart.setVisibility(View.VISIBLE);
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
    }


    private void initData() {
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
                    imageView.setOnClickListener(new OnClickListener() {
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
        image.setOnClickListener(new OnClickListener() {
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
            case R.id.apartment:
                    selectApartmentDialog();
                break;
            case R.id.emergency:
                if (apartment == null) {
                    Toast.makeText(getThisContext(),
                            "请选择小区", Toast.LENGTH_SHORT).show();
                } else {
                     if (serviceTypeTo == null) {
                        Toast.makeText(getThisContext(),
                                "请选择提报类型", Toast.LENGTH_SHORT).show();
                    } else {
                         selectEmergencyDialog();
                    }
                }

                break;
            case R.id.category:
                if (apartment == null) {
                    Toast.makeText(getThisContext(),
                            "请选择小区", Toast.LENGTH_SHORT).show();
                } else {
                    categoryShowDialog();
                }
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
//选择紧急程度
    private void selectEmergencyDialog() {

        final CustomDialog dialog = new CustomDialog(getActivity(), R.layout.dialog_emergency, R.style.myDialogTheme);
        TextView mCancel = (TextView) dialog.findViewById(R.id.cancel);
        mCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        TextView mUrgent = (TextView) dialog.findViewById(R.id.urgent);
        mUrgent.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mEmergency.setText(mUrgent.getText().toString());
                dialog.dismiss();
            }
        });
        TextView mTvemergency = (TextView) dialog.findViewById(R.id.emergency);
        mTvemergency.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mEmergency.setText(mTvemergency.getText().toString());
                dialog.dismiss();
            }
        });
        TextView mMedium = (TextView) dialog.findViewById(R.id.medium);
        mMedium.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mEmergency.setText(mMedium.getText().toString());
                dialog.dismiss();
            }
        });
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }
//选择小区
    private void selectApartmentDialog() {
        final CustomDialog dialog = new CustomDialog(getActivity(), R.layout.dialog_apartment, R.style.myDialogTheme);
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
        dialogFragment.show(getFragmentManager(), "");
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
                                    apartment = infoList.get(position);
                                    mApartment.setText(apartment.getApartmentName());
                                    mCategory.setText("请选择提报类别");

                                    //  init();
                                    serviceTypeTo = null;
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

    /**
     * 选择类别
     */
    private void categoryShowDialog() {

        final CustomDialog dialog = new CustomDialog(getActivity(), R.layout.dialog_apartment, R.style.myDialogTheme);
        final ListView mList = (ListView) dialog.findViewById(R.id.list);
        TextView mCancel = (TextView) dialog.findViewById(R.id.cancel);
        HomeApi api = ApiClient.create(HomeApi.class);
        final CustomDialogFragment dialogFragment = new CustomDialogFragment();
        dialogFragment.show(getFragmentManager(),"");
        api.findServiceTypeInfoByApartmentAndCategory(4, apartment.getApartmentSid(), new HttpCallback<MessageTo<List<ServiceTypeTo>>>(getActivity()) {
            @Override
            public void success(MessageTo<List<ServiceTypeTo>> msg, Response response) {
                dialogFragment.dismissAllowingStateLoss();
                if (msg.getSuccess() == 0) {
                    final List<ServiceTypeTo> typeToList = new ArrayList<>();
                    typeToList.addAll(msg.getData());
                    TypeAdapter mAdapter = new TypeAdapter(getThisContext());
                    mAdapter.setList(typeToList);
                    mList.setAdapter(mAdapter);
                    mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            serviceTypeTo = typeToList.get(position);
                            Log.i("2222", "onItemClick: "+serviceTypeTo.toString());
                            mCategory.setText(serviceTypeTo.getTypeName());
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
        });

        mCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

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
        if (type==0) {
            if (TextUtils.isEmpty(ownerRoom.getText())) {
                Toast.makeText(getThisContext(), "提报房号为空哦", Toast.LENGTH_SHORT).show();
                return true;
            }
        }
         if (TextUtils.isEmpty(mContent.getText())) {
            Toast.makeText(getThisContext(), "提报内容为空哦，请输入描述以便于您的提报的被准确处理", Toast.LENGTH_SHORT).show();
            return true;
        } else if (apartment == null) {
            Toast.makeText(getThisContext(), "请请选择小区", Toast.LENGTH_SHORT).show();
            return true;
        } else if (serviceTypeTo == null) {
            Toast.makeText(getThisContext(), "请选择提报类型", Toast.LENGTH_SHORT).show();
            return true;
        }else if (TextUtils.isEmpty(mEmergency.getText())) {
             Toast.makeText(getThisContext(), "请选择紧急程度", Toast.LENGTH_SHORT).show();
             return true;
         }
        return false;
    }

    private void submitData() {
        HomeApi api = ApiClient.create(HomeApi.class);
        ServiceRequestParam param = new ServiceRequestParam();
        param.setServiceDesc(mContent.getText().toString());
        param.setOwnerSid(mUserHelper.getSid());
        if (stringBuffer.toString().endsWith(";")) {
            param.setServiceImages(stringBuffer.toString().substring(0, stringBuffer.toString().lastIndexOf(";")));
        }
        param.setApartmentSid(apartment.getApartmentSid());
        param.setServiceEmergenctStatus(mEmergency.getText().toString());
        param.setServiceCategory(serviceTypeTo.getCategorySid());
        param.setTypeSid(serviceTypeTo.getTypeSid());
        param.setTypeName(serviceTypeTo.getTypeName());
        if (type==0){
            param.setServicePhone(ownerPhone.getText().toString());
            param.setRoomNo(ownerRoom.getText().toString());
            param.setServiceContact(ownerName.getText().toString());
         //   param.setServiceDesc((TextUtils.isEmpty(ownerName.getText())?"":(ownerName.getText().toString()+";"))+ownerRoom.getText().toString()+";"+(TextUtils.isEmpty(ownerPhone.getText())?"":ownerPhone.getText().toString()+";")+mContent.getText().toString());
        }
        if (dialogFragment == null) {
           dialogFragment = new CustomDialogFragment();dialogFragment.show(getFragmentManager(),"");
        }
        System.out.println(param);
        SpUtil.put(getThisContext(), "PushDelay", true);
        api.addServiceRequest(param, new HttpCallback<MessageTo<ServiceMainTo>>(getThisContext()) {

            @Override
            public void success(MessageTo<ServiceMainTo> msg, Response response) {
                dialogFragment.dismissAllowingStateLoss();
                if (msg.getSuccess() == 0) {

//                    Intent intent = new Intent(getThisContext(), VillageInspectionActivity.class);
//                    intent.putExtra("value", "0");
//                    intent.putExtra("type", getActivity().getIntent().getIntExtra("type", 1));
//                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    Log.i("4444", "success: fragment");
//                    startActivity(intent);
                   mViewPager.setCurrentItem(1);
                    CommonFragmentPagerAdapter dd= (CommonFragmentPagerAdapter)mViewPager.getAdapter();
                    MySubmissionFragment c=(MySubmissionFragment)dd.getItem(1);
                    c.setList(0);
                    pathList.clear();
                    mContent.setText("");
                    ownerName.setText("");
                    ownerRoom.setText("");
                    ownerPhone.setText("");
                    roomStart.setVisibility(View.VISIBLE);
                    flowLayout.removeAllViewsInLayout();
                    flowLayout.addView(mAdd, 0);
                    mApartment.setText("请选择提报类型");
                    mEmergency.setText("请选择紧急程度");
                    mCategory.setText("请选择提报类别");
                } else {
                    Toast.makeText(getThisContext(), msg.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                dialogFragment.dismissAllowingStateLoss();
                super.failure(error);
            }
        });

    }

    private void uploadImageDialog() {
        final CustomDialog dialog = new CustomDialog(getActivity(), R.layout.dialog_upload_image, R.style.myDialogTheme);
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
}
