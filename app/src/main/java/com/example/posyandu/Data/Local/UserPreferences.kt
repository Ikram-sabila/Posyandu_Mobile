package com.example.posyandu.Data.Local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "user_prefs")
object UserPreferences {
    private val TOKEN_KEY = stringPreferencesKey("auth_token")
    private val NIK_KEY = stringPreferencesKey("nik")
    private val NO_KK_KEY = stringPreferencesKey("no_kk")
    private val USER_ID_KEY = intPreferencesKey("user_id")
    private val NAMA_KEY = stringPreferencesKey("nama_lengkap")
    private val EMAIL_KEY = stringPreferencesKey("email")
    private val NO_TELP_KEY = stringPreferencesKey("no_telp")

    suspend fun saveUserData(context: Context, token: String, nik: String, noKK: String, userId: Int, nama: String, email: String, no_telp: String) {
        context.dataStore.edit { prefs ->
            prefs[TOKEN_KEY] = token
            prefs[NIK_KEY] = nik
            prefs[NO_KK_KEY] = noKK
            prefs[USER_ID_KEY] = userId
            prefs[NAMA_KEY] = nama
            prefs[EMAIL_KEY] = email
            prefs[NO_TELP_KEY] = no_telp
        }
    }

    fun getToken(context: Context): Flow<String?> = context.dataStore.data.map { it[TOKEN_KEY] }
    fun getNik(context: Context): Flow<String?> = context.dataStore.data.map { it[NIK_KEY] }
    fun getNoKK(context: Context): Flow<String?> = context.dataStore.data.map { it[NO_KK_KEY] }
    fun getUserId(context: Context): Flow<Int?> = context.dataStore.data.map { it[USER_ID_KEY] }
    fun getNama(context: Context): Flow<String?> = context.dataStore.data.map { it[NAMA_KEY] }
    fun getEmail(context: Context): Flow<String?> = context.dataStore.data.map { it[EMAIL_KEY] }
    fun getNoTelp(context: Context): Flow<String?> = context.dataStore.data.map { it[NO_TELP_KEY] }


    suspend fun clearUserData(context: Context) {
        context.dataStore.edit {
            it.remove(TOKEN_KEY)
            it.remove(NIK_KEY)
            it.remove(NO_KK_KEY)
            it.remove(USER_ID_KEY)
            it.remove(NAMA_KEY)
            it.remove(EMAIL_KEY)
            it.remove(NO_TELP_KEY)
        }
    }
}