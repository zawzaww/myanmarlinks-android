package com.zawzaw.savethelibrary.model.gson

import com.google.gson.annotations.SerializedName

/**
 * Created by zawzaw on 04/01/18.
 */

class GsonPdf {

    @SerializedName("pdf_id")
    val pdf_id: Int = 0

    @SerializedName("pdf_title")
    val pdf_title: String? = null

    @SerializedName("download_link")
    val download_link: String? = null

    @SerializedName("image")
    val pdf_image: String? = null

    @SerializedName("category_mm")
    val category_mm: String? = null

    @SerializedName("category_en")
    val category_en: String? = null

    @SerializedName("pdf_source")
    val pdf_source: String? = null

}
