package com.tiki.tikitesting

import android.app.Application

class AppApplication : Application() {
    companion object{
        lateinit var INSTANCE : Application
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }
}