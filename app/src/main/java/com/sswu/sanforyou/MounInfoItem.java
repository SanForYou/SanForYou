package com.sswu.sanforyou;

@org.parceler.Parcel
public class MounInfoItem {
    public String mountainName;
    public int mountainSeq;
    public String address;
    public String score;
    public String fileName;
    public String height;
    public String latitude;
    public String longitude;
    public boolean isKeep;

    public MounInfoItem(String mountainName, int mountainSeq, String address, String score, String fileName, String height, String latitude, String longitude, boolean isKeep) {
        this.mountainName = mountainName;
        this.mountainSeq = mountainSeq;
        this.address = address;
        this.score = score;
        this.fileName = fileName;
        this.height = height;
        this.latitude = latitude;
        this.longitude = longitude;
        this.isKeep = isKeep;
    }

    @Override
    public String toString() {
        return "MounInfoItem{" +
                "mountainName=" + mountainName +
                ", mountainSeq='" + mountainSeq + '\'' +
                ", address='" + address + '\'' +
                ", score='" + score + '\'' +
                ", fileName='" + fileName + '\'' +
                ", height='" + height + '\'' +
                ", isKeep='" + isKeep+ '\'' +
                '}';
    }
}
