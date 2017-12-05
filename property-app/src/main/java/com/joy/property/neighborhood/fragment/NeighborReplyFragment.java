package com.joy.property.neighborhood.fragment;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.text.Layout;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.house.HouseValueTypeTo;
import com.jinyi.ihome.module.neighbor.NeighborCommentAndLikeParam;
import com.jinyi.ihome.module.neighbor.NeighborCommentHandleParam;
import com.jinyi.ihome.module.neighbor.NeighborCommentTo;
import com.jinyi.ihome.module.neighbor.NeighborLikeParam;
import com.jinyi.ihome.module.neighbor.NeighborLikeTo;
import com.jinyi.ihome.module.neighbor.NeighborPostTo;
import com.jinyi.ihome.module.neighbor.NeighborhoodLogParam;
import com.jinyi.ihome.module.neighbor.NeighborhoodLogTo;
import com.jinyi.ihome.module.neighbor.OwnerMessageTo;
import com.joy.common.api.ApiClient;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.NeighborApi;
import com.joy.common.helper.ApartmentInfoHelper;
import com.joy.common.helper.UserInfoHelper;
import com.joy.library.utils.DateUtil;
import com.joy.property.R;
import com.joy.property.base.BaseFragment;
import com.joy.property.common.LongPictureShowActivity;
import com.joy.property.neighborhood.MyNeighborActivity;
import com.joy.property.neighborhood.PostActivity;
import com.joy.property.neighborhood.PostDetailActivity;
import com.joy.property.neighborhood.adapter.NeighborPostToAdapter;
import com.joy.property.utils.ACache;
import com.joy.property.utils.CustomDialog;
import com.joy.property.utils.SpUtil;
import com.joyhome.nacity.app.MainApp;
import com.joyhome.nacity.app.util.CustomDialogFragment;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by xzz on 2016/3/11.
 **/
@SuppressLint("ValidFragment")
public class NeighborReplyFragment extends BaseFragment implements View.OnClickListener, NeighborPostToAdapter.INeighborPostTo {
    private View self;

    private PullToRefreshListView mPullToRefreshListView;
    private int pageIndex = 0;
    private List<NeighborPostTo> postList = new ArrayList<>();
    private List<NeighborPostTo> SortPostList = new ArrayList<>();
    private NeighborPostToAdapter mAdapter = null;
    private EditText mContent;
    private ListView mListView;
    private boolean init=false;
    private  int id;
    private   Context context;
    protected ApartmentInfoHelper mHelper;
    protected UserInfoHelper mUserHelper ;
    protected SharedPreferences sp ;
    private  String typeName;
    private Boolean havesend;
    private TextView content;
    private TextView textView;
    private View headView;
    private ConvenientBanner lbt;
    private TextView commentContent;
    private boolean isRefresh;
    private CustomDialog dialog;
    private String lastType="first";
    private boolean isPark;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }
    public NeighborReplyFragment(){

    }

    public NeighborReplyFragment(TextView textView, TextView commentContent){
        this.textView=textView;
        this.commentContent=commentContent;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (this.self == null) {
            this.self = inflater.inflate(R.layout.neighborhoodview, null);
        }
        if (this.self.getParent() != null) {
            ViewGroup parent = (ViewGroup) this.self.getParent();
            parent.removeView(this.self);
        }
        findById();
        mHelper = ApartmentInfoHelper.getInstance(MainApp.mContext);
        mUserHelper = UserInfoHelper.getInstance(MainApp.mContext);
        sp = PreferenceManager.getDefaultSharedPreferences(getThisContext());
        this.context=container.getContext();
        mPullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        mListView = mPullToRefreshListView.getRefreshableView();
        registerForContextMenu(mListView);
        mAdapter = new NeighborPostToAdapter(context);
        mAdapter.setOnNeighborPost(NeighborReplyFragment.this);
        mAdapter.setList(postList);
        mListView.setAdapter(mAdapter);
        refreshData();


        return self;
    }
    public void init(String type){

        if (!lastType.equals(type)) {
            if ("".equals(type))
                setList2(0);
            else
                setListSort(0, Integer.valueOf(type), false);
        }
        lastType=type;
    }

    private void findById() {

        mPullToRefreshListView = (PullToRefreshListView)  self.findViewById(R.id.list);
        content = (TextView) self.findViewById(R.id.content);
        //  SpUtil.put(getThisContext(), "canRefreshAdapter", false);
        SpUtil.put(getThisContext(),"BackRefresh",false);
    }

    @Override
    public void onResume() {
        super.onResume();
        applyScrollListener();
        if (lbt!=null)
            lbt.startTurning(2000);
    }

    private void applyScrollListener() {
        mListView.setOnScrollListener(new PauseOnScrollListener(ImageLoader.getInstance(), false, true));
    }


    // 上下拉刷新
    private void refreshData() {


        /**
         * 刷新监听
         */
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
//                EventBus.getDefault().post(new RefreshEvent("jjj"));
                pageIndex = 0;
                if (id == 0)
                    setList(0);
                else
                    setListSort(0, id, false);
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
                listViewPullToRefreshBase.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                pageIndex++;
                isRefresh = true;
                if (id == 0)
                    setList(pageIndex);
                else
                    setListSort(pageIndex, id, false);
            }
        });
    }

    private void setList(final int index) {
        String json = SpUtil.getString(getThisContext(), "Neighborhood_Cache");
        if (json!= null) {
            try {
                JSONObject obj = new JSONObject(json);
                String js = obj.getString("Neighborhood_Cache");
                List<NeighborPostTo> homeNoticeToList1 = JSON.parseArray(js, NeighborPostTo.class);
                postList.addAll(homeNoticeToList1);

                mAdapter.setList(postList);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


            NeighborApi api = ApiClient.create(NeighborApi.class);
            NeighborCommentAndLikeParam param=new NeighborCommentAndLikeParam();
            param.setPostOwner(mUserHelper.getSid());
            param.setApartmentSid(mHelper.getSid());
        if (TextUtils.isEmpty(mHelper.getSid()))
            param.setApartmentSid("30e1baa2-877f-48c8-92be-67d026752039");
            param.setPageIndex(index);
            param.setPostType("1");
            param.setType(1);
            if (SpUtil.getString(getThisContext(),"neighborReplyTime")!=null)
                param.setDateTime(DateUtil.getFormatDateLongTime(SpUtil.getString(getThisContext(), "neighborReplyTime")));
            else
                param.setDateTime(DateUtil.getDate());
            final CustomDialogFragment customDialog = new CustomDialogFragment();
            customDialog.show(getFragmentManager(), "");
            api.getNewReply(param, new HttpCallback<MessageTo<List<NeighborPostTo>>>(context) {
                @Override
                public void success(MessageTo<List<NeighborPostTo>> msg, Response response) {
                    if (msg.getSuccess() == 0) {

                        customDialog.dismissAllowingStateLoss();
                        if (index == 0 ) {
                            postList.clear();
                        }


                        if (msg.getData() != null)
                            postList.addAll(msg.getData());
                        if (postList.size()==0) {
                            setListNoCache(0);
                            return;
                        }
                        postList = deleteList(postList);
                        postList = getSubList(postList);
                        mAdapter.setList(postList);
                        mAdapter.notifyDataSetChanged();
                        setListComplete();
                        SpUtil.put(getThisContext(), "neighborReplyTime", DateUtil.getDateString());
                        SpUtil.put(getThisContext(),"BackRefresh",false);
                    } else {
                        customDialog.dismissAllowingStateLoss();
                        setListComplete();
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


    public void setList2(final int index) {
        id=0;
        String json = SpUtil.getString(getThisContext(), "Neighborhood_Cache");
        if (json!= null) {
            try {
                JSONObject obj = new JSONObject(json);
                String js = obj.getString("Neighborhood_Cache");
                List<NeighborPostTo> homeNoticeToList1 = JSON.parseArray(js, NeighborPostTo.class);
                postList.addAll(homeNoticeToList1);

                mAdapter.setList(postList);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


            NeighborCommentAndLikeParam param=new NeighborCommentAndLikeParam();
            param.setPostOwner(mUserHelper.getSid());
            param.setApartmentSid(mHelper.getSid());
        if (TextUtils.isEmpty(mHelper.getSid()))
            param.setApartmentSid("30e1baa2-877f-48c8-92be-67d026752039");
            param.setPageIndex(index);
            param.setPostType("1");
            param.setType(1);
            if (SpUtil.getString(getThisContext(),"neighborReplyTime")!=null)
                param.setDateTime(DateUtil.getFormatDateLongTime(SpUtil.getString(getThisContext(), "neighborReplyTime")));
            else
                param.setDateTime(DateUtil.getDate());
            NeighborApi api = ApiClient.create(NeighborApi.class);
            api.getNewReply(param, new HttpCallback<MessageTo<List<NeighborPostTo>>>(context) {

                @Override
                public void success(MessageTo<List<NeighborPostTo>> msg, Response response) {
                    System.out.println(msg+"msg");
                    if (msg.getSuccess() == 0) {

                        if (index == 0&&msg.getData()!=null&&msg.getData().size()>0) {
                            postList.clear();
                        }
                        if (msg.getData()!=null&&msg.getData().size()>0)
                            postList.addAll(msg.getData());
                        postList = deleteList(postList);
                        postList=getSubList(postList);


                        mAdapter.setList(postList);
                        if (postList.size()==0){
                            setListNoCache(0);

                            return;
                        }
                        mAdapter.notifyDataSetChanged();
                        setListComplete();
                        SpUtil.put(getThisContext(), "neighborReplyTime", DateUtil.getDateString());
                    } else {
                    }
                    setListComplete();

                }

                @Override
                public void failure(RetrofitError error) {

                    setListComplete();
                }
            });

        }



    private void setListComplete() {
        mPullToRefreshListView.onRefreshComplete();
    }


    @Override
    public void onClick(View v) {
        Intent intent1=new Intent(context,PostActivity.class);
        switch (v.getId()) {


            case R.id.ll_invite:
                neighborShowDialog();
                break;
            case R.id.rl_invite:
                neighborShowDialog();
                break;


        }
    }



    /**
     * 分享对话框
     */
    private void neighborShowDialog() {
        final CustomDialog dialog = new CustomDialog(context, R.layout.dialog_neighbor_share, R.style.myDialogTheme);
        ImageView close = (ImageView) dialog.findViewById(R.id.close);
        TextView mWeChat = (TextView) dialog.findViewById(R.id.wechat);
        TextView mQQ = (TextView) dialog.findViewById(R.id.QQ);
        final TextView mMsg = (TextView) dialog.findViewById(R.id.short_msg);
        TextView mMoment = (TextView) dialog.findViewById(R.id.moment);
      //  ShareSDK.initSDK(getThisContext());
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        final Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.share_ic);
//        mWeChat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Wechat.ShareParams wsp = new Wechat.ShareParams();
//                wsp.setShareType(Platform.SHARE_TEXT);
//                wsp.setText("悦嘉家，等你回家！http://dwz.cn/11N1DJ");
//                Platform weChat = ShareSDK.getPlatform(getThisContext(), Wechat.NAME);
//                weChat.share(wsp);
//
//            }
//        });

//        mQQ.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                QQ.ShareParams qsp = new QQ.ShareParams();
//                qsp.setShareType(Platform.SHARE_IMAGE);
//                qsp.setTitle("悦嘉家，等你回家");
//                qsp.setImagePath(UploadImage.getInstance(getThisContext()).saveBitmap(bitmap));
//                Platform qq = ShareSDK.getPlatform(QQ.NAME);
//                qq.share(qsp);
//            }
//        });
//        mMsg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ShortMessage.ShareParams msp = new ShortMessage.ShareParams();
//                msp.setShareType(Platform.SHARE_TEXT);
//                msp.setText("悦嘉家，等你回家！http://dwz.cn/11N1DJ");
//                Platform msg = ShareSDK.getPlatform(ShortMessage.NAME);
//                msg.share(msp);
//            }
//        });
//        mMoment.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                WechatMoments.ShareParams wsp = new WechatMoments.ShareParams();
//                wsp.setShareType(Platform.SHARE_IMAGE);
//                wsp.setTitle("悦嘉家等你回家");
//                wsp.setImageData(bitmap);
//                Platform moment = ShareSDK.getPlatform(WechatMoments.NAME);
//                moment.share(wsp);
//            }
//        });

        dialog.setCancelable(true);
        dialog.show();
    }


    @Override
    public void postComment(final NeighborPostTo item, final NeighborCommentTo mItem) {

        Intent intent=new Intent(getThisContext(), PostDetailActivity.class);
        intent.putExtra("postSid",item.getPostSid());
        intent.putExtra("BackType","Right");
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
        dialogFragment.show(getFragmentManager(), "");
        api.addNeighborLike(likeParam, new HttpCallback<MessageTo<NeighborLikeTo>>(context) {
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
                            if (mUserHelper.getSid().equals(likeTo.getLikeOwner().getSid())) {
                                iterator.remove();
                            }
                        }
                        item.setLikeList(likeList);
                    }
                    mAdapter.notifyDataSetChanged();

                } else {
                }
            }

            @Override
            public void failure(RetrofitError error) {
                dialogFragment.dismissAllowingStateLoss();

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
        final CustomDialog dialog = new CustomDialog(context, R.layout.dialog_neighbor_post, R.style.myDialogTheme);
        Button btnAdd = (Button) dialog.findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NeighborApi api = ApiClient.create(NeighborApi.class);
                api.delNeighborPost(item.getPostSid(), new HttpCallback<MessageTo<Void>>(getThisContext()) {
                    @Override
                    public void success(MessageTo<Void> msg, Response response) {
                        if (msg.getSuccess() == 0) {
                            if (id == 0)
                                setList(pageIndex);
                            else
                                setListSort(pageIndex, id, false);

                            dialog.dismiss();
                        } else {
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {

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
        Intent intent=new Intent(getThisContext(), MyNeighborActivity.class);
        if (item.getPostOwner()!=null&&!mUserHelper.getSid().equals(item.getPostOwner().getSid()))
            intent.putExtra("otherMainPage",true);
        else
            intent.putExtra("otherMainPage",false);
        if (!mHelper.getSid().equals(item.getApartmentSid()))
            intent.putExtra("apartmentSid",item.getApartmentSid());
        intent.putExtra("otherSid", item.getPostOwner().getSid());
        OwnerMessageTo messageTo=new OwnerMessageTo();
        messageTo.setOwnerSid(item.getPostOwner().getSid());
        messageTo.setFamilyName(item.getPostOwner().getName());
        messageTo.setOwnerImage(item.getPostOwner().getIcon());
        intent.putExtra("messageTo", messageTo);
        startActivity(intent);


    }

    @Override
    public void enterPostDetail(NeighborPostTo item) {
        Intent intent=new Intent(getThisContext(), PostDetailActivity.class);
        intent.putExtra("postSid", item.getPostSid());
        startActivity(intent);
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


    }

    @Override
    public void postViewImage(int index, String item) {
        if (TextUtils.isEmpty(item)) {
            return;
        }
        Intent intent = new Intent(context, LongPictureShowActivity.class);
        intent.putExtra("index", index);
        intent.putExtra("path", item);
        startActivity(intent);
    }



    @Override
    public void onDestroy() {

        super.onDestroy();
        if (mAdapter != null && mAdapter.getListView()!=null) {
            mAdapter.getListView().destroyDrawingCache();
        }
//EventBus.getDefault().unregister(MyFragment.this);
    }


    private void deleteComment(final NeighborCommentTo mItem) {
        final CustomDialog dialog = new CustomDialog(context, R.layout.delete_comment, R.style.myDialogTheme);
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
            customDialog.show(getFragmentManager(), "");
            api.deleteComment(mItem.getCommentSid(),
                    new HttpCallback<MessageTo<List<NeighborCommentHandleParam>>>(context) {
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

                        }
                    });
        }
    }

    public void setTop(){
        mListView.setSelection(1);
    }
    public void setListSort(final int index,final int idType,final boolean isSelectRefresh) {
        postList.clear();
        id=idType;
        if(JSON.parseArray(new ACache().getAsString("neighborSortCache"+id),HouseValueTypeTo.class)!=null) {
            postList = JSON.parseArray(new ACache().getAsString("neighborSortCache" + id), NeighborPostTo.class);
            mAdapter.setList(postList);
            mAdapter.notifyDataSetChanged();
        }

            NeighborApi api = ApiClient.create(NeighborApi.class);
            NeighborCommentAndLikeParam param=new NeighborCommentAndLikeParam();
            param.setPostOwner(mUserHelper.getSid());
            param.setApartmentSid(mHelper.getSid());
            if (TextUtils.isEmpty(mHelper.getSid()))
                param.setApartmentSid("30e1baa2-877f-48c8-92be-67d026752039");
            param.setPageIndex(index);
            param.setPostType(id + "");
            param.setType(2);
            if (SpUtil.getString(getThisContext(),id+"neighborReplyTime")!=null)
                param.setDateTime(DateUtil.getFormatDateLongTime(SpUtil.getString(getThisContext(), id + "neighborReplyTime")));
            else
                param.setDateTime(DateUtil.getDate());
            final CustomDialogFragment customDialog = new CustomDialogFragment();
            if(postList==null)
                customDialog.show(getFragmentManager(), "");
            if (param.getDateTime()==null)
                param.setDateTime(DateUtil.getDate());
            System.out.println(param+"param");
            api.getNewReply(param, new HttpCallback<MessageTo<List<NeighborPostTo>>>(getThisContext()) {
                @Override
                public void success(MessageTo<List<NeighborPostTo>> msg, Response response) {
                    if (msg.getSuccess() == 0) {
                        isRefresh=false;
                        if (index == 0) {

                            postList.clear();
                        }
                        customDialog.dismissAllowingStateLoss();
                        if (msg.getData()!=null)
                            postList.addAll(msg.getData());
                        postList = deleteList(postList);
                        mAdapter.setList(postList);
                        if (postList.size()==0) {
                            setListSortNoCache(0, id, false);
                            return;
                        }
                        mAdapter.notifyDataSetChanged();
                        setListComplete();
                        new ACache().put("neighborSortCache" + id, JSON.toJSONString(postList));
                        SpUtil.put(getThisContext(), id + "neighborReplyTime", DateUtil.getDateString());
                        SpUtil.put(getThisContext(),"BackRefresh",false);
                    } else {
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
    private OnContinuePayClickListener listener;
    public void setOnContinuePayClickListener(OnContinuePayClickListener listener){
        this.listener=listener;
    }
    public interface OnContinuePayClickListener{
        void OnContinuePayClick(float serviceSid);
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


            return result.substring(0,result.length());
        }
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
    public List<NeighborPostTo> getSubList(List<NeighborPostTo> postList){
        for (int i=0;i<postList.size();i++) {
            if (!TextUtils.isEmpty(postList.get(i).getRefId()))
                postList.get(i).setPostSubject(postList.get(i).getTopicTitle());
            if (!TextUtils.isEmpty(postList.get(i).getPostSubject()))
                postList.get(i).setPostSubject("#"+postList.get(i).getPostSubject()+"# ");
            if (!TextUtils.isEmpty(postList.get(i).getPostSubject())){
                if (postList.get(i).getPostContent() != null && !(postList.get(i).getPostSubject()+postList.get(i).getPostContent()).equals(getSubContent(textView,postList.get(i).getPostSubject()+ postList.get(i).getPostContent())))
                    postList.get(i).setSubPostContent((getSubContent(textView, postList.get(i).getPostSubject() + postList.get(i).getPostContent())).substring(0,getSubContent(textView, postList.get(i).getPostSubject() + postList.get(i).getPostContent()).length()-4));
                else
                    postList.get(i).setSubPostContent(null);
            }
            else {
                if (postList.get(i).getPostContent() != null && !(postList.get(i).getPostContent()).equals(getSubContent(textView, postList.get(i).getPostContent())))
                    postList.get(i).setSubPostContent(getSubContent(textView, postList.get(i).getPostContent()).substring(0,getSubContent(textView, postList.get(i).getPostContent()).length()-4));
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



    @Override
    public void onStop() {
        super.onStop();
        if (lbt!=null)
            lbt.stopTurning();
    }
    public void joinCampaign(String interactionSid,String text,Intent intent,int type,int status){
        NeighborApi api= ApiClient.create(NeighborApi.class);
        final CustomDialogFragment customDialog = new CustomDialogFragment();
        customDialog.show(getFragmentManager(), "");
        NeighborhoodLogParam param=new NeighborhoodLogParam();
        param.setOwnerSid(mUserHelper.getSid());
        param.setApartmentSid(mUserHelper.getSid());
        param.setRefId(interactionSid);
        param.setType(type);
        param.setStatus(1);
        api.JoinCampaign(param, new HttpCallback<MessageTo<NeighborhoodLogTo>>(getThisContext()) {
            @Override
            public void success(MessageTo<NeighborhoodLogTo> msg, Response response) {
                customDialog.dismiss();

                if (msg.getSuccess() == 0) {
                    if (status == 2) {
                        new Thread(() -> {
                            SystemClock.sleep(1000);
                            intent.putExtra("interactionSid", interactionSid);
                            startActivity(intent);
                        }).start();
                    }
                }
            }

            @Override
            public void failure(RetrofitError error) {
                customDialog.dismiss();
            }
        });

    }

    public void hideDialog(){
        dialog.dismiss();
    }

    public List<NeighborPostTo> deleteList(List<NeighborPostTo> postToList){
        List<NeighborPostTo>deleteList=new ArrayList<>();
        for (NeighborPostTo postTo:postToList){
            if (!deleteList.contains(postTo)){
                deleteList.add(postTo);
            }
        }
        return deleteList;
    }
    public void setListNoCache(final int index) {



            NeighborApi api = ApiClient.create(NeighborApi.class);

            api.findNeighborPostListByApartment(SpUtil.getBoolean(getThisContext(),"ParkNeighbor")?"":mHelper.getSid(), index,
                    new HttpCallback<MessageTo<List<NeighborPostTo>>>(context) {

                        @Override
                        public void success(MessageTo<List<NeighborPostTo>> msg, Response response) {
                            if (msg.getSuccess() == 0) {

                                if (index == 0) {
                                    postList.clear();
                                }

                                postList.addAll(msg.getData());
                                postList = deleteList(postList);
                                postList=getSubList(postList);


                                Map<String,List<NeighborPostTo>> map=new HashMap<String, List<NeighborPostTo>>();
                                map.put("Neighborhood_Cache", postList);
                                String json=JSON.toJSONString(map);
                                if(getThisContext()!=null) {
                                    SpUtil.put(getThisContext(), "Neighborhood_Cache", json);
                                }
                                mAdapter.setList(postList);
                                mAdapter.notifyDataSetChanged();
                                setListComplete();

                            } else {


                            }
                            setListComplete();

                        }

                        @Override
                        public void failure(RetrofitError error) {
                            setListComplete();

                        }
                    });

        }


    public void setListSortNoCache(final int index, int setId,final boolean isRefresh) {
            NeighborApi api = ApiClient.create(NeighborApi.class);
            final CustomDialogFragment customDialog = new CustomDialogFragment();
            api.findNeighborPostListByApartmentSort(SpUtil.getBoolean(getThisContext(),"ParkNeighbor")?"":mHelper.getSid(), id, index,
                    new HttpCallback<MessageTo<List<NeighborPostTo>>>(getThisContext()) {
                        @Override
                        public void success(MessageTo<List<NeighborPostTo>> msg, Response response) {

                            if (msg.getSuccess() == 0) {
                                if (index == 0) {

                                    postList.clear();
                                }

                                postList.addAll(msg.getData());
                                postList = deleteList(postList);
                                postList = getSubList(postList);
                                mAdapter.setList(postList);
                                mAdapter.notifyDataSetChanged();
                                setListComplete();
                                new ACache().put("neighborSortCache" + id, JSON.toJSONString(postList));


                            } else {
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


}
