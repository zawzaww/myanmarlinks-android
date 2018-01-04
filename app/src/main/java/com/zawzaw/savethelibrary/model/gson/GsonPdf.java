package com.zawzaw.savethelibrary.model.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by zawzaw on 04/01/18.
 */

public class GsonPdf {

    @SerializedName("pdf_id")
    private int pdf_id;

    @SerializedName("pdf_title")
    private String pdf_title;

    @SerializedName("download_link")
    private String download_link;

    @SerializedName("image")
    private String pdf_image;

    @SerializedName("category_mm")
    private String category_mm;

    @SerializedName("category_en")
    private String category_en;

    @SerializedName("pdf_source")
    private String pdf_source;

    public int getPdf_id() {
        return pdf_id;
    }

    public String getPdf_title() {
        return pdf_title;
    }

    public String getDownload_link() {
        return download_link;
    }

    public String getPdf_image() {
        return pdf_image;
    }

    public String getCategory_mm() {
        return category_mm;
    }

    public String getCategory_en() {
        return category_en;
    }

    public String getPdf_source() {
        return pdf_source;
    }

}
