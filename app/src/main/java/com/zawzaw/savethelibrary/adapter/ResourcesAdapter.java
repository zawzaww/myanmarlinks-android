package com.zawzaw.savethelibrary.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.zawzaw.savethelibrary.R;
import com.zawzaw.savethelibrary.model.gson.GsonPdf;
import com.zawzaw.savethelibrary.ui.fragment.LatestResourceFragment;
import com.zawzaw.savethelibrary.utils.Const;
import com.zawzaw.savethelibrary.utils.FontEmbedder;
import java.util.List;

/**
 * Created by zawzaw on 29/12/17.
 */

public class ResourcesAdapter extends RecyclerView.Adapter<ResourcesAdapter.ResourcesVewHolder>
{
    Context context;
    List<GsonPdf> gsonPdfs;
    ResourceOnClickListener mLinstener;

    public ResourcesAdapter(Context context, List<GsonPdf> gsonPdfs, LatestResourceFragment mListener)
    {
        this.context = context;
        this.gsonPdfs = gsonPdfs;
        this.mLinstener = mListener;
    }

    @Override
    public ResourcesVewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_book_card, parent, false);
        return new ResourcesVewHolder(view);
    }

    @Override
    public void onBindViewHolder(ResourcesVewHolder holder, int position)
    {
        Glide.with(context).load(Const.IMAGE_URL + gsonPdfs.get(position).getPdf_image()).into(holder.bookCover);
        FontEmbedder.force(holder.bookTitle, gsonPdfs.get(position).getPdf_title());
    }

    @Override
    public int getItemCount() {
        return gsonPdfs.size();
    }


    public class ResourcesVewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        ImageView bookCover;
        TextView bookTitle;

        public ResourcesVewHolder(View itemView)
        {
            super(itemView);
            bookCover = itemView.findViewById(R.id.review_book_cover);
            bookTitle = itemView.findViewById(R.id.review_book_title);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v)
        {
            mLinstener.OnItemClicked(gsonPdfs.get(getLayoutPosition()));
        }
    }

    public interface ResourceOnClickListener
    {
        void OnItemClicked(GsonPdf gsonPdf);
    }

}
