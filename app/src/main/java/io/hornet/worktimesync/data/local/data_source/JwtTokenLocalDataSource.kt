package io.hornet.worktimesync.data.local.data_source

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import androidx.core.content.edit
import io.hornet.worktimesync.core.domain.model.JwtToken

class JwtTokenLocalDataSource(
    private val context: Context
) {
    companion object {
        private const val KEY_ACCESS_TOKEN = "encrypted_access_token"
        private const val KEY_REFRESH_TOKEN = "encrypted_refresh_token"
    }

    private val sharedPreferences: SharedPreferences by lazy {
        try {
            val masterKey = MasterKey.Builder(context)
                .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                .setRequestStrongBoxBacked(true)
                .build()

            EncryptedSharedPreferences.create(
                context,
                "secure_token_prefs",
                masterKey,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
        } catch (e: Exception) {
            context.getSharedPreferences("secure_token_prefs_fallback", Context.MODE_PRIVATE)
        }
    }

    fun getLocalJwtToken(): JwtToken? {
        val access = sharedPreferences.getString(KEY_ACCESS_TOKEN, null)
        val refresh = sharedPreferences.getString(KEY_REFRESH_TOKEN, null)

        if (access == null && refresh == null) return null

        return JwtToken(
            access_token = access,
            refresh_token = refresh,
            token_type = "bearer"
        )
    }

    fun saveLocalJwtToken(jwtToken: JwtToken) {
        sharedPreferences.edit {
            putString(KEY_ACCESS_TOKEN, jwtToken.access_token)
            putString(KEY_REFRESH_TOKEN, jwtToken.refresh_token)
        }
    }

    fun deleteJwtToken() {
        sharedPreferences.edit { clear() }
    }
    init{
        deleteJwtToken()
    }
}