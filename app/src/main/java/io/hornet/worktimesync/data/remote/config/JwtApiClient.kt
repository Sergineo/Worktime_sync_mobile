package io.hornet.worktimesync.data.remote.config

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class JwtApiClient {
    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(3000, TimeUnit.SECONDS)
        .readTimeout(3000, TimeUnit.SECONDS)
        .writeTimeout(3000, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .build()
    private val jwtApiConfig: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://localhost:9000/v1/data/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    fun createJwtApiClientSession(): Retrofit = jwtApiConfig
}