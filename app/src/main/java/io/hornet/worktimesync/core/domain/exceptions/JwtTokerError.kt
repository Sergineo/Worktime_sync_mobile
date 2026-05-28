package io.hornet.worktimesync.core.domain.exceptions

interface JwtTokerError {
    object PleaceUnauthorized : JwtTokerError
    object UncorrectData: JwtTokerError
    object Unauthorized : JwtTokerError
    object NoInternet : JwtTokerError
    object Unknown : JwtTokerError
}