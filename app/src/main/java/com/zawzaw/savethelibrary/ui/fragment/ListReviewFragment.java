package com.zawzaw.savethelibrary.ui.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.malinskiy.superrecyclerview.SuperRecyclerView;
import java.util.List;

import com.wang.avi.AVLoadingIndicatorView;
import com.zawzaw.savethelibrary.R;
import com.zawzaw.savethelibrary.adapter.ReviewDetailAdapter;
import com.zawzaw.savethelibrary.adapter.ReviewsAdapter;
import com.zawzaw.savethelibrary.event.eventclass.Events;
import com.zawzaw.savethelibrary.event.main.OttoBus;
import com.zawzaw.savethelibrary.model.gson.GsonBook;
import com.zawzaw.savethelibrary.model.gson.GsonBooks;
import com.zawzaw.savethelibrary.network.BaseApi;
import com.zawzaw.savethelibrary.network.services.MainService;
import com.zawzaw.savethelibrary.utils.Const;
import com.zawzaw.savethelibrary.viewmodel.ReviewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        reviewModel = ViewModelProviders.of(this).get(ReviewModel.class);

        mAdapter = new ReviewDetailAdapter(reviewModel.gsonBooks, getActivity().getApplicationContext());
        mRecycler.setAdapter(mAdapter);

        if (reviewModel.gsonBooks.isEmpty()) {
            loadData();
        }

        mRecycler.setOnMoreListener((overallItemsCount, itemsBeforeMore, maxLastVisiblePosition) -> {
            loadData();
        });
        return view;
    }

    private void loadData() {
        Call<GsonBooks> call = BaseApi.createService(MainService.class).getLatestBookReviews(Const.INJECTED_STRING, mState.nextPage);
        call.enqueue(new Callback<GsonBooks>() {
            @Override
            public void onResponse(Call<GsonBooks> call, Response<GsonBooks> response) {
                Log.i("API", "API Called");
                consumeApiData(response.body().getBooks());
                indicator.hide();
                mRecycler.hideMoreProgress();
            }

            @Override
            public void onFailure(Call<GsonBooks> call, Throwable t) {
                consumeApiData(null);
                indicator.hide();
                mRecycler.hideMoreProgress();
                Events.NoInternetConection noInternetConection = new Events.NoInternetConection("no");
                OttoBus.getBus().post(noInternetConection);
            }
        });

    }

    private void consumeApiData(List<GsonBook> books) {
        if(books != null) {
            reviewModel.gsonBooks.addAll(books);
            mAdapter.notifyDataSetChanged();
            mState.nextPage++;
        }
    }

    @Override
    public void OnItemClicked(GsonBook gsonBook) {

    }

}
