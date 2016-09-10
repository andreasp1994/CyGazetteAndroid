package com.apogiatzis.cygazetteapp.models;

import java.util.ArrayList;

/**
 * Created by andre on 10/09/2016.
 */
public abstract class FeedItem {

    private String creationTime;
    private ArrayList<Comment> comments;
    private Long likes;
    private Long dislikes;

    public FeedItem(String creationTime, ArrayList<Comment> comments, Long likes, Long dislikes){
        this.creationTime = creationTime;
        this.comments = comments;
        this.likes = likes;
        this.dislikes = dislikes;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
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
}
