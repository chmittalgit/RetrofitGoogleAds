package com.example.retrofitgoogleads

import android.app.Application
import com.example.retrofitgoogleads.googleadsretrofit.di.DaggerGoogleAdsApplicationComponent
import com.example.retrofitgoogleads.googleadsretrofit.di.GoogleAdsApplicationComponent

class AdsApplication:Application() {
    lateinit var appComponent: GoogleAdsApplicationComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerGoogleAdsApplicationComponent.factory().create(applicationContext)
    }
}