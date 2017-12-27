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
import me.relex.circleindicator.CircleIndicator;
import com.wang.avi.AVLoadingIndicatorView;
import com.zawzaw.savethelibrary.R;
import com.zawzaw.savethelibrary.model.gson.GsonNew;
import com.zawzaw.savethelibrary.viewmodel.MainModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsSlideFragment extends Fragment
{
    List<GsonNew> gsonNews = new ArrayList<>();

    private MainModel mainModel;
    ViewPager mViewPager;
    CircleIndicator indicator;
    NewsPagerAdapter adapter;
    AVLoadingIndicatorView loadingIndicator;

    public NewsSlideFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_news_slide, container, false);
        mViewPager = view.findViewById(R.id.main_slider);
        indicator = view.findViewById(R.id.indicator);
        loadingIndicator = view.findViewById(R.id.slider_loading_indicator);

        adapter = new NewsPagerAdapter(getFragmentManager(), gsonNews);
        mViewPager.setAdapter(adapter);
        indicator.setViewPager(mViewPager);

        adapter.registerDataSetObserver(indicator.getDataSetObserver());

        mainModel = ViewModelProviders.of(getActivity()).get(MainModel.class);

        mainModel.getLatestNews(1).observe(this, latestNews -> {
            gsonNews.addAll(latestNews.getNews());
            loadingIndicator.hide();
            adapter.notifyDataSetChanged();
        });
        return view;
    }

    private class NewsPagerAdapter extends FragmentPagerAdapter
    {
        public List<GsonNew> gsonNews;

        public NewsPagerAdapter(FragmentManager fm, List<GsonNew> gsonNews) {
            super(fm);
            this.gsonNews = gsonNews;
        }

        @Override
        public Fragment getItem(int position) {
            SlideFragment slideFragment = SlideFragment.newInstance(gsonNews.get(position).getFeature_image_path(), gsonNews.get(position).getPost_title(), gsonNews.get(position).getPost_id());
            return slideFragment;
        }

        @Override
        public int getCount() {
            return gsonNews.size();
        }
    }

}
