package io.hornet.worktimesync.profile.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.hornet.worktimesync.profile.domain.interactor.ProfileInteractor
import io.hornet.worktimesync.profile.domain.screen_model.ProfileUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(private val interactor: ProfileInteractor) : ViewModel() {

    private val _uiState = MutableStateFlow<ProfileUiState>(ProfileUiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun loadProfileData() {
        viewModelScope.launch {
            _uiState.value = ProfileUiState.Loading
            interactor.execute()
                .onSuccess { formattedProfile ->
                    _uiState.value = ProfileUiState.Success(formattedProfile)
                }
                .onFailure { error ->
                    _uiState.value = ProfileUiState.Error(error.localizedMessage ?: "Ошибка загрузки")
                }
        }
    }
    init {
        loadProfileData()
    }
}