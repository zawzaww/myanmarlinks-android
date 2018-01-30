package com.zawzaw.savethelibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.arch.lifecycle.ViewModelProviders;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.squareup.otto.Subscribe;
import com.wang.avi.AVLoadingIndicatorView;
import de.hdodenhof.circleimageview.CircleImageView;

import com.zawzaw.savethelibrary.event.eventclass.Events;
import com.zawzaw.savethelibrary.event.main.OttoBus;
import com.zawzaw.savethelibrary.ui.ListReviewActivity;
import com.zawzaw.savethelibrary.ui.NoConnectionActivity;
import com.zawzaw.savethelibrary.utils.Const;
import com.zawzaw.savethelibrary.utils.FontEmbedder;
import com.zawzaw.savethelibrary.utils.GlideApp;
import com.zawzaw.savethelibrary.utils.Helper;
import com.zawzaw.savethelibrary.viewmodel.MainModel;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String TAG = MainActivity.class.getSimpleName();

    private FirebaseAnalytics mFirebaseAnalytics;

    FloatingActionButton fab;
    TextView mQuoteText;
    AVLoadingIndicatorView quoteLoadingIndicator;
    ImageView quoteOpen;
    CircleImageView authorImage;
    TextView quoteAuthorName;
    TextView quoteReference;

    @Override
    protected void onStart() {
        super.onStart();
        OttoBus.getBus().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        OttoBus.getBus().unregister(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Firebase Analytics
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, String.valueOf("1"));
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, String.valueOf("SaveTheLib"));
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "text");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

        mQuoteText = findViewById(R.id.quote_text);
        quoteLoadingIndicator = findViewById(R.id.quote_loading_indicator);
        quoteOpen = findViewById(R.id.quote_open);
        authorImage = findViewById(R.id.quote_author);
        quoteAuthorName = findViewById(R.id.quote_author_name);
        quoteReference = findViewById(R.id.quote_reference);

        if (!Helper.checkInternetConnection(getApplicationContext())) {
            changeToNoConnection();
        }

        MainModel mainModel = ViewModelProviders.of(this).get(MainModel.class);
        mainModel.getRandomQuote().observe(this, randomQuote -> {
            quoteLoadingIndicator.hide();
            quoteOpen.setVisibility(View.VISIBLE);

            GlideApp.with(getApplicationContext())
                    .load(Const.IMG_URL + randomQuote.get("quote").getQuote_author_name())
                    .placeholder(R.drawable.profile )
                    .into(authorImage);

            FontEmbedder.force(mQuoteText, randomQuote.get("quote").getContent());
            FontEmbedder.force(quoteAuthorName, randomQuote.get("quote").getQuote_author_name());
            FontEmbedder.force(quoteReference, randomQuote.get("quote").getReference());

        });

        TextView latestReviews = findViewById(R.id.title_latest_review);
        FontEmbedder.forceTitle(latestReviews, getString(R.string.latest_reviews));
        latestReviews.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ListReviewActivity.class);
            startActivity(intent);
        });

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your Own Action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Subscribe
    public void getMessage(Events.NoInternetConection noInternetConnection) {
        changeToNoConnection();
    }

    private void changeToNoConnection() {
        Intent intent = new Intent(MainActivity.this, NoConnectionActivity.class);
        Bundle args = new Bundle();
        args.putString("returned_activity", TAG);
        intent.putExtras(args);

        startActivity(intent);
        finish();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
