package com.zawzaw.savethelibrary.model.gson

import com.google.gson.annotations.SerializedName

/**
 * Created by zawzaw on 04/01/18.
 */

class GsonPdfs {

    @SerializedName("total")
    val total: Int = 0

    @SerializedName("data")
    val pdfList: List<GsonPdf>? = null

}
