package com.sswu.sanforyou;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

/*프래그먼트를 보여주는 컨테이너 역할을 함*/

public class MainActivity extends AppCompatActivity {

    MemberInfoItem memberInfoItem;

    //플래그먼트 프로퍼
    MapFragment map_fragment;
    InfoFragment info_fragment;
    ReviewFragment review_fragment;
    MypageFragment mypageFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 플래그먼트 생성
        map_fragment = new MapFragment();
        info_fragment = new InfoFragment();
        review_fragment = new ReviewFragment();
        mypageFragment = new MypageFragment();

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
                        switch (item.getItemId()){
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
                                getSupportFragmentManager().beginTransaction().replace(R.id.container, mypageFragment).commit();
                                return true;
                        }

                        return false;
                    }

                }
        );
        memberInfoItem = ((MyApp)getApplication()).getMemberInfoItem();
    }



}