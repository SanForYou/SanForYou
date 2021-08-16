package com.sswu.sanforyou.mypage;

public class Cart {
    private String mountainName;
    private String address;

    public Cart(String mountainName, String address){
        this.mountainName = mountainName;
        this.address=address;
    }

    public String getMountainName(){ return mountainName; }

    public String getAddress() { return address; }
}
