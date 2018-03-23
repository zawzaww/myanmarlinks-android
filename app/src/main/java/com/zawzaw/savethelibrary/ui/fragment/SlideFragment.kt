package com.zawzaw.savethelibrary.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.zawzaw.savethelibrary.R
import com.zawzaw.savethelibrary.ui.NewsDetailActivity
import com.zawzaw.savethelibrary.utils.Const
import com.zawzaw.savethelibrary.utils.FontEmbedder
import com.zawzaw.savethelibrary.utils.GlideApp

/**
 * A simple [Fragment] subclass.
 * Use the [SlideFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class SlideFragment : Fragment(), View.OnClickListener {

    private var imageLink: String? = null
    private var newTitle: String? = null
    private var post_id: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            imageLink = arguments!!.getString(ARG_PARAM1)
            newTitle = arguments!!.getString(ARG_PARAM2)
            post_id = arguments!!.getInt(ARG_PARAM3)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_slide, container, false)
        val imageView = view.findViewById<ImageView>(R.id.main_slider_image)
        val textView = view.findViewById<TextView>(R.id.main_slider_text)

        GlideApp.with(activity!!.applicationContext).load(Const.IMG_URL + imageLink!!)
                .into(imageView)

        FontEmbedder.force(textView, newTitle)

        view.setOnClickListener(this)

        return view
    }

    override fun onClick(v: View) {
        val intent = Intent(activity, NewsDetailActivity::class.java)
        val args = Bundle()
        args.putInt("post_id", post_id)
        intent.putExtras(args)
        startActivity(intent)
    }

    companion object {

        private val ARG_PARAM1 = "imageLink"
        private val ARG_PARAM2 = "newTitle"
        private val ARG_PARAM3 = "post_id"

        fun newInstance(imageLink: String, newTitle: String, post_id: Int): SlideFragment {
            val fragment = SlideFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, imageLink)
            args.putString(ARG_PARAM2, newTitle)
            args.putInt(ARG_PARAM3, post_id)
            fragment.arguments = args
            return fragment
        }
    }

}// Required empty public constructor
