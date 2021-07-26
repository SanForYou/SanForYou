package com.sswu.sanforyou.review;

public class Review {
    private int reviewID;
    private String writerID;
    private int scope;
    private String content;
    private int likes;
//    private long images;
    private String mountainName;

    public Review(int reviewID, String writerID, int scope, String content, int likes, String mountainName) {
        this.reviewID = reviewID;
        this.writerID = writerID;
        this.scope = scope;
        this.content = content;
        this.likes = likes;
//        this.images = images;
        this.mountainName = mountainName;
    }

    public int getReviewID() {
        return reviewID;
    }

    public String getWriterID() {
        return writerID;
    }

    public int getScope() {
        return scope;
    }

    public String getContent() {
        return content;
    }

    public int getLikes() {
        return likes;
    }

//    public long getImages() {
////        return images;
//    }

    public String getMountainName() {
        return mountainName;
    }
}
