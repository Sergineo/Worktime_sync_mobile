package io.hornet.worktimesync.authorization.domain.exceptions

sealed interface ValidationError {
    object ErrorAuthorization : ValidationError
    object ErrorRegistration : ValidationError
    object NeedToRegistration : ValidationError
}