package com.mtcdb.stem.mathtrix

import android.app.Application

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
		//Enable it for App Logs
        //Logger.initialize(this)
		MLogger.initialize(this)
    }      
}
