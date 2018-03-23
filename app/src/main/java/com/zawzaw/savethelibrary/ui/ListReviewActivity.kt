package com.zawzaw.savethelibrary.ui

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.PagerTabStrip
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity

import com.zawzaw.savethelibrary.MainActivity
import com.zawzaw.savethelibrary.R
import com.zawzaw.savethelibrary.event.eventclass.Events
import com.zawzaw.savethelibrary.ui.fragment.ListReviewAuthorFragment
import com.zawzaw.savethelibrary.ui.fragment.ListReviewCategoryFragment
import com.zawzaw.savethelibrary.ui.fragment.ListReviewFragment
import com.zawzaw.savethelibrary.ui.fragment.ListReviewPublisherFragment

class ListReviewActivity : AppCompatActivity() {

    internal var mViewPager: ViewPager
    internal var mPagerTabStrip: PagerTabStrip

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_review)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        mViewPager = findViewById(R.id.review_pager)
        mPagerTabStrip = findViewById(R.id.review_pager_tab_strip)
        mPagerTabStrip.setBackgroundColor(resources.getColor(R.color.colorPrimary))
        mPagerTabStrip.setTextColor(resources.getColor(R.color.white))
        mPagerTabStrip.tabIndicatorColor = resources.getColor(R.color.colorAccent)

        val mAdapter = ReviewPagerAdapter(supportFragmentManager)
        mViewPager.adapter = mAdapter
    }

    fun getMessage(noInternetConection: Events.NoInternetConection) {
        changeToNoConnection()
    }

    private fun changeToNoConnection() {
        val intent = Intent(this@ListReviewActivity, NoConnectionActivity::class.java)
        val args = Bundle()
        args.putString("returned_activity", TAG)
        intent.putExtras(args)

        startActivity(intent)
        finish()
    }

    inner class ReviewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment? {
            when (position) {
                0 -> return ListReviewFragment()
                1 -> return ListReviewAuthorFragment()
                2 -> return ListReviewCategoryFragment()
                3 -> return ListReviewPublisherFragment()
            }
            return null
        }

        override fun getCount(): Int {
            return 4
        }

        override fun getPageTitle(position: Int): CharSequence? {
            when (position) {
                0 -> return getString(R.string.latest)
                1 -> return getString(R.string.authors)
                2 -> return getString(R.string.categories)
                3 -> return getString(R.string.publishers)
            }
            return super.getPageTitle(position)
        }

    }

    companion object {

        val TAG = MainActivity::class.java.simpleName
    }

}
