package com.shilpa.codecheck.repository.network

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log

import com.shilpa.codecheck.repository.model.MovieInfo

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieClientService : MovieClientInterface {
    private val moviesData: MutableLiveData<List<MovieInfo>>

    override val movies: LiveData<List<MovieInfo>>
        get() {

            val apiInterface = ApiClient.retrofitInstance?.create(ApiInterface::class.java!!)

            apiInterface?.movies?.enqueue(object : Callback<List<MovieInfo>> {
                override fun onResponse(call: Call<List<MovieInfo>>, response: Response<List<MovieInfo>>) {
                    if (isResponseValid(response)) {
                        moviesData.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<List<MovieInfo>>, t: Throwable) {
                    Log.e("MoviewClientService", "Retrofit response failed!")
                }
            })

            return moviesData
        }

    init {
        this.moviesData = MutableLiveData()
    }

    private fun isResponseValid(response: Response<List<MovieInfo>>): Boolean {
        if (response.isSuccessful) {
            if (response.body() != null)
                return true
        }
        return false
    }
}
