package com.brian_big.gadsleaderboard.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object FormClientInstance{
    private var retrofit: Retrofit? = null
    private const val BASE_URL = "https://docs.google.com/forms/d/e/"
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