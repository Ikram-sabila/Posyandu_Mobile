package com.example.posyandu.Data.Remote.Client

import com.example.posyandu.Data.Remote.Service.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
//    private const val BASE_URL = "http://10.0.2.2:8000/"
    private const val BASE_URL = "http://192.168.171.77:8000/"

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}