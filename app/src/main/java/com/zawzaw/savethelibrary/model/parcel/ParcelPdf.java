package com.zawzaw.savethelibrary.model.parcel;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zawzaw on 04/01/18.
 */

public class ParcelPdf implements Parcelable {

    private int pdf_id;
    private String pdf_title;
    private String download_link;
    private String pdf_image;
    private String category_mm;
    private String category_en;
    private String pdf_source;

    public ParcelPdf(int pdf_id, String pdf_title, String download_link, String pdf_image, String category_mm, String category_en, String pdf_source) {
        this.pdf_id = pdf_id;
        this.pdf_title = pdf_title;
        this.download_link = download_link;
        this.pdf_image = pdf_image;
        this.category_mm = category_mm;
        this.category_en = category_en;
        this.pdf_source = pdf_source;
    }

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

    protected ParcelPdf(Parcel in) {
        pdf_id = in.readInt();
        pdf_title = in.readString();
        download_link = in.readString();
        pdf_image = in.readString();
        category_mm = in.readString();
        category_en = in.readString();
        pdf_source = in.readString();
    }

    public static final Creator<ParcelPdf> CREATOR = new Creator<ParcelPdf>() {
        @Override
        public ParcelPdf createFromParcel(Parcel in) {
            return new ParcelPdf(in);
        }

        @Override
        public ParcelPdf[] newArray(int size) {
            return new ParcelPdf[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(pdf_id);
        parcel.writeString(pdf_title);
        parcel.writeString(download_link);
        parcel.writeString(pdf_image);
        parcel.writeString(category_mm);
        parcel.writeString(category_en);
        parcel.writeString(pdf_source);
    }

}

