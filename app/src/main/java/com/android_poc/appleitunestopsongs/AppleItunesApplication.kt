package com.android_poc.appleitunestopsongs

import android.app.Application
import com.android_poc.appleitunestopsongs.utility.AppSharedPrefRepository

class AppleItunesApplication:Application() {
    companion object {
        private lateinit var appleItunesApplication: AppleItunesApplication
        fun getApplicationInstance(): AppleItunesApplication {
            return appleItunesApplication
        }
    }

    override fun onCreate() {
        super.onCreate()
        appleItunesApplication = this
        AppSharedPrefRepository.getInstance().initPrefrence(this)

    }
}