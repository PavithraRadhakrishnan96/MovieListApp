package com.example.sampleapp.movielist.viewmodel

import androidx.lifecycle.ViewModel
import com.example.sampleapp.movielist.api.DaggerViewModelInjector
import com.example.sampleapp.movielist.api.NetworkModule
import com.example.sampleapp.movielist.api.ViewModelInjector

abstract class BaseViewModel:ViewModel() {
    private val injector: ViewModelInjector = DaggerViewModelInjector.builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }


    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is MovieViewModel -> injector.inject(this)
        }
    }


}