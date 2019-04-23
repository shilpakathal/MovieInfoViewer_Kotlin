package com.shilpa.codecheck.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData

import com.shilpa.codecheck.repository.model.MovieInfo
import com.shilpa.codecheck.repository.network.MovieClientService

class MoviesListViewModel(application: Application, private val movieClientService: MovieClientService) : AndroidViewModel(application) {

    val movies: LiveData<List<MovieInfo>>
        get() = movieClientService.movies
}
