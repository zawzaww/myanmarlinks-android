package com.zawzaw.savethelibrary.model.gson;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by zawzaw on 25/12/17.
 */

public class GsonNews
{
    @SerializedName("total")
    private int total;

    @SerializedName("data")
    private List<GsonNew> news;

    public int getTotal()
    {
        return total;
    }

    public List<GsonNew> getNews()
    {
        return news;
    }

}
