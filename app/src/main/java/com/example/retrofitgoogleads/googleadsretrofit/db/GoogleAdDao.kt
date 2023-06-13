package com.example.retrofitgoogleads.googleadsretrofit.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.retrofitgoogleads.googleadsretrofit.model.AdsUiModel

@Dao
interface GoogleAdDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAds(userAds: AdsUiModel): Long

    @Query("Select * from adsData")
    suspend fun getAllAds(): List<AdsUiModel>?


}