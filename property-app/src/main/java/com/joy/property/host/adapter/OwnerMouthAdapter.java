package com.joy.property.host.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ToxicBakery.viewpager.transforms.ABaseTransformer;
import com.ToxicBakery.viewpager.transforms.ForegroundToBackgroundTransformer;
import com.ToxicBakery.viewpager.transforms.RotateUpTransformer;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.jinyi.ihome.module.common.AdInfoTo;
import com.jinyi.ihome.module.common.SatisfactionTo;
import com.jinyi.ihome.module.home.ServiceReportGradeTo;
import com.joy.property.R;
import com.joy.property.common.adapter.ModeListAdapter;
import com.joyhome.nacity.app.propertyCenter.NetworkImageHolderView;

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
 * Created by Admin on 2015-02-09
 */
public class OwnerMouthAdapter
        extends ModeListAdapter<ServiceReportGradeTo> {


    private Context mContext;

    public OwnerMouthAdapter(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        OwnerMouthHolder holder;
        ServiceReportGradeTo mode = mList.get(position);
        LayoutInflater inflater = LayoutInflater.from(mContext);

        if (row == null) {
            row = inflater.inflate(R.layout.list_owner_item, null);
            holder = new OwnerMouthHolder(row);
            row.setTag(holder);
        } else {
            holder = (OwnerMouthHolder) row.getTag();
        }

        if (!TextUtils.isEmpty(mode.getApartmentName())) {
            holder.getApartmentName().setText(mode.getApartmentName());
        }
        holder.getImage().setBackgroundResource("入室维修".equals(mode.getApartmentName()) ? R.drawable.room_repair_icon : "公共维修".equals(mode.getApartmentName()) ? R.drawable.public_repair_icon :"投诉管理".equals(mode.getApartmentName())?R.drawable.complaint_manag_icon:"家政管理".equals(mode.getApartmentName())?R.drawable.house_keeping_icon:R.drawable.images);
        //满意度的的好中差评
        List<SatisfactionTo> satisfactionList=new ArrayList<>();
        if (mode.getValueSatisfied() != null) {
            /***
             * praise 好评
             * medium 中评
             * bad 差评
             */
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
           satisfactionList.add(new SatisfactionTo(mGood,mMedium,mBad));
        } else {
            satisfactionList.add(new SatisfactionTo(0.0f,0.0f,0.0f));
        }

        // 服务汇总 :valueService;
        if (mode.getValueService() != null) {
            /***
             * praise 好评
             * medium 中评
             * bad 差评
             */
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
            satisfactionList.add(new SatisfactionTo(mGood,mMedium,mBad));
        } else {
            satisfactionList.add(new SatisfactionTo(0.0f,0.0f,0.0f));
        }


        // 解决速度汇总valueSolve;
        if (mode.getValueSolve() != null) {
            /***
             * praise 好评
             * medium 中评
             * bad 差评
             */
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
            satisfactionList.add(new SatisfactionTo(mGood,mMedium,mBad));
        } else {
            satisfactionList.add(new SatisfactionTo(0.0f,0.0f,0.0f));
        }
      setTopAutoRow(satisfactionList,holder.getAutoRow(),holder.getServiceAttitude(),holder.getSolveSpeed(),holder.getSatisfaction());

        return row;
    }

    public int getPositionForSection(int section) {
        for (int i = 0; i < getCount(); i++) {
            String sortStr = getPingYin(mList.get(i).getApartmentName()).toUpperCase().substring(0, 1);
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
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

                if (Character.toString(curchar).matches(

                        "[\\u4E00-\\u9FA5]+")) {

                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(

                            curchar, format);

                    output += temp[0];

                } else

                    output += Character.toString(curchar);

            }

        } catch (BadHanyuPinyinOutputFormatCombination e) {

            e.printStackTrace();

        }

        return output;

    }

    private void setTopAutoRow(List<SatisfactionTo> mList,ConvenientBanner autoRow,TextView serviceAttitude,TextView solveSpeed,TextView satisfaction) {
        String transformerName = ForegroundToBackgroundTransformer.class.getSimpleName();
        try {
            Class cls = Class.forName("com.ToxicBakery.viewpager.transforms." + transformerName);
            ABaseTransformer transformer = (ABaseTransformer) cls.newInstance();
            autoRow.getViewPager().setPageTransformer(true, transformer);
            //部分3D特效需要调整滑动速度
            if (transformerName.equals("StackTransformer")) {
                autoRow.setScrollDuration(1200);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }



        autoRow.setPages(AutoRowView::new, mList    ).setPageIndicator(new int[]{R.drawable.auto_row_dot_gray, R.drawable.auto_row_dot_black});

        autoRow.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    serviceAttitude.setBackgroundColor(Color.parseColor("#62bfe1"));
                    solveSpeed.setBackgroundColor(Color.parseColor("#ffffff"));
                    satisfaction.setBackgroundColor(Color.parseColor("#ffffff"));
                    serviceAttitude.setTextColor(Color.parseColor("#ffffff"));
                    solveSpeed.setTextColor(Color.parseColor("#999999"));
                    satisfaction.setTextColor(Color.parseColor("#999999"));
                } else if (position == 1) {
                    serviceAttitude.setBackgroundColor(Color.parseColor("#ffffff"));
                    solveSpeed.setBackgroundColor(Color.parseColor("#62bfe1"));
                    satisfaction.setBackgroundColor(Color.parseColor("#ffffff"));
                    serviceAttitude.setTextColor(Color.parseColor("#999999"));
                    solveSpeed.setTextColor(Color.parseColor("#ffffff"));
                    satisfaction.setTextColor(Color.parseColor("#999999"));
                } else if (position == 2) {
                    serviceAttitude.setBackgroundColor(Color.parseColor("#ffffff"));
                    solveSpeed.setBackgroundColor(Color.parseColor("#ffffff"));
                    satisfaction.setBackgroundColor(Color.parseColor("#62bfe1"));
                    serviceAttitude.setTextColor(Color.parseColor("#999999"));
                    solveSpeed.setTextColor(Color.parseColor("#999999"));
                    satisfaction.setTextColor(Color.parseColor("#ffffff"));
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        serviceAttitude.setOnClickListener(v -> autoRow.setcurrentitem(0));
        solveSpeed.setOnClickListener(v -> autoRow.setcurrentitem(1));
        satisfaction.setOnClickListener(v -> autoRow.setcurrentitem(2));
        autoRow.startTurning(4000);
    }

}
