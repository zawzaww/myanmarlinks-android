package com.zawzaw.savethelibrary.ui

import android.content.Intent
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

import com.zawzaw.savethelibrary.MainActivity
import com.zawzaw.savethelibrary.R
import com.zawzaw.savethelibrary.utils.FontEmbedder

class NoConnectionActivity : AppCompatActivity() {

    internal var mView: View
    internal var returnedActivity: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_no_connection)
        mView = findViewById(R.id.no_connection_activity)
        val args = intent.extras
        returnedActivity = args!!.getString("returned_activity")

        val noInternet = findViewById<TextView>(R.id.text_no_internet)
        FontEmbedder.force(noInternet, getString(R.string.not_internet_connection_mm))

        Snackbar.make(mView, getString(R.string.no_internet_connection), Snackbar.LENGTH_INDEFINITE).setAction("RETRY") {
            var intent: Intent? = null
            if (returnedActivity == "MainActivity") {
                intent = Intent(this@NoConnectionActivity, MainActivity::class.java)
            } else if (returnedActivity == "ListReviewActivity") {
                intent = Intent(this@NoConnectionActivity, ListReviewActivity::class.java)
            } else if (returnedActivity == "ReviewDetailActivity") {
                intent = Intent(this@NoConnectionActivity, ReviewDetailActivity::class.java)
            }
            startActivity(intent)
            finish()
        }.show()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

}
