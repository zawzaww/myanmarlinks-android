package com.zawzaw.savethelibrary.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter;
import org.sufficientlysecure.htmltextview.HtmlTextView;
import com.wang.avi.AVLoadingIndicatorView;

import com.zawzaw.savethelibrary.R;
import com.zawzaw.savethelibrary.utils.Const;
import com.zawzaw.savethelibrary.utils.FontEmbedder;
import com.zawzaw.savethelibrary.utils.GlideApp;
import com.zawzaw.savethelibrary.viewmodel.ReviewDetailModel;

public class ReviewDetailActivity extends AppCompatActivity {

    ReviewDetailModel reviewDetailModel;
    ImageView imgBook;
    TextView txtBookTitleDetail,txtBookAuthorDetails, txtPublisher,
            txtPublisherAddress,lblBookSubDetails,lblBookNote,lblPublisherDetails;
    HtmlTextView htmlSubjectReview;
    EditText htmlNoteReview;
    ScrollView mScrollView;
    AVLoadingIndicatorView loadingIndicatorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_detail);

        imgBook = findViewById(R.id.img_book_detail);
        txtBookTitleDetail = findViewById(R.id.txt_book_title_detail);
        txtBookAuthorDetails= findViewById(R.id.txt_book_author_detail);
        txtPublisher = findViewById(R.id.txt_publisher);
        txtPublisherAddress = findViewById(R.id.txt_publisher_address);
        lblBookSubDetails = findViewById(R.id.lbl_book_subject_details);
        lblBookNote = findViewById(R.id.lbl_book_note_details);
        lblPublisherDetails = findViewById(R.id.lbl_publisher);
        mScrollView = findViewById(R.id.review_main_scrollview);
        loadingIndicatorView = findViewById(R.id.review_detail_loading_indicator);
        htmlSubjectReview = findViewById(R.id.review_subject);
        htmlNoteReview = findViewById(R.id.review_note);

        reviewDetailModel = ViewModelProviders.of(this).get(ReviewDetailModel.class);

        Bundle args = getIntent().getExtras();
        reviewDetailModel.setReview_id(args.getInt("review_id"));

        reviewDetailModel.getBooks().observe(this, gsonBook -> {
            loadingIndicatorView.hide();
            mScrollView.setVisibility(View.VISIBLE);
            GlideApp.with(getApplicationContext()).load(Const.IMG_URL + gsonBook.getImage())
                    .placeholder(R.drawable.book_cover)
                    .into(imgBook);

            FontEmbedder.force(txtBookTitleDetail, gsonBook.getBook_title());
            FontEmbedder.force(txtBookAuthorDetails, gsonBook.getBookauthor_name());
            FontEmbedder.force(txtPublisher, gsonBook.getBookpublisher_name());
            FontEmbedder.force(txtPublisherAddress, gsonBook.getBookpublisher_address());
            FontEmbedder.force(htmlSubjectReview, gsonBook.getSubjects());
            FontEmbedder.force(lblBookSubDetails, getResources().getString(R.string.title_review_subject));
            FontEmbedder.force(lblBookNote, getResources().getString(R.string.title_review_note));
            FontEmbedder.force(lblPublisherDetails, getResources().getString(R.string.title_publisher));

            HtmlHttpImageGetter htmlGetter = new HtmlHttpImageGetter(htmlNoteReview);
            htmlGetter.enableCompressImage(true, 100);
            FontEmbedder.force(htmlNoteReview, gsonBook.getNotes(), htmlGetter);

        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace your Own Action", Snackbar.LENGTH_LONG).setAction("Action", null).show());
    }

}
