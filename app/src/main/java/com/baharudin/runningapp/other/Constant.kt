package com.baharudin.runningapp.other

import android.graphics.Color

object Constant {
    const val RUNNING_DATABASE_NAME = "running_db"
    const val REQUEST_CODE_INT = 0

    const val ACTION_START_OR_RESUME ="ACTION_START_OR_RESUME"
    const val ACTION_PAUSE ="ACTION_PAUSED"
    const val ACTION_STOP ="ACTION_STOOPED"
    const val ACTION_SHOW_TRACKING_FRAGMENT = "ACTION_SHOW_TRACKING_FRAGMENT"

    const val LOCATION_UPDATE_INTERVAL = 5000L
    const val FASTEST_INTERVAL = 2000L

    const val POLYLINE_COLOR = Color.GREEN
    const val POLYLINE_WIDTH = 15f
    const val MAPS_ZOOM = 15f
    const val TIMER_UPDATE_INTERVAL = 50L

    const val NOTIFICATION_CHANNEL_ID ="tracking_channel"
    const val NOTIFICATION_CHANNEL_NAME ="Tracking"
    const val NOTIFICATION_ID = 1
}