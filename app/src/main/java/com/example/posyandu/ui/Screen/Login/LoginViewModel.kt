package com.example.posyandu.ui.Screen.Login

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.posyandu.Data.Local.UserPreferences
import com.example.posyandu.Data.Model.Request.LoginRequest
import com.example.posyandu.Data.Model.Response.WargaData
import com.example.posyandu.Data.Remote.Client.ApiClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    var email = mutableStateOf("")
    var password = mutableStateOf("")

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState

    private val _showErrorToast = MutableStateFlow(false)
    val showErrorToast = _showErrorToast.asStateFlow()

    fun resetErrorToast() {
        _showErrorToast.value = false
    }

    fun login(context: Context) {
        viewModelScope.launch {
            _loginState.value = LoginState.Loading

            try {
                val response = ApiClient.apiService.loginWarga(
                    LoginRequest(email.value, password.value)
                )
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    if (loginResponse != null) {
                        _showErrorToast.value = false
                        val user = loginResponse.user
                        Log.d("LoginResponse", "Body: $loginResponse")
                        Log.d("LoginResponse", "User from response: $user")

                        if (user == null) {
                            Log.e("LoginViewModel", "User null di response")
                            _loginState.value = LoginState.Error("Data user kosong")
                            return@launch // keluar dari coroutine
                        }

                        // Simpan data user ke preferences
                        UserPreferences.saveUserData(
                            context = context,
                            token = "Bearer ${loginResponse.access_token}",
                            nik = user.anggota_keluarga_nik,
                            noKK = user.keluarga_no_kk,
                            userId = user.id,
                            email = user.email,
                            no_telp = user.no_telp,
                            nama = user.nama_lengkap
                        )

                        Log.d("LoginViewModel", "Disimpan ke preferences:")
                        Log.d("LoginViewModel", "Token: Bearer ${loginResponse.access_token}")
                        Log.d("LoginViewModel", "NIK: ${user.anggota_keluarga_nik}")
                        Log.d("LoginViewModel", "No KK: ${user.keluarga_no_kk}")
                        Log.d("LoginViewModel", "User ID: ${user.id}")

                        _loginState.value = LoginState.Success(
                            token = loginResponse.access_token,
                            user = user
                        )
                    } else {
                        _loginState.value = LoginState.Error("Response kosong dari server")
                    }
                } else {
                    val errorBody = response.errorBody()?.string()
                    val errorCode = response.code()
                    Log.e("Login", "Gagal login: $errorCode - $errorBody")

                    _loginState.value = LoginState.Error("Login gagal: $errorCode - $errorBody")
                    _showErrorToast.value = true
                }
            } catch (e: Exception) {
                Log.e("Login", "Exception saat login: ${e.message}", e)
                _loginState.value = LoginState.Error("Terjadi kesalahan: ${e.message}")
                _showErrorToast.value = true
            }
        }
    }
}

sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    data class Success(val token: String, val user: WargaData) : LoginState()
    data class Error(val message: String) : LoginState()
}
