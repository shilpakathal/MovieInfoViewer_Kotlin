package com.shilpa.codecheck.repository.network

import com.shilpa.codecheck.repository.model.MovieInfo

import retrofit2.Call
import retrofit2.http.GET

import com.shilpa.codecheck.common.Constants.QUERY_MOVIES

interface ApiInterface {

    @get:GET(QUERY_MOVIES)
    val movies: Call<List<MovieInfo>>
}
