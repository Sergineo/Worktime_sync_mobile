package io.hornet.worktimesync.core.domain.interactor

import io.hornet.worktimesync.core.domain.model.JwtToken

interface CoreTokenRepository {
    suspend fun getLocalToken(): JwtToken?
    suspend fun saveLocalToken(token: JwtToken)
    suspend fun refreshRemoteToken(oldToken: JwtToken): Result<JwtToken?>
}