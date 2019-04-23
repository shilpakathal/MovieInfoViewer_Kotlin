package com.shilpa.codecheck.repository.network

import android.arch.lifecycle.LiveData
import com.shilpa.codecheck.repository.model.MovieInfo

interface MovieClientInterface {
    val movies: LiveData<List<MovieInfo>>
}
