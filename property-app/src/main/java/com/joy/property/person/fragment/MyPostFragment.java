package com.joy.property.person.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.neighbor.NeighborCommentHandleParam;
import com.jinyi.ihome.module.neighbor.NeighborCommentParam;
import com.jinyi.ihome.module.neighbor.NeighborCommentTo;
import com.jinyi.ihome.module.neighbor.NeighborLikeParam;
import com.jinyi.ihome.module.neighbor.NeighborLikeTo;
import com.jinyi.ihome.module.neighbor.NeighborPostTo;
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
import com.joy.property.common.PictureShowActivity;
import com.joy.property.neighborhood.PostActivity;
import com.joy.property.neighborhood.adapter.NeighborPostToAdapter;
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
 */
public class MyPostFragment extends BaseFragment implements View.OnClickListener, NeighborPostToAdapter.INeighborPostTo {
    private View self;

    private PullToRefreshListView mPullToRefreshListView;
    private int pageIndex = 0;
    private List<NeighborPostTo> postList = new ArrayList<>();
    private List<NeighborPostTo> SortPostList = new ArrayList<>();
    private NeighborPostToAdapter mAdapter = null;
    private EditText mContent;
    private ListView mListView;
    private boolean init;
    private  int id;
    private   Context context;
    protected ApartmentInfoHelper mHelper;
    protected UserInfoHelper mUserHelper ;
    protected SharedPreferences sp ;
    public MyPostFragment(){

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
        mAdapter.setOnNeighborPost(MyPostFragment.this);
//        if(!init) {

//            if (id == 0) {
//                if (SpUtil.getBoolean(getThisContext(), "FIRSTLOADERCICLE"))
//                    setList2(0);
//                else
                    setList(0);

      //      }

            mListView.setAdapter(mAdapter);
            //  init();

            refreshData();
            init=true;
      //  }
        return self;
    }


    private void findById() {

        mPullToRefreshListView = (PullToRefreshListView)  self.findViewById(R.id.lv_PullToRefreshListView);



    }

    @Override
    public void onResume() {
        super.onResume();
        applyScrollListener();
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
                pageIndex =0 ;
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
                listViewPullToRefreshBase.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                pageIndex++;
                setList(pageIndex);
            }
        });
    }

    private void setList(final int index) {
        if (!TextUtils.isEmpty(mHelper.getSid())) {
            NeighborApi api = ApiClient.create(NeighborApi.class);
            final CustomDialogFragment customDialog = new CustomDialogFragment();
            customDialog.show(getFragmentManager(), "");
            api.getMyPost(mUserHelper.getSid(), index,
                    new HttpCallback<MessageTo<List<NeighborPostTo>>>(context) {
                        @Override
                        public void success(MessageTo<List<NeighborPostTo>> msg, Response response) {
                            if (msg.getSuccess() == 0) {

                                customDialog.dismissAllowingStateLoss();
                                if (index == 0) {
                                    postList.clear();
                                }
                                postList.addAll(msg.getData());

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
                            super.failure(error);
                        }
                    });

        }

    }
    private void setList2(final int index) {
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
        if (!TextUtils.isEmpty(mUserHelper.getSid())) {
            NeighborApi api = ApiClient.create(NeighborApi.class);
            api.findNeighborPostListByApartment(mUserHelper.getSid(), index,
                    new HttpCallback<MessageTo<List<NeighborPostTo>>>(context) {

                        @Override
                        public void success(MessageTo<List<NeighborPostTo>> msg, Response response) {
                            if (msg.getSuccess() == 0) {

                                if (index == 0) {
                                    postList.clear();
                                }
                                postList.addAll(msg.getData());
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
                                Toast.makeText(getThisContext(),
                                        msg.getMessage(), Toast.LENGTH_LONG).show();

                            }
                            setListComplete();
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            setListComplete();

                            super.failure(error);
                        }
                    });

        }

    }

    private void setListComplete() {
        mPullToRefreshListView.onRefreshComplete();
    }


    @Override
    public void onClick(View v) {
        Intent intent1=new Intent(context,PostActivity.class);
        switch (v.getId()) {


            case R.id.ll_invite:
              //  neighborShowDialog();
                break;
            case R.id.rl_invite:
            //    neighborShowDialog();
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

        int mWidth =getScreenWidthPixels(context);
        final CustomDialog dialog = new CustomDialog(context, mWidth, 120, R.layout.diolag_editor_input, R.style.myDialogTheme);
        final Button mSend = (Button) dialog.findViewById(R.id.btn_send);
        mContent = (EditText) dialog.findViewById(R.id.like_content);

        // Button btnSmile = (Button) dialog.findViewById(R.id.btn_smile);
        if(mItem!=null) {
            if (mItem.getCommentOwner().getSid().equals(mUserHelper.getSid())) {
                deleteComment(mItem);
                return;
            }
        }
//if(mUserHelper.getSid().equals(mItem.getCommentOwner().getSid())){
//    deleteComment(mItem);
//}
        if (mItem != null && mItem.getCommentOwner() != null) {
            mContent.setHint("@" + mItem.getCommentOwner().getName());
        }

        mContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    mSend.setBackgroundResource(R.drawable.selector_send_button);
                } else {
                    mSend.setBackgroundResource(R.drawable.null_send_ic);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }


        });


        mSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(mContent.getText().toString())) {
                    Toast.makeText(getThisContext(), "请输入评论内容！", Toast.LENGTH_LONG).show();
                } else {
                    NeighborCommentParam mParam = new NeighborCommentParam();
                    mParam.setPostSid(item.getPostSid());
                    mParam.setCommentContent(mContent.getText().toString());
                    mParam.setCommentOwnerSid(mUserHelper.getSid());
                    if (mItem != null && mItem.getCommentOwner() != null) {
                        mParam.setAtOwnerSid(mItem.getCommentOwner().getSid());
                    }
                    NeighborApi api = ApiClient.create(NeighborApi.class);
                    final CustomDialogFragment dialogFragment = new CustomDialogFragment();
                    dialogFragment.show(getFragmentManager(), "");
                    api.addNeighborComment(mParam, new HttpCallback<MessageTo<NeighborCommentTo>>(getThisContext()) {
                        @Override
                        public void success(MessageTo<NeighborCommentTo> msg, Response response) {
                            dialogFragment.dismissAllowingStateLoss();
                            if (msg.getSuccess() == 0) {
                                List<NeighborCommentTo> list = item.getCommentList();
                                list.add(msg.getData());
                                item.setCommentList(list);
                                mAdapter.notifyDataSetChanged();
                                dialog.dismiss();

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
            }
        });
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        dialog.show();
        mContent.setFocusable(true);
        mContent.setFocusableInTouchMode(true);
        mContent.requestFocus();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                InputMethodManager inputManager = (InputMethodManager) mContent.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.showSoftInput(mContent, 0);
            }
        }, 300);


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
                    if (msg.getData() != null) {
                        List<NeighborLikeTo> likeToList = item.getLikeList();
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
    public void enterPostDetail(NeighborPostTo item) {

    }

    @Override
    public void enterCommentMainPage(NeighborCommentTo item) {

    }

    @Override
    public void postViewImage(int index, String item) {
        if (TextUtils.isEmpty(item)) {
            return;
        }
        Intent intent = new Intent(context, PictureShowActivity.class);
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
    }

    private void deleteComment(final NeighborCommentTo mItem) {
        final CustomDialog dialog = new CustomDialog(context, R.layout.delete_comment, R.style.myDialogTheme);
        Button btnAdd = (Button) dialog.findViewById(R.id.btn_add);
        Button btnCancel = (Button) dialog.findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCommentContent(mItem);
                dialog.dismiss();
            }
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
                            super.failure(error);
                        }
                    });
        }
    }
}
