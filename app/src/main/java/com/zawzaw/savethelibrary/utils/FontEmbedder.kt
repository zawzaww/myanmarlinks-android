package com.zawzaw.savethelibrary.utils

import android.graphics.Typeface
import android.support.design.widget.CollapsingToolbarLayout
import android.text.Editable
import android.text.Spannable
import android.text.SpannableString
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.view.SubMenu
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.SearchView
import android.widget.TextView
import me.myatminsoe.mdetect.MDetect
import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter
import org.sufficientlysecure.htmltextview.HtmlTextView
import com.zawzaw.savethelibrary.MainApplication

/**
 * Created by zawzaw on 01/01/18.
 */

object FontEmbedder {

    private var typeface: Typeface? = null

    fun init(typeface: Typeface) {
        FontEmbedder.typeface = typeface
    }

    @JvmOverloads
    fun force(textView: TextView, text: String = textView.text.toString()) {
        textView.text = Moulder.mercyOnZgUser(text)
        textView.typeface = typeface
    }

    fun forceTitle(textView: TextView, text: String) {
        textView.text = Moulder.mercyOnZgUser(text)

        if (MDetect.isUnicode()) {
            textView.typeface = MainApplication.typefaceManager.myanmarSager
        } else {
            textView.typeface = typeface
        }

    }

    fun force(view: View, text: String, htmlGetter: HtmlHttpImageGetter) {
        force(view as TextView, text)
    }

    fun force(view: View) {
        force(view as TextView)
    }

    @JvmOverloads
    fun force(button: Button, text: String = button.text.toString()) {
        button.text = Moulder.mercyOnZgUser(text)
        button.typeface = typeface
    }

    fun force(htmlTextView: HtmlTextView, text: String, imageGetter: HtmlHttpImageGetter) {
        htmlTextView.typeface = typeface
        htmlTextView.setHtml(Moulder.mercyOnZgUser(text), imageGetter)
    }

    fun force(htmlTextView: HtmlTextView, text: String) {
        htmlTextView.typeface = typeface
        htmlTextView.setHtml(Moulder.mercyOnZgUser(text))
    }

    fun force(editText: EditText, text: String, hint: String) {
        editText.setText(Moulder.mercyOnZgUser(text))
        editText.hint = Moulder.mercyOnZgUser(hint)
        editText.typeface = typeface

        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.length > 0) {
                    editText.typeface = null
                } else {
                    editText.typeface = typeface
                }
            }

            override fun afterTextChanged(s: Editable) {

            }
        })
    }

    @JvmOverloads
    fun force(checkBox: CheckBox, text: String = checkBox.text.toString()) {
        checkBox.text = Moulder.mercyOnZgUser(text)
        checkBox.typeface = typeface
    }

    fun force(collapsingToolbarLayout: CollapsingToolbarLayout, text: String) {
        collapsingToolbarLayout.setCollapsedTitleTypeface(typeface)
        collapsingToolbarLayout.setExpandedTitleTypeface(typeface)
        collapsingToolbarLayout.title = Moulder.mercyOnZgUser(text)
    }

    fun force(searchView: SearchView) {
        val searchText = searchView.findViewById<View>(android.support.v7.appcompat.R.id.search_src_text) as TextView
        FontEmbedder.force(searchText, "Search...")
        searchText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.length > 0) {
                    searchText.typeface = null
                } else {
                    searchText.typeface = typeface
                }
            }

            override fun afterTextChanged(s: Editable) {

            }
        })
    }

    fun force(mi: MenuItem) {
        val mNewTitle = SpannableString(Moulder.mercyOnZgUser(mi.title.toString()))
        mNewTitle.setSpan(CustomTypefaceSpan("", typeface), 0, mNewTitle.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        mi.title = mNewTitle
    }

    fun force(m: Menu) {

        for (i in 0 until m.size()) {
            val mi = m.getItem(i)
            val subMenu = mi.subMenu

            if (subMenu != null && subMenu.size() > 0) {
                for (j in 0 until subMenu.size()) {
                    val subMenuItem = subMenu.getItem(j)
                    force(subMenuItem)
                }
            }
            force(mi)
        }
    }

}
