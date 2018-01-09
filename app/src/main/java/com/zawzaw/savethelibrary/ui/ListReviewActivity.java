package com.zawzaw.savethelibrary.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.zawzaw.savethelibrary.MainActivity;
import com.zawzaw.savethelibrary.R;
import com.zawzaw.savethelibrary.event.eventclass.Events;
import com.zawzaw.savethelibrary.ui.fragment.ListReviewAuthorFragment;
import com.zawzaw.savethelibrary.ui.fragment.ListReviewCategoryFragment;
import com.zawzaw.savethelibrary.ui.fragment.ListReviewFragment;
import com.zawzaw.savethelibrary.ui.fragment.ListReviewPublisherFragment;

public class ListReviewActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    ViewPager mViewPager;
    TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_review);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mViewPager = findViewById(R.id.review_pager);
        mTabLayout = findViewById(R.id.review_pager_title);
        ReviewPagerAdapter mAdapter = new ReviewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    public void getMessage(Events.NoInternetConection noInternetConection) {
        changeToNoConnection();
    }

    private void changeToNoConnection() {
        Intent intent = new Intent(ListReviewActivity.this, NoConnectionActivity.class);
        Bundle args = new Bundle();
        args.putString("returned_activity", TAG);
        intent.putExtras(args);

        startActivity(intent);
        finish();
    }

    public class ReviewPagerAdapter extends FragmentPagerAdapter {

        public ReviewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new ListReviewFragment();
                case 1:
                    return new ListReviewCategoryFragment();
                case 2:
                    return new ListReviewAuthorFragment();
                case 3:
                    return new ListReviewPublisherFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Latest";
                case 1:
                    return "Categories";
                case 2:
                    return "Author";
                case 3:
                    return "Publisher";
            }
            return super.getPageTitle(position);
        }

    }

}
