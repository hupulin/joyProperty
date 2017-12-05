package com.joy.property.reaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jinyi.ihome.module.reaction.ReactionTimeListTo;
import com.joy.common.api.ApiClient;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.ReactionApi;
import com.joy.property.R;
import com.joy.property.base.BaseFragment;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by xz on 2016/5/28.
 **/
@SuppressLint("ValidFragment")
public class ReactionRateFragmentPark extends BaseFragment{

    private TextView responseTodayMinute;
    private TextView responseTodayHour;
    private TextView responseTodayDay;
    private TextView responseHistoryMinute;
    private TextView responseHistoryHour;
    private TextView responseHistoryDay;
    private TextView responseRateMinute;
    private TextView responseRateHour;
    private TextView responseRateDay;
    private TextView processTodayMinute;
    private TextView processTodayHour;
    private TextView processTodayDay;
    private TextView processHistoryMinute;
    private TextView processHistoryHour;
    private TextView processHistoryDay;
    private TextView processRateMinute;
    private TextView processRateHour;
    private TextView processRateDay;
    private String apartmentSid;
    public ReactionRateFragmentPark(String apartmentSid){
        this.apartmentSid=apartmentSid;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.item, container, false);
        findById(rootView);
   getData(apartmentSid);
        return rootView;
    }



    private void findById(View view) {
       //每个名称后面的minute表示15分钟，hour表示一小时，day表示24小时
        responseTodayMinute = (TextView) view.findViewById(R.id.tv_response_time_over_today_minute);
        responseTodayHour = (TextView) view.findViewById(R.id.tv_response_time_over_today_hour);
        responseTodayDay = (TextView) view.findViewById(R.id.tv_response_time_over_today_day);
        responseHistoryMinute = (TextView) view.findViewById(R.id.tv_response_time_over_history_minute);
        responseHistoryHour = (TextView) view.findViewById(R.id.tv_response_time_over_history_hour);
        responseHistoryDay = (TextView) view.findViewById(R.id.tv_response_time_over_history_day);
        responseRateMinute = (TextView) view.findViewById(R.id.tv_response_time_over_rate_minute);
        responseRateHour = (TextView) view.findViewById(R.id.tv_response_time_over_rate_hour);
        responseRateDay = (TextView) view.findViewById(R.id.tv_response_time_over_rate_day);
        processTodayMinute = (TextView) view.findViewById(R.id.tv_process_time_over_today_minute);
        processTodayHour = (TextView) view.findViewById(R.id.tv_process_time_over_today_hour);
        processTodayDay = (TextView) view.findViewById(R.id.tv_process_time_over_today_day);
        processHistoryMinute = (TextView) view.findViewById(R.id.tv_process_time_over_history_minute);
        processHistoryHour = (TextView) view.findViewById(R.id.tv_process_time_over_history_hour);
        processHistoryDay = (TextView) view.findViewById(R.id.tv_process_time_over_history_day);
        processRateMinute = (TextView) view.findViewById(R.id.tv_process_time_over_rate_minute);
        processRateHour = (TextView) view.findViewById(R.id.tv_process_time_over_rate_hour);
        processRateDay = (TextView) view.findViewById(R.id.tv_process_time_over_rate_day);


    }
    private void getData(String apartmentSid) {
        Toast.makeText(getThisContext(),apartmentSid,Toast.LENGTH_LONG).show();
        ReactionApi api= ApiClient.create(ReactionApi.class);
        System.out.println(apartmentSid+"----");
        api.getReactionData(apartmentSid, new HttpCallback<ReactionTimeListTo>(getThisContext()) {
            @Override
            public void success(ReactionTimeListTo msg, Response response) {
                if(msg!=null)
                 setData(msg.getData().get(0));

            }

            @Override
            public void failure(RetrofitError error) {

                System.out.println(error.toString());
            }
        });
    }
    public void setData(ReactionTimeListTo.DataBean data){
        responseTodayMinute.setText(data.getValueXY().get(0).getToday()+"");
        responseTodayHour.setText(data.getValueXY().get(1).getToday()+"");
        responseTodayDay.setText(data.getValueXY().get(2).getToday()+"");
        responseHistoryMinute.setText(data.getValueXY().get(0).getHistory()+"");
        responseHistoryHour.setText(data.getValueXY().get(1).getHistory()+"");
        responseHistoryDay.setText(data.getValueXY().get(2).getHistory()+"");
        responseRateMinute.setText(data.getValueXY().get(0).getRate()+"%");
        responseRateHour.setText(data.getValueXY().get(1).getRate()+"%");
        responseRateDay.setText(data.getValueXY().get(2).getRate()+"%");
        processTodayMinute.setText(data.getValueCL().get(0).getToday()+"");
        processTodayHour.setText(data.getValueCL().get(1).getToday()+"");
        processTodayDay.setText(data.getValueCL().get(2).getToday()+"");
        processHistoryMinute.setText(data.getValueCL().get(0).getHistory()+"");
        processHistoryHour.setText(data.getValueCL().get(1).getHistory()+"");
        processHistoryDay.setText(data.getValueCL().get(2).getHistory()+"");
        processRateMinute.setText(data.getValueCL().get(0).getRate()+"%");
        processRateHour.setText(data.getValueCL().get(1).getRate() + "%");
        processRateDay.setText(data.getValueCL().get(2).getRate() + "%");
    }
}
