package com.sswu.sanforyou.review;

public class Review {
    private String mountainName;
    private int image;
    private String userName;
    private String content;
    private float rating;

    public Review(String mountainName, float rating, int image, String userName, String content) {
        this.mountainName = mountainName;
        this.image = image;
        this.userName = userName;
        this.content = content;
        this.rating = rating;
    }

    public String getMountainName() {
        return mountainName;
    }

    public String getUserName() {
        return userName;
    }

    public String getContent() {
        return content;
    }

    public int getImage() {
        return image;
    }

    public float getRating() {return rating;}

    public void setMountainName(String mountainName) {
        this.mountainName = mountainName;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
