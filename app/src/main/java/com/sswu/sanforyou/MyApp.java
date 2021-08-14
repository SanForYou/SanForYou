package com.sswu.sanforyou;

import android.app.Application;
import android.os.StrictMode;

public class MyApp extends Application {
    private MemberInfoItem memberInfoItem;
    private MounInfoItem mounInfoItem;

    @Override
    public void onCreate() {
        super.onCreate();

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
    }

    public MemberInfoItem getMemberInfoItem() {
        if (memberInfoItem == null) memberInfoItem = new MemberInfoItem();
        return memberInfoItem;
    }

    public void setMemberInfoItem(MemberInfoItem item) {
        this.memberInfoItem = item;
    }

    public String getMemberID() {
        return memberInfoItem.memberID;
    }

    public void setMounInfoItem (MounInfoItem mounInfoItem) {
        this.mounInfoItem = mounInfoItem;
    }

    public MounInfoItem getMounInfoItem() { return mounInfoItem; }
}
