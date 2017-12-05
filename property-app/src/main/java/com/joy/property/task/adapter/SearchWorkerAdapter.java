package com.joy.property.task.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jinyi.ihome.module.owner.UserInfoTo;
import com.joy.property.R;
import com.joy.property.common.adapter.ModeListAdapter;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * Created by usb on 2016/8/15.
 */
public class SearchWorkerAdapter extends ModeListAdapter<UserInfoTo> {
    private Context mContext;

    public SearchWorkerAdapter(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ForwardCenterCache holder;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        if (row == null) {
            row = inflater.inflate(R.layout.list_item_forwardcenter, null);
            holder = new ForwardCenterCache(row);
            row.setTag(holder);
        } else {
            holder = (ForwardCenterCache) row.getTag();
        }
        UserInfoTo mode = mList.get(position);
        if (!TextUtils.isEmpty(mode.getName())) {
            holder.getmTypeName().setText(mode.getName());
        }

        if (!TextUtils.isEmpty(mode.getPhone())) {
            holder.getmPhone().setText(mode.getPhone());
        }
        displayImage(holder.getmHeadImage(), mode.getImage(), R.drawable.head_images);
        return row;
    }

    public void remove(UserInfoTo object) {
        mList.remove(object);
        notifyDataSetChanged();
    }

    public void add(UserInfoTo object) {
        mList.add(object);
        notifyDataSetChanged();
    }

    public int getPositionForSection(int section) {
        for (int i = 0; i < getCount(); i++) {
            String sortStr = getPingYin(mList.get(i).getName()).toUpperCase().substring(0, 1);
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
