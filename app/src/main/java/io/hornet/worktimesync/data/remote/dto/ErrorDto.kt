package io.hornet.worktimesync.data.remote.dto

data class AuthorizationError(
    val detail: List<ValidationErrorDetail>? = null
)

data class ValidationErrorDetail(
    val loc: List<Any>? = null,
    val msg: String? = null,
    val type: String? = null,
    val input: Any? = null
)