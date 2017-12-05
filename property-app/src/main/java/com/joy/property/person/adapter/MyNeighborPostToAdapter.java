package com.joy.property.person.adapter;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.text.Spannable;
import android.text.SpannableString;
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
import com.joy.property.neighborhood.adapter.CommentTextView;
import com.joy.property.neighborhood.adapter.NeighborCache;
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
public class MyNeighborPostToAdapter extends ModeListAdapter<NeighborPostTo> {
    private Context mContext;

    private INeighborPostTo onNeighborPost;
    private PopupWindow mPopupWindow = null;
    private LayoutInflater inflater = null;
    private final ImageLoader loader;
    private final DisplayImageOptions option;
    private Button btnPraise;
    private List<TextView>textViewList=new ArrayList<>();
    private final View mView;
    public TextView textview;
    private List<String> sidList = new ArrayList<>();
    private String urlContent="";
    public MyNeighborPostToAdapter(Context context) {
        super(context);
        this.mContext = context;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        final NeighborCache holder;


        if (row == null) {
            row = inflater.inflate(R.layout.list_item_circle_post, null);
            holder = new NeighborCache(row);
            row.setTag(holder);
        } else {
            holder = (NeighborCache) row.getTag();

        }
        textview= (TextView) row.findViewById(R.id.content);
        final NeighborPostTo mode = mList.get(position);
        System.out.println(mode.toString());
        if (!TextUtils.isEmpty(mode.getPostContent())) {
//            holder.getPostContent().setText(mode.getPostContent());
            urlContent=mode.getPostContent();
            if(!TextUtils.isEmpty(mode.getPostSubject()))
                urlContent=mode.getPostSubject()+mode.getPostContent();
            else
                urlContent=mode.getPostContent();
            holder.getPostContent().setOnLongClickListener(v -> {
                copyText(urlContent);
                return false;
            });
            holder.getLikeLine().setVisibility(View.VISIBLE);



        holder.getPostContent().setMaxLines(100);
                SpannableString ss=new SpannableString(mode.getPostSubject()==null?(mode.getPostContent()):(mode.getPostSubject()+mode.getPostContent()));
                if(mode.getPostSubject()!=null) {

                    ss.setSpan(new ForegroundColorSpan(Color.parseColor("#576b95")), 0, mode.getPostSubject().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    ss.setSpan(new TextClick(holder.getPostContent(), mode.getPostContent(), mode.getPostUrl(),mode.getPostSubject()
                    ), 0, mode.getPostSubject().length()
                            , Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    holder.getPostContent().setMovementMethod(LinkMovementMethod.getInstance());
                }
                holder.getPostContent().setText(ss);
                holder.getExpandAll().setVisibility(View.GONE);

            }




        if (mode.getPostOwner() != null && !TextUtils.isEmpty(mode.getPostOwner().getName())){
            holder.getPostOwner().setText(mode.getPostOwner().getName());
        }
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
         * 评论显示
         */
        if (!TextUtils.isEmpty(mode.getPostTimeStr())) {
            holder.getPostTime().setText(mode.getPostTimeStr());
        }
        if (!TextUtils.isEmpty(mode.getPostTypeName())) {
            holder.getmLabel().setText(mode.getPostTypeName());
        }
        holder.getTriangle().setVisibility(View.GONE);
        holder.getmLike().setVisibility(View.GONE);
        holder.getLikeList().setVisibility(View.GONE);
        holder.getCommentList().removeAllViews();
        List<NeighborLikeTo> list;
        list = mode.getLikeList();
        if (list != null && list.size() > 0) {
            holder.getLikeCount().setText(list.size()+"");
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
            for (int i = 0; i <list.size(); i++)
                if(list.get(i).getLikeOwner()!=null)
                    sidList.add(list.get(i).getLikeOwner().getSid());
            if (sidList.contains(mUserHelper.getSid())) {
                holder.getLikeCount().setTextColor(Color.parseColor("#f17834"));
                holder.getLikeIcon().setBackgroundResource(R.drawable.like_press);
            } else {
                holder.getLikeCount().setTextColor(Color.parseColor("#999999"));
                holder.getLikeIcon().setBackgroundResource(R.drawable.like_unpress);
            }
        }else
            holder.getLikeCount().setText("0");

        List<NeighborCommentTo> mList = mode.getCommentList();
        if (mList != null && mList.size() > 0) {
            holder.getCommentCount().setText(mList.size()+"");
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
                HeadImageView headImageView= (HeadImageView) mView.findViewById(R.id.comment_head_image);
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
                    if(commentTo.getAtOwner()!=null){
                        String atOwner = commentTo.getAtOwner().getName();
                        // value = String.format("<font color='#95a5b0'>%s</font>%s<font color='#95a5b0'>%s:</font>", user, "回复", atOwner);
                        ss = new SpannableString(" 回复@" + atOwner + " ：" + comment);
                        ss.setSpan(new ForegroundColorSpan(0xfff17834), 3,4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        ss.setSpan(new ForegroundColorSpan(0xfff17834),  4,atOwner.length() + 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
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
            holder.getCommentCount().setText("0");
        }


        holder.getFlowLayout().removeAllViews();
        if (!TextUtils.isEmpty(mode.getPostImages())) {
            String[] path = mode.getPostImages().split(";");
            int mScreenWidth = getScreenWidthPixels(mContext);
            int mWidth = (mScreenWidth - 300) / 3;
            FlowLayout.LayoutParams param = new FlowLayout.LayoutParams(mWidth, mWidth);
            for (int i = 0; i < path.length; i++) {
                ImageView postImage = new ImageView(mContext);
                //displayImage(postImage, path[i]);
                //   imageLoader.displayImage(MainApp.getImagePath(path[i]),postImage);
                //  loader.displayImage(MainApp.getImagePath(path[i]),postImage,option);

                Picasso.with(mContext).load(MainApp.getImagePath(path[i])).placeholder(R.drawable.post_content_bg).error(R.drawable.post_content_bg).into(postImage);

                postImage.setScaleType(ImageView.ScaleType.FIT_XY);
                param.setMargins(7, 7, 7, 7);
                postImage.setTag(R.id.tag_first, i);
                postImage.setTag(R.id.tag_second, mode.getPostImages());
                postImage.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onNeighborPost != null) {
                            onNeighborPost.postViewImage((int) v.getTag(R.id.tag_first), (String) v.getTag(R.id.tag_second));
                        }
                    }
                });


                holder.getFlowLayout().addView(postImage, param);

            }

        }

//        holder.getCommentOpen().setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                initPopupWindow(mode);
//                if (mPopupWindow.isShowing()) {
//                    mPopupWindow.dismiss();
//                } else {
//                    mPopupWindow.showAsDropDown(holder.getCommentOpen(), -60, -60);
//                }
//            }
//
//
//        });

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
        if (position==getCount()-1)
            holder.getDivideLine().setVisibility(View.GONE);
        else
            holder.getDivideLine().setVisibility(View.VISIBLE);


        return row;
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
        if (item.getLikeList().size() > 0) {
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
                System.out.println(v+"V");
                System.out.println(onNeighborPost);
                System.out.println(v.getTag());
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
    public  String getSubString(TextView tv, String content, int maxLine){

        float width = tv.getPaint().measureText(content);
        //这里只是为了方便，用屏幕宽度代替了textview控件宽度，如果需要精准控制，可以换成控件宽度
        double tvWidth =getScreenWidthPixels(mContext)*0.9333;
        TextView textView=new TextView(mContext);
        if(width / tvWidth > (maxLine + 0.7)){
            double line=width /tvWidth;
            double lineNumber=content.length()/line;
            String text1="";
            String text2="";

            content= content.substring(0,(int)(lineNumber*(maxLine-1 + 0.7)));
            if(content.contains("\n"))
            {
                text1=content.substring(0,content.indexOf("\n")+1);
                content=content.substring(content.indexOf("\n")+1,content.length());
                textView.setText(text1);

                width = textView.getPaint().measureText(content);
                if(width / tvWidth > (textView.getLineCount()==2?1:2 + 0.7)) {
                    line = width / tvWidth;

                    lineNumber = content.length() / line;
                    if (textView.getLineCount() == 3)
                        content = text1;
                    else
                        content = text1 + content.substring(0, (int) (lineNumber * ((textView.getLineCount() == 1 ? 2 : 1) - 1 + 0.7)));
                    return content;
                }else
                    return text1+content;
//
// if(textView.getLineCount()==2)
//               return  text1+content;
//                else {
//               text2=content.substring(0,content.indexOf("\n")+1);
//               content=content.substring(content.indexOf("\n")+1,content.length());
//               textView.setText(text2);
//               content= content.substring(0,(int)(lineNumber*((textView.getLineCount()==1?2:1)-1 + 0.65))) ;
//          return text1+text2+content;
            }
            //        }
        }
        return content;
    }
//public  String getSubString(TextView tv, String content, int maxLine){
//    float width = tv.getPaint().measureText(content);
//    //这里只是为了方便，用屏幕宽度代替了textview控件宽度，如果需要精准控制，可以换成控件宽度
//    double tvWidth =getScreenWidthPixels(mContext)*0.9333;
//
//    if(width / tvWidth > (maxLine + 0.65)){
//        double line=width/tvWidth;
//        double lineNumber=content.trim().length()/line;
//
//        return content.substring(0,(int)(lineNumber*(maxLine-1 + 0.65))) + "...";
//    }
//    return content;
//}

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
        public TextClick(TextView view,String content,String url,String urlTitle){
            this.view=view;
            this.content=content;
            this.url=url;
            this.urlTitle=urlTitle;
        }
        @Override
        public void onClick(View widget) {
            if("收起".equals(tag)) {
                if(!TextUtils.isEmpty(mode.getPostSubject())){
                    Spannable ss=new SpannableString(getSubString(view, mode.getPostSubject()+content, 3));
                    ss.setSpan(new ForegroundColorSpan(Color.parseColor("#576b95")), 0, mode.getPostSubject().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    ss.setSpan(new TextClick(textView, mode.getPostContent(), mode.getPostUrl(),urlTitle), 0, mode.getPostSubject().length()
                            , Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    textView.setMovementMethod(LinkMovementMethod.getInstance());
                    view.setText(ss);
                }else
                    view.setText(getSubString(view, content, 3));
                view.setMaxLines(3);
                textViewList.remove(view);
                expandAll.setVisibility(View.VISIBLE);
            }else {
                Intent intent=new Intent(mContext, NeighborLinkShowActivity.class);
                intent.putExtra("neighborUrl",url);
                intent.putExtra("urlTitle",urlTitle);
                mContext.startActivity(intent);
            }
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setColor(Color.parseColor("#576b95"));
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
            commentTime=commentTimeSecond+"分钟前";
        else if(commentTimeSecond/60<24)
            commentTime=commentTimeSecond/60+"小时前";
        else
            commentTime=commentTimeSecond/60/24+"天前";
        return commentTime;

    }
}