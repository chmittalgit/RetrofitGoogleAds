package com.example.retrofitgoogleads.googleadsretrofit.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.retrofitgoogleads.googleadsretrofit.viewmodel.GoogleAdsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [NetworkModule::class])
interface ViewModelModule {

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(GoogleAdsViewModel::class)
    fun bindsAlbumListViewModel(viewModel: GoogleAdsViewModel) : ViewModel
}
