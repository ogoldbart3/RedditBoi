package com.stockx.redditboi.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by oliver on 3/29/18
 */

public class RedditPost implements Serializable {
    @SerializedName("kind")
    private String kind;

    @SerializedName("data")
    private RedditPostData data;

    public RedditPost() {
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public RedditPostData getData() {
        return data;
    }

    public void setData(RedditPostData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RedditPost{" +
                "kind='" + kind + '\'' +
                ", data=" + data +
                '}';
    }
}
