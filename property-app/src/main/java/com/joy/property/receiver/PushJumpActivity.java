package com.joy.property.receiver;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.Util.SpUtil;
import com.alibaba.fastjson.JSONObject;
import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.home.ServiceMainExpandTo;
import com.joy.common.api.ApiClient;
import com.joy.common.api.HomeApi;
import com.joy.common.api.HttpCallback;
import com.joy.library.utils.ConfigUtil;
import com.joy.property.MainApp;
import com.joy.property.MainApplication;
import com.joy.property.R;
import com.joy.property.base.BaseActivity;
import com.joy.property.community.NoticeDetailActivity;
import com.joy.property.inspection.CallDetailActivity;
import com.joy.property.myservice.MyServiceOrderActivity;
import com.joy.property.neighborhood.CampaignActivity;
import com.joy.property.neighborhood.FansActivity;
import com.joy.property.neighborhood.InvestigateActivity;
import com.joy.property.neighborhood.RefreshEvent;
import com.joy.property.neighborhood.TopicActivity;
import com.joy.property.shop.CampaignListActivity;
import com.joy.property.shop.GoodsDetailActivity;
import com.joy.property.shop.MyCouponActivity;
import com.joy.property.shop.MyShoppingActivity;
import com.joy.property.shop.MyWaltActivity;
import com.joy.property.shop.SideGoodsDetailActivity;
import com.joy.property.task.LeaveMessageActivity;
import com.joy.property.task.ReceiveTaskDetailActivity;
import com.joy.property.task.TaskHallActivity;
import com.joy.property.utils.ChangeParkUtil;
import com.joy.property.utils.CustomDialog;
import com.joy.property.utils.StatuBarUtil;
import com.joyhome.nacity.app.photo.util.PublicWay;

import org.greenrobot.eventbus.EventBus;

import cn.jpush.android.api.JPushInterface;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by usb on 2016/8/5.
 **/
public class PushJumpActivity extends BaseActivity {


    private String data;
    private int notificationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatuBarUtil.setStatueBarTransparent(getWindow());
        setContentView(R.layout.activity_push_jump);
        data=SpUtil.getString(this, "ReceiverData");
        notificationId=SpUtil.getInt(this, "ReceiverNotificationId");

        competenceShow();
        PublicWay.pushJumpActivity=this;

    }

    /**
     * 派单弹窗
     **/
    private  CustomDialog dialog;
    private void competenceShow() {
        Log.i("222222", "competenceShow: ");
          dialog = new CustomDialog(this, R.layout.dialog_assignment, R.style.myDialogTheme);
        Button btn_go = (Button) dialog.findViewById(R.id.btn_go);
        Button btn_close = (Button) dialog.findViewById(R.id.btn_close);
        TextView titleName = (TextView)dialog. findViewById(R.id.title_name);
        if (data!=null)
            titleName.setText(JSONObject.parseObject(data).getString("message"));
        String taskType=JSONObject.parseObject(data).getString("type");
        if ("33".equals(taskType)||"34".equals(taskType)||"36".equals(taskType)||"37".equals(taskType)||"40".equals(taskType))
            EventBus.getDefault().post(new RefreshEvent("TaskPoint"));
            btn_go.setOnClickListener((View v) -> {
                ChangeParkUtil.changeToHome(getThisContext(), mUserHelper);

                    EventBus.getDefault().post(new RefreshEvent("TaskPoint"));
                EventBus.getDefault().post(new RefreshEvent("TaskPoint"));
               message();
               dialog.dismiss();

           });
        btn_close.setOnClickListener((View v) -> {
            dialog.dismiss();
            finish();

        });

        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setOnKeyListener(keylistener);
        dialog.show();

    }

    private void message() {


        if (!TextUtils.isEmpty(data)) {
        int type = Integer.parseInt(JSONObject.parseObject(data).getString("type"));

            if (isRunningForeground()) {
                ActivityManager am = (ActivityManager) this.getSystemService(Context.ACTIVITY_SERVICE);
                ComponentName cn = am.getRunningTasks    (1).get(0).topActivity;
                Context c = null;
                try {

                    c = createPackageContext(cn.getPackageName(), CONTEXT_INCLUDE_CODE | CONTEXT_IGNORE_SECURITY);
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
                jumpActivity(c, type, data);

            } else {
                jumpActivity(getThisContext(), type, data);
            }


        }
        JPushInterface.clearNotificationById(getThisContext(), notificationId);
    }

    /**
     * 消息跳转
     * １：呼叫
     * ２：消息
     * ３：公告
     * 4: 团购
     * 5：用户认证通过
     * 6 快递
     * 7 帐单
     * 8 调查
     * 10 自定义网站
     * 11 家政
     * 12 公共维修
     * 13 入室维修
     * 14 投诉
     * 17 巡检管理
     * 18 送水管理
     *推送跳转
     * @param context
     * @param type
     * @param data
     */
    private void jumpActivity(Context context, int type, String data) {

        Intent i;
       String sid = JSONObject.parseObject(data).getString("sid");
//        String sid = JSONObject.parseObject(data).getString("goodsId");
        System.out.println(type+"type"+sid);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getThisContext());
        switch (type) {
            case 98:
                i = new Intent(context, SideGoodsDetailActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("GoodsSid", JSONObject.parseObject(data).getString("goodsId"));
                startActivity(i);
                ConfigUtil.saveString(sp,MainApplication.KeyValue.KEY_HOME_DATA, "");
                finish();
                break;
            //访客验证通过推送
            case 26:
//                i = new Intent(context, GoodsDetailActivity.class);
//                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                i.putExtra("GoodsSid", sid);
//                startActivity(i);
////                Log.i("goods", sid);
                ConfigUtil.saveString(sp,MainApplication.KeyValue.KEY_HOME_DATA, "");
                break;
            //日常工单-
            case 34:


                i = new Intent(context, TaskHallActivity.class);
                i.putExtra("page", 1);
                startActivity(i);
                ConfigUtil.saveString(sp, MainApplication.KeyValue.KEY_HOME_DATA, "");
                finish();
                break;
            case 40:
                i = new Intent(context, TaskHallActivity.class);
                startActivity(i);
                ConfigUtil.saveString(sp, MainApplication.KeyValue.KEY_HOME_DATA, "");
                finish();
                break;
            case 36:
                i = new Intent(context, TaskHallActivity.class);
                i.putExtra("page", 3);
                startActivity(i);
                ConfigUtil.saveString(sp, MainApplication.KeyValue.KEY_HOME_DATA, "");
                finish();
                break;
            //
            case 37:
                i = new Intent(context, TaskHallActivity.class);
                i.putExtra("page", 3);
                startActivity(i);
                ConfigUtil.saveString(sp, MainApplication.KeyValue.KEY_HOME_DATA, "");
                finish();
                break;
            //可跳可不跳
            case 99:
                i = new Intent(context, MyShoppingActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //   i.putExtra("serviceSid", sid);
                startActivity(i);
                ConfigUtil.saveString(sp, MainApplication.KeyValue.KEY_HOME_DATA, "");
                finish();
                break;
            case 97:
                i = new Intent(context, MyCouponActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                ConfigUtil.saveString(sp, MainApplication.KeyValue.KEY_HOME_DATA, "");
                finish();
                break;
            case 39:
                i = new Intent(getThisContext(), CampaignListActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                ConfigUtil.saveString(sp, MainApp.KeyValue.KEY_HOME_DATA, "");
                break;
            case 1:

                    i = new Intent(context, MyServiceOrderActivity.class);
                    i.putExtra("Index", 2);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);


                    startActivity(i);
                    finish();
                ConfigUtil.saveString(sp, MainApplication.KeyValue.KEY_HOME_DATA, "");
                break;

            //推送
            case 6:

                    i = new Intent(context, MyServiceOrderActivity.class);
                    i.putExtra("Index", 1);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                ConfigUtil.saveString(sp, MainApplication.KeyValue.KEY_HOME_DATA, "");
                finish();
                break;
            //留言推送
            case 9:
//                i = new Intent(context, LeaveMessageActivity.class);
//                i.putExtra("sid", sid);
                initMessageDatas(sid);
//                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(i);
                ConfigUtil.saveString(sp, MainApplication.KeyValue.KEY_HOME_DATA, "");
                break;
            //小区通知推送
            case 3:
                i = new Intent(context, NoticeDetailActivity.class);
                i.putExtra("sid", sid);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                ConfigUtil.saveString(sp, MainApplication.KeyValue.KEY_HOME_DATA, "");
                finish();
                break;

            case 11:
                i = new Intent(context, ReceiveTaskDetailActivity.class);
                i.putExtra("sid", sid);
                i.putExtra("houseKeeping", true);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                ConfigUtil.saveString(sp, MainApplication.KeyValue.KEY_HOME_DATA, "");
                finish();
                break;
            case 12:
                i = new Intent(context, ReceiveTaskDetailActivity.class);
                i.putExtra("sid", sid);
                i.putExtra("publicRepair", true);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                ConfigUtil.saveString(sp, MainApplication.KeyValue.KEY_HOME_DATA, "");
               finish();
                break;
            case 13:
                i = new Intent(context, ReceiveTaskDetailActivity.class);
                i.putExtra("sid", sid);
                i.putExtra("roomRepair", true);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                ConfigUtil.saveString(sp, MainApplication.KeyValue.KEY_HOME_DATA, "");
               finish();
                break;
            case 14:
                i = new Intent(context, ReceiveTaskDetailActivity.class);
                i.putExtra("sid", sid);
                i.putExtra("isComplaintPush", true);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                ConfigUtil.saveString(sp, MainApplication.KeyValue.KEY_HOME_DATA, "");
               finish();
                break;
            case 17:
                i = new Intent(context, ReceiveTaskDetailActivity.class);
                i.putExtra("sid", sid);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                ConfigUtil.saveString(sp, MainApplication.KeyValue.KEY_HOME_DATA, "");
                finish();
                break;
            case 18:
                i = new Intent(context, ReceiveTaskDetailActivity.class);
                i.putExtra("sid", sid);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                ConfigUtil.saveString(sp, MainApplication.KeyValue.KEY_HOME_DATA, "");
                finish();
                break;
//            case 100:
//                Log.i("ForceOfflineService", "jumpActivity:强制下线 ");
//                ConfigUtil.saveString(sp, MainApplication.KeyValue.KEY_HOME_DATA, "");
//                context.getApplicationContext().startService(new Intent(context.getApplicationContext(), ForceOfflineService.class));
//                break;
            //任务大厅新增单子
            case 33:
                i = new Intent(context, TaskHallActivity.class);
                i.putExtra("page", 2);
                startActivity(i);
                ConfigUtil.saveString(sp, MainApplication.KeyValue.KEY_HOME_DATA, "");
                finish();
                goToAnimation(1);
                break;

            case 101:

                i = new Intent(context, ReceiveTaskDetailActivity.class);
                i.putExtra("sid", sid);
                i.putExtra("taskHall", true);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                ChangeParkUtil.changeToPark(getThisContext(), mUserHelper);
                ConfigUtil.saveString(sp, MainApplication.KeyValue.KEY_HOME_DATA, "");
                finish();
                break;
            //推送
            case 106:
                i = new Intent(context, CallDetailActivity.class);
                i.putExtra("sid", sid);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                ChangeParkUtil.changeToPark(getThisContext(), mUserHelper);
                ConfigUtil.saveString(sp, MainApplication.KeyValue.KEY_HOME_DATA, "");
                finish();
                break;
            //留言推送
            case 121:
//                i = new Intent(context, LeaveMessageActivity.class);
//                i.putExtra("sid", sid);
                initMessageDatas(sid);
//                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(i);
                ChangeParkUtil.changeToPark(getThisContext(), mUserHelper);
                ConfigUtil.saveString(sp, MainApplication.KeyValue.KEY_HOME_DATA, "");
                finish();
                break;
            //小区通知推送
            case 125:
                i = new Intent(context, NoticeDetailActivity.class);
                i.putExtra("sid", sid);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                ChangeParkUtil.changeToPark(getThisContext(), mUserHelper);
                ConfigUtil.saveString(sp, MainApplication.KeyValue.KEY_HOME_DATA, "");
                finish();
                break;

            case 110:
                i = new Intent(context, ReceiveTaskDetailActivity.class);
                i.putExtra("sid", sid);
                i.putExtra("houseKeeping", true);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                ConfigUtil.saveString(sp, MainApplication.KeyValue.KEY_HOME_DATA, "");
               finish();
                break;
            case 111:
                i = new Intent(context, ReceiveTaskDetailActivity.class);
                i.putExtra("sid", sid);
                i.putExtra("publicRepair", true);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                ChangeParkUtil.changeToPark(getThisContext(), mUserHelper);
                startActivity(i);
                ConfigUtil.saveString(sp, MainApplication.KeyValue.KEY_HOME_DATA, "");
                finish();
                break;
            case 122:
                i = new Intent(context, ReceiveTaskDetailActivity.class);
                i.putExtra("sid", sid);
                i.putExtra("roomRepair", true);
                ChangeParkUtil.changeToPark(getThisContext(), mUserHelper);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                ConfigUtil.saveString(sp, MainApplication.KeyValue.KEY_HOME_DATA, "");
                finish();
                break;
            case 113:
                i = new Intent(context, ReceiveTaskDetailActivity.class);
                i.putExtra("sid", sid);
                i.putExtra("isComplaintPush", true);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                ChangeParkUtil.changeToPark(getThisContext(), mUserHelper);
                startActivity(i);
                ConfigUtil.saveString(sp, MainApplication.KeyValue.KEY_HOME_DATA, "");
               finish();
                break;
            case 126:
                i = new Intent(context, TaskHallActivity.class);
                i.putExtra("sid", sid);
                i.putExtra("HallTask",true);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                ChangeParkUtil.changeToPark(getThisContext(), mUserHelper);
                startActivity(i);
                ConfigUtil.saveString(sp, MainApplication.KeyValue.KEY_HOME_DATA, "");
                finish();
                break;
            case 123:
                i =  new Intent(getThisContext(), CampaignActivity.class);
                i.putExtra("interactionSid", sid);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                ConfigUtil.saveString(sp, MainApp.KeyValue.KEY_HOME_DATA, "");
                finish();
                break;
            case 124:
                i =  new Intent(getThisContext(), TopicActivity.class);
                i.putExtra("interactionSid", sid);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                ConfigUtil.saveString(sp, MainApp.KeyValue.KEY_HOME_DATA, "");
                finish();
                break;
            case 128:
                i =  new Intent(getThisContext(), InvestigateActivity.class);
                i.putExtra("interactionSid", sid);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                ConfigUtil.saveString(sp, MainApp.KeyValue.KEY_HOME_DATA, "");
                finish();
                break;
            case 119:
                i = new Intent(getThisContext(), FansActivity.class);
                startActivity(i);
                ConfigUtil.saveString(sp, MainApp.KeyValue.KEY_HOME_DATA, "");
                finish();
                break;
            default:
        }
    }

    private void initMessageDatas(String mServiceSid) {

        HomeApi api = ApiClient.create(HomeApi.class);
        api.findServiceMainExpandBySid(mServiceSid, new HttpCallback<MessageTo<ServiceMainExpandTo>>(this) {
            @Override
            public void success(MessageTo<ServiceMainExpandTo> msg, Response response) {
                if (msg.getSuccess() == 0) {
                    ServiceMainExpandTo  mainExpandTo = msg.getData();
                    Intent intent = new Intent(getThisContext(), LeaveMessageActivity.class);
                    intent.putExtra("mode", mainExpandTo);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                } else {
                    /*Toast.makeText(getThisContext(), msg.getMessage(),
                            Toast.LENGTH_LONG).show();*/
                }
            }

            @Override
            public void failure(RetrofitError error) {
                super.failure(error);
            }
        });

    }

    private boolean isRunningForeground() {
        ActivityManager am = (ActivityManager) this.getSystemService(Context.ACTIVITY_SERVICE);
        ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
        String currentPackageName = cn.getPackageName();
        return !TextUtils.isEmpty(currentPackageName) && currentPackageName.equals(getPackageName());
    }

    public void loginOutDialog(){

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private DialogInterface.OnKeyListener keylistener = new DialogInterface.OnKeyListener() {
        public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                finish();
                return true;
            } else {
                return false;
            }
        }
    };
}
