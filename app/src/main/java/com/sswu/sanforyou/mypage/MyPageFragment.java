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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.sswu.sanforyou.AppHelper;
import com.sswu.sanforyou.MainActivity;
import com.sswu.sanforyou.R;
import com.sswu.sanforyou.SignInActivity;
import com.sswu.sanforyou.SignUpActivity;
import com.sswu.sanforyou.review.Review;
import com.sswu.sanforyou.review.ReviewAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class MyPageFragment extends Fragment {
    private Button btn_cart, btn_review, btn_logout;
    private TextView memberID, my_level;
    private FirebaseAuth mFirebaseAuth;
    private String loginID;
    ViewGroup rootView;
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
        //ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_mypage, container, false);
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_mypage, container, false);

        //마이 페이지에서 현재 로그인한 사용자의 닉네임 띄우기
        memberID= (TextView) rootView.findViewById(R.id.text1);
        mFirebaseAuth= FirebaseAuth.getInstance();
        FirebaseUser user = mFirebaseAuth.getCurrentUser();
        if (user != null) {
            // User is signed in
            memberID.setText(user.getEmail());
        } else {
            // No user is signed in
            System.out.println("user는 null");
        }

        //로그인한 사용자가 오른 산 갯수 띄우기
        //sendRequest();

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


/* 마이페이지 현재 로그인한 사용자의 level 띄우기
    public void sendRequest() {

        //현재 로그인한 사용자 불러오기
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // User is signed in
            loginID= user.getEmail();
        } else {
            // No user is signed in
            Toast.makeText(getActivity(), "에러 발생", Toast.LENGTH_SHORT).show();
        }

        String url = "http://ec2-3-34-189-249.ap-northeast-2.compute.amazonaws.com/my_level.php?memberID=%27"+loginID+"%27";

        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("my_level");

                            for(int i=0;i<jsonArray.length();i++){

                                JSONObject item = jsonArray.getJSONObject(i);

                                int level = item.getInt("level");

                                my_level =  (TextView)rootView.findViewById(R.id.text2);
                                my_level.setText(level);
                                System.out.println("-------------------level: "+level);
                            }

                        } catch (JSONException e) {
                            System.out.println("---------------------------------------------" + e);
                        }
                    }
                },
                new Response.ErrorListener() { //에러 발생시 호출될 리스너 객체
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error.getMessage());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String,String>();
                return params;
            }
        };
        request.setShouldCache(false); //이전 결과 있어도 새로 요청하여 응답을 보여준다.
        AppHelper.requestQueue = Volley.newRequestQueue(getActivity());
        AppHelper.requestQueue.add(request);
    }
*/
}