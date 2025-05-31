package com.example.posyandu.Data.Remote.Service

import androidx.compose.runtime.MutableState
import com.example.posyandu.Data.Model.Request.AnggotaKeluargaRequest
import com.example.posyandu.Data.Model.Request.DaftarAntrianRequest
import com.example.posyandu.Data.Model.Request.LoginRequest
import com.example.posyandu.Data.Model.Request.PortalProfileAnggotaRequest
import com.example.posyandu.Data.Model.Request.PortalProfileRequest
import com.example.posyandu.Data.Model.Request.RegisterRequest
import com.example.posyandu.Data.Model.Request.UpdateEmailRequest
import com.example.posyandu.Data.Model.Request.UpdatePasswordRequest
import com.example.posyandu.Data.Model.Response.AnggotaBeritaResponse
import com.example.posyandu.Data.Model.Response.AnggotaResponse
import com.example.posyandu.Data.Model.Response.AnggotaTerdaftarResponse
import com.example.posyandu.Data.Model.Response.Antrian
import com.example.posyandu.Data.Model.Response.BeritaDetailResponse
import com.example.posyandu.Data.Model.Response.DaftarAntrianResponse
import com.example.posyandu.Data.Model.Response.DataResponse
import com.example.posyandu.Data.Model.Response.LoginResponse
import com.example.posyandu.Data.Model.Response.PemeriksaanResponse
import com.example.posyandu.Data.Model.Response.PortalBeritaResponse
import com.example.posyandu.Data.Model.Response.PortalEkmsResponse
import com.example.posyandu.Data.Model.Response.PortalPeriksaResponse
import com.example.posyandu.Data.Model.Response.PortalProfileResponse
import com.example.posyandu.Data.Model.Response.PosyanduDetailResponse
import com.example.posyandu.Data.Model.Response.PosyanduItem
import com.example.posyandu.Data.Model.Response.PosyanduResponse
import com.example.posyandu.Data.Model.Response.RegisterResponse
import com.example.posyandu.Data.Model.Response.UpdateEmailResponse
import com.example.posyandu.Data.Model.Response.UpdatePasswordResponse
import com.example.posyandu.Data.Model.Response.WargaResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("api/auth/warga/register")
    suspend fun registerWarga(@Body request: RegisterRequest): Response<RegisterResponse>

    @GET("api/auth/warga/posko")
    suspend fun getPosko(): Response<PosyanduResponse<List<PosyanduItem>>>

    @POST("api/auth/warga/login")
    suspend fun loginWarga(@Body request: LoginRequest): Response<LoginResponse>

    @POST("api/auth/warga/anggota")
    suspend fun anggotaKeluarga(@Body request: AnggotaKeluargaRequest): Response<AnggotaKeluargaRequest>

    @GET("api/auth/warga/anggota/{no_kk}")
    suspend fun getAnggotaKeluarga(
        @Header("Authorization")  bearerToken: String,
        @Path("no_kk") noKk: String
    ): Response<List<AnggotaResponse>>

    @PATCH("api/auth/warga/update-profil/{id}")
    suspend fun updateProfile(
        @Header("Authorization")  bearerToken: String,
        @Path("id") id: Int,
        @Body request: PortalProfileRequest
    ): Response<PortalProfileResponse>

    @GET("api/auth/warga/update-profil/{id}")
    suspend fun getProfile(
        @Header("Authorization")  bearerToken: String,
        @Path("id") id: Int
    ): Response<WargaResponse>

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

    @GET("api/auth/warga/berita/{no_kk}")
    suspend fun getBerita(
        @Header("Authorization") Bearer: String,
        @Path("no_kk") id: String?,
        @Header("Accept") accept: String = "application/json"
    ): PortalBeritaResponse

    @GET("api/auth/warga/berita-detail/{berita_id}")
    suspend fun getBeritaDetail(
        @Header("Authorization") Bearer: String,
        @Path("berita_id") id: Int,
        @Header("Accept") accept: String = "application/json"
    ): BeritaDetailResponse

    @GET("api/auth/warga/berita-antrian/{berita_id}")
    suspend fun getBeritaAntrian(
        @Header("Authorization") Bearer: String,
        @Path("berita_id") id: Int,
        @Header("Accept") accept: String = "application/json"
    ): Antrian

    @POST("api/auth/warga/berita-daftar")
    suspend fun daftarAntrian(
        @Header("Authorization") Bearer: String,
        @Body request: DaftarAntrianRequest
    ): DaftarAntrianResponse

    @GET("api/auth/warga/berita-anggota/{no_kk}")
    suspend fun getAnggotaBerita(
        @Header("Authorization") Bearer: String,
        @Path("no_kk") id: String?,
        @Header("Accept") accept: String = "application/json"
    ): AnggotaBeritaResponse

    @GET("api/auth/warga/berita-detail/{acara_id}/{warga_id}")
    suspend fun getAnggotaTerdaftar(
        @Header("Authorization") Bearer: String,
        @Path("acara_id") acaraId: Int?,
        @Path("warga_id") wargaId: Int?,
        @Header("Accept") accept: String = "application/json"
    ): AnggotaTerdaftarResponse

    @PATCH("api/auth/warga/update-email/{id}")
    suspend fun updateEmail(
        @Header("Authorization") authorization: String,
        @Path("id") id: Int,
        @Body body: UpdateEmailRequest,
        @Header("Accept") accept: String = "application/json"
    ): Response<UpdateEmailResponse>

    @PATCH("api/auth/warga/update-password/{id}")
    suspend fun updatePassword(
        @Header("Authorization") authorization: String,
        @Path("id") id: Int,
        @Body body: UpdatePasswordRequest,
        @Header("Accept") accept: String = "application/json"
    ): Response<UpdatePasswordResponse>

    @GET("api/auth/warga/ekms/data/{nik}/{definisi}")
    suspend fun getEkms(
        @Header("Authorization") authorization: String,
        @Path("nik") nik: String,
        @Path("definisi") definisi: String,
        @Header("Accept") accept: String = "application/json"
    ): Response<DataResponse>

    @GET("api/auth/warga/ekms/data-diri/{nik}")
    suspend fun getDataDiriEkms(
        @Header("Authorization") authorization: String,
        @Path("nik") nik: String,
        @Header("Accept") accept: String = "application/json"
    ): Response<PortalEkmsResponse>

    @GET("api/auth/warga/ekms/data/{nik}")
    suspend fun getPerkembangan(
        @Header("Authorization") authorization: String,
        @Path("nik") nik: String,
        @Header("Accept") accept: String = "application/json"
    ): Response<DataResponse>

    @PATCH("api/auth/warga/profil/anggota/{nik}")
    suspend fun updateProfileAnggota(
        @Header("Authorization")  bearerToken: String,
        @Path("nik") nik: String,
        @Body request: PortalProfileAnggotaRequest
    ): Response<PortalProfileResponse>

    @GET("api/auth/warga/profil/anggota/{nik}")
    suspend fun getProfileAnggota(
        @Header("Authorization")  bearerToken: String,
        @Path("nik") nik: String
    ): Response<WargaResponse>

    @GET("api/auth/warga/posyandu/{no_kk}")
    suspend fun getPosyandu(
        @Header("Authorization")  bearerToken: String,
        @Path("no_kk") no_kk: String
    ): Response<PosyanduDetailResponse>
}