package com.zawzaw.savethelibrary.model.gson

import com.google.gson.annotations.SerializedName

/**
 * Created by zawzaw on 22/03/18.
 */

class GsonAuthors {

    @SerializedName("total")
    val author_total: Int = 0

    @SerializedName("data")
    val authorList: List<GsonAuthor>? = null

}
