package com.example.posyandu.Data.Model.Response

data class PortalProfileResponse (
    val status: String,
    val message: String,
    val data: PortalProfileResponseData?
)

data class PortalProfileResponseData(
    val id: Int,
    val nama_lengkap: String?,
    val no_telp: String?,
    val nik: String?,
    val tanggal_lahir: String?,
    val jenis_kelamin: String?
)

data class UpdateEmailResponse(
    val status: String,
    val message: String,
    val data: UserEmailResponse? = null,
    val errors: Map<String, List<String>>? = null
)

data class UserEmailResponse(
    val id: Int,
    val email: String,
    val nama_lengkap: String?
)

data class WargaResponse(
    val nama_lengkap: String?,
    val no_telp: String?,
    val nik: String?,
    val tanggal_lahir: String?,
    val jenis_kelamin: String?
)

data class UpdatePasswordResponse(
    val message: String
)