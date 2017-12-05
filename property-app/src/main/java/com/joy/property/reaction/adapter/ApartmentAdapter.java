package com.joy.property.reaction.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jinyi.ihome.module.apartment.ApartmentInfoTo;
import com.joy.property.R;
import com.joy.property.common.adapter.ModeListAdapter;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * Created by Admin on 2015-02-04
 */
public class ApartmentAdapter extends ModeListAdapter<ApartmentInfoTo> {
    private Context  mContext ;
    public ApartmentAdapter(Context context) {
        super(context);
        this.mContext = context ;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ApartmentInfoTo mode = mList.get(position);
        LayoutInflater inflater  = LayoutInflater.from(mContext);
        if (convertView == null) {
           convertView = inflater.inflate(R.layout.list_item_apartment ,null);
        }

        TextView mApartment = (TextView) convertView.findViewById(R.id.city_name);
        mApartment.setText(mode.getApartmentName());
        return convertView;
    }

    /**
     * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
     */
    public int getPositionForSection(int section) {
        for (int i = 0; i < getCount(); i++) {
            String sortStr = getPingYin(mList.get(i).getPlace().getName()).toUpperCase().substring(0,1);
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
}
