package com.sswu.sanforyou.review;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.sswu.sanforyou.R;

public class ReviewFragment extends Fragment {

    ListView listview;
    private static ReviewAdapter reviewAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_review, container, false);

        listview = (ListView) view.findViewById(R.id.review_list_view);
        ReviewAdapter adapter = new ReviewAdapter();
        listview.setAdapter(adapter);

        adapter.addReview("북한산", 3.0f, R.drawable.mountain1, "유저1", "하산하고 북한산 근처 식당에서 식사를 할 거면 미리 예약하고 주차를 하고 올라가는 것도 추천한다. 백운지지원센터에서 주차했을 때도 식사까지 하고 나왔는데 주차요금 만원 정도 나왔다. 백운지지원센터는 등산용품 파는 가게들이 많다. 이쪽으로 하산하면 눈 돌아간다. 그쪽에 있는 손칼국수 집이 가격도 괜찮고 맛집이다. ");
        adapter.addReview("남산",4.5f,  R.drawable.mountain2,"유저2", "케이블카 재밌다");
        adapter.addReview("한라산", 3.75f, R.drawable.mountain3,"유저3", "춥다");

        return view;
    }
}