package com.zawzaw.savethelibrary.ui.fragment

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.util.ArrayList

import com.wang.avi.AVLoadingIndicatorView
import com.zawzaw.savethelibrary.R
import com.zawzaw.savethelibrary.adapter.ReviewsAdapter
import com.zawzaw.savethelibrary.model.gson.GsonBook
import com.zawzaw.savethelibrary.ui.ReviewDetailActivity
import com.zawzaw.savethelibrary.viewmodel.MainModel

/**
 * A simple [Fragment] subclass.
 */
class LatestReviewsFragment : Fragment(), ReviewsAdapter.ReviewsOnClickedListener {

    internal var books: MutableList<GsonBook> = ArrayList()
    internal var mRecycler: RecyclerView
    internal var mAdapter: ReviewsAdapter
    internal var loadingIndicator: AVLoadingIndicatorView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_latest_reviews, container, false)
        mRecycler = view.findViewById(R.id.review_recycler)
        loadingIndicator = view.findViewById(R.id.review_loading_indicator)
        val lm = LinearLayoutManager(activity!!.applicationContext, LinearLayoutManager.HORIZONTAL, false)
        mRecycler.layoutManager = lm
        mRecycler.setHasFixedSize(true)

        mAdapter = ReviewsAdapter(activity!!.applicationContext, books, this)
        mRecycler.adapter = mAdapter

        val mainModel = ViewModelProviders.of(activity!!).get(MainModel::class.java)

        mainModel.getLatestBookReviews(1).observe(this, { gsonBooks ->
            books.addAll(gsonBooks)
            mAdapter.notifyDataSetChanged()
            loadingIndicator.hide()
        })
        return view
    }

    override fun OnItemClicked(gsonBook: GsonBook) {
        val args = Bundle()
        args.putInt("review_id", gsonBook.book_id)
        val intent = Intent(activity, ReviewDetailActivity::class.java)
        intent.putExtras(args)

        startActivity(intent)
    }

}// Required empty public constructor
