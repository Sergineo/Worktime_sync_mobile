package io.hornet.worktimesync.core.domain.exceptions

import io.hornet.worktimesync.R

data class DomainCoreException(
    val error: JwtTokerError,
    val description: Int = R.string.unknown
): Exception()
