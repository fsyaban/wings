package com.fachrul.wings

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WingsApp:Application(){
    override fun onCreate() {
        super.onCreate()
        Thread.setDefaultUncaughtExceptionHandler{ thread, throwable->
            Log.e("Apps Error", "Error System", throwable)
        }
    }
}