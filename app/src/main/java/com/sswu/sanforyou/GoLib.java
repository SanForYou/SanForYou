package com.sswu.sanforyou;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

/*액티비티나 프래그먼트 실행 라이브러리*/
public class GoLib {
    public final String TAG = GoLib.class.getSimpleName();
    private volatile static GoLib instance;

    public static GoLib getInstance() {
        if (instance == null) {
            synchronized (GoLib.class) {
                if (instance == null) {
                    instance = new GoLib();
                }
            }
        }
        return instance;
    }

    /*프래그먼트 보여줌*/
    public void goFragment(FragmentManager fragmentManager, int containerViewId, Fragment fragment) {
        fragmentManager.beginTransaction()
                .replace(containerViewId, fragment)
                .commit();
    }

    /*뒤로가기 할 수 있는 프래그먼트 보여줌*/
    public void goFragmentBack(FragmentManager fragmentManager, int containerViewId, Fragment fragment) {
        fragmentManager.beginTransaction()
                .replace(containerViewId, fragment)
                .addToBackStack(null)
                .commit();
    }

    /*이전 프래그먼트 보여줌*/
    public void goBackFragment(FragmentManager fragmentManager) {
        fragmentManager.popBackStack();
    }

    /*Info 상세페이지 액티비티 실행*/
    public void goBestMounInfoActivity (Context context, String mountainName) {
        Intent intent = new Intent(context, BestMounInfoActivity.class);
    }
}
