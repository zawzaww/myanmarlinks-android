package com.zawzaw.savethelibrary.service

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v4.app.NotificationCompat
import android.support.v4.app.TaskStackBuilder
import android.util.Log
import android.media.RingtoneManager

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.zawzaw.savethelibrary.MainActivity
import com.zawzaw.savethelibrary.R

/**
 * Created by zawzaw on 30/01/18.
 */

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        // ...
        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage!!.from!!)

        val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val resultIntent = Intent(this, MainActivity::class.java)
        resultIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        val stackBuilder = TaskStackBuilder.create(this)
        stackBuilder.addParentStack(MainActivity::class.java)
        stackBuilder.addNextIntent(resultIntent)

        val resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)

        val mBuilder = NotificationCompat.Builder(this)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.savethelibrary_noti)
                .setContentTitle(remoteMessage.data["title"])
                .setSound(uri)
                .setAutoCancel(true)
                .setContentText(remoteMessage.data["body"])
                .setContentIntent(resultPendingIntent)

        val mNotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        mNotificationManager.notify(1, mBuilder.build())
    }

    companion object {

        val TAG = MyFirebaseMessagingService::class.java.simpleName
    }

}
