package com.android.shop.arena.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getString
import com.android.shop.arena.MainActivity
import com.android.shop.arena.R



fun notificationChannel(context: Context){

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
        val name = "Orders"
        //val description = "Notification for orders placed"
        val importance = NotificationManager.IMPORTANCE_HIGH

        val channel = NotificationChannel(
            Constants.ORDER_CHANNEL_ID,
            name,
            importance
        )
         val notificationManager : NotificationManager = context.getSystemService(
             Context.NOTIFICATION_SERVICE
         ) as NotificationManager

        notificationManager.createNotificationChannel(channel)
    }
}

fun showNotification(context: Context){


        val builder = NotificationCompat.Builder(context, Constants.ORDER_CHANNEL_ID)
        .setSmallIcon(R.drawable.arena_logo)
        .setContentTitle("Order")
        .setContentText("Order Received")
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setAutoCancel(true)
        .setDefaults(NotificationCompat.DEFAULT_ALL)
        .build()

    val notificationId = System.currentTimeMillis().toInt()

    with(NotificationManagerCompat.from(context)){
        notify(notificationId, builder)
    }
}