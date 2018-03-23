package com.zawzaw.savethelibrary.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.arch.lifecycle.ViewModelProviders
import com.malinskiy.superrecyclerview.SuperRecyclerView
import com.wang.avi.AVLoadingIndicatorView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import com.zawzaw.savethelibrary.R
import com.zawzaw.savethelibrary.adapter.ReviewDetailAdapter
import com.zawzaw.savethelibrary.adapter.ReviewsAdapter
import com.zawzaw.savethelibrary.event.eventclass.Events
import com.zawzaw.savethelibrary.event.main.OttoBus
import com.zawzaw.savethelibrary.model.gson.GsonBook
import com.zawzaw.savethelibrary.model.gson.GsonBooks
import com.zawzaw.savethelibrary.network.BaseApi
import com.zawzaw.savethelibrary.network.services.MainService
import com.zawzaw.savethelibrary.ui.ReviewDetailActivity
import com.zawzaw.savethelibrary.utils.Const
import com.zawzaw.savethelibrary.viewmodel.ReviewModel

/**
 * A simple [Fragment] subclass.
 */
class ListReviewFragment : Fragment(), ReviewDetailAdapter.ReviewItemClickListener {

    internal var mRecycler: SuperRecyclerView
    internal var mAdapter: ReviewDetailAdapter
    internal var indicator: AVLoadingIndicatorView

    internal var reviewModel: ReviewModel

    private val mState = ActivityState()

    private class ActivityState {
        private val nextPage = 1
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_list_review, container, false)

        mRecycler = view.findViewById(R.id.list_review_recycler)
        indicator = view.findViewById(R.id.main_review_loading_indicator)

        val lm = LinearLayoutManager(activity!!.applicationContext)
        mRecycler.setLayoutManager(lm)
        mRecycler.recyclerView.setHasFixedSize(true)

        reviewModel = ViewModelProviders.of(this).get(ReviewModel::class.java)

        mAdapter = ReviewDetailAdapter(reviewModel.gsonBooks, activity!!.applicationContext, this)
        mRecycler.adapter = mAdapter

        if (reviewModel.gsonBooks.isEmpty()) {
            loadData()
        }

        mRecycler.setOnMoreListener { overallItemsCount, itemsBeforeMore, maxLastVisiblePosition -> loadData() }

        mRecycler.setRefreshListener {
            mRecycler.isEnabled = false
            reviewModel.gsonBooks.clear()
            mState.nextPage = 1
            loadData()
        }
        return view
    }

    private fun loadData() {
        val call = BaseApi.createService(MainService::class.java).getLatestBookReviews(Const.INJECTED_STRING, mState.nextPage)
        call.enqueue(object : Callback<GsonBooks> {
            override fun onResponse(call: Call<GsonBooks>, response: Response<GsonBooks>) {
                Log.i("API", "API Called")
                consumeApiData(response.body()!!.books)
                indicator.hide()
                mRecycler.isEnabled = true
                mRecycler.hideMoreProgress()
            }

            override fun onFailure(call: Call<GsonBooks>, t: Throwable) {
                consumeApiData(null)
                indicator.hide()
                mRecycler.isEnabled = true
                mRecycler.hideMoreProgress()
                val noInternetConection = Events.NoInternetConection("no")
                OttoBus.getBus().post(noInternetConection)
            }
        })

    }

    private fun consumeApiData(books: List<GsonBook>?) {
        if (books != null) {
            reviewModel.gsonBooks.addAll(books)
            mAdapter.notifyDataSetChanged()
            mState.nextPage++
        }
    }

    override fun OnItemClicked(gsonBook: GsonBook) {
        val args = Bundle()
        args.putInt("review_id", gsonBook.book_id)
        val intent = Intent(activity, ReviewDetailActivity::class.java)
        intent.putExtras(args)
        startActivity(intent)
    }

}// Required empty public constructor
