package com.apogiatzis.cygazetteapp.models;

import java.util.ArrayList;

/**
 * Created by andre on 10/09/2016.
 */
public class CitationFeedItem extends FeedItem {

    private String user; // To be changed to user object
    private String citation; //Also to be changed. For the moment a link to a saved image is enough

    public CitationFeedItem(String creationTime, ArrayList<Comment> comments, Long likes, Long dislikes, String user, String citation){
        super(creationTime, comments, likes, dislikes);
        this.user = user;
        this.citation = citation;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCitation() {
        return citation;
    }

    public void setCitation(String citation) {
        this.citation = citation;
    }
}
