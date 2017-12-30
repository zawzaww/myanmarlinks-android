package com.zawzaw.savethelibrary.ui.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wang.avi.AVLoadingIndicatorView;
import java.util.ArrayList;
import java.util.List;
import com.zawzaw.savethelibrary.R;
import com.zawzaw.savethelibrary.adapter.ResourcesAdapter;
import com.zawzaw.savethelibrary.model.gson.GsonPdf;
import com.zawzaw.savethelibrary.model.parcel.ParcelPdf;
import com.zawzaw.savethelibrary.ui.PdfDetailsActivity;
import com.zawzaw.savethelibrary.viewmodel.MainModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class LatestResourceFragment extends Fragment implements ResourcesAdapter.ResourceOnClickListener
{
    List<GsonPdf> pdfs = new ArrayList<>();
    RecyclerView mRecycler;
    ResourcesAdapter mAdapter;
    AVLoadingIndicatorView loadingIndicator;

    public LatestResourceFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_latest_reviews, container, false);
        mRecycler = view.findViewById(R.id.review_recycler);
        loadingIndicator = view.findViewById(R.id.review_loading_indicator);
        LinearLayoutManager lm = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        mRecycler.setLayoutManager(lm);
        mRecycler.setHasFixedSize(true);

        mAdapter = new ResourcesAdapter(getActivity().getApplicationContext(),pdfs, this);
        mRecycler.setAdapter(mAdapter);

        MainModel mainModel = ViewModelProviders.of(getActivity()).get(MainModel.class);

        mainModel.getLatestPdfs(1).observe(this, gsonPdfs ->
        {
            pdfs.addAll(gsonPdfs);
            mAdapter.notifyDataSetChanged();
            loadingIndicator.hide();
        });

        return view;
    }

    @Override
    public void OnItemClicked(GsonPdf gsonPdf)
    {
        ParcelPdf parcelPdf = new ParcelPdf( gsonPdf.getPdf_id(), gsonPdf.getPdf_title(),
                gsonPdf.getDownload_links(), gsonPdf.getPdf_image(), gsonPdf.getCategory_mm(),
                gsonPdf.getCategory_en(), gsonPdf.getPdf_sources());
        Bundle args = new Bundle();
        args.putParcelable("pdf", parcelPdf);
        Intent intent = new Intent(getActivity(), PdfDetailsActivity.class);
        intent.putExtras(args);
        startActivity(intent);
    }

}
