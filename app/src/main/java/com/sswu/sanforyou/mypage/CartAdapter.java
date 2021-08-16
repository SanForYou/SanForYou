package com.sswu.sanforyou.mypage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sswu.sanforyou.R;

import java.util.ArrayList;

public class CartAdapter extends BaseAdapter {
    ArrayList<Cart> carts;

    public CartAdapter(ArrayList<Cart> carts){ this.carts = carts; }
    @Override
    public int getCount() {
        return carts.size();
    }

    @Override
    public Object getItem(int position) {
        return carts.get(position);
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
            convertView = inflater.inflate(R.layout.cart_list_item, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        TextView mountainName = (TextView) convertView.findViewById(R.id.mountainName);
        TextView address = (TextView) convertView.findViewById(R.id.address);

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        Cart cart = carts.get(position);

        mountainName.setText(cart.getMountainName());
        address.setText(cart.getAddress());

        return convertView;
    }
}
