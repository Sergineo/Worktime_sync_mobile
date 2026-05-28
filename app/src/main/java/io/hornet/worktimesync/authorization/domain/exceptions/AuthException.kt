package io.hornet.worktimesync.authorization.domain.exceptions

data class AuthException(
    val exception: ValidationError,
    val description: Int? = null
): Exception()