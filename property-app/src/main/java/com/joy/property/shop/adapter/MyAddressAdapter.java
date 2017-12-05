package com.joy.property.shop.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jinyi.ihome.module.newshop.AddressTo;
import com.jinyi.ihome.module.newshop.CouponTo;
import com.joy.property.R;
import com.joyhome.nacity.app.common.adapter.ModeListAdapter;

/**
 * Created by xz on 2016/7/12.
 **/
public class MyAddressAdapter extends ModeListAdapter<AddressTo> {
    private Context mContext;

    public MyAddressAdapter(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        MyAddressHolder holder;

        if (row == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            row = inflater.inflate(R.layout.my_address_item, null);
            holder = new MyAddressHolder(row);
            row.setTag(holder);
        } else {
            holder = (MyAddressHolder) row.getTag();
        }

        AddressTo mode = mList.get(position);
        if (mode != null) {
            holder.getAddressDetail().setText(mode.getReceiverArea()+""+mode.getReceiverDetailAddress());
            holder.getAddressName().setText(mode.getReceiverName());
            holder.getAddressPhone().setText(mode.getReceiverPhone());
            if(!TextUtils.isEmpty(mode.getIdentityCardNo())){
                holder.getIdCardNum().setText(mode.getIdentityCardNo().replaceAll("(?<=\\d{4})\\d(?=\\d{4})", "*") );
            }else{
                holder.getidCardLayout().setVisibility(View.GONE);
            }
            holder.getSetDefault().setText(mode.getIsDefaultAddress().equals("Y")? "默认地址" : "设为默认");
            holder.getSetDefault().setTextColor(mode.getIsDefaultAddress().equals("Y") ? Color.parseColor("#4fb2d6") : Color.parseColor("#353535"));
            holder.getIsSelect().setImageResource(mode.getIsDefaultAddress().equals("Y")? R.drawable.default_select : R.drawable.no_check);
            holder.getChangeDefaultAddress().setOnClickListener(view -> {
                if (setDefaultListener != null) {
                    setDefaultListener.setDefaultAddress(mode, position);
                }
            });
            holder.getAddressEdit().setOnClickListener(view -> {
                if (editListener != null) {
                    editListener.editAddress(mode, position);
                }
            });
            holder.getAddressDelete().setOnClickListener(view -> {
                if (deleteListener!=null){
                    deleteListener.deleteAddress(position);
                }
            });

        }
        return row;
    }
    private MyAddressEditListener editListener;
    private MyAddressDeleteListener deleteListener;
    private MyAddressSetDefaultListener setDefaultListener;


    public interface MyAddressSetDefaultListener {
        void setDefaultAddress(AddressTo mode, int position);

    }
    public void setMyAddressSetDefaultListener(MyAddressSetDefaultListener listener){
        this.setDefaultListener=listener;
    }
    public interface MyAddressEditListener {
        void editAddress(AddressTo mode, int position);

    }
    public void setMyAddressEditListener(MyAddressEditListener listener){
        this.editListener=listener;
    }

    public interface MyAddressDeleteListener {
        void deleteAddress(int position);

    }
    public void setMyAddressDeleteListener(MyAddressDeleteListener listener){
        this.deleteListener=listener;
    }
}
