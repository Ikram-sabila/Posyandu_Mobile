package com.example.posyandu.Data.Model.Request

data class RegisterRequest(
    val nama_lengkap: String,
    val email: String,
    val password: String,
    val no_telp: String,
    val nik: String,
    val posyandu: Int,
    val no_kk: String,
    val jenis_kelamin: String,
    val tanggal_lahir: String
)