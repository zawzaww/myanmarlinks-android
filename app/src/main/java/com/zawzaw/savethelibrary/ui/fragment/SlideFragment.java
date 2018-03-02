package com.zawzaw.savethelibrary.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.zawzaw.savethelibrary.R;
import com.zawzaw.savethelibrary.ui.NewsDetailActivity;
import com.zawzaw.savethelibrary.utils.Const;
import com.zawzaw.savethelibrary.utils.FontEmbedder;
import com.zawzaw.savethelibrary.utils.GlideApp;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SlideFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class SlideFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_PARAM1 = "imageLink";
    private static final String ARG_PARAM2 = "newTitle";
    private static final String ARG_PARAM3 = "post_id";

    private String imageLink;
    private String newTitle;
    private int post_id;

    public SlideFragment() {
        // Required empty public constructor
    }

    public static SlideFragment newInstance(String imageLink, String newTitle, int post_id) {
        SlideFragment fragment = new SlideFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, imageLink);
        args.putString(ARG_PARAM2, newTitle);
        args.putInt(ARG_PARAM3, post_id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            imageLink = getArguments().getString(ARG_PARAM1);
            newTitle = getArguments().getString(ARG_PARAM2);
            post_id = getArguments().getInt(ARG_PARAM3);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_slide, container, false);
        ImageView imageView = view.findViewById(R.id.main_slider_image);
        TextView textView = view.findViewById(R.id.main_slider_text);

        GlideApp.with(getActivity().getApplicationContext()).load(Const.IMG_URL + imageLink)
                .into(imageView);

        FontEmbedder.force(textView, newTitle);

        view.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
        Bundle args = new Bundle();
        args.putInt("post_id", post_id);
        intent.putExtras(args);
        startActivity(intent);
    }

}
