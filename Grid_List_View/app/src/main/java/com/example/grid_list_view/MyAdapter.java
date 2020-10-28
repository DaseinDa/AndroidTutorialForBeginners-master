package com.example.grid_list_view;
import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * 自定义BaseAdapter
 * @author NanFeiLong
 *
 */

public class MyAdapter extends BaseAdapter {

    private LayoutInflater mInflater = null;
    private Context mContext;
    private ArrayList<MyBean> mArrayList = null;

    public MyAdapter(Context context, ArrayList<MyBean> arrayList) {
        mContext = context;
        mArrayList = arrayList;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mArrayList == null ? 0 : mArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return mArrayList == null ? null : mArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder holder = null;
        MyBean mBean = (MyBean) getItem(position);
        if (convertView == null) {
            convertView = mInflater.from(mContext).inflate(R.layout.items, parent, false);
            holder = new ViewHolder();
            holder.imageView = (ImageView) convertView.findViewById(R.id.imageView1);
            //holder.imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            holder.textView = (TextView) convertView.findViewById(R.id.textView1);
            //注意这里是个坑，如果写在这里的话，listview中会出现item重复显示的bug，
            //本人当时由于写错了,在这里走了冤枉了，望读者能注意
            //holder.imageView.setImageResource(mBean.getImageId());
            //holder.textView.setText(mBean.getIconName());
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.imageView.setImageResource(mBean.getImageId());
        holder.textView.setText(mBean.getIconName());
        return convertView;
    }


    class ViewHolder {
        ImageView imageView;
        TextView textView;
    }

}
