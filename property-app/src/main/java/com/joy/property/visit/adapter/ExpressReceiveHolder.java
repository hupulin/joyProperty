package com.joy.property.visit.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joy.property.R;

/**待领取
 *  拍照领取快递 * 领取快递列表

 * Created by usb on 2017/8/1.
 */


    public class ExpressReceiveHolder {
        private View     view;
        private TextView apartmentName;
        private TextView phoneNo;
        private TextView tvSelectExpress;
        private TextView  expressName;
        private TextView  expressNo;
        private TextView  inputTime;
    private TextView  remark;

    public TextView getRemark() {
        if (remark==null)
            remark= (TextView) view.findViewById(R.id.remark);
        return remark;
    }


    private TextView  resendCode;
    private TextView  receiveName;
    private TextView  getExpress;
    private ImageView expressImage;
    private ImageView isRegister;
    private ImageView selectExpress;

        public TextView getExpressName() {
            if (expressName==null)
                expressName= (TextView) view.findViewById(R.id.express_name);
            return expressName;
        }
        public TextView getReceivename() {
            if (receiveName==null)
                receiveName= (TextView) view.findViewById(R.id.name);
            return receiveName;
        }
        public TextView getTvSelectExpress() {
            if (tvSelectExpress==null)
                tvSelectExpress= (TextView) view.findViewById(R.id.tv_select_express);
            return tvSelectExpress;
        } public TextView getResendCode() {
            if (resendCode==null)
                resendCode= (TextView) view.findViewById(R.id.resend_code);
            return resendCode;
        } public TextView getGetExpress() {
            if (getExpress==null)
                getExpress= (TextView) view.findViewById(R.id.get_express);
            return getExpress;
        }

        public ImageView getSelectExpress() {
            if (selectExpress==null)
                selectExpress= (ImageView) view.findViewById(R.id.select_express);
            return selectExpress;
        }
        public ImageView getExpressImage() {
            if (expressImage==null)
                expressImage= (ImageView) view.findViewById(R.id.express_icon);
            return expressImage;
        }
 public ImageView getIsRegister() {
            if (isRegister==null)
                isRegister= (ImageView) view.findViewById(R.id.isRegister);
            return isRegister;
        }

        public TextView getInputTime() {
            if (inputTime==null)
                inputTime= (TextView) view.findViewById(R.id.inputTime);
            return inputTime;
        }

        public ExpressReceiveHolder(View view) {
            this.view = view;
        }
        public TextView getPhoneNo() {
            if (phoneNo==null)
                phoneNo= (TextView) view.findViewById(R.id.phoneNo);
            return phoneNo;
        }


        public TextView getExpressNo() {
            if (expressNo==null)
                expressNo= (TextView) view.findViewById(R.id.expressNo);
            return expressNo;
        }

        public TextView getApartmentName() {
            if (apartmentName==null)
                apartmentName= (TextView) view.findViewById(R.id.apartment_name);
            return apartmentName;
        }
    private RelativeLayout  itemCode;

    public RelativeLayout getItemCode() {
            if (itemCode==null)
                itemCode= (RelativeLayout) view.findViewById(R.id.item_code);
            return itemCode;
        }





}
