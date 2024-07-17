package com.android.shop.arena.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.android.shop.arena.MainActivity
import com.android.shop.arena.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FirebasePushNotification : FirebaseMessagingService() {


    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

            message.notification?.let {
                Log.d("FirebasePushNotification", "Message Notification Body: ${it.body}")
                showNotification(this, it.title!!, it.body!!)
            }

    }


    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("FirebasePushNotification", "New token: $token")
        // TODO: Send token to your server for keeping user's device token updated
    }




}