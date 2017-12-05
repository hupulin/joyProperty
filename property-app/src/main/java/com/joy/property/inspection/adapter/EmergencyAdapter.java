package com.joy.property.inspection.adapter;

/**
 * Created by usb on 2017/3/3.
 */


    import android.content.Context;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.TextView;

    import com.joy.property.common.adapter.ModeListAdapter;
    import com.joy.property.R;


    public class EmergencyAdapter extends ModeListAdapter<String> {
        private Context  mContext ;
        public EmergencyAdapter(Context context) {
            super(context);
            this.mContext = context ;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater  = LayoutInflater.from(mContext);
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.list_item_type ,null);
            }

            TextView mApartment = (TextView) convertView.findViewById(R.id.type);

            mApartment.setText(mList.get(position));
            return convertView;
        }
    }


