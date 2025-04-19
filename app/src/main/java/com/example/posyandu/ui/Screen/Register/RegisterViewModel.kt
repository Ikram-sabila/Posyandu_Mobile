package com.example.posyandu.ui.Screen.Register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.posyandu.Data.Model.Request.RegisterRequest
import com.example.posyandu.Data.Remote.Client.ApiClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RegisterViewModel: ViewModel() {
    private val _registerState = MutableStateFlow<RegisterState>(RegisterState.Idle)
    val registerState: StateFlow<RegisterState> = _registerState

    fun register(username: String, email: String, password: String) {
        viewModelScope.launch {
            _registerState.value = RegisterState.Loading

            try {
                val response = ApiClient.apiService.registerUser(
                    RegisterRequest(username, email, password)
                )
                if (response.isSuccessful) {
                    _registerState.value = RegisterState.Success("Register Berhasil")
                } else {
                    val errorBody = response.errorBody()?.string()
                    val errorCode = response.code()
                    _registerState.value = RegisterState.Error("Register gagal: $errorCode - $errorBody")
                }
            } catch (e: Exception) {
                _registerState.value = RegisterState.Error("Terjadi kesalahan: ${e.message}")
            }
        }
    }
}

sealed class RegisterState {
    object Idle: RegisterState()
    object Loading: RegisterState()
    data class Success(val message: String): RegisterState()
    data class Error(val error: String): RegisterState()
}