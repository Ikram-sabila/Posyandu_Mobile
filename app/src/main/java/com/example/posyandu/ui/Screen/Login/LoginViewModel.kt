package com.example.posyandu.ui.Screen.Login

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.posyandu.Data.Model.Request.LoginRequest
import com.example.posyandu.Data.Model.Response.WargaData
import com.example.posyandu.Data.Remote.Client.ApiClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    var email = mutableStateOf("")
    var password = mutableStateOf("")

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState

    fun login() {
        viewModelScope.launch {
            _loginState.value = LoginState.Loading

            try {
                val response = ApiClient.apiService.loginWarga(
                    LoginRequest(email.value, password.value)
                )
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    if (loginResponse != null) {
                        val user = loginResponse.user
                        if (user != null) {
                            _loginState.value = LoginState.Success(
                                token = loginResponse.access_token,
                                user = user
                            )
                        } else {
                            _loginState.value = LoginState.Error("Data user kosong")
                        }
                    } else {
                        _loginState.value = LoginState.Error("Response kosong")
                    }
                } else {
                    val errorBody = response.errorBody()?.string()
                    val errorCode = response.code()
                    Log.e("Login", "Gagal login: $errorCode - $errorBody")

                    _loginState.value = LoginState.Error("Login gagal: $errorCode - $errorBody")
                }
            } catch (e: Exception) {
                Log.e("Login", "Exception saat login: ${e.message}", e)
                _loginState.value = LoginState.Error("Terjadi kesalahan: ${e.message}")
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