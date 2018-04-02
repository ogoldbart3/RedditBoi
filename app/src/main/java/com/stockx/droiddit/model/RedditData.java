package com.stockx.droiddit.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by harold on 3/29/18
 */

public class RedditData implements Serializable {
    @SerializedName("after")
    private String after;

    @SerializedName("dist")
    private int dist;

    @SerializedName("modhash")
    private String modhash;

    @SerializedName("whitelist_status")
    private String whitelistStatus;

    @SerializedName("children")
    private List<RedditPost> children;

    @SerializedName("before")
    private String before;

    public RedditData() {
    }

    public String getAfter() {
        return after;
    }

    public void setAfter(String after) {
        this.after = after;
    }

    public int getDist() {
        return dist;
    }

    public void setDist(int dist) {
        this.dist = dist;
    }

    public String getModhash() {
        return modhash;
    }

    public void setModhash(String modhash) {
        this.modhash = modhash;
    }

    public String getWhitelistStatus() {
        return whitelistStatus;
    }

    public void setWhitelistStatus(String whitelistStatus) {
        this.whitelistStatus = whitelistStatus;
    }

    public List<RedditPost> getChildren() {
        return children;
    }

    public void setChildren(List<RedditPost> children) {
        this.children = children;
    }

    public String getBefore() {
        return before;
    }

    public void setBefore(String before) {
        this.before = before;
    }

    @Override
    public String toString() {
        return "RedditData{" +
                "after='" + after + '\'' +
                ", dist=" + dist +
                ", modhash='" + modhash + '\'' +
                ", whitelistStatus='" + whitelistStatus + '\'' +
                ", children=" + children +
                ", before='" + before + '\'' +
                '}';
    }
}
