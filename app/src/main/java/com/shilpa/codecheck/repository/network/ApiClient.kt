package com.shilpa.codecheck.repository.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.shilpa.codecheck.common.Constants

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private var retrofit: Retrofit? = null

    val retrofitInstance: Retrofit?
        get() {
            if (retrofit == null) {
                val gson = GsonBuilder()
                        .setLenient()
                        .create()
                retrofit = retrofit2.Retrofit.Builder()
                        .baseUrl(Constants.APP_BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build()
            }
            return retrofit
        }
}

