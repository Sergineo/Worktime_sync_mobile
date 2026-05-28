package io.hornet.worktimesync.profile.domain.interactor

import io.hornet.worktimesync.profile.domain.model.UserProfile

interface ProfileRepository {
    suspend fun getProfileData(): Result<UserProfile>
}