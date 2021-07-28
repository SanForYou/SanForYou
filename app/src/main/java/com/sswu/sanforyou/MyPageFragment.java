package com.sswu.sanforyou;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MyPageFragment extends Fragment {
    private Button btn_cart, btn_logOut, btn_review;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /*
        btn_cart= btn_cart.findViewById(R.id.bt1_cart);
        btn_review= btn_review.findViewById(R.id.bt2_myReview);
        btn_logOut= btn_logOut.findViewById(R.id.bt3_logOut);


        //'산바구니' 버튼 클릭시 수행
        btn_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(MyPageFragment.this, activity_cart.class);
                //startActivity(intent);
            }
        });


        //'내가 쓴 리뷰' 버튼 클릭시 수행
        btn_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(MyPageFragment.this, activity_myReview.class);
                //startActivity(intent);
            }
        });

        //'로그아웃' 버튼 클릭시 수행
        btn_logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(MyPageFragment.this, activity_main.class);
                //startActivity(intent);
            }
        });
        */
        return inflater.inflate(R.layout.fragment_mypage, container, false);
    }
}