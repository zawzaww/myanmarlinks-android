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
import com.zawzaw.savethelibrary.ui.fragment.ListReviewFragment;
import com.zawzaw.savethelibrary.utils.Const;
import com.zawzaw.savethelibrary.utils.FontEmbedder;
import com.zawzaw.savethelibrary.utils.GlideApp;

/**
 * Created by zawzaw on 09/01/18.
 */

public class ReviewDetailAdapter extends RecyclerView.Adapter<ReviewDetailAdapter.ReviewDetailViewHolder> {

    List<GsonBook> gsonBooks;
    Context context;
    ReviewItemClickListener mListener;

    public ReviewDetailAdapter(List<GsonBook> gsonBooks, Context context, ListReviewFragment listReviewFragment) {
        this.gsonBooks = gsonBooks;
        this.context = context;
        this.mListener = mListener;
    }

    @Override
    public ReviewDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_book_card_detail, parent, false);
        return new ReviewDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReviewDetailViewHolder holder, int position) {
        FontEmbedder.force(holder.textAuthor, gsonBooks.get(position).getBookauthor_name());
        FontEmbedder.force(holder.txtPublisher, gsonBooks.get(position).getBookpublisher_name());
        FontEmbedder.force(holder.txtBookCategory, gsonBooks.get(position).getBookcategory_name_mm());
        FontEmbedder.force(holder.txtBooktitle, gsonBooks.get(position).getBook_title());

        GlideApp.with(context)
                .load(Const.MAIN_URL + gsonBooks.get(position).getFeature_image_path())
                .placeholder(R.drawable.pdf_cover)
                .into(holder.bookCover);
    }

    @Override
    public int getItemCount() {
        return gsonBooks.size();
    }


    public class ReviewDetailViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textAuthor;
        TextView txtBooktitle;
        TextView txtPublisher;
        TextView txtBookCategory;
        ImageView bookCover;

        public ReviewDetailViewHolder(View itemView) {
            super(itemView);
            textAuthor = itemView.findViewById(R.id.main_view_author);
            txtBooktitle = itemView.findViewById(R.id.main_view_title);
            txtBookCategory = itemView.findViewById(R.id.main_view_category);
            txtPublisher = itemView.findViewById(R.id.main_view_publisher);
            bookCover = itemView.findViewById(R.id.review_detail_book_cover);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mListener.OnItemClicked(gsonBooks.get(getLayoutPosition()));
        }
    }

    public interface ReviewItemClickListener {
        void OnItemClicked(GsonBook gsonBook);
    }

}
