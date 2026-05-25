package io.hornet.worktimesync.data.remote.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class RegistrationDto(
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String
)