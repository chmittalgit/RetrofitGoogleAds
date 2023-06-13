package com.example.retrofitgoogleads.googleadsretrofit.di

import com.example.retrofitgoogleads.googleadsretrofit.service.GoogleApiService
import com.example.retrofitgoogleads.googleadsretrofit.service.RetrofitClient
import dagger.Module
import dagger.Provides
@Module
class NetworkModule {
    @Provides
    fun provideGoogleApiService():GoogleApiService{
        return RetrofitClient.apiService
    }
}