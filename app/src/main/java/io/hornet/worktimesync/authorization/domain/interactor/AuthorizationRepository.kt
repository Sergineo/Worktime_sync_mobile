package io.hornet.worktimesync.authorization.domain.interactor

import io.hornet.worktimesync.authorization.domain.User
import io.hornet.worktimesync.authorization.domain.screen_model.AuthorizationFragment
import io.hornet.worktimesync.authorization.domain.screen_model.RegistrationFragment
import io.hornet.worktimesync.core.domain.model.JwtToken

interface AuthorizationRepository {
    suspend fun login(email: String, password: String): Result<JwtToken?>
    suspend fun registration(email: String, password: String): Result<User?>
    suspend fun saveLocalToken(jwtToken: JwtToken)
}