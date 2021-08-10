package com.sswu.sanforyou.mypage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sswu.sanforyou.MainActivity;
import com.sswu.sanforyou.R;

public class MyPageFragment extends Fragment {
    private Button btn_cart, btn_review, btn_logout;
    MainActivity activity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        activity = (MainActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();

        activity = null;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_mypage, container, false);

        //'산바구니' 버튼 클릭시 수행
        btn_cart= rootView.findViewById(R.id.bt1_cart);
        btn_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.changeFragment(1);
            }
        });

        //'내가 쓴 리뷰' 버튼 클릭시 수행
        btn_review= rootView.findViewById(R.id.bt2_myReview);
        btn_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.changeFragment(2);
            }
        });

        /*
        //'로그아웃' 버튼 클릭시 수행
        btn_logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(MyPageFragment.this, activity_main.class);
                //startActivity(intent);
            }
        });
        */
        //return inflater.inflate(R.layout.fragment_mypage, container, false);
        return rootView;
    }
}