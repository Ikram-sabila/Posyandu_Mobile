package com.example.posyandu.Data.Model.Request

class PortalProfileRequest(
    val nama_lengkap: String?,
    val no_telp: String?,
    val nik: String?,
    val tanggal_lahir: String?,
    val jenis_kelamin: String?
)

data class UpdateEmailRequest(
    val email_baru: String,
    val email_sekarang: String,
    val password: String
)

data class UpdatePasswordRequest(
    val password_sekarang: String,
    val password_baru: String
)

class PortalProfileAnggotaRequest(
    val nama_lengkap: String?,
    val no_telp: String?,
    val nik: String?,
    val posisi_keluarga: String?,
    val tanggal_lahir: String?,
    val jenis_kelamin: String?
)