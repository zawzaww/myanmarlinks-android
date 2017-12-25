package com.zawzaw.savethelibrary.model.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by zawzaw on 25/12/17.
 */

public class GsonNew
{

 @SerializedName("post_id")
 private int post_id;

 @SerializedName("post_title")
 private String post_title;

 @SerializedName("post_content")
 private String post_content;

 @SerializedName("author_name")
 private String author_name;

 @SerializedName("date")
 private String date;

 @SerializedName("image")
 private String feature_image_path;

    public int getPost_id()
    {
        return post_id;
    }

    public String getPost_title()
    {
        return post_title;
    }

    public String getPost_content()
    {
        return post_content;
    }

    public String getAuthor_name()
    {
        return author_name;
    }

    public String getDate()
    {
        return date;
    }

    public String getFeature_image_path()
    {
        return feature_image_path;
    }

}
