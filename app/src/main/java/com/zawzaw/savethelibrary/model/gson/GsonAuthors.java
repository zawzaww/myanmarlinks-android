package com.zawzaw.savethelibrary.model.gson;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by zawzaw on 22/03/18.
 */

public class GsonAuthors {

    @SerializedName("total")
    private int author_total;

    @SerializedName("data")
    private List<GsonAuthor> authorList;

    public int getAuthor_total() {
        return author_total;
    }

    public List<GsonAuthor> getAuthorList() {
        return authorList;
    }

}
