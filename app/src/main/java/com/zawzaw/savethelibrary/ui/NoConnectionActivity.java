package com.zawzaw.savethelibrary.ui;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zawzaw.savethelibrary.MainActivity;
import com.zawzaw.savethelibrary.R;
import com.zawzaw.savethelibrary.utils.FontEmbedder;

public class NoConnectionActivity extends AppCompatActivity {

    View mView;
    String returnedActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_connection);
        mView = findViewById(R.id.no_connection_activity);
        Bundle args = getIntent().getExtras();
        returnedActivity = args.getString("returned_activity");

        TextView noInternet = findViewById(R.id.text_no_internet);
        FontEmbedder.force(noInternet, getString(R.string.not_internet_connection_mm));

        Snackbar.make(mView, getString(R.string.no_internet_connection), Snackbar.LENGTH_INDEFINITE).setAction("RETRY", new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = null;
                if (returnedActivity.equals("MainActivity")) {
                    intent = new Intent(NoConnectionActivity.this, MainActivity.class);
                }
                else if (returnedActivity.equals("ListReviewActivity")){
                    intent = new Intent(NoConnectionActivity.this, ListReviewActivity.class);
                }

                startActivity(intent);
                finish();

            }
        }).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
