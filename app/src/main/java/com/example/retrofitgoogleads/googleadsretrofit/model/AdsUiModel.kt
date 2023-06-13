package com.example.retrofitgoogleads.googleadsretrofit.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "adsData")
data class AdsUiModel(
    @PrimaryKey
    val id: String,
    val matches: Int,
    val startDate: String,
    val name: String,
    val endDate: String

    ) {
}