package io.hornet.worktimesync.core.domain.model

data class JwtToken(
    val accessToken: String? = null,
    val refreshToken: String? = null
)