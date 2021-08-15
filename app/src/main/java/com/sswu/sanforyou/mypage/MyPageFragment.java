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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.sswu.sanforyou.MainActivity;
import com.sswu.sanforyou.R;
import com.sswu.sanforyou.SignInActivity;
import com.sswu.sanforyou.SignUpActivity;

import org.w3c.dom.Text;

public class MyPageFragment extends Fragment {
    private Button btn_cart, btn_review, btn_logout;
    private TextView memberName;
    private FirebaseAuth mFirebaseAuth;
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


        //마이 페이지에서 현재 로그인한 사용자의 닉네임 띄우기
        memberName= (TextView) rootView.findViewById(R.id.text1);
        mFirebaseAuth= FirebaseAuth.getInstance();
        FirebaseUser user = mFirebaseAuth.getCurrentUser();
        if (user != null) {
            // User is signed in
            memberName.setText(user.getEmail());
        } else {
            // No user is signed in
            System.out.println("user는 null");
        }

        //로그인한 사용자가 오른 산 갯수 띄우기


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

        //'로그아웃' 버튼 클릭시 수행
        btn_logout= rootView.findViewById(R.id.bt3_logOut);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "로그아웃 완료", Toast.LENGTH_SHORT).show();
                mFirebaseAuth.signOut();
                Intent intent = new Intent(getActivity(), SignInActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }
}