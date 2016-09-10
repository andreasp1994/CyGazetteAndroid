package com.apogiatzis.cygazetteapp.models;

/**
 * Created by andre on 10/09/2016.
 */
public class Comment {

    private String user; // To be Changed to user object
    private String creationDate;
    private String content;

    public Comment(String user, String creationDate, String content) {
        this.user = user;
        this.creationDate = creationDate;
        this.content = content;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
