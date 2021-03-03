package com.example.testaddgooglemap.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.testaddgooglemap.Model.MonHoc;
import com.example.testaddgooglemap.R;

import java.util.List;

public class MonhocAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<MonHoc> monHocList;

    public MonhocAdapter(Context context, int layout, List<MonHoc> monHocList) {
        this.context = context;
        this.layout = layout;
        this.monHocList = monHocList;
    }

    @Override
    public int getCount() {
        return monHocList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class ViewHolder{
        ImageView imageView;
        TextView textView_tenmonhoc,textView_mamonhoc;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);

            viewHolder.imageView = convertView.findViewById(R.id.imageView7);
            viewHolder.textView_mamonhoc = convertView.findViewById(R.id.textView_mamonhoc);
            viewHolder.textView_tenmonhoc = convertView.findViewById(R.id.textView_tenmonhoc);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textView_tenmonhoc.setText(monHocList.get(position).getTenMon());
        viewHolder.textView_mamonhoc.setText(monHocList.get(position).getMaMon());

        return convertView;
    }
}
