package com.example.retrofitgoogleads.googleadsretrofit.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Singleton
object RetrofitClient {
const val myDomain="https://api.cricapi.com/"

    val apiService:GoogleApiService
        get() {
            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(myDomain)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GoogleApiService::class.java)
        }
    private val okHttpClient:OkHttpClient
        get() {
            return OkHttpClient.Builder()
                .connectTimeout(20,TimeUnit.SECONDS)
                .build()
        }

}