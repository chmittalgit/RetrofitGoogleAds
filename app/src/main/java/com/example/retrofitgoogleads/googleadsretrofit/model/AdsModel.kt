package com.example.retrofitgoogleads.googleadsretrofit.model


data class AdsModel(
    val apikey: String,
    val `data`: List<Data>,
    val info: Info,
    val status: String
)

data class Data(
    val endDate: String,
    val id: String,
    val matches: Int,
    val name: String,
    val odi: Int,
    val squads: Int,
    val startDate: String,
    val t20: Int,
    val test: Int
)

data class Info(
    val cache: Int,
    val credits: Int,
    val hitsLimit: Int,
    val hitsToday: Int,
    val hitsUsed: Int,
    val offsetRows: Int,
    val queryTime: Double,
    val s: Int,
    val server: Int,
    val totalRows: Int
)