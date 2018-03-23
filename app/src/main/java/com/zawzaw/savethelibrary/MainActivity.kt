package com.zawzaw.savethelibrary

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.arch.lifecycle.ViewModelProviders
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.OvershootInterpolator
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.analytics.FirebaseAnalytics
import com.squareup.otto.Subscribe
import com.wang.avi.AVLoadingIndicatorView
import at.blogc.android.views.ExpandableTextView
import de.hdodenhof.circleimageview.CircleImageView
import com.zawzaw.savethelibrary.event.eventclass.Events
import com.zawzaw.savethelibrary.event.main.OttoBus
import com.zawzaw.savethelibrary.ui.ListReviewActivity
import com.zawzaw.savethelibrary.ui.NoConnectionActivity
import com.zawzaw.savethelibrary.utils.Const
import com.zawzaw.savethelibrary.utils.FontEmbedder
import com.zawzaw.savethelibrary.utils.GlideApp
import com.zawzaw.savethelibrary.utils.Helper
import com.zawzaw.savethelibrary.viewmodel.MainModel

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var mFirebaseAnalytics: FirebaseAnalytics? = null

    internal var fab: FloatingActionButton
    internal var mQuoteText: ExpandableTextView
    internal var quoteLoadingIndicator: AVLoadingIndicatorView
    internal var quoteOpen: ImageView
    internal var authorImage: CircleImageView
    internal var quoteAuthorName: TextView
    internal var quoteReference: TextView
    internal var txtMore: TextView

    override fun onStart() {
        super.onStart()
        OttoBus.getBus().register(this)
    }

    override fun onStop() {
        super.onStop()
        OttoBus.getBus().unregister(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        // Firebase Analytics
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "1")
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "SaveTheLib")
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "text")
        mFirebaseAnalytics!!.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle)

        mQuoteText = findViewById(R.id.quote_text)
        quoteLoadingIndicator = findViewById(R.id.quote_loading_indicator)
        quoteOpen = findViewById(R.id.quote_open)
        authorImage = findViewById(R.id.quote_author)
        quoteAuthorName = findViewById(R.id.quote_author_name)
        quoteReference = findViewById(R.id.quote_reference)
        txtMore = findViewById(R.id.txt_more)

        if (!Helper.checkInternetConnection(applicationContext)) {
            changeToNoConnection()
        }

        val mainModel = ViewModelProviders.of(this).get(MainModel::class.java)
        mainModel.randomQuote.observe(this, { randomQuote ->
            quoteLoadingIndicator.hide()
            quoteOpen.visibility = View.VISIBLE

            GlideApp.with(applicationContext)
                    .load(Const.IMG_URL + randomQuote.get("quote").quote_author_name!!)
                    .placeholder(R.drawable.profile)
                    .into(authorImage)

            mQuoteText.setAnimationDuration(750L)
            mQuoteText.setInterpolator(OvershootInterpolator())
            mQuoteText.expandInterpolator = OvershootInterpolator()
            mQuoteText.collapseInterpolator = OvershootInterpolator()
            txtMore.setOnClickListener {
                txtMore.text = if (mQuoteText.isExpanded) "More >>>" else "<<< Less"
                mQuoteText.toggle()
            }

            FontEmbedder.force(mQuoteText, randomQuote.get("quote").content)
            FontEmbedder.force(quoteAuthorName, randomQuote.get("quote").quote_author_name)
            FontEmbedder.force(quoteReference, randomQuote.get("quote").reference)

        })

        val latestReviews = findViewById<TextView>(R.id.title_latest_review)
        FontEmbedder.forceTitle(latestReviews, getString(R.string.latest_reviews))
        latestReviews.setOnClickListener { view ->
            val intent = Intent(this@MainActivity, ListReviewActivity::class.java)
            startActivity(intent)
        }

        fab = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your Own Action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)

    }

    @Subscribe
    fun getMessage(noInternetConnection: Events.NoInternetConection) {
        changeToNoConnection()
    }

    private fun changeToNoConnection() {
        val intent = Intent(this@MainActivity, NoConnectionActivity::class.java)
        val args = Bundle()
        args.putString("returned_activity", TAG)
        intent.putExtras(args)

        startActivity(intent)
        finish()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    companion object {

        val TAG = MainActivity::class.java.simpleName
    }

}
