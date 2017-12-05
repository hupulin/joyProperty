package com.joy.property.message;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.joy.common.api.ApiClient;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.UserApi;
import com.joy.property.base.BaseActivity;
import com.joy.property.inspection.CallDetailActivity;
import com.joy.property.message.adpater.HomeMessageAdapter;
import com.joy.property.task.ReceiveTaskDetailActivity;
import com.joy.property.utils.ChangeParkUtil;
import com.joy.property.utils.CustomDialog;
import com.joy.property.R;

import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.owner.UserMessageTo;
import com.joy.library.fragment.CustomDialogFragment;
import com.joy.property.utils.ParkToast;
import com.joy.property.utils.SpUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit.RetrofitError;
import retrofit.client.Response;

public class MessageCenterActivity extends BaseActivity
        implements OnClickListener {

    private PullToRefreshListView mPullToRefreshListView;
    private ListView mList;
    private HomeMessageAdapter messageAdapter;
    private int pageIndex = 0;
    private List<UserMessageTo> userMessageList = new ArrayList<>();
    private ImageView changePark;
    private boolean firstChange;
    private TextView mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        findById();
        mList = mPullToRefreshListView.getRefreshableView();
        mPullToRefreshListView.setMode(Mode.BOTH);
        mList.setItemsCanFocus(true);
        registerForContextMenu(mList);
        messageAdapter = new HomeMessageAdapter(getThisContext());
        messageAdapter.setList(userMessageList);
        mList.setAdapter(messageAdapter);
        setList(0);
        initDatas();
    }



    private void findById() {
        mBack = (ImageView) findViewById(R.id.back);
        mBack.setOnClickListener(this);
        mPullToRefreshListView = (PullToRefreshListView) findViewById(R.id.list);
        changePark = (ImageView) findViewById(R.id.changePark);
        changePark.setOnClickListener(this);
        mTitle = (TextView) findViewById(R.id.title);
    }

    private void initDatas() {
        if (SpUtil.getString(getThisContext(), "limitPark")!=null&&SpUtil.getString(getThisContext(),"limitPark").contains("A014"))
        {
            changePark.setVisibility(View.VISIBLE);
            mTitle.setText("消息中心(住宅)");
        }
        mPullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> listViewPullToRefreshBase) {
                String label = DateUtils.formatDateTime(
                        getThisContext(),
                        System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME
                                | DateUtils.FORMAT_SHOW_DATE
                                | DateUtils.FORMAT_ABBREV_ALL);
                // Update the LastUpdatedLabel
                listViewPullToRefreshBase.getLoadingLayoutProxy()
                        .setLastUpdatedLabel(label);
                setList(0);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> listViewPullToRefreshBase) {
                String label = DateUtils.formatDateTime(
                        getThisContext(),
                        System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME
                                | DateUtils.FORMAT_SHOW_DATE
                                | DateUtils.FORMAT_ABBREV_ALL);
                // Update the LastUpdatedLabel
                listViewPullToRefreshBase.getLoadingLayoutProxy()
                        .setLastUpdatedLabel(label);
                pageIndex++;
                setList(pageIndex);
            }
        });

    }

    /**
     * 拉取列表数据
     *
     * @param index
     */
    private void setList(final int index) {
        UserApi api = ApiClient.create(UserApi.class);
        final CustomDialogFragment customDialog = new CustomDialogFragment();
        customDialog.show(getSupportFragmentManager(), "");

        api.findGroupUserMessageList(mUserHelper.getSid(), index, new HttpCallback<MessageTo<List<UserMessageTo>>>(getThisContext()) {
            @Override
            public void success(MessageTo<List<UserMessageTo>> msg, Response response) {
                customDialog.dismiss();
                if (msg.getSuccess() == 0) {
                    if (index == 0) {
                        userMessageList.clear();
                    }
                    System.out.println(msg.getData()+"data");

                    userMessageList.addAll(msg.getData());
                    messageAdapter.notifyDataSetChanged();
                    System.out.println(userMessageList.toString() + "ccccccc");
                    System.out.println(messageAdapter.toString() + "messageAdapter");
                    mPullToRefreshListView.onRefreshComplete();
                    mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            UserMessageTo messageTo = userMessageList.get(position - 1);
                            Log.i("2222", "onItemClick: "+messageTo.getMsgSourceTag());
                            Intent intent = null;
                            if (TextUtils.equals("1", messageTo.getMsgSourceTag())) {
                                Log.i("222", "1");

                                intent = new Intent(getThisContext(), ReceiveTaskDetailActivity.class);
                                intent.putExtra("sid", messageTo.getMsgSourceSid());
                                startActivity(intent);

                            } else if (TextUtils.equals("6", messageTo.getMsgSourceTag())) {
                                Log.i("222", "2");

                                intent = new Intent(getThisContext(), CallDetailActivity.class);
                                intent.putExtra("sid", messageTo.getMsgSourceSid());
                                startActivity(intent);

                            }else{
                                return;
                            }

                        }
                    });
                    mList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                        @Override
                        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                            UserMessageTo msg = userMessageList.get(position - 1);
                            deleteDialogShow(msg.getMsgSid());

                            return false;
                        }
                    });
                } else {
                    Toast.makeText(getThisContext(), msg.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                customDialog.dismiss();
                super.failure(error);
            }
        });


    }

    /**
     * 删除对话框
     *
     * @param id
     */
    private void deleteDialogShow(final String id) {
        final CustomDialog dialog = new CustomDialog(this, R.layout.dialog_detele_msg, R.style.myDialogTheme);
        Button btnAdd = (Button) dialog.findViewById(R.id.btn_add);
        Button btnCancel = (Button) dialog.findViewById(R.id.btn_cancel);
        //取消
        btnCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        //确定
        btnAdd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                UserApi api = ApiClient.create(UserApi.class);
                api.msgDel(id, new HttpCallback<Void>(getThisContext()) {
                    @Override
                    public void success(Void aVoid, Response response) {
                        setList(0);
                        dialog.dismiss();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        super.failure(error);
                    }
                });
            }
        });

        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                onBackPressed();
                break;
            case R.id.changePark:
                changePark();
                break;
            default:
                break;
        }
    }

    @Override
    protected Context getThisContext() {
        return MessageCenterActivity.this;
    }

    @Override
    protected String toPageName() {
         super.toPageName();
        return "消息中心";
    }
    private void changePark() {
        if (!firstChange){
            ChangeParkUtil.changeToPark(getThisContext(), mUserHelper);
            changePark.setBackgroundResource(R.drawable.selector_home);
            firstChange=true;
            setList(0);
            mTitle.setText("消息中心(园区)");
        }else {
            ChangeParkUtil.changeToHome(getThisContext(), mUserHelper);
            changePark.setBackgroundResource(R.drawable.selector_park);
            firstChange = false;
            setList(0);
            mTitle.setText("消息中心(住宅)");
        }
    }
}
