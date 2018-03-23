package com.zawzaw.savethelibrary.ui

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.TextView
import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter
import org.sufficientlysecure.htmltextview.HtmlTextView
import com.wang.avi.AVLoadingIndicatorView

import com.zawzaw.savethelibrary.MainActivity
import com.zawzaw.savethelibrary.R
import com.zawzaw.savethelibrary.event.eventclass.Events
import com.zawzaw.savethelibrary.utils.Const
import com.zawzaw.savethelibrary.utils.FontEmbedder
import com.zawzaw.savethelibrary.utils.GlideApp
import com.zawzaw.savethelibrary.viewmodel.ReviewDetailModel

class ReviewDetailActivity : AppCompatActivity() {

    internal var reviewDetailModel: ReviewDetailModel
    internal var imgBook: ImageView
    internal var txtBookTitleDetail: TextView
    internal var txtBookAuthorDetails: TextView
    internal var txtPublisher: TextView
    internal var txtPublisherAddress: TextView
    internal var lblBookSubDetails: TextView
    internal var lblBookNote: TextView
    internal var lblPublisherDetails: TextView
    internal var htmlSubjectReview: HtmlTextView
    internal var htmlNoteReview: EditText
    internal var mScrollView: ScrollView
    internal var loadingIndicatorView: AVLoadingIndicatorView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review_detail)

        imgBook = findViewById(R.id.img_book_detail)
        txtBookTitleDetail = findViewById(R.id.txt_book_title_detail)
        txtBookAuthorDetails = findViewById(R.id.txt_book_author_detail)
        txtPublisher = findViewById(R.id.txt_publisher)
        txtPublisherAddress = findViewById(R.id.txt_publisher_address)
        lblBookSubDetails = findViewById(R.id.lbl_book_subject_details)
        lblBookNote = findViewById(R.id.lbl_book_note_details)
        lblPublisherDetails = findViewById(R.id.lbl_publisher)
        mScrollView = findViewById(R.id.review_main_scrollview)
        loadingIndicatorView = findViewById(R.id.review_detail_loading_indicator)
        htmlSubjectReview = findViewById(R.id.review_subject)
        htmlNoteReview = findViewById(R.id.review_note)

        reviewDetailModel = ViewModelProviders.of(this).get(ReviewDetailModel::class.java)

        val args = intent.extras
        reviewDetailModel.review_id = args!!.getInt("review_id")

        reviewDetailModel.books.observe(this, { gsonBook ->
            loadingIndicatorView.hide()
            mScrollView.visibility = View.VISIBLE
            GlideApp.with(applicationContext).load(Const.IMG_URL + gsonBook.image!!)
                    .placeholder(R.drawable.book_cover)
                    .into(imgBook)

            FontEmbedder.force(txtBookTitleDetail, gsonBook.book_title)
            FontEmbedder.force(txtBookAuthorDetails, gsonBook.bookauthor_name)
            FontEmbedder.force(txtPublisher, gsonBook.bookpublisher_name)
            FontEmbedder.force(txtPublisherAddress, gsonBook.bookpublisher_address)
            FontEmbedder.force(htmlSubjectReview, gsonBook.subjects)
            FontEmbedder.force(lblBookSubDetails, resources.getString(R.string.title_review_subject))
            FontEmbedder.force(lblBookNote, resources.getString(R.string.title_review_note))
            FontEmbedder.force(lblPublisherDetails, resources.getString(R.string.title_publisher))

            val htmlGetter = HtmlHttpImageGetter(htmlNoteReview)
            htmlGetter.enableCompressImage(true, 100)
            FontEmbedder.force(htmlNoteReview, gsonBook.notes, htmlGetter)

        })

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace your Own Action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    fun getMessage(noInternetConection: Events.NoInternetConection) {
        changedToNoConnecton()
    }

    private fun changedToNoConnecton() {
        val intent = Intent(this@ReviewDetailActivity, NoConnectionActivity::class.java)
        val args = Bundle()
        args.putString("returned_activity", TAG)
        intent.putExtras(args)

        startActivity(intent)
        finish()
    }

    companion object {

        val TAG = MainActivity::class.java.simpleName
    }

}
