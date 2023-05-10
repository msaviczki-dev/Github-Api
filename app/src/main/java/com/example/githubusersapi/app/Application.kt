package com.example.githubusersapi.app

import android.app.Application
import com.example.githubusersapi.di.ModuleProvider
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class Application : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@Application)
            modules(ModuleProvider().provide())
        }
    }
}