package com.example.retrofitgoogleads.googleadsretrofit.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitgoogleads.googleadsretrofit.model.AdsModel
import com.example.retrofitgoogleads.googleadsretrofit.model.AdsUiModel
import com.example.retrofitgoogleads.googleadsretrofit.repository.ApiFailed
import com.example.retrofitgoogleads.googleadsretrofit.repository.ApiSuccess
import com.example.retrofitgoogleads.googleadsretrofit.repository.GoogleApiRepository
import com.example.retrofitgoogleads.googleadsretrofit.repository.SocketTimeout
import kotlinx.coroutines.launch
import javax.inject.Inject

class GoogleAdsViewModel @Inject constructor(private val repo:GoogleApiRepository):ViewModel() {
   /* //online data show krne
    fun fetchListDataRemotely() {
        viewModelScope.launch {
            when (val responseStatus = repo.apiListOfData()) {
                is ApiSuccess -> {
                    _notesLiveData.value = responseStatus.adsData?: listOf()
                }
                ApiFailed -> {
                    _notesLiveData.value = null
                }
                SocketTimeout -> {
                    _notesLiveData.value = null
                }
            }
        }
    }
   */
    private var _adListLiveData = MutableLiveData<List<AdsUiModel>>()
    val adListLiveData: LiveData<List<AdsUiModel>> = _adListLiveData

    private var _progressLiveData = MutableLiveData<Boolean>()
    val progressLiveData: LiveData<Boolean> = _progressLiveData

    private var _isAdssUpdatedLiveData = MutableLiveData<Boolean>()
    val isAdssUpdatedLiveData: LiveData<Boolean> = _isAdssUpdatedLiveData

    //liveData observe karne ke liye
    private var _productLiveData = MutableLiveData<AdsUiModel>()
    val productLiveData: LiveData<AdsUiModel> = _productLiveData

    private var _adssLiveData = MutableLiveData<List<AdsUiModel>?>()
    val adssLiveData: LiveData<List<AdsUiModel>?> = _adssLiveData

    fun getAllAdssLocally() {
        viewModelScope.launch {
            _adListLiveData.value = repo.getAllAdss()
        }
    }

    fun setProgressVisibility(isProgress: Boolean) {
        _progressLiveData.value = isProgress
    }

    fun fetchAdsRemotely() {
        viewModelScope.launch {
            when(val responseStatus = repo.downloadListOfAdss()) {
                is ApiSuccess -> {
                   // repo.saveCarsInDb(responseStatus.adsData ?: listOf())
                    _adssLiveData.value = responseStatus.adsData?: listOf()
                  //  _isCarsUpdatedLiveData.value = true
                }
                ApiFailed -> {
                    _adssLiveData.value = null
                }
                SocketTimeout -> {
                    _adssLiveData.value = null
                }
            }
        }
    }
}