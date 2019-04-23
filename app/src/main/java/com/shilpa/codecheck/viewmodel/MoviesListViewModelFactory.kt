package com.shilpa.codecheck.viewmodel

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

import com.shilpa.codecheck.repository.network.MovieClientService

class MoviesListViewModelFactory(internal var mApp: Application, internal var mApi: MovieClientService) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MoviesListViewModel::class.java!!)) {
            return MoviesListViewModel(mApp, mApi) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
