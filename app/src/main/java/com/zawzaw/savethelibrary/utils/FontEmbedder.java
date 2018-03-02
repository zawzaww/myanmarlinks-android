package com.zawzaw.savethelibrary.utils;

import android.graphics.Typeface;
import android.support.design.widget.CollapsingToolbarLayout;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;
import me.myatminsoe.mdetect.MDetect;
import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter;
import org.sufficientlysecure.htmltextview.HtmlTextView;
import com.zawzaw.savethelibrary.MainApplication;

/**
 * Created by zawzaw on 01/01/18.
 */

public class FontEmbedder {

    private static Typeface typeface;

    public static void init(Typeface typeface){
        FontEmbedder.typeface = typeface;
    }

    public static void force(TextView textView, String text){
        textView.setText(Moulder.mercyOnZgUser(text));
        textView.setTypeface(typeface);
    }

    public static void forceTitle(TextView textView, String text) {
        textView.setText(Moulder.mercyOnZgUser(text));

        if (MDetect.INSTANCE.isUnicode()) {
            textView.setTypeface(MainApplication.typefaceManager.getMyanmarSager());
        } else {
            textView.setTypeface(typeface);
        }

    }

    public static void force(TextView textView) {
        force(textView, textView.getText().toString());
    }

    public static void force(View view, String text, HtmlHttpImageGetter htmlGetter){
        force((TextView) view, text);
    }

    public static void force(View view){
        force((TextView) view);
    }

    public static void force(Button button, String text){
        button.setText(Moulder.mercyOnZgUser(text));
        button.setTypeface(typeface);
    }

    public static void force(HtmlTextView htmlTextView, String text, HtmlHttpImageGetter imageGetter) {
        htmlTextView.setTypeface(typeface);
        htmlTextView.setHtml(Moulder.mercyOnZgUser(text), imageGetter);
    }

    public static void force(HtmlTextView htmlTextView, String text) {
        htmlTextView.setTypeface(typeface);
        htmlTextView.setHtml(Moulder.mercyOnZgUser(text));
    }

    public static void force(Button button){
        force(button, button.getText().toString());
    }

    public static void force(final EditText editText, String text, String hint) {
        editText.setText(Moulder.mercyOnZgUser(text));
        editText.setHint(Moulder.mercyOnZgUser(hint));
        editText.setTypeface(typeface);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    editText.setTypeface(null);
                }else {
                    editText.setTypeface(typeface);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public static void force(CheckBox checkBox, String text){
        checkBox.setText(Moulder.mercyOnZgUser(text));
        checkBox.setTypeface(typeface);
    }

    public static void force(CheckBox checkBox){
        force(checkBox, checkBox.getText().toString());
    }

    public static void force(CollapsingToolbarLayout collapsingToolbarLayout, String text) {
        collapsingToolbarLayout.setCollapsedTitleTypeface(typeface);
        collapsingToolbarLayout.setExpandedTitleTypeface(typeface);
        collapsingToolbarLayout.setTitle(Moulder.mercyOnZgUser(text));
    }

    public static void force(SearchView searchView) {
        final TextView searchText = (TextView) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        FontEmbedder.force(searchText, "Search...");
        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    searchText.setTypeface(null);
                }else {
                    searchText.setTypeface(typeface);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public static void force(MenuItem mi) {
        SpannableString mNewTitle = new SpannableString(Moulder.mercyOnZgUser(mi.getTitle().toString()));
        mNewTitle.setSpan(new CustomTypefaceSpan("", typeface), 0, mNewTitle.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        mi.setTitle(mNewTitle);
    }

    public static void force(Menu m) {

        for (int i=0;i<m.size();i++) {
            MenuItem mi = m.getItem(i);
            SubMenu subMenu = mi.getSubMenu();

            if (subMenu!=null && subMenu.size() >0) {
                for (int j=0; j <subMenu.size();j++) {
                    MenuItem subMenuItem = subMenu.getItem(j);
                    force(subMenuItem);
                }
            }
            force(mi);
        }
    }

}
