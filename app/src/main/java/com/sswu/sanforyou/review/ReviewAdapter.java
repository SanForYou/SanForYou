package com.sswu.sanforyou.review;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.sswu.sanforyou.R;

import java.util.ArrayList;

public class ReviewAdapter extends BaseAdapter {
    ArrayList<Review> reviews = new ArrayList<>();

    public ReviewAdapter() {
    }

    @Override
    public int getCount() {
        return reviews.size();
    }

    @Override
    public Object getItem(int position) {
        return reviews.get(position);
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
            convertView = inflater.inflate(R.layout.review_list_item, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        TextView mountainName = (TextView) convertView.findViewById(R.id.mountainName);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.review_photo);
        TextView userName = (TextView) convertView.findViewById(R.id.userName);
        TextView content = (TextView) convertView.findViewById(R.id.review_content);
        RatingBar rating = (RatingBar) convertView.findViewById(R.id.ratingView);

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        Review review = reviews.get(position);

        mountainName.setText(review.getMountainName());
        imageView.setImageResource(review.getImage());
        userName.setText(review.getUserName());
        content.setText(review.getContent());
        rating.setRating(review.getRating());

        return convertView;
    }

    public void addReview(String mountainName, float rating, int image, String userName, String content){
        Review review = new Review(mountainName, rating, image, userName, content);
        reviews.add(review);
    }
}
