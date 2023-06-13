package com.example.retrofitgoogleads.googleadsretrofit.repository

import com.example.retrofitgoogleads.googleadsretrofit.model.AdsModel
import com.example.retrofitgoogleads.googleadsretrofit.model.AdsUiModel
import javax.inject.Inject

// This is responsible for converting api data to ui data
class AdsDataUseCase @Inject constructor() : DataConvertor<AdsModel, AdsUiModel> {
    override fun intoUiModel(dataClass: AdsModel?): List<AdsUiModel> {
        val listOfCar = mutableListOf<AdsUiModel>()
        if (dataClass != null) {
            for (ads in dataClass.data) {
                listOfCar.add(AdsUiModel(
                    id = ads.id,
                    startDate = ads.startDate,
                    endDate = ads.endDate,
                    name = ads.name,
                    matches = ads.matches
                ))
            }
        }
        return listOfCar
    }
}