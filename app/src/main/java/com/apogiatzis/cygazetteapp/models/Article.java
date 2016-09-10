package com.apogiatzis.cygazetteapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by andre on 17/08/2016.
 */
public class Article implements Parcelable, Serializable{

    private String id;
    private String link;
    private Long citations;
    private String issue;
    private String date;
    private Long likes;
    private Long dislikes;

    public Article(String link, String issue, String date, Long citations, Long likes, Long dislikes){
        this.link = link;
        this.issue = issue;
        this.date = date;
        this.citations = citations;
        this.likes = likes;
        this.dislikes = dislikes;
    }

    protected Article(Parcel in) {
        id = in.readString();
        link = in.readString();
        issue = in.readString();
        date = in.readString();
        citations = in.readLong();
        likes = in.readLong();
        dislikes = in.readLong();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(link);
        dest.writeString(issue);
        dest.writeString(date);
        dest.writeLong(citations);
        dest.writeLong(likes);
        dest.writeLong(dislikes);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Article> CREATOR = new Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel in) {
            return new Article(in);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };

    public Long getCitations() {
        return citations;
    }

    public void setCitations(Long citations) {
        this.citations = citations;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getLikes() {
        return likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }

    public Long getDislikes() {
        return dislikes;
    }

    public void setDislikes(Long dislikes) {
        this.dislikes = dislikes;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void like() {
        this.likes++;
    }

    public void dislike() {
        this.dislikes++;
    }
}
