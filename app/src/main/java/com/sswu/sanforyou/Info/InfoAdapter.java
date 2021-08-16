package com.sswu.sanforyou.Info;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sswu.sanforyou.R;
import com.sswu.sanforyou.review.Review;

import java.util.ArrayList;

public class InfoAdapter extends BaseAdapter {
    ArrayList<Info> information;

    public InfoAdapter(ArrayList<Info> information) {
        this.information = information;
    }

    @Override
    public int getCount() {
        return information.size();
    }

    @Override
    public Object getItem(int position) {
        return information.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.info_item, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        TextView mountainName = (TextView) convertView.findViewById(R.id.info_mountainName);
      //  TextView mountainURL = (TextView) convertView.findViewById(R.id.mountainImage);
        TextView scope = (TextView) convertView.findViewById(R.id.info_scope);
        TextView address = (TextView) convertView.findViewById(R.id.info_address);
        TextView latitude = (TextView) convertView.findViewById(R.id.info_latitude);
        TextView longitude = (TextView) convertView.findViewById(R.id.info_longitude);
        TextView height = (TextView) convertView.findViewById(R.id.info_height);


        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        Info info = information.get(position);

        mountainName.setText(info.getMountainName());
      //  mountainURL.setText(info.getMountainURL());
        scope.setText(Double.toString(info.getScope()));
        address.setText(info.getAddress());
        latitude.setText(Double.toString(info.getLatitude()));
        longitude.setText(Double.toString(info.getLongitude()));
        height.setText(info.getHeight());


        return convertView;
    }

}