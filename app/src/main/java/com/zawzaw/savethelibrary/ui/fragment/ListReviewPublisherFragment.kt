package com.zawzaw.savethelibrary.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zawzaw.savethelibrary.R

/**
 * A simple [Fragment] subclass.
 */
class ListReviewPublisherFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_review_publisher, container, false)
    }

}// Required empty public constructor
