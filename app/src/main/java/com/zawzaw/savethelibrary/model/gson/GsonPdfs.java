package com.zawzaw.savethelibrary.model.gson;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by zawzaw on 04/01/18.
 */

public class GsonPdfs {

    @SerializedName("total")
    private int total;

    @SerializedName("data")
    private List<GsonPdf> pdfList;

    public int getTotal() {
        return total;
    }

    public List<GsonPdf> getPdfList() {
        return pdfList;
    }

}
