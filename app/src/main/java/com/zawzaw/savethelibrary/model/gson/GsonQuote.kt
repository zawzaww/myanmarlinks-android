package com.zawzaw.savethelibrary.model.gson

import com.google.gson.annotations.SerializedName

/**
 * Created by zawzaw on 08/01/18.
 */

class GsonQuote {

    @SerializedName("id")
    val id: Int = 0

    @SerializedName("summery")
    val summery: String? = null

    @SerializedName("content")
    val content: String? = null

    @SerializedName("quoteauthor_name")
    val quote_author_name: String? = null

    @SerializedName("quoteauthor_image")
    val quote_author_image: String? = null

    @SerializedName("reference")
    val reference: String? = null

}
