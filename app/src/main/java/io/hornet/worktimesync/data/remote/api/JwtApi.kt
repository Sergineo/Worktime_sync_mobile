package io.hornet.worktimesync.data.remote.api

import io.hornet.worktimesync.authorization.domain.User
import io.hornet.worktimesync.core.domain.model.JwtToken
import io.hornet.worktimesync.data.remote.dto.RegistrationDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface JwtApi {
    @FormUrlEncoded
    @POST("api/auth/login")
    suspend fun login(
        @Field("username") email: String,
        @Field("password") password: String,
        @Field("grant_type") grantType: String
    ): Response<JwtToken>

    @POST("auth/refresh")
    suspend fun refreshToken(
        @Body refreshToken: String
    ): Response<JwtToken>

    @POST("api/users/register")
    suspend fun registration(
        @Body request: RegistrationDto
    ): Response<User>
}