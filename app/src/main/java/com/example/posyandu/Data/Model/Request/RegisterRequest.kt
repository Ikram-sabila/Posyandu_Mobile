package com.example.posyandu.Data.Model.Request

import java.time.LocalDate

data class RegisterRequest (
    val nama_lengkap: String,
    val email: String,
    val password: String,
    val no_telp: String,
    val nik: String,
    val no_kk: String,
    val jenis_kelamin: String,
    val tanggal_lahir: String
)