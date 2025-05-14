package com.example.posyandu.Data.Remote.Service

import com.example.posyandu.Data.Model.Request.LoginRequest
import com.example.posyandu.Data.Model.Request.RegisterRequest
import com.example.posyandu.Data.Model.Response.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("api/auth/warga/register")
    suspend fun registerWarga(@Body request: RegisterRequest): Response<RegisterResponse>

    @POST("api/auth/warga/login")
    suspend fun loginWarga(@Body request: LoginRequest): Response<LoginRequest>
}