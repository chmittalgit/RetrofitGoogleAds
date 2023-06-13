package com.example.retrofitgoogleads.googleadsretrofit.repository

// Generic data convertor interface
// K is API MODEL and T is UI DATA MODEL
interface DataConvertor<K, T> {
    fun intoUiModel(dataClass: K?): List<T>
}
