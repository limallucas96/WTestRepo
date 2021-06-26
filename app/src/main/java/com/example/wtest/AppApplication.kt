package com.example.wtest

import android.app.Application
import com.example.wtest.di.applicationModules
import com.facebook.stetho.Stetho
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setupStetho()
        setupKoin()
    }

    private fun setupStetho() {
        Stetho.initializeWithDefaults(this)
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@AppApplication)
            modules(applicationModules)
        }
    }

}