package com.joy.property.neighborhood;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.Layout;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.house.AllHouseMenuTo;
import com.jinyi.ihome.module.neighbor.NeighborCommentHandleParam;
import com.jinyi.ihome.module.neighbor.NeighborCommentParam;
import com.jinyi.ihome.module.neighbor.NeighborCommentTo;
import com.jinyi.ihome.module.neighbor.NeighborJoinMessageParam;
import com.jinyi.ihome.module.neighbor.NeighborLikeParam;
import com.jinyi.ihome.module.neighbor.NeighborLikeTo;
import com.jinyi.ihome.module.neighbor.NeighborPostTo;
import com.jinyi.ihome.module.neighbor.NeighborhoodUserConnectParam;
import com.jinyi.ihome.module.neighbor.NeighborhoodUserConnectTo;
import com.jinyi.ihome.module.neighbor.OwnerMessageTo;
import com.joy.common.api.ApiClient;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.NeighborApi;
import com.joy.common.helper.UserInfoHelper;
import com.joy.library.utils.DateUtil;

import com.joy.property.R;
import com.joy.property.common.LongPictureShowActivity;
import com.joy.property.common.PictureShowActivity;
import com.joy.property.neighborhood.adapter.MyNeighborAdapter;
import com.joy.property.utils.ACache;
import com.joy.property.utils.ChangeParkUtil;
import com.joy.property.utils.CustomDialog;
import com.joy.property.utils.SpUtil;
import com.joy.property.base.BaseActivity;


import com.joyhome.nacity.app.util.CustomDialogFragment;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by xz on 2016/10/31.
 **/
public class MyNeighborActivity extends BaseActivity implements MyNeighborAdapter.INeighborPostTo, View.OnClickListener {
    private PullToRefreshListView pullToRefreshListView;
    private TextView investigateContent;
    private List<NeighborPostTo> postList = new ArrayList<>();

    private EditText mContent;
    private int pageIndex = 0;
    private boolean havesend;
    private MyNeighborAdapter mAdapter;
    private boolean isChange=false;
    private TextView textView;
    private ListView listView;
    private OwnerMessageTo messageTo;
    private TextView titleText;
    private TextView commentContent;
    private PopupWindow mPopupWindow;
    private RelativeLayout neighborShare;
    private boolean isRefresh;
    private ImageView changePark;
    private boolean firstChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investigate);
        initView();

    refreshData();
    }

    private void initIntent() {
        if (!getIntent().getBooleanExtra("otherMainPage",false)){
           getCarePoint();
            getJoinRedPoint();
            getFansPoint();

        }

    }


    private void initView() {
        pullToRefreshListView = (PullToRefreshListView) findViewById(R.id.listView);
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        listView=pullToRefreshListView.getRefreshableView();
        SpUtil.put(getThisContext(), "BackRefresh", false);
        investigateContent = (TextView) findViewById(R.id.investigateContent);
        textView = (TextView) findViewById(R.id.content);
        findViewById(R.id.confirmJoin).setVisibility(View.GONE);
        findViewById(R.id.iv_back).setOnClickListener(this);
        titleText = (TextView) findViewById(R.id.titleText);
        commentContent = (TextView) findViewById(R.id.commentContent);
        neighborShare = (RelativeLayout) findViewById(R.id.neighborShare);
        changePark = (ImageView) findViewById(R.id.changePark);
        changePark.setOnClickListener(this);
        if (SpUtil.getString(getThisContext(), "limitPark")!=null&&SpUtil.getString(getThisContext(),"limitPark").contains("A009")&&getIntent().getBooleanExtra("isMain", false))
        {
            changePark.setVisibility(View.VISIBLE);

        }
        messageTo = (OwnerMessageTo) getIntent().getSerializableExtra("messageTo");
        System.out.println(messageTo.toString() + "messageTo11111");
    }
    private void refreshData() {
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageIndex = 0;
                setList(0);
                isRefresh=true;
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageIndex++;
                setList(pageIndex);
                isRefresh=true;
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);


if (!isChange) {
    System.out.println(messageTo+"messageTo-------------------");
    if (messageTo!=null&&messageTo.getFamilyName()!=null)
    titleText.setText(messageTo.getFamilyName() + "的主页");
    isChange=true;

    if (!getIntent().getBooleanExtra("otherMainPage",false))
        messageTo.setFamilyName(mUserHelper.getUserInfoTo().getFamilyName());
        mAdapter = new MyNeighborAdapter(getThisContext(),getIntent().getBooleanExtra("otherMainPage",false), messageTo,getIntent().getStringExtra("type"));
        mAdapter.setOnNeighborPost(MyNeighborActivity.this);
        setList(0);
    if (messageTo==null){
        messageTo=new OwnerMessageTo();
    mUserHelper=UserInfoHelper.getInstance(getThisContext());
    messageTo.setFamilyName(mUserHelper.getUserInfoTo().getFamilyName());
    messageTo.setOwnerImage(mUserHelper.getUserInfoTo().getImage());
        messageTo.setOwnerSid(mUserHelper.getUserInfoTo().getSid());
    System.out.println(messageTo.getOwnerImage()+"ownerImage");
    }
        mAdapter.setList(postList);
        listView.setAdapter(mAdapter);
        listView.setDividerHeight((int) (getScreenWidth() * 0.277));
         listView.setDivider(getResources().getDrawable(R.drawable.divide_bg));
        }

    }

    private void setList(final int index) {
        mUserHelper= UserInfoHelper.getInstance(getThisContext());

            if(JSON.parseArray(new ACache().getAsString("MyMainPage"),AllHouseMenuTo.class)!=null&&!getIntent().getBooleanExtra("otherMainPage",false))
                postList=JSON.parseArray(new ACache().getAsString("MyMainPage"),NeighborPostTo.class);
            NeighborApi api = ApiClient.create(NeighborApi.class);
            final CustomDialogFragment customDialog = new CustomDialogFragment();
            if (postList!=null&&postList.size()>0)
            customDialog.show(getSupportFragmentManager(), "");
        System.out.println(SpUtil.getBoolean(getThisContext(),"ParkNeighbor")?"":mHelper.getSid()+"--sid");
            api.getAllPost(SpUtil.getBoolean(getThisContext(),"ParkNeighbor")?"":mHelper.getSid(), messageTo.getOwnerSid(), mUserHelper.getSid(), index,
                    new HttpCallback<MessageTo<List<NeighborPostTo>>>(getThisContext()) {
                        @Override
                        public void success(MessageTo<List<NeighborPostTo>> msg, Response response) {
                            if (msg.getSuccess() == 0) {
                                customDialog.dismissAllowingStateLoss();
                                if (index == 0) {
                                    postList.clear();
                                }
                                SpUtil.put(getThisContext(), "BackRefresh", false);
                                initIntent();
                                isRefresh = false;

                                postList.addAll(msg.getData());
                                postList = getSubList(postList);
                                messageTo.setFlag(msg.getTotal());
                                if (postList != null && postList.size() > 0) {
                                    if (!TextUtils.isEmpty(mHelper.getSid())&&!mHelper.getSid().equals(postList.get(0).getApartmentSid()))
                                        messageTo.setFlag(2);
                                }


                                mAdapter.setList(postList);

                                setListComplete();

                                mAdapter.notifyDataSetChanged();
                                new ACache().put("MyMainPage", JSON.toJSONString(postList));
                            } else {
                                Toast.makeText(getThisContext(),
                                        msg.getMessage(), Toast.LENGTH_LONG).show();
                                customDialog.dismissAllowingStateLoss();
                            }
                            setListComplete();
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            setListComplete();
                            customDialog.dismissAllowingStateLoss();
                        }
                    });

        }


    private void setListComplete() {
        pullToRefreshListView.onRefreshComplete();
    }
    @Override
    public void postComment(final NeighborPostTo item, final NeighborCommentTo mItem) {
        Intent intent=new Intent(getThisContext(), PostDetailActivity.class);
        intent.putExtra("postSid", item.getPostSid());
        startActivity(intent);
    }

    /**
     * 点赞
     *
     * @param item
     */
    @Override
    public void postPraise(final NeighborPostTo item) {

        NeighborLikeParam likeParam = new NeighborLikeParam();
        likeParam.setPostSid(item.getPostSid());
        likeParam.setLikeOwnerSid(mUserHelper.getSid());
        NeighborApi api = ApiClient.create(NeighborApi.class);
        final CustomDialogFragment dialogFragment = new CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "");
        api.addNeighborLike(likeParam, new HttpCallback<MessageTo<NeighborLikeTo>>(getThisContext()) {
            @Override
            public void success(MessageTo<NeighborLikeTo> msg, Response response) {
                dialogFragment.dismissAllowingStateLoss();
                if (msg.getSuccess() == 0) {
                    List<NeighborLikeTo> likeToList = item.getLikeList();
                    if (likeToList == null)
                        likeToList = new ArrayList<>();
                    if (msg.getData() != null) {


                        likeToList.add(msg.getData());
                        item.setLikeList(likeToList);
                    } else {
                        List<NeighborLikeTo> likeList = item.getLikeList();

                        Iterator<NeighborLikeTo> iterator = likeList.iterator();
                        while (iterator.hasNext()) {
                            NeighborLikeTo likeTo = iterator.next();
                            if (likeTo.getLikeOwner() != null && mUserHelper.getSid().equals(likeTo.getLikeOwner().getSid())) {
                                iterator.remove();
                            }
                        }
                        item.setLikeList(likeList);
                    }
                    mAdapter.notifyDataSetChanged();

                } else {
                    Toast.makeText(getThisContext(), msg.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                dialogFragment.dismissAllowingStateLoss();
                super.failure(error);
            }
        });

    }


    /**
     * 删除帖子
     *
     * @param item
     */
    @Override
    public void postDeleteItem(final NeighborPostTo item) {
        final CustomDialog dialog = new CustomDialog(getThisContext(), R.layout.dialog_neighbor_post, R.style.myDialogTheme);
        Button btnAdd = (Button) dialog.findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NeighborApi api = ApiClient.create(NeighborApi.class);
                api.delNeighborPost(item.getPostSid(), new HttpCallback<MessageTo<Void>>(getThisContext()) {
                    @Override
                    public void success(MessageTo<Void> msg, Response response) {
                        if (msg.getSuccess() == 0) {

                                setList(pageIndex);


                            dialog.dismiss();
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
        });
        Button btnCancel = (Button) dialog.findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setCancelable(false);
        dialog.show();

    }

    @Override
    public void enterMainPage(NeighborPostTo item) {

    }

    @Override
    public void enterCommentMainPage(NeighborCommentTo item) {
        if (mUserHelper.getSid().equals(item.getCommentOwner().getSid()))
            return;
        Intent intent=new Intent(getThisContext(), MyNeighborActivity.class);

        intent.putExtra("otherMainPage",true);

        intent.putExtra("otherSid", item.getCommentOwner().getSid());
        OwnerMessageTo messageTo=new OwnerMessageTo();
        messageTo.setOwnerSid(item.getCommentOwner().getSid());
        messageTo.setFamilyName(item.getCommentOwner().getName());
        messageTo.setOwnerImage(item.getCommentOwner().getIcon());
        intent.putExtra("messageTo", messageTo);
        startActivity(intent);

        goToAnimation(1);

    }

    @Override
    public void postCare(ImageView careOther, TextView cancelOther,int flag) {

if (flag==1)
    cancelCare(careOther,cancelOther);
        else
        getCare(careOther,cancelOther);
    }


    @Override
    public void postViewImage(int index, String item,boolean isHeadView) {
        if (TextUtils.isEmpty(item)) {
            return;
        }
        Intent intent;
        if (!isHeadView)
     intent = new Intent(getThisContext(), LongPictureShowActivity.class);
        else
        intent=new Intent(getThisContext(), PictureShowActivity.class);
        intent.putExtra("index", index);
        intent.putExtra("path", item);
        startActivity(intent);
    }

    @Override
    public void enterPostDetail(NeighborPostTo item) {
        Intent intent=new Intent(getThisContext(), PostDetailActivity.class);
        intent.putExtra("postSid",item.getPostSid());
        startActivity(intent);
    }

    private void deleteComment(final NeighborCommentTo mItem) {
        final CustomDialog dialog = new CustomDialog(getThisContext(), R.layout.delete_comment, R.style.myDialogTheme);
        Button btnAdd = (Button) dialog.findViewById(R.id.btn_add);
        Button btnCancel = (Button) dialog.findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(v -> dialog.dismiss());
        btnAdd.setOnClickListener(v -> {
            deleteCommentContent(mItem);
            dialog.dismiss();
        });
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        dialog.show();
    }
    public void deleteCommentContent(final NeighborCommentTo mItem){
        if (!TextUtils.isEmpty(mHelper.getSid())) {
            NeighborApi api = ApiClient.create(NeighborApi.class);
            final CustomDialogFragment customDialog = new CustomDialogFragment();
            customDialog.show(getSupportFragmentManager(), "");
            api.deleteComment(mItem.getCommentSid(),
                    new HttpCallback<MessageTo<List<NeighborCommentHandleParam>>>(getThisContext()) {
                        @Override
                        public void success(MessageTo<List<NeighborCommentHandleParam>> msg, Response response) {
                            if (msg.getSuccess() == 0) {
                                customDialog.dismissAllowingStateLoss();
                                setList(pageIndex);

                            }
                        }

                        @Override
                        public void failure(RetrofitError error) {

                            customDialog.dismissAllowingStateLoss();
                            super.failure(error);
                        }
                    });
        }
    }
    public String getSubContent(TextView tv,String content){


        tv.setText(content);
        Layout layout=tv.getLayout();
        int line=tv.getLayout().getLineCount();
        if (line<3){
            return content;
        }else {

            String result = "";
            String text = layout.getText().toString();
            for (int i = 0; i < 3; i++) {
                int start = layout.getLineStart(i);
                int end = layout.getLineEnd(i);
                result += text.substring(start, end);
            }

            System.out.println(result + "result");
            return result.substring(0,result.length());
        }
    }


public void cancelCare(ImageView care,TextView cancel){
    NeighborApi api= ApiClient.create(NeighborApi.class);
    final CustomDialogFragment customDialog = new CustomDialogFragment();
    customDialog.show(getSupportFragmentManager(), "");
    System.out.println(mUserHelper.getSid() + "/" + getIntent().getStringExtra("otherSid"));
    api.deleteCare(mUserHelper.getSid(), getIntent().getStringExtra("otherSid"), new HttpCallback<MessageTo<Boolean>>(getThisContext()) {
        @Override
        public void success(MessageTo<Boolean> msg, Response response) {
            customDialog.dismiss();
            if (msg.getSuccess() == 0) {
                cancel.setVisibility(View.GONE);
                care.setVisibility(View.VISIBLE);
                ToastShowLong(getThisContext(), "取消关注成功");
            } else
                ToastShowLong(getThisContext(), msg.getMessage());
        }

        @Override
        public void failure(RetrofitError error) {
            System.out.println(error.toString());
            customDialog.dismiss();
        }
    });
}
    public void getCare(ImageView care,TextView cancel){
        NeighborApi api= ApiClient.create(NeighborApi.class);
        final CustomDialogFragment customDialog = new CustomDialogFragment();
        customDialog.show(getSupportFragmentManager(), "");
        NeighborhoodUserConnectParam param=new NeighborhoodUserConnectParam();
        param.setOwnerSid(mUserHelper.getSid());
        param.setToOwnerSid(getIntent().getStringExtra("otherSid"));
        api.getCare(param, new HttpCallback<MessageTo<NeighborhoodUserConnectTo>>(getThisContext()) {
            @Override
            public void success(MessageTo<NeighborhoodUserConnectTo> msg, Response response) {
                customDialog.dismiss();
                System.out.println(msg.toString());
                ToastShowLong(getThisContext(), "关注成功");
                care.setVisibility(View.GONE);
                cancel.setVisibility(View.VISIBLE);
            }

            @Override
            public void failure(RetrofitError error) {
                super.failure(error);
                System.out.println(error.toString());
            }
        });
    }
    public void getJoinRedPoint(){
        NeighborApi api= ApiClient.create(NeighborApi.class);
        NeighborJoinMessageParam param=new NeighborJoinMessageParam();
        param.setPageIndex(0);
        param.setApartmentSid(mHelper.getSid());
        param.setPostOwner(mUserHelper.getSid());
        if (SpUtil.getString(getThisContext(), "neighborJoinTime")==null)
        param.setLastTime(DateUtil.getDate());
        else
            param.setLastTime(DateUtil.getFormatDateLongTime(SpUtil.getString(getThisContext(), "neighborJoinTime")));
        System.out.println(param.toString() + "join");
        api.getJoinRedPoint(param, new HttpCallback<MessageTo<List<NeighborPostTo>>>(getThisContext()) {
            @Override
            public void success(MessageTo<List<NeighborPostTo>> msg, Response response) {
                System.out.println(msg + "join");
                if (msg.getTotal() > 0)
                    mAdapter.setJoinPoint(true);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
    public void getCarePoint(){
        NeighborApi api= ApiClient.create(NeighborApi.class);
        NeighborJoinMessageParam param=new NeighborJoinMessageParam();
        param.setPageIndex(0);
        param.setApartmentSid(mHelper.getSid());
        param.setPostOwner(mUserHelper.getSid());
        if (SpUtil.getString(getThisContext(),"neighborCareTime")==null)
            param.setLastTime(DateUtil.getDate());
        else
            param.setLastTime(DateUtil.getFormatDateLongTime(SpUtil.getString(getThisContext(), "neighborCareTime")));
        System.out.println(param.toString()+"care");
   api.getCareRedPoint(param, new HttpCallback<MessageTo<Integer>>(getThisContext()) {
       @Override
       public void success(MessageTo<Integer> msg, Response response) {
           System.out.println(msg + "msg---------------------");
           if (msg.getTotal() > 0) {
               mAdapter.setCarePoint(true);

           }
       }

       @Override
       public void failure(RetrofitError error) {
           System.out.println(error.toString());
       }
   });

    }
    public void getFansPoint(){
        NeighborApi api= ApiClient.create(NeighborApi.class);
        NeighborJoinMessageParam param=new NeighborJoinMessageParam();
        param.setPageIndex(0);
        param.setApartmentSid(mHelper.getSid());
        param.setPostOwner(mUserHelper.getSid());
        if (SpUtil.getString(getThisContext(),"neighborFansTime")==null)
            param.setLastTime(DateUtil.getDate());
        else
            param.setLastTime(DateUtil.getFormatDateLongTime(SpUtil.getString(getThisContext(), "neighborFansTime")));
        System.out.println(param.toString()+"fans");
    api.getFansRedPoint(param, new HttpCallback<MessageTo<Integer>>(getThisContext()) {
        @Override
        public void success(MessageTo<Integer> msg, Response response) {
            System.out.println(msg + "Fans");
            if (msg.getTotal() > 0)
                mAdapter.setInvestigatePoint(true);
        }

        @Override
        public void failure(RetrofitError error) {
            System.out.println(error.toString());
        }
    });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (SpUtil.getString(getThisContext(),"neighborJoinTime")==null){
            SpUtil.put(getThisContext(), "neighborJoinTime", DateUtil.getDateString());
        }
        if (SpUtil.getString(getThisContext(),"neighborCareTime")==null){
            SpUtil.put(getThisContext(), "neighborCareTime", DateUtil.getDateString());
        }
        if (SpUtil.getString(getThisContext(),"neighborFansTime")==null){
            SpUtil.put(getThisContext(), "neighborFansTime", DateUtil.getDateString());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.neighborShare:
                showPopupWindow();
                break;
            case R.id.changePark:
                changePark();
                break;
        }
    }
    public List<NeighborPostTo> getSubList(List<NeighborPostTo> postList){

        for (int i=0;i<postList.size();i++) {
            if (!TextUtils.isEmpty(postList.get(i).getRefId()))
                postList.get(i).setPostSubject(postList.get(i).getTopicTitle());
            if (!TextUtils.isEmpty(postList.get(i).getPostSubject()))
                postList.get(i).setPostSubject("#"+postList.get(i).getPostSubject()+"# ");
            if (!TextUtils.isEmpty(postList.get(i).getPostSubject())){
                if (postList.get(i).getPostContent() != null && !(postList.get(i).getPostSubject()+postList.get(i).getPostContent()).equals(getSubContent(textView,postList.get(i).getPostSubject()+ postList.get(i).getPostContent())))
                    postList.get(i).setSubPostContent((getSubContent(textView, postList.get(i).getPostSubject() + postList.get(i).getPostContent())).substring(0, getSubContent(textView, postList.get(i).getPostSubject() + postList.get(i).getPostContent()).length() - 4));
                else
                    postList.get(i).setSubPostContent(null);
            }
            else {
                if (postList.get(i).getPostContent() != null && !(postList.get(i).getPostContent()).equals(getSubContent(textView, postList.get(i).getPostContent())))
                    postList.get(i).setSubPostContent(getSubContent(textView, postList.get(i).getPostContent()).substring(0, getSubContent(textView, postList.get(i).getPostContent()).length() - 4));
                else
                    postList.get(i).setSubPostContent(null);

            }
        }
        String subContent;
        for (int j=0;j<postList.size();j++) {
            if (postList.get(j).getCommentList() != null) {
                for (int i = 0; i < postList.get(j).getCommentList().size(); i++) {
                    NeighborCommentTo commentTo = postList.get(j).getCommentList().get(i);
                    subContent = getSubComment(commentContent, commentTo.getCommentContent());
                    if (!subContent.equals(commentTo.getCommentContent()))
                        postList.get(j).getCommentList().get(i).setSubComment(subContent);

                }
            }
        }
        return postList;
    }
    public String getSubComment(TextView tv,String content){


        tv.setText(content);
        Layout layout=tv.getLayout();
        if (tv.getLayout()==null)
            return content;
        int line=tv.getLayout().getLineCount();

        if (line<3){
            return content;
        }else {

            String result = "";
            String text = layout.getText().toString();
            for (int i = 0; i < 3; i++) {
                int start = layout.getLineStart(i);
                int end = layout.getLineEnd(i);
                result += text.substring(start, end);
            }


            return result.substring(0,result.length()-4);
        }
    }
    public void showPopupWindow(){
        View popupView = getLayoutInflater().inflate(R.layout.popup_neighbor_share, null);
        mPopupWindow = new PopupWindow(popupView, (int) (getScreenWidth() * 0.2277), LinearLayout.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setAnimationStyle(R.style.expand);

        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));


        mPopupWindow.showAsDropDown(neighborShare, (int) (getScreenWidth() * 0.7611), 0);
        LinearLayout share = (LinearLayout) popupView.findViewById(R.id.share);
        //   TextView report = (TextView) popupView.findViewById(R.id.report);
        share.setOnClickListener(v -> {


            mPopupWindow.dismiss();

        });


    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            if (mPopupWindow!=null&&mPopupWindow.isShowing())
            mPopupWindow.dismiss();

        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (SpUtil.getBoolean(getThisContext(), "BackRefresh"))
            setList(pageIndex);
    }
    private void changePark() {
        if (!firstChange){
            ChangeParkUtil.changeToPark(getThisContext(), mUserHelper);
            changePark.setBackgroundResource(R.drawable.selector_home);
            firstChange=true;
setList(0);

        }else {
            ChangeParkUtil.changeToHome(getThisContext(), mUserHelper);
            changePark.setBackgroundResource(R.drawable.selector_park);
            firstChange=false;
            setList(0);

        }
    }
}
