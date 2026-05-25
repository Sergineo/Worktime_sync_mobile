package io.hornet.worktimesync.core.presentaition.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.hornet.worktimesync.R
import io.hornet.worktimesync.core.domain.interactor.NavigationInteractor
import io.hornet.worktimesync.core.domain.exceptions.DomainCoreException
import io.hornet.worktimesync.core.domain.exceptions.JwtTokerError
import io.hornet.worktimesync.core.domain.screen_model.NavigationScreenEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NavViewModel(
    private val navigationInteractor: NavigationInteractor
) : ViewModel() {
    val _mainViewModel =
        MutableStateFlow<NavigationScreenEvent>(NavigationScreenEvent.AuthorizationScreenPoint)
    val mainViewModel: StateFlow<NavigationScreenEvent> = _mainViewModel.asStateFlow()

    val _mainErrorViewModel = MutableStateFlow<DomainCoreException?>(null)
    val mainErrorViewModel: SharedFlow<DomainCoreException?> = _mainErrorViewModel.asSharedFlow()


    fun getStartRouting() {
        viewModelScope.launch {
            val result = navigationInteractor.defineNavRootStartDestination()
            result.onSuccess {
                navigationInteractor.saveLocalToken(result.getOrNull()!!)
                _mainViewModel.update { NavigationScreenEvent.ProfileScreenPoint }
            }
            result.onFailure {
                _mainViewModel.update { NavigationScreenEvent.AuthorizationScreenPoint }
                val authError = (it as? DomainCoreException)?.error
                when (authError) {
                    is JwtTokerError.Unknown -> {
                        _mainErrorViewModel.emit(
                            DomainCoreException(
                                JwtTokerError.Unknown,
                                R.string.unknown
                            )
                        )
                    }

                    is JwtTokerError.Unauthorized -> {
                        _mainErrorViewModel.emit(
                            DomainCoreException(
                                JwtTokerError.Unauthorized,
                                R.string.need_to_registration
                            )
                        )
                    }

                    is JwtTokerError.NoInternet -> {
                        _mainErrorViewModel.emit(
                            DomainCoreException(
                                JwtTokerError.NoInternet,
                                R.string.no_internet_connection
                            )
                        )
                    }
                }
            }
        }
    }

    init {
        getStartRouting()
    }
}