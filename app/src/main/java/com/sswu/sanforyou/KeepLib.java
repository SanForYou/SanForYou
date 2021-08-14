package com.sswu.sanforyou;

/*즐겨찾기 관련 라이브러리*/

import android.os.Handler;

public class KeepLib {
    public final String TAG = KeepLib.class.getSimpleName();
    private volatile  static KeepLib instance;

    public static KeepLib getInstance() {
        if(instance == null) {
            synchronized (KeepLib.class) {
                if (instance == null) {
                    instance = new KeepLib();
                }
            }
        }
        return instance;
    }

    /*즐겨찾기 추가를 서버에 요청*/
    public void insertKeep(final android.os.Handler handler, String memberSeq, final String mountainName) {

    }

    /*즐겨찾기 삭제를 서버에 요청*/
    public void deleteKeep(final Handler handler, String memberID, final String mountainName) {

    }
}
