package io.hornet.worktimesync.core.domain.model

import com.google.gson.annotations.SerializedName

data class JwtToken(
    val access_token: String? = null,
    val refresh_token: String? = null,
    val token_type: String? = null
)