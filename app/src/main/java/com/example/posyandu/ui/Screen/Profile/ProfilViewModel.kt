package com.example.posyandu.ui.Screen.Profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.posyandu.Data.Model.Request.PortalProfileRequest
import com.example.posyandu.Data.Model.Request.UpdateEmailRequest
import com.example.posyandu.Data.Model.Request.UpdatePasswordRequest
import com.example.posyandu.Data.Model.Response.PortalProfileResponseData
import com.example.posyandu.Data.Model.Response.UpdateEmailResponse
import com.example.posyandu.Data.Model.Response.UpdatePasswordResponse
import com.example.posyandu.Data.Model.Response.WargaResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfilViewModel(private val repository: ProfileRepository) : ViewModel() {

    var updateState by mutableStateOf<UpdateProfileState>(UpdateProfileState.Idle)
        private set

    fun updateProfile(request: PortalProfileRequest, id: Int, token: String) {
        viewModelScope.launch {
            updateState = UpdateProfileState.Loading

            val result = repository.updateProfile(request, id, token)
            updateState = result.fold(
                onSuccess = { data -> UpdateProfileState.Success(data) },
                onFailure = { e -> UpdateProfileState.Error(e.message ?: "Terjadi kesalahan") }
            )
        }
    }

    private val _profileState = MutableStateFlow<GetProfileState>(GetProfileState.Idle)
    val profileState: StateFlow<GetProfileState> = _profileState.asStateFlow()

    fun getProfile(token: String, id: Int) {
        viewModelScope.launch {
            _profileState.value = GetProfileState.Loading
            val result = repository.getProfile(token, id)
            _profileState.value = when {
                result.isSuccess -> GetProfileState.Success(result.getOrThrow())
                result.isFailure -> GetProfileState.Error(result.exceptionOrNull()?.message ?: "Terjadi kesalahan")
                else -> GetProfileState.Idle
            }
        }
    }

    fun resetState() {
        updateState = UpdateProfileState.Idle
    }

    var updateEmailState by mutableStateOf<UpdateEmailState>(UpdateEmailState.Idle)
        private set

    fun updateEmail(request: UpdateEmailRequest, id: Int, token: String) {
        viewModelScope.launch {
            updateEmailState = UpdateEmailState.Loading

            val result = repository.updateEmail(request, id, token)
            updateEmailState = result.fold(
                onSuccess = { data -> UpdateEmailState.Success(data) },
                onFailure = { e -> UpdateEmailState.Error(e.message ?: "Terjadi kesalahan") }
            )
        }
    }

    fun resetEmailState() {
        updateEmailState = UpdateEmailState.Idle
    }

    var updatePasswordState by mutableStateOf<UpdatePasswordState>(UpdatePasswordState.Idle)
        private set

    fun updatePassword(request: UpdatePasswordRequest, id: Int, token: String) {
        viewModelScope.launch {
            updatePasswordState = UpdatePasswordState.Loading

            val result = repository.updatePassword(request, id, token)
            updatePasswordState = result.fold(
                onSuccess = { data -> UpdatePasswordState.Success(data) },
                onFailure = { e -> UpdatePasswordState.Error(e.message ?: "Terjadi kesalahan") }
            )
        }
    }

    fun resetPasswordState() {
        updatePasswordState = UpdatePasswordState.Idle
    }
}

sealed class UpdateProfileState {
    object Idle : UpdateProfileState()
    object Loading : UpdateProfileState()
    data class Success(val data: PortalProfileResponseData) : UpdateProfileState()
    data class Error(val message: String) : UpdateProfileState()
}

sealed class GetProfileState {
    object Idle : GetProfileState()
    object Loading : GetProfileState()
    data class Success(val data: WargaResponse) : GetProfileState()
    data class Error(val message: String) : GetProfileState()
}

sealed class UpdateEmailState {
    object Idle : UpdateEmailState()
    object Loading : UpdateEmailState()
    data class Success(val data: UpdateEmailResponse) : UpdateEmailState()
    data class Error(val message: String) : UpdateEmailState()
}

sealed class UpdatePasswordState {
    object Idle : UpdatePasswordState()
    object Loading : UpdatePasswordState()
    data class Success(val data: UpdatePasswordResponse) : UpdatePasswordState()
    data class Error(val message: String) : UpdatePasswordState()
}