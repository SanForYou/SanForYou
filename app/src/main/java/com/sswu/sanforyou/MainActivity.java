package com.sswu.sanforyou;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sswu.sanforyou.Info.InfoFragment;
import com.sswu.sanforyou.Map.MapFragment;
import com.sswu.sanforyou.Info.InfoFragment;
import com.sswu.sanforyou.mypage.CartFragment;
import com.sswu.sanforyou.mypage.MyPageFragment;
import com.sswu.sanforyou.mypage.MyReviewFragment;
import com.sswu.sanforyou.review.ReviewFragment;

public class MainActivity extends AppCompatActivity {

    //플래그먼트 프로퍼티
    MapFragment map_fragment;
    InfoFragment info_fragment;
    ReviewFragment review_fragment;
    MyPageFragment mypage_fragment;
    CartFragment cart_fragment;
    MyReviewFragment myReviewFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 플래그먼트 생성
        map_fragment = new MapFragment();
        info_fragment = new InfoFragment();
        review_fragment = new ReviewFragment();
        mypage_fragment = new MyPageFragment();
        cart_fragment= new CartFragment();
        myReviewFragment = new MyReviewFragment();

        // map_fragment를 첫번째 화면으로 설정
        getSupportFragmentManager().beginTransaction().replace(R.id.container, map_fragment).commit();

        // bottom_navigation을 참조하는 변수 선언
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // bottom_navigation의 item이 선택되면 호출되는 함수
        // item번호에 따라 해당하는 플래그먼트를 commit한다
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.tab_map:
                                getSupportFragmentManager().beginTransaction().replace(R.id.container, map_fragment).commit();
                                return true;
                            case R.id.tab_info:
                                getSupportFragmentManager().beginTransaction().replace(R.id.container, info_fragment).commit();
                                return true;
                            case R.id.tab_review:
                                getSupportFragmentManager().beginTransaction().replace(R.id.container, review_fragment).commit();
                                return true;
                            case R.id.tab_mypage:
                                getSupportFragmentManager().beginTransaction().replace(R.id.container, mypage_fragment).commit();
                                return true;
                        }

                        return false;
                    }
                }
        );
    }

    //feat: 유정/ 마이 페이지에서 버튼 클릭으로 fragment간의 전환을 구현하기 위한 함수
    public void changeFragment(int index){
        switch(index){
            case 1:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, cart_fragment).commit();
                break;
            case 2:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, myReviewFragment).commit();
                break;
        }
    }
}