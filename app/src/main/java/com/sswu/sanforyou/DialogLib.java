package com.sswu.sanforyou;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import java.util.logging.Handler;

public class DialogLib {
    public final String TAG = DialogLib.class.getSimpleName();
    private volatile static DialogLib instance;

    public static DialogLib getInstance(){
        if(instance == null) {
            synchronized ( (DialogLib.class)) {
                if (instance == null) {
                    instance = new DialogLib();
                }
            }
        }
        return instance;
    }

    /*즐겨찾기 추가 다이얼로그 화면 보여줌*/
    public void showKeepInsertDialog(Context context, final Handler handler, final String memberID, final String mountainName) {
        new AlertDialog.Builder(context).setTitle(R.string.keep_insert).setMessage(R.string.keep_insert_message)
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
                })
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        KeepLib.getInstance().insertKeep(handler, memberID, mountainName);
                    }
                })
                .show();
    }

    public void showKeepDeleteDialog(Context context, final Handler handler, final String memberID, final String mountainName) {
        new AlertDialog.Builder(context).setTitle(R.string.keep_delete).setMessage(R.string.keep_delete_message)
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        KeepLib.getInstance().deleteKeep(handler, memberID, mountainName);
                    }
                }) .show();
    }
}
