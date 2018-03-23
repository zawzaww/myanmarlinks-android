package com.zawzaw.savethelibrary.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.zawzaw.savethelibrary.R
import com.zawzaw.savethelibrary.model.gson.GsonBook
import com.zawzaw.savethelibrary.utils.Const
import com.zawzaw.savethelibrary.utils.FontEmbedder
import com.zawzaw.savethelibrary.utils.GlideApp

/**
 * Created by zawzaw on 02/01/18.
 */

class ReviewsAdapter(internal var context: Context, internal var gsonBooks: List<GsonBook>, internal var mListener: ReviewsOnClickedListener) : RecyclerView.Adapter<ReviewsAdapter.ReviewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.review_book_card, parent, false)
        return ReviewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReviewsViewHolder, position: Int) {
        GlideApp.with(context)
                .load(Const.MAIN_URL + gsonBooks[position].feature_image_path!!)
                .placeholder(R.drawable.pdf_cover)
                .into(holder.bookCover)
        FontEmbedder.force(holder.bookTitle, gsonBooks[position].book_title)
    }

    override fun getItemCount(): Int {
        return gsonBooks.size
    }

    interface ReviewsOnClickedListener {
        fun OnItemClicked(gsonBook: GsonBook)
    }

    inner class ReviewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        internal var bookCover: ImageView
        internal var bookTitle: TextView

        init {
            bookCover = itemView.findViewById(R.id.review_book_cover)
            bookTitle = itemView.findViewById(R.id.review_book_title)
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            mListener.OnItemClicked(gsonBooks[layoutPosition])
        }
    }

}
