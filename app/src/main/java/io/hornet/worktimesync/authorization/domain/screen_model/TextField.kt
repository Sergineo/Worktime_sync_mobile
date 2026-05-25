package io.hornet.worktimesync.authorization.domain.screen_model

data class TextField(
    val message: String? = "",
    var isError: Boolean = false,
    val errorDescription: Int? = null
)