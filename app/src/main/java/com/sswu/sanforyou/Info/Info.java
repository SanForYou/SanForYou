package com.sswu.sanforyou.Info;

public class Info {
    //image
   // private String mountainURL;
    private String mountainName;
    private double scope;
    private String address;
    private String height;
    private double latitude;
    private double longitude;


    public Info(String mountainName, double scope, String address, String height, double latitude, double longitude){
        this.mountainName = mountainName;
        this.scope = scope;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.height = height;
       // this.mountainURL = mountainURL;
    }


   // public String getMountainURL() {return mountainURL;}

    public String getMountainName() {return mountainName;}

    public double getScope() {return scope;}

    public String getAddress() {return address;}

    public double getLatitude(){return latitude;}

    public double getLongitude(){return longitude;}

    public String getHeight(){return height;}



}