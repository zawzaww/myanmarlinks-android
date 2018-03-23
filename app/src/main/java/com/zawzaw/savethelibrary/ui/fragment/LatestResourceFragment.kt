package com.zawzaw.savethelibrary.ui.fragment

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.net.Uri
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
import com.zawzaw.savethelibrary.adapter.ResourcesAdapter
import com.zawzaw.savethelibrary.model.gson.GsonPdf
import com.zawzaw.savethelibrary.viewmodel.MainModel

/**
 * A simple [Fragment] subclass.
 */
class LatestResourceFragment : Fragment(), ResourcesAdapter.ResourcesOnClickListener {

    internal var pdfs: MutableList<GsonPdf> = ArrayList()
    internal var mRecycler: RecyclerView
    internal var mAdapter: ResourcesAdapter
    internal var loadingIndicator: AVLoadingIndicatorView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_latest_reviews, container, false)
        mRecycler = view.findViewById(R.id.review_recycler)
        loadingIndicator = view.findViewById(R.id.review_loading_indicator)

        val lm = LinearLayoutManager(activity!!.applicationContext, LinearLayoutManager.HORIZONTAL, false)
        mRecycler.layoutManager = lm
        mRecycler.setHasFixedSize(true)

        mAdapter = ResourcesAdapter(activity!!.applicationContext, pdfs, this)
        mRecycler.adapter = mAdapter

        val mainModel = ViewModelProviders.of(activity!!).get(MainModel::class.java)

        mainModel.getLatestPdfs(1).observe(this, { gsonPdfs ->
            pdfs.addAll(gsonPdfs)
            mAdapter.notifyDataSetChanged()
            loadingIndicator.hide()
        })

        return view
    }

    override fun OnItemClicked(gsonPdf: GsonPdf) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(gsonPdf.download_link)

        startActivity(intent)
    }

}// Required empty public constructor
