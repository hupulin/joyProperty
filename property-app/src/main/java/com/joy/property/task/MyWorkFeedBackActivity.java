package com.joy.property.task;

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
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.home.MyWorkHandleParam;
import com.jinyi.ihome.module.home.ServiceHandleParam;
import com.jinyi.ihome.module.home.ServiceMainExpandTo;
import com.jinyi.ihome.module.home.ServiceMyWorkTo;
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
import com.joy.property.utils.CustomDialog;
import com.lidong.photopicker.ImageCaptureManager;
import com.lidong.photopicker.PhotoPickerActivity;
import com.lidong.photopicker.PhotoPreviewActivity;
import com.lidong.photopicker.SelectModel;
import com.lidong.photopicker.intent.PhotoPickerIntent;
import com.lidong.photopicker.intent.PhotoPreviewIntent;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;
import com.wefika.flowlayout.FlowLayout;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import retrofit.RetrofitError;
import retrofit.client.Response;

public class MyWorkFeedBackActivity extends BaseActivity implements
        OnClickListener, UpCompletionHandler {

    private EditText mContent;

    private TextView mAdd;
    private FlowLayout flowLayout;
    private List<String> mList = new ArrayList<>();
    private List<String> pathList = new ArrayList<>();
    private int count = 0;
    private StringBuilder stringBuilder = new StringBuilder();
    private ServiceMyWorkTo mainExpandTo;
    private TextView mTip;
    private CustomDialogFragment dialogFragment = null;
    private Button mSubmit;
    Intent intent;
    private GridView gridView;
    private static final int REQUEST_CAMERA_CODE = 10;
    private static final int REQUEST_PREVIEW_CODE = 20;
    private ArrayList<String> imagePaths = new ArrayList<>();
    private ImageCaptureManager captureManager; // 相机拍照处理类
    private GridAdapter gridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_task_old);
        findById();
        initIntentDatas();
        initDatas();
        int cols = getResources().getDisplayMetrics().widthPixels / getResources().getDisplayMetrics().densityDpi;
        cols = cols <4 ? 4 : cols;
        gridView.setNumColumns(cols);

        // preview
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String imgs = (String) parent.getItemAtPosition(position);
                if ("000000".equals(imgs)) {
                    PhotoPickerIntent intent = new PhotoPickerIntent(getThisContext());
                    intent.setSelectModel(SelectModel.MULTI);
                    intent.setShowCarema(true); // 是否显示拍照
                    intent.setMaxTotal(9); // 最多选择照片数量，默认为6
                    intent.setSelectedPaths(imagePaths); // 已选中的照片地址， 用于回显选中状态
                    startActivityForResult(intent, REQUEST_CAMERA_CODE);
                } else {
                    PhotoPreviewIntent intent = new PhotoPreviewIntent(getThisContext());
                    intent.setCurrentItem(position);
                    intent.setPhotoPaths(imagePaths);
                    if ("000000".equals(imagePaths.get(imagePaths.size() - 1))){
                        imagePaths.remove(imagePaths.get(imagePaths.size()-1));
                    }
                    startActivityForResult(intent, REQUEST_PREVIEW_CODE);
                }
            }
        });
        imagePaths.add("000000");
        gridAdapter = new GridAdapter(imagePaths);
        gridView.setAdapter(gridAdapter);


    }


    private void findById() {
        findViewById(R.id.back).setOnClickListener(this);
        mContent = (EditText) findViewById(R.id.content);
        mSubmit = (Button) findViewById(R.id.submit);
        mSubmit.setOnClickListener(this);
        mTip = (TextView) findViewById(R.id.tip);
        gridView = (GridView) findViewById(R.id.gridView);
    }

    private void initIntentDatas() {
        mainExpandTo = (ServiceMyWorkTo) getIntent().getSerializableExtra("mode");

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

                pathList=imagePaths;
                if ("000000".equals(pathList.get(pathList.size()-1)))
                    pathList.remove(pathList.size()-1);

                if (pathList!=null&&pathList.size() > 0) {
                    uploaderImage();
                } else {
                    submit();
                    view.setEnabled(false);//将当前触发控件给禁用
                }
                break;
            default:
                break;

        }

    }

    private boolean checking() {

        if (TextUtils.isEmpty(mContent.getText())) {

            Toast.makeText(this, "反馈内容不能为空哦，请输入", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    private void submit() {

        HomeApi api = ApiClient.create(HomeApi.class);
        MyWorkHandleParam param = new MyWorkHandleParam();
        param.setWorkSid(mainExpandTo.getWorkSid());
        param.setProcessDesc(mContent.getText().toString());

        Log.i("222", "submit: "+param.toString());

        if (stringBuilder.toString().endsWith(";")) {
            param.setProcessImage(stringBuilder.toString().substring(0, stringBuilder.toString().lastIndexOf(";")));
        }

        if (dialogFragment == null) {
            dialogFragment = new CustomDialogFragment();
            dialogFragment.show(getSupportFragmentManager(),"");
        }

        api.finishAndFeedWork(param, new HttpCallback<MessageTo<ServiceMyWorkTo>>(this) {
            @Override
            public void success(MessageTo<ServiceMyWorkTo> msg, Response response) {
                dialogFragment.dismissAllowingStateLoss();
                if (msg.getSuccess() == 0) {
                    Intent intent = new Intent(getThisContext(), MyWorkDetailActivity.class);
                    intent.putExtra("sid",msg.getData().getWorkSid());
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("is_submit",true);
                    intent.putExtra("flag","flag_who");
                    //startActivity(intent);
                    setResult(0x123,intent);
                    finish();
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
                            uploadManager.put(TaskUploadImage.getImageUri(pathList.get(i)), UUID.randomUUID().toString(), token, MyWorkFeedBackActivity.this, null);
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
        btnCamera.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                openCamera();
                dialog.dismiss();
            }
        });

        Button btnAlbum = (Button) dialog.findViewById(R.id.btn_album);
        btnAlbum.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

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
        dialog.setCancelable(false);
        dialog.show();


    }

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

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            switch (requestCode) {
                // 选择照片
                case REQUEST_CAMERA_CODE:
                    ArrayList<String> list = data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT);

                    loadAdpater(list);
                    break;
                // 预览
                case REQUEST_PREVIEW_CODE:
                    ArrayList<String> ListExtra = data.getStringArrayListExtra(PhotoPreviewActivity.EXTRA_RESULT);

                    loadAdpater(ListExtra);
                    break;
            }
        }
    }

    private void loadAdpater(ArrayList<String> paths){
        if (imagePaths!=null&& imagePaths.size()>0){
            imagePaths.clear();
        }
        if (paths.contains("000000")){
            paths.remove("000000");
        }
        paths.add("000000");
        imagePaths.addAll(paths);
        gridAdapter  = new GridAdapter(imagePaths);
        gridView.setAdapter(gridAdapter);
        try{
            JSONArray obj = new JSONArray(imagePaths);
            Log.e("--", obj.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
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


    @Override
    public void complete(String s, ResponseInfo responseInfo, JSONObject jsonObject) {
        stringBuilder.append(s);
        stringBuilder.append(";");
        count++;


        if (count == gridView.getChildCount() - (pathList.size()==9?0:1)) {
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
        return MyWorkFeedBackActivity.this;
    }

    @Override
    protected String toPageName() {

        return "任务反馈";
    }


    private class GridAdapter extends BaseAdapter {
        private ArrayList<String> listUrls;
        private LayoutInflater inflater;
        public GridAdapter(ArrayList<String> listUrls) {
            this.listUrls = listUrls;
            if(listUrls.size() == 10){
                listUrls.remove(listUrls.size()-1);
            }
            inflater = LayoutInflater.from(getThisContext());
        }

        public int getCount(){
            return  listUrls.size();
        }
        @Override
        public String getItem(int position) {
            return listUrls.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = inflater.inflate(R.layout.item_image, parent,false);
                holder.image = (ImageView) convertView.findViewById(R.id.imageView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder)convertView.getTag();
            }

            final String path=listUrls.get(position);
            if (path.equals("000000")){
                holder.image.setImageResource(R.drawable.add_ic);
            }else {
                Glide.with(getThisContext())
                        .load(path)
                        .placeholder(R.mipmap.default_error)
                        .error(R.mipmap.default_error)
                        .centerCrop()
                        .crossFade()
                        .into(holder.image);
            }
            return convertView;
        }
        class ViewHolder {
            ImageView image;
        }
    }
}
