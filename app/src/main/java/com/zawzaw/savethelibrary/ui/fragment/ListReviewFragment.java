package com.zawzaw.savethelibrary.ui.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.malinskiy.superrecyclerview.SuperRecyclerView;
import java.util.ArrayList;
import java.util.List;

import com.wang.avi.AVLoadingIndicatorView;
import com.zawzaw.savethelibrary.R;
import com.zawzaw.savethelibrary.adapter.ReviewDetailAdapter;
import com.zawzaw.savethelibrary.adapter.ReviewsAdapter;
import com.zawzaw.savethelibrary.model.gson.GsonBook;
import com.zawzaw.savethelibrary.viewmodel.ReviewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListReviewFragment extends Fragment implements ReviewsAdapter.ReviewsOnClickedListener {

    SuperRecyclerView mRecycler;
    ReviewDetailAdapter mAdapter;
    AVLoadingIndicatorView indicator;

    ReviewModel reviewModel;

    private static class ActivityState {
        private int nextPage = 1;
        private List<GsonBook> reviews = new ArrayList<>();
    }

    private ActivityState mState = new ActivityState();

    public ListReviewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list_review, container, false);

        mRecycler = view.findViewById(R.id.list_review_recycler);
        indicator = view.findViewById(R.id.main_review_loading_indicator);

        LinearLayoutManager lm = new LinearLayoutManager(getActivity().getApplicationContext());
        mRecycler.setLayoutManager(lm);
        mRecycler.getRecyclerView().setHasFixedSize(true);

        mAdapter = new ReviewDetailAdapter(mState.reviews, getActivity().getApplicationContext());
        mRecycler.setAdapter(mAdapter);

        reviewModel = ViewModelProviders.of(this).get(ReviewModel.class);
        loadData(mState.nextPage);

        mRecycler.setOnMoreListener((overallItemsCount, itemsBeforeMore, maxLastVisiblePosition) -> {
            loadData(mState.nextPage);
        });

        return view;
    }

    private void loadData(int page) {
        reviewModel.getLatestBookReviews(page).observe(this, gsonBooks -> {
            consumeApiData(gsonBooks);
            indicator.hide();
            mRecycler.hideMoreProgress();
        });
    }

    private void consumeApiData(List<GsonBook> books) {
        if(books != null) {
            mState.reviews.addAll(books);
            mAdapter.notifyDataSetChanged();
            mState.nextPage++;
        }
    }

    @Override
    public void OnItemClicked(GsonBook gsonBook) {

    }

}
