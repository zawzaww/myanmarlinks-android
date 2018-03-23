package com.zawzaw.savethelibrary.service

import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService

/**
 * Created by zawzaw on 30/01/18.
 */

class FirebaseIdService : FirebaseInstanceIdService() {

    override fun onTokenRefresh() {
        val refreshToken = FirebaseInstanceId.getInstance().token
        Log.i("TOKEN", refreshToken)
    }

}
