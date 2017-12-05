package com.joy.property.task;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.Util.SpUtil;
import com.alibaba.fastjson.JSON;
import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.home.DataTo;
import com.jinyi.ihome.module.home.ServiceHandleParam;
import com.jinyi.ihome.module.home.ServiceMainExpandTo;
import com.joy.common.api.ApiClient;
import com.joy.common.api.HomeApi;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.VendorApi;
import com.joy.common.widget.TaskUploadImage;
import com.joy.common.widget.UploadImage;
import com.joy.library.fragment.CustomDialogFragment;
import com.joy.library.utils.DateUtil;
import com.joy.library.utils.FileUtil;
import com.joy.property.MainApplication;
import com.joy.property.R;
import com.joy.property.base.BaseActivity;
import com.joy.property.common.ShowImageActivity;
import com.joy.property.constant.Constant;
import com.joy.property.utils.ACache;
import com.joy.property.utils.CustomDialog;
import com.joy.property.utils.flowlayout.TagAdapter;
import com.joy.property.utils.flowlayout.TagFlowLayout;
import com.joyhome.nacity.app.photo.adapter.GridAdapter;
import com.lidong.photopicker.ImageCaptureManager;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.wefika.flowlayout.FlowLayout;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import retrofit.RetrofitError;
import retrofit.client.Response;
import rx.Observable;
import rx.functions.Action1;

public class FeedBackTaskActivity extends BaseActivity implements
        OnClickListener, UpCompletionHandler {

    private EditText mContent;
    private EditText mEvaluation;
    private  String photoTime;
    private ImageView mAdd;
    private FlowLayout flowLayout;
    private List<String> mList = new ArrayList<>();
    private List<String> pathList = new ArrayList<>();
    private int count = 0;
    private StringBuilder stringBuilder = new StringBuilder();
    private ServiceMainExpandTo mainExpandTo;
    private TextView mTip;
    private CustomDialogFragment dialogFragment = null;
    private Button mSubmit;
    Intent intent;
    private GridView gridView;
    private static final int REQUEST_CAMERA_CODE = 10;
    private static final int REQUEST_PREVIEW_CODE = 20;
    private ArrayList<String> imagePaths = new ArrayList<>();
    private ImageCaptureManager captureManager; // 相机拍照处理类
    private TagFlowLayout mFlowLayout;
    private List<String> mNamesSelect=new ArrayList<>();
    private List<String> mLabel=new ArrayList<>();
    private int [] select={};
    private List<TextView> flowViewList=new ArrayList<>();
    private List<PhotoCacheTo> cacheList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_task);
        findById();
        initIntentDatas();
//        getCache();
        getEvaluateLabel();
        initDatas();
       if (JSON.parseArray(new ACache().getAsString(mainExpandTo.getServiceNo()),PhotoCacheTo.class)!=null){
           cacheList=JSON.parseArray(new ACache().getAsString(mainExpandTo.getServiceNo()),PhotoCacheTo.class);
           if (cacheList.size()>0)
              Observable.from(cacheList).subscribe(photoCacheTo -> getDisplayImage(photoCacheTo.getPath(),photoCacheTo.getTimePath(),false));
       }else {
           cacheList = new ArrayList<>();
       }

       mContent.setText(new ACache().getAsString(mainExpandTo.getServiceSid()));

    }


//    private void getCache() {
//        if(!TextUtils.isEmpty(SpUtil.getString(getThisContext(),mainExpandTo.getServiceNo()))){
//            String cache=SpUtil.getString(getThisContext(),mainExpandTo.getServiceNo());
//            String d[] = cache.split(",");
//            for (int i = 0; i < d.length; i++) {
//                Log.i("222", "onCreate: "+d[i]);
//                getDisplayImageCache(d[i]);
//                pathList.add("d[i]");
//            }
//        }
//    }
//    private void getDisplayImageCache(String path) {
//        flowLayout.removeAllViewsInLayout();
//        FlowLayout.LayoutParams params = getFlowLayoutParams();
//        params.leftMargin = 10;
//        params.topMargin = 10;
//        flowLayout.addView(mAdd, flowLayout.getChildCount());
//        mAdd.setVisibility(View.VISIBLE);
//        ImageView imageView = new ImageView(getThisContext());
//        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//        Bitmap bitmap = BitmapFactory.decodeFile(path);
//        imageView.setImageBitmap(bitmap);
//        imageView.setTag(path);
//        flowLayout.addView(imageView, flowLayout.getChildCount() - 1, params);
//        imageView.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getThisContext(), ShowImageActivity.class);
//                intent.putExtra("path", (String) v.getTag());
//                startActivityForResult(intent, 1024);
//
//            }
//        });
//    }
    private void getEvaluateLabel() {
        HomeApi api = ApiClient.create(HomeApi.class);
        api.findEvaluateLabel("1", new HttpCallback<MessageTo<DataTo>>(this) {
            @Override
            public void success(MessageTo<DataTo> msg, Response response) {
                {
                if (msg.getSuccess() == 0) {
                    mLabel.addAll(msg.getData().getList());
                    Log.i("2221", "success: "+mLabel.toString());
                    Log.i("2222", "success: "+msg.getData().toString());
                    mAdapter.notifyDataChanged();
//                    if(!TextUtils.isEmpty(SpUtil.getString(getThisContext(),mainExpandTo.getTypeSid()))){
//                        String cache=SpUtil.getString(getThisContext(),mainExpandTo.getTypeSid());
//                        Log.i("22222", "findById: "+cache);
//                        String d[] = cache.split(",");
//                        Set<Integer> staffsSet = new HashSet<Integer>();
//
//                        for (int i = 0; i < d.length; i++) {
//                            flowViewList.get(Integer.parseInt(d[i])).setTextColor(Color.parseColor("#ffffff"));
//                            flowViewList.get(Integer.parseInt(d[i])).setSelected(true);
//                            flowViewList.get(Integer.parseInt(d[i])).setBackgroundDrawable(getResources().getDrawable(R.drawable.negative_comment_check_bg));
//                            mNamesSelect.add(d[i]);
//
//                            staffsSet.add(Integer.parseInt(d[i]));
//                        }
//
//                        mAdapter.setSelectedList(staffsSet);

//                    }
                }
                }
            }
            @Override
            public void failure(RetrofitError error) {
                super.failure(error);


            }
        });
    }

    public static void sortArray(int[] targetArr) {
        long t = System.currentTimeMillis();
        Arrays.sort(targetArr);
        System.out.println("执行时间：" + (System.currentTimeMillis() - t) + "毫秒");
        System.out.println("排序后结果");
        for(int i : targetArr){
            System.out.print(i + "  ");
        }
    }
    private TagAdapter<String> mAdapter=new TagAdapter<String>(mLabel){
        @Override
        public View getView(com.joy.property.utils.flowlayout.FlowLayout parent, int position, String s) {
            LayoutInflater mInflater = LayoutInflater.from(getThisContext());
            RelativeLayout mRelativeLayout = (RelativeLayout) mInflater.inflate(R.layout.tv_new,
                    mFlowLayout, false);
            TextView textView = (TextView) mRelativeLayout.findViewById(R.id.textView);
            textView.setText(s);
            flowViewList.add(textView);
            mFlowLayout.setOnSelectListener(new TagFlowLayout.OnSelectListener(){
                @Override
                public void onSelected(Set<Integer> selectPosSet)
                {
                    mNamesSelect.clear();
                    Object[] obj = selectPosSet.toArray();
                    select=new int [selectPosSet.size()];
                    for (int i = 0; i < obj.length; i++) {
                        select[i] = (int) obj[i];//将Object对象数组转为整型数组（强制向下转型）
                        flowViewList.get(select[i]);
                    }
                    sortArray(select);
                    for (int i = 0; i <select.length ; i++) {
                        mNamesSelect.add(mLabel.get(select[i]));
                    }
                    Log.i("2222", "onSelected: "+mNamesSelect.toString());
//                    String cache=listToString(mNamesSelectnNumCache);
////                        Log.i("22222", "onActivityResult: "+cache);
////                        Log.i("22222", "onActivityResult: "+mList.get(0));
//                    SpUtil.put(getThisContext(),mainExpandTo.getTypeSid(),cache);
                }
            });

            mFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                @Override
                public boolean onTagClick(View view, int position, com.joy.property.utils.flowlayout.FlowLayout parent)
                {
                    if (flowViewList.get(position).isSelected()){
                        flowViewList.get(position).setTextColor(Color.parseColor("#666666"));
                        flowViewList.get(position).setSelected(false);
                        flowViewList.get(position).setBackgroundDrawable(getResources().getDrawable(R.drawable.negative_comment_uncheck_bg));

                    }else{
                        flowViewList.get(position).setTextColor(Color.parseColor("#ffffff"));
                        flowViewList.get(position).setSelected(true);
                        flowViewList.get(position).setBackgroundDrawable(getResources().getDrawable(R.drawable.negative_comment_check_bg));
                    }
                    return true;
                }
            });
//
            return mRelativeLayout;
        }
    };
    private void findById() {
        mAdd = (ImageView) findViewById(R.id.add);
        mAdd.setOnClickListener(this);
        findViewById(R.id.back).setOnClickListener(this);
        mContent = (EditText) findViewById(R.id.content);
        mEvaluation = (EditText) findViewById(R.id.evaluation_content);
        mSubmit = (Button) findViewById(R.id.submit);
        mSubmit.setOnClickListener(this);
        mTip = (TextView) findViewById(R.id.tip);
        mFlowLayout = (TagFlowLayout) findViewById(R.id.flowlayout);
        flowLayout = (FlowLayout) findViewById(R.id.flow);
        mFlowLayout.setAdapter(mAdapter);


    }
    private String serviceType;
    private String serviceSid;
    private void initIntentDatas() {
        mainExpandTo = (ServiceMainExpandTo) getIntent().getSerializableExtra("mode");
        serviceType= getIntent().getStringExtra("serviceType");
        serviceSid=getIntent().getStringExtra("serviceSid");

    }

    private void initDatas() {
        mContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

               mTip.setText("还可以输入" + (200 - s.length()) + "字");
                if (s.length() > 200) {
                    Toast.makeText(getThisContext(), "反馈内容最多只能输入200字哦", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        new ACache().put(mainExpandTo.getServiceSid(), mContent.getText().toString());
        super.onBackPressed();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                onBackPressed();
                break;
            case R.id.add:
                addDialogShow();
                break;
            case R.id.submit:
                if (checking())
                    return;

                    uploaderImage();

                    view.setEnabled(false);//将当前触发控件给禁用

                break;
            default:
                break;

        }

    }



    private boolean checking() {

        if (TextUtils.isEmpty(mContent.getText())) {

            Toast.makeText(this, "反馈内容不能为空哦，请输入", Toast.LENGTH_SHORT).show();
            return true;
        }if (pathList.size()==0) {

            Toast.makeText(this, "请上传图片", Toast.LENGTH_SHORT).show();
            return true;
        }
//        if(mNamesSelect.size()==0&&TextUtils.isEmpty(mEvaluation.getText().toString())){
//            Toast.makeText(this, "评价业主信息不能为空哦，请输入", Toast.LENGTH_SHORT).show();
//
//            return true;
//
//        }
        return false;
    }
    public static String listToString(List<String> stringList){
        if (stringList==null) {
            return null;
        }
        StringBuilder result=new StringBuilder();
        boolean flag=false;
        for (String string : stringList) {
            if (flag) {
                result.append(",");
            }else {
                flag=true;
            }
            result.append(string);
        }
        return result.toString();
    }
    private void submit() {

        HomeApi api = ApiClient.create(HomeApi.class);
        ServiceHandleParam param = new ServiceHandleParam();
        param.setServiceSid(mainExpandTo.getServiceSid());
        if(serviceType!=null&&serviceSid!=null){
            param.setTypeSid(serviceSid);
            param.setTypeName(serviceType);
        }
        param.setGroupUserSid(mUserHelper.getSid());
        param.setProcessDesc(mContent.getText().toString());
        Log.i("2222", "提交: "+cacheList.toString());
        param.setPhotoTime(cacheList.get(0).getPhotoTime());
        //评价标签和评价信息
        param.setEvaluationTags(listToString(mNamesSelect));
        param.setEvaluationMsg(mEvaluation.getText().toString());
        Log.i("222", "submit: "+param.toString());

        if (stringBuilder.toString().endsWith(";")) {
            param.setProcessImage(stringBuilder.toString().substring(0, stringBuilder.toString().lastIndexOf(";")));
        }

        if (dialogFragment == null) {
            dialogFragment = new CustomDialogFragment();
            dialogFragment.show(getSupportFragmentManager(),"");
        }

        api.handleService(param, new HttpCallback<MessageTo<ServiceMainExpandTo>>(this) {
            @Override
            public void success(MessageTo<ServiceMainExpandTo> msg, Response response) {
                dialogFragment.dismissAllowingStateLoss();
                if (msg.getSuccess() == 0) {
                    Intent intent = new Intent(getThisContext(), ReceiveTaskDetailActivity.class);
                    intent.putExtra("mode", msg.getData().getServiceSid());
                    intent.putExtra("sid",msg.getData().getServiceSid());
                    intent.putExtra("type","0");
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("is_submit",true);
                    intent.putExtra("flag","flag_who");
                    //startActivity(intent);
                    setResult(0x123,intent);
                    finish();
                    cacheList.clear();
                    new ACache().put(mainExpandTo.getServiceSid(), "");
                    new ACache().put(mainExpandTo.getServiceNo(), JSON.toJSONString(cacheList));
                } else {

                    Toast.makeText(getThisContext(), msg.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                new ACache().put(mainExpandTo.getServiceSid(), mContent.getText().toString());

                dialogFragment.dismissAllowingStateLoss();
                super.failure(error);
            }
        });


    }

    private void uploaderImage() {
        VendorApi api = ApiClient.create(VendorApi.class);
        dialogFragment = new CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "");
        api.getQnToken(new HttpCallback<MessageTo<String>>(this) {
            @Override
            public void success(MessageTo<String> msg, Response response) {
                if (msg.getSuccess() == 0) {
                    String token = msg.getData();
                    UploadManager uploadManager = new UploadManager();
                    if (pathList != null && pathList.size() > 0) {
                        for (int i = 0; i < pathList.size(); i++) {
                            System.out.println(pathList.get(i)+"path----------------");
                            uploadManager.put(TaskUploadImage.getImageUri(pathList.get(i)), UUID.randomUUID().toString(), token, FeedBackTaskActivity.this, null);
                        }
                    }
                }
            }

            @Override
            public void failure(RetrofitError error) {
                dialogFragment.dismiss();
                super.failure(error);

            }
        });

    }

    private void addDialogShow() {
        final CustomDialog dialog = new CustomDialog(this,
         R.layout.dialog_upload_image, R.style.myDialogTheme);
        Button btnCancel = (Button) dialog.findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        Button btnCamera = (Button) dialog.findViewById(R.id.btn_camera);
        btnCamera.setVisibility(View.GONE);
        Button btnAlbum = (Button) dialog.findViewById(R.id.btn_album);
        btnAlbum.setText("相机拍照");
        btnAlbum.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                getpermission();
                dialog.dismiss();
            }
        });
//        Button btnAlbum = (Button) dialog.findViewById(R.id.btn_album);
//        btnAlbum.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                if (FileUtil.IsExistsSDCard()) {
//                    // 打开相册
//                    Intent intent = new Intent();
//                    intent.setAction(Intent.ACTION_GET_CONTENT);
//                    intent.setType("image/*");
//                    intent.addCategory(Intent.CATEGORY_OPENABLE);
//                    intent.putExtra("return_data", true);
//                    startActivityForResult(intent, Constant.RESULT_SDCARD);
//                } else {
//                    Toast.makeText(getThisContext(), "sdcard无效或没有插入", Toast.LENGTH_LONG).show();
//                }
//                dialog.dismiss();
//            }
//        });
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(false);
        dialog.show();
//        requestPermissions(Manifest.permission.CAMERA);


    }

    private void getpermission() {
        //请求单个权限     为我们申请运行时权限
        RxPermissions rxPermissions=new RxPermissions(this);
        rxPermissions.request(Manifest.permission.CAMERA)//这里填写所需要的权限
                        .subscribe(aBoolean -> {
                            if (aBoolean) {//true表示获取权限成功（注意这里在android6.0以下默认为true）
                                Log.i("permissions", Manifest.permission.CAMERA + "：" + "获取成功");
                                openCamera();
                            } else {
                                ToastShowLong(FeedBackTaskActivity.this,"没有获得拍照权限");
                            }
                        });



    }

    //    public void choosePhone(View view){
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
//        {
//            ActivityCompat.requestPermissions(this,
//                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
//                    MY_PERMISSIONS_REQUEST_CALL_PHONE2);
//
//        }else {
//            choosePhoto();
//        }
//    }
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == FragmentActivity.RESULT_OK) {
            switch (requestCode) {

                case Constant.RESULT_CAMERA:
                    Uri uri2 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                    if (uri2 != null) {

                        getDisplayImage(mList.get(0),UploadImage.getImageUri(mList.get(0)),true);

                    } else {
                        Toast.makeText(getThisContext(), "sdcard无效或没有插入", Toast.LENGTH_LONG).show();
                    }
                    break;
                case Constant.RESULT_SERVICE_TYPE_CANCEL:
                   finish();
                    break;
            }
        } else if (resultCode == 1014) {

            if (requestCode == 1024) {
                String path = data.getStringExtra("path");
                for (int i=0;i<cacheList.size();i++){
                    if (path.equals(cacheList.get(i).getTimePath())){
                      cacheList.remove(i);
                        break;
                    }
                }
                new ACache().put(mainExpandTo.getServiceNo(), JSON.toJSONString(cacheList));
                Iterator<String> iterator = pathList.iterator();

                while (iterator.hasNext()) {
                    String s = iterator.next();
                    if (path.equals(s)) {
                        iterator.remove();

                    }

                }
                String cache=listToString(pathList);
                SpUtil.put(getThisContext(),mainExpandTo.getServiceNo(),cache);
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
                if (flowLayout.getChildCount() > 9) {
                    mAdd.setVisibility(View.GONE);
                }


            }
        }
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK) {
//            switch (requestCode) {
//                case Constant.RESULT_SDCARD:
//                    Uri uri = data.getData();
//                    Log.e("uri", uri.toString());
//                    String mPhotoPath = FileUtil.getPath(this, uri);
//                    if (!TextUtils.isEmpty(mPhotoPath)) {
//                        getImageDisplay(mPhotoPath);
//                    }
//                    break;
//                case Constant.RESULT_CAMERA:
//                    Uri uri2 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
//                    if (uri2 != null) {
//                        getImageDisplay(mList.get(0));
//                    } else {
//                        Toast.makeText(this, "sdcard无效或没有插入", Toast.LENGTH_LONG).show();
//                    }
//                    break;
//                case Constant.RESULT_SERVICE_TYPE_CANCEL:
//                    finish();
//                    break;
//            }
//        } else if (resultCode == 1014) {
//            if (requestCode == 1234) {
//
//                // 用迭代器删除集合中的元素
//                String mDeletePath = data.getStringExtra("path");
//                Iterator<String> iterator = pathList.iterator();
//                while (iterator.hasNext()) {
//                    String s = iterator.next();
//                    if (mDeletePath.equals(s)) {
//                        iterator.remove();
//                    }
//                }
//                flowLayout.removeAllViews();
//                flowLayout.addView(mAdd);
//                mAdd.setVisibility(View.VISIBLE);
//                for (int i = 0; i < pathList.size(); i++) {
//                    DisplayMetrics mDisplayMetrics = new DisplayMetrics();
//                    getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
//                    int W = mDisplayMetrics.widthPixels - 50;
//                    FlowLayout.LayoutParams params = new FlowLayout.LayoutParams(W / 4, W / 4);
//                    params.leftMargin = 10;
//                    params.topMargin = 10;
//                    ImageView imageView = new ImageView(this);
//                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//                    Bitmap bitmap = BitmapFactory.decodeFile(pathList.get(i));
//                    imageView.setImageBitmap(bitmap);
//                    imageView.setTag(pathList.get(i));
//                    flowLayout.addView(imageView, flowLayout.getChildCount() - 1, params);
//                    imageView.setOnClickListener(new OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            Intent intent = new Intent(getThisContext(), ShowImageActivity.class);
//                            intent.putExtra("path", (String) v.getTag());
//                            startActivityForResult(intent, 1234);
//
//                        }
//                    });
//                }
//
//            }
//        }
//    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(resultCode == RESULT_OK) {
//            switch (requestCode) {
//                // 选择照片
//                case REQUEST_CAMERA_CODE:
//                    ArrayList<String> list = data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT);
//
//                    loadAdpater(list);
//                    break;
//                // 预览
//                case REQUEST_PREVIEW_CODE:
//                    ArrayList<String> ListExtra = data.getStringArrayListExtra(PhotoPreviewActivity.EXTRA_RESULT);
//
//                    loadAdpater(ListExtra);
//                    break;
//            }
//        }
//    }

//    private void loadAdpater(ArrayList<String> paths){
//        if (imagePaths!=null&& imagePaths.size()>0){
//            imagePaths.clear();
//        }
//        if (paths.contains("000000")){
//            paths.remove("000000");
//        }
//        paths.add("000000");
//        imagePaths.addAll(paths);
//        gridAdapter  = new GridAdapter(imagePaths);
//        gridView.setAdapter(gridAdapter);
//        try{
//            JSONArray obj = new JSONArray(imagePaths);
//            Log.e("--", obj.toString());
//        }catch (Exception e){
//            e.printStackTrace();
//        }
   }

    /**
     * 保存并显示图片
     */
    private void getImageDisplay(String path) {
        String mPath = UploadImage.getImageUri(path);
        pathList.add(mPath);
        Bitmap bitmap = BitmapFactory.decodeFile(mPath);
        DisplayMetrics mDisplayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
        int W = mDisplayMetrics.widthPixels - 50;
        FlowLayout.LayoutParams params = new FlowLayout.LayoutParams(W / 4, W / 4);
        params.leftMargin = 10;
        params.topMargin = 10;
        ImageView image = new ImageView(this);
        image.setScaleType(ImageView.ScaleType.FIT_XY);
        image.setImageBitmap(bitmap);
        image.setTag(mPath);
        image.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getThisContext(), ShowImageActivity.class);
                intent.putExtra("path", v.getTag().toString());
                startActivityForResult(intent, 1234);
            }
        });

        flowLayout.addView(image, flowLayout.getChildCount() - 1, params);
        if (flowLayout.getChildCount() > 4) {
            mAdd.setVisibility(View.GONE);
        }
    }
    public FlowLayout.LayoutParams getFlowLayoutParams() {
        int W = getScreenWidthPixels(getThisContext()) - 70;
        return new FlowLayout.LayoutParams(W / 4, W / 4);
    }

    @Override
    public void complete(String s, ResponseInfo responseInfo, JSONObject jsonObject) {
        stringBuilder.append(s);
        stringBuilder.append(";");
        count++;


        if (count == flowLayout.getChildCount() - 1) {
//            SpUtil.put(getThisContext(),mainExpandTo.getServiceNo(),"");
////            SpUtil.put(getThisContext(),mainExpandTo.getTypeSid(),"");

            submit();
        }
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        mSubmit.setEnabled(true);//将触发控件设置为可用
        //tv1.setEnabled(true);
    }
    @Override
    protected Context getThisContext() {
        return FeedBackTaskActivity.this;
    }

    @Override
    protected String toPageName() {

        return "任务反馈";
    }


//    private class GridAdapter extends BaseAdapter {
//        private ArrayList<String> listUrls;
//        private LayoutInflater inflater;
//        public GridAdapter(ArrayList<String> listUrls) {
//            this.listUrls = listUrls;
//            if(listUrls.size() == 10){
//                listUrls.remove(listUrls.size()-1);
//            }
//            inflater = LayoutInflater.from(getThisContext());
//        }
//
//        public int getCount(){
//            return  listUrls.size();
//        }
//        @Override
//        public String getItem(int position) {
//            return listUrls.get(position);
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return position;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            ViewHolder holder = null;
//            if (convertView == null) {
//                holder = new ViewHolder();
//                convertView = inflater.inflate(R.layout.item_image, parent,false);
//                holder.image = (ImageView) convertView.findViewById(R.id.imageView);
//                convertView.setTag(holder);
//            } else {
//                holder = (ViewHolder)convertView.getTag();
//            }
//
//            final String path=listUrls.get(position);
//            if (path.equals("000000")){
//                holder.image.setImageResource(R.drawable.add_ic);
//            }else {
//                Glide.with(getThisContext())
//                        .load(path)
//                        .placeholder(R.mipmap.default_error)
//                        .error(R.mipmap.default_error)
//                        .centerCrop()
//                        .crossFade()
//                        .into(holder.image);
//            }
//            return convertView;
//        }
//        class ViewHolder {
//            ImageView image;
//        }
//    }
    private boolean isFirst=true;
    private Date photoTimeDate;

private void getDisplayImage(String photoPath,String path,boolean addCache){
  //  String path = UploadImage.getImageUri(photoPath);
    pathList.add(path);
if (addCache) {
//    String cache = listToString(pathList);
//    SpUtil.put(getThisContext(), mainExpandTo.getServiceNo(), cache);
    ExifInterface exifInterface = null;
    try {
        exifInterface = new ExifInterface(photoPath);
    } catch (IOException e) {
        e.printStackTrace();
    }
    if (exifInterface != null) {
        photoTime = exifInterface.getAttribute(ExifInterface.TAG_DATETIME);
        photoTimeDate = null;
        photoTimeDate = DateUtil.getFormatDateLongTimePhoto(photoTime);
    }
    isFirst = false;


               PhotoCacheTo  cacheTo=new PhotoCacheTo();
               cacheTo.setPath(photoPath);
               cacheTo.setTimePath(path);
               cacheTo.setPhotoTime(photoTimeDate);
               cacheList.add(cacheTo);
           }
    Log.i("2222", "拍完照: "+cacheList.toString());

            new ACache().put(mainExpandTo.getServiceNo(), JSON.toJSONString(cacheList));
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
        if (flowLayout.getChildCount() > 9) {
            mAdd.setVisibility(View.GONE);
        }

}
}
