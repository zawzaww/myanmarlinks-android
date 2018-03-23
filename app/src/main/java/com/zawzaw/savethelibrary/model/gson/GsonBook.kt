package com.zawzaw.savethelibrary.model.gson

import com.google.gson.annotations.SerializedName

/**
 * Created by zawzaw on 02/01/18.
 */

class GsonBook {

    @SerializedName("id")
    val book_id: Int = 0

    @SerializedName("title")
    val title: String? = null

    @SerializedName("book_name")
    val book_title: String? = null

    @SerializedName("download_link")
    val download_link: String? = null

    @SerializedName("subjects")
    val subjects: String? = null

    @SerializedName("notes")
    val notes: String? = null

    @SerializedName("bookauthor_name")
    val bookauthor_name: String? = null

    @SerializedName("bookcategory_name_mm")
    val bookcategory_name_mm: String? = null

    @SerializedName("bookcategory_name_en")
    val bookcategory_name_en: String? = null

    @SerializedName("bookpublisher_name")
    val bookpublisher_name: String? = null

    @SerializedName("bookpublisher_address")
    val bookpublisher_address: String? = null

    @SerializedName("feature_image_path")
    val feature_image_path: String? = null

    @SerializedName("image")
    val image: String? = null

    @SerializedName("media")
    val mediaBookList: List<GsonBookMedia>? = null

}
