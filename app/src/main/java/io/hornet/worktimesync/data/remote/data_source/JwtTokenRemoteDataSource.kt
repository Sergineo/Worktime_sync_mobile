package io.hornet.worktimesync.data.remote.data_source

import android.util.Log
import io.hornet.worktimesync.authorization.domain.User
import io.hornet.worktimesync.core.domain.exceptions.DomainCoreException
import io.hornet.worktimesync.core.domain.exceptions.JwtTokerError
import io.hornet.worktimesync.core.domain.model.JwtToken
import io.hornet.worktimesync.data.remote.api.JwtApi
import io.hornet.worktimesync.data.remote.dto.RegistrationDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.io.IOException

class JwtTokenRemoteDataSource(
    private val jwtApi: JwtApi,
) {
    suspend fun login(email: String, password: String): Result<JwtToken?> {
        return withContext(Dispatchers.IO) {
            try {
                val response: Response<JwtToken> = jwtApi.login(
                    email = email,
                    password = password,
                    grantType = "password"
                )
                Log.d("NETWORK_START", "Отправка на сервер: $email")

                if (response.isSuccessful) {
                    val token = response.body()
                    if (token != null && !token.access_token.isNullOrBlank()) {
                        Result.success(token)
                    } else {
                        Result.failure(DomainCoreException(JwtTokerError.Unknown))
                    }
                } else {
                    val rawError = response.errorBody()?.string()
                    Log.e("HTTP_ERROR_BODY", "Код: ${response.code()} | Тело: $rawError")
                    when (response.code()) {
                        401, 422 -> Result.failure(DomainCoreException(JwtTokerError.Unauthorized))
                        else -> Result.failure(DomainCoreException(JwtTokerError.Unknown))
                    }
                }
            } catch (e: IOException) {
                Log.e("NETWORK_CRASH", "Ошибка сети: ${e.message}")
                Result.failure(DomainCoreException(JwtTokerError.NoInternet))
            } catch (e: Exception) {
                Log.e("NETWORK_CRASH", "Критический сбой: ${e.message}")
                Result.failure(e)
            }
        }
    }

    suspend fun refreshToken(jwtToken: JwtToken): Result<JwtToken> {
        return try {
            val response: Response<JwtToken> = jwtApi.refreshToken(
                jwtToken.refresh_token.toString()
            )
            if (response.isSuccessful) {
                val token = response.body()
                if (token != null) {
                    Result.success(token)
                } else {
                    Result.failure(DomainCoreException(JwtTokerError.Unknown))
                }
            } else {
                when (response.code()) {
                    401 -> Result.failure(DomainCoreException(JwtTokerError.Unauthorized))
                    else -> Result.failure(DomainCoreException(JwtTokerError.Unknown))
                }
            }
        } catch (e: IOException) {
            Result.failure(DomainCoreException(JwtTokerError.NoInternet))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun registration(fragment: RegistrationDto): Result<User?> {
        return try {
            val response: Response<User> = jwtApi.registration(fragment)

            if (response.isSuccessful) {
                val user = response.body()
                if (user != null) {
                    Result.success(user)
                } else {
                    Result.failure(DomainCoreException(JwtTokerError.Unknown))
                }
            } else {
                when (response.code()) {
                    401 -> Result.failure(DomainCoreException(JwtTokerError.Unauthorized))
                    else -> Result.failure(DomainCoreException(JwtTokerError.Unknown))
                }
            }
        } catch (e: IOException) {
            Result.failure(DomainCoreException(JwtTokerError.NoInternet))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}