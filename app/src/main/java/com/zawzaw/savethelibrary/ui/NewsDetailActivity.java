package com.zawzaw.savethelibrary.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter;
import org.sufficientlysecure.htmltextview.HtmlTextView;

import com.zawzaw.savethelibrary.R;
import com.zawzaw.savethelibrary.utils.Const;
import com.zawzaw.savethelibrary.utils.FontEmbedder;
import com.zawzaw.savethelibrary.utils.GlideApp;
import com.zawzaw.savethelibrary.viewmodel.NewsDetailModel;

public class NewsDetailActivity extends AppCompatActivity {

    ImageView imageView;
    TextView txtNewsTitle, txtNewsAuthor, txtNewsDate;
    HtmlTextView htmlTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        imageView = findViewById(R.id.new_image);
        txtNewsTitle = findViewById(R.id.new_title);
        txtNewsAuthor = findViewById(R.id.news_author);
        txtNewsDate = findViewById(R.id.news_date);
        htmlTextView = findViewById(R.id.news_content);

        Bundle args = getIntent().getExtras();
        NewsDetailModel newsDetailModel = ViewModelProviders.of(this).get(NewsDetailModel.class);
        newsDetailModel.setPost_id(args.getInt("post_id"));

        newsDetailModel.getNews().observe(this, gsonNew -> {
            GlideApp.with(getApplicationContext())
                    .load(Const.IMG_URL + gsonNew.getFeature_image_path())
                    .into(imageView);

            FontEmbedder.force(txtNewsTitle, gsonNew.getPost_title());
            FontEmbedder.force(txtNewsAuthor, gsonNew.getAuthor_name());
            FontEmbedder.force(txtNewsDate, gsonNew.getDate());
            HtmlHttpImageGetter htmlGetter = new HtmlHttpImageGetter(htmlTextView);
            htmlGetter.enableCompressImage(true, 100);
            FontEmbedder.force(htmlTextView, gsonNew.getPost_content(), htmlGetter);
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(v -> Snackbar.make(v, "Replace your own Action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
