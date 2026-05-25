package io.hornet.worktimesync.core.domain.interactor

import io.hornet.worktimesync.core.domain.exceptions.DomainCoreException
import io.hornet.worktimesync.core.domain.exceptions.JwtTokerError
import io.hornet.worktimesync.core.domain.model.JwtToken

class NavigationInteractor(
    private val coreTokenRepository: CoreTokenRepository
) {
    suspend fun defineNavRootStartDestination(): Result<JwtToken?> {
        val localToken = coreTokenRepository.getLocalToken()
        return if (localToken != null) {
            Result.success(localToken)
        } else {
            Result.failure(DomainCoreException(JwtTokerError.Unauthorized))
        }
    }
    suspend fun saveLocalToken(jwtToken: JwtToken){
        coreTokenRepository.saveLocalToken(jwtToken)
    }
}