package com.joy.property.neighborhood.adapter;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jinyi.ihome.module.neighbor.NeighborCommentTo;
import com.jinyi.ihome.module.neighbor.NeighborLikeTo;
import com.jinyi.ihome.module.neighbor.NeighborPostTo;
import com.joy.library.utils.DateUtil;
import com.joy.library.utils.HeadImageView;

import com.joy.property.R;
import com.joy.property.neighborhood.NeighborLinkShowActivity;
import com.joy.property.utils.CustomDialog;
import com.joy.property.utils.SpUtil;
import com.joyhome.nacity.app.MainApp;
import com.joyhome.nacity.app.common.adapter.ModeListAdapter;

import com.joyhome.nacity.app.util.ImageLoadleUtil;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.squareup.picasso.Picasso;
import com.wefika.flowlayout.FlowLayout;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Admin on 2014-11-25
 */
public class TopicAdapter extends ModeListAdapter<NeighborPostTo> {
    private Context mContext;

    private INeighborPostTo onNeighborPost;
    private PopupWindow mPopupWindow = null;
    private LayoutInflater inflater = null;
    private final ImageLoader loader;
    private final DisplayImageOptions option;
    private Button btnPraise;
    public TextView textview;
    private final View mView;
    private String urlContent="";
    private List<String> sidList = new ArrayList<>();
    private List<TextView>textViewList=new ArrayList<>();
    private String subContent;
    private String content;
    private TextView expandAllTop;
    private TextView investigateContent;

    public TopicAdapter(Context context,String subContent,String content) {
        super(context);
        this.mContext = context;
        this.subContent=subContent;
        this.content=content;
        ImageLoaderConfiguration config = ImageLoadleUtil.config(mContext);
        ImageLoader.getInstance().init(config);
        option = ImageLoadleUtil.getImageloaderOption();
        loader = ImageLoader.getInstance();
        inflater = LayoutInflater.from(mContext);
        mView = inflater.inflate(R.layout.list_item_popupwindow, null);
        btnPraise = (Button) mView.findViewById(R.id.btn_good);
    }

    public INeighborPostTo getOnNeighborPost() {
        return onNeighborPost;
    }
    public void setOnNeighborPost(INeighborPostTo onNeighborPost) {
        this.onNeighborPost = onNeighborPost;
    }

    @Override
    public int getCount() {
        return mList==null?1:mList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
     if (position==0){
         View headView;
         headView=View.inflate(mContext, R.layout.investigate_head_view, null);
         expandAllTop = (TextView) headView.findViewById(R.id.expandAll);
         investigateContent = (TextView) headView.findViewById(R.id.investigateContent);

         if (subContent.length()<content.length())
             expandAllTop.setVisibility(View.VISIBLE);
         investigateContent.setText(subContent);
         expandAllTop.setOnClickListener(v -> {
             Spannable spannable = new SpannableString(content + "收起");

             spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#007aff")), content.length(), content.length() + 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
             spannable.setSpan(new TextClick(investigateContent, "收起","收起全文"), content.length(), content.length() + 2
                     , Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
             investigateContent.setMovementMethod(LinkMovementMethod.getInstance());
             investigateContent.setMaxLines(100);

             investigateContent.setText(spannable);
             expandAllTop.setVisibility(View.GONE);

         });
       return headView;
     }else {
         View row =null;

         final NeighborCache holder;
         if (row == null) {
             row = inflater.inflate(R.layout.list_item_circle_post, null);
             holder = new NeighborCache(row);
             row.setTag(holder);
         } else {
             holder = (NeighborCache) row.getTag();

         }
         textview = (TextView) row.findViewById(R.id.content);
         final NeighborPostTo mode = mList.get(position-1);

         if (!TextUtils.isEmpty(mode.getPostContent())) {

             urlContent = mode.getPostContent();
             if (!TextUtils.isEmpty(mode.getSubPostContent()))
                 urlContent = mode.getSubPostContent();
             else
                 urlContent = mode.getPostContent();

             holder.getPostContent().setOnLongClickListener(v -> {
                 copyText(mode.getPostSubject() == null ? (mode.getPostContent()) : (mode.getPostSubject() + mode.getPostContent()));
                 return false;
             });
             holder.getLikeLine().setVisibility(View.VISIBLE);
             //这里只是为了方便，用屏幕宽度代替了textview控件宽度，如果需要精准控制，可以换成控件宽度

             holder.getPostContent().setText(urlContent);

             if (mode.getSubPostContent() != null) {
                 holder.getExpandAll().setVisibility(View.VISIBLE);
             } else {

                 holder.getExpandAll().setVisibility(View.GONE);

             }


             if (!TextUtils.isEmpty(mode.getPostSubject())) {
                 Spannable ss = new SpannableString(urlContent);
                 ss.setSpan(new ForegroundColorSpan(Color.parseColor("#007aff")), 0, mode.getPostSubject().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                 ss.setSpan(new TextClick(holder.getPostContent(), mode.getPostContent(), mode.getPostUrl(), mode.getPostSubject(), mode), 0, mode.getPostSubject().length()
                         , Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                 holder.getPostContent().setMovementMethod(LinkMovementMethod.getInstance());

                 holder.getPostContent().setText(ss);
             } else
                 holder.getPostContent().setText(urlContent);
             holder.getExpandAll().setOnClickListener(v -> {
                         holder.getPostContent().setMaxLines(100);
                         textViewList.add(holder.getPostContent());
                         //       holder.getPostContent().setText(TextUtils.isEmpty(mode.getPostSubject())?mode.getPostContent():mode.getPostSubject()+mode.getPostContent());
                         holder.getExpandAll().setVisibility(View.GONE);
                         String content = (TextUtils.isEmpty(mode.getPostSubject()) ? mode.getPostContent() : (mode.getPostSubject() + mode.getPostContent())) + "收起";
                         String clickTxt = "收起";
                         holder.getPostContent().setText(content);
//
                         if (!TextUtils.isEmpty(mode.getPostSubject())) {
                             SpannableStringBuilder spannable = new SpannableStringBuilder(content);
                             int startIndex = content.lastIndexOf(clickTxt);
                             int endIndex = startIndex + clickTxt.length();
                             spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#007aff")), 0, mode.getPostSubject().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                             spannable.setSpan(new TextClick(holder.getPostContent(), mode.getPostContent(), mode.getPostUrl(), mode.getPostSubject(), mode), 0, mode.getPostSubject().length()
                                     , Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                             spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#576b95")), startIndex, endIndex
                                     , Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                             //文字点击
                             spannable.setSpan(new TextClick(holder.getPostContent(), TextUtils.isEmpty(mode.getPostSubject()) ? mode.getPostContent() : mode.getPostSubject() + mode.getPostContent(), holder.getExpandAll(), "收起", mode, holder.getPostContent()), startIndex, endIndex
                                     , Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                             holder.getPostContent().setMovementMethod(LinkMovementMethod.getInstance());
                             holder.getPostContent().setText(spannable);
                         } else {


                             SpannableStringBuilder spannable = new SpannableStringBuilder(content);
                             int startIndex = content.lastIndexOf(clickTxt);
                             int endIndex = startIndex + clickTxt.length();

                             spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#576b95")), startIndex, endIndex
                                     , Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                             //文字点击
                             Toast.makeText(mContext, mode.getSubPostContent(), Toast.LENGTH_LONG).show();
                             spannable.setSpan(new TextClick(holder.getPostContent(), TextUtils.isEmpty(mode.getPostSubject()) ? mode.getPostContent() : mode.getPostSubject() + mode.getPostContent(), holder.getExpandAll(), "收起", mode, holder.getPostContent()), startIndex, endIndex
                                     , Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                             //一定要记得设置，不然点击不生效
                             holder.getPostContent().setMovementMethod(LinkMovementMethod.getInstance());
                             holder.getPostContent().setText(spannable);
                         }

                     }
             );


         }

         if (mode.getPostOwner() != null && !TextUtils.isEmpty(mode.getPostOwner().getName())) {
             holder.getPostOwner().setText(mode.getPostOwner().getName());
         }

         /**
          * 设置帖子的类型
          */

         if (!TextUtils.isEmpty(mode.getPostTypeName())) {
             holder.getmLabel().setText(mode.getPostTypeName());
         }

         /**
          * 设置头像
          */
         holder.getHeadImage().setScaleType(ImageView.ScaleType.FIT_XY);
         if (mode.getPostOwner() != null) {
             displayImage(holder.getHeadImage(),
                     mode.getPostOwner().getIcon(), R.drawable.head_image);
             //   imageLoader.displayImage(MainApp.getImagePath(mode.getPostOwner().getIcon()) ,
             //           holder.getHeadImage() ,MainApp.optionsHeadImage);
             //    loader.displayImage(MainApp.getImagePath(mode.getPostOwner().getIcon()), holder.getHeadImage(), option);
             //  Picasso.with(mContext).load(mode.getPostOwner().getIcon()).placeholder(R.drawable.guest_head_image).error(R.drawable.guest_head_image).into(holder.getHeadImage());
         }


         /**
          * 显示点赞用户
          */

         holder.getTriangle().setVisibility(View.GONE);
         holder.getmLike().setVisibility(View.GONE);
         holder.getLikeList().setVisibility(View.GONE);
         holder.getCommentList().removeAllViews();
         List<NeighborLikeTo> list;
         list = mode.getLikeList();
         if (list != null && list.size() > 0) {
             StringBuilder str = new StringBuilder();
             for (NeighborLikeTo likeTo : list) {
                 if (likeTo.getLikeOwner() != null) {
                     //  holder.getTriangle().setVisibility(View.VISIBLE);

                     holder.getmLike().setVisibility(View.VISIBLE);
                     holder.getLikeList().setVisibility(View.VISIBLE);
                     str.append(likeTo.getLikeOwner().getName());
                     str.append(",");
                 }
             }

             if (str.toString().endsWith(",")) {
                 holder.getLikeList().setText(str.toString().substring(0, str.lastIndexOf(",")));
             }
         }
         /**
          * 发帖时间
          */
         if (!TextUtils.isEmpty(mode.getPostTimeStr())) {
             holder.getPostTime().setText(mode.getPostTimeStr());
         }


         /**
          * 评论显示
          */
         List<NeighborCommentTo> mList = mode.getCommentList();
         if (mList != null && mList.size() > 0) {
             //  holder.getTriangle().setVisibility(View.VISIBLE);
             if (list.size() == 0) {
                 holder.getPadding().setVisibility(View.VISIBLE);
             } else
                 holder.getPadding().setVisibility(View.GONE);
             holder.getCommentList().setVisibility(View.VISIBLE);

             for (NeighborCommentTo commentTo : mList) {
                 LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                         ViewGroup.LayoutParams.WRAP_CONTENT);

                 String comment = commentTo.getCommentContent();
                 CommentTextView mView = new CommentTextView(mContext);
                 TextView tv = (TextView) mView.findViewById(R.id.commentContent);
                 HeadImageView headImageView = (HeadImageView) mView.findViewById(R.id.comment_head_image);
                 TextView commentTime = (TextView) mView.findViewById(R.id.commentTime);
                 TextView commentName = (TextView) mView.findViewById(R.id.commentName);
                 tv.setTextColor(Color.parseColor("#040404"));
                 tv.setSelected(true);
                 tv.setMinimumHeight(40);
                 if (commentTo.getCommentOwner() != null) {
                     String user = commentTo.getCommentOwner().getName();
                     Picasso.with(mContext).load(MainApp.getPicassoImagePath(commentTo.getCommentOwner().getIcon())).into(headImageView);
                     commentTime.setText(getDateTime(commentTo.getCommentTime()));

                     commentName.setText(commentTo.getCommentOwner().getName());
                     SpannableString ss;
                     tv.setText(commentTo.getCommentContent());
                     if (commentTo.getAtOwner() != null) {
                         String atOwner = commentTo.getAtOwner().getName();
                         // value = String.format("<font color='#95a5b0'>%s</font>%s<font color='#95a5b0'>%s:</font>", user, "回复", atOwner);
                         ss = new SpannableString(" 回复@" + atOwner + " ：" + comment);
                         ss.setSpan(new ForegroundColorSpan(0xfff17834), 3, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                         ss.setSpan(new ForegroundColorSpan(0xfff17834), 4, atOwner.length() + 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                         tv.setText(ss);
                     }
//                    if (commentTo.getAtOwner() == null) {
//
//                        ss = new SpannableString(user + " ：" + comment);
//                        ss.setSpan(new ForegroundColorSpan(0xff576B95), 0, user.length() + 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//                    } else {
//                        String atOwner = commentTo.getAtOwner().getName();
//                        // value = String.format("<font color='#95a5b0'>%s</font>%s<font color='#95a5b0'>%s:</font>", user, "回复", atOwner);
//                        ss = new SpannableString(user + " 回复 " + atOwner + " ：" + comment);
//                        ss.setSpan(new ForegroundColorSpan(0xff576B95), 0, user.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//                        ss.setSpan(new ForegroundColorSpan(0xff576B95), user.length() + 4, user.length() + atOwner.length() + 6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//
//                    }
//
//                    //  tv.setTextSize(mContext.getResources().getDimension(R.dimen.neighbor_indicator_textSize));
//
//                    int width = SpUtil.getInt(mContext, "WINDOWWIDTH");
//                    tv.setPadding((int) (width * 0.01667), 0, (int) (width * 0.01667), 0);
//
//                    //   tv.setGravity(Gravity.CENTER_VERTICAL);
//                    if (list != null)
//                        tv.setBackgroundResource(R.color.comment_list_text_bg);
//                    else
//                        holder.getCommentList().setBackgroundResource(R.drawable.circle_good_bg);
//                    tv.setText(ss);
                     // tv.setText(Html.fromHtml(value));

                 } else {
                     SpannableString ss;
                     //String value = "";
                     if (commentTo.getAtOwner() == null) {
                         // value = String.format("<font color='#95a5b0'>%s:</font>%s", "", comment);
                         ss = new SpannableString("" + "：" + comment);
                         //ss.setSpan(new ForegroundColorSpan(0xff95a5b0), 0, user.length() + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                     } else {
                         String atOwner = commentTo.getAtOwner().getName();
                         // value = String.format("<font color='#95a5b0'>%s</font>%s<font color='#95a5b0'>%s:</font>%s", "", "回复", atOwner, comment);
                         ss = new SpannableString("" + " 回复 " + atOwner + " ：" + comment);
                         ss.setSpan(new ForegroundColorSpan(0xff576B95), 4, atOwner.length() + 6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                     }
                     int width = SpUtil.getInt(mContext, "WINDOWWIDTH");
                     tv.setPadding((int) (width * 0.01667), 0, (int) (width * 0.01667), 0);

                     // tv.setTextSize(mContext.getResources().getDimension(R.dimen.neighbor_indicator_textSize));
                     //   tv.setGravity(Gravity.CENTER_VERTICAL);
                     if (list != null)
                         tv.setBackgroundResource(R.color.comment_list_text_bg);
                     else
                         holder.getCommentList().setBackgroundResource(R.drawable.circle_good_bg);
                     tv.setText(ss);
                     //tv.setText(Html.fromHtml(value));
                 }
                 tv.setTag(R.id.tag_first, mode);
                 tv.setTag(R.id.tag_second, commentTo);

                 tv.setOnClickListener(new OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         onNeighborPost.postComment((NeighborPostTo) v.getTag(R.id.tag_first), (NeighborCommentTo) v.getTag(R.id.tag_second));
                     }
                 });
                 tv.setOnLongClickListener(new View.OnLongClickListener() {
                     @Override
                     public boolean onLongClick(View v) {
                         copyText(comment);
                         return false;
                     }
                 });
                 holder.getCommentList().addView(mView, params);
             }

         } else {
             holder.getLikeLine().setVisibility(View.GONE);
         }
         holder.getBtnLike().setOnClickListener(new OnClickListener() {
             @Override
             public void onClick(View v) {
                 sidList.clear();

                 if (mode.getLikeList() != null && mode.getLikeList().size() > 0) {
                     for (int i = 0; i < mode.getLikeList().size(); i++)
                         if (mode.getLikeList().get(i).getLikeOwner() != null)
                             sidList.add(mode.getLikeList().get(i).getLikeOwner().getSid());
                     if (sidList.contains(mUserHelper.getSid())) {
                         holder.getLikeIcon().setBackgroundResource(R.drawable.like_unpress);
                         holder.getLikeCount().setTextColor(Color.parseColor("#999999"));
                     } else {
                         holder.getLikeIcon().setBackgroundResource(R.drawable.like_press);
                         holder.getLikeCount().setTextColor(Color.parseColor("#f17834"));
                     }
                 } else {
                     holder.getLikeIcon().setBackgroundResource(R.drawable.like_press);
                     holder.getLikeCount().setTextColor(Color.parseColor("#f17834"));
                 }
                 holder.getBtnLike().setTag(mode);
                 if (onNeighborPost == null) return;
                 onNeighborPost.postPraise((NeighborPostTo) v.getTag());
             }
         });


         holder.getBtnComment().setOnClickListener(new OnClickListener() {

             @Override
             public void onClick(View v) {
                 holder.getBtnComment().setTag(R.id.tag_first, mode);
                 if (onNeighborPost == null) return;
                 onNeighborPost.postComment((NeighborPostTo) v.getTag(R.id.tag_first), null);
             }
         });

         holder.getFlowLayout().removeAllViews();
         if (!TextUtils.isEmpty(mode.getPostImages())) {
             String[] path = mode.getPostImages().split(";");
             int mScreenWidth = getScreenWidthPixels(mContext);
             int mWidth = (int) (mScreenWidth * 0.297);
             int margin = (int) (mScreenWidth * 0.006994);
             FlowLayout.LayoutParams param = new FlowLayout.LayoutParams(mWidth, mWidth);

             for (int i = 0; i < path.length; i++) {
                 View postImageView = View.inflate(mContext, R.layout.neighbor_image_item, null);
                 ImageView postImage = (ImageView) postImageView.findViewById(R.id.postImage);
                 TextView postImageType = (TextView) postImageView.findViewById(R.id.postImageType);
                 //  postImage.setScaleType(ImageView.ScaleType.FIT_CENTER);
                 ImageLoadleUtil.getImageLoaderOption4(mContext, MainApp.getImagePath(path[i]), postImage);
                 //  Picasso.with(mContext).load(MainApp.getImagePath(path[i])).into(postImage);
                 param.setMargins(margin, margin, margin, margin);
                 postImage.setTag(R.id.tag_first, i);
                 postImage.setTag(R.id.tag_second, mode.getPostImages());
                 postImage.setOnClickListener(v -> {
                     if (onNeighborPost != null) {
                         onNeighborPost.postViewImage((int) v.getTag(R.id.tag_first), (String) v.getTag(R.id.tag_second));
                     }
                 });


                 holder.getFlowLayout().addView(postImageView, param);

             }

         }


         holder.getmDelete().setTag(mode);

         holder.getmDelete().setVisibility(View.GONE);

         if (mode.getPostOwner() != null && !TextUtils.isEmpty(mUserHelper.getSid())) {
             if (mode.getPostOwner().getSid().equals(mUserHelper.getSid())) {
                 holder.getmDelete().setVisibility(View.VISIBLE);
             }
         }


         holder.getmDelete().setOnClickListener(new OnClickListener() {
             @Override
             public void onClick(View v) {
                 if (onNeighborPost == null) return;
                 onNeighborPost.postDeleteItem((NeighborPostTo) v.getTag());
             }
         });


         return row;
     }
    }

    private void initPopupWindow(final NeighborPostTo item) {
        mPopupWindow = new PopupWindow(mContext);
        mPopupWindow.setWidth(RelativeLayout.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setHeight(RelativeLayout.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);

        final Button btnComment = (Button) mView.findViewById(R.id.btn_comment);
        final RelativeLayout mPopup = (RelativeLayout) mView.findViewById(R.id.popup);
        mPopupWindow.setContentView(mView);
        sidList.clear();
        if (item.getLikeList()!=null&&item.getLikeList().size() > 0) {
            for (int i = 0; i < item.getLikeList().size(); i++)
                if(item.getLikeList().get(i).getLikeOwner()!=null)
                sidList.add(item.getLikeList().get(i).getLikeOwner().getSid());
            if (sidList.contains(mUserHelper.getSid())) {
                btnPraise.setBackgroundResource(R.drawable.selector_circle_cancel);
            } else {
                btnPraise.setBackgroundResource(R.drawable.selector_circle_good);
            }
        } else
            btnPraise.setBackgroundResource(R.drawable.selector_circle_good);

        mPopup.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });

        btnPraise.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                btnPraise.setTag(item);
                if (onNeighborPost == null) return;
                onNeighborPost.postPraise((NeighborPostTo) v.getTag());
                mPopupWindow.dismiss();
            }
        });


        btnComment.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                btnComment.setTag(R.id.tag_first, item);
                if (onNeighborPost == null) return;
                onNeighborPost.postComment((NeighborPostTo) v.getTag(R.id.tag_first), null);
                mPopupWindow.dismiss();

            }
        });
    }

    public interface INeighborPostTo {
        void postComment(NeighborPostTo item, NeighborCommentTo comment);

        void postPraise(NeighborPostTo item);

        void postViewImage(int a, String item);

        void postDeleteItem(NeighborPostTo item);


    }
    public  void copyText(String text) {

        final CustomDialog dialog = new CustomDialog(mContext, R.layout.copy_text_dialog, R.style.myDialogTheme);
        TextView copy = (TextView) dialog.findViewById(R.id.copy);
        TextView cancel = (TextView) dialog.findViewById(R.id.cancel);
//        TextView report = (TextView) dialog.findViewById(R.id.report);
//        report.setOnClickListener(v2 -> mContext.startActivity(new Intent(mContext, NeighborReportActivity.class)));
        dialog.findViewById(R.id.layout).setOnClickListener(v1 -> dialog.dismiss());
        cancel.setOnClickListener(v -> dialog.dismiss());
copy.setOnClickListener(v -> {
    ClipboardManager cmb = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
    cmb.setText(text); //将内容放入粘贴管理器,在别的地方长按选择"粘贴"即可
    Toast.makeText(mContext,"文字已复制",Toast.LENGTH_SHORT).show();
    dialog.dismiss();
});
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        dialog.show();
    }



    private class TextClick extends ClickableSpan {
        private TextView view;
        private String content;
        private TextView expandAll;
        private String url;
        private String tag;
        private NeighborPostTo mode;
        private TextView textView;
        private String urlTitle;
public TextClick(TextView view,String content,TextView expandAll,String tag,NeighborPostTo mode,TextView textView){
    this.view=view;
    this.content=content;
    this.expandAll=expandAll;
    this.tag=tag;
    this.mode=mode;
    this.textView=textView;
}
        public TextClick(TextView view,String content,String tag){
            this.tag=tag;
            this.view=view;
            this.content=content;
        }
        public TextClick(TextView view,String content,String url,String urlTitle,NeighborPostTo mode){
            this.view=view;
            this.content=content;
            this.url=url;
            this.urlTitle=urlTitle;
            this.mode=mode;
        }
        @Override
        public void onClick(View widget) {
            if("收起".equals(tag)) {
                if(!TextUtils.isEmpty(mode.getPostSubject())){

                    Spannable ss=new SpannableString(mode.getSubPostContent());
                    ss.setSpan(new ForegroundColorSpan(Color.parseColor("#007aff")), 0, mode.getPostSubject().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    ss.setSpan(new TextClick(textView, mode.getPostContent(), mode.getPostUrl(),urlTitle,mode), 0, mode.getPostSubject().length()
                            , Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                textView.setMovementMethod(LinkMovementMethod.getInstance());
                    view.setText(ss);
                }else
                view.setText(mode.getSubPostContent());

                view.setMaxLines(3);
                textViewList.remove(view);
                expandAll.setVisibility(View.VISIBLE);
            }else if("收起全文".equals(tag)){
                investigateContent.setMaxLines(5);
                investigateContent.setText(subContent);
                expandAllTop.setVisibility(View.VISIBLE);
            }
            else {
              Intent intent=new Intent(mContext, NeighborLinkShowActivity.class);
                intent.putExtra("neighborUrl",url);
                if(mode!=null)
                    System.out.println(url);
                intent.putExtra("urlTitle",mode.getPostSubject());
                mContext.startActivity(intent);
            }
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setColor(Color.parseColor("#007aff"));
        }
    }
    public void setMaxLine(){
        for(TextView textView :textViewList){
            textView.setMaxLines(3);
        }

    }
    public String getDateTime(Date date){
        String commentTime;
        long commentTimeSecond=( DateUtil.getDate().getTime()- date.getTime())/1000/60;
        if(commentTimeSecond/60<1)
            commentTime=commentTimeSecond/60+"分钟前";
        else if(commentTimeSecond/60<24)
            commentTime=commentTimeSecond/60+"小时前";
        else
            commentTime=commentTimeSecond/60/24+"天前";
        return commentTime;

    }
}