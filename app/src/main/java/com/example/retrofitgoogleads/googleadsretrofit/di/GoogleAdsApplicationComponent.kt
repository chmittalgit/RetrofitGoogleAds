package com.example.retrofitgoogleads.googleadsretrofit.di

import android.content.Context
import com.example.retrofitgoogleads.AdListActivity
import com.example.retrofitgoogleads.googleadsretrofit.view.AdsListFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class,DatabaseModule::class, ViewModelModule::class])
interface GoogleAdsApplicationComponent {
    fun inject(activity: AdListActivity)
    fun inject(fragment: AdsListFragment)

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Context):GoogleAdsApplicationComponent
    }
}