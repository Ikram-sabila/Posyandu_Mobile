package com.example.posyandu.ui.Screen.Register

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.posyandu.Data.Model.Request.RegisterRequest
import com.example.posyandu.Data.Remote.Client.ApiClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

class RegisterViewModel : ViewModel() {
    var email: String = ""
    var name: String = ""
    var phone: String = ""

    var password: String = ""

    var nik: String = ""
    var no_kk: String = ""
    var jenisKelamin: String = ""
    var tanggalLahir: String = ""

    private val _registerState = MutableStateFlow<RegisterState>(RegisterState.Idle)
    val registerState: StateFlow<RegisterState> = _registerState

    suspend fun saveRegisterInfo(email: String, name: String, phone: String) {
        this.email = email
        this.name = name
        this.phone = phone
    }

    suspend fun savePassword(password: String) {
        Log.d("TAG", "Saving password: $password")
        this.password = password
    }

    fun saveCompleteData(nik: String, no_kk: String, jenisKelamin: String, tanggalLahir: LocalDate) {
        this.nik = nik
        this.no_kk = no_kk
        this.jenisKelamin = jenisKelamin
        this.tanggalLahir = tanggalLahir.toString()
    }

    fun register(password: String) {
        viewModelScope.launch {
            _registerState.value = RegisterState.Loading

            try {
                val response = ApiClient.apiService.registerWarga(
                    RegisterRequest(name, email, password, phone, nik, no_kk, jenisKelamin, tanggalLahir)
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