package com.zawzaw.savethelibrary.model.gson

import com.google.gson.annotations.SerializedName

/**
 * Created by zawzaw on 31/12/17.
 */

class GsonNew {

    @SerializedName("post_id")
    val post_id: Int = 0

    @SerializedName("post_title")
    val post_title: String? = null

    @SerializedName("post_content")
    val post_content: String? = null

    @SerializedName("author_name")
    val author_name: String? = null

    @SerializedName("date")
    val date: String? = null

    @SerializedName("image")
    val feature_image_path: String? = null

}
