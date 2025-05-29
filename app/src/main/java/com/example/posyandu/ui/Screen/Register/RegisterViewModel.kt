package com.example.posyandu.ui.Screen.Register

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.posyandu.Data.Model.Request.JenisKelamin
import com.example.posyandu.Data.Model.Request.RegisterRequest
import com.example.posyandu.Data.Model.Response.PosyanduItem
import com.example.posyandu.Data.Remote.Client.ApiClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import org.json.JSONObject

open class RegisterViewModel : ViewModel() {
    val email = MutableStateFlow("")
    val name = MutableStateFlow("")
    val phone = MutableStateFlow("")

    var password = MutableStateFlow("")

    var nik = MutableStateFlow("")
    var no_kk = MutableStateFlow("")
    lateinit var jenisKelamin: JenisKelamin
    var tanggalLahir = MutableStateFlow("")

    var poskoId: Int? = null

    private val _registerState = MutableStateFlow<RegisterState>(RegisterState.Idle)
    val registerState: StateFlow<RegisterState> = _registerState

    fun saveRegisterInfo(email: String, name: String, phone: String) {
        this.email.value  = email
        this.name.value = name
        this.phone.value = phone
    }

    fun savePassword(password: String) {
        Log.d("TAG", "Saving password: $password")
        this.password.value = password
    }

    fun saveNik(nik: String) {
        this.nik.value = nik
    }

    fun saveNoKk(no_kk: String) {
        this.no_kk.value = no_kk
    }

    fun saveCompleteData(nik: String, no_kk: String, jenisKelamin: JenisKelamin, tanggalLahir: LocalDate, posko: Int) {
        this.nik.value = nik
        this.no_kk.value = no_kk
        this.jenisKelamin = jenisKelamin
        this.tanggalLahir.value = tanggalLahir.toString()
        this.poskoId = posko
    }

    fun register(password: String) {
        viewModelScope.launch {
            _registerState.value = RegisterState.Loading

            try {
                val response = ApiClient.apiService.registerWarga(
                    RegisterRequest(name.value, email.value, password, phone.value, nik.value, poskoId ?: 1, no_kk.value, jenisKelamin.value, tanggalLahir.value)
                )
                if (response.isSuccessful) {
                    _registerState.value = RegisterState.Success("Register Berhasil")
                } else {
                    val errorBody = response.errorBody()?.string()
                    Log.d("Register", "Error body: $errorBody")

                    val errorMessage = try {
                        val json = JSONObject(errorBody ?: "")
                        when {
                            json.has("message") -> {
                                json.getString("message")
                            }
                            json.has("errors") -> {
                                val errors = json.getJSONObject("errors")
                                val keys = errors.keys()
                                if (keys.hasNext()) {
                                    val firstKey = keys.next()
                                    val messages = errors.getJSONArray(firstKey)
                                    messages.getString(0)
                                } else {
                                    "Terjadi kesalahan"
                                }
                            }
                            else -> {
                                "Terjadi kesalahan"
                            }
                        }
                    } catch (e: Exception) {
                        "Terjadi kesalahan"
                    }
                    val errorCode = response.code()
                    Log.e("Register", "Gagal register: $errorCode - $errorBody")
                    _registerState.value = RegisterState.Error(errorMessage)
                }
            } catch (e: Exception) {
                Log.e("Register", "Exception saat register: ${e.message}", e)
                _registerState.value = RegisterState.Error("Terjadi kesalahan: ${e.message}")
            }
        }
    }

    private val _poskoState = MutableStateFlow<PoskoState>(PoskoState.Idle)
    val poskoState: StateFlow<PoskoState> = _poskoState

    fun posko() {
        viewModelScope.launch {
            _poskoState.value = PoskoState.Loading
            try {
                val response = ApiClient.apiService.getPosko()
                if (response.isSuccessful) {
                    val body = response.body()
                    Log.d("POSKO_DATA", "Data diterima: ${body?.data}")

                    if (body?.data != null) {
                        _poskoState.value = PoskoState.Success(body.data)
                    } else {
                        _poskoState.value = PoskoState.Error("Data kosong")
                    }
                } else {
                    val error = response.errorBody()?.string() ?: "Unknown error"
                    _poskoState.value = PoskoState.Error("Error ${response.code()}: $error")
                }
            } catch (e: Exception) {
                _poskoState.value = PoskoState.Error("Network error: ${e.message}")
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

sealed class PoskoState {
    object Idle : PoskoState()
    object Loading : PoskoState()
    data class Success(val poskoList: List<PosyanduItem>) : PoskoState()
    data class Error(val error: String) : PoskoState()
}