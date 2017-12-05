package com.joy.property.visit;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.apartment.ApartmentInfoTo;
import com.jinyi.ihome.module.express.CheckExpressParam;
import com.jinyi.ihome.module.express.CompanyTo;
import com.jinyi.ihome.module.express.ExpressNewTo;
import com.jinyi.ihome.module.express.ExpressParam;
import com.jinyi.ihome.module.express.ExpressToNew;
import com.jinyi.ihome.module.newshop.ExpressName;
import com.joy.common.api.ApartmentApi;
import com.joy.common.api.ApiClient;
import com.joy.common.api.ExpressApi;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.VendorApi;
import com.joy.common.widget.TaskUploadImage;
import com.joy.common.widget.UploadImage;
import com.joy.library.fragment.CustomDialogFragment;
import com.joy.library.utils.DateUtil;
import com.joy.library.utils.FileUtil;
import com.joy.property.MainActivity;
import com.joy.property.MainApplication;
import com.joy.property.R;
import com.joy.property.base.BaseActivity;
import com.joy.property.common.ShowImageActivity;
import com.joy.property.constant.Constant;
import com.joy.property.inspection.adapter.ApartmentAdapter;
import com.joy.property.neighborhood.NoScrollGridView;
import com.joy.property.utils.CustomDialog;

import com.joy.property.visit.adapter.ExpressNameAdapter;
import com.joy.property.visit.decoding.Intents;
import com.joy.property.visit.view.ExpressGridView;
import com.joy.property.visit.view.MyImageView;
import com.joyhome.nacity.app.photo.adapter.GridAdapter;
import com.joyhome.nacity.app.photo.util.Bimp;
import com.joyhome.nacity.app.photo.util.ImageItem;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by usb on 2017/6/5.
 */

public class ExpressActivity extends BaseActivity implements View.OnClickListener,UpCompletionHandler {
    private String                           codeNo;
    private EditText                         mCodeNo;
    private TextView                         apartmentName;
    private EditText                         name;
    private EditText                         phoneNo;
    private ApartmentInfoTo                  apartment;
    private CompanyTo                        companyTo;
    private String         expressCompany;
    private StringBuffer stringBuffer = new StringBuffer();
    private GridAdapter gridAdapter;
    private ArrayList<String> imagePaths = new ArrayList<>();

    private int count = 0;
    private ImageView                        mAdd;
    private List<String> mList = new ArrayList<>();
    private CustomDialogFragment dialogFragment = null;
    private List<String> pathList = new ArrayList<>();
    private ExpressGridView dragView;
    private static final int REQUEST_CAMERA_CODE = 10;
    private static final int REQUEST_PREVIEW_CODE = 20;
    private NoScrollGridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_express);
        findById();
        initView();
        if(isScan){
            checkExpressStatus(codeNo);
        }
        getExpressName();

        setGridView();
    }

    private void checkExpressStatus(String expressNo) {

        ApartmentApi api = ApiClient.create(ApartmentApi.class);
        CheckExpressParam param=new CheckExpressParam();
        param.setExpressNo(expressNo);
        api.checkExpressStatus(param,new HttpCallback<MessageTo<Integer>>(this) {
            @Override
            public void success(MessageTo<Integer> msg, Response response) {
                dialogFragment.dismissAllowingStateLoss();
                if (msg.getSuccess() == 0) {
                    //if(msg.getData()==0){
                    //
                    //}
                    if(msg.getData()==1){
                            warnDialogShow(isScan);
                    }
                }
                else{
                    Toast.makeText(getThisContext(), msg.getMsg(), Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void failure(RetrofitError error) {
                dialogFragment.dismissAllowingStateLoss();
                super.failure(error);
            }
        });
    }



    public void inPutExpress(){
        mPostSubmit.setClickable(false);
        pathList.addAll(imagePaths);
//        pathList=imagePaths;
        if ("000000".equals(imagePaths.get(imagePaths.size() - 1)))
            pathList.remove(pathList.size()-1);

        for (int i=0;i<pathList.size();i++) {
            ImageItem takePhoto = new ImageItem();
            takePhoto.setImagePath(pathList.get(i));
            takePhoto.setBitmap(Bimp.getBitmap(UploadImage.getImageUri(pathList.get(i))));
            Bimp.tempSelectBitmap.add(takePhoto);
        }

        if (Bimp.tempSelectBitmap.size() > 0) {
            uploadImage();

        } else {
            Log.i("222", "onClick: 33333");
            submit();
        }
    }
//    public void setGridView(){
//        int cols = getResources().getDisplayMetrics().widthPixels / getResources().getDisplayMetrics().densityDpi;
//        cols = cols <4 ? 4 : cols;
//        gridView.setNumColumns(cols);
//        // preview
//        gridView.setOnItemClickListener((parent, view, position, id) -> {
//            String imgs = (String) parent.getItemAtPosition(position);
//            if ("000000".equals(imgs)) {
//                PhotoPickerIntent intent = new PhotoPickerIntent(getThisContext());
//                intent.setSelectModel(SelectModel.MULTI);
//                intent.setShowCarema(true); // 是否显示拍照
//                intent.setMaxTotal(4); // 最多选择照片数量，默认为6
//                intent.setSelectedPaths(imagePaths); // 已选中的照片地址， 用于回显选中状态
//                startActivityForResult(intent, REQUEST_CAMERA_CODE);
//            } else {
//                PhotoPreviewIntent intent = new PhotoPreviewIntent(getThisContext());
//                intent.setCurrentItem(position);
//                intent.setPhotoPaths(imagePaths);
//                if ("000000".equals(imagePaths.get(imagePaths.size() - 1))){
//                    imagePaths.remove(imagePaths.get(imagePaths.size()-1));
//                    startActivityForResult(intent, REQUEST_PREVIEW_CODE);
//                }
//            }
//        });
//        imagePaths.add("000000");
//        gridAdapter = new GridAdapter(this,imagePaths);
//        gridView.setAdapter(gridAdapter);
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
    private void getExpressName() {
        if (dialogFragment == null) {
            dialogFragment = new CustomDialogFragment();
        }
        dialogFragment.show(getSupportFragmentManager(),"");
        ApartmentApi api = ApiClient.create(ApartmentApi.class);
        api.findExpressCompany(new HttpCallback<MessageTo<List<CompanyTo>>>(this) {
            @Override
            public void success(MessageTo<List<CompanyTo>> msg, Response response) {
                dialogFragment.dismissAllowingStateLoss();
                if (msg.getSuccess() == 0) {
                    final List<CompanyTo> infoList = new ArrayList<>();
                    infoList.addAll(msg.getData());
                    for (int i = 0; i <infoList.size() ; i++) {
                      viewInfos.add(infoList.get(i).getCompanyName());
                        Log.i("3333", "success: "+  infoList.get(i).getCompanyName());

                    }
                    dragView.setItems(viewInfos);
                    dragView.setOnItemClickListener((v, text) -> {
                        if (text.equals("更  多")) {
                            dragView.setVisible();
                            for (int i = 0; i < dragView.getChildCount(); i++) {
                                TextView tvOther=  (TextView)dragView.getChildAt(i).findViewById(R.id.textView);
                                Log.i("2222", "success:111111111 "+expressCompany);
                                Log.i("2222", "success:222222222 "+tvOther.getText());
                                if(tvOther.getText().equals(expressCompany)){
                                    tvOther.setTextColor(Color.parseColor("#ffffff"));
                                    tvOther.setBackgroundResource(R.drawable.express_bg_press);
                                }
                            }
                        } else if (text.equals("收  起")) {
                            dragView.setInVisible();
                                TextView tvOther=  (TextView)dragView.getChildAt(8).findViewById(R.id.textView);
                                tvOther.setTextColor(Color.parseColor("#aaaaaa"));
                                tvOther.setBackgroundResource(R.drawable.express_bg_unpress);
                        } else {
                            for (int i = 0; i < dragView.getChildCount(); i++) {
                                TextView tvOther=  (TextView)dragView.getChildAt(i).findViewById(R.id.textView);
                                tvOther.setTextColor(Color.parseColor("#aaaaaa"));
                                tvOther.setBackgroundResource(R.drawable.express_bg_unpress);
                            }
                            TextView tv= (TextView)v.findViewById(R.id.textView);
                            tv.setTextColor(Color.parseColor("#ffffff"));
                            tv.setBackgroundResource(R.drawable.express_bg_press);
                            expressCompany=text;
                        }


                    });
                  }
                else{
//                    Toast.makeText(getThisContext(),
//                            msg.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void failure(RetrofitError error) {
                dialogFragment.dismissAllowingStateLoss();
                super.failure(error);
            }
        });
    }
    private TextView mPostSubmit;


    private List<String> viewInfos=new ArrayList<>();
    private void findById() {
        dragView = (ExpressGridView) findViewById(R.id.dragView);
        codeNo=getIntent().getStringExtra("codeNo");
        findViewById(R.id.back).setOnClickListener(this);
        mCodeNo=(EditText) findViewById(R.id.codeNo);
        apartmentName=(TextView) findViewById(R.id.tv_apartment_name);
//        expressName=(TextView) findViewById(R.id.express_company);
        phoneNo=(EditText) findViewById(R.id.phoneNo);
        name=(EditText) findViewById(R.id.name);
        apartmentName.setOnClickListener(this);
        gridView = (NoScrollGridView) findViewById(R.id.gridView);
        mPostSubmit=(TextView)findViewById(R.id.manual_input);
        mPostSubmit.setOnClickListener(this);
        findViewById(R.id.record).setOnClickListener(this);
        mCodeNo.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    EditText v1=(EditText)v;
                    checkExpressStatus(v1.getText().toString());
                Log.e("输入完点击确认执行该方法", "输入结束1"+hasFocus);}
            }});
    }
    private boolean isScan;
    private void initView() {
        if(codeNo!=null){
            isScan=true;
            mCodeNo.setText(codeNo);
            mCodeNo.setFocusable(false);
            mCodeNo.setEnabled(false);
        }else{
            isScan=false;
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                onBackPressed();
                goToAnimation(2);
                break;
            case R.id.tv_apartment_name:
                selectApartmentDialog();
                break;
//            case R.id.express_company:
//                selectExpressDialog();
//                break;
            case R.id.manual_input:
                Log.i("kuaidi", "onClick: "+expressCompany);
                if (checking())
                    return;
                inPutExpress();

                break;
            case R.id.record:
                Intent intent =new Intent(getThisContext(),ExpressRecordActivity.class);
                startActivity(intent);
                goToAnimation(1);
                break;
        }

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
                Log.i("222", "onClick:2222 ");

                dialogFragment.dismissAllowingStateLoss();
                if (msg.getSuccess() == 0) {
                    String token = msg.getData();
                    UploadManager uploadManager = new UploadManager();
                    for (ImageItem item : Bimp.tempSelectBitmap) {
                        uploadManager.put(Bimp.getImageUri(item.getImagePath(), false, true),  UUID.randomUUID().toString(), token, ExpressActivity.this, null);
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
//第一步
    private void submit() {
        ExpressApi api = ApiClient.create(ExpressApi.class);
        ExpressParam param = new ExpressParam();
        param.setApartmentSid(apartment.getApartmentSid());
        param.setUserSid(mUserHelper.getSid());
        param.setExpressCompany(expressCompany);
        param.setExpressPhone(phoneNo.getText().toString());
        param.setExpressNo(mCodeNo.getText().toString());
        param.setUserName(name.getText().toString());
        if (stringBuffer.toString().endsWith(";")) {
            param.setExpressImages(stringBuffer.toString().substring(0, stringBuffer.toString().lastIndexOf(";")));
        }
        Log.i("2222", "submit: "+param.toString());
        if (dialogFragment == null) {

            dialogFragment = new CustomDialogFragment();
        }
        dialogFragment.show(getSupportFragmentManager(),"");

        mPostSubmit.setClickable(false);

        api.addExpressInfoNew(param, new HttpCallback<MessageTo<ExpressToNew>>(getThisContext()) {
            @Override
            public void success(MessageTo<ExpressToNew> msg, Response response) {
                stringBuffer.delete(0,stringBuffer.length());
                mPostSubmit.setClickable(true);
                Bimp.tempSelectBitmap.clear();
                dialogFragment.dismissAllowingStateLoss();
                if (msg.getSuccess() == 0) {
                    Log.i("1111", "success: 1"+msg.getData().toString());
                    finishDialogShow();
//                    updateExpress(msg.getData().getExpressSid());
                }else {
                    ToastShowLong(getThisContext(),msg.getMessage());
                }
            }

            @Override
            public void failure(RetrofitError error) {
                stringBuffer.delete(0,stringBuffer.length()-1);
                mPostSubmit.setClickable(true);
                dialogFragment.dismissAllowingStateLoss();
                Log.i("1111", "error: 1"+error.toString());
                super.failure(error);
            }

        });
    }
    private boolean checking() {

        // 判断快递单号 手机号码 快递公司 小区项目
        if(TextUtils.isEmpty(mCodeNo.getText())){
           ToastShowLong(getThisContext(),"请输入快递单号");
            return true;

        } else if(TextUtils.isEmpty(phoneNo.getText())){
           ToastShowLong(getThisContext(),"请输入手机号码");
            return true;

        }else if(phoneNo.getText().toString().length()!=4&&phoneNo.getText().toString().length()!=11){
            Log.i("长度", "checking: "+phoneNo.getText().toString().length());
            ToastShowLong(getThisContext(),"请输入11位手机号码或者手机号后四位");
            return true;

        }  else if(phoneNo.getText().toString().length()==11&&!isMobileNO(phoneNo.getText().toString())){

                Log.i("长度", "chec "+phoneNo.getText().toString());
                ToastShowLong(getThisContext(),"糟糕！请检查您的手机号码输入是否正确");
            return true;

        } else if(TextUtils.isEmpty(expressCompany)){
                ToastShowLong(getThisContext(),"请选择快递公司");
                return true;
        } else if(TextUtils.isEmpty(apartmentName.getText())){
           ToastShowLong(getThisContext(),"请选择项目");
            return true;

        }

        return false;
    }
    public static boolean isMobileNO(String mobiles){
//"^1(3[0-9]|4[57]|5[0-35-9]|7[0135678]|8[0-9])\\d{8}$"

        Pattern p = Pattern.compile("^((13[0-9])|(15[^4])|(18[0-9])|(17[0-8])|(147)|(145))\\d{8}$");

        Matcher m = p.matcher(mobiles);

            return m.matches();

}

    private void selectApartmentDialog() {
        final CustomDialog dialog = new CustomDialog(getThisContext(), R.layout.dialog_apartment, R.style.myDialogTheme);
        TextView mCancel = (TextView) dialog.findViewById(R.id.cancel);
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        final ListView mList = (ListView) dialog.findViewById(R.id.list);
        ApartmentApi api = ApiClient.create(ApartmentApi.class);
        if (dialogFragment == null) {

            dialogFragment = new CustomDialogFragment();
        }
        dialogFragment.show(getSupportFragmentManager(),"");

        api.findByUserSid(mUserHelper.getSid(), new HttpCallback<MessageTo<List<ApartmentInfoTo>>>(getThisContext()) {
                    @Override
                    public void success(MessageTo<List<ApartmentInfoTo>> msg, Response response) {
                        dialogFragment.dismissAllowingStateLoss();
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
                                    apartmentName.setText(infoList.get(position).getApartmentName());
                                    dialog.dismiss();
                                }
                            });
                        } else {
//                            Toast.makeText(getThisContext(),
//                                    msg.getMessage(), Toast.LENGTH_SHORT).show();
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


    private DialogInterface.OnKeyListener keylistener = new DialogInterface.OnKeyListener() {
        public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                return true;
            } else {
                return false;
            }
        }
    };
    private DialogInterface.OnKeyListener keylistenerExpress = new DialogInterface.OnKeyListener() {
        public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                if(isScan){
                    finish();
                }
            }
            return false;

        }
    };
    //已经录入弹窗
    private void warnDialogShow(boolean isBefore) {
        final com.joyhome.nacity.app.util.CustomDialog dialog = new com.joyhome.nacity.app.util.CustomDialog(getThisContext(), R.layout.dialog_express_warn, R.style.myDialogTheme);
        dialog.setOnKeyListener(keylistenerExpress);
        Button btnAdd = (Button) dialog.findViewById(R.id.confirm);
        btnAdd.setOnClickListener(v -> {
            dialog.dismiss();
            if(isBefore){
                finish();
            }
        });
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
           mThread = new Thread(() -> {
            for( int i=2;i>=0;i--){
                SystemClock.sleep(1000);
                final int finalI = i;
                runOnUiThread(() -> {
                    if(finalI ==0){
                        dialog.dismiss();
                        if(isBefore){
                            finish();
                        }
                        return;
                    }
                    btnAdd.setText("关闭( "+finalI+"s )" );
                });
            }
        });
        mThread.start();
    }
    //录入完成弹窗
    private Thread   mThread;
    private void finishDialogShow() {

        final com.joyhome.nacity.app.util.CustomDialog dialog = new com.joyhome.nacity.app.util.CustomDialog(getThisContext(), R.layout.dialog_finish_input, R.style.myDialogTheme);
        dialog.setOnKeyListener(keylistener);

        Button btnAdd = (Button) dialog.findViewById(R.id.confirm);
        Button btnCancel = (Button) dialog.findViewById(R.id.cancel);
        btnAdd.setOnClickListener(v -> {

            dialog.dismiss();
            finish();


        });
        btnCancel.setOnClickListener(v1 -> {
            dialog.dismiss();
            Intent intent = new Intent(getThisContext(),MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });

        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }


//提交图片完成
    @Override
    public void complete(String key, ResponseInfo info, JSONObject response) {
        stringBuffer.append(key + ";");
        count++;
        Log.i("2222", ""+count);
        if (count == Bimp.tempSelectBitmap.size()) {
            count=0;
            pathList.clear();
            submit();
        }

    }
    @Override
    public void onBackPressed() {


        super.onBackPressed();
        Bimp.tempSelectBitmap.clear();

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
    protected void onDestroy() {
        super.onDestroy();
        new Handler().removeCallbacks(mThread);
    }
}
