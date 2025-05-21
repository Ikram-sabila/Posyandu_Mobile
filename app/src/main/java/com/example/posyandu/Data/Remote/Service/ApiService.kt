package com.example.posyandu.Data.Remote.Service

import androidx.compose.runtime.MutableState
import com.example.posyandu.Data.Model.Request.AnggotaKeluargaRequest
import com.example.posyandu.Data.Model.Request.LoginRequest
import com.example.posyandu.Data.Model.Request.RegisterRequest
import com.example.posyandu.Data.Model.Response.AnggotaResponse
import com.example.posyandu.Data.Model.Response.LoginResponse
import com.example.posyandu.Data.Model.Response.PemeriksaanResponse
import com.example.posyandu.Data.Model.Response.PortalPeriksaResponse
import com.example.posyandu.Data.Model.Response.RegisterResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("api/auth/warga/register")
    suspend fun registerWarga(@Body request: RegisterRequest): Response<RegisterResponse>

    @POST("api/auth/warga/login")
    suspend fun loginWarga(@Body request: LoginRequest): Response<LoginResponse>

    @POST("api/auth/warga/anggota")
    suspend fun anggotaKeluarga(@Body request: AnggotaKeluargaRequest): Response<AnggotaKeluargaRequest>

    @GET("api/auth/warga/anggota/{no_kk}")
    suspend fun getAnggotaKeluarga(
        @Header("Authorization")  bearerToken: String,
        @Path("no_kk") noKk: String
    ): Response<List<AnggotaResponse>>

    @GET("api/auth/warga/show/{wargaId}/{nik}/{tipe}")
    suspend fun getRiwayat(
        @Header("Authorization") Bearer: MutableState<String>,
        @Path("wargaId") wargaId: Int,
        @Path("nik") nik: String,
        @Path("tipe") tipe: String,
        @Header("Accept") accept: String = "application/json"
    ): Response<PortalPeriksaResponse>

    @GET("api/auth/warga/get/{id}")
    suspend fun getPemeriksaan(
        @Header("Authorization") Bearer: String,
        @Path("id") id: Int,
        @Header("Accept") accept: String = "application/json"
    ): PemeriksaanResponse
}