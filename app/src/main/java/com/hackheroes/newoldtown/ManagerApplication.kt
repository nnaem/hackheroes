package com.hackheroes.newoldtown

import android.app.Application
import com.hackheroes.newoldtown.di.preferencesModule
import com.hackheroes.newoldtown.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ManagerApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@ManagerApplication)
            modules(preferencesModule, viewModelModule)
        }
    }
}