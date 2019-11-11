package com.game.bfinder

import android.app.Application
import com.google.firebase.FirebaseApp
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        BuildConfig.DEBUG.takeIf { true }.apply { Timber.plant(Timber.DebugTree()) }
    }
}