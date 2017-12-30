package com.zawzaw.savethelibrary.model.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by zawzaw on 29/12/17.
 */

public class GsonPdf
{
    @SerializedName("pdf_id")
    private int pdf_id;

    @SerializedName("pdf_title")
    private String pdf_title;

    @SerializedName("download_links")
    private String download_links;

    @SerializedName("image")
    private String pdf_image;

    @SerializedName("category_mm")
    private String category_mm;

    @SerializedName("category_en")
    private String category_en;

    @SerializedName("pdf_sources")
    private String pdf_sources;

    public int getPdf_id() {
        return pdf_id;
    }

    public String getPdf_title() {
        return pdf_title;
    }

    public String getDownload_links() {
        return download_links;
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

    public String getPdf_sources() {
        return pdf_sources;
    }

}
