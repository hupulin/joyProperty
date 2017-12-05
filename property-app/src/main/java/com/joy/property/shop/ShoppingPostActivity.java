package com.joy.property.shop;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jinyi.ihome.infrastructure.MessageToBulk;
import com.jinyi.ihome.module.newshop.GoodsCommentListTo;
import com.jinyi.ihome.module.newshop.GoodsCommentParam;
import com.jinyi.ihome.module.newshop.GoodsCommentParamListTo;
import com.jinyi.ihome.module.newshop.OlderDetailParam;
import com.joy.common.api.ApiClientBulk;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.NewShopApi;
import com.joy.property.MainApp;
import com.joy.property.R;
import com.joy.property.base.BaseActivity;
import com.joyhome.nacity.app.util.CustomDialogFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit.client.Response;

/**
 * Created by Admin on 2014-11-25
 */
public class ShoppingPostActivity extends BaseActivity
        implements OnClickListener{


    private LinearLayout commentLayout;
    private List<GoodsCommentParamListTo>commentParamList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_post);
        findById();
        initDatas(getIntent().getStringExtra("OlderSid"));
    }


    private void findById() {
        commentLayout = (LinearLayout) findViewById(R.id.commentLayout);
        findViewById(R.id.back).setOnClickListener(this);
        findViewById(R.id.postComment).setOnClickListener(this);
        findViewById(R.id.back).setOnClickListener(this);
    }


    private void initDatas(String olderSid) {
        NewShopApi api= ApiClientBulk.create(NewShopApi.class);
        OlderDetailParam param=new OlderDetailParam();
        param.setOrderId(olderSid);
        CustomDialogFragment dialogFragment=new CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "");
        api.getCommentList(param, new HttpCallback<MessageToBulk<List<GoodsCommentListTo>>>(getThisContext()) {
            @Override
            public void success(MessageToBulk<List<GoodsCommentListTo>> msg, Response response) {
                dialogFragment.dismiss();
                if (msg.getCode() == 0) {
                    if (msg.getOrderGoodsList() != null)
                        setView(msg.getOrderGoodsList());
                }
            }
        });

    }


    @Override
    public void onClick(View view) {
switch (view.getId()){
    case R.id.postComment:
        postComment();
        break;
    case R.id.back:
        onBackPressed();
        break;
}
    }
    public void setView(List<GoodsCommentListTo> mList){

        for (int i=0;i<mList.size();i++) {
            View view = View.inflate(getThisContext(), R.layout.goods_post_comment_item, null);
            ImageView goodsImage = (ImageView) view.findViewById(R.id.goodsImage);
            EditText commentContent = (EditText) view.findViewById(R.id.commentContent);
            ImageView goodCommentIcon = (ImageView) view.findViewById(R.id.goodCommentIcon);
            ImageView middleCommentIcon = (ImageView) view.findViewById(R.id.middleCommentIcon);
            ImageView badCommentIcon = (ImageView) view.findViewById(R.id.badCommentIcon);
            TextView goodCommentText = (TextView) view.findViewById(R.id.goodCommentText);
            TextView middleCommentText = (TextView) view.findViewById(R.id.middleCommentText);
            TextView badCommentText = (TextView) view.findViewById(R.id.badCommentText);
            Picasso.with(getThisContext()).load(MainApp.getPicassoImagePath(mList.get(i).getPicUrl())).into(goodsImage);

            GoodsCommentParamListTo commentParamListTo=new GoodsCommentParamListTo();
            commentParamListTo.setGoodsId(mList.get(i).getGoodsId());
            commentParamListTo.setEvaluateResult("1");
            commentParamListTo.setEvaluateContent("");
            commentParamListTo.setGoodsType(mList.get(i).getGoodsType());
            commentParamListTo.setSpecificationsId(mList.get(i).getSpecificationsId());
            if(TextUtils.isEmpty(mList.get(i).getSpecificationsName())||"null".equals(mList.get(i).getSpecificationsName())||mList.get(i).getSpecificationsName().contains("null"))
            commentParamListTo.setSpecificationsName("");
            else
                commentParamListTo.setSpecificationsName(mList.get(i).getSpecificationsName());
            commentParamList.add(commentParamListTo);
            commentContent.setTag(i);
            commentContent.setMinimumHeight((int) (getScreenWidth()*0.3013));
            view.findViewById(R.id.goodCommentLayout).setTag(i);
            view.findViewById(R.id.middleCommentLayout).setTag(i);
            view.findViewById(R.id.badCommentLayout).setTag(i);
            view.findViewById(R.id.goodCommentLayout).setOnClickListener(view1 -> {
                goodCommentIcon.setBackgroundResource(R.drawable.good_comment_select);
                middleCommentIcon.setBackgroundResource(R.drawable.middle_comment_un_select);
                badCommentIcon.setBackgroundResource(R.drawable.bad_comment_un_select);
                goodCommentText.setTextColor(Color.parseColor("#f17834"));
                middleCommentText.setTextColor(Color.parseColor("#353535"));
                badCommentText.setTextColor(Color.parseColor("#353535"));
                commentParamList.get((int) view1.getTag()).setEvaluateResult("1");
            });
            view.findViewById(R.id.middleCommentLayout).setOnClickListener(view1 -> {
                goodCommentIcon.setBackgroundResource(R.drawable.good_comment_un_select);
                middleCommentIcon.setBackgroundResource(R.drawable.middle_comment_select);
                badCommentIcon.setBackgroundResource(R.drawable.bad_comment_un_select);
                goodCommentText.setTextColor(Color.parseColor("#353535"));
                middleCommentText.setTextColor(Color.parseColor("#ffb400"));
                badCommentText.setTextColor(Color.parseColor("#353535"));
               commentParamList.get((int) view1.getTag()).setEvaluateResult("2");
            });
            view.findViewById(R.id.badCommentLayout).setOnClickListener(view1 -> {
                goodCommentIcon.setBackgroundResource(R.drawable.good_comment_un_select);
                middleCommentIcon.setBackgroundResource(R.drawable.middle_comment_un_select);
                badCommentIcon.setBackgroundResource(R.drawable.bad_comment_select);
                goodCommentText.setTextColor(Color.parseColor("#353535"));
                middleCommentText.setTextColor(Color.parseColor("#353535"));
                badCommentText.setTextColor(Color.parseColor("#353535"));
                commentParamList.get((int) view1.getTag()).setEvaluateResult("3");
            });
commentContent.addTextChangedListener(new TextWatcher() {
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        commentParamList.get((Integer) commentContent.getTag()).setEvaluateContent(charSequence+"");
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
});
            commentLayout.addView(view);

        }
    }
    public void postComment(){
    NewShopApi api=ApiClientBulk.create(NewShopApi.class);
        GoodsCommentParam param=new GoodsCommentParam();
        param.setOrderId(getIntent().getStringExtra("OlderSid"));
        param.setUserId(mUserHelperBulk.getSid());
        param.setOrderGoodsList(commentParamList);


        CustomDialogFragment dialogFragment=new CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(),"");
        api.postComment(param, new HttpCallback<MessageToBulk>(getThisContext()) {
            @Override
            public void success(MessageToBulk msg, Response response) {
                dialogFragment.dismiss();
                Intent intent=new Intent(getThisContext(),MyShoppingActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("CurrentPage",5);
                startActivity(intent);
                goToAnimation(2);
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        goToAnimation(2);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK)
            onBackPressed();
        return super.onKeyDown(keyCode, event);
    }
}
