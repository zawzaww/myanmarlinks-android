package com.zawzaw.savethelibrary.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.zawzaw.savethelibrary.R;
import com.zawzaw.savethelibrary.utils.FontEmbedder;
import com.zawzaw.savethelibrary.viewmodel.ReviewDetailModel;

public class ReviewDetailActivity extends AppCompatActivity {

    ReviewDetailModel reviewDetailModel;
    WebView txtBookSubDetail, txtBookNoteDetail;
    ImageView imgBook;
    TextView txtBookTitleDetail, txtBookAuthorDetails, txtTagDetails, txtPublisher,
            txtPublisherAddress, lblBookSubDetails, lblBookNote, lblPublisherDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_detail);

        imgBook = findViewById(R.id.img_book_detail);
        txtBookTitleDetail = findViewById(R.id.txt_book_title_detail);
        txtBookAuthorDetails= findViewById(R.id.txt_book_author_detail);
        txtBookSubDetail = findViewById(R.id.txt_book_subject_details);
        txtBookNoteDetail = findViewById(R.id.txt_book_note_details);
        txtPublisher = findViewById(R.id.txt_publisher);
        txtPublisherAddress = findViewById(R.id.txt_publisher_address);
        lblBookSubDetails = findViewById(R.id.lbl_book_subject_details);
        lblBookNote = findViewById(R.id.lbl_book_note_details);
        lblPublisherDetails = findViewById(R.id.lbl_publisher);

        reviewDetailModel = ViewModelProviders.of(this).get(ReviewDetailModel.class);

        Bundle args = getIntent().getExtras();
        reviewDetailModel.setReview_id(args.getInt("review_id"));

        reviewDetailModel.getBooks().observe(this, gsonBook -> {
            FontEmbedder.force(txtBookTitleDetail, gsonBook.getBook_title());
            FontEmbedder.force(txtBookAuthorDetails, gsonBook.getBookauthor_name());
            FontEmbedder.force(txtPublisher, gsonBook.getBookpublisher_name());
            FontEmbedder.force(txtPublisherAddress, gsonBook.getBookpublisher_address());
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace your Own Action", Snackbar.LENGTH_LONG).setAction("Action", null).show());
    }

}
