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
import android.text.Editable;
import android.text.Layout;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.ToxicBakery.viewpager.transforms.ABaseTransformer;
import com.ToxicBakery.viewpager.transforms.ForegroundToBackgroundTransformer;
import com.alibaba.fastjson.JSON;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.house.HouseValueTypeTo;
import com.jinyi.ihome.module.neighbor.NeighborCommentHandleParam;
import com.jinyi.ihome.module.neighbor.NeighborCommentParam;
import com.jinyi.ihome.module.neighbor.NeighborCommentTo;
import com.jinyi.ihome.module.neighbor.NeighborLikeParam;
import com.jinyi.ihome.module.neighbor.NeighborLikeTo;
import com.jinyi.ihome.module.neighbor.NeighborPostTo;
import com.jinyi.ihome.module.neighbor.NeighborhoodInteractionTo;
import com.jinyi.ihome.module.neighbor.NeighborhoodLogParam;
import com.jinyi.ihome.module.neighbor.NeighborhoodLogTo;
import com.jinyi.ihome.module.neighbor.OwnerMessageTo;
import com.joy.common.api.ApiClient;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.NeighborApi;
import com.joy.common.helper.ApartmentInfoHelper;
import com.joy.common.helper.UserInfoHelper;
import com.joy.common.widget.UploadImage;
import com.joy.library.utils.ConfigUtil;
import com.joy.library.utils.DateUtil;
import com.joy.property.R;
import com.joy.property.base.BaseFragment;
import com.joy.property.common.LongPictureShowActivity;
import com.joy.property.neighborhood.CampaignActivity;
import com.joy.property.neighborhood.InvestigateActivity;
import com.joy.property.neighborhood.MyNeighborActivity;
import com.joy.property.neighborhood.PostActivity;
import com.joy.property.neighborhood.PostDetailActivity;
import com.joy.property.neighborhood.TopicActivity;

import com.joy.property.neighborhood.adapter.NeighborNetworkImageHolderView;
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
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;


import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by xzz on 2016/3/11.
 **/
@SuppressLint("ValidFragment")
public class NeighborFragment extends BaseFragment implements View.OnClickListener, NeighborPostToAdapter.INeighborPostTo {
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
    private CustomDialog dialog;
    private String lastType="first";
    private boolean isFresh;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }
    public NeighborFragment(){

    }

    public NeighborFragment(TextView textView,TextView commentContent,Context context){
        this.textView=textView;
        this.commentContent=commentContent;
        this.context=context;
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
        mAdapter.setOnNeighborPost(NeighborFragment.this);
        mAdapter.setList(postList);
        mListView.setAdapter(mAdapter);
        setHeadViewList();

        if(!init) {

            if (SpUtil.getBoolean(getThisContext(), "FIRSTLOADERCICLE"))
                setList2(0);
            else
                setList(0);
        }
        refreshData();
        init=true;

        return self;
    }

    public void init(String type){
//        if (!lastType.equals(type)) {
            if ("".equals(type))
                setList2(0);
            else
                setListSort(0, Integer.valueOf(type), false);
//        }
//        lastType=type;
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

        if (lbt!=null) {

            lbt.startTurning(5000);
        }
        if (SpUtil.getBoolean(getThisContext(),"BackRefresh")){

            if (id==0)
                setList(pageIndex);
            else
                setListSort(pageIndex,id,false);
        }

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
                ConfigUtil.saveString(sp, MainApp.KeyValue.KEY_REFRESH_TIME, DateUtil.getDateTimeFormat(DateUtil.mDateTimeFormatString, new Date()));
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
                isFresh=true;
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
                if (id == 0)
                    setList(pageIndex);
                else
                    setListSort(pageIndex, id, false);
            }
        });
    }

     void setList(final int index) {


            NeighborApi api = ApiClient.create(NeighborApi.class);
            final CustomDialogFragment customDialog = new CustomDialogFragment();
            customDialog.show(getFragmentManager(), "");
                  api.findNeighborPostListByApartment(SpUtil.getBoolean(getThisContext(),"ParkNeighbor")?"":mHelper.getSid(), index,
                    new HttpCallback<MessageTo<List<NeighborPostTo>>>(context) {
                        @Override
                        public void success(MessageTo<List<NeighborPostTo>> msg, Response response) {
                            if (msg.getSuccess() == 0) {

                                customDialog.dismissAllowingStateLoss();
                                if (index == 0) {
                                    postList.clear();
                                }
                                postList.addAll(msg.getData());
                                postList=getSubList(postList);

                                Map<String,List<NeighborPostTo>> map=new HashMap<String, List<NeighborPostTo>>();
                                map.put("Neighborhood_Cache", postList);
                                String json=JSON.toJSONString(map);
                                if(getThisContext()!=null) {
                                    SpUtil.put(getThisContext(), "Neighborhood_Cache", json);
                                    SpUtil.put(getThisContext(), "FIRSTLOADERCICLE", true);
                                }
                                mAdapter.setList(postList);
                                mAdapter.notifyDataSetChanged();
                                setListComplete();

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


            NeighborApi api = ApiClient.create(NeighborApi.class);
        System.out.println(SpUtil.getBoolean(getThisContext(), "ParkNeighbor") ? "**********" : mHelper.getSid()+"---");

        api.findNeighborPostListByApartment(SpUtil.getBoolean(getThisContext(), "ParkNeighbor") ? "" : mHelper.getSid(), index,
                new HttpCallback<MessageTo<List<NeighborPostTo>>>(context) {

                    @Override
                    public void success(MessageTo<List<NeighborPostTo>> msg, Response response) {

                        if (msg.getSuccess() == 0) {

                            if (index == 0) {
                                postList.clear();
                            }

                            postList.addAll(msg.getData());

                            postList = getSubList(postList);


                            Map<String, List<NeighborPostTo>> map = new HashMap<String, List<NeighborPostTo>>();
                            map.put("Neighborhood_Cache", postList);
                            String json = JSON.toJSONString(map);
                            if (getThisContext() != null) {
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
                        System.out.println(error.toString() + "------");
                        setListComplete();
                    }
                });

        }



    private void setListComplete() {
        mPullToRefreshListView.onRefreshComplete();
        SpUtil.put(getThisContext(), "BackRefresh", false);
    }


    @Override
    public void onClick(View v) {
        Intent intent1=new Intent(context,PostActivity.class);
        switch (v.getId()) {


            case R.id.ll_invite:
           //     neighborShowDialog();
                break;
            case R.id.rl_invite:
           //     neighborShowDialog();
                break;


        }
    }



    /**
     * 分享对话框
     */
//    private void neighborShowDialog() {
//        final CustomDialog dialog = new CustomDialog(context, R.layout.dialog_neighbor_share, R.style.myDialogTheme);
//        ImageView close = (ImageView) dialog.findViewById(R.id.close);
//        TextView mWeChat = (TextView) dialog.findViewById(R.id.wechat);
//        TextView mQQ = (TextView) dialog.findViewById(R.id.QQ);
//        final TextView mMsg = (TextView) dialog.findViewById(R.id.short_msg);
//        TextView mMoment = (TextView) dialog.findViewById(R.id.moment);
//        ShareSDK.initSDK(getThisContext());
//        close.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });
//
//        final Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.share_ic);
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
//
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
//
//        dialog.setCancelable(true);
//        dialog.show();
//    }


    @Override
    public void postComment(final NeighborPostTo item, final NeighborCommentTo mItem) {
        Intent intent=new Intent(getThisContext(), PostDetailActivity.class);
        intent.putExtra("postSid",item.getPostSid());

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

    public void setTop(){
        mListView.setSelection(1);
    }
    public void setListSort(final int index, int setId,final boolean isRefresh) {
        id=setId;
        postList.clear();

        if(JSON.parseArray(new ACache().getAsString("neighborSortCache"+id),HouseValueTypeTo.class)!=null) {
            postList = JSON.parseArray(new ACache().getAsString("neighborSortCache" + id), NeighborPostTo.class);
            mAdapter.setList(postList);
            mAdapter.notifyDataSetChanged();
        }

            NeighborApi api = ApiClient.create(NeighborApi.class);
            final CustomDialogFragment customDialog = new CustomDialogFragment();
            if(postList==null)
                customDialog.show(getFragmentManager(), "");
            System.out.println(SpUtil.getBoolean(getThisContext(),"ParkNeighbor")?"":mHelper.getSid()+"id====");
            api.findNeighborPostListByApartmentSort(SpUtil.getBoolean(getThisContext(),"ParkNeighbor")?"":mHelper.getSid(), id, index,
                    new HttpCallback<MessageTo<List<NeighborPostTo>>>(getThisContext()) {
                        @Override
                        public void success(MessageTo<List<NeighborPostTo>> msg, Response response) {
                            System.out.println(msg+"msg----------");
                            if (msg.getSuccess() == 0) {
                                if (index == 0) {

                                    postList.clear();
                                }
isFresh=false;
                                customDialog.dismissAllowingStateLoss();
                                postList.addAll(msg.getData());
                                postList=getSubList(postList);
                                mAdapter.setList(postList);
                                mAdapter.notifyDataSetChanged();
                                setListComplete();
                                new ACache().put("neighborSortCache"+id, JSON.toJSONString(postList));


                            } else {
                                customDialog.dismissAllowingStateLoss();

                            }
                            setListComplete();
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            setListComplete();
                            customDialog.dismissAllowingStateLoss();
                            System.out.println(error.toString() + "msg----------");

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

    public void setHeadViewList(){
        NeighborApi api= ApiClient.create(NeighborApi.class);
        System.out.println(SpUtil.getBoolean(getThisContext(), "ParkNeighbor")?"":"/"+mHelper.getSid()+"=======");
        api.getAutoRow(SpUtil.getBoolean(getThisContext(), "ParkNeighbor")?"":mHelper.getSid(),new HttpCallback<MessageTo<List<NeighborhoodInteractionTo>>>(getThisContext()) {
            @Override
            public void success(MessageTo<List<NeighborhoodInteractionTo>> msg, Response response) {
                if (msg.getSuccess()==0){
                    System.out.println(msg.getData()+"data");
                    if (msg.getData()!=null&&msg.getData().size()>0)
                        setHeadView(msg.getData());

                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
    public void setHeadView(List<NeighborhoodInteractionTo>interactionTos){
        if (getThisContext()==null)
            return;
        headView = View.inflate(getThisContext(), R.layout.auto_row_head_view, null);
        lbt = (ConvenientBanner) headView.findViewById(R.id.lbt);
            lbt.startTurning(5000);
            String transforemerName =ForegroundToBackgroundTransformer.class.getSimpleName();
            try {
                Class cls = Class.forName("com.ToxicBakery.viewpager.transforms." + transforemerName);
                ABaseTransformer transforemer = (ABaseTransformer) cls.newInstance();
                lbt.getViewPager().setPageTransformer(true, transforemer);

                if (transforemerName.equals("StackTransformer")){
                    lbt.setScrollDuration(1200);
                }

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (java.lang.InstantiationException e) {
                e.printStackTrace();
            }
                lbt.setPages(NeighborNetworkImageHolderView::new, interactionTos).setPageIndicator(new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused}).setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT);
                mListView.addHeaderView(headView);
            lbt.setOnItemClickListener(position -> {
                Intent intent = null;
                if (interactionTos.get(position).getInteractionType() == 2) {
                    intent = new Intent(getThisContext(), TopicActivity.class);
                    joinCampaign(interactionTos.get(position).getRefId(), null, null, 2, 1);
                } else if (interactionTos.get(position).getInteractionType() == 1) {
                    intent = new Intent(getThisContext(), CampaignActivity.class);
                    joinCampaign(interactionTos.get(position).getRefId(), null, null, 1, 1);
                } else if (interactionTos.get(position).getInteractionType() == 3) {
                    intent = new Intent(getThisContext(), InvestigateActivity.class);
                    intent.putExtra("outOfDate",interactionTos.get(position).isOutOfDate());
                    joinCampaign(interactionTos.get(position).getRefId(), null, null, 3, 1);
                }
                if (intent == null)
                    return;
                intent.putExtra("interactionSid", interactionTos.get(position).getRefId());


                startActivity(intent);

                if (lbt != null)
                    lbt.stopTurning();
            });




    }

    @Override
    public void onStop() {
        super.onStop();
        if (lbt!=null)
            lbt.stopTurning();
    }
    public void joinCampaign(String interactionSid,String text,Intent intent,int type,int status){
        NeighborApi api= ApiClient.create(NeighborApi.class);

        NeighborhoodLogParam param=new NeighborhoodLogParam();
        param.setOwnerSid(mUserHelper.getSid());
        param.setApartmentSid(mHelper.getSid());
        param.setRefId(interactionSid);
        param.setType(type);
        param.setStatus(1);
        api.JoinCampaign(param, new HttpCallback<MessageTo<NeighborhoodLogTo>>(getThisContext()) {
            @Override
            public void success(MessageTo<NeighborhoodLogTo> msg, Response response) {


                if (msg.getSuccess() == 0) {
                    if (status == 2) {
                        Toast.makeText(getThisContext(), text, Toast.LENGTH_LONG).show();
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

            }
        });

    }

    public void setId(int setId){
        id=setId;
    }

    public void hideDialog(){
        dialog.dismiss();
    }

    @Override
    protected Context getThisContext() {
        return context;

    }
}
