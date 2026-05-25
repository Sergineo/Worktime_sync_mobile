package io.hornet.worktimesync.core.domain.exceptions

data class DomainCoreException(
    val error: JwtTokerError,
    val description: Int? = null
): Exception()
