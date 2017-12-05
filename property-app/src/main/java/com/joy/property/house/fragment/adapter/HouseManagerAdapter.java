package com.joy.property.house.fragment.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ToxicBakery.viewpager.transforms.ABaseTransformer;
import com.ToxicBakery.viewpager.transforms.RotateUpTransformer;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.jinyi.ihome.module.common.ComplaintTo;
import com.jinyi.ihome.module.home.ServiceReportFlowTo;
import com.joy.property.R;
import com.joy.property.common.adapter.ModeListAdapter;
import com.joy.property.complaint.NetworkViewHolderView;
import com.joy.property.complaint.fragment.adapter.ComplaintManagerCache;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 2015-02-10
 */
public class HouseManagerAdapter extends ModeListAdapter<ServiceReportFlowTo> {


    private Context mContext;
    ServiceReportFlowTo mode;
    public HouseManagerAdapter(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ComplaintManagerCache holder = null;
        mode = mList.get(position);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        if (row == null) {
            row = inflater.inflate(R.layout.list_complaint_item1, null);
            holder = new ComplaintManagerCache(row);
            row.setTag(holder);
        } else {
            holder = (ComplaintManagerCache) row.getTag();
        }
        //row.setTag(R.id.tag_first1,mode.getApartmentSid());
        //holder.getPie().setTag(mode.getApartmentSid());

        if (!TextUtils.isEmpty(mode.getApartmentName())) {
            holder.getmApartment().setText(mode.getApartmentName());
        }
        /***
         * pending 待处理
         * processing 处理中
         * processed 已处理
         *
         */
        if (mode.getValue() != null) {
            int mProcessing = 0;
            int mPending = 0;
            int mProcessed = 0;
            if (mode.getValue().containsKey("pending")) {
                mPending = mode.getValue().get("pending");
            }

            if (mode.getValue().containsKey("processing")) {
                mProcessing = mode.getValue().get("processing");
            }

            if (mode.getValue().containsKey("processed")) {
                mProcessed = mode.getValue().get("processed");
            }
            holder.getmPending().setText(String.valueOf(mPending));
            holder.getmProcessing().setText(String.valueOf(mProcessing));
            holder.getmProcessed().setText(String.valueOf(mProcessed));
            int total = mPending + mProcessing + mProcessed;
            holder.getmComplain().setText(String.valueOf(total));
        }

        getAdInfoSecond(mode,holder.getLbt(),position);

        return row;
    }

    private void getAdInfoSecond(ServiceReportFlowTo mList,ConvenientBanner lbt,final int position){
        String transformerName = RotateUpTransformer.class.getSimpleName();
        try {
            Class cls = Class.forName("com.ToxicBakery.viewpager.transforms." + transformerName);
            ABaseTransformer transformer = (ABaseTransformer) cls.newInstance();
            lbt .getViewPager().setPageTransformer(true, transformer);
            //部分3D特效需要调整滑动速度
            if (transformerName.equals("StackTransformer")) {
                lbt.setScrollDuration(1200);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        List<ComplaintTo> networkImages = new ArrayList<>();
        List<ComplaintTo>tos = new ArrayList<>();



        //服务汇总 valueService
        if (mode.getValueService() != null){

//              * praise 好评
//              * medium 中评
//              * bad 差评

            float mGood = 0.0f;
            float mMedium = 0.0f;
            float mBad = 0.0f;

            if (mode.getValueService().containsKey("praise")) {
                mGood = mode.getValueService().get("praise");

            }
            if (mode.getValueService().containsKey("medium")) {
                mMedium = mode.getValueService().get("medium");
            }

            if (mode.getValueService().containsKey("bad")) {
                mBad = mode.getValueService().get("bad");
            }

            DecimalFormat df = new DecimalFormat("0.0");
            tos.add(new ComplaintTo(" 好评率：" + df.format(mGood) + "%", " 中评率：" + df.format(mMedium) + "%", " 差评率：" + df.format(mBad) + "%", R.drawable.serve, "服务态度"));

        }else{
            tos.add(new ComplaintTo(" 好评率：0.0%"," 中评率：0.0%"," 差评率：0.0%",R.drawable.serve,"服务态度"));
        }



        // 解决速度汇总  valueSolve;
        if (mode.getValueSolve() != null){

//              * praise 好评
//              * medium 中评
//              * bad 差评

            float mGood = 0.0f;
            float mMedium = 0.0f;
            float mBad = 0.0f;

            if (mode.getValueSolve().containsKey("praise")) {
                mGood = mode.getValueSolve().get("praise");

            }
            if (mode.getValueSolve().containsKey("medium")) {
                mMedium = mode.getValueSolve().get("medium");
            }

            if (mode.getValueSolve().containsKey("bad")) {
                mBad = mode.getValueSolve().get("bad");
            }

            DecimalFormat df = new DecimalFormat("0.0");
            tos.add(new ComplaintTo(" 好评率："+df.format(mGood)+"%"," 中评率："+df.format(mMedium) + "%"," 差评率："+df.format(mBad) + "%",R.drawable.response,"解决速度"));

        }else{
            tos.add(new ComplaintTo(" 好评率：0.0%"," 中评率：0.0%"," 差评率：0.0%",R.drawable.response,"解决速度"));
        }


        //满意汇总
        if (mode.getValueSatisfied() != null){

//              * praise 好评
//              * medium 中评
//              * bad 差评

            float mGood = 0.0f;
            float mMedium = 0.0f;
            float mBad = 0.0f;

            if (mode.getValueSatisfied().containsKey("praise")) {
                mGood = mode.getValueSatisfied().get("praise");

            }
            if (mode.getValueSatisfied().containsKey("medium")) {
                mMedium = mode.getValueSatisfied().get("medium");
            }

            if (mode.getValueSatisfied().containsKey("bad")) {
                mBad = mode.getValueSatisfied().get("bad");
            }

            DecimalFormat df = new DecimalFormat("0.0");
            tos.add(new ComplaintTo(" 好评率："+df.format(mGood)+"%"," 中评率："+df.format(mMedium) + "%"," 差评率："+df.format(mBad) + "%",R.drawable.satisfaction,"满意度"));
        }else{
            tos.add(new ComplaintTo(" 好评率：0.0%", " 中评率：0.0%", " 差评率：0.0%", R.drawable.satisfaction, "满意度"));
        }
        for (int i = 0; i < tos.size(); i++)
            networkImages.add(tos.get(i));
        lbt.setPages(new CBViewHolderCreator<NetworkViewHolderView>() {
            @Override
            public NetworkViewHolderView createHolder() {
                return new NetworkViewHolderView();
            }

        }, networkImages).setPageIndicator(new int[]{R.drawable.ic_page_indicators, R.drawable.ic_page_indicator_focuseds});
        lbt.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int positions) {
                listener.OnContinuePayClick(position);
            }
        });
        if(tos.size()<2){
            lbt.setCanLoop(false);

        }
    }


    private OnContinuePayClickListener listener;
    public void setOnContinuePayClickListener(OnContinuePayClickListener listener){
        this.listener=listener;
    }
    public interface OnContinuePayClickListener{
        void OnContinuePayClick(int serviceSid);
    }
    public int getPositionForSection(int section) {
        for (int i = 0; i < getCount(); i++) {
            String sortStr = getPingYin(mList.get(i).getApartmentName()).toUpperCase().substring(0,1);
            char firstChar = sortStr.toUpperCase().charAt(0);
            if(firstChar == section) {
                return i;
            }
        }

        return -1;
    }


    public static String getPingYin(String inputString) {

        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();

        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);

        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

        format.setVCharType(HanyuPinyinVCharType.WITH_V);

        char[] input = inputString.trim().toCharArray();

        String output = "";

        try {

            for (char curchar : input) {

                if (java.lang.Character.toString(curchar).matches(

                        "[\\u4E00-\\u9FA5]+")) {

                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(

                            curchar, format);

                    output += temp[0];

                } else

                    output += java.lang.Character.toString(curchar);

            }

        } catch (BadHanyuPinyinOutputFormatCombination e) {

            e.printStackTrace();

        }

        return output;

    }
}
