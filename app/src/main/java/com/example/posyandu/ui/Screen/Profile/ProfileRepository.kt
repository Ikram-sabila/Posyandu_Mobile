package com.example.posyandu.ui.Screen.Profile

import com.example.posyandu.Data.Model.Request.PortalProfileRequest
import com.example.posyandu.Data.Model.Request.UpdateEmailRequest
import com.example.posyandu.Data.Model.Request.UpdatePasswordRequest
import com.example.posyandu.Data.Model.Response.PortalProfileResponseData
import com.example.posyandu.Data.Model.Response.UpdateEmailResponse
import com.example.posyandu.Data.Model.Response.UpdatePasswordResponse
import com.example.posyandu.Data.Model.Response.UserEmailResponse
import com.example.posyandu.Data.Model.Response.WargaResponse
import com.example.posyandu.Data.Remote.Client.ApiClient
import com.google.gson.Gson
import org.json.JSONObject

class ProfileRepository {
    suspend fun updateProfile(request: PortalProfileRequest, id: Int ,token: String): Result<PortalProfileResponseData> {
        return try {
            val response = ApiClient.apiService.updateProfile("Bearer $token", id, request)
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null && body.status == "success") {
                    Result.success(body.data!!)
                } else {
                    Result.failure(Exception(body?.message ?: "Unknown error"))
                }
            } else {
                val errorMsg = response.errorBody()?.string() ?: "Request failed"
                Result.failure(Exception(errorMsg))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun updateEmail(request: UpdateEmailRequest, id: Int, token: String): Result<UpdateEmailResponse> {
        return try {
            val response = ApiClient.apiService.updateEmail("Bearer $token", id, request)
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null && body.status == "success") {
                    Result.success(body)
                } else {
                    Result.failure(Exception(body?.message ?: "Unknown error"))
                }
            } else {
                val errorJson = response.errorBody()?.string()
                val errorMsg = try {
                    JSONObject(errorJson).getString("message")
                } catch (e: Exception) {
                    "Terjadi kesalahan"
                }
                Result.failure(Exception(errorMsg))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }


    suspend fun updatePassword(request: UpdatePasswordRequest, id: Int, token: String): Result<UpdatePasswordResponse> {
        return try {
            val response = ApiClient.apiService.updatePassword("Bearer $token", id, request)
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Result.success(body)
                } else {
                    Result.failure(Exception(Exception("Body kosong")))
                }
            } else {
                val errorJson = response.errorBody()?.string()
                val errorBody = try {
                    Gson().fromJson(errorJson, UpdatePasswordResponse::class.java)
                } catch (e: Exception) {
                    null
                }

                val errorMessage = errorBody?.message ?: "Terjadi kesalahan"
                Result.failure(Exception(errorMessage))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getProfile(token: String, id: Int): Result<WargaResponse> {
        return try {
            val response = ApiClient.apiService.getProfile("Bearer $token", id)
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Result.success(body)
                } else {
                    Result.failure(Exception(Exception("Body kosong")))
                }
            } else {
                val errorMsg = response.errorBody()?.string() ?: "Request failed"
                Result.failure(Exception(errorMsg))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}