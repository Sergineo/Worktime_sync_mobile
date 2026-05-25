package io.hornet.worktimesync.data.repository

import io.hornet.worktimesync.core.domain.interactor.CoreTokenRepository
import io.hornet.worktimesync.core.domain.model.JwtToken
import io.hornet.worktimesync.data.local.data_source.JwtTokenLocalDataSource
import io.hornet.worktimesync.data.remote.data_source.JwtTokenRemoteDataSource

class CoreTokenRepositoryImpl(
    private val jwtTokenRemoteDataSource: JwtTokenRemoteDataSource,
    private val jwtTokenLocalDataSource: JwtTokenLocalDataSource
): CoreTokenRepository {
    override suspend fun getLocalToken(): JwtToken? {
        return jwtTokenLocalDataSource.getLocalJwtToken()
    }

    override suspend fun saveLocalToken(token: JwtToken) {
        jwtTokenLocalDataSource.saveLocalJwtToken(token)
    }

    override suspend fun refreshRemoteToken(oldToken: JwtToken): Result<JwtToken?> {
        return jwtTokenRemoteDataSource.refreshToken(oldToken)
    }
}