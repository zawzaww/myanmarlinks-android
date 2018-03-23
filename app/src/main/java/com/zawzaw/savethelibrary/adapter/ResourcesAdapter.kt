package com.zawzaw.savethelibrary.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.zawzaw.savethelibrary.R
import com.zawzaw.savethelibrary.model.gson.GsonPdf
import com.zawzaw.savethelibrary.utils.Const
import com.zawzaw.savethelibrary.utils.FontEmbedder
import com.zawzaw.savethelibrary.utils.GlideApp

/**
 * Created by zawzaw on 04/01/18.
 */

class ResourcesAdapter(internal var context: Context, internal var gsonPdfs: List<GsonPdf>, internal var mListener: ResourcesOnClickListener) : RecyclerView.Adapter<ResourcesAdapter.ResourcesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResourcesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.review_book_card, parent, false)
        return ResourcesViewHolder(view)
    }

    override fun onBindViewHolder(holder: ResourcesViewHolder, position: Int) {
        GlideApp.with(context)
                .load(Const.IMG_URL + gsonPdfs[position].pdf_image!!)
                .placeholder(R.drawable.pdf_cover)
                .into(holder.bookCover)
        FontEmbedder.force(holder.bookTitle, gsonPdfs[position].pdf_title)
    }

    override fun getItemCount(): Int {
        return gsonPdfs.size
    }

    inner class ResourcesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        internal var bookCover: ImageView
        internal var bookTitle: TextView

        init {
            bookCover = itemView.findViewById(R.id.review_book_cover)
            bookTitle = itemView.findViewById(R.id.review_book_title)
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            mListener.OnItemClicked(gsonPdfs[layoutPosition])
        }
    }

    interface ResourcesOnClickListener {
        fun OnItemClicked(gsonPdf: GsonPdf)
    }

}
