package com.zawzaw.savethelibrary.model.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by zawzaw on 02/01/18.
 */

public class GsonBookMedia {

    @SerializedName("id")
    private int media_id;

    @SerializedName("file_name")
    private String media_file_name;

    public int getMedia_id() {
        return media_id;
    }

    public String getMedia_file_name() {
        return media_file_name;
    }

}
