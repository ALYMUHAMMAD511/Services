package com.example.services

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.widget.Toast

class MyServices : Service() {
    private val serviceId = 1

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "my_channel_11"
            val channel = NotificationChannel(channelId, "Default", NotificationManager.IMPORTANCE_HIGH)
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
            val notification = Notification.Builder(this, channelId).apply {
                setContentTitle("Notification Title")
                setContentText("This is content")
                setSmallIcon(R.drawable.ic_launcher_foreground)
            }.build()
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
//                startForeground(serviceId, notification, FOREGROUND_SERVICE_TYPE_SYSTEM_EXEMPTED)
//            }
            startForeground(serviceId, notification)
        }

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        showMessage()
        return super.onStartCommand(intent, flags, startId)
    }

    private fun showMessage() {
        Handler(Looper.getMainLooper()).postDelayed(
            {
                Toast.makeText(applicationContext, "HELLO!", Toast.LENGTH_SHORT).show()
            },
            5000
        )
    }
}