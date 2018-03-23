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
import com.zawzaw.savethelibrary.ui.fragment.ListReviewFragment
import com.zawzaw.savethelibrary.utils.Const
import com.zawzaw.savethelibrary.utils.FontEmbedder
import com.zawzaw.savethelibrary.utils.GlideApp

/**
 * Created by zawzaw on 09/01/18.
 */

class ReviewDetailAdapter(internal var gsonBooks: List<GsonBook>, internal var context: Context, listReviewFragment: ListReviewFragment) : RecyclerView.Adapter<ReviewDetailAdapter.ReviewDetailViewHolder>() {
    internal var mListener: ReviewItemClickListener

    init {
        this.mListener = mListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewDetailViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.review_book_card_detail, parent, false)
        return ReviewDetailViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReviewDetailViewHolder, position: Int) {
        FontEmbedder.force(holder.textAuthor, gsonBooks[position].bookauthor_name)
        FontEmbedder.force(holder.txtPublisher, gsonBooks[position].bookpublisher_name)
        FontEmbedder.force(holder.txtBookCategory, gsonBooks[position].bookcategory_name_mm)
        FontEmbedder.force(holder.txtBooktitle, gsonBooks[position].book_title)

        GlideApp.with(context)
                .load(Const.MAIN_URL + gsonBooks[position].feature_image_path!!)
                .placeholder(R.drawable.pdf_cover)
                .into(holder.bookCover)
    }

    override fun getItemCount(): Int {
        return gsonBooks.size
    }


    inner class ReviewDetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        internal var textAuthor: TextView
        internal var txtBooktitle: TextView
        internal var txtPublisher: TextView
        internal var txtBookCategory: TextView
        internal var bookCover: ImageView

        init {
            textAuthor = itemView.findViewById(R.id.main_view_author)
            txtBooktitle = itemView.findViewById(R.id.main_view_title)
            txtBookCategory = itemView.findViewById(R.id.main_view_category)
            txtPublisher = itemView.findViewById(R.id.main_view_publisher)
            bookCover = itemView.findViewById(R.id.review_detail_book_cover)
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            mListener.OnItemClicked(gsonBooks[layoutPosition])
        }
    }

    interface ReviewItemClickListener {
        fun OnItemClicked(gsonBook: GsonBook)
    }

}
