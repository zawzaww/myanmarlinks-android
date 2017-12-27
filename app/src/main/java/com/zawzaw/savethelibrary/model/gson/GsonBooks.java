package com.zawzaw.savethelibrary.model.gson;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by zawzaw on 27/12/17.
 */

public class GsonBooks
{
    @SerializedName("total")
    private int total;

    @SerializedName("data")
    private List<GsonBook> books;

    public int getTotal() {
        return total;
    }

    public List<GsonBook> getBooks() {
        return books;
    }

}
