package com.ishwar_arcore.nobroker.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ResponseItem implements Serializable {

    @SerializedName("image")
    private String image;

    @SerializedName("subTitle")
    private String subTitle;

    @SerializedName("title")
    private String title;

    public String getImage() {
        return image;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public String getTitle() {
        return title;
    }
}