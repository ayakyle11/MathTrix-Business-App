package com.mtcdb.stem.mathtrix

import android.app.Application

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Logger.initialize(this)
		MLogger.initialize(this)
    }      
}
