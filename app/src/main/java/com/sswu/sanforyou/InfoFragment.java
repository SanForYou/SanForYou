package com.sswu.sanforyou;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;

/* 산 정보 리스트 프래그먼트 */

public class InfoFragment extends Fragment implements View.OnClickListener {

    Context context;
    TextView textView;
    RecyclerView bestMounList;
    ImageView listType;
    InfoListAdapter infoListAdapter;
    StaggeredGridLayoutManager layoutManager;
    EndlessRecyclerViewScrollListener scrollListener;
    String mountainName;
    String memberID;
    int listTypeValue = 2;

    /*인스턴스 생성*/
    public static InfoFragment newInstance() {
        InfoFragment IF = new InfoFragment();
        return IF;
    }

    /* 뷰 생성*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        context = this.getActivity();
        memberID = ((MyApp)this.getActivity().getApplication()).getMemberID();
        View layout = inflater.inflate(R.layout.fragment_info, container, false);

        return layout;
    }

    /*즐겨찾기 상태가 변경되었을 경우 이를 반영하는 용도로 사용됨*/
    @Override
    public void onResume() {
        super.onResume();
        MyApp myApp = ((MyApp) getActivity().getApplication());
        MounInfoItem currentInfoItem = myApp.getMounInfoItem();

        if (infoListAdapter != null && currentInfoItem != null) {
            infoListAdapter.setItem(currentInfoItem);
            myApp.setMounInfoItem(null);
        }
    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bestMounList = (RecyclerView) view.findViewById(R.id.list);
        listType = (ImageView) view.findViewById(R.id.list_type);
        listType.setOnClickListener(this);
        setRecyclerView();
    }

    private void setLayoutManager (int row) {
        layoutManager = new StaggeredGridLayoutManager(row, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
        bestMounList.setLayoutManager(layoutManager);
    }

    private void setRecyclerView() {
        setLayoutManager(listTypeValue);

        infoListAdapter = new InfoListAdapter(context, R.layout.row_bestmoun_list, new ArrayList<MounInfoItem>());
        bestMounList.setAdapter(infoListAdapter);

        scrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                listInfo(memberID, page);
            }
        };
        bestMounList.addOnScrollListener(scrollListener);
    }

    /*서버에서 산 정보 조회*/
    private void listInfo(String memberID, final int currentPage) {

    }

    /*각종 버튼에 대한 클릭 처리 정의*/
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.list_type) {
            changeListType();
        }
        setRecyclerView();
        listInfo(memberID, 0);
    }

    private  void changeListType() {
        if(listTypeValue == 1) {
            listTypeValue = 2;
            listType.setImageResource(R.drawable.unfilledhearticon);
        } else {
            listTypeValue = 1;
            listType.setImageResource(R.drawable.filledhearticon);
        }
        setLayoutManager(listTypeValue);
    }
}