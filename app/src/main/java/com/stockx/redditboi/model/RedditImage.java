package com.stockx.redditboi.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by oliver on 3/29/18
 */

public class RedditImage implements Serializable {
    @SerializedName("source")
    private RedditImageSource source;

    public RedditImage() {
    }

    public RedditImageSource getSource() {
        return source;
    }

    public void setSource(RedditImageSource source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return "RedditImage{" +
                "source=" + source +
                '}';
    }
}
