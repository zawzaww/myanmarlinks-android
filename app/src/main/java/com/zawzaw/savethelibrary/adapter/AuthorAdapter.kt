package com.zawzaw.savethelibrary.adapter

import android.arch.paging.PagedListAdapter
import android.support.v7.recyclerview.extensions.DiffCallback
import android.support.v7.recyclerview.extensions.ListAdapterConfig
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.google.gson.Gson

/**
 * Created by zawzaw on 21/03/18.
 */

class AuthorAdapter : PagedListAdapter<Gson, RecyclerView.ViewHolder> {

    protected constructor(diffCallback: DiffCallback<Gson>) : super(diffCallback) {}

    protected constructor(config: ListAdapterConfig<Gson>) : super(config) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder? {
        return null
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    companion object {

        private val TAG = "AuthorAdapter"
    }

}
