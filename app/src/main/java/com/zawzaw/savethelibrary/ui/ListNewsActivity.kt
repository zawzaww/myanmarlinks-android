package com.zawzaw.savethelibrary.ui

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.zawzaw.savethelibrary.R

class ListNewsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_news)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace your Own Action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

}
