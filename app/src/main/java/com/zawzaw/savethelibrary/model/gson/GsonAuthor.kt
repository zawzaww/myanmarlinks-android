package com.zawzaw.savethelibrary.model.gson

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.PrimaryKey
import android.support.v7.recyclerview.extensions.DiffCallback
import com.google.gson.annotations.SerializedName

/**
 * Created by zawzaw on 21/03/18.
 */

class GsonAuthor {

    @SerializedName("id")
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "author_id")
    var author_id: Int = 0

    @SerializedName("name")
    @ColumnInfo(name = "author_name")
    var author_name: String? = null

    @SerializedName("book_count")
    @ColumnInfo(name = "book_count")
    var author_count: Int = 0

    override fun equals(obj: Any?): Boolean {
        if (obj === this)
            return true
        val author = obj as GsonAuthor?

        return author!!.author_id == this.author_id && author.author_name === this.author_name
    }

    companion object {

        var DIFF_CALLBACK: DiffCallback<GsonAuthor> = object : DiffCallback<GsonAuthor>() {
            override fun areItemsTheSame(oldItem: GsonAuthor, newItem: GsonAuthor): Boolean {
                return oldItem.author_id == newItem.author_id
            }

            override fun areContentsTheSame(oldItem: GsonAuthor, newItem: GsonAuthor): Boolean {
                return oldItem == newItem
            }
        }
    }

}
