package com.brian_big.gadsleaderboard.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object LeadersClientInstance {
    private var retrofit: Retrofit? = null
    private const val BASE_URL = "https://gadsapi.herokuapp.com"
    val retrofitInstance: Retrofit?
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }
}