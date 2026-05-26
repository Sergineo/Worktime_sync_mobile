package io.hornet.worktimesync.data.repository

import io.hornet.worktimesync.authorization.domain.User
import io.hornet.worktimesync.authorization.domain.interactor.AuthorizationRepository
import io.hornet.worktimesync.authorization.domain.screen_model.AuthorizationFragment
import io.hornet.worktimesync.authorization.domain.screen_model.RegistrationFragment
import io.hornet.worktimesync.core.domain.model.JwtToken
import io.hornet.worktimesync.data.local.data_source.JwtTokenLocalDataSource
import io.hornet.worktimesync.data.remote.data_source.JwtTokenRemoteDataSource
import io.hornet.worktimesync.data.remote.mapper.toDto

class AuthorizationRepositoryImpl(
    private val jwtTokenRemoteDataSource: JwtTokenRemoteDataSource,
    private val jwtTokenLocalDataSource: JwtTokenLocalDataSource
): AuthorizationRepository {
    override suspend fun login(email: String, password: String): Result<JwtToken?> {
        return jwtTokenRemoteDataSource.login(email, password)
    }

    override suspend fun registration(registrationFragment: RegistrationFragment): Result<User?> {
        return jwtTokenRemoteDataSource.registration(
            registrationFragment.toDto()
        )
    }

    override suspend fun saveLocalToken(jwtToken: JwtToken) {
        jwtTokenLocalDataSource.saveLocalJwtToken(jwtToken)
    }
}