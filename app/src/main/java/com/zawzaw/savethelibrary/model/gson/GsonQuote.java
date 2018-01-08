package com.zawzaw.savethelibrary.model.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by zawzaw on 08/01/18.
 */

public class GsonQuote {

    @SerializedName("id")
    private int id;

    @SerializedName("summery")
    private String summery;

    @SerializedName("content")
    private String content;

    @SerializedName("quoteauthor_name")
    private String quote_author_name;

    @SerializedName("quoteauthor_image")
    private String quote_author_image;

    @SerializedName("reference")
    private String reference;

    public int getId() {
        return id;
    }

    public String getSummery() {
        return summery;
    }

    public String getContent() {
        return content;
    }

    public String getQuote_author_name() {
        return quote_author_name;
    }

    public String getQuote_author_image() {
        return quote_author_image;
    }

    public String getReference() {
        return reference;
    }

}
