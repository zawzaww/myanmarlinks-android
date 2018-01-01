package com.zawzaw.savethelibrary.ui.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import com.zawzaw.savethelibrary.R;
import com.zawzaw.savethelibrary.model.gson.GsonNew;
import com.zawzaw.savethelibrary.viewmodel.NewsModel;
import me.relex.circleindicator.CircleIndicator;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsSlideFragment extends Fragment {

    List<GsonNew> gsonNews = new ArrayList<>();

    private NewsModel newsModel;
    ViewPager mViewPager;
    CircleIndicator indicator;
    NewsPagerAdapter adapter;

    public NewsSlideFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_news_slide, container, false);
        mViewPager = view.findViewById(R.id.main_slider);
        indicator = view.findViewById(R.id.indicator);
        adapter = new NewsPagerAdapter(getFragmentManager(), gsonNews);
        mViewPager.setAdapter(adapter);
        indicator.setViewPager(mViewPager);
        adapter.registerDataSetObserver(indicator.getDataSetObserver());

        newsModel = ViewModelProviders.of(getActivity()).get(NewsModel.class);

        newsModel.getLatestNews(1).observe(this, latestNews -> {
            gsonNews.addAll(latestNews.getNews());
            adapter.notifyDataSetChanged();
        });
        return view;
    }

    public class NewsPagerAdapter extends FragmentPagerAdapter {

        List<GsonNew> gsonNews;

        public NewsPagerAdapter(FragmentManager fm, List<GsonNew> gsonNews) {
            super(fm);
            this.gsonNews = gsonNews;
        }

        @Override
        public Fragment getItem(int position) {
            SlideFragment slideFragment = SlideFragment.newInstance(gsonNews.get(position).getFeature_image_path(), gsonNews.get(position).getPost_title());
            return slideFragment;
        }

        @Override
        public int getCount() {
            return gsonNews.size();
        }
    }

}
