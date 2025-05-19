package com.example.posyandu.Data.Model.Response

data class LoginResponse(
    val access_token: String,
    val expires_in: Int,
    val user: WargaData?
)

data class WargaData(
    val id: Int,
    val nama_lengkap: String,
    val email: String,
    val no_telp: String?,
    val anggota_keluarga_nik: String,
    val keluarga_no_kk: String
)
