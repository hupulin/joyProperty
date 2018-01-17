package com.joy.property;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.infrastructure.MessageToBulk;
import com.jinyi.ihome.module.common.DeviceParam;
import com.jinyi.ihome.module.newshop.UpdateUserInfoParam;
import com.jinyi.ihome.module.owner.UserBasicParam;
import com.jinyi.ihome.module.owner.UserInfoTo;
import com.joy.common.api.ApiClient;
import com.joy.common.api.ApiClientBulk;
import com.joy.common.api.ApiClientPark;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.NewShopApi;
import com.joy.common.api.UserApi;
import com.joy.common.api.VendorApi;
import com.joy.common.widget.UploadImage;
import com.joy.library.utils.DateUtil;
import com.joy.library.utils.FileUtil;
import com.joy.property.asyntask.MainTask;
import com.joy.property.asyntask.TaskParams;
import com.joy.property.base.BaseFragment;
import com.joy.property.constant.Constant;
import com.joy.property.login.LoginActivity;
import com.joy.property.login.ResetPwdActivity;
import com.joy.property.myservice.MyServiceOrderActivity;
import com.joy.property.neighborhood.NeighborPark;
import com.joy.property.shop.MyShoppingActivity;
import com.joy.property.shop.MyWaltActivity;
import com.joy.property.utils.ACache;
import com.joy.property.utils.CustomDialog;
import com.joy.property.utils.SpUtil;
import com.joy.property.utils.StatisticsUtil;
import com.makeramen.roundedimageview.RoundedImageView;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import retrofit.RetrofitError;
import retrofit.client.Response;

public class NavigationFragment extends BaseFragment
        implements OnClickListener, UpCompletionHandler {


    private RoundedImageView mHeadImage;
    private TextView mName;
    private TextView mTitle;
    private TextView mPropertyName;
    private List<String> mList = new ArrayList<>();
    private RelativeLayout parent;
    private static NavigationFragment instance = null;
    private TextView changeName;
    private PopupWindow pw;
    private TextView tvNickName;
    private StatisticsUtil star;
    public static NavigationFragment getInstance() {
        if (instance == null) {
            instance = new NavigationFragment();
        }
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View mRootView = inflater.inflate(R.layout.fragment_navigation,
                container, false);

        findById(mRootView);
        initDatas();

        return mRootView;

    }


    private void findById(View view) {
        parent = (RelativeLayout) view.findViewById(R.id.parent);
        parent.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager manager = (InputMethodManager) getThisContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                manager.hideSoftInputFromWindow(parent.getWindowToken(), 0);
                return false;
            }
        });

        view.findViewById(R.id.text_share01).setOnClickListener(this);
        view.findViewById(R.id.home_more).setOnClickListener(this);
        view.findViewById(R.id.change_password).setOnClickListener(this);
        view.findViewById(R.id.my_order).setOnClickListener(this);
        view.findViewById(R.id.text_walt01).setOnClickListener(this);
        view.findViewById(R.id.text_about01).setOnClickListener(this);
        view.findViewById(R.id.text_address01).setOnClickListener(this);
        view.findViewById(R.id.update).setOnClickListener(this);
        view.findViewById(R.id.about).setOnClickListener(this);
        view.findViewById(R.id.disclaimer).setOnClickListener(this);
        view.findViewById(R.id.feedback).setOnClickListener(this);
        view.findViewById(R.id.loginOut).setOnClickListener(this);
        mHeadImage = (RoundedImageView) view.findViewById(R.id.head_image);
        mTitle=(TextView)view.findViewById(R.id.title);

        mHeadImage.setOnClickListener(this);
        mName = (TextView) view.findViewById(R.id.name);
        mPropertyName = (TextView) view.findViewById(R.id.property_name);
        changeName = (TextView) view.findViewById(R.id.change_nickname);
        changeName.setOnClickListener(this);
        star= new StatisticsUtil();
    }


    private void initDatas() {
       // displayImage(mHeadImage, mUserHelper.getUserInfoTo().getImage(),R.drawable.head_image);
        Picasso.with(getThisContext()).load(MainApp.getPicassoImagePath(mUserHelper.getUserInfoTo().getImage())).into(mHeadImage);

        if (mUserHelper.getUserInfoTo() != null) {
            mName.setText(mUserHelper.getUserInfoTo().getName());
            mPropertyName.setText(mUserHelper.getUserInfoTo().getTag());

        }

        if (mUserHelper.getUserInfoTo().getGroupTo() != null) {
            mTitle.setText(mUserHelper.getUserInfoTo().getGroupTo().getGroupName());
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.text_share01:
                //我的邻居圈

                Intent intentMyNeighbor=new Intent(getThisContext(), NeighborPark.class) ;

                startActivity(intentMyNeighbor);
                StatisticsUtil.sendStatistics(mUserHelper.getSid(), "侧边栏-我的邻居圈", getThisContext());
                break;
            case R.id.text_walt01:
//                Toast.makeText(getThisContext(), "正在开发中，敬请期待！",
//                        Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getThisContext(), MyWaltActivity.class));
                StatisticsUtil.sendStatistics(mUserHelper.getSid(), "侧边栏-我的钱包", getThisContext());
                break;
            case R.id.my_order:
//                Toast.makeText(getThisContext(), "正在开发中，敬请期待！",
//                        Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getThisContext(), MyServiceOrderActivity.class));
                break;
            case R.id.text_about01:
                Intent intent_01=new Intent(getThisContext(), MyShoppingActivity.class);
                intent_01.putExtra("type", "1");
                startActivity(intent_01);

                StatisticsUtil.sendStatistics(mUserHelper.getSid(), "侧边栏-我的悦购", getThisContext());
                break;
            case R.id.update:
                TaskParams params = new TaskParams();
                MainTask.UpdateTask dbTask = new MainTask.UpdateTask(getThisContext(),true,(MainActivity)getActivity());
                dbTask.execute(params);
                break;
            case R.id.feedback:
                startActivity(new Intent(getThisContext(), FeedBackActivity.class));
                StatisticsUtil.sendStatistics(mUserHelper.getSid(), "侧边栏-用户反馈", getThisContext());
                break;
            case R.id.change_password:
                Intent intent = new Intent(getThisContext(), ResetPwdActivity.class);
                intent.putExtra("changePassword","true");
                startActivity(intent);
                break;
            case R.id.disclaimer:
                startActivity(new Intent(getThisContext(), DisclaimerActivity.class));
                break;
            case R.id.about:
                startActivity(new Intent(getThisContext(), AboutNewActivity.class));
                break;
            case R.id.loginOut:
                loginOutDialog();

                StatisticsUtil.sendStatistics(mUserHelper.getSid(), "侧边栏-注销", getThisContext());
                break;
            case R.id.text_address01:
                startActivity(new Intent(getThisContext(), MyAddressActivity.class));
                break;
            case R.id.head_image:
                uploadShowDialog();
                StatisticsUtil.sendStatistics(mUserHelper.getSid(), "侧边栏-头像", getThisContext());
                break;
            case R.id.change_nickname:
                showChangeNick();
                StatisticsUtil.sendStatistics(mUserHelper.getSid(), "侧边栏-修改昵称", getThisContext());
                break;
            case R.id.home_more:
                MainActivity y=  (MainActivity)getActivity();
                y.toggle();
                break;
            default:
                break;
        }

    }

    private void showChangeNick() {
        View view = View.inflate(getThisContext(), R.layout.change_nickname, null);

        pw = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
        pw.showAtLocation(parent, Gravity.CENTER, Gravity.CENTER, Gravity.CENTER);
     //   AlertDialog dialog1=new AlertDialog.Builder(getThisContext());


        final EditText changeName = (EditText) view.findViewById(R.id.et_change_nickname);
      //  tvNickName= (TextView) view.findViewById(R.id.tv_change_nickname);
        if (mUserHelper.getUserInfoTo() != null) {

          // changeName.setHint("请输入昵称昵称： ");

        }
        Button btnComfirm = (Button) view.findViewById(R.id.btn_add);
        Button btnCancel = (Button) view.findViewById(R.id.btn_cancel);
        btnComfirm.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(changeName.getText().toString())) {
                    Toast.makeText(getThisContext(), "昵称不能为空", Toast.LENGTH_LONG).show();
                    return;
                }
                changeNickName(changeName.getText().toString());

            }
        });
        btnCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                pw.dismiss();
            }
        });
    }

    private void uploadShowDialog() {
        final CustomDialog dialog = new CustomDialog(getActivity(), R.layout.dialog_upload_image, R.style.myDialogTheme);
        Button btnCamera = (Button) dialog.findViewById(R.id.btn_camera);
        Button btnAlbum = (Button) dialog.findViewById(R.id.btn_album);
        Button btnCancel = (Button) dialog.findViewById(R.id.btn_cancel);
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


        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == FragmentActivity.RESULT_OK) {
            switch (requestCode) {
                case Constant.RESULT_SDCARD:
                    Uri uri = data.getData();
                    Log.e("uri", uri.toString());
                    String mPhotoPath = FileUtil.getPath(getActivity(), uri);
                    if (!TextUtils.isEmpty(mPhotoPath)) {
                        getImageDisplay(mPhotoPath);
                    }
                    break;
                case Constant.RESULT_CAMERA:
                    Uri uri2 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                    if (uri2 != null) {
                        getImageDisplay(mList.get(0));
                    } else {
                        Toast.makeText(getActivity(), "sdcard无效或没有插入", Toast.LENGTH_LONG).show();
                    }
                    break;
                case Constant.RESULT_SERVICE_TYPE_CANCEL:
                    getActivity().finish();
                    break;
            }
        }


    }

    private void getImageDisplay(String path) {
        String mPath = UploadImage.getInstance(getActivity()).getImageUri(path);
        Bitmap bitmap = BitmapFactory.decodeFile(mPath);
        mHeadImage.setImageBitmap(bitmap);
        uploaderHeadImage(mPath);
    }


    private void uploaderHeadImage(final String path) {
        VendorApi api = ApiClient.create(VendorApi.class);
        api.getQnToken(new HttpCallback<MessageTo<String>>(getActivity()) {
            @Override
            public void success(MessageTo<String> msg, Response response) {
                if (msg.getSuccess() == 0) {
                    String token = msg.getData();
                    System.out.println(msg.getData() + "Home----------");
                    UploadManager uploadManager = new UploadManager();
                    uploadManager.put(path, "user_" + UUID.randomUUID().toString(),
                            token, NavigationFragment.this, null);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                super.failure(error);

            }
        });


    }

    private void loginOutDialog() {
        final CustomDialog dialog = new CustomDialog(getActivity(),
                R.layout.dialog_detele_msg, R.style.myDialogTheme);
        TextView mTip = (TextView) dialog.findViewById(R.id.tip);
        Button btnAdd = (Button) dialog.findViewById(R.id.btn_add);
        Button btnCancel = (Button) dialog.findViewById(R.id.btn_cancel);
        mTip.setText("您确定要注销当前登录吗?");
        btnCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnAdd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                accountLoggedOut();
                star.sendStatistics(mUserHelper.getSid(), "注销", getThisContext());
                mUserHelper.updateLogin(false);
                mUserHelper.updateUser(null, getThisContext());
                mUserHelperBulk.updateUser(null);


                startActivity(new Intent(getThisContext(), LoginActivity.class));

                getActivity().finish();
                new ACache().clear();
                dialog.dismiss();
            }
        });

        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }


    private void accountLoggedOut(){
        UserApi api = ApiClient.create(UserApi.class);

        DeviceParam param = new DeviceParam();
        param.setOwnerSid(mUserHelper.getSid());
        param.setType(1);
        api.accountLoggedOut(param, new HttpCallback<MessageTo>(getThisContext()) {
            @Override
            public void success(MessageTo msg, Response response) {
                Log.i("2222", "success");
                System.out.println(msg+"msg"+param.getOwnerSid());
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getThisContext(), error.toString(), Toast.LENGTH_LONG).show();

                super.failure(error);
            }
        });
    }
    @Override
    public void complete(String s, ResponseInfo responseInfo, JSONObject jsonObject) {

        UserApi api = ApiClient.create(UserApi.class);
        String id = mUserHelper.getSid();
        System.out.println(id+"/"+s);
        api.updateOwnerIcon(id, s, new HttpCallback<MessageTo<UserInfoTo>>(getThisContext()) {
            @Override
            public void success(MessageTo<UserInfoTo> msg, Response response) {
                if (msg.getSuccess() == 0) {
                    System.out.println(mUserHelper.getUserInfoTo().getImage());
                    mUserHelper.getUserInfoTo().setImage(msg.getData().getImage());
                    mUserHelper.updateUser(null, getThisContext());
                    mUserHelper.updateUser(msg.getData(), getThisContext());
                    SpUtil.put(getThisContext(), "HomeInfo", JSON.toJSONString(msg.getData()));
                    System.out.println(mUserHelper.getUserInfoTo().getImage());
                } else {
                    Toast.makeText(getThisContext(), msg.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                super.failure(error);
            }
        });

        if (!TextUtils.isEmpty(SpUtil.getString(getThisContext(), "limitPark"))) {
            UserApi apiPark = ApiClientPark.create(UserApi.class);
            UserInfoTo userInfoTo = JSON.parseObject(SpUtil.getString(getThisContext(), "ParkInfo"), UserInfoTo.class);
            apiPark.updateOwnerIcon(userInfoTo.getSid(), s, new HttpCallback<MessageTo<UserInfoTo>>(getThisContext()) {
                @Override
                public void success(MessageTo<UserInfoTo> msg, Response response) {
                    if (msg.getSuccess() == 0) {
                        SpUtil.put(getThisContext(), "ParkInfo", JSON.toJSONString(msg.getData()));

                    } else {
                        Toast.makeText(getThisContext(), msg.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void failure(RetrofitError error) {
                    super.failure(error);
                }
            });
        }
        if (mUserHelperBulk!=null&&mUserHelperBulk.getSid()!=null){
            NewShopApi api1= ApiClientBulk.create(NewShopApi.class);
            UpdateUserInfoParam param=new UpdateUserInfoParam();
            param.setOldUserId(mUserHelper.getSid());
            param.setHeadUrl(s);
            param.setNickName("");
            api1.updateInfo(param, new HttpCallback<MessageToBulk>(getThisContext()) {
                @Override
                public void success(MessageToBulk msg, Response response) {

                }

                @Override
                public void failure(RetrofitError error) {

                }
            });

        }
    }

    @Override
    protected Context getThisContext() {
        return getActivity();
    }

    private void changeNickName(final String name) {
        UserApi api = ApiClient.create(UserApi.class);
        UserBasicParam basicParam = new UserBasicParam();
        basicParam.setName(name);

        basicParam.setSid(mUserHelper.getSid());
        api.updateUserInfoWork(basicParam, new HttpCallback<MessageTo<UserInfoTo>>(getThisContext()) {
            @Override
            public void success(MessageTo<UserInfoTo> msg, Response response) {
                if (msg.getSuccess() == 0) {
                    pw.dismiss();
                    mUserHelper.getUserInfoTo().setFamilyName(name);
                   SpUtil.put(getThisContext(),"HomeInfo",JSON.toJSONString(msg.getData()));



                } else {
                    Toast.makeText(getThisContext(), msg.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                super.failure(error);
            }
        });
        if (!TextUtils.isEmpty(SpUtil.getString(getThisContext(), "limitPark"))) {
            UserApi apiPark = ApiClientPark.create(UserApi.class);
            UserBasicParam basicParamPark = new UserBasicParam();
            basicParamPark.setName(name);
            UserInfoTo userInfoTo = JSON.parseObject(SpUtil.getString(getThisContext(), "ParkInfo"), UserInfoTo.class);
            basicParamPark.setSid(userInfoTo.getSid());
            apiPark.updateUserInfoWork(basicParamPark, new HttpCallback<MessageTo<UserInfoTo>>(getThisContext()) {
                @Override
                public void success(MessageTo<UserInfoTo> msg, Response response) {
                    if (msg.getSuccess() == 0) {
                        pw.dismiss();
                        mUserHelper.getUserInfoTo().setFamilyName(name);
                        SpUtil.put(getThisContext(),"ParkInfo",JSON.toJSONString(msg.getData()));

                    } else {
                        Toast.makeText(getThisContext(), msg.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void failure(RetrofitError error) {
                    super.failure(error);
                }
            });
        }
        System.out.println(mUserHelperBulk.getSid() + "------" + mUserHelperBulk);
if (mUserHelperBulk!=null&&mUserHelperBulk.getSid()!=null){
    NewShopApi api1=ApiClientBulk.create(NewShopApi.class);
    UpdateUserInfoParam param=new UpdateUserInfoParam();
    param.setOldUserId(mUserHelper.getSid());
    param.setNickName(name);
    param.setHeadUrl("");
       api1.updateInfo(param, new HttpCallback<MessageToBulk>(getThisContext()) {
           @Override
           public void success(MessageToBulk msg, Response response) {

           }

           @Override
           public void failure(RetrofitError error) {
           }
       });

}
    }

}
