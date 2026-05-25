package io.hornet.worktimesync.authorization.domain

data class User(
    val id: Int? = null,
    val email: String? = null,
    val role: String? = null,
    val is_active: Boolean? = null
)