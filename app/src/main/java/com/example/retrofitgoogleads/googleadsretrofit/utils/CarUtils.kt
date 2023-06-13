package com.example.retrofitgoogleads.googleadsretrofit.utils

import android.content.Context
import android.net.ConnectivityManager
import java.text.SimpleDateFormat
import java.util.*

object CarUtils {
    // Check internet is available or not
    fun isOnline(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        //null check is for airplane mode
        return netInfo != null && netInfo.isConnected
    }
/*
    fun dateFormatConversion(givenDateTime: String) : String {
        val format = isTheSameYear(givenDateTime)
        val format1 = SimpleDateFormat(SOURCE_DATE_FORMAT, Locale.ENGLISH)
        val format2 = SimpleDateFormat(format, Locale.ENGLISH)
        val date: Date = format1.parse(givenDateTime)
        return format2.format(date)
    }

    private fun isTheSameYear(mDate: String): String {
        val cal = Calendar.getInstance()
        return if (cal.get(Calendar.YEAR) == getYear(mDate)) {
            THIS_YEAR_DATE_FORMAT
        } else {
            DIFF_YEAR_DATE_FORMAT
        }
    }

    private fun getYear(d: String): Int {
        val format1 = SimpleDateFormat(SOURCE_DATE_FORMAT, Locale.ENGLISH)
        val date: Date = format1.parse(d)
        val calendar = Calendar.getInstance()
        calendar.time = date
        return calendar.get(Calendar.YEAR)
    }

 */
}