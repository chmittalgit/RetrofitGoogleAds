package com.example.retrofitgoogleads.googleadsretrofit.service

import com.example.retrofitgoogleads.googleadsretrofit.model.AdsModel
import retrofit2.Response
import retrofit2.http.GET

interface GoogleApiService {

    @GET("v1/series?apikey=5d96e58a-40db-42e3-8bd9-a02881ea9a6d&offset=0")
    suspend fun googleApi():Response<AdsModel?>
}