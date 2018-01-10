package com.joy.property.worksign.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.holder.Holder;
import com.jinyi.ihome.module.worksign.MyPrintTo;
import com.jinyi.ihome.module.worksign.SignRecordInfoTo;
import com.joy.library.utils.DateUtil;
import com.joy.property.R;
import com.joy.property.worksign.SignRecordActivity;
import com.joy.property.worksign.fragment.SignFragment;

/**
 * Created by Sai on 15/8/4.
 * 网络图片加载例子
 */
public class FootprintHolderView implements Holder<MyPrintTo.TimeslistBean > {
    private View holdView;
    Context mContext;
    private SignRecordInfoTo recordInfoTo;

    public FootprintHolderView(SignRecordInfoTo recordInfoTo){
        this.recordInfoTo=recordInfoTo;
    }
    @Override
    public View createView(Context context) {
        //你可以通过layout文件来创建，也可以像我一样用代码创建，不一定是Image，任何控件都可以进行翻页
        holdView = View.inflate(context, R.layout.footprint_auto_row_item, null);
        return holdView;
    }

    @Override
    public void UpdateUI(Context context, int position, MyPrintTo.TimeslistBean timeTo) {
         if (position==0)
             holdView.setVisibility(View.INVISIBLE);
        else {
             holdView.setVisibility(View.VISIBLE);

             ((TextView) holdView.findViewById(R.id.work_content)).setText("工作内容：" + timeTo.getWorkContent());
             if (timeTo.getBlock().length() > 10) {
                 ((TextView) holdView.findViewById(R.id.sign_date)).setText(DateUtil.formatDateString(DateUtil.mFormatDateShort, timeTo.getBlock().substring(0, 10)));
                 ((TextView) holdView.findViewById(R.id.detail_date)).setText(DateUtil.getDateString(DateUtil.getFormatDateExpectTime((timeTo.getBlock().substring(0, 10) + " " + timeTo.getBlock().substring(11) + ":00")), DateUtil.mDateTimeFormatStringNoSecondSign));
             } else {
                 ((TextView) holdView.findViewById(R.id.sign_date)).setText(timeTo.getBlock());
                 ((TextView) holdView.findViewById(R.id.detail_date)).setText(timeTo.getBlock());
             }
             ((TextView) holdView.findViewById(R.id.address)).setText(timeTo.getWorkPlace());
             ((TextView) holdView.findViewById(R.id.sign_count)).setText("签到" + timeTo.getBktotal() + "次");

             holdView.findViewById(R.id.footprint_detail_layout).setOnClickListener(v -> {
                 Intent intent = new Intent(context, SignRecordActivity.class);
                 intent.putExtra("SignRecordInfo",recordInfoTo);
                 context.startActivity(intent);

             });
         }
    }

    public SignRecordInfoTo getSignRecordInfo(SignRecordInfoTo  recordInfoTo){
        return recordInfoTo;
    }

}
