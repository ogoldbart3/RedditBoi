package com.stockx.redditboi.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by oliver on 3/29/18
 */

public class RedditPostData implements Serializable {

    @SerializedName("subreddit_id")
    private String subredditId;

    @SerializedName("subreddit")
    private String subreddit;

    @SerializedName("selftext_html")
    private String selftextHtml;

    @SerializedName("selftext")
    private String selftext;

    @SerializedName("score")
    private int score;

    @SerializedName("preview")
    private RedditPreview preview;

    @SerializedName("over_18")
    private boolean over18;

    @SerializedName("stickied")
    private boolean stickied;

    @SerializedName("visited")
    private boolean visited;

    @SerializedName("permalink")
    private String permalink;

    @SerializedName("url")
    private boolean url;

    @SerializedName("title")
    private String title;

    public RedditPostData() {
    }

    public String getSubredditId() {
        return subredditId;
    }

    public void setSubredditId(String subredditId) {
        this.subredditId = subredditId;
    }

    public String getSubreddit() {
        return subreddit;
    }

    public void setSubreddit(String subreddit) {
        this.subreddit = subreddit;
    }

    public String getSelftextHtml() {
        return selftextHtml;
    }

    public void setSelftextHtml(String selftextHtml) {
        this.selftextHtml = selftextHtml;
    }

    public String getSelftext() {
        return selftext;
    }

    public void setSelftext(String selftext) {
        this.selftext = selftext;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public RedditPreview getPreview() {
        return preview;
    }

    public void setPreview(RedditPreview preview) {
        this.preview = preview;
    }

    public boolean isOver18() {
        return over18;
    }

    public void setOver18(boolean over18) {
        this.over18 = over18;
    }

    public boolean isStickied() {
        return stickied;
    }

    public void setStickied(boolean stickied) {
        this.stickied = stickied;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public boolean isUrl() {
        return url;
    }

    public void setUrl(boolean url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "RedditPostData{" +
                "subredditId='" + subredditId + '\'' +
                ", subreddit='" + subreddit + '\'' +
                ", selftextHtml='" + selftextHtml + '\'' +
                ", selftext='" + selftext + '\'' +
                ", score=" + score +
                ", preview=" + preview +
                ", over18=" + over18 +
                ", stickied=" + stickied +
                ", visited=" + visited +
                ", permalink='" + permalink + '\'' +
                ", url=" + url +
                ", title='" + title + '\'' +
                '}';
    }
}
