package com.zawzaw.savethelibrary.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.zawzaw.savethelibrary.R;

public class ListReviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_review);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
