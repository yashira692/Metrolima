package com.example.metrolimago.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    // baseUrl termina en "/" y es el host del archivo
    private const val BASE_URL = "https://raw.githubusercontent.com/"

    val api: MetroApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MetroApiService::class.java)
    }
}
