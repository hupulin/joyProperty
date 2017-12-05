package com.joy.property.neighborhood;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.Util.ActivityBarColorUtil;
import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.neighbor.NeighborPostParam;
import com.jinyi.ihome.module.neighbor.NeighborPostTo;
import com.jinyi.ihome.module.neighbor.NeighborPostTypeTo;
import com.joy.common.api.ApiClient;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.NeighborApi;
import com.joy.common.api.VendorApi;
import com.joy.common.widget.UploadImage;
import com.joy.library.utils.ConfigUtil;
import com.joy.property.R;
import com.joy.property.constant.Constant;
import com.joy.property.neighborhood.adapter.GridViews;
import com.joy.property.neighborhood.adapter.KeyboardListenRelativeLayout;
import com.joy.property.utils.ACache;
import com.joy.property.utils.SpUtil;
import com.joy.property.utils.StatisticsUtil;
import com.joyhome.nacity.app.MainApp;
import com.joyhome.nacity.app.base.BaseActivity;

import com.joyhome.nacity.app.photo.GalleryActivity;
import com.joyhome.nacity.app.photo.NeighborAlbumActivity;
import com.joyhome.nacity.app.photo.util.Bimp;
import com.joyhome.nacity.app.photo.util.ImageItem;

import com.joyhome.nacity.app.photo.util.PublicWay;
import com.joyhome.nacity.app.util.CustomDialogFragment;
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
import java.util.List;
import java.util.UUID;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Admin on 2014-11-25
 */

public class PostActivity extends BaseActivity
        implements OnClickListener, UpCompletionHandler {


    private int selectPosition;
    private EditText mPostContent;
    private FlowLayout flLabel;
    private String postType;
    private StringBuilder str = new StringBuilder();
    private int count = 0;
    private CustomDialogFragment dialogFragment = null;
    //private NoScrollGridView noScrollGridView;
    private GridAdapter adapter;
    public static Bitmap bitmap = null;
    private String photoPath = "";
    private boolean isFristRefresh;
    private int myPosition=0;
    private List<NeighborPostTypeTo> typeList=new ArrayList<>();
    private int WRITE_EXTERNAL_STORAGE_REQUEST_CODE;
    private com.joy.library.utils.CustomDialog dialog;
    private TextView textCount;
    private Thread thread;
    private Dialog mdialog;
    private ImageView dailog_close;
    private LinearLayout selectType;
    private TextView typeName;
    private Dialog mDialog;
    private InputMethodManager manager;
    private boolean isSelect;
    private boolean keyBoardShow;
    private NoScrollGridView gridView;
    private static final int REQUEST_CAMERA_CODE = 10;
    private static final int REQUEST_PREVIEW_CODE = 20;
    private ArrayList<String> imagePaths = new ArrayList<>();
    private ImageCaptureManager captureManager; // 相机拍照处理类
    private GridAdapter gridAdapter;
    private List<String> pathList = new ArrayList<>();
    private Button mPostSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.post_add_pic_ic);
        setContentView(R.layout.activity_post);

        if(Bimp.tempSelectBitmap.size()>0){

            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        }
        SpUtil.put(getThisContext(), "MyPosition", myPosition);
        // LoaderLabelData.getInstance(this.getApplication());
        findById();
        initDatas();
        setData();
        manager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        setGridView();
        setKeyBoard();
    }




    private void findById() {
        findViewById(R.id.back).setOnClickListener(this);
        mPostSubmit = (Button) findViewById(R.id.post_submit);
        mPostSubmit.setOnClickListener(this);
        textCount = (TextView) findViewById(R.id.textCount);
        mPostContent = (EditText) findViewById(R.id.post_content);
        mPostContent.setMinHeight((int) (getScreenWidth() * 0.2861));

        flLabel = (FlowLayout)findViewById(R.id.fl_label);
        gridView = (NoScrollGridView) findViewById(R.id.gridView);

        String mStr = ConfigUtil.getString(sp, MainApp.KeyValue.KEY_POST_CONTENT, "");
        if (!TextUtils.isEmpty(mStr)) {
            mPostContent.setText(mStr);
            textCount.setText(mStr.length()+"/500");
        }

        selectType = (LinearLayout) findViewById(R.id.selectType);
        selectType.setOnClickListener(this);


        typeName = (TextView) findViewById(R.id.typeName);
    }


    private void initDatas() {

       postType=SpUtil.getString(getThisContext(),"postType");
        typeName.setText(SpUtil.getString(getThisContext(),"typeNameNeighbor"));
//        System.out.println(myPosition+"position-------------------");
//       typeList = LoaderLabelData.getInstance(this).getTypeList();
//        if (typeList.size() > 0) {
//            System.out.println(myPosition+"position");
//           // position=Integer.valueOf(getIntent().getStringExtra("neighbor_typeName"))-11;
//            postType = typeList.get(SpUtil.getInt(getThisContext(),"MyPosition")).getTypeSid();
//            Toast.makeText(getThisContext(),postType+"",Toast.LENGTH_LONG).show();
//            final SparseArray<Button> ids = new SparseArray<>();
//            for (int i = 0; i < typeList.size(); i++) {
//                FlowLayout.LayoutParams params = new FlowLayout.LayoutParams(FlowLayout.LayoutParams.WRAP_CONTENT, 110);
//                final Button label = new Button(PostActivity.this);
//                label.setText(typeList.get(i).getTypeName());
//                params.leftMargin = 30;
//                params.topMargin = 28;
//                label.setTextColor(getResources().getColor(R.color.post_label_color));
//                if (i == 0) {
//                    label.setBackgroundResource(R.drawable.label_selected_ic);
//                } else {
//                    label.setBackgroundResource(R.drawable.label_ic);
//                }
//
//                ids.put(i, label);
//                label.setId(i);
//                label.setTag(typeList.get(i));
//                flLabel.addView(label, params);
//
//                label.setOnClickListener(new OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        NeighborPostTypeTo typeTo = (NeighborPostTypeTo) v.getTag();
//                        postType = typeTo.getTypeSid();
//                        for (int j = 0; j < ids.size(); j++) {
//                            Button button = ids.get(j);
//                            button.setBackgroundResource(R.drawable.label_ic);
//                            if (j == v.getId()) {
//                                button.setBackgroundResource(R.drawable.label_selected_ic);
//                            }
//                        }
//                    }
//                });
//            }
//        }
        if (!TextUtils.isEmpty(SpUtil.getString(getThisContext(), "TopicSid"))) {
            typeName.setText("话题");
            selectType.setVisibility(View.GONE);
        }
    }

    private void setData() {
        mPostContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                textCount.setText(s.length() + "/500");

                ConfigUtil.saveString(sp, MainApp.KeyValue.KEY_POST_CONTENT, mPostContent.getText().toString());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //  noScrollGridView.setSelector(new ColorDrawable(Color.TRANSPARENT));

        mPostContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() >= 499) {
                    ToastShowLong(getThisContext(), "字数不能超过500个字");

                }
                ConfigUtil.saveString(sp, MainApp.KeyValue.KEY_POST_CONTENT, mPostContent.getText().toString());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                //                Bimp.tempSelectBitmap.clear();
//                if (PublicWay.activityList!=null&&PublicWay.activityList.size()>0) {
//                    for (Activity activity : PublicWay.activityList) {
//                        activity.finish();
//                    }
//                }
                System.out.println( android.os.Build.MODEL+"mode---");
           if (keyBoardShow) {
                    InputMethodManager imms = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    imms.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                       return;
                }
//                finish();
//                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                onBackPressed();
                break;

            case R.id.post_submit:

                if (checking())
                    return;
                pathList=imagePaths;
                if ("000000".equals(pathList.get(pathList.size() - 1)))
                    pathList.remove(pathList.size()-1);
                for (int i=0;i<pathList.size();i++) {
                    ImageItem takePhoto = new ImageItem();
                    takePhoto.setImagePath(pathList.get(i));
                    takePhoto.setBitmap(Bimp.getBitmap(UploadImage.getImageUri(pathList.get(i))));
                    Bimp.tempSelectBitmap.add(takePhoto);
                }
                mPostSubmit.setClickable(false);
                if (Bimp.tempSelectBitmap.size() > 0) {
                    pictureSubmit();
                } else {
                    postSubmit();
                }

                break;
            case R.id.iv_neighbor_dailog_close:
                ScaleAnimation mAnimation=new ScaleAnimation(1f, 1.2f, 1f, 1.2f,
                        Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                mAnimation.setDuration(300);
                dailog_close.startAnimation(mAnimation);
                mDialog.dismiss();
                break;
            case R.id.selectType:
                showSelectType();
                break;
        }
    }

    private void pictureSubmit() {
        VendorApi api = ApiClient.create(VendorApi.class);
        dialogFragment = new CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "");
        api.getQnToken(new HttpCallback<MessageTo<String>>(this) {
            @Override
            public void success(MessageTo<String> msg, Response response) {
                if (msg.getSuccess() == 0) {
                    String token = msg.getData();
                    UploadManager uploadManager = new UploadManager();
                    new Thread(() -> {
                        for (ImageItem item : Bimp.tempSelectBitmap) {
                            String mStr = UUID.randomUUID().toString();
                            str.append(mStr);
                            str.append(";");

                            uploadManager.put(Bimp.getImageUri(item.getImagePath(), false, true), mStr, token, PostActivity.this, null);
                            //System.out.println(Bimp.getImageUri(item.getImagePath())+"url");
                        }
                    }).start();

                }
            }

            @Override
            public void failure(RetrofitError error) {
                dialogFragment.dismissAllowingStateLoss();
                super.failure(error);

            }
        });
    }

    private void submissionShowDialog() {
        dialog = new com.joy.library.utils.CustomDialog(this, R.layout.dialog_pic_type, R.style.myDialogTheme);
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

//                getPerssion();
                photo();


            }
        });

        Button btnAlbum = (Button) dialog.findViewById(R.id.btn_album);
        btnAlbum.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
//               requestPermission(1, Manifest.permission.READ_EXTERNAL_STORAGE, new Runnable() {
//                    @Override
//                    public void run() {
//
//                    }
//                }, new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(PostActivity.this, "权限被拒绝", Toast.LENGTH_SHORT).show();
//                    }
//                });
                ConfigUtil.saveString(sp, MainApp.KeyValue.KEY_POST_CONTENT, mPostContent.getText().toString());

                Intent intent = new Intent(getThisContext(), NeighborAlbumActivity.class);
                intent.putExtra("number", 9);
                intent.putExtra("flag", "chat");

                startActivityForResult(intent, Constant.RESULT_SDCARD);
                new Handler().removeCallbacks(thread);
                overridePendingTransition(R.anim.activity_translate_in, R.anim.activity_translate_out);

                dialog.dismiss();
            }
        });
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();

    }





    protected void onRestart() {

        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private static final int TAKE_PICTURE = 0x000001;

    public void photo() {
        Log.i("1212", "photo: 打开相机");
        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File mPhotoFile = new File(MainApp.getCacheImagePath() + "/joyhome/");
        if (!mPhotoFile.exists())
            mPhotoFile.mkdir();
        String fileName = String.valueOf(System.currentTimeMillis());
        File mOutPhotoFile = new File(MainApp.getCacheImagePath() + "/joyhome/",
                fileName + ".jpg");
        photoPath = mOutPhotoFile.getAbsolutePath();
        Uri uri = Uri.fromFile(mOutPhotoFile);
        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(openCameraIntent, TAKE_PICTURE);
    }




    /**
     * 验证
     *
     * @return
     */
    private boolean checking() {
        if (TextUtils.isEmpty(mPostContent.getText())) {
            Toast.makeText(this, "请输入发帖内容！", Toast.LENGTH_LONG).show();
            return true;
        }
        return false;
    }


    /**
     * 发布
     */
    private void postSubmit() {
        NeighborPostParam param = new NeighborPostParam();
        if (postType==null) {
            typeList.clear();
            if(JSON.parseArray(new ACache().getAsString("typeList"), NeighborPostTypeTo.class)!=null)
                typeList=JSON.parseArray(new ACache().getAsString("typeList"),NeighborPostTypeTo.class);
            postType = typeList.get(0).getTypeSid();
        }
        if (TextUtils.isEmpty(mHelper.getSid()))
            param.setApartmentSid("30e1baa2-877f-48c8-92be-67d026752039");
        else
        param.setApartmentSid(mHelper.getSid());
        param.setPostContent(mPostContent.getText().toString());
        param.setPostOwner(mUserHelper.getSid());

        param.setPostType(postType);
        if (str.toString().endsWith(";")) {
            param.setPostImages(str.toString().substring(0, str.toString().lastIndexOf(";")));

        }


        if (!TextUtils.isEmpty(SpUtil.getString(getThisContext(), "TopicSid"))) {
            param.setRefId(SpUtil.getString(getThisContext(), "TopicSid"));
            param.setPostType("18");

            typeName.setText("话题");
            param.setTopicTitle(SpUtil.getString(getThisContext(), "topicTitle"));
            selectType.setVisibility(View.GONE);
        }

        NeighborApi api = ApiClient.create(NeighborApi.class);
        if (dialogFragment == null) {
            dialogFragment = new CustomDialogFragment();
            dialogFragment.show(getSupportFragmentManager(), "");
        }
        System.out.println(param);
        api.addNeighborPost(param, new HttpCallback<MessageTo<NeighborPostTo>>(this) {
            @Override
            public void success(MessageTo<NeighborPostTo> msg, Response response) {
                dialogFragment.dismissAllowingStateLoss();
                System.out.println(msg + "msg----");
                mPostSubmit.setClickable(true);
                if (msg.getSuccess() == 0) {
                    for (Activity activity : PublicWay.activityList) {
                        activity.finish();
                    }
//                    if (bitmap != null) {
//                        bitmap.recycle();
//                        bitmap = null;
//                    }
                    ConfigUtil.saveString(sp, MainApp.KeyValue.KEY_POST_CONTENT, "");
                    if (TextUtils.isEmpty(SpUtil.getString(getThisContext(), "TopicSid"))) {
                        Intent intent = new Intent(getThisContext(), NeighborActivity.class);
                      intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("BACKPOSTTYPE", postType);

                        Bimp.tempSelectBitmap.clear();
                        startActivity(intent);
                        onBackPressed();
                        finish();
                    }else {
                        Intent intent=new Intent(getThisContext(),TopicActivity.class);
                        intent.putExtra("interactionSid", SpUtil.getString(getThisContext(), "TopicSid"));
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                        Bimp.tempSelectBitmap.clear();
                        finish();
                        startActivity(intent);

                    }
                } else {
                    Toast.makeText(getThisContext(), msg.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                dialogFragment.dismissAllowingStateLoss();
                mPostSubmit.setClickable(true);
                super.failure(error);
            }
        });


    }

    @Override
    public void complete(String s, ResponseInfo responseInfo, JSONObject jsonObject) {


        count++;

        if (count == Bimp.tempSelectBitmap.size()) {
            postSubmit();
        }

    }

    @Override
    public void onBackPressed() {


        super.onBackPressed();
        Bimp.tempSelectBitmap.clear();
        for (Activity activity : PublicWay.activityList) {
            activity.finish();
        }
    }

    @Override
    protected Context getThisContext() {
        return PostActivity.this;
    }

    @Override
    protected String toPageName() {
        return "邻居圈发帖";
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        doNext(requestCode, grantResults);
    }
    private void doNext(int requestCode, int[] grantResults) {
        if (requestCode == WRITE_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getApplicationContext(),"接受",Toast.LENGTH_LONG).show();
                photo();
                dialog.dismiss();
            } else {
                Toast.makeText(getApplicationContext(),"拒绝",Toast.LENGTH_LONG).show();

                dialog.dismiss();
            }
        }
    }
//    public void getPerssion(){
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
//                != PackageManager.PERMISSION_GRANTED) {
//            //申请WRITE_EXTERNAL_STORAGE权限
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
//                    WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
//        }
//    }
    public View newView(int position){
        View  convertView = View.inflate(getThisContext(),R.layout.item_published_grida, null);
        ImageView imageView= (ImageView) convertView.findViewById(R.id.item_grid_image);
        ImageView deleteImage= (ImageView) convertView.findViewById(R.id.deleteImage);
        convertView.setLayoutParams(getLayoutParams());
        convertView.setTag(position);
        if (position == Bimp.tempSelectBitmap.size()) {
            imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.post_add_pic_ic));
            if (position == 9) {
                imageView.setVisibility(View.GONE);
            }
        } else {
            imageView.setImageBitmap(Bimp.tempSelectBitmap.get(position).getBitmap());
            deleteImage.setOnClickListener(v -> {
                Bimp.tempSelectBitmap.remove(position);
              //  noScrollGridView.removeViewAt(position);
            });

        }

        return convertView;
    }
    public GridLayout.LayoutParams getLayoutParams() {

        int mScreenWidth =getScreenWidthPixels(getApplication());

        int mWidth = (mScreenWidth - 140) / 4;
        GridLayout.LayoutParams params=new GridLayout.LayoutParams();
        params.height=(int)(mScreenWidth*0.2069);
        params.width=(int)(mScreenWidth*0.2069);
        int margin=(int)(mScreenWidth*0.0277);
        params.setMargins(0, margin, margin, 0);
        return params;
    }
//    OnClickListener ocl = new OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            if(dsglOicl !=null){
//                for(int i=0;i<getChildCount();i++)
//                    if(v==getChildAt(i))
//                        typeSid=recordItems.get(i).getTypeSid();
//                dsglOicl.onItemClick( v, typeSid);
//            }
//
//        }
//    };
    public void showSelectType(){
        if (keyBoardShow) {
            manager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        }
       isSelect=true;
        if (!keyBoardShow){
            mDialog = new Dialog(PostActivity.this, R.style.EditDialog);
            mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            View dialogView = View.inflate(PostActivity.this, R.layout.neighbor_post_dailog, null);
            GridViews mGridView = (GridViews) dialogView.findViewById(R.id.gv_neighbor);
            View divideView = dialogView.findViewById(R.id.divideView);
            mGridView.setAllowDrag(true);
            typeList.clear();
            if (JSON.parseArray(new ACache().getAsString("typeList"), NeighborPostTypeTo.class) != null)
                typeList = JSON.parseArray(new ACache().getAsString("typeList"), NeighborPostTypeTo.class);
            if (typeList.size() > 6)
                divideView.setVisibility(View.GONE);
            mGridView.setItems(typeList);
            dailog_close = (ImageView) dialogView.findViewById(R.id.iv_neighbor_dailog_close);
            dailog_close.setOnClickListener(PostActivity.this);
            mDialog.setContentView(dialogView);
            mGridView.setOnItemClickListener((v1, text, type) -> {
                SpUtil.put(getThisContext(), "Neighbor_TypeSid", text);
                SpUtil.put(getThisContext(), "postType", text);
                postType=text;
                SpUtil.put(getThisContext(), "typeNameNeighbor", type);
                typeName.setText(type);
                StatisticsUtil.sendStatistics(mUserHelper.getSid(), "邻居圈发帖-" + text,getThisContext());
                mDialog.dismiss();

            });
            WindowManager.LayoutParams layoutParams = mDialog.getWindow().getAttributes();
            // 指定高度
            layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
            // // 指定对齐方法
            layoutParams.gravity = Gravity.TOP;
            mDialog.show();
            isSelect=false;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode== KeyEvent.KEYCODE_BACK)
            onBackPressed();
        return super.onKeyDown(keyCode, event);
    }
    private void setKeyBoard() {
        KeyboardListenRelativeLayout relativeLayout = (KeyboardListenRelativeLayout) findViewById(R.id.keyboardRelativeLayout);
        relativeLayout.setOnKeyboardStateChangedListener(new KeyboardListenRelativeLayout.IOnKeyboardStateChangedListener() {

            public void onKeyboardStateChanged(int state) {
                switch (state) {
                    case KeyboardListenRelativeLayout.KEYBOARD_STATE_HIDE://软键盘隐藏
                        keyBoardShow=false;
                        if (isSelect) {
                            mDialog = new Dialog(PostActivity.this, R.style.EditDialog);
                            mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            View dialogView = View.inflate(PostActivity.this, R.layout.neighbor_post_dailog, null);
                            GridViews mGridView = (GridViews) dialogView.findViewById(R.id.gv_neighbor);
                            View divideView = dialogView.findViewById(R.id.divideView);
                            mGridView.setAllowDrag(true);
                            typeList.clear();
                            if (JSON.parseArray(new ACache().getAsString("typeList"), NeighborPostTypeTo.class) != null)
                                typeList = JSON.parseArray(new ACache().getAsString("typeList"), NeighborPostTypeTo.class);
                            if (typeList.size() > 6)
                                divideView.setVisibility(View.GONE);
                            mGridView.setItems(typeList);
                            dailog_close = (ImageView) dialogView.findViewById(R.id.iv_neighbor_dailog_close);
                            dailog_close.setOnClickListener(PostActivity.this);
                            mDialog.setContentView(dialogView);
                            mGridView.setOnItemClickListener((v1, text, type) -> {
                                SpUtil.put(getThisContext(), "Neighbor_TypeSid", text);
                                SpUtil.put(getThisContext(), "postType", text);
                                SpUtil.put(getThisContext(), "typeNameNeighbor", type);
                                typeName.setText(type);
                                postType=text;
                                StatisticsUtil.sendStatistics(mUserHelper.getSid(), "邻居圈发帖-" + text,getThisContext());
                                mDialog.dismiss();

                            });
                            WindowManager.LayoutParams layoutParams = mDialog.getWindow().getAttributes();
                            // 指定高度
                            layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
                            // // 指定对齐方法
                            layoutParams.gravity = Gravity.TOP;
                            mDialog.show();
                            isSelect=false;
                        }
                        break;
                    case KeyboardListenRelativeLayout.KEYBOARD_STATE_SHOW:
                 keyBoardShow=true;
                        break;
                    default:
                        break;
                }
            }
        });
    }
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        // TODO Auto-generated method stub
//        if(event.getAction() == MotionEvent.ACTION_DOWN){
//            if(getCurrentFocus()!=null && getCurrentFocus().getWindowToken()!=null){
//                manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
//            }
//        }
//        return super.onTouchEvent(event);
//    }


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
                holder.image.setImageResource(R.drawable.post_add_pic_ic);
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
}
