package com.joy.property.visit;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.express.ExpressNewTo;
import com.jinyi.ihome.module.express.ExpressRecordTo;
import com.jinyi.ihome.module.express.ReceiveExpressParam;
import com.joy.common.api.ApartmentApi;
import com.joy.common.api.ApiClient;
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
import com.joy.property.inspection.fragment.SubmissionFragment;
import com.joy.property.neighborhood.PostActivity;
import com.joy.property.utils.CustomDialog;
import com.joy.property.utils.NoScrollGridView;
import com.joy.property.visit.adapter.ExpressGridViewAdapter;
import com.joyhome.nacity.app.photo.adapter.GridAdapter;
import com.joyhome.nacity.app.photo.util.Bimp;
import com.joyhome.nacity.app.photo.util.ImageItem;
import com.joyhome.nacity.app.photo.util.PublicWay;
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

/**
 * 拍照领取详情
 * Created by usb on 2017/8/2.
 */

public class PhotoReceiveDetailActivity extends BaseActivity implements View.OnClickListener, UpCompletionHandler {
    private     FlowLayout flowLayout;
    private ImageView  mAdd;
    private String  expressSids="";
    private GridAdapter gridAdapter;
    private ArrayList<String> imagePaths = new ArrayList<>();
    private static final int REQUEST_CAMERA_CODE = 10;
    private static final int REQUEST_PREVIEW_CODE = 20;
    private NoScrollGridView mNoScrollGridView;
    private StringBuffer stringBuffer = new StringBuffer();
    private int count = 0;
    private CustomDialogFragment dialogFragment = null;

    private List<String>       pathList      = new ArrayList<>();
    private List<String>       mList         = new ArrayList<>();
    private List<ExpressNewTo> expressToList = new ArrayList<>();
    protected String apartmentSid ;
    private ExpressGridViewAdapter mAdapter = null;
    private com.joy.property.neighborhood.NoScrollGridView gridView;

    @Override
     protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_receive_detail);
        initView();
        initData();
        setGridView();

        }

    private void initData() {
        apartmentSid=getIntent().getStringExtra("apartmentSid");

        expressToList=  (ArrayList<ExpressNewTo>) getIntent().getSerializableExtra("infoList");
        for (ExpressNewTo expressNewTo:expressToList){
        }
        for (int i = 0; i <expressToList.size() ; i++) {
            if(i==0){
                expressSids=expressSids+expressToList.get(i).getExpressSid();
            }else{
                expressSids=expressSids+","+expressToList.get(i).getExpressSid();
            }
        }
        Log.i("数据", "expressSids: "+expressSids);
        mAdapter = new ExpressGridViewAdapter(getThisContext());
        mAdapter.setList(expressToList);
        mNoScrollGridView.setAdapter(mAdapter);
    }
    private TextView submit;
    private void initView() {
        mNoScrollGridView = (NoScrollGridView) findViewById(R.id.gv_express);
        gridView = (com.joy.property.neighborhood.NoScrollGridView) findViewById(R.id.gridView);
        submit=(TextView)findViewById(R.id.submit);
        submit.setOnClickListener(this);
        findViewById(R.id.back).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.back:
                onBackPressed();
                goToAnimation(2);
                break;
            case R.id.submit:
                pathList=imagePaths;
                Log.i("111111", "pathList: "+pathList.size()+"imagePaths"+imagePaths.size());
                if ("000000".equals(pathList.get(pathList.size() - 1)))
                    pathList.remove(pathList.size()-1);
                for (int i=0;i<pathList.size();i++) {
                    ImageItem takePhoto = new ImageItem();
                    takePhoto.setImagePath(pathList.get(i));
                    takePhoto.setBitmap(Bimp.getBitmap(UploadImage.getImageUri(pathList.get(i))));
                    Bimp.tempSelectBitmap.add(takePhoto);
                }
                if (Bimp.tempSelectBitmap.size() > 0) {
                    submit.setClickable(false);
                    uploadImage();
                } else {
                    imagePaths.add("000000");
                    Toast.makeText(this, "请上传图片", Toast.LENGTH_SHORT).show();
                    return ;
                }

                break;
        }
    }
    public void setGridView(){
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
                    intent.setMaxTotal(4); // 最多选择照片数量，默认为6
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
        gridAdapter = new GridAdapter(this,imagePaths);
        gridView.setAdapter(gridAdapter);
    }
    private void uploadImage() {
        VendorApi api = ApiClient.create(VendorApi.class);
        if (dialogFragment == null) {
            dialogFragment = new CustomDialogFragment();
            dialogFragment.show(getSupportFragmentManager(),"");
        }
        api.getQnToken(new HttpCallback<MessageTo<String>>(this) {
            @Override
            public void success(MessageTo<String> msg, Response response) {
                dialogFragment.dismissAllowingStateLoss();
                if (msg.getSuccess() == 0) {
                    String token = msg.getData();
                    UploadManager uploadManager = new UploadManager();
//                        for (int i = 0; i < pathList.size(); i++) {
//                            uploadManager.put(TaskUploadImage.getImageUri(pathList.get(i)), UUID.randomUUID().toString(), token, PhotoReceiveDetailActivity.this, null);
//                        }
                        for (ImageItem item : Bimp.tempSelectBitmap) {
                            uploadManager.put(Bimp.getImageUri(item.getImagePath(), false, true),  UUID.randomUUID().toString(), token, PhotoReceiveDetailActivity.this, null);

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

        gridAdapter  = new GridAdapter(this,imagePaths);
        gridView.setAdapter(gridAdapter);
        try{
            JSONArray obj = new JSONArray(imagePaths);
            Log.e("--", obj.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void complete(String s, ResponseInfo responseInfo, JSONObject jsonObject) {

        stringBuffer.append(s + ",");
        count++;
        if (count == Bimp.tempSelectBitmap.size()) {
            submitData();
        }
    }
//领取快递
    private void submitData() {

            ApartmentApi api = ApiClient.create(ApartmentApi.class);
        if (dialogFragment == null) {
            dialogFragment = new CustomDialogFragment();
            dialogFragment.show(getSupportFragmentManager(),"");
        }
            ReceiveExpressParam param=new ReceiveExpressParam();
            param.setExpressSids(expressSids);
        param.setHandleUserSid(mUserHelper.getSid());
        if (stringBuffer.toString().endsWith(",")) {
            param.setReceivePhoto(stringBuffer.toString().substring(0, stringBuffer.toString().lastIndexOf(",")));
        }
        Log.i("数据", "param: "+param.toString());
            api.receiveExpress(param, new HttpCallback<MessageTo>(getThisContext()) {
                        @Override
                        public void success(MessageTo msg, Response response) {
                            Log.i("数据", "success: "+msg.toString());
                            submit.setClickable(true);
                            dialogFragment.dismissAllowingStateLoss();
                            if (msg.getSuccess() == 0) {
                                Log.i("数据", "success: ");
                                Intent intent = new Intent(getThisContext(), PhotoReceiveExpressActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent.putExtra("apartmentSid",apartmentSid);
                                startActivity(intent);
                                Bimp.tempSelectBitmap.clear();
                                finish();
                                ToastShowLong(PhotoReceiveDetailActivity.this,"领取成功！");

                            } else {
                                Toast.makeText(getThisContext(),
                                        msg.getMsg(), Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void failure(RetrofitError error) {
                            dialogFragment.dismissAllowingStateLoss();
                            submit.setClickable(true);
                            Log.i("数据", "failure: "+error.toString());
                            super.failure(error);
                        }
                    }
            );
        }



    @Override
    public void onBackPressed() {


        super.onBackPressed();
        Bimp.tempSelectBitmap.clear();

    }

}
