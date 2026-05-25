package io.hornet.worktimesync.authorization.domain.interactor

import io.hornet.worktimesync.authorization.domain.User
import io.hornet.worktimesync.authorization.domain.screen_model.AuthorizationFragment
import io.hornet.worktimesync.authorization.domain.screen_model.RegistrationFragment
import io.hornet.worktimesync.core.domain.model.JwtToken

interface AuthorizationRepository {
    suspend fun login(authorizationFragment: AuthorizationFragment): Result<JwtToken?>
    suspend fun registration(registrationFragment: RegistrationFragment): Result<User?>
    suspend fun saveLocalToken(jwtToken: JwtToken)
}