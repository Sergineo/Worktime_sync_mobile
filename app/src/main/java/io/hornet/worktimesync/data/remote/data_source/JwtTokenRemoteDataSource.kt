package io.hornet.worktimesync.data.remote.data_source

import io.hornet.worktimesync.authorization.domain.User
import io.hornet.worktimesync.authorization.domain.screen_model.AuthorizationFragment
import io.hornet.worktimesync.authorization.domain.screen_model.RegistrationFragment
import io.hornet.worktimesync.core.domain.exceptions.DomainCoreException
import io.hornet.worktimesync.core.domain.model.JwtToken
import io.hornet.worktimesync.core.domain.exceptions.JwtTokerError
import io.hornet.worktimesync.data.remote.api.JwtApi
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume

class JwtTokenRemoteDataSource(
    private val jwtApi: JwtApi,
) {
    suspend fun login(authorizationFragment: AuthorizationFragment): Result<JwtToken?> {
        val jwtCall: Call<JwtToken> = jwtApi.login(
            authorizationFragment.email.message.toString(),
            authorizationFragment.password.message.toString()
        )
        return suspendCancellableCoroutine { continuation ->
            jwtCall.enqueue(object : Callback<JwtToken?> {
                override fun onResponse(call: Call<JwtToken?>, response: Response<JwtToken?>) {
                    if (response.isSuccessful) {
                        val token = response.body() ?: return continuation.resume(
                            Result.failure(
                                DomainCoreException(JwtTokerError.Unknown)
                            )
                        )
                        if (response.code() == 401) {
                            continuation.resume(
                                Result.failure(
                                    DomainCoreException(
                                        JwtTokerError.Unauthorized
                                    )
                                )
                            )
                        } else {
                            continuation.resume(Result.success(token))
                        }
                    } else {
                        continuation.resume(
                            Result.failure(
                                DomainCoreException(
                                    JwtTokerError.NoInternet
                                )
                            )
                        )
                    }
                }

                override fun onFailure(
                    call: Call<JwtToken?>, t: Throwable
                ) {
                    continuation.resume(Result.failure(t))
                }
            })
        }
    }

    suspend fun refreshToken(jwtToken: JwtToken): Result<JwtToken>{
        val jwtCall: Call<JwtToken> = jwtApi.refreshToken(jwtToken.refreshToken.toString())
        return suspendCancellableCoroutine { continuation ->
            jwtCall.enqueue(object : Callback<JwtToken?> {
                override fun onResponse(call: Call<JwtToken?>, response: Response<JwtToken?>) {
                    if (response.isSuccessful) {
                        val token = response.body() ?: return continuation.resume(
                            Result.failure(
                                DomainCoreException(JwtTokerError.Unknown)
                            )
                        )
                        if (response.code() == 401) {
                            continuation.resume(
                                Result.failure(
                                    DomainCoreException(
                                        JwtTokerError.Unauthorized
                                    )
                                )
                            )
                        } else {
                            continuation.resume(Result.success(token))
                        }
                    } else {
                        continuation.resume(
                            Result.failure(
                                DomainCoreException(
                                    JwtTokerError.NoInternet
                                )
                            )
                        )
                    }
                }

                override fun onFailure(
                    call: Call<JwtToken?>, t: Throwable
                ) {
                    continuation.resume(Result.failure(t))
                }
            })
        }
    }

    suspend fun registration(fragment: RegistrationFragment): Result<User?> {
        val jwtCall: Call<User> = jwtApi.registration(fragment)
        return suspendCancellableCoroutine { continuation ->
            jwtCall.enqueue(object : Callback<User?> {
                override fun onResponse(call: Call<User?>, response: Response<User?>) {
                    if (response.isSuccessful) {
                        val token = response.body() ?: return continuation.resume(
                            Result.failure(
                                DomainCoreException(JwtTokerError.Unknown)
                            )
                        )
                        if (response.code() == 401) {
                            continuation.resume(
                                Result.failure(
                                    DomainCoreException(
                                        JwtTokerError.Unauthorized
                                    )
                                )
                            )
                        } else {
                            continuation.resume(Result.success(token))
                        }
                    } else {
                        continuation.resume(
                            Result.failure(
                                DomainCoreException(
                                    JwtTokerError.NoInternet
                                )
                            )
                        )
                    }
                }

                override fun onFailure(
                    call: Call<User?>, t: Throwable
                ) {
                    continuation.resume(Result.failure(t))
                }
            })
        }
    }
}