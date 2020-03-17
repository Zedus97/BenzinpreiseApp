package com.example.benzinpreise;

import com.google.gson.annotations.SerializedName;

public class Post {
    private String userID;
    private String id;
    private String title;
    @SerializedName("body")
    private String body;

    public String getUserID() {
        return userID;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}
