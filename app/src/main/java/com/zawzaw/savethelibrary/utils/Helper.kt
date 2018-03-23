package com.zawzaw.savethelibrary.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

/**
 * Created by zawzaw on 07/01/18.
 */

object Helper {

    fun checkInternetConnection(mContext: Context): Boolean {

        val conMgr = mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val i = conMgr.activeNetworkInfo ?: return false

        if (!i.isConnected)
            return false
        return if (!i.isAvailable) false else true
    }

}
