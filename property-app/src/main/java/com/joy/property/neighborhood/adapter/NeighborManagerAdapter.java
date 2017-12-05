package com.joy.property.neighborhood.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.jinyi.ihome.module.home.ServiceReportFlowTo;
import com.joy.property.R;
import com.joy.property.common.adapter.ModeListAdapter;
import com.joy.property.complaint.fragment.adapter.ComplaintManagerCache;
import com.joy.property.utils.NeighborService;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * Created by Admin on 2015-02-10
 */
public class NeighborManagerAdapter extends ModeListAdapter<ServiceReportFlowTo> {


    private Context mContext;
    public NeighborManagerAdapter(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ComplaintManagerCache holder = null;
        ServiceReportFlowTo mode = mList.get(position);
        NeighborService service = new Gson().fromJson(mode.getValue().toString(), NeighborService.class);



        LayoutInflater inflater = LayoutInflater.from(mContext);
        if (row == null) {
            row = inflater.inflate(R.layout.list_apartment_item, null);
            holder = new ComplaintManagerCache(row);
            row.setTag(holder);
        } else {
            holder = (ComplaintManagerCache) row.getTag();
        }

        if (!TextUtils.isEmpty(mode.getApartmentName())) {
            holder.getmApartment().setText(mode.getApartmentName());
        }

        /***
         * pending 待处理
         * processing 处理中
         * processed 已处理
         */
        if (mode.getValue() != null) {
//            int mProcessing = 0;
//            int mPending = 0;
//            int mProcessed = 0;
//            if (mode.getValue().containsKey("pending")) {
//                mPending = mode.getValue().get("pending");
//            }
//
//            if (mode.getValue().containsKey("processing")) {
//                mProcessing = mode.getValue().get("processing");
//            }
//
//            if (mode.getValue().containsKey("processed")) {
//                mProcessed = mode.getValue().get("processed");
//            }
//            holder.getmPending().setText(String.valueOf(mPending));
//            holder.getmProcessing().setText(String.valueOf(mProcessing));
//            holder.getmProcessed().setText(String.valueOf(mProcessed));
//            int total = mPending + mProcessing + mProcessed;

            holder.getmComplain().setText(service.getTodaycount()+"");
            holder.getmProcessing().setText(service.getTodaycomment()+"");
            holder.getmProcessed().setText(service.getCount()+"");


        }
        return row;
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
