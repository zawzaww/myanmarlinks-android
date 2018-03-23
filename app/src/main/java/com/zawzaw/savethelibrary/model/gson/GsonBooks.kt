package com.zawzaw.savethelibrary.model.gson

import com.google.gson.annotations.SerializedName

/**
 * Created by zawzaw on 02/01/18.
 */

class GsonBooks {

    @SerializedName("total")
    val total: Int = 0

    @SerializedName("data")
    val books: List<GsonBook>? = null

}
