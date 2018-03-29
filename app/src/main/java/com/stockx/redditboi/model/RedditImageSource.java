package com.stockx.redditboi.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by oliver on 3/29/18
 */

public class RedditImageSource implements Serializable {
    @SerializedName("url")
    private String url;

    @SerializedName("width")
    private int width;

    @SerializedName("height")
    private int height;

    public RedditImageSource() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "RedditImageSource{" +
                "url='" + url + '\'' +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
