package com.sswu.sanforyou;

public class KeepItem {

    public String keepSeq;
    public String memberID;
    public String mountainName;

    @Override
    public String toString() {
        return "KeepItem{" +
                "keepSeq=" + keepSeq + '|' +
                ", memberID=" + memberID + '|' +
                ", mountainName=" + mountainName + '|' +
                '}';
    }
}
