package com.stockx.droiddit.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by harold on 3/29/18
 */

public class RedditPreview implements Serializable {

    @SerializedName("images")
    private List<RedditImage> redditImages;

    @SerializedName("enabled")
    private boolean enabled;

    public RedditPreview() {
    }

    public List<RedditImage> getRedditImages() {
        return redditImages;
    }

    public void setRedditImages(List<RedditImage> redditImages) {
        this.redditImages = redditImages;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "RedditPreview{" +
                "redditImages=" + redditImages +
                ", enabled=" + enabled +
                '}';
    }
}
