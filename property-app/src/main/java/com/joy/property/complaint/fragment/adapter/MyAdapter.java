package com.joy.property.complaint.fragment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jinyi.ihome.module.home.ServiceReportFlowTo;
import com.jinyi.ihome.module.home.ServiceReportGradeTo;
import com.joy.property.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/11.
 */
public class MyAdapter extends BaseAdapter{
    List<ServiceReportFlowTo> list_flowTwo;
    List<ServiceReportGradeTo> list_Grade;
    Context context;
    LayoutInflater inflater;
    //ComplaintManagerCache holder = null;
    public MyAdapter(Context context,ArrayList<ServiceReportFlowTo> list_flowTwo, ArrayList<ServiceReportGradeTo> list_Grade) {
        this.list_flowTwo = list_flowTwo;
        this.list_Grade = list_Grade;
        this.context=context;
        inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {

        if (list_flowTwo==null&&list_Grade==null){
            return 0;
        }

        return list_flowTwo.size()>list_Grade.size()||list_Grade.size()==list_flowTwo.size()?list_flowTwo.size():list_flowTwo.size();
    }

    @Override
    public Object getItem(int position) {
        return list_flowTwo.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHoder viewHoder=null;
        if (convertView==null){
            convertView=inflater.inflate(R.layout.list_complaint_item,parent,false);
            viewHoder=new ViewHoder();
            viewHoder.textView=(TextView)convertView.findViewById(R.id.pending);
            viewHoder.textView2= (TextView) convertView.findViewById(R.id.praise);
            convertView.setTag(viewHoder);
        }else {
            viewHoder= (ViewHoder) convertView.getTag();
        }
        if (list_flowTwo.size()>list_Grade.size()||list_Grade.size()==list_flowTwo.size()){
            viewHoder.textView.setText(list_flowTwo.get(position).getApartmentName()+"mm");
            if (position>list_Grade.size()-1){

            }else {
                viewHoder.textView2.setText(list_Grade.get(position).getValueTS().get("praise")+"qq");

            }

        }else {
            viewHoder.textView2.setText(list_Grade.get(position).getApartmentName()+"mm");
            if (position>list_flowTwo.size()-1){

            }else {
                viewHoder.textView.setText(list_flowTwo.get(position).getApartmentName()+"qq");
            }

        }




        return convertView;
    }



    class ViewHoder{
        TextView textView,textView2;
    }



    public void add_list_flowTwo(List<ServiceReportFlowTo> add_flow,boolean isClear){
     //刷新的list
        if (isClear){
            list_flowTwo.clear();
           // list_Grade.clear();
        }
        list_flowTwo.addAll(add_flow);

        if (list_flowTwo.size()>list_Grade.size()||list_Grade.size()==list_flowTwo.size()){

        }else if (list_flowTwo.size()<list_Grade.size()){
            for (int i=0;i<list_Grade.size()-list_flowTwo.size();i++){
                list_flowTwo.add(new ServiceReportFlowTo());
            }
        }

        this.notifyDataSetChanged();

    }

    public void add_list_grad(List<ServiceReportGradeTo> addgrad,boolean isClear){
        if (isClear){
            list_Grade.clear();
        }
        list_Grade.addAll(addgrad);

    }




}
