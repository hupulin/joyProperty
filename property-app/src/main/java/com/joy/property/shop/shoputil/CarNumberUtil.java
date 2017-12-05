package com.joy.property.shop.shoputil;

import android.content.Context;
import android.widget.TextView;

import com.jinyi.ihome.infrastructure.MessageToBulk;
import com.jinyi.ihome.module.shop.CarNumberParam;
import com.joy.common.api.ApiClientBulk;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.NewShopApi;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by xz on 2017/1/13.
 */
public class CarNumberUtil {
    public static void getCarNumber(String userSid,Context context,TextView textView){
        CarNumberParam param=new CarNumberParam();
        param.setUserId(userSid);
        NewShopApi api= ApiClientBulk.create(NewShopApi.class);
        api.getCarNumber(param, new HttpCallback<MessageToBulk>(context) {
            @Override
            public void success(MessageToBulk msg, Response response) {
                if (msg.getCode()==0){
                    textView.setText(msg.getTotal()+"");
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
}
