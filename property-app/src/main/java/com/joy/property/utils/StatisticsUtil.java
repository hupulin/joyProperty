package com.joy.property.utils;

import android.content.Context;

import com.jinyi.ihome.infrastructure.MessageTo;
import com.joy.common.api.ApiClient;
import com.joy.common.api.HttpCallback;
import com.joy.property.DataStatistics.OwnerLogParam;
import com.joy.property.DataStatistics.OwnerLogTo;
import com.joy.property.DataStatistics.StatisticsApi;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by xz on 2016/6/20.
 */
public class StatisticsUtil {
    public static OwnerLogParam getStatictiscParam(String ownerSid,String content){
        OwnerLogParam param=new OwnerLogParam();
        param.setOsType("ANDROID");
        param.setSystemType("1");
        param.setDeviceModel(android.os.Build.MODEL + "");
        param.setContent(content);
        param.setOwnerSid(ownerSid);
        return param;
    }
//    public static void sendStatistics(String sid,String content) {
//        StatisticsApi api = ApiClient.create(StatisticsApi.class);
//        api.getStatistics(getStatictiscParam(sid,content)).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<MessageTo<OwnerLogTo>>() {
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                System.out.println("失败");
//                System.out.println(e.getCause());
//                System.out.println(e.getMessage());
//                System.out.println(e.toString());
//            }
//
//            @Override
//            public void onNext(MessageTo<OwnerLogTo> ownerLogToMessageTo) {
//                System.out.println("成功");
//            }
//        });
//    }



    public static void sendStatistics(String sid,String content,Context context) {
        //OwnerLogParam param=new OwnerLogParam();
        StatisticsApi api = ApiClient.create(StatisticsApi.class);
        api.getStatistics(getStatictiscParam(sid,content), new HttpCallback<MessageTo<OwnerLogTo>>(context) {
            @Override
            public void success(MessageTo<OwnerLogTo> msg, Response response) {

                System.out.println(msg.getSuccess());
                System.out.println(msg.getData());
                if (msg.getSuccess() == 0) {
                    //Toast.makeText(getThisContext(), "测试", Toast.LENGTH_LONG).show();
                } else {
                }
            }
            public void failure(RetrofitError error) {
                super.failure(error);
            }
        });
    }


}
