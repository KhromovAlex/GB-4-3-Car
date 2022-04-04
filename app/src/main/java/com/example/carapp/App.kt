package com.example.carapp

import android.app.Application
import com.example.carapp.di.DI
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                DI.getModule()
            )
        }
    }
}
