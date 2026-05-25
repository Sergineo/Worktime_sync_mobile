package io.hornet.worktimesync.data.remote.api

import io.hornet.worktimesync.authorization.domain.User
import io.hornet.worktimesync.authorization.domain.screen_model.RegistrationFragment
import io.hornet.worktimesync.core.domain.model.JwtToken
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface JwtApi {
    @FormUrlEncoded
    @POST("auth/login")
    suspend fun login(
        @Field("username") email: String,
        @Field("password") password: String
    ): Call<JwtToken>

    @POST("auth/refresh")
    suspend fun refreshToken(
        @Body refreshToken: String
    ): Call<JwtToken>

    @POST("auth/registration")
    suspend fun registration(
        @Body request: RegistrationFragment
    ): Call<User>
}