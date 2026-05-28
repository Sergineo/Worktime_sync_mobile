package io.hornet.worktimesync.profile.domain.screen_model

import io.hornet.worktimesync.profile.domain.model.FormattedProfile

sealed interface ProfileUiState {
    object Loading : ProfileUiState
    data class Success(val profile: FormattedProfile) : ProfileUiState
    data class Error(val message: String) : ProfileUiState
}