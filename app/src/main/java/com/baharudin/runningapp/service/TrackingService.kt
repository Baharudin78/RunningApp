package com.baharudin.runningapp.service

import android.content.Intent
import androidx.lifecycle.LifecycleService
import com.baharudin.runningapp.other.Constant.ACTION_PAUSE
import com.baharudin.runningapp.other.Constant.ACTION_START_OR_RESUME
import com.baharudin.runningapp.other.Constant.ACTION_STOP
import timber.log.Timber

class TrackingService : LifecycleService() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {


        intent?.let {
            when(it.action){
                ACTION_START_OR_RESUME -> {
                    Timber.d("Action is started")
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
}