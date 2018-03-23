package com.zawzaw.savethelibrary.model.gson

import com.google.gson.annotations.SerializedName

/**
 * Created by zawzaw on 31/12/17.
 */

class GsonNews {

    @SerializedName("total")
    val total: Int = 0

    @SerializedName("data")
    val news: List<GsonNew>? = null

}
