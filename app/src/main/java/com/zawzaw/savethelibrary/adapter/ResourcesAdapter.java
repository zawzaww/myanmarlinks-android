package com.zawzaw.savethelibrary.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import com.bumptech.glide.Glide;

import com.zawzaw.savethelibrary.R;
import com.zawzaw.savethelibrary.model.gson.GsonPdf;
import com.zawzaw.savethelibrary.utils.Const;
import com.zawzaw.savethelibrary.utils.FontEmbedder;

/**
 * Created by zawzaw on 04/01/18.
 */

public class ResourcesAdapter extends RecyclerView.Adapter<ResourcesAdapter.ResourcesViewHolder>{

    Context context;
    List<GsonPdf> gsonPdfs;
    ResourcesOnClickListener mListener;

    public ResourcesAdapter(Context context, List<GsonPdf> gsonPdfs, ResourcesOnClickListener mListener) {
        this.context = context;
        this.gsonPdfs = gsonPdfs;
        this.mListener = mListener;
    }

    @Override
    public ResourcesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_book_card, parent, false);
        return new ResourcesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ResourcesViewHolder holder, int position) {
        Glide.with(context).load(Const.IMG_URL + gsonPdfs.get(position).getPdf_image()).into(holder.bookCover);
        FontEmbedder.force(holder.bookTitle, gsonPdfs.get(position).getPdf_title());
    }

    @Override
    public int getItemCount() {
        return gsonPdfs.size();
    }

    public class ResourcesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView bookCover;
        TextView bookTitle;

        public ResourcesViewHolder(View itemView) {
            super(itemView);
            bookCover = itemView.findViewById(R.id.review_book_cover);
            bookTitle = itemView.findViewById(R.id.review_book_title);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mListener.OnItemClicked(gsonPdfs.get(getLayoutPosition()));
        }
    }

    public interface ResourcesOnClickListener {
        void OnItemClicked(GsonPdf gsonPdf);
    }

}
