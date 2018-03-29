package com.stockx.redditboi.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by oliver on 3/29/18
 */

public class RedditWrapper implements Serializable {
    @SerializedName("kind")
    private String kind;

    @SerializedName("data")
    private RedditData data;

    public RedditWrapper() {
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public RedditData getData() {
        return data;
    }

    public void setData(RedditData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RedditWrapper{" +
                "kind='" + kind + '\'' +
                ", data=" + data +
                '}';
    }
}
