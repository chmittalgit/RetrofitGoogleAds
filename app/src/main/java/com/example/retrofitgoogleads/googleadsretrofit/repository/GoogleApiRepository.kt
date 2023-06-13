package com.example.retrofitgoogleads.googleadsretrofit.repository

import com.example.retrofitgoogleads.googleadsretrofit.db.GoogleAdDao
import com.example.retrofitgoogleads.googleadsretrofit.model.AdsUiModel
import com.example.retrofitgoogleads.googleadsretrofit.service.GoogleApiService
import javax.inject.Inject

class GoogleApiRepository @Inject constructor(
    private val service: GoogleApiService,
    private val dao: GoogleAdDao,
    private val adsDataUseCase: AdsDataUseCase
) {

    suspend fun downloadListOfAdss(): AdsResponse {
        return try {
            val call = service.googleApi()
            if (call.isSuccessful) {
                val adss = adsDataUseCase.intoUiModel(call.body())
                ApiSuccess(adss)
            } else {
                ApiFailed
            }
        } catch (sException: Exception) {
            SocketTimeout
        }
    }

    suspend fun getAllAdss(): List<AdsUiModel> {
        return dao.getAllAds() ?: listOf()
    }

    suspend fun saveAdssInDb(ads: List<AdsUiModel>) {
        for (ad in ads) {
            dao.addAds(ad)
        }
    }


}

sealed class AdsResponse
data class ApiSuccess(val adsData: List<AdsUiModel>?) : AdsResponse()
object ApiFailed : AdsResponse()
object SocketTimeout : AdsResponse()