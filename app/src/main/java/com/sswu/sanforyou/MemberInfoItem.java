package com.sswu.sanforyou;

public class MemberInfoItem {
    public String memberID;
    public String memberName;
    public String password;

    @Override
    public String toString() {
        return "MemberInfoItem{" +
                "memberID=" + memberID +
                ", memberName='" + memberName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
