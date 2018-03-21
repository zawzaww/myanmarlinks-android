package com.zawzaw.savethelibrary.model.gson;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.DiffCallback;
import com.google.gson.annotations.SerializedName;

/**
 * Created by zawzaw on 21/03/18.
 */

public class GsonAuthor {

    public static DiffCallback<GsonAuthor> DIFF_CALLBACK = new DiffCallback<GsonAuthor>() {
        @Override
        public boolean areItemsTheSame(@NonNull GsonAuthor oldItem, @NonNull GsonAuthor newItem) {
            return oldItem.author_id == newItem.author_id;
        }

        @Override
        public boolean areContentsTheSame(@NonNull GsonAuthor oldItem, @NonNull GsonAuthor newItem) {
            return oldItem.equals(newItem);
        }
    };

    @SerializedName("id")
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "author_id")
    public int author_id;

    @SerializedName("name")
    @ColumnInfo(name = "author_name")
    public String author_name;

    @SerializedName("book_count")
    @ColumnInfo(name = "book_count")
    public int author_count;

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        GsonAuthor author = (GsonAuthor) obj;

        return author.author_id == this.author_id && author.author_name == this.author_name;
    }

}
