package com.example.retrofitgoogleads.googleadsretrofit.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.retrofitgoogleads.googleadsretrofit.model.AdsUiModel

@Database(entities = [AdsUiModel::class], version = 1, exportSchema = false)
abstract class GoogleAdDatabase: RoomDatabase() {
    abstract fun googleDao():GoogleAdDao
    companion object {
        @Volatile
        private var INSTANCE: GoogleAdDatabase? = null

        fun getDbInstance(
            context: Context
        ): GoogleAdDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    GoogleAdDatabase::class.java,
                    "GoogleAdDb"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}