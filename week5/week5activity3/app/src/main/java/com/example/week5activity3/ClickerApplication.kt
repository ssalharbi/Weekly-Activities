package com.example.week5activity3

import android.app.Application
import timber.log.Timber

public final class ClickerApplication: Application() {
    override fun onCreate() {
        super.onCreate()
    Timber.plant(Timber.DebugTree())
    }




}