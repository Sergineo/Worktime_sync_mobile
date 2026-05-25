package io.hornet.worktimesync.core.domain.exceptions

interface JwtTokerError {
    object Unauthorized : JwtTokerError // Сюда попадает 401
    object NoInternet : JwtTokerError
    object Unknown : JwtTokerError
}