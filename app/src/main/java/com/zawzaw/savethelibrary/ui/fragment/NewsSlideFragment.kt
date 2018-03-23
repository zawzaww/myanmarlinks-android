package com.zawzaw.savethelibrary.ui.fragment

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.util.ArrayList
import com.wang.avi.AVLoadingIndicatorView
import me.relex.circleindicator.CircleIndicator
import com.zawzaw.savethelibrary.R
import com.zawzaw.savethelibrary.model.gson.GsonNew
import com.zawzaw.savethelibrary.viewmodel.MainModel

/**
 * A simple [Fragment] subclass.
 */
class NewsSlideFragment : Fragment() {

    internal var gsonNews: MutableList<GsonNew> = ArrayList()

    private var mainModel: MainModel? = null
    internal var mViewPager: ViewPager
    internal var indicator: CircleIndicator
    internal var adapter: NewsPagerAdapter
    internal var loadingIndicator: AVLoadingIndicatorView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_news_slide, container, false)
        mViewPager = view.findViewById(R.id.main_slider)
        indicator = view.findViewById(R.id.indicator)
        loadingIndicator = view.findViewById(R.id.slider_loading_indicator)

        adapter = NewsPagerAdapter(fragmentManager, gsonNews)
        mViewPager.adapter = adapter
        indicator.setViewPager(mViewPager)
        adapter.registerDataSetObserver(indicator.dataSetObserver)

        mainModel = ViewModelProviders.of(activity!!).get(MainModel::class.java)

        mainModel!!.getLatestNews(1).observe(this, { latestNews ->
            gsonNews.addAll(latestNews.news!!)
            loadingIndicator.hide()
            adapter.notifyDataSetChanged()
        })
        return view
    }

    inner class NewsPagerAdapter(fm: FragmentManager, internal var gsonNews: List<GsonNew>) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return SlideFragment.newInstance(gsonNews[position].feature_image_path, gsonNews[position].post_title, gsonNews[position].post_id)
        }

        override fun getCount(): Int {
            return gsonNews.size
        }
    }

}// Required empty public constructor
