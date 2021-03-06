package com.zawzaw.savethelibrary.ui

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter
import org.sufficientlysecure.htmltextview.HtmlTextView

import com.zawzaw.savethelibrary.R
import com.zawzaw.savethelibrary.utils.Const
import com.zawzaw.savethelibrary.utils.FontEmbedder
import com.zawzaw.savethelibrary.utils.GlideApp
import com.zawzaw.savethelibrary.viewmodel.NewsDetailModel

class NewsDetailActivity : AppCompatActivity() {

    internal var imageView: ImageView
    internal var txtNewsTitle: TextView
    internal var txtNewsAuthor: TextView
    internal var txtNewsDate: TextView
    internal var htmlTextView: HtmlTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)

        imageView = findViewById(R.id.new_image)
        txtNewsTitle = findViewById(R.id.new_title)
        txtNewsAuthor = findViewById(R.id.news_author)
        txtNewsDate = findViewById(R.id.news_date)
        htmlTextView = findViewById(R.id.news_content)

        val args = intent.extras
        val newsDetailModel = ViewModelProviders.of(this).get(NewsDetailModel::class.java)
        newsDetailModel.post_id = args!!.getInt("post_id")

        newsDetailModel.news.observe(this, { gsonNew ->
            GlideApp.with(applicationContext)
                    .load(Const.IMG_URL + gsonNew.feature_image_path!!)
                    .into(imageView)

            FontEmbedder.force(txtNewsTitle, gsonNew.post_title)
            FontEmbedder.force(txtNewsAuthor, gsonNew.author_name)
            FontEmbedder.force(txtNewsDate, gsonNew.date)
            val htmlGetter = HtmlHttpImageGetter(htmlTextView)
            htmlGetter.enableCompressImage(true, 100)
            FontEmbedder.force(htmlTextView, gsonNew.post_content, htmlGetter)
        })

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener { v ->
            Snackbar.make(v, "Replace your own Action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

}
