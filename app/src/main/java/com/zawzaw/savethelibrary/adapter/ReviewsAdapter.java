package com.zawzaw.savethelibrary.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

import com.zawzaw.savethelibrary.R;
import com.zawzaw.savethelibrary.model.gson.GsonBook;
import com.zawzaw.savethelibrary.utils.Const;
import com.zawzaw.savethelibrary.utils.FontEmbedder;
import com.zawzaw.savethelibrary.utils.GlideApp;

/**
 * Created by zawzaw on 02/01/18.
 */

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ReviewsViewHolder> {

    Context context;
    List<GsonBook> gsonBooks;
    ReviewsOnClickedListener mListener;


    public ReviewsAdapter(Context context, List<GsonBook> gsonBooks, ReviewsOnClickedListener mListener) {
        this.context = context;
        this.gsonBooks = gsonBooks;
        this.mListener = mListener;
    }

    @Override
    public ReviewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_book_card, parent, false);
        return new ReviewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReviewsViewHolder holder, int position) {
        GlideApp.with(context)
                .load(Const.MAIN_URL + gsonBooks.get(position).getFeature_image_path())
                .placeholder(R.drawable.pdf_cover)
                .into(holder.bookCover);
        FontEmbedder.force(holder.bookTitle, gsonBooks.get(position).getBook_title());
    }

    @Override
    public int getItemCount() {
        return gsonBooks.size();
    }

    public interface ReviewsOnClickedListener {
        void OnItemClicked(GsonBook gsonBook);
    }

    public class ReviewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView bookCover;
        TextView bookTitle;

        public ReviewsViewHolder(View itemView) {
            super(itemView);
            bookCover = itemView.findViewById(R.id.review_book_cover);
            bookTitle = itemView.findViewById(R.id.review_book_title);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mListener.OnItemClicked(gsonBooks.get(getLayoutPosition()));
        }
    }

}
