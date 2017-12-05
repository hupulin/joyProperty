package com.joy.common.api;

import android.content.Context;
import android.widget.Toast;

import retrofit.Callback;
import retrofit.RetrofitError;

/**
 * Created by Admin on 2015-01-17
 */
public abstract class HttpCallback<T> implements Callback<T> {

    private Context mContext;
    public HttpCallback(Context context) {
       this.mContext = context;
    }

    @Override
    public void failure(RetrofitError error) {
        if (RetrofitError.Kind.NETWORK == error.getKind()) {
            Toast.makeText(mContext, "网络异常,请检查网络", Toast.LENGTH_LONG).show();
        } else if (RetrofitError.Kind.HTTP == error.getKind()) {
            Toast.makeText(mContext, "服务异常,可以通过用户反馈给开发团队,以便于更好的为您服务", Toast.LENGTH_LONG).show();
        }  else if (RetrofitError.Kind.UNEXPECTED == error.getKind()) {
            Toast.makeText(mContext, "未知异常,可以通过用户反馈给开发团队,以便于更好的为您服务", Toast.LENGTH_LONG).show();
        }else if (RetrofitError.Kind.CONVERSION == error.getKind()) {
            Toast.makeText(mContext, "服务异常,可以通过用户反馈给开发团队,以便于更好的为您服务", Toast.LENGTH_LONG).show();
        }

    }


}