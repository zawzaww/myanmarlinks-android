package com.zawzaw.savethelibrary.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wang.avi.AVLoadingIndicatorView;
import java.util.ArrayList;
import java.util.List;
import android.arch.lifecycle.ViewModelProviders;
import com.zawzaw.savethelibrary.R;
import com.zawzaw.savethelibrary.adapter.ReviewsAdapter;
import com.zawzaw.savethelibrary.model.gson.GsonBook;
import com.zawzaw.savethelibrary.viewmodel.MainModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class LatestReviewsFragment extends Fragment implements ReviewsAdapter.ReviewsOnClickedLinstener
{
    List<GsonBook> books = new ArrayList<>();
    RecyclerView mRecycler;
    ReviewsAdapter mAdapter;
    AVLoadingIndicatorView loadingIndicator;

    public LatestReviewsFragment()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_latest_reviews, container, false);
        mRecycler = view.findViewById(R.id.review_recycler);
        loadingIndicator = view.findViewById(R.id.review_loading_indicator);
        LinearLayoutManager lm = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        mRecycler.setLayoutManager(lm);
        mRecycler.setHasFixedSize(true);

        mAdapter = new ReviewsAdapter(getActivity().getApplicationContext(),books, this);
        mRecycler.setAdapter(mAdapter);

        MainModel mainModel = ViewModelProviders.of(getActivity()).get(MainModel.class);

        mainModel.getLatestBookReviews(1).observe(this, gsonBooks -> {
            books.addAll(gsonBooks);
            mAdapter.notifyDataSetChanged();
            loadingIndicator.hide();
        });
        return view;
    }

    @Override
    public void OnItemClicked(GsonBook gsonBook)
    {

    }

}
