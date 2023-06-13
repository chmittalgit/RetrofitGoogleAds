package com.example.retrofitgoogleads.googleadsretrofit.di

import android.content.Context
import com.example.retrofitgoogleads.googleadsretrofit.db.GoogleAdDao
import com.example.retrofitgoogleads.googleadsretrofit.db.GoogleAdDatabase
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {
    @Provides
    fun provideGoogleAdDbDao(context: Context):GoogleAdDao{
        return GoogleAdDatabase.getDbInstance(context).googleDao()
    }
}