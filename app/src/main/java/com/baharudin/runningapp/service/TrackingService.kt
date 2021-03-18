package com.baharudin.runningapp.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_LOW
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LifecycleService
import com.baharudin.runningapp.R
import com.baharudin.runningapp.other.Constant.ACTION_PAUSE
import com.baharudin.runningapp.other.Constant.ACTION_SHOW_TRACKING_FRAGMENT
import com.baharudin.runningapp.other.Constant.ACTION_START_OR_RESUME
import com.baharudin.runningapp.other.Constant.ACTION_STOP
import com.baharudin.runningapp.other.Constant.NOTIFICATION_CHANNEL_ID
import com.baharudin.runningapp.other.Constant.NOTIFICATION_CHANNEL_NAME
import com.baharudin.runningapp.other.Constant.NOTIFICATION_ID
import com.baharudin.runningapp.ui.MainActivity
import timber.log.Timber

class TrackingService : LifecycleService() {

    var isFirstRun = true

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {


        intent?.let {
            when(it.action){
                ACTION_START_OR_RESUME -> {
                    if (isFirstRun){
                        startForegrondService()
                        isFirstRun = false
                    }else {
                        Timber.d("Action is resuming")
                    }

                }

                ACTION_PAUSE -> {
                    Timber.d("Action is paused")
                }

                ACTION_STOP -> {
                    Timber.d("Action is stopped")
                }
            }
        }

        return super.onStartCommand(intent, flags, startId)

    }
    //menjalankan apliaksi di foregrond
    private fun startForegrondService(){
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE)
        as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            createNotificationChannel(notificationManager)
        }
        val notificationBuilder = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
            .setAutoCancel(false)
            .setOngoing(true)
            .setSmallIcon(R.drawable.ic_run)
            .setContentTitle("Running App")
            .setContentText("00:00:00")
            .setContentIntent(getMainActivityPendingIntent())

        startForeground(NOTIFICATION_ID,notificationBuilder.build())
    }
    private fun getMainActivityPendingIntent() = PendingIntent.getActivity(
        this,
        0,
        Intent(this,MainActivity::class.java).also {
            it.action = ACTION_SHOW_TRACKING_FRAGMENT
        },
        FLAG_UPDATE_CURRENT
    )

    //membuat notifikasi
    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(notificationManager: NotificationManager){
            val channel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                NOTIFICATION_CHANNEL_NAME,
                IMPORTANCE_LOW
                )
        notificationManager.createNotificationChannel(channel)
    }
}