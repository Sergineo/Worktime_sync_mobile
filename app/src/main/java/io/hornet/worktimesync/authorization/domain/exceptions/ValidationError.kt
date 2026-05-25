package io.hornet.worktimesync.authorization.domain.exceptions

sealed interface ValidationError {
    data class ErrorAuthorization(val errorDescription: Int) : ValidationError
    data class ErrorRegistration(val errorDescription: Int) : ValidationError
}