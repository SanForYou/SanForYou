package com.sswu.sanforyou;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.fragment.app.Fragment;

public class InfoFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_info, container, false);

        GridView gridView = (GridView) view.findViewById(R.id.gridView);
        GridAdapter adapter = new GridAdapter();
        gridView.setAdapter(adapter);

        adapter.addItem("북한산", R.drawable.mountain1);
        adapter.addItem("남산", R.drawable.mountain3);
        adapter.addItem("백두산", R.drawable.mountain2);
        adapter.addItem("남산", R.drawable.mountain2);
        adapter.addItem("백두산", R.drawable.mountain1);

        return view;
    }
}