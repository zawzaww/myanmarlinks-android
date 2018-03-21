package com.zawzaw.savethelibrary.adapter;

import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.DiffCallback;
import android.support.v7.recyclerview.extensions.ListAdapterConfig;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import com.google.gson.Gson;

/**
 * Created by zawzaw on 21/03/18.
 */

public class AuthorAdapter extends PagedListAdapter<Gson, RecyclerView.ViewHolder> {

    private static final String TAG = "AuthorAdapter";

    protected AuthorAdapter(@NonNull DiffCallback<Gson> diffCallback) {
        super(diffCallback);
    }

    protected AuthorAdapter(@NonNull ListAdapterConfig<Gson> config) {
        super(config);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

}
