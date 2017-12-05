package com.joy.property.task;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.Util.SpUtil;
import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.apartment.ApartmentInfoTo;
import com.jinyi.ihome.module.home.ServiceCategoryParam;
import com.jinyi.ihome.module.home.ServiceMainTo;
import com.jinyi.ihome.module.home.ServiceMyworkParam;
import com.jinyi.ihome.module.home.ServiceTypeParam;
import com.jinyi.ihome.module.home.ServiceTypeSelectParam;
import com.jinyi.ihome.module.home.ServiceTypeTo;
import com.jinyi.ihome.module.owner.UserInfoTo;
import com.joy.common.api.ApartmentApi;
import com.joy.common.api.ApiClient;
import com.joy.common.api.ApiClientNormal;
import com.joy.common.api.HomeApi;
import com.joy.common.api.HttpCallback;
import com.joy.library.fragment.CustomDialogFragment;
import com.joy.property.R;
import com.joy.property.base.BaseActivity;
import com.joy.property.inspection.adapter.ApartmentAdapter;
import com.joy.property.inspection.adapter.StringAdapter;
import com.joy.property.myservice.ExecuteNewActivity;
import com.joy.property.myservice.FilterResultActivity;
import com.joy.property.myservice.FilterResultCommonActivity;
import com.joy.property.myservice.MyFilterResultActivity;
import com.joy.property.myservice.SelectServiceActivity;
import com.joy.property.myservice.SelectTaskServiceActivity;
import com.joy.property.myservice.TaskFilResultActivity;
import com.joy.property.myservice.adapter.ApplyServiceAdapter;
import com.joy.property.myservice.adapter.PersonListAdapter;
import com.joy.property.myservice.widget.OnWheelChangedListener;
import com.joy.property.myservice.widget.WheelView;
import com.joy.property.myservice.widget.adapters.NumericWheelAdapter;
import com.joy.property.utils.CustomDialog;
import com.joy.property.utils.ViewPagerCompat;
import com.joy.property.vehicle.adapter.RefreshEvent;
import com.joy.property.vehicle.adapter.RefreshEventOther;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by usb on 2017/3/12.
 **/
public class TaskScreenActivity extends BaseActivity implements View.OnClickListener {
    private ApartmentInfoTo apartment;
    private String state;
    private Dialog mDialog;

    private ServiceTypeTo serviceTypeTo;
    private List<ServiceTypeTo> serviceTypeToList = new ArrayList<>();
    private GridLayout gridView;
    private TextView mCategory;
    private TextView mState;
    private TextView mTime;
    private TextView mCommunity;
    private List<View> viewList = new ArrayList<>();

    ApplyServiceAdapter mAdapter;
    private TextView mConfirm;
    private TextView mPersonName;
    private GridLayout.LayoutParams layoutParamsGird;
    private PopupWindow pw;
    private int allIndex;
    private RelativeLayout parent;
    private String typeSid;
    private String mDate;
    private String userSid;
    private String apartmentSid="";
    private ServiceCategoryParam param=new ServiceCategoryParam();
    private ServiceMyworkParam taskParam=new ServiceMyworkParam();
    private boolean isFirstRegist;
    private RelativeLayout peopleLayout;
    private RelativeLayout stateLayout;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);
        findById();
        getIntentData();
        if (!isFirstRegist)
            EventBus.getDefault().register(this);
        isFirstRegist=true;
    }
    private void findById() {
        mCategory=(TextView)findViewById(R.id.tv_select_category);
        mState=(TextView)findViewById(R.id.tv_select_state);
        mTime=(TextView)findViewById(R.id.tv_select_time);
        mCommunity=(TextView)findViewById(R.id.tv_select_community);
        mPersonName=(TextView)findViewById(R.id.tv_person);
        mConfirm=(TextView)findViewById(R.id.confirm);
        mConfirm.setOnClickListener(this);
        findViewById(R.id.select_category).setOnClickListener(this);
        findViewById(R.id.select_state).setOnClickListener(this);
        findViewById(R.id.select_time).setOnClickListener(this);
        findViewById(R.id.select_community).setOnClickListener(this);
        findViewById(R.id.person).setOnClickListener(this);
        findViewById(R.id.iv_back).setOnClickListener(this);
        parent = (RelativeLayout) findViewById(R.id.parent);
        peopleLayout = (RelativeLayout) findViewById(R.id.peopleLayout);
        stateLayout = (RelativeLayout) findViewById(R.id.stateLayout);

    }

    private void getIntentData() {
//        if ( !getIntent().getBooleanExtra("IsMyService",false))
//            findViewById(R.id.selectApartmentLayout).setVisibility(View.GONE);
//        apartmentSid=getIntent().getStringExtra("ApartmentSid");
//        if (apartmentSid==null)
//            apartmentSid="";
        if (getIntent().getIntExtra("CurrentPosition",0)==0||getIntent().getIntExtra("CurrentPosition",0)==1)
            peopleLayout.setVisibility(View.GONE);
        if (getIntent().getIntExtra("CurrentPosition",0)==2){
            peopleLayout.setVisibility(View.GONE);
            stateLayout.setVisibility(View.GONE);
        }

//        param.setServiceCategory(getIntent().getStringExtra("CategorySid"));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.select_state:
                selectStateDialog();
                break;
            case R.id.select_category:
                //选择种类
                if (TextUtils.isEmpty(apartmentSid)){

                    ToastShowLong(getThisContext(),"请先选择小区");
                    return;
                }
                if(getIntent().getIntExtra("CurrentPosition",0)==1){
                    Intent intent=new Intent(getThisContext(),SelectTaskServiceActivity.class);
                    intent.putExtra("ApartmentSid",apartmentSid);
                    intent.putExtra("CategorySid",getIntent().getStringExtra("CategorySid"));
                    startActivity(intent);
                    goToAnimation(1);
                }else{
                    Intent intent=new Intent(getThisContext(),SelectServiceActivity.class);
                    intent.putExtra("ApartmentSid",apartmentSid);
                    intent.putExtra("CategorySid",getIntent().getStringExtra("CategorySid"));
                    startActivity(intent);
                    goToAnimation(1);
                }

                break;
            case R.id.select_time:
                getBirthDay();
                break;
            case R.id.select_community:
                selectApartmentDialog();
                break;
            case R.id.person:
                if (TextUtils.isEmpty(typeSid)){
                    ToastShowLong(getThisContext(),"请先选择具体类型");
                    return;
                }
                Intent  intent=new Intent(getThisContext(),ExecuteNewActivity.class);
                intent.putExtra("typeSid",typeSid);
                startActivity(intent);
                break;
            case R.id.confirm:
                if (TextUtils.isEmpty(mPersonName.getText())&&TextUtils.isEmpty(mCommunity.getText())&&TextUtils.isEmpty(mCategory.getText())&&TextUtils.isEmpty(mTime.getText())){
                    ToastShowLong(getThisContext(),"请至少选择一种类型");
                    return;

                }

                param.setApartmentSid(apartmentSid);
//                type  0，代表我的任务 1，代表任务大厅 2，代表特别关注
                if (getIntent().getIntExtra("CurrentPosition", 0) == 3)
                    param.setType("2");
                if (getIntent().getIntExtra("CurrentPosition", 0) == 2)
                    param.setType("1");
                if (getIntent().getIntExtra("CurrentPosition", 0) == 0)
                    param.setType("0");
                if (getIntent().getIntExtra("CurrentPosition",0)==0){
                    param.setResponseUser(mUserHelper.getSid());
                    param.setOwnerSid("");
                }

//                if (getIntent().getBooleanExtra("IsMyService",false)) {

                    param.setOwnerSid(mUserHelper.getSid());
                    if (getIntent().getIntExtra("CurrentPosition", 0)==2){
                        param.setType("1");
                        intent=new Intent(getThisContext(),FilterTaskResultActivity.class);
                        intent.putExtra("ServiceParam", param);
                        startActivity(intent);
                        goToAnimation(1);
                    }else if(getIntent().getIntExtra("CurrentPosition", 0)==1){
                        taskParam.setApartmentSid(apartmentSid);
                        intent=new Intent(getThisContext(),TaskFilResultActivity.class);
                        intent.putExtra("ServiceParam", taskParam);
                        startActivity(intent);
                        goToAnimation(1);
                    }else{
                        param.setOwnerSid("");
                        intent=new Intent(getThisContext(),MyFilterResultActivity.class);
                        intent.putExtra("ServiceParam", param);
                        startActivity(intent);
                        goToAnimation(1);
                    }

                break;
            default:
                break;
        }
    }
    private void selectStateDialog() {
        final CustomDialog dialog = new CustomDialog(getThisContext(), R.layout.dialog_apartment, R.style.myDialogTheme);
        TextView mCancel = (TextView) dialog.findViewById(R.id.cancel);
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        final ListView mList = (ListView) dialog.findViewById(R.id.list);
        final List<String> infoList = new ArrayList<>();
        StringAdapter mAdapter = new StringAdapter(getThisContext());
        infoList.add("已指派");
        infoList.add("处理中");
        infoList.add("已结束");
        if (getIntent().getIntExtra("CurrentPosition", 0)!=1){
            infoList.add("待评价");
        }
        mAdapter.setList(infoList);
        mList.setAdapter(mAdapter);
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                         @Override
                                         public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                             mState.setText(infoList.get(position));
                                             if("已指派".equals(infoList.get(position))){
                                                 state ="2" ;
                                                 param.setServiceStatus(state);
                                                 taskParam.setWorkStatus(state);
                                             }if("处理中".equals(infoList.get(position))){
                                                 state ="22" ;
                                                 param.setServiceStatus(state);
                                                 taskParam.setWorkStatus(state);
                                             }if("待评价".equals(infoList.get(position))){
                                                 state ="4" ;
                                                 param.setServiceStatus(state);
                                                 taskParam.setWorkStatus(state);
                                             }if("已结束".equals(infoList.get(position))){
                                                 state ="6" ;
                                                 param.setServiceStatus(state);
                                                 taskParam.setWorkStatus(state);
                                             }

                                             dialog.dismiss();
                                         }

                                     }
        );

        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
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
                                    apartment = infoList.get(position);
                                    mCommunity.setText(apartment.getApartmentName());
                                    if (!apartmentSid.equals(apartment.getApartmentSid()))
                                        cleanData();
                                    apartmentSid = apartment.getApartmentSid();
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

    private void getBirthDay() {
        View view = View.inflate(this, R.layout.dialog_select_birthday, null);
        Calendar calendar = Calendar.getInstance();


        pw = new PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        pw.showAtLocation(parent, Gravity.CENTER,Gravity.CENTER,Gravity.CENTER);
        TextView  tv_ok = (TextView) view.findViewById(R.id.tv_ok);

        TextView  tv_cancel = (TextView) view.findViewById(R.id.tv_cancel);
        final WheelView month = (WheelView) view.findViewById(R.id.month);
        final WheelView year = (WheelView)view.findViewById(R.id.year);
        final WheelView day = (WheelView)view.findViewById(R.id.day);
        OnWheelChangedListener listener = (wheel, oldValue, newValue) -> updateDays(year, month, day);
        final int curMonth = calendar.get(Calendar.MONTH);
        DateNumericAdapter monAdapter = new DateNumericAdapter(this, 1, 12, curMonth,"have");
        monAdapter.setItemResource(R.layout.wheel_text_item);
        monAdapter.setItemTextResource(R.id.text);
        month.setViewAdapter(monAdapter);
        month.setCyclic(true);
        month.setCurrentItem(curMonth);
        month.addChangingListener(listener);

        // year
        int curYear = calendar.get(Calendar.YEAR);
        DateNumericAdapter yearAdapter = new DateNumericAdapter(this, curYear - 3, curYear, curYear,null);
        yearAdapter.setItemResource(R.layout.wheel_text_item);
        yearAdapter.setItemTextResource(R.id.text);
        year.setViewAdapter(yearAdapter);
        //year.setCyclic(true);
        year.setCurrentItem(3);
        month.setCurrentItem(curMonth);

        year.addChangingListener(listener);


        //day
        updateDays(year, month, day);
        day.setCurrentItem(calendar.get(Calendar.DATE));

        tv_ok.setOnClickListener(v -> {
            mTime.setText((curYear - 3+year.getCurrentItem())+"-"+(month.getCurrentItem() + 1 )+ "-" + (day.getCurrentItem() + 1) );
            String newdate=month.getCurrentItem()+1+"";
            String newtime=day.getCurrentItem()+1+"";
            if(newdate.length()==1){
                newdate=0+newdate;
            }
            if(newtime.length()==1){
                newtime=0+newtime;
            }

            param.setModifiedOn((curYear - 3+year.getCurrentItem())+"-"+((month.getCurrentItem() + 1 )<10?"0"+(month.getCurrentItem() + 1 ):(month.getCurrentItem() + 1))+"-"+((day.getCurrentItem() + 1 )<10?"0"+(day.getCurrentItem() + 1 ):(day.getCurrentItem() + 1))+" 00:00:00");
            taskParam.setCreatedOn((curYear - 3+year.getCurrentItem())+"-"+((month.getCurrentItem() + 1 )<10?"0"+(month.getCurrentItem() + 1 ):(month.getCurrentItem() + 1))+"-"+((day.getCurrentItem() + 1 )<10?"0"+(day.getCurrentItem() + 1 ):(day.getCurrentItem() + 1))+" 00:00:00");
            pw.dismiss();
        });
        tv_cancel.setOnClickListener(v ->{
                    pw.dismiss();
                    param.setModifiedOn("");
            taskParam.setCreatedOn("");
                    mTime.setText("");
                }


        );

    }

    public class DateNumericAdapter extends NumericWheelAdapter {
        // Index of current item
        int currentItem;
        // Index of item to be highlighted
        int currentValue;

        /**
         * Constructor
         */
        public DateNumericAdapter(Context context, int minValue, int maxValue, int current, String h) {
            super(context, minValue, maxValue, h);
            this.currentValue = current;
            setTextSize(50);

        }

        @Override
        protected void configureTextView(TextView view) {
            super.configureTextView(view);
            if (currentItem == currentValue) {
                view.setTextColor(0xFF0000F0);
            }
            view.setTypeface(Typeface.SANS_SERIF);
        }

        @Override
        public View getItem(int index, View cachedView, ViewGroup parent) {
            currentItem = index;
            return super.getItem(index, cachedView, parent);
        }
    }

    void updateDays(WheelView year, WheelView month, WheelView day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + year.getCurrentItem());
        calendar.set(Calendar.MONTH, month.getCurrentItem());

        int maxDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        DateNumericAdapter dayAdapter = new DateNumericAdapter(this, 1, maxDays, maxDays - 1, null);
        dayAdapter.setItemResource(R.layout.wheel_text_item);
        dayAdapter.setItemTextResource(R.id.text);
        day.setViewAdapter(dayAdapter);
        day.setCyclic(true);
        int curDay = Math.min(maxDays, day.getCurrentItem() + 1);
        day.setCurrentItem(curDay - 1, true);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        ToastShowLong(getThisContext(), requestCode + "--" + data);
        switch (requestCode){
            case 10:
                if (data!=null) {
                    UserInfoTo userInfoTo = (UserInfoTo) data.getSerializableExtra("assist");

                }
                break;

        }

        super.onActivityResult(requestCode, resultCode, data);
    }


    @Subscribe
    public void onEventMainThread(RefreshEventOther event) {

        String msg = event.getMsg();

        if (event.getType()==3) {
            mCategory.setText(event.getmName());
            param.setTypeSid(msg);
            taskParam.setTypeApartmentSid(msg);
            typeSid=msg;
            param.setServiceCategory((String) event.getmCommon());
            taskParam.setCategorySid((String) event.getmCommon());
            Log.i("2222", "allTypeSid: "+event.getMsg());
            Log.i("2222", "getType: "+event.getType());
            Log.i("2222", "allType: "+event.getmName());
            Log.i("2222", "categorySid: "+event.getmCommon());
        }
        if (event.getType()==4){
            mPersonName.setText(event.getmName());
            userSid =msg;
            param.setResponseUser(userSid);

        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    public void cleanData(){
        mTime.setText("");
        mCategory.setText("");
        mPersonName.setText("");
        param.setTypeSid(null);
        param.setModifiedOn("");
        param.setResponseUser("");
    }
}
