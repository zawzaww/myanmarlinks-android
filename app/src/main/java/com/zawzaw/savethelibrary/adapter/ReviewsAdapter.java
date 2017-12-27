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
import com.zawzaw.savethelibrary.model.gson.GsonBook;
import com.zawzaw.savethelibrary.utils.Const;
import com.zawzaw.savethelibrary.utils.FontEmbedder;

/**
 * Created by zawzaw on 26/12/17.
 */

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ReviewsViewHolder>
{
    Context context;
    List<GsonBook> gsonBooks;
    ReviewsOnClickedLinstener mListener;

    public ReviewsAdapter(Context context, List<GsonBook> gsonBooks, ReviewsOnClickedLinstener mListener)
    {
        this.context = context;
        this.gsonBooks = gsonBooks;
        this.mListener = mListener;
    }

    @Override
    public ReviewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_book_card, parent, false);
        return new ReviewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReviewsViewHolder holder, int position)
    {
        Glide.with(context).load(Const.MAIN_URL + gsonBooks.get(position).getFeature_image_path()).into(holder.bookCover);
        FontEmbedder.force(holder.bootTitle, gsonBooks.get(position).getBook_title());
    }

    @Override
    public int getItemCount()
    {
        return gsonBooks.size();
    }

    public interface ReviewsOnClickedLinstener
    {
        void OnItemClicked(GsonBook gsonBook);
    }

    public class ReviewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView bookCover;
        TextView bootTitle;

        public ReviewsViewHolder(View itemView)
        {
            super(itemView);
            bookCover = itemView.findViewById(R.id.review_book_cover);
            bootTitle = itemView.findViewById(R.id.review_book_title);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view)
        {
            mListener.OnItemClicked(gsonBooks.get(getLayoutPosition()));
        }
    }

}
